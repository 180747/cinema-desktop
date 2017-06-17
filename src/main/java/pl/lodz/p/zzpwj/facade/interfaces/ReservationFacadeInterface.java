package pl.lodz.p.zzpwj.facade.interfaces;

import pl.lodz.p.zzpwj.entity.Reservation;

import java.util.List;

public interface ReservationFacadeInterface {

    void create(Reservation reservation);

    void edit(Reservation reservation);

    Reservation find(Object id);

    List<Reservation> findAll();

    List<Reservation> findRange(int[] range);

    int count();

    Reservation findByReservationNumber(int reservationNumber);

}