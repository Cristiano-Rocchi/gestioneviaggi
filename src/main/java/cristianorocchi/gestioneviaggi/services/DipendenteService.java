package cristianorocchi.gestioneviaggi.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import cristianorocchi.gestioneviaggi.entities.Dipendente;

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
        return dipendenteRepository.save(dipendente);
    }

    public Dipendente trovaPerId(Long dipendenteId) {
        return dipendenteRepository.findById(dipendenteId)
                .orElseThrow(() -> new NotFoundException("Dipendente "+ dipendenteId +" non trovato " ));
    }

    public void cancella(Long dipendenteId) {
        Dipendente dipendente = trovaPerId(dipendenteId);
        dipendenteRepository.delete(dipendente);
    }

    public Dipendente uploadImmagineProfilo(Long dipendenteId, MultipartFile file) throws IOException {

        String url = (String) cloudinaryUploader.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("url");
        System.out.println("URL------>" + url);

        Dipendente dipendente = trovaPerId(dipendenteId);


        dipendente.setImmagineProfilo(url);


        return dipendenteRepository.save(dipendente);
    }


}




