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
@Table(name = "modalita")
public class Modalita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nomeModalita;

    @Column(nullable = false)
    private String descrizione;

    @Column(nullable = false)
    private BigDecimal prezzo;

    @Column(nullable = false)
    private Integer numeroMinPartecipanti;

    @Column(nullable = false)
    private Integer numeroMaxPartecipanti;

    @OneToMany(mappedBy = "laserGamePackage", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<SlotOrario> slotAvailabilities;

    // Relazione ManyToMany con Abbonamento
    @ManyToMany(mappedBy = "modalitaSet")
    private Set<Abbonamento> abbonamenti;

    @ManyToMany(mappedBy = "modalitaCoperte")
    private Set<Promozione> promozioni;

    // Costruttore senza argomenti (necessario per JPA)
    public Modalita() {}

    // Costruttore con ID
    public Modalita(Long id) {
        this.id = id;
    }
    // Getter & Setter (generati automaticamente da Lombok)
}
