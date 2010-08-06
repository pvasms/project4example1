package com.mycompany.test.dao;

import java.io.InputStream;
import java.sql.Connection;
import java.util.Collection;

import junit.framework.TestCase;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.SessionFactoryUtils;

import com.mycompany.dao.ICustomerDao;
import com.mycompany.entity.Customer;

public class CustomerDaoTest extends TestCase {

	private static final String[] LOCATIONS = { "application-context.xml" };
	private static final String FLAT_XML_DATASET = "FlatXmlDataSet.xml";
	private ApplicationContext context;
	private ICustomerDao iCustomerDao;


	@Override
	protected void setUp() throws Exception {
		super.setUp();
		context = new ClassPathXmlApplicationContext(LOCATIONS);
		iCustomerDao = (ICustomerDao) context.getBean("customerDao");
		DatabaseOperation.CLEAN_INSERT.execute(getConnection(), getDataSet());

	}

	public void testGetAllCustomers() {
		Collection<Customer> listCustomers = iCustomerDao.getAll();
		assertFalse(listCustomers.isEmpty());
	}
	
	public void testSaveCustomer() {
		Collection<Customer> listCustomers1 = iCustomerDao.getAll();
		Customer customer = new Customer(1, "name","adresse", "city", "state", "123", "0606060606", null);
		iCustomerDao.save(customer);
		Collection<Customer> listCustomers2 = iCustomerDao.getAll();
		assertEquals(listCustomers2.size() - listCustomers1.size(), 1);
	}
	

	@SuppressWarnings("deprecation")
	private IDataSet getDataSet() throws Exception {
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(FLAT_XML_DATASET);
		IDataSet dataset = new FlatXmlDataSet(inputStream);
		return dataset;
	}

	private IDatabaseConnection getConnection() throws Exception {
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
		Connection jdbcConnection = SessionFactoryUtils.getDataSource(sessionFactory).getConnection();
		IDatabaseConnection connection = new DatabaseConnection(jdbcConnection);
		return connection;
	}
}
