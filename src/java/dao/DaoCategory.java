/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Category;
import imp.IAction;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.hibernate.Session;
import util.HibernateMovie;

/**
 *
 * @author gaone
 */
public class DaoCategory implements IAction<Category> {

    private Session session;

    public DaoCategory(Session session) {
        this.session = session;
    }

    @Override
    public List<Category> getAll() {
        session.beginTransaction();
        List<Category> list = session.createQuery("from Category").list();
        session.getTransaction().commit();
        return list;
    }

    @Override
    public Category findById(int id) {
        session.beginTransaction();
        Category category = (Category) session.get(Category.class, id);
        session.getTransaction().commit();
        return category;
    }

    @Override
    public void delete(Category object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Category object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void add(Category object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Set getAllByListId(char[] lsitIdCategory) {
        Set categorys = new HashSet(0);
        for (int i = 0; i < lsitIdCategory.length; i++) {
            int id=Integer.parseInt(String.valueOf(lsitIdCategory[i]));
            categorys.add(findById(id));
        }
        return categorys;
    }
}
