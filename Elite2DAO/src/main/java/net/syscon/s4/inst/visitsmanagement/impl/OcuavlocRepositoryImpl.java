package net.syscon.s4.inst.visitsmanagement.impl;

import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.inst.visitsmanagement.OcuavlocRepository;
import net.syscon.s4.inst.visitsmanagement.beans.VOcuavlocAvailable;
import net.syscon.s4.inst.visitsmanagement.beans.VOcuavlocUnavailable;
import oracle.jdbc.OracleTypes;

/**
 * Class OcuavlocRepositoryImpl
 */
@Repository
public class OcuavlocRepositoryImpl extends RepositoryBase implements OcuavlocRepository {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcuavlocRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> vOcuavlocUnavMap = new ImmutableMap.Builder<String, FieldMapper>()
			.put("AGENCY_VISIT_SLOT_ID", new FieldMapper("agencyVisitSlotId"))
			.put("CAPACITY", new FieldMapper("capacity")).put("ADULTS_BOOKED", new FieldMapper("adultsBooked"))
			.put("VISIT_DATE", new FieldMapper("visitDate")).put("AGY_LOC_ID", new FieldMapper("agyLocId"))
			.put("INTERNAL_LOCATION_ID", new FieldMapper("internalLocationId"))
			.put("GROUPS_BOOKED", new FieldMapper("groupsBooked")).put("WEEK_DAY", new FieldMapper("weekDay"))
			.put("MAX_ADULTS", new FieldMapper("maxAdults")).put("TOTAL_BOOKED", new FieldMapper("totalBooked"))
			.put("END_TIME", new FieldMapper("endTime")).put("DESCRIPTION", new FieldMapper("description"))
			.put("MAX_GROUPS", new FieldMapper("maxGroups")).put("START_TIME", new FieldMapper("startTime")).build();

	private final Map<String, FieldMapper> vOcuavlocAvaMap = new ImmutableMap.Builder<String, FieldMapper>()
			.put("AGENCY_VISIT_SLOT_ID", new FieldMapper("agencyVisitSlotId"))
			.put("CAPACITY", new FieldMapper("capacity")).put("ADULTS_BOOKED", new FieldMapper("adultsBooked"))
			.put("VISIT_DATE", new FieldMapper("visitDate")).put("AGY_LOC_ID", new FieldMapper("agyLocId"))
			.put("INTERNAL_LOCATION_ID", new FieldMapper("internalLocationId"))
			.put("GROUPS_BOOKED", new FieldMapper("groupsBooked")).put("WEEK_DAY", new FieldMapper("weekDay"))
			.put("MAX_ADULTS", new FieldMapper("maxAdults")).put("TOTAL_BOOKED", new FieldMapper("totalBooked"))
			.put("END_TIME", new FieldMapper("endTime")).put("DESCRIPTION", new FieldMapper("description"))
			.put("MAX_GROUPS", new FieldMapper("maxGroups")).put("START_TIME", new FieldMapper("startTime")).build();

