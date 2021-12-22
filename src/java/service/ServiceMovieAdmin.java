/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.DaoMovie;
import dao.DaoMovieAdmin;
import model.MovieAdmin;
import imp.IAction;
import java.util.List;
import util.HibernateMovie;

/**
 *
 * @author gaone
 */
public class ServiceMovieAdmin implements IAction<MovieAdmin> {
    private DaoMovieAdmin daoMovieAdmin;

    public ServiceMovieAdmin() {
        daoMovieAdmin=new DaoMovieAdmin(HibernateMovie.openSession());
    }
    
    
    @Override
    public List<MovieAdmin> getAll() {
        return daoMovieAdmin.getAll();
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
       daoMovieAdmin.add(object);
    }
    
}
