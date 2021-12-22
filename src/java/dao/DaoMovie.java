/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import imp.IAction;
import java.util.ArrayList;
import model.Movie;
import java.util.List;
import model.Filmtype;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.hibernate.type.Type;
import util.HibernateMovie;

/**
 *
 * @author gaone
 */
public class DaoMovie implements IAction<Movie> {

    private Session session;

    public DaoMovie(Session session) {
        this.session = session;
    }

    @Override
    public List<Movie> getAll() {
        session.beginTransaction();
        List<Movie> movies = session.createQuery("from Movie").list();
        session.getTransaction().commit();
        return movies;
    }

    @Override
    public Movie findById(int id) {
        session.beginTransaction();
        Movie movie = (Movie) session.get(Movie.class, id);
        session.getTransaction().commit();
        return movie;
    }

    public List<Movie> findByName(String str, boolean flag) {
        if (flag) {
            session.beginTransaction();
            List<Movie> movies = session.createQuery("from Movie where nameMovie='" + str + "'").list();
            session.getTransaction().commit();
            return movies;
        } else {
            session.beginTransaction();
            List<Movie> movies = session.createQuery("from Movie where nameMovie LIKE '%" + str + "%'").list();
            session.getTransaction().commit();
            return movies;
        }
    }

    @Override
    public void delete(Movie object) {
        session.beginTransaction();
        session.delete(object);
        session.getTransaction().commit();
        session.close();
    }
     public void delete(int id) {
        session.beginTransaction();
        session.createSQLQuery("DELETE FROM `movie` WHERE `movie`.`id_movie` = '"+id+"'");
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(Movie object) {
        session.beginTransaction();
        session.update(object);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void add(Movie object) {
        session.beginTransaction();
        session.save(object);
        session.getTransaction().commit();
        session.close();
    }

    public List<Movie> seachMovie(String str) {
        if (StringUtils.isNumeric(str)) {
            List<Movie> movieList = new ArrayList();
            int id = Integer.parseInt(str);
            Movie movie = findById(id);
            if (movie != null) {
                movieList.add(movie);
            }
            return movieList;
        } else {
            List<Movie> movieList = findByName(str, false);
            return movieList;
        }
    }

    public int countMovie() {
        session.beginTransaction();
        List countList = session.createSQLQuery("SELECT COUNT(id_movie) FROM `movie`").list();
        session.getTransaction().commit();
        int count = Integer.parseInt(countList.get(0).toString());
        return count;
    }

    public int countNewMovieAdd() {
        session.beginTransaction();
        List countList = session.createSQLQuery("SELECT COUNT(dateAdd) FROM `movie` WHERE YEAR(dateAdd)=YEAR(CURRENT_DATE()) AND MONTH(dateAdd)=MONTH(CURRENT_DATE())").list();
        session.getTransaction().commit();
        int count = Integer.parseInt(countList.get(0).toString());
        return count;
    }
}
