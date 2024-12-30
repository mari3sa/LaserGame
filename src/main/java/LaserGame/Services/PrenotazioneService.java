package LaserGame.Services;

import LaserGame.Entities.*;
import LaserGame.Exception.PrenotazioneInesistenteException;
import LaserGame.Repository.PrenotazioneRepository;
import LaserGame.Utils.enumeration;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PrenotazioneService {

    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    @Transactional
    // Trova tutte le prenotazioni
    public List<Prenotazione> getAllPrenotazioni() {
        return prenotazioneRepository.findAll();
    }

    @Transactional(readOnly = true)
    // Trova una prenotazione per ID
    public Optional<Prenotazione> getPrenotazioneById(Long id) {
        return prenotazioneRepository.findById(id);
    }



    @Transactional(readOnly = true)
    // Trova prenotazioni per utente
    public List<Prenotazione> getPrenotazioniByUtenteId(Long utenteId) {
        return prenotazioneRepository.findByUserId(utenteId);
    }

    @Transactional(readOnly = true)
    // Trova prenotazioni per data
    public List<Prenotazione> getPrenotazioniByData(LocalDateTime data) {
        return prenotazioneRepository.findByData(data);
    }

    @Transactional(readOnly = true)
    public List<Prenotazione> getPrenotazioniByStatus(enumeration.StatoPrenotazione stato) {
        return prenotazioneRepository.findByStatus(stato);
    }

    @Transactional
    // Crea una prenotazione
    public Prenotazione creaPrenotazione(Prenotazione prenotazione) {
        return prenotazioneRepository.save(prenotazione);
    }

    @Transactional(readOnly = true)
    public List<Prenotazione> findByModalita(Modalita modalita) {
        return prenotazioneRepository.findByModalita(modalita);
    }

    /**
     * Trova prenotazioni con numero di partecipanti minore o uguale a un valore specifico.
     * @param numeroPartecipanti Numero massimo di partecipanti.
     * @return Una lista di prenotazioni che soddisfano il criterio.
     */
    @Transactional(readOnly = true)
    public List<Prenotazione> findByNumeroPartecipantiLessThanEqual(Integer numeroPartecipanti) {
        return prenotazioneRepository.findByNumeroPartecipantiLessThanEqual(numeroPartecipanti);
    }

    @Transactional(readOnly = true)
    public Prenotazione findById(long id) {
        if (prenotazioneRepository.findById(id).isPresent()) {
            return prenotazioneRepository.findById(id).get();
        }
        else
            throw new PrenotazioneInesistenteException("Prenotazione inesistente");
    }

    @Transactional(readOnly = true)
    public List<Prenotazione> findByCliente(int clienteId) {
        boolean isAdmin = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"));
        if (! isAdmin){
            throw new SecurityException("Utente non autorizzato");
        }
        return prenotazioneRepository.findByUtenteId(clienteId);
    }

    @Transactional
    public void creaPrenotazioneModalita(Utente utente, Modalita modalita, LocalDateTime data) {
        Prenotazione prenotazione = new Prenotazione();
        prenotazione.setData(data);
        prenotazione.setStatus(enumeration.StatoPrenotazione.CONFERMATA);
        prenotazione.setModalita(modalita); // Collegamento alla modalità
        prenotazione.setUser(utente); // Collegamento all'utente

        validaPrenotazione(prenotazione);

        prenotazioneRepository.save(prenotazione);
    }

    @Transactional
    public void creaPrenotazionePromozione(Utente utente, Promozione promozione, LocalDateTime data) {
        Prenotazione prenotazione = new Prenotazione();

        // Imposta i dettagli della prenotazione
        prenotazione.setData(data);
        prenotazione.setStatus(enumeration.StatoPrenotazione.CONFERMATA);
        prenotazione.setPromozione(promozione); // Associa la promozione alla prenotazione
        prenotazione.setUser(utente); // Associa l'utente alla prenotazione

        // Valida la prenotazione prima del salvataggio
        validaPrenotazione(prenotazione);

        // Salva la prenotazione nel repository
        prenotazioneRepository.save(prenotazione);
    }

    @Transactional
    public void creaPrenotazioneAbbonamento(Utente utente, Abbonamento abbonamento, LocalDateTime data) {
        Prenotazione prenotazione = new Prenotazione();

        // Imposta i dettagli della prenotazione
        prenotazione.setData(data);
        prenotazione.setStatus(enumeration.StatoPrenotazione.CONFERMATA);
        prenotazione.setAbbonamento(abbonamento); // Associa l'abbonamento alla prenotazione
        prenotazione.setUser(utente); // Associa l'utente alla prenotazione

        // Valida la prenotazione prima del salvataggio
        validaPrenotazione(prenotazione);

        // Salva la prenotazione nel repository
        prenotazioneRepository.save(prenotazione);
    }




    @Transactional(readOnly = true)
    public List<Prenotazione> findAll() {
        return prenotazioneRepository.findAll();
    }

    private void validaPrenotazione(Prenotazione prenotazione) {
        if (prenotazione.getUser() == null) {
            throw new IllegalArgumentException("L'utente è obbligatorio.");
        }
        if (prenotazione.getModalita() == null) {
            throw new IllegalArgumentException("La modalità è obbligatoria.");
        }
        if (prenotazione.getData() == null || prenotazione.getData().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("La data deve essere valida e futura.");
        }
        if (prenotazione.getNumeroOre() == null || prenotazione.getNumeroOre() <= 0) {
            throw new IllegalArgumentException("Il numero di ore deve essere maggiore di zero.");
        }
        if (prenotazione.getNumeroPartecipanti() == null || prenotazione.getNumeroPartecipanti() < 2) {
            throw new IllegalArgumentException("Il numero minimo di partecipanti deve essere almeno 2.");
        }
        if (prenotazione.getNumeroPartecipanti() > 10) {
            throw new IllegalArgumentException("Il numero massimo di partecipanti deve essere 10 o meno.");
        }
        if (prenotazione.getStatus() == null) {
            throw new IllegalArgumentException("Lo stato della prenotazione è obbligatorio.");
        }
    }

}
