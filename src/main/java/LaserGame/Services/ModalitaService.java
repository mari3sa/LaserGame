package LaserGame.Services;

import LaserGame.Entities.Modalita;
import LaserGame.Repository.ModalitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ModalitaService {

    @Autowired
    private ModalitaRepository modalitaRepository;

    // Trova tutte le modalità
    public List<Modalita> getAllModalita() {
        return modalitaRepository.findAll();
    }

    // Trova una modalità per ID
    public Optional<Modalita> getModalitaById(Long id) {
        return modalitaRepository.findById(id);
    }

    // Trova modalità per nome
    public List <Modalita> findByNome(String nome) {
        return modalitaRepository.findByNomeModalita(nome);
    }

    // Crea una modalità
    public Modalita creaModalita(Modalita modalita) {
        return modalitaRepository.save(modalita);
    }

    /**
     * Trova modalità con prezzo minore o uguale al valore specificato.
     * @param prezzo Il prezzo massimo.
     * @return Una lista di modalità che soddisfano il criterio.
     */
    public List<Modalita> findByPrezzoLessThanEqual(Integer prezzo) {
        return modalitaRepository.findByPrezzoLessThanEqual(prezzo);
    }

    /**
     * Trova modalità con un numero minimo di partecipanti maggiore o uguale al valore specificato.
     * @param minPartecipanti Il numero minimo di partecipanti.
     * @return Una lista di modalità che soddisfano il criterio.
     */
    public List<Modalita> findByNumeroMinPartecipantiGreaterThanEqual(Integer minPartecipanti) {
        return modalitaRepository.findByNumeroMinPartecipantiGreaterThanEqual(minPartecipanti);
    }
}
