package cristianorocchi.gestioneviaggi.services;

import cristianorocchi.gestioneviaggi.entities.Viaggio;
import cristianorocchi.gestioneviaggi.exceptions.BadRequestException;
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

        if (viaggio.getDestinazione() == null || viaggio.getDestinazione().isEmpty()) {
            throw new BadRequestException("La destinazione è obbligatoria.");
        }
        if (viaggio.getData() == null) {
            throw new BadRequestException("La data è obbligatoria.");
        }
        if (viaggio.getStato() == null || viaggio.getStato().isEmpty()) {
            throw new BadRequestException("Lo stato è obbligatorio.");
        }

        return viaggioRepository.save(viaggio);
    }

    public Viaggio trovaPerId(Long viaggioId) {

        return viaggioRepository.findById(viaggioId)
                .orElseThrow(() -> new NotFoundException("Viaggio con id"+ viaggioId +" non trovato" ));
    }

    public void cancella(Long viaggioId) {

        Viaggio viaggio = trovaPerId(viaggioId);
        if (viaggio == null) {
            throw new NotFoundException("Viaggio con id " + viaggioId + " non trovato");
        }
        viaggioRepository.delete(viaggio);
    }
}
