package LaserGame.Entities;

import jakarta.persistence.*;
import java.math.BigDecimal;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "articolo_carrello")
public class ArticoloCarrello {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "carrello_id", nullable = false)
    private Carrello carrello;

    @ManyToOne
    @JoinColumn(name = "modalita_id")
    private Modalita modalita; // Modalita o abbonamento o promozione

    @ManyToOne
    @JoinColumn(name = "abbonamento_id")
    private Abbonamento abbonamento; // Abbonamento legato al carrello

    @ManyToOne
    @JoinColumn(name = "promozione_id")
    private Promozione promozione; // Promozione legata al carrello

    @Column(nullable = false)
    private Integer quantita;

    @Column(nullable = false)
    private BigDecimal prezzoUnitario;

    @Column(nullable = false)
    private BigDecimal totale; // Calcolato come prezzoUnitario * quantita
}
