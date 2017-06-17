package pl.lodz.p.zzpwj.facade.interfaces.implementations;

import pl.lodz.p.zzpwj.entity.AbstractFacade;
import pl.lodz.p.zzpwj.entity.Seance;
import pl.lodz.p.zzpwj.entity.Ticket;
import pl.lodz.p.zzpwj.facade.interfaces.TicketFacadeInterface;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class TicketFacade extends AbstractFacade<Ticket> implements TicketFacadeInterface {

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TicketFacade() {
        super(Ticket.class);
    }

    @Override
    public List<Ticket> findAll() {
        this.em = createEntityManagerFactory();
        TypedQuery<Ticket> tq = em.createNamedQuery("Ticket.findAll", Ticket.class);
        return tq.getResultList();
    }

    public List<Ticket> findBySeance(Seance seance) {
        this.em = createEntityManagerFactory();
        TypedQuery<Ticket> tq = em.createNamedQuery("Ticket.findBySeance", Ticket.class);
        tq.setParameter("seance", seance);
        return tq.getResultList();
    }

}