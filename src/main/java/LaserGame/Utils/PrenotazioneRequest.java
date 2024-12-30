package LaserGame.Utils;


import LaserGame.Entities.Abbonamento;
import LaserGame.Entities.Modalita;
import LaserGame.Entities.Promozione;
import LaserGame.Entities.Utente;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class PrenotazioneRequest {
    private Utente utente;
    private Modalita modalita;
    private LocalDateTime data;
    private Promozione promozione;
    private Abbonamento abbonamento;
}
