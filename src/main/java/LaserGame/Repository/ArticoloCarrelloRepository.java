package LaserGame.Repository;

import LaserGame.Entities.ArticoloCarrello;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;

public interface ArticoloCarrelloRepository extends JpaRepository<ArticoloCarrello, Long> {
    BigDecimal findTotaleByCarrelloId(Long carrelloId);
}
