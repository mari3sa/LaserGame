package LaserGame.Repository;

import LaserGame.Entities.SlotOrario;  // Importa la classe SlotOrario
import org.springframework.data.jpa.repository.JpaRepository;  // Estende JpaRepository per operazioni CRUD
import org.springframework.stereotype.Repository;  // Indica che è un Repository Spring

@Repository  // Annota il repository Spring
public interface SlotOrarioRepository extends JpaRepository<SlotOrario, Long> {
    // Repository per l'entità SlotOrario.
}
