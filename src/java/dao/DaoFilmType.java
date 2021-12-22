/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Filmtype;
import imp.IAction;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author gaone
 */
public class DaoFilmType implements IAction<Filmtype>{
    private Session session;

    public DaoFilmType(Session session) {
        this.session = session;
    }
    

    @Override
    public List<Filmtype> getAll() {
        session.beginTransaction();
        List<Filmtype> list=session.createQuery("from Filmtype").list();
        session.getTransaction().commit();
        return list;
    }

    @Override
    public Filmtype findById(int id) {
        session.beginTransaction();
        Filmtype filmType=(Filmtype) session.get(Filmtype.class, id);
        session.getTransaction().commit();
        return filmType;
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
