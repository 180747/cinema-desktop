package pl.lodz.p.zzpwj.facade.interfaces.implementations;

import pl.lodz.p.zzpwj.entity.AbstractFacade;
import pl.lodz.p.zzpwj.entity.Reservation;
import pl.lodz.p.zzpwj.facade.interfaces.ReservationFacadeInterface;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class ReservationFacade extends AbstractFacade<Reservation> implements ReservationFacadeInterface {

    EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        em = createEntityManagerFactory();
        return em;
    }

    public ReservationFacade() {
        super(Reservation.class);
    }

    @Override
    public List<Reservation> findAll() {
        em = createEntityManagerFactory();
        TypedQuery<Reservation> tq = em.createNamedQuery("Reservation.findAll", Reservation.class);
        return tq.getResultList();
    }

    public Reservation findByReservationNumber(int number) {
        createEntityManagerFactory();
        TypedQuery<Reservation> tq = em.createNamedQuery("Reservation.findByNumber", Reservation.class);
        tq.setParameter("number", number);
        return tq.getSingleResult();
    }

}
