package fr.epsi.b32526;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CLIENT")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "NOM", nullable = false, length = 50)
    private String nom;

    @Column(name = "PRENOM", nullable = false, length = 50)
    private String prenom;

    @OneToMany(mappedBy = "client")
    private List<Emprunt> emprunts = new ArrayList<>();

    public Client() {}

    public Client(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }

    public Integer getId() { return id; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public List<Emprunt> getEmprunts() { return emprunts; }

    @Override
    public String toString() {
        return "Client{id=" + id + ", nom='" + nom + "', prenom='" + prenom + "'}";
    }
}
