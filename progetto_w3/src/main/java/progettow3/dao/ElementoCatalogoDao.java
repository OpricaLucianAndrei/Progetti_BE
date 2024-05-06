package progettow3.dao;

import progettow3.entities.Rivista;

import javax.persistence.EntityManager;

public class ElementoCatalogoDao {
    private EntityManager em;

    public ElementoCatalogoDao(EntityManager em) {
        this.em = em;
    }

    public void save(Rivista rivista) {
        em.getTransaction().begin();
        em.persist(rivista);
        em.getTransaction().commit();
    }

    public void update(Rivista rivista) {
        em.getTransaction().begin();
        em.merge(rivista);
        em.getTransaction().commit();
    }

    public void delete(Rivista rivista) {
        em.getTransaction().begin();
        em.remove(rivista);
        em.getTransaction().commit();
    }
}
