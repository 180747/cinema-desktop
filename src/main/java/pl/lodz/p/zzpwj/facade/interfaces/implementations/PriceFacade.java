package pl.lodz.p.zzpwj.facade.interfaces.implementations;

import pl.lodz.p.zzpwj.entity.AbstractFacade;
import pl.lodz.p.zzpwj.entity.Price;
import pl.lodz.p.zzpwj.facade.interfaces.PriceFacadeInterface;

import javax.persistence.EntityManager;

public class PriceFacade extends AbstractFacade<Price> implements PriceFacadeInterface {

    public PriceFacade() {
        super(Price.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }


    public Price find() {
        this.em = createEntityManagerFactory();
        return super.findAll().get(0);
    }

    @Override
    public void edit(Price price) {
        this.em = createEntityManagerFactory();
        super.edit(price);
    }

}
