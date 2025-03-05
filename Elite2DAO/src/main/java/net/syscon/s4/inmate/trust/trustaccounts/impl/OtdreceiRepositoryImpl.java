package net.syscon.s4.inmate.trust.trustaccounts.impl;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlInOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.ProcessGlTransNewBean;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.TransactionTypes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.im.beans.SysDual;
import net.syscon.s4.inmate.trust.trustaccounts.OtdreceiRepository;
import oracle.jdbc.internal.OracleTypes;


@Repository
public class OtdreceiRepositoryImpl extends RepositoryBase implements OtdreceiRepository{
	
	private static Logger logger = LogManager.getLogger(OtdreceiRepositoryImpl.class.getName());

 private final Map<String, FieldMapper> transactionTypesMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("LIST_SEQ", 							new FieldMapper("listSeq"))
.put("DESCRIPTION", 						new FieldMapper("description"))
.put("CASELOAD_ID", 						new FieldMapper("caseloadId"))
.put("TXN_TYPE", 							new FieldMapper("txnType"))
.put("DAYS", 								new FieldMapper("days"))
.put("TXN_USAGE", 							new FieldMapper("txnUsage"))
.put("sub_account_type", 					new FieldMapper("subAccountType"))
.put("txn_posting_type", 					new FieldMapper("txnPostingType"))
.build();
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
private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("DESCRIPTION", 				new FieldMapper("description"))
.put("TXN_TYPE", 					new FieldMapper("txnType"))
.put("CODE", 						new FieldMapper("code"))
.put("LIST_SEQ", 					new FieldMapper("listSeq"))
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
.put("SEAL_FLAG", 							new FieldMapper("sealFlag"))
.put("CREATE_DATETIME", 					new FieldMapper("createDatetime"))
.put("HOLD_NUMBER", 						new FieldMapper("holdNumber"))
.put("TXN_TYPE", 							new FieldMapper("txnType"))
.put("GROSS_AMOUNT", 						new FieldMapper("grossAmount"))
.put("RECEIPT_PENDING_PRINT_FLAG", 			new FieldMapper("receiptPendingPrintFlag"))
.put("MODIFY_DATETIME", 					new FieldMapper("modifyDatetime"))
.put("PAYEE_PERSON_ID", 					new FieldMapper("payeePersonId"))
.put("ORG_TXN_TYPE", 						new FieldMapper("orgTxnType"))
.put("TRANSFER_CASELOAD_ID", 				new FieldMapper("transferCaseloadId"))
.put("TXN_ID", 								new FieldMapper("txnId"))
.put("OFFENDER_BOOK_ID", 					new FieldMapper("offenderBookId"))
.put("REMITTER_ID", 						new FieldMapper("remitterId"))
.put("CREATE_USER_ID", 						new FieldMapper("createUserId"))
.put("TXN_ENTRY_DATE", 						new FieldMapper("txnEntryDate"))
.put("PAYEE_CODE", 							new FieldMapper("payeeCode"))
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
	 * Creates new OtdreceiRepositoryImpl class Object
	 */
	public OtdreceiRepositoryImpl() {
		// OtdreceiRepositoryImpl
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            OffenderTransactions
	 *
	 * @return List<OffenderTransactions>
	 *
	 * @throws SQLException
	 */
	public List<OffenderTransactions> offTxn1ExecuteQuery(OffenderTransactions objSearchDao) {
		final String sql = getQuery("OTDRECEI_OFFTXN1_FIND_OFFENDER_TRANSACTIONS");
		final RowMapper<OffenderTransactions> OffenderTransactionsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderTransactions.class, offenderTransactionsMapping);
		final ArrayList<OffenderTransactions> returnList = (ArrayList<OffenderTransactions>) namedParameterJdbcTemplate
				.query(sql, createParams(), OffenderTransactionsRowMapper);
		return returnList;
	}

