package pl.lodz.p.zzpwj.facade.interfaces.implementations;

import pl.lodz.p.zzpwj.entity.AbstractFacade;
import pl.lodz.p.zzpwj.entity.AdminAccount;
import pl.lodz.p.zzpwj.facade.interfaces.AdminAccountFacadeInterface;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class AdminAccountFacade extends AbstractFacade<AdminAccount> implements AdminAccountFacadeInterface {

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AdminAccountFacade() {
        super(AdminAccount.class);
    }

    @Override
    public List<AdminAccount> findAll() {
        this.em = createEntityManagerFactory();
        TypedQuery<AdminAccount> tq = em.createNamedQuery("AdminAccount.findAll", AdminAccount.class);
        //closeEntityManagerFactory();
        return tq.getResultList();
    }

    public AdminAccount findByLogin(String login) {
        this.em = createEntityManagerFactory();
        TypedQuery<AdminAccount> tq = em.createNamedQuery("AdminAccount.findByLogin", AdminAccount.class);
        tq.setParameter("login", login);
        List<AdminAccount> acc = tq.getResultList();
        if(acc.isEmpty())
            return null;
        return acc.get(0);
    }


}
