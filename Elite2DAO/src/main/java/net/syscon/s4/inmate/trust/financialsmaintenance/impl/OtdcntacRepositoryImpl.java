package net.syscon.s4.inmate.trust.financialsmaintenance.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.inmate.beans.OffenderDeductions;
import net.syscon.s4.inmate.beans.OffenderSubAccounts;
import net.syscon.s4.inmate.trust.financialsmaintenance.OtdcntacRepository;
import oracle.jdbc.OracleTypes;

/**
 * Class OtdcntacRepositoryImpl
 */
@Repository
public class OtdcntacRepositoryImpl extends RepositoryBase implements OtdcntacRepository {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OtdcntacRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> systemProfilesMapping = new ImmutableMap.Builder<String, FieldMapper>()
				.put("PROFILE_CODE", 						new FieldMapper("profileCode"))
				.put("PROFILE_TYPE", 						new FieldMapper("profileType"))
				.put("CREATE_USER_ID", 						new FieldMapper("createUserId"))
				.put("SEAL_FLAG", 							new FieldMapper("sealFlag"))
				.put("CREATE_DATETIME", 					new FieldMapper("createDatetime"))
				.put("MODIFY_USER_ID", 						new FieldMapper("modifyUserId"))
				.put("OLD_TABLE_NAME", 						new FieldMapper("oldTableName"))
				.put("PROFILE_VALUE", 						new FieldMapper("profileValue"))
				.put("MODIFY_DATETIME", 					new FieldMapper("modifyDatetime"))
				.put("PROFILE_VALUE_2", 					new FieldMapper("profileValue2"))
				.put("DESCRIPTION", 						new FieldMapper("description"))
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
				.put("CREATE_DATETIME", 					new FieldMapper("createDateTime"))
				.put("HOLD_NUMBER", 						new FieldMapper("holdNumber"))
				.put("TXN_TYPE", 						    new FieldMapper("txnType"))
				.put("GROSS_AMOUNT", 						new FieldMapper("grossAmount"))
				.put("RECEIPT_PENDING_PRINT_FLAG", 			new FieldMapper("receiptPendingPrintFlag"))
				.put("MODIFY_DATETIME", 					new FieldMapper("modifyDateTime"))
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
	 * Creates new OtdcntacRepositoryImpl class Object
	 */
	public OtdcntacRepositoryImpl() {
		// OtdcntacRepositoryImpl
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
		final String sql = getQuery("OTDCNTAC_OFFTXN_FIND_OFFENDER_TRANSACTIONS");
		final RowMapper<OffenderTransactions> OffenderTransactionsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderTransactions.class, offenderTransactionsMapping);
		List<OffenderTransactions> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(sql, createParams(), OffenderTransactionsRowMapper);
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
	 * @param lstOffenderTransactions
	 *            List<OffenderTransactions>
	 *
	 * @return List<Integer>
	 *
	 * @
	 */
	public Integer offTxnInsertOffenderTransactions(final List<OffenderTransactions> lstOffenderTransactions) {
		final String sql = getQuery("OTDCNTAC_OFFTXN_INSERT_OFFENDER_TRANSACTIONS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderTransactions list : lstOffenderTransactions) {
			parameters.add(new BeanPropertySqlParameterSource(list));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenderTransactions.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

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
	public Integer offTxnInsertOffenderTrustAccounts(final OffenderTransactions lstOffenderTransactions) {
		final String sql = getQuery("OTDCNTAC_OFFENDER_TRUST_ACCOUNTS");
		Integer returnArray = null;
		returnArray = namedParameterJdbcTemplate.update(sql,
				new BeanPropertySqlParameterSource(lstOffenderTransactions));
		if (returnArray != 0) {
			return returnArray;
		}
		return returnArray;

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
	public Integer offTxnInsertOffenderSubAccounts(final OffenderSubAccounts lstOffenderTransactions) {
		final String sql = getQuery("OTDCNTAC_OFFENDER_SUB_ACCOUNTS");
		Integer returnArray = null;
		returnArray = namedParameterJdbcTemplate.update(sql,
				new BeanPropertySqlParameterSource(lstOffenderTransactions));
		if (returnArray != 0) {
			return returnArray;
		}
		return returnArray;
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
		final String sql = getQuery("OTDCNTAC_SYSPFL_FIND_SYSTEM_PROFILES");
		final RowMapper<SystemProfiles> SystemProfilesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				SystemProfiles.class, systemProfilesMapping);
		List<SystemProfiles> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(sql, createParams(), SystemProfilesRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 *
	 * @return OffenderTransactions
	 *
	 */
	public OffenderTransactions initPostTranTypesTxnType(final String user) {
		final String sql = getQuery("OTDCNTAC_INIT_POST_TRAN_TYPES_TXN_TYPE");
		final RowMapper<OffenderTransactions> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderTransactions.class, offenderTransactionsMapping);
		OffenderTransactions returnList = null;
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("USER", user), columnRowMapper);
		} catch (Exception e) {
			logger.error("initPostTranTypesTxnType :" + e);
			logger.error("initPostTranTypesTxnType Error message " + e.getMessage());
			return returnList;
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 *
	 * @return OffenderTransactions
	 * @param txnType
	 *
	 */
	public OffenderTransactions initPostTranTypesAccountType(final String txnType, final String user) {
		final String sql = getQuery("OTDCNTAC_INIT_POST_TRAN_TYPES_SUB_ACCOUNT_TYPE");
		final RowMapper<OffenderTransactions> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderTransactions.class, offenderTransactionsMapping);
		OffenderTransactions returnList = null;
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("TXNTYPE", txnType,"USER", user),
					columnRowMapper);
		} catch (Exception e) {
			logger.error("initPostTranTypesAccountType :" + e);
			logger.error("initPostTranTypesAccountType Error message " + e.getMessage());
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
		final String sql = getQuery("OTDCNTAC_TXN_ID");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Integer.class);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 *
	 * @return Integer
	 * @param txnId
	 *
	 */
	public Integer txnEntrySeqNextValData(final Integer txnId) {
		final String sql = getQuery("OTDCNTAC_TXN_ENTRY_SEQ");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("TXNID", txnId), Integer.class);
	}

