package LaserGame.Services;

import LaserGame.Entities.Abbonamento;
import LaserGame.Repository.AbbonamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AbbonamentoService {

    @Autowired
    private AbbonamentoRepository abbonamentoRepository;

    /**
     * Restituisce tutti gli abbonamenti.
     *
     * @return Lista di tutti gli abbonamenti.
     */
    public List<Abbonamento> findAll() {
        return abbonamentoRepository.findAll();
    }

    /**
     * Trova un abbonamento per ID.
     *
     * @param id ID dell'abbonamento.
     * @return L'abbonamento trovato o Optional vuoto.
     */
    public Optional<Abbonamento> findById(Long id) {
        return abbonamentoRepository.findById(id);
    }

    /**
     * Salva o aggiorna un abbonamento.
     *
     * @param abbonamento L'entità Abbonamento da salvare o aggiornare.
     * @return L'entità Abbonamento salvata.
     */
    public Abbonamento save(Abbonamento abbonamento) {
        return abbonamentoRepository.save(abbonamento);
    }

    /**
     * Elimina un abbonamento per ID.
     *
     * @param id ID dell'abbonamento da eliminare.
     */
    public void deleteById(Long id) {
        abbonamentoRepository.deleteById(id);
    }

    /**
     * Trova gli abbonamenti di un utente specifico.
     *
     * @param utenteId ID dell'utente.
     * @return Lista di abbonamenti dell'utente.
     */
    public List<Abbonamento> findByUtenteId(Long utenteId) {
        return abbonamentoRepository.findByUtenteId(utenteId);
    }

    /**
     * Trova gli abbonamenti con data di fine antecedente a una data specifica.
     *
     * @param date La data di riferimento.
     * @return Lista di abbonamenti scaduti prima della data.
     */
    public List<Abbonamento> findByDataFineBefore(LocalDate date) {
        return abbonamentoRepository.findByDataFineBefore(date);
    }

    /**
     * Trova gli abbonamenti con data di fine successiva a una data specifica.
     *
     * @param date La data di riferimento.
     * @return Lista di abbonamenti con scadenza dopo la data.
     */
    public List<Abbonamento> findByDataFineAfter(LocalDate date) {
        return abbonamentoRepository.findByDataFineAfter(date);
    }
}
