/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.dao.hibernate;

import com.mycompany.dao.ICustomerOrderDao;
import com.mycompany.entity.CustomerOrder;
import java.util.Collection;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 *
 * @author abdelkafi_s
 */
public class CustomerOrderDao  extends HibernateDaoSupport implements ICustomerOrderDao{

  public Collection<CustomerOrder> getAll() {
        return getHibernateTemplate().loadAll(CustomerOrder.class);
    }

    public CustomerOrder getById(Long id) {
         return (CustomerOrder) getHibernateTemplate().load(CustomerOrder.class, id);
    }

    public void save(CustomerOrder customerOrder) {
        getHibernateTemplate().save(customerOrder);
    }

    public void delete(CustomerOrder customerOrder) {
        getHibernateTemplate().delete(customerOrder);
    }

}
