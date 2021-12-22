/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.DaoAdmin;
import model.Admin;
import imp.IAction;
import util.HibernateMovie;
import imp.ICheckLogin;
import imp.ICheckName;
import imp.ICheckPassword;
import java.util.List;
import imp.IListName;
import java.util.Date;

/**
 *
 * @author gaone
 */
public class ServiceAdmin implements ICheckLogin<Admin>, IAction<Admin>, IListName, ICheckName, ICheckPassword {

    private DaoAdmin daoAdmin;

    public ServiceAdmin() {
        daoAdmin = new DaoAdmin(HibernateMovie.openSession());
    }

    @Override
    public Admin checkLogin(String username, String password) {
        return daoAdmin.checkLogin(username, password);
    }

    @Override
    public List<Admin> getAll() {
        return daoAdmin.getAll();
    }

    @Override
    public Admin findById(int id) {
        return daoAdmin.findById(id);
    }

    @Override
    public void delete(Admin object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Admin object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void update(int id, String fullName, String password, Date birthday, String gmail, Integer sdt) {
        daoAdmin.update(id, fullName, password, birthday, gmail, sdt);
    }

    @Override
    public void add(Admin object) {
        daoAdmin.add(object);
    }

    @Override
    public List<String> getAllUserName() {
        return daoAdmin.getAllUserName();
    }

    @Override
    public boolean checkUserName(String username) {
        return daoAdmin.checkUserName(username);
    }

    @Override
    public boolean checkPassword(int id, String password) {
        Admin admin = findById(id);
        if (admin.getPassword().equals(password)) {
            return true;
        }
        return false;
    }
    public int countAdmin() {
       
        return daoAdmin.countAdmin();
    }
}
