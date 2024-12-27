package LaserGame.Controller;



import LaserGame.Entities.SlotOrario;
import LaserGame.Services.SlotOrarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/public/fasce-orarie")
public class SlotOrarioController {

    @Autowired
    private SlotOrarioService slotOrarioService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getFasciaOraria(@PathVariable int id) {
        try {
            SlotOrario fasciaOraria = slotOrarioService.findById(id);
            return ResponseEntity.ok(fasciaOraria);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/crea")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> creaFascia(@RequestParam Long modalitaId, @RequestParam LocalDateTime dataInizio, @RequestParam Integer orariDisponibili) {
        try {
            boolean isAdmin = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"));
            if (!isAdmin) {
                throw new SecurityException("Utente non autorizzato");
            }
            SlotOrario creata = slotOrarioService.creaSlotOrario(modalitaId, dataInizio, orariDisponibili);
            return ResponseEntity.ok(creata);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    @GetMapping("/lista-fasce-orarie")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getAllFasceOrarie() {
        try {
            boolean isAdmin = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"));
            if (!isAdmin) {
                throw new SecurityException("Utente non autorizzato");
            }
            List<SlotOrario> fasceOrarie = slotOrarioService.findAll();
            return ResponseEntity.ok(fasceOrarie);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/nuova-fascia")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> creaFasciaOraria(@RequestBody SlotOrario fasciaOraria) {
        try {
            boolean isAdmin = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"));
            if (!isAdmin) {
                throw new SecurityException("Utente non autorizzato");
            }
            SlotOrario createdFasciaOraria = slotOrarioService.aggiungiFasciaOraria(fasciaOraria);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdFasciaOraria);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/prenota")
    public ResponseEntity<?> prenotaFasciaOraria(@RequestBody SlotOrario fasciaOraria) {
        try {
            SlotOrario prenotataFasciaOraria = slotOrarioService.prenotaFasciaOraria(fasciaOraria);
            return ResponseEntity.ok(prenotataFasciaOraria);
        } catch (Exception e) {
            e.printStackTrace(); // Stampa l'eccezione per il debug
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
