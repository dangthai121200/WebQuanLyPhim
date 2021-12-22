/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imp;

import java.util.List;


/**
 *
 * @author gaone
 */
public interface IAction<T> {
    List<T> getAll();
    T findById(int id);
    void delete(T object);
    void update(T object);
    void add(T object);
}
