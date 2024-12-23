package LaserGame.Services;

import LaserGame.Entities.Modalita;
import LaserGame.Repository.ModalitaRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ModalitaService {

    @Autowired
    private ModalitaRepository modalitaRepository;

    @Transactional
    // Trova tutte le modalità
    public List<Modalita> getAllModalita() {
        return modalitaRepository.findAll();
    }

    @Transactional(readOnly = true)
    // Trova una modalità per ID
    public Optional<Modalita> getModalitaById(Long id) {
        return modalitaRepository.findById(id);
    }

    @Transactional(readOnly = true)
    // Trova modalità per nome
    public List <Modalita> findByNome(String nome) {
        return modalitaRepository.findByNomeModalita(nome);
    }

    @Transactional
    // Crea una modalità
    public Modalita creaModalita(Modalita modalita) {
        return modalitaRepository.save(modalita);
    }

    /**
     * Trova modalità con prezzo minore o uguale al valore specificato.
     * @param prezzo Il prezzo massimo.
     * @return Una lista di modalità che soddisfano il criterio.
     */
    @Transactional(readOnly = true)
    public List<Modalita> findByPrezzoLessThanEqual(Integer prezzo) {
        return modalitaRepository.findByPrezzoLessThanEqual(prezzo);
    }

    /**
     * Trova modalità con un numero minimo di partecipanti maggiore o uguale al valore specificato.
     * @param minPartecipanti Il numero minimo di partecipanti.
     * @return Una lista di modalità che soddisfano il criterio.
     */
    @Transactional(readOnly = true)
    public List<Modalita> findByNumeroMinPartecipantiGreaterThanEqual(Integer minPartecipanti) {
        return modalitaRepository.findByNumeroMinPartecipantiGreaterThanEqual(minPartecipanti);
    }

    private void validaModalita(Modalita modalita) {
        if (modalita.getNomeModalita() == null || modalita.getNomeModalita().trim().isEmpty()) {
            throw new IllegalArgumentException("Il nome della modalità è obbligatorio.");
        }
        if (modalita.getDescrizione() == null || modalita.getDescrizione().trim().isEmpty()) {
            throw new IllegalArgumentException("La descrizione della modalità è obbligatoria.");
        }
        if (modalita.getPrezzo() == null || modalita.getPrezzo().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Il prezzo della modalità è obbligatorio e deve essere maggiore di zero.");
        }

        if (modalita.getNumeroMinPartecipanti() == null || modalita.getNumeroMinPartecipanti() < 2) {
            throw new IllegalArgumentException("Il numero minimo di partecipanti deve essere almeno 2.");
        }
        if (modalita.getNumeroMaxPartecipanti() == null || modalita.getNumeroMaxPartecipanti() < 2 || modalita.getNumeroMaxPartecipanti() > 10) {
            throw new IllegalArgumentException("Il numero massimo di partecipanti deve essere compreso tra 2 e 10.");
        }
        if (modalita.getNumeroMaxPartecipanti() < modalita.getNumeroMinPartecipanti()) {
            throw new IllegalArgumentException("Il numero massimo di partecipanti deve essere maggiore o uguale al numero minimo.");
        }
    }

}
