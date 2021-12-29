/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.MovieAdmin;
import imp.IAction;
import java.util.List;
import java.util.Set;
import model.Category;
import model.CategoryMoive;
import model.Movie;
import org.hibernate.Session;
import util.HibernateMovie;

/**
 *
 * @author gaone
 */
public class DaoMovieAdmin implements IAction<MovieAdmin>  {
 private Session session;

    public DaoMovieAdmin(Session session) {
        this.session = session;
    }
    @Override
    public List<MovieAdmin> getAll() {
     session.beginTransaction();
     List<MovieAdmin>list= session.createQuery("from MovieAdmin").list();
     session.getTransaction().commit();
     return list;
    }

    @Override
    public MovieAdmin findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(MovieAdmin object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(MovieAdmin object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void add(MovieAdmin object) {
     session.beginTransaction();
     session.save(object);
     session.getTransaction().commit();
     session.close();
    }
    

//    public static void main(String[] args) {
//        List<MovieAdmin>list=new DaoMovieAdmin(HibernateMovie.openSession()).getAll();
//        System.out.println(list.toString());
//        
//    }
    
}
