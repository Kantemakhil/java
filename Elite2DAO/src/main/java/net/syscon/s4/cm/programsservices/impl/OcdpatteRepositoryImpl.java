package net.syscon.s4.cm.programsservices.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.cm.casemanagement.maintenance.beans.EventMeasureOutcomes;
import net.syscon.s4.cm.casemanagement.maintenance.beans.EventMeasures;
import net.syscon.s4.cm.programsservices.CourseScheduleStaff;
import net.syscon.s4.cm.programsservices.OcdpatteRepository;
import net.syscon.s4.cm.programsservices.VAcpSchedules;
import net.syscon.s4.cm.teamsworkflow.beans.TeamMembers;
import net.syscon.s4.common.beans.OffenderCourseAttendance;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.im.beans.VAddresses;
import net.syscon.s4.inst.casemanagement.beans.ProgramServices;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.CourseSchedules;
import net.syscon.s4.inst.legals.beans.VOffenderSentenceEvents;
import net.syscon.s4.iwp.impl.OcdtapowRepositoryImpl;
import oracle.jdbc.OracleTypes;

/**
 * Class OcdpatteRepositoryImpl
 * 
 */
@Repository
public class OcdpatteRepositoryImpl extends RepositoryBase implements OcdpatteRepository {

	private static final String PEVENTID = "P_EVENT_ID";
	private static final String PEVENTTYPE = "P_EVENT_TYPE";
	private static final String PEVENTSUBTYPE = "P_EVENT_SUB_TYPE";
	private static final String POLDOUTCOME = "P_OLD_OUTCOME";
	private static final String PNEWOUTCOME = "P_NEW_OUTCOME";

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcdtapowRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> mmteamMembersMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description")).put("TEAM_CODE", new FieldMapper("teamCode"))
			.put("TEAM_ID", new FieldMapper("teamId")).put("LIST_SEQ", new FieldMapper("teamMemberId"))
			.put("CODE", new FieldMapper("code")).build();

	private final Map<String, FieldMapper> offenderMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("LAST_NAME", new FieldMapper("lastName")).put("FIRST_NAME", new FieldMapper("firstName"))
			.put("OFFENDER_ID_DISPLAY", new FieldMapper("offenderIdDisplay")).build();

	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description")).put("PROGRAM_CODE", new FieldMapper("programCode"))
			.build();

	private final Map<String, FieldMapper> refMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("AGENCY_LOCATION_TYPE", new FieldMapper("agencyLocationType")).put("CODE", new FieldMapper("code"))
			.put("DESCRIPTION", new FieldMapper("description")).put("LAST_NAME", new FieldMapper("lastName"))
			.put("program_id", new FieldMapper("programId")).put("FIRST_NAME", new FieldMapper("firstName")).build();

	private final Map<String, FieldMapper> courseschMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("COURSE_SCHEDULE_STAFF_ID", new FieldMapper("courseScheduleStaffId"))
			.put("CRS_SCH_ID", new FieldMapper("crsSchId")).put("STAFF_ROLE", new FieldMapper("staffRole"))
			.put("STAFF_ID", new FieldMapper("staffId")).build();

