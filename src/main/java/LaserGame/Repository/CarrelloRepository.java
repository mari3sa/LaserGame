package LaserGame.Repository;
import LaserGame.Entities.Carrello;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository  // Annota il repository Spring
public interface CarrelloRepository extends JpaRepository<Carrello, Long> {
        Optional<Carrello> findByUtenteId(int utenteId);
    }


