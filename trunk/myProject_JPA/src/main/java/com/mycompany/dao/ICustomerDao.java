/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.dao;

import com.mycompany.entity.Customer;
import java.util.Collection;

/**
 *
 * @author abdelkafi_s
 */
public interface ICustomerDao {

    public Collection<Customer> getAll();

    public Customer getById(Long id);

    public void save(Customer dossier);

    public void delete(Customer dossier);

}
