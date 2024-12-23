package LaserGame.Entities;

import LaserGame.Utils.enumeration;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "pagamento")
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "utente_id", nullable = false)
    private Utente utente; // Relazione con l'utente

    @Column(nullable = false)
    private BigDecimal importo; // Importo totale del pagamento

    @Column(nullable = false)
    private LocalDateTime dataPagamento; // Data e ora del pagamento

    @Column(nullable = false)
    private String metodoPagamento; // E.g., "Carta di credito", "PayPal", ecc.

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private enumeration.StatoPagamento stato; // Stato del pagamento (SUCCESSO, FALLITO, IN ATTESA)

    @Column(nullable = true)
    private String riferimentoTransazione; // Riferimento della transazione, se applicabile
}

