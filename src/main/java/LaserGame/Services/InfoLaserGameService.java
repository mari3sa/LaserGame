package LaserGame.Services;

import LaserGame.Entities.InfoLaserGame;
import LaserGame.Repository.InfoLaserGameRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InfoLaserGameService {

    @Autowired
    private InfoLaserGameRepository infoLaserGameRepository;

    /**
     * Trova tutte le informazioni del LaserGame.
     *
     * @return Lista di tutte le informazioni.
     */
    @Transactional(readOnly = true)
    public List<InfoLaserGame> findAll() {
        return infoLaserGameRepository.findAll();
    }

    /**
     * Trova le informazioni del LaserGame per ID.
     *
     * @param id ID delle informazioni.
     * @return Informazioni trovate o Optional vuoto se non esiste.
     */
    @Transactional(readOnly = true)
    public Optional<InfoLaserGame> findById(Long id) {
        return infoLaserGameRepository.findById(id);
    }

    /**
     * Salva nuove informazioni o aggiorna informazioni esistenti.
     *
     * @param infoLaserGame L'entità InfoLaserGame da salvare o aggiornare.
     * @return L'entità InfoLaserGame salvata.
     */
    @Transactional
    public InfoLaserGame save(InfoLaserGame infoLaserGame) {
        return infoLaserGameRepository.save(infoLaserGame);
    }

    /**
     * Elimina informazioni del LaserGame per ID.
     *
     * @param id ID delle informazioni da eliminare.
     */
    @Transactional
    public void deleteById(Long id) {
        infoLaserGameRepository.deleteById(id);
    }

    /**
     * Verifica se informazioni del LaserGame con un dato ID esistono.
     *
     * @param id ID delle informazioni.
     * @return True se esistono, False altrimenti.
     */
    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return infoLaserGameRepository.existsById(id);
    }

    /**
     * Verifica se un codice amministrativo esiste.
     *
     * @param codice Il codice amministrativo da verificare.
     * @return True se esiste, False altrimenti.
     */
    @Transactional(readOnly = true)
    public boolean verificaCodiceAmministratore(int codice) {
        return infoLaserGameRepository.existsByCodiceAmministrativo(codice);
        //si utilizza un'istanza di infoLaserGame, non il repository InfoLaserGame che è
        // un'interfaccia di spring e di conseguenza per essere usata ha bisogno di metodi statici.
    }

    private void validaInfoLaserGame(InfoLaserGame infoLaserGame) {
        if (infoLaserGame.getNome() == null || infoLaserGame.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Il nome è obbligatorio.");
        }
        if (infoLaserGame.getEmail() == null || !infoLaserGame.getEmail().matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            throw new IllegalArgumentException("L'email non è valida.");
        }
        if (infoLaserGame.getIndirizzo() == null || infoLaserGame.getIndirizzo().trim().isEmpty()) {
            throw new IllegalArgumentException("L'indirizzo è obbligatorio.");
        }
        if (infoLaserGame.getCitta() == null || infoLaserGame.getCitta().trim().isEmpty()) {
            throw new IllegalArgumentException("La città è obbligatoria.");
        }
        if (infoLaserGame.getPaese() == null || infoLaserGame.getPaese().trim().isEmpty()) {
            throw new IllegalArgumentException("Il paese è obbligatorio.");
        }
        if (infoLaserGame.getCodicePostale() == null || infoLaserGame.getCodicePostale().trim().isEmpty()) {
            throw new IllegalArgumentException("Il codice postale è obbligatorio.");
        }
        if (infoLaserGame.getLatitude() == null || !infoLaserGame.getLatitude().matches("^-?\\d+(\\.\\d+)?$")) {
            throw new IllegalArgumentException("La latitudine non è valida.");
        }
        if (infoLaserGame.getLongitude() == null || !infoLaserGame.getLongitude().matches("^-?\\d+(\\.\\d+)?$")) {
            throw new IllegalArgumentException("La longitudine non è valida.");
        }
    }
}
