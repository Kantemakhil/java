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
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlInOutParameter;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.GetOffenderSubBalanceBean;
import net.syscon.s4.common.beans.OtddisbuProcessTransactionsBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.TransactionTypes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.ChkFreezeDisbursements;
import net.syscon.s4.im.beans.Corporates;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.inmate.beans.OffenderDeductions;
import net.syscon.s4.inmate.trust.trustaccounts.OtddisbuRepository;
import net.syscon.s4.inst.booking.beans.Persons;
import oracle.jdbc.internal.OracleTypes;

/**
 * Class OtddisbuRepositoryImpl
 */
@Repository
public class OtddisbuRepositoryImpl extends RepositoryBase implements OtddisbuRepository {
	
	private static Logger logger = LogManager.getLogger(OtddisbuRepositoryImpl.class.getName());

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger log = LogManager.getLogger(OtddisbuRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> personsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PAYEE_PERSON_ID", 	new FieldMapper("payeePersonId"))
			.put("LAST_NAME", 			new FieldMapper("lastName"))
			.put("FIRST_NAME", 			new FieldMapper("firstName"))
			.build();
	private final Map<String, FieldMapper> transactionTypesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("LIST_SEQ", 			new FieldMapper("listSeq"))
			.put("DESCRIPTION", 		new FieldMapper("description"))
			.put("TXN_TYPE", 			new FieldMapper("txnType")).build();
	private final Map<String, FieldMapper> offendersMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_ID", 		new FieldMapper("offenderId"))
			.put("LAST_NAME", 			new FieldMapper("lastName"))
			.put("FIRST_NAME", 			new FieldMapper("firstName"))
			.build();
	private final Map<String, FieldMapper> systemProfilesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PROFILE_CODE", 		new FieldMapper("profileCode"))
			.put("PROFILE_TYPE", 		new FieldMapper("profileType"))
			.put("CREATE_USER_ID", 		new FieldMapper("createUserId"))
			.put("SEAL_FLAG", 			new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", 	new FieldMapper("createDatetime"))
			.put("MODIFY_USER_ID", 		new FieldMapper("modifyUserId"))
			.put("OLD_TABLE_NAME", 		new FieldMapper("oldTableName"))
			.put("PROFILE_VALUE", 		new FieldMapper("profileValue"))
			.put("MODIFY_DATETIME", 	new FieldMapper("modifyDatetime"))
			.put("PROFILE_VALUE_2", 	new FieldMapper("profileValue2"))
			.put("DESCRIPTION", 		new FieldMapper("description"))
			.build();
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DSP_DESCRIPTION", 	new FieldMapper("description"))
			.put("PERSON_ID", 			new FieldMapper("personId"))
			.put("TXN_TYPE", 			new FieldMapper("txnType"))
			.put("LAST_NAME", 			new FieldMapper("lastName"))
			.put("CODE", 			new FieldMapper("code"))
			.put("DESCRIPTION", 			new FieldMapper("description"))
			.build();
	private final Map<String, FieldMapper> offenderTransactionsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("TXN_REFERENCE_NUMBER", 		new FieldMapper("txnReferenceNumber"))
			.put("INFO_NUMBER", 		 		new FieldMapper("infoNumber"))
			.put("APPLY_SPENDING_LIMIT_AMOUNT", new FieldMapper("applySpendingLimitAmount"))
			.put("TXN_ADJUSTED_FLAG", 			new FieldMapper("txnAdjustedFlag"))
			.put("ADJUST_TXN_ENTRY_ID", 		new FieldMapper("adjustTxnEntryId"))
			.put("PAYEE_NAME_TEXT", 			new FieldMapper("payeeNameText"))
			.put("RECEIPT_PRINTED_FLAG", 		new FieldMapper("receiptPrintedFlag"))
			.put("OFFENDER_ID", 				new FieldMapper("offenderId"))
			.put("HOLD_CLEAR_FLAG", 			new FieldMapper("holdClearFlag"))
			.put("GROSS_NET_FLAG", 				new FieldMapper("grossNetFlag"))
			.put("SEAL_FLAG", 					new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", 			new FieldMapper("createDatetime"))
			.put("HOLD_NUMBER", 				new FieldMapper("holdNumber"))
			.put("TXN_TYPE", 					new FieldMapper("txnType"))
			.put("GROSS_AMOUNT", 				new FieldMapper("grossAmount"))
			.put("RECEIPT_PENDING_PRINT_FLAG",  new FieldMapper("receiptPendingPrintFlag"))
			.put("MODIFY_DATETIME", 			new FieldMapper("modifyDatetime"))
			.put("PAYEE_PERSON_ID", 			new FieldMapper("payeePersonId"))
			.put("ORG_TXN_TYPE", 				new FieldMapper("orgTxnType"))
			.put("TRANSFER_CASELOAD_ID", 		new FieldMapper("transferCaseloadId"))
			.put("TXN_ID", 						new FieldMapper("txnId"))
			.put("OFFENDER_BOOK_ID", 			new FieldMapper("offenderBookId"))
			.put("REMITTER_ID", 				new FieldMapper("remitterId"))
			.put("CREATE_USER_ID", 				new FieldMapper("createUserId"))
			.put("TXN_ENTRY_DATE", 				new FieldMapper("txnEntryDate"))
			.put("PAYEE_CODE", 					new FieldMapper("payeeCode"))
			.put("MODIFY_USER_ID", 				new FieldMapper("modifyUserId"))
			.put("TXN_ENTRY_AMOUNT", 			new FieldMapper("txnEntryAmount"))
			.put("ADJUST_OFFENDER_ID", 			new FieldMapper("adjustOffenderId"))
			.put("RECEIPT_NUMBER", 				new FieldMapper("receiptNumber"))
			.put("ADJUST_TXN_ID", 				new FieldMapper("adjustTxnId"))
			.put("CLOSING_CHEQUE_NUMBER", 		new FieldMapper("closingChequeNumber"))
			.put("HOLD_UNTIL_DATE", 			new FieldMapper("holdUntilDate"))
			.put("SLIP_PRINTED_FLAG", 			new FieldMapper("slipPrintedFlag"))
			.put("ADJUST_ACCOUNT_CODE", 		new FieldMapper("adjustAccountCode"))
			.put("CASELOAD_ID", 				new FieldMapper("caseloadId"))
			.put("MODIFY_DATE", 				new FieldMapper("modifyDate"))
			.put("DEDUCTION_FLAG", 				new FieldMapper("deductionFlag"))
			.put("TXN_ENTRY_SEQ", 				new FieldMapper("txnEntrySeq"))
			.put("DEDUCTION_TYPE", 				new FieldMapper("deductionType"))
			.put("REMITTER_NAME", 				new FieldMapper("remitterName"))
			.put("PAYEE_CORPORATE_ID", 			new FieldMapper("payeeCorporateId"))
			.put("SUB_ACCOUNT_TYPE", 			new FieldMapper("subAccountType"))
			.put("TXN_ENTRY_DESC", 				new FieldMapper("txnEntryDesc"))
			.put("PRE_WITHHOLD_AMOUNT", 		new FieldMapper("preWithholdAmount"))
			.put("TXN_POSTING_TYPE", 			new FieldMapper("txnPostingType"))
			.build();
	private final Map<String, FieldMapper> corporatesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CORPORATE_NAME", 				new FieldMapper("corporateName"))
			.put("PAYEE_CORPORATE_ID", 			new FieldMapper("payeeCorporateId"))
			.build();
	
	private final Map<String, FieldMapper> offenderSubActMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("P_CSLD_ID",		new FieldMapper("pCsldId"))
			.put("P_OFF_ID",		new FieldMapper("pOffId"))
			.put("P_SUB_ACT_TYPE",	new FieldMapper("pSubActType"))
			.put("P_AMOUNT",		new FieldMapper("pAmount"))
			.put("P_MINBAL",		new FieldMapper("pMinBal"))
			.put("P_INDDAYS",		new FieldMapper("pIndDays"))
			.put("P_INDDATE",		new FieldMapper("pIndDate"))
			.put("P_TRSTCODE",		new FieldMapper("pTrustCode"))
			.put("P_LOCK_FLAG",		new FieldMapper("pLockFlag"))
			.put("TXNTYPE",			new FieldMapper("txntype"))
			.put("MOD_NAME",		new FieldMapper("modName"))
			.put("P_SETUP_CSLD_ID",	new FieldMapper("pSetupCsldId"))
			.put("BALANCE", 		new FieldMapper("pAmount"))
			.put("MINIMUM_BALANCE",	new FieldMapper("pMinBal"))
			.put("IND_DAYS",		new FieldMapper("pIndDays"))
			.put("IND_DATE",		new FieldMapper("pInddate"))
			.build();
	
	private final Map<String, FieldMapper> deductiontMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DEDUCTION_TYPE",	new FieldMapper("deductionType"))
			.put("OFFENDER_DEDUCTION_ID",	new FieldMapper("offenderDeductionId"))
			.build();
	/**
	 * Creates new OtddisbuRepositoryImpl class Object
	 */
	public OtddisbuRepositoryImpl() {
		// OtddisbuRepositoryImpl
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
	public List<OffenderTransactions> offTxn1ExecuteQuery(final OffenderTransactions objSearchDao) {
		final String sql = getQuery("OTDDISBU_OFFTXN1_FIND_OFFENDER_TRANSACTIONS");
		final RowMapper<OffenderTransactions> OffenderTransactionsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderTransactions.class, offenderTransactionsMapping);
		final ArrayList<OffenderTransactions> returnList = (ArrayList<OffenderTransactions>) namedParameterJdbcTemplate
				.query(sql, createParams(), OffenderTransactionsRowMapper);
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
	 * @
	 */
	public List<OffenderTransactions> offTxnExecuteQuery(final OffenderTransactions objSearchDao) {
		final String sql = getQuery("OTDDISBU_OFFTXN1_FIND_OFFENDER_TRANSACTIONS");
		final RowMapper<OffenderTransactions> OffenderTransactionsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderTransactions.class, offenderTransactionsMapping);
		List<OffenderTransactions> returnList = new ArrayList<OffenderTransactions>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), OffenderTransactionsRowMapper);
		} catch (Exception e) {
			log.error("offTxnExecuteQuery", e);

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
	public Integer offtxn1InsertOffenderTransactions(final List<OffenderTransactions> lstOffenderTransactions) {
		int insertCount = 0;
		String sql = getQuery("OTDDISBU_OFFTXN1_INSERT_OFFENDER_TRANSACTIONS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		for (int i = 0; i < returnArray.length; i++) {
			insertCount = insertCount++;
		}
		if (lstOffenderTransactions.size() == insertCount) {
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
		final String sql = getQuery("OTDDISBU_SYSPFL_FIND_SYSTEM_PROFILES");
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
	public List<ReferenceCodes> cgfkOffTxn1PayeePersonIdRecordGroup() {
		final String sql = getQuery("OTDDISBU_FIND_CGFKOFFTXN1PAYEECORPORATE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (Exception e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<TransactionTypes> cgfkOffTxn1TxnTypeRecordGroup(final String caseloadId, final String caseloadType) {
		final String sql = getQuery("OTDDISBU_FIND_CGFKOFFTXN1TXNTYPE");
		final RowMapper<TransactionTypes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, TransactionTypes.class,
				mMapping);
		List<TransactionTypes> returnList = new ArrayList<TransactionTypes>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("CASELOADID", caseloadId, "CASELOADTYPE", caseloadType), mRowMapper);
		} catch (Exception e) {
			log.error("cgfkOffTxn1TxnTypeRecordGroup", e);
		}
		return returnList;
	}

	public String checkproductionFlag(final String caseloadId, final String txCode) {
		final String sql = getQuery("OTDDISBU_CHEQUE_PRODUCTION_FLAG");
		String checkFlag = "";
		try {
			checkFlag = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("CASELOADID", caseloadId, "TXN_TYPE", txCode), String.class);
		} catch (Exception e) {
			log.error("checkproductionFlag", e);
		}
		return checkFlag;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<MMCrp.corporateName>
	 */
	public List<ReferenceCodes> cgfkOffTxn1PayeeCorporateRecordGroup() {
		final String sql = getQuery("OTDDISBU_FIND_CGFKOFFTXN1PAYEECORPORATE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (Exception e) {
			return Collections.emptyList();
		}
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOffTxn1OffTxnPer
	 *
	 * @param params
	 *
	 */
	public Persons cgfkchkOffTxn1OffTxnPer(final Persons paramBean) {
		final String sql = getQuery("OTDDISBU_CGFKCHK_OFF_TXN1_OFF_TXN_PER_");
		final RowMapper<Persons> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Persons.class, personsMapping);
		Persons returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOffTxn1OffTxnCorp
	 *
	 * @param params
	 *
	 */
	public Corporates cgfkchkOffTxn1OffTxnCorp(final Corporates paramBean) {
		final String sql = getQuery("OTDDISBU_CGFKCHK_OFF_TXN1_OFF_TXN_CORP");
		final RowMapper<Corporates> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Corporates.class,
				corporatesMapping);
		Corporates returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOffTxn1OffTxnTxn
	 *
	 * @param params
	 *
	 */
	public TransactionTypes cgfkchkOffTxn1OffTxnTxn(final TransactionTypes paramBean) {
		final String sql = getQuery("OTDDISBU_CGFKCHK_OFF_TXN1_OFF_TXN_TXN_");
		final RowMapper<TransactionTypes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, TransactionTypes.class,
				transactionTypesMapping);
		TransactionTypes returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	// /**
	// * This method is execute a sql query when trigger event is called
	// *
	// * cgwhenNewFormInstance
	// *
	// * @param params
	// *
	// */
	// public List<Object> cgwhenNewFormInstance(SysDual paramBean) {
	// final String sql = getQuery("OTDDISBU_CGWHEN_NEW_FORM_INSTANCE");
	// final RowMapper<SysDual> columnRowMapper =
	// Row2BeanRowMapper.makeMapping(sql,SysDual.class, sysDualMapping);
	// final ArrayList<SysDual> returnList =(ArrayList<SysDual>)
	// namedParameterJdbcTemplate.query(sql, createParams(SysDual),
	// columnRowMapper);
	// return returnList;
	// }
	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOffTxnOffTxnOff
	 *
	 * @param params
	 *
	 */
	public Offenders cgfkchkOffTxnOffTxnOff(final Offenders paramBean) {
		final String sql = getQuery("OTDDISBU_CGFKCHK_OFF_TXN_OFF_TXN_OFF_N");
		final RowMapper<Offenders> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Offenders.class,
				offendersMapping);
		Offenders returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	@Override
	public Integer offTxn1InsertOffenderTransactions(final List<OffenderTransactions> lstOffenderTransactions) {
		// TODO Auto-generated method stub
		return null;
	}

	public Integer getDebitActCode(String txnType, String caseloadId) {
		final String sql = getQuery("GET_DEBIT_ACT_CODE");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql,
					createParams("txntype", txnType, "caseloadId", caseloadId), Integer.class);
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public GetOffenderSubBalanceBean getOffenderSubBalanceBeanFetBalOne(String getpCsldId, Long getpOffId,
			Integer getpTrstcode) {
		final String sql = getQuery("GET_OFFENDER_SUB_BALANCE_FET_BAL_ONE");
		GetOffenderSubBalanceBean bean = new GetOffenderSubBalanceBean();
		try {
			final RowMapper<GetOffenderSubBalanceBean> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
					GetOffenderSubBalanceBean.class, offenderSubActMapping);
			bean = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("caseloadId", getpCsldId, "offenderId", getpOffId, "trustAccountCode", getpTrstcode),
					columnRowMapper);
		} catch (Exception e) {
			logger.error("Error in getOffenderSubBalanceBeanFetBalOne : ", e);
		}
		return bean;
	}

	@Override
	public GetOffenderSubBalanceBean getOffenderSubBalanceBeanFetBalTwo(String getpCsldId, Long getpOffId,
			Integer getpTrstcode) {
		final String sql = getQuery("GET_OFFENDER_SUB_BALANCE_FET_BAL_TWO");
		GetOffenderSubBalanceBean bean = new GetOffenderSubBalanceBean();
		try {
			final RowMapper<GetOffenderSubBalanceBean> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
					GetOffenderSubBalanceBean.class, offenderSubActMapping);
			bean = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("caseloadId", getpCsldId, "offenderId", getpOffId, "trustAccountCode", getpTrstcode),
					columnRowMapper);
		} catch (Exception e) {
			logger.error("Error in getOffenderSubBalanceBeanFetBalTwo : ", e);
		}
		return bean;
	}

	public Integer getInDays(String getpCsldId, Long getpOffId, Integer getpTrstcode,String userId) {
		final String sql = getQuery("GET_IN_DAYS");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql,
					createParams("caseloadId", getpCsldId, "offenderId", getpOffId, "accountCode", getpTrstcode,"USERID",userId),
					Integer.class);
		} catch (Exception e) {
			logger.error("Error in getInDays : ", e);
			return null;
		}
	}

	public Integer getInDaysTwo(String getpCsldId, Integer getpTrstcode) {
		final String sql = getQuery("GET_IN_DAYS_TWO");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql,
					createParams("caseloadId", getpCsldId, "accountCode", getpTrstcode), Integer.class);
		} catch (Exception e) {
			logger.error("Error in getInDaysTwo : ", e);
			return null;
		}

	}

	public Integer getMinBal(String getpCsldId, Long getpOffId, Integer getpTrstcode,String userId) {
		final String sql = getQuery("GET_MIN_BAL");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql,
					createParams("caseloadId", getpCsldId, "offenderId", getpOffId, "accountCode", getpTrstcode,"USERID",userId),
					Integer.class);
		} catch (Exception e) {
			logger.error("Error in getMinBal : ", e);
			return 0;
		}
	}

	@Override
	public Long getOffenderBookId(Long offenderId,String userId) {
		final String sql = getQuery("GET_OFFENDER_BOOK_ID_A");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderId", offenderId,"USERID",userId), Long.class);
		} catch (Exception e) {
			logger.error("OtddisbuRepositoryImpl getOffenderBookId : offender not found", e);
		}
		return null;
	}

	@Override
	public String getTxnUsage(String txnType, String caseloadId,String userId) {
		final String sql = getQuery("GET_TXNUSAGE");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql,
					createParams("txnType", txnType, "caseloadId", caseloadId, "USER_ID", userId), String.class);
		} catch (Exception e) {
			logger.error("OtddisbuRepositoryImpl getOffenderBookId : offender not found", e);
		}
		return null;
	}

	@Override
	public Map<String, Object> getSubActType(String moduleName, String txnType, String caseloadId,String userId) {
		final String sql = getQuery("GET_SUB_ACT_TYPE");
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			result = namedParameterJdbcTemplate.queryForMap(sql,
					createParams("moduleName", moduleName, "txnType", txnType, "caseloadId", caseloadId,"USER_ID",userId));
		} catch (Exception e) {
			logger.error("OtddisbuRepositoryImpl getSubActType : sub Account Type not found ", e);
		}
		return result;

	}

	@Override
	public String getLowHighFlag() {
		SqlParameter[] sqlParameters = new SqlParameter[] { new SqlOutParameter("LOW_HIGH_FLAG", OracleTypes.VARCHAR) };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TRUST").withProcedureName("GET_LOW_HIGH_FLAG").declareParameters(sqlParameters);
		Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("LOW_HIGH_FLAG", "");
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		final Map<String, Object> returnObject = simpleJDBCCall.execute(inParameter);
		if (returnObject.get("LOW_HIGH_FLAG") != null) {
			return returnObject.get("LOW_HIGH_FLAG").toString();
		}
		return null;
	}

	@Override
	public List<OffenderDeductions> getTxnFeeType(Long offenderId, String caseloadId, String txnType) {
		final String sql = getQuery("GET_TXN_FEE_TYPE");
		try {
			final RowMapper<OffenderDeductions> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
					OffenderDeductions.class, deductiontMapping);
			return namedParameterJdbcTemplate.query(sql,
					createParams("offenderId", offenderId, "caseloadId", caseloadId, "txnType", txnType),
					columnRowMapper);
		} catch (Exception e) {
			logger.error("OtddisbuRepositoryImpl getSubActType : Txn_Fee not found ", e);
		}
		return Collections.emptyList();
	}

	@Override
	public Map<String, Object> trustGetTransactionFee(Long offenderId, String caseloadId, Long offenderDeductionId,
			String txnType, Double txnEntryAmount, String lowHighFlag) {
		SqlParameter[] sqlParameters = new SqlParameter[] { new SqlParameter("OFF_ID", OracleTypes.NUMBER),
				new SqlParameter("CSLD_ID", OracleTypes.VARCHAR), new SqlParameter("DED_ID", OracleTypes.NUMBER),
				new SqlParameter("DISBU_TYPE", OracleTypes.VARCHAR), new SqlParameter("TXN_AMOUNT", OracleTypes.NUMBER),
				new SqlParameter("LOW_HIGH_FLAG", OracleTypes.VARCHAR),
				new SqlOutParameter("TXN_FEE_AMT", OracleTypes.NUMBER) };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TRUST").withProcedureName("GET_TRANSACTION_FEE").declareParameters(sqlParameters);
		Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("OFF_ID", offenderId);
		inParamMap.put("CSLD_ID", caseloadId);
		inParamMap.put("DED_ID", offenderDeductionId);
		inParamMap.put("DISBU_TYPE", txnType);
		inParamMap.put("TXN_AMOUNT", txnEntryAmount);
		inParamMap.put("LOW_HIGH_FLAG", lowHighFlag);
		inParamMap.put("TXN_FEE_AMT", 0);
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		final Map<String, Object> returnObject = simpleJDBCCall.execute(inParameter);
		return returnObject;
	}

	@Override
	public Map<String, Object> getTxnCreditObligation(String txnType) {
		final String sql = getQuery("GET_TXN_CREDIT_OBLIGATION");
		Map<String, Object> result = new HashMap<>();
		try {
			return result = namedParameterJdbcTemplate.queryForMap(sql, createParams("txnType", txnType));
		} catch (Exception e) {
			logger.error("OtddisbuRepository getTxnCreditObligation obligation not found", e);
		}
		return null;
	}

	@Override
	public String getCrob(String obligationtype) {
		final String sql = getQuery("GET_CROB");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("obligationtype", obligationtype),
					String.class);
		} catch (Exception e) {
			logger.error("OtddisbuRepository getTxnCreditObligation No Obligation Found", e);
		}
		return null;
	}

	@Override
	public String getIndegentFlag(String caseloadId, String obligationtype) {
		final String sql = getQuery("GET_INDIGENT_FLAG");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql,
					createParams("caseloadId", caseloadId, "obligationtype", obligationtype), String.class);
		} catch (Exception e) {
			logger.error("OtddisbuRepository getTxnCreditObligation No Obligation Found", e);
		}
		return null;
	}

	@Override
	public Integer ctrWashSpecific() {
		final String sql = getQuery("CTR_WASH_SPECIFIC");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Integer.class);
		} catch (Exception e) {
			logger.error("OtddisbuRepository ctrWashSpecific No avaliable code", e);
		}
		return null;
	}

	@Override
	public Map<String, Object> getLimitAmountAndPeriodType(Long offenderId, String caseloadId, String obligationtype) {
		final String sql = getQuery("GET_LIMIT_AMOUNT_AND_PERIOD_TYPE");
		try {
			return namedParameterJdbcTemplate.queryForMap(sql,
					createParams("caseloadId", caseloadId, "offenderId", offenderId, "obligationtype", obligationtype));
		} catch (Exception e) {
			logger.error("OtddisbuRepository getLimitAmountAndPeriodType No Data for the Offender", e);
		}
		return null;
	}

	@Override
	public Map<String, Object> getLimitAmountAndPeriodTypeOne(String caseloadId, String obligationtype) {
		final String sql = getQuery("GET_LIMIT_AMOUNT_AND_PERIOD_TYPE_ONE");
		try {
			return namedParameterJdbcTemplate.queryForMap(sql,
					createParams("caseloadId", caseloadId, "obligationtype", obligationtype));
		} catch (Exception e) {
			logger.error("OtddisbuRepository getLimitAmountAndPeriodTypeOne No Data for the values", e);
		}
		return null;

	}

	@Override
	public Integer getWeekDay() {
		final String sql = getQuery("GET_WEEK_DAY");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Integer.class);
		} catch (Exception e) {
			logger.error("OtddisbuRepository getWeekDay No Data found", e);
		}
		return null;
	}

	@Override
	public Date getfromDate(Integer weekDay) {
		final String sql = getQuery("GET_FROM_DATE");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("weekDay", weekDay), Date.class);
		} catch (Exception e) {
			logger.error("OtddisbuRepository getfromDate No Data found", e);
		}
		return null;
	}

	@Override
	public Date getToDate(Integer weekDay) {
		final String sql = getQuery("GET_TO_DATE");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("weekDay", weekDay), Date.class);
		} catch (Exception e) {
			logger.error("OtddisbuRepository getToDate No Data found", e);
		}
		return null;
	}

	@Override
	public Date getfromDateOne() {
		final String sql = getQuery("GET_FROM_DATE_ONE");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Date.class);
		} catch (Exception e) {
			logger.error("OtddisbuRepository getfromDateOne No Data found", e);
		}
		return null;
	}

	@Override
	public Date getToDateOne() {
		final String sql = getQuery("GET_TO_DATE_ONE");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Date.class);
		} catch (Exception e) {
			logger.error("OtddisbuRepository getToDate No Data found", e);
		}
		return null;
	}

	@Override
	public BigDecimal getAmountWrittenOff(String caseloadId, Long offenderId, Date fromdate, Date todate,
			String obligationtype) {
		final String sql = getQuery("GET_AMOUNT_WRITTEN_OFF");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("caseloadId", caseloadId, "offenderId",
					offenderId, "fromdate", fromdate, "todate", todate, "obligationtype", obligationtype),
					BigDecimal.class);
		} catch (Exception e) {
			logger.error("OtddisbuRepository getAmountWrittenOff No Data found", e);
		}
		return null;
	}

	@Override
	public BigDecimal getTotalTaken(Long offenderId, String obligationtype, String caseloadId, Date fromdate,
			Date todate) {
		final String sql = getQuery("GET_TOTAL_TAKEN");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("caseloadId", caseloadId, "offenderId",
					offenderId, "fromdate", fromdate, "todate", todate, "obligationtype", obligationtype),
					BigDecimal.class);
		} catch (Exception e) {
			logger.error("OtddisbuRepository getTotalTaken No Data found", e);
		}
		return null;
	}

	@Override
	public BigDecimal getTotalReversed(Long offenderId, String obligationtype, String caseloadId, Date fromdate,
			Date todate) {
		final String sql = getQuery("GET_TOTAL_REVERSED");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("caseloadId", caseloadId, "offenderId",
					offenderId, "fromdate", fromdate, "todate", todate, "obligationtype", obligationtype),
					BigDecimal.class);
		} catch (Exception e) {
			logger.error("OtddisbuRepository getTotalReversed No Data found", e);
		}
		return null;
	}

	@Override
	public Integer genTrustTrans(String seqId) {
		try {
			final String sql = getQuery("GEN_TRUST_TRANS");
			if (sql != null) {
				final String preparedSql = sql.replace("#SEQ", seqId);
				return namedParameterJdbcTemplate.queryForObject(preparedSql, createParams(), Integer.class);
			}
		} catch (Exception e) {
			logger.error("OTDRDTFU_MAIN_PROCESS_TXN_ID_CUR", e);
		}
		return null;
	}

	@Override
	public Map<String, Object> lModuleFlagsCur(String caseloadId, String txnType) {
		final String sql = getQuery("L_MODULE_FLAGS_CUR");
		try {
			return namedParameterJdbcTemplate.queryForMap(sql,
					createParams("txnType", txnType, "caseloadId", caseloadId));
		} catch (Exception e) {
			logger.error("OtddisbuReposiotryImpl lModuleFlagsCur No Data Found", e);
		}
		return null;
	}

	@Override
	public Map<String, Object> otddisbuTranactionProcess(OtddisbuProcessTransactionsBean otddissbutProcTran) {
		SqlParameter[] sqlParameters = new SqlParameter[] { new SqlParameter("P_CASELOAD_ID", OracleTypes.VARCHAR),
				new SqlParameter("P_TXN_TYPE", OracleTypes.VARCHAR),
				new SqlParameter("P_TXN_DESC", OracleTypes.VARCHAR),
				new SqlParameter("P_TXN_USAGE", OracleTypes.VARCHAR), new SqlParameter("P_TXN_ID", OracleTypes.NUMBER),
				new SqlParameter("P_OFFENDER_ID", OracleTypes.NUMBER),
				new SqlParameter("P_TXN_ENTRY_AMOUNT", OracleTypes.NUMBER),
				new SqlParameter("P_PAYEE_PERSON_ID", OracleTypes.NUMBER),
				new SqlParameter("P_PAYEE_CORP_ID", OracleTypes.NUMBER),
				new SqlParameter("P_PAYEE_NAME_TEXT", OracleTypes.VARCHAR),
				new SqlParameter("P_RECEIPT_FLAG", OracleTypes.VARCHAR),
				new SqlParameter("P_TXN_REF_NUMBER", OracleTypes.VARCHAR),
				new SqlParameter("P_CHEQUE_PROD_FLAG", OracleTypes.VARCHAR),
				new SqlInOutParameter("P_TXN_ENTRY_SEQ", OracleTypes.NUMBER),
				new SqlInOutParameter("P_ERROR_MESSAGE", OracleTypes.VARCHAR),
				new SqlParameter("P_TXN_FEE", OracleTypes.NUMBER),
				new SqlParameter("P_RECEIPT_NUM", OracleTypes.VARCHAR), };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("OTDDISBU").withProcedureName("PROCESS_TRANSACTIONS").declareParameters(sqlParameters);
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("P_CASELOAD_ID", otddissbutProcTran.getpCaseloadId());
		inParamMap.put("P_TXN_TYPE", otddissbutProcTran.getpTxnType());
		inParamMap.put("P_TXN_DESC", otddissbutProcTran.getpTxnDesc());
		inParamMap.put("P_TXN_USAGE", otddissbutProcTran.getpTxnUsage());
		inParamMap.put("P_TXN_ID", otddissbutProcTran.getpTxnId());
		inParamMap.put("P_OFFENDER_ID", otddissbutProcTran.getpOffenderId());
		inParamMap.put("P_TXN_ENTRY_AMOUNT", otddissbutProcTran.getpTxnEntryAmount());
		inParamMap.put("P_PAYEE_PERSON_ID", otddissbutProcTran.getpPayeePersonId());
		inParamMap.put("P_PAYEE_CORP_ID", otddissbutProcTran.getpPayeeCorpId());
		inParamMap.put("P_PAYEE_NAME_TEXT", otddissbutProcTran.getpPayeeNameText());
		inParamMap.put("P_RECEIPT_FLAG", otddissbutProcTran.getpReceiptFlag());
		inParamMap.put("P_TXN_REF_NUMBER", otddissbutProcTran.getpTxnRefNumber());
		inParamMap.put("P_CHEQUE_PROD_FLAG", otddissbutProcTran.getpChequeProdFlag());
		inParamMap.put("P_TXN_ENTRY_SEQ", otddissbutProcTran.getpTxnEntrySeq());
		inParamMap.put("P_TXN_FEE", otddissbutProcTran.getpTxnFee());
		inParamMap.put("P_RECEIPT_NUM", otddissbutProcTran.getpReceiptNum());
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		final Map<String, Object> returnObject = simpleJDBCCall.execute(inParameter);
		return returnObject;
	}

	@Override
	public String getVProValue() {
		final String sql = getQuery("GET_V_PRO_VALUE");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);
		} catch (Exception e) {
			logger.error("OtddisbuRepositoryImpl getVProValue Data not found", e);
		}
		return "N";
	}

	@Override
	public ChkFreezeDisbursements chkDisbursementFreeze(ChkFreezeDisbursements chkFreezeDisbursements) {
		SqlParameter[] sqlParameters = new SqlParameter[] {
				new SqlParameter("p_csld_id", OracleTypes.VARCHAR),
				new SqlParameter("p_off_id", OracleTypes.NUMBER),
				new SqlParameter("p_txn_type", OracleTypes.VARCHAR),
				new SqlParameter("p_acnt_code", OracleTypes.NUMBER),
				new SqlInOutParameter("p_csld_type", OracleTypes.VARCHAR),
				new SqlInOutParameter("P_MODULE_NAME", OracleTypes.VARCHAR),
				new SqlOutParameter("p_date", OracleTypes.DATE),
				new SqlOutParameter("frz_flag", OracleTypes.VARCHAR),
				
		};
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TRUST").withProcedureName("CHK_FREEZE_DISBURSEMENTS").declareParameters(sqlParameters);
		Map<String, Object> inParamMap = new HashMap<String, Object>();
				inParamMap.put("p_csld_id", chkFreezeDisbursements.getpCsldId());
				inParamMap.put("p_off_id", chkFreezeDisbursements.getpOffId());
				inParamMap.put("p_txn_type", chkFreezeDisbursements.getpTxnType());
				inParamMap.put("p_acnt_code", null);
				inParamMap.put("p_csld_type", chkFreezeDisbursements.getpCsldType());
				
				inParamMap.put("p_date", null);
				inParamMap.put("P_MODULE_NAME", chkFreezeDisbursements.getpModuleName());
				
				
				final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
				final Map<String, Object> returnObject = simpleJDBCCall.execute(inParameter);
				final ChkFreezeDisbursements returnObj = chkFreezeDisbursements;
				if (returnObject.get("frz_flag") != null) {
					returnObj.setFrzFlag(returnObject.get("frz_flag").toString());
				}
		return returnObj;
	}

}
