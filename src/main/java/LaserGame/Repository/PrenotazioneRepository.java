package LaserGame.Repository;

import LaserGame.Entities.Modalita;
import LaserGame.Entities.Prenotazione;  // Importa la classe Prenotazione per operazioni CRUD
import LaserGame.Utils.enumeration;
import org.springframework.data.jpa.repository.JpaRepository;  // Estende JpaRepository per operazioni CRUD
import org.springframework.data.jpa.repository.Query;  // Importa Query per definire query custom
import org.springframework.data.repository.query.Param;  // Importa Param per la gestione dei parametri nelle query
import org.springframework.stereotype.Repository;  // Indica che è un Repository Spring

import java.time.LocalDateTime;  // Importa LocalDateTime per la gestione delle date e ore
import java.util.List;  // Importa List per la gestione delle liste

@Repository  // Annota il repository Spring
public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long> {

    // Trova le prenotazioni per un utente specifico
    List<Prenotazione> findByUserId(Long userId);  // Restituisce le prenotazioni legate a un utente specifico

    // Trova le prenotazioni basate sulla data e stato di prenotazione
    List<Prenotazione> findByData(@Param("data") LocalDateTime data);

    List<Prenotazione> findByStatus(@Param("status") enumeration.StatoPrenotazione status);  // Restituisce le prenotazioni basate su data e stato di prenotazione
    List<Prenotazione> findByUtenteId(int clienteId);
    // Trova le prenotazioni basate sulla modalità e lo stato
    List<Prenotazione> findByModalita(@Param("modalita") Modalita modalita);  // Restituisce le prenotazioni basate su modalità e stato

    // Trova le prenotazioni con numero partecipanti minore o uguale a un valore specifico
    List<Prenotazione> findByNumeroPartecipantiLessThanEqual(@Param("numeroPartecipanti") Integer numeroPartecipanti);  // Restituisce le prenotazioni con numero partecipanti ≤ valore specifico

}
