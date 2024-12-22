package LaserGame.Repository;

import LaserGame.Entities.Promozione;  // Importa la classe Promozione per operazioni CRUD
import org.springframework.data.jpa.repository.JpaRepository;  // Estende JpaRepository per operazioni CRUD
import org.springframework.data.jpa.repository.Query;  // Importa Query per definire query custom
import org.springframework.data.repository.query.Param;  // Importa Param per la gestione dei parametri nelle query
import org.springframework.stereotype.Repository;  // Indica che è un Repository Spring

import java.math.BigDecimal;  // Importa BigDecimal per la gestione dei numeri decimali
import java.util.List;  // Importa List per la gestione delle liste

@Repository  // Annota il repository Spring
public interface PromozioneRepository extends JpaRepository<Promozione, Long> {

    // Trova le promozioni basate sul nome
    List<Promozione> findByNome(String nome);  // Restituisce le promozioni con un nome specifico

    // Trova le promozioni con un prezzo inferiore o uguale a un valore specifico
    List<Promozione> findByPrezzoLessThanEqual(@Param("prezzo") BigDecimal prezzo);  // Restituisce le promozioni con prezzo ≤ valore specifico

    // Trova le promozioni con un numero minimo di ingressi
    List<Promozione> findByNumeroIngressiGreaterThanEqual(@Param("numeroIngressi") int numeroIngressi);  // Restituisce le promozioni con numero di ingressi ≥ valore specifico
}
