package pl.lodz.p.zzpwj.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the admin_account database table.
 *
 */
@Entity
@Table(name="admin_account")
@NamedQuery(name="AdminAccount.findAll", query="SELECT a FROM AdminAccount a")
public class AdminAccount implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer idAdminAccount;
    private String email;
    private String login;
    private String name;
    private String password;
    private String surname;
    private Long version;

    public AdminAccount() {
    }


    @Id
    @SequenceGenerator(name="ADMIN_ACCOUNT_IDADMINACCOUNT_GENERATOR" )
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ADMIN_ACCOUNT_IDADMINACCOUNT_GENERATOR")
    @Column(name="id_admin_account")
    public Integer getIdAdminAccount() {
        return this.idAdminAccount;
    }

    public void setIdAdminAccount(Integer idAdminAccount) {
        this.idAdminAccount = idAdminAccount;
    }


    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getLogin() {
        return this.login;
    }

    public void setLogin(String login) {
        this.login = login;
    }


    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
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
