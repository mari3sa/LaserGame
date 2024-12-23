package LaserGame.Services;

import LaserGame.Entities.InfoLaserGame;
import LaserGame.Repository.InfoLaserGameRepository;
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
    public List<InfoLaserGame> findAll() {
        return infoLaserGameRepository.findAll();
    }

    /**
     * Trova le informazioni del LaserGame per ID.
     *
     * @param id ID delle informazioni.
     * @return Informazioni trovate o Optional vuoto se non esiste.
     */
    public Optional<InfoLaserGame> findById(Long id) {
        return infoLaserGameRepository.findById(id);
    }

    /**
     * Salva nuove informazioni o aggiorna informazioni esistenti.
     *
     * @param infoLaserGame L'entità InfoLaserGame da salvare o aggiornare.
     * @return L'entità InfoLaserGame salvata.
     */
    public InfoLaserGame save(InfoLaserGame infoLaserGame) {
        return infoLaserGameRepository.save(infoLaserGame);
    }

    /**
     * Elimina informazioni del LaserGame per ID.
     *
     * @param id ID delle informazioni da eliminare.
     */
    public void deleteById(Long id) {
        infoLaserGameRepository.deleteById(id);
    }

    /**
     * Verifica se informazioni del LaserGame con un dato ID esistono.
     *
     * @param id ID delle informazioni.
     * @return True se esistono, False altrimenti.
     */
    public boolean existsById(Long id) {
        return infoLaserGameRepository.existsById(id);
    }
}
