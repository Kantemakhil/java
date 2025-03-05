package net.syscon.s4.common.impl;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public final class GetConnection {
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(GetConnection.class.getName());

	private GetConnection() {

	}

	public static BasicDataSource getDataSource() throws ClassNotFoundException {
		logger.info("GetConnection : getDataSource()");
		  ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		return (BasicDataSource) ac.getBean("dataSource");
	}
}
