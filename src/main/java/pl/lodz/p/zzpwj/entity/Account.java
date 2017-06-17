package pl.lodz.p.zzpwj.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the account database table.
 *
 */
@Entity
@NamedQuery(name="Account.findAll", query="SELECT a FROM Account a")
public class Account implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer idAccount;
    private String email;
    private String name;
    private String surname;
    private Long version;

    public Account() {
    }


    @Id
    @SequenceGenerator(name="ACCOUNT_IDACCOUNT_GENERATOR" )
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ACCOUNT_IDACCOUNT_GENERATOR")
    @Column(name="id_account")
    public Integer getIdAccount() {
        return this.idAccount;
    }

    public void setIdAccount(Integer idAccount) {
        this.idAccount = idAccount;
    }


    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getSurname() {
        return this.surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }


    public Long getVersion() {
        return this.version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

}
