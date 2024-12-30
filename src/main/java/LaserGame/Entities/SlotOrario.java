package LaserGame.Entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "slotOrario")
public class SlotOrario{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "package_id", nullable = false)
    private Modalita modalita;

    @Column(nullable = false)
    private LocalDateTime data;

    @Column(nullable = false)
    private Integer orariDisponibili;

    @Column(name = "prenotata", nullable = false)
    private boolean prenotata = false;


    // Getter & Setter
}
