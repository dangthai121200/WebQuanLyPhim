/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Admin;
import imp.IAction;
import java.sql.ResultSet;
import java.util.List;
import org.hibernate.Session;
import util.HibernateMovie;
import imp.ICheckLogin;
import imp.ICheckName;
import imp.ICheckPassword;
import imp.IListName;
import java.util.Date;

/**
 *
 * @author gaone
 */
public class DaoAdmin implements ICheckLogin<Admin>, IAction<Admin>, IListName, ICheckName {

    private Session session;

    public DaoAdmin(Session session) {
        this.session = session;
    }

    @Override
    public Admin checkLogin(String username, String password) {
        List<Admin> admins = null;
        session.beginTransaction();
        admins = session.createQuery("from Admin where username=:username").setParameter("username", username).list();
        session.getTransaction().commit();
        if (admins.size() > 0 && admins.get(0).getPassword().equals(password)) {
            return admins.get(0);
        }
        return null;
    }

    @Override
    public List<Admin> getAll() {
        session.beginTransaction();
        List<Admin> admins = session.createQuery("from Admin").list();
        session.getTransaction().commit();
        return admins;
    }

    @Override
    public Admin findById(int id) {
        session.beginTransaction();
        Admin admin = (Admin) session.get(Admin.class, id);
        session.getTransaction().commit();
        return admin;
    }

    @Override
    public void delete(Admin object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Admin object) {
        session.beginTransaction();
        session.update(object);
        session.getTransaction().commit();
        session.close();
    }

    public void update(int id, String fullName, String password, Date birthday, String gmail, Integer sdt) {
        session.beginTransaction();
        Admin admin = (Admin) session.get(Admin.class, id);
        admin.setFullName(fullName);
        if(password.length()>0){
               admin.setPassword(password);
        }
        admin.setBirthday(birthday);
        admin.setGmail(gmail);
        admin.setSdt(sdt);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void add(Admin object) {
        session.beginTransaction();
        session.save(object);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<String> getAllUserName() {
        session.beginTransaction();
        List<String> listUserNamse = session.createQuery("select username from Admin").list();
        session.getTransaction().commit();
        return listUserNamse;
    }
//    public static void main(String[] args) {
//        List<String> admins=new DaoAdmin(HibernateMovie.openSession()).getAllUserName();
//        System.out.println(admins.toString());
//    }

    @Override
    public boolean checkUserName(String username) {
        List<String> names = null;
        session.beginTransaction();
        names = session.createQuery("select username from Admin where username=:username").setParameter("username", username).list();
        session.getTransaction().commit();
        if (names.size() == 0) {
            return false;
        }
        return true;
    }
    public int countAdmin() {
        session.beginTransaction();
        List countList = session.createSQLQuery("SELECT COUNT(id_admin) FROM `admin`").list();
        session.getTransaction().commit();
        int count = Integer.parseInt(countList.get(0).toString());
        return count;
    }
}
