package net.syscon.s4.services.oauth;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.stereotype.Component;


@Component
public class PGJDBCTokenStore extends JdbcTokenStore{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private static Logger logger = LogManager.getLogger(PGJDBCTokenStore.class);
	private static final String DEFAULT_ACCESS_TOKEN_SELECT_STATEMENT = "select token_id, token from oauth_access_token where token_id = ?";
	private String selectAccessTokenSql = DEFAULT_ACCESS_TOKEN_SELECT_STATEMENT;
	
	public PGJDBCTokenStore(DataSource dataSource) {
		super(dataSource);
	}
	
	
	@Override
	public OAuth2AccessToken readAccessToken(String tokenValue) {
		OAuth2AccessToken accessToken = null;

		try {
			accessToken = jdbcTemplate.queryForObject(selectAccessTokenSql, new RowMapper<OAuth2AccessToken>() {
				public OAuth2AccessToken mapRow(ResultSet rs, int rowNum) throws SQLException {
					logger.info(rs);
					return deserializeAccessToken(rs.getBytes(2));
				}
			}, extractTokenKey(tokenValue));
		}
		catch (EmptyResultDataAccessException e) {
			if (logger.isInfoEnabled()) {
				logger.info("Failed to find access token for token " + tokenValue);
			}
		}
		catch (IllegalArgumentException e) {
			logger.warn("Failed to deserialize access token for " + tokenValue, e);
			removeAccessToken(tokenValue);
		}

		return accessToken;
	}

}
