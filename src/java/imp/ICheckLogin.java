/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imp;

/**
 *
 * @author gaone
 */
public interface ICheckLogin<T> {
    T checkLogin(String username,String password);
}