	private final Map<String, FieldMapper> courseMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("VIDEO_REFERENCE_ID", new FieldMapper("videoReferenceId"))
			.put("START_TIME", new FieldMapper("startTime")).put("END_TIME", new FieldMapper("endTime"))
			.put("SESSION_NO", new FieldMapper("sessionNo"))
			.put("CATCH_UP_SESSION_FLAG", new FieldMapper("catchUpSessionFlag"))
			.put("SCHEDULE_STATUS", new FieldMapper("scheduleStatus")).put("MODULE_DESC", new FieldMapper("moduleDesc"))
			.put("OCCURRENCE_CODE", new FieldMapper("occurrenceCode")).put("PHASE_DESC", new FieldMapper("phaseDesc"))
			.put("PHASE_ID", new FieldMapper("phaseId"))
			.put("PROGRAM_INSTANCE_ID", new FieldMapper("programInstanceId"))
			.put("PROGRAM_DESC", new FieldMapper("programDesc")).put("PROGRAM_ID", new FieldMapper("programId"))
			.put("PAY_FLAG", new FieldMapper("payFlag"))
			.put("AUTHORISED_ABSENCE_FLAG", new FieldMapper("authorisedAbsenceFlag")).build();
	private final Map<String, FieldMapper> courseAttendMap = new ImmutableMap.Builder<String, FieldMapper>()
			.put("EVENT_ID", new FieldMapper("eventId")).put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId"))
			.put("EVENT_DATE", new FieldMapper("eventDate")).put("START_TIME", new FieldMapper("startTime"))
			.put("END_TIME", new FieldMapper("endTime")).put("EVENT_SUB_TYPE", new FieldMapper("eventSubType"))
			.put("EVENT_STATUS", new FieldMapper("eventStatus")).put("COMMENT_TEXT", new FieldMapper("commentText"))
			.put("HIDDEN_COMMENT_TEXT", new FieldMapper("hiddenCommentText"))
			.put("TO_INTERNAL_LOCATION_ID", new FieldMapper("toInternalLocationId"))
			.put("CRS_SCH_ID", new FieldMapper("crsSchId"))
			.put("OUTCOME_REASON_CODE", new FieldMapper("outcomeReasonCode"))
			.put("ENGAGEMENT_CODE", new FieldMapper("engagementCode"))
			.put("UNDERSTANDING_CODE", new FieldMapper("understandingCode")).put("DETAILS", new FieldMapper("details"))
			.put("EVENT_OUTCOME", new FieldMapper("eventOutcome"))
			.put("OFF_CRS_SCH_REF_ID", new FieldMapper("offCrsSchRefId")).put("AGY_LOC_ID", new FieldMapper("agyLocId"))
			.put("PROGRAM_ID", new FieldMapper("programId")).build();

	private final Map<String, FieldMapper> vOffenderSentenceEventsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("SENTENCE_SEQ", new FieldMapper("sentenceSeq")).put("EVENT_DATE", new FieldMapper("eventDate"))
			.put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId")).build();

