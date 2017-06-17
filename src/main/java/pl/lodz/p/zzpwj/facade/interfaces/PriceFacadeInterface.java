package pl.lodz.p.zzpwj.facade.interfaces;

import pl.lodz.p.zzpwj.entity.Price;

public interface PriceFacadeInterface {

    Price find();

    void edit(Price price);

}
