package com.mycompany.test.dao;

import java.io.InputStream;
import java.sql.Connection;
import java.util.Collection;

import junit.framework.Assert;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mycompany.dao.ICustomerDao;
import com.mycompany.entity.Customer;

@ContextConfiguration(locations = { "/application-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class CustomerDaoTest {

	private static final String FLAT_XML_DATASET = "FlatXmlDataSet.xml";
	
	@Autowired
	@Qualifier("customerDao")
	private ICustomerDao iCustomerDao;
	
	@Autowired
    @Qualifier("sessionFactory")
	SessionFactory sessionFactory;


	@Before
	public void setUp() throws Exception {
		DatabaseOperation.CLEAN_INSERT.execute(getConnection(), getDataSet());

	}

	@Test
	public void testGetAllCustomers() {
		Collection<Customer> listCustomers = iCustomerDao.getAll();
		Assert.assertFalse(listCustomers.isEmpty());
	}
	
	public void testSaveCustomer() {
		Collection<Customer> listCustomers1 = iCustomerDao.getAll();
		Customer customer = new Customer(1, "name","adresse", "city", "state", "123", "0606060606", null);
		iCustomerDao.save(customer);
		Collection<Customer> listCustomers2 = iCustomerDao.getAll();
		Assert.assertEquals(listCustomers2.size() - listCustomers1.size(), 1);
	}

	@SuppressWarnings("deprecation")
	private IDataSet getDataSet() throws Exception {
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(FLAT_XML_DATASET);
		IDataSet dataset = new FlatXmlDataSet(inputStream);
		return dataset;
	}

	private IDatabaseConnection getConnection() throws Exception {
		Connection jdbcConnection = SessionFactoryUtils.getDataSource(sessionFactory).getConnection();
		IDatabaseConnection connection = new DatabaseConnection(jdbcConnection);
		return connection;
	}
}
