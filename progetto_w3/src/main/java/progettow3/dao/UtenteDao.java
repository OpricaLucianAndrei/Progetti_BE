package progettow3.dao;

import progettow3.entities.Utente;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

public class UtenteDao {
    private EntityManager em;
    public UtenteDao(EntityManager em) {
        this.em = em;
    }
    public void save(Utente utente){
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.persist(utente);
        et.commit();
    }

    public Utente getByNumeroDiTessera(int numeroDiTessera){
        return em.find(Utente.class,numeroDiTessera);
    }

    public void delete(int numeroDiTessera){
        EntityTransaction et = em.getTransaction();
        et.begin();
        Utente utente = getByNumeroDiTessera(numeroDiTessera);

        if(utente!=null){
            em.remove(utente);
        }
        else{
            System.out.println("Utente non presente");
        }

        et.commit();

    }

    public List<Utente> getPersonaByName(String nome){
        Query query = em.createNamedQuery("getUtenteByName");
        query.setParameter("nome", nome);
        return query.getResultList();
    }

    public List<Utente> getPersonaByCognome(String cognome){
        Query query = em.createQuery("select p from Utente p where p.cognome= :cognome");
        query.setParameter("cognome", cognome);
        return query.getResultList();
    }

    public List<Utente> getPersonaByPartName(String nome){
        Query query = em.createQuery("select p from Utente p where p.nome like :nome");
        query.setParameter("nome", "%" + nome + "%");
        return query.getResultList();
    }
}
