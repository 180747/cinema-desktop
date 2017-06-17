package pl.lodz.p.zzpwj.facade.interfaces;

import pl.lodz.p.zzpwj.entity.Seat;

import java.util.List;

public interface SeatFacadeInterface {

    void edit(Seat seat);

    Seat find(Object id);

    List<Seat> findAll();

    List<Seat> findRange(int[] range);

    int count();

    Seat findByNumber(int number);
}
