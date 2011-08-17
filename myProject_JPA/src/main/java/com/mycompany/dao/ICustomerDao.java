/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.dao;

import com.mycompany.entity.Customer;
import java.util.Collection;

/**
 * @author abdelkafi_s
 */
public interface ICustomerDao {

    public Collection<Customer> getAll();

    public Customer getById(Long id);
    
    Collection<Customer> findByName(String name);

    public void save(Customer customer);

    public void delete(Long id);

}
