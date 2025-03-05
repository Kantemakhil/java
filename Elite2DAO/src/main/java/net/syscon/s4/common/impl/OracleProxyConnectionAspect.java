package net.syscon.s4.common.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Arrays;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import net.syscon.s4.common.utils.UserSecurityUtils;
import oracle.jdbc.driver.OracleConnection;

@Aspect
@DependsOn("org.springframework.context.config.internalBeanConfigurerAspect")
public class OracleProxyConnectionAspect {
	private static Logger logger = LogManager.getLogger(OracleProxyConnectionAspect.class.getName());

	private AspectJExpressionPointcutAdvisor closeConnectionAdvisor;
	private boolean enableProxy;
	private String rolePassword;
	@Autowired
	protected NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public OracleProxyConnectionAspect(final boolean enableProxy) {
		this.enableProxy = enableProxy;
	}

	@PostConstruct
	public void postConstruct() {
		if (enableProxy) {
			try {

//				final MapSqlParameterSource params = new MapSqlParameterSource();
//				final String encryptedPassword = namedParameterJdbcTemplate.queryForObject(
//						"SELECT PROFILE_VALUE ROLE_PWD FROM SYSTEM_PROFILES WHERE PROFILE_TYPE = 'SYS' AND PROFILE_CODE = 'ROLE_PSWD'",
//						params, String.class);
//				if(encryptedPassword != null){
//					params.addValue("password", encryptedPassword);
//					rolePassword = namedParameterJdbcTemplate.queryForObject(
//						"SELECT decryption('2DECRYPTPASSWRD', :password) FROM DUAL", params, String.class);
//				}
				rolePassword = "omsowner";
			} catch (final DataAccessException ex) {
				logger.error(ex.getMessage(), ex);
			}

			closeConnectionAdvisor = new AspectJExpressionPointcutAdvisor();
			closeConnectionAdvisor.setExpression("execution (* java.sql.Connection.close(..))");
			closeConnectionAdvisor.setOrder(10);
			closeConnectionAdvisor.setAdvice((MethodBeforeAdvice) (method, args, target) -> {
				if (method.getName().equals("close")) {
					final Connection conn = (Connection) target;
					final OracleConnection oracleConn = (OracleConnection) conn.unwrap(Connection.class);
					oracleConn.close(OracleConnection.PROXY_SESSION);
				}
			});
		}
	}

	//@Pointcut("execution (* org.apache.tomcat.jdbc.pool.DataSourceProxy.getConnection())")
	private void onNewConnectionPointcut() {
		// onNewConnectionPointcut
	}

	//@Around("onNewConnectionPointcut()")
	public Object connectionAround(final ProceedingJoinPoint joinPoint) throws Throwable {
		if (logger.isDebugEnabled()) {
			logger.debug("Enter: {}.{}()", joinPoint.getSignature().getDeclaringTypeName(),
					joinPoint.getSignature().getName());
		}
		try {
			final Connection conn = (Connection) joinPoint.proceed();
			if (enableProxy) {
				final OracleConnection oracleConn = (OracleConnection) conn.unwrap(Connection.class);
				final String username = UserSecurityUtils.getInstance().getCurrentUserName();
				if (username != null && !username.isEmpty() && closeConnectionAdvisor != null) {

					final Properties info = new Properties();
					info.put(OracleConnection.PROXY_USER_NAME, username);
					oracleConn.openProxySession(OracleConnection.PROXYTYPE_USER_NAME, info);

					final ProxyFactory proxyFactory = new ProxyFactory(conn);
					proxyFactory.addAdvisor(closeConnectionAdvisor);
					final Connection proxyConn = (Connection) proxyFactory.getProxy();

					final String startSessionSQL = "SET ROLE TAG_USER IDENTIFIED BY " + rolePassword;
					final PreparedStatement stmt = oracleConn.prepareStatement(startSessionSQL);
					stmt.execute();
					stmt.close();

					if (logger.isDebugEnabled()) {
						logger.debug("Exit: {}.{}() with result = {}", joinPoint.getSignature().getDeclaringTypeName(),
								joinPoint.getSignature().getName(), conn);
					}
					return proxyConn;
				} else {
					return conn;
				}
			} else {
				return conn;
			}
		} catch (final Throwable e) {
			logger.error("Illegal argument: {} in {}.{}()", Arrays.toString(joinPoint.getArgs()),
					joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(), e);
			throw e;
		}
	}

}
