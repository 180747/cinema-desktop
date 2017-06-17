package pl.lodz.p.zzpwj.facade.interfaces;

import pl.lodz.p.zzpwj.entity.Movie;

import java.util.List;

public interface MovieFacadeInterface {

    void create(Movie movie);

    void edit(Movie movie);

    Movie find(Object id);

    List<Movie> findAll();

    List<Movie> findRange(int[] range);

    int count();

    Movie findByTitle(String title);

}
