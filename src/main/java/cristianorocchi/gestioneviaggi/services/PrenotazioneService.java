package cristianorocchi.gestioneviaggi.services;



import cristianorocchi.gestioneviaggi.entities.Prenotazione;
import cristianorocchi.gestioneviaggi.repositories.PrenotazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrenotazioneService {

    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    public List<Prenotazione> trovaTutte() {
        return prenotazioneRepository.findAll();
    }

    public Prenotazione trovaPerId(Long id) {
        return prenotazioneRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("la prenotazione non è stata trovata"));
    }

    public Prenotazione salva(Prenotazione prenotazione) {
        return prenotazioneRepository.save(prenotazione);
    }

    public void cancella(Long id) {
        prenotazioneRepository.deleteById(id);
    }
}
