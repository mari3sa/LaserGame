package LaserGame.Controller;

import LaserGame.Entities.Utente;
import LaserGame.Services.CarrelloService;
import LaserGame.Services.PagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/carrello")
public class CarrelloController {

    @Autowired
    private CarrelloService carrelloService;

    @Autowired
    private PagamentoService pagamentoService;

    @PostMapping("/aggiungi")
    public void aggiungiAlCarrello(
            @RequestBody Utente utente,
            @RequestParam(required = false) Long modalitaId,
            @RequestParam(required = false) Long abbonamentoId,
            @RequestParam(required = false) Long promozioneId,
            @RequestParam int quantita
    ) {
        carrelloService.aggiungiAlCarrello(utente, modalitaId, abbonamentoId, promozioneId, quantita);
    }

    @PostMapping("/rimuovi")
    public void rimuoviDalCarrello(@RequestBody Utente utente, @RequestParam Long articoloCarrelloId) {
        carrelloService.rimuoviDalCarrello(utente, articoloCarrelloId);
    }

    @GetMapping("/totale")
    public BigDecimal calcolaTotale(@RequestBody Utente utente) {
        return carrelloService.calcolaTotale(utente);
    }

    @PostMapping("/paga")
    public void processaPagamento(@RequestBody Utente utente,@RequestParam String metodoPagamento) {
        BigDecimal totaleCarrello = carrelloService.calcolaTotale(utente); // Ottieni il totale dal carrello
        pagamentoService.processaPagamento(utente, totaleCarrello, metodoPagamento); // Passa l'importo del carrello
    }
}

