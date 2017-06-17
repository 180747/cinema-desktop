package pl.lodz.p.zzpwj.facade.interfaces;

import pl.lodz.p.zzpwj.entity.Category;

import java.util.List;

public interface CategoryFacadeInterface {

    void create(Category category);

    Category find(Object id);

    Category findByName(String categoryName);

    List<Category> findAll();

    int count();

}