	/**
	 * This method will update the transaction in the table
	 * 
	 * @param offTrans
	 * @return String
	 */
	public String checkAccountSatus(final OffenderTransactions offTrans) {
		String openAnAccount = null;
		Map<String, Object> returnObject = null;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[10];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_CSLD_ID", OracleTypes.VARCHAR),
				new SqlParameter("P_OFFENDER_ID", OracleTypes.NUMBER),
				new SqlOutParameter("P_OPEN_AN_ACCOUNT", OracleTypes.VARCHAR), };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TRUST").withProcedureName("CHK_ACCOUNT_STATUS").declareParameters(sqlParameters);
		inParamMap.put("P_CSLD_ID", offTrans.getCaseloadId());
		inParamMap.put("P_OFFENDER_ID", offTrans.getOffenderId());
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			openAnAccount = String.valueOf(returnObject.get("P_OPEN_AN_ACCOUNT"));
		} catch (Exception e) {
			logger.error("checkAccountSatus :" + e);
			logger.error("checkAccountSatus Error message " + e.getMessage());
		}
		return openAnAccount;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 *
	 * @return OffenderTransactions
	 *
	 */
	public OffenderTransactions drAccountCodeCrAccountCode(final String user) {
		final String sql = getQuery("OTDCNTAC_DR_ACCOUNT_CODE_CR_ACCOUNT_CODE");
		final RowMapper<OffenderTransactions> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderTransactions.class, offenderTransactionsMapping);
		OffenderTransactions returnList = null;
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("USER", user), columnRowMapper);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("drAccountCodeCrAccountCode :" + e);
			logger.error("drAccountCodeCrAccountCode Error message " + e.getMessage());
			return returnList;
		}
		return returnList;
	}

	/**
	 * This method will update the transaction in the table
	 * 
	 * @return String
	 */
	public String getGroupPrivilege() {
		String openAnAccount = null;
		Map<String, Object> returnObject = null;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[10];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_MODULE_NAME", OracleTypes.VARCHAR),
				new SqlOutParameter("RETURN_VALUE", OracleTypes.VARCHAR), };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_SECURITY").withFunctionName("GET_GROUP_PRIVILEGE")
				.declareParameters(sqlParameters);
		inParamMap.put("P_MODULE_NAME", "OTDCNTAC");
		inParamMap.put("RETURN_VALUE", null);
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			openAnAccount = String.valueOf(returnObject.get("RETURN_VALUE"));
		} catch (Exception e) {
			logger.error("getGroupPrivilege :" + e);
			logger.error("getGroupPrivilege Error message " + e.getMessage());
		}
		return openAnAccount;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 *
	 * @return OffenderTransactions
	 * @param txnType
	 *
	 */
	public String transactionOperationsFlag(final OffenderTransactions objSearchDao) {
		final String sql = getQuery("OTDCNTAC_TRANSACTION_OPERATIONS_Y");
		String returnList = null;
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("PSUBACTTYPE",
					objSearchDao.getSubAccountType(), "PTXNTYPE", objSearchDao.getTxnType(),"USER", objSearchDao.getCreateUserId()), String.class);
		} catch (Exception e) {
			logger.error("transactionOperationsFlag :" + e);
			logger.error("transactionOperationsFlag Error message " + e.getMessage());
			return returnList;
		}
		return returnList;
	}

	/**
	 * This method will update the transaction in the table
	 * 
	 * @param offTrans
	 * @return String
	 */
	public Integer genTrustRcptNmbr(String seqId) {
		try {
			final String sql = getQuery("OTDCNTAC_RECEIPT_NUMBER");
			if (sql != null) {
				final String preparedSql = sql.replace("#SEQ", seqId);
				return namedParameterJdbcTemplate.queryForObject(preparedSql, createParams(), Integer.class);
			}
		} catch (Exception e) {
			logger.error("genTrustRcptNmbr", e);
		}
		return null;
	}

	/**
	 * This method will update the transaction in the table
	 * 
	 * @param offTrans
	 * @return Integer
	 */
	@Override
	public Integer processGlTransNew(final OffenderTransactions offTrans) {
		Integer genSeq = 0;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("P_CSLD_ID", offTrans.getCaseloadId());
		inParamMap.put("P_TRANS_TYPE", offTrans.getTxnType());
		inParamMap.put("P_OPERATION_TYPE", null);
		inParamMap.put("P_TRANS_AMOUNT", offTrans.getTxnEntryAmount());
		inParamMap.put("P_TRANS_NUMBER", offTrans.getTxnId());
		inParamMap.put("P_TRANS_DATE", offTrans.getTxnEntryDate());
		inParamMap.put("P_TRANS_DESC", offTrans.getTxnEntryDesc());
		inParamMap.put("P_TRANS_SEQ", offTrans.getTxnEntrySeq());
		inParamMap.put("P_MODULE_NAME", "OTDCNTAC");
		inParamMap.put("P_OFF_ID", offTrans.getOffenderId());
		inParamMap.put("P_OFF_BOOK_ID", offTrans.getOffenderBookId());
		inParamMap.put("P_SUB_ACT_TYPE_DR", null);
		inParamMap.put("P_SUB_ACT_TYPE_CR", offTrans.getSubAccountType());
		inParamMap.put("P_PAYEE_PERS_ID", null);
		inParamMap.put("P_PAYEE_CORP_ID", null);
		inParamMap.put("P_PAYEE_NAME_TEXT", "");
		inParamMap.put("P_GL_SQNC", 0);
		inParamMap.put("P_OFF_DED_ID", null);
		namedParameterJdbcTemplate
				.update("call OMS_OWNER.TRUST.PROCESS_GL_TRANS_NEW(:P_CSLD_ID, :P_TRANS_TYPE, :P_OPERATION_TYPE, :P_TRANS_AMOUNT, :P_TRANS_NUMBER, :P_TRANS_DATE, :P_TRANS_DESC,"
						+ " :P_TRANS_SEQ, :P_MODULE_NAME, :P_OFF_ID, :P_OFF_BOOK_ID, :P_SUB_ACT_TYPE_DR, :P_SUB_ACT_TYPE_CR, :P_PAYEE_PERS_ID, :P_PAYEE_CORP_ID, :P_PAYEE_NAME_TEXT,"
						+ " :P_GL_SQNC, :P_OFF_DED_ID)", inParamMap);
		genSeq = 2;
		return genSeq;
	}

	public BigDecimal accountCodeTemp(final String subActType, final String user) {
		final String sql = getQuery("OTDCNTAC_ACCOUNT_CODE");
		BigDecimal resultData = null;
		try {
			resultData = namedParameterJdbcTemplate.queryForObject(sql, createParams("PSUBACTTYPE", subActType,"USER", user),
					BigDecimal.class);
		} catch (Exception e) {
			logger.error("accountCodeTemp :" + e);
			logger.error("accountCodeTemp Error message " + e.getMessage());
			return resultData;
		}
		return resultData;
	}

	public String deductionTypesProcedure(final OffenderTransactions paramBean) {
		String openAnAccount = null;
		Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("p_csld_id", paramBean.getCaseloadId());
		inParamMap.put("p_off_id", paramBean.getOffenderId());
		try {
			namedParameterJdbcTemplate
					.update("call OMS_OWNER.DEDUCTIONS.CREATE_DEFAULT_DEDUCTIONS(:p_csld_id, :p_off_id)", inParamMap);
			openAnAccount = "TEST";
		} catch (Exception e) {
			logger.error("deductionTypesProcedure :" + e);
			logger.error("deductionTypesProcedure Error message " + e.getMessage());
		}
		return openAnAccount;
	}

	public List<OffenderDeductions> deductionTypeTemp(final Long offenderId) {
		final String sql = getQuery("OTDCNTAC_DEDUCTION_TYPE");
		List<OffenderDeductions> resultData = new ArrayList<>();
		final RowMapper<OffenderDeductions> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderDeductions.class, offenderTransactionsMapping);
		try {
			resultData = namedParameterJdbcTemplate.query(sql, createParams("POFFID", offenderId), columnRowMapper);
		} catch (Exception e) {
			logger.error("deductionTypeTemp :" + e);
			logger.error("deductionTypeTemp Error message " + e.getMessage());
			return resultData;
		}
		return resultData;
	}

	public String obligationGroups() {
		final String sql = getQuery("OTDCNTAC_ALL_TABLES");
		String resultData = null;
		try {
			resultData = namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);
		} catch (Exception e) {
			logger.error("obligationGroups :" + e);
			logger.error("obligationGroups Error message " + e.getMessage());
			return resultData;
		}
		return resultData;
	}

	public String caseloadCodeTemp(final String deductionType) {
		final String sql = getQuery("OTDCNTAC_CASELOAD_CODE");
		String resultData = null;
		try {
			resultData = namedParameterJdbcTemplate.queryForObject(sql, createParams("DEDUCTIONTYPE", deductionType),
					String.class);
		} catch (Exception e) {
			logger.error("caseloadCodeTemp :" + e);
			logger.error("caseloadCodeTemp Error message " + e.getMessage());
			return resultData;
		}
		return resultData;
	}

	public Integer groupIdTemp(final String deductionType) {
		final String sql = getQuery("OTDCNTAC_GROUP_ID");
		Integer resultData = 0;
		try {
			resultData = namedParameterJdbcTemplate.queryForObject(sql, createParams("DEDUCTIONTYPE", deductionType),
					Integer.class);
		} catch (Exception e) {
			logger.error("groupIdTemp :" + e);
			logger.error("groupIdTemp Error message " + e.getMessage());
			return resultData;
		}
		return resultData;
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
	public Integer offDedInsertOffenderDeductions(final OffenderTransactions lstOffenderTransactions) {
		final String sql = getQuery("OTDCNTAC_UPDATE_OFFENDER_DEDUCTIONS");
		Integer returnArray = null;
		try {
		returnArray = namedParameterJdbcTemplate.update(sql,
				new BeanPropertySqlParameterSource(lstOffenderTransactions));
		if (returnArray != 0) {
			return returnArray;
		}
		} catch(Exception e) {
			logger.error("offDedInsertOffenderDeductions :" + e);
			logger.error("offDedInsertOffenderDeductions Error message " + e.getMessage());
		}
		return returnArray;
	}

	public void updateOffenderBalance(final OffenderTransactions paramBean) {
		SqlParameter[] sqlParameters = new SqlParameter[] { new SqlParameter("p_csld_id", OracleTypes.VARCHAR),
				new SqlParameter("p_off_id", OracleTypes.NUMBER),
				new SqlParameter("p_trans_post_type", OracleTypes.VARCHAR),
				new SqlParameter("p_trans_date", OracleTypes.DATE),
				new SqlParameter("p_trans_number", OracleTypes.NUMBER),
				new SqlParameter("p_trans_type", OracleTypes.VARCHAR),
				new SqlParameter("p_trans_amount", OracleTypes.NUMBER),
				new SqlParameter("p_sub_act_type", OracleTypes.VARCHAR),
				new SqlParameter("p_allow_overdrawn", OracleTypes.VARCHAR), };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TRUST").withProcedureName("UPDATE_OFFENDER_BALANCE").declareParameters(sqlParameters);
		Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("p_csld_id", paramBean.getCaseloadId());
		inParamMap.put("p_off_id", paramBean.getOffenderId());
		inParamMap.put("p_trans_post_type", paramBean.getTxnPostingType());
		inParamMap.put("p_trans_date", paramBean.getTxnEntryDate());
		inParamMap.put("p_trans_number", paramBean.getTxnId());
		inParamMap.put("p_trans_type", paramBean.getTxnType());
		inParamMap.put("p_trans_amount", paramBean.getTxnEntryAmount());
		inParamMap.put("p_sub_act_type", paramBean.getSubAccountType());
		inParamMap.put("p_allow_overdrawn", "N");
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		simpleJDBCCall.execute(inParameter);
	}

	public String getAcAndSetIndDate(final OffenderTransactions paramBean) {
		String openAnAccount = null;
		Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("p_csld_id", paramBean.getCaseloadId());
		inParamMap.put("p_off_id", paramBean.getOffenderId());
		try {
			namedParameterJdbcTemplate
					.update("call OMS_OWNER.DEDUCTIONS.GET_AC_AND_SET_IND_DATE(:p_off_id, :p_csld_id)", inParamMap);
			openAnAccount = "GETINDDATE";
		} catch (Exception e) {
			logger.error("getAcAndSetIndDate :" + e);
			logger.error("getAcAndSetIndDate Error message " + e.getMessage());
		}
		return openAnAccount;
	}

	@Override
	public OffenderDeductions gettingOldRecordOffendderDeduction(Long offenderId, String deductionType) {
		final String sql = getQuery("OTDCNTAC_GETTING_OLD_OFFENDER_DEDUCTIONS");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderId", offenderId,"deductionType",deductionType), new BeanPropertyRowMapper<OffenderDeductions>(OffenderDeductions.class));
	}

}
