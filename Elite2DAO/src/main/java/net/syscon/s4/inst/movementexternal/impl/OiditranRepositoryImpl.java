package net.syscon.s4.inst.movementexternal.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlInOutParameter;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.support.TransactionOperations;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.CaseloadAgencyLocations;
import net.syscon.s4.common.beans.Caseloads;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.OffenderExternalMovements;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.TransactionTypes;
import net.syscon.s4.common.beans.VOffExm;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.BedAssignmentHistories;
import net.syscon.s4.im.beans.CaseloadAdmAlertProfiles;
import net.syscon.s4.im.beans.CaseloadAdmOtherProfiles;
import net.syscon.s4.im.beans.LivingUnits;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.inst.movementexternal.OiditranRepository;
import oracle.jdbc.OracleTypes;

@Repository
public class OiditranRepositoryImpl extends RepositoryBase implements OiditranRepository {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OiditranRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> transactionTypesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description"))
			.build();
	private final Map<String, FieldMapper> vOffExmMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId"))
			.put("COMMENT_TEXT", new FieldMapper("commentText"))
			.put("REPORTING_DATE", new FieldMapper("reportingDate"))
			.put("ACTIVE_FLAG", new FieldMapper("activeFlag"))
			.put("REPORTING_TIME", new FieldMapper("reportingTime"))
			.put("FROM_AGY_LOC_ID", new FieldMapper("fromAgyLocId"))
			.put("TO_AGY_LOC_ID", new FieldMapper("toAgyLocId"))
			.put("MOVEMENT_TIME", new FieldMapper("movementTime"))
			.put("INTERNAL_SCHEDULE_TYPE", new FieldMapper("internalScheduleType"))
			.put("INTERNAL_SCHEDULE_REASON_CODE", new FieldMapper("internalScheduleReasonCode"))
			.put("ESCORT_CODE", new FieldMapper("escortCode"))
			.put("FROM_CITY", new FieldMapper("fromCity"))
			.put("MOVEMENT_SEQ", new FieldMapper("movementSeq"))
			.put("TO_CITY", new FieldMapper("toCity"))
			.put("TO_PROV_STAT_CODE", new FieldMapper("toProvStatCode"))
			.put("ESCORT_TEXT", new FieldMapper("escortText"))
			.put("MOVEMENT_REASON_CODE", new FieldMapper("movementReasonCode"))
			.put("MOVEMENT_TYPE", new FieldMapper("movementType"))
			.put("ARREST_AGENCY_LOC_ID", new FieldMapper("arrestAgencyLocId"))
			.put("MOVEMENT_DATE", new FieldMapper("movementDate"))
			.put("DIRECTION_CODE", new FieldMapper("directionCode"))
			.put("OFFENDER_ID", new FieldMapper("offenderId"))
			.build();
	private final Map<String, FieldMapper> caseloadsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("AGY_LOC_ID", new FieldMapper("agyLocId"))
			.put("TRUST_ACCOUNTS_FLAG", new FieldMapper(" trustAccountsFlag "))
			.put("CASELOAD_ID", new FieldMapper("caseloadId"))
			.build();
	private final Map<String, FieldMapper> caseloadAdmAlertProfilesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("MESSAGE_NUMBER", new FieldMapper("messageNumber"))
			.put("ROOT_OFFENDER_ID", new FieldMapper("rootOffenderId"))
			.put("CASELOAD_ID", new FieldMapper("caseloadId"))
			.build();
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CODE", new FieldMapper("code ")).build();
	private final Map<String, FieldMapper> agencyLocationsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("TO_AGY_LOC_ID", new FieldMapper("toAgyLocId"))
			.build();
	private final Map<String, FieldMapper> caseloadAgencyLocationsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("AGY_LOC_ID", new FieldMapper("agyLocId"))
			.put("TRUST_ACCOUNTS_FLAG", new FieldMapper(" trustAccountsFlag "))
			.put("CASELOAD_ID", new FieldMapper("caseloadId")).build();
	private final Map<String, FieldMapper> caseloadAdmOtherProfilesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("COUNT", new FieldMapper("count"))
			.put("CASELOAD_ID", new FieldMapper("caseloadId"))
			.put("CODE", new FieldMapper("code"))
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("LIVING_UNIT_ID", new FieldMapper("livingUnitId"))
			.put("AGY_LOC_ID", new FieldMapper("agyLocId"))
			.put("DSP_DESCRIPTION", new FieldMapper("dspDescription"))
			.put("LIV_UNIT_ID", new FieldMapper("livUnitId"))
			.put("LIV_UNIT_CODE", new FieldMapper("livUnitCode"))
			.put("ACTIVE_FLAG", new FieldMapper("activeFlag"))
			.build();
	private final Map<String, FieldMapper> livingUnitsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CAPACITY", new FieldMapper("capacity"))
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("LIVING_UNIT_ID", new FieldMapper("livingUnitId"))
			.put("LIVING_UNIT_CODE", new FieldMapper("livingUnitCode"))
			.build();
	private final Map<String, FieldMapper> offenderExternalMovementsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("0)", new FieldMapper("0)"))
			.put("MOVEMENT_DAT", new FieldMapper("movementDat"))
			.put("NVL(MAX(MOVEMENT_SEQ)", new FieldMapper("  nvl(max(movementSeq)"))
			.put("MOVEMENT_TIME", new FieldMapper("movementTime"))
			.put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId"))
			.build();
	private final Map<String, FieldMapper> bedAssignmentHistoriesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("0", new FieldMapper("0"))
			.put("NV", new FieldMapper("nv"))
			.build();
	private final Map<String, FieldMapper> offenderBookingsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_ID_DISPLAY", new FieldMapper("offenderIdDisplay"))
			.put("OFFENDER_ID", new FieldMapper("offenderId"))
			.put("BIRTH_DATE", new FieldMapper("birthDate"))
			.put("ROOT_OFFENDER_ID", new FieldMapper("rootOffenderId"))
			.put("AGY_LOC_ID", new FieldMapper(" agyLocId "))
			.put("LAST_NAME", new FieldMapper("lastName"))
			.put("FIRST_NAME", new FieldMapper("firstName"))
			.put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId"))
			.build();
	private final Map<String, FieldMapper> offendersMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_ID_DISPLAY", new FieldMapper("offenderIdDisplay"))
			.put("OFFENDER_ID", new FieldMapper("offenderId"))
			.put("BIRTH_DATE", new FieldMapper("birthDate"))
			.put("ROOT_OFFENDER_ID", new FieldMapper("rootOffenderId"))
			.put("AGY_LOC_ID", new FieldMapper(" agyLocId "))
			.put("LAST_NAME", new FieldMapper("lastName"))
			.put("FIRST_NAME", new FieldMapper("firstName"))
			.build();

