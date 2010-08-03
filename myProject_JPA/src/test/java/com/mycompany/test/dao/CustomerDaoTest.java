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
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.orm.hibernate3.SessionHolder;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.mycompany.dao.ICustomerDao;
import com.mycompany.entity.Customer;

public class CustomerDaoTest extends TestCase {

	private static final String[] LOCATIONS = { "application-context.xml" };
	protected ApplicationContext context;
	protected SessionFactory sessionFactory;
	protected Session session;
	protected IDataSet dataset;
	protected InputStream inputStream;
	protected Connection jdbcConnection;

	@Override
	protected void setUp() throws Exception {

		super.setUp();
		context = new ClassPathXmlApplicationContext(LOCATIONS);
		inputStream = this.getClass().getClassLoader().getResourceAsStream("FlatXmlDataSet.xml");
		dataset = new FlatXmlDataSet(inputStream);
		sessionFactory = (SessionFactory) context.getBean("sessionFactory");
		session = SessionFactoryUtils.getSession(sessionFactory, true);
		jdbcConnection = SessionFactoryUtils.getDataSource(sessionFactory).getConnection();
		TransactionSynchronizationManager.bindResource(sessionFactory, new SessionHolder(session));
		ICustomerDao iCustomerDao = (ICustomerDao) context.getBean("customerDao");
		Collection<Customer> listCustomers = iCustomerDao.getAll();
		for(Customer c: listCustomers) {
			System.out.println(c.getCustomerId()+ "--" + c.getName());
		}
		DatabaseOperation.INSERT.execute(getConnection(), dataset);

	}

	public void testDossier() {
		ICustomerDao iCustomerDao = (ICustomerDao) context.getBean("customerDao");
		Collection<Customer> listDossier = iCustomerDao.getAll();
		assertEquals(listDossier.size(), 8);
	}

	protected IDatabaseConnection getConnection() throws Exception {
		IDatabaseConnection connection = new DatabaseConnection(jdbcConnection);
		return connection;
	}
	
	
}
