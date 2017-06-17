package pl.lodz.p.zzpwj.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the reservation database table.
 *
 */
@Entity
@NamedQuery(name="Reservation.findAll", query="SELECT r FROM Reservation r")
public class Reservation implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long idReservation;
    private Boolean active;
    private Long reservationNumber;
    private Long version;
    private Account account;
    private Seance seance;
    private Seat seat;

    public Reservation() {
    }


    @Id
    @SequenceGenerator(name="RESERVATION_IDRESERVATION_GENERATOR" )
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="RESERVATION_IDRESERVATION_GENERATOR")
    @Column(name="id_reservation")
    public Long getIdReservation() {
        return this.idReservation;
    }

    public void setIdReservation(Long idReservation) {
        this.idReservation = idReservation;
    }


    public Boolean getActive() {
        return this.active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }


    @Column(name="reservation_number")
    public Long getReservationNumber() {
        return this.reservationNumber;
    }

    public void setReservationNumber(Long reservationNumber) {
        this.reservationNumber = reservationNumber;
    }


    public Long getVersion() {
        return this.version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }


    //uni-directional many-to-one association to Account
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_account")
    public Account getAccount() {
        return this.account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }


    //bi-directional many-to-one association to Seance
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_seance")
    public Seance getSeance() {
        return this.seance;
    }

    public void setSeance(Seance seance) {
        this.seance = seance;
    }


    //uni-directional many-to-one association to Seat
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_seat")
    public Seat getSeat() {
        return this.seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

}
