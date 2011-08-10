/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.dao;

import com.mycompany.entity.CustomerOrder;
import java.util.Collection;

/**
 * @author abdelkafi_s
 */
public interface ICustomerOrderDao {

    Collection<CustomerOrder> getAll();

    CustomerOrder getById(Long id);

    void save(CustomerOrder customerOrder);

    void delete(Long id);

}
