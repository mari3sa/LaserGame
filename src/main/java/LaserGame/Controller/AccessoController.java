package LaserGame.Controller;
import LaserGame.Utils.enumeration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/public/accesso")
public class AccessoController {

    @GetMapping("/credenziali")
    public ResponseEntity<?> getCredenziali() {
        try {
            Map<String, Object> response = new HashMap<>();
            response.put("username", enumeration.usernameAdmin);
            response.put("password", enumeration.passwordAdmin);
            return ResponseEntity.ok(response);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Impossibile effettuare l'operazione");
        }
    }
}