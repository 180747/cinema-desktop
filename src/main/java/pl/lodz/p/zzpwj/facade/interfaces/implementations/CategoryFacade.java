package pl.lodz.p.zzpwj.facade.interfaces.implementations;

import pl.lodz.p.zzpwj.entity.AbstractFacade;
import pl.lodz.p.zzpwj.entity.Category;
import pl.lodz.p.zzpwj.facade.interfaces.CategoryFacadeInterface;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class CategoryFacade extends AbstractFacade<Category> implements CategoryFacadeInterface {

    public CategoryFacade() {
        super(Category.class);
    }

    @Override
    public void create(Category entity) {
        this.em = createEntityManagerFactory();
        super.create(entity);

    }

    public Category findByName(String categoryName) {
        this.em = createEntityManagerFactory();
        TypedQuery<Category> tq = em.createNamedQuery("Category.findByName", Category.class);
        tq.setParameter("name", categoryName);
        List<Category> categoryList = tq.getResultList();
        if(categoryList.isEmpty())
        {
            return null;
        }
        return categoryList.get(0);
    }

    @Override
    public List<Category> findAll() {
        this.em = createEntityManagerFactory();
        TypedQuery<Category> tq = em.createNamedQuery("Category.findAll", Category.class);
        return tq.getResultList();
    }


    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
