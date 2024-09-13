package cristianorocchi.gestioneviaggi.services;



import cristianorocchi.gestioneviaggi.entities.Dipendente;
import cristianorocchi.gestioneviaggi.repositories.DipendenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DipendenteService {

    @Autowired
    private DipendenteRepository dipendenteRepository;

    public List<Dipendente> trovaTutti() {
        return dipendenteRepository.findAll();
    }

    public Dipendente trovaPerId(Long id) {
        return dipendenteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("il dipendente non è stato trovato"));
    }

    public Dipendente salva(Dipendente dipendente) {
        return dipendenteRepository.save(dipendente);
    }

    public void cancella(Long id) {
        dipendenteRepository.deleteById(id);
    }
}



