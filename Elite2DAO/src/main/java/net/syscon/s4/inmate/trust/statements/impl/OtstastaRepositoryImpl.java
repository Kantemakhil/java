package net.syscon.s4.inmate.trust.statements.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.im.beans.SubAccountBalancesBean;
import net.syscon.s4.im.beans.SysDual;
import net.syscon.s4.im.beans.TransStatementBean;
import net.syscon.s4.inmate.beans.Printers;
import net.syscon.s4.inmate.trust.statements.OtstastaRepository;

/**
 * Class OtstastaRepositoryImpl
 */
@Repository
public class OtstastaRepositoryImpl extends RepositoryBase implements OtstastaRepository {
	private static Logger logger = LogManager.getLogger(OtstastaRepositoryImpl.class.getName());
	private final Map<String, FieldMapper> subAccBalMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_ID", 			new FieldMapper("offenderId"))
			.put("CASELOAD_ID", 			new FieldMapper("caseloadId"))
			.put("SUB_ACCOUNT_TYPE", 		new FieldMapper("fAccountName"))
			.put("OPENING_BALANCE", 		new FieldMapper("fOpeningBalance"))
			.put("CLOSING_BALANCE", 		new FieldMapper("fClosigBalance"))
			.put("TYPE", 		            new FieldMapper("fType"))
			.put("PAYABLE", 				new FieldMapper("fPayable"))
			.put("INFO_NUMBER", 			new FieldMapper("fInfoNumber"))
			.put("AMOUNT", 					new FieldMapper("fAmount"))
			.put("AMT_PAID", 				new FieldMapper("fAmtPaid"))
			.put("WRTOFF", 					new FieldMapper("fWriteOff"))
			.put("OFFENDER_DEDUCTION_ID", 	new FieldMapper("offenderDeductionId"))
			.put("MAX_TOTAL_AMOUNT", 		new FieldMapper("maxTotalAmount"))
			.put("MAX_MONTHLY_AMOUNT", 		new FieldMapper("maxMonthlyAmount"))
			.put("ADJUSTMENT_REASON_CODE", 	new FieldMapper("adjustmentReasonCode"))
			.put("OFFENDER_ID1", 			new FieldMapper("offenderIdOne"))
			.put("OFF_ID_DIS", 			    new FieldMapper("fOffenderId"))
			.put("NAME", 					new FieldMapper("fName"))
			.put("BIRTH_DATE", 				new FieldMapper("fBirthDate"))
			.put("LIVING_UNIT", 			new FieldMapper("livingUnit"))
			.put("OFFENDER_BOOK_ID", 		new FieldMapper("offenderBookId"))
			.put("LIVING_UNIT_DESCRIPTION", new FieldMapper("fLivingUnitDescription"))
			.put("FROM_DATE", 				new FieldMapper("beginDate"))
			.put("TO_DATE", 				new FieldMapper("endDate"))
			.put("TOTAL", 					new FieldMapper("fTotal"))
			.put("CURRENT_BALANCE", 		new FieldMapper("fCurrentBalance"))
			.put("HOLD_BALANCE", 			new FieldMapper("fHoldBalance"))
			.put("MAX_DATE", 				new FieldMapper("maxDate"))
			.build();
	private final Map<String, FieldMapper> printersMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PRINTER_ID", 				new FieldMapper("printerId"))
			.put("DESCRIPTION", 			new FieldMapper("description"))
			.build();
	private final Map<String, FieldMapper> referenceCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("LIST_SEQ", 				new FieldMapper("listSeq"))
			.put("DESCRIPTION", 			new FieldMapper("description"))
			.put("SUB_ACCOUNT_TYPE", 		new FieldMapper("subAccountType"))
			.build();
	private final Map<String, FieldMapper> subAccountTransactionsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_ID", 			new FieldMapper("offenderId"))
			.put("CASELOAD_ID", 			new FieldMapper("caseloadId"))
			.put("SUB_ACCT_TYPE", 		new FieldMapper("subAccountType"))
			.put("TXN_ENTRY_DATE", 			new FieldMapper("fTxnEnt"))
			.put("TXN_TYPE", 				new FieldMapper("fTxnType"))
			.put("OFFENDER_BOOK_ID", 		new FieldMapper("offenderBookId"))
			.put("TXN_DESC", 				new FieldMapper("fTxnEntryDesc"))
			.put("TXN_ENTRY_AMOUNT", 		new FieldMapper("fTxnEntryAmount"))
			.put("TXN_ID", 		new FieldMapper("fTxnId"))
			.put("TXN_ENTRY_SEQ", 		new FieldMapper("fTxnEntrySeq"))
			.build();
	private final Map<String, FieldMapper> accountCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CREATE_USER_ID", 			new FieldMapper("createUserId"))
			.put("POSTING_STATUS_FLAG", 	new FieldMapper("postingStatusFlag"))
			.put("MODIFY_USER_ID", 			new FieldMapper("modifyUserId"))
			.put("LIST_SEQ", 				new FieldMapper("listSeq"))
			.put("ACCOUNT_NAME", 			new FieldMapper("fAccountName"))
			.put("CASELOAD_TYPE", 			new FieldMapper("caseloadType"))
			.put("MODIFY_DATE", 			new FieldMapper("modifyDate"))
			.put("ALL_CASELOAD_FLAG", 		new FieldMapper("allCaseloadFlag"))
			.put("SEAL_FLAG", 				new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", 		new FieldMapper("createDatetime"))
			.put("ACCOUNT_CODE", 			new FieldMapper("accountCode"))
			.put("MODIFY_DATETIME", 		new FieldMapper("modifyDatetime"))
			.put("ACCOUNT_TYPE", 			new FieldMapper("accountType"))
			.put("SUB_ACCOUNT_TYPE", 		new FieldMapper("fSubAccountType"))
			.put("PARENT_ACCOUNT_CODE", 	new FieldMapper("parentAccountCode"))
			.put("REC_ACCOUNT_CODE", 		new FieldMapper("recAccountCode"))
			.put("TXN_POSTING_TYPE", 		new FieldMapper("txnPostingType"))
			.put("OFFENDER_ID", 			new FieldMapper("offenderId"))
			.put("CASELOAD_ID", 			new FieldMapper("caseloadId"))
			.build();
	private final Map<String, FieldMapper> systemProfilesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PROFILE_CODE", 			new FieldMapper("profileCode"))
			.put("PROFILE_TYPE", 			new FieldMapper("profileType"))
			.put("CREATE_USER_ID", 			new FieldMapper("createUserId"))
			.put("MODIFY_USER_ID", 			new FieldMapper("modifyUserId"))
			.put("OLD_TABLE_NAME", 			new FieldMapper("oldTableName"))
			.put("LTRIM(RTRIM(DESCRIPTION))", new FieldMapper(" ltrim(rtrim(description)) "))
			.put("PROFILE_VALUE_2", 		new FieldMapper("profileValue2")).put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", 		new FieldMapper("createDatetime"))
			.put("MODIFY_DATETIME", 		new FieldMapper("modifyDatetime"))
			.put("PROFILE_VALUE", 			new FieldMapper(" profileValue "))
			.put("DESCRIPTION", 			new FieldMapper("description"))
			.build();
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CODE", 					new FieldMapper("code"))
			.put("PRINTER_ID", 				new FieldMapper("printerId"))
			.put("DESCRIPTION", 			new FieldMapper("description"))
			.build();
	private final Map<String, FieldMapper> offenderBookingsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("COMM_STAFF_ID", 			new FieldMapper("commStaffId"))
			.put("ROOT_OFFENDER_ID", 		new FieldMapper("rootOffenderId"))
			.put("PHOTO_TAKING_STAFF_ID", 	new FieldMapper("photoTakingStaffId"))
			.put("IN_OUT_STATUS", 			new FieldMapper("inOutStatus"))
			.put("BOOKING_CREATED_DATE", 	new FieldMapper("bookingCreatedDate"))
			.put("SERVICE_FEE_FLAG", 		new FieldMapper("serviceFeeFlag"))
			.put("SEAL_FLAG", 				new FieldMapper("sealFlag"))
			.put("INTAKE_AGY_LOC_ID", 		new FieldMapper("intakeAgyLocId"))
			.put("CREATE_DATETIME", 		new FieldMapper("createDatetime"))
			.put("COMMUNITY_AGY_LOC_ID", 	new FieldMapper("communityAgyLocId"))
			.put("FINGER_PRINTED_STAFF_ID", new FieldMapper("fingerPrintedStaffId"))
			.put("EKSTRAND_CREDIT_LEVEL", 	new FieldMapper("ekstrandCreditLevel"))
			.put("OFFENDER_BOOK_ID", 		new FieldMapper("offenderBookId"))
			.put("COMM_STAFF_ROLE", 		new FieldMapper("commStaffRole"))
			.put("CREATE_USER_ID", 			new FieldMapper("createUserId"))
			.put("CASE_TIME", 				new FieldMapper("caseTime"))
			.put("AGENCY_IML_ID", 			new FieldMapper("agencyImlId"))
			.put("BOOKING_TYPE", 			new FieldMapper("bookingType"))
			.put("ACTIVITY_DATE", 			new FieldMapper("activityDate"))
			.put("NO_COMM_AGY_LOC_ID", 		new FieldMapper("noCommAgyLocId"))
			.put("AGY_LOC_ID", 				new FieldMapper("agyLocId"))
			.put("RECORD_USER_ID", 			new FieldMapper("recordUserId"))
			.put("REQUEST_NAME",			new FieldMapper("requestName"))
			.put("BOOKING_STATUS", 			new FieldMapper("bookingStatus"))
			.put("COMM_STATUS", 			new FieldMapper("commStatus"))
			.put("CASE_DATE", 				new FieldMapper("caseDate"))
			.put("SEARCH_STAFF_ID", 		new FieldMapper("searchStaffId"))
			.put("DISCLOSURE_FLAG", 		new FieldMapper("disclosureFlag"))
			.put("TOTAL_UNEXCUSED_ABSENCES",new FieldMapper("totalUnexcusedAbsences"))
			.put("CREATE_AGY_LOC_ID", 		new FieldMapper("createAgyLocId"))
			.put("LIVING_UNIT_ID", 			new FieldMapper("livingUnitId"))
			.put("OFFENDER_ID", 			new FieldMapper("offenderId"))
			.put("MODIFY_DATETIME", 		new FieldMapper("modifyDatetime"))
			.put("CASE_OFFICER_ID", 		new FieldMapper("caseOfficerId"))
			.put("INTAKE_USER_ID", 			new FieldMapper("intakeUserId"))
			.put("ACTIVE_FLAG", 			new FieldMapper("activeFlag"))
			.put("INTAKE_CASELOAD_ID", 		new FieldMapper("intakeCaseloadId"))
			.put("MODIFY_USER_ID", 			new FieldMapper("modifyUserId"))
			.put("STATUS_REASON", 			new FieldMapper("statusReason"))
			.put("EARNED_CREDIT_LEVEL", 	new FieldMapper("earnedCreditLevel"))
			.put("BOOKING_END_DATE", 		new FieldMapper("bookingEndDate"))
			.put("COMMUNITY_ACTIVE_FLAG", 	new FieldMapper("communityActiveFlag"))
			.put("BOOKING_NO", 				new FieldMapper("bookingNo"))
			.put("YOUTH_ADULT_CODE", 		new FieldMapper("youthAdultCode"))
			.put("ASSIGNED_STAFF_ID", 		new FieldMapper("assignedStaffId"))
			.put("INTAKE_AGY_LOC_ASSIGN_DATE", new FieldMapper("intakeAgyLocAssignDate"))
			.put("BOOKING_BEGIN_DATE", 		new FieldMapper(" bookingBeginDate "))
			.put("CREATE_INTAKE_AGY_LOC_ID", new FieldMapper("createIntakeAgyLocId"))
			.put("AGY_LOC_ID_LIST", 		new FieldMapper("agyLocIdList"))
			.build();

	/**
	 * Creates new OtstastaRepositoryImpl class Object
	 */
	public OtstastaRepositoryImpl() {
		
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            AccountCodes
	 *
	 * @return List<AccountCodes>
	 *
	 * 
	 */
	public List<AccountCodes> acCodeExecuteQuery(final AccountCodes objSearchDao) {
		final String sql = getQuery("OTSTASTA_ACCODE_FIND_ACCOUNT_CODES");
		final RowMapper<AccountCodes> accountCodesRowMapper = Row2BeanRowMapper.makeMapping(sql, AccountCodes.class,
				accountCodesMapping);
		final ArrayList<AccountCodes> returnList = (ArrayList<AccountCodes>) namedParameterJdbcTemplate.query(sql,
				createParams(), accountCodesRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstAccountCodes
	 *            List<AccountCodes>
	 *
	 * @return List<Integer>
	 *
	 * 
	 */
	public Integer acCodeInsertAccountCodes(final List<AccountCodes> lstAccountCodes) {
		final String sql = getQuery("OTSTASTA_ACCODE_INSERT_ACCOUNT_CODES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final AccountCodes accountCodes : lstAccountCodes) {
			parameters.add(new BeanPropertySqlParameterSource(accountCodes));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstAccountCodes.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            OffenderBookings
	 *
	 * @return List<OffenderBookings>
	 *
	 * 
	 */
	public List<OffenderBookings> offBkg1ExecuteQuery(final OffenderBookings objSearchDao) {
		final String sql = getQuery("OTSTASTA_OFFBKG1_FIND_OFFENDER_BOOKINGS");
		final RowMapper<OffenderBookings> offenderBookingsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderBookings.class, offenderBookingsMapping);
		final ArrayList<OffenderBookings> returnList = (ArrayList<OffenderBookings>) namedParameterJdbcTemplate
				.query(sql, createParams(), offenderBookingsRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstOffenderBookings
	 *            List<OffenderBookings>
	 *
	 * @return List<Integer>
	 *
	 * 
	 */
	public Integer offBkg1InsertOffenderBookings(final List<OffenderBookings> lstOffenderBookings) {
		final String sql = getQuery("OTSTASTA_OFFBKG1_INSERT_OFFENDER_BOOKINGS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderBookings offenderBookings : lstOffenderBookings) {
			parameters.add(new BeanPropertySqlParameterSource(offenderBookings));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenderBookings.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstOffenderBookings
	 *            List<OffenderBookings>
	 *
	 * 
	 */
	public Integer offBkg1UpdateOffenderBookings(final List<OffenderBookings> lstOffenderBookings) {
		final String sql = getQuery("OTSTASTA_OFFBKG1_UPDATE_OFFENDER_BOOKINGS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderBookings offenderBookings : lstOffenderBookings) {
			parameters.add(new BeanPropertySqlParameterSource(offenderBookings));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenderBookings.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            SystemProfiles
	 *
	 * @return List<SystemProfiles>
	 *
	 * 
	 */
	public List<SystemProfiles> sysPflExecuteQuery(final SystemProfiles objSearchDao) {
		final String sql = getQuery("OTSTASTA_SYSPFL_FIND_SYSTEM_PROFILES");
		final RowMapper<SystemProfiles> systemProfilesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				SystemProfiles.class, systemProfilesMapping);
		final ArrayList<SystemProfiles> returnList = (ArrayList<SystemProfiles>) namedParameterJdbcTemplate.query(sql,
				createParams(), systemProfilesRowMapper);
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<Printers>
	 */
	public List<Printers> cgfkOmsReqPrinterIdRecordGroup() {
		final String sql = getQuery("OTSTASTA_FIND_CGFKOMSREQPRINTERID");
		final RowMapper<Printers> mRowMapper = Row2BeanRowMapper.makeMapping(sql, Printers.class, mMapping);

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
	public List<ReferenceCodes> cgfkAcCodeSubAccountTypeRecordGroup() {
		final String sql = getQuery("OTSTASTA_FIND_CGFKACCODESUBACCOUNTTYPE");
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
	 * omsReqPreInsert
	 *
	 * @param params
	 *
	 */
	public Object omsReqPreInsert(final SysDual paramBean) {
		final String sql = getQuery("OTSTASTA_OMS_REQ_PREINSERT");
		final RowMapper<SysDual> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, SysDual.class, mMapping);
		final SysDual returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * otstastaKeyCommit
	 *
	 * @param params
	 *
	 */
	public OffenderBookings otstastaKeyCommit(final OffenderBookings paramBean) {
		final String sql = getQuery("OTSTASTA_OTSTASTA_KEYCOMMIT");
		final RowMapper<OffenderBookings> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderBookings.class,
				offenderBookingsMapping);
		final OffenderBookings returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOmsReqOmsReqPrint
	 *
	 * @param params
	 *
	 */
	public Printers cgfkchkOmsReqOmsReqPrint(final Printers paramBean) {
		final String sql = getQuery("OTSTASTA_CGFKCHK_OMS_REQ_OMS_REQ_PRINT");
		final RowMapper<Printers> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Printers.class, printersMapping);
		final Printers returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkAcCodeAcSubAcct
	 *
	 * @param params
	 *
	 */
	public ReferenceCodes cgfkchkAcCodeAcSubAcct(final ReferenceCodes paramBean) {
		final String sql = getQuery("OTSTASTA_CGFKCHK_AC_CODE_AC_SUB_ACCT_T");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		final ReferenceCodes returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * runReport
	 *
	 * @param params
	 *
	 */
	public SystemProfiles runReport(final SystemProfiles paramBean) {
		final String sql = getQuery("OTSTASTA_RUN_REPORT");
		final RowMapper<SystemProfiles> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, SystemProfiles.class,
				systemProfilesMapping);
		final SystemProfiles returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * runReport
	 *
	 * @param params
	 *
	 */
	public List<SubAccountBalancesBean> subAccountBalanceData(final TransStatementBean paramBean) {
		final String sql = getQuery("OTSTASTA_RUN_REPORT_SUMMARY");
		final RowMapper<SubAccountBalancesBean> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				SubAccountBalancesBean.class, subAccBalMapping);
		List<SubAccountBalancesBean> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(sql,
				createParams("CASELOADID", paramBean.getCaseloadId(), "OFFENDERID", paramBean.getOffenderId(),
						"STARTDATE", paramBean.getBeginDate()!=null?new SimpleDateFormat("dd/MM/yyyy").format(paramBean.getBeginDate()):null, "ENDDATE",
								paramBean.getEndDate() !=null?new SimpleDateFormat("dd/MM/yyyy").format(paramBean.getEndDate()):null),
				columnRowMapper);
		return returnList;
	}

	@Override
	public String headerBlockDataQuery(final String headerDta) {
		final String sql = getQuery("OTSTASTA_RUN_REPORT_HEADERDATA");
		String returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("PROFILECODE", headerDta),
					String.class);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}

	@Override
	public String caseloadTypeData(final String caseloadId) {
		final String sql = getQuery("OTSTASTA_RUN_REPORT_CASELOAD_TYPE");
		String returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("CASELOAD", caseloadId),
					String.class);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}

	@Override
	public String caceloadNameOneQuery(final String caseloadId) {
		final String sql = getQuery("OTSTASTA_HEADER_CASELOAD_NAME");
		String returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("CASELOAD", caseloadId),
					String.class);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}

	@Override
	public String fFacilityData() {
		final String sql = getQuery("OTSTASTA_PROFILE_CODE_FACILITY");
		String returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}

	@Override
	public String fDobData() {
		final String sql = getQuery("OTSTASTA_PROFILE_CODE_DOB_DATA");
		String returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * runReport
	 *
	 * @param params
	 *
	 */
	public List<SubAccountBalancesBean> debsObligationsQueryDataBase(final TransStatementBean paramBean) {
		final String sql = getQuery("OTSTASTA_REPORT_DEBS_OBLIGATIONS");
		final RowMapper<SubAccountBalancesBean> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				SubAccountBalancesBean.class, subAccBalMapping);
		List<SubAccountBalancesBean> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(sql,
				createParams("CASELOADID", paramBean.getCaseloadId(), "OFFENDERID", paramBean.getOffenderId(),
						"DISCLOSUREFLAG", paramBean.getDisclosureFlag(), "CASELOADTYPE", paramBean.getCaseloadType()),
				columnRowMapper);
		return returnList;
	}

	@Override
	public String profileValueId() {
		final String sql = getQuery("OTSTASTA_PROFILE_VALUE_ID");
		String returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * runReport
	 *
	 * @param params
	 *
	 */
	public List<SubAccountBalancesBean> subAccountData(final TransStatementBean paramBean) {
		final String sql = getQuery("OTSTASTA_SUB_ACCOUNT_BALANCES_DATA");
		final RowMapper<SubAccountBalancesBean> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				SubAccountBalancesBean.class, accountCodesMapping);
		List<SubAccountBalancesBean> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(sql,
				createParams("CASELOADID", paramBean.getCaseloadId(), "OFFENDERID", paramBean.getOffenderId()),
				columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * runReport
	 *
	 * @param params
	 *
	 */
	public List<TransStatementBean> mainHeaderQuery(final TransStatementBean paramBean) {
		final String sql = getQuery("OTSTASTA_MAIN_HEADER_QUERY_REPORT");
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		String preSqlQuery = null;
		final MapSqlParameterSource params = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (paramBean != null) {
			sqlQuery.append("select off_name.offender_id offender_id,off_ta.caseload_id caseload_id,off_name.root_offender_id offender_id1, "
					+ " off_name.offender_id_display off_id_dis,off_name.last_name || ', ' || off_name.first_name || ' ' || coalesce(off_name.middle_name,'') as name, "
					+ " off_name.birth_date birth_date,off_bkg.living_unit_id living_unit,off_bkg.offender_book_id,case "
					+ " when agy_loc.agency_location_type = 'COMM' then off_bkg.agy_loc_id || ' ' || staff.first_name || ', ' || staff.last_name "
					+ " else liv_unit.description end living_unit_description,coalesce(to_date(:STARTDATE::Text, 'dd/mm/yyyy'), date_trunc('day', off_bkg.booking_begin_date))::date as from_date, "
					+ " coalesce(to_date(:ENDDATE::Text, 'dd/mm/yyyy'), current_timestamp)::date as to_date,coalesce(off_ta.current_balance, 0)+ coalesce(off_ta.hold_balance, 0) total, "
					+ " off_ta.current_balance,off_ta.hold_balance,os_aggs.actual_max_date max_date from offender_trust_accounts off_ta,offenders off_name,offender_bookings off_bkg "
					+ " left outer join living_units liv_unit on (off_bkg.living_unit_id = liv_unit.living_unit_id) left outer join offender_sentence_aggs os_aggs on (off_bkg.offender_book_id = os_aggs.offender_book_id) "
					+ " left outer join staff_members staff on (off_bkg.assigned_staff_id = staff.staff_id) left outer join agency_locations agy_loc on(off_bkg.agy_loc_id = agy_loc.agy_loc_id) "
					+ " where off_name.root_offender_id between coalesce(:OFFENDERID, 0) and coalesce(:OFFENDERID, 9999999999) and ( (off_name.root_offender_id between coalesce(:OFFENDERID, 0) and coalesce(:OFFENDERID, 9999999999) "
					+ " and coalesce(:BATCHID::text, '') = '') or ( (:BATCHID::text is not null and :BATCHID::text <> '') and exists (select offender_id from batch_offenders_tmp where module_name = 'OTRTASTA' "
					+ " and report_batch_id = :BATCHID and offender_id = off_name.root_offender_id )) or (coalesce(:OFFENDERID::text, '') = ''and coalesce(:BATCHID::text, '') = '')) "
					+ " and exists (select 1 from offender_sub_accounts where caseload_id = off_ta.caseload_id and offender_id = off_ta.offender_id )and off_bkg.offender_book_id = ( "
					+ " select MAX(offender_book_id)from offender_bookings ob,offenders o where o.offender_id = ob.offender_id and o.root_offender_id = off_ta.offender_id "
					+ " and ob.booking_type = (select caseload_type from caseloads where caseload_id = :CASELOADID))and off_ta.caseload_id = :CASELOADID and off_ta.offender_id = off_name.root_offender_id "
					+ " and off_name.offender_id = off_bkg.offender_id ");
			params.addValue("CASELOADID", paramBean.getCaseloadId());
			params.addValue("OFFENDERID", paramBean.getOffenderId());
			if (null != paramBean.getBeginDate()) {
				params.addValue("STARTDATE", new SimpleDateFormat("dd/MM/yyyy").format(paramBean.getBeginDate()) );
			} else {
				params.addValue("STARTDATE", null);
			}
			if (null != paramBean.getEndDate()) {
				params.addValue("ENDDATE", new SimpleDateFormat("dd/MM/yyyy").format(paramBean.getEndDate()));
			} else {
				params.addValue("ENDDATE", null);
			}
			params.addValue("BATCHID", paramBean.getBatchId());
		}
		if (paramBean != null && "COMM".equals(paramBean.getCaseloadType())) {
			sqlQuery.append(
					" AND ( off_bkg.intake_agy_loc_id = NVL(:CASELOADID, off_bkg.intake_agy_loc_id) OR off_bkg.intake_agy_loc_id IS NULL) "
							+ " AND ( off_bkg.intake_agy_loc_id IN (SELECT Caseload_ID FROM Caseloads CSLD WHERE Community_Trust_Caseload = :CASELOADID AND Caseload_Type = 'COMM' "
							+ " UNION SELECT Caseload_ID FROM Caseloads CSLD WHERE Caseload_ID = :CASELOADID AND Caseload_Type = 'COMM' UNION SELECT cal.agy_loc_id "
							+ " FROM caseload_agency_locations cal, caseloads cas WHERE cas.caseload_type = 'COMM' AND cas.caseload_id = cal.caseload_id AND cas.caseload_id = :CASELOADID "
							+ " UNION SELECT cal.agy_loc_id FROM caseload_agency_locations cal, caseloads cas WHERE cas.caseload_type = 'COMM' AND cas.caseload_id = cal.caseload_id "
							+ " AND cas.community_trust_caseload = :CASELOADID) OR off_bkg.intake_agy_loc_id IS NULL ) ");
			params.addValue("CASELOADID", paramBean.getCaseloadId());
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("where")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("and")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		preSqlQuery = preparedSql.concat(" ORDER BY OFF_NAME.ROOT_OFFENDER_ID, LIVING_UNIT_DESCRIPTION ");

		final RowMapper<TransStatementBean> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				TransStatementBean.class, subAccBalMapping);
		List<TransStatementBean> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(preSqlQuery, params, columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * runReport
	 *
	 * @param params
	 *
	 */
	public List<SubAccountBalancesBean> subAccountTransactionQueryDataBase(final TransStatementBean paramBean) {
		final String sql = getQuery("OTSTASTA_SUB_ACCOUNT_TRANSACTIONS_QUERY");
		List<SubAccountBalancesBean> list=Collections.checkedList(new ArrayList<SubAccountBalancesBean>(), SubAccountBalancesBean.class);
		final RowMapper<SubAccountBalancesBean> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				SubAccountBalancesBean.class, subAccountTransactionsMapping);
		
		try {
			MapSqlParameterSource param = createParams("CASELOADID", paramBean.getCaseloadId(), "OFFENDERID", paramBean.getOffenderId(),
					"STARTDATE", new SimpleDateFormat("dd/MM/yyyy").format(paramBean.getBeginDate()), "ENDDATE",
					new SimpleDateFormat("dd/MM/yyyy").format(paramBean.getEndDate()), "SUBACT",
					paramBean.getSubAccountType(), "DISCLOSUREFLAG", paramBean.getDisclosureFlag());
			param.getValues().forEach((k,v) -> {
				logger.warn(k  + " - - >" + v);
			});
		list= namedParameterJdbcTemplate.query(sql, param, columnRowMapper);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+" error in subAccountTransactionQueryDataBase"+e);
		}
		return list;
		
	}
	
	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * runReport
	 *
	 * @param params
	 *
	 */
	public List<SubAccountBalancesBean> subAccountTransactionQueryDataBaseWithoutSubAccType(
			final TransStatementBean paramBean) {
		final String sql = getQuery("OTSTASTA_SUB_ACCOUNT_TRANSACTIONS_QUERY_WITHOUT_SUB_ACCOUNT_TYPE");
		final RowMapper<SubAccountBalancesBean> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				SubAccountBalancesBean.class, subAccountTransactionsMapping);

		try {
			MapSqlParameterSource param = createParams("CASELOADID", paramBean.getCaseloadId(), "OFFENDERID", paramBean.getOffenderId(),
					"STARTDATE", new SimpleDateFormat("dd/MM/yyyy").format(paramBean.getBeginDate()), "ENDDATE",
					new SimpleDateFormat("dd/MM/yyyy").format(paramBean.getEndDate()), "DISCLOSUREFLAG",
					paramBean.getDisclosureFlag());
			param.getValues().forEach((k,v) -> {
				logger.warn(k  + " - - >" + v);
			});
			return namedParameterJdbcTemplate.query(sql, param, columnRowMapper);
		} catch (Exception e) {
			return Collections.emptyList();
		}

	}

	@Override
	public String bookLabelData() {
		final String sql = getQuery("OTSTASTA_BOOK_LABEL_DATA");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public String bookingNumberData(final Long offenderBookId) {
		final String sql = getQuery("OTSTASTA_BOOKING_NUMBER_QUERY");
		String returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("OFFENDERBOOKID", offenderBookId),
					String.class);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}

	@Override
	public String dollarSymbolQuery() {
		final String sql = getQuery("OTSTASTA_DOLLAR_SYMBOL");
		String returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}

	@Override
	public String accountCodesRegSavData(final String caseloadType, final String subAccountType) {
		final String sql = getQuery("OTSTASTA_ACCOUNT_CODE_REG_SAV");
		String returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("CASELOADTYPE", caseloadType, "SUBACCOUNTTYPE", subAccountType), String.class);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}
	
	@Override
	public BigDecimal gettingBalance(final BigDecimal openingBalnce, final BigDecimal txnEntryAmount) {
		final String sql = getQuery("OTSTASTA_GETTING_BALANCE");
		BigDecimal returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("OPENINGBALANCE", openingBalnce, "TXNENTRYAMOUNT", txnEntryAmount), BigDecimal.class);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}
}
