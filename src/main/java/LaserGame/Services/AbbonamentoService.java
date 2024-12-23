package LaserGame.Services;

import LaserGame.Entities.Abbonamento;
import LaserGame.Repository.AbbonamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
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
    @Transactional(readOnly = true)
    public List<Abbonamento> findAll() {
        return abbonamentoRepository.findAll();
    }

    /**
     * Trova un abbonamento per ID.
     *
     * @param id ID dell'abbonamento.
     * @return L'abbonamento trovato o Optional vuoto.
     */
    @Transactional(readOnly = true)
    public Optional<Abbonamento> findById(Long id) {
        return abbonamentoRepository.findById(id);
    }

    /**
     * Salva o aggiorna un abbonamento.
     *
     * @param abbonamento L'entità Abbonamento da salvare o aggiornare.
     * @return L'entità Abbonamento salvata.
     */
    @Transactional
    public Abbonamento save(Abbonamento abbonamento) {
        return abbonamentoRepository.save(abbonamento);
    }

    /**
     * Elimina un abbonamento per ID.
     *
     * @param id ID dell'abbonamento da eliminare.
     */
    @Transactional
    public void deleteById(Long id) {
        abbonamentoRepository.deleteById(id);
    }

    /**
     * Trova gli abbonamenti di un utente specifico.
     *
     * @param utenteId ID dell'utente.
     * @return Lista di abbonamenti dell'utente.
     */
    @Transactional(readOnly = true)
    public List<Abbonamento> findByUtenteId(Long utenteId) {
        return abbonamentoRepository.findByUtenteId(utenteId);
    }

    /**
     * Trova gli abbonamenti con data di fine antecedente a una data specifica.
     *
     * @param date La data di riferimento.
     * @return Lista di abbonamenti scaduti prima della data.
     */
    @Transactional(readOnly = true)
    public List<Abbonamento> findByDataFineBefore(LocalDate date) {
        return abbonamentoRepository.findByDataFineBefore(date);
    }

    /**
     * Trova gli abbonamenti con data di fine successiva a una data specifica.
     *
     * @param date La data di riferimento.
     * @return Lista di abbonamenti con scadenza dopo la data.
     */
    @Transactional(readOnly = true)
    public List<Abbonamento> findByDataFineAfter(LocalDate date) {
        return abbonamentoRepository.findByDataFineAfter(date);
    }

    private void validaAbbonamento(Abbonamento abbonamento) {
        if (abbonamento.getUtenteId() <= 0) {
            throw new IllegalArgumentException("L'ID utente è obbligatorio e deve essere un valore positivo.");
        }
        if (abbonamento.getTipo() == null || abbonamento.getTipo().trim().isEmpty()) {
            throw new IllegalArgumentException("Il tipo di abbonamento è obbligatorio.");
        }
        if (abbonamento.getDataInizio() == null) {
            throw new IllegalArgumentException("La data di inizio è obbligatoria.");
        }
        if (abbonamento.getDataFine() == null) {
            throw new IllegalArgumentException("La data di fine è obbligatoria.");
        }
        if (abbonamento.getDataFine().isBefore(abbonamento.getDataInizio())) {
            throw new IllegalArgumentException("La data di fine deve essere successiva alla data di inizio.");
        }
        if (abbonamento.getPrezzo() == null || abbonamento.getPrezzo().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Il prezzo deve essere maggiore di zero.");
        }
    }

}