	/**
	 * Creates new OcuavlocRepositoryImpl class Object
	 */
	public OcuavlocRepositoryImpl() {
		// OcuavlocRepositoryImpl
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao VOcuavlocAvailable
	 *
	 * @return List<VOcuavlocAvailable>
	 *
	 */
	public List<VOcuavlocAvailable> avlLocExecuteQuery(final VOcuavlocAvailable objSearchDao) {
		Integer count = createTable();
		Integer result = createTableAvailable();
		final String sql = getQuery("OCUAVLOC_AVLLOC_FIND_V_OCUAVLOC_AVAILABLE");
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource params = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" WHERE ");
			sqlQuery.append("(VISIT_DATE = :VISIT_DATE OR :VISIT_DATE IS NULL)" + " AND ");
			sqlQuery.append("AGY_LOC_ID = :AGY_LOC_ID" + " AND " + "WEEK_DAY = TO_CHAR (:VISIT_DATE , 'DY' ) " + " AND "
					+ "START_TIME = :START_TIME" + " AND ");
			sqlQuery.append(
					"internal_location_id NOT IN (SELECT internal_location_id FROM V_OCUAVLOC_UNAVAILABLE WHERE visit_date = :VISIT_DATE AND ");
			sqlQuery.append("AGY_LOC_ID = :AGY_LOC_ID" + " AND " + "WEEK_DAY = TO_CHAR (:VISIT_DATE, 'DY' )" + " AND "
					+ "START_TIME = :START_TIME)" + " AND ");
			params.addValue("VISIT_DATE", new java.sql.Date(objSearchDao.getVisitDate().getTime()));
			params.addValue("START_TIME", objSearchDao.getStartTime());
			params.addValue("AGY_LOC_ID", objSearchDao.getAgyLocId());
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("WHERE")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("AND")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		final RowMapper<VOcuavlocAvailable> vOcuavlocAvailableRowMapper = Row2BeanRowMapper.makeMapping(sql,
				VOcuavlocAvailable.class, vOcuavlocAvaMap);
		final ArrayList<VOcuavlocAvailable> returnList = (ArrayList<VOcuavlocAvailable>) namedParameterJdbcTemplate
				.query(preparedSql, params, vOcuavlocAvailableRowMapper);
		return returnList;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao VOcuavlocUnavailable
	 *
	 * @return List<VOcuavlocUnavailable>
	 *
	 */
	public List<VOcuavlocUnavailable> fboLocExecuteQuery(final VOcuavlocUnavailable objSearchDao) {
		Integer count = createTable();
		Integer result = createTableUnAvailable();
		final String sql = getQuery("OCUAVLOC_FBOLOC_FIND_V_OCUAVLOC_UNAVAILABLE");
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource params = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" WHERE ");
			sqlQuery.append("VISIT_DATE = :VISIT_DATE" + " AND ");
			sqlQuery.append("AGY_LOC_ID = :AGY_LOC_ID" + " AND " + "WEEK_DAY = TO_CHAR (:VISIT_DATE , 'DY' ) " + " AND "
					+ "START_TIME = :START_TIME" + " AND ");
			params.addValue("VISIT_DATE", new java.sql.Date(objSearchDao.getVisitDate().getTime()));
			params.addValue("START_TIME", objSearchDao.getStartTime());
			params.addValue("AGY_LOC_ID", objSearchDao.getAgyLocId());
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("WHERE")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("AND")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		final RowMapper<VOcuavlocUnavailable> vOcuavlocUnavailableRowMapper = Row2BeanRowMapper.makeMapping(sql,
				VOcuavlocUnavailable.class, vOcuavlocUnavMap);
		final ArrayList<VOcuavlocUnavailable> returnList = (ArrayList<VOcuavlocUnavailable>) namedParameterJdbcTemplate
				.query(preparedSql, params, vOcuavlocUnavailableRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * avlLocPreQuery
	 *
	 * @param params
	 *
	 */
	public List<VOcuavlocUnavailable> avlLocPreQuery(final VOcuavlocUnavailable paramBean) {
		final String sql = getQuery("OCUAVLOC_AVL_LOC_PREQUERY_PREQUERY");
		final RowMapper<VOcuavlocUnavailable> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				VOcuavlocUnavailable.class, vOcuavlocUnavMap);
		final ArrayList<VOcuavlocUnavailable> returnList = (ArrayList<VOcuavlocUnavailable>) namedParameterJdbcTemplate
				.query(sql, createParams(paramBean), columnRowMapper);
		return returnList;
	}

	/**
	 * used to execute GET_OCUAVLOC_AVAILABLE procedure.
	 * 
	 * @Param objSearchDao
	 * @return List<VOcuavlocAvailable>
	 */
	public List<VOcuavlocAvailable> getOcuavlocAvailable(final VOcuavlocAvailable objSearchDao) {
		List<VOcuavlocAvailable> returnList = null;
		Map<String, Object> returnObject = null;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[7];
		sqlParameters = new SqlParameter[] { new SqlOutParameter("P_RECS", OracleTypes.CURSOR),
				new SqlParameter("P_DATE", OracleTypes.DATE), new SqlParameter("P_AGY_LOC_ID", Types.VARCHAR),
				new SqlParameter("P_WEEK_DAY", Types.VARCHAR), new SqlParameter("P_START_TIME", Types.VARCHAR) };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_VISITS").withProcedureName("GET_OCUAVLOC_AVAILABLE")
				.declareParameters(sqlParameters);
		inParamMap.put("P_RECS", OracleTypes.CURSOR);
		inParamMap.put("P_DATE", objSearchDao.getVisitDate());
		inParamMap.put("P_AGY_LOC_ID", objSearchDao.getAgyLocId());
		inParamMap.put("P_WEEK_DAY", objSearchDao.getWeekDay());
		inParamMap.put("P_START_TIME", objSearchDao.getStartTime());
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		VOcuavlocAvailable bean = new VOcuavlocAvailable();
		returnObject = simpleJDBCCall.execute(inParameter);
		returnList = new ArrayList<>();
		final List<Map<String, String>> list = (List<Map<String, String>>) returnObject.get("P_RECS");
		for (int i = 0; i < list.size(); i++) {
			final Map<String, String> childMap = list.get(i);
			bean = new VOcuavlocAvailable();
			bean.setAgyLocId(String.valueOf(childMap.get("AGY_LOC_ID")));
			bean.setWeekDay(String.valueOf(childMap.get("WEEK_DAY")));
			bean.setInternalLocationId(Integer.parseInt(String.valueOf(childMap.get("INTERNAL_LOCATION_ID"))));
			bean.setAgencyVisitSlotId(Integer.parseInt(String.valueOf(childMap.get("AGENCY_VISIT_SLOT_ID"))));
			bean.setMaxGroups(
					childMap.get("MAX_GROUPS") != null ? Integer.parseInt(String.valueOf(childMap.get("MAX_GROUPS")))
							: 0);
			bean.setMaxAdults(
					childMap.get("MAX_ADULTS") != null ? Integer.parseInt(String.valueOf(childMap.get("MAX_ADULTS")))
							: 0);
			bean.setCapacity(
					childMap.get("CAPACITY") != null ? Integer.parseInt(String.valueOf(childMap.get("CAPACITY"))) : 0);
			bean.setGroupsBooked(childMap.get("GROUPS_BOOKED") != null
					? Integer.parseInt(String.valueOf(childMap.get("GROUPS_BOOKED")))
					: 0);
			bean.setTotalBooked(childMap.get("TOTAL_BOOKED") != null
					? Integer.parseInt(String.valueOf(childMap.get("TOTAL_BOOKED")))
					: 0);
			bean.setAdultsBooked(childMap.get("ADULTS_BOOKED") != null
					? Integer.parseInt(String.valueOf(childMap.get("ADULTS_BOOKED")))
					: 0);
			bean.setDescription((String.valueOf(childMap.get("DESCRIPTION"))));
			returnList.add(bean);
		}
		return returnList;
	}

	/**
	 * used to execute GET_OCUAVLOC_UNAVAILABLE procedure.
	 * 
	 * @Param objSearchDao
	 * @return List<VOcuavlocAvailable>
	 */
	public List<VOcuavlocUnavailable> getOcuavlocUnAvailable(final VOcuavlocUnavailable objSearchDao) {
		List<VOcuavlocUnavailable> returnList = null;
		Map<String, Object> returnObject = null;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[7];
		sqlParameters = new SqlParameter[] { new SqlOutParameter("P_RECS", OracleTypes.CURSOR),
				new SqlParameter("P_DATE", OracleTypes.DATE), new SqlParameter("P_AGY_LOC_ID", Types.VARCHAR),
				new SqlParameter("P_WEEK_DAY", Types.VARCHAR), new SqlParameter("P_START_TIME", Types.VARCHAR) };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_VISITS").withProcedureName("GET_OCUAVLOC_UNAVAILABLE")
				.declareParameters(sqlParameters);
		inParamMap.put("P_RECS", OracleTypes.CURSOR);
		inParamMap.put("P_DATE", objSearchDao.getVisitDate());
		inParamMap.put("P_AGY_LOC_ID", objSearchDao.getAgyLocId());
		inParamMap.put("P_WEEK_DAY", objSearchDao.getWeekDay());
		inParamMap.put("P_START_TIME", objSearchDao.getStartTime());
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		VOcuavlocUnavailable bean = new VOcuavlocUnavailable();
		returnObject = simpleJDBCCall.execute(inParameter);
		returnList = new ArrayList<>();
		final List<Map<String, String>> list = (List<Map<String, String>>) returnObject.get("P_RECS");
		for (int i = 0; i < list.size(); i++) {
			final Map<String, String> childMap = list.get(i);
			bean = new VOcuavlocUnavailable();
			bean.setAgyLocId(String.valueOf(childMap.get("AGY_LOC_ID")));
			bean.setWeekDay(String.valueOf(childMap.get("WEEK_DAY")));
			bean.setInternalLocationId(Integer.parseInt(String.valueOf(childMap.get("INTERNAL_LOCATION_ID"))));
			bean.setAgencyVisitSlotId(Integer.parseInt(String.valueOf(childMap.get("AGENCY_VISIT_SLOT_ID"))));
			bean.setMaxGroups(
					childMap.get("MAX_GROUPS") != null ? Integer.parseInt(String.valueOf(childMap.get("MAX_GROUPS")))
							: 0);
			bean.setMaxAdults(
					childMap.get("MAX_ADULTS") != null ? Integer.parseInt(String.valueOf(childMap.get("MAX_ADULTS")))
							: 0);
			bean.setCapacity(
					childMap.get("CAPACITY") != null ? Integer.parseInt(String.valueOf(childMap.get("CAPACITY"))) : 0);
			bean.setGroupsBooked(childMap.get("GROUPS_BOOKED") != null
					? Integer.parseInt(String.valueOf(childMap.get("GROUPS_BOOKED")))
					: 0);
			bean.setTotalBooked(childMap.get("TOTAL_BOOKED") != null
					? Integer.parseInt(String.valueOf(childMap.get("TOTAL_BOOKED")))
					: 0);
			bean.setAdultsBooked(childMap.get("ADULTS_BOOKED") != null
					? Integer.parseInt(String.valueOf(childMap.get("ADULTS_BOOKED")))
					: 0);
			bean.setDescription((String.valueOf(childMap.get("DESCRIPTION"))));
			returnList.add(bean);
		}

		return returnList;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao VOcuavlocAvailable
	 *
	 * @return VOcuavlocAvailable
	 *
	 */
	public VOcuavlocAvailable reCheckTimeSlot(final VOcuavlocAvailable objSearchDao) {
		final String sql = getQuery("OCUAVLOC_AVL_LOC_RECHECK_TIME_SLOT");
		VOcuavlocAvailable returnList = new VOcuavlocAvailable();
		final RowMapper<VOcuavlocAvailable> voaRowMapper = Row2BeanRowMapper.makeMapping(sql, VOcuavlocAvailable.class,
				vOcuavlocAvaMap);
		final List<VOcuavlocAvailable> list = namedParameterJdbcTemplate.query(sql,
				createParams("AGENCY_VISIT_SLOT_ID", objSearchDao.getAgencyVisitSlotId()), voaRowMapper);
		if (list.size() > 0) {
			returnList = list.get(0);
		} else {
			returnList = new VOcuavlocAvailable();
		}
		return returnList;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao VOcuavlocAvailable
	 *
	 * @return VOcuavlocAvailable
	 *
	 */
	public VOcuavlocAvailable reCheckTimeSlotCursorcVis(final VOcuavlocAvailable objSearchDao) {
		final String sql = getQuery("OCUAVLOC_AVL_LOC_RECHECK_TIME_SLOT_CURSOR_C_VIS");
		VOcuavlocAvailable returnList = new VOcuavlocAvailable();
		final RowMapper<VOcuavlocAvailable> voaRowMapper = Row2BeanRowMapper.makeMapping(sql, VOcuavlocAvailable.class,
				vOcuavlocAvaMap);
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("OFFENDER_VISIT_ID",
					objSearchDao.getOffenderVisitId(), "VISIT_DATE", objSearchDao.getVisitDate()), voaRowMapper);
		} catch (EmptyResultDataAccessException e) {
			returnList = new VOcuavlocAvailable();
			logger.error("reCheckTimeSlotCursorcVis:", e);
		}
		return returnList;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao VOcuavlocAvailable
	 *
	 * @return VOcuavlocAvailable
	 *
	 */
	public VOcuavlocAvailable reCheckTimeSlotCursorcAll(final VOcuavlocAvailable objSearchDao) {
		final String sql = getQuery("OCUAVLOC_AVL_LOC_RECHECK_TIME_SLOT_CURSOR_C_ALL");
		VOcuavlocAvailable returnList = new VOcuavlocAvailable();
		final RowMapper<VOcuavlocAvailable> voaRowMapper = Row2BeanRowMapper.makeMapping(sql, VOcuavlocAvailable.class,
				vOcuavlocAvaMap);
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("P_AGENCY_VISIT_SLOT_ID", objSearchDao.getAgencyVisitSlotId(), "P_OFFENDER_VISIT_ID",
							objSearchDao.getOffenderVisitId(), "VISIT_DATE", objSearchDao.getVisitDate()),
					voaRowMapper);
		} catch (EmptyResultDataAccessException e) {
			returnList = new VOcuavlocAvailable();
			logger.error("reCheckTimeSlotCursorcAll:", e);
		}
		return returnList;
	}

