package LaserGame.Entities;

import LaserGame.Utils.enumeration;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "abbonamento")
public class Abbonamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "utente_id", nullable = false)
    private int utenteId; // Riferimento all'utente

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false)
    private enumeration.TipoAbbonamento tipo; // Es. "Mensile", "Annuale", ecc.

    @Column(name = "data_inizio", nullable = false)
    private LocalDate dataInizio;

    @Column(name = "data_fine", nullable = false)
    private LocalDate dataFine;

    @Column(name = "prezzo", nullable = false)
    private BigDecimal prezzo;

    // Relazione con Modalita
    @ManyToMany
    @JoinTable(
            name = "abbonamento_modalita",
            joinColumns = @JoinColumn(name = "id_abbonamento"),
            inverseJoinColumns = @JoinColumn(name = "id_modalita")
    )
    private Set<Modalita> modalitaDisponibili;

}

