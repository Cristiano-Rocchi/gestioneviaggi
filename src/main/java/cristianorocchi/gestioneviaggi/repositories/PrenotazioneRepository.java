package cristianorocchi.gestioneviaggi.repositories;



import cristianorocchi.gestioneviaggi.entities.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long> {
}
