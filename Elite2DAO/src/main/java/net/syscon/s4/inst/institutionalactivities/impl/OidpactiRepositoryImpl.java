package net.syscon.s4.inst.institutionalactivities.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.jdbc.support.oracle.SqlReturnStruct;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.im.beans.OffenderProgramProfiles;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.im.beans.ProgramsNonAssocTmp;
import net.syscon.s4.inst.institutionalactivities.OidpactiRepository;
import net.syscon.s4.inst.institutionalactivities.beans.VPrisonActivities;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.VOffenderCourseEvents;
import net.syscon.s4.inst.movements.beans.OffRec;
import oracle.jdbc.OracleTypes;

/**
 * Class OidpactiRepositoryImpl
 */
@Repository
public class OidpactiRepositoryImpl extends RepositoryBase implements OidpactiRepository {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidpactiRepositoryImpl.class.getName());
	private final Map<String, FieldMapper> offenderProgramProfilesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_END_DATE", new FieldMapper("offenderEndDate")).put("AGY_LOC_ID", new FieldMapper("agyLocId"))
			.put("OFFENDER_START_DATE", new FieldMapper("offenderStartDate")).put("COUNT", new FieldMapper("count"))
			.put("CRS_ACTY_ID", new FieldMapper("crsActyId")).put("PROGRAM_ID", new FieldMapper("programId"))
			.put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId")).put("VACANCY", new FieldMapper("vacancy"))
			.put("OFFENDREASONVAL", new FieldMapper("offEndReasonVal"))
			.put("OFFENDCOMMENTVAL", new FieldMapper("offEndCommentVal"))
			.put("REFCOMMENTVAL", new FieldMapper("refCommentVal")).put("REJREASON", new FieldMapper("rejReason"))
			.put("REJDATE", new FieldMapper("rejDate")).put("SCHEDULE_START_DATE", new FieldMapper("scheduleStartDate"))
			.put("SCHEDULE_END_DATE", new FieldMapper("scheduleEndDate")).put("DECISION", new FieldMapper("decision"))
			.put("CHK_ACTIVE_IA_ALLOCATION", new FieldMapper("chkActiveIaAllocation"))
			.put("OFF_END_DATE", new FieldMapper("offEndDate"))
			.put("oldStartDate", new FieldMapper("oldStartDate"))
			.put("CONFIRMEDRECORD", new FieldMapper("confirmedRecord"))
			.put("PAYFLAG_COUNT", new FieldMapper("payflagCount"))

			.build();
	private final Map<String, FieldMapper> vOffenderCourseEventsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_END_DATE", new FieldMapper("offenderEndDate")).put("AGY_LOC_ID", new FieldMapper("agyLocId"))
			.put("OFFENDER_START_DATE", new FieldMapper("offenderStartDate")).put("COUNT", new FieldMapper("count"))
			.put("CRS_ACTY_ID", new FieldMapper("crsActyId")).put("PROGRAM_ID", new FieldMapper("programId"))
			.put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId"))
			.put("DB_START_TIME", new FieldMapper("dbStartTime")).put("DB_END_TIME", new FieldMapper("dbEndTime"))
			.put("PAY_FLAG", new FieldMapper("payFlag")).put("PAY_BATCH_ID", new FieldMapper("payBatchId")).build();
	private final Map<String, FieldMapper> courseActivitiesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("AGY_LOC_I", new FieldMapper("agyLocI")).put("DESCRIPTION", new FieldMapper("description"))
			.put("LIST_SE", new FieldMapper("listSe")).put("CASELOAD_ID", new FieldMapper("caseloadId")).build();
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("LIST_SEQ", new FieldMapper("listSeq")).put("CODE", new FieldMapper("code"))
			.put("DESCRIPTION", new FieldMapper("description")).put("SERVICE", new FieldMapper("service"))
			.put("PROGRAM_ID", new FieldMapper("programId")).put("PROGRAM_CODE", new FieldMapper("programCode"))
			.put("CHECK_FLAG", new FieldMapper("checkFlag")).put("SEAL_FLAG", new FieldMapper("sealFlag")).build();
	private final Map<String, FieldMapper> offenderMedicalRecordsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PLAN_NOTE", new FieldMapper(" planNote ")).put("ROOT_OFFENDER_ID", new FieldMapper("rootOffenderId"))
			.put("MEDICAL_RECORD_SEQ", new FieldMapper("medicalRecordSeq")).build();
	private final Map<String, FieldMapper> offendersMapping = new ImmutableMap.Builder<String, FieldMapper>().build();

	/**
	 * Creates new OidpactiRepositoryImpl class Object
	 */
	public OidpactiRepositoryImpl() {
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao OffenderProgramProfiles
	 *
	 * @return List<OffenderProgramProfiles>
	 *
	 * @throws SQLException
	 */
	public List<OffenderProgramProfiles> offProgProfExecuteQuery(OffenderProgramProfiles objSearchDao) {
		final String sql = getQuery("OIDPACTI_OFFPROGPROF_FIND_OFFENDER_PROGRAM_PROFILES");
		final RowMapper<OffenderProgramProfiles> OffenderProgramProfilesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderProgramProfiles.class, offenderProgramProfilesMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("OFFENDER_BOOK_ID", objSearchDao.getOffenderBookId()),
				OffenderProgramProfilesRowMapper);
	}

	@Override
	public List<OffenderProgramProfiles> offenderProgramProfileswaitExecuteQuery(OffenderProgramProfiles searchRecord) {
		final String sql = getQuery("OIDPACTI_OFFPROGPROF_FIND_OFFENDER_PROGRAM_PROFILES_TWO");
		final RowMapper<OffenderProgramProfiles> OffenderProgramProfilesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderProgramProfiles.class, offenderProgramProfilesMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("OFFENDER_BOOK_ID", searchRecord.getOffenderBookId()),
				OffenderProgramProfilesRowMapper);
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstOffenderProgramProfiles List<OffenderProgramProfiles>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public String offProgProfInsertOffenderProgramProfiles(final List<OffenderProgramProfiles> lstObj) {
		String sql = getQuery("OIDPACTI_OFFPROGPROF_INSERT_OFFENDER_PROGRAM_PROFILES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<>();
		for (OffenderProgramProfiles offenderProgramProfiles : lstObj) {
			parameters.add(new BeanPropertySqlParameterSource(offenderProgramProfiles));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			return e.getMessage();
		}
		if (lstObj.size() == returnArray.length) {
			return "1";
		} else {
			return "0";
		}
	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstOffenderProgramProfiles List<OffenderProgramProfiles>
	 *
	 * @throws SQLException
	 */
	public String offProgProfUpdateOffenderProgramProfiles(final List<OffenderProgramProfiles> lstObj) {
		String sql = getQuery("OIDPACTI_OFFPROGPROF_UPDATE_OFFENDER_PROGRAM_PROFILES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (OffenderProgramProfiles offenderProgramProfiles : lstObj) {
			parameters.add(new BeanPropertySqlParameterSource(offenderProgramProfiles));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			return e.getMessage();
		}
		if (lstObj.size() == returnArray.length) {
			return "1";
		} else {
			return null;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstOffenderProgramProfiles List<OffenderProgramProfiles>
	 *
	 * @throws SQLException
	 */
	public String insertOffenderProgramProfiles(final List<OffenderProgramProfiles> lstObj) {
		String sql = getQuery("OIDPACTI_OFFPROGPROF_INSERT_OFFENDER_PROGRAM_PROFILES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<>();
		for (OffenderProgramProfiles offenderProgramProfiles : lstObj) {
			parameters.add(new BeanPropertySqlParameterSource(offenderProgramProfiles));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			return e.getMessage();
		}
		if (lstObj.size() == returnArray.length) {
			return "1";
		} else {
			return null;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstOffenderProgramProfiles List<OffenderProgramProfiles>
	 *
	 * @throws SQLException
	 */
	public String offProgProfDeleteOffenderProgramProfiles(final List<OffenderProgramProfiles> lstObj) {
		String sql = getQuery("OIDPACTI_OFFPROGPROF_DELETE_OFFENDER_PROGRAM_PROFILES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<>();
		for (OffenderProgramProfiles offenderProgramProfiles : lstObj) {
			parameters.add(new BeanPropertySqlParameterSource(offenderProgramProfiles));
		}
		try {
			String tableName = "OFFENDER_PROGRAM_PROFILES";
			String whereClause = "OFF_PRGREF_ID=:offPrgrefId";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method offProgProfDeleteOffenderProgramProfiles", e);
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			return e.getMessage();
		}
		if (lstObj.size() == returnArray.length) {
			return "1";
		} else {
			return "0";
		}
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao VOffenderCourseEvents
	 *
	 * @return List<VOffenderCourseEvents>
	 *
	 * @throws SQLException
	 */
	public List<VOffenderCourseEvents> vOffCourseEvntsExecuteQuery(VOffenderCourseEvents objSearchDao) {

		final String sql = getQuery("OIDPACTI_VOFFCOURSEEVNTS_FIND_V_OFFENDER_COURSE_EVENTS");
		final RowMapper<VOffenderCourseEvents> VOffenderCourseEventsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				VOffenderCourseEvents.class, vOffenderCourseEventsMapping);
		String preparedSql = null;
		final MapSqlParameterSource inParameterSource = new MapSqlParameterSource();
		final StringBuffer sqlQuery = new StringBuffer();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			if (objSearchDao.getOffPrgrefId() != null) {
				sqlQuery.append(" OFF_PRGREF_ID = :OFF_PRGREF_ID" + " AND ");
				inParameterSource.addValue("OFF_PRGREF_ID", objSearchDao.getOffPrgrefId());
			}
			if ("CONFIRMED".equals(objSearchDao.getEventOutcome())) {
				sqlQuery.append(" EVENT_OUTCOME IS NOT NULL");
			} else {
				sqlQuery.append(" EVENT_OUTCOME IS  NULL");
			}

		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("WHERE")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("AND")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		if (objSearchDao != null && "CONFIRMED".equals(objSearchDao.getEventOutcome())) {
			preparedSql = preparedSql + " ORDER BY EVENT_DATE DESC";
		} else {
			preparedSql = preparedSql + " ORDER BY EVENT_DATE";
		}
		return namedParameterJdbcTemplate.query(preparedSql, inParameterSource, VOffenderCourseEventsRowMapper);

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstVOffenderCourseEvents List<VOffenderCourseEvents>
	 *
	 * @throws SQLException
	 */
	public Integer vOffCourseEvntsUpdateVOffenderCourseEvents(
			final List<VOffenderCourseEvents> lstVOffenderCourseEvents) {
		String sql = getQuery("OIDPACTI_VOFFCOURSEEVNTS_UPDATE_V_OFFENDER_COURSE_EVENTS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<>();
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
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstVOffenderCourseEvents List<VOffenderCourseEvents>
	 *
	 * @throws SQLException
	 */
	public Integer vOffCourseEvntsDeleteVOffenderCourseEvents(
			final List<VOffenderCourseEvents> lstVOffenderCourseEvents) {
		String sql = getQuery("OIDPACTI_VOFFCOURSEEVNTS_DELETE_V_OFFENDER_COURSE_EVENTS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<>();
		for (VOffenderCourseEvents vOffenderCourseEvents : lstVOffenderCourseEvents) {
			parameters.add(new BeanPropertySqlParameterSource(vOffenderCourseEvents));
		}
		try {
			String tableName = "offender_course_attendances";
			String whereClause = "EVENT_ID = :eventId";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method vOffCourseEvntsDeleteVOffenderCourseEvents", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstVOffenderCourseEvents.size() == returnArray.length) {
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
	public List<AgencyLocations> rgEstablishmentRecordGroup(final String caseloadId) {
		final String sql = getQuery("OIDPACTI_FIND_RGESTABLISHMENT");
		final RowMapper<AgencyLocations> mRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams("CASELOADID", caseloadId), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("In rgEstablishmentRecordGroup method : ", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<VPrisonActivities> rgServicesRecordGroup(final String agyLocId) {
		final String sql = getQuery("OIDPACTI_FIND_RGSERVICES");
		final RowMapper<VPrisonActivities> mRowMapper = Row2BeanRowMapper.makeMapping(sql, VPrisonActivities.class,
				mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams("AGYLOCID", agyLocId), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("In rgServicesRecordGroup method : ", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<VPrisonActivities> getServices() {
		final String sql = getQuery("OIDPACTI_FIND_SERVICES_ONLOAD");
		final RowMapper<VPrisonActivities> mRowMapper = Row2BeanRowMapper.makeMapping(sql, VPrisonActivities.class,
				mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("In getServices method : ", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgEndReasonRecordGroup() {
		final String sql = getQuery("OIDPACTI_FIND_RGENDREASON");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("In rgEndReasonRecordGroup method : ", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> pgPsRejRsnRecordGroup() {
		final String sql = getQuery("OIDPACTI_FIND_PGPSREJRSN");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("In pgPsRejRsnRecordGroup method : ", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgPerformanceRecordGroup() {
		final String sql = getQuery("OIDPACTI_FIND_RGPERFORMANCE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("In rgPerformanceRecordGroup method : ", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgPriorityRecordGroup() {
		final String sql = getQuery("OIDPACTI_FIND_RGPRIORITY");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("In rgPriorityRecordGroup method : ", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgDecisionRecordGroup(final String systemMode) {
		final String sql = getQuery("OIDPACTI_FIND_RGDECISION");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams("systemMode", systemMode), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("In rgDecisionRecordGroup method : ", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgAttendenceRecordGroup(final String pshowoutcome) {
		final String sql = getQuery("OIDPACTI_FIND_RGATTENDENCE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams("PSHOWOUTCOME", pshowoutcome), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("In rgAttendenceRecordGroup method : ", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<VPrisonActivities> lovServices2RecordGroup() {
		final String sql = getQuery("OIDPACTI_FIND_LOVSERVICES2");
		final RowMapper<VPrisonActivities> mRowMapper = Row2BeanRowMapper.makeMapping(sql, VPrisonActivities.class,
				mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("In lovServices2RecordGroup method : ", e);
			return Collections.emptyList();
		}
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offBkgOnCheckDeleteMaster
	 *
	 * @param params
	 *
	 */
	public List<OffenderProgramProfiles> offBkgOnCheckDeleteMasters(OffenderProgramProfiles paramBean) {
		final String sql = getQuery("OIDPACTI_OFF_BKG_ONCHECKDELETEMASTER");
		final RowMapper<OffenderProgramProfiles> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderProgramProfiles.class, offenderProgramProfilesMapping);
		return namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offBkgOnCheckDeleteMaster
	 *
	 * @param params
	 *
	 */
	public List<OffenderProgramProfiles> offBkgOnCheckDeleteMaster(OffenderProgramProfiles paramBean) {
		final String sql = getQuery("OIDPACTI_OFF_BKG_ONCHECKDELETEMASTER");
		final RowMapper<OffenderProgramProfiles> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderProgramProfiles.class, offenderProgramProfilesMapping);
		return namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offProgProfWhenValidateRecord
	 *
	 * @param params
	 *
	 */
	public List<OffenderProgramProfiles> offProgProfWhenValidateRecord(OffenderProgramProfiles paramBean) {
		final String sql = getQuery("OIDPACTI_OFF_PROG_PROF_WHENVALIDATERECORD");
		final RowMapper<OffenderProgramProfiles> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderProgramProfiles.class, offenderProgramProfilesMapping);
		return namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offProgProfWhenCreateRecord
	 *
	 * @param params
	 *
	 */
	public List<Object> offProgProfWhenCreateRecord(CourseActivities paramBean) {
		final String sql = getQuery("OIDPACTI_OFF_PROG_PROF_WHENCREATERECORD");
		return namedParameterJdbcTemplate.queryForList(sql, createParams(), Object.class);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * oidpactiWhenNewFormInstance
	 *
	 * @param params
	 *
	 */
	public List<ReferenceCodes> oidpactiWhenNewFormInstance(ReferenceCodes paramBean) {
		final String sql = getQuery("OIDPACTI_OIDPACTI_WHENNEWFORMINSTANCE");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				mMapping);
		return namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * chkActiveIaAllocation
	 *
	 * @param params
	 *
	 */
	public List<OffenderProgramProfiles> chkActiveIaAllocation(OffenderProgramProfiles paramBean) {
		final String sql = getQuery("OIDPACTI_CHK_ACTIVE_IA_ALLOCATION");
		final RowMapper<OffenderProgramProfiles> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderProgramProfiles.class, offenderProgramProfilesMapping);
		return namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * checkDup
	 *
	 * @param params
	 *
	 */
	public Integer checkDup(OffenderProgramProfiles paramBean) {
		final String sql = getQuery("OIDPACTI_CHECK_DUP");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("OFFENDER_BOOK_ID", paramBean.getOffenderBookId(), "crs_acty_id", paramBean.getCrsActyId(),
						"program_id", paramBean.getProgramId(), "AGY_LOC_ID", paramBean.getAgyLocId(),
						"offender_end_date", paramBean.getOffEndDate(), "offender_start_date",
						paramBean.getOffenderStartDate()),
				Integer.class);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * checkAssignConflictWait
	 *
	 * @param params
	 *
	 */
	public List<OffenderProgramProfiles> checkAssignConflictWait(OffenderProgramProfiles paramBean) {
		final String sql = getQuery("OIDPACTI_CHECK_ASSIGN_CONFLICT_WAIT");
		final RowMapper<OffenderProgramProfiles> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderProgramProfiles.class, offenderProgramProfilesMapping);
		return namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * checkAssignConflictWait
	 *
	 * @param params
	 *
	 */
	public List<OffenderProgramProfiles> checkAssignConflictWaits(OffenderProgramProfiles paramBean) {
		final String sql = getQuery("OIDPACTI_CHECK_ASSIGN_CONFLICT_WAIT");
		final RowMapper<OffenderProgramProfiles> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderProgramProfiles.class, offenderProgramProfilesMapping);
		return namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
	}

	/**
	 * This method will check if there are any non association conflicts
	 * 
	 * @return ProgramsNonAssocTmp
	 */
	public ProgramsNonAssocTmp checkConflict(final OffenderProgramProfiles object) {
		String conflict = null;
		Map<String, Object> returnObject = null;
		final Map<String, Object> inParamMap = new HashMap<>();
		SqlParameter[] sqlParameters = new SqlParameter[20];
		final ProgramsNonAssocTmp returnProgObj = new ProgramsNonAssocTmp();
		sqlParameters = new SqlParameter[] { new SqlParameter("P_OFFENDER_ID", OracleTypes.NUMBER),
				new SqlParameter("P_OFFENDER_BOOK_ID", OracleTypes.NUMBER),
				new SqlParameter("P_CRS_ACTY_ID", OracleTypes.NUMBER),
				new SqlOutParameter("P_OFF_INFO", OracleTypes.STRUCT, "TAG_CORE.OFF_INFO_TYPE",
						new SqlReturnStruct(OffRec.class)),
				new SqlOutParameter("P_CONFLICT_FLAG", OracleTypes.VARCHAR),
				new SqlParameter("P_OFFENDER_START_DATE", OracleTypes.DATE) };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("NON_ASSOCIATION").withProcedureName("CHK_NA_PRG_SRV_CONFLICT_RT")
				.declareParameters(sqlParameters);
		inParamMap.put("P_OFFENDER_ID", object.getOffenderId());
		inParamMap.put("P_OFFENDER_BOOK_ID", object.getOffenderBookId());
		inParamMap.put("P_CRS_ACTY_ID", object.getCrsActyId());
		inParamMap.put("P_OFFENDER_START_DATE", object.getOffenderStartDate());
		MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			conflict = returnObject.get("P_CONFLICT_FLAG").toString();
			if (conflict != null && "Y".equals(conflict)) {
				final OffRec st = (OffRec) returnObject.get("P_OFF_INFO");
				sqlParameters = new SqlParameter[20];
				sqlParameters = new SqlParameter[] { new SqlParameter("P_OFFENDER_ID", OracleTypes.NUMBER),
						new SqlParameter("P_OFF_INFO", OracleTypes.STRUCT, "TAG_CORE.OFF_INFO_TYPE"),
						new SqlOutParameter("LV_WARNING_MESSAGE", OracleTypes.VARCHAR),
						new SqlOutParameter("LV_WARNING_PROMPT", OracleTypes.VARCHAR) };

				simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
						.withCatalogName("NON_ASSOCIATION").withProcedureName("GET_CONFLICT_WARNING")
						.declareParameters(sqlParameters);
				inParamMap.put("P_OFFENDER_ID", object.getOffenderId());
				inParamMap.put("P_OFF_INFO", st);
				inParameter = new MapSqlParameterSource(inParamMap);
				returnObject = simpleJDBCCall.execute(inParameter);
				returnProgObj.setWarningMsg(String.valueOf(returnObject.get("LV_WARNING_MESSAGE")));
				returnProgObj.setWarningPrompt(String.valueOf(returnObject.get("LV_WARNING_PROMPT")));
			}
		} catch (Exception e) {
			logger.error("checkConflict: ", e);
		}
		return returnProgObj;
	}

	@Override
	public Long getPrgRefId() {
		final String sql = getQuery("OIDPACTI_OFF_PRG_PROFILE_PRE_INSERT");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Long.class);
	}

	public Integer cntAsnCur(OffenderProgramProfiles paramBean) {
		final String sql = getQuery("OIDPACTI_CNT_ASN_CUR");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("agy_loc_id", paramBean.getAgyLocId(),
				"offender_book_id", paramBean.getOffenderBookId(), "crs_acty_id", paramBean.getCrsActyId()),
				Integer.class);
	}

	public Integer deleteVoffenderCourseEvents(final OffenderProgramProfiles paramBean) {
		final String sql = getQuery("OIDPACTI_DELETE_V_OFFENDER_COURSE_EVENTS");
		try {
			String tableName = "V_OFFENDER_COURSE_EVENTS";
			String whereClause = "OFF_PRGREF_ID = :OFFPRGREFID";
			Map<String , Object> inputMap = new HashMap<String, Object>();
			inputMap.put("OFFPRGREFID", paramBean.getOffPrgrefId());
			inputMap.put("modifyUserId", paramBean.getModifyUserId());
			updatePreDeletedRow(tableName, whereClause , inputMap);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteVoffenderCourseEvents", e);
		}
		try {
			return namedParameterJdbcTemplate.update(sql, createParams("OFFPRGREFID", paramBean.getOffPrgrefId()));
		} catch (final Exception e) {
			logger.error("deleteVoffenderCourseEvents", e);
		}
		return 0;
	}

	public Integer deleteOffenderCourseApptGrps(final OffenderProgramProfiles paramBean) {
		final String sql = getQuery("OIDPACTI_DELETE_OFFENDER_COURSE_APPT_GRPS");
		try {
			String tableName = "OFFENDER_COURSE_APPT_GRPS";
			String whereClause = "OFF_PRGREF_ID = :OFFPRGREFID";
			Map<String , Object> inputMap = new HashMap<String, Object>();
			inputMap.put("OFFPRGREFID", paramBean.getOffPrgrefId());
			inputMap.put("modifyUserId", paramBean.getModifyUserId());
			updatePreDeletedRow(tableName, whereClause , inputMap);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteOffenderCourseApptGrps", e);
		}
		try {
			return namedParameterJdbcTemplate.update(sql, createParams("OFFPRGREFID", paramBean.getOffPrgrefId()));
		} catch (final Exception e) {
			logger.error("deleteOffenderCourseApptGrps", e);
		}
		return 0;
	}

	public Integer deleteOffenderCourseAttendances(final OffenderProgramProfiles paramBean) {
		final String sql = getQuery("OIDPACTI_DELETE_OFFENDER_COURSE_ATTENDANCES");
		try {
			String tableName = "OFFENDER_COURSE_ATTENDANCES";
			String whereClause = "OFF_PRGREF_ID = :OFFPRGREFID";
			Map<String , Object> inputMap = new HashMap<String, Object>();
			inputMap.put("OFFPRGREFID", paramBean.getOffPrgrefId());
			inputMap.put("modifyUserId", paramBean.getModifyUserId());
			updatePreDeletedRow(tableName, whereClause , inputMap);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteOffenderCourseAttendances", e);
		}
		try {
			return namedParameterJdbcTemplate.update(sql, createParams("OFFPRGREFID", paramBean.getOffPrgrefId()));
		} catch (final Exception e) {
			logger.error("deleteOffenderCourseAttendances", e);
		}
		return 0;
	}

	@Override
	public String getProfileValue() {
		final String sql = getQuery("OIDPACTI_GET_PROFILE_VALUE");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);
	}

	@Override
	public List<Offenders> getNaPrgSrv(OffenderProgramProfiles bean) {
		final String sql = getQuery("OIDPACTI_CHECK_CONFLICT_GET_NA_PRG_SRV");
		final RowMapper<Offenders> mRowMapper = Row2BeanRowMapper.makeMapping(sql, Offenders.class, offendersMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("p_offender_id", bean.getOffenderId(),
				"p_crs_acty_id", bean.getCrsActyId(), "p_offender_start_date", bean.getOffenderStartDate()),
				mRowMapper);
	}

	@Override
	public List<Offenders> getStgNaPrgSrv(OffenderProgramProfiles bean) {
		final String sql = getQuery("OIDPACTI_CHECK_CONFLICT_GET_STG_NA_PRG_SRV");
		final RowMapper<Offenders> mRowMapper = Row2BeanRowMapper.makeMapping(sql, Offenders.class, offendersMapping);
		return namedParameterJdbcTemplate.query(sql,
				createParams("p_offender_book_id", bean.getOffenderBookId(), "p_crs_acty_id", bean.getCrsActyId()),
				mRowMapper);
	}

	@Override
	public List<Offenders> getOffDetails(final OffenderProgramProfiles obj) {
		final String sql = getQuery("CKECK_CONFLICT_GET_OFFENDER_DETAILS");
		final RowMapper<Offenders> mRowMapper = Row2BeanRowMapper.makeMapping(sql, Offenders.class, offendersMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("p_offender_id", obj.getOffenderId()), mRowMapper);

	}

	public VOffenderCourseEvents getOldValues(final VOffenderCourseEvents obj) {
		final String sql = getQuery("CKECK_V_OFFENDER_COURSE_EVENTS");
		VOffenderCourseEvents retList = new VOffenderCourseEvents();
		retList = namedParameterJdbcTemplate.queryForObject(sql, createParams("crsSchId", obj.getCrsSchId()),
				VOffenderCourseEvents.class);
		return retList;
	}

	@Override
	public Integer gettingOldValueDeleteVoffenderCrseEvents(final OffenderProgramProfiles paramBean) {
		final String sql = getQuery("OIDPACTI_GETTING_OLD_VALUE_DELETE_V_OFFENDER_COURSE_EVENTS");
		try {
			return namedParameterJdbcTemplate.update(sql,
					createParams("offenderBookId", paramBean.getOffenderBookId()));
		} catch (final Exception e) {
			logger.error("deleteVoffenderCourseEvents", e);
		}
		return 0;
	}

}
