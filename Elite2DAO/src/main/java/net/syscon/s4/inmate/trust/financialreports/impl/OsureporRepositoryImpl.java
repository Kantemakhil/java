package net.syscon.s4.inmate.trust.financialreports.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.cf.deductions.beans.FeeAccountBalanceBean;
import net.syscon.s4.cf.deductions.beans.offBillingStatements;
import net.syscon.s4.common.beans.OffenderBillingProfiles;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.im.beans.CasePlans;
import net.syscon.s4.im.beans.Dual;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.im.beans.OmsModuleParameters;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.im.beans.OtrbnrcnBean;
import net.syscon.s4.im.beans.VAgencyAddresses;
import net.syscon.s4.inmate.trust.financialreports.OsureporRepository;
import net.syscon.s4.inst.schedules.bean.VAddressUsages;

/**
 * Class OsureporRepositoryImpl
 */
@Repository
public class OsureporRepositoryImpl extends RepositoryBase implements OsureporRepository {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger log = LogManager.getLogger(OsureporRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> omsModuleParametersMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PARAMETER_DOMAIN", new FieldMapper("parameterDomain"))
			.put("PARAMETER_NAME", new FieldMapper("parameterName"))
			.put("NBT_MODULE_NAME", new FieldMapper("nbtModuleName"))
			.put("PARAMETER_TYPE", new FieldMapper("parameterType")).put("Y", new FieldMapper("y"))
			.put("PARAMETER_SEQ", new FieldMapper("parameterSeq"))
			.put("NBT_PARAMETER_NAME", new FieldMapper("nbtParameterName"))
			.put("PARAMETER_LOV_SELECT", new FieldMapper("parameterLovSelect"))
			.put("NBT_VALUE", new FieldMapper("nbtValue")).put("OPTIONAL_FLAG", new FieldMapper("optionalFlag"))
			.build();
	private final Map<String, FieldMapper> offenderBillingProfilesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DISTINC", new FieldMapper("distinc")).put("EFFECTIVE_DATE_END", new FieldMapper("effectiveDateEnd"))
			.put("EFFECTIVE_DATE_START", new FieldMapper("effectiveDateStart")).build();
	private final Map<String, FieldMapper> omsModulesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description")).put("MODULE_NAME", new FieldMapper("moduleName"))
			.put("OUTPUT_TYPE", new FieldMapper("outputType")).put("APPLN_CODE", new FieldMapper("applnCode")).build();
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("NULL", new FieldMapper("null")).put("DESCRIPTION", new FieldMapper("description ")).build();
	private final Map<String, FieldMapper> systemProfilesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("MODULE_NAME", new FieldMapper("moduleName")).put("DESCRIPTION", new FieldMapper("description"))
			.put("PROFILE_VALUE", new FieldMapper("profileValue"))
			.put("LTRIM(RTRIM(DESCRIPTION))", new FieldMapper("ltrim(rtrim(description))"))
			.put("PRINTER_ALIAS_CODE", new FieldMapper("printerAliasCode")).put("NUM_COPY", new FieldMapper("numCopy"))
			.build();
	private final Map<String, FieldMapper> refCodeMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CODE", new FieldMapper("code")).put("DESCRIPTION", new FieldMapper("description")).build();
	private final Map<String, FieldMapper> vAgyAddrMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("ADDRESS_ID", new FieldMapper("addressId")).put("ADDRESS_TYPE", new FieldMapper("addressType"))
			.put("ADDRESS_TYPE_DESC", new FieldMapper("addressTypeDesc")).put("AGY_LOC_ID", new FieldMapper("agyLocId"))
			.put("START_DATE", new FieldMapper("startDate")).put("END_DATE", new FieldMapper("endDate"))
			.put("ACTIVE_FLAG", new FieldMapper("activeFlag")).put("STREET", new FieldMapper("street"))
			.put("AREA", new FieldMapper("area")).put("COUNTRY", new FieldMapper("country"))
			.put("STREET_NUMBER", new FieldMapper("streetNumber")).put("MAIL_CARE_OF", new FieldMapper("mailCareOf"))
			.put("COUNTRY_CODE", new FieldMapper("countryCode")).put("HOUSE", new FieldMapper("house"))
			.put("STREET_DIRECTION", new FieldMapper("streetDirection"))
			.put("STREET_DIRECTION_DESC", new FieldMapper("streetDirectionDesc"))
			.put("STREET_INFORMATION", new FieldMapper("streetInformation"))
			.put("CITY_CODE", new FieldMapper("cityCode")).put("CITY_NAME", new FieldMapper("cityName"))
			.put("PROV_STATE_CODE", new FieldMapper("provStateCode"))
			.put("PROV_STATE_DESC", new FieldMapper("provStateDesc"))
			.put("ZIP_POSTAL_CODE", new FieldMapper("zipPostalCode"))
			.put("SUITE_NUMBER", new FieldMapper("suiteNumber")).put("COMMENT_TEXT", new FieldMapper("commentText"))
			.put("PRIMARY_FLAG", new FieldMapper("primaryFlag")).put("MAIL_FLAG", new FieldMapper("mailFlag"))
			.put("VALIDATED_FLAG", new FieldMapper("validatedFlag")).build();
	private final Map<String, FieldMapper> accountCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("ACCOUNT_NAME", new FieldMapper("accountName")).put("ACC_CODE", new FieldMapper("accountCode"))
			.put("DISPLAY_AMNT_BNK", new FieldMapper("displayAmntBnk")).put("AMNT_P_ADJ", new FieldMapper("amntPAdj"))
			.put("AMNT_L_ADJ", new FieldMapper("amntLAdj")).put("AMNT_ADJ_BLNC", new FieldMapper("amntAdjBalance"))
			.put("ADJUSTMENT_COMMENT", new FieldMapper("adjComment"))
			.put("DESC_ACNT_C", new FieldMapper("accountCodeDesc")).put("CR_TX", new FieldMapper("crTxn"))
			.put("CR_DATE", new FieldMapper("fCrdate2")).put("CR_TYPE", new FieldMapper("crType"))
			.put("CR_RFRNC", new FieldMapper("fCrReference2")).put("CR_PYE", new FieldMapper("fCrPye2"))
			.put("CR_AMT_TEMP", new FieldMapper("fCrAmount2")).put("DR_DATE", new FieldMapper("fDrDate1"))
			.put("DR_TYPE", new FieldMapper("fDr")).put("DR_RFRNC", new FieldMapper("fDrReference1"))
			.put("DR_DESC", new FieldMapper("fDrDesc")).put("DR_AMNT", new FieldMapper("fDrAmount"))
			.put("TXN_ENTRY_DATE", new FieldMapper("fCrdate1")).put("TXN_ID", new FieldMapper("crTxn"))
			.put("TXN_ENTRY_DESC", new FieldMapper("fCrPye1")).put("AMOUNT", new FieldMapper("fCrAmount1")).build();
	private final Map<String, FieldMapper> casePlansMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CREATION_USER", new FieldMapper("creationUser")).put("AGY_LOC_ID", new FieldMapper("agyLocId"))
			.put("SAC_STAFF_I", new FieldMapper("sacStaffI"))
			.put("AUTO_ASSESS_MODIFY_DATETIME", new FieldMapper("autoAssessModifyDatetime"))
			.put("CAL_AGY_LOC_ID", new FieldMapper("calAgyLocId"))
			.put("PROFILE_VALUE", new FieldMapper(" profileValue "))
			.put("INST_POSITION", new FieldMapper("instPosition"))
			.put("0)", new FieldMapper("0)"))
			.put("INST_SAC_STAFF_I", new FieldMapper("instSacStaffI"))
			.put("CREATION_DATE", new FieldMapper("creationDate"))
			.put("CASE_PLAN_STATUS", new FieldMapper("casePlanStatus"))
			.put("officer", new FieldMapper("officer"))
			.build();
	private final Map<String, FieldMapper> offFeeMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("begining_balance_amount", new FieldMapper("beginingBalanceAmount"))
			.put("payments_credits_amount", new FieldMapper("paymentsCreditsAmount"))
			.put("billings_amount", new FieldMapper("billingsAmount"))
			.put("ending_balance_amount", new FieldMapper("endingBalanceAmount"))
			.put("CASELOAD_ID", new FieldMapper("caseloadId"))
			.put("BILL_ID", new FieldMapper("billId"))
			.put("BILLINGS_AMOUNT", new FieldMapper("billingsAmount"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("ROOT_OFFENDER_ID", new FieldMapper("rootOffenderId"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("feeCode", new FieldMapper("feeCode"))
			.put("paymentAmount", new FieldMapper("paymentAmount"))
			.put("caseloadDescription", new FieldMapper("caseloadDescription")).build();
	private final Map<String, FieldMapper> addressUsagesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.build();
	/**
	 * Creates new OsureporRepositoryImpl class Object
	 */
	public OsureporRepositoryImpl() {
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<Dual>
	 */
	public List<Dual> rg1cRecordGroup() {
		final String sql = getQuery("OSUREPOR_FIND_RG1C");
		final RowMapper<Dual> mRowMapper = Row2BeanRowMapper.makeMapping(sql, Dual.class, mMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<Dual>
	 */
	public List<Dual> rg2cRecordGroup() {
		final String sql = getQuery("OSUREPOR_FIND_RG2C");
		final RowMapper<Dual> mRowMapper = Row2BeanRowMapper.makeMapping(sql, Dual.class, mMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<Dual>
	 */
	public List<Dual> rg3cRecordGroup() {
		final String sql = getQuery("OSUREPOR_FIND_RG3C");
		final RowMapper<Dual> mRowMapper = Row2BeanRowMapper.makeMapping(sql, Dual.class, mMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<Dual>
	 */
	public List<Dual> rg4cRecordGroup() {
		final String sql = getQuery("OSUREPOR_FIND_RG4C");
		final RowMapper<Dual> mRowMapper = Row2BeanRowMapper.makeMapping(sql, Dual.class, mMapping);
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
	public List<Dual> rg5cRecordGroup() {
		final String sql = getQuery("OSUREPOR_FIND_RG5C");
		final RowMapper<Dual> mRowMapper = Row2BeanRowMapper.makeMapping(sql, Dual.class, mMapping);
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
	public List<Dual> rg5c1RecordGroup() {
		final String sql = getQuery("OSUREPOR_FIND_RG5C1");
		final RowMapper<Dual> mRowMapper = Row2BeanRowMapper.makeMapping(sql, Dual.class, mMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
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
	public OmsModules createFormGlobals(final OmsModules paramBean) {
		final String sql = getQuery("OSUREPOR_CREATE_FORM_GLOBALS");
		final RowMapper<OmsModules> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OmsModules.class,
				omsModulesMapping);
		OmsModules returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * populateRecords
	 *
	 * @param params
	 *
	 */
	public List<OmsModuleParameters> populateRecords(final OmsModuleParameters paramBean) {
		final String sql = getQuery("OSUREPOR_POPULATE_RECORDS");
		final RowMapper<OmsModuleParameters> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OmsModuleParameters.class, omsModuleParametersMapping);
		List<OmsModuleParameters> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(sql, createParams("MODULENAME", paramBean.getModuleName()),
				columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * populateRecordstemp
	 *
	 * @param params
	 *
	 */
	public SystemProfiles populateRecordstemp(final SystemProfiles paramBean) {
		final String sql = getQuery("OSUREPOR_POPULATE_RECORDS_TEMP");
		final RowMapper<SystemProfiles> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, SystemProfiles.class,
				systemProfilesMapping);
		SystemProfiles returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * submitRequestSUBMIT_REQUEST
	 *
	 * @param params
	 *
	 */
	public OmsModules submitRequestSUBMIT_REQUEST(final OmsModules paramBean) {
		final String sql = getQuery("OSUREPOR_SUBMIT_REQUEST_SUBMIT_REQUEST");
		final RowMapper<OmsModules> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OmsModules.class,
				omsModulesMapping);
		OmsModules returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
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
		final String sql = getQuery("OSUREPOR_RUN_REPORT");
		final RowMapper<SystemProfiles> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, SystemProfiles.class,
				systemProfilesMapping);
		SystemProfiles returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * runReportrole_cur
	 *
	 * @param params
	 *
	 */
	public OmsModules runReportrole_cur(final OmsModules paramBean) {
		final String sql = getQuery("OSUREPOR_RUN_REPORT_ROLE_CUR");
		final RowMapper<OmsModules> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OmsModules.class,
				omsModulesMapping);
		OmsModules returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * populateReportName
	 *
	 * @param params
	 *
	 */
	public List<OmsModules> populateReportName(final OmsModules paramBean) {
		final String sql = getQuery("OSUREPOR_POPULATE_REPORT_NAME");
		final RowMapper<OmsModules> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OmsModules.class,
				omsModulesMapping);
		final ArrayList<OmsModules> returnList = (ArrayList<OmsModules>) namedParameterJdbcTemplate.query(sql,
				createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * preInvInsert
	 *
	 * @param params
	 *
	 */
	public OffenderBillingProfiles preInvInsert(final OffenderBillingProfiles paramBean) {
		final String sql = getQuery("OSUREPOR_PRE_INV_INSERT");
		final RowMapper<OffenderBillingProfiles> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderBillingProfiles.class, offenderBillingProfilesMapping);
		OffenderBillingProfiles returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				columnRowMapper);
		return returnObj;
	}

	public String profileValueData() {
		String returnData = null;
		final String sql = getQuery("OSUREPOR_PROFILE_VALUE");
		try {
			returnData = namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);
		} catch (Exception e) {
			return returnData;
		}
		return returnData;
	}

	public String profileValueDataOne() {
		String returnData = null;
		final String sql = getQuery("OSUREPOR_PROFILE_VALUE_ONE");
		try {
			returnData = namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);
		} catch (Exception e) {
			return returnData;
		}
		return returnData;
	}

	public String profileValueDataTwo() {
		String returnData = null;
		final String sql = getQuery("OSUREPOR_PROFILE_VALUE_TWO");
		try {
			returnData = namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);
		} catch (Exception e) {
			return returnData;
		}
		return returnData;
	}

	public List<ReferenceCodes> getDynamicLov(final String qry, final String caseload, final BigDecimal accCode) {
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(qry, ReferenceCodes.class,
				refCodeMapping);
		final List<ReferenceCodes> returnList = namedParameterJdbcTemplate.query(qry,
				createParams("global.caseload_id", caseload, "global.account_code", accCode), columnRowMapper);
		return returnList;
	}

	public List<AccountCodes> matserdata(final String caseloadId) {
		final String sql = getQuery("OSUREPOR_MATSERDATA");
		final RowMapper<AccountCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, AccountCodes.class,
				accountCodesMapping);
		final List<AccountCodes> returnList = namedParameterJdbcTemplate.query(sql,
				createParams("caseloadId", caseloadId), columnRowMapper);
		return returnList;
	}

	public List<AccountCodes> childData(final String caseloadId, final Long parentAccountCode) {
		final String sql = getQuery("OSUREPOR_CHILDDATA");
		final RowMapper<AccountCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, AccountCodes.class,
				accountCodesMapping);
		final List<AccountCodes> returnList = namedParameterJdbcTemplate.query(sql,
				createParams("caseloadId", caseloadId, "parentAccountCode", parentAccountCode), columnRowMapper);
		return returnList;
	}

	public List<AccountCodes> subChildData(final String caseloadId, final Integer accountCode) {
		final String sql = getQuery("OSUREPOR_SUBCHILDDATA");
		final RowMapper<AccountCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, AccountCodes.class,
				accountCodesMapping);
		final List<AccountCodes> returnList = namedParameterJdbcTemplate.query(sql,
				createParams("caseloadId", caseloadId, "accountCode", accountCode), columnRowMapper);
		return returnList;
	}

	public List<AccountCodes> getReport(final String caseloadId, final String flag) {
		return null;
	}

	public BigDecimal getTempCloseBalance(final String caseloadId, final Integer accountCode) {
		final String sql = getQuery("OSUREPOR_GETTEMPCLOSEBALANCE");
		BigDecimal amount = BigDecimal.ZERO;
		try {
			amount = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("caseloadId", caseloadId, "accountCode", accountCode), BigDecimal.class);
		} catch (EmptyResultDataAccessException e) {
			amount = BigDecimal.ZERO;
		}
		return amount;
	}

	public BigDecimal gettempTxnAmount(final String caseloadId, final Long accountPeriod, final Integer accountCode) {
		final String sql = getQuery("OSUREPOR_GETTEMPTXNAMOUNT");
		BigDecimal amount = BigDecimal.ZERO;
		try {
			amount = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("caseloadId", caseloadId, "accountPeriod", accountPeriod, "accountCode", accountCode),
					BigDecimal.class);
		} catch (EmptyResultDataAccessException e) {
			amount = BigDecimal.ZERO;
		}
		return amount;
	}

	public BigDecimal gettempTxnMonAmount(final String caseloadId, final Long accountPeriod, final Integer accountCode,
			final Date d) {
		final String sql = getQuery("OSUREPOR_GETTEMPTXNMONAMOUNT");
		BigDecimal amount = BigDecimal.ZERO;
		try {
			amount = namedParameterJdbcTemplate.queryForObject(sql, createParams("caseloadId", caseloadId,
					"accountPeriod", accountPeriod, "accountCode", accountCode, "dbDate", d), BigDecimal.class);
		} catch (EmptyResultDataAccessException e) {
			amount = BigDecimal.ZERO;
		}
		return amount;
	}

	public BigDecimal getOpenBalance(final Long accountPeriod, final String caseloadId, final Integer accountCode) {
		final String sql = getQuery("OSUREPOR_GETOPENBALANCE");
		BigDecimal amount = BigDecimal.ZERO;
		try {
			amount = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("accountPeriod", accountPeriod, "caseloadId", caseloadId, "accountCode", accountCode),
					BigDecimal.class);
		} catch (EmptyResultDataAccessException e) {
			amount = BigDecimal.ZERO;
		}
		return amount;
	}

	public BigDecimal getctempTxnAmount(final Long accountPeriod, final String caseloadId, final Integer accountCode,
			final Date d) {
		final String sql = getQuery("OSUREPOR_GETCTEMPTXNAMOUNT");
		BigDecimal amount = BigDecimal.ZERO;
		try {
			amount = namedParameterJdbcTemplate.queryForObject(sql, createParams("accountPeriod", accountPeriod,
					"caseloadId", caseloadId, "accountCode", accountCode, "dbDate", d), BigDecimal.class);
		} catch (EmptyResultDataAccessException e) {
			amount = BigDecimal.ZERO;
		}
		return amount;
	}

	public Long getAccountPeriod(final Date date) {
		final String sql = getQuery("OSUREPOR_GETACCOUNTPERIOD");
		Long periodId = null;
		try {
			periodId = namedParameterJdbcTemplate.queryForObject(sql, createParams("USERDATE", date), Long.class);
		} catch (EmptyResultDataAccessException e) {
			log.error("getAccountPeriod", e);
		}
		return periodId;
	}

	public List<OtrbnrcnBean> getBankReconciliationReport(final Long accountCode, final String userDate,
			final String caseloadId) {
		final String sql = getQuery("OSUREPOR_GETBANKRECONCILIATIONREPORT");
		final RowMapper<OtrbnrcnBean> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OtrbnrcnBean.class,
				accountCodesMapping);
		List<OtrbnrcnBean> returnList = new ArrayList<OtrbnrcnBean>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("accountCode", accountCode, "userDate", userDate, "caseloadId", caseloadId),
					columnRowMapper);
		} catch (Exception e) {
			log.error("getBankReconciliationReport", e);
		}
		return returnList;
	}

	@Override
	public String reportApplnCode(final String moduleName) {
		final String sql = getQuery("OSUREPOR_SUBMIT_REQUESTSUBMIT_REQUEST");
		String reportAplnCode = null;
		try {
			reportAplnCode = namedParameterJdbcTemplate.queryForObject(sql, createParams("MODULENAME", moduleName),
					String.class);
		} catch (EmptyResultDataAccessException e) {
			log.error("getAccountPeriod", e);
		}
		return reportAplnCode;
	}

	public List<OtrbnrcnBean> getQuery3List(Long accountCode, String userDate, String caseloadId) {
		final String sql = getQuery("OSUREPOR_GETQUERY3LIST");
		final RowMapper<OtrbnrcnBean> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OtrbnrcnBean.class,
				accountCodesMapping);
		List<OtrbnrcnBean> returnList = new ArrayList<OtrbnrcnBean>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("accountCode", accountCode, "userDate", userDate, "caseloadId", caseloadId),
					columnRowMapper);
		} catch (Exception e) {
			log.error("getBankReconciliationReport", e);
		}
		return returnList;
	}

