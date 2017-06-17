package pl.lodz.p.zzpwj.facade.interfaces.implementations;

import pl.lodz.p.zzpwj.entity.AbstractFacade;
import pl.lodz.p.zzpwj.entity.Movie;
import pl.lodz.p.zzpwj.entity.Seance;
import pl.lodz.p.zzpwj.facade.interfaces.SeanceFacadeInterface;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;

public class SeanceFacade extends AbstractFacade<Seance> implements SeanceFacadeInterface {

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SeanceFacade() {
        super(Seance.class);
    }

    @Override
    public List<Seance> findAll() {
        this.em = createEntityManagerFactory();
        TypedQuery<Seance> tq = em.createNamedQuery("Seance.findAll", Seance.class);
        return tq.getResultList();
    }

    public List<Seance> findByDate(Date date) {
        this.em = createEntityManagerFactory();
        TypedQuery<Seance> tq = em.createNamedQuery("Seance.findByDate", Seance.class);
        tq.setParameter("date", date);
        return tq.getResultList();
    }


    public Seance findByDateAndFilm(Date date, Movie movie) {
        this.em = createEntityManagerFactory();
        TypedQuery<Seance> tq = em.createNamedQuery("Seance.findByDateAndFilm", Seance.class);
        tq.setParameter("date", date);
        tq.setParameter("movie", movie);
        return tq.getSingleResult();
    }

}