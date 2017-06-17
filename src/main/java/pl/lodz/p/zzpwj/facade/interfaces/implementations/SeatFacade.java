package pl.lodz.p.zzpwj.facade.interfaces.implementations;

import pl.lodz.p.zzpwj.entity.AbstractFacade;
import pl.lodz.p.zzpwj.entity.Seat;
import pl.lodz.p.zzpwj.facade.interfaces.SeatFacadeInterface;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class SeatFacade extends AbstractFacade<Seat> implements SeatFacadeInterface {

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SeatFacade() {
        super(Seat.class);
    }

    @Override
    public List<Seat> findAll() {
        this.em = createEntityManagerFactory();
        TypedQuery<Seat> tq = em.createNamedQuery("Seat.findAll", Seat.class);
        return tq.getResultList();
    }

    public Seat findByNumber(int number) {
        this.em = createEntityManagerFactory();
        TypedQuery<Seat> tq = em.createNamedQuery("Seat.findByNumber", Seat.class);
        tq.setParameter("number", number);
        return tq.getSingleResult();
    }

}
