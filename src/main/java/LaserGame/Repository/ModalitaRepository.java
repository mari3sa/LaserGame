package LaserGame.Repository;

import LaserGame.Entities.Modalita;  // Importa la classe Modalita per operazioni CRUD
import org.springframework.data.jpa.repository.JpaRepository;  // Estende JpaRepository per operazioni CRUD
import org.springframework.data.jpa.repository.Query;  // Importa Query per definire query custom
import org.springframework.data.repository.query.Param;  // Importa Param per la gestione dei parametri nelle query
import org.springframework.stereotype.Repository;  // Indica che è un Repository Spring

import java.util.List;  // Importa List per la gestione delle liste

@Repository  // Annota il repository Spring
public interface ModalitaRepository extends JpaRepository<Modalita, Long> {

    // Trova le modalità di gioco basate sul nome
    List<Modalita> findByNomeModalita (String nomeModalita);  // Restituisce tutte le modalità con un nome specifico

    // Trova le modalità con prezzo minore o uguale a un valore specifico
    List<Modalita> findByPrezzoLessThanEqual(@Param("prezzo") Integer prezzo);  // Restituisce le modalità con prezzo ≤ valore specifico

    // Trova le modalità con un numero minimo di partecipanti
    List<Modalita> findByNumeroMinPartecipantiGreaterThanEqual(@Param("minPartecipanti") Integer minPartecipanti);  // Restituisce le modalità con numero min di partecipanti ≥ valore specifico
}
