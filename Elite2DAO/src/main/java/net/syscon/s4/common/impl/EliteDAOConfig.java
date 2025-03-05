package net.syscon.s4.common.impl;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jndi.JndiTemplate;

import net.syscon.s4.common.impl.OracleProxyConnectionAspect;

@Configuration
@PropertySource("classpath:/appconfig.properties")
public class EliteDAOConfig {

	public static Logger logger = LogManager.getLogger(EliteDAOConfig.class.getName());

	@Autowired
	private Environment env;

	@Bean
	public DataSource dataSource() {
		try {
			String jndi = env.getProperty("app.datasource.jndi");
			if (null != jndi && !jndi.isEmpty()) {
				JndiTemplate jndiTemplate = new JndiTemplate();
				DataSource dataSource = (DataSource) jndiTemplate.lookup(jndi);
				return dataSource;
			} else {
				String dbUrl = env.getProperty("app.test.jdbc.url");
				if (null != dbUrl && !dbUrl.isEmpty()) {
					DriverManagerDataSource dataSource = new DriverManagerDataSource();
					dataSource.setDriverClassName(env.getRequiredProperty("app.test.jdbc.driverClassName"));
					dataSource.setUrl(dbUrl);
					dataSource.setUsername(env.getRequiredProperty("app.test.jdbc.username"));
					dataSource.setPassword(env.getRequiredProperty("app.test.jdbc.password"));
					return dataSource;
				} else {
					logger.error("DB details are not given in appconfig....");
				}
			}
		} catch (NamingException e) {
			logger.error("Exception in Datasource lookup", e);
		}
		return null;
	}

	@Bean
	public OracleProxyConnectionAspect oracleConnectionAspect() {
		return new OracleProxyConnectionAspect(true);
	}

}
