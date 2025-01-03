package LaserGame.Entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "promozione")
public class Promozione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "descrizione", nullable = true)
    private String descrizione;

    @Column(name = "prezzo", nullable = false)
    private BigDecimal prezzo;

    @Column(name = "numero_ingressi", nullable = false)
    private int numeroIngressi;

    @ManyToMany
    @JoinTable(
            name = "promozione_modalita",
            joinColumns = @JoinColumn(name = "id_promozione"),
            inverseJoinColumns = @JoinColumn(name = "id_modalita")
    )
    private Set<Modalita> modalitaCoperte;

    // Getter & Setter
}
