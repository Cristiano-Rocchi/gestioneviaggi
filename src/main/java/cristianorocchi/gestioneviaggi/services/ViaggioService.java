package cristianorocchi.gestioneviaggi.services;

import cristianorocchi.gestioneviaggi.entities.Viaggio;
import cristianorocchi.gestioneviaggi.exceptions.NotFoundException;
import cristianorocchi.gestioneviaggi.repositories.ViaggioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ViaggioService {

    @Autowired
    private ViaggioRepository viaggioRepository;

    public Page<Viaggio> trovaTuttiPageable(Pageable pageable) {
        return viaggioRepository.findAll(pageable);
    }

    public Viaggio salva(Viaggio viaggio) {
        return viaggioRepository.save(viaggio);
    }

    public Viaggio trovaPerId(Long viaggioId) {
        return viaggioRepository.findById(viaggioId)
                .orElseThrow(() -> new NotFoundException("Viaggio non trovato con ID: " + viaggioId));
    }

    public void cancella(Long viaggioId) {
        Viaggio viaggio = trovaPerId(viaggioId);
        viaggioRepository.delete(viaggio);
    }
}

