package pl.lodz.p.zzpwj.facade.interfaces;

import pl.lodz.p.zzpwj.entity.AdminAccount;

import java.util.List;

public interface AdminAccountFacadeInterface {

    void create(AdminAccount adminAccount);

    void edit(AdminAccount adminAccount);

    AdminAccount find(Object id);

    List<AdminAccount> findAll();

    List<AdminAccount> findRange(int[] range);

    int count();

    AdminAccount findByLogin(String login);
}
