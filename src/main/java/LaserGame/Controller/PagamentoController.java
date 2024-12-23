package LaserGame.Controller;

import LaserGame.Entities.Utente;
import LaserGame.Services.PagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/pagamento")
public class PagamentoController {

    @Autowired
    private PagamentoService pagamentoService;

    @PostMapping("/processa")
    public void processaPagamento(
            @RequestBody Utente utente,
            @RequestParam BigDecimal importo,
            @RequestParam String metodoPagamento
    ) {
        pagamentoService.processaPagamento(utente, importo, metodoPagamento);
    }


}
