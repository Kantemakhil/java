package net.syscon.s4.inmate.trust.trustaccounts.impl;

import java.math.BigDecimal;
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
import org.springframework.jdbc.core.SqlOutParameter;
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
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.im.beans.SysDual;
import net.syscon.s4.inmate.beans.OffenderSubAccounts;
import net.syscon.s4.inmate.trust.trustaccounts.OtdcloseRepository;
import oracle.jdbc.OracleTypes;

/**
 * Class OtdcloseRepositoryImpl
 */
@Repository
public class OtdcloseRepositoryImpl extends RepositoryBase implements OtdcloseRepository {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OtdcloseRepositoryImpl.class.getName());
	
	private final Map<String, FieldMapper> referenceCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
				.put("DOMAIN", 						new FieldMapper("domain"))
				.put("DESCRIPTION", 				new FieldMapper("description"))
				.put("PAYEE_CODE", 					new FieldMapper("payeeCode"))
				.put("ACCOUNT_NAME", 				new FieldMapper("accountName"))
				.build();
	private final Map<String, FieldMapper> offenderSubAccountsMapping = new ImmutableMap.Builder<String, FieldMapper>()
				.put("CREATE_USER_ID", 					new FieldMapper("createUserId"))
				.put("TRUST_ACCOUNT_CODE", 				new FieldMapper("trustAccountCode"))
				.put("MODIFY_USER_ID", 					new FieldMapper("modifyUserId"))
				.put("LIST_SEQ", 						new FieldMapper("listSeq"))
				.put("HOLD_BALANCE", 					new FieldMapper("holdBalance"))
				.put("LAST_TXN_ID", 					new FieldMapper("lastTxnId"))
				.put("IND_DAYS", 						new FieldMapper("indDays"))
				.put("OFFENDER_ID", 					new FieldMapper("offenderId"))
				.put("BALANCE", 						new FieldMapper("balance"))
				.put("CASELOAD_ID", 					new FieldMapper("caseloadId"))
				.put("MODIFY_DATE", 					new FieldMapper("modifyDate"))
				.put("IND_DATE", 						new FieldMapper("indDate"))
				.put("MINIMUM_BALANCE", 				new FieldMapper("minimumBalance"))
				.put("SEAL_FLAG", 						new FieldMapper("sealFlag"))
				.put("CREATE_DATETIME", 				new FieldMapper("createDatetime"))
				.put("MODIFY_DATETIME", 				new FieldMapper("modifyDatetime"))
				.build();
	private final Map<String, FieldMapper> systemProfilesMapping = new ImmutableMap.Builder<String, FieldMapper>()
				.put("PROFILE_CODE", 					new FieldMapper("profileCode"))
				.put("PROFILE_TYPE", 					new FieldMapper("profileType"))
				.put("CREATE_USER_ID", 					new FieldMapper("createUserId"))
				.put("SEAL_FLAG", 						new FieldMapper("sealFlag"))
				.put("CREATE_DATETIME", 				new FieldMapper("createDatetime"))
				.put("MODIFY_USER_ID", 					new FieldMapper("modifyUserId"))
				.put("OLD_TABLE_NAME", 					new FieldMapper("oldTableName"))
				.put("PROFILE_VALUE", 					new FieldMapper("profileValue"))
				.put("MODIFY_DATETIME", 				new FieldMapper("modifyDatetime"))
				.put("PROFILE_VALUE_2", 				new FieldMapper("profileValue2"))
				.put("DESCRIPTION", 					new FieldMapper("description"))
				.build();
	private final Map<String, FieldMapper> offenderTransactionsMapping = new ImmutableMap.Builder<String, FieldMapper>()
				.put("TXN_REFERENCE_NUMBER", 				new FieldMapper("txnReferenceNumber"))
				.put("INFO_NUMBER", 						new FieldMapper("infoNumber"))
				.put("APPLY_SPENDING_LIMIT_AMOUNT", 		new FieldMapper("applySpendingLimitAmount"))
				.put("TXN_ADJUSTED_FLAG", 					new FieldMapper("txnAdjustedFlag"))
				.put("ADJUST_TXN_ENTRY_ID", 				new FieldMapper("adjustTxnEntryId"))
				.put("PAYEE_NAME_TEXT", 					new FieldMapper("payeeNameText"))
				.put("RECEIPT_PRINTED_FLAG", 				new FieldMapper("receiptPrintedFlag"))
				.put("OFFENDER_ID", 						new FieldMapper("offenderId"))
				.put("HOLD_CLEAR_FLAG", 					new FieldMapper("holdClearFlag"))
				.put("GROSS_NET_FLAG", 						new FieldMapper("grossNetFlag"))
				.put("SEAL_FLAG", 						    new FieldMapper("sealFlag"))
				.put("CREATE_DATETIME", 					new FieldMapper("createDatetime"))
				.put("HOLD_NUMBER", 						new FieldMapper("holdNumber"))
				.put("TXN_TYPE", 						    new FieldMapper("txnType"))
				.put("GROSS_AMOUNT", 						new FieldMapper("grossAmount"))
				.put("RECEIPT_PENDING_PRINT_FLAG", 			new FieldMapper("receiptPendingPrintFlag"))
				.put("MODIFY_DATETIME", 				    new FieldMapper("modifyDatetime"))
				.put("PAYEE_PERSON_ID", 					new FieldMapper("payeePersonId"))
				.put("ORG_TXN_TYPE", 						new FieldMapper("orgTxnType"))
				.put("TRANSFER_CASELOAD_ID", 				new FieldMapper("transferCaseloadId"))
				.put("TXN_ID", 						        new FieldMapper("txnId"))
				.put("OFFENDER_BOOK_ID", 					new FieldMapper("offenderBookId"))
				.put("REMITTER_ID", 						new FieldMapper("remitterId"))
				.put("CREATE_USER_ID", 						new FieldMapper("createUserId"))
				.put("TXN_ENTRY_DATE", 						new FieldMapper("txnEntryDate"))
				.put("PAYEE_CODE", 						    new FieldMapper("payeeCode"))
				.put("MODIFY_USER_ID", 						new FieldMapper("modifyUserId"))
				.put("TXN_ENTRY_AMOUNT", 					new FieldMapper("txnEntryAmount"))
				.put("ADJUST_OFFENDER_ID", 					new FieldMapper("adjustOffenderId"))
				.put("RECEIPT_NUMBER", 						new FieldMapper("receiptNumber"))
				.put("ADJUST_TXN_ID", 						new FieldMapper("adjustTxnId"))
				.put("CLOSING_CHEQUE_NUMBER", 				new FieldMapper("closingChequeNumber"))
				.put("HOLD_UNTIL_DATE", 					new FieldMapper("holdUntilDate"))
				.put("SLIP_PRINTED_FLAG", 					new FieldMapper("slipPrintedFlag"))
				.put("ADJUST_ACCOUNT_CODE", 				new FieldMapper("adjustAccountCode"))
				.put("CASELOAD_ID", 						new FieldMapper("caseloadId"))
				.put("MODIFY_DATE", 						new FieldMapper("modifyDate"))
				.put("DEDUCTION_FLAG", 						new FieldMapper("deductionFlag"))
				.put("TXN_ENTRY_SEQ", 						new FieldMapper("txnEntrySeq"))
				.put("DEDUCTION_TYPE", 						new FieldMapper("deductionType"))
				.put("REMITTER_NAME", 						new FieldMapper("remitterName"))
				.put("PAYEE_CORPORATE_ID", 					new FieldMapper("payeeCorporateId"))
				.put("SUB_ACCOUNT_TYPE", 					new FieldMapper("subAccountType"))
				.put("TXN_ENTRY_DESC", 						new FieldMapper("txnEntryDesc"))
				.put("PRE_WITHHOLD_AMOUNT", 				new FieldMapper("preWithholdAmount"))
				.put("TXN_POSTING_TYPE", 					new FieldMapper("txnPostingType"))
				.build();

	/**
	 * Creates new OtdcloseRepositoryImpl class Object
	 */
	public OtdcloseRepositoryImpl() {
		// OtdcloseRepositoryImpl
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            OffenderSubAccounts
	 *
	 * @return List<OffenderSubAccounts>
	 *
	 * @
	 */
	public List<OffenderSubAccounts> offSubaExecuteQuery(final OffenderSubAccounts objSearchDao) {
		final String sql = getQuery("OTDCLOSE_OFFSUBA_FIND_OFFENDER_SUB_ACCOUNTS");
		final RowMapper<OffenderSubAccounts> OffenderSubAccountsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderSubAccounts.class, offenderSubAccountsMapping);
		List<OffenderSubAccounts> returnList = new ArrayList<>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), OffenderSubAccountsRowMapper);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"error in method offSubaExecuteQuery : ", e);
		}
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
	 * @param lstOffenderSubAccounts
	 *            List<OffenderSubAccounts>
	 *
	 * @return List<Integer>
	 *
	 * @
	 */
	public Integer offSubaInsertOffenderSubAccounts(final List<OffenderSubAccounts> lstOffenderSubAccounts) {
		final String sql = getQuery("OTDCLOSE_OFFSUBA_INSERT_OFFENDER_SUB_ACCOUNTS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderSubAccounts offenderSubAccounts : lstOffenderSubAccounts) {
			parameters.add(new BeanPropertySqlParameterSource(offenderSubAccounts));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"error in method offSubaInsertOffenderSubAccounts : ", e);
		}
		if (lstOffenderSubAccounts.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstOffenderSubAccounts
	 *            List<OffenderSubAccounts>
	 *
	 * @
	 */
	public Integer offSubaUpdateOffenderSubAccounts(final List<OffenderSubAccounts> lstOffenderSubAccounts) {
		final String sql = getQuery("OTDCLOSE_OFFSUBA_UPDATE_OFFENDER_SUB_ACCOUNTS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderSubAccounts offenderSubAccounts : lstOffenderSubAccounts) {
			parameters.add(new BeanPropertySqlParameterSource(offenderSubAccounts));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"error in method offSubaUpdateOffenderSubAccounts : ", e);
		}
		if (lstOffenderSubAccounts.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstOffenderSubAccounts
	 *            List<OffenderSubAccounts>
	 *
	 * @
	 */
	public Integer offSubaDeleteOffenderSubAccounts(final List<OffenderSubAccounts> lstOffenderSubAccounts) {
		final String sql = getQuery("OTDCLOSE_OFFSUBA_DELETE_OFFENDER_SUB_ACCOUNTS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderSubAccounts offenderSubAccounts : lstOffenderSubAccounts) {
			parameters.add(new BeanPropertySqlParameterSource(offenderSubAccounts));
		}
		try {
			String tableName = "OFFENDER_SUB_ACCOUNTS";
			String whereClause = null;
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method offSubaDeleteOffenderSubAccounts", e);
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"error in method offSubaDeleteOffenderSubAccounts : ", e);
		}
		if (lstOffenderSubAccounts.size() == returnArray.length) {
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
	 * @
	 */
	public List<OffenderTransactions> offTxnExecuteQuery(final OffenderTransactions objSearchDao) {
		final String sql = getQuery("OTDCLOSE_OFFTXN_FIND_OFFENDER_TRANSACTIONS");
		final RowMapper<OffenderTransactions> OffenderTransactionsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderTransactions.class, offenderTransactionsMapping);
		List<OffenderTransactions> returnList = new ArrayList<>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), OffenderTransactionsRowMapper);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"error in method offTxnExecuteQuery : ", e);
		}
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
	 * @
	 */
	public Integer offTxnInsertOffenderTransactions(final List<OffenderTransactions> lstOffenderTransactions) {
		final String sql = getQuery("OTDCLOSE_OFFTXN_INSERT_OFFENDER_TRANSACTIONS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderTransactions offenderTxns : lstOffenderTransactions) {
			parameters.add(new BeanPropertySqlParameterSource(offenderTxns));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"error in method offTxnInsertOffenderTransactions : ", e);

		}
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
	 * @
	 */
	public List<SystemProfiles> sysPflExecuteQuery(final SystemProfiles objSearchDao) {
		final String sql = getQuery("OTDCLOSE_SYSPFL_FIND_SYSTEM_PROFILES");
		final RowMapper<SystemProfiles> SystemProfilesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				SystemProfiles.class, systemProfilesMapping);
		List<SystemProfiles> returnList = new ArrayList<>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), SystemProfilesRowMapper);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"error in method sysPflExecuteQuery : ", e);
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> cgfkOffTxnPayeeCodeRecordGroup() {
		final String sql = getQuery("OTDCLOSE_FIND_CGFKOFFTXNPAYEECODE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error(this.getClass().getName()+"error in method cgfkOffTxnPayeeCodeRecordGroup : ", e);
			return Collections.emptyList();
		}
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offTxnWhenNewBlockInstanceWHEN-NEW-BLOCK-INSTANCE
	 *
	 * @param params
	 *
	 */
	public ReferenceCodes offTxnWhenNewBlockInstance(final ReferenceCodes paramBean) {
		final String sql = getQuery("OTDCLOSE_OFF_TXN_WHENNEWBLOCKINSTANCE_WHENNEWBLOCKINSTANCE");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		ReferenceCodes returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
					columnRowMapper);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"error in method offTxnWhenNewBlockInstance : ", e);
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgwhenNewFormInstance
	 *
	 * @param params
	 *
	 */
	public List<Object> cgwhenNewFormInstance(final SysDual paramBean) {
		final String sql = getQuery("OTDCLOSE_CGWHEN_NEW_FORM_INSTANCE");
		List<Object> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.queryForList(sql, createParams(), Object.class);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOffTxnOffTxnRef
	 *
	 * @param params
	 *
	 */
	public List<ReferenceCodes> cgfkchkOffTxnOffTxnRef(final ReferenceCodes paramBean) {
		final String sql = getQuery("OTDCLOSE_CGFKCHK_OFF_TXN_OFF_TXN_REF_C");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"error in method cgfkchkOffTxnOffTxnRef : ", e);
			return Collections.emptyList();

		}
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOffTxnOffTxnRef
	 *
	 * @param params
	 *
	 */
	public BigDecimal getRegBal(final Long offenderId, final String caseloadId) {
		final String sql = getQuery("OTDCLOSE_GET_REG_BAL");
		BigDecimal returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("offenderId", offenderId, "caseloadId", caseloadId), BigDecimal.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"error in method getRegBal : ", e);
		}
		if (returnObj != null) {
			return returnObj;
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOffTxnOffTxnRef
	 *
	 * @param params
	 *
	 */
	public List<AccountCodes> accountNameForValidation() {
		final String sql = getQuery("OTDCLOSE_ACCOUNT_NAME_FOR_VALIDATION");
		final RowMapper<AccountCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, AccountCodes.class,
				referenceCodesMapping);
		final List<AccountCodes> returnObj = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOffTxnOffTxnRef
	 *
	 * @param params
	 *
	 */
	public String pCashtxnOperation(String userName) {
		final String sql = getQuery("OTDCLOSE_P_CACH");
		String returnObj = "";
		try {
		returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("USER_ID", userName), String.class);
		}catch (Exception e) {
			logger.error(this.getClass().getName()+"error in method pCashtxnOperation: ", e);
			returnObj = "";
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOffTxnOffTxnRef
	 *
	 * @param params
	 *
	 */
	public String pChecktxnOperation(String userName) {
		final String sql = getQuery("OTDCLOSE_P_CHECK");
		String returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("USER_ID", userName), String.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"error in method pChecktxnOperation: ", e);
			returnObj = "";
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOffTxnOffTxnRef
	 *
	 * @param params
	 *
	 */
	public String accountClosedFlagValidation(final Long offenderId, final String caseloadId) {
		final String sql = getQuery("OTDCLOSE_ACCOUNT_CLOSED_FLAG");
		String returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("POFFID", offenderId, "PCSLD", caseloadId), String.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"error in method accountClosedFlagValidation: ", e);
			returnObj = "N";
			return returnObj;
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOffTxnOffTxnRef
	 *
	 * @param params
	 *
	 */
	public Integer offenderForeignCurrenciesCount(final Long offenderId, final String caseloadId) {
		final String sql = getQuery("OTDCLOSE_OFFENDER_FOREIGN_CURRENCIES");
		Integer returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("POFFID", offenderId, "PCSLD", caseloadId), Integer.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"error in method offenderForeignCurrenciesCount : ", e);
		}
		if (returnObj > 1) {
			returnObj = 2;
			return returnObj;
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOffTxnOffTxnRef
	 *
	 * @param params
	 *
	 */
	public Double holdDataCount(final Long offenderId, final String caseloadId) {
		final String sql = getQuery("OTDCLOSE_HOLD_DATA_COUNT");
		Double returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("POFFID", offenderId, "PCSLD", caseloadId), Double.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"error in method holdDataCount : ", e);
			return returnObj;
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * checkLock
	 *
	 * @param params
	 *
	 */
	public List<OffenderSubAccounts> balanceSubAccountCode(final Long offenderId, final String caseloadId) {
		final String sql = getQuery("OTDCLOSE_TRUST_ACCOUNT_CODE_BALANCE");
		final RowMapper<OffenderSubAccounts> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderSubAccounts.class, offenderSubAccountsMapping);
		List<OffenderSubAccounts> returnList = new ArrayList<>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("POFFID", offenderId, "PCSLDID", caseloadId),
					columnRowMapper);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"error in method balanceSubAccountCode : ", e);
		}
		if (returnList != null) {
			return returnList;
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 *
	 * @return Integer
	 */
	public Integer txnIdNextValData() {
		final String sql = getQuery("OTDCLOSE_TXN_ID");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Integer.class);
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
	 * @
	 */
	public Integer offTxnInsertOffenderTransactions(final Integer txnIdNextVal, final BigDecimal pTxnEntrySeq,
			final String caseloadId, final Long offenderId, final Long offenderBookId, final String postType,
			final String txnType, final String txnDesc, final Double pBalance, final String pSuAaccountType,
			final String modifyUserId, final String slippedFlag, final String txnAdjFlag, final String holdFlag,
			final String payeeCode, final Integer payeeCorporateId, final Integer payeePersonId, final String userName) {
		final String sql = getQuery("OTDCLOSE_INSERT_INTO_OFFENDER_TRANSACTIONS");
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("PTRANSNUMBER", txnIdNextVal);
		paramMap.put("PTXNENTRYSEQ", pTxnEntrySeq);
		paramMap.put("PCSLDID", caseloadId);
		paramMap.put("ROOTOFFID", offenderId);
		paramMap.put("POFFBOOKID", offenderBookId);
		paramMap.put("PTXNPTGTYPE", postType);
		paramMap.put("PTXNTYPE", txnType);
		paramMap.put("PTXNDESC", txnDesc);
		paramMap.put("PTXNAMOUNT", pBalance);
		paramMap.put("PSUBACTYPE", pSuAaccountType);
		paramMap.put("PMODFYUSERID", modifyUserId);
		paramMap.put("PSLIPPRFLAG", slippedFlag);
		paramMap.put("PTXNADJFLAG", txnAdjFlag);
		paramMap.put("PHOLDCLFLAG", holdFlag);
		paramMap.put("PPAYEECODE", payeeCode);
		paramMap.put("PCORPORATEID", payeeCorporateId);
		paramMap.put("PPERSONID", payeePersonId);
		paramMap.put("createUserId", userName);
		Integer returnArray = null;
		try {
			returnArray = namedParameterJdbcTemplate.update(sql, paramMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"error in method offTxnInsertOffenderTransactions : ", e);
		}
		if (returnArray != 0) {
			return returnArray;
		}
		return returnArray;
	}

	public void updateOffenderBalance(final String caseloadId, final Long offenderId, final String postType,
			final Date transDate, final Integer txnIdNextVal, final String txnType, final Double pBalance,
			final String pSuAaccountType) {
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("p_csld_id", caseloadId);
		inParamMap.put("p_off_id", offenderId);
		inParamMap.put("p_trans_post_type", postType);
		inParamMap.put("p_trans_date", transDate);
		inParamMap.put("p_trans_number", txnIdNextVal);
		inParamMap.put("p_trans_type", txnType);
		inParamMap.put("p_trans_amount", pBalance);
		inParamMap.put("p_sub_act_type", pSuAaccountType);
		inParamMap.put("p_allow_overdrawn", "N");
		namedParameterJdbcTemplate
		.update("call OMS_OWNER.TRUST.UPDATE_OFFENDER_BALANCE(:p_csld_id, :p_off_id, :p_trans_post_type, :p_trans_date, :p_trans_number, :p_trans_type, :p_trans_amount,"
				+ " :p_sub_act_type, :p_allow_overdrawn)", inParamMap);
	}

	/**
	 * This method will update the transaction in the table
	 * 
	 * @param offTrans
	 * @return Integer
	 */
	@Override
	public Integer processGlTransNew(final String caseloadId, final String txnType, final String operationType,
			final Double pBalance, final Integer txnIdNextVal, final Date transDate, final String txnDesc,
			final BigDecimal pGlTxnEntrySeq, final Long offenderId, final Long offenderBookId,
			final String pSuAaccountType, final String pSuAaccountTypeCr, final Integer payeePersonId,
			final Integer corporateId, final String payeeNameText, final BigDecimal pGlSeq) {
		Integer genSeq = 0;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("P_CSLD_ID", caseloadId);
		inParamMap.put("P_TRANS_TYPE", txnType);
		inParamMap.put("P_OPERATION_TYPE", operationType);
		inParamMap.put("P_TRANS_AMOUNT", pBalance);
		inParamMap.put("P_TRANS_NUMBER", txnIdNextVal);
		inParamMap.put("P_TRANS_DATE", transDate);
		inParamMap.put("P_TRANS_DESC", txnDesc);
		inParamMap.put("P_TRANS_SEQ", pGlTxnEntrySeq);
		inParamMap.put("P_MODULE_NAME", "OTDCLOSE");
		inParamMap.put("P_OFF_ID", offenderId);
		inParamMap.put("P_OFF_BOOK_ID", offenderBookId);
		inParamMap.put("P_SUB_ACT_TYPE_DR", pSuAaccountType);
		inParamMap.put("P_SUB_ACT_TYPE_CR", pSuAaccountTypeCr);
		inParamMap.put("P_PAYEE_PERS_ID", payeePersonId);
		inParamMap.put("P_PAYEE_CORP_ID", corporateId);
		inParamMap.put("P_PAYEE_NAME_TEXT", payeeNameText);
		inParamMap.put("P_GL_SQNC", 0);
		inParamMap.put("P_OFF_DED_ID", null);
		namedParameterJdbcTemplate
				.update("call OMS_OWNER.TRUST.PROCESS_GL_TRANS_NEW(:P_CSLD_ID, :P_TRANS_TYPE, :P_OPERATION_TYPE, :P_TRANS_AMOUNT, :P_TRANS_NUMBER, :P_TRANS_DATE, :P_TRANS_DESC,"
						+ " :P_TRANS_SEQ, :P_MODULE_NAME, :P_OFF_ID, :P_OFF_BOOK_ID, :P_SUB_ACT_TYPE_DR, :P_SUB_ACT_TYPE_CR, :P_PAYEE_PERS_ID, :P_PAYEE_CORP_ID, :P_PAYEE_NAME_TEXT,"
						+ " :P_GL_SQNC, :P_OFF_DED_ID)", inParamMap);
		genSeq = 1;
		return genSeq;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOffTxnOffTxnRef
	 *
	 * @param params
	 *
	 */
	public Integer accountCodeNumber(final String subAcntType, String userName) {
		final String sql = getQuery("OTDCLOSE_P_TRUST_ACCOUNT_CODE");
		Integer returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("PSUBACTYPE", subAcntType, "USER_ID", userName),
					Integer.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"error in method accountCodeNumber : ", e);
		}
		if (returnObj != null) {
			return returnObj;
		}
		return returnObj;
	}

	public Integer updateOffenderSubAccounts(final Integer txnIdNextVal, final BigDecimal pTxnEntrySeq,
			final String caseloadId, final Long offenderId, final Integer pTrustAccountCode, final String userName) {
		final String sql = getQuery("OTDCLOSE_OFFENDER_SUB_ACCOUNTS_BALANCE");
		Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("PTXNNUM", txnIdNextVal);
		inParamMap.put("PTXNENTRYSEQ", pTxnEntrySeq);
		inParamMap.put("PCSLDID", caseloadId);
		inParamMap.put("POFFID", offenderId);
		inParamMap.put("PTRUSTACCOUNTCODE", pTrustAccountCode);
		inParamMap.put("modifyUserId", userName);
		Integer returnArray = null;
		try {
			returnArray = namedParameterJdbcTemplate.update(sql, inParamMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"error in method updateOffenderSubAccounts : ", e);
		}
		if (returnArray != 0) {
			return returnArray;
		}
		return returnArray;
	}

	@Override
	public String lastNameFirstName(final Long offenderId, final String userName) {
		final String sql = getQuery("OTDCLOSE_FIRST_NAME_LAST_NAME");
		String returnObj = null;
		try {
			try {
				returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("ROOTOFFENDERID", offenderId, "USER_ID", userName),
						String.class);
			} catch (Exception e) {
				logger.error(this.getClass().getName()+"error in method lastNameFirstName : ", e);
			}
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * checkLock
	 *
	 * @param params
	 *
	 */
	public List<OffenderSubAccounts> balanceSubAccountCodeProp(final Long offenderId, final String caseloadId, final String userName) {
		final String sql = getQuery("OTDCLOSE_TRUST_ACCOUNT_CODE_BALANCE_PROP");
		final RowMapper<OffenderSubAccounts> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderSubAccounts.class, offenderSubAccountsMapping);
		List<OffenderSubAccounts> returnList = new ArrayList<>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("ROOTOFFENDERID", offenderId, "PCSLDID", caseloadId, "USER_ID", userName), columnRowMapper);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"error in method balanceSubAccountCodeProp : ", e);
		}
		if (returnList != null) {
			return returnList;
		}
		return returnList;
	}

	public Integer insertIntoOffenderTransaction(final Integer txnIdNextVal, final BigDecimal pTxnEntrySeq,
			final String caseloadId, final Long offenderId, final Long offenderBookId, final String lvTxnType,
			final String lvTxnEntryDesc, final Double pBalance, final Date transDate, final String pSuAaccountType) {
		Integer genSeq = 0;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("p_trans_number", txnIdNextVal);
		inParamMap.put("p_trans_seq", pTxnEntrySeq);
		inParamMap.put("p_csld_id", caseloadId);
		inParamMap.put("p_off_id", offenderId);
		inParamMap.put("p_off_book_id", offenderBookId);
		inParamMap.put("p_trans_post_type", "DR");
		inParamMap.put("p_trans_type", lvTxnType);
		inParamMap.put("p_trans_desc", lvTxnEntryDesc);
		inParamMap.put("p_trans_amount", pBalance);
		inParamMap.put("p_trans_date", transDate);
		inParamMap.put("p_sub_act_type", pSuAaccountType);
		inParamMap.put("p_deduction_flag", null);
		inParamMap.put("p_pre_ded_amount", null);
		inParamMap.put("p_deduction_type", null);
		inParamMap.put("p_payee_corp_id", null);
		inParamMap.put("p_payee_person_id", null);
		inParamMap.put("p_info_number", null);
		inParamMap.put("p_slip_print_flag", "N");
		inParamMap.put("p_allow_overdrawn", "N");
		try {
			namedParameterJdbcTemplate.update(
					" call OMS_OWNER.TRUST.INSERT_INTO_OFFENDER_TRANS(:p_trans_number, :p_trans_seq, :p_csld_id, :p_off_id, :p_off_book_id, :p_trans_post_type, "
							+ " :p_trans_type, :p_trans_desc, :p_trans_amount, :p_trans_date, :p_sub_act_type, :p_deduction_flag, :p_pre_ded_amount, :p_deduction_type, "
							+ " :p_payee_corp_id, :p_payee_person_id, :p_info_number, :p_slip_print_flag, :p_allow_overdrawn)",
					inParamMap);
			genSeq = 1;
		} catch (Exception e) {
			logger.error("insertIntoOffenderTransaction :" + e);
			return genSeq;
		}
		return genSeq;
	}

	public Integer updateOffenderTransactionsTransfer(final String lastNameFirstName, final Integer txnIdNextVal,
			final BigDecimal pTxnEntrySeq) {
		final String sql = getQuery("OTDCLOSE_UPDATE_OFFENDER_TRANSACTIONS_TRANSFER");
		Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("LVPAYEENAME", lastNameFirstName);
		inParamMap.put("PTXNNUM", txnIdNextVal);
		inParamMap.put("PTXNENTRYSEQ", pTxnEntrySeq);
		Integer returnArray = null;
		try {
			returnArray = namedParameterJdbcTemplate.update(sql, inParamMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"error in method updateOffenderTransactionsTransfer : ", e);
		}
		if (returnArray != 0) {
			return returnArray;
		}
		return returnArray;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOffTxnOffTxnRef
	 *
	 * @param params
	 *
	 */
	public String personIdLastfirstName(final Integer personId) {
		final String sql = getQuery("OTDCLOSE_P_PERSON_ID");
		String returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("PPERSONID", personId), String.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"error in method personIdLastfirstName : ", e);
		}
		if (returnObj != null) {
			return returnObj;
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOffTxnOffTxnRef
	 *
	 * @param params
	 *
	 */
	public String corporateIdLastfirstName(final Integer corporateId) {
		final String sql = getQuery("OTDCLOSE_P_CORPORATE_ID");
		String returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("PCORPORATEID", corporateId),
					String.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"error in method corporateIdLastfirstName : ", e);
		}
		if (returnObj != null) {
			return returnObj;
		}
		return returnObj;
	}

	public Integer insertIntoChequeData(final String caseloadId, final Integer txnIdTwo, final Double nbtTxnEntryAmount,
			final Integer pTxnNumber, final Integer payeePersonId, final Integer payeeCorporateId,
			final String payeeNameText, final Long offenderId, final String vTxnType) {
		Integer genSeq = 0;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("p_csld_id", caseloadId);
		inParamMap.put("p_trans_number", txnIdTwo);
		inParamMap.put("p_trans_amount", nbtTxnEntryAmount);
		inParamMap.put("p_cheque_flag", "O");
		inParamMap.put("p_start_txn_id", pTxnNumber);
		inParamMap.put("p_end_txn_id", txnIdTwo);
		inParamMap.put("p_pers_payee_id", payeePersonId);
		inParamMap.put("p_corp_payee_id", payeeCorporateId);
		inParamMap.put("p_payee_name", payeeNameText);
		inParamMap.put("p_offender_payee", offenderId);
		inParamMap.put("p_single_entry", "N");
		inParamMap.put("p_bank_act_code", null);
		inParamMap.put("p_module_name", "OTDCLOSE");
		inParamMap.put("p_trans_type", vTxnType);
		try {
			namedParameterJdbcTemplate.update(
					" call OMS_OWNER.TRUST.INSERT_INTO_CHEQUE_DATA(:p_csld_id, :p_trans_number, :p_trans_amount, :p_cheque_flag, :p_start_txn_id, :p_end_txn_id, "
							+ " :p_pers_payee_id, :p_corp_payee_id, :p_payee_name, :p_offender_payee, :p_single_entry, :p_bank_act_code, :p_module_name, :p_trans_type)",
					inParamMap);
			genSeq = 1;
		} catch (Exception e) {
			logger.error("insertIntoChequeData :" + e);
			return genSeq;
		}
		return genSeq;
	}

	public Integer updateOffenderTrustAccountsY(final Long offenderId, final String caseloadId) {
		final String sql = getQuery("OTDCLOSE_OFFENDER_TRUST_ACCOUNTS_Y");
		Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("LVROOTOFFENDERID", offenderId);
		inParamMap.put("CASELOADID", caseloadId);
		Integer returnArray = null;
		try {
			returnArray = namedParameterJdbcTemplate.update(sql, inParamMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"error in method updateOffenderTrustAccountsY : ", e);
		}
		if (returnArray != 0) {
			return returnArray;
		}
		return returnArray;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOffTxnOffTxnRef
	 *
	 * @param params
	 *
	 */
	public BigDecimal chkAccountClosedFlag(final Long offenderId, final String caseloadId,String userName) {
		final String sql = getQuery("OTDCLOSE_CHK_CLOSED_ACCOUNT");
		BigDecimal returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("POFFID", offenderId, "PCSLD", caseloadId,"USER_ID",userName), BigDecimal.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"error in method chkAccountClosedFlag : ", e);
		}
		if (returnObj != null) {
			return returnObj;
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOffTxnOffTxnRef
	 *
	 * @param params
	 *
	 */
	public BigDecimal chkSubAccountFlag(final Long offenderId, final String caseloadId) {
		final String sql = getQuery("OTDCLOSE_CHK_SUB_ACCOUNT");
		BigDecimal returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("POFFID", offenderId, "PCSLD", caseloadId), BigDecimal.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"error in method chkSubAccountFlag : ", e);
		}
		if (returnObj != null) {
			return returnObj;
		}
		return returnObj;
	}

	@Override
	public String freezDisbursement(final String caseloadId, final Long offenderId, final String pTxnType,
			final String caseloadType) {
		String openAnAccount = null;
		Map<String, Object> returnObject = null;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[10];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_CSLD_ID", OracleTypes.VARCHAR),
				new SqlParameter("P_OFF_ID", OracleTypes.NUMBER), new SqlParameter("P_TXN_TYPE", OracleTypes.VARCHAR),
				new SqlParameter("P_ACNT_CODE", OracleTypes.VARCHAR),
				new SqlParameter("P_CSLD_TYPE", OracleTypes.VARCHAR),
				new SqlParameter("P_MODULE_NAME", OracleTypes.VARCHAR), new SqlParameter("P_DATE", OracleTypes.DATE),
				new SqlOutParameter("FRZ_FLAG", OracleTypes.VARCHAR), };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TRUST").withProcedureName("CHK_FREEZE_DISBURSEMENTS")
				.declareParameters(sqlParameters);
		inParamMap.put("P_CSLD_ID", caseloadId);
		inParamMap.put("P_OFF_ID", offenderId);
		inParamMap.put("P_TXN_TYPE", pTxnType);
		inParamMap.put("P_ACNT_CODE", null);
		inParamMap.put("P_CSLD_TYPE", caseloadType);
		inParamMap.put("P_MODULE_NAME", "OTDCLOSE");
		inParamMap.put("P_DATE", null);
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			openAnAccount = String.valueOf(returnObject.get("FRZ_FLAG"));
		} catch (Exception e) {
			logger.error("freezDisbursement :" + e);
		}
		return openAnAccount;
	}

}
