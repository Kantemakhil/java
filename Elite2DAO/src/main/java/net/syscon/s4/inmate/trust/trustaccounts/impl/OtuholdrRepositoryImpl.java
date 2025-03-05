package net.syscon.s4.inmate.trust.trustaccounts.impl;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.im.beans.SysDual;
import net.syscon.s4.inmate.trust.trustaccounts.OtuholdrRepository;
/**
 * Class OtuholdrRepositoryImpl
 */
@Repository
public class OtuholdrRepositoryImpl extends RepositoryBase implements OtuholdrRepository{

	private static Logger logger = LogManager.getLogger(OtuholdrRepositoryImpl.class.getName());
	
	private final Map<String, FieldMapper> systemProfilesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PROFILE_CODE", 				new FieldMapper("profileCode"))
			.put("PROFILE_TYPE", 				new FieldMapper("profileType"))
			.put("CREATE_USER_ID", 				new FieldMapper("createUserId"))
			.put("SEAL_FLAG",					new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", 			new FieldMapper("createDatetime"))
			.put("MODIFY_USER_ID", 				new FieldMapper("modifyUserId"))
			.put("OLD_TABLE_NAME", 				new FieldMapper("oldTableName"))
			.put("PROFILE_VALUE", 				new FieldMapper("profileValue"))
			.put("MODIFY_DATETIME", 			new FieldMapper("modifyDatetime"))
			.put("PROFILE_VALUE_2", 			new FieldMapper("profileValue2"))
			.put("DESCRIPTION", 				new FieldMapper("description"))
			.build();
	
	private final Map<String, FieldMapper> offenderTransactionsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("TXN_REFERENCE_NUMBER", 		new FieldMapper("txnReferenceNumber"))
			.put("INFO_NUMBER", 				new FieldMapper("infoNumber"))
			.put("APPLY_SPENDING_LIMIT_AMOUNT", new FieldMapper("applySpendingLimitAmount"))
			.put("TXN_ADJUSTED_FLAG", 			new FieldMapper("txnAdjustedFlag"))
			.put("ADJUST_TXN_ENTRY_ID", 		new FieldMapper("adjustTxnEntryId"))
			.put("PAYEE_NAME_TEXT", 			new FieldMapper("payeeNameText"))
			.put("RECEIPT_PRINTED_FLAG", 		new FieldMapper("receiptPrintedFlag"))
			.put("OFFENDER_ID", 				new FieldMapper("offenderId"))
			.put("HOLD_CLEAR_FLAG", 			new FieldMapper("holdClearFlag"))
			.put("GROSS_NET_FLAG", 				new FieldMapper("grossNetFlag"))
			.put("SEAL_FLAG", 					new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", 			new FieldMapper("createDateTime"))
			.put("HOLD_NUMBER", 				new FieldMapper("holdNumber"))
			.put("TXN_TYPE", 					new FieldMapper("txnType"))
			.put("GROSS_AMOUNT", 				new FieldMapper("grossAmount"))
			.put("RECEIPT_PENDING_PRINT_FLAG",  new FieldMapper("receiptPendingPrintFlag"))
			.put("MODIFY_DATETIME", 			new FieldMapper("modifyDateTime"))
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
/**
 * Creates new OtuholdrRepositoryImpl class Object 
 */
public OtuholdrRepositoryImpl() {
}

/**
* Fetch the records from database table
*
* @param objSearchDao OffenderTransactions
*
* @return List<OffenderTransactions>
*
* @throws SQLException
*/
 public List<OffenderTransactions> offTxnExecuteQuery(OffenderTransactions objSearchDao)  {
		final String sql = getQuery("OTUHOLDR_OFFTXN_FIND_OFFENDER_TRANSACTIONS");
		final RowMapper<OffenderTransactions> OffenderTransactionsRowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderTransactions.class,offenderTransactionsMapping);
		final ArrayList<OffenderTransactions> returnList = (ArrayList<OffenderTransactions>)namedParameterJdbcTemplate.query(sql, createParams(), OffenderTransactionsRowMapper);
		return returnList;
} 
/**
* @param 
*
* @throws SQLException 
*
*/
public int PRE_INSERT()  {
return 0;
}

/**
*  This method is used to insert the records in the data base tables based on
*
* @param lstOffenderTransactions List<OffenderTransactions>
*
* @return List<Integer>
*
* @throws SQLException
*/
 public Integer offTxnInsertOffenderTransactions(final List<OffenderTransactions> lstOffenderTransactions)  {
	int insertCount=0;
	String sql = getQuery("OTUHOLDR_OFFTXN_INSERT_OFFENDER_TRANSACTIONS");
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
* This method is used to update the data base tables based on
*
* @param lstOffenderTransactions List<OffenderTransactions>
*
* @throws SQLException
*/
 public Integer offTxnUpdateOffenderTransactions(final List<OffenderTransactions> lstOffenderTransactions)  {
	String sql = getQuery("OTUHOLDR_OFFTXN_UPDATE_OFFENDER_TRANSACTIONS");
	int[] returnArray = new int[] {};
	List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
	for (OffenderTransactions offenderTransactions : lstOffenderTransactions) {
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
* This method is used to delete records from  data base tables based on
*
* @param lstOffenderTransactions List<OffenderTransactions>
*
* @throws SQLException
*/
 public Integer offTxnDeleteOffenderTransactions(final List<OffenderTransactions> lstOffenderTransactions)  {
 	int deleteCount=0;
	String sql = getQuery("OTUHOLDR_OFFTXN_DELETE_OFFENDER_TRANSACTIONS");
	int[] returnArray = new int[] {};
	List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
	for (OffenderTransactions offenderTransactions : lstOffenderTransactions) {
		 parameters.add(new BeanPropertySqlParameterSource(offenderTransactions));
	}
	try {
		String tableName = "OFFENDER_TRANSACTIONS";
		String whereClause = null;
		batchUpdatePreDeletedRows(tableName, whereClause , parameters);
	} catch (Exception e) {
		logger.error("Exception occured in " + this.getClass().getName() + " in method offTxnDeleteOffenderTransactions", e);
	}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
	for (int i = 0; i < returnArray.length; i++) {
		deleteCount = deleteCount++;
	}
	if (lstOffenderTransactions.size() == deleteCount) {
		return 1;
	} else {
		return 0;
	}

}
	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgwhenNewFormInstance
	 *
	 * @param params
	 *
	 */
	public List<Object> cgwhenNewFormInstance(SysDual paramBean)  {
		final String sql = getQuery("OTUHOLDR_CGWHEN_NEW_FORM_INSTANCE");
		final RowMapper<Object> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,Object.class,  systemProfilesMapping);
		final List<Object> returnList =(ArrayList<Object>) namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		return returnList;
	}
	
	@Override
	public String getVHoldClearFlag(final OffenderTransactions paramBean) {
		final String sql = getQuery("OTUHOLDR_GET_V_HOLD_CLEAR_FLAG");
		String returnValue = "";
		try {
			returnValue = namedParameterJdbcTemplate.queryForObject(sql, createParams("txnId", paramBean.getTxnId(),"txnEntrySeq", paramBean.getTxnEntrySeq(), "offenderId", paramBean.getOffenderId()), String.class);
		}catch (Exception e) {
			returnValue = "X";
		}
		return returnValue;
	}
	
	@Override
	public Integer genTrustTrans(String seqId) {
	
			final String sql = getQuery("OTUHOLDR_GEN_TRUST_TRANS");
			if (sql != null) {
			final String preparedSql = sql.replace("#SEQ", seqId);
			return namedParameterJdbcTemplate.queryForObject(preparedSql, createParams(), Integer.class);
			}
		return null;
	}

	@Override
	public Long getMaxBookId(final Long offenderId) {
		final String sql = getQuery("OTUHOLDR_GET_MAX_OFFENDER_BOOK_ID");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderId", offenderId), Long.class);
	}

	@Override
	public void processGlTransNew(String pCsldId, String pTransType, String pOperationType, Double pTransAmount,
			Integer pTransNumber, Date pTransDate, String pTransDesc, Integer pTransSeq, String pModuleName,
			Long pOffId, Long pOffBookId, String pSubActTypeDr, String pSubActTypeCr, Long pPayeePersId,
			BigDecimal pPayeeCorpId, String pPayeeNameText, Integer pGlSqnc, BigDecimal pOffDedId) {
			
			String query = getQuery("OTUHOLDR_TRUST_PROCESS_GL_TRANS_NEW");
			
			Map<String, Object> inParamMap = new HashMap<String, Object>();
			inParamMap.put("P_CSLD_ID", pCsldId);
			inParamMap.put("P_TRANS_TYPE", pTransType);
			inParamMap.put("P_OPERATION_TYPE", pOperationType);
			inParamMap.put("P_TRANS_AMOUNT", pTransAmount);
			inParamMap.put("P_TRANS_NUMBER", pTransNumber);
			inParamMap.put("P_TRANS_DATE", pTransDate);
			inParamMap.put("P_TRANS_DESC", pTransDesc);
			inParamMap.put("P_TRANS_SEQ", pTransSeq);
			inParamMap.put("P_MODULE_NAME", pModuleName);
			inParamMap.put("P_OFF_ID", pOffId);
			inParamMap.put("P_OFF_BOOK_ID", pOffBookId);
			inParamMap.put("P_SUB_ACT_TYPE_DR", pSubActTypeDr);
			inParamMap.put("P_SUB_ACT_TYPE_CR", pSubActTypeCr);
			inParamMap.put("P_PAYEE_PERS_ID", pPayeePersId);
			inParamMap.put("P_PAYEE_CORP_ID", pPayeeCorpId);
			inParamMap.put("P_PAYEE_NAME_TEXT", pPayeeNameText);
			inParamMap.put("P_GL_SQNC", pGlSqnc);
			inParamMap.put("P_OFF_DED_ID", pOffDedId);
			
			namedParameterJdbcTemplate.update(query, inParamMap);
		
	}

	@Override
	public void updateOffenderTrustAccounts(Double txnEntryAmount, Long offenderId, String caseloadId, String modifyUserId) {
		final String sql = getQuery("OTUHOLDR_UPDATE_OFFENDER_TRUST_ACCOUNTS");
		Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("txnEntryAmount", txnEntryAmount);
		inParamMap.put("offenderId", offenderId);
		inParamMap.put("caseloadId", caseloadId);
		inParamMap.put("modifyUserId", modifyUserId);
		namedParameterJdbcTemplate.update(sql, inParamMap);
	}

	@Override
	public void updateOffenderSubAccount(Double txnEntryAmount, Long offenderId, String caseloadId,
			String subAccountType, String modifyUserId) {
		final String sql = getQuery("OTUHOLDR_UPDATE_OFFENDER_SUB_ACCOUNT");
		Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("txnEntryAmount", txnEntryAmount);
		inParamMap.put("offenderId", offenderId);
		inParamMap.put("caseloadId", caseloadId);
		inParamMap.put("subAccountType", subAccountType);
		inParamMap.put("modifyUserId", modifyUserId);
		namedParameterJdbcTemplate.update(sql, inParamMap);
		
	}

	@Override
	public void subInsertOffTxnInsert(Integer txnId, Integer txnEntrySeq, String caseloadId, Long offenderId,
			Long offenderBookId, String txnPostingType, String txnType, String txnEntryDesc, Double txnEntryAmount,
			Date txnEntryDate, String subAccountType, Date modifyDate, String modifyUserId, String slipPrintedFlag,
			String txnAdjustedFlag, String holdClearFlag, Integer holdNumber,String createuserId) {
		final String sql = getQuery("OTUHOLDR_SUB_INSERT_OFF_TXN_INSERT");
		MapSqlParameterSource inParamMap = new MapSqlParameterSource();
		inParamMap.addValue("txnId", txnId);
		inParamMap.addValue("txnEntrySeq", txnEntrySeq );
		inParamMap.addValue("caseloadId", caseloadId );
		inParamMap.addValue("offenderId", offenderId );
		inParamMap.addValue("offenderBookId", offenderBookId );
		inParamMap.addValue("txnPostingType", txnPostingType );
		inParamMap.addValue("txnType", txnType );
		inParamMap.addValue("txnEntryDesc", txnEntryDesc );
		inParamMap.addValue("txnEntryAmount", txnEntryAmount);
		inParamMap.addValue("txnEntryDate", txnEntryDate);
		inParamMap.addValue("subAccountType", subAccountType );
		inParamMap.addValue("modifyDate", modifyDate );
		inParamMap.addValue("modifyUserId", modifyUserId );
		inParamMap.addValue("slipPrintedFlag", slipPrintedFlag );
		inParamMap.addValue("txnAdjustedFlag", txnAdjustedFlag );
		inParamMap.addValue("holdClearFlag", holdClearFlag);
		inParamMap.addValue("holdNumber", holdNumber);
		inParamMap.addValue("createUserId", createuserId);

		namedParameterJdbcTemplate.update(sql, inParamMap);
		
	}

	@Override
	public void updateOffenderBalance(String pCsldId, Long pOffId, String pTransPostType, Date pTransDate,
			Integer pTransNumber, String pTransType, Double pTransAmount, String pSubActType, String pAllowOverdrawn) {
		final String sql = getQuery("OTUHOLDR_UPDATE_OFFENDER_BALANCE");
		Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("P_CSLD_ID", pCsldId);
		inParamMap.put("P_OFF_ID", pOffId);
		inParamMap.put("P_TRANS_POST_TYPE", pTransPostType);
		inParamMap.put("P_TRANS_DATE", pTransDate);
		inParamMap.put("P_TRANS_NUMBER", pTransNumber);
		inParamMap.put("P_TRANS_TYPE", pTransType);
		inParamMap.put("P_TRANS_AMOUNT", pTransAmount);
		inParamMap.put("P_SUB_ACT_TYPE", pSubActType);
		inParamMap.put("P_ALLOW_OVERDRAWN", pAllowOverdrawn);
		namedParameterJdbcTemplate.update(sql, inParamMap);
		
	}

	@Override
	public void updateHoldRecord(Integer txnId, Integer holdNumber, Long offenderId, String modifyUserId) {
		final String sql = getQuery("OTUHOLDR_UPDATE_HOLD_RECORD");
		Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("txnId", txnId);
		inParamMap.put("holdNumber", holdNumber);
		inParamMap.put("offenderId", offenderId);
		inParamMap.put("modifyUserId", modifyUserId);
		namedParameterJdbcTemplate.update(sql, inParamMap);
	}

	@Override
	public String getOrgTxnType(Integer txnId, Integer txnEntrySeq, Long offenderId) {
		final String sql = getQuery("OTUHOLDR_GET_ORG_TXN_TYPE");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("txnId",txnId,"txnEntrySeq", txnEntrySeq, "offenderId", offenderId), String.class);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public void financialDoDuctionsFinancial(String pCsldId, Long pOffId, Long pOffBookId, String pTransType,
			Integer pTransNumber, Date pTransDate, String pSubActType, String pDedFlag, Double pReceiptAmount,
			Object pShadowId, Double pDedAmount, Integer txnSequence, String pInfoNumber) {
		final String sql = getQuery("OTUHOLDR_OMS_OWNER_FINANCIAL_DO_DEDUCTIONS_FINANCIAL");
		Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("P_CSLD_ID", pCsldId);
		inParamMap.put("P_OFF_ID", pOffId);
		inParamMap.put("P_OFF_BOOK_ID", pOffBookId);
		inParamMap.put("P_TRANS_TYPE", pTransType);
		inParamMap.put("P_TRANS_NUMBER", pTransNumber);
		inParamMap.put("P_TRANS_DATE", pTransDate);
		inParamMap.put("P_SUB_ACT_TYPE", pSubActType);
		inParamMap.put("P_DED_FLAG", pDedFlag);
		inParamMap.put("P_RECEIPT_AMOUNT", pReceiptAmount);
		inParamMap.put("P_SHADOW_ID", pShadowId);
		inParamMap.put("P_DED_AMOUNT", pDedAmount);
		inParamMap.put("TXN_SEQUENCE", txnSequence);
		inParamMap.put("P_INFO_NUMBER", pInfoNumber);
		namedParameterJdbcTemplate.update(sql, inParamMap);

	}
	
	@Override
	public void deductionGetAcAndSetIndDate(Long offenderId, String caseloadId) {
		final String sql = getQuery("OTUHOLDR_DEDUCTION_GET_AC_AND_SET_IND_DATE");
		Map<String, Object> param = new HashMap<>();
		param.put("P_OFF_ID", offenderId);
		param.put("P_CSLD_ID", caseloadId);
		namedParameterJdbcTemplate.update(sql, param);
	}

}
