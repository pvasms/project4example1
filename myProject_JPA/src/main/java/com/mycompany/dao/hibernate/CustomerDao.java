package com.mycompany.dao.hibernate;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mycompany.dao.ICustomerDao;
import com.mycompany.entity.Customer;


/**
 * @author abdelkafi_s
 */
@Repository
public class CustomerDao implements ICustomerDao{
	
	@PersistenceContext
	private EntityManager em;

    public Collection<Customer> getAll() {
    	Query query = em.createQuery("from Customer");
        return query.getResultList();
    }
    
    public Collection<Customer> findByName(String name) {
    	Query query = em.createQuery("from Customer as c where c.name like :name");
    	query.setParameter("name", name);
        return query.getResultList();
    }

    public Customer getById(Long id) {
        return em.find(Customer.class, id);
    }

    @Transactional
    public void save(Customer customer) {
        em.persist(customer);
    }

    @Transactional
    public void delete(Long id) {
    	Customer customer = em.find(Customer.class, id);  
    	em.remove(customer);
    }
	   
}
