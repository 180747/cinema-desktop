package pl.lodz.p.zzpwj.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the ticket database table.
 *
 */
@Entity
@NamedQuery(name="Ticket.findAll", query="SELECT t FROM Ticket t")
public class Ticket implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer idTicket;
    private BigDecimal price;
    private Long version;
    private Seance seance;
    private Seat seat;

    public Ticket() {
    }


    @Id
    @SequenceGenerator(name="TICKET_IDTICKET_GENERATOR" )
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TICKET_IDTICKET_GENERATOR")
    @Column(name="id_ticket")
    public Integer getIdTicket() {
        return this.idTicket;
    }

    public void setIdTicket(Integer idTicket) {
        this.idTicket = idTicket;
    }


    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }


    public Long getVersion() {
        return this.version;
    }

    public void setVersion(Long version) {
        this.version = version;
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
