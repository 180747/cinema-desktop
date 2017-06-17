package pl.lodz.p.zzpwj.facade.interfaces;

import pl.lodz.p.zzpwj.entity.Seance;
import pl.lodz.p.zzpwj.entity.Ticket;

import java.util.List;

public interface TicketFacadeInterface {

    void create(Ticket ticket);

    void edit(Ticket ticket);

    Ticket find(Object id);

    List<Ticket> findAll();

    List<Ticket> findRange(int[] range);

    int count();

    List<Ticket> findBySeance(Seance seance);

}