	public BigDecimal getQuery4List(Long crTxn, Long accountCode) {
		final String sql = getQuery("OSUREPOR_GETQUERY4LIST");
		BigDecimal amnt = BigDecimal.ZERO;
		amnt = namedParameterJdbcTemplate.queryForObject(sql, createParams("crTxn", crTxn, "accountCode", accountCode),
				BigDecimal.class);
		return amnt;
	}

	public List<OtrbnrcnBean> getQuery5List(final Long accountCode, final String userDate, final String caseloadId) {
		final String sql = getQuery("OSUREPOR_GETQUERY5LIST");
		final RowMapper<OtrbnrcnBean> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OtrbnrcnBean.class,
				accountCodesMapping);
		List<OtrbnrcnBean> returnList = new ArrayList<OtrbnrcnBean>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("accountCode", accountCode, "userDate", userDate, "caseloadId", caseloadId),
					columnRowMapper);
		} catch (Exception e) {
			log.error("getBankReconciliationReport", e);
		}
		return returnList;
	}

	public BigDecimal getTotalCrAmnt(Long accountCode, String userDate, String caseloadId) {
		final String sql = getQuery("OSUREPOR_GETTOTALCRAMNT");
		BigDecimal amnt = BigDecimal.ZERO;
		try {
			amnt = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("accountCode", accountCode, "userDate", userDate, "caseloadId", caseloadId),
					BigDecimal.class);
			if (amnt == null) {
				amnt = BigDecimal.ZERO;
			}
		} catch (NullPointerException e) {
			log.error("Exception: getTotalCrAmnt", e);
			amnt = BigDecimal.ZERO;
		}
		return amnt;
	}

	public BigDecimal getTotalDrAmnt(Long accountCode, String userDate, String caseloadId) {
		final String sql = getQuery("OSUREPOR_GETTOTALDRAMNT");
		BigDecimal amnt = BigDecimal.ZERO;
		try {
			amnt = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("accountCode", accountCode, "userDate", userDate, "caseloadId", caseloadId),
					BigDecimal.class);
			if (amnt == null) {
				amnt = BigDecimal.ZERO;
			}
		} catch (NullPointerException e) {
			log.error("Exception: getTotalDrAmnt", e);
			amnt = BigDecimal.ZERO;
		}
		return amnt;
	}

	public List<OtrbnrcnBean> getQuery4List(Long accountCode, String userDate, String caseloadId) {
		final String sql = getQuery("OSUREPOR_GETQUERY4LIST");
		final RowMapper<OtrbnrcnBean> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OtrbnrcnBean.class,
				accountCodesMapping);
		List<OtrbnrcnBean> returnList = new ArrayList<OtrbnrcnBean>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("accountCode", accountCode, "userDate", userDate, "caseloadId", caseloadId),
					columnRowMapper);
		} catch (Exception e) {
			log.error("Exception: getQuery4List", e);
			returnList = new ArrayList<>();
		}
		return returnList;
	}

	public List<OtrbnrcnBean> getQueryFourMainList(Long accountCode, String userDate, String caseloadId) {
		final String sql = getQuery("OSUREPOR_GETQUERYFOURMAINLIST");
		final RowMapper<OtrbnrcnBean> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OtrbnrcnBean.class,
				accountCodesMapping);
		List<OtrbnrcnBean> returnList = new ArrayList<OtrbnrcnBean>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("accountCode", accountCode, "userDate", userDate, "caseloadId", caseloadId),
					columnRowMapper);
		} catch (Exception e) {
			log.error("Exception: getQueryFourMainList", e);
			returnList = new ArrayList<>();
		}
		return returnList;
	}

	public BigDecimal queryFourSubtotalAmnt(Long accountCode, String userDate) {
		final String sql = getQuery("OSUREPOR_QUERYFOURSUBTOTALAMNT");
		BigDecimal amount = BigDecimal.ZERO;
		try {
			amount = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("accountCode", accountCode, "userDate", userDate), BigDecimal.class);
		} catch (NullPointerException e) {
			log.error("Exception: queryFourSubtotalAmnt", e);
			amount = BigDecimal.ZERO;

		}
		return amount;
	}

	public BigDecimal getFsysBal(final String caseloadId, final Long accountCode, final String userDate) {
		final String sql = getQuery("OSUREPOR_GETFSYSBAL");
		BigDecimal amount = BigDecimal.ZERO;

		try {
			amount = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("caseloadId", caseloadId, "accountCode", accountCode, "userDate", userDate),
					BigDecimal.class);
		} catch (NullPointerException e) {
			log.error("Exception: getFsysBal", e);
			amount = BigDecimal.ZERO;

		}
		return amount;
	}

	public BigDecimal getFsysBalN(String caseloadId, Long accountCode) {
		final String sql = getQuery("OSUREPOR_GETFSYSBALN");
		BigDecimal amount = BigDecimal.ZERO;

		try {
			amount = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("caseloadId", caseloadId, "accountCode", accountCode), BigDecimal.class);
		} catch (NullPointerException e) {
			log.error("Exception: getFsysBalN", e);
			amount = BigDecimal.ZERO;

		}
		return amount;
	}

	public String getProfileVal() {
		final String sql = getQuery("OSUREPOR_GETPROFILEVAL");
		String val = null;
		try {
			val = namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);
		} catch (Exception e) {
			log.error("getProfileVal", e);
		}

		return val;
	}

	public BigDecimal queryFourAdjustmentAmount(Long accountCode, String userDate) {
		final String sql = getQuery("OSUREPOR_QUERYFOURADJUSTMENTAMOUNT");
		BigDecimal amount = BigDecimal.ZERO;
		try {
			amount = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("accountCode", accountCode, "userDate", userDate), BigDecimal.class);
		} catch (NullPointerException e) {
			log.error("Exception: queryFourAdjustmentAmount", e);
			amount = BigDecimal.ZERO;

		}
		return amount;
	}

	public String getUserName() {
		final String sql = getQuery("OSUREPOR_GET_USER_NAME");
		String returnVal = null;
		try {
			returnVal = namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);
		} catch (Exception e) {
			log.error("Exception: getUserName", e);
			return returnVal;
		}
		return returnVal;
	}

	@Override
	public String getfcaseloadDesc(final String caseloadId) {
		final String sql = getQuery("OSUREPOR_CF_CASELOAD_DESC");
		String returnVal = null;
		try {
			returnVal = namedParameterJdbcTemplate.queryForObject(sql, createParams("CASELOAD", caseloadId),
					String.class);
		} catch (Exception e) {
			log.error("Exception: getfcaseloadDesc", e);
			return returnVal;
		}
		return returnVal;
	}

	@Override
	public List<VAgencyAddresses> getAddreesDetails(final VAgencyAddresses paramBean) {
		final String sql = getQuery("OSUREPOR_GET_ADDRESS_ONE_TWO_DETAILS");
		final RowMapper<VAgencyAddresses> VAgyAddRowMapper = Row2BeanRowMapper.makeMapping(sql, VAgencyAddresses.class,
				vAgyAddrMapping);
		final ArrayList<VAgencyAddresses> returnList = (ArrayList<VAgencyAddresses>) namedParameterJdbcTemplate.query(
				sql, createParams("agyLocId", paramBean.getAgyLocId(), "addrssType", paramBean.getAddressType()),
				VAgyAddRowMapper);
		return returnList;
	}

	@Override
	public String getClientName(Long offenderBookId) {
		final String sql = getQuery("OSUREPOR_GET_CLIENT_NAME_FOR_RECEIPT_REPORT");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderBookId", offenderBookId),
					String.class);
		} catch (Exception e) {
			log.error("Exception: getClientName", e);
			return null;
		}
	}

	@Override
	public Date getLongestSupervisionExpireDate(Long offenderBookId) {
		final String sql = getQuery("OSUREPOR_GET_LONGEST_SUPV_EXPIRE_DATE");
		Date returnVal = null;
		try {
			returnVal = namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderBookId", offenderBookId),
					Date.class);
		} catch (Exception e) {
			log.error("Exception: getLongestSupervisionExpireDate", e);
			return returnVal;
		}
		return returnVal;
	}

	@Override
	public List<CasePlans> getCasePlanDetailsToGetPoName(Long offenderBookId) {
		final String sql = getQuery("OCDIPLAN_CASPLN_FIND_CASE_PLANS");
		final RowMapper<CasePlans> CasePlansRowMapper = Row2BeanRowMapper.makeMapping(sql, CasePlans.class,
				casePlansMapping);
		final ArrayList<CasePlans> returnList = (ArrayList<CasePlans>) namedParameterJdbcTemplate.query(sql,
				createParams("offenderBookId", offenderBookId), CasePlansRowMapper);
		return returnList;
	}

	public List<offBillingStatements> gettingAccountBalanceOffBillingStatements(final OffenderTransactions paramBean) {
		String sql = getQuery("OSUREPOR_GETTING_ACCOUNT_BALANCE_OFF_BILLING_STATEMENTS");
		final RowMapper<offBillingStatements> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				offBillingStatements.class, offFeeMapping);
		final StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource inParameterSource = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (paramBean.getRootOffenderId() != null) {
			sqlQuery.append(" AND ROOT_OFFENDER_ID = :OFFENDER_ID");
			inParameterSource.addValue("OFFENDER_ID", paramBean.getRootOffenderId());
		}
		if ("Active".equals(paramBean.getInOutStatus())) {
			sqlQuery.append(
					" and root_offender_id in (SELECT  V_HEADER_BLOCK.ROOT_OFFENDER_ID FROM V_HEADER_BLOCK V_HEADER_BLOCK WHERE "
							+ " (v_header_block.offender_book_id =( SELECT MAX(vhb2.offender_book_id) FROM v_header_block vhb2 WHERE vhb2.root_offender_id = v_header_block.root_offender_id "
							+ "  AND ( vhb2.community_active_flag = 'Y' OR (NOT EXISTS ( SELECT 'X' FROM v_header_block vhb3 WHERE vhb3.community_active_flag = 'Y' AND vhb3.root_offender_id = vhb2.root_offender_id))) "
							+ "	 AND ((vhb2.intake_agy_loc_id <> 'CLOSE' AND EXISTS ( SELECT 'X' FROM offender_booking_agy_locs locs,caseload_agency_locations cal1 "
							+ "	 WHERE cal1.caseload_id =:caseload_id AND cal1.agy_loc_id = locs.agy_loc_id AND locs.offender_book_id = vhb2.offender_book_id AND locs.removed_date IS  NULL AND  'COMM'  = 'COMM'))))))");
			inParameterSource.addValue("caseload_id", paramBean.getCaseloadId());
		}
		if ("Outcount".equals(paramBean.getInOutStatus())) {
			sqlQuery.append(
					" AND root_offender_id  IN (SELECT root_offender_id FROM V_HEADER_BLOCK V_HEADER_BLOCK WHERE "
							+ " ( v_header_block.offender_book_id =( SELECT MAX(vhb2.offender_book_id) FROM v_header_block vhb2 WHERE vhb2.root_offender_id = v_header_block.root_offender_id"
							+ "    AND ( vhb2.community_active_flag = 'Y' OR (NOT EXISTS ( SELECT 'X' FROM v_header_block vhb3 WHERE vhb3.community_active_flag = 'Y' AND vhb3.root_offender_id = vhb2.root_offender_id)))"
							+ "	 AND (vhb2.intake_agy_loc_id = 'CLOSE' )"
							+ "     AND  v_header_block.offender_book_id IN ( SELECT offender_book_id FROM OFFENDER_BOOKING_EVENTS WHERE offender_book_id= v_header_block.offender_book_id "
							+ "  AND EVENT_SEQ IN (SELECT MAX(EVENT_SEQ) FROM OFFENDER_BOOKING_EVENTS WHERE offender_book_id= v_header_block.offender_book_id) AND REASON_CODE IN ('CMPO','INPR','UNSU')))))");
			inParameterSource.addValue("caseload_id", paramBean.getCaseloadId());
		}
		if ("Both".equals(paramBean.getInOutStatus())) {
			sqlQuery.append("  AND root_offender_id IN (SELECT ROOT_OFFENDER_ID FROM V_HEADER_BLOCK V_HEADER_BLOCK WHERE "
					+ " (v_header_block.offender_book_id =( SELECT MAX(vhb2.offender_book_id) FROM v_header_block vhb2 WHERE vhb2.root_offender_id = v_header_block.root_offender_id "
					+ "  AND (vhb2.community_active_flag = 'Y' OR (NOT EXISTS ( SELECT 'X' FROM v_header_block vhb3 WHERE vhb3.community_active_flag = 'Y' AND vhb3.root_offender_id = vhb2.root_offender_id))) "
					+ "  AND ((vhb2.intake_agy_loc_id <> 'CLOSE' AND EXISTS ( SELECT 'X' FROM offender_booking_agy_locs locs,caseload_agency_locations cal1 "
					+ "  WHERE cal1.caseload_id =:caseload_id AND cal1.agy_loc_id = locs.agy_loc_id AND locs.offender_book_id = vhb2.offender_book_id AND locs.removed_date IS  NULL AND  'COMM'  = 'COMM')) "
					+ "  OR ((vhb2.intake_agy_loc_id = 'CLOSE') "
					+ "  AND  vhb2.offender_book_id IN ( SELECT offender_book_id FROM OFFENDER_BOOKING_EVENTS WHERE offender_book_id= vhb2.offender_book_id  "
					+ "  AND EVENT_SEQ IN (SELECT MAX(EVENT_SEQ) FROM OFFENDER_BOOKING_EVENTS WHERE offender_book_id= vhb2.offender_book_id) AND REASON_CODE IN ('CMPO','INPR','UNSU' )))))))");
			inParameterSource.addValue("caseload_id", paramBean.getCaseloadId());
		}
		if (paramBean.getPoCaseload() != null) {
			sqlQuery.append(" AND caseload_id = :POCASELOAD");
			inParameterSource.addValue("POCASELOAD", paramBean.getPoCaseload());
		}
		if (paramBean.getLowerLimit() != null) {
			sqlQuery.append(" AND ending_balance_amount >= :LOWERLIMIT");
			inParameterSource.addValue("LOWERLIMIT", paramBean.getLowerLimit());
		}
		if (paramBean.getUpperLimit() != null) {
			sqlQuery.append(" AND ending_balance_amount <= :UPPERLIMIT");
			inParameterSource.addValue("UPPERLIMIT", paramBean.getUpperLimit());
		}
		inParameterSource.addValue("START_DATETIME", paramBean.getBeginDate());
		inParameterSource.addValue("END_DATETIME", paramBean.getToDate());
		String preparedSql = null;
		preparedSql = sqlQuery.toString().trim();
		preparedSql = preparedSql + "  order by BILLING_CYCLE_END_DATE asc";
		try {

			return namedParameterJdbcTemplate.query(preparedSql, inParameterSource, columnRowMapper);
		} catch (Exception e) {
			return Collections.emptyList();
		}
	}

	public List<FeeAccountBalanceBean> gettingPaymentsAndCreditsForAccounts(final OffenderTransactions paramBean,final Integer StatementId) {
		 String sql = getQuery("OSUREPOR_GETTING_PAYMENT_AND_CREDITS_FOR_ACCOUNTS");
		final RowMapper<FeeAccountBalanceBean> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				FeeAccountBalanceBean.class, offFeeMapping);
		if(paramBean.getPoCaseload() != null) {
			sql = sql + " and ofp.CASELOAD_ID = '" + paramBean.getPoCaseload()+"'";
		} 
		sql = sql +"  ORDER BY OFBT.BILL_ID,ofbt.BILL_TXN_NO"; 
		try {
			MapSqlParameterSource param = createParams("OFFENDERBOOKID", paramBean.getOffenderBookId(),
					"BILLING_STATEMENT_ID",StatementId);
			return namedParameterJdbcTemplate.query(sql, param, columnRowMapper);
		} catch (Exception e) {
			return Collections.emptyList();
		}
	}

	public BigDecimal gettingPaymentSumAmount(final OffenderTransactions paramBean,Integer statementId) {
		final String sql = getQuery("OSUREPOR_GETTING_PAYMENT_AND_CREDITS_SUM_AMOUNT");
		BigDecimal paymenySumAmount = null;
		try {
			MapSqlParameterSource param = createParams("OFFENDERBOOKID", paramBean.getOffenderBookId(),
					"BILLING_STATEMENT_ID", statementId);
			paymenySumAmount = namedParameterJdbcTemplate.queryForObject(sql, param, BigDecimal.class);
		} catch (Exception e) {
			log.error("Exception: gettingPaymentSumAmount", e);
			return paymenySumAmount;
		}
		return paymenySumAmount;
	}

	public List<FeeAccountBalanceBean> gettingBilingForAccountSection(final OffenderTransactions paramBean,Integer statementId) {
		 String sql = getQuery("OSUREPOR_GETTING_BILLING_FOR_ACCOUNT_SECTION");
		final RowMapper<FeeAccountBalanceBean> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				FeeAccountBalanceBean.class, offFeeMapping);
		if(paramBean.getPoCaseload() != null) {
			sql = sql + " AND CASELOAD_ID = '" + paramBean.getPoCaseload()+"'";
		}
		sql = sql + " )feeCode";
		try {
			MapSqlParameterSource param = createParams("OFFENDER_BOOK_ID", paramBean.getOffenderBookId(),
					"BILLING_STATEMENT_ID",statementId);
			return namedParameterJdbcTemplate.query(sql, param, columnRowMapper);
		} catch (Exception e) {
			return Collections.emptyList();
		}
	}

	public List<offBillingStatements> gettingSummaryOfFeeAccount(final OffenderTransactions paramBean) {
		final String sql = getQuery("OSUREPOR_GETTING_SUMMARY_OF_FEE_ACCOUNT");
		final RowMapper<offBillingStatements> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				offBillingStatements.class, offFeeMapping);
		try {
			MapSqlParameterSource param = createParams("OFFENDERBOOKID", paramBean.getOffenderBookId());
			return namedParameterJdbcTemplate.query(sql, param, columnRowMapper);
		} catch (Exception e) {
			return Collections.emptyList();
		}
	}

	@Override
	public Date getBillingCycleEndDate(Long rootOffenderId) {
		final String sql = getQuery("OSUREPOR_GET_LONGEST_SUPV_EXPIRE_DATE");
		Date returnVal = null;
		try {
			returnVal = namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderBookId", rootOffenderId),
					Date.class);
		} catch (Exception e) {
			log.error("Exception: getBillingCycleEndDate", e);
			return returnVal;
		}
		return returnVal;
	}

	@Override
	public Date getFBookingDate(Long rootOffenderId) {
		final String sql = getQuery("OSUREPOR_GET_F_DATE_ONE");
		Date returnVal = null;
		try {
			returnVal = namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderBookId", rootOffenderId),
					Date.class);
		} catch (Exception e) {
			log.error("Exception: getFBookingDate", e);
			return returnVal;
		}
		return returnVal;
	}

	@Override
	public String getMsgValue(String moduleName) {
		final String sql = getQuery("OSUREPOR_GET_MSG_VALUE");
		String returnVal = null;
		try {
			returnVal = namedParameterJdbcTemplate.queryForObject(sql, createParams("MODULENAME",moduleName), String.class);
		} catch (Exception e) {
			log.error("Exception: getGlobalMsgValue", e);
			return returnVal;
		}
		return returnVal;
	}

	@Override
	public String getCountySpecificMsg(String messageKey) {
		final String sql = getQuery("OSUREPOR_GET_COUNTY_MESSAGE");
		String returnVal = null;
		try {
			returnVal = namedParameterJdbcTemplate.queryForObject(sql, createParams("MESSAGEKEY",messageKey), String.class);
		} catch (Exception e) {
			log.error("Exception: getCountySpecificMsg", e);
			return returnVal;
		}
		return returnVal;
	}
	@Override
	public List<ReferenceCodes> getFromBillingCycleDate() {
		final String sql = getQuery("OSUREPOR_GET_FROM_BILLINGCYCLE_LOVDATA");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				refCodeMapping);
		final List<ReferenceCodes> returnList = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		return returnList;
	}

	@Override
	public List<ReferenceCodes> getToBillingCycleDate() {
		final String sql = getQuery("OSUREPOR_GET_TO_BILLINGCYCLE_LOVDATA");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				refCodeMapping);
		final List<ReferenceCodes> returnList = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		return returnList;
	}
	@Override
	public VAddressUsages getBadAddress(final BigDecimal ownerId) {
		final String sql = getQuery("OSUREPOR_GET_BAD_ADDRESS_DATA");
		final RowMapper<VAddressUsages> VAgyAddRowMapper = Row2BeanRowMapper.makeMapping(sql, VAddressUsages.class,
				addressUsagesMapping);
		VAddressUsages returnObj = new VAddressUsages();
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("OWNER_ID", ownerId),
					VAgyAddRowMapper);
		} catch (EmptyResultDataAccessException e) {
			 returnObj= new VAddressUsages();
		}
		return returnObj;
	}
	@Override
	public List<CasePlans> getPoName(Long offenderBookId) {
		final String sql = getQuery("OSUREPOR_GET_PO_OFFICER_NAME");
		final RowMapper<CasePlans> CasePlansRowMapper = Row2BeanRowMapper.makeMapping(sql, CasePlans.class,
				casePlansMapping);
		final ArrayList<CasePlans> returnList = (ArrayList<CasePlans>) namedParameterJdbcTemplate.query(sql,
				createParams("offenderBookId", offenderBookId), CasePlansRowMapper);
		return returnList;
	}
	@Override
	public List<ReferenceCodes> getPoCasloadNames(final String caseloadId) {
		final String sql = getQuery("OSUREPOR_GET_PO_CASELODS");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				refCodeMapping);
		final List<ReferenceCodes> returnList = namedParameterJdbcTemplate.query(sql, createParams("CASELOAD_ID", caseloadId), columnRowMapper);
		return returnList;
	}
	
	
	
	
	
	@Override
	public Date getForPeriodEndingdate(String endDate) {
		final String sql = getQuery("OSUREPOR_GET_FOR_PERIOD_ENDINGDATE");
		Date returnVal = null;
		try {
			returnVal = namedParameterJdbcTemplate.queryForObject(sql, createParams("enddate",endDate), Date.class);
		} catch (EmptyResultDataAccessException e) {
			log.error("Exception: getForPeriodEndingdate", e);
			return returnVal;
		}
		return returnVal;
	}
}
