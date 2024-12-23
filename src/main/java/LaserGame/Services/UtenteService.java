package LaserGame.Services;

import LaserGame.Entities.Utente;
import LaserGame.Repository.UtenteRepository;
import LaserGame.Utils.enumeration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UtenteService {

    @Autowired
    private UtenteRepository utenteRepository;

    // Trova tutti gli utenti
    public List<Utente> getAllUtenti() {
        return utenteRepository.findAll();
    }


    // Trova un utente per ID
    public Optional<Utente> getUtenteById(Long id) {
        return utenteRepository.findById(id);
    }

    // Trova un utente per email
    public Optional<Utente> findByEmail(String email) {
        return utenteRepository.findByEmail(email);
    }

    // Crea un nuovo utente
    public Utente creaUtente(String nome, String cognome, String email, String telefono) {
        Utente utente = new Utente();
        utente.setNome(nome);
        utente.setCognome(cognome);
        utente.setEmail(email);
        utente.setTelefono(telefono);
        return utenteRepository.save(utente);
    }

    // Crea un amministratore
    public Utente creaAmministratore(String nome, String cognome, String email, String telefono, String password) {
        Utente amministratore = new Utente();
        amministratore.setNome(nome);
        amministratore.setCognome(cognome);
        amministratore.setEmail(email);
        amministratore.setTelefono(telefono);
        amministratore.setTipo(LaserGame.Utils.enumeration.TipoUtente.ADMIN);
        return utenteRepository.save(amministratore);
    }
    public List<Utente> findByTipo(enumeration.TipoUtente tipo) {
        return utenteRepository.findByTipo(tipo);
    }
}
