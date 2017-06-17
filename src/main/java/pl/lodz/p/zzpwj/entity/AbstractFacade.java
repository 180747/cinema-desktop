package pl.lodz.p.zzpwj.entity;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;


public abstract class AbstractFacade<T> {

    private Class<T> entityClass;

    protected EntityManager em;
    EntityManagerFactory factory;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    protected EntityManager createEntityManagerFactory() {
        factory = Persistence.createEntityManagerFactory("CinemaProject");
        em = factory.createEntityManager();

        return em;
    }

    public void closeEntityManagerFactory() {
        em.close();
        factory.close();
    }

    public void create(T entity) throws PersistenceException {
        EntityTransaction etx = getEntityManager().getTransaction();
        etx.begin();
        try {
            getEntityManager().persist(entity);
            getEntityManager().flush();
            etx.commit();
        } catch(PersistenceException e) {
            etx.rollback();
        }

    }


    public void edit(T entity) throws PersistenceException {
        getEntityManager().getTransaction().begin();
        try {
            getEntityManager().merge(entity);
            getEntityManager().flush();
            getEntityManager().getTransaction().commit();
        } catch (PersistenceException e) {
            getEntityManager().getTransaction().rollback();
            throw new PersistenceException();
        }



    }

    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    public List<T> findAll() {
        createEntityManagerFactory();
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<T> findRange(int[] range) {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }



}