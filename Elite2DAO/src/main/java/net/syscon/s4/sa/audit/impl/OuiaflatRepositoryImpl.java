package net.syscon.s4.sa.audit.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.SanctionNotices;
import net.syscon.s4.sa.audit.OuiaflatRepository;
import net.syscon.s4.sa.audit.TagLoginAlerts;

/**
 * Class OuiaflatRepositoryImpl
 * 
 */
@Repository
public class OuiaflatRepositoryImpl extends RepositoryBase implements OuiaflatRepository {

	private final Map<String, FieldMapper> omsModulesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("MODULE_NAME", new FieldMapper("moduleName"))
			.put("MODULE_TYPE", new FieldMapper("moduleType"))
			.put("DESCRIPTION", new FieldMapper("description"))
			.build();

	private final Map<String, FieldMapper> tagLoginAlertsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("LOG_DATE", new FieldMapper("logDate"))
			.put("LOG_TIME", new FieldMapper("logTime"))
			.put("IP_ADDRESS", new FieldMapper("ipAddress"))
			.put("HOST", new FieldMapper("host"))
			.put("OS_USER", new FieldMapper("osUser"))
			.put("DB_USER", new FieldMapper("dbUser"))
			.put("TOOL", new FieldMapper("tool")).build();

	/**
	 * Creates new OuiaflatRepositoryImpl class Object
	 */
	public OuiaflatRepositoryImpl() {
		// OuiaflatRepositoryImpl
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            TagLoginAlerts
	 *
	 * @return List<TagLoginAlerts>
	 *
	 * @throws SQLException
	 */
	public List<TagLoginAlerts> loginAlertsBlkExecuteQuery(final TagLoginAlerts objSearchDao) {
		final String sql = getQuery("OUIAFLAT_LOGINALERTSBLK_FIND_TAG$LOGIN_ALERTS");
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		List<TagLoginAlerts> returnList;
		final MapSqlParameterSource params = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" WHERE");
			if (objSearchDao.getLogDate() != null) {
				sqlQuery.append(" LOG_DATE = to_date('" + new java.sql.Date(objSearchDao.getLogDate().getTime())
						+ "','yyyy/MM/dd')" + " and ");
			}
			if (objSearchDao.getIpAddress() != null
					&& !objSearchDao.getIpAddress().trim().equals("")) {
				sqlQuery.append(" IP_ADDRESS = :ipAddress and");
				params.addValue("ipAddress", objSearchDao.getIpAddress());
			}
			if (objSearchDao.getHost()!=null && !objSearchDao.getHost().trim().equals("") ) {
				sqlQuery.append(" HOST = :host and");
				params.addValue("host", objSearchDao.getHost());
			}
			if (objSearchDao.getOsUser()!=null && !objSearchDao.getOsUser().trim().equals("")) {
				sqlQuery.append(" OS_USER = :osUser and");
				params.addValue("osUser", objSearchDao.getOsUser());
			}
			if (objSearchDao.getDbUser()!=null && !objSearchDao.getDbUser().trim().equals("")) {
				sqlQuery.append(" DB_USER = :dbUser and");
				params.addValue("dbUser", objSearchDao.getDbUser());
			}
			if (objSearchDao.getTool()!=null && !objSearchDao.getTool().trim().equals("")) {
				sqlQuery.append(" TOOL = :tool and");
				params.addValue("tool", objSearchDao.getTool());
			}

		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("WHERE")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("and")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		preparedSql = preparedSql.concat("ORDER BY LOG_DATE DESC, LOG_TIME DESC");
		final RowMapper<TagLoginAlerts> TagLoginAlertsRowMapper = Row2BeanRowMapper.makeMapping(preparedSql,
				TagLoginAlerts.class, tagLoginAlertsMapping);
		 returnList = (ArrayList<TagLoginAlerts>) namedParameterJdbcTemplate.query(preparedSql,
				 params, TagLoginAlertsRowMapper);
		return returnList;
	}

	

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * createFormGlobalsCREATE_FORM_GLOBALS
	 *
	 * @param params
	 *
	 *//*
	public List<OmsModules> createFormGlobalsCREATE_FORM_GLOBALS(OmsModules paramBean) {
		final String sql = getQuery("OUIAFLAT_CREATE_FORM_GLOBALS_CREATE_FORM_GLOBALS");
		final RowMapper<OmsModules> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OmsModules.class,
				omsModulesMapping);
		final ArrayList<OmsModules> returnList = (ArrayList<OmsModules>) namedParameterJdbcTemplate.query(sql,
				createParams(), columnRowMapper);
		return returnList;
	}*/

}
