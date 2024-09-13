package cristianorocchi.gestioneviaggi.services;

import cristianorocchi.gestioneviaggi.entities.Dipendente;
import cristianorocchi.gestioneviaggi.entities.Prenotazione;
import cristianorocchi.gestioneviaggi.entities.Viaggio;
import cristianorocchi.gestioneviaggi.repositories.DipendenteRepository;
import cristianorocchi.gestioneviaggi.repositories.PrenotazioneRepository;
import cristianorocchi.gestioneviaggi.repositories.ViaggioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrenotazioneService {

    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    @Autowired
    private DipendenteRepository dipendenteRepository;

    @Autowired
    private ViaggioRepository viaggioRepository;

    public List<Prenotazione> trovaTutte() {
        return prenotazioneRepository.findAll();
    }

    public Prenotazione trovaPerId(Long id) {
        Prenotazione prenotazione = prenotazioneRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("la prenotazione non è stata trovata"));


        Dipendente dipendente = dipendenteRepository.findById(prenotazione.getDipendente().getId())
                .orElseThrow(() -> new RuntimeException("il dipendente non è stato trovato"));

        Viaggio viaggio = viaggioRepository.findById(prenotazione.getViaggio().getId())
                .orElseThrow(() -> new RuntimeException("il viaggio non è stato trovato"));


        prenotazione.setDipendente(dipendente);
        prenotazione.setViaggio(viaggio);

        return prenotazione;
    }

    public Prenotazione salva(Prenotazione prenotazione) {

        Dipendente dipendente = dipendenteRepository.findById(prenotazione.getDipendente().getId())
                .orElseThrow(() -> new RuntimeException("il dipendente non è stato trovato"));

        Viaggio viaggio = viaggioRepository.findById(prenotazione.getViaggio().getId())
                .orElseThrow(() -> new RuntimeException("il viaggio non è stato trovato"));

        prenotazione.setDipendente(dipendente);
        prenotazione.setViaggio(viaggio);


        return prenotazioneRepository.save(prenotazione);
    }

    public void cancella(Long id) {
        prenotazioneRepository.deleteById(id);
    }
}
