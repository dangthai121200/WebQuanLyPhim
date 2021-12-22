/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.DaoCategoryMovie;
import imp.IAction;
import java.util.List;
import model.CategoryMoive;
import util.HibernateMovie;

/**
 *
 * @author gaone
 */
public class ServiceCategoryMovie implements IAction<CategoryMoive>{

    private DaoCategoryMovie daoCategoryMovie;

    public ServiceCategoryMovie() {
        daoCategoryMovie=new DaoCategoryMovie(HibernateMovie.openSession());
    }
    
    @Override
    public List<CategoryMoive> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CategoryMoive findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(CategoryMoive object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(CategoryMoive object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void add(CategoryMoive object) {
       daoCategoryMovie.add(object);
    }
    
}
