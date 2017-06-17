package pl.lodz.p.zzpwj.facade.interfaces.implementations;

import pl.lodz.p.zzpwj.entity.AbstractFacade;
import pl.lodz.p.zzpwj.entity.Account;
import pl.lodz.p.zzpwj.facade.interfaces.AccountFacadeInterface;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class AccountFacade extends AbstractFacade<Account> implements AccountFacadeInterface {

    EntityManager em;


    @Override
    protected EntityManager getEntityManager() {
        em = createEntityManagerFactory();
        return em;
    }

    public AccountFacade() {
        super(Account.class);
    }

    @Override
    public List<Account> findAll() {
        em = createEntityManagerFactory();
        TypedQuery<Account> tq = em.createNamedQuery("Account.findAll", Account.class);
        return tq.getResultList();
    }

    public Account findByEmail(String email) {
        em = createEntityManagerFactory();
        TypedQuery<Account> tq = em.createNamedQuery("Account.findByEmail", Account.class);
        tq.setParameter("email", email);
        List<Account> acc = tq.getResultList();
        if(acc.isEmpty())
            return null;
        return acc.get(0);
    }
}
