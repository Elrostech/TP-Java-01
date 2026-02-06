package fr.epsi.b32526;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "EMPRUNT")
public class Emprunt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "DATE_DEBUT", nullable = false)
    private LocalDateTime dateDebut;

    @Column(name = "DATE_FIN")
    private LocalDateTime dateFin;

    @Column(name = "DELAI")
    private Integer delai;

    @ManyToOne
    @JoinColumn(name = "ID_CLIENT", nullable = false)
    private Client client;

    @ManyToMany
    @JoinTable(
            name = "COMPO",
            joinColumns = @JoinColumn(name = "ID_EMP"),
            inverseJoinColumns = @JoinColumn(name = "ID_LIV")
    )
    private List<Livre> livres = new ArrayList<>();

    public Emprunt() {}

    // 🔽 Getters strictement utiles pour le TP

    public Integer getId() {
        return id;
    }

    public Client getClient() {
        return client;
    }

    public List<Livre> getLivres() {
        return livres;
    }

    @Override
    public String toString() {
        return "Emprunt{id=" + id +
                ", debut=" + dateDebut +
                ", fin=" + dateFin +
                ", delai=" + delai +
                ", client=" + (client != null ? client.getId() : null) +
                "}";
    }
}
