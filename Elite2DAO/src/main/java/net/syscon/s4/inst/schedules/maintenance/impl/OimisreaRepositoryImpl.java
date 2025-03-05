package net.syscon.s4.inst.schedules.maintenance.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.inst.casemanagement.beans.InternalScheduleReasons;
import net.syscon.s4.inst.schedules.maintenance.OimisreaRepository;

/**
 * Class OimisreaRepositoryImpl
 */
@Repository
public class OimisreaRepositoryImpl extends RepositoryBase implements OimisreaRepository {

	private static Logger logger = LogManager.getLogger(OimisreaRepositoryImpl.class);

	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CODE", 						new FieldMapper("code"))
			.put("DESCRIPTION", 				new FieldMapper("description"))
			.put("LIST_SEQ", 					new FieldMapper("listSeq"))
			.build();

	private final Map<String, FieldMapper> intSchMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("INTERNAL_SCHEDULE_TYPE", 		new FieldMapper("internalScheduleType"))
			.put("INTERNAL_SCHEDULE_RSN_CODE", 	new FieldMapper("internalScheduleRsnCode"))
			.put("DESCRIPTION", 				new FieldMapper("description"))
			.put("ACTIVE_FLAG", 				new FieldMapper("activeFlag"))
			.put("LIST_SEQ", 					new FieldMapper("listSeq"))
			.put("EXPIRY_DATE", 				new FieldMapper("expiryDate"))
			.put("CREATE_USER_ID", 				new FieldMapper("createUserId"))
			.put("CREATE_DATETIME", 			new FieldMapper("createDatetime"))
			.put("MODIFY_DATETIME", 			new FieldMapper("modifyDatetime"))
			.put("MODIFY_USER_ID", 				new FieldMapper("modifyUserId"))
			.put("SEAL_FLAG", 					new FieldMapper("sealFlag"))
			.build();

