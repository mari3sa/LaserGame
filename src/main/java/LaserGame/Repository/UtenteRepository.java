package LaserGame.Repository;

//Servono per accedere e manipolare i dati nel database in modo strutturato e comodo,
// utilizzando le funzionalità già fornite da Spring Data JPA.

import LaserGame.Entities.Utente;
import LaserGame.Utils.enumeration.TipoUtente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UtenteRepository extends JpaRepository<Utente, Long> {

    List<Utente> findByTipo(TipoUtente tipo);

    @Query("SELECT u FROM Utente u WHERE LOWER(u.email) = LOWER(:email)")
    Optional<Utente> findByEmail(@Param("email") String email);
}
