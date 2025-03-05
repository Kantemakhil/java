package net.syscon.s4.cm.programsservices.impl;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
import net.syscon.s4.cm.programsservices.OcduprojRepository;
import net.syscon.s4.cm.programsservices.OffenderUnpaidWorkAdj;
import net.syscon.s4.cm.programsservices.VOffenderSentCondActs;
import net.syscon.s4.cm.programsservices.maintenance.OffenderCourseSkills;
import net.syscon.s4.cm.programsservices.maintenance.impl.OcmctoffRepositoryImpl;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SentenceCalcTypes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.im.beans.OffenderNonAssociations;
import net.syscon.s4.im.beans.OffenderProgramProfiles;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.VOffenderCourseEvents;
import oracle.jdbc.internal.OracleTypes;

/**
 * Class OcduprojRepositoryImpl
 */
@Repository
public class OcduprojRepositoryImpl extends RepositoryBase implements OcduprojRepository {

	/**
	 * Creates new OcduprojRepositoryImpl class Object
	 */
	private static Logger logger = LogManager.getLogger(OcmctoffRepositoryImpl.class);

	public OcduprojRepositoryImpl() {
	}

	/**
	 * Logger object used to print the log in the file
	 */

	private final Map<String, FieldMapper> offunPaidWorkAdj = new ImmutableMap.Builder<String, FieldMapper>()
			.put("SENTENCE_SEQ", new FieldMapper("sentenceSeq"))
			.put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId"))
			.put("ADJUSTMENT_DATE", new FieldMapper("adjustmentDate"))
			.put("ADJUSTMENT_MINUTES", new FieldMapper("adjustmentMinutes"))
			.put("ADJUSTMENT_TYPE", new FieldMapper("adjustmentType"))
			.put("COMMENT_TEXT", new FieldMapper("commentText"))
			.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("OFFENDER_UNPAID_WORK_ADJ_ID", new FieldMapper("offenderUnpaidWorkAdjId"))
			.put("REASON_CODE", new FieldMapper("reasonCode")).put("SEAL_FLAG", new FieldMapper("sealFlag")).build();

	private final Map<String, FieldMapper> offProProfMapp = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_END_DATE", new FieldMapper("offenderEndDate"))
			.put("SENTENCE_SEQ", new FieldMapper("sentenceSeq")).put("CRS_ACTY_ID", new FieldMapper("crsActyId"))
			.put("MIN(OFFENDER_START_DATE)", new FieldMapper(" min(offenderStartDate) "))
			.put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId")).put("firstName", new FieldMapper("FIRST_NAME"))
			.put("lastName", new FieldMapper("LAST_NAME"))
			.put("offenderEndCommentText", new FieldMapper("COMMENT_TEXT"))
			.put("AGREED_TRAVEL_FARE", new FieldMapper("agreedTravelFare1")).build();

	private final Map<String, FieldMapper> vOffCourEvtMapp = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CRS_ACTY_ID", new FieldMapper("crsActyId")).put("ACTION_CODE", new FieldMapper("actionCode"))
			.put("AGREED_TRAVEL_HOUR", new FieldMapper("agreedTravelHour"))
			.put("AGY_LOC_ID", new FieldMapper("agyLocId")).put("BEHAVIOUR_CODE", new FieldMapper("behaviourCode"))
			.put("CHECK_SUM", new FieldMapper("checkSum")).put("COMMENT_TEXT", new FieldMapper("commentText"))
			.put("COURSE_CODE", new FieldMapper("courseCode")).put("CREDITED_HOURS", new FieldMapper("creditedHours"))
			.put("CRS_APPT_ID", new FieldMapper("crsApptId")).put("CRS_SCH_ID", new FieldMapper("crsSchId"))
			.put("DESCRIPTION", new FieldMapper("description")).put("DIRECTION_CODE", new FieldMapper("directionCode"))
			.put("END_TIME", new FieldMapper("endTime")).put("ENGAGEMENT_CODE", new FieldMapper("engagementCode"))
			.put("EVENT_CLASS", new FieldMapper("eventClass")).put("EVENT_DATE", new FieldMapper("eventDate"))
			.put("EVENT_ID", new FieldMapper("eventId")).put("EVENT_OUTCOME", new FieldMapper("eventOutcome"))
			.put("EVENT_OUTCOME_DESC", new FieldMapper("eventOutcomeDesc"))
			.put("EVENT_STATUS", new FieldMapper("eventStatus")).put("EVENT_SUB_TYPE", new FieldMapper("eventSubType"))
			.put("EVENT_TYPE", new FieldMapper("eventType")).put("EXT_MOVE_IN_TIME", new FieldMapper("extMoveInTime"))
			.put("EXT_MOVE_OUT_TIME", new FieldMapper("extMoveOutTime")).put("IN_TIME", new FieldMapper("inTime"))
			.put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId"))
			.put("OFF_PRGREF_ID", new FieldMapper("offPrgrefId"))
			.put("OUTCOME_REASON_CODE", new FieldMapper("outcomeReasonCode"))
			.put("OUT_TIME", new FieldMapper("outTime")).put("PERFORMANCE_CODE", new FieldMapper("performanceCode"))
			.put("PERFORMANCE_DESC", new FieldMapper("performanceDesc")).put("PIECE_WORK", new FieldMapper("pieceWork"))
			.put("PROGRAM_ID", new FieldMapper("programId")).put("RECORD_SOURCE", new FieldMapper("recordSource"))
			.put("REFERENCE_ID", new FieldMapper("referenceId"))
			.put("SCHEDULE_MOVEMENT_TIME", new FieldMapper("scheduleMovementTime"))
			.put("SESSION_NO", new FieldMapper("sessionNo"))
			.put("SICK_NOTE_EXPIRY_DATE", new FieldMapper("sickNoteExpiryDate"))
			.put("SICK_NOTE_RECEIVED_DATE", new FieldMapper("sickNoteReceivedDate"))
			.put("START_TIME", new FieldMapper("startTime"))
			.put("SUPERVISOR_STAFF_ID", new FieldMapper("supervisorStaffId"))
			.put("TO_ADDRESS_ID", new FieldMapper("toAddressId")).put("TO_AGY_LOC_ID", new FieldMapper("toAgyLocId"))
			.put("TO_INTERNAL_LOCATION_ID", new FieldMapper("toInternalLocationId"))
			.put("UNDERSTANDING_CODE", new FieldMapper("understandingCode"))
			.put("UNEXCUSED_ABSENCE_FLAG", new FieldMapper("unexcusedAbsenceFlag"))
			.put("WEEKDAY", new FieldMapper("weekday")).build();

