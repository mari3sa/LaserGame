package LaserGame.Repository;

import LaserGame.Entities.ArticoloCarrello;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
@Repository  // Annota il repository Spring
public interface ArticoloCarrelloRepository extends JpaRepository<ArticoloCarrello, Long> {
    BigDecimal findTotaleByCarrelloId(Long carrelloId);
}