	/**
	 * Creates new OimisreaRepositoryImpl class Object
	 */
	public OimisreaRepositoryImpl() {
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            InternalScheduleReasons
	 *
	 * @return List<InternalScheduleReasons>
	 */
	public List<InternalScheduleReasons> intSrExecuteQuery(final InternalScheduleReasons objSearchDao) {
		final String sql = getQuery("OIMISREA_INTSR_FIND_INTERNAL_SCHEDULE_REASONS");
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		String preSqlQuery = null;
		final MapSqlParameterSource params = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" WHERE ");
			if (objSearchDao.getInternalScheduleType() != null && !"".equals(objSearchDao.getInternalScheduleType())) {
				sqlQuery.append(" INTERNAL_SCHEDULE_TYPE  = :internalScheduleType " + " AND ");
				params.addValue("internalScheduleType", objSearchDao.getInternalScheduleType());
			}
			if (objSearchDao.getInternalScheduleRsnCode() != null
					&& !"".equals(objSearchDao.getInternalScheduleRsnCode())) {
				sqlQuery.append(" INTERNAL_SCHEDULE_RSN_CODE  = :internalScheduleRsnCode " + " AND ");
				params.addValue("internalScheduleRsnCode", objSearchDao.getInternalScheduleRsnCode());
			}
			if (objSearchDao != null && objSearchDao.getDescription() != null
					&& !"".equals(objSearchDao.getDescription())) {
				sqlQuery.append(" DESCRIPTION  = :description " + " AND ");
				params.addValue("description", objSearchDao.getDescription().trim());
			}
			if (objSearchDao != null && objSearchDao.getListSeq() != null) {
				sqlQuery.append(" LIST_SEQ  = :listSeq " + " AND ");
				params.addValue("listSeq", objSearchDao.getListSeq());
			}
			if (objSearchDao.getActiveFlag() != null) {
				if ("true".equals(objSearchDao.getActiveFlag())) {
					objSearchDao.setActiveFlag("Y");
				} else {
					objSearchDao.setActiveFlag("N");
				}
				sqlQuery.append(" ACTIVE_FLAG  = :activeFlag " + " AND ");
				params.addValue("activeFlag", objSearchDao.getActiveFlag());
			}
			if (objSearchDao.getExpiryDate() != null) {
				sqlQuery.append(" EXPIRY_DATE  = :expiryDate " + " AND ");
				params.addValue("expiryDate", objSearchDao.getExpiryDate());
			}
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("WHERE")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("AND")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		preSqlQuery = preparedSql.concat(" ORDER BY LIST_SEQ ASC ");

		final RowMapper<InternalScheduleReasons> intRowMapper = Row2BeanRowMapper.makeMapping(preSqlQuery,
				InternalScheduleReasons.class, intSchMapping);
		List<InternalScheduleReasons> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(preSqlQuery, params, intRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstInternalScheduleReasons
	 *            List<InternalScheduleReasons>
	 *
	 * @return List<Integer>
	 */
	public Integer intSrInsertInternalScheduleReasons(final List<InternalScheduleReasons> lstIntSchReasons) {
		final String sql = getQuery("OIMISREA_INTSR_INSERT_INTERNAL_SCHEDULE_REASONS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final InternalScheduleReasons intSchReasons : lstIntSchReasons) {
			parameters.add(new BeanPropertySqlParameterSource(intSchReasons));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstIntSchReasons.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstInternalScheduleReasons
	 *            List<InternalScheduleReasons>
	 */
	public Integer intSrUpdateInternalScheduleReasons(final List<InternalScheduleReasons> lstIntSchReasons) {
		final String sql = getQuery("OIMISREA_INTSR_UPDATE_INTERNAL_SCHEDULE_REASONS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final InternalScheduleReasons intSchReasons : lstIntSchReasons) {
			parameters.add(new BeanPropertySqlParameterSource(intSchReasons));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstIntSchReasons.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstInternalScheduleReasons
	 *            List<InternalScheduleReasons>
	 */
	public Integer intSrDeleteInternalScheduleReasons(final List<InternalScheduleReasons> lstIntSchReasons) {
		final String sql = getQuery("OIMISREA_INTSR_DELETE_INTERNAL_SCHEDULE_REASONS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final InternalScheduleReasons intSchReasons : lstIntSchReasons) {
			parameters.add(new BeanPropertySqlParameterSource(intSchReasons));
		}
		try {
			String tableName = "INTERNAL_SCHEDULE_REASONS";
			String whereCondition = "INTERNAL_SCHEDULE_TYPE=:internalScheduleType AND INTERNAL_SCHEDULE_RSN_CODE=:internalScheduleRsnCode";
			batchUpdatePreDeletedRows(tableName, whereCondition, parameters);
		} catch (Exception e) {
			logger.error(e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstIntSchReasons.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgIntSchRsnRecordGroup() {
		final String sql = getQuery("OIMISREA_FIND_RGINTSCHRSN");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		List<ReferenceCodes> returnList = null;
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (Exception e) {
			logger.error("rgIntSchRsnRecordGroup", e);
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgIntSchTypeRecordGroup() {
		final String sql = getQuery("OIMISREA_FIND_RGINTSCHTYPE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		List<ReferenceCodes> returnList = null;
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (Exception e) {
			logger.error("rgIntSchTypeRecordGroup", e);
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * intSrKeyDelrec
	 *
	 * @param params
	 *
	 */
	public Long intSrKeyDelrec(final InternalScheduleReasons paramBean) {
		final String sql = getQuery("OIMISREA_INT_SR_KEYDELREC");
		Long resultCount = null;
		resultCount = namedParameterJdbcTemplate.queryForObject(sql, createParams("EVENTTYPE",
				paramBean.getInternalScheduleType(), "EVENTSUBTYPE", paramBean.getInternalScheduleRsnCode()),
				Long.class);
		return resultCount;
	}

}
