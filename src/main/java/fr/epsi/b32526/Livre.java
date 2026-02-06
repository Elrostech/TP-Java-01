package fr.epsi.b32526;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "LIVRE")
public class Livre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "TITRE", nullable = false, length = 255)
    private String titre;

    @Column(name = "AUTEUR", nullable = false, length = 50)
    private String auteur;

    @ManyToMany(mappedBy = "livres")
    private List<Emprunt> emprunts = new ArrayList<>();

    public Livre() {}

    public Livre(String titre, String auteur) {
        this.titre = titre;
        this.auteur = auteur;
    }

    public Integer getId() {
        return id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public List<Emprunt> getEmprunts() {
        return emprunts;
    }

    @Override
    public String toString() {
        return "Livre{id=" + id +
                ", titre='" + titre + '\'' +
                ", auteur='" + auteur + '\'' +
                '}';
    }
}
