package cristianorocchi.gestioneviaggi.controller;



import cristianorocchi.gestioneviaggi.entities.Viaggio;
import cristianorocchi.gestioneviaggi.services.ViaggioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/viaggi")
public class ViaggioController {

    @Autowired
    private ViaggioService viaggioService;

    @GetMapping
    public List<Viaggio> trovaTuttiViaggi() {
        return viaggioService.trovaTutti();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Viaggio creaViaggio(@Valid @RequestBody Viaggio viaggio) {
        return viaggioService.salva(viaggio);
    }

    @GetMapping("/{viaggioId}")
    public Viaggio trovaViaggioPerId(@PathVariable Long viaggioId) {
        return viaggioService.trovaPerId(viaggioId);
    }

    @PutMapping("/{viaggioId}")
    public Viaggio aggiornaViaggio(@PathVariable Long viaggioId, @RequestBody Viaggio viaggio) {
        Viaggio esistente = viaggioService.trovaPerId(viaggioId);
        esistente.setDestinazione(viaggio.getDestinazione());
        esistente.setData(viaggio.getData());
        esistente.setStato(viaggio.getStato());
        return viaggioService.salva(esistente);
    }

    @DeleteMapping("/{viaggioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancellaViaggio(@PathVariable Long viaggioId) {
        viaggioService.cancella(viaggioId);
    }
}

