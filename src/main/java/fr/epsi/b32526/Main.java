package fr.epsi.b32526;

import jakarta.persistence.*;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();

        try {
            Livre livre1 = em.find(Livre.class, 1);
            if (livre1 != null) {
                System.out.println("Find ID=1 -> " + livre1.getTitre() + " / " + livre1.getAuteur());
            } else {
                System.out.println("Livre ID=1 introuvable");
            }

            em.getTransaction().begin();
            Livre nouveau = new Livre("Le Comte de Monte-Cristo", "Alexandre Dumas");
            em.persist(nouveau);
            em.getTransaction().commit();
            System.out.println("Insert -> " + nouveau);

            em.getTransaction().begin();
            Livre livre5 = em.find(Livre.class, 5);
            if (livre5 != null) {
                livre5.setTitre("Du plaisir dans la cuisine");
                System.out.println("Update ID=5 -> " + livre5);
            } else {
                System.out.println("Livre ID=5 introuvable (impossible de corriger le titre)");
            }
            em.getTransaction().commit();

            TypedQuery<Livre> qTitre = em.createQuery(
                    "SELECT l FROM Livre l WHERE l.titre = :titre", Livre.class
            );
            qTitre.setParameter("titre", "Germinal");
            List<Livre> resTitre = qTitre.getResultList();
            System.out.println("JPQL par titre='Germinal' -> " + resTitre);

            TypedQuery<Livre> qAuteur = em.createQuery(
                    "SELECT l FROM Livre l WHERE l.auteur = :auteur", Livre.class
            );
            qAuteur.setParameter("auteur", "Jules Verne");
            List<Livre> resAuteur = qAuteur.getResultList();
            System.out.println("JPQL par auteur='Jules Verne' -> " + resAuteur);

            em.getTransaction().begin();
            Livre aSupprimer = em.find(Livre.class, nouveau.getId());
            if (aSupprimer != null) {
                em.remove(aSupprimer);
                System.out.println("Delete -> " + aSupprimer);
            } else {
                System.out.println("Livre à supprimer introuvable");
            }
            em.getTransaction().commit();

            TypedQuery<Livre> qAll = em.createQuery(
                    "SELECT l FROM Livre l ORDER BY l.id", Livre.class
            );
            List<Livre> tous = qAll.getResultList();

            System.out.println("---- Tous les livres ----");
            for (Livre l : tous) {
                System.out.println(l.getTitre() + " / " + l.getAuteur());
            }

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}
