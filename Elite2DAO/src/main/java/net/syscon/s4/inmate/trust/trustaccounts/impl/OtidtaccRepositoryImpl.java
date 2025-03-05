package net.syscon.s4.inmate.trust.trustaccounts.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.im.beans.OffenderTrustAccounts;
import net.syscon.s4.inmate.beans.DeductionTypes;
import net.syscon.s4.inmate.beans.OffenderDeductions;
import net.syscon.s4.inmate.beans.OffenderSubAccounts;
import net.syscon.s4.inmate.beans.OffenderWorks;
import net.syscon.s4.inmate.trust.trustaccounts.OtidtaccRepository;
import oracle.jdbc.OracleTypes;

@Repository
public class OtidtaccRepositoryImpl extends RepositoryBase implements OtidtaccRepository {
	
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OtidtaccRepositoryImpl.class.getName());


	
	private final Map<String, FieldMapper> offenderDeductionsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("ADJUSTMENT_USER_ID",          new FieldMapper("adjustmentUserId"))
			.put("GROUP_ID",                    new FieldMapper("groupId"))
			.put("PROCESS_PRIORITY_NUMBER",     new FieldMapper("processPriorityNumber"))
			.put("PAY_DEDUCTION_FLAG",          new FieldMapper("payDeductionFlag"))
			.put("DEDUCTION_STATUS",            new FieldMapper("deductionStatus"))
			.put("COLLECT_SENT_DATE",           new FieldMapper("collectSentDate"))
			.put("MAX_RECURSIVE_AMOUNT",        new FieldMapper("maxRecursiveAmount"))
			.put("JS_STATUS",                   new FieldMapper("jsStatus"))
			.put("OFFENDER_ID",                 new FieldMapper("offenderId"))
			.put("FIFO_FLAG",                   new FieldMapper("fifoFlag"))
			.put("ADJUSTMENT_AMOUNT",           new FieldMapper("adjustmentAmount"))
			.put("DEDUCTION_PRIORITY",          new FieldMapper("deductionPriority"))
			.put("SEAL_FLAG",                   new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME",             new FieldMapper("createDatetime"))
			.put("EFFECTIVE_DATE",              new FieldMapper("effectiveDate"))
			.put("MODIFY_DATETIME",             new FieldMapper("modifyDatetime"))
			.put("PAYEE_PERSON_ID",             new FieldMapper("payeePersonId"))
			.put("ADJUSTMENT_TEXT",             new FieldMapper("adjustmentText"))
			.put("COLLECT_AGENCY_FLAG",         new FieldMapper("collectAgencyFlag"))
			.put("OFFENDER_DEDUCTION_ID",       new FieldMapper("offenderDeductionId"))
			.put("COMMENT_TEXT",                new FieldMapper("commentText"))
			.put("ADJUSTMENT_REASON_CODE",      new FieldMapper("adjustmentReasonCode"))
			.put("CREATE_USER_ID",              new FieldMapper("createUserId"))
			.put("OFFENDER_PAYMENT_PROFILE_ID", new FieldMapper("offenderPaymentProfileId"))
			.put("MAX_TOTAL_AMOUNT",            new FieldMapper("maxTotalAmount"))
			.put("MODIFY_USER_ID",              new FieldMapper("modifyUserId"))
			.put("INFORMATION_NUMBER",          new FieldMapper("informationNumber"))
			.put("ADJUSTMENT_TXN_ID",           new FieldMapper("adjustmentTxnId"))
			.put("CASE_ID",                     new FieldMapper("caseId"))
			.put("COLLECT_AGENCY_AMOUNT",       new FieldMapper("collectAgencyAmount"))
			.put("CREDIT_LIMIT",                new FieldMapper("creditLimit"))
			.put("DEDUCTION_AMOUNT",            new FieldMapper("deductionAmount"))
			.put("DEDUCTION_PERCENTAGE",        new FieldMapper("deductionPercentage"))
			.put("CASELOAD_ID",                 new FieldMapper("caseloadId"))
			.put("MAX_MONTHLY_AMOUNT",          new FieldMapper("maxMonthlyAmount"))
			.put("MODIFY_DATE",                 new FieldMapper("modifyDate"))
			.put("DEDUCTION_TYPE",              new FieldMapper("deductionType"))
			.put("PARENT_DEDUCTION_ID",         new FieldMapper("parentDeductionId"))
			.put("ROOT_OFFENDER_ID",            new FieldMapper("rootOffenderId"))
			.put("PAYEE_CORPORATE_ID",          new FieldMapper("payeeCorporateId"))
			.put("MAX_MONTHLY_AMOUN",           new FieldMapper("maxMonthlyAmoun"))
			.put("TOTAL_CREDIT",                new FieldMapper("maxTotalAmount"))
			.put("PAID_CREDIT",                 new FieldMapper("deductionAmount"))
			.put("WRITE_OFF_CREDIT",            new FieldMapper("adjustmentAmount"))
			.put("TOTAL_FIXED",                 new FieldMapper("maxMonthlyAmount"))
			.put("PAID_FIXED",                  new FieldMapper("maxRecursiveAmount"))
			.build();
	
	
	private final Map<String, FieldMapper> accountCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("TRUST_ACCOUNT_CODE",          new FieldMapper("trustAccountCode"))
			.put("DESCRIPTION",                 new FieldMapper("description"))
			.put("SUB_ACCOUNT_TYPE",            new FieldMapper("subAccountType"))
			.put("CODE",                       new FieldMapper("code"))
			.build();
	
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("HOA",                         new FieldMapper("hoa"))
			.put("TXN_TYPE",                    new FieldMapper("txnType"))
			.put("TXN_ENTRY_AMOUNT",            new FieldMapper("txnEntryAmount"))
			.put("HOR",                         new FieldMapper("hor"))
			.put("DR",                          new FieldMapper("dr"))
			.put("CR",                          new FieldMapper("cr"))
			.build();
	
	private final Map<String, FieldMapper> offenderTrustAccountsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CREATE_USER_ID",              new FieldMapper("createUserId"))
			.put("MODIFY_USER_ID",              new FieldMapper("modifyUserId"))
			.put("LIST_SEQ",                    new FieldMapper("listSeq"))
			.put("HOLD_BALANCE",                new FieldMapper("holdBalance"))
			.put("CURRENT_BALANCE",             new FieldMapper("currentBalance"))
			.put("OFFENDER_ID",                 new FieldMapper("offenderId"))
			.put("CASELOAD_ID",                 new FieldMapper("caseloadId"))
			.put("MODIFY_DATE",                 new FieldMapper("modifyDate"))
			.put("SEAL_FLAG",                   new FieldMapper("sealFlag"))
			.put("ACCOUNT_CLOSED_FLAG",         new FieldMapper("accountClosedFlag"))
			.put("CREATE_DATETIME",             new FieldMapper("createDatetime"))
			.put("MODIFY_DATETIME",             new FieldMapper("modifyDatetime"))
			.put("NOTIFY_DATE",                 new FieldMapper("notifyDate"))
			.build();
	
	
	private final Map<String, FieldMapper> offenderSubAccountsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CREATE_USER_ID",              new FieldMapper("createUserId"))
			.put("TRUST_ACCOUNT_CODE",          new FieldMapper("trustAccountCode"))
			.put("MODIFY_USER_ID",              new FieldMapper("modifyUserId"))
			.put("LIST_SEQ",                    new FieldMapper("listSeq"))
			.put("HOLD_BALANCE",                new FieldMapper("holdBalance"))
			.put("LAST_TXN_ID",                 new FieldMapper("lastTxnId"))
			.put("IND_DAYS",                    new FieldMapper("indDays"))
			.put("OFFENDER_ID",                 new FieldMapper("offenderId"))
			.put("BALANCE",                     new FieldMapper("balance"))
			.put("CASELOAD_ID",                 new FieldMapper("caseloadId"))
			.put("MODIFY_DATE",                 new FieldMapper("modifyDate"))
			.put("IND_DATE",                    new FieldMapper("indDate"))
			.put("MINIMUM_BALANCE",             new FieldMapper("minimumBalance"))
			.put("SEAL_FLAG",                   new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME",             new FieldMapper("createDatetime"))
			.put("MODIFY_DATETIME",             new FieldMapper("modifyDatetime"))
			.build();
	
	private final Map<String, FieldMapper> offenderWorksMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_ID",                 new FieldMapper("offenderId"))
			.put("TXN_ID",                      new FieldMapper("txnId"))
			.put("TXN_ENTRY_SEQ",               new FieldMapper("txnEntrySeq"))
			.put("PAYROLL_BATCH_ID",            new FieldMapper(" payrollBatchId "))
			.build();
	
	private final Map<String, FieldMapper> deductionTypesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("LIST_SEQ",                    new FieldMapper("listSeq"))
			.put("DEDUCTION_TYPE",              new FieldMapper("deductionType"))
			.put("DEDUCTION_DESC",              new FieldMapper("deductionDesc"))
			.put("DEDUCTION_CATEGORY",          new FieldMapper("deductionCategory"))
			.build();
	
	private final Map<String, FieldMapper> systemProfilesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PROFILE_CODE",                new FieldMapper("profileCode"))
			.put("PROFILE_TYPE",                new FieldMapper("profileType"))
			.put("CREATE_USER_ID",              new FieldMapper("createUserId"))
			.put("SEAL_FLAG",                   new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME",             new FieldMapper("createDatetime"))
			.put("MODIFY_USER_ID",              new FieldMapper("modifyUserId"))
			.put("OLD_TABLE_NAME",              new FieldMapper("oldTableName"))
			.put("PROFILE_VALUE",               new FieldMapper("profileValue"))
			.put("MODIFY_DATETIME",             new FieldMapper("modifyDatetime"))
			.put("PROFILE_VALUE_2",             new FieldMapper("profileValue2"))
			.put("DESCRIPTION",                 new FieldMapper("description"))
			.build();
	
	
	private final Map<String, FieldMapper> offenderTransactionsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("TXN_REFERENCE_NUMBER",        new FieldMapper("txnReferenceNumber"))
			.put("CORPORATE_NAME",              new FieldMapper("corporateName"))
			.put("TXN_ADJUSTED_FLAG",           new FieldMapper("txnAdjustedFlag"))
			.put("ADJUST_TXN_ENTRY_ID",         new FieldMapper("adjustTxnEntryId"))
			.put("PAYEE_NAME_TEXT",             new FieldMapper("payeeNameText"))
			.put("TXN_ENTRY_SEQ",               new FieldMapper("txnEntrySeq"))
			.put("RECEIPT_PRINTED_FLAG",        new FieldMapper("receiptPrintedFlag"))
			.put("GROSS_NET_FLAG",              new FieldMapper("grossNetFlag"))
			.put("SEAL_FLAG",                   new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME",             new FieldMapper("createDatetime"))
			.put("TXN_TYPE",                    new FieldMapper("txnType"))
			.put("GROSS_AMOUNT",                new FieldMapper("grossAmount"))
			.put("RECEIPT_PENDING_PRINT_FLAG",  new FieldMapper("receiptPendingPrintFlag"))
			.put("PAYEE_PERSON_ID",             new FieldMapper("payeePersonId"))
			.put("TRANSFER_CASELOAD_ID",        new FieldMapper("transferCaseloadId"))
			.put("OFFENDER_BOOK_ID",            new FieldMapper("offenderBookId"))
			.put("CREATE_USER_ID",              new FieldMapper("createUserId"))
			.put("TXN_ENTRY_DATE",              new FieldMapper("txnEntryDate"))
			.put("ADJUST_OFFENDER_ID",          new FieldMapper("adjustOffenderId"))
			.put("RECEIPT_NUMBER",              new FieldMapper("receiptNumber"))
			.put("ADJUST_TXN_ID",               new FieldMapper("adjustTxnId"))
			.put("CLOSING_CHEQUE_NUMBER",       new FieldMapper("closingChequeNumber"))
			.put("CASELOAD_ID",                 new FieldMapper("caseloadId"))
			.put("DEDUCTION_FLAG",              new FieldMapper("deductionFlag"))
			.put("REMITTER_NAME",               new FieldMapper("remitterName"))
			.put("TXN_ENTRY_DESC",              new FieldMapper("txnEntryDesc"))
			.put("INFO_NUMBER",                 new FieldMapper("infoNumber"))
			.put("APPLY_SPENDING_LIMIT_AMOUNT", new FieldMapper("applySpendingLimitAmount"))
			.put("OFFENDER_ID",                 new FieldMapper("offenderId"))
			.put("HOLD_CLEAR_FLAG",             new FieldMapper("holdClearFlag"))
			.put("FIRST_NAM",                   new FieldMapper("firstNam"))
			.put("HOLD_NUMBER",                 new FieldMapper("holdNumber"))
			.put("PAYEE_CORPORATE_ID",          new FieldMapper("payeeCorporateId"))
			.put("MODIFY_DATETIME",             new FieldMapper("modifyDatetime"))
			.put("ORG_TXN_TYPE",                new FieldMapper("orgTxnType"))
			.put("REMITTER_ID",                 new FieldMapper("remitterId")) 
			.put("PAYEE_CODE",                  new FieldMapper("payeeCode"))
			.put("TXN_ID",                      new FieldMapper("txnId"))
			.put("MODIFY_USER_ID",              new FieldMapper("modifyUserId"))
			.put("TXN_ENTRY_AMOUNT",            new FieldMapper("txnEntryAmount"))
			.put("HOLD_UNTIL_DATE",             new FieldMapper("holdUntilDate"))
			.put("SLIP_PRINTED_FLAG",           new FieldMapper("slipPrintedFlag"))
			.put("ADJUST_ACCOUNT_CODE",         new FieldMapper("adjustAccountCode"))
			.put("LAST_NAM",                    new FieldMapper("lastNam"))
			.put("MODIFY_DATE",                 new FieldMapper("modifyDate"))
			.put("SUB_ACCOUNT_TYPE",            new FieldMapper("subAccountType"))
			.put("BOOKING_NO",                  new FieldMapper("bookingNo"))
			.put("PRE_WITHHOLD_AMOUNT",         new FieldMapper("preWithholdAmount"))
			.put("TXN_POSTING_TYPE",            new FieldMapper("txnPostingType"))
			.build();
	
	
	/**
	 * Creates new OtidtaccRepositoryImpl class Object
	 */
	public OtidtaccRepositoryImpl() {
		// OtidtaccRepositoryImpl
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            OffenderTrustAccounts
	 *
	 * @return List<OffenderTrustAccounts>
	 *
	 */
	public List<OffenderTrustAccounts> offTaExecuteQuery(final OffenderTrustAccounts objSearchDao) {
		final String sql = getQuery("OTIDTACC_OFFTA_FIND_OFFENDER_TRUST_ACCOUNTS");
		final RowMapper<OffenderTrustAccounts> OffenderTrustAccountsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderTrustAccounts.class, offenderTrustAccountsMapping);
		final ArrayList<OffenderTrustAccounts> returnList = (ArrayList<OffenderTrustAccounts>) namedParameterJdbcTemplate
				.query(sql, createParams(), OffenderTrustAccountsRowMapper);
		return returnList;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            OffenderSubAccounts
	 *
	 * @return List<OffenderSubAccounts>
	 *
	 */
	public List<OffenderSubAccounts> offSubaExecuteQuery(final OffenderSubAccounts objSearchDao) {
		final String sql = getQuery("OTIDTACC_OFFSUBA_FIND_OFFENDER_SUB_ACCOUNTS");
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource params = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" where ");
			if (objSearchDao.getOffenderId() != null) {
				sqlQuery.append("OFFENDER_ID = :OFFENDER_ID" + " and ");
				params.addValue("OFFENDER_ID", objSearchDao.getOffenderId());
			}
			if (objSearchDao.getCaseloadId() != null) {
				sqlQuery.append("CASELOAD_ID = :CASELOAD_ID" + " and ");
				params.addValue("CASELOAD_ID", objSearchDao.getCaseloadId());
			}
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("where")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("and")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		preparedSql = preparedSql.concat(" ORDER BY  TRUST_ACCOUNT_CODE");
		final RowMapper<OffenderSubAccounts> OffenderSubAccountsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderSubAccounts.class, offenderSubAccountsMapping);
		final ArrayList<OffenderSubAccounts> returnList = (ArrayList<OffenderSubAccounts>) namedParameterJdbcTemplate
				.query(preparedSql, params, OffenderSubAccountsRowMapper);
		return returnList;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            OffenderDeductions
	 *
	 * @return List<OffenderDeductions>
	 *
	 */
	public List<OffenderDeductions> offDedExecuteQuery(final OffenderDeductions objSearchDao) {
		final String sql = getQuery("OTIDTACC_OFFDED_FIND_OFFENDER_DEDUCTIONS");
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		final StringBuffer defOrderBy = new StringBuffer();
		final MapSqlParameterSource params = new MapSqlParameterSource();
		sqlQuery.append(sql);
		final StringBuffer closeBracket = new StringBuffer();
		final StringBuffer finalOrderBy = new StringBuffer();
		String dedCatTo = "ZZZZZZZZZ";
		Integer ordno = 0;
		 List<OffenderDeductions> returnList=new ArrayList<>();
		try {
		if (objSearchDao != null) {
			sqlQuery.append(" where ");
			if (objSearchDao.getOffenderId() != null) {
				sqlQuery.append("OFFENDER_ID = :OFFENDER_ID" + " and ");
				params.addValue("OFFENDER_ID", objSearchDao.getOffenderId());
			}
			sqlQuery.append(
					" DEDUCTION_TYPE IN (SELECT DEDUCTION_TYPE FROM DEDUCTION_TYPES WHERE DEDUCTION_CATEGORY IN ('FXOB','CROB') AND caseload_code in ('BOTH',:CASELOADTYPE))"
							+ " and ");
			params.addValue("CASELOADTYPE", objSearchDao.getDeductionType());
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("where")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("and")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		final List<DeductionTypes> orderByData = getOrderByClauseOfOffenderDeductions(objSearchDao);
		for (final DeductionTypes objOne : orderByData) {
			if (!objOne.getDeductionCategory().equals(dedCatTo)) {
				ordno = ordno + 1;
				dedCatTo = objOne.getDeductionCategory();
			}
			defOrderBy.append("case deduction_type when " + "'"+ objOne.getDeductionType() + "'" + " then TO_CHAR (" + ordno +") else ");
			closeBracket.append(" end ");
			
		}
		defOrderBy.toString();
		finalOrderBy.append(" order by " + defOrderBy + "'9999'::text" + closeBracket + " ," +
		"ABS(coalesce(MAX_TOTAL_AMOUNT,coalesce(MAX_MONTHLY_AMOUNT, 0)) - DEDUCTION_AMOUNT) DESC");
		preparedSql = preparedSql.concat(finalOrderBy.toString());
		final RowMapper<OffenderDeductions> offenderDeductionsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderDeductions.class, offenderDeductionsMapping);
		 returnList =  namedParameterJdbcTemplate
				.query(preparedSql, params, offenderDeductionsRowMapper);
		}catch (Exception e) {
			logger.error(this.getClass().getName()+" Error In "+e);
		}
		return returnList;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            OffenderTransactions
	 *
	 * @return List<OffenderTransactions>
	 *
	 */
	public List<OffenderTransactions> offTxnExecuteQuery(final OffenderTransactions objSearchDao) {
		final String sql = getQuery("OTIDTACC_OFFTXN_FIND_OFFENDER_TRANSACTIONS");
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource params = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" where ");
			if (objSearchDao.getOffenderId() != null) {
				sqlQuery.append("OFFENDER_ID = :OFFENDER_ID" + " and ");
				params.addValue("OFFENDER_ID", objSearchDao.getOffenderId());
			}
			if (objSearchDao.getCaseloadId() != null) {
				sqlQuery.append("CASELOAD_ID = :CASELOAD_ID" + " and ");
				params.addValue("CASELOAD_ID", objSearchDao.getCaseloadId());
			}
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("where")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("and")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		preparedSql = preparedSql.concat("  ORDER BY TXN_ENTRY_DATE DESC,TXN_ID DESC, TXN_ENTRY_SEQ DESC");
		final RowMapper<OffenderTransactions> OffenderTransactionsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderTransactions.class, offenderTransactionsMapping);
		final ArrayList<OffenderTransactions> returnList = (ArrayList<OffenderTransactions>) namedParameterJdbcTemplate
				.query(preparedSql, params, OffenderTransactionsRowMapper);
		return returnList;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            SystemProfiles
	 *
	 * @return List<SystemProfiles>
	 *
	 */
	public List<SystemProfiles> sysPflExecuteQuery(final SystemProfiles objSearchDao) {
		final String sql = getQuery("OTIDTACC_SYSPFL_FIND_SYSTEM_PROFILES");
		final RowMapper<SystemProfiles> SystemProfilesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				SystemProfiles.class, systemProfilesMapping);
		final ArrayList<SystemProfiles> returnList = (ArrayList<SystemProfiles>) namedParameterJdbcTemplate.query(sql,
				createParams(), SystemProfilesRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstSystemProfiles
	 *            List<SystemProfiles>
	 *
	 * @return List<Integer>
	 *
	 */
	public Integer syspflInsertSystemProfiles(final List<SystemProfiles> lstSystemProfiles) {
		final String sql = getQuery("OTIDTACC_SYSPFL_INSERT_SYSTEM_PROFILES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final SystemProfiles systemProfiles : lstSystemProfiles) {
			parameters.add(new BeanPropertySqlParameterSource(systemProfiles));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstSystemProfiles.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstSystemProfiles
	 *            List<SystemProfiles>
	 *
	 */
	public Integer sysPflDeleteSystemProfiles(final List<SystemProfiles> lstSystemProfiles) {
		final String sql = getQuery("OTIDTACC_SYSPFL_DELETE_SYSTEM_PROFILES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final SystemProfiles systemProfiles : lstSystemProfiles) {
			parameters.add(new BeanPropertySqlParameterSource(systemProfiles));
		}
		try {
			String tableName = "SYSTEM_PROFILES";
			String whereClause = null;
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method sysPflDeleteSystemProfiles", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstSystemProfiles.size() == returnArray.length) {
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
	public List<OffenderTransactions> calcAccountBalancesRecordGroup() {
		final String sql = getQuery("OTIDTACC_FIND_CALCACCOUNTBALANCES");
		final RowMapper<OffenderTransactions> mRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderTransactions.class, mMapping);
		return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offTxnWhenNewRecordInstance
	 *
	 * @param params
	 *
	 */
	public List<OffenderWorks> offTxnWhenNewRecordInstance(final OffenderWorks paramBean) {
		final String sql = getQuery("OTIDTACC_OFF_TXN_WHENNEWRECORDINSTANCE");
		final RowMapper<OffenderWorks> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderWorks.class,
				offenderWorksMapping);
		final ArrayList<OffenderWorks> returnList = (ArrayList<OffenderWorks>) namedParameterJdbcTemplate.query(sql,
				createParams(paramBean), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOffSubaOffSubaAc
	 *
	 * @param params
	 *
	 */
	public ReferenceCodes cgfkchkOffSubaOffSubaAc(final Long accountCode) {
		final String sql = getQuery("OTIDTACC_CGFKCHK_OFF_SUBA_OFF_SUBA_AC");
		ReferenceCodes returnObj = new ReferenceCodes();
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				accountCodesMapping);
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("TRUSTACCOUNTCODE", accountCode),
					columnRowMapper);
		} catch (EmptyResultDataAccessException e) {
			returnObj = new ReferenceCodes();
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOffDedOffDedDed
	 *
	 * @param params
	 *
	 */
	public DeductionTypes getTheDescriptionOfDeductionType(final DeductionTypes paramBean) {
		final String sql = getQuery("OTIDTACC_CGFKCHK_OFF_DED_OFF_DED_DED_T");
		final RowMapper<DeductionTypes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, DeductionTypes.class,
				deductionTypesMapping);
		DeductionTypes returnObj = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("DEDUCTIONTYPE", paramBean.getDeductionType()), columnRowMapper);
		return returnObj;
	}


	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * populateCreditObligation
	 *
	 * @param params
	 *
	 */
	public OffenderDeductions populateCreditObligation(final Long rootOffenderId) {
		final String sql = getQuery("OTIDTACC_POPULATE_CREDIT_OBLIGATION");
		OffenderDeductions returnList = new OffenderDeductions();
		final RowMapper<OffenderDeductions> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderDeductions.class, offenderDeductionsMapping);
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("ROOTOFFENDERID", rootOffenderId),
					columnRowMapper);
		} catch (EmptyResultDataAccessException e) {
			returnList = new OffenderDeductions();
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * populateFixedObligation
	 *
	 * @param params
	 *
	 */
	public OffenderDeductions populateFixedObligation(final Long rootOffenderId) {
		final String sql = getQuery("OTIDTACC_POPULATE_FIXED_OBLIGATION");
		OffenderDeductions returnList = new OffenderDeductions();
		final RowMapper<OffenderDeductions> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderDeductions.class, offenderDeductionsMapping);
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("ROOTOFFENDERID", rootOffenderId),
					columnRowMapper);
		} catch (EmptyResultDataAccessException e) {
			returnList = new OffenderDeductions();
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * checkFixMthAct
	 *
	 * @param params
	 *
	 */
	public OffenderDeductions checkFixMthAct(final OffenderDeductions paramBean) {
		final String sql = getQuery("OTIDTACC_CHECK_FIX_MTH_ACT");
		final RowMapper<OffenderDeductions> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderDeductions.class, offenderDeductionsMapping);
		OffenderDeductions returnObj = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("ROOTOFFENDERID", paramBean.getOffenderId(), "OFFENDERDEDUCTIONID",
						paramBean.getOffenderDeductionId(), "DEDUCTIONTYPE", paramBean.getDeductionType()),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * populatePayeeRemitterName
	 *
	 * @param params
	 *
	 */
	public OffenderTransactions populatePayeeRemitterName(final OffenderTransactions paramBean) {
		final String sql = getQuery("OTIDTACC_GET_PAYEE_REMITTER_NAME");
		OffenderTransactions returnObj = new OffenderTransactions();
		final RowMapper<OffenderTransactions> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderTransactions.class,
				offenderTransactionsMapping);
		try {
		returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("OFFENDERID",paramBean.getOffenderId(),"TXNID",paramBean.getTxnId(),"TXNENTRYSEQ",paramBean.getTxnEntrySeq()), columnRowMapper);
		} catch(EmptyResultDataAccessException e) {
			returnObj = new OffenderTransactions();
		}
		return returnObj;
	}

	/**
	 * Used to get agyLocId from DB
	 * 
	 * @param paramBean
	 *
	 */
	public String getAgyLocId(final OffenderSubAccounts paramBean) {
		final String sql = getQuery("OTIDTACC_GET_AGY_LOC_ID");
		String returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("CASELOADID", paramBean.getCaseloadId(), "AGYLOCID", paramBean.getDescription()),
					String.class);
		} catch (EmptyResultDataAccessException e) {
			returnObj = "OUT";
		}
		return returnObj;
	}

	/**
	 * Used to get agyLocId from DB
	 * 
	 * @param paramBean
	 *
	 */
	public Integer getTheDaysRemaining(final OffenderSubAccounts paramBean) {
		final String sql = getQuery("OTIDTACC_GET_DAYS_REMAINING");
		Integer returnObj = 0;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("CASELOADID", paramBean.getCaseloadId(), "ACCCODE", paramBean.getTrustAccountCode(),
							"OFFID", paramBean.getOffenderId(), "AGYLOCID", paramBean.getDescription()),
					Integer.class);
		} catch (EmptyResultDataAccessException e) {
			returnObj = 0;
		}
		return returnObj;
	}

	/**
	 * This method will get the default status code
	 * 
	 * @return String
	 */
	public String indigentFlagFormula(final OffenderSubAccounts bean) {
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[4];
		String value = null;
		sqlParameters = new SqlParameter[] { new SqlParameter("P_OFF_ID", OracleTypes.NUMBER),
				new SqlParameter("P_CSLD_ID", OracleTypes.VARCHAR),
				new SqlParameter("P_AGYLOC_ID", OracleTypes.VARCHAR) };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("INDIGENT").withFunctionName("IND_FLAGFORMULA").declareParameters(sqlParameters);
		inParamMap.put("P_OFF_ID", bean.getOffenderId());
		inParamMap.put("P_CSLD_ID", bean.getSealFlag());
		inParamMap.put("P_AGYLOC_ID", bean.getCaseloadId());
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			value = simpleJDBCCall.executeFunction(String.class, inParameter);

		} catch (Exception e) {
			logger.error("In indigentFlagFormula method : ", e);
		}
		return value;
	}

	/**
	 * Used to get agyLocId from DB
	 * 
	 * @param paramBean
	 *
	 */
	public String getNbtAccountClosedFlag(final OffenderSubAccounts paramBean) {
		final String sql = getQuery("OTIDTACC_GET_NBT_ACCOUNT_CLOSED_FLAG");
		List<String> returnObj = null;
		String returnValue = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForList(sql,
					createParams("OFFID", paramBean.getOffenderId(), "CSLD_ID", paramBean.getSealFlag()), String.class);
		} catch (EmptyResultDataAccessException e) {
			returnValue = "N";
		}
		if (returnObj.size() > 0) {
			returnValue = "Y";
		} else {
			returnValue = "N";
		}
		return returnValue;
	}

	/**
	 * Used to get agyLocId from DB
	 * 
	 * @param paramBean
	 *
	 */
	public Integer calculateTheRemainDays(final OffenderSubAccounts paramBean) {
		final String sql = getQuery("OTIDTACC_CALCULATE_DAYS_REMAIN");
		Integer returnObj = 0;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("INDDAYS",
					paramBean.getDaysRemain(), "INDDATE", new java.sql.Date(paramBean.getIndDate().getTime())),
					Integer.class);
		} catch (EmptyResultDataAccessException e) {
			returnObj = 0;
		}
		return returnObj;
	}
	public OffenderTransactions getChequeAndClearData(final Integer txnId) {
		final String sql = getQuery("OTIDTACC_GET_CHEQUE_CLEAR_DATA");
		OffenderTransactions returnList = new OffenderTransactions();
		final RowMapper<OffenderTransactions> OffenderTransactionsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderTransactions.class, offenderTransactionsMapping);
		try{
		returnList = namedParameterJdbcTemplate
				.queryForObject(sql, createParams("TXNID",txnId), OffenderTransactionsRowMapper);
		} catch(EmptyResultDataAccessException e) {
			returnList = new OffenderTransactions();
		}
		return returnList;
		
	}
	public Integer getThePayRollId(final OffenderTransactions paramBean) {
		final String sql = getQuery("OTIDTACC_GET_PAYROLL_ID");
		Integer returnObj = 0;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("TXN_ID",
					paramBean.getTxnId(), "TXN_ENTRY_SEQ",paramBean.getTxnEntrySeq(), "OFFENDER_ID",paramBean.getOffenderId()),
					Integer.class);
		} catch (EmptyResultDataAccessException e) {
			returnObj = 0;
		}
		return returnObj;
	}
	public String getTheBookingNoOfOffenderBookId(final Long offenderBookId) {
		final String sql = getQuery("OTIDTACC_GET_BOOKING_NO");
		String returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("OFFENDER_BOOK_ID",offenderBookId),
					String.class);
		} catch (EmptyResultDataAccessException e) {
			returnObj = null;
		}
		return returnObj;
	}
	public String getTheRemitterName(final Integer remitterId) {
		final String sql = getQuery("OTIDTACC_GET_REMITTER_NAME");
		String returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("REMITTER_ID",remitterId),
					String.class);
		} catch (EmptyResultDataAccessException e) {
			returnObj = null;
		}
		return returnObj;
	}
	public String getPersonCursor(final Integer personId) {
		final String sql = getQuery("OTIDTACC_GET_PERSON_CURSOR");
		String returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("P_PERSON_ID",personId),
					String.class);
		} catch (EmptyResultDataAccessException e) {
			returnObj = null;
		}
		return returnObj;
	}
	public String getCorporateCursor(final Integer corporateId) {
		final String sql = getQuery("OTIDTACC_GET_CORPORATE_CURSOR");
		String returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("P_CORPORATE_ID",corporateId),
					String.class);
		} catch (EmptyResultDataAccessException e) {
			returnObj = null;
		}
		return returnObj;
	}
	public BigDecimal getTheCurrentBalance(final Long offenderId) {
		final String sql = getQuery("OTIDTACC_GET_CUREENT_BALANCE");
		BigDecimal returnObj = new BigDecimal(0);
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("OFFENDER_ID",offenderId),
					BigDecimal.class);
		} catch (EmptyResultDataAccessException e) {
			returnObj = new BigDecimal(0);
		}
		return returnObj;
	}
	public String getThedeductionCategory(final String deductionType) {
		final String sql = getQuery("OTIDTACC_CHK_DED_CAT");
		String returnVal = null;
		try {
			returnVal = namedParameterJdbcTemplate.queryForObject(sql, createParams("DEDUCTIONTYPE",deductionType),
					String.class);
		} catch (EmptyResultDataAccessException e) {
			returnVal = null;
		}
		return returnVal;
	}
	public BigDecimal getTheDeductionAmount(final Long offDedId) {
		final String sql = getQuery("OTIDTACC_GET_DEDUCTION_AMOUNT");
		BigDecimal returnObj = new BigDecimal(0);
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("DEDID",offDedId),
					BigDecimal.class);
		} catch (EmptyResultDataAccessException e) {
			returnObj = new BigDecimal(0);
		}
		return returnObj;
	}
	public List<DeductionTypes> getOrderByClauseOfOffenderDeductions(final OffenderDeductions objSearchDao) {
		final String sql = getQuery("OTIDTACC_CASE_DED_PFL");
		List<DeductionTypes> returnObj = new ArrayList<DeductionTypes>();
		final RowMapper<DeductionTypes> dedTypesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				DeductionTypes.class, deductionTypesMapping);
			returnObj = namedParameterJdbcTemplate.query(sql, createParams("caseLoad",objSearchDao.getCaseloadId(),"offenderId",objSearchDao.getOffenderId()),
					dedTypesRowMapper);
		return returnObj;
	}
	
	/**
	 * Used to get agyLocId from DB
	 * 
	 * @param paramBean
	 *
	 */
	public String getaccountClosedFlag(final OffenderSubAccounts paramBean) {
		final String sql = getQuery("OTIDTACC_GET_ACCOUNT_CLOSED_FLAG");
		String returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("offenderId", paramBean.getOffenderId(), "caseloadId", paramBean.getSealFlag()), String.class);
		} catch (EmptyResultDataAccessException e) {
			returnObj = "N";
		}
		return returnObj;
	}
	public Double getHoldBalance(final String offenderId,final String caseloadId){
		final String sql = getQuery("OTIDTACC_FIND_CALCACCOUNTBALANCES");
		Double returnObj = 0.0d;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("offenderId", offenderId, "caseloadId", caseloadId), Double.class);
		} catch (EmptyResultDataAccessException e) {
			logger.error("getHoldBalance",e);
		}
		return returnObj;
	}
	public Double getTheCurrentBalanceWithCaseLoad(final Long offenderId, final String caseLoadId) {
		final String sql = getQuery("OTIDTACC_GET_CUREENT_BALANCE_WITH_CASELOAD");
		Double returnObj = 0.0d;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("OFFENDER_ID",offenderId,"CASELOAD_ID",caseLoadId),
					Double.class);
		} catch (EmptyResultDataAccessException e) {
			returnObj = 0.0;
		}
		return returnObj;
	}
}
