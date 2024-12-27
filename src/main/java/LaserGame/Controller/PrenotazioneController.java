package LaserGame.Controller;

import LaserGame.Entities.Modalita;
import LaserGame.Entities.Prenotazione;
import LaserGame.Services.PrenotazioneService;
import LaserGame.Utils.enumeration.StatoPrenotazione;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/prenotazioni")
public class PrenotazioneController {

    @Autowired
    private PrenotazioneService prenotazioneService;


    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getPrenotazione( @PathVariable int id) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null && authentication.isAuthenticated()) {
                boolean isAdmin = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"));
                if (isAdmin) {
                    Prenotazione prenotazione = prenotazioneService.findById(id);
                    return ResponseEntity.ok(prenotazione);
                } else {
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Collections.singletonMap("message", "Non sei autorizzato."));
                }
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Collections.singletonMap("message", "Non sei autenticato. Effettua il login per visualizzare prenotazioni."));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // Crea una nuova prenotazione
    @PostMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> creaPrenotazione(@RequestBody Prenotazione prenotazione) {
        try {
            Prenotazione nuovaPrenotazione = prenotazioneService.creaPrenotazione(prenotazione);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuovaPrenotazione);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Errore durante la creazione della prenotazione.");
        }
    }

    @GetMapping("/cliente/{clienteId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getPrenotazioniByCliente( @PathVariable int clienteId) {
        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null && authentication.isAuthenticated()) {
                boolean isAdmin = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"));
                if (isAdmin) {
                    List<Prenotazione> prenotazioni = prenotazioneService.findByCliente(clienteId);
                    return ResponseEntity.ok(prenotazioni);
                } else {
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Collections.singletonMap("message", "Non sei autorizzato."));
                }
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Collections.singletonMap("message", "Non sei autenticato. Effettua il login per visualizzare prenotazioni."));
            }
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }


    // Recupera prenotazioni per data
    @GetMapping("/data/{data}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getPrenotazioniByData(@PathVariable String data) {
        try {
            LocalDateTime dataTime = LocalDateTime.parse(data);
            List<Prenotazione> prenotazioni = prenotazioneService.getPrenotazioniByData(dataTime);
            return ResponseEntity.ok(prenotazioni);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Formato data non valido. Utilizza ISO-8601.");
        }
    }

    // Recupera prenotazioni per stato
    @GetMapping("/stato/{stato}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getPrenotazioniByStato(@PathVariable StatoPrenotazione stato) {
        try {
            List<Prenotazione> prenotazioni = prenotazioneService.getPrenotazioniByStatus(stato);
            return ResponseEntity.ok(prenotazioni);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    // Recupera prenotazioni per modalità
    @GetMapping("/modalita/{modalitaId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getPrenotazioniByModalita(@PathVariable Long modalitaId) {
        try {
            List<Prenotazione> prenotazioni = prenotazioneService.findByModalita(new Modalita(modalitaId));
            return ResponseEntity.ok(prenotazioni);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    // Recupera prenotazioni per numero massimo di partecipanti
    @GetMapping("/partecipanti/{numeroPartecipanti}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getPrenotazioniByNumeroPartecipanti(@PathVariable Integer numeroPartecipanti) {
        try {
            List<Prenotazione> prenotazioni = prenotazioneService.findByNumeroPartecipantiLessThanEqual(numeroPartecipanti);
            return ResponseEntity.ok(prenotazioni);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("tutte-prenotazioni")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getAllPrenotazioni() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null && authentication.isAuthenticated()) {
                boolean isAdmin = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"));
                if (isAdmin) {
                    List<Prenotazione> prenotazioni = prenotazioneService.findAll();
                    return ResponseEntity.ok(prenotazioni);
                } else {
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Collections.singletonMap("message", "Non sei autorizzato."));
                }
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Collections.singletonMap("message", "Non sei autenticato. Effettua il login per visualizzare prenotazioni."));
            }
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
