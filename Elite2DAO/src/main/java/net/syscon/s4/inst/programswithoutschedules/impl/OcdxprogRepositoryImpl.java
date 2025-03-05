package net.syscon.s4.inst.programswithoutschedules.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlInOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.cm.programsservices.VOffenderSentCondActs;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SentenceCalcTypes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.im.beans.Dual;
import net.syscon.s4.im.beans.OffenderPrgObligations;
import net.syscon.s4.im.beans.OffenderProgramProfiles;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.im.beans.VCourseActivities;
import net.syscon.s4.inst.casemanagement.beans.ProgramServices;
import net.syscon.s4.inst.legals.beans.OffenderSentences;
import net.syscon.s4.inst.programswithoutschedules.OcdxprogRepository;
import oracle.jdbc.OracleTypes;

/**
 * Class OcdxprogRepositoryImpl
 */

@Repository
public class OcdxprogRepositoryImpl extends RepositoryBase implements OcdxprogRepository {

	private static Logger logger = LogManager.getLogger(OcdxprogRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> offenderProgramProfilesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CRS_ACTY_ID", new FieldMapper("crsActyId")).put("PROGRAM_ID", new FieldMapper("programId"))
			.put("SCHEDULE_END_DATE", new FieldMapper("scheduleEndDate"))
			.put("OFFENDER_PRG_OBLIGATION_ID", new FieldMapper("offenderPrgObligationId"))
			.put("MIN(OFFENDER_START_DATE)", new FieldMapper("min(offenderStartDate)"))
			.put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId"))
			.put("OFF_PRGREF_ID", new FieldMapper("offPrgrefId"))
			.put("PLACEMENT_RECORD", new FieldMapper("placementRecord"))
			.build();
	private final Map<String, FieldMapper> offenderPrgObligationsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId")).build();
	private final Map<String, FieldMapper> courseActivitiesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CRS_ACTY_ID", new FieldMapper("crsActyId")).put("PROGRAM_ID", new FieldMapper("programId"))
			.put("SCHEDULE_END_DATE", new FieldMapper("scheduleEndDate"))
			.put("OFFENDER_PRG_OBLIGATION_ID", new FieldMapper("offenderPrgObligationId"))
			.put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId")).build();
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description")).put("PROGRAM_ID", new FieldMapper("programId"))
			.put("PROGRAM_CODE", new FieldMapper("programCode")).put("CODE", new FieldMapper("code"))
			.put("COURSE_CLASS", new FieldMapper("courseClass")).build();

	/**
	 * Creates new OcdxprogRepositoryImpl class Object
	 */
	public OcdxprogRepositoryImpl() {
		// OcdxprogRepositoryImpl
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao OffenderPrgObligations
	 *
	 * @return List<OffenderPrgObligations>
	 *
	 * @throws SQLException
	 */
	public List<OffenderPrgObligations> offPrgObligationsExecuteQuery(final OffenderPrgObligations objSearchDao) {
		final String sql = getQuery("OCDXPROG_OFFPRGOBLIGATIONS_FIND_OFFENDER_PRG_OBLIGATIONS");
		final RowMapper<OffenderPrgObligations> OffenderPrgObligationsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderPrgObligations.class, offenderPrgObligationsMapping);
		List<OffenderPrgObligations> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(sql,
				createParams("OFFENDERBOOKID", objSearchDao.getOffenderBookId()), OffenderPrgObligationsRowMapper);
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
	public Integer offPrgObligationsInsertOffenderPrgObligations(
			final List<OffenderPrgObligations> lstOffenderPrgObligations) {
		String sql = getQuery("OCDXPROG_OFFPRGOBLIGATIONS_INSERT_OFFENDER_PRG_OBLIGATIONS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (OffenderPrgObligations offenderPrgObligations : lstOffenderPrgObligations) {
			parameters.add(new BeanPropertySqlParameterSource(offenderPrgObligations));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
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
	public Integer offPrgObligationsUpdateOffenderPrgObligations(
			final List<OffenderPrgObligations> lstOffenderPrgObligations) {
		String sql = getQuery("OCDXPROG_OFFPRGOBLIGATIONS_UPDATE_OFFENDER_PRG_OBLIGATIONS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (OffenderPrgObligations offenderPrgObligations : lstOffenderPrgObligations) {
			parameters.add(new BeanPropertySqlParameterSource(offenderPrgObligations));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
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
	public Integer offPrgObligationsDeleteOffenderPrgObligations(
			final List<OffenderPrgObligations> lstOffenderPrgObligations) {
		String sql = getQuery("OCDXPROG_OFFPRGOBLIGATIONS_DELETE_OFFENDER_PRG_OBLIGATIONS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (OffenderPrgObligations offenderPrgObligations : lstOffenderPrgObligations) {
			parameters.add(new BeanPropertySqlParameterSource(offenderPrgObligations));
		}
		try {
			String tableName = "OFFENDER_PRG_OBLIGATIONS";
			String whereCondition = "OFFENDER_PRG_OBLIGATION_ID = :offenderPrgObligationId";
			batchUpdatePreDeletedRows(tableName, whereCondition, parameters);
		} catch (Exception e) {
			logger.error(e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
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
	public List<OffenderProgramProfiles> offProgramProfilesExecuteQuery(final OffenderProgramProfiles objSearchDao) {
		final String sql = getQuery("OCDXPROG_OFFPROGRAMPROFILES_FIND_OFFENDER_PROGRAM_PROFILES");
		final RowMapper<OffenderProgramProfiles> OffenderProgramProfilesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderProgramProfiles.class, offenderProgramProfilesMapping);
		List<OffenderProgramProfiles> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(sql,
				createParams("OFFENDERPRGOBLIGATIONID", objSearchDao.getOffenderPrgObligationId()),
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
	public Integer offProgramProfilesInsertOffenderProgramProfiles(
			final List<OffenderProgramProfiles> lstOffenderProgramProfiles) {
		String sql = getQuery("OCDXPROG_OFFPROGRAMPROFILES_INSERT_OFFENDER_PROGRAM_PROFILES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (OffenderProgramProfiles offenderProgramProfiles : lstOffenderProgramProfiles) {
			parameters.add(new BeanPropertySqlParameterSource(offenderProgramProfiles));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("offProgramProfilesInsertOffenderProgramProfiles");
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
	public Integer offProgramProfilesUpdateOffenderProgramProfiles(
			final List<OffenderProgramProfiles> lstOffenderProgramProfiles) {
		String sql = getQuery("OCDXPROG_OFFPROGRAMPROFILES_UPDATE_OFFENDER_PROGRAM_PROFILES");
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
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgAvailabilityCodeRecordGroup() {
		final String sql = getQuery("OCDXPROG_FIND_RGAVAILABILITYCODE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ProgramServices> rgProgramRecordGroup() {
		final String sql = getQuery("OCDXPROG_FIND_RGPROGRAM");
		final RowMapper<ProgramServices> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ProgramServices.class,
				mMapping);
		return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgEndReasonRecordGroup() {
		final String sql = getQuery("OCDXPROG_FIND_RGENDREASON");
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
	public List<OffenderPrgObligations> offBkgOnCheckDeleteMaster(final OffenderPrgObligations paramBean) {
		final String sql = getQuery("OCDXPROG_OFF_BKG_ONCHECKDELETEMASTER");
		final RowMapper<OffenderPrgObligations> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderPrgObligations.class, offenderPrgObligationsMapping);
		final ArrayList<OffenderPrgObligations> returnList = (ArrayList<OffenderPrgObligations>) namedParameterJdbcTemplate
				.query(sql, createParams(), columnRowMapper);
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
	public List<OffenderProgramProfiles> offPrgObligationsOnCheckDeleteMaster(final OffenderProgramProfiles paramBean) {
		final String sql = getQuery("OCDXPROG_OFF_PRG_OBLIGATIONS_ONCHECKDELETEMASTER");
		final RowMapper<OffenderProgramProfiles> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderProgramProfiles.class, offenderProgramProfilesMapping);
		final ArrayList<OffenderProgramProfiles> returnList = (ArrayList<OffenderProgramProfiles>) namedParameterJdbcTemplate
				.query(sql, createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offPrgObligationsWhenNewRecordInstance
	 *
	 * @param params
	 *
	 */
	public List<Dual> offPrgObligationsWhenNewRecordInstance(final Dual paramBean) {
		final String sql = getQuery("OCDXPROG_OFF_PRG_OBLIGATIONS_WHENNEWRECORDINSTANCE");
		final RowMapper<Dual> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Dual.class,
				offenderProgramProfilesMapping);
		final List<Dual> returnList = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
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
	public String offProgramProfilesPostQuery(final VCourseActivities paramBean) {
		final String sql = getQuery("OCDXPROG_OFF_PROGRAM_PROFILES_POSTQUERY");
		String returnList = null;
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("CRSACTYID", paramBean.getCrsActyId()),
				String.class);
		}catch (Exception e) {
			logger.error(e);
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offProgramProfilesWhenValidateRecord
	 *
	 * @param params
	 *
	 */
	public List<OffenderProgramProfiles> offProgramProfilesWhenValidateRecord(final OffenderProgramProfiles paramBean) {
		final String sql = getQuery("OCDXPROG_OFF_PROGRAM_PROFILES_WHENVALIDATERECORD");
		final RowMapper<OffenderProgramProfiles> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderProgramProfiles.class, offenderProgramProfilesMapping);
		final ArrayList<OffenderProgramProfiles> returnList = (ArrayList<OffenderProgramProfiles>) namedParameterJdbcTemplate
				.query(sql, createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * createFormGlobals
	 *
	 * @param params
	 *
	 */
	public List<OmsModules> createFormGlobals(final OmsModules paramBean) {
		final String sql = getQuery("OCDXPROG_CREATE_FORM_GLOBALS");
		final RowMapper<OmsModules> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OmsModules.class,
				offenderProgramProfilesMapping);
		final ArrayList<OmsModules> returnList = (ArrayList<OmsModules>) namedParameterJdbcTemplate.query(sql,
				createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * callOciscata
	 *
	 * @param params
	 *
	 */
	public List<CourseActivities> callOciscata(final CourseActivities paramBean) {
		final String sql = getQuery("OCDXPROG_CALL_OCISCATA");
		final RowMapper<CourseActivities> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, CourseActivities.class,
				courseActivitiesMapping);
		final ArrayList<CourseActivities> returnList = (ArrayList<CourseActivities>) namedParameterJdbcTemplate
				.query(sql, createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * callOciscata
	 *
	 * @param params
	 *
	 */
	public String currentCaseloadType(final String caseloadId) {
		final String sql = getQuery("OCDXPROG_CASELOAD_TYPE");
		String returnList = null;
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("CASELOADID", caseloadId),
					String.class);
		} catch (Exception e) {
			logger.error(e);
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * callOciscata
	 *
	 * @param params
	 *
	 */
	public String obligationStatus(final String status) {
		final String sql = getQuery("OCDXPROG_OBLIGATION_STATUS");
		String returnList = null;
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("STATUS", status), String.class);
		} catch (Exception e) {
			logger.error(e);
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * callOciscata
	 *
	 * @param params
	 *
	 */
	public String obligationStatusCode(final String status) {
		final String sql = getQuery("OCDXPROG_OBLIGATION_STATUS_CODE");
		String returnList = null;
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("STATUS", status), String.class);
		} catch (Exception e) {
			logger.error(e);
	}
		return returnList;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao OffenderPrgObligations
	 *
	 * @return List<OffenderPrgObligations>
	 *
	 * @throws SQLException
	 */
	public ProgramServices offPrgObligationsProgramId(final OffenderPrgObligations offPrgObligations) {
		final String sql = getQuery("OCDXPROG_OBLIGATION_PROGRAM_ID");
		final RowMapper<ProgramServices> OffenderPrgObligationsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ProgramServices.class, mMapping);
		ProgramServices returnList = new ProgramServices();
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("PROGRAMID", offPrgObligations.getProgramId()), OffenderPrgObligationsRowMapper);
		} catch (Exception e) {
			logger.error(e);
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offenderGrievancesPreInsert
	 *
	 * @param params
	 *
	 */
	public Long offenderPrgObligationId() {
		final String sql = getQuery("OCDXPROG_OFFENDER_PRG_OBLIGATION_ID");
		Long obj = null;
		Long returnval = null;
		obj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), Long.class);
		if (obj != null) {
			returnval = obj;
		}
		return returnval;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offenderGrievancesPreInsert
	 *
	 * @param params
	 *
	 */
	public Long offenderProramCode(final String description) {
		final String sql = getQuery("OCDXPROG_OBLIGATION_PROGRAM_CODE");
		String obj = null;
		Long returnval = null;
		try {
			obj = namedParameterJdbcTemplate.queryForObject(sql, createParams("DESCRIPTION", description),
					String.class);
		} catch (Exception e) {
			logger.error(e);
		}
		if (obj != null) {
			returnval = Long.valueOf(obj);
		}
		return returnval;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offenderGrievancesPreInsert
	 *
	 * @param params
	 *
	 */
	public Long ocdxprogOffPrgrefId() {
		final String sql = getQuery("OCDXPROG_OFF_PRGREF_ID");
		Long obj = null;
		Long returnval = null;
		obj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), Long.class);
		if (obj != null) {
			returnval = obj;
		}
		return returnval;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao OffenderPrgObligations
	 *
	 * @return List<OffenderPrgObligations>
	 *
	 * @throws SQLException
	 */
	public CourseActivities offPrgPrflesProjectDescription(final Long crsActyId) {
		final String sql = getQuery("OFF_PRG_PROFILE_PROJECT_DESCRIPTION");
		final RowMapper<CourseActivities> OffenderPrgObligationsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				CourseActivities.class, mMapping);
		CourseActivities returnList = new CourseActivities();
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("CRSACTYID", crsActyId),
					OffenderPrgObligationsRowMapper);
		} catch (Exception e) {
			return returnList;
		}
		return returnList;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao OffenderPrgObligations
	 *
	 * @return List<OffenderPrgObligations>
	 *
	 * @throws SQLException
	 */
	public Integer offProgramPrflesUpdatePrgStatus(final Long offenderPrgObligationId, final Long offenderBookId) {
		final String sql = getQuery("OFFENDER_PROGRAM_PROFILES_EARLY_END_REASON_OFFENDER_END_DATE");
		Integer returnNumber = 0;
		Long returnList = null;
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("OFFENDERPRGOBLIGATIONID", offenderPrgObligationId, "OFFENDERBOOKID", offenderBookId),
					Long.class);
			if (returnList != 0) {
				returnNumber = Integer.valueOf(returnList.toString());
			}
		} catch (Exception e) {
			return returnNumber;
		}
		return returnNumber;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao OffenderPrgObligations
	 *
	 * @return List<OffenderPrgObligations>
	 *
	 * @throws SQLException
	 */
	public String offProgramPrflesSameValidation(final OffenderProgramProfiles offProgramProfiles) {
		final String sql = getQuery("OFFENDER_PROGRAM_PROFILES_SAME_VALIDATION");
		String returnList = null;
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("OFFENDERPRGOBLIGATIONID",
					offProgramProfiles.getOffenderPrgObligationId(), "CRSACTYID", offProgramProfiles.getCrsActyId()),
					String.class);
		} catch (Exception e) {
			return returnList;
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offSchPreInsert
	 *
	 *
	 */
	public Integer offProgramProfilePreInsert(final OffenderProgramProfiles offProgramProfiles) {
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[4];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_CRS_ACTY_ID", OracleTypes.NUMBER),
				new SqlParameter("P_OFFENDER_BOOK_ID", OracleTypes.NUMBER),
				new SqlParameter("P_OFFENDER_START_DATE", OracleTypes.DATE),
				new SqlInOutParameter("RETURN_VALUE", OracleTypes.VARCHAR) };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_PRISON_ACTIVITIES").withFunctionName("CHK_ALLOCATED")
				.declareParameters(sqlParameters);
		inParamMap.put("P_CRS_ACTY_ID", offProgramProfiles.getCrsActyId());
		inParamMap.put("P_OFFENDER_BOOK_ID", offProgramProfiles.getOffenderBookId());
		inParamMap.put("P_OFFENDER_START_DATE", offProgramProfiles.getOffenderStartDate());
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		final String value = simpleJDBCCall.executeFunction(String.class, inParameter);
		return Integer.parseInt(value);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao OffenderPrgObligations
	 *
	 * @return List<OffenderPrgObligations>
	 *
	 * @throws SQLException
	 */
	public Integer checkDeleteOffenderPrgObligations(final Long offenderPrgObligationId) {
		final String sql = getQuery("CHECK_DELETE_OFFENDER_PRG_OBLIGATIONS");
		Integer returnNumber = 0;
		Long returnList = null;
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("OFFENDERPRGOBLIGATIONID", offenderPrgObligationId), Long.class);
			if (returnList != 0) {
				returnNumber = Integer.valueOf(returnList.toString());
			}
		} catch (Exception e) {
			return returnNumber;
		}
		return returnNumber;
	}

	@Override
	public int checkPrivilegeExists() {
		int result = 0;
		final String sql = getQuery("CHECK_PRIVILEGE_EXISTS");
		try {
			result = namedParameterJdbcTemplate.queryForObject(sql, createParams(), Integer.class);
		} catch (Exception e) {
			return result;
		}
		return result;
	}

	@Override
	public void updateOffObligationStatus(Long offenderPrgObligationId, String modifyUserId) {
		final String sql = getQuery("UPDATE_OFF_OBLIGATION_STATUS");
		namedParameterJdbcTemplate.update(sql,
				createParams("p_offender_prg_obligation_id", offenderPrgObligationId, "modifyUserId", modifyUserId));
	}

	@Override
	public OffenderPrgObligations getOffenderPrgObligationsOldRecord(Long offenderPrgObligationId) {
		OffenderPrgObligations result = null;
		final String sql = getQuery("OCDXPROG_OFFPRGOBLIGATIONS_FIND_OFFENDER_PRG_OBLIGATIONS_OLD");
		try {
			result = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("OFFENDER_PRG_OBLIGATION_ID", offenderPrgObligationId),
					new BeanPropertyRowMapper<OffenderPrgObligations>(OffenderPrgObligations.class));
		} catch (Exception e) {
			return result;
		}
		return result;
	}

	@Override
	public List<VOffenderSentCondActs> rgOffenderSentencesRecordGroup(Integer offenderBookId) {
			final String sql = getQuery("OCDXPROG_FIND_RGOFFENDERSENTENCES");
			final RowMapper<VOffenderSentCondActs> mMSentenceCalcTypesOffenderCasesRowMapper = Row2BeanRowMapper
					.makeMapping(sql, VOffenderSentCondActs.class, mMapping);

			try {
				return namedParameterJdbcTemplate.query(sql, createParams("OFFENDER_BOOK_ID", offenderBookId),
						mMSentenceCalcTypesOffenderCasesRowMapper);
			} catch (EmptyResultDataAccessException e) {
				return Collections.emptyList();
			}
		
	}
	@Override
	public String getCourtData(final String agyLocId) {
		final String sql = getQuery("OCDXPROG_COURT_EXECUTEQUERY");
		String returnList = null;
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("agyLocId", agyLocId),
					String.class);
		} catch (final Exception e) {
			logger.error(this.getClass().getName()+" getCourtData", e);
		}
		return returnList;
	}
	
	@Override
	public List<SentenceCalcTypes> getSentenceData () {
		final String sql = getQuery("OCDXPROG_SENTENCE_EXECUTEQUERY");
		final RowMapper<SentenceCalcTypes> mMSentenceCalcTypesOffenderCasesRowMapper = Row2BeanRowMapper
				.makeMapping(sql, SentenceCalcTypes.class, mMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(),
					mMSentenceCalcTypesOffenderCasesRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	@Override
	public Integer checkNonAssociationGroupConflict(BigDecimal OffenderBookId1, BigDecimal OffenderBookId2) {
		final String sql = getQuery("OCDXPROG_NONASSOCIATION_GROUPS_OFFENDER");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("P_OFFENDER_BOOK_ID1",OffenderBookId1, "P_OFFENDER_BOOK_ID2", OffenderBookId2), Integer.class);
		}catch (Exception e) {
			logger.error("In checkNonAssociationConflict method : ", e);
			return 0;
		}
	}
	
}
