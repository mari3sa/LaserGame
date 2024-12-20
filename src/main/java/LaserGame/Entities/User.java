package LaserGame.Entities;
//Questa classe User è generalmente utilizzata per rappresentare un'entità più leggera
//e destinata al trasferimento di dati tra il backend e il frontend o per logiche di business.
//Ad esempio, potrebbe essere utilizzata per la registrazione o il login degli utenti,
// dove i dettagli come firstName, lastName, email, e password vengono utilizzati per
// l'autenticazione o per raccogliere i dati personali di base degli utenti.
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class User {

    private String nome;
    private String cognome;
    private String email;
    private String password;

}
