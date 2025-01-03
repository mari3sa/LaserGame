package LaserGame.Repository;

import LaserGame.Entities.Pagamento;
import LaserGame.Entities.Utente;
import LaserGame.Utils.enumeration.StatoPagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {

    // Trova pagamenti per utente
    List<Pagamento> findByUtenteId(Utente utenteId);

    // Trova pagamenti per stato
    List<Pagamento> findByStato(StatoPagamento stato);

    // Trova pagamenti effettuati con un certo metodo di pagamento
    List<Pagamento> findByMetodoPagamento(String metodoPagamento);

    // Trova pagamenti con importo maggiore di un valore specifico
    List<Pagamento> findByImportoGreaterThanEqual(Double importo);

    // Query custom: Pagamenti recenti (ultimi X giorni)
    @Query("SELECT p FROM Pagamento p WHERE p.dataPagamento >= CURRENT_DATE - :days")
    List<Pagamento> findRecentPayments(int days);
}
