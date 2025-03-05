package net.syscon.s4.common.impl;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import net.syscon.s4.common.ApplicationConstants;

public class RepositoryBase{
	
	@Autowired
	protected NamedParameterJdbcTemplate  namedParameterJdbcTemplate ;

	@Autowired
	protected SQLProvider sqlProvider;

	@Autowired
	protected QueryBuilderFactory queryBuilderFactory;
	
	@Autowired
	protected DataSource dataSource;
	
	@Autowired
	protected JdbcTemplate jdbcTemplate;
	
	private static Logger logger = LogManager.getLogger(RepositoryBase.class.getName());
	
	@PostConstruct
	public void initSql() {
		final String path= getClass().getPackage().toString();
		sqlProvider.loadSql(getClass().getSimpleName().replace('.', '/'),path);
	}
	

	public MapSqlParameterSource createParams(final Object ... keysValues) {
		if (keysValues.length %2 != 0) throw new IllegalArgumentException("The keysValues must always be in pairs");
		final MapSqlParameterSource params = new MapSqlParameterSource();
		for (int i = 0; i < keysValues.length / 2; i++) {
			final int j = i * 2;
			params.addValue(keysValues[j].toString(), keysValues[j + 1]);
		}
		return params;
	}

	
	public String getQuery(final String name) {
		return sqlProvider.get(name);
	}

	public int updatePreDeletedRow(String tableName, String whereClause, Map<String,Object> inputMap) {
		String sql=null;
		if(whereClause!=null) {		
			 sql= "update "+tableName+" set MODIFY_DATETIME = current_timestamp , modify_user_id = :modifyUserId where "+whereClause ;
		} else {
			sql= "update "+tableName+" set MODIFY_DATETIME = current_timestamp , modify_user_id = :modifyUserId";
		}
		try {
			return namedParameterJdbcTemplate.update(sql, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " updatePreDeletedRow in error :: "+sql);
			return 0;
		}
	}
	
	public int[] batchUpdatePreDeletedRows(String tableName, String whereClause, List<SqlParameterSource> inputMap) {	
		String sql=null;
		if(whereClause!=null) {		
			 sql = "update "+tableName+" set MODIFY_DATETIME = current_timestamp , modify_user_id = :modifyUserId where "+whereClause ;
		} else {
			sql = "update "+tableName+" set MODIFY_DATETIME = current_timestamp , modify_user_id = :modifyUserId" ;
		}
		try {
			return namedParameterJdbcTemplate.batchUpdate(sql, inputMap.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " batchUpdatePreDeletedRows in error :: "+sql);
			return null;
		}
	}

}
