package LaserGame.Services;

import LaserGame.Entities.Pagamento;
import LaserGame.Entities.Utente;
import LaserGame.Exception.PagamentoInesistenteException;
import LaserGame.Repository.PagamentoRepository;
import LaserGame.Utils.enumeration.StatoPagamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PagamentoService {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    /**
     * Processa un nuovo pagamento.
     *
     * @param utente          Utente che effettua il pagamento.
     * @param importo         Importo del pagamento.
     * @param metodoPagamento Metodo di pagamento utilizzato.
     * @return Pagamento creato e salvato.
     */
    @Transactional
    public Pagamento processaPagamento(Utente utente, BigDecimal importo, String metodoPagamento) {
        Pagamento pagamento = new Pagamento();
        pagamento.setUtente(utente);
        pagamento.setImporto(importo);
        pagamento.setMetodoPagamento(metodoPagamento);
        pagamento.setDataPagamento(LocalDateTime.now());

        // Simula il risultato del pagamento (es. SUCCESSO o FALLITO)
        StatoPagamento stato = simulaTransazione(importo);
        pagamento.setStato(stato);

        // Aggiungi un riferimento univoco per la transazione
        pagamento.setRiferimentoTransazione(generaRiferimentoTransazione());

        return pagamentoRepository.save(pagamento);
    }

    @Transactional(readOnly = true)
    public Pagamento findById(long id) {
        if (pagamentoRepository.findById(id).isPresent()) {
            return pagamentoRepository.findById(id).get();
        } else
            throw new PagamentoInesistenteException("Pagamento inesistente");
    }



    /**
     * Trova tutti i pagamenti di un utente specifico.
     *
     * @param utenteId ID dell'utente.
     * @return Lista di pagamenti dell'utente.
     */
    @Transactional(readOnly = true)
    public List<Pagamento> trovaPagamentiPerUtente(Long utenteId) {
        return pagamentoRepository.findByUtenteId(utenteId);
    }

    /**
     * Trova tutti i pagamenti in base al loro stato.
     *
     * @param stato Stato del pagamento.
     * @return Lista di pagamenti con lo stato specificato.
     */
    @Transactional(readOnly = true)
    public List<Pagamento> trovaPagamentiPerStato(StatoPagamento stato) {
        return pagamentoRepository.findByStato(stato);
    }

    /**
     * Trova pagamenti recenti (ultimi N giorni).
     *
     * @param giorni Numero di giorni da considerare.
     * @return Lista di pagamenti recenti.
     */
    @Transactional(readOnly = true)
    public List<Pagamento> trovaPagamentiRecenti(int giorni) {
        return pagamentoRepository.findRecentPayments(giorni);
    }

    /**
     * Genera un riferimento univoco per la transazione.
     *
     * @return Riferimento univoco.
     */
    private String generaRiferimentoTransazione() {
        return "TX-" + LocalDateTime.now().toString().replace(":", "").replace(".", "");
    }

    /**
     * Simula il risultato di una transazione.
     *
     * @param importo Importo della transazione.
     * @return Stato del pagamento (SUCCESSO o FALLITO).
     */
    private StatoPagamento simulaTransazione(BigDecimal importo) {
        // Per semplicità, consideriamo i pagamenti sotto una certa soglia come FALLITI
        BigDecimal sogliaMinima = new BigDecimal("10.00");
        return importo.compareTo(sogliaMinima) >= 0 ? StatoPagamento.SUCCESSO : StatoPagamento.FALLITO;
    }
}
