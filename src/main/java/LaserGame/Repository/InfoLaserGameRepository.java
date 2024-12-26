package LaserGame.Repository;

import LaserGame.Entities.InfoLaserGame;  // Importa la classe InfoLaserGame per operazioni CRUD
import org.springframework.data.jpa.repository.JpaRepository;  // Estende JpaRepository per operazioni CRUD
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;  // Indica che è un Repository Spring

@Repository  // Annota il repository Spring
public interface InfoLaserGameRepository extends JpaRepository<InfoLaserGame, Long> {
    @Query("SELECT CASE WHEN COUNT(a) > 0 THEN TRUE ELSE FALSE END FROM InfoLaserGame a WHERE a.codice_amministrativo = :codiceAmministrativo")
    static boolean existsByCodiceAmministrativo(@Param("codiceAmministrativo") long codiceAmministrativo) {
        return false;
    }
}
