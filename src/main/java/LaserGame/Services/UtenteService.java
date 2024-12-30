package LaserGame.Services;

import LaserGame.Entities.Utente;
import LaserGame.Exception.ClienteInesistenteException;
import LaserGame.Repository.UtenteRepository;
import LaserGame.Utils.enumeration.TipoUtente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UtenteService {

    @Autowired
    private UtenteRepository utenteRepository;

    /**
     * Trova tutti gli utenti.
     *
     * @return Lista di tutti gli utenti.
     */
    @Transactional
    public List<Utente> getAllUtenti() {
        return utenteRepository.findAll();
    }

    /**
     * Trova un utente per ID.
     *
     * @param id ID dell'utente.
     * @return Optional contenente l'utente trovato o vuoto se non esiste.
     */
    @Transactional(readOnly = true)
    public Optional<Utente> getUtenteById(Long id) {
        return utenteRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public Utente findById(long id) {
        Optional<Utente> utente = utenteRepository.findById(id);
        if (utente.isEmpty()) {
            throw new ClienteInesistenteException("Utente non trovato con ID: " + id);
        }
        return utente.get();
    }

    /**
     * Trova un utente per email.
     *
     * @param email Email dell'utente.
     * @return Optional contenente l'utente trovato o vuoto se non esiste.
     */
    @Transactional(readOnly = true)
    public Optional<Utente> findByEmail(String email) {
        return utenteRepository.findByEmail(email);
    }

    /**
     * Crea un nuovo utente.
     *
     * @param nome     Nome dell'utente.
     * @param cognome  Cognome dell'utente.
     * @param email    Email dell'utente.
     * @param telefono Telefono dell'utente.
     * @return L'utente creato.
     */
    @Transactional
    public Utente creaUtente(String nome, String cognome, String email, String telefono) {
        Utente utente = new Utente();
        utente.setNome(nome);
        utente.setCognome(cognome);
        utente.setEmail(email);
        utente.setTelefono(telefono);
        return utenteRepository.save(utente);
    }

    /**
     * Crea un amministratore.
     *
     * @param nome     Nome dell'amministratore.
     * @param cognome  Cognome dell'amministratore.
     * @param email    Email dell'amministratore.
     * @param telefono Telefono dell'amministratore.
     * @param password Password dell'amministratore.
     * @return L'amministratore creato.
     */
    @Transactional
    public Utente creaAmministratore(String nome, String cognome, String email, String telefono, String password) {
        Utente amministratore = new Utente();
        amministratore.setNome(nome);
        amministratore.setCognome(cognome);
        amministratore.setEmail(email);
        amministratore.setTelefono(telefono);
        amministratore.setTipo(LaserGame.Utils.enumeration.TipoUtente.ADMIN);
        return utenteRepository.save(amministratore);
    }

    @Transactional(readOnly = true)
    public List<Utente> findAmministratoreUtente() {
        return utenteRepository.findByTipo(TipoUtente.ADMIN);
    }

    @Transactional(readOnly = true)
    public List<Utente> findUserUtente() {
        return utenteRepository.findByTipo(TipoUtente.CLIENTE);
    }

    /**
     * Trova utenti per tipo specifico.
     *
     * @param tipo Tipo di utente.
     * @return Lista di utenti con il tipo specificato.
     */
    @Transactional(readOnly = true)
    public List<Utente> findByTipo(TipoUtente tipo) {
        return utenteRepository.findByTipo(tipo);
    }

    private void validaUtente(Utente utente) {
        if (utente.getNome() == null || utente.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Il nome è obbligatorio.");
        }
        if (utente.getCognome() == null || utente.getCognome().trim().isEmpty()) {
            throw new IllegalArgumentException("Il cognome è obbligatorio.");
        }
        if (utente.getEmail() == null || !utente.getEmail().matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            throw new IllegalArgumentException("L'email non è valida.");
        }
        if (utente.getTelefono() == null || !utente.getTelefono().matches("^\\+?[0-9. ()-]{7,25}$")) {
            throw new IllegalArgumentException("Il numero di telefono non è valido.");
        }
    }
}
