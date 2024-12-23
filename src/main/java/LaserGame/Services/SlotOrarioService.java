package LaserGame.Services;

import LaserGame.Entities.SlotOrario;
import LaserGame.Repository.SlotOrarioRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SlotOrarioService {

    @Autowired
    private SlotOrarioRepository slotOrarioRepository;

    /**
     * Trova tutti gli slot orari.
     *
     * @return Lista di tutti gli slot orari.
     */
    @Transactional
    public List<SlotOrario> getAllSlotOrari() {
        return slotOrarioRepository.findAll();
    }

    /**
     * Trova uno slot orario per ID.
     *
     * @param id ID dello slot orario.
     * @return Optional contenente lo slot orario trovato, o vuoto se non esiste.
     */
    @Transactional(readOnly = true)
    public Optional<SlotOrario> getSlotOrarioById(Long id) {
        return slotOrarioRepository.findById(id);
    }

    /**
     * Trova slot orari per data specifica.
     *
     * @param data Data di riferimento.
     * @return Lista di slot orari per la data specifica.
     */
    @Transactional(readOnly = true)
    public List<SlotOrario> findByData(LocalDateTime data) {
        return slotOrarioRepository.findByData(data);
    }

    /**
     * Trova slot orari per ID della modalità.
     *
     * @param modalitaId ID della modalità.
     * @return Lista di slot orari associati alla modalità.
     */
    @Transactional(readOnly = true)
    public List<SlotOrario> findByModalitaId(Long modalitaId) {
        return slotOrarioRepository.findByModalitaId(modalitaId);
    }

    /**
     * Crea uno slot orario.
     *
     * @param slotOrario L'entità SlotOrario da salvare.
     * @return Lo slot orario salvato.
     */
    @Transactional
    public SlotOrario creaSlotOrario(SlotOrario slotOrario) {
        return slotOrarioRepository.save(slotOrario);
    }

    private void validaSlotOrario(SlotOrario slotOrario) {
        if (slotOrario.getModalita() == null) {
            throw new IllegalArgumentException("La modalità associata è obbligatoria.");
        }
        if (slotOrario.getData() == null || slotOrario.getData().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("La data deve essere valida e futura.");
        }
        if (slotOrario.getOrariDisponibili() == null || slotOrario.getOrariDisponibili() <= 0) {
            throw new IllegalArgumentException("Il numero di orari disponibili deve essere maggiore di zero.");
        }
    }

}
