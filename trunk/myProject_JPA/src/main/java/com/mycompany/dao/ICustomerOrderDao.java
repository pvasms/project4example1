/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.dao;

import com.mycompany.entity.CustomerOrder;
import java.util.Collection;

/**
 *
 * @author abdelkafi_s
 */
public interface ICustomerOrderDao {

    public Collection<CustomerOrder> getAll();

    public CustomerOrder getById(Long id);

    public void save(CustomerOrder customerOrder);

    public void delete(CustomerOrder customerOrde);

}
