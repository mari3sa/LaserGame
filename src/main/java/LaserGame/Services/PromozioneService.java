package LaserGame.Services;

import LaserGame.Entities.Promozione;
import LaserGame.Repository.PromozioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class PromozioneService {

    @Autowired
    private PromozioneRepository promozioneRepository;

    /**
     * Trova tutte le promozioni.
     *
     * @return Lista di tutte le promozioni.
     */
    public List<Promozione> findAll() {
        return promozioneRepository.findAll();
    }

    /**
     * Trova una promozione per ID.
     *
     * @param id ID della promozione.
     * @return Promozione trovata o Optional vuoto se non esiste.
     */
    public Optional<Promozione> findById(Long id) {
        return promozioneRepository.findById(id);
    }

    /**
     * Trova le promozioni con un nome specifico.
     *
     * @param nome Nome della promozione.
     * @return Lista di promozioni con il nome specificato.
     */
    public List<Promozione> findByNome(String nome) {
        return promozioneRepository.findByNome(nome);
    }

    /**
     * Trova le promozioni con un prezzo inferiore o uguale a un valore specifico.
     *
     * @param prezzo Prezzo massimo delle promozioni.
     * @return Lista di promozioni con prezzo ≤ valore specificato.
     */
    public List<Promozione> findByPrezzoLessThanEqual(BigDecimal prezzo) {
        return promozioneRepository.findByPrezzoLessThanEqual(prezzo);
    }

    /**
     * Trova le promozioni con un numero minimo di ingressi.
     *
     * @param numeroIngressi Numero minimo di ingressi.
     * @return Lista di promozioni con numero ingressi ≥ valore specificato.
     */
    public List<Promozione> findByNumeroIngressiGreaterThanEqual(int numeroIngressi) {
        return promozioneRepository.findByNumeroIngressiGreaterThanEqual(numeroIngressi);
    }

    /**
     * Salva una nuova promozione o aggiorna una esistente.
     *
     * @param promozione L'entità promozione da salvare o aggiornare.
     * @return L'entità promozione salvata.
     */
    public Promozione save(Promozione promozione) {
        return promozioneRepository.save(promozione);
    }

    /**
     * Elimina una promozione per ID.
     *
     * @param id ID della promozione da eliminare.
     */
    public void deleteById(Long id) {
        promozioneRepository.deleteById(id);
    }

    /**
     * Verifica se una promozione con un dato ID esiste.
     *
     * @param id ID della promozione.
     * @return True se la promozione esiste, False altrimenti.
     */
    public boolean existsById(Long id) {
        return promozioneRepository.existsById(id);
    }
}
