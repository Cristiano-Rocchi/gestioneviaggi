package cristianorocchi.gestioneviaggi.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "prenotazione", uniqueConstraints = {@UniqueConstraint(columnNames = {"dipendente_id", "dataRichiesta"})})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Prenotazione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "dipendente_id", nullable = false)
    private Dipendente dipendente;

    @ManyToOne
    @JoinColumn(name = "viaggio_id", nullable = false)
    private Viaggio viaggio;

    @Column(nullable = false)
    private java.sql.Date dataRichiesta;

    @Column
    private String note;

    @Column
    private String preferenze;
}

