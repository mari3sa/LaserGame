package LaserGame.Entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import LaserGame.Utils.enumeration.StatoPrenotazione;
import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "prenotazione")
public class Prenotazione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_modalita")
    private Modalita modalita;

    @Column(nullable = false)
    private LocalDateTime data;

    @Column(nullable = false)
    private Integer numeroOre;

    @Column(nullable = false)
    private Integer numeroPartecipanti;

    @Enumerated(EnumType.STRING)
    @Column(name = "statoPrenotazione", nullable = false)
    private StatoPrenotazione status;
    // Getter & Setter
}

