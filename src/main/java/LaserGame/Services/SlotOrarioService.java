package LaserGame.Services;

import LaserGame.Entities.Modalita;
import LaserGame.Entities.SlotOrario;
import LaserGame.Exception.FasciaOrariaInesistenteException;
import LaserGame.Exception.FasciaOrariaSaturaException;
import LaserGame.Repository.SlotOrarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional(readOnly = true)
    public SlotOrario findById(long id) {
        if (slotOrarioRepository.findById(id).isEmpty()){
            throw new FasciaOrariaInesistenteException("Fascia Oraria non trovato");
        }
        return slotOrarioRepository.findById(id).get();
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
        validaSlotOrario(slotOrario);
        return slotOrarioRepository.save(slotOrario);
    }

    @Transactional(readOnly = true)
    public List<SlotOrario> findAll() {
        boolean isAdmin = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"));
        if (!isAdmin) {
            throw new SecurityException("Utente non autorizzato");
        }
        return slotOrarioRepository.findAll();
    }

    /**
     * Crea uno slot orario utilizzando un ID di servizio, l'inizio e il giorno specifici.
     *
     * @param modalitaId        ID della modalità.
     * @param dataInizio         Data e ora di inizio.
     * @param orariDisponibili   Numero di orari disponibili.
     * @return Lo slot orario creato.
     */
    @Transactional
    public SlotOrario creaSlotOrario(Long modalitaId, LocalDateTime dataInizio, Integer orariDisponibili) {
        Modalita modalita = new Modalita(modalitaId);  // Assumo che Modalita abbia un costruttore con id
        SlotOrario slotOrario = new SlotOrario();
        slotOrario.setModalita(modalita);
        slotOrario.setData(dataInizio);
        slotOrario.setOrariDisponibili(orariDisponibili);
        return creaSlotOrario(slotOrario);
    }

    /**
     * Aggiunge una fascia oraria (slot) all'insieme esistente.
     *
     * @param slotOrario L'entità SlotOrario da aggiungere.
     * @return Lo slot orario aggiornato.
     */
    @Transactional
    public SlotOrario aggiungiFasciaOraria(SlotOrario slotOrario) {
        validaSlotOrario(slotOrario);
        return slotOrarioRepository.save(slotOrario);
    }

    /**
     * Prenota una fascia oraria (slot) specifica.
     *
     * @param slotOrario L'entità SlotOrario da prenotare.
     * @return Lo slot orario aggiornato dopo la prenotazione.
     */
    @Transactional
    public SlotOrario prenotaFasciaOraria(SlotOrario slotOrario) {
        Optional<SlotOrario> fasciaOrariaOptional = slotOrarioRepository.findByIdWithLock(slotOrario.getId());
        if (fasciaOrariaOptional.isEmpty()) {
            throw new FasciaOrariaInesistenteException("Fascia Oraria non trovata");
        }
        SlotOrario fasciaOraria = fasciaOrariaOptional.get();
        if (fasciaOraria.isPrenotata()) {
            throw new FasciaOrariaSaturaException("Fascia Oraria al completo");
        }
        fasciaOraria.setPrenotata(true);
        try {
            slotOrarioRepository.save(fasciaOraria);
        } catch (Exception e) {
            throw e;
        }
        return fasciaOraria;
    }

    /**
     * Validazione dello slot orario.
     */
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