	private final Map<String, FieldMapper> vOffSntConMapp = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId"))
			.put("ACTIVITY_CODE", new FieldMapper("activityCode")).put("ACTIVITY_DESC", new FieldMapper("activityDesc"))
			.put("CASE_INFO_NUMBER", new FieldMapper("caseInfoNumber")).put("CHECK_SUM", new FieldMapper("checkSum"))
			.put("COMM_CONDITION_CODE", new FieldMapper("commConditionCode"))
			.put("COMM_CONDITION_TYPE", new FieldMapper("commConditionType"))
			.put("COMM_PROGRAM_METHOD", new FieldMapper("commProgramMethod"))
			.put("CONDITION_DESC", new FieldMapper("conditionDesc"))
			.put("CONDITION_END_DATE", new FieldMapper("conditionEndDate"))
			.put("CONDITION_LENGTH", new FieldMapper("conditionLength"))
			.put("CONDITION_START_DATE", new FieldMapper("conditionStartDate"))
			.put("CONDITION_STATUS", new FieldMapper("conditionStatus"))
			.put("COURT_AGY_LOC_ID", new FieldMapper("courtAgyLocId")).put("COURT_NAME", new FieldMapper("courtName"))
			.put("CREDITED_UNITS", new FieldMapper("creditedUnits")).put("EVENT_ID", new FieldMapper("eventId"))
			.put("LENGTH", new FieldMapper("length")).put("LENGTH_UNIT", new FieldMapper("lengthUnit"))
			.put("OFFENDER_SENT_CONDITION_ID", new FieldMapper("offenderSentConditionId"))
			.put("ORDER_ID", new FieldMapper("orderId"))
			.put("PROGRAM_ACTIVITY_STATUS", new FieldMapper("programActivityStatus"))
			.put("PROGRAM_CATEGORY", new FieldMapper("programCategory")).put("PROGRAM_ID", new FieldMapper("programId"))
			.put("RECORD_SOURCE", new FieldMapper("recordSource"))
			.put("SENTENCE_CALC_TYPE", new FieldMapper("sentenceCalcType"))
			.put("SENTENCE_CATEGORY", new FieldMapper("sentenceCategory"))
			.put("SENTENCE_DESC", new FieldMapper("sentenceDesc"))
			.put("SENTENCE_END_DATE", new FieldMapper("sentenceEndDate"))
			.put("SENTENCE_PROGRAM_METHOD", new FieldMapper("sentenceProgramMethod"))
			.put("SENTENCE_SEQ", new FieldMapper("sentenceSeq"))
			.put("SENTENCE_START_DATE", new FieldMapper("sentenceStartDate"))
			.put("SENTENCE_STATUS", new FieldMapper("sentenceStatus"))
			.put("SENTENCE_STATUS_DESC", new FieldMapper("sentenceStatusDesc"))
			.put("SENT_COND_DESC", new FieldMapper("sentCondDesc")).build();

	private final Map<String, FieldMapper> staffMemMapp = new ImmutableMap.Builder<String, FieldMapper>()
			.put("STAFF_ID", new FieldMapper("staffId")).put("LAST_NAME", new FieldMapper("lastName"))
			.put("FIRST_NAME", new FieldMapper("firstName")).put("CODE", new FieldMapper("code"))
			.put("DESCRIPTION", new FieldMapper("description")).build();

	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CODE", new FieldMapper("code")).put("DESCRIPTION", new FieldMapper("description"))
			.put("SENTENCE_CATEGORY", new FieldMapper("sentenceCategory"))
			.put("sentence_calc_type", new FieldMapper("sentenceCalcType")).build();

	private final Map<String, FieldMapper> projCodeMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CODE", new FieldMapper("code")).put("CRS_ACTY_ID", new FieldMapper("crsActyId")).build();

