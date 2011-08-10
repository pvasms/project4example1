package com.mycompany.test.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.InputStream;
import java.sql.Connection;
import java.util.Collection;
import java.util.List;

import org.apache.commons.dbcp.BasicDataSource;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mycompany.dao.ICustomerDao;
import com.mycompany.entity.Customer;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/application-context.xml"})
public class CustomerDaoTest  {

	private static final String FLAT_XML_DATASET = "FlatXmlDataSet.xml";
	
	@Autowired
	BasicDataSource bds;
	
	@Autowired
	private ICustomerDao iCustomerDao;


	@Before
	public void setUp() throws Exception {
		DatabaseOperation.CLEAN_INSERT.execute(getConnection(), getDataSet());
	}

	@Test
	public void testGetAllCustomers() {
		Collection<Customer> listCustomers = iCustomerDao.getAll();
		assertFalse(listCustomers.isEmpty());
	}
	
	@Test
	public void testSaveCustomer() {
		Customer customer = new Customer(25, "nameTest","adresse", "city", "state", "123", "0606060606", null);
		iCustomerDao.save(customer);
		List<Customer> listCustomers =  (List<Customer>) iCustomerDao.findByName("nameTest"); 
		assertFalse(listCustomers.isEmpty());
		Customer customerRes = (Customer) listCustomers.get(0);
		assertEquals(customerRes.getCustomerId(), customer.getCustomerId());		
	}
	
	@Test
	public void testdeleteCustomer() {
		Customer customer = new Customer(25, "nameTest","adresse", "city", "state", "123", "0606060606", null);
		iCustomerDao.save(customer);
		List<Customer> listCustomers =  (List<Customer>) iCustomerDao.findByName("nameTest"); 
		assertFalse(listCustomers.isEmpty());
		
		Customer customerRes = (Customer) listCustomers.get(0);
		iCustomerDao.delete(customerRes.getCustomerId());
		listCustomers =  (List<Customer>) iCustomerDao.findByName("nameTest"); 
		assertTrue(listCustomers.isEmpty());
	}
	

	@SuppressWarnings("deprecation")
	private IDataSet getDataSet() throws Exception {
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(FLAT_XML_DATASET);
		IDataSet dataset = new FlatXmlDataSet(inputStream);
		return dataset;
	}

	private IDatabaseConnection getConnection() throws Exception {
		Connection jdbcConnection = bds.getConnection();
		IDatabaseConnection connection = new DatabaseConnection(jdbcConnection);
		return connection;
	}
}
