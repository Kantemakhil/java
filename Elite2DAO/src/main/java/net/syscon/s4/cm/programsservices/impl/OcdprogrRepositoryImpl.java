package net.syscon.s4.cm.programsservices.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.cm.casemanagement.maintenance.beans.EventMeasures;
import net.syscon.s4.cm.programsservices.OcdprogrRepository;
import net.syscon.s4.cm.programsservices.VAcpProgress;
import net.syscon.s4.cm.programsservices.VOffenderPrgObligations;
import net.syscon.s4.common.beans.OffenderCourseAttendance;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.OracleSimpleJdbcCall;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.core.EliteDateRepository;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.InternalLocationUsages;
import net.syscon.s4.im.beans.OffenderNonAssociations;
import net.syscon.s4.im.beans.OffenderPrgObligations;
import net.syscon.s4.im.beans.OffenderProgramProfiles;
import net.syscon.s4.im.beans.VOffenderProgramProfiles;
import net.syscon.s4.inst.casemanagement.beans.ProgramServices;
import net.syscon.s4.inst.legals.beans.OffenderSentences;
import net.syscon.s4.inst.legals.beans.VOffenderSentenceEvents;
import oracle.jdbc.OracleTypes;

/**
 * Class OcdprogrRepositoryImpl
 * 
 * Class OcdprogrRepositoryImpl
 */
@Repository
public class OcdprogrRepositoryImpl extends RepositoryBase implements OcdprogrRepository {

	private final JdbcTemplate jdbcTemplate;
	@Autowired
	private EliteDateRepository dateRepository;

	private static Logger logger = LogManager.getLogger(OcdprogrRepositoryImpl.class.getName());

	public OcdprogrRepositoryImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/**
	 * Logger object used to print the log in the file
	 */
	private final Map<String, FieldMapper> vOffenderPrgObligationsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId")).put("ALLOCCOUNT", new FieldMapper("allocCount"))
			.put("COURSEPROFILE", new FieldMapper("courseProfile"))
			.put("CHKAPPOINTMENT", new FieldMapper("chkAppointment")).put("STATUSDESC", new FieldMapper("statusDesc"))

