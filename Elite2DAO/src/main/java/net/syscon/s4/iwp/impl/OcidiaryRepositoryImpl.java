package net.syscon.s4.iwp.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules;
import net.syscon.s4.iwp.OcidiaryRepository;
import oracle.jdbc.OracleTypes;

/**
 * Class OcidiaryRepositoryImpl
 * 
 */
@Repository
public class OcidiaryRepositoryImpl extends RepositoryBase implements OcidiaryRepository {

	/**
	 * Creates new OcidiaryRepositoryImpl class Object
	 */
	public OcidiaryRepositoryImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = new JdbcTemplate();
	}

	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description")).put("CODE", new FieldMapper("code"))

			.build();


	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao VOffenderAllSchedules
	 *
	 * @return List<VOffenderAllSchedules>
	 *
	 * @throws SQLException
	 */
	public List<VOffenderAllSchedules> offSchExecuteQuery(VOffenderAllSchedules objSearchDao) {
		final String sql = getQuery("OCIDIARY_OFFSCH_FIND_V_OFFENDER_ALL_SCHEDULES");
		String preparedSql = null;
		String subWhere = null;
		final StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource params = new MapSqlParameterSource();
		sqlQuery.append(sql);
		sqlQuery.append(" WHERE");
		if (objSearchDao.getFromDate() != null && objSearchDao.getToDate() == null) {
			sqlQuery.append(" v_offender_all_schedules.event_date >= :NBT_FROM_DATE "
					+ "AND (   v_offender_all_schedules.event_class = 'COMM' OR (v_offender_all_schedules.reference_id IS NOT NULL "
					+ "AND v_offender_all_schedules.event_type = 'CRT')) "
					+ "AND v_offender_all_schedules.event_status IN ('SCH','COMP','EXP') "
					+ "AND offender_book_id IN (SELECT off_bkg.offender_book_id "
					+ "FROM offender_bookings off_bkg WHERE (off_bkg.active_flag = 'Y' "
					+ "OR off_bkg.community_active_flag = 'Y')) ");
			params.addValue("NBT_FROM_DATE", objSearchDao.getFromDate());
		}
		if (objSearchDao.getFromDate() != null && objSearchDao.getToDate() != null) {
			sqlQuery.append(" v_offender_all_schedules.event_date between :NBT_FROM_DATE  and  :NBT_TODATE "
					+ " AND  (v_offender_all_schedules.event_class = 'COMM' OR (v_offender_all_schedules.reference_id IS NOT NULL "
					+ "AND v_offender_all_schedules.event_type = 'CRT')) "
					+ "AND v_offender_all_schedules.event_status IN ('SCH','COMP','EXP') "
					+ "AND offender_book_id IN (SELECT off_bkg.offender_book_id " + "FROM offender_bookings off_bkg "
					+ "WHERE (off_bkg.active_flag = 'Y' " + "OR off_bkg.community_active_flag = 'Y')) ");
			params.addValue("NBT_FROM_DATE", objSearchDao.getFromDate());
			params.addValue("NBT_TODATE", objSearchDao.getToDate());
		}
		if (objSearchDao.getAgyLocId() != null) {
			if (subWhere != null) {
				sqlQuery.append(" AND v_offender_all_schedules.to_agy_loc_id = :AGY_LOC_ID ");
			} else 
			{
				sqlQuery.append(" AND v_offender_all_schedules.to_agy_loc_id = :AGY_LOC_ID ");
			}
			params.addValue("AGY_LOC_ID", objSearchDao.getAgyLocId());
		}
		if (objSearchDao.getInChargeStaffId() != null) {
			if (subWhere != null) {
				sqlQuery.append(" AND  v_offender_all_schedules.in_charge_staff_id = :NBT_STAFF_ID ");
			} else {
				sqlQuery.append(" AND v_offender_all_schedules.in_charge_staff_id = :NBT_STAFF_ID ");
			}
			params.addValue("NBT_STAFF_ID", objSearchDao.getInChargeStaffId());
		}
		if (objSearchDao.getStartTime() != null) {
//			sqlQuery.append(" AND  TO_CHAR(	v_offender_all_schedules.start_time,'HH24:MI') = :v_nbt_time ");
			if (subWhere != null) {
				sqlQuery.append(
						" AND  TO_CHAR(v_offender_all_schedules.start_time,'HH24:MI')  = to_char(:v_nbt_time,'HH24:MI') ");
				params.addValue("v_nbt_time", objSearchDao.getStartTime());
			} else {
				sqlQuery.append(
						" AND  TO_CHAR(v_offender_all_schedules.start_time,'HH24:MI') = to_char(:v_nbt_time,'HH24:MI') ");
			}
			params.addValue("v_nbt_time", objSearchDao.getStartTime());
		}
		if (objSearchDao.getEventOutcome() != null && !"PENDING".equals(objSearchDao.getEventOutcome())) {
			if (subWhere != null) {
				sqlQuery.append(" AND v_offender_all_schedules.event_outcome= :NBT_OUTCOME ");
			} else {
				sqlQuery.append(" AND v_offender_all_schedules.event_outcome= :NBT_OUTCOME ");
			}
			params.addValue("NBT_OUTCOME", objSearchDao.getEventOutcome());
		} else if (objSearchDao.getEventOutcome() != null && "PENDING".equals(objSearchDao.getEventOutcome())) {
			if (subWhere != null) {
				sqlQuery.append(" AND v_offender_all_schedules.event_outcome IS NULL ");
			} else {
				sqlQuery.append(" AND v_offender_all_schedules.event_outcome IS NULL ");
			}
			params.addValue("lastName", objSearchDao.getEventOutcome());
		}
		if ("Y".equals(objSearchDao.getUnexcusedAbsenceFlag())) {
				sqlQuery.append(" AND v_offender_all_schedules.unexcused_absence_flag = :counter ");
			params.addValue("counter", objSearchDao.getUnexcusedAbsenceFlag());
		}

		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("WHERE")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("AND")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		preparedSql = preparedSql.concat(" ORDER BY OFFENDER_LAST_NAME ,EVENT_DATE DESC,START_TIME ");
		final RowMapper<VOffenderAllSchedules> aliasesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				VOffenderAllSchedules.class, mMapping);
		return namedParameterJdbcTemplate.query(preparedSql, params, aliasesRowMapper);
	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstVOffenderAllSchedules List<VOffenderAllSchedules>
	 *
	 */
	public Integer offSchUpdateVOffenderAllSchedules(final List<VOffenderAllSchedules> lstVOffenderAllSchedules) {
		String sql = getQuery("OCIDIARY_OFFSCH_UPDATE_V_OFFENDER_ALL_SCHEDULES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (VOffenderAllSchedules vOffenderAllSchedules : lstVOffenderAllSchedules) {
			parameters.add(new BeanPropertySqlParameterSource(vOffenderAllSchedules));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstVOffenderAllSchedules.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao VOffenderAllSchedules
	 *
	 * @return List<VOffenderAllSchedules>
	 *
	 */

	public String checkUaEventOutcome(final List<VOffenderAllSchedules> lstVOffenderAllSchedules) {
		String sql = getQuery("OCIDIARY_CHECK_UA_EVENT_OUTCOME");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<>();
		for (VOffenderAllSchedules vOffenderAllSchedules : lstVOffenderAllSchedules) {
			parameters.add(new BeanPropertySqlParameterSource(vOffenderAllSchedules));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstVOffenderAllSchedules.size() == returnArray.length) {
			return "";
		} else {
			return "";
		}
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param  VOffenderAllSchedules
	 *
	 * @return List<VOffenderAllSchedules>
	 *
	 */

	public Integer destroyUas(final List<VOffenderAllSchedules> lstVOffenderAllSchedules) {
		String sql = getQuery("OCIDIARY_DESTROY_UAS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (VOffenderAllSchedules vOffenderAllSchedules : lstVOffenderAllSchedules) {
			parameters.add(new BeanPropertySqlParameterSource(vOffenderAllSchedules));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstVOffenderAllSchedules.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param VOffenderAllSchedules
	 *
	 * @return List<VOffenderAllSchedules>
	 *
	 */

	public Integer createUas(final List<VOffenderAllSchedules> lstVOffenderAllSchedules) {
		String sql = getQuery("OCIDIARY_CREATE_UAS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (VOffenderAllSchedules vOffenderAllSchedules : lstVOffenderAllSchedules) {
			parameters.add(new BeanPropertySqlParameterSource(vOffenderAllSchedules));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstVOffenderAllSchedules.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao VOffenderAllSchedules
	 *
	 * @return List<VOffenderAllSchedules>
	 *
	 */
	public String countSentenceUa(final List<VOffenderAllSchedules> lstVOffenderAllSchedules) {
		String sql = getQuery("OCIDIARY_COUNT_SENTENCE_UA");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (VOffenderAllSchedules vOffenderAllSchedules : lstVOffenderAllSchedules) {
			parameters.add(new BeanPropertySqlParameterSource(vOffenderAllSchedules));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstVOffenderAllSchedules.size() == returnArray.length) {
			return "";
		} else {
			return "";
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<AgencyLocations>
	 */
	public List<AgencyLocations> rgLocationRecordGroup(final String caseloadid) {
		final String sql = getQuery("OCIDIARY_FIND_RGLOCATION");
		final RowMapper<AgencyLocations> MRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams("CASELOADID", caseloadid), MRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgTypeRecordGroup() {
		final String sql = getQuery("OCIDIARY_FIND_RGTYPE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgSubTypeRecordGroup() {
		final String sql = getQuery("OCIDIARY_FIND_RGSUBTYPE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgOutcomeRecordGroup() {

		final String sql = getQuery("OCIDIARY_FIND_RGOUTCOME");
		final RowMapper<ReferenceCodes> returnList = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), returnList);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * createFormGlobals
	 *
	 * @param params
	 *
	 */
	public List<OmsModules> createFormGlobals(OmsModules paramBean) {
		final String sql = getQuery("OCIDIARY_CREATE_FORM_GLOBALS");
		final RowMapper<OmsModules> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OmsModules.class, mMapping);
		final ArrayList<OmsModules> returnList = (ArrayList<OmsModules>) namedParameterJdbcTemplate.query(sql,
				createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * defineWhereClauseDEFINE_WHERE_CLAUSE
	 *
	 * @param params
	 *
	 */
	public List<OffenderBookings> defineWhereClauseDEFINE_WHERE_CLAUSE1(OffenderBookings paramBean) {
		final String sql = getQuery("OCIDIARY_DEFINE_WHERE_CLAUSE_DEFINE_WHERE_CLAUSE");
		final RowMapper<OffenderBookings> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderBookings.class,
				mMapping);
		final ArrayList<OffenderBookings> returnList = (ArrayList<OffenderBookings>) namedParameterJdbcTemplate
				.query(sql, createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * defineWhereClauseDEFINE_WHERE_CLAUSE
	 *
	 * @param params
	 *
	 */
	public List<OffenderBookings> defineWhereClauseDEFINE_WHERE_CLAUSE(OffenderBookings paramBean) {
		final String sql = getQuery("OCIDIARY_DEFINE_WHERE_CLAUSE_DEFINE_WHERE_CLAUSE");
		final RowMapper<OffenderBookings> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderBookings.class,
				mMapping);
		final ArrayList<OffenderBookings> returnList = (ArrayList<OffenderBookings>) namedParameterJdbcTemplate
				.query(sql, createParams(), columnRowMapper);
		return returnList;
	}

	@Override
	public List<OffenderBookings> defineWhereClause(OffenderBookings paramBean) {
		return null;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * defineWhereClauseDEFINE_WHERE_CLAUSE
	 *
	 * @param params
	 *
	 */
	public BigDecimal getStaffName(String lastName, String firstName) {
		final String sql = getQuery("OCIDIARY_CREATE_FORM_GLOBALS_STAFF_ID");
		BigDecimal returnList = null;
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("LASTNAME", lastName, "FIRSTNAME", firstName), BigDecimal.class);
		} catch (Exception e) {
			return returnList;
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * defineWhereClauseDEFINE_WHERE_CLAUSE
	 *
	 * @param params
	 *
	 */
	public Integer adjustUa(VOffenderAllSchedules bean) {
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[10];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_EVENT_ID", OracleTypes.NUMBER),
				new SqlParameter("P_EVENT_TYPE", OracleTypes.VARCHAR),
				new SqlParameter("P_EVENT_SUB_TYPE", OracleTypes.VARCHAR),
				new SqlParameter("P_OLD_OUTCOME", OracleTypes.VARCHAR),
				new SqlParameter("P_NEW_OUTCOME", OracleTypes.VARCHAR),
				new SqlParameter("P_COUNT_FLAG", OracleTypes.VARCHAR) };
		final SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("OIDCOUNT").withProcedureName("SUBMIT_COUNT_JOB").declareParameters(sqlParameters);
		inParamMap.put("P_EVENT_ID", bean.getEventId());
		inParamMap.put("P_EVENT_TYPE", bean.getEventType());
		inParamMap.put("P_EVENT_SUB_TYPE", bean.getEventSubType());
		inParamMap.put("P_OLD_OUTCOME", bean.getEventOutcome());
		inParamMap.put("P_NEW_OUTCOME", bean.getEventOutcome());
		inParamMap.put("P_COUNT_FLAG", bean.getUnexcusedAbsenceFlag());
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			simpleJDBCCall.execute(inParameter);
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}

}
