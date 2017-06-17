package pl.lodz.p.zzpwj.entity;

import java.io.Serializable;

import javafx.beans.property.SimpleStringProperty;

import javax.persistence.*;


/**
 * The persistent class for the helpCategory database table.
 *
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "Category.findAll", query = "SELECT c FROM Category c"),
        @NamedQuery(name = "Category.findByName", query = "SELECT c FROM Category c WHERE c.name = :name")})
public class Category implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer idCategory;
    private SimpleStringProperty name;
    private Long version;

    public Category() {
    }


    @Id
    @SequenceGenerator(name="categorySeq", sequenceName="CATEGORY_SEQ", allocationSize=1, initialValue=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="categorySeq")
    @Column(name="id_category")
    public Integer getIdCategory() {
        return this.idCategory;
    }

    public void setIdCategory(Integer idCategory) {
        this.idCategory = idCategory;
    }


    public String getName() {
        return this.name.get();
    }

    public void setName(String name) {
        this.name = new SimpleStringProperty(name);
    }

}
