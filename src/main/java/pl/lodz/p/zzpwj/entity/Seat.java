package pl.lodz.p.zzpwj.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the seat database table.
 *
 */
@Entity
@NamedQuery(name="Seat.findAll", query="SELECT s FROM Seat s")
public class Seat implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer idSeat;
    private String number;
    private Long version;

    public Seat() {
    }


    @Id
    @SequenceGenerator(name="SEAT_IDSEAT_GENERATOR" )
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEAT_IDSEAT_GENERATOR")
    @Column(name="id_seat")
    public Integer getIdSeat() {
        return this.idSeat;
    }

    public void setIdSeat(Integer idSeat) {
        this.idSeat = idSeat;
    }


    public String getNumber() {
        return this.number;
    }

    public void setNumber(String number) {
        this.number = number;
    }


    public Long getVersion() {
        return this.version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

}