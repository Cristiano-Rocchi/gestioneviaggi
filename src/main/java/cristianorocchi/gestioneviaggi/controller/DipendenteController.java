package cristianorocchi.gestioneviaggi.controller;



import cristianorocchi.gestioneviaggi.entities.Dipendente;
import cristianorocchi.gestioneviaggi.services.DipendenteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/dipendenti")
public class DipendenteController {

    @Autowired
    private DipendenteService dipendenteService;

    @GetMapping
    public List<Dipendente> trovaTuttiDipendenti() {
        return dipendenteService.trovaTutti();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Dipendente creaDipendente(@Valid @RequestBody Dipendente dipendente) {
        return dipendenteService.salva(dipendente);
    }

    @GetMapping("/{dipendenteId}")
    public Dipendente trovaDipendentePerId(@PathVariable Long dipendenteId) {
        return dipendenteService.trovaPerId(dipendenteId);
    }

    @PutMapping("/{dipendenteId}")
    public Dipendente aggiornaDipendente(@PathVariable Long dipendenteId, @RequestBody Dipendente dipendente) {
        Dipendente esistente = dipendenteService.trovaPerId(dipendenteId);
        esistente.setUsername(dipendente.getUsername());
        esistente.setNome(dipendente.getNome());
        esistente.setCognome(dipendente.getCognome());
        esistente.setEmail(dipendente.getEmail());
        esistente.setImmagineProfilo(dipendente.getImmagineProfilo());
        return dipendenteService.salva(esistente);
    }

    @DeleteMapping("/{dipendenteId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancellaDipendente(@PathVariable Long dipendenteId) {
        dipendenteService.cancella(dipendenteId);
    }
}
