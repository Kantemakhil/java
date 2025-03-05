package net.syscon.s4.common.impl;

import java.sql.Connection;
import java.util.Base64;

import javax.sql.DataSource;

import net.syscon.s4.global.Omss40Repository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.UserAuthenticationDao;
import oracle.jdbc.driver.OracleConnection;

@Repository
public class UserAuthenticationDaoImpl implements UserAuthenticationDao {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(UserAuthenticationDaoImpl.class.getName());

	@Autowired
	protected DataSource dataSource;

	@Autowired
	private Omss40Repository omss40Repository;

	@Override
	public boolean authenticate(final String username, final String password) {
		Connection conn = null;
		OracleConnection oracleConn = null;
		if(password == null || password.contains(" ") || password.equals("")) {
			return false;
		}
		try {
			String encodePass = omss40Repository.getEncryPassword(username);

			Base64.Decoder decoder = Base64.getDecoder();  // decoding password
			String decodedPass = new String(decoder.decode(encodePass));

			if (password.equals(decodedPass)) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			logger.error(e);
			return false;
		} finally {
			try {
				if (oracleConn != null && oracleConn.isProxySession()) {
					oracleConn.close(OracleConnection.PROXY_SESSION);
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception ex) {
				logger.error(ex);
			}
		}
	}

}
