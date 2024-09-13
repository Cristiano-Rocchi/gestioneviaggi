package cristianorocchi.gestioneviaggi.controller;



import cristianorocchi.gestioneviaggi.entities.Prenotazione;
import cristianorocchi.gestioneviaggi.services.PrenotazioneService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/prenotazioni")
public class PrenotazioneController {

    @Autowired
    private PrenotazioneService prenotazioneService;

    @GetMapping
    public List<Prenotazione> trovaTuttePrenotazioni() {
        return prenotazioneService.trovaTutte();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Prenotazione creaPrenotazione(@Valid @RequestBody Prenotazione prenotazione) {
        return prenotazioneService.salva(prenotazione);
    }

    @GetMapping("/{prenotazioneId}")
    public Prenotazione trovaPrenotazionePerId(@PathVariable Long prenotazioneId) {
        return prenotazioneService.trovaPerId(prenotazioneId);
    }

    @PutMapping("/{prenotazioneId}")
    public Prenotazione aggiornaPrenotazione(@PathVariable Long prenotazioneId, @RequestBody Prenotazione prenotazione) {
        Prenotazione esistente = prenotazioneService.trovaPerId(prenotazioneId);
        esistente.setDipendente(prenotazione.getDipendente());
        esistente.setViaggio(prenotazione.getViaggio());
        esistente.setDataRichiesta(prenotazione.getDataRichiesta());
        esistente.setNote(prenotazione.getNote());
        esistente.setPreferenze(prenotazione.getPreferenze());
        return prenotazioneService.salva(esistente);
    }

    @DeleteMapping("/{prenotazioneId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancellaPrenotazione(@PathVariable Long prenotazioneId) {
        prenotazioneService.cancella(prenotazioneId);
    }
}
