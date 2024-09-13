package cristianorocchi.gestioneviaggi.services;

import cristianorocchi.gestioneviaggi.entities.Dipendente;
import cristianorocchi.gestioneviaggi.entities.Prenotazione;
import cristianorocchi.gestioneviaggi.entities.Viaggio;
import cristianorocchi.gestioneviaggi.payloads.NewPrenotazioneDTO;
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
                .orElseThrow(() -> new RuntimeException("La prenotazione non è stata trovata"));

        Dipendente dipendente = dipendenteRepository.findById(prenotazione.getDipendente().getId())
                .orElseThrow(() -> new RuntimeException("Il dipendente non è stato trovato"));

        Viaggio viaggio = viaggioRepository.findById(prenotazione.getViaggio().getId())
                .orElseThrow(() -> new RuntimeException("Il viaggio non è stato trovato"));

        prenotazione.setDipendente(dipendente);
        prenotazione.setViaggio(viaggio);

        return prenotazione;
    }

    public Prenotazione salvaDaDTO(NewPrenotazioneDTO newPrenotazioneDTO) {
        Dipendente dipendente = dipendenteRepository.findById(newPrenotazioneDTO.dipendenteId())
                .orElseThrow(() -> new RuntimeException("Il dipendente non è stato trovato"));

        Viaggio viaggio = viaggioRepository.findById(newPrenotazioneDTO.viaggioId())
                .orElseThrow(() -> new RuntimeException("Il viaggio non è stato trovato"));

        Prenotazione prenotazione = new Prenotazione();
        prenotazione.setDipendente(dipendente);
        prenotazione.setViaggio(viaggio);
        prenotazione.setDataRichiesta(newPrenotazioneDTO.dataRichiesta());
        prenotazione.setNote(newPrenotazioneDTO.note());
        prenotazione.setPreferenze(newPrenotazioneDTO.preferenze());

        return prenotazioneRepository.save(prenotazione);
    }

    public Prenotazione aggiornaDaDTO(Long prenotazioneId, NewPrenotazioneDTO newPrenotazioneDTO) {
        Prenotazione prenotazione = prenotazioneRepository.findById(prenotazioneId)
                .orElseThrow(() -> new RuntimeException("La prenotazione non è stata trovata"));

        Dipendente dipendente = dipendenteRepository.findById(newPrenotazioneDTO.dipendenteId())
                .orElseThrow(() -> new RuntimeException("Il dipendente non è stato trovato"));

        Viaggio viaggio = viaggioRepository.findById(newPrenotazioneDTO.viaggioId())
                .orElseThrow(() -> new RuntimeException("Il viaggio non è stato trovato"));

        prenotazione.setDipendente(dipendente);
        prenotazione.setViaggio(viaggio);
        prenotazione.setDataRichiesta(newPrenotazioneDTO.dataRichiesta());
        prenotazione.setNote(newPrenotazioneDTO.note());
        prenotazione.setPreferenze(newPrenotazioneDTO.preferenze());

        return prenotazioneRepository.save(prenotazione);
    }

    public void cancella(Long id) {
        prenotazioneRepository.deleteById(id);
    }

    public Prenotazione assegnaDipendenteAViaggio(Long dipendenteId, Long viaggioId) {
        Dipendente dipendente = dipendenteRepository.findById(dipendenteId)
                .orElseThrow(() -> new RuntimeException("Il dipendente non è stato trovato"));

        Viaggio viaggio = viaggioRepository.findById(viaggioId)
                .orElseThrow(() -> new RuntimeException("Il viaggio non è stato trovato"));

        Prenotazione prenotazione = new Prenotazione();
        prenotazione.setDipendente(dipendente);
        prenotazione.setViaggio(viaggio);
        prenotazione.setDataRichiesta(java.time.LocalDate.now().toString());

        return prenotazioneRepository.save(prenotazione);
    }
}
