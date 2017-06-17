package pl.lodz.p.zzpwj.facade.interfaces;

import pl.lodz.p.zzpwj.entity.Movie;
import pl.lodz.p.zzpwj.entity.Seance;

import java.util.Date;
import java.util.List;

public interface SeanceFacadeInterface {

    void create(Seance seance);

    void edit(Seance seance);

    Seance find(Object id);

    List<Seance> findAll();

    List<Seance> findRange(int[] range);

    int count();

    List<Seance> findByDate(Date date);

    Seance findByDateAndFilm(Date date, Movie movie);

}