	private final Map<String, FieldMapper> offCouSkillMapp = new ImmutableMap.Builder<String, FieldMapper>()
			.put("EVENT_ID", new FieldMapper("eventId")).put("COMMENT_TEXT", new FieldMapper("commentText"))
			.put("CREATE_DATETIME", new FieldMapper("createDatetime")).put("ROW_ID", new FieldMapper("rowIdOne"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId")).put("NO_OF_HOURS", new FieldMapper("noOfHours"))
			.put("SEAL_FLAG", new FieldMapper("sealFlag")).put("SKILL_CODE", new FieldMapper("skillCode"))
			.put("STAFF_ID", new FieldMapper("staffId")).put("STAFF_ROLE", new FieldMapper("staffRole")).build();

	private final Map<String, FieldMapper> courseEventMap = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CODE", new FieldMapper("projectCode")).put("CRS_ACTY_ID", new FieldMapper("crsActyId"))
			.put("DESCRIPTION", new FieldMapper("projectDescription"))
			.put("TEAM_DECSRIPTION", new FieldMapper("teamDescription")).put("CAPACITY", new FieldMapper("maxCapacity"))
			.put("PROGRAM_ID", new FieldMapper("pProgramId"))
			.put("SCHEDULE_START_DATE", new FieldMapper("scheduleStartDate"))
			.put("SCHEDULE_END_DATE", new FieldMapper("scheduleEndDate")).build();

	private final Map<String, FieldMapper> vAcpProgressMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.build();

	private final Map<String, FieldMapper> sentenceMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("ID", new FieldMapper("Id")).put("FORM_INFO_JSON", new FieldMapper("formInfoJson"))
			.put("FORM_IDENTIFIER", new FieldMapper("formIdentifier"))
			.put("OFFENDER_ID_DISPLAY", new FieldMapper("offenderIdDisplay"))
			.put("LAST_NAME", new FieldMapper("lastName")).put("FIRST_NAME", new FieldMapper("firstName")).build();
	
	public final Map<String, FieldMapper> referenceCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("CODE", new FieldMapper("code"))
			.put("PARENT_CODE", new FieldMapper("parentCode"))
			.put("PARENT_DOMAIN", new FieldMapper("parentDomain"))
			.put("DOMAIN", new FieldMapper("domain"))
			.put("LIST_SEQ", new FieldMapper("listSeq"))
			.build();

	/**
	 * Fetch the records from database table
	 *
	 */
	public List<VOffenderSentCondActs> unpaidWkExecuteQuery(final VOffenderSentCondActs objSearchDao) {
		final String sql = getQuery("OCDUPROJ_UNPAIDWK_FIND_V_OFFENDER_SENT_COND_ACTS");
		final RowMapper<VOffenderSentCondActs> VOffenderSentCondActsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				VOffenderSentCondActs.class, vOffSntConMapp);
		final ArrayList<VOffenderSentCondActs> returnList = (ArrayList<VOffenderSentCondActs>) namedParameterJdbcTemplate
				.query(sql, createParams("offenderBookId", objSearchDao.getOffenderBookId()),
						VOffenderSentCondActsRowMapper);
		returnList.stream().forEach(i -> {
			if (i.getOffenderSentConditionId() != null) {
				if (Optional.ofNullable(i.getConditionStatus()).isPresent() && i.getConditionStatus().equals("A")) {
					i.setSentenceStatusDesc("ACTIVE");
				} else {
					i.setSentenceStatusDesc("INACTIVE");
				}
			} else if (i.getSentenceStatus().equals("A")) {
				i.setSentenceStatusDesc("ACTIVE");
			} else {
				i.setSentenceStatusDesc("INACTIVE");
			}
			if (i.getLength() == null || i.getLength().compareTo(BigDecimal.ZERO) == 0) {
				BigDecimal length = getLength(i.getOffenderBookId(), i.getSentenceSeq());
				if (Optional.ofNullable(length).isPresent()) {
					i.setLength(length);
				}
			}
			i.setRemaining(i.getLength().subtract(i.getCreditedUnits()));
		});

		return returnList;
	}

	public BigDecimal getLength(final BigDecimal offenderBookId, final BigDecimal sentenceSeq) {
		Map<String, Object> returnObject = null;
		BigDecimal length = null;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[3];

		sqlParameters = new SqlParameter[] { new SqlParameter("P_OFFENDER_BOOK_ID", Types.NUMERIC),
				new SqlParameter("P_SENTENCE_SEQ", Types.NUMERIC), new SqlOutParameter("v_Return", Types.NUMERIC) };

		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_UNPAID_WORK").withFunctionName("GET_OFF_SENT_TERM_HRS")
				.declareParameters(sqlParameters);
		inParamMap.put("p_offender_book_id", offenderBookId);
		inParamMap.put("p_sentence_seq", sentenceSeq);
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			length = (BigDecimal) returnObject.get("v_Return");
		} catch (Exception e) {
			logger.error("Exception :", e);
			length = null;
		}
		return length;
	}

	/**
	 * Fetch the records from database table
	 *
	 */
	public List<OffenderProgramProfiles> projAllocExecuteQuery(final OffenderProgramProfiles objSearchDao) {
		final String sql = getQuery("OCDUPROJ_PROJALLOC_FIND_OFFENDER_PROGRAM_PROFILES");
		final RowMapper<OffenderProgramProfiles> OffenderProgramProfilesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderProgramProfiles.class, offProProfMapp);
		final List<OffenderProgramProfiles> returnList = namedParameterJdbcTemplate.query(sql,
				createParams("offenderBookId", objSearchDao.getOffenderBookId(), "unpaid_wk_sentence_seq",
						objSearchDao.getSentenceSeq(), "offenderSentConditionId",
						objSearchDao.getOffenderSentConditionId()),
				OffenderProgramProfilesRowMapper);
		if (returnList.size() > 0) {
			returnList.forEach(i -> {
				i = getOffenderProgramProfiles(i);
				CourseActivities courseActivities = getProjectCode(i.getProjectCode(), i.getCrsActyId());
				if (courseActivities != null && Optional.ofNullable(courseActivities).isPresent()
						&& Optional.ofNullable(courseActivities.getCode()).isPresent()) {
					i.setCode(courseActivities.getCode());
				}
			});
		}

