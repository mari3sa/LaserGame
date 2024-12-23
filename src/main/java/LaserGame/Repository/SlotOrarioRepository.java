package LaserGame.Repository;

import LaserGame.Entities.SlotOrario;  // Importa la classe SlotOrario
import org.springframework.data.jpa.repository.JpaRepository;  // Estende JpaRepository per operazioni CRUD
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;  // Indica che è un Repository Spring

import java.time.LocalDateTime;
import java.util.List;

@Repository  // Annota il repository Spring
public interface SlotOrarioRepository extends JpaRepository<SlotOrario, Long> {
    // Repository per l'entità SlotOrario.
    // Trova slot orari per data
    @Query("SELECT s FROM SlotOrario s WHERE s.data = :data")
    List<SlotOrario> findByData(@Param("data") LocalDateTime data);

    // Trova slot orari per modalità
    @Query("SELECT s FROM SlotOrario s WHERE s.modalita.id = :modalitaId")
    List<SlotOrario> findByModalitaId(@Param("modalitaId") Long modalitaId);
}
