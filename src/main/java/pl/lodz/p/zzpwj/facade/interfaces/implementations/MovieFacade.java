package pl.lodz.p.zzpwj.facade.interfaces.implementations;

import pl.lodz.p.zzpwj.entity.AbstractFacade;
import pl.lodz.p.zzpwj.entity.Movie;
import pl.lodz.p.zzpwj.facade.interfaces.MovieFacadeInterface;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class MovieFacade extends AbstractFacade<Movie> implements MovieFacadeInterface {

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MovieFacade() {
        super(Movie.class);
    }

    @Override
    public List<Movie> findAll() {
        this.em = createEntityManagerFactory();
        TypedQuery<Movie> tq = em.createNamedQuery("Movie.findAll", Movie.class);
        return tq.getResultList();
    }

    public Movie findByTitle(String title) {
        this.em = createEntityManagerFactory();
        TypedQuery<Movie> tq = em.createNamedQuery("Movie.findByTitle", Movie.class);
        tq.setParameter("title", title);
        List<Movie> acc = tq.getResultList();
        if(acc.isEmpty())
            return null;
        return acc.get(0);
    }

    @Override
    public void create(Movie entity) {
        this.em = createEntityManagerFactory();
        super.create(entity);
    }
}
