package LaserGame.Controller;


import LaserGame.Entities.*;
import LaserGame.Exception.DatiAziendaException;
import LaserGame.Services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/public/amministrazione")
public class LaserController {

    @Autowired
    private InfoLaserGameService infoLaserGameService;

    @Autowired
    private UtenteService utenteService;

    @Autowired
    private ModalitaService modalitaService;

    @Autowired
    private PrenotazioneService prenotazioneService;

    private List<String> getColumnNamesAmministratori(List<Utente> amministratori) {
        List<String> columnNames = new ArrayList<>();
        if (amministratori != null && !amministratori.isEmpty()) {
            Class<?> clazz = amministratori.get(0).getClass();
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                columnNames.add(field.getName());
            }
        }
        return columnNames;
    }


    private List<String> getColumnNamesModalita(List<Modalita> modalita) {
        List<String> columnNames = new ArrayList<>();
        if (modalita != null && !modalita.isEmpty()) {
            Class<?> clazz = modalita.get(0).getClass();
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                columnNames.add(field.getName());
            }
        }
        return columnNames;
    }

    private List<String> getColumnNamesPrenotazione(List<Prenotazione> prenotazioni) {
        List<String> columnNames = new ArrayList<>();
        if (prenotazioni != null && !prenotazioni.isEmpty()) {
            Class<?> clazz = prenotazioni.get(0).getClass();
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                columnNames.add(field.getName());
            }
        }
        return columnNames;
    }

    private List<String> getColumnNamesUtenti(List<Utente> utenti) {
        List<String> columnNames = new ArrayList<>();
        if (utenti != null && !utenti.isEmpty()) {
            Class<?> clazz = utenti.get(0).getClass();
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                columnNames.add(field.getName());
            }
        }
        return columnNames;
    }


    @GetMapping("/pacchetti")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getModalita() {
        try {
            boolean isAdmin = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"));
            if (!isAdmin) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Non sei autorizzato");
            }
            List<Modalita> modalita = modalitaService.findAll();
            List<String> columnNames = getColumnNamesModalita(modalita);
            Map<String, Object> response = new HashMap<>();
            response.put("data", modalita);
            response.put("columns", columnNames);
            return ResponseEntity.ok(response);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Impossibile effettuare l'operazione");
        }
    }

    @GetMapping("/utenti")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getUtenti() {
        try {
            boolean isAdmin = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"));
            if (!isAdmin) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Non sei autorizzato");
            }
            List<Utente> utente = utenteService.findUserUtente();
            List<String> columnNames = getColumnNamesUtenti(utente);
            Map<String, Object> response = new HashMap<>();
            response.put("data", utente);
            response.put("columns", columnNames);
            return ResponseEntity.ok(response);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Impossibile effettuare l'operazione");
        }
    }

    @GetMapping("/amministratori")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getAmministratori() {
        try {
            boolean isAdmin = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"));
            if (!isAdmin) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Non sei autorizzato");
            }
            List<Utente> amministratori = utenteService.findAmministratoreUtente();
            List<String> columnNames = getColumnNamesAmministratori(amministratori);
            Map<String, Object> response = new HashMap<>();
            response.put("data", amministratori);
            response.put("columns", columnNames);

            return ResponseEntity.ok(response);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Impossibile effettuare l'operazione");
        }
    }

    @GetMapping("/prenotazioni")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getPrenotazioni() {
        try {
            boolean isAdmin = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"));
            if (!isAdmin) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Non sei autorizzato");
            }
            List<Prenotazione> prenotazioni = prenotazioneService.findAll();
            List<String> columnNames = getColumnNamesPrenotazione(prenotazioni);

            Map<String, Object> response = new HashMap<>();
            response.put("data", prenotazioni);
            response.put("columns", columnNames);

            return ResponseEntity.ok(response);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Impossibile effettuare l'operazione");
        }
    }

    @PostMapping("/aggiorna")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> aggiornaInfo(@RequestBody InfoLaserGame azienda) {
        try {
            boolean isAdmin = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"));
            if (!isAdmin) {
                throw new SecurityException("Utente non autorizzato");
            }
            try {
                InfoLaserGame aziendaAggiornata = infoLaserGameService.aggiornaDatiInfoLaserGame(azienda);
                return ResponseEntity.ok(aziendaAggiornata);
            } catch (DatiAziendaException | SecurityException e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
            }
        }catch (Exception e) {
            throw new IllegalArgumentException("Impossibile effettuare l'operazione");
        }
    }

}