			.build();

	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("LIST_SEQ", new FieldMapper("listSeq")).put("EVENT_SUB_TYPE", new FieldMapper("eventSubType"))
			.put("GETDESCCODE", new FieldMapper("getdesccode")).put("CODE", new FieldMapper("code"))
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("INTERNAL_LOCATION_CODE", new FieldMapper("internalLocationCode")).build();
	private final Map<String, FieldMapper> vAcpProgressMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.build();
	private final Map<String, FieldMapper> offenderProgramProfilesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFPRGSTATUSDBVAL", new FieldMapper("offPrgStatusDbVal"))
			.put("OFF_END_DATE", new FieldMapper("offEndDate")).build();
	private final Map<String, FieldMapper> offenderCourseAttendancesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("STAFFNAME", new FieldMapper("staffName"))
			.put("EVENTOUTCOMEDBVAL", new FieldMapper("eventOutcomeDbVal")).build();
	private final Map<String, FieldMapper> prgServiceMapping = new ImmutableMap.Builder<String, FieldMapper>().build();
	private final Map<String, FieldMapper> vOffSentEventmapping = new ImmutableMap.Builder<String, FieldMapper>()
			.build();

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao VOffenderPrgObligations
	 *
	 * @return List<VOffenderPrgObligations>
	 *
	 * @throws SQLException
	 */
	public List<VOffenderPrgObligations> vOffPrgOblExecuteQuery(VOffenderPrgObligations objSearchDao) {
		final String sql = getQuery("OCDPROGR_VOFFPRGOBL_FIND_V_OFFENDER_PRG_OBLIGATIONS");
		final RowMapper<VOffenderPrgObligations> VOffenderPrgObligationsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				VOffenderPrgObligations.class, vOffenderPrgObligationsMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("OFFENDER_BOOK_ID", objSearchDao.getOffenderBookId()),
				VOffenderPrgObligationsRowMapper);
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstVOffenderPrgObligations List<VOffenderPrgObligations>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public Integer vOffPrgOblInsertVOffenderPrgObligations(
			final List<VOffenderPrgObligations> lstVOffenderPrgObligations) {
		String sql = getQuery("OCDPROGR_VOFFPRGOBL_INSERT_V_OFFENDER_PRG_OBLIGATIONS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<>();
		for (VOffenderPrgObligations vOffenderPrgObligations : lstVOffenderPrgObligations) {
			parameters.add(new BeanPropertySqlParameterSource(vOffenderPrgObligations));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstVOffenderPrgObligations.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstVOffenderPrgObligations List<VOffenderPrgObligations>
	 *
	 * @throws SQLException
	 */
	public Integer vOffPrgOblUpdateVOffenderPrgObligations(
			final List<VOffenderPrgObligations> lstVOffenderPrgObligations) {
		String sql = getQuery("OCDPROGR_VOFFPRGOBL_UPDATE_V_OFFENDER_PRG_OBLIGATIONS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<>();
		for (VOffenderPrgObligations vOffenderPrgObligations : lstVOffenderPrgObligations) {
			parameters.add(new BeanPropertySqlParameterSource(vOffenderPrgObligations));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstVOffenderPrgObligations.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstVOffenderPrgObligations List<VOffenderPrgObligations>
	 *
	 * @throws SQLException
	 */
	public Integer vOffPrgOblDeleteVOffenderPrgObligations(
			final List<VOffenderPrgObligations> lstVOffenderPrgObligations) {
		String sql = getQuery("OCDPROGR_VOFFPRGOBL_DELETE_V_OFFENDER_PRG_OBLIGATIONS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<>();
		for (VOffenderPrgObligations vOffenderPrgObligations : lstVOffenderPrgObligations) {
			parameters.add(new BeanPropertySqlParameterSource(vOffenderPrgObligations));
		}
		try {
			String tableName = "V_OFFENDER_PRG_OBLIGATIONS";
			String whereClause = "OFFENDER_PRG_OBLIGATION_ID = :offenderPrgObligationId";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method vOffPrgOblDeleteVOffenderPrgObligations", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstVOffenderPrgObligations.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao VAcpProgress
	 *
	 * @return List<VAcpProgress>
	 *
	 * @throws SQLException
	 */
	public List<VAcpProgress> vAcpProgressExecuteQuery(VAcpProgress objSearchDao) {
		final String sql = getQuery("OCDPROGR_VACPPROGRESS_FIND_V_ACP_PROGRESS");
		final RowMapper<VAcpProgress> VAcpProgressRowMapper = Row2BeanRowMapper.makeMapping(sql, VAcpProgress.class,
				vAcpProgressMapping);
		return namedParameterJdbcTemplate.query(sql,
				createParams("offender_prg_obligation_id", objSearchDao.getOffenderPrgObligationId()),
				VAcpProgressRowMapper);
	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstVAcpProgress List<VAcpProgress>
	 *
	 * @throws SQLException
	 */
	public Integer vAcpProgressUpdateVAcpProgress(final List<VAcpProgress> lstVAcpProgress) {
		String sql = getQuery("OCDPROGR_VACPPROGRESS_UPDATE_V_ACP_PROGRESS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (VAcpProgress vAcpProgress : lstVAcpProgress) {
			parameters.add(new BeanPropertySqlParameterSource(vAcpProgress));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstVAcpProgress.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstVAcpProgress List<VAcpProgress>
	 *
	 * @throws SQLException
	 */
	public Integer vAcpProgressPreDelete(final List<VOffenderPrgObligations> listObj) {
		String sql = getQuery("OCDPROGR_V_OFF_PRG_OBL_PREDELETE");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<>();
		for (VOffenderPrgObligations bean : listObj) {
			parameters.add(new BeanPropertySqlParameterSource(bean));
		}
		try {
			String tableName = "offender_program_profiles";
			String whereClause = "offender_prg_obligation_id = :offenderPrgObligationId";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method vAcpProgressPreDelete", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (listObj.size() == returnArray.length) {
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
	public List<OffenderProgramProfiles> offPgmProfExecuteQuery(OffenderProgramProfiles objSearchDao) {
		final String sql = getQuery("OCDPROGR_OFFPGMPROF_FIND_OFFENDER_PROGRAM_PROFILES");
		final RowMapper<OffenderProgramProfiles> OffenderProgramProfilesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderProgramProfiles.class, offenderProgramProfilesMapping);
		return namedParameterJdbcTemplate.query(sql,
				createParams("OFFENDER_PRG_OBLIGATION_ID", objSearchDao.getOffenderPrgObligationId()),
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
	public Integer offPgmProfInsertOffenderProgramProfiles(
			final List<OffenderProgramProfiles> lstOffenderProgramProfiles) {
		String sql = getQuery("OCDPROGR_OFFPGMPROF_INSERT_OFFENDER_PROGRAM_PROFILES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<>();
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
	 * This method is used to update the data base tables based on
	 *
	 * @param lstOffenderProgramProfiles List<OffenderProgramProfiles>
	 *
	 * @throws SQLException
	 */
	public Integer offPgmProfUpdateOffenderProgramProfiles(
			final List<OffenderProgramProfiles> lstOffenderProgramProfiles) {
		String sql = getQuery("OCDPROGR_OFFPGMPROF_UPDATE_OFFENDER_PROGRAM_PROFILES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (OffenderProgramProfiles offenderProgramProfiles : lstOffenderProgramProfiles) {
			parameters.add(new BeanPropertySqlParameterSource(offenderProgramProfiles));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(e);
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
	 * @param objSearchDao OffenderCourseAttendances
	 *
	 * @return List<OffenderCourseAttendances>
	 *
	 * @throws SQLException
	 */
	public List<OffenderCourseAttendance> offCrsAppExecuteQuery(OffenderCourseAttendance objSearchDao) {
		final String sql = getQuery("OCDPROGR_OFFCRSAPP_FIND_OFFENDER_COURSE_ATTENDANCES");
		final RowMapper<OffenderCourseAttendance> OffenderCourseAttendancesRowMapper = Row2BeanRowMapper
				.makeMapping(sql, OffenderCourseAttendance.class, offenderCourseAttendancesMapping);
		return namedParameterJdbcTemplate
				.query(sql,
						createParams("OFFENDER_PRG_OBLIGATION_ID", objSearchDao.getOffenderPrgObligationId(),
								"offenderBookId", objSearchDao.getOffenderBookId()),
						OffenderCourseAttendancesRowMapper);
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstOffenderCourseAttendances List<OffenderCourseAttendances>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public Integer offCrsAppInsertOffenderCourseAttendances(
			final List<OffenderCourseAttendance> lstOffenderCourseAttendances) {
		String sql = getQuery("OCDPROGR_OFFCRSAPP_INSERT_OFFENDER_COURSE_ATTENDANCES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (OffenderCourseAttendance offenderCourseAttendances : lstOffenderCourseAttendances) {
			parameters.add(new BeanPropertySqlParameterSource(offenderCourseAttendances));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenderCourseAttendances.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstOffenderCourseAttendances List<OffenderCourseAttendances>
	 *
	 * @throws SQLException
	 */
	public Integer offCrsAppUpdateOffenderCourseAttendances(
			final List<OffenderCourseAttendance> lstOffenderCourseAttendances) {
		String sql = getQuery("OCDPROGR_OFFCRSAPP_UPDATE_OFFENDER_COURSE_ATTENDANCES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (OffenderCourseAttendance offenderCourseAttendances : lstOffenderCourseAttendances) {
			parameters.add(new BeanPropertySqlParameterSource(offenderCourseAttendances));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenderCourseAttendances.size() == returnArray.length) {
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
	public List<ReferenceCodes> rgOffPrgStsRecordGroup() {
		final String sql = getQuery("OCDPROGR_FIND_RGOFFPRGSTS");
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
	 * @return List<MMInternalLocationUsages>
	 */
	public List<InternalLocationUsages> rgIntLocationRecordGroup(final String agyLocId) {
		final String sql = getQuery("OCDPROGR_FIND_RGINTLOCATION");
		final RowMapper<InternalLocationUsages> mMInternalLocationUsagesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				InternalLocationUsages.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams("AGYLOCID", agyLocId),
					mMInternalLocationUsagesRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ProgramServices> rgProgramServicesRecordGroup() {
		final String sql = getQuery("OCDPROGR_FIND_RGPROGRAMSERVICES");
		final RowMapper<ProgramServices> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ProgramServices.class,
				mMapping);

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
	public List<ReferenceCodes> rgPsPrgAvailRecordGroup() {
		final String sql = getQuery("OCDPROGR_FIND_RGPSPRGAVAIL");
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
	 * @return List<MMSentenceCalcTypesOffenderCases>
	 */
	public List<OffenderSentences> rgOffenderSentencesRecordGroup(final Integer offenderBookId) {
		final String sql = getQuery("OCDPROGR_FIND_RGOFFENDERSENTENCES");
		final RowMapper<OffenderSentences> mMSentenceCalcTypesOffenderCasesRowMapper = Row2BeanRowMapper
				.makeMapping(sql, OffenderSentences.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams("OFFENDERBOOKID", offenderBookId),
					mMSentenceCalcTypesOffenderCasesRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<EventMeasures> rgEventSubTypesRecordGroup() {
		final String sql = getQuery("OCDPROGR_FIND_RGEVENTSUBTYPES");
		final RowMapper<EventMeasures> mRowMapper = Row2BeanRowMapper.makeMapping(sql, EventMeasures.class, mMapping);

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
	public List<ReferenceCodes> rgOutcomeReasonsRecordGroup(final String eventType, final String eventSubType) {
		final String sql = getQuery("OCDPROGR_FIND_RGOUTCOMEREASONS");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql,
					createParams("EVENT_TYPE", eventType, "EVENT_SUB_TYPE", eventSubType), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<AgencyLocations> rgAgyLocIdRecordGroup(final String caseloadId) {
		final String sql = getQuery("OCDPROGR_FIND_RGAGYLOCID");
		final RowMapper<AgencyLocations> mRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams("CASELOADID", caseloadId), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ProgramServices> rgPhasesRecordGroup(final Integer programId) {
		final String sql = getQuery("OCDPROGR_FIND_RGPHASES");
		final RowMapper<ProgramServices> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ProgramServices.class,
				mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams("PROGRAMID", programId), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ProgramServices> rgModulesRecordGroup(final Integer phaseId) {
		final String sql = getQuery("OCDPROGR_FIND_RGMODULES");
		final RowMapper<ProgramServices> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ProgramServices.class,
				mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams("PHASEID", phaseId), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgEngagementRecordGroup() {
		final String sql = getQuery("OCDPROGR_FIND_RGENGAGEMENT");
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
	public List<ReferenceCodes> rgUnderstandingRecordGroup() {
		final String sql = getQuery("OCDPROGR_FIND_RGUNDERSTANDING");
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
	public List<ReferenceCodes> rgPsEndAllocRecordGroup() {
		final String sql = getQuery("OCDPROGR_FIND_RGPSENDALLOC");
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
	public List<ReferenceCodes> rgFutureAttendanceRecordGroup() {
		final String sql = getQuery("OCDPROGR_FIND_RGFUTUREATTENDANCE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
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
	public List<VOffenderPrgObligations> offBkgOnCheckDeleteMaster(VOffenderPrgObligations paramBean) {
		final String sql = getQuery("OCDPROGR_OFF_BKG_ONCHECKDELETEMASTER");
		final RowMapper<VOffenderPrgObligations> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				VOffenderPrgObligations.class, vOffenderPrgObligationsMapping);
		return namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
	}

	public ProgramServices getPrgSrvDetails(OffenderProgramProfiles paramBean) {
		final String sql = getQuery("OCDPROGR_GET_PRG_SRV_DETAILS");
		final RowMapper<ProgramServices> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ProgramServices.class,
				prgServiceMapping);
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("p_program_id", paramBean.getProgramId()),
				columnRowMapper);
	}

	public OffenderProgramProfiles getAllocationInfo(final OffenderProgramProfiles paramBean) {
		OffenderProgramProfiles bean = new OffenderProgramProfiles();
		SqlParameter[] sqlParameters = new SqlParameter[] { new SqlParameter("P_CRS_ACTY_ID", OracleTypes.INTEGER),
				new SqlOutParameter("P_PROVIDER_NAME", OracleTypes.VARCHAR),
				new SqlOutParameter("P_OCCURENCE_CODE", OracleTypes.VARCHAR) };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_SERVICE").withProcedureName("GET_ALLOCATION_INFO")
				.declareParameters(sqlParameters);
		Map<String, Object> inParamMap = new HashMap<>();
		inParamMap.put("P_CRS_ACTY_ID", paramBean.getCrsActyId());

		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		final Map<String, Object> returnObject = simpleJDBCCall.execute(inParameter);

		bean.setProviderName(
				returnObject.get("P_PROVIDER_NAME") != null ? String.valueOf(returnObject.get("P_PROVIDER_NAME"))
						: null);
		bean.setOccuranceCode(
				returnObject.get("P_OCCURENCE_CODE") != null ? String.valueOf(returnObject.get("P_OCCURENCE_CODE"))
						: null);
		return bean;
	}

	public Date getOffenderDates(final VHeaderBlock bean) {
		final Map<String, Object> inParamMap = new HashMap<>();
		SqlParameter[] sqlParameters = new SqlParameter[1];
		Map<String, Object> returnValObj = null;
		sqlParameters = new SqlParameter[] { new SqlParameter("P_OFFENDER_BOOK_ID", OracleTypes.NUMBER),
				new SqlOutParameter("P_OFFENDER_START_DATE", OracleTypes.DATE) };
		final SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_PROGRAMMES").withProcedureName("GET_OFFENDER_DATES")
				.declareParameters(sqlParameters);
		inParamMap.put("P_OFFENDER_BOOK_ID", bean.getOffenderBookId());
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		returnValObj = simpleJDBCCall.execute(inParameter);
		return (Date) returnValObj.get("P_OFFENDER_START_DATE");
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgObligationSource() {
		final String sql = getQuery("OCDPROGR_FIND_RGOBLIGATIONSOURCE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	public Integer checkActiveObligation(List<BigDecimal> offBkgId, List<BigDecimal> programid) {
		final String sql = getQuery("OCDPROGR_CHECK_ACTIVE_OBLIGATION");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("P_OFFENDER_BOOK_ID", offBkgId, "P_PROGRAM_ID", programid), Integer.class);
	}

	public OffenderCourseAttendance getprogInfo(final OffenderCourseAttendance paramBean) {
		OffenderCourseAttendance bean = new OffenderCourseAttendance();
		SqlParameter[] sqlParameters = new SqlParameter[] { new SqlParameter("P_OFF_PRGREF_ID", OracleTypes.INTEGER),
				new SqlOutParameter("P_PHASE_ID", OracleTypes.INTEGER),
				new SqlOutParameter("P_PHASE_DESCRIPTION", OracleTypes.VARCHAR),
				new SqlOutParameter("P_MODULE_ID", OracleTypes.INTEGER),
				new SqlOutParameter("P_MODULE_DESCRIPTION", OracleTypes.VARCHAR) };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_PROGRAMMES").withProcedureName("GET_PROGRAM_INFO")
				.declareParameters(sqlParameters);
		Map<String, Object> inParamMap = new HashMap<>();
		inParamMap.put("P_OFF_PRGREF_ID", paramBean.getOffPrgrefId());

		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		final Map<String, Object> returnObject = simpleJDBCCall.execute(inParameter);
		bean.setPhaseId(
				returnObject.get("P_PHASE_ID") != null ? Integer.valueOf(returnObject.get("P_PHASE_ID").toString())
						: null);
		bean.setModuleId(
				returnObject.get("P_MODULE_ID") != null ? Integer.valueOf(returnObject.get("P_MODULE_ID").toString())
						: null);
		// bean.setPhaseId(Integer.valueOf(returnObject.get("P_PHASE_ID").toString()));
		// bean.setModuleId(Integer.valueOf(returnObject.get("P_MODULE_ID").toString()));
		return bean;
	}

	@Override
	public Integer checkScheduleConflict(final OffenderCourseAttendance bean) {
		final String sql = getQuery("OCDPROGR_TAG_SCHEDULE_CHECK_SCHEDULE_CONFLICT");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql,
					createParams("offenderBookId", bean.getOffenderBookId(), "eventDate", bean.getEventDate()),
					Integer.class);
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public Boolean checkUaEventOutcome(OffenderCourseAttendance bean) {
		SqlParameterSource args = new MapSqlParameterSource().addValue("p_event_type", bean.getEventType())
				.addValue("p_event_sub_type", bean.getEventSubType())
				.addValue("p_event_outcome", bean.getEventOutcome());

		SimpleJdbcCall jdbcCall = new OracleSimpleJdbcCall(jdbcTemplate).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_MULTIPLE_FAILURE").withFunctionName("CHECK_UA_EVENT_OUTCOME")
				.withoutProcedureColumnMetaDataAccess();

		jdbcCall.declareParameters(new SqlOutParameter("RETURN", Types.BOOLEAN),
				new SqlParameter("p_event_type", Types.VARCHAR), new SqlParameter("p_event_sub_type", Types.VARCHAR),
				new SqlParameter("p_event_outcome", Types.VARCHAR));

		Boolean result = jdbcCall.executeFunction(Boolean.class, args);
		return result;
	}

	@Override
	public List<VOffenderSentenceEvents> failureCur(OffenderCourseAttendance bean) {
		final String sql = getQuery("OCDPROGR_FAIL_CURSOR");
		final RowMapper<VOffenderSentenceEvents> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				VOffenderSentenceEvents.class, vOffSentEventmapping);
		return namedParameterJdbcTemplate.query(sql, createParams("p_event_id", bean.getEventId()), columnRowMapper);
	}

	@Override
	public Integer countSentenceUa(final VOffenderSentenceEvents bean) {

		final String sql = getQuery("OCDPROGR_COUNT_SENTENCE_UA");
		return namedParameterJdbcTemplate.queryForObject(
				sql, createParams("p_offender_book_id", bean.getOffenderBookId(), "p_sentence_seq",
						bean.getSentenceSeq(), "p_event_date", bean.getEventDate(), "p_event_id", bean.getEventId()),
				Integer.class);

		// Map<String, Object> returnObject = null;
		// final Map<String, Object> inParamMap = new HashMap<>();
		// SqlParameter[] sqlParameters = new SqlParameter[5];
		// sqlParameters = new SqlParameter[] {
		// new SqlParameter("P_OFFENDER_BOOK_ID", Types.NUMERIC),
		// new SqlParameter("P_SENTENCE_SEQ", Types.NUMERIC),
		// new SqlParameter("P_EVENT_DATE", Types.DATE),
		// new SqlParameter("P_EVENT_ID", Types.NUMERIC),
		// new SqlOutParameter("RETURN", Types.NUMERIC) };
		// SimpleJdbcCall simpleJDBCCall = new
		// SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
		// .withCatalogName("TAG_MULTIPLE_FAILURE").withFunctionName("COUNT_SENTENCE_UA")
		// .declareParameters(sqlParameters);
		// inParamMap.put("P_OFFENDER_BOOK_ID", bean.getOffenderBookId());
		// inParamMap.put("P_SENTENCE_SEQ", bean.getOffenderBookId());
		// inParamMap.put("P_EVENT_DATE", bean.getEventDate());
		// inParamMap.put("P_EVENT_ID", bean.getEventId());
		//
		//
		// final MapSqlParameterSource inParameter = new
		// MapSqlParameterSource(inParamMap);
		//
		// try {
		// returnObject = simpleJDBCCall.execute(inParameter);
		// return (Integer) returnObject.get("RETURN");
		// } catch (Exception e) {
		// return 0;
		// }
	}

	@Override
	public String prgApptEventClass(final OffenderCourseAttendance bean) {
		Map<String, Object> returnObject = null;
		final Map<String, Object> inParamMap = new HashMap<>();
		SqlParameter[] sqlParameters = new SqlParameter[5];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_OFFENDER_BOOK_ID", Types.NUMERIC),
				new SqlParameter("P_AGY_LOC_ID", Types.VARCHAR), new SqlOutParameter("RETURN", Types.VARCHAR) };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_PRG").withFunctionName("PRG_APPT_EVENT_CLASS").declareParameters(sqlParameters);
		inParamMap.put("P_OFFENDER_BOOK_ID", bean.getOffenderBookId());
		inParamMap.put("P_AGY_LOC_ID", bean.getAgyLocId());
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			return (String) returnObject.get("RETURN");
		} catch (Exception e) {
			return null;
		}
	}

	public Integer adjustUa(final OffenderCourseAttendance paramBean) {
		SqlParameter[] sqlParameters = new SqlParameter[] { new SqlParameter("P_EVENT_ID", OracleTypes.INTEGER),
				new SqlParameter("P_EVENT_TYPE", OracleTypes.VARCHAR),
				new SqlParameter("P_EVENT_SUB_TYPE", OracleTypes.VARCHAR),
				new SqlParameter("P_OLD_OUTCOME", OracleTypes.VARCHAR),
				new SqlParameter("P_NEW_OUTCOME", OracleTypes.VARCHAR),
				new SqlParameter("P_COUNT_FLAG", OracleTypes.VARCHAR), };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_MULTIPLE_FAILURE").withProcedureName("ADJUST_UA")
				.declareParameters(sqlParameters);
		Map<String, Object> inParamMap = new HashMap<>();
		inParamMap.put("P_EVENT_ID", paramBean.getEventId());
		inParamMap.put("P_EVENT_TYPE", paramBean.getEventType());
		inParamMap.put("P_EVENT_SUB_TYPE", paramBean.getEventSubType());
		inParamMap.put("P_OLD_OUTCOME", paramBean.getEventOutcomeDbVal());
		inParamMap.put("P_NEW_OUTCOME", paramBean.getEventOutcome());
		inParamMap.put("P_COUNT_FLAG", paramBean.getUnexcusedAbsenceFlag());

		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		simpleJDBCCall.execute(inParameter);

		return 0;
	}

	@Override
	public Boolean validAllocationEndDate(OffenderProgramProfiles bean) {
		SqlParameterSource args = new MapSqlParameterSource().addValue("P_OFF_PRGREF_ID", bean.getOffPrgrefId())
				.addValue("P_END_DATE", bean.getOffEndDate());
		SimpleJdbcCall jdbcCall = new OracleSimpleJdbcCall(jdbcTemplate).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_PROGRAMMES").withFunctionName("VALID_ALLOCATION_END_DATE")
				.withoutProcedureColumnMetaDataAccess();

		jdbcCall.declareParameters(new SqlOutParameter("RETURN", Types.BOOLEAN),
				new SqlParameter("P_OFF_PRGREF_ID", Types.INTEGER), new SqlParameter("P_END_DATE", Types.DATE));

		Boolean result = jdbcCall.executeFunction(Boolean.class, args);
		return result;
	}

	@Override
	public Boolean checkModuleAllocated(OffenderProgramProfiles bean) {
		SqlParameterSource args = new MapSqlParameterSource()

				.addValue("P_OFFENDER_BOOK_ID", bean.getOffenderBookId()).addValue("P_CRS_ACTY_ID", bean.getCrsActyId())
				.addValue("P_MODULE_FROM", bean.getOffPrgrefId()).addValue("P_MODULE_TO", bean.getOffEndDate());
		SimpleJdbcCall jdbcCall = new OracleSimpleJdbcCall(jdbcTemplate).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_PROGRAMMES").withFunctionName("check_module_allocated")
				.withoutProcedureColumnMetaDataAccess();

		jdbcCall.declareParameters(new SqlOutParameter("RETURN", Types.BOOLEAN),
				new SqlParameter("P_OFFENDER_BOOK_ID", Types.INTEGER), new SqlParameter("P_CRS_ACTY_ID", Types.INTEGER),
				new SqlParameter("P_MODULE_FROM", Types.INTEGER), new SqlParameter("P_MODULE_TO", Types.INTEGER));

		Boolean result = jdbcCall.executeFunction(Boolean.class, args);
		return result;
	}

	@Override
	public Boolean checkSessionAllocated(OffenderProgramProfiles bean) {
		SqlParameterSource args = new MapSqlParameterSource()

				.addValue("P_OFFENDER_BOOK_ID", bean.getOffenderBookId()).addValue("P_CRS_ACTY_ID", bean.getCrsActyId())
				.addValue("P_START_SESSION_NO", bean.getStartSessionNo())
				.addValue("P_OFFENDER_START_DATE", bean.getOffenderStartDate())
				.addValue("P_OFFENDER_END_DATE", bean.getOffenderEndDate());
		SimpleJdbcCall jdbcCall = new OracleSimpleJdbcCall(jdbcTemplate).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_PROGRAMMES").withFunctionName("CHECK_SESSION_ALLOCATED")
				.withoutProcedureColumnMetaDataAccess();

		jdbcCall.declareParameters(new SqlOutParameter("RETURN", Types.BOOLEAN),
				new SqlParameter("P_OFFENDER_BOOK_ID", Types.INTEGER), new SqlParameter("P_CRS_ACTY_ID", Types.INTEGER),
				new SqlParameter("P_START_SESSION_NO", Types.INTEGER),
				new SqlParameter("P_OFFENDER_START_DATE", Types.DATE),
				new SqlParameter("P_OFFENDER_END_DATE", Types.DATE));

		Boolean result = jdbcCall.executeFunction(Boolean.class, args);
		return result;
	}

	public Integer updateModuleAllocations(final OffenderProgramProfiles paramBean) {
		SqlParameter[] sqlParameters = new SqlParameter[] {
				new SqlParameter("P_PHASE_ALLOCATION_ID", OracleTypes.INTEGER),
				new SqlParameter("P_PHASE_CRS_ACTY_ID", OracleTypes.INTEGER),
				new SqlParameter("P_OFFENDER_BOOK_ID", OracleTypes.INTEGER),
				new SqlParameter("P_OFFENDER_PRG_OBLIGATION_ID", OracleTypes.INTEGER),
				new SqlParameter("P_LIST_SEQ_BEGIN", OracleTypes.INTEGER),
				new SqlParameter("P_LIST_SEQ_END", OracleTypes.INTEGER), };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_PROGRAMMES").withProcedureName("UPDATE_MODULE_ALLOCATIONS")
				.declareParameters(sqlParameters);
		Map<String, Object> inParamMap = new HashMap<>();
		inParamMap.put("P_PHASE_ALLOCATION_ID", paramBean.getOffPrgrefId());
		inParamMap.put("P_PHASE_CRS_ACTY_ID", paramBean.getCrsActyId());
		inParamMap.put("P_OFFENDER_BOOK_ID", paramBean.getOffenderBookId());
		inParamMap.put("P_OFFENDER_PRG_OBLIGATION_ID", paramBean.getOffenderPrgObligationId());
		inParamMap.put("P_LIST_SEQ_BEGIN", paramBean.getModuleFrom());
		inParamMap.put("P_LIST_SEQ_END", paramBean.getModuleTo());

		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			simpleJDBCCall.execute(inParameter);
		} catch (Exception e) {

		}

		return 0;
	}

	public Integer createOppAttendances(final OffenderProgramProfiles paramBean) {
		SqlParameter[] sqlParameters = new SqlParameter[] { new SqlParameter("P_OFF_PRGREF_ID", OracleTypes.INTEGER) };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_PROGRAMMES").withProcedureName("CREATE_OPP_ATTENDANCES")
				.declareParameters(sqlParameters);
		Map<String, Object> inParamMap = new HashMap<>();
		inParamMap.put("P_OFF_PRGREF_ID", paramBean.getOffPrgrefId());

		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			simpleJDBCCall.execute(inParameter);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}

	public Integer updateStatus(final OffenderProgramProfiles paramBean) {
		SqlParameter[] sqlParameters = new SqlParameter[] {
				new SqlParameter("P_OFFENDER_PRG_OBLIGATION_ID", OracleTypes.INTEGER),
				new SqlParameter("P_STATUS", OracleTypes.VARCHAR), new SqlParameter("P_REASON", OracleTypes.VARCHAR),
				new SqlParameter("P_DATE", OracleTypes.DATE) };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_PROGRAMMES").withProcedureName("UPDATE_STATUS").declareParameters(sqlParameters);
		Map<String, Object> inParamMap = new HashMap<>();
		inParamMap.put("P_OFFENDER_PRG_OBLIGATION_ID", paramBean.getOffenderPrgObligationId());
		inParamMap.put("P_STATUS", "ALLOC");
		inParamMap.put("P_REASON", null);
		inParamMap.put("P_DATE", dateRepository.getDBTime());
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			simpleJDBCCall.execute(inParameter);
		} catch (Exception e) {

		}

		return 0;
	}

	public Integer endModuleAllocations(final OffenderProgramProfiles paramBean) {
		SqlParameter[] sqlParameters = new SqlParameter[] {
				new SqlParameter("P_PARENT_OFF_PRGREF_ID", OracleTypes.INTEGER),
				new SqlParameter("P_END_DATE", OracleTypes.DATE) };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_PROGRAMMES").withProcedureName("END_MODULE_ALLOCATIONS")
				.declareParameters(sqlParameters);
		Map<String, Object> inParamMap = new HashMap<>();
		inParamMap.put("P_PARENT_OFF_PRGREF_ID", paramBean.getOffPrgrefId());
		inParamMap.put("P_END_DATE", paramBean.getOffenderEndDate());
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			simpleJDBCCall.execute(inParameter);
		} catch (Exception e) {

		}

		return 0;
	}

	public Integer cancelModuleAllocations(final OffenderProgramProfiles paramBean) {
		SqlParameter[] sqlParameters = new SqlParameter[] {
				new SqlParameter("P_PARENT_OFF_PRGREF_ID", OracleTypes.INTEGER) };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_PROGRAMMES").withProcedureName("CANCEL_MODULE_ALLOCATIONS")
				.declareParameters(sqlParameters);
		Map<String, Object> inParamMap = new HashMap<>();
		inParamMap.put("P_PARENT_OFF_PRGREF_ID", paramBean.getOffPrgrefId());
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			simpleJDBCCall.execute(inParameter);
		} catch (Exception e) {

		}

		return 0;
	}

	public Integer deleteOppAttendances(final OffenderProgramProfiles paramBean) {
		SqlParameter[] sqlParameters = new SqlParameter[] { new SqlParameter("P_OFF_PRGREF_ID", OracleTypes.INTEGER),
				new SqlParameter("P_END_DATE", OracleTypes.DATE) };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_PROGRAMMES").withProcedureName("DELETE_OPP_ATTENDANCES")
				.declareParameters(sqlParameters);
		Map<String, Object> inParamMap = new HashMap<>();
		inParamMap.put("P_OFF_PRGREF_ID", paramBean.getOffPrgrefId());
		inParamMap.put("P_END_DATE", paramBean.getOffEndDate());
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			simpleJDBCCall.execute(inParameter);
		} catch (Exception e) {

		}

		return 0;
	}

	public OffenderProgramProfiles getAllocationListSeqRange(final OffenderProgramProfiles paramBean) {
		OffenderProgramProfiles bean = new OffenderProgramProfiles();
		SqlParameter[] sqlParameters = new SqlParameter[] {
				new SqlParameter("P_PARENT_OFF_PRGREF_ID", OracleTypes.INTEGER),
				new SqlOutParameter("P_LIST_SEQ_FROM", OracleTypes.INTEGER),
				new SqlOutParameter("P_LIST_SEQ_TO", OracleTypes.INTEGER) };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_PROGRAMMES").withProcedureName("GET_ALLOCATION_LIST_SEQ_RANGE")
				.declareParameters(sqlParameters);
		Map<String, Object> inParamMap = new HashMap<>();
		inParamMap.put("P_PARENT_OFF_PRGREF_ID", paramBean.getOffPrgrefId());
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		final Map<String, Object> returnObject = simpleJDBCCall.execute(inParameter);

		bean.setModuleFrom(Integer.valueOf(returnObject.get("P_LIST_SEQ_FROM").toString()));
		bean.setModuleTo(Integer.valueOf(returnObject.get("P_LIST_SEQ_TO").toString()));
		return bean;
	}

	@Override
	public Boolean checkAttendanceTaken(OffenderProgramProfiles bean) {
		SqlParameterSource args = new MapSqlParameterSource().addValue("P_OFF_PRGREF_ID", bean.getOffenderBookId())
				.addValue("P_END_DATE", bean.getOffenderEndDate());
		SimpleJdbcCall jdbcCall = new OracleSimpleJdbcCall(jdbcTemplate).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_PROGRAMMES").withFunctionName("CHECK_ATTENDANCE_TAKEN")
				.withoutProcedureColumnMetaDataAccess();
		jdbcCall.declareParameters(new SqlOutParameter("RETURN", Types.BOOLEAN),
				new SqlParameter("P_OFF_PRGREF_ID", Types.INTEGER), new SqlParameter("P_END_DATE", Types.DATE));

		Boolean result = jdbcCall.executeFunction(Boolean.class, args);
		return result;
	}

	@Override
	public Boolean checkAttendanceOutcomes(OffenderProgramProfiles bean) {
		SqlParameterSource args = new MapSqlParameterSource().addValue("P_OFF_PRGREF_ID", bean.getOffPrgrefId());
		SimpleJdbcCall jdbcCall = new OracleSimpleJdbcCall(jdbcTemplate).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_PROGRAMMES").withFunctionName("CHECK_ATTENDANCE_OUTCOMES")
				.withoutProcedureColumnMetaDataAccess();
		jdbcCall.declareParameters(new SqlOutParameter("RETURN", Types.BOOLEAN),
				new SqlParameter("P_OFF_PRGREF_ID", Types.INTEGER));

		Boolean result = jdbcCall.executeFunction(Boolean.class, args);
		return result;
	}

	@Override
	public Boolean checkAllocationExists(OffenderProgramProfiles bean) {
		SqlParameterSource args = new MapSqlParameterSource()
				.addValue("P_OFFENDER_PRG_OBLIGATION_ID", bean.getOffenderPrgObligationId())
				.addValue("P_CRS_ACTY_ID", bean.getCrsActyId()).addValue("P_PARENT_OFF_PRGREF_ID", null);
		SimpleJdbcCall jdbcCall = new OracleSimpleJdbcCall(jdbcTemplate).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_PROGRAMMES").withFunctionName("CHECK_ALLOCATION_EXISTS")
				.withoutProcedureColumnMetaDataAccess();
		jdbcCall.declareParameters(new SqlOutParameter("RETURN", Types.BOOLEAN),
				new SqlParameter("P_OFFENDER_PRG_OBLIGATION_ID", Types.INTEGER),
				new SqlParameter("P_CRS_ACTY_ID", Types.INTEGER),
				new SqlParameter("P_PARENT_OFF_PRGREF_ID", Types.INTEGER));

		Boolean result = jdbcCall.executeFunction(Boolean.class, args);
		return result;
	}

	@Override
	public OffenderProgramProfiles getCrsDetails(final OffenderProgramProfiles object) {
		Map<String, Object> returnObject = null;
		final SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		OffenderProgramProfiles returnValue = new OffenderProgramProfiles();
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[5];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_CRS_ACTY_ID", OracleTypes.NUMBER),
				new SqlOutParameter("P_START_DATE", OracleTypes.DATE),
				new SqlOutParameter("P_END_DATE", OracleTypes.DATE),
				new SqlOutParameter("P_PROGRAM_ID", OracleTypes.NUMBER) };

		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_SERVICE").withProcedureName("get_crs_details").declareParameters(sqlParameters);
		inParamMap.put("P_CRS_ACTY_ID", object.getCrsActyId());

		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = (Map<String, Object>) simpleJDBCCall.withoutProcedureColumnMetaDataAccess()
					.execute(inParameter);
			returnValue.setOffenderStartDate(returnObject.get("P_START_DATE") != null
					? sdf1.parse(String.valueOf(returnObject.get("P_START_DATE")))
					: null);
			returnValue.setOffenderEndDate(
					returnObject.get("P_END_DATE") != null ? sdf1.parse(String.valueOf(returnObject.get("P_END_DATE")))
							: null);
			returnValue.setProgramId(returnObject.get("P_PROGRAM_ID") != null
					? Long.valueOf(String.valueOf(returnObject.get("P_PROGRAM_ID")))
					: null);
		} catch (Exception e) {
			return returnValue;
		}
		return returnValue;
	}

	public OffenderProgramProfiles getListSeqRange(final OffenderProgramProfiles paramBean) {
		OffenderProgramProfiles bean = new OffenderProgramProfiles();
		SqlParameter[] sqlParameters = new SqlParameter[] { new SqlParameter("P_CRS_ACTY_ID", OracleTypes.INTEGER),
				new SqlOutParameter("P_LIST_SEQ_FROM", OracleTypes.INTEGER),
				new SqlOutParameter("P_LIST_SEQ_TO", OracleTypes.INTEGER) };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_PROGRAMMES").withProcedureName("get_list_seq_range")
				.declareParameters(sqlParameters);
		Map<String, Object> inParamMap = new HashMap<>();
		inParamMap.put("P_CRS_ACTY_ID", paramBean.getOffPrgrefId());
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		final Map<String, Object> returnObject = simpleJDBCCall.execute(inParameter);

		bean.setModuleFrom(Integer.valueOf(returnObject.get("P_LIST_SEQ_FROM").toString()));
		bean.setModuleTo(Integer.valueOf(returnObject.get("P_LIST_SEQ_TO").toString()));
		return bean;
	}

	@Override
	public Long getOffPrgrefId() {
		final String sql = getQuery("OCDPROGR_GET_OFF_PRG_GRE_ID");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Long.class);
	}

	@Override
	public Integer getOffPrgrefIdOne(Long offenderPrgObligationId, Long programId) {
		final String sql = getQuery("OCDPROGR_GET_OFF_PRGREF_ID_ONE");
		Integer result = null;
		try {
			result = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("P_OFFENDER_PRG_OBLIGATION_ID", offenderPrgObligationId, "P_PROGRAM_ID", programId),
					Integer.class);
		} catch (Exception e) {

		}
		return result;
	}

	public List<OffenderNonAssociations> checkNonAssociations(final OffenderCourseAttendance paramBean) {
		final String sql = getQuery("OCDPROGR_CHK_NONASSOCIATIONS");
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

	public List<OffenderCourseAttendance> checkNonAssociationAppointments(final OffenderCourseAttendance search,
			final OffenderNonAssociations offNonAss) {
		final String sql = getQuery("OCDPROGR_CHK_NONASSOCIATION_APPOINTMENTS");
		List<OffenderCourseAttendance> returnList = new ArrayList<OffenderCourseAttendance>();
		final RowMapper<OffenderCourseAttendance> VAcpProgressRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderCourseAttendance.class, vAcpProgressMapping);
		try {
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("offenderBookId", offNonAss.getNsOffenderBookId(), "eventDate", search.getEventDate(),
							"agyLocId", search.getAgyLocId(), "startTime", search.getStartTime(), "endTime",
							search.getEndTime()),
					VAcpProgressRowMapper);
		} catch (Exception e) {
			return returnList;
		}
		return returnList;
	}

	public VHeaderBlock ocdprogrGetOffenderNames(final BigDecimal offBookId, String userName) {
		final String sql = getQuery("OCDPROGR_GET_OFFENDER_NAMES");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("nsOffenderBookId", offBookId, "USERID", userName),
				new BeanPropertyRowMapper<VHeaderBlock>(VHeaderBlock.class));
	}

	@Override
	public OffenderPrgObligations gettingOldData(BigDecimal offenderPrgObligationId) {
		final String sql = getQuery("OCDPROGR_GETTING_OLD_RECORD");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("offenderPrgPbligationId", offenderPrgObligationId),
				new BeanPropertyRowMapper<OffenderPrgObligations>(OffenderPrgObligations.class));

	}

	@Override
	public BigDecimal gettingOffenderPrgObligationId() {
		final String sql = getQuery("OCDPROGR_GETTING_OFFENDER_PRG_OBLIGATION_ID");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), (BigDecimal.class));

	}

	@Override
	public OffenderProgramProfiles gettingOffenderProgramStatus(BigDecimal offPrgrefId) {
		final String sql = getQuery("OCDPROGR_GETTING_OFFENDER_PROGRAM_PROFILE_RECORD");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("offPrgrefId", offPrgrefId),
				new BeanPropertyRowMapper<OffenderProgramProfiles>(OffenderProgramProfiles.class));

	}

	@Override
	public Long gettingEventId() {
		final String sql = getQuery("OCDPROGR_GETTING_EVENT_ID");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), (Long.class));
	}

	@Override
	public Long gettingOffprgrefId() {
		final String sql = getQuery("OCDPROGR_GETTING_OFFPRGRE_ID");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), (Long.class));
	}

	@Override
	public List<VOffenderProgramProfiles> vOffPrgProfilesExecuteQuery(VOffenderProgramProfiles objSearchDao) {
		String sql = getQuery("OCDPROGR_GET_V_OFFENDER_PROGRAM_PROFILES");
		RowMapper<VOffenderProgramProfiles> mapper = Row2BeanRowMapper.makeMapping(sql, VOffenderProgramProfiles.class,
				mMapping);
		return namedParameterJdbcTemplate.query(sql,
				createParams("offenderName", objSearchDao.getOffenderName(), "programId", objSearchDao.getProgramId()),
				mapper);
	}

	@Override
	public List<OffenderNonAssociations> getNonAssociationforInst(BigDecimal offenderBookId) {
		String sql = getQuery("OCDPROGR_OFFENDER_NON_ASSOCIATIONS");
		RowMapper<OffenderNonAssociations> rowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderNonAssociations.class,
				mMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("offenderBookId", offenderBookId), rowMapper);
	}

	@Override
	public List<OffenderCourseAttendance> getNonAssociationsforAppointments(OffenderCourseAttendance attendence) {
		String sql = getQuery("OCDPROGR_OFFENDER_COURSE_ATTENDANCES");
		List<OffenderCourseAttendance> attendenceList = new ArrayList<>();
		RowMapper<OffenderCourseAttendance> mapper = Row2BeanRowMapper.makeMapping(sql, OffenderCourseAttendance.class,
				mMapping);
		try {
			SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String eventDate = dateFormat.format(attendence.getEventDate());
			String startTime = dateFormat1.format(attendence.getStartTime());
			String endTime = dateFormat1.format(attendence.getEndTime());
			attendenceList = namedParameterJdbcTemplate
					.query(sql,
							createParams("agyLocId", attendence.getAgyLocId(), "eventDate", eventDate, "startTime",
									startTime, "endTime", endTime, "offenderBookId", attendence.getOffenderBookId()),
							mapper);
		} catch (Exception e) {
			logger.error("exception in " + this.getClass().getName(), e);
		}
		return attendenceList;
	}

	@Override
	public String getProgramDescription(BigDecimal offPrgrefId) {
		final String sql = getQuery("OCDPROGR_GET_PROGRAM_DESCRIPTION_BASED_ON_OFFPRGREFID");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("offPrgRefId", offPrgrefId), (String.class));
	}

	@Override
	public Integer updateCourseActivitiesEmail(OffenderProgramProfiles OffenderProgramProfiles) {
		final String sql = getQuery("OCDPROGR_UPDATE_OFFENDER_COURSE_ATTENDANCES_EMAIL");
		Integer result = 0;
		try {
			result = namedParameterJdbcTemplate.update(sql,
					createParams("emailFlag", OffenderProgramProfiles.getEmailFlag(), "emailScheduleHoursBefore",
							OffenderProgramProfiles.getEmailScheduleHoursBefore(), "offenderBookId",
							OffenderProgramProfiles.getOffenderBookId(), "offPrgrefId",
							OffenderProgramProfiles.getOffPrgrefId()));
		} catch (Exception e) {
			logger.error("updateComment", e);
		}
		return result;
	}

	@Override
	public Integer updateCourseActivitiesSms(OffenderProgramProfiles offenderProgramProfiles) {
		final String sql = getQuery("OCDPROGR_UPDATE_OFFENDER_COURSE_ATTENDANCES_SMS");
		Integer result = 0;
		try {
			result = namedParameterJdbcTemplate.update(sql,
					createParams("smsFlag", offenderProgramProfiles.getSmsFlag(), "smsScheduleHoursBefore",
							offenderProgramProfiles.getSmsScheduleHoursBefore(), "offenderBookId",
							offenderProgramProfiles.getOffenderBookId(), "offPrgrefId",
							offenderProgramProfiles.getOffPrgrefId()));
		} catch (Exception e) {
			logger.error("updateComment", e);
		}
		return result;
	}

	@Override
	public Integer updateCourseActivitiesEmailAndSms(OffenderProgramProfiles offenderProgramProfiles) {
		final String sql = getQuery("OCDPROGR_UPDATE_OFFENDER_COURSE_ATTENDANCES_EMAIL_AND_SMS");
		Integer result = 0;
		try {
			result = namedParameterJdbcTemplate.update(sql,
					createParams("emailFlag", offenderProgramProfiles.getEmailFlag(), "emailScheduleHoursBefore",
							offenderProgramProfiles.getEmailScheduleHoursBefore(), "smsFlag",
							offenderProgramProfiles.getSmsFlag(), "smsScheduleHoursBefore",
							offenderProgramProfiles.getSmsScheduleHoursBefore(), "offenderBookId",
							offenderProgramProfiles.getOffenderBookId(), "offPrgrefId",
							offenderProgramProfiles.getOffPrgrefId()));
		} catch (Exception e) {
			logger.error("updateComment", e);
		}
		return result;
	}

	@Override
	public Date getProgramLastEventDateTime(Long offPrgrefId) {
		Date programLastDate = null;
		final String sql = getQuery("OCDPROGR_OFFENDER_PROGRAM_LAST_DATE");

		try {
			programLastDate = namedParameterJdbcTemplate.queryForObject(sql, createParams("off_prgref_id", offPrgrefId),
					Date.class);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
		return programLastDate;
	}
}
