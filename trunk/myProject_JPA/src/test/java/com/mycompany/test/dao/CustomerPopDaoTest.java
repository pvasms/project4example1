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

public class CustomerPopDaoTest extends TestCase {

	private static final String[] LOCATIONS = { "application-context.xml" };
	protected ApplicationContext context;
	protected SessionFactory sessions;
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
		sessions = (SessionFactory) context.getBean("sessionFactory");
		session = SessionFactoryUtils.getSession(sessions, true);
		jdbcConnection = SessionFactoryUtils.getDataSource(sessions).getConnection();
		TransactionSynchronizationManager.bindResource(sessions, new SessionHolder(session));
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