	/**
	 * Creates new OcdpatteRepositoryImpl class Object
	 */
	public OcdpatteRepositoryImpl() {

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao OffenderCourseAttendances
	 *
	 * @return List<OffenderCourseAttendances>
	 *
	 */
	public List<OffenderCourseAttendance> offCourseAttendancesExecuteQuery(
			final OffenderCourseAttendance objSearchDao) {
		final String sql = getQuery("OCDPATTE_OFFCOURSEATTENDANCES_FIND_OFFENDER_COURSE_ATTENDANCES");
		final RowMapper<OffenderCourseAttendance> OffenderCourseAttendancesRowMapper = Row2BeanRowMapper
				.makeMapping(sql, OffenderCourseAttendance.class, courseAttendMap);
		return namedParameterJdbcTemplate.query(sql, createParams("crs_sch_id", objSearchDao.getCrsSchId()),
				OffenderCourseAttendancesRowMapper);
	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstOffenderCourseAttendances List<OffenderCourseAttendances>
	 *
	 */
	public Integer offCourseAttendancesUpdateOffenderCourseAttendances(final List<OffenderCourseAttendance> lst) {
		final String sql = getQuery("OCDPATTE_OFFCOURSEATTENDANCES_UPDATE_OFFENDER_COURSE_ATTENDANCES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderCourseAttendance offenderCourseAttendances : lst) {
			parameters.add(new BeanPropertySqlParameterSource(offenderCourseAttendances));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lst.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao CourseScheduleStaffs
	 *
	 * @return List<CourseScheduleStaffs>
	 *
	 */
	public List<CourseScheduleStaff> courseScheduleStaffsExecuteQuery(final CourseScheduleStaff objSearchDao) {
		final String sql = getQuery("OCDPATTE_COURSESCHEDULESTAFFS_FIND_COURSE_SCHEDULE_STAFFS");
		final RowMapper<CourseScheduleStaff> rowMapper = Row2BeanRowMapper.makeMapping(sql, CourseScheduleStaff.class,
				courseschMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("crschId", objSearchDao.getCrsSchId()), rowMapper);
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstCourseScheduleStaffs List<CourseScheduleStaffs>
	 *
	 * @return List<Integer>
	 *
	 */
	public Integer courseScheduleStaffsInsertCourseScheduleStaffs(final List<CourseScheduleStaff> lst) {
		int insertCount = 0;
		final String sql = getQuery("OCDPATTE_COURSESCHEDULESTAFFS_INSERT_COURSE_SCHEDULE_STAFFS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final CourseScheduleStaff courseSchStaff : lst) {
			parameters.add(new BeanPropertySqlParameterSource(courseSchStaff));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		for (int i = 0; i < returnArray.length; i++) {
			insertCount = insertCount + 1;
		}
		if (lst.size() == insertCount) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstCourseScheduleStaffs List<CourseScheduleStaffs>
	 *
	 */
	public Integer courseScheduleStaffsUpdateCourseScheduleStaffs(final List<CourseScheduleStaff> lst) {
		final String sql = getQuery("OCDPATTE_COURSESCHEDULESTAFFS_UPDATE_COURSE_SCHEDULE_STAFFS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final CourseScheduleStaff courseScheduleStaffs : lst) {
			parameters.add(new BeanPropertySqlParameterSource(courseScheduleStaffs));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lst.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstCourseScheduleStaffs List<CourseScheduleStaffs>
	 *
	 */
	public Integer courseScheduleStaffsDeleteCourseScheduleStaffs(final List<CourseScheduleStaff> lst) {
		final String sql = getQuery("OCDPATTE_COURSESCHEDULESTAFFS_DELETE_COURSE_SCHEDULE_STAFFS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final CourseScheduleStaff courseScheStaffs : lst) {
			parameters.add(new BeanPropertySqlParameterSource(courseScheStaffs));
		}
		try {
			String tableName = "COURSE_SCHEDULE_STAFFS";
			String whereClause = "COURSE_SCHEDULE_STAFF_ID=:courseScheduleStaffId and CRS_SCH_ID=:crsSchId";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method courseScheduleStaffsDeleteCourseScheduleStaffs", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (returnArray.length > 0) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao CourseSchedules
	 *
	 * @return List<CourseSchedules>
	 *
	 */
	public List<CourseSchedules> deliveryDetailsExecuteQuery(final CourseSchedules objSearchDao) {
		final String sql = getQuery("OCDPATTE_DELIVERYDETAILS_FIND_COURSE_SCHEDULES");
		final RowMapper<CourseSchedules> rowMapper = Row2BeanRowMapper.makeMapping(sql, CourseSchedules.class,
				courseMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("crschId", objSearchDao.getCrsSchId()), rowMapper);
	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstCourseSchedules List<CourseSchedules>
	 *
	 */
	public Integer deliveryDetailsUpdateCourseSchedules(final List<CourseSchedules> lst) {
		final String sql = getQuery("OCDPATTE_DELIVERYDETAILS_UPDATE_COURSE_SCHEDULES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final CourseSchedules courseSchedules : lst) {
			parameters.add(new BeanPropertySqlParameterSource(courseSchedules));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (returnArray.length > 0) {
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
	public List<ReferenceCodes> rgScheduleTypeRecordGroup() {
		final String sql = getQuery("OCDPATTE_FIND_RGSCHEDULETYPE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (final EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ProgramServices> rgServiceRecordGroup() {
		final String sql = getQuery("OCDPATTE_FIND_RGSERVICE");
		final RowMapper<ProgramServices> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ProgramServices.class,
				refMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (final EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgEngagementRecordGroup() {
		final String sql = getQuery("OCDPATTE_FIND_RGENGAGEMENT");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				refMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (final EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<AgencyLocations> rgInstProviderRecordGroup(String caseLoadId) {
		final String sql = getQuery("OCDPATTE_FIND_RGINSTPROVIDER");
		final RowMapper<AgencyLocations> mRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				refMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams("CASELOADID", caseLoadId), mRowMapper);
		} catch (final EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<MMTeamMembers>
	 */
	public List<ReferenceCodes> rgCommProviderRecordGroup(String createUserId) {
		final String sql = getQuery("OCDPATTE_FIND_RGCOMMPROVIDER");
		final RowMapper<ReferenceCodes> rowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				refMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams("createUserId", createUserId), rowMapper);
		} catch (final EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<MMTeamMembersMEventMeasures>
	 */
	public List<ReferenceCodes> rgConfirmAttendanceRecordGroup() {
		final String sql = getQuery("OCDPATTE_FIND_RGCONFIRMATTENDANCE");
		final RowMapper<ReferenceCodes> rowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				refMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), rowMapper);
		} catch (final EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgUnderstandingRecordGroup() {
		final String sql = getQuery("OCDPATTE_FIND_RGUNDERSTANDING");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				refMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (final EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgStaffRoleRecordGroup() {
		final String sql = getQuery("OCDPATTE_FIND_RGSTAFFROLE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				refMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (final EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<MM>
	 */
	public List<StaffMembers> rgStaffNameRecordGroup(final BigDecimal progInstId) {
		final String sql = getQuery("OCDPATTE_FIND_RGSTAFFNAME");
		final RowMapper<StaffMembers> mMRowMapper = Row2BeanRowMapper.makeMapping(sql, StaffMembers.class, refMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams("progInstId", progInstId), mMRowMapper);
		} catch (final EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	@Override
	public List<VAcpSchedules> getcourseSchedule(final CourseSchedules courseSchedules) {
		final String sql = getQuery("OCDPATTE_GET_COURSE_SCHEDULE");
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
		final String strDate = formatter.format(courseSchedules.getScheduleDate());
		final MapSqlParameterSource params = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (courseSchedules != null) {
			sqlQuery.append(" WHERE");
			if (strDate != null) {
				sqlQuery.append(" schedule_date = TO_DATE(:p_schedule_date::text,'DD-MM-YYYY') AND");
				params.addValue("p_schedule_date", strDate);

			}
			if (courseSchedules.getProviderPartyClass() != null
					&& courseSchedules.getProviderPartyClass().equalsIgnoreCase("AGY")
					|| courseSchedules.getProviderPartyClass().equalsIgnoreCase("TEAM")) {

				sqlQuery.append("(:p_phase_provider_party_code IS NOT NULL AND");
				sqlQuery.append("  phase_provider_party_code =:p_phase_provider_party_code) AND");
				params.addValue("p_phase_provider_party_code", courseSchedules.getProviderDesc());
			}

			if (courseSchedules.getProviderPartyClass() != null
					&& courseSchedules.getProviderPartyClass().equalsIgnoreCase("CORP")) {
				sqlQuery.append(" (:p_phase_provider_party_id IS NOT NULL AND");
				sqlQuery.append("   phase_provider_party_id = :p_phase_provider_party_id) AND");
				params.addValue("p_phase_provider_party_id", new BigDecimal(courseSchedules.getProviderDesc()));
			}

			if (courseSchedules.getProgramId() != null) {
				sqlQuery.append(" program_id =(:program_id) AND  ");
				params.addValue("program_id", courseSchedules.getProgramId());
			}
			if (courseSchedules.getServiceId() != null) {
				sqlQuery.append(" program_code =(:p_service_id) ");
				params.addValue("p_service_id", courseSchedules.getServiceId());
			}
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("WHERE")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("AND")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		final RowMapper<VAcpSchedules> OffenderRowMapper = Row2BeanRowMapper.makeMapping(preparedSql,
				VAcpSchedules.class, courseMapping);
		List<VAcpSchedules> list = null;
		try {

			list = namedParameterJdbcTemplate.query(preparedSql, params, OffenderRowMapper);
		} catch (final Exception e) {
			logger.error("getOffenderDetails", e);
		}
		return list;
	}

	@Override
	public Date getReviewDate(final CourseSchedules searchRecord) {
		Date reviewDate = null;
		SqlParameter[] sqlParameters = new SqlParameter[2];
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		sqlParameters = new SqlParameter[] { new SqlParameter("p_crs_sch_id", OracleTypes.VARCHAR),

				new SqlOutParameter("PROVIDER_TYPE_CUR", OracleTypes.DATE) };
		final SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("OCDTMREV").withFunctionName("get_review_date").declareParameters(sqlParameters);
		inParamMap.put("p_crs_sch_id", searchRecord.getCrsSchId());
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
		} catch (final Exception e) {
			logger.error("getReviewDate", e.getMessage());
			reviewDate = null;
		}
		return reviewDate;
	}

	@Override
	public Map checkUa(final OffenderCourseAttendance searchBean) {
		Map<String, Object> returnObject = null;
		final Map<String, Object> outObj = new HashMap<>();
		SqlParameter[] sqlParameters = new SqlParameter[10];
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		Boolean lvOldUa = false;
		Boolean lvNewUa = false;
		Boolean vMultipleFailure = false;
		final String str = searchBean.getEventOutcome();
		final List<String> list = Arrays.asList(str.split(";"));
		final String oldOutcome = list.get(0);
		final String newOutcome = list.get(1);

		sqlParameters = new SqlParameter[] { new SqlParameter(PEVENTID, OracleTypes.NUMBER),
				new SqlParameter(PEVENTTYPE, OracleTypes.VARCHAR), new SqlParameter(PEVENTSUBTYPE, OracleTypes.VARCHAR),
				new SqlParameter(POLDOUTCOME, OracleTypes.VARCHAR), new SqlParameter(PNEWOUTCOME, OracleTypes.VARCHAR),

				new SqlOutParameter("p_old_ua", OracleTypes.BOOLEAN),
				new SqlOutParameter("p_new_ua", OracleTypes.BOOLEAN),
				new SqlOutParameter("p_multiple_failure", OracleTypes.BOOLEAN) };
		final SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("tag_multiple_failure").withProcedureName("check_ua").declareParameters(sqlParameters);
		inParamMap.put(PEVENTID, searchBean.getEventId());
		inParamMap.put(PEVENTTYPE, searchBean.getEventType());
		inParamMap.put(PEVENTSUBTYPE, searchBean.getEventSubType());
		inParamMap.put(POLDOUTCOME, oldOutcome);
		inParamMap.put(PNEWOUTCOME, newOutcome);
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			lvOldUa = (Boolean) returnObject.get("p_old_ua");
			lvNewUa = (Boolean) returnObject.get("p_new_ua");
			vMultipleFailure = (Boolean) returnObject.get("p_multiple_failure");

		} catch (final Exception e) {
			logger.error("getReviewDate", e.getMessage());
		}
		outObj.put("lvOldUa", lvOldUa);
		outObj.put("lvNewUa", lvNewUa);
		outObj.put("vMultipleFailure", vMultipleFailure);
		return outObj;
	}

	@Override
	public String getproviderType(final String caseloadId) {
		Map<String, Object> returnObject = null;
		String providerType = null;
		SqlParameter[] sqlParameters = new SqlParameter[2];
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		sqlParameters = new SqlParameter[] { new SqlParameter("P_AGY_LOC_ID", OracleTypes.VARCHAR),

				new SqlOutParameter("Return_Value", OracleTypes.VARCHAR) };
		final SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("ocdpatte").withFunctionName("GET_PROVIDER_TYPE").declareParameters(sqlParameters);
		inParamMap.put("P_AGY_LOC_ID", caseloadId);
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			providerType = (String) returnObject.get("Return_Value");

		} catch (final Exception e) {
			logger.error("getproviderType", e.getMessage());
		}

		return providerType;
	}

	@Override
	public OffenderCourseAttendance getActOutcomeFlag(final OffenderCourseAttendance searchBean) {

		final String sql = getQuery("OCDPATTE_GET_ACT_OUT_COME_FLAG");
		final RowMapper<OffenderCourseAttendance> OffenderRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderCourseAttendance.class, courseMapping);
		OffenderCourseAttendance attendence = new OffenderCourseAttendance();
		try {
			if (searchBean.getEventDate() != null) {
			}

			attendence = namedParameterJdbcTemplate
					.queryForObject(sql,
							createParams("outComeCode", searchBean.getEventOutcome(), "eventType",
									searchBean.getEventType(), "eventDate", searchBean.getEventDate()),
							OffenderRowMapper);
		} catch (final Exception e) {
			logger.error("getOffenderDetails", e.getMessage());
		}

		return attendence;
	}

	@Override
	public Offenders getOffenderDetails(final OffenderCourseAttendance offCourseAtt) {

		final String sql = getQuery("OCDPATTE_GET_OFFENDER_DETAILS");
		final RowMapper<Offenders> OffenderRowMapper = Row2BeanRowMapper.makeMapping(sql, Offenders.class,
				offenderMapping);
		Offenders offenders = new Offenders();
		try {
			offenders = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("offBookId", offCourseAtt.getOffenderBookId()), OffenderRowMapper);
		} catch (final Exception e) {
			logger.error("getOffenderDetails", e.getMessage());
		}

		return offenders;

	}

	@Override
	public String getPaylock(final long eventId) {
		Map<String, Object> returnObject = null;
		String paylock = null;
		SqlParameter[] sqlParameters = new SqlParameter[2];
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		sqlParameters = new SqlParameter[] { new SqlParameter(PEVENTID, OracleTypes.NUMERIC),

				new SqlOutParameter("TXN_ID_CUR", OracleTypes.VARCHAR) };
		final SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("OCDPATTE").withFunctionName("GET_PAY_LOCK").declareParameters(sqlParameters);
		inParamMap.put(PEVENTID, eventId);
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			paylock = (String) returnObject.get("TXN_ID_CUR");
		} catch (final Exception e) {
			logger.error("getPaylock", e.getMessage());
		}
		return paylock;
	}

	@Override
	public String adjustUa(final OffenderCourseAttendance offCourseAtt) {
		Map<String, Object> returnObject = null;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[6];

		sqlParameters = new SqlParameter[] { new SqlParameter(PEVENTID, OracleTypes.NUMBER),
				new SqlParameter(PEVENTTYPE, OracleTypes.VARCHAR), new SqlParameter(PEVENTSUBTYPE, OracleTypes.VARCHAR),
				new SqlParameter(POLDOUTCOME, OracleTypes.VARCHAR), new SqlParameter(PNEWOUTCOME, OracleTypes.VARCHAR),
				new SqlParameter("P_COUNT_FLAG", OracleTypes.VARCHAR), };

		final SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_MULTIPLE_FAILURE").withProcedureName("ADJUST_UA")
				.declareParameters(sqlParameters);

		inParamMap.put(PEVENTID, offCourseAtt.getEventId());
		inParamMap.put("P_EVENT_TYPE", offCourseAtt.getEventType());
		inParamMap.put("P_EVENT_SUB_TYPE", offCourseAtt.getEventSubType());
		inParamMap.put("P_OLD_OUTCOME", offCourseAtt.getEventOutcomeDbVal());
		inParamMap.put("P_NEW_OUTCOME", offCourseAtt.getEventOutcome());
		inParamMap.put("P_COUNT_FLAG", offCourseAtt.getUnexcusedAbsenceFlag());

		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			if (returnObject.size() == 0) {
			}
		} catch (final Exception e) {
		}
		return "1";
	}

	@Override
	public OffenderCourseAttendance getoffcourseattDetails(final OffenderCourseAttendance offCourseAtt) {
		return null;
	}

	@Override
	public String checkUaCheckUaEventOutcome(final OffenderCourseAttendance searchBean) {
		final String sql = getQuery("OCDPATTE_CHECK_UA_EVENT_OUTCOME");
		try {
			return namedParameterJdbcTemplate
					.queryForObject(sql,
							createParams("pEventType", searchBean.getEventType(), "pEventSubType",
									searchBean.getEventSubType(), "pEventOutcome", searchBean.getEventOutcome()),
							String.class);
		} catch (final Exception e) {
			return null;
		}
	}

	@Override
	public Integer isStaffExists(final CourseSchedules searchBean) {
		final String sql = getQuery("OCDPATTE_IS_STAFF_EXISTS");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql,
					createParams("progInsId", searchBean.getProgramInstanceId()), Integer.class);
		} catch (final Exception e) {
			return null;
		}
	}

	@Override
	public CourseActivities getcrsactivityDetails(final CourseSchedules courseSchedules) {
		final String sql = getQuery("OCDPATTE_GET_CRS_ACTIVITY_DETAILS");
		final RowMapper<CourseActivities> OffenderRowMapper = Row2BeanRowMapper.makeMapping(sql, CourseActivities.class,
				offenderMapping);
		CourseActivities courseactivities = new CourseActivities();
		try {
			courseactivities = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("progInsId", courseSchedules.getProgramInstanceId()), OffenderRowMapper);
		} catch (final Exception e) {
			logger.error("getOffenderDetails", e.getMessage());
		}
		return courseactivities;
	}

	@Override
	public VAddresses getprogLoc(final Long servicesAddressId) {
		final String sql = getQuery("OCDPATTE_GET_PROG_LOC");
		final RowMapper<VAddresses> OffenderRowMapper = Row2BeanRowMapper.makeMapping(sql, VAddresses.class,
				offenderMapping);
		VAddresses vAddresses = new VAddresses();
		try {
			vAddresses = namedParameterJdbcTemplate.queryForObject(sql, createParams("addId", servicesAddressId),
					OffenderRowMapper);
		} catch (final Exception e) {
			logger.error("getOffenderDetails", e.getMessage());
		}
		return vAddresses;
	}

	@Override
	public String getInternalLocationDescription(final Long intLocId) {
		final String sql = getQuery("OCDPATTE_INT_LOC_DESCRIPTION");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("intLocationId", intLocId),
					String.class);
		} catch (final Exception e) {
			return null;
		}
	}

	@Override
	public VOffenderSentenceEvents getOffenderSentenceEvents(OffenderCourseAttendance searchBean) {
		final String sql = getQuery("OCDPATTE_GET_OFFENDER_SENTENCE_EVENTS");
		final RowMapper<VOffenderSentenceEvents> OffenderRowMapper = Row2BeanRowMapper.makeMapping(sql,
				VOffenderSentenceEvents.class, offenderMapping);
		VOffenderSentenceEvents vAddresses = new VOffenderSentenceEvents();
		try {
			vAddresses = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("eventId", searchBean.getEventId()), OffenderRowMapper);
		} catch (final Exception e) {
			logger.error("getOffenderDetails", e.getMessage());
		}
		return vAddresses;

	}

	@Override
	public Integer getOffenderSentenceUa(VOffenderSentenceEvents events) {
		final String sql = getQuery("OCDPATTE_GET_OFFENDER_SENTENCEUA");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql,
					createParams("offBookId", events.getOffenderBookId(), "sentSeq", events.getSentenceSeq(),
							"eventDate", events.getEventDate(), "eventId", events.getEventId()),
					Integer.class);
		} catch (final Exception e) {
			logger.error("getOffenderSentenceUa", e.getMessage());
			return null;
		}
	}

	public List<AgencyLocations> rgAgyLocsRecordGroup(String caseLoadId) {
		final String sql = getQuery("OCDPATTE_FIND_RGAGYLOCS");
		final RowMapper<AgencyLocations> mRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				mMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("caseLoadId", caseLoadId), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	public List<TeamMembers> rgTeamAgyLocsRecordGroup(String caseLoadId) {
		final String sql = getQuery("OCDPATTE_FIND_RGTEAMAGYLOCS");
		final RowMapper<TeamMembers> mMTeamMembersRowMapper = Row2BeanRowMapper.makeMapping(sql, TeamMembers.class,
				mmteamMembersMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("caseLoadId", caseLoadId),
					mMTeamMembersRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	public List<TeamMembers> rgCorpLocsRecordGroup() {
		final String sql = getQuery("OCDPATTE_FIND_RGCORPLOCS");
		final RowMapper<TeamMembers> mMTeamMembersMRowMapper = Row2BeanRowMapper.makeMapping(sql, TeamMembers.class,
				mmteamMembersMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mMTeamMembersMRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	@Override
	public List<EventMeasureOutcomes> cancelFlagOutcomeList(EventMeasures eventMeasureObj) {
		final String sql = getQuery("OCDPATTE_FIND_OUTCOME_CANCELFLAG");
		final StringBuffer pSql = new StringBuffer(sql);
		String preparedSql = "";
		final MapSqlParameterSource param = new MapSqlParameterSource();
		try {
			if (eventMeasureObj != null) {
				String subQuery = "";
				final StringBuffer subQueryBuilder = new StringBuffer();
				subQueryBuilder.append("select event_measure_id	from event_measures em  where ");
				if (eventMeasureObj.getEventType() != null) {
					subQueryBuilder.append(" em.event_type = :eventType and");
					param.addValue("eventType", eventMeasureObj.getEventType());
				}
				if (eventMeasureObj.getEventSubType() != null) {
					subQueryBuilder.append(" em.event_sub_type = :eventSubType");
					param.addValue("eventSubType", eventMeasureObj.getEventSubType());
				}
				subQuery = subQueryBuilder.toString().trim();
				if (subQuery.endsWith("and")) {
					subQuery = subQuery.substring(0,subQuery.length() - 3);
				}
				pSql.append(" AND emo.event_measure_id in ( ");
				pSql.append(subQuery);
				pSql.append(" )");
			}
			preparedSql = pSql.toString().trim();
			return namedParameterJdbcTemplate.query(preparedSql, param,new RowMapperResultSetExtractor<EventMeasureOutcomes>(new BeanPropertyRowMapper<EventMeasureOutcomes>(EventMeasureOutcomes.class)));
		} catch (Exception e) {
			logger.info("Exception in cancelFlagOutcomeList ", e.getMessage());
			return Collections.emptyList();
		}

	}
}
