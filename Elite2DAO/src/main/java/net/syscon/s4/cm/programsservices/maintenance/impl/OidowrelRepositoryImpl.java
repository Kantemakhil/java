package net.syscon.s4.cm.programsservices.maintenance.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.cm.programsservices.maintenance.OidowrelRepository;
import net.syscon.s4.common.beans.OffenderExternalMovements;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.Corporates;
import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.im.beans.OffenderCaseNotes;
import net.syscon.s4.im.beans.OffenderPrgObligations;
import net.syscon.s4.im.beans.OffenderProgramProfiles;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.im.beans.VCourseActivities;
import net.syscon.s4.inst.casemanagement.beans.ProgramAssessments;
import net.syscon.s4.inst.casemanagement.beans.ProgramServices;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.VOffenderCourseEvents;
import net.syscon.s4.triggers.OffenderCourseAttendances;

/**
 * Class OidowrelRepositoryImpl
 * 
 */
@Repository
public class OidowrelRepositoryImpl extends RepositoryBase implements OidowrelRepository {

	private static Logger logger = LogManager.getLogger(OidowrelRepositoryImpl.class.getName());

	/**
	 * Creates new OidowrelRepositoryImpl class Object
	 */
	public OidowrelRepositoryImpl() {
	}

	private final Map<String, FieldMapper> mmMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description")).put("CODE", new FieldMapper("code"))
			.put("COURSE_ACTIVITY_CODE", new FieldMapper("courseActivityCode"))
			.put("PROVIDER_NAME", new FieldMapper("providerName")).build();

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao OffenderPrgObligations
	 *
	 * @return List<OffenderPrgObligations>
	 *
	 * @throws SQLException
	 */
	public List<OffenderPrgObligations> offPrgObligationsExecuteQuery(OffenderPrgObligations objSearchDao) {
		final String sql = getQuery("OIDOWREL_OFFPRGOBLIGATIONS_FIND_OFFENDER_PRG_OBLIGATIONS");
		final RowMapper<OffenderPrgObligations> OffenderPrgObligationsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderPrgObligations.class, mmMapping);
		final List<OffenderPrgObligations> returnList = namedParameterJdbcTemplate.query(sql,
				createParams("offenderBookId", objSearchDao.getOffenderBookId()), OffenderPrgObligationsRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstOffenderPrgObligations List<OffenderPrgObligations>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public Integer insertOffenderPrgObligations(final List<OffenderPrgObligations> lstOffenderPrgObligations) {
		String sql = getQuery("OIDOWREL_INSERT_OFFENDER_PRG_OBLIGATIONS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (OffenderPrgObligations obj : lstOffenderPrgObligations) {
			parameters.add(new BeanPropertySqlParameterSource(obj));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " insertOffenderPrgObligations" + e);
		}
		if (lstOffenderPrgObligations.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstOffenderPrgObligations List<OffenderPrgObligations>
	 *
	 * @throws SQLException
	 */
	public Integer updateOffenderPrgObligations(final List<OffenderPrgObligations> lstOffenderPrgObligations) {
		String sql = getQuery("OIDOWREL_UPDATE_OFFENDER_PRG_OBLIGATIONS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (OffenderPrgObligations obj : lstOffenderPrgObligations) {
			parameters.add(new BeanPropertySqlParameterSource(obj));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " updateOffenderPrgObligations" + e);
		}
		if (lstOffenderPrgObligations.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstOffenderPrgObligations List<OffenderPrgObligations>
	 *
	 * @throws SQLException
	 */
	public Integer deleteOffenderPrgObligations(final List<OffenderPrgObligations> lstOffenderPrgObligations) {
		String sql = getQuery("OIDOWREL_DELETE_OFFENDER_PRG_OBLIGATIONS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (OffenderPrgObligations offenderPrgObligations : lstOffenderPrgObligations) {
			parameters.add(new BeanPropertySqlParameterSource(offenderPrgObligations));
		}
		try {
			String tableName = "OFFENDER_PRG_OBLIGATIONS";
			String whereClause = "offender_prg_obligation_id  = :offenderPrgObligationId AND OFFENDER_BOOK_ID = :offenderBookId";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteOffenderPrgObligations", e);
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " deleteOffenderPrgObligations" + e);
			if (e.getMessage().contains("offender_prg_obligation_hty")
					|| e.getMessage().contains("offender_prg_obligations")) {
				return 3;
			}
		}
		if (lstOffenderPrgObligations.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

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
	public List<OffenderProgramProfiles> offProgramProfilesExecuteQuery(OffenderProgramProfiles objSearchDao) {
		final String sql = getQuery("OIDOWREL_FIND_OFFENDER_PROGRAM_PROFILES");
		final RowMapper<OffenderProgramProfiles> OffenderProgramProfilesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderProgramProfiles.class, mmMapping);
		final List<OffenderProgramProfiles> returnList = namedParameterJdbcTemplate.query(sql,
				createParams("offenderPrgObligationId", objSearchDao.getOffenderPrgObligationId()),
				OffenderProgramProfilesRowMapper);
		return returnList;
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
	public Integer insertOffenderProgramProfiles(final List<OffenderProgramProfiles> lstOffenderProgramProfiles) {
		String sql = getQuery("OIDOWREL_INSERT_OFFENDER_PROGRAM_PROFILES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (OffenderProgramProfiles bean : lstOffenderProgramProfiles) {
			parameters.add(new BeanPropertySqlParameterSource(bean));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " insertOffenderProgramProfiles");
		}
		if (lstOffenderProgramProfiles.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstOffenderProgramProfiles List<OffenderProgramProfiles>
	 *
	 * @throws SQLException
	 */
	public Integer updateOffenderProgramProfiles(final List<OffenderProgramProfiles> lstOffenderProgramProfiles) {
		String sql = getQuery("OIDOWREL_UPDATE_OFFENDER_PROGRAM_PROFILES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (OffenderProgramProfiles offenderProgramProfiles : lstOffenderProgramProfiles) {
			parameters.add(new BeanPropertySqlParameterSource(offenderProgramProfiles));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " updateOffenderProgramProfiles");
		}
		if (lstOffenderProgramProfiles.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
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
	public List<VOffenderCourseEvents> vOffenderCourseEventsExecuteQuery(VOffenderCourseEvents objSearchDao) {
		final String sql = getQuery("OIDOWREL_VOFFENDER_COURSE_EVENTS_EXECUTEQUERY");
		final StringBuffer pSql = new StringBuffer(sql);
		String preparedSql = "";
		if (objSearchDao != null) {
			if (objSearchDao.getEventOutcomeDbVal() != null && !objSearchDao.getEventOutcomeDbVal().isEmpty()) {
				pSql.append(" AND ");
				if (objSearchDao.getEventOutcomeDbVal().equals("2")) {
					pSql.append(" EVENT_STATUS = 'SCH' AND EVENT_OUTCOME IS NULL");
				} else {
					pSql.append(" ( EVENT_OUTCOME IN ('COMP','CANC') OR EVENT_STATUS = 'EXP') ");
				}
			}
		}
		preparedSql = pSql.toString().trim();
		preparedSql = preparedSql.concat(" ORDER BY EVENT_DATE ASC, START_TIME ASC ");
		final RowMapper<VOffenderCourseEvents> VOffenderCourseEventsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				VOffenderCourseEvents.class, mmMapping);
		final List<VOffenderCourseEvents> returnList = namedParameterJdbcTemplate.query(preparedSql,
				createParams("offPrgrefId", objSearchDao.getOffPrgrefId()), VOffenderCourseEventsRowMapper);
		return returnList;
	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstVOffenderCourseEvents List<VOffenderCourseEvents>
	 *
	 * @throws SQLException
	 */
	public Integer vOffenderCourseEventsUpdateVOffenderCourseEvents(List<VOffenderCourseEvents> list) {
		String sql = getQuery("OIDOWREL_UPDATE_V_OFFENDER_COURSE_EVENTS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (VOffenderCourseEvents vOffenderCourseEvents : list) {
			parameters.add(new BeanPropertySqlParameterSource(vOffenderCourseEvents));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (list.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao OffenderCaseNotes
	 *
	 * @return List<OffenderCaseNotes>
	 *
	 * @throws SQLException
	 */
	public List<OffenderCaseNotes> offenderCaseNotesExecuteQuery(OffenderCaseNotes objSearchDao) {
		final String sql = getQuery("OIDOWREL_OFFENDERCASENOTES_FIND_OFFENDER_CASE_NOTES");
		final RowMapper<OffenderCaseNotes> OffenderCaseNotesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderCaseNotes.class, mmMapping);
		final List<OffenderCaseNotes> returnList = namedParameterJdbcTemplate.query(sql,
				createParams("offenderBookId", objSearchDao.getOffenderBookId()), OffenderCaseNotesRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstOffenderCaseNotes List<OffenderCaseNotes>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public Integer insertOffenderCaseNotes(final List<OffenderCaseNotes> lstOffenderCaseNotes) {
		String sql = getQuery("OIDOWREL_OFFENDERCASENOTES_INSERT_OFFENDER_CASE_NOTES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (OffenderCaseNotes bean : lstOffenderCaseNotes) {
			parameters.add(new BeanPropertySqlParameterSource(bean));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " offendercasenotesInsertOffenderCaseNotes");
		}
		if (lstOffenderCaseNotes.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstOffenderCaseNotes List<OffenderCaseNotes>
	 *
	 * @throws SQLException
	 */
	public Integer offenderCaseNotesUpdateOffenderCaseNotes(final List<OffenderCaseNotes> lstOffenderCaseNotes) {
		String sql = getQuery("OIDOWREL_OFFENDERCASENOTES_UPDATE_OFFENDER_CASE_NOTES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (OffenderCaseNotes offenderCaseNotes : lstOffenderCaseNotes) {
			parameters.add(new BeanPropertySqlParameterSource(offenderCaseNotes));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenderCaseNotes.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstOffenderCaseNotes List<OffenderCaseNotes>
	 *
	 * @throws SQLException
	 */
	public Integer offenderCaseNotesDeleteOffenderCaseNotes(final List<OffenderCaseNotes> lstOffenderCaseNotes) {
		String sql = getQuery("OIDOWREL_OFFENDERCASENOTES_DELETE_OFFENDER_CASE_NOTES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (OffenderCaseNotes offenderCaseNotes : lstOffenderCaseNotes) {
			parameters.add(new BeanPropertySqlParameterSource(offenderCaseNotes));
		}
		try {
			String tableName = "OFFENDER_CASE_NOTES";
			String whereClause = "CASE_NOTE_ID = :caseNoteId  AND OFFENDER_BOOK_ID = :offenderBookId";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method offenderCaseNotesDeleteOffenderCaseNotes", e);
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " offenderCaseNotesDeleteOffenderCaseNotes");
			if (e.getMessage().contains("off_cn_recpt_off_cn_fk")) {
				return 3;
			}
		}
		if (lstOffenderCaseNotes.size() == returnArray.length) {
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
	public List<ReferenceCodes> rgPriorityRecordGroup() {
		final String sql = getQuery("OIDOWREL_FIND_RGPRIORITY");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				mmMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " rgPriorityRecordGroup" + e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ProgramServices>
	 */
	public List<ProgramServices> rgProgramRecordGroup() {
		final String sql = getQuery("OIDOWREL_FIND_RGPROGRAM");
		final RowMapper<ProgramServices> mMRowMapper = Row2BeanRowMapper.makeMapping(sql, ProgramServices.class,
				mmMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mMRowMapper);
		} catch (Exception e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgEndReasonRecordGroup() {
		final String sql = getQuery("OIDOWREL_FIND_RGENDREASON");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				mmMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (Exception e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgCancelReasonRecordGroup() {
		final String sql = getQuery("OIDOWREL_FIND_RGCANCELREASON");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				mmMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " rgCancelReasonRecordGroup" + e);
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
	public List<OffenderPrgObligations> offBkgOnCheckDeleteMaster(OffenderPrgObligations paramBean) {
		final String sql = getQuery("OIDOWREL_OFF_BKG_ONCHECKDELETEMASTER");
		final RowMapper<OffenderPrgObligations> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderPrgObligations.class, mmMapping);
		final List<OffenderPrgObligations> returnList = namedParameterJdbcTemplate.query(sql, createParams(),
				columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offBkgOnCheckDeleteMaster
	 *
	 * @param params
	 *
	 */
	public List<OffenderCaseNotes> offBkgOnCheckDeleteMaster(OffenderCaseNotes paramBean) {
		final String sql = getQuery("OIDOWREL_OFF_BKG_ONCHECKDELETEMASTER");
		final RowMapper<OffenderCaseNotes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderCaseNotes.class,
				mmMapping);
		final List<OffenderCaseNotes> returnList = namedParameterJdbcTemplate.query(sql, createParams(),
				columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offPrgObligationsOnCheckDeleteMaster
	 *
	 * @param params
	 *
	 */
	public List<OffenderProgramProfiles> offPrgObligationsOnCheckDeleteMaster(OffenderProgramProfiles paramBean) {
		final String sql = getQuery("OIDOWREL_OFF_PRG_OBLIGATIONS_ONCHECKDELETEMASTER");
		final RowMapper<OffenderProgramProfiles> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderProgramProfiles.class, mmMapping);
		final List<OffenderProgramProfiles> returnList = namedParameterJdbcTemplate.query(sql, createParams(),
				columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offProgramProfilesPostQuery
	 *
	 * @param params
	 *
	 */
	public Corporates offProgramProfilesPostQuery(Corporates paramBean) {
		final String sql = getQuery("OIDOWREL_OFF_PROGRAM_PROFILES_POSTQUERY");
		final RowMapper<Corporates> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Corporates.class, mmMapping);
		Corporates returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offProgramProfilesOnCheckDeleteMaster
	 *
	 * @param params
	 *
	 */
	public List<VOffenderCourseEvents> offProgramProfilesOnCheckDeleteMaster(VOffenderCourseEvents paramBean) {
		final String sql = getQuery("OIDOWREL_OFF_PROGRAM_PROFILES_ONCHECKDELETEMASTER");
		final RowMapper<VOffenderCourseEvents> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				VOffenderCourseEvents.class, mmMapping);
		final List<VOffenderCourseEvents> returnList = namedParameterJdbcTemplate.query(sql, createParams(),
				columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * vOffenderCourseEventsPostQuery
	 *
	 * @param params
	 *
	 */
	public Corporates vOffenderCourseEventsPostQuery(Corporates paramBean) {
		final String sql = getQuery("OIDOWREL_V_OFFENDER_COURSE_EVENTS_POSTQUERY");
		final RowMapper<Corporates> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Corporates.class, mmMapping);
		Corporates returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * vOffenderCourseEventsWhenNewRecordInstance
	 *
	 * @param params
	 *
	 */
	public Object vOffenderCourseEventsWhenNewRecordInstance(OffenderExternalMovements paramBean) {
		final String sql = getQuery("OIDOWREL_V_OFFENDER_COURSE_EVENTS_WHENNEWRECORDINSTANCE");
		final RowMapper<OffenderExternalMovements> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderExternalMovements.class, mmMapping);
		OffenderExternalMovements returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * vOffenderCourseEventsWhenNewRecordInstance
	 *
	 * mmMapping * @param params
	 *
	 */
	public List<VOffenderCourseEvents> vOffenderCourseEventsWhenNewRecordInstance(VOffenderCourseEvents paramBean) {
		final String sql = getQuery("OIDOWREL_V_OFFENDER_COURSE_EVENTS_WHENNEWRECORDINSTANCE");
		final RowMapper<VOffenderCourseEvents> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				VOffenderCourseEvents.class, mmMapping);
		final ArrayList<VOffenderCourseEvents> returnList = (ArrayList<VOffenderCourseEvents>) namedParameterJdbcTemplate
				.query(sql, createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offenderCaseNotesPreInsert
	 *
	 * @param params
	 *
	 */
	public ReferenceCodes offenderCaseNotesPreInsert(ReferenceCodes paramBean) {
		final String sql = getQuery("OIDOWREL_OFFENDER_CASE_NOTES_PREINSERT");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				mmMapping);
		ReferenceCodes returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * createFormGlobals
	 *
	 * @param params
	 *
	 */
	public OmsModules createFormGlobals(OmsModules paramBean) {
		final String sql = getQuery("OIDOWREL_CREATE_FORM_GLOBALS");
		final RowMapper<OmsModules> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OmsModules.class, mmMapping);
		OmsModules returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * callOcuoscpv
	 *
	 * @param params
	 *
	 */
	public ProgramServices callOcuoscpv(ProgramServices paramBean) {
		final String sql = getQuery("OIDOWREL_CALL_OCUOSCPV");
		final RowMapper<ProgramServices> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ProgramServices.class,
				mmMapping);
		ProgramServices returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * callOcuupsta
	 *
	 * @param params
	 *
	 */
	public ProgramServices callOcuupsta(ProgramServices paramBean) {
		final String sql = getQuery("OIDOWREL_CALL_OCUUPSTA");
		final RowMapper<ProgramServices> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ProgramServices.class,
				mmMapping);
		ProgramServices returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * createAllocation
	 *
	 * @param params
	 *
	 */
	public CourseActivities createAllocation(CourseActivities paramBean) {
		final String sql = getQuery("OIDOWREL_CREATE_ALLOCATION");
		final RowMapper<CourseActivities> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, CourseActivities.class,
				mmMapping);
		CourseActivities returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * chkDeleteRule
	 *
	 * @param params
	 *
	 */
	public List<OffenderCourseAttendances> chkDeleteRule(OffenderCourseAttendances paramBean) {
		final String sql = getQuery("OIDOWREL_CHK_DELETE_RULE");
		final RowMapper<OffenderCourseAttendances> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderCourseAttendances.class, mmMapping);
		final ArrayList<OffenderCourseAttendances> returnList = (ArrayList<OffenderCourseAttendances>) namedParameterJdbcTemplate
				.query(sql, createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * displayAssessmentButton
	 *
	 * @param params
	 *
	 */
	public ProgramAssessments displayAssessmentButton(ProgramAssessments paramBean) {
		final String sql = getQuery("OIDOWREL_DISPLAY_ASSESSMENT_BUTTON");
		final RowMapper<ProgramAssessments> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ProgramAssessments.class, mmMapping);
		ProgramAssessments returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	@Override
	public VCourseActivities getCourseActivityData(Long crsActId) {
		final String sql = getQuery("OIDOWREL_GET_COURSE_ACTIVITYDATA");
		VCourseActivities bean = new VCourseActivities();
		final RowMapper<VCourseActivities> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, VCourseActivities.class,
				mmMapping);
		try {
			bean = namedParameterJdbcTemplate.queryForObject(sql, createParams("crsActId", crsActId), columnRowMapper);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " getCourseActivityData");
		}
		return bean;
	}

	@Override
	public String getOldStatus(final Long offenderPrgObligationId) {
		final String sql = getQuery("OIDOWRE_GET_OLD_DATA_OFFENDER_PRG_OBLIGATIONS");
		String status = null;
		try {
			status = namedParameterJdbcTemplate.queryForObject(sql, createParams("", offenderPrgObligationId),
					String.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " getOldStatus");
		}
		return status;
	}

	@Override
	public Long getseqPrgRefIdSeq() {
		final String sql = getQuery("OIDOWRE_GET_OFF_PRGREF_ID_SEQ");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Long.class);
	}

	@Override
	public ReferenceCodes getReferenceCode(String caseLoadType) {
		final String sql = getQuery("OIDOWRE_GET_REFERENCE_CODES_NOTE_SOURCE");
		ReferenceCodes bean = new ReferenceCodes();
		try {
			bean = namedParameterJdbcTemplate.queryForObject(sql, createParams("code", caseLoadType),
					new BeanPropertyRowMapper<ReferenceCodes>(ReferenceCodes.class));
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " getReferenceCode");
		}
		return bean;
	}

	@Override
	public String getNbtProviderName(Long crsActId) {
		final String sql = getQuery("OIDOWREL_GET_NBT_PROVIDER_NAME");
		String result = null;
		try {
			result = namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " getNbtProviderName");
		}
		return result;
	}
}
