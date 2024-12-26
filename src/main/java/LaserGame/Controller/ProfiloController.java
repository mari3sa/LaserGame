package LaserGame.Controller;


import LaserGame.Services.InfoLaserGameService;
import LaserGame.Services.UtenteService;
import LaserGame.Utils.UtenteRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import LaserGame.Entities.Utente;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/public/profilo")
public class ProfiloController {

    @Autowired
    private UtenteService utenteService;

    @Autowired
    private InfoLaserGameService infoLaserGame;

    @GetMapping("/user")
    public ResponseEntity<?> getUtente() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String email = userDetails.getUsername();
            Optional<Utente> utente = utenteService.findByEmail(email);
            if (utente != null) {
                return ResponseEntity.ok(utente);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }
    @GetMapping("/userEmail")
    public ResponseEntity<?> getUtentebyEmail(@RequestParam String email) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null && authentication.isAuthenticated()) {
                String username = authentication.getName();
                boolean isAdmin = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"));
                if (email.equals(username) || isAdmin) {
                    Optional<Utente> utenteOpt = utenteService.findByEmail(email);
                    if (utenteOpt.isPresent()) {
                        return ResponseEntity.ok(utenteOpt.get());
                    } else {
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("message", "Utente non trovato."));
                    }
                }else {
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Collections.singletonMap("message", "Non sei autorizzato."));
                }
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Collections.singletonMap("message", "Non sei autenticato. Effettua il login per visualizzare gli abbonamenti."));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/verificaCodice")
    public boolean verificaCodice(@RequestParam int codice) {
        return infoLaserGame.verificaCodiceAmministratore(codice);
    }

    @PostMapping("/creaUtente")
    public ResponseEntity<String> creaUtente(@RequestBody UtenteRequest utenteRequest) {
        try {
            utenteService.creaUtente(
                    utenteRequest.getNome(),
                    utenteRequest.getCognome(),
                    utenteRequest.getEmail(),
                    utenteRequest.getTelefono()
            );
            return ResponseEntity.ok("Utente creato con successo.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Errore nella creazione dell'utente.");
        }
    }

    @PostMapping("/creaAmministratore")
    public ResponseEntity<String> creaAmministratore(@RequestBody UtenteRequest utenteRequest) {
        try {
            utenteService.creaAmministratore(
                    utenteRequest.getNome(),
                    utenteRequest.getCognome(),
                    utenteRequest.getEmail(),
                    utenteRequest.getTelefono(),
                    utenteRequest.getPassword()
            );
            return ResponseEntity.ok("Amministratore creato con successo.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Errore nella creazione dell'utente.");
        }
    }

}


