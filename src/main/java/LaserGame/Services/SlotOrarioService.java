package LaserGame.Services;

import LaserGame.Entities.SlotOrario;
import LaserGame.Repository.SlotOrarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SlotOrarioService {

    @Autowired
    private SlotOrarioRepository slotOrarioRepository;

    // Trova tutti gli slot orari
    public List<SlotOrario> getAllSlotOrari() {
        return slotOrarioRepository.findAll();
    }

    // Trova uno slot orario per ID
    public Optional<SlotOrario> getSlotOrarioById(Long id) {
        return slotOrarioRepository.findById(id);
    }

    // Trova slot orari per data
    public List<SlotOrario> findByData(LocalDateTime data) {
        return slotOrarioRepository.findByData(data);
    }

    // Trova slot orari per modalità
    public List<SlotOrario> findByModalitaId(Long modalitaId) {
        return slotOrarioRepository.findByModalitaId(modalitaId);
    }

    // Crea uno slot orario
    public SlotOrario creaSlotOrario(SlotOrario slotOrario) {
        return slotOrarioRepository.save(slotOrario);
    }
}
