package cristianorocchi.gestioneviaggi.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import cristianorocchi.gestioneviaggi.entities.Dipendente;
import cristianorocchi.gestioneviaggi.exceptions.BadRequestException;
import cristianorocchi.gestioneviaggi.exceptions.NotFoundException;
import cristianorocchi.gestioneviaggi.repositories.DipendenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class DipendenteService {

    @Autowired
    private DipendenteRepository dipendenteRepository;

    @Autowired
    private Cloudinary cloudinaryUploader;

    public List<Dipendente> trovaTutti() {
        return dipendenteRepository.findAll();
    }

    public Page<Dipendente> trovaTuttiPageable(Pageable pageable) {
        return dipendenteRepository.findAll(pageable);
    }

    public Dipendente salva(Dipendente dipendente) {
        // Verifica se i campi obbligatori sono presenti
        if (dipendente.getEmail() == null || dipendente.getEmail().isEmpty()) {
            throw new BadRequestException("email obbligatoria.");
        }
        if (dipendente.getUsername() == null || dipendente.getUsername().isEmpty()) {
            throw new BadRequestException("username obbligatorio.");
        }
        return dipendenteRepository.save(dipendente);
    }

    public Dipendente trovaPerId(Long dipendenteId) {

        return dipendenteRepository.findById(dipendenteId)
                .orElseThrow(() -> new NotFoundException("Dipendente " + dipendenteId + " non trovato"));
    }

    public void cancella(Long dipendenteId) {

        Dipendente dipendente = trovaPerId(dipendenteId);
        if (dipendente == null) {
            throw new NotFoundException("Dipendente " + dipendenteId + " non trovato");
        }
        dipendenteRepository.delete(dipendente);
    }

    public Dipendente uploadImmagineProfilo(Long dipendenteId, MultipartFile file) throws IOException {
        // Verifica se il file è vuoto
        if (file.isEmpty()) {
            throw new BadRequestException("immagine obbligatoria.");
        }


        String url;
        try {
            url = (String) cloudinaryUploader.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("url");
            System.out.println("URL------>" + url);
        } catch (IOException e) {
            throw new IOException("Errore durante il caricamento immagine", e);
        }


        Dipendente dipendente = trovaPerId(dipendenteId);
        dipendente.setImmagineProfilo(url);

        return dipendenteRepository.save(dipendente);
    }
}
