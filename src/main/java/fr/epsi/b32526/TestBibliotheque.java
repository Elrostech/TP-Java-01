package fr.epsi.b32526;

import jakarta.persistence.*;
import java.util.List;

public class TestBibliotheque {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            Integer empruntId = 1;

            TypedQuery<Emprunt> q1 = em.createQuery(
                    "SELECT e FROM Emprunt e " +
                            "JOIN FETCH e.livres " +
                            "JOIN FETCH e.client " +
                            "WHERE e.id = :id", Emprunt.class
            );
            q1.setParameter("id", empruntId);

            Emprunt e = q1.getSingleResult();
            System.out.println("Emprunt " + e.getId() + " client=" + e.getClient());
            System.out.println("Livres de l'emprunt :");
            for (Livre l : e.getLivres()) {
                System.out.println("- " + l.getTitre() + " / " + l.getAuteur());
            }

            Integer clientId = 1;

            TypedQuery<Emprunt> q2 = em.createQuery(
                    "SELECT e FROM Emprunt e " +
                            "JOIN FETCH e.client " +
                            "WHERE e.client.id = :cid " +
                            "ORDER BY e.id", Emprunt.class
            );
            q2.setParameter("cid", clientId);

            List<Emprunt> emprunts = q2.getResultList();
            System.out.println("\nEmprunts du client ID=" + clientId);
            for (Emprunt emp : emprunts) {
                System.out.println(emp);
            }

            em.getTransaction().commit();

        } catch (Exception ex) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            ex.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}