	public int createTable() {
		String createTableSQL = " CREATE GLOBAL TEMPORARY TABLE OCUAVLOC_TMP ( AGY_LOC_ID VARCHAR(6), WEEK_DAY VARCHAR(3), INTERNAL_LOCATION_ID BIGINT, AGENCY_VISIT_SLOT_ID BIGINT, MAX_GROUPS BIGINT, MAX_ADULTS BIGINT, CAPACITY BIGINT, GROUPS_BOOKED BIGINT, TOTAL_BOOKED BIGINT, ADULTS_BOOKED BIGINT, DESCRIPTION VARCHAR(100), START_TIME VARCHAR(5), END_TIME VARCHAR(5), VISIT_DATE TIMESTAMP(0) ) ON COMMIT PRESERVE ROWS";
		return namedParameterJdbcTemplate.update(createTableSQL, createParams());
	}

	public int createTableAvailable() {
		String sql = getQuery("OCUAVLOC_TEMPORARY_TABLE_CREATE_TABLE_AVAILABLE");
		return namedParameterJdbcTemplate.update(sql, createParams());
	}

	public int createTableUnAvailable() {
		String sql = getQuery("OCUAVLOC_TEMPORARY_TABLE_CREATE_TABLE_UN_AVAILABLE");
		return namedParameterJdbcTemplate.update(sql, createParams());
	}
}
