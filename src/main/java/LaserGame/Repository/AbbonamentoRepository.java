package LaserGame.Repository;

import LaserGame.Entities.Abbonamento;  // Importa la classe Abbonamento per operazioni CRUD
import org.springframework.data.jpa.repository.JpaRepository;  // Estende JpaRepository per operazioni CRUD
import org.springframework.data.jpa.repository.Query;  // Importa Query per definire query custom
import org.springframework.data.repository.query.Param;  // Importa Param per la gestione dei parametri nelle query
import org.springframework.stereotype.Repository;  // Indica che è un Repository Spring

import java.time.LocalDate;  // Importa LocalDate per la gestione delle date
import java.util.List;  // Importa List per la gestione delle liste

@Repository  // Annota il repository Spring
public interface AbbonamentoRepository extends JpaRepository<Abbonamento, Long> {

    // Trova gli abbonamenti per un utente specifico
    List<Abbonamento> findByUtenteId(Long utenteId);  // Restituisce gli abbonamenti legati a un utente specifico

    // Trova gli abbonamenti con data di fine antecedente a una data specifica
    List<Abbonamento> findByDataFineBefore(@Param("date") LocalDate date);  // Restituisce gli abbonamenti con scadenza prima di una data specifica

    // Trova gli abbonamenti con data di fine successiva a una data specifica
    List<Abbonamento> findByDataFineAfter(@Param("date") LocalDate date);  // Restituisce gli abbonamenti con scadenza dopo una data specifica
}
