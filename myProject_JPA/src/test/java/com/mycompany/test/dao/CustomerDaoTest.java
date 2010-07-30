/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.test.dao;

import com.mycompany.dao.ICustomerDao;
import com.mycompany.entity.Customer;
import com.mycompany.entity.CustomerOrder;
import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author abdelkafi_s
 */
public class CustomerDaoTest extends TestCase {

    private static final String[] LOCATIONS = {"application-context.xml"};
    protected ApplicationContext context;
    private ICustomerDao iCustomerDao= null;


    @Override
    protected void setUp() throws Exception {
        super.setUp();
        context = new ClassPathXmlApplicationContext( LOCATIONS );
        iCustomerDao = (ICustomerDao) context.getBean("customerDao");
   
    }

    public CustomerDaoTest(String testName) {
        super(testName);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    // TODO add test methods here. The name must begin with 'test'. For example:
    public void testReadCustomer() {
      
        Customer customer = new Customer(1, "CustomerName", "CustomerName", "CustomerCity", "CustomerState", "CustomerZip", "010101010101", new ArrayList<CustomerOrder>());
   
        iCustomerDao.save(customer);

        List<Customer> customers = (List<Customer>) iCustomerDao.getAll();
        assertFalse(customers.isEmpty());
        assertEquals(customers.get(0).getName(), customer.getName());
    
    }

}
