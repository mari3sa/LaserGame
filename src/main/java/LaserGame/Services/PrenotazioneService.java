package LaserGame.Services;

import LaserGame.Entities.Modalita;
import LaserGame.Entities.Prenotazione;
import LaserGame.Repository.PrenotazioneRepository;
import LaserGame.Utils.enumeration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PrenotazioneService {

    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    // Trova tutte le prenotazioni
    public List<Prenotazione> getAllPrenotazioni() {
        return prenotazioneRepository.findAll();
    }

    // Trova una prenotazione per ID
    public Optional<Prenotazione> getPrenotazioneById(Long id) {
        return prenotazioneRepository.findById(id);
    }

    // Trova prenotazioni per utente
    public List<Prenotazione> getPrenotazioniByUtenteId(Long utenteId) {
        return prenotazioneRepository.findByUserId(utenteId);
    }

    // Trova prenotazioni per data
    public List<Prenotazione> getPrenotazioniByData(LocalDateTime data) {
        return prenotazioneRepository.findByData(data);
    }

    public List<Prenotazione> getPrenotazioniByStatus(enumeration.StatoPrenotazione stato) {
        return prenotazioneRepository.findByStatus(stato);
    }

    // Crea una prenotazione
    public Prenotazione creaPrenotazione(Prenotazione prenotazione) {
        return prenotazioneRepository.save(prenotazione);
    }

    public List<Prenotazione> findByModalita(Modalita modalita) {
        return prenotazioneRepository.findByModalita(modalita);
    }

    /**
     * Trova prenotazioni con numero di partecipanti minore o uguale a un valore specifico.
     * @param numeroPartecipanti Numero massimo di partecipanti.
     * @return Una lista di prenotazioni che soddisfano il criterio.
     */
    public List<Prenotazione> findByNumeroPartecipantiLessThanEqual(Integer numeroPartecipanti) {
        return prenotazioneRepository.findByNumeroPartecipantiLessThanEqual(numeroPartecipanti);
    }
}
