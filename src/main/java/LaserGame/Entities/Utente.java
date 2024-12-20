package LaserGame.Entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import LaserGame.Utils.enumeration.TipoUtente;
//Questa classe rappresenta un'entità più completa e dettagliata, destinata a essere persistente
// nel database.
//È un'entità JPA (Java Persistence API) che viene utilizzata per archiviare e recuperare dati
//persistenti relativi all'utente.
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "utente")
public class Utente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "cognome", nullable = false)
    private String cognome;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "telefono", nullable = false)
    private String telefono;

    @Column(name = "dataiscrizione", nullable = false)
    private LocalDate dataiscrizione;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false)
    private TipoUtente tipo;


}
