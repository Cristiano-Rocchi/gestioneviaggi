package cristianorocchi.gestioneviaggi.services;



import cristianorocchi.gestioneviaggi.entities.Viaggio;
import cristianorocchi.gestioneviaggi.repositories.ViaggioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViaggioService {

    @Autowired
    private ViaggioRepository viaggioRepository;

    public List<Viaggio> trovaTutti() {
        return viaggioRepository.findAll();
    }

    public Viaggio trovaPerId(Long id) {
        return viaggioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("il viaggio non è stato trovato"));
    }

    public Viaggio salva(Viaggio viaggio) {
        return viaggioRepository.save(viaggio);
    }

    public void cancella(Long id) {
        viaggioRepository.deleteById(id);
    }
}
