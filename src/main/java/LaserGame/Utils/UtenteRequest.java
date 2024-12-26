package LaserGame.Utils;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class UtenteRequest {
    private String nome;
    private String cognome;
    private String email;
    private String telefono;
    private String password;
}