	/**
	 * @param
	 *
	 * @throws SQLException
	 *
	 */
	public int PRE_INSERT() {
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
	 * @throws SQLException
	 */
	public Integer offTxn1InsertOffenderTransactions(final List<OffenderTransactions> lstOffenderTransactions) {
		int insertCount = 0;
		String sql = getQuery("OTDRECEI_OFFTXN1_INSERT_OFFENDER_TRANSACTIONS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"Error in deductionsChkOffenderDeductions : ", e);
		}

		if (lstOffenderTransactions.size() == returnArray.length) {
			return insertCount;
		} else {
			return insertCount;
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
	 * @throws SQLException
	 */
	public List<SystemProfiles> sysPflExecuteQuery(SystemProfiles objSearchDao) {
		final String sql = getQuery("OTDRECEI_SYSPFL_FIND_SYSTEM_PROFILES");
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
	public List<TransactionTypes> cgfkOffTxn1TxnTypeRecordGroup(final String caseloadId) {
		final String sql = getQuery("OTDRECEI_FIND_CGFKOFFTXN1TXNTYPE");
		final RowMapper<TransactionTypes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, TransactionTypes.class,
				mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams("caseloadId", caseloadId), mRowMapper);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"Error in deductionsChkOffenderDeductions : ", e);
			return Collections.emptyList();
		}
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offTxn1WhenValidateRecordWHEN-VALIDATE-RECORD
	 *
	 * @param params
	 *
	 */
	public List<AccountCodes> offTxn1WhenValidateRecordWhenValidateRecord(AccountCodes paramBean) {
		final String sql = getQuery("OTDRECEI_OFF_TXN1_WHENVALIDATERECORD_WHENVALIDATERECORD");
		final RowMapper<AccountCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, AccountCodes.class,
				mMapping);
		final ArrayList<AccountCodes> returnList = (ArrayList<AccountCodes>) namedParameterJdbcTemplate.query(sql,
				createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOffTxn1OffTxnTxn
	 *
	 * @param params
	 *
	 */
	public TransactionTypes cgfkchkOffTxn1OffTxnTxn(TransactionTypes paramBean) {
		final String sql = getQuery("OTDRECEI_CGFKCHK_OFF_TXN1_OFF_TXN_TXN_");
		final RowMapper<TransactionTypes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, TransactionTypes.class,
				transactionTypesMapping);
		TransactionTypes returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"Error in cgfkchkOffTxn1OffTxnTxn : ", e);
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
	public List<SysDual> cgwhenNewFormInstance(SysDual paramBean) {
		final String sql = getQuery("OTDRECEI_CGWHEN_NEW_FORM_INSTANCE");
		final RowMapper<SysDual> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, SysDual.class, mMapping);
		final ArrayList<SysDual> returnList = (ArrayList<SysDual>) namedParameterJdbcTemplate.query(sql, createParams(),
				columnRowMapper);
		return returnList;
	}

	@Override
	public String OtdreceiChkReceiptFlag(String txnType, String caseloadId) {
		final String sql = getQuery("OTDRECEI_CHK_RECEIPT_FLAG");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql,
					createParams("txnType", txnType, "caseloadId", caseloadId), String.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"Error in deductionsChkOffenderDeductions : ", e);
			return "N";
		}
	}

	@Override
	public Integer genTrustTrans(String seqId) {
		try {
			final String sql = getQuery("OTDRECEI_GEN_TRUST_TRANS");
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
	public String chkAccountStatus(String caseloadId, Long offenderId) {
		SqlParameter[] sqlParameters = new SqlParameter[] { new SqlParameter("P_CSLD_ID", OracleTypes.VARCHAR),
				new SqlParameter("P_OFFENDER_ID", OracleTypes.NUMBER) };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TRUST").withProcedureName("CHK_ACCOUNT_STATUS").declareParameters(sqlParameters);
		Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("P_CSLD_ID", caseloadId);
		inParamMap.put("P_OFFENDER_ID", offenderId);

		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		final Map<String, Object> returnObject = simpleJDBCCall.execute(inParameter);

		if (returnObject.get("P_OPEN_AN_ACCOUNT") != null) {
			return returnObject.get("P_OPEN_AN_ACCOUNT").toString();
		}
		return "";
	}

	public String deductionsChkOffenderDeductions(String caseloasdId, Long offenderId, String txnType,
			Integer shadowId) {
		SqlParameter[] sqlParameters = new SqlParameter[] { new SqlParameter("P_CSLD_ID", OracleTypes.VARCHAR),
				new SqlParameter("P_OFF_ID", OracleTypes.NUMBER), new SqlParameter("P_TRANS_TYPE", OracleTypes.VARCHAR),
				new SqlParameter("P_SHADOW_ID", OracleTypes.NUMBER),
				new SqlInOutParameter("P_DED_FLAG", OracleTypes.VARCHAR), };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("DEDUCTIONS").withProcedureName("CHK_OFFENDER_DEDUCTIONS")
				.declareParameters(sqlParameters);
		Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("P_CSLD_ID", caseloasdId);
		inParamMap.put("P_OFF_ID", offenderId);
		inParamMap.put("P_TRANS_TYPE", txnType);
		inParamMap.put("P_SHADOW_ID", shadowId);
		inParamMap.put("P_DED_FLAG", "");
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		final Map<String, Object> returnObject = simpleJDBCCall.execute(inParameter);
		if (returnObject.get("P_DED_FLAG") != null) {
			return returnObject.get("P_DED_FLAG").toString();
		}

		return "";
	}

	@Override
	public Map<String, Object> getSubActType(String moduleName, String txnType, String caseloadId) {
		String procedureQuery = "{CALL OMS_OWNER.TRUST.GET_SUB_ACT_TYPE(:P_MODULE_NAME, :P_TXN_TYPE, :P_TXN_POST_TYPE, :P_SUB_ACT_TYPE, :CSLD_ID)}";
		try(Connection connection = dataSource.getConnection()) {
			CallableStatement callableStatement = connection.prepareCall(procedureQuery);
			callableStatement.setString("P_MODULE_NAME", moduleName);
			callableStatement.setString("P_TXN_TYPE", txnType);
			callableStatement.registerOutParameter("P_TXN_POST_TYPE", Types.VARCHAR);
			callableStatement.registerOutParameter("P_SUB_ACT_TYPE", Types.VARCHAR);
			callableStatement.setString("CSLD_ID", caseloadId);
			callableStatement.execute();
			Map<String, Object> returnMapData = new HashMap<>();
			returnMapData.put("P_TXN_POST_TYPE", callableStatement.getString("P_TXN_POST_TYPE"));
			returnMapData.put("P_SUB_ACT_TYPE", callableStatement.getString("P_SUB_ACT_TYPE"));
			return returnMapData;
		} catch (Exception e) {
			logger.error("Error in OMS_OWNER.TRUST.GET_SUB_ACT_TYPE call", e);
		}
		return null;
	}

	@Override
	public Integer insrtIntoOffenderTransForm(OffenderTransactions offenderTransaction) {

		final String sql = getQuery("OTDRECEI_INSRT_INTO_OFFENDER_TRANS_FORM");
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("txnId", offenderTransaction.getTxnId());
		param.addValue("txnEntrySeq", offenderTransaction.getTxnEntrySeq());
		param.addValue("caseloadId", offenderTransaction.getCaseloadId());
		param.addValue("offenderId", offenderTransaction.getOffenderId());
		param.addValue("offenderBookId", offenderTransaction.getOffenderBookId());
		param.addValue("txnPostingType", offenderTransaction.getTxnPostingType());
		param.addValue("txnType", offenderTransaction.getTxnType());
		param.addValue("txnEntryDesc", offenderTransaction.getTxnEntryDesc());
		param.addValue("txnEntryAmount", offenderTransaction.getTxnEntryAmount());
		param.addValue("subAccountType", offenderTransaction.getSubAccountType());
		param.addValue("remitterName", offenderTransaction.getRemitterName());
		param.addValue("remitterId", offenderTransaction.getRemitterId());
		param.addValue("receiptNumber", offenderTransaction.getReceiptNumber());
		param.addValue("createUserId", offenderTransaction.getCreateUserId());
		try {
		return namedParameterJdbcTemplate.update(sql, param);
		}catch (Exception e) {
			logger.error(this.getClass().getName()+"Error in insrtIntoOffenderTransForm : ", e);
		}
		return null;
	}

	@Override
	public Integer processGlTransNew(ProcessGlTransNewBean offTrans) {
		Integer genSeq = 0;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("P_CSLD_ID", offTrans.getpCsldId());
		inParamMap.put("P_TRANS_TYPE", offTrans.getpTransType());
		inParamMap.put("P_OPERATION_TYPE", offTrans.getpOperationType());
		inParamMap.put("P_TRANS_AMOUNT", offTrans.getpTransAmount());
		inParamMap.put("P_TRANS_NUMBER", offTrans.getpTransNumber());
		inParamMap.put("P_TRANS_DATE", offTrans.getpTransDate());
		inParamMap.put("P_TRANS_DESC", offTrans.getpTransDesc());
		inParamMap.put("P_TRANS_SEQ", offTrans.getpTransSeq());
		inParamMap.put("P_MODULE_NAME", offTrans.getpModuleName());
		inParamMap.put("P_OFF_ID", offTrans.getpOffId());
		inParamMap.put("P_OFF_BOOK_ID", offTrans.getpOffBookId());
		inParamMap.put("P_SUB_ACT_TYPE_DR", offTrans.getpSubActTypeDr());
		inParamMap.put("P_SUB_ACT_TYPE_CR", offTrans.getpSubActTypeCr());
		inParamMap.put("P_PAYEE_PERS_ID", offTrans.getpPayeePersId());
		inParamMap.put("P_PAYEE_CORP_ID", offTrans.getpPayeeCorpId());
		inParamMap.put("P_PAYEE_NAME_TEXT", offTrans.getpPayeeNameText());
		inParamMap.put("P_GL_SQNC", offTrans.getpGlSqnc());
		inParamMap.put("P_OFF_DED_ID", offTrans.getpOffDedId());
		namedParameterJdbcTemplate
				.update("call OMS_OWNER.TRUST.PROCESS_GL_TRANS_NEW(:P_CSLD_ID, :P_TRANS_TYPE, :P_OPERATION_TYPE, :P_TRANS_AMOUNT, :P_TRANS_NUMBER, :P_TRANS_DATE, :P_TRANS_DESC,"
						+ " :P_TRANS_SEQ, :P_MODULE_NAME, :P_OFF_ID, :P_OFF_BOOK_ID, :P_SUB_ACT_TYPE_DR, :P_SUB_ACT_TYPE_CR, :P_PAYEE_PERS_ID, :P_PAYEE_CORP_ID, :P_PAYEE_NAME_TEXT,"
						+ " :P_GL_SQNC, :P_OFF_DED_ID)", inParamMap);
		genSeq = 1;
		return genSeq;

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

	@Override
	public TransactionTypes daysCur(String txnType) {
		final String sql = getQuery("OTDRECEI_DAYS_CUR");
		final RowMapper<TransactionTypes> transactionsTypeRowMapper = Row2BeanRowMapper.makeMapping(sql,
				TransactionTypes.class, transactionTypesMapping);
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("txnType", txnType),
					transactionsTypeRowMapper);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"Error in daysCur : ", e);
			return new TransactionTypes();
		}
	}

	@Override
	public void processHold(Integer txnId, String caseloadId, Long offenderId, String txnType, Integer holdDays,
			String subAccountType, Double txnEntryAmount, String txnEntryDesc, String txnReferenceNumber,
			Integer txnnum, Integer holdNumbers) {
		final String sql = "CALL OMS_OWNER.PROCESS_HOLD(:P_TXN_ID, :P_CSLD_ID, :P_OFF_ID, :P_ORG_TXN_TYPE, :P_HOLD_DAYS, :P_SUB_ACCOUNT_TYPE, :P_TOT_AMT, :P_TXN_DESC, :P_TXN_REF_NUM, :P_TXN_NUM, :P_HOLD_NUMBER)";
		Map<String, Object> inParamMap = new HashMap<String, Object>();

		inParamMap.put("P_TXN_ID", txnId);
		inParamMap.put("P_CSLD_ID", caseloadId);
		inParamMap.put("P_OFF_ID", offenderId);
		inParamMap.put("P_ORG_TXN_TYPE", txnType);
		inParamMap.put("P_HOLD_DAYS", holdDays);
		inParamMap.put("P_SUB_ACCOUNT_TYPE", subAccountType);
		inParamMap.put("P_TOT_AMT", txnEntryAmount);
		inParamMap.put("P_TXN_DESC", txnEntryDesc);
		inParamMap.put("P_TXN_REF_NUM", txnReferenceNumber);
		inParamMap.put("P_TXN_NUM", txnnum);
		inParamMap.put("P_HOLD_NUMBER", holdNumbers);

		namedParameterJdbcTemplate.update(sql, inParamMap);

	}

	@Override
	public Integer getMaxTxnEntrySeq(Integer txnId) {
		final String sql = getQuery("OTDRECEI_GET_MAX_TXN_ENTRY_SEQ");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("txnId", txnId), Integer.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"Error in getMaxTxnEntrySeq : ", e);
			return 0;
		}

	}

	@Override
	public void financialDoDuctionsFinancial(String caseloadId, Long offenderId, Long offenderBookId, String txnType,
			Integer txnId, Date txnEntryDate, String subAccountType, String dedFlag, Double txnEntryAmount,
			Integer shadowId, Double dedAmount, Integer txnEntrySeq, String infoNumber) {
		final String query = "CALL OMS_OWNER.FINANCIAL.DO_DEDUCTIONS_FINANCIAL(:P_CSLD_ID, :P_OFF_ID, :P_OFF_BOOK_ID, :P_TRANS_TYPE, "
				+ ":P_TRANS_NUMBER, :P_TRANS_DATE, :P_SUB_ACT_TYPE, :P_DED_FLAG, :P_RECEIPT_AMOUNT, :P_SHADOW_ID, :P_DED_AMOUNT, "
				+ ":TXN_SEQUENCE, :P_INFO_NUMBER)";
		Map<String, Object> param = new HashMap<>();
		param.put("P_CSLD_ID", caseloadId);
		param.put("P_OFF_ID", offenderId);
		param.put("P_OFF_BOOK_ID", offenderBookId);
		param.put("P_TRANS_TYPE", txnType);
		param.put("P_TRANS_NUMBER", txnId);
		param.put("P_TRANS_DATE", txnEntryDate);
		param.put("P_SUB_ACT_TYPE", subAccountType);
		param.put("P_DED_FLAG", dedFlag);
		param.put("P_RECEIPT_AMOUNT", txnEntryAmount);
		param.put("P_SHADOW_ID", shadowId);
		param.put("P_DED_AMOUNT", dedAmount);
		param.put("TXN_SEQUENCE", txnEntrySeq);
		param.put("P_INFO_NUMBER", infoNumber);
		namedParameterJdbcTemplate.update(query, param);

	}

	@Override
	public void deductionsGetAcAndSetIndDate(Long offenderId, String caseloadId) {
		final String sql = "CALL OMS_OWNER.DEDUCTIONS.GET_AC_AND_SET_IND_DATE(:P_OFF_ID,:P_CSLD_ID)";
		Map<String, Object> param = new HashMap<>();
		param.put("P_OFF_ID", offenderId);
		param.put("P_CSLD_ID", caseloadId);
		namedParameterJdbcTemplate.update(sql, param);
	}

	@Override
	public Integer reopenClosedTrustAccount(Long offenderId, String caseloadId,String modifyUserId) {
		final String sql = getQuery("OTDRECEI_REOPEN_CLOSED_TRUST_ACCOUNT");
		try {
			return namedParameterJdbcTemplate.update(sql,
					createParams("offenderId", offenderId, "caseloadId", caseloadId,"modifyUserId",modifyUserId));
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"Error in reopenClosedTrustAccount : ", e);
			return 0;
		}

	}

	@Override
	public String getRTxnType(String moduleName, String caseloadId) {
		final String sql = getQuery("OTDRECEI_GET_R_TXN_TYPE");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql,
					createParams("moduleName", moduleName, "caseloadId", caseloadId), String.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"Error in getRTxnType : ", e);
			return "";
		}

	}

	@Override
	public AccountCodes getSubAccType(String moduleName, String txnType, String caseloadId) {
		final String sql = getQuery("OTDRECEI_GET_SUB_ACC_TYPE");
		final RowMapper<AccountCodes> transactionsTypeRowMapper = Row2BeanRowMapper.makeMapping(sql, AccountCodes.class,
				transactionTypesMapping);
		try {
			return namedParameterJdbcTemplate.queryForObject(sql,
					createParams("moduleName", moduleName, "txnType", txnType, "caseloadId", caseloadId),
					transactionsTypeRowMapper);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"Error in getSubAccType : ", e);
			return new AccountCodes();
		}
	}

	@Override
	public Integer insertIntoOffenderTransaction(OffenderTransactions offTrans) {
		Integer genSeq = 0;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("p_trans_number", offTrans.getTxnId());
		inParamMap.put("p_trans_seq", offTrans.getTxnEntrySeq());
		inParamMap.put("p_csld_id", offTrans.getCaseloadId());
		inParamMap.put("p_off_id", offTrans.getOffenderId());
		inParamMap.put("p_off_book_id", offTrans.getOffenderBookId());
		inParamMap.put("p_trans_post_type", offTrans.getTxnPostingType());
		inParamMap.put("p_trans_type", offTrans.getTxnType());
		inParamMap.put("p_trans_desc", offTrans.getTxnEntryDesc());
		inParamMap.put("p_trans_amount", new BigDecimal(0.0));
		inParamMap.put("p_trans_date", offTrans.getTxnEntryDate());
		inParamMap.put("p_sub_act_type", offTrans.getSubAccountType());
		inParamMap.put("p_deduction_flag", offTrans.getDeductionFlag());
		inParamMap.put("p_pre_ded_amount", offTrans.getPreWithholdAmount());
		inParamMap.put("p_deduction_type", offTrans.getDeductionType());
		inParamMap.put("p_payee_corp_id", offTrans.getPayeeCorporateId());
		inParamMap.put("p_payee_person_id", offTrans.getPayeePersonId());
		inParamMap.put("p_info_number", offTrans.getInfoNumber());
		inParamMap.put("p_slip_print_flag", offTrans.getSlipPrintedFlag());
		inParamMap.put("p_allow_overdrawn", offTrans.getPayeePersonId());
		try {
			namedParameterJdbcTemplate.update(
					" call OMS_OWNER.TRUST.INSERT_INTO_OFFENDER_TRANS(:p_trans_number, :p_trans_seq, :p_csld_id, :p_off_id, :p_off_book_id, :p_trans_post_type, "
							+ " :p_trans_type, :p_trans_desc, :p_trans_amount, :p_trans_date, :p_sub_act_type, :p_deduction_flag, :p_pre_ded_amount, :p_deduction_type, "
							+ " :p_payee_corp_id, :p_payee_person_id, :p_info_number, :p_slip_print_flag, :p_allow_overdrawn)",
					inParamMap);
			genSeq = 1;
		} catch (Exception e) {
			logger.error("In insertIntoOffenderTransaction :" + e);
			return genSeq;
		}
		return genSeq;
	}

}