		return returnList;
	}

	public Integer projAllocOnDeleteQuery(final OffenderProgramProfiles searchBean) {
		final String sql = getQuery("OCDUPROJ_PROJ_ALLOCONDELETE_QUERY");
		int count = 0;
		try {
			count = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("OFFPRGREFID", searchBean.getOffPrgrefId(), "CRSACTYID", searchBean.getCrsActyId()),
					Integer.class);
			if (count >= 1) {
				return 1;
			} else {
				return 0;
			}
		} catch (EmptyResultDataAccessException e) {
			logger.error("Exception :", e);
			return 0;
		}
	}

	public boolean lvProfileValue() {
		final String sql = getQuery("LV_PROFILE_VALUE");
		String result = null;
		result = namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);
		if (result.equals("Y")) {
			return true;
		} else
			return false;
	}

	public CourseActivities getProjectCode(final String code, final Long crsActyId) {
		final String sql = getQuery("OCDUPROJ_FIND_RGPROJECTCHECK");
		final RowMapper<CourseActivities> mRowMapper = Row2BeanRowMapper.makeMapping(sql, CourseActivities.class,
				projCodeMapping);
		try {
			CourseActivities courseactiv = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("code", code, "crsActyId", crsActyId), mRowMapper);
			return courseactiv;
		} catch (EmptyResultDataAccessException e) {
			logger.error("Exception :", e);
			return null;

		}

	}

	public OffenderProgramProfiles getOffenderProgramProfiles(final OffenderProgramProfiles objSearchDao) {

		final String sql = getQuery("GET_PROJECT_ALLOC_DETAIL");
		OffenderProgramProfiles programData = null;
		final RowMapper<OffenderProgramProfiles> mRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderProgramProfiles.class, courseEventMap);
		try {
			programData = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("crsActyId", objSearchDao.getCrsActyId()), mRowMapper);
			if (programData != null) {
				objSearchDao.setProjectCode(programData.getProjectCode());
				objSearchDao.setProjectDescription(programData.getProjectDescription());
				objSearchDao.setMaxCapacity(programData.getMaxCapacity());
				if (programData.getProgramId() != null) {
					objSearchDao.setpProgramId(new BigDecimal(programData.getProgramId()));
				}

				objSearchDao.setScheduleStartDate(programData.getScheduleStartDate());
				objSearchDao.setScheduleEndDate(programData.getScheduleEndDate());
				objSearchDao.setTeamDescription(programData.getTeamDescription());

			}

		} catch (EmptyResultDataAccessException e) {
			logger.error("Exception in getOffenderProgramProfiles :", e);

		}
		return programData;

	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 */
	public Integer projAllocInsertOffenderProgramProfiles(
			final List<OffenderProgramProfiles> lstOffenderProgramProfiles) {
		final String sql = getQuery("OCDUPROJ_PROJALLOC_INSERT_OFFENDER_PROGRAM_PROFILES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderProgramProfiles cases : lstOffenderProgramProfiles) {
			cases.setOffPrgrefId((getOffPrgrefIdCur()).longValue());
			parameters.add(new BeanPropertySqlParameterSource(cases));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenderProgramProfiles.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 */
	public Integer projAllocUpdateOffenderProgramProfiles(
			final List<OffenderProgramProfiles> lstOffenderProgramProfiles) {
		final String sql = getQuery("OCDUPROJ_PROJALLOC_UPDATE_OFFENDER_PROGRAM_PROFILES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (OffenderProgramProfiles offenderProgramProfiles : lstOffenderProgramProfiles) {
			parameters.add(new BeanPropertySqlParameterSource(offenderProgramProfiles));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenderProgramProfiles.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 */
	public Integer projAllocDeleteOffenderProgramProfiles(
			final List<OffenderProgramProfiles> lstOffenderProgramProfiles) {
		final String sql = getQuery("OCDUPROJ_PROJALLOC_DELETE_OFFENDER_PROGRAM_PROFILES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (OffenderProgramProfiles offenderProgramProfiles : lstOffenderProgramProfiles) {
			parameters.add(new BeanPropertySqlParameterSource(offenderProgramProfiles));
		}
		try {
			String tableName = "OFFENDER_PROGRAM_PROFILES";
			String whereClause = "OFFENDER_BOOK_ID= :offenderBookId  AND  OFF_PRGREF_ID=:offPrgrefId";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method projAllocDeleteOffenderProgramProfiles", e);
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("Exception :", e);
			return 3;
		}
		if (lstOffenderProgramProfiles.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 */
	public List<VOffenderCourseEvents> attendanceExecuteQuery(final VOffenderCourseEvents objSearchDao) {
		final String sql = getQuery("OCDUPROJ_ATTENDANCE_FIND_V_OFFENDER_COURSE_EVENTS");
		final StringBuffer pSql = new StringBuffer(sql);
		String preparedSql = "";
		if (objSearchDao != null) {
			if (objSearchDao.getEventOutcomeDbVal() != null && !objSearchDao.getEventOutcomeDbVal().isEmpty()) {
				pSql.append(" AND ");
				if (objSearchDao.getEventOutcomeDbVal().equals("2")) {
					pSql.append("EVENT_STATUS IN ('SCH','EXP')");
				} else {
					pSql.append("EVENT_STATUS IN ('SCH','EXP','COMP','CANC')");
				}
			}
		}
		preparedSql = pSql.toString().trim();
		preparedSql = preparedSql.concat(" ORDER BY EVENT_DATE ASC ");
		final RowMapper<VOffenderCourseEvents> VOffenderCourseEventsRowMapper = Row2BeanRowMapper
				.makeMapping(preparedSql, VOffenderCourseEvents.class, vOffCourEvtMapp);
		final List<VOffenderCourseEvents> returnList = namedParameterJdbcTemplate.query(preparedSql,
				createParams("offenderBookId", objSearchDao.getOffenderBookId(), "offPrgrefId",
						objSearchDao.getOffPrgrefId()),
				VOffenderCourseEventsRowMapper);
		return returnList;
	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 */
	public Integer attendanceUpdateVOffenderCourseEvents(final List<VOffenderCourseEvents> lstVOffenderCourseEvents) {
		final String sql = getQuery("OCDUPROJ_ATTENDANCE_UPDATE_V_OFFENDER_COURSE_EVENTS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (VOffenderCourseEvents vOffenderCourseEvents : lstVOffenderCourseEvents) {
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
	 */
	public List<OffenderCourseSkills> skillsExecuteQuery(final OffenderCourseSkills objSearchDao) {
		final String sql = getQuery("OCDUPROJ_SKILLS_FIND_OFFENDER_COURSE_SKILLS");
		final RowMapper<OffenderCourseSkills> OffenderCourseSkillsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderCourseSkills.class, offCouSkillMapp);
		final List<OffenderCourseSkills> returnList = namedParameterJdbcTemplate.query(sql,
				createParams("eventId", objSearchDao.getEventId()), OffenderCourseSkillsRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 */
	public Integer skillsInsertOffenderCourseSkills(final List<OffenderCourseSkills> lstOffenderCourseSkills) {
		final String sql = getQuery("OCDUPROJ_SKILLS_INSERT_OFFENDER_COURSE_SKILLS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (OffenderCourseSkills offenderCourseSkills : lstOffenderCourseSkills) {
			offenderCourseSkills.setStaffRole("TUTOR");
			parameters.add(new BeanPropertySqlParameterSource(offenderCourseSkills));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("Exception :", e);
			return 2;
		}
		if (lstOffenderCourseSkills.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 */
	public Integer skillsUpdateOffenderCourseSkills(final List<OffenderCourseSkills> lstOffenderCourseSkills) {
		final String sql = getQuery("OCDUPROJ_SKILLS_UPDATE_OFFENDER_COURSE_SKILLS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (OffenderCourseSkills offenderCourseSkills : lstOffenderCourseSkills) {
			offenderCourseSkills.setStaffRole("TUTOR");
			parameters.add(new BeanPropertySqlParameterSource(offenderCourseSkills));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("Exception :", e);
			return 3;
		}
		if (lstOffenderCourseSkills.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 */
	public Integer skillsDeleteOffenderCourseSkills(final List<OffenderCourseSkills> lstOffenderCourseSkills) {
		final String sql = getQuery("OCDUPROJ_SKILLS_DELETE_OFFENDER_COURSE_SKILLS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (OffenderCourseSkills offenderCourseSkills : lstOffenderCourseSkills) {
			parameters.add(new BeanPropertySqlParameterSource(offenderCourseSkills));
		}
		try {
			String tableName = "OFFENDER_COURSE_SKILLS";
			String whereClause = "EVENT_ID=:eventId and STAFF_ID=:staffId and SKILL_CODE=:skillCode";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method skillsDeleteOffenderCourseSkills", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenderCourseSkills.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * Fetch the records from database table
	 */
	public List<OffenderUnpaidWorkAdj> creditAdjExecuteQuery(final OffenderUnpaidWorkAdj objSearchDao) {
		final String sql = getQuery("OCDUPROJ_CREDITADJ_FIND_OFFENDER_UNPAID_WORK_ADJ");
		final RowMapper<OffenderUnpaidWorkAdj> OffenderUnpaidWorkAdjRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderUnpaidWorkAdj.class, offunPaidWorkAdj);
		final List<OffenderUnpaidWorkAdj> returnList = namedParameterJdbcTemplate.query(sql,
				createParams("offenderBookId", objSearchDao.getOffenderBookId(), "sentenceSeq",
						objSearchDao.getSentenceSeq(), "offenderSentConditionId",
						objSearchDao.getOffenderSentConditionId()),
				OffenderUnpaidWorkAdjRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 */
	public Integer creditAdjInsertOffenderUnpaidWorkAdj(final List<OffenderUnpaidWorkAdj> lstOffenderUnpaidWorkAdj) {
		final String sql = getQuery("OCDUPROJ_CREDITADJ_INSERT_OFFENDER_UNPAID_WORK_ADJ");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderUnpaidWorkAdj cases : lstOffenderUnpaidWorkAdj) {
			cases.setOffenderUnpaidWorkAdjId(offenderUnpaidWorkAdjId());
			parameters.add(new BeanPropertySqlParameterSource(cases));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenderUnpaidWorkAdj.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Used to capture results from select query
	 * 
	 */
	public List<ReferenceCodes> rgAttendanceRecordGroup() {
		final String sql = getQuery("OCDUPROJ_FIND_RGATTENDANCE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("Exception :", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 */
	public List<ReferenceCodes> rgSupervisorRecordGroup(final Long crsActyId) {
		final String sql = getQuery("OCDUPROJ_FIND_RGSUPERVISOR");
		final RowMapper<ReferenceCodes> mMStaffMembersRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, staffMemMapp);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("crsActyId", crsActyId), mMStaffMembersRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("Exception :", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 */
	public List<ReferenceCodes> rgBehaviourRecordGroup() {
		final String sql = getQuery("OCDUPROJ_FIND_RGBEHAVIOUR");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("Exception :", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 */
	public List<ReferenceCodes> rgWorkQualityRecordGroup() {
		final String sql = getQuery("OCDUPROJ_FIND_RGWORKQUALITY");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("Exception :", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 */
	public List<CourseActivities> rgProjectCheckRecordGroup() {
		final String sql = getQuery("OCDUPROJ_FIND_RGPROJECTCHECK");
		final RowMapper<CourseActivities> mRowMapper = Row2BeanRowMapper.makeMapping(sql, CourseActivities.class,
				mMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("Exception :", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 */
	public List<CourseActivities> rgProjectRecordGroup(final Long offenderBookId) {
		final String sql = getQuery("OCDUPROJ_FIND_RGPROJECT");
		final RowMapper<CourseActivities> mMRowMapper = Row2BeanRowMapper.makeMapping(sql, CourseActivities.class,
				mMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("OFFENDERBOOKID", offenderBookId), mMRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("Exception :", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 */
	public List<ReferenceCodes> rgSkillsRecordGroup() {
		final String sql = getQuery("OCDUPROJ_FIND_RGSKILLS");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("Exception :", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 */
	public List<StaffMembers> rgStaffCheckRecordGroup() {
		final String sql = getQuery("OCDUPROJ_FIND_RGSTAFFCHECK");
		final RowMapper<StaffMembers> mMRowMapper = Row2BeanRowMapper.makeMapping(sql, StaffMembers.class, mMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mMRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("Exception :", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 */
	public List<ReferenceCodes> rgAdjReasonRecordGroup() {
		final String sql = getQuery("OCDUPROJ_FIND_RGADJREASON");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("Exception :", e);
			return Collections.emptyList();
		}
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 *
	 */
	public List<VOffenderSentCondActs> offBkgOnCheckDeleteMaster(final VOffenderSentCondActs paramBean) {
		final String sql = getQuery("OCDUPROJ_OFF_BKG_ONCHECKDELETEMASTER");
		final RowMapper<VOffenderSentCondActs> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				VOffenderSentCondActs.class, vOffSntConMapp);
		final List<VOffenderSentCondActs> returnList = namedParameterJdbcTemplate.query(sql, createParams(),
				columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 *
	 */
	public List<OffenderProgramProfiles> unpaidWkOnCheckDeleteMaster(final OffenderProgramProfiles paramBean) {
		final String sql = getQuery("OCDUPROJ_UNPAID_WK_ONCHECKDELETEMASTER");
		final RowMapper<OffenderProgramProfiles> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderProgramProfiles.class, offProProfMapp);
		final List<OffenderProgramProfiles> returnList = namedParameterJdbcTemplate.query(sql, createParams(),
				columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 *
	 */
	public List<OffenderUnpaidWorkAdj> unpaidWkOnCheckDeleteMaster(final OffenderUnpaidWorkAdj paramBean) {
		final String sql = getQuery("OCDUPROJ_UNPAID_WK_ONCHECKDELETEMASTER");
		final RowMapper<OffenderUnpaidWorkAdj> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderUnpaidWorkAdj.class, offunPaidWorkAdj);
		final List<OffenderUnpaidWorkAdj> returnList = namedParameterJdbcTemplate.query(sql, createParams(),
				columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 *
	 */
	public List<VOffenderCourseEvents> projAllocOnCheckDeleteMaster(final VOffenderCourseEvents paramBean) {
		final String sql = getQuery("OCDUPROJ_PROJ_ALLOC_ONCHECKDELETEMASTER");
		final RowMapper<VOffenderCourseEvents> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				VOffenderCourseEvents.class, vOffCourEvtMapp);
		final List<VOffenderCourseEvents> returnList = namedParameterJdbcTemplate.query(sql, createParams(),
				columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 *
	 */
	public List<OffenderCourseSkills> attendanceOnCheckDeleteMaster(final OffenderCourseSkills paramBean) {
		final String sql = getQuery("OCDUPROJ_ATTENDANCE_ONCHECKDELETEMASTER");
		final RowMapper<OffenderCourseSkills> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderCourseSkills.class, offCouSkillMapp);
		final List<OffenderCourseSkills> returnList = namedParameterJdbcTemplate.query(sql, createParams(),
				columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 *
	 */
	public List<OffenderProgramProfiles> checkAssignConflict(final OffenderProgramProfiles paramBean) {
		final String sql = getQuery("OCDUPROJ_CHECK_ASSIGN_CONFLICT");
		final RowMapper<OffenderProgramProfiles> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderProgramProfiles.class, offProProfMapp);
		final List<OffenderProgramProfiles> returnList = namedParameterJdbcTemplate.query(sql, createParams(),
				columnRowMapper);
		return returnList;
	}

	public String userNameRecordGroup(final String userName) {
		final String sql = getQuery("OCDUPROJOFFENDER_UNPAID_WORK_ADJ_USER_NAME");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("P_USER_ID", userName), String.class);
		} catch (EmptyResultDataAccessException e) {
			logger.error("Exception :", e);
			return null;
		}
	}

	public BigDecimal offenderUnpaidWorkAdjId() {
		final String sql = getQuery("OCDUPROJ_OFFENDER_UNPAID_WORK_ADJ_ID");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams(), BigDecimal.class);
		} catch (EmptyResultDataAccessException e) {
			logger.error("Exception :", e);
			return null;
		}
	}

	public BigDecimal getOffPrgrefIdCur() {
		final String sql = getQuery("OCDUPROJ_GET_OFF_PRGREF_ID_CUR");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams(), BigDecimal.class);
		} catch (EmptyResultDataAccessException e) {
			logger.error("Exception :", e);
			return null;
		}
	}

	public int updateCourseAttendance(final VOffenderCourseEvents searchBean) {

		final String sql = getQuery("UPDATE_COURSE_ATTENDENCE");
		Integer returnArray = null;
		try {
			returnArray = namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(searchBean));
		} catch (Exception e) {
			logger.error("updateCourseAttendance :", e);
			return 0;
		}

		if (returnArray == 0) {
			return 0;
		}
		return 1;

	}

	public int createOffCourseAttendance(final VOffenderCourseEvents searchBean) {

		final String sql = getQuery("INSERT_COURSE_ATTENDENCE");
		Integer returnArray = null;
		try {
			returnArray = namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(searchBean));
		} catch (Exception e) {
			logger.error("createOffCourseAttendance :", e);
			return 0;
		}

		if (returnArray == 0) {
			return 0;
		}
		return 1;
	}

	public OffenderCourseSkills firstNameLastName(final OffenderCourseSkills paramBean) {
		final String sql = getQuery("OCDUPROG_FIRST_NAME_LASTNAME");
		final RowMapper<OffenderCourseSkills> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderCourseSkills.class, offProProfMapp);
		final OffenderCourseSkills returnList = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("staffId", paramBean.getStaffId()), columnRowMapper);
		return returnList;
	}

	public String errorNameValidation(final String errorName) {
		final String sql = getQuery("OCMSUWPJ_CONSTRAINT_VALIDATIONS");
		String returnData = null;
		try {
			returnData = namedParameterJdbcTemplate.queryForObject(sql, createParams("CONSTRAINTNAME", errorName),
					String.class);
		} catch (Exception e) {
			logger.error("Exception :", e);
			returnData = null;
			return returnData;
		}
		return returnData;
	}

	@Override
	public void unpaidWorkCreditHours(VOffenderCourseEvents searchBean) {
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[2];

		sqlParameters = new SqlParameter[] { new SqlParameter("P_OFF_BOOK_ID", OracleTypes.NUMBER),
				new SqlParameter("P_CRS_ACTY_ID", OracleTypes.NUMBER),
				new SqlParameter("P_OFF_PRGREF_ID", OracleTypes.NUMBER) };

		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_UNPAID_WORK").withProcedureName("UPDATE_CREDIT_HRS")
				.declareParameters(sqlParameters);
		inParamMap.put("P_OFF_BOOK_ID", searchBean.getOffenderBookId());
		inParamMap.put("P_CRS_ACTY_ID", searchBean.getCrsActyId());
		inParamMap.put("P_OFF_PRGREF_ID", searchBean.getOffPrgrefId());

		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			simpleJDBCCall.execute(inParameter);
		} catch (Exception e) {
			logger.error("UPDATE_CREDIT_HRS :", e);
		}

	}

	@Override
	public BigDecimal getCourseAttendenceId() {
		final String sql = getQuery("GET_COURSE_ATTENDENCE_ID");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), BigDecimal.class);
	}

	@Override
	public List<VOffenderCourseEvents> getOldRecvOffenderCourseEvents(final Long eventId) {
		final String sql = getQuery("GET_OLD_REC_V_OFFENDER_COURSE_EVENTS");
		List<VOffenderCourseEvents> obj = new ArrayList<VOffenderCourseEvents>();
		final RowMapper<VOffenderCourseEvents> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				VOffenderCourseEvents.class, vOffCourEvtMapp);
		obj = namedParameterJdbcTemplate.query(sql, createParams("event_id", eventId), columnRowMapper);
		return obj;
	}

	@Override
	public Integer getActiveFlag(Long offenderId) {
		String sql = getQuery("OCDUPROJ_GET_ACTIVE_FLAGS");
		Integer count = null;
		try {
			count = namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderId", offenderId),
					Integer.class);
		} catch (Exception e) {
			logger.error("getActiveFlag" + e);
		}
		return count;
	}

	@Override
	public List<VHeaderBlock> getLastAndFirstName(Long offenderId) {
		String sql = getQuery("OCDUPROJ_GET_LASTNAME_AND_FIRSTNAME");
		return namedParameterJdbcTemplate.query(sql, createParams("offenderid", offenderId),
				new RowMapperResultSetExtractor<VHeaderBlock>(
						new BeanPropertyRowMapper<VHeaderBlock>(VHeaderBlock.class)));
	}

	@Override
	public List<OffenderNonAssociations> checkNonAssociations(final OffenderProgramProfiles paramBean) {
		final String sql = getQuery("OCDUPROJ_CHK_NONASSOCIATIONS");
		List<OffenderNonAssociations> returnList = new ArrayList<OffenderNonAssociations>();
		final RowMapper<OffenderNonAssociations> VAcpProgressRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderNonAssociations.class, vAcpProgressMapping);
		try {
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("offenderBookId", paramBean.getOffenderBookId()), VAcpProgressRowMapper);
		} catch (Exception e) {
			return returnList;
		}
		return returnList;
	}

	@Override
	public List<VOffenderCourseEvents> getOffederManualSchedules(OffenderProgramProfiles offenderProgramProfiles) {
		String sql = getQuery("OCDUPROJ_GET_DAILY_SCHEDULES");
		List<VOffenderCourseEvents> returnList = new ArrayList<VOffenderCourseEvents>();
		final RowMapper<VOffenderCourseEvents> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				VOffenderCourseEvents.class, offProProfMapp);
		try {
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("crsActyId", offenderProgramProfiles.getCrsActyId(), "offenderBookId",
							offenderProgramProfiles.getOffenderBookId()),
					columnRowMapper);
		} catch (Exception e) {
			logger.error("in getOffederSchedules method", e);
		}
		return returnList;
	}

	@Override
	public List<OffenderProgramProfiles> checkNonAssociationProgramAssignment(final OffenderProgramProfiles search,
			final OffenderNonAssociations offNonAss) {
		final String sql = getQuery("OCDUPROJ_CHK_NONASSOCIATION_PROGRAM_ASSIGNMENT");
		List<OffenderProgramProfiles> returnList = new ArrayList<OffenderProgramProfiles>();
		final RowMapper<OffenderProgramProfiles> VAcpProgressRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderProgramProfiles.class, vAcpProgressMapping);
		try {
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("offenderBookId", offNonAss.getNsOffenderBookId(), "crsActyId", search.getCrsActyId()),
					VAcpProgressRowMapper);
		} catch (Exception e) {
			return returnList;
		}
		return returnList;
	}

	public VHeaderBlock ocdprogrGetOffenderNames(final Long offBookId) {
		final String sql = getQuery("OCDUPROJ_GET_OFFENDER_NAMES");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("nsOffenderBookId", offBookId),
				new BeanPropertyRowMapper<VHeaderBlock>(VHeaderBlock.class));
	}

	@Override
	public List<VOffenderCourseEvents> getVOffenderCourseEventsData(Long offPrgrefId, Date offenderEndDate) {
		String sql = getQuery("OCDUPROJ_GET_VOFFENDER_COURSE_EVENTS_DATA");
		List<VOffenderCourseEvents> returnList = new ArrayList<VOffenderCourseEvents>();
		final RowMapper<VOffenderCourseEvents> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				VOffenderCourseEvents.class, offProProfMapp);
		try {
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("OFF_PRG_REF_ID", offPrgrefId, "offender_end_date", offenderEndDate), columnRowMapper);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " getVOffenderCourseEventsData", e);
		}
		return returnList;
	}

	public Integer updateVOffenderCourseEventsDataStatus(final List<VOffenderCourseEvents> voffendercourseevents) {
		final String sql = getQuery("OCDUPROJ_UPDATE_STATUS_VOFFENDER_COURSE_EVENTS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (VOffenderCourseEvents obj : voffendercourseevents) {
			parameters.add(new BeanPropertySqlParameterSource(obj));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " updateVOffenderCourseEventsDataStatus", e);
		}
		if (voffendercourseevents.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	@Override
	public List<VOffenderCourseEvents> getVOffenderCourseEventsDataOne(Long offPrgrefId, Date offenderEndDate) {
		String sql = getQuery("OCDUPROJ_GET_VOFFENDER_COURSE_EVENTS_DATA_ONE");
		List<VOffenderCourseEvents> returnList = new ArrayList<VOffenderCourseEvents>();
		final RowMapper<VOffenderCourseEvents> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				VOffenderCourseEvents.class, offProProfMapp);
		try {
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("offPrgrefId", offPrgrefId, "offender_EndDate", offenderEndDate), columnRowMapper);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " getVOffenderCourseEventsDataOne", e);
		}
		return returnList;
	}

	@Override
	public List<VOffenderCourseEvents> getVOffenderCourseEventsDataWithoutDate(Long offPrgrefId) {
		String sql = getQuery("GET_VOFFENDER_COURSE_EVENTS_DATA_WITHOUT_DATE");
		List<VOffenderCourseEvents> returnList = new ArrayList<VOffenderCourseEvents>();
		final RowMapper<VOffenderCourseEvents> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				VOffenderCourseEvents.class, offProProfMapp);
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("offPrgrefId", offPrgrefId),
					columnRowMapper);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " getVOffenderCourseEventsDataWithoutDate", e);
		}
		return returnList;
	}

	@Override
	public List<VOffenderSentCondActs> getSentenceData(VOffenderSentCondActs searchBean) {
		String sql = getQuery("GET_SENTENCE_DATA_OCDLEGLO");
		try {
			final RowMapper<VOffenderSentCondActs> sentenceRowMapper = Row2BeanRowMapper.makeMapping(sql,
					VOffenderSentCondActs.class, sentenceMapping);
			return namedParameterJdbcTemplate.query(sql,
					createParams("OFFENDER_BOOK_ID", searchBean.getOffenderBookId().toString()), sentenceRowMapper);
		} catch (Exception e) {
			logger.error("Exception in OcduprojRepositoryImpl class getSentenceData : ", e);
			return Collections.emptyList();
		}
	}

	@Override
	public List<OffenderProgramProfiles> projectOldData(final OffenderProgramProfiles objSearchDao) {
		final String sql = getQuery("OCDUPROJ_GET_OLD_RECORD");
		final RowMapper<OffenderProgramProfiles> OffenderProgramProfilesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderProgramProfiles.class, offProProfMapp);
		final List<OffenderProgramProfiles> returnList = namedParameterJdbcTemplate.query(sql,
				createParams("OFF_PRGREF_ID", objSearchDao.getOffPrgrefId()), OffenderProgramProfilesRowMapper);

		return returnList;
	}

	@Override
	public List<SentenceCalcTypes> senTypeExecuteQuery(String senCategory) {
		final String sql = getQuery("OCDUPROJ_SENTENCE_CATEGORY_TYPE");
		final RowMapper<SentenceCalcTypes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, SentenceCalcTypes.class,
				mMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("sentenceCategory", senCategory), mRowMapper);
		} catch (Exception e) {
			logger.error("Exception in OcduprojRepositoryImpl class senTypeExecuteQuery :", e);
			return Collections.emptyList();
		}

	}

	@Override
	public String getCourtrecords(String agyLocId) {
		final String sql = getQuery("OCDUPROJ_COURT_RECORD_EXECUTEQUERY");
		String returnList = null;
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("agyLocId", agyLocId),
					String.class);
		} catch (final Exception e) {
			logger.error(this.getClass().getName() + " getCourtData", e);
		}
		return returnList;
	}

	@Override
	public Integer getEventId() {

		final String sql = getQuery("OCDUPROJ_GET_EVENT_ID");
		Integer eventId = null;
		try {
			eventId = namedParameterJdbcTemplate.queryForObject(sql, createParams(), Integer.class);
		} catch (final Exception e) {
			logger.error(this.getClass().getName() + " getEventId", e);
		}
		return eventId;
	}

	@Override
	public List<ReferenceCodes> getReferenceDomainCodes(String domainName) {
		final String sql = getQuery("OCDUPROJ_GET_REFERENCE_CODE_PARENT_VALUES");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("domainName",domainName ), mRowMapper);
		} catch (Exception e) {
			logger.error("Exception in OcduprojRepositoryImpl class getReferenceDomainCodes :", e);
			return Collections.emptyList();
		}
	}
}
