package cristianorocchi.gestioneviaggi.repositories;



import cristianorocchi.gestioneviaggi.entities.Viaggio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ViaggioRepository extends JpaRepository<Viaggio, Long> {
}

