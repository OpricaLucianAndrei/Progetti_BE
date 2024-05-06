package progettow3.dao;

import progettow3.entities.Libro;

import javax.persistence.*;
import java.util.List;

public class LibroDao {
    private EntityManager em;

    public LibroDao(EntityManager em) {
        this.em = em;
    }

    public void save(Libro libro) {
        em.getTransaction().begin();
        em.persist(libro);
        em.getTransaction().commit();
    }

    public void deleteByISBN(String codiceISBN) {
        em.getTransaction().begin();
        Libro libro = em.find(Libro.class, codiceISBN);
        if (libro != null) {
            em.remove(libro);
        }
        em.getTransaction().commit();
    }

    public Libro findByISBN(String codiceISBN) {
        return em.find(Libro.class, codiceISBN);
    }

    public List<Libro> findByAnnoPubblicazione(int anno) {
        Query query = em.createQuery("SELECT l FROM Libro l WHERE YEAR(l.dataPubblicazione) = :anno", Libro.class);
        query.setParameter("anno", anno);
        return query.getResultList();
    }

    public List<Libro> findByAutore(String autore) {
        Query query = em.createQuery("SELECT l FROM Libro l WHERE l.autore = :autore", Libro.class);
        query.setParameter("autore", autore);
        return query.getResultList();
    }

    public List<Libro> findByTitolo(String titolo) {
        Query query = em.createQuery("SELECT l FROM Libro l WHERE LOWER(l.titolo) LIKE :titolo", Libro.class);
        query.setParameter("titolo", "%" + titolo.toLowerCase() + "%");
        return query.getResultList();
    }
}
