package cristianorocchi.gestioneviaggi.controller;

import cristianorocchi.gestioneviaggi.entities.Dipendente;
import cristianorocchi.gestioneviaggi.payloads.NewDipendenteDTO;
import cristianorocchi.gestioneviaggi.services.DipendenteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/dipendenti")
public class DipendenteController {

    @Autowired
    private DipendenteService dipendenteService;

    @GetMapping
    public Page<Dipendente> trovaTuttiDipendentiPageable(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return dipendenteService.trovaTuttiPageable(pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Dipendente creaDipendente(@Valid @RequestBody NewDipendenteDTO newDipendenteDTO) {
        Dipendente dipendente = new Dipendente();
        dipendente.setNome(newDipendenteDTO.nome());
        dipendente.setCognome(newDipendenteDTO.cognome());
        dipendente.setUsername(newDipendenteDTO.username());
        dipendente.setEmail(newDipendenteDTO.email());
        dipendente.setImmagineProfilo(newDipendenteDTO.immagineProfilo());

        return dipendenteService.salva(dipendente);
    }

    @GetMapping("/{dipendenteId}")
    public Dipendente trovaDipendentePerId(@PathVariable Long dipendenteId) {
        return dipendenteService.trovaPerId(dipendenteId);
    }

    @PutMapping("/{dipendenteId}")
    public Dipendente aggiornaDipendente(@PathVariable Long dipendenteId, @Valid @RequestBody NewDipendenteDTO newDipendenteDTO) {
        Dipendente esistente = dipendenteService.trovaPerId(dipendenteId);

        esistente.setUsername(newDipendenteDTO.username());
        esistente.setNome(newDipendenteDTO.nome());
        esistente.setCognome(newDipendenteDTO.cognome());
        esistente.setEmail(newDipendenteDTO.email());
        esistente.setImmagineProfilo(newDipendenteDTO.immagineProfilo());

        return dipendenteService.salva(esistente);
    }

    @DeleteMapping("/{dipendenteId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancellaDipendente(@PathVariable Long dipendenteId) {
        dipendenteService.cancella(dipendenteId);
    }


    @PostMapping("/{dipendenteId}/img")
    @ResponseStatus(HttpStatus.OK)
    public Dipendente uploadImmagineProfilo(@PathVariable Long dipendenteId, @RequestParam("immagine") MultipartFile file) throws IOException {
        return dipendenteService.uploadImmagineProfilo(dipendenteId, file);
    }
}
