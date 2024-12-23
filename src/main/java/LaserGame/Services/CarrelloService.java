package LaserGame.Services;

import LaserGame.Entities.Carrello;
import LaserGame.Entities.ArticoloCarrello;
import LaserGame.Entities.Utente;
import LaserGame.Entities.Modalita;
import LaserGame.Entities.Abbonamento;
import LaserGame.Entities.Promozione;
import LaserGame.Repository.CarrelloRepository;
import LaserGame.Repository.ArticoloCarrelloRepository;
import LaserGame.Repository.ModalitaRepository;
import LaserGame.Repository.AbbonamentoRepository;
import LaserGame.Repository.PromozioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class CarrelloService {

    @Autowired
    private CarrelloRepository carrelloRepository;

    @Autowired
    private ArticoloCarrelloRepository articoloCarrelloRepository;

    @Autowired
    private ModalitaRepository modalitaRepository;

    @Autowired
    private AbbonamentoRepository abbonamentoRepository;

    @Autowired
    private PromozioneRepository promozioneRepository;

    public void aggiungiAlCarrello(Utente utente, Long modalitaId, Long abbonamentoId, Long promozioneId, int quantita) {
        Optional<Carrello> optionalCarrello = carrelloRepository.findByUtenteId(utente.getId());

        Carrello carrello = optionalCarrello.orElseGet(() -> {
            Carrello nuovoCarrello = new Carrello();
            nuovoCarrello.setUtente(utente);
            return carrelloRepository.save(nuovoCarrello);
        });

        // Gestire le modalità
        if (modalitaId != null) {
            Modalita modalita = modalitaRepository.findById(modalitaId)
                    .orElseThrow(() -> new IllegalArgumentException("Modalità non trovata"));
            aggiungiArticoloCarrello(carrello, modalita, quantita);
        }

        // Gestire gli abbonamenti
        if (abbonamentoId != null) {
            Abbonamento abbonamento = abbonamentoRepository.findById(abbonamentoId)
                    .orElseThrow(() -> new IllegalArgumentException("Abbonamento non trovato"));
            aggiungiArticoloCarrello(carrello, abbonamento, quantita);
        }

        // Gestire le promozioni
        if (promozioneId != null) {
            Promozione promozione = promozioneRepository.findById(promozioneId)
                    .orElseThrow(() -> new IllegalArgumentException("Promozione non trovata"));
            aggiungiArticoloCarrello(carrello, promozione, quantita);
        }
    }

    private void aggiungiArticoloCarrello(Carrello carrello, Object item, int quantita) {
        ArticoloCarrello articoloCarrello = new ArticoloCarrello();
        articoloCarrello.setCarrello(carrello);
        articoloCarrello.setQuantita(quantita);

        if (item instanceof Modalita) {
            Modalita modalita = (Modalita) item;
            articoloCarrello.setModalita(modalita);
            articoloCarrello.setPrezzoUnitario(modalita.getPrezzo());
            articoloCarrello.setTotale(modalita.getPrezzo().multiply(BigDecimal.valueOf(quantita)));
        } else if (item instanceof Abbonamento) {
            Abbonamento abbonamento = (Abbonamento) item;
            articoloCarrello.setAbbonamento(abbonamento);
            articoloCarrello.setPrezzoUnitario(abbonamento.getPrezzo());
            articoloCarrello.setTotale(abbonamento.getPrezzo().multiply(BigDecimal.valueOf(quantita)));
        } else if (item instanceof Promozione) {
            Promozione promozione = (Promozione) item;
            articoloCarrello.setPromozione(promozione);
            articoloCarrello.setPrezzoUnitario(promozione.getPrezzo());
            articoloCarrello.setTotale(promozione.getPrezzo().multiply(BigDecimal.valueOf(quantita)));
        } else {
            throw new IllegalArgumentException("Tipo articolo non supportato");
        }

        articoloCarrelloRepository.save(articoloCarrello);
    }

    // Rimuovere un articolo dal carrello
    public void rimuoviDalCarrello(Utente utente, Long articoloCarrelloId) {
        Carrello carrello = carrelloRepository.findByUtenteId(utente.getId())
                .orElseThrow(() -> new IllegalArgumentException("Carrello non trovato"));

        ArticoloCarrello articoloCarrello = articoloCarrelloRepository.findById(articoloCarrelloId)
                .orElseThrow(() -> new IllegalArgumentException("Articolo nel carrello non trovato"));

        articoloCarrelloRepository.delete(articoloCarrello);
    }

    // Calcola il totale del carrello
    public BigDecimal calcolaTotale(Utente utente) {
        Carrello carrello = carrelloRepository.findByUtenteId(utente.getId())
                .orElseThrow(() -> new IllegalArgumentException("Carrello non trovato"));

        return articoloCarrelloRepository.findTotaleByCarrelloId(carrello.getId());
    }
}
