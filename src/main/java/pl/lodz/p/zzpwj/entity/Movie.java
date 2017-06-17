package pl.lodz.p.zzpwj.entity;

import java.io.Serializable;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the movie database table.
 *
 */
@Entity
@NamedQuery(name="Movie.findAll", query="SELECT m FROM Movie m")
public class Movie implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer idMovie;
    private SimpleStringProperty description;
    private SimpleIntegerProperty duration;
    private SimpleStringProperty title;
    private Long version;
    private Category category;
    private List<Seance> seances;

    public Movie() {
    }


    @Id
    @SequenceGenerator(name="movieSeq", sequenceName="movie_SEQ", allocationSize=1, initialValue=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="movieSeq")
    @Column(name="id_movie")
    public Integer getIdMovie() {
        return this.idMovie;
    }

    public void setIdMovie(Integer idMovie) {
        this.idMovie = idMovie;
    }


    public String getDescription() {
        return this.description.get();
    }

    public void setDescription(String description) {
        this.description = new SimpleStringProperty(description);
    }


    public Integer getDuration() {
        return this.duration.get();
    }

    public void setDuration(Integer duration) {
        this.duration = new SimpleIntegerProperty(duration);
    }


    public String getTitle() {
        return this.title.get();
    }

    public void setTitle(String title) {
        this.title = new SimpleStringProperty(title);
    }

    //uni-directional many-to-one association to Category
    @JoinColumn(name = "id_category", referencedColumnName = "id_category", nullable = false)
    @ManyToOne(optional = false)
    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getCategoryName() {
        return this.category.getName();
    }

    //bi-directional many-to-one association to Seance
    @OneToMany(mappedBy="movie", cascade={CascadeType.PERSIST, CascadeType.MERGE})
    public List<Seance> getSeances() {
        return this.seances;
    }

    public void setSeances(List<Seance> seances) {
        this.seances = seances;
    }

    public Seance addSeance(Seance seance) {
        getSeances().add(seance);
        seance.setMovie(this);

        return seance;
    }

    public Seance removeSeance(Seance seance) {
        getSeances().remove(seance);
        seance.setMovie(null);

        return seance;
    }

}
