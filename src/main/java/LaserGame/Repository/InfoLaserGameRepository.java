package LaserGame.Repository;

import LaserGame.Entities.InfoLaserGame;  // Importa la classe InfoLaserGame per operazioni CRUD
import org.springframework.data.jpa.repository.JpaRepository;  // Estende JpaRepository per operazioni CRUD
import org.springframework.stereotype.Repository;  // Indica che è un Repository Spring

@Repository  // Annota il repository Spring
public interface InfoLaserGameRepository extends JpaRepository<InfoLaserGame, Long> {
    // Repository per l'entità InfoLaserGame.
}
