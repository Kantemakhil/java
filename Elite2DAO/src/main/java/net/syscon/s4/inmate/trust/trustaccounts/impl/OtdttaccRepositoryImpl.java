package net.syscon.s4.inmate.trust.trustaccounts.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlInOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.Caseloads;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.TransactionTypes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.Corporates;
import net.syscon.s4.im.beans.OffenderSubAcShadows;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.im.beans.VTrustHeader;
import net.syscon.s4.inmate.beans.AccountPeriods;
import net.syscon.s4.inmate.beans.CaseloadTransactionTypes;
import net.syscon.s4.inmate.beans.OffenderSubAccounts;
import net.syscon.s4.inmate.trust.trustaccounts.OtdttaccRepository;
import oracle.jdbc.OracleTypes;

/**
 * Class OtdttaccRepositoryImpl
 */
@Repository
public class OtdttaccRepositoryImpl extends RepositoryBase implements OtdttaccRepository {

	private static Logger logger = LogManager.getLogger(OtdttaccRepositoryImpl.class.getName());
	
	private final Map<String, FieldMapper> transactionTypesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("TXN_TYPE", new FieldMapper("txnType")).build();
	private final Map<String, FieldMapper> caseloadTransactionTypesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CASELOAD_ID", new FieldMapper("caseloadId"))
			.put("MODIFY_DATE", new FieldMapper("modifyDate"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("TXN_TYPE", new FieldMapper("txnType"))
			.put("LIST_SEQ", new FieldMapper("listSeq"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.build();
	private final Map<String, FieldMapper> caseloadsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("CASELOAD_ID", new FieldMapper("caseloadId"))
			.build();
	private final Map<String, FieldMapper> accountPeriodsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("END_DATE", new FieldMapper("endDate"))
			.put("MODIFY_DATE", new FieldMapper("modifyDate"))
			.put("START_DATE", new FieldMapper("startDate"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("PARENT_ACCOUNT_PERIOD_ID", new FieldMapper("parentAccountPeriodId"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("LIST_SEQ", new FieldMapper("listSeq"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("ACCOUNT_PERIOD_ID", new FieldMapper("accountPeriodId"))
			.put("ACCOUNT_PERIOD_TYPE", new FieldMapper("accountPeriodType"))
			.build();
	private final Map<String, FieldMapper> systemProfilesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PROFILE_CODE", new FieldMapper("profileCode"))
			.put("PROFILE_TYPE", new FieldMapper("profileType"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("OLD_TABLE_NAME", new FieldMapper("oldTableName"))
			.put("PROFILE_VALUE", new FieldMapper("profileValue"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("PROFILE_VALUE_2", new FieldMapper("profileValue2"))
			.put("DESCRIPTION", new FieldMapper("description"))
			.build();
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("ROOT_OFFENDER_ID", new FieldMapper("rootOffenderId"))
			.put("CASELOAD_ID", new FieldMapper("caseloadId"))
			.put("LAST_NAME", new FieldMapper("lastName"))
			.put("TXN_TYPE", new FieldMapper("txnType"))
			.build();
	private final Map<String, FieldMapper> offenderTransactionsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("TXN_REFERENCE_NUMBER", 	new FieldMapper("txnReferenceNumber"))
			.put("INFO_NUMBER", 			new FieldMapper("infoNumber"))
			.put("APPLY_SPENDING_LIMIT_AMOUNT", new FieldMapper("applySpendingLimitAmount"))
			.put("TXN_ADJUSTED_FLAG", 		new FieldMapper("txnAdjustedFlag"))
			.put("ADJUST_TXN_ENTRY_ID", 	new FieldMapper("adjustTxnEntryId"))
			.put("PAYEE_NAME_TEXT", 		new FieldMapper("payeeNameText"))
			.put("RECEIPT_PRINTED_FLAG", 	new FieldMapper("receiptPrintedFlag"))
			.put("OFFENDER_ID", 			new FieldMapper("offenderId"))
			.put("HOLD_CLEAR_FLAG", 		new FieldMapper("holdClearFlag"))
			.put("GROSS_NET_FLAG", 			new FieldMapper("grossNetFlag"))
			.put("SEAL_FLAG", 				new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", 		new FieldMapper("createDatetime"))
			.put("HOLD_NUMBER", 			new FieldMapper("holdNumber"))
			.put("TXN_TYPE", 				new FieldMapper("txnType"))
			.put("GROSS_AMOUNT", 			new FieldMapper("grossAmount"))
			.put("RECEIPT_PENDING_PRINT_FLAG", new FieldMapper("receiptPendingPrintFlag"))
			.put("MODIFY_DATETIME", 		new FieldMapper("modifyDatetime"))
			.put("PAYEE_PERSON_ID", 		new FieldMapper("payeePersonId"))
			.put("ORG_TXN_TYPE", 			new FieldMapper("orgTxnType"))
			.put("TRANSFER_CASELOAD_ID", 	new FieldMapper("transferCaseloadId"))
			.put("TXN_ID", 					new FieldMapper("txnId"))
			.put("REMITTER_ID", 			new FieldMapper("remitterId"))
			.put("CREATE_USER_ID", 			new FieldMapper("createUserId"))
			.put("TXN_ENTRY_DATE", 			new FieldMapper("txnEntryDate"))
			.put("PAYEE_CODE", 				new FieldMapper("payeeCode"))
			.put("MODIFY_USER_ID", 			new FieldMapper("modifyUserId"))
			.put("TXN_ENTRY_AMOUNT", 		new FieldMapper("txnEntryAmount"))
			.put("ADJUST_OFFENDER_ID", 		new FieldMapper("adjustOffenderId"))
			.put("RECEIPT_NUMBER", 			new FieldMapper("receiptNumber"))
			.put("ADJUST_TXN_ID", 			new FieldMapper("adjustTxnId"))
			.put("CLOSING_CHEQUE_NUMBER", 	new FieldMapper("closingChequeNumber"))
			.put("HOLD_UNTIL_DATE", 		new FieldMapper("holdUntilDate"))
			.put("SLIP_PRINTED_FLAG", 		new FieldMapper("slipPrintedFlag"))
			.put("ADJUST_ACCOUNT_CODE", 	new FieldMapper("adjustAccountCode"))
			.put("CASELOAD_ID", 			new FieldMapper("caseloadId"))
			.put("MODIFY_DATE", 			new FieldMapper("modifyDate"))
			.put("DEDUCTION_FLAG", 			new FieldMapper("deductionFlag"))
			.put("TXN_ENTRY_SEQ", 			new FieldMapper("txnEntrySeq"))
			.put("DEDUCTION_TYPE", 			new FieldMapper("deductionType"))
			.put("REMITTER_NAME", 			new FieldMapper("remitterName"))
			.put("PAYEE_CORPORATE_ID", 		new FieldMapper("payeeCorporateId"))
			.put("SUB_ACCOUNT_TYPE", 		new FieldMapper("subAccountType"))
			.put("TXN_ENTRY_DESC", 			new FieldMapper("txnEntryDesc"))
			.put("PRE_WITHHOLD_AMOUNT", 	new FieldMapper("preWithholdAmount"))
			.put("TXN_POSTING_TYPE", 		new FieldMapper("txnPostingType"))
			.put("LAST_NAME", 				new FieldMapper("lastName"))
			.put("FIRST_NAME", 				new FieldMapper("firstName"))
			.put("OFFENDER_BOOK_ID", 		new FieldMapper("offenderBookId"))
			.put("STATUS_DISPLAY", 			new FieldMapper("statusDispaly"))
			.build();
	private final Map<String, FieldMapper> vTrustHeaderAMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_ID", new FieldMapper("offenderId"))
			.put("LAST_NAME", new FieldMapper("lastName"))
			.put("FIRST_NAME", new FieldMapper("firstName")).build();

	private final Map<String, FieldMapper> offenderSubAccountsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("SAT", new FieldMapper("subAccountType"))
			.put("BAL", new FieldMapper("balance"))
			.put("HOLDBAL", new FieldMapper("holdBalance"))
			.build();
	private final Map<String, FieldMapper> corporatesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CORPORATE_ID", new FieldMapper("corporateId"))
			.put("CORPORATE_NAME", new FieldMapper("corporateName"))
			.build();
	private final Map<String, FieldMapper> shadowsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("TRANSFERED_AMOUNT", new FieldMapper("transferedAmount"))
			.put("TRUST_ACCOUNT_CODE", new FieldMapper("trustAccountCode"))
			.put("TAC", new FieldMapper("trustAccountCode"))
			.put("TAMT", new FieldMapper("transferedAmount"))
			.put("OFFENDER_ID", new FieldMapper("offenderId")).build();
	
	private final Map<String, FieldMapper> vtrustHeaderMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("ROOT_OFFENDER_ID", new FieldMapper("rootOffenderId"))
			.put("OFFENDER_ID", new FieldMapper("offenderId")).build();
	
	

	/**
	 * Creates new OtdttaccRepositoryImpl class Object
	 */
	public OtdttaccRepositoryImpl() {
		// OtdttaccRepositoryImpl
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            CaseloadTransactionTypes
	 *
	 * @return List<CaseloadTransactionTypes>
	 *
	 * 
	 */
	public List<CaseloadTransactionTypes> csldTtExecuteQuery(final CaseloadTransactionTypes objSearchDao) {
		final String sql = getQuery("OTDTTACC_CSLDTT_FIND_CASELOAD_TRANSACTION_TYPES");
		final RowMapper<CaseloadTransactionTypes> CaseloadTransactionTypesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				CaseloadTransactionTypes.class, caseloadTransactionTypesMapping);
		final ArrayList<CaseloadTransactionTypes> returnList = (ArrayList<CaseloadTransactionTypes>) namedParameterJdbcTemplate
				.query(sql, createParams(), CaseloadTransactionTypesRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstCaseloadTransactionTypes
	 *            List<CaseloadTransactionTypes>
	 *
	 * @return List<Integer>
	 *
	 * 
	 */
	public Integer csldTtInsertCaseloadTransactionTypes(
			final List<CaseloadTransactionTypes> lstCaseloadTransactionTypes) {
		final String sql = getQuery("OTDTTACC_CSLDTT_INSERT_CASELOAD_TRANSACTION_TYPES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final CaseloadTransactionTypes caseloadTransactionTypes : lstCaseloadTransactionTypes) {
			parameters.add(new BeanPropertySqlParameterSource(caseloadTransactionTypes));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstCaseloadTransactionTypes.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstCaseloadTransactionTypes
	 *            List<CaseloadTransactionTypes>
	 *
	 * 
	 */
	public Integer csldTtDeleteCaseloadTransactionTypes(
			final List<CaseloadTransactionTypes> lstCaseloadTransactionTypes) {
		final String sql = getQuery("OTDTTACC_CSLDTT_DELETE_CASELOAD_TRANSACTION_TYPES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final CaseloadTransactionTypes caseloadTransactionTypes : lstCaseloadTransactionTypes) {
			parameters.add(new BeanPropertySqlParameterSource(caseloadTransactionTypes));
		}
		try {
			String tableName = "CASELOAD_TRANSACTION_TYPES";
			String whereClause = null;
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method csldTtDeleteCaseloadTransactionTypes", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstCaseloadTransactionTypes.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            AccountPeriods
	 *
	 * @return List<AccountPeriods>
	 *
	 * 
	 */
	public List<AccountPeriods> acPrdExecuteQuery(final AccountPeriods objSearchDao) {
		final String sql = getQuery("OTDTTACC_ACPRD_FIND_ACCOUNT_PERIODS");
		final RowMapper<AccountPeriods> AccountPeriodsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				AccountPeriods.class, accountPeriodsMapping);
		final ArrayList<AccountPeriods> returnList = (ArrayList<AccountPeriods>) namedParameterJdbcTemplate.query(sql,
				createParams(), AccountPeriodsRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstAccountPeriods
	 *            List<AccountPeriods>
	 *
	 * @return List<Integer>
	 *
	 */
	public Integer acPrdInsertAccountPeriods(final List<AccountPeriods> lstAccountPeriods) {
		final String sql = getQuery("OTDTTACC_ACPRD_INSERT_ACCOUNT_PERIODS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final AccountPeriods accountPeriods : lstAccountPeriods) {
			parameters.add(new BeanPropertySqlParameterSource(accountPeriods));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstAccountPeriods.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstAccountPeriods
	 *            List<AccountPeriods>
	 * 
	 */
	public Integer acPrdUpdateAccountPeriods(final List<AccountPeriods> lstAccountPeriods) {
		final String sql = getQuery("OTDTTACC_ACPRD_UPDATE_ACCOUNT_PERIODS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final AccountPeriods accountPeriods : lstAccountPeriods) {
			parameters.add(new BeanPropertySqlParameterSource(accountPeriods));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstAccountPeriods.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            OffenderTransactions
	 *
	 * @return List<OffenderTransactions>
	 *
	 * 
	 */
	public List<OffenderTransactions> offTxnExecuteQuery(final OffenderTransactions objSearchDao) {
		final String sql = getQuery("OTDTTACC_OFFTXN_FIND_OFFENDER_TRANSACTIONS");
		final RowMapper<OffenderTransactions> OffenderTransactionsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderTransactions.class, offenderTransactionsMapping);
		final ArrayList<OffenderTransactions> returnList = (ArrayList<OffenderTransactions>) namedParameterJdbcTemplate
				.query(sql, createParams(), OffenderTransactionsRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstOffenderTransactions
	 *            List<OffenderTransactions>
	 *
	 * @return List<Integer>
	 * 
	 */
	public Integer offTxnInsertOffenderTransactions(final List<OffenderTransactions> lstOffenderTransactions) {
		final String sql = getQuery("OTDTTACC_INSRT_INTO_OFFENDER_TRANS_FORM");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderTransactions offenderTransactions : lstOffenderTransactions) {
			parameters.add(new BeanPropertySqlParameterSource(offenderTransactions));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("offTxnInsertOffenderTransactions Insert Time : ", e);
		}
		if (lstOffenderTransactions.size() == returnArray.length) {
			return 1;

		} else {
			return 0;
		}

	}

	public void updateOffenderBalance(final List<OffenderTransactions> paramBean) {

		SqlParameter[] sqlParameters = new SqlParameter[] { new SqlParameter("P_CSLD_ID", OracleTypes.VARCHAR),
				new SqlParameter("P_OFF_ID", OracleTypes.NUMBER),
				new SqlParameter("P_TRANS_POST_TYPE", OracleTypes.VARCHAR),
				new SqlParameter("P_TRANS_DATE", OracleTypes.DATE),
				new SqlParameter("P_TRANS_NUMBER", OracleTypes.NUMBER),
				new SqlParameter("P_TRANS_TYPE", OracleTypes.VARCHAR),
				new SqlParameter("P_TRANS_AMOUNT", OracleTypes.NUMBER),
				new SqlParameter("P_SUB_ACT_TYPE", OracleTypes.VARCHAR),
				new SqlParameter("P_ALLOW_OVERDRAWN", OracleTypes.VARCHAR), };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TRUST").withProcedureName("UPDATE_OFFENDER_BALANCE").declareParameters(sqlParameters);
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		for (final OffenderTransactions obj : paramBean) {
			inParamMap.put("P_CSLD_ID", obj.getCaseloadId());
			inParamMap.put("P_OFF_ID", obj.getOffenderId());
			inParamMap.put("P_TRANS_POST_TYPE", "DR");
			inParamMap.put("P_TRANS_DATE", obj.getTxnEntryDate());
			inParamMap.put("P_TRANS_NUMBER", obj.getTxnId());
			inParamMap.put("P_TRANS_TYPE", obj.getTxnType());
			inParamMap.put("P_TRANS_AMOUNT", obj.getTxnEntryAmount());
			inParamMap.put("P_SUB_ACT_TYPE", obj.getSubAccountType());
			inParamMap.put("P_ALLOW_OVERDRAWN", "N");
		}
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		simpleJDBCCall.execute(inParameter);
	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstOffenderTransactions
	 *            List<OffenderTransactions>
	 *
	 * 
	 */
	public Integer offTxnUpdateOffenderTransactions(final List<OffenderTransactions> lstOffenderTransactions) {
		final String sql = getQuery("OTDTTACC_OFFTXN_UPDATE_OFFENDER_TRANSACTIONS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderTransactions offenderTransactions : lstOffenderTransactions) {
			parameters.add(new BeanPropertySqlParameterSource(offenderTransactions));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenderTransactions.size() == returnArray.length) {
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
		final String sql = getQuery("OTDTTACC_SYSPFL_FIND_SYSTEM_PROFILES");
		final RowMapper<SystemProfiles> SystemProfilesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				SystemProfiles.class, systemProfilesMapping);
		final ArrayList<SystemProfiles> returnList = (ArrayList<SystemProfiles>) namedParameterJdbcTemplate.query(sql,
				createParams(), SystemProfilesRowMapper);
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<TransactionTypes> cgfkCsldTtTxnTypeRecordGroup(final String caseLoadId) {
		final String sql = getQuery("OTDTTACC_FIND_CGFKCSLDTTTXNTYPE");
		final RowMapper<TransactionTypes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, TransactionTypes.class,
				mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams("CASELOADID", caseLoadId), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	/*
	 * public List<M> cgfkOffTxnOffenderIdRecordGroup() { final String sql =
	 * getQuery("OTDTTACC_FIND_CGFKOFFTXNOFFENDERID"); final
	 * RowMapper<M>mRowMapper = Row2BeanRowMapper.makeMapping(sql,M.class,
	 * mMapping);
	 * 
	 * try { return
	 * namedParameterJdbcTemplate.query(sql,createParams(),mRowMapper); } catch
	 * (EmptyResultDataAccessException e) { return Collections.emptyList(); } }
	 */

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<Caseloads> cgfkCsldTtCaseloadIdRecordGroup(final String caseLoadId) {
		final String sql = getQuery("OTDTTACC_FIND_CGFKCSLDTTCASELOADID");
		final RowMapper<Caseloads> mRowMapper = Row2BeanRowMapper.makeMapping(sql, Caseloads.class, mMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("CASELOADID", caseLoadId), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkCsldTtCsldTyTxn
	 *
	 * @param params
	 *
	 */
	public TransactionTypes cgfkchkCsldTtCsldTyTxn(final TransactionTypes paramBean) {
		final String sql = getQuery("OTDTTACC_CGFKCHK_CSLD_TT_CSLD_TY_TXN_T");
		final RowMapper<TransactionTypes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, TransactionTypes.class,
				transactionTypesMapping);
		TransactionTypes returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkCsldTtCsldTtCsld
	 *
	 * @param params
	 *
	 */
	public Caseloads cgfkchkCsldTtCsldTtCsld(final Caseloads paramBean) {
		final String sql = getQuery("OTDTTACC_CGFKCHK_CSLD_TT_CSLD_TT_CSLD_");
		final RowMapper<Caseloads> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Caseloads.class,
				caseloadsMapping);
		Caseloads returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	
	public String getHoldClearFlag(final String caseloadId, final Long offenderId, final String casaeloadType) {
		final String sql = getQuery("OTDTTACC_GET_HOLD_CLEAR_FLAG");
		String flag = null;
		flag = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("caseloadId", caseloadId, "offenderId", offenderId, "casaeloadType", casaeloadType),
				String.class);
		return flag;
	}

	
	public List<OffenderSubAccounts> getHoldBal(final String caseloadId, final Long offenderId, final String txnType) {
		final String sql = getQuery("OTDTTACC_GET_HOLD_BAL");
		List<OffenderSubAccounts> returnList = new ArrayList<OffenderSubAccounts>();
		final RowMapper<OffenderSubAccounts> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderSubAccounts.class, offenderSubAccountsMapping);
		returnList = namedParameterJdbcTemplate.query(sql,
				createParams("caseloadId", caseloadId, "offenderId", offenderId, "txnType", txnType), columnRowMapper);
		return returnList;
	}

	
	public String getDuplicateOffenders(final Long offenderId) {
		String value = null;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[2];
		sqlParameters = new SqlParameter[] { new SqlInOutParameter("RETURN_VALUE", OracleTypes.VARCHAR),
				new SqlParameter("P_ROOT_OFFENDER_ID", OracleTypes.NUMBER) };
		final SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("OTDTTACC").withFunctionName("IS_DUPLICATE_OFFENDERS")
				.declareParameters(sqlParameters);
		inParamMap.put("P_ROOT_OFFENDER_ID", offenderId);
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		value = simpleJDBCCall.executeFunction(String.class, inParameter);
		return value;
	}

	
	public List<Corporates> getCorporateidNames(final String toCaseload) {
		final String sql = getQuery("OTDTTACC_GET_CORPORATEID_NAMES");
		List<Corporates> returnList = new ArrayList<Corporates>();
		final RowMapper<Corporates> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Corporates.class,
				corporatesMapping);
		returnList = namedParameterJdbcTemplate.query(sql, createParams("toCaseload", toCaseload), columnRowMapper);
		return returnList;
	}

	
	public Object checkTxnType(final String toCaseload, final String txnType, final String caseloadId) {
		final String sql = getQuery("OTDTTACC_GET_CHECKTXNTYPE");
		Object getOne;
		getOne = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("toCaseload", toCaseload, "txnType", txnType, "caseloadId", caseloadId), Object.class);
		return getOne;
	}

	
	public Integer getNextVal() {
		final String sql = getQuery("OTDTTACC_GET_NEXT_VAL");
		Integer txnId = 0;
		txnId = namedParameterJdbcTemplate.queryForObject(sql, createParams(), Integer.class);
		return txnId;
	}

	
	public List<OffenderSubAcShadows> gettxnAmtandTxnsubAc(final Long offenderId, final String caseloadId) {
		final String sql = getQuery("OTDTTACC_GET_TXN_AMT_TXN_SUBAC");
		List<OffenderSubAcShadows> retunList = new ArrayList<OffenderSubAcShadows>();
		final RowMapper<OffenderSubAcShadows> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderSubAcShadows.class, shadowsMapping);
		retunList = namedParameterJdbcTemplate.query(sql,
				createParams("offenderId", offenderId, "caseloadId", caseloadId), columnRowMapper);
		return retunList;
	}

	
	public Integer processGlTransNew(final List<OffenderTransactions> insertList) {
		Integer genSeq = 0;
		final String desc = " TO CASELOAD: ";
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		for (final OffenderTransactions bean : insertList) {
			final String txnDesc = bean.getTxnEntryDesc() + desc + bean.getTransferCaseloadId();
			inParamMap.put("P_CSLD_ID", bean.getCaseloadId());
			inParamMap.put("P_TRANS_TYPE", bean.getTxnType());
			inParamMap.put("P_OPERATION_TYPE", null);
			inParamMap.put("P_TRANS_AMOUNT", bean.getTxnEntryAmount());
			inParamMap.put("P_TRANS_NUMBER", bean.getTxnId());
			inParamMap.put("P_TRANS_DATE", bean.getTxnEntryDate());
			inParamMap.put("P_TRANS_DESC", txnDesc);
			inParamMap.put("P_TRANS_SEQ", bean.getTxnEntrySeq());
			inParamMap.put("P_MODULE_NAME", "OTDTTACC");
			inParamMap.put("P_OFF_ID", bean.getOffenderId());
			inParamMap.put("P_OFF_BOOK_ID", bean.getOffenderBookId());
			inParamMap.put("P_SUB_ACT_TYPE_DR", bean.getSubAccountType());
			inParamMap.put("P_SUB_ACT_TYPE_CR", null);
			inParamMap.put("P_PAYEE_PERS_ID", null);
			inParamMap.put("P_PAYEE_CORP_ID", bean.getPayeeCorporateId());
			inParamMap.put("P_PAYEE_NAME_TEXT", bean.getPayeeNameText());
			inParamMap.put("P_GL_SQNC", 0);
			inParamMap.put("P_OFF_DED_ID", null);
		}
		namedParameterJdbcTemplate
				.update("call OMS_OWNER.TRUST.PROCESS_GL_TRANS_NEW(:P_CSLD_ID, :P_TRANS_TYPE, :P_OPERATION_TYPE, :P_TRANS_AMOUNT, :P_TRANS_NUMBER, :P_TRANS_DATE, :P_TRANS_DESC,"
						+ " :P_TRANS_SEQ, :P_MODULE_NAME, :P_OFF_ID, :P_OFF_BOOK_ID, :P_SUB_ACT_TYPE_DR, :P_SUB_ACT_TYPE_CR, :P_PAYEE_PERS_ID, :P_PAYEE_CORP_ID, :P_PAYEE_NAME_TEXT,"
						+ " :P_GL_SQNC,:P_OFF_DED_ID)", inParamMap);
		genSeq = 1;
		return genSeq;
	}

	
	public Integer offTxnTorInsertOffenderTransactions(final List<OffenderTransactions> insertList) {
		final String sql = getQuery("OTDTTACC_INSRT_INTO_OFFENDER_TRANS_TOR_FORM");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderTransactions offenderTransactions : insertList) {
			parameters.add(new BeanPropertySqlParameterSource(offenderTransactions));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (insertList.size() == returnArray.length) {
			return 1;

		} else {
			return 0;
		}
	}

	
	public List<OffenderSubAcShadows> getTrustacTransAmt(final Long offenderId, final String caseloadId,
			final String subAccountType) {
		final String sql = getQuery("OTDTTACC_GET_TRUSTACN_TRANSFRAMT");
		List<OffenderSubAcShadows> retunList = new ArrayList<OffenderSubAcShadows>();
		final RowMapper<OffenderSubAcShadows> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderSubAcShadows.class, shadowsMapping);
		retunList = namedParameterJdbcTemplate.query(sql,
				createParams("offenderId", offenderId, "caseloadId", caseloadId, "subAccountType", subAccountType),
				columnRowMapper);
		return retunList;
	}

	
	public Integer offTxnCheckAmtInsertOffenderTransactions(final List<OffenderTransactions> insertList) {
		final String sql = getQuery("OTDTTACC_INSRT_INTO_OFFENDER_CHECKAMT_TRANS_TOR_FORM");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderTransactions offenderTransactions : insertList) {
			parameters.add(new BeanPropertySqlParameterSource(offenderTransactions));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (insertList.size() == returnArray.length) {
			return 1;

		} else {
			return 0;
		}
	}


	
	public Integer processGlTransNewforcheckAmt(final List<OffenderTransactions> insertList) {
		Integer genSeq = 0;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		for (final OffenderTransactions bean : insertList) {
			inParamMap.put("P_CSLD_ID", bean.getCaseloadId());
			inParamMap.put("P_TRANS_TYPE", bean.getTxnType());
			inParamMap.put("P_OPERATION_TYPE", null);
			inParamMap.put("P_TRANS_AMOUNT", bean.getTxnEntryAmount());
			inParamMap.put("P_TRANS_NUMBER", bean.getTxnId());
			inParamMap.put("P_TRANS_DATE", bean.getTxnEntryDate());
			inParamMap.put("P_TRANS_DESC", bean.getTxnEntryDesc());
			inParamMap.put("P_TRANS_SEQ", bean.getTxnEntrySeq());
			inParamMap.put("P_MODULE_NAME", "OTDTTACC");
			inParamMap.put("P_OFF_ID", bean.getOffenderId());
			inParamMap.put("P_OFF_BOOK_ID", bean.getOffenderBookId());
			inParamMap.put("P_SUB_ACT_TYPE_DR", bean.getSubAccountType());
			inParamMap.put("P_SUB_ACT_TYPE_CR", null);
			inParamMap.put("P_PAYEE_PERS_ID", 0);
			inParamMap.put("P_PAYEE_CORP_ID", bean.getPayeeCorporateId());
			inParamMap.put("P_PAYEE_NAME_TEXT", bean.getPayeeNameText());
			inParamMap.put("P_GL_SQNC", 0);
			inParamMap.put("P_OFF_DED_ID", 0);
		}
		namedParameterJdbcTemplate
				.update("call OMS_OWNER.TRUST.PROCESS_GL_TRANS_NEW(:P_CSLD_ID, :P_TRANS_TYPE, :P_OPERATION_TYPE, :P_TRANS_AMOUNT, :P_TRANS_NUMBER, :P_TRANS_DATE, :P_TRANS_DESC,"
						+ " :P_TRANS_SEQ, :P_MODULE_NAME, :P_OFF_ID, :P_OFF_BOOK_ID, :P_SUB_ACT_TYPE_DR, :P_SUB_ACT_TYPE_CR, :P_PAYEE_PERS_ID, :P_PAYEE_CORP_ID, :P_PAYEE_NAME_TEXT,"
						+ " :P_GL_SQNC, :P_OFF_DED_ID)", inParamMap);

		genSeq = 1;
		return genSeq;
	}

	public Integer deleteoffendersubAcShadows(final List<OffenderTransactions> lstOffenderTransactions) {
		final String sql = getQuery("OTDTTACC_DELETE_OFFENDER_SUB_AC_SHADOWS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderTransactions offenderTransactions : lstOffenderTransactions) {
			parameters.add(new BeanPropertySqlParameterSource(offenderTransactions));
		}
		try {
			String tableName = "OFFENDER_SUB_AC_SHADOWS";
			String whereClause = "CASELOAD_ID = :caseloadId   AND   OFFENDER_ID = :offenderId";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteoffendersubAcShadows", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenderTransactions.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	public String getcheckProductionflag(final OffenderTransactions tempBean) {
		final String sql = getQuery("OTDTTACC_CHECK_PRO");
		String checkPro = null;
		try {
			checkPro = namedParameterJdbcTemplate.queryForObject(sql, createParams("caseloadId", tempBean.getCaseloadId()),
					String.class);
		} catch(Exception e) {
			return checkPro;
		}
		return checkPro;
	}

	public String checkChkRrecCur(final OffenderTransactions tempBean) {
		final String sql = getQuery("OTDTTACC_CHECK_BANK_CHEQUE_DATA");
		String checkOne = null;
		try {
			checkOne = namedParameterJdbcTemplate.queryForObject(sql, createParams("txnId", tempBean.getTxnId()),
					String.class);
		} catch (EmptyResultDataAccessException e) {
			checkOne = null;
		}
		return checkOne;
	}

	public void insertIntoChequeData(final OffenderTransactions tempBean) {
		SqlParameter[] sqlParameters = new SqlParameter[] { new SqlParameter("P_CSLD_ID", OracleTypes.VARCHAR),
				new SqlParameter("P_TRANS_NUMBER", OracleTypes.NUMBER),
				new SqlParameter("P_TRANS_AMOUNT", OracleTypes.NUMBER),
				new SqlParameter("P_CHEQUE_FLAG", OracleTypes.VARCHAR),
				new SqlParameter("P_START_TXN_ID", OracleTypes.NUMBER),
				new SqlParameter("P_END_TXN_ID", OracleTypes.NUMBER),
				new SqlParameter("P_PERS_PAYEE_ID", OracleTypes.NUMBER),
				new SqlParameter("P_CORP_PAYEE_ID", OracleTypes.NUMBER),
				new SqlParameter("P_PAYEE_NAME", OracleTypes.VARCHAR),
				new SqlParameter("P_OFFENDER_PAYEE", OracleTypes.NUMBER),
				new SqlParameter("P_SINGLE_ENTRY", OracleTypes.VARCHAR),
				new SqlParameter("P_BANK_ACT_CODE", OracleTypes.NUMBER),
				new SqlParameter("P_MODULE_NAME", OracleTypes.VARCHAR),
				new SqlParameter("P_TRANS_TYPE", OracleTypes.VARCHAR), };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TRUST").withProcedureName("INSERT_INTO_CHEQUE_DATA").declareParameters(sqlParameters);
		final Map<String, Object> inParamMap = new HashMap<String, Object>();

		inParamMap.put("P_CSLD_ID", tempBean.getCaseloadId());
		inParamMap.put("P_TRANS_NUMBER", tempBean.getTxnId());
		inParamMap.put("P_TRANS_AMOUNT", tempBean.getCheckAmnt());
		inParamMap.put("P_CHEQUE_FLAG", "O");
		inParamMap.put("P_START_TXN_ID", tempBean.getTxnId());
		inParamMap.put("P_END_TXN_ID", tempBean.getTxnId());
		inParamMap.put("P_PERS_PAYEE_ID", null);
		inParamMap.put("P_CORP_PAYEE_ID", tempBean.getPayeeCorporateId());
		inParamMap.put("P_PAYEE_NAME", tempBean.getCorporateName());
		inParamMap.put("P_OFFENDER_PAYEE", null);
		inParamMap.put("P_SINGLE_ENTRY", "Y");
		inParamMap.put("P_BANK_ACT_CODE", null);
		inParamMap.put("P_MODULE_NAME", "OTDTTACC");
		inParamMap.put("P_TRANS_TYPE", tempBean.getTxnType());

		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		simpleJDBCCall.execute(inParameter);
	}

	public void updatecheckAmt(final OffenderTransactions tempBean) {
		final String sql = getQuery("OTDTTACC_UPDATE_BANK_CHEQUE_DATA");
		namedParameterJdbcTemplate.update(sql,
				createParams("txnId", tempBean.getTxnId(), "chqamt", tempBean.getTxnEntryAmount()));
	}

	public String trustTranExistCur(final OffenderTransactions tempBean) {
		final String sql = getQuery("OTDTTACC_TRUST_TRAN_EXIST_CUR");
		String trustCur = null;
		try {
			trustCur = namedParameterJdbcTemplate.queryForObject(sql, createParams("txnId", tempBean.getTxnId()),
					String.class);
		} catch (EmptyResultDataAccessException e) {
			trustCur = null;
		}
		return trustCur;
	}

	public void trustTranupdateOffendertrustTrans(final OffenderTransactions tempBean) {
		final String sql = getQuery("OTDTTACC_UPDATE_OFFENDER_TRUST_TRANSFERS");
		namedParameterJdbcTemplate.update(sql,
				createParams("txnId", tempBean.getTxnId(), "checkAmt", tempBean.getTxnEntryAmount(), "modifyUserId", tempBean.getCreateUserId()));
	}

	public void trustTranInsertOffendertrustTrans(final OffenderTransactions tempBean) {
		final String sql = getQuery("OTDTTACC_INSERT_OFFENDER_TRUST_TRANSFERS");
		namedParameterJdbcTemplate.update(sql,
				createParams("txnId", tempBean.getTxnId(), "checkAmt", tempBean.getTxnEntryAmount(), "frcaseload",
						tempBean.getCaseloadId(), "tocaseload", tempBean.getTransferCaseloadId(), "createUserId", tempBean.getCreateUserId()));

	}

	public List<OffenderSubAcShadows> selectOffenderAcntShadows(final String caseloadId, final Long offenderId) {
		final String sql = getQuery("OTDTTACC_SELECT_OFFENDER_SUB_AC_SHADOWS");
		List<OffenderSubAcShadows> returnList= new ArrayList<OffenderSubAcShadows>();
		final RowMapper<OffenderSubAcShadows> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderSubAcShadows.class, shadowsMapping);
		returnList = namedParameterJdbcTemplate.query(sql, createParams("caseloadId", caseloadId, "offenderId",offenderId)
				,columnRowMapper);
		return returnList;

	}
	public Integer inserOffenderAcntShadows(final OffenderSubAccounts objSub) {
		Integer val = 0;
		final String sql = getQuery("OTDTTACC_INSERT_OFFENDER_SUB_AC_SHADOWS");
		val = namedParameterJdbcTemplate.update(sql, createParams("caseloadid", objSub.getCaseloadId(), "offid",
				objSub.getOffenderId(), "sat", objSub.getSubAccountType(), "bal", objSub.getBalance(), "createUserId", objSub.getCreateUserId()));
		return val;

	}

	public OffenderTransactions getRootOffenderId(final String casaeloadType, final String offenderIdDisplay) {
		final String sql = getQuery("OTDTTACC_GET_ROOT_OFFENDER_ID");
		OffenderTransactions bean = new OffenderTransactions();
		final RowMapper<OffenderTransactions> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderTransactions.class, offenderTransactionsMapping);
		try {
			bean = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("caseloadType", casaeloadType, "offenderIdDisplay", offenderIdDisplay),
					columnRowMapper);
			
		} catch (Exception e) {
			logger.error("getRootOffenderId", e);
		}
		return bean;
	}

	public List<OffenderTransactions> whenNewBlockInstanceRetrive(final Date startDate, final Date endDate,
			final String currentCaseload, final String toCaseload, final String txnType) {
		final String sql = getQuery("OTDTTACC_RETRIEVE_WHEN_NEWBLOCK_INSTANCE");
		List<OffenderTransactions> returnList = new ArrayList<OffenderTransactions>();
		final RowMapper<OffenderTransactions> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderTransactions.class, corporatesMapping);
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("startDate", startDate, "endDate", endDate,
					"currentCaseload", currentCaseload, "toCaseload", toCaseload, "txnType", txnType), columnRowMapper);
		} catch (Exception e) {
			logger.error("whenNewBlockInstanceRetrive", e);
		}
		return returnList;
	}

	public Integer deleteOffacShads(final String caseloadId, final Long offenderId, String modifyUserId) {
		final String sql = getQuery("OTDTTACC_DELETE_OFFENDER_SUB_AC_SHADOWS");
		Integer val = 0;
		try {
			String tableName = "OFFENDER_SUB_AC_SHADOWS";
			String whereClause = "CASELOAD_ID = :caseloadId   AND   OFFENDER_ID = :offenderId";
			Map<String , Object> inputMap = new HashMap<String, Object>();
			inputMap.put("caseloadId", caseloadId);
			inputMap.put("offenderId", offenderId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereClause , inputMap);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteOffacShads", e);
		}
		val = namedParameterJdbcTemplate.update(sql, createParams("caseloadId", caseloadId, "offenderId", offenderId));
		return val;

	}

	public List<VTrustHeader> getRootOffenderIds(final String caseloadId, final String offenderIdDisplay) {
		final String sql = getQuery("OTDTTACC_RETRIEVE_ROOTOFFENDERS");
		List<VTrustHeader> returnList = new ArrayList<VTrustHeader>();
		final RowMapper<VTrustHeader> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				VTrustHeader.class, vtrustHeaderMapping);
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(
					"caseloadId", caseloadId,"offenderIdDisplay",offenderIdDisplay ), columnRowMapper);
		} catch (Exception e) {
			logger.error("whenNewBlockInstanceRetrive", e);
		}
		return returnList;
	}

	public String deductionGetAcAndSetIndDate(final Long offenderId, final String caseloadId) {
		
		String openAnAccount = null;
		Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("p_csld_id", caseloadId);
		inParamMap.put("p_off_id", offenderId);
		try {
			namedParameterJdbcTemplate
					.update("call OMS_OWNER.DEDUCTIONS.GET_AC_AND_SET_IND_DATE(:p_off_id, :p_csld_id)", inParamMap);
			openAnAccount = "GETINDDATE";
		} catch (Exception e) {
			logger.error("getAcAndSetIndDate :" + e);
		}
		return openAnAccount;
	}

	public OffenderTransactions getBookIdandStatusDisplay(final Long rootOffenderId, final String casaeloadType, final String userName) {
		final String sql = getQuery("OTDTTACC_GET_OFFENDER_BOOK_IDAND_STATUS_DISPLAY");
		OffenderTransactions bean = new OffenderTransactions();
		final RowMapper<OffenderTransactions> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderTransactions.class, offenderTransactionsMapping);
		try {
			bean = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("rootOffenderId",rootOffenderId ,"caseloadType", casaeloadType, "CURRENT_USER", userName),
					columnRowMapper);
			
		} catch (Exception e) {
			logger.error("getBookIdandStatusDisplay", e);
		}
		return bean;
	}

}
