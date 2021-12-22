/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.DaoFilmType;
import model.Filmtype;
import imp.IAction;
import java.util.List;
import util.HibernateMovie;

/**
 *
 * @author gaone
 */
public class ServiceFilmType implements IAction<Filmtype> {
    private DaoFilmType daoFilmType;

    public ServiceFilmType() {
        this.daoFilmType=new DaoFilmType(HibernateMovie.openSession());
    }
    

    @Override
    public List<Filmtype> getAll() {
        return daoFilmType.getAll();
    }

    @Override
    public Filmtype findById(int id) {
        return daoFilmType.findById(id);
    }

    @Override
    public void delete(Filmtype object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Filmtype object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void add(Filmtype object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
