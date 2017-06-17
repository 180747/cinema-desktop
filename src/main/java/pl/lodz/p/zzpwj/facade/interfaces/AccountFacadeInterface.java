package pl.lodz.p.zzpwj.facade.interfaces;

import pl.lodz.p.zzpwj.entity.Account;

import java.util.List;

public interface AccountFacadeInterface {

    void create(Account account);

    void edit(Account account);

    Account find(Object id);

    List<Account> findAll();

    List<Account> findRange(int[] range);

    int count();

    Account findByEmail(String email);

}