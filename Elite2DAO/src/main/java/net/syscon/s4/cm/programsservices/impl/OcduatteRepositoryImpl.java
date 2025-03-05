package net.syscon.s4.cm.programsservices.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.cm.programsservices.OcduatteRepository;
import net.syscon.s4.cm.programsservices.maintenance.OffenderCourseSkills;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.im.beans.Teams;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.VOffenderCourseEvents;
import net.syscon.s4.inst.legals.beans.VOffenderSentenceEvents;
import oracle.jdbc.internal.OracleTypes;

/**
 * Class OcduatteRepositoryImpl
 */
@Repository
public class OcduatteRepositoryImpl extends RepositoryBase implements OcduatteRepository {
	private static final String P_NEW_OUTCOME = "P_NEW_OUTCOME";
	private static final String P_OLD_OUTCOME = "P_OLD_OUTCOME";
	private static final String P_EVENT_SUB_TYPE = "P_EVENT_SUB_TYPE";
	private static final String P_EVENT_TYPE = "P_EVENT_TYPE";
	private static final String P_OFF_PRGREF_ID = "P_OFF_PRGREF_ID";
	private static final String TAG_UNPAID_WORK = "TAG_UNPAID_WORK";
	private static final String OMS_OWNER = "OMS_OWNER";
	private static final String P_ACTIVITY_DESC = "P_ACTIVITY_DESC";
	private static final String P_CODE = "P_CODE";
	private static final String P_SUPERVISOR_NAME = "P_SUPERVISOR_NAME";
	private static final String P_WORK_QUALITY = "P_WORK_QUALITY";
	private static final String P_BEHAVIOUR = "P_BEHAVIOUR";
	private static final String P_ATTENDANCE = "P_ATTENDANCE";
	private static final String P_NAME = "P_NAME";
	private static final String P_OFFENDER_ID_DISPLAY = "P_OFFENDER_ID_DISPLAY";
	private static final String P_CRS_ACTY_ID = "P_CRS_ACTY_ID";
	private static final String P_SUPERVISOR_STAFF_ID = "P_SUPERVISOR_STAFF_ID";
	private static final String P_PERFORMANCE_CODE = "P_PERFORMANCE_CODE";
	private static final String P_BEHAVIOUR_CODE = "P_BEHAVIOUR_CODE";
	private static final String P_EVENT_OUTCOME = "P_EVENT_OUTCOME";
	private static final String P_OFFENDER_BOOK_ID = "P_OFFENDER_BOOK_ID";
	private static final String DESCRIPTION2 = "description";
	private static final String STAFF_DESC = "staffDesc";
	private static final String DESCRIPTION = "DESCRIPTION";
	private static Logger logger = LogManager.getLogger(OcduatteRepositoryImpl.class.getName());

	/**
	 * Creates new OcduatteRepositoryImpl class Object
	 */
	public OcduatteRepositoryImpl() {
		// OcduatteRepositoryImpl
	}

	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put(DESCRIPTION, new FieldMapper(DESCRIPTION2)).build();
	private final Map<String, FieldMapper> offenderCourseSkillsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("EVENT_ID", new FieldMapper("eventId")).put(STAFF_DESC, new FieldMapper("staffdesc")).build();
	private final Map<String, FieldMapper> mMMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put(DESCRIPTION, new FieldMapper(DESCRIPTION2)).put("CODE", new FieldMapper("code")).build();
	private final Map<String, FieldMapper> vOffenderCourseEventsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put(DESCRIPTION, new FieldMapper(DESCRIPTION2)).build();
	private final Map<String, FieldMapper> mMStaffMembersMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put(DESCRIPTION, new FieldMapper(DESCRIPTION2)).put("CODE", new FieldMapper("code")).build();
	private final Map<String, FieldMapper> staffNameMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put(STAFF_DESC, new FieldMapper(STAFF_DESC)).build();

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            VOffenderCourseEvents
	 *
	 * @return List<VOffenderCourseEvents>
	 *
	 * @throws SQLException
	 */
	public List<VOffenderCourseEvents> offCourseAttendExecuteQuery(final VOffenderCourseEvents objSearchDao) {
		final String sql = getQuery("OCDUATTE_OFFCOURSEATTEND_FIND_V_OFFENDER_COURSE_EVENTS");
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource params = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (Optional.ofNullable(objSearchDao).isPresent()) {
			sqlQuery.append(" WHERE");
			if (Optional.ofNullable(objSearchDao.getEventDate()).isPresent()) {
				final DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				sqlQuery.append(" EVENT_TYPE = 'UW' AND EVENT_CLASS = 'COMM' AND EVENT_DATE = TO_DATE(:nbtDate::text,'dd/MM/yyyy'::text) AND");
				params.addValue("nbtDate", sdf.format(objSearchDao.getEventDate()));
			}
			if (Optional.ofNullable(objSearchDao.getCrsActyId()).isPresent()) {
				sqlQuery.append(" CRS_ACTY_ID = :nbtCrsActyId AND");
				params.addValue("nbtCrsActyId", objSearchDao.getCrsActyId());
			} else {
				if (Optional.ofNullable(objSearchDao.getTeamId()).isPresent()) {
					sqlQuery.append(
							" CRS_ACTY_ID IN (SELECT CRS_ACTY_ID FROM COURSE_ACTIVITIES WHERE COURSE_ACTIVITY_TYPE = 'WS' AND PROVIDER_PARTY_CLASS = 'TEAM' AND PROVIDER_PARTY_ID = :nbtTeamId AND ACTIVE_FLAG = 'Y') AND");
					params.addValue("nbtTeamId", objSearchDao.getTeamId());
				}
			}
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("WHERE")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("AND")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		preparedSql = preparedSql
				+ " ORDER BY TAG_UNPAID_WORK_SORT_BY_PROJECT(CRS_ACTY_ID) ASC, TAG_UNPAID_WORK_SORT_BY_OFFENDER(OFFENDER_BOOK_ID) ASC";

		final RowMapper<VOffenderCourseEvents> vOffenderCourseEventsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				VOffenderCourseEvents.class, vOffenderCourseEventsMapping);
		try {
			return namedParameterJdbcTemplate.query(preparedSql, params, vOffenderCourseEventsRowMapper);
		} catch (final Exception e) {
			return Collections.emptyList();
		}
	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstVOffenderCourseEvents
	 *            List<VOffenderCourseEvents>
	 *
	 * @throws SQLException
	 */
	public Integer offCourseAttendUpdateVOffenderCourseEvents(
			final List<VOffenderCourseEvents> lstVOffenderCourseEvents) {
		final String sql = getQuery("OCDUATTE_OFFCOURSEATTEND_UPDATE_V_OFFENDER_COURSE_EVENTS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final VOffenderCourseEvents vOffenderCourseEvents : lstVOffenderCourseEvents) {
			parameters.add(new BeanPropertySqlParameterSource(vOffenderCourseEvents));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstVOffenderCourseEvents.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            OffenderCourseSkills
	 *
	 * @return List<OffenderCourseSkills>
	 *
	 * @throws Exception
	 */
	public List<OffenderCourseSkills> offCourseSkillsExecuteQuery(final OffenderCourseSkills objSearchDao) {
		final String sql = getQuery("OCDUATTE_OFFCOURSESKILLS_FIND_OFFENDER_COURSE_SKILLS");
		final RowMapper<OffenderCourseSkills> offenderCourseSkillsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderCourseSkills.class, offenderCourseSkillsMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("eventId", objSearchDao.getEventId()),
					offenderCourseSkillsRowMapper);
		} catch (final Exception e) {
			return Collections.emptyList();
		}
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstOffenderCourseSkills
	 *            List<OffenderCourseSkills>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public Integer offCourseSkillsInsertOffenderCourseSkills(final List<OffenderCourseSkills> lstOffenderCourseSkills) {
		final String sql = getQuery("OCDUATTE_OFFCOURSESKILLS_INSERT_OFFENDER_COURSE_SKILLS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final OffenderCourseSkills vOffenderCourseEvents : lstOffenderCourseSkills) {
			parameters.add(new BeanPropertySqlParameterSource(vOffenderCourseEvents));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenderCourseSkills.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstOffenderCourseSkills
	 *            List<OffenderCourseSkills>
	 *
	 * @throws SQLException
	 */
	public Integer offCourseSkillsUpdateOffenderCourseSkills(final List<OffenderCourseSkills> lstOffenderCourseSkills) {
		final String sql = getQuery("OCDUATTE_OFFCOURSESKILLS_UPDATE_OFFENDER_COURSE_SKILLS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final OffenderCourseSkills offenderCourseSkills : lstOffenderCourseSkills) {
			parameters.add(new BeanPropertySqlParameterSource(offenderCourseSkills));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenderCourseSkills.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstOffenderCourseSkills
	 *            List<OffenderCourseSkills>
	 *
	 * @throws SQLException
	 */
	public Integer offCourseSkillsDeleteOffenderCourseSkills(final List<OffenderCourseSkills> lstOffenderCourseSkills) {
		final String sql = getQuery("OCDUATTE_OFFCOURSESKILLS_DELETE_OFFENDER_COURSE_SKILLS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final OffenderCourseSkills offenderCourseSkills : lstOffenderCourseSkills) {
			parameters.add(new BeanPropertySqlParameterSource(offenderCourseSkills));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenderCourseSkills.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgAttendanceRecordGroup(final String pShowOutcome) {
		final String sql = getQuery("OCDUATTE_FIND_RGATTENDANCE_MODIFIED");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams("pShowOutcome", pShowOutcome), mRowMapper);
		} catch (final Exception e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 */
	public List<ReferenceCodes> rgSupervisorRecordGroup(final Long offenderbookId) {
		final String sql = getQuery("OCDUATTE_FIND_RGSUPERVISOR");
		final RowMapper<ReferenceCodes> mMStaffMembersRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				mMStaffMembersMapping);
		List<ReferenceCodes> list=new ArrayList<>();
		try {
			list= namedParameterJdbcTemplate.query(sql, createParams("OFFENDER_BOOK_ID", offenderbookId),
					mMStaffMembersRowMapper);
		} catch (final Exception e) {
			return list;
		}
		return list;
	}

	/**
	 * Used to capture results from select query
	 * 
	 */
	public List<ReferenceCodes> rgBehaviourRecordGroup() {
		final String sql = getQuery("OCDUATTE_FIND_RGBEHAVIOUR");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (final Exception e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgWorkQualityRecordGroup() {
		final String sql = getQuery("OCDUATTE_FIND_RGWORKQUALITY");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (final Exception e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<CourseActivities> rgProjectsRecordGroup(final String teamId) {
		final String sql = getQuery("OCDUATTE_FIND_RGPROJECTS");
		final RowMapper<CourseActivities> mRowMapper = Row2BeanRowMapper.makeMapping(sql, CourseActivities.class,
				mMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("NBTTEAMID", teamId), mRowMapper);
		} catch (final Exception e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<MM>
	 */
	public List<CourseActivities> rgProjects2RecordGroup(final Long offenderBookId) {
		final String sql = getQuery("OCDUATTE_FIND_RGPROJECTS_NBT_CODE");
		final RowMapper<CourseActivities> mMRowMapper = Row2BeanRowMapper.makeMapping(sql, CourseActivities.class,
				mMMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("OFFENDERBOOKID", offenderBookId), mMRowMapper);
		} catch (final Exception e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgSkillsRecordGroup() {
		final String sql = getQuery("OCDUATTE_FIND_RGSKILLS");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (final Exception e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<MM>
	 */
	public List<StaffMembers> rgStaffCheckRecordGroup() {
		final String sql = getQuery("OCDUATTE_FIND_RGSTAFFCHECK");
		final RowMapper<StaffMembers> mMRowMapper = Row2BeanRowMapper.makeMapping(sql, StaffMembers.class, mMMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams("NBTSTAFFDESC", ""), mMRowMapper);
		} catch (final Exception e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<MMM>
	 */
	public List<Teams> rgTeamsRecordGroup(String userId) {
		final String sql = getQuery("OCDUATTE_FIND_RGTEAMS");
		final RowMapper<Teams> mMMRowMapper = Row2BeanRowMapper.makeMapping(sql, Teams.class, mMMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams("createUserId",userId), mMMRowMapper);
		} catch (final Exception e) {
			return Collections.emptyList();
		}
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offCourseAttendOnCheckDeleteMaster
	 *
	 * @param params
	 *
	 */
	public List<OffenderCourseSkills> offCourseAttendOnCheckDeleteMaster(final OffenderCourseSkills paramBean) {
		final String sql = getQuery("OCDUATTE_OFF_COURSE_ATTEND_ONCHECKDELETEMASTER");
		final RowMapper<OffenderCourseSkills> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderCourseSkills.class, offenderCourseSkillsMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		} catch (final Exception e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 */

	@Override
	public VOffenderCourseEvents offCourseAttendPostQuery(final VOffenderCourseEvents actionObj) {
		Map<String, Object> returnObject;
		final VOffenderCourseEvents resultObject = new VOffenderCourseEvents();
		final Map<String, Object> inParamMap = new HashMap<>();
		final SqlParameter[] sqlParameters = new SqlParameter[] {
				new SqlParameter(P_OFFENDER_BOOK_ID, OracleTypes.NUMBER),
				new SqlParameter(P_EVENT_OUTCOME, OracleTypes.VARCHAR),
				new SqlParameter(P_BEHAVIOUR_CODE, OracleTypes.VARCHAR),
				new SqlParameter(P_PERFORMANCE_CODE, OracleTypes.VARCHAR),
				new SqlParameter(P_SUPERVISOR_STAFF_ID, OracleTypes.NUMBER),
				new SqlParameter(P_CRS_ACTY_ID, OracleTypes.NUMBER),
				new SqlOutParameter(P_OFFENDER_ID_DISPLAY, OracleTypes.VARCHAR),
				new SqlOutParameter(P_NAME, OracleTypes.VARCHAR),
				new SqlOutParameter(P_ATTENDANCE, OracleTypes.VARCHAR),
				new SqlOutParameter(P_BEHAVIOUR, OracleTypes.VARCHAR),
				new SqlOutParameter(P_WORK_QUALITY, OracleTypes.VARCHAR),
				new SqlOutParameter(P_SUPERVISOR_NAME, OracleTypes.VARCHAR),
				new SqlOutParameter(P_CODE, OracleTypes.VARCHAR),
				new SqlOutParameter(P_ACTIVITY_DESC, OracleTypes.VARCHAR), };
		final SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName(OMS_OWNER)
				.withCatalogName(TAG_UNPAID_WORK).withProcedureName("GET_ATTEND_INFO").declareParameters(sqlParameters);
		inParamMap.put(P_OFFENDER_BOOK_ID, actionObj.getOffenderBookId());
		inParamMap.put(P_EVENT_OUTCOME, actionObj.getEventOutcome());
		inParamMap.put(P_BEHAVIOUR_CODE, actionObj.getBehaviourCode());
		inParamMap.put(P_PERFORMANCE_CODE, actionObj.getPerformanceCode());
		inParamMap.put(P_SUPERVISOR_STAFF_ID, actionObj.getSupervisorStaffId());
		inParamMap.put(P_CRS_ACTY_ID, actionObj.getCrsActyId());
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			if (!returnObject.isEmpty()) {
				if (Optional.ofNullable(returnObject.get(P_OFFENDER_ID_DISPLAY)).isPresent()) {
					resultObject.setpOffenderIdDisplay(returnObject.get(P_OFFENDER_ID_DISPLAY).toString());
				}
				if (Optional.ofNullable(returnObject.get(P_NAME)).isPresent()) {
					resultObject.setpName(returnObject.get(P_NAME).toString());
				}
				if (Optional.ofNullable(returnObject.get(P_ATTENDANCE)).isPresent()) {
					resultObject.setpAttendance(returnObject.get(P_ATTENDANCE).toString());
				}
				if (Optional.ofNullable(returnObject.get(P_BEHAVIOUR)).isPresent()) {
					resultObject.setpBehaviour(returnObject.get(P_BEHAVIOUR).toString());
				}
				if (Optional.ofNullable(returnObject.get(P_WORK_QUALITY)).isPresent()) {
					resultObject.setpWorkQuality(returnObject.get(P_WORK_QUALITY).toString());
				}
				if (Optional.ofNullable(returnObject.get(P_SUPERVISOR_NAME)).isPresent()) {
					resultObject.setpSupervisorName(returnObject.get(P_SUPERVISOR_NAME).toString());
				}
				if (Optional.ofNullable(returnObject.get(P_CODE)).isPresent()) {
					resultObject.setpCode(returnObject.get(P_CODE).toString());
				}
				if (Optional.ofNullable(returnObject.get(P_ACTIVITY_DESC)).isPresent()) {
					resultObject.setpActivityDesc(returnObject.get(P_ACTIVITY_DESC).toString());
				}
			}
		} catch (final Exception e) {
			logger.error("offCourseAttendPostQuery", e);
		}
		return resultObject;
	}

	/**
	 * Used to capture results from select query
	 * 
	 */
	@Override
	public Integer compareDateTime(final VOffenderCourseEvents actionObj) {
		Map<String, Object> returnObject = null;
		Integer code = null;
		final DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		final Map<String, Object> inParamMap = new HashMap<>();
		final SqlParameter[] sqlParameters = new SqlParameter[] { new SqlParameter("P_DATE_1", OracleTypes.VARCHAR),
				new SqlParameter("P_TIME_1", OracleTypes.VARCHAR), new SqlParameter("P_DATE_2", OracleTypes.VARCHAR),
				new SqlParameter("P_TIME_2", OracleTypes.VARCHAR),
				new SqlOutParameter("v_Return", OracleTypes.NUMBER) };
		final SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName(OMS_OWNER)
				.withCatalogName("OMS_DATE_TIME").withFunctionName("COMPARE_DATE_TIME")
				.declareParameters(sqlParameters);
		inParamMap.put("P_DATE_1", simpleDateFormat.format(actionObj.getEventDate()));
		inParamMap.put("P_TIME_1", formatter.format(actionObj.getStartTime()));
		inParamMap.put("P_DATE_2", simpleDateFormat.format(actionObj.getEventDate()));
		inParamMap.put("P_TIME_2", formatter.format(actionObj.getEndTime()));
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = simpleJDBCCall.withoutProcedureColumnMetaDataAccess().execute(inParameter);
			final BigDecimal coded = (BigDecimal) returnObject.get("v_Return");
			code = coded.intValue();
		} catch (final Exception e) {
			logger.error("compareDateTime", e);
			return code;
		}
		return code;

	}

	/**
	 * Used to capture results from select query
	 * 
	 */

	@Override
	public Integer getCompareDateTime(final VOffenderCourseEvents actionObj) {
		final String sql = getQuery("OCDUATTE_COMPARE_DATE_TIME");
		final DateFormat sdfTime = new SimpleDateFormat("dd-MM-yyyy hh:mm a");
		final DateFormat sdfDate = new SimpleDateFormat("dd-MM-yyyy");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql,
					createParams("P_DATE_1", sdfDate.format(actionObj.getEventDate()), "P_DATE_2",
							sdfDate.format(actionObj.getEventDate()), "P_TIME_1",
							sdfTime.format(actionObj.getStartTime()), "P_TIME_2",
							sdfTime.format(actionObj.getEndTime())),
					Integer.class);
		} catch (final Exception e) {
			return null;
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 */
	@Override
	public OffenderCourseSkills getStaffNamePostQuery(final OffenderCourseSkills action) {
		final String sql = getQuery("OCDUATTE_OFFCOURSESKILLS_FIND_STAFF_NAMES");
		final RowMapper<OffenderCourseSkills> offenderCourseSkillsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderCourseSkills.class, staffNameMapping);
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("pStaffId", action.getStaffId()),
					offenderCourseSkillsRowMapper);
		} catch (final Exception e) {
			return null;
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 */
	@Override
	public Integer saveOffCourseAttendonUpdate(final VOffenderCourseEvents actionObj) {
		final Integer code = null;
		final Map<String, Object> inParamMap = new HashMap<>();
		final SqlParameter[] sqlParameters = new SqlParameter[] {
				new SqlParameter(P_OFFENDER_BOOK_ID, OracleTypes.NUMBER),
				new SqlParameter("P_EVENT_DATE", OracleTypes.DATE),
				new SqlParameter(P_EVENT_OUTCOME, OracleTypes.VARCHAR), new SqlParameter("P_IN_TIME", OracleTypes.DATE),
				new SqlParameter("P_OUT_TIME", OracleTypes.DATE),
				new SqlParameter("P_AGREED_TRAVEL_HOUR", OracleTypes.NUMBER),
				new SqlParameter("P_CREDITED_HOURS", OracleTypes.NUMBER),
				new SqlParameter(P_SUPERVISOR_STAFF_ID, OracleTypes.NUMBER),
				new SqlParameter(P_BEHAVIOUR_CODE, OracleTypes.VARCHAR),
				new SqlParameter(P_PERFORMANCE_CODE, OracleTypes.VARCHAR),
				new SqlParameter(P_OFF_PRGREF_ID, OracleTypes.NUMBER),
				new SqlParameter(P_CRS_ACTY_ID, OracleTypes.NUMBER),
				new SqlParameter("P_EVENT_STATUS", OracleTypes.VARCHAR),
				new SqlParameter(P_EVENT_TYPE, OracleTypes.VARCHAR),
				new SqlParameter(P_EVENT_SUB_TYPE, OracleTypes.VARCHAR),
				new SqlParameter("P_COMMENT_TEXT", OracleTypes.VARCHAR), };
		final SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName(OMS_OWNER)
				.withCatalogName(TAG_UNPAID_WORK).withProcedureName("CREATE_OFF_COURSE_ATTENDANCE")
				.declareParameters(sqlParameters);
		inParamMap.put(P_OFFENDER_BOOK_ID, actionObj.getOffenderBookId());
		inParamMap.put("P_EVENT_DATE", actionObj.getEventDate());
		inParamMap.put(P_EVENT_OUTCOME, actionObj.getEventOutcome());
		inParamMap.put("P_IN_TIME", actionObj.getStartTime());
		inParamMap.put("P_OUT_TIME", actionObj.getEndTime());
		inParamMap.put("P_AGREED_TRAVEL_HOUR", actionObj.getAgreedTravelHour());
		inParamMap.put("P_CREDITED_HOURS", actionObj.getCreditedHours());
		inParamMap.put(P_SUPERVISOR_STAFF_ID, actionObj.getSupervisorStaffId());
		inParamMap.put(P_BEHAVIOUR_CODE, actionObj.getBehaviourCode());
		inParamMap.put(P_PERFORMANCE_CODE, actionObj.getPerformanceCode());
		inParamMap.put(P_OFF_PRGREF_ID, actionObj.getNbtRecordOffPrgrefId());
		inParamMap.put(P_CRS_ACTY_ID, actionObj.getNbtRecordCrsActyId());
		inParamMap.put("P_EVENT_STATUS", "SCH");
		inParamMap.put(P_EVENT_TYPE, actionObj.getEventType());
		inParamMap.put(P_EVENT_SUB_TYPE, actionObj.getEventSubType());
		inParamMap.put("P_COMMENT_TEXT", actionObj.getCommentText());
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			simpleJDBCCall.withoutProcedureColumnMetaDataAccess().execute(inParameter);
		} catch (final Exception e) {
			logger.error("saveOffCourseAttendonUpdate", e);
			return code;
		}
		return code;

	}

	/**
	 * Used to capture results from select query
	 * 
	 */

	@Override
	public Integer saveOffCourseAttendPostUpdate(final VOffenderCourseEvents actionObj) {
		final Integer code = null;
		final Map<String, Object> inParamMap = new HashMap<>();
		final SqlParameter[] sqlParameters = new SqlParameter[] { new SqlParameter("P_OFF_BOOK_ID", OracleTypes.NUMBER),
				new SqlParameter(P_CRS_ACTY_ID, OracleTypes.NUMBER),
				new SqlParameter(P_OFF_PRGREF_ID, OracleTypes.NUMBER), };
		final SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName(OMS_OWNER)
				.withCatalogName(TAG_UNPAID_WORK).withProcedureName("UPDATE_CREDIT_HRS")
				.declareParameters(sqlParameters);
		inParamMap.put("P_OFF_BOOK_ID", actionObj.getOffenderBookId());
		inParamMap.put(P_CRS_ACTY_ID, actionObj.getCrsActyId());
		inParamMap.put(P_OFF_PRGREF_ID, actionObj.getOffPrgrefId());
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			simpleJDBCCall.withoutProcedureColumnMetaDataAccess().execute(inParameter);
		} catch (final Exception e) {
			logger.error("saveOffCourseAttendPostUpdate", e);
			return code;
		}
		return code;

	}

	/**
	 * Used to save results to Databse
	 * 
	 */
	@Override
	public Integer saveOffCourseAttendPreUpdate(final VOffenderCourseEvents actionObj) {
		final Integer code = null;
		final Map<String, Object> inParamMap = new HashMap<>();
		final SqlParameter[] sqlParameters = new SqlParameter[] { new SqlParameter("P_EVENT_ID", OracleTypes.NUMBER),
				new SqlParameter(P_EVENT_TYPE, OracleTypes.VARCHAR),
				new SqlParameter(P_EVENT_SUB_TYPE, OracleTypes.VARCHAR),
				new SqlParameter(P_OLD_OUTCOME, OracleTypes.VARCHAR),
				new SqlParameter(P_NEW_OUTCOME, OracleTypes.VARCHAR),
				new SqlParameter("P_COUNT_FLAG", OracleTypes.VARCHAR), };
		final SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName(OMS_OWNER)
				.withCatalogName("TAG_MULTIPLE_FAILURE").withProcedureName("ADJUST_UA")
				.declareParameters(sqlParameters);
		inParamMap.put("P_EVENT_ID", actionObj.getEventId());
		inParamMap.put(P_EVENT_TYPE, actionObj.getEventType());
		inParamMap.put(P_EVENT_SUB_TYPE, actionObj.getEventSubType());
		inParamMap.put(P_OLD_OUTCOME, actionObj.getEventOutcomeDbVal());
		inParamMap.put(P_NEW_OUTCOME, actionObj.getEventOutcome());
		inParamMap.put("P_COUNT_FLAG", actionObj.getUnexcusedAbsenceFlag());
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			simpleJDBCCall.withoutProcedureColumnMetaDataAccess().execute(inParameter);
		} catch (final Exception e) {
			logger.error("saveOffCourseAttendPreUpdate", e);
			return code;
		}
		return code;

	}

	/**
	 * Used to capture results from select query
	 * 
	 */
	@Override
	public VOffenderCourseEvents checkUa(final VOffenderCourseEvents actionObj) {
		Map<String, Object> returnObject = null;
		final VOffenderCourseEvents response = new VOffenderCourseEvents();
		final Map<String, Object> inParamMap = new HashMap<>();
		final SqlParameter[] sqlParameters = new SqlParameter[] { new SqlParameter("P_EVENT_ID", OracleTypes.NUMBER),
				new SqlParameter(P_EVENT_TYPE, OracleTypes.VARCHAR),
				new SqlParameter(P_EVENT_SUB_TYPE, OracleTypes.VARCHAR),
				new SqlParameter(P_OLD_OUTCOME, OracleTypes.VARCHAR),
				new SqlParameter(P_NEW_OUTCOME, OracleTypes.VARCHAR),
				new SqlOutParameter("P_OLD_UA", OracleTypes.BOOLEAN),
				new SqlOutParameter("P_NEW_UA", OracleTypes.BOOLEAN),
				new SqlOutParameter("P_MULTIPLE_FAILURE", OracleTypes.BOOLEAN) };
		final SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName(OMS_OWNER)
				.withCatalogName("TAG_MULTIPLE_FAILURE").withProcedureName("CHECK_UA").declareParameters(sqlParameters);
		inParamMap.put("P_EVENT_ID", actionObj.getEventId());
		inParamMap.put(P_EVENT_TYPE, actionObj.getEventType());
		inParamMap.put(P_EVENT_SUB_TYPE, actionObj.getEventSubType());
		inParamMap.put(P_OLD_OUTCOME, actionObj.getEventOutcomeDbVal());
		inParamMap.put(P_NEW_OUTCOME, actionObj.getEventOutcome());
		inParamMap.put("P_OLD_UA", actionObj.getpOldUa());
		inParamMap.put("P_NEW_UA", actionObj.getpNewUa());
		inParamMap.put("P_MULTIPLE_FAILURE", actionObj.getpMultipleFailure());

		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = simpleJDBCCall.withoutProcedureColumnMetaDataAccess().execute(inParameter);
			response.setpOldUa((Boolean) returnObject.get("P_OLD_UA"));
			response.setpNewUa((Boolean) returnObject.get("P_NEW_UA"));
			response.setpMultipleFailure((Boolean) returnObject.get("P_MULTIPLE_FAILURE"));
		} catch (final Exception e) {
			logger.error("checkUa", e);
			return null;
		}
		return response;

	}

	/**
	 * Used to capture results from select query
	 * 
	 */
	@Override
	public List<VOffenderSentenceEvents> checkUaFailCur(final VOffenderCourseEvents actionObj) {
		final String sql = getQuery("OCDUATTE_VOFFENDER_COURSE_EVENTS_CHECK_UA_FAIL_CUR");
		final RowMapper<VOffenderSentenceEvents> offenderCourseSkillsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				VOffenderSentenceEvents.class, offenderCourseSkillsMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("pEventId", actionObj.getEventId()),
					offenderCourseSkillsRowMapper);
		} catch (final Exception e) {
			logger.error("checkUaFailCur", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 */

	@Override
	public String checkUaCheckUaEventOutcome(final VOffenderCourseEvents actionObj, final String eventOutcom) {
		final String sql = getQuery("OCDUATTE_VOFFENDER_COURSE_EVENTS_CHECK_UA_EVENT_OUTCOME");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("pEventType", actionObj.getEventType(),
					"pEventSubType", actionObj.getEventSubType(), "pEventOutcome", eventOutcom), String.class);
		} catch (final Exception e) {
			logger.error("checkUaCheckUaEventOutcome", e);
			return null;
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 */

	@Override
	public Integer checkUaCountSentenceUa(final VOffenderSentenceEvents actionObj, final BigDecimal eventId) {
		final String sql = getQuery("OCDUATTE_VOFFENDER_COURSE_EVENTS_CHECK_UA_COUNT_SENTENCE_UA");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql,
					createParams("pOffenderBookId", actionObj.getOffenderBookId(), "pSentenceSeq",
							actionObj.getSentenceSeq(), "pEventDate", actionObj.getEventDate(), "pEventId", eventId),
					Integer.class);
		} catch (final Exception e) {
			logger.error("checkUaCountSentenceUa", e);
			return null;
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 */
	@Override
	public String getStaffName(final long staffId) {
		Map<String, Object> returnObject = null;
		String staffName = null;
		final Map<String, Object> inParamMap = new HashMap<>();
		final SqlParameter[] sqlParameters = new SqlParameter[] { new SqlParameter("P_STAFF_ID", OracleTypes.NUMBER),
				new SqlOutParameter("v_Return", OracleTypes.VARCHAR) };
		final SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName(OMS_OWNER)
				.withCatalogName("OMS_MISCELLANEOUS").withFunctionName("STAFF_NAME").declareParameters(sqlParameters);
		inParamMap.put("P_STAFF_ID", staffId);
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = simpleJDBCCall.withoutProcedureColumnMetaDataAccess().execute(inParameter);
			if (Optional.ofNullable(returnObject.get("v_Return")).isPresent()) {
				staffName = (String) returnObject.get("v_Return");
			}
		} catch (final Exception e) {
			logger.error("getStaffName", e);
			return null;
		}
		return staffName;

	}

	public VOffenderCourseEvents ocduatteVoffenderCourseEventsOldObject(final VOffenderCourseEvents objSearchDao) {
		final String sql = getQuery("OCDUATTE_VOFFENDER_COURSE_EVENTS_OLD_OBJECT");
		VOffenderCourseEvents returnlist = new 		VOffenderCourseEvents();
		try {
			returnlist = namedParameterJdbcTemplate.queryForObject(sql, createParams("eventId", objSearchDao.getEventId()),
					new BeanPropertyRowMapper<VOffenderCourseEvents>(VOffenderCourseEvents.class));
		} catch (final Exception e) {  
			logger.error("", e);
		}
		return returnlist;
	}
}