	/**
	 * Creates new OiditranRepositoryImpl class Object
	 */
	public OiditranRepositoryImpl() {
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            VOffExm
	 *
	 * @return List<VOffExm>
	 *
	 * @
	 */
	public List<VOffExm> offEmExecuteQuery(final String caseloadId) {
		final String sql = getQuery("OIDITRAN_OFFEM_FIND_V_OFF_EXM");
		final RowMapper<VOffExm> VOffExmRowMapper = Row2BeanRowMapper.makeMapping(sql, VOffExm.class, vOffExmMapping);
		final ArrayList<VOffExm> returnList = (ArrayList<VOffExm>) namedParameterJdbcTemplate.query(sql,
				createParams("CASELOAD_ID", caseloadId), VOffExmRowMapper);
		return returnList;
	}

	/**
	 * @param
	 *
	 * @
	 *
	 */
	public int preInsert() {
		return 0;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstOffenderExternalMovements
	 *            List<OffenderExternalMovements>
	 *
	 * @return List<Integer>
	 *
	 * @
	 */
	public Integer offEmInsertOffenderExternalMovements(
			final List<OffenderExternalMovements> lstOffenderExternalMovements) {
		final String sql = getQuery("OIDITRAN_OFFEM_INSERT_OFFENDER_EXTERNAL_MOVEMENTS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderExternalMovements offenderExternalMovements : lstOffenderExternalMovements) {
			parameters.add(new BeanPropertySqlParameterSource(offenderExternalMovements));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));

		if (lstOffenderExternalMovements.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstVOffExm
	 *            List<VOffExm>
	 *
	 * @
	 */
	public Integer offEmUpdateVOffExm(final List<OffenderExternalMovements> lstVOffExm) {
		final String sql = getQuery("OIDITRAN_OFFEM_UPDATE_V_OFF_EXM");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderExternalMovements vOffExm : lstVOffExm) {
			parameters.add(new BeanPropertySqlParameterSource(vOffExm));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstVOffExm.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstVOffExm
	 *            List<VOffExm>
	 *
	 * @
	 */
	public Integer offEmDeleteVOffExm(final List<OffenderExternalMovements> lstVOffExm) {
		final String sql = getQuery("OIDITRAN_OFFEM_DELETE_V_OFF_EXM");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderExternalMovements vOffExm : lstVOffExm) {
			parameters.add(new BeanPropertySqlParameterSource(vOffExm));
		}
		try {
			batchUpdatePreDeletedRows("offender_external_movements", "OFFENDER_BOOK_ID = :offenderBookId AND MOVEMENT_SEQ=:movementSeq", parameters);
		} catch (Exception e) {
			logger.error("batchUpdatePreDeletedRows in offEmDeleteVOffExm"+e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstVOffExm.size() == returnArray.length) {
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
	public List<ReferenceCodes> moveRsnLovRecordGroup() {
		final String sql = getQuery("OIDITRAN_FIND_MOVERSNLOV");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (Exception e) {
			logger.error("In method moveRsnLovRecordGroup" + e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<MMAgencyLocations>
	 */
	public List<CaseloadAdmOtherProfiles> cgfkOffEmToAgyLocIdRecordGroup(final String caseloadId) {
		final String sql = getQuery("OIDITRAN_FIND_CGFKOFFEMTOAGYLOCID");
		final RowMapper<CaseloadAdmOtherProfiles> mMAgencyLocationsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				CaseloadAdmOtherProfiles.class, caseloadAdmOtherProfilesMapping);

		try {
			return namedParameterJdbcTemplate.query(sql,
					createParams("CASELOADID", caseloadId, "CASELOADID", caseloadId, "CASELOADID", caseloadId),
					mMAgencyLocationsRowMapper);
		} catch (Exception e) {
			logger.error("In method cgfkOffEmToAgyLocIdRecordGroup" + e);
			return Collections.emptyList();
		}
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * createFormGlobalsCREATE_FORM_GLOBALS
	 *
	 * @param params
	 *
	 */
	public OmsModules createFormGlobalscreateFormGlobals(final OmsModules paramBean) {
		final String sql = getQuery("OIDITRAN_CREATE_FORM_GLOBALS_CREATE_FORM_GLOBALS");
		final RowMapper<OmsModules> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OmsModules.class, mMapping);
		final OmsModules returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(""), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOffEmOffEmOffBkg
	 *
	 * @param params
	 *
	 */
	public Offenders cgfkchkOffEmOffEmOffBkg(final OffenderBookings paramBean) {
		final String sql = getQuery("OIDITRAN_CGFKCHK_OFF_EM_OFF_EM_OFF_BKG");
		final RowMapper<Offenders> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Offenders.class,
				offendersMapping);
		Offenders returnObj = (Offenders) namedParameterJdbcTemplate.queryForObject(sql,
				createParams("OFFENDER_BOOK_ID", paramBean.getOffenderBookId()), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOffEmOffEmAgyLoc
	 *
	 * @param params
	 *
	 */
	public AgencyLocations cgfkchkOffEmOffEmAgyLoc(final AgencyLocations paramBean) {
		final String sql = getQuery("OIDITRAN_CGFKCHK_OFF_EM_OFF_EM_AGY_LOC");
		final RowMapper<AgencyLocations> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				agencyLocationsMapping);
		final AgencyLocations returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(""), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * tohlocLov
	 *
	 * @param params
	 *
	 */
	public List<LivingUnits> tohlocLov(final LivingUnits paramBean) {
		final String sql = getQuery("OIDITRAN_TOHLOC_LOV");
		final RowMapper<LivingUnits> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, LivingUnits.class,
				livingUnitsMapping);
		final ArrayList<LivingUnits> returnList = (ArrayList<LivingUnits>) namedParameterJdbcTemplate.query(sql,
				createParams(""), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * nonassSecValProc
	 *
	 * @param params
	 *
	 */
	public OffenderBookings nonassSecValProc(final OffenderBookings paramBean) {
		final String sql = getQuery("OIDITRAN_NONASS_SEC_VAL_PROC");
		final RowMapper<OffenderBookings> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderBookings.class,
				offenderBookingsMapping);
		final OffenderBookings returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(""), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * insIntoBedAssHist
	 *
	 * @param params
	 *
	 */
	public BedAssignmentHistories insIntoBedAssHist(final BedAssignmentHistories paramBean) {
		final String sql = getQuery("OIDITRAN_INS_INTO_BED_ASS_HIST");
		final RowMapper<BedAssignmentHistories> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				BedAssignmentHistories.class, bedAssignmentHistoriesMapping);
		BedAssignmentHistories returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(""),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * insIntoOffExtMovement
	 *
	 * @param params
	 *
	 */
	public OffenderExternalMovements insIntoOffExtMovement(final OffenderExternalMovements paramBean) {
		final String sql = getQuery("OIDITRAN_INS_INTO_OFF_EXT_MOVEMENT");
		final RowMapper<OffenderExternalMovements> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderExternalMovements.class, offenderExternalMovementsMapping);
		OffenderExternalMovements returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(""),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * initForm
	 *
	 * @param params
	 *
	 */
	public Caseloads initForm(final Caseloads paramBean) {
		final String sql = getQuery("OIDITRAN_INIT_FORM");
		final RowMapper<Caseloads> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Caseloads.class,
				caseloadsMapping);
		Caseloads returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(""), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * initForm
	 *
	 * @param params
	 *
	 */
	public Object initFormCaseloadAgencyLocations(final CaseloadAgencyLocations paramBean) {
		final String sql = getQuery("OIDITRAN_INIT_FORM");
		final RowMapper<CaseloadAgencyLocations> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				CaseloadAgencyLocations.class, caseloadAgencyLocationsMapping);
		CaseloadAgencyLocations returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(""),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * initForm
	 *
	 * @param params
	 *
	 */
	public List<Object> initForm(final CaseloadAgencyLocations paramBean) {
		final String sql = getQuery("OIDITRAN_INIT_FORM");
		final RowMapper<Object> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Object.class,
				caseloadAgencyLocationsMapping);
		final ArrayList<Object> returnList = (ArrayList<Object>) namedParameterJdbcTemplate.query(sql, createParams(""),
				columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * populateLivingUnit
	 *
	 * @param params
	 *
	 */
	public List<LivingUnits> populateLivingUnit(final LivingUnits paramBean) {
		final String sql = getQuery("OIDITRAN_POPULATE_LIVING_UNIT");
		final RowMapper<LivingUnits> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, LivingUnits.class,
				livingUnitsMapping);
		final ArrayList<LivingUnits> returnList = (ArrayList<LivingUnits>) namedParameterJdbcTemplate.query(sql,
				createParams(""), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * getCountOfAgyInCase
	 *
	 * @param params
	 *
	 */
	public CaseloadAdmOtherProfiles getCountOfAgyInCase(final CaseloadAdmOtherProfiles paramBean) {
		final String sql = getQuery("OIDITRAN_GET_COUNT_OF_AGY_IN_CASE");
		final RowMapper<CaseloadAdmOtherProfiles> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				CaseloadAdmOtherProfiles.class, caseloadAdmOtherProfilesMapping);
		CaseloadAdmOtherProfiles returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(""),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * checkCapacityIndividual
	 *
	 * @param params
	 *
	 */
	public List<LivingUnits> checkCapacityIndividual(final LivingUnits paramBean) {
		final String sql = getQuery("OIDITRAN_CHECK_CAPACITY_INDIVIDUAL");
		final RowMapper<LivingUnits> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, LivingUnits.class,
				livingUnitsMapping);
		final ArrayList<LivingUnits> returnList = (ArrayList<LivingUnits>) namedParameterJdbcTemplate.query(sql,
				createParams(""), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * insertMasterRecINSERT_MASTER_REC
	 *
	 * @param params
	 *
	 */
	public TransactionOperations insertMasterRecinsertMasterRec(final TransactionOperations paramBean) {
		final String sql = getQuery("OIDITRAN_INSERT_MASTER_REC_INSERT_MASTER_REC");
		final RowMapper<TransactionOperations> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				TransactionOperations.class, mMapping);
		TransactionOperations returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(""),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * insertMasterRecINSERT_MASTER_REC
	 *
	 * @param params
	 *
	 */
	public AccountCodes insertMasterRecINSERTMASTERREC(final AccountCodes paramBean) {
		final String sql = getQuery("OIDITRAN_INSERT_MASTER_REC_INSERT_MASTER_REC");
		final RowMapper<AccountCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, AccountCodes.class,
				mMapping);
		final AccountCodes returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(""), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * insertMasterRecINSERT_MASTER_REC
	 *
	 * @param params
	 *
	 */
	public TransactionTypes insertMasterRecinsertMasterRecTransactionTypes(final TransactionTypes paramBean) {
		final String sql = getQuery("OIDITRAN_INSERT_MASTER_REC_INSERT_MASTER_REC");
		final RowMapper<TransactionTypes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, TransactionTypes.class,
				transactionTypesMapping);
		final TransactionTypes returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(""), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * storGlobalsSTOR_GLOBALS
	 *
	 * @param params
	 *
	 */
	public TransactionOperations storGlobals(final TransactionOperations paramBean) {
		final String sql = getQuery("OIDITRAN_STOR_GLOBALS_STOR_GLOBALS");
		final RowMapper<TransactionOperations> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				TransactionOperations.class, mMapping);
		TransactionOperations returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(""),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * storGlobalsSTOR_GLOBALS
	 *
	 * @param params
	 *
	 */
	public AccountCodes storGlobals(final AccountCodes paramBean) {
		final String sql = getQuery("OIDITRAN_STOR_GLOBALS_STOR_GLOBALS");
		final RowMapper<AccountCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, AccountCodes.class,
				mMapping);
		final AccountCodes returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(""), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * storGlobalsSTOR_GLOBALS
	 *
	 * @param params
	 *
	 */
	public TransactionTypes storGlobals(final TransactionTypes paramBean) {
		final String sql = getQuery("OIDITRAN_STOR_GLOBALS_STOR_GLOBALS");
		final RowMapper<TransactionTypes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, TransactionTypes.class,
				transactionTypesMapping);
		final TransactionTypes returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(""), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * displayNecessaryAlerts
	 *
	 * @param params
	 *
	 */
	public CaseloadAdmAlertProfiles displayNecessaryAlerts(final CaseloadAdmAlertProfiles paramBean) {
		final String sql = getQuery("OIDITRAN_DISPLAY_NECESSARY_ALERTS");
		final RowMapper<CaseloadAdmAlertProfiles> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				CaseloadAdmAlertProfiles.class, caseloadAdmAlertProfilesMapping);
		CaseloadAdmAlertProfiles returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(""),
				columnRowMapper);
		return returnObj;
	}

	@Override
	public TransactionOperations insertMasterRecInsertMasterRec(final TransactionOperations paramBean) {
		return null;
	}

	@Override
	public AccountCodes insertMasterRecinsertMasterRec(final AccountCodes paramBean) {
		return null;
	}

	public Integer offExternalMovmentssgetMaxBookIdMovmentSeq(final Long offenderBookId) {
		final String sql = getQuery("OIDITRAN_INS_INTO_OFF_EXT_MOVEMENT_");
		Integer refList;
		refList = namedParameterJdbcTemplate.queryForObject(sql, createParams("OFFENDER_BOOK_ID", offenderBookId),
				Integer.class);
		return refList;

	}

	@Override
	public List<String> findToAgyLocIdList() {
		final String sql = getQuery("OIDITRAN_TO_AGY_LOC_ID");
		return namedParameterJdbcTemplate.queryForList(sql, createParams(), String.class);
	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstVOffExm
	 *            List<VOffExm>
	 *
	 * @
	 */
	public Integer offBkgCommit(final List<OffenderBookings> lstVOffExm) {
		final String sql = getQuery("OIDITRAN_OFFEM_UPDATE_OFFENDER_BOOKINGS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderBookings vOffExm : lstVOffExm) {
			parameters.add(new BeanPropertySqlParameterSource(vOffExm));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstVOffExm.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	@Override
	public Integer getCountOfAgyInCase(final String caseloadId) {
		final String sql = getQuery("OIDITRAN_GET_COUNT_OF_AGY_IN_CASE");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("CASELOAD_ID", caseloadId), Integer.class);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * oidadmisGetDefaults
	 *
	 *
	 */
	@Override
	public String oidadmisGetDefaults(final String caseloadId) {
		Map<String, Object> returnObject = null;
		String returnObj = null;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[6];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_CASELOAD_ID", OracleTypes.VARCHAR),
				new SqlOutParameter("P_AGY_LOC_ID", OracleTypes.VARCHAR),
				new SqlOutParameter("P_LIVING_UNIT_ID", OracleTypes.VARCHAR), };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("OIDADMIS").withProcedureName("GET_DEFAULTS").declareParameters(sqlParameters);
		inParamMap.put("P_CASELOAD_ID", caseloadId);
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			returnObj = returnObject.get("P_AGY_LOC_ID") != null ? String.valueOf(returnObject.get("P_AGY_LOC_ID"))
					: null;
			returnObj = returnObj.concat("-");
			returnObj = returnObj.concat(returnObject.get("P_LIVING_UNIT_ID") != null
					? String.valueOf(returnObject.get("P_LIVING_UNIT_ID")) : null);

		} catch (NullPointerException e) {
			return returnObj;
		} catch (Exception e) {
			logger.error("In method oidadmisGetDefaults", e);
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * getActiveAgyLocDesc
	 *
	 *
	 */
	public String getActiveAgyLocDesc(final String caseloadId) {
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[4];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_AGY_LOC_ID", OracleTypes.VARCHAR),
				new SqlInOutParameter("RETURN_VALUE", OracleTypes.VARCHAR) };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_ESTABLISHMENT").withFunctionName("GET_ACTIVE_AGY_LOC_DESC")
				.declareParameters(sqlParameters);
		inParamMap.put("P_AGY_LOC_ID", caseloadId);
		inParamMap.put("P_RETURN", "true");
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		final String value = simpleJDBCCall.executeFunction(String.class, inParameter);
		return value;
	}

	@Override
	public LivingUnits populateLivingUnit(final String livUnitId) {
		final String sql = getQuery("OIDITRAN_POPULATE_LIVINGUNIT");
		final RowMapper<LivingUnits> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, LivingUnits.class,
				livingUnitsMapping);
		final LivingUnits returnList = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("LIVUNITID", livUnitId), columnRowMapper);
		return returnList;
	}
	
	public Integer offEmUpdateOffenderExternalMovements(
			final List<OffenderExternalMovements> lstOffenderExternalMovements) {
		final String sql = getQuery("OIDITRAN_OFFEM_UPDATE_OFFENDER_EXTERNAL_MOVEMENTS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderExternalMovements offenderExternalMovements : lstOffenderExternalMovements) {
			parameters.add(new BeanPropertySqlParameterSource(offenderExternalMovements));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));

		if (lstOffenderExternalMovements.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}
	
	@Override
	public Integer noOfBedAvailableInTheGivenLocation(BigDecimal living_unit_id) {
		final String sql = getQuery("OIDITRAN_NO_OF_BED_AVAILABLE_IN_LOCATION");
		Integer availabelBeds = 0;
		try {
			availabelBeds = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("living_unit_id", living_unit_id), Integer.class);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " noOfBedAvailableInTheGivenLocation() ",
					e);
		}

		return availabelBeds;
	}

}
