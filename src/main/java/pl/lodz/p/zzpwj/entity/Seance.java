package pl.lodz.p.zzpwj.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Time;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the seance database table.
 *
 */
@Entity
@NamedQuery(name="Seance.findAll", query="SELECT s FROM Seance s")
public class Seance implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer idSeance;
    private Boolean active;
    private Date date;
    private Time hour;
    private Long version;
    private List<Reservation> reservations;
    private Movie movie;
    private List<Ticket> tickets;

    public Seance() {
    }


    @Id
    @SequenceGenerator(name="SEANCE_IDSEANCE_GENERATOR" )
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEANCE_IDSEANCE_GENERATOR")
    @Column(name="id_seance")
    public Integer getIdSeance() {
        return this.idSeance;
    }

    public void setIdSeance(Integer idSeance) {
        this.idSeance = idSeance;
    }


    public Boolean getActive() {
        return this.active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }


    @Temporal(TemporalType.DATE)
    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    public Time getHour() {
        return this.hour;
    }

    public void setHour(Time hour) {
        this.hour = hour;
    }


    public Long getVersion() {
        return this.version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }


    //bi-directional many-to-one association to Reservation
    @OneToMany(mappedBy="seance")
    public List<Reservation> getReservations() {
        return this.reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public Reservation addReservation(Reservation reservation) {
        getReservations().add(reservation);
        reservation.setSeance(this);

        return reservation;
    }

    public Reservation removeReservation(Reservation reservation) {
        getReservations().remove(reservation);
        reservation.setSeance(null);

        return reservation;
    }


    //bi-directional many-to-one association to Movie
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_movie")
    public Movie getMovie() {
        return this.movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }


    //bi-directional many-to-one association to Ticket
    @OneToMany(mappedBy="seance")
    public List<Ticket> getTickets() {
        return this.tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public Ticket addTicket(Ticket ticket) {
        getTickets().add(ticket);
        ticket.setSeance(this);

        return ticket;
    }

    public Ticket removeTicket(Ticket ticket) {
        getTickets().remove(ticket);
        ticket.setSeance(null);

        return ticket;
    }

}
