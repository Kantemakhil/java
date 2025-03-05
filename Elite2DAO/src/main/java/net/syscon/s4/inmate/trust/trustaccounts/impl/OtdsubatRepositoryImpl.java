package net.syscon.s4.inmate.trust.trustaccounts.impl;

import java.util.ArrayList;
import java.util.Collections;
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
import net.syscon.s4.cf.deductions.beans.FeeAccountProfiles;
import net.syscon.s4.cf.maintenance.beans.FeeAccounts;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.im.beans.SysDual;
import net.syscon.s4.im.beans.TransactionOperation;
import net.syscon.s4.inmate.trust.trustaccounts.OtdsubatRepository;
import oracle.jdbc.OracleTypes;

/**
 * Class OtdsubatRepositoryImpl
 */
@Repository
public class OtdsubatRepositoryImpl extends RepositoryBase implements OtdsubatRepository {
	/**
	 * Creates new OtdofrezRepositoryImpl class Object
	 * 
	 */
	private static Logger log = LogManager.getLogger(OtdsubatRepositoryImpl.class.getName());


	private final Map<String, FieldMapper> referenceCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DOMAIN",                      new FieldMapper("domain"))
			.put("DESCRIPTION",                 new FieldMapper("description"))
			.put("SUB_ACCOUNT_TYPE",            new FieldMapper("subAccountType"))
			.build();
	
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CODE",                      new FieldMapper("code"))
			.put("DESCRIPTION",               new FieldMapper("description"))
			.build();
	
	private final Map<String, FieldMapper> offenderTransactionsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("TXN_REFERENCE_NUMBER",         new FieldMapper("txnReferenceNumber"))
			.put("INFO_NUMBER",                  new FieldMapper("infoNumber"))
			.put("APPLY_SPENDING_LIMIT_AMOUNT",  new FieldMapper("applySpendingLimitAmount"))
			.put("TXN_ADJUSTED_FLAG",            new FieldMapper("txnAdjustedFlag"))
			.put("ADJUST_TXN_ENTRY_ID",          new FieldMapper("adjustTxnEntryId"))
			.put("PAYEE_NAME_TEXT",              new FieldMapper("payeeNameText"))
			.put("RECEIPT_PRINTED_FLAG",         new FieldMapper("receiptPrintedFlag"))
			.put("OFFENDER_ID",                  new FieldMapper("offenderId"))
			.put("HOLD_CLEAR_FLAG",              new FieldMapper("holdClearFlag"))
			.put("GROSS_NET_FLAG",               new FieldMapper("grossNetFlag"))
			.put("SEAL_FLAG",                    new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME",              new FieldMapper("createDatetime"))
			.put("HOLD_NUMBER",                  new FieldMapper("holdNumber"))
			.put("TXN_TYPE",                     new FieldMapper("txnType"))
			.put("GROSS_AMOUNT",                 new FieldMapper("grossAmount"))
			.put("RECEIPT_PENDING_PRINT_FLAG",   new FieldMapper("receiptPendingPrintFlag"))
			.put("MODIFY_DATETIME",              new FieldMapper("modifyDatetime"))
			.put("PAYEE_PERSON_ID",              new FieldMapper("payeePersonId"))
			.put("ORG_TXN_TYPE",                 new FieldMapper("orgTxnType"))
			.put("TRANSFER_CASELOAD_ID",         new FieldMapper("transferCaseloadId"))
			.put("TXN_ID",                       new FieldMapper("txnId"))
			.put("OFFENDER_BOOK_ID",             new FieldMapper("offenderBookId"))
			.put("REMITTER_ID",                  new FieldMapper("remitterId"))
			.put("CREATE_USER_ID",               new FieldMapper("createUserId"))
			.put("TXN_ENTRY_DATE",               new FieldMapper("txnEntryDate"))
			.put("PAYEE_CODE",                   new FieldMapper("payeeCode"))
			.put("MODIFY_USER_ID",               new FieldMapper("modifyUserId"))
			.put("TXN_ENTRY_AMOUNT",             new FieldMapper("txnEntryAmount"))
			.put("ADJUST_OFFENDER_ID",           new FieldMapper("adjustOffenderId"))
			.put("RECEIPT_NUMBER",               new FieldMapper("receiptNumber"))
			.put("ADJUST_TXN_ID",                new FieldMapper("adjustTxnId"))
			.put("CLOSING_CHEQUE_NUMBER",        new FieldMapper("closingChequeNumber"))
			.put("HOLD_UNTIL_DATE",              new FieldMapper("holdUntilDate"))
			.put("SLIP_PRINTED_FLAG",            new FieldMapper("slipPrintedFlag"))
			.put("ADJUST_ACCOUNT_CODE",          new FieldMapper("adjustAccountCode"))
			.put("CASELOAD_ID",                  new FieldMapper("caseloadId"))
			.put("MODIFY_DATE",                  new FieldMapper("modifyDate"))
			.put("DEDUCTION_FLAG",               new FieldMapper("deductionFlag"))
			.put("TXN_ENTRY_SEQ",                new FieldMapper("txnEntrySeq"))
			.put("DEDUCTION_TYPE",               new FieldMapper("deductionType"))
			.put("REMITTER_NAME",                new FieldMapper("remitterName"))
			.put("PAYEE_CORPORATE_ID",           new FieldMapper("payeeCorporateId"))
			.put("SUB_ACCOUNT_TYPE",             new FieldMapper("subAccountType"))
			.put("TXN_ENTRY_DESC",               new FieldMapper("txnEntryDesc"))
			.put("PRE_WITHHOLD_AMOUNT",          new FieldMapper("preWithholdAmount"))
			.put("TXN_POSTING_TYPE",             new FieldMapper("txnPostingType")).build();

	private final Map<String, FieldMapper> sysDualMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("SYSDATE",                      new FieldMapper("sysDate"))
			.put("USER",                         new FieldMapper("user"))
			.build();
	
	private final Map<String, FieldMapper> checkFlagMapping = new ImmutableMap.Builder<String, FieldMapper>()
	.put("AC_CODE",                      new FieldMapper("drAccountCode"))
	.put("CHEQUE_PRODUCTION_FLAG",       new FieldMapper("chequeProductionFlag"))
	.build();

	/**
	 * Creates new OtdsubatRepositoryImpl class Object
	 */
	public OtdsubatRepositoryImpl() {
		// OtdsubatRepositoryImpl
	}

	
	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            OffenderTransactions
	 *
	 * @return List<OffenderTransactions>
	 */
	public List<OffenderTransactions> offTxn2ExecuteQuery(final OffenderTransactions objSearchDao) {
		final String sql = getQuery("OTDSUBAT_OFFTXN2_FIND_OFFENDER_TRANSACTIONS");
		final RowMapper<OffenderTransactions> offTxnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderTransactions.class, offenderTransactionsMapping);
		final ArrayList<OffenderTransactions> returnList = (ArrayList<OffenderTransactions>) namedParameterJdbcTemplate
				.query(sql, createParams(), offTxnRowMapper);
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
	public Integer offtxn2InsertOffenderTransactions(final List<OffenderTransactions> list) {
		Integer listreturn=0;
		Map<String, Object> returnVal=null;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[20];
		sqlParameters = new SqlParameter[] {
				new SqlParameter("P_CASELOAD_TYPE", OracleTypes.VARCHAR),
				new SqlParameter("P_CASELOAD_ID", OracleTypes.VARCHAR),
				new SqlParameter("P_OFFENDER_ID", OracleTypes.NUMBER),
				new SqlParameter("P_TXN_TYPE", OracleTypes.VARCHAR),
				new SqlParameter("P_TXN_DESC", OracleTypes.VARCHAR),
				
				new SqlParameter("P_TXN_ENTRY_AMOUNT", OracleTypes.NUMBER),
				
				new SqlParameter("P_FROM_SUB_ACCOUNT_TYPE", OracleTypes.VARCHAR),
				new SqlParameter("P_TO_SUB_ACCOUNT_TYPE", OracleTypes.VARCHAR),
				new SqlParameter("P_CHEQUE_FLAG", OracleTypes.VARCHAR),
				new SqlParameter("P_BANK_ACCOUNT_CODE", OracleTypes.NUMBER),
				new SqlParameter("P_PAYEE_CORP_ID", OracleTypes.NUMBER),
				new SqlParameter("P_PAYEE_CORP_NAME", OracleTypes.VARCHAR),
				
				new SqlParameter("P_TXN_ID", OracleTypes.NUMBER),
				new SqlParameter("P_TXN_ENTRY_SEQ", OracleTypes.NUMBER),
				new SqlParameter("P_OKAY_FLAG", OracleTypes.VARCHAR),
				new SqlParameter("P_ERROR_MESSAGE", OracleTypes.VARCHAR),
		};
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("OTDSUBAT").withProcedureName("PROCESS_TRANSACTION").declareParameters(sqlParameters);
		 for(final OffenderTransactions obj:list){
		inParamMap.put("P_CASELOAD_TYPE",obj.getCaseloadType());
		inParamMap.put("P_CASELOAD_ID",obj.getCaseloadId());
		inParamMap.put("P_OFFENDER_ID",obj.getOffenderId());
		inParamMap.put("P_TXN_TYPE",obj.getTxnType());
		inParamMap.put("P_TXN_DESC",obj.getTxnEntryDesc());
		inParamMap.put("P_TXN_ENTRY_AMOUNT",obj.getTxnEntryAmount());
		inParamMap.put("P_FROM_SUB_ACCOUNT_TYPE",obj.getFmSubAccountType());
		inParamMap.put("P_TO_SUB_ACCOUNT_TYPE",obj.getToSubAccountType());
		inParamMap.put("P_CHEQUE_FLAG",obj.getChequeProductionFlag());
		inParamMap.put("P_BANK_ACCOUNT_CODE",obj.getCrAccountCode());
		inParamMap.put("P_PAYEE_CORP_ID",obj.getPayeeCorporateId());
		inParamMap.put("P_PAYEE_CORP_NAME",obj.getCorporateName());
		inParamMap.put("P_TXN_ID",obj.getTxnId());
		inParamMap.put("P_TXN_ENTRY_SEQ","1");
		inParamMap.put("P_OKAY_FLAG","Y");
		inParamMap.put("P_ERROR_MESSAGE",null);
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		 try {
			 returnVal = simpleJDBCCall.execute(inParameter);
			 if(returnVal !=null){
				 listreturn =1;
				 
			 }
		 }
				catch(Exception e ){
					
				}
		}
//		 final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
//		 try {
//			 returnVal = simpleJDBCCall.execute(inParameter);
//			 if(returnVal !=null){
//				 listreturn =1;
//				 
//			 }
//		 }
//				catch(Exception e ){
//					
//				}
//		final String sql = getQuery("OTDSUBAT_OFFTXN2_INSERT_OFFENDER_TRANSACTIONS");
//		int[] returnArray = new int[] {};
//		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
//		for (final OffenderTransactions obj : list) {
//			parameters.add(new BeanPropertySqlParameterSource(obj));
//		}
//		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
//		if (list.size() == returnArray.length) {
//			return 1;
//		} else {
//			return 0;
//		}
		return listreturn;
		

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstOffenderTransactions
	 *            List<OffenderTransactions>
	 *
	 * @
	 */
	public Integer offTxn2UpdateOffenderTransactions(final List<OffenderTransactions> lstOffenderTransactions) {
		final String sql = getQuery("OTDSUBAT_OFFTXN2_UPDATE_OFFENDER_TRANSACTIONS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderTransactions obj : lstOffenderTransactions) {
			parameters.add(new BeanPropertySqlParameterSource(obj));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenderTransactions.size() == returnArray.length) {
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
	public List<TransactionOperation> cgfkOffTxn2SubAccountTypeRecordGroup(final String caseLoadId) {
		final String sql = getQuery("OTDSUBAT_FIND_CGFKOFFTXN2SUBACCOUNTTYPE");
		List<TransactionOperation> returnList= new ArrayList<TransactionOperation>();
		final RowMapper<TransactionOperation> mRowMapper = Row2BeanRowMapper.makeMapping(sql, TransactionOperation.class, mMapping);

		try {
			returnList= namedParameterJdbcTemplate.query(sql, createParams("CASELOADID",caseLoadId), mRowMapper);
		} catch (Exception e) {
			log.error("cgfkOffTxn2SubAccountTypeRecordGroup",e);
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> cgfkOffTxnSubAccountTypeRecordGroup(final String fromSubAccount,final String caseLoadId) {
		final String sql = getQuery("OTDSUBAT_FIND_CGFKOFFTXNSUBACCOUNTTYPE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams("SUBACCOUNTTYPE",fromSubAccount,"CASELOADID",caseLoadId), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOffTxn2OffTxnRef
	 *
	 * @param params
	 *
	 */
	public List<ReferenceCodes> cgfkchkOffTxn2OffTxnRef(final ReferenceCodes paramBean) {
		final String sql = getQuery("OTDSUBAT_CGFKCHK_OFF_TXN2_OFF_TXN_REF");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		List<ReferenceCodes> returnObj = namedParameterJdbcTemplate.query(sql, createParams(paramBean),
				columnRowMapper);
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
	public List<SysDual> cgwhenNewFormInstance(final SysDual paramBean) {
		final String sql = getQuery("OTDSUBAT_CGWHEN_NEW_FORM_INSTANCE");
		final RowMapper<SysDual> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, SysDual.class, sysDualMapping);
		final List<SysDual> returnList = namedParameterJdbcTemplate.query(sql, createParams(paramBean),
				columnRowMapper);
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
		final String sql = getQuery("OTDSUBAT_CGFKCHK_OFF_TXN_OFF_TXN_REF_C");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		List<ReferenceCodes> returnObj = namedParameterJdbcTemplate.query(sql, createParams(paramBean),
				columnRowMapper);
		return returnObj;
	}


	@Override
	public String getDescription(final String caseloadType,final String txnType) {
		final String sql=getQuery("OTDSUBAT_GET_DESCRIPTION");
		String txnDesc =null;
		 txnDesc=namedParameterJdbcTemplate.queryForObject(sql, createParams("txnType",txnType,"caseloadType",caseloadType), String.class);		
		return txnDesc;
	}


	@Override
	public Integer getAccountCode(final String caseloadType, final String fmSubAccountType) {
		final String sql=getQuery("OTDSUBAT_GET_ACCOUNT_CODE");
		Integer acCode =0;
		acCode=namedParameterJdbcTemplate.queryForObject(sql, createParams("caseloadType",caseloadType ,"fmSubAccountType",fmSubAccountType), Integer.class);
		return acCode;
	}


	@Override
	public List<TransactionOperation> getCheckProductionFlag(final String moduleName, final String txnType, final Integer crAccountCode,
			final Integer drAcCode, final String caseloadId) {
		final String sql=getQuery("OTDSUBAT_GET_CHECK_PRODUCTION_FLAG");
		List<TransactionOperation> returnList = new ArrayList<TransactionOperation>();
		final RowMapper<TransactionOperation> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, TransactionOperation.class,
				checkFlagMapping);
		returnList=namedParameterJdbcTemplate.query(sql,createParams("moduleName",moduleName,"txnType",txnType,
				"crAccountCode",crAccountCode,"drAcCode",drAcCode,"caseloadId",caseloadId),columnRowMapper);
		
		return returnList;
	}


	@Override
	public List<OffenderTransactions> getCorporateIdName(final String moduleName, final  String txnType,final Integer crAccountCode,
			final Integer drAcCode, final String caseloadId,String userId) {
		final String sql=getQuery("OTDSUBAT_GET_CORPORATE_ID_AND_NAME");
		List<OffenderTransactions> returnList = new ArrayList<OffenderTransactions>();
		final RowMapper<OffenderTransactions> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderTransactions.class,
				checkFlagMapping);
		returnList=namedParameterJdbcTemplate.query(sql,createParams("moduleName",moduleName,"txnType",txnType,
				"crAccountCode",crAccountCode,"drAcCode",drAcCode,"caseloadId",caseloadId,"userId",userId),columnRowMapper);
		
		return returnList;
	}


	@Override
	public Integer getTxnId() {
		final String sql=getQuery("OTDSUBAT_GET_TXN_ID");
		Integer txnId =0;
		txnId=namedParameterJdbcTemplate.queryForObject(sql, createParams(), Integer.class);
		return txnId;
	}


	@Override
	public String getacCode(final String code, final String caseloadType) {
		final String sql=getQuery("OTDSUBAT_GET_AC_CODE");
		String txnDesc =null;
		 txnDesc=namedParameterJdbcTemplate.queryForObject(sql, createParams("code",code,"caseloadType",caseloadType), String.class);		
		return txnDesc;
	}


	@Override
	public String getBal(final String offenderId, final String caseloadId, final String acCode) {
		final String sql=getQuery("OTDSUBAT_GET_BAL");
		String bal =null;
		bal=namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderId",offenderId,"caseloadId",caseloadId,"acCode",acCode), String.class);
		return bal;
	}


	@Override
	public String getAcClosedFlg(final Long offenderId, final String caseloadId) {
		final String sql=getQuery("OTDSUBAT_ACCOUNT_CLOSED_FLAG");
		String closedFlg =null;
		closedFlg=namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderId",offenderId,"caseloadId",caseloadId), String.class);
		return closedFlg;
	}


	@Override
	public Long getRootOffenderId(final String offenderIdDisplay, final String caseloadId, final String bookingNo) {
		final String sql=getQuery("OTDSUBAT_GET_ROOTOFFENDER_ID");
		Long offenderId =null;
		offenderId=namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderIdDisplay",offenderIdDisplay,"caseloadId",caseloadId,"bookingNo",bookingNo), Long.class);
		return offenderId;
	}

	@Override
	public Integer updateoffFeeAccountProfileHty(OffenderTransactions bean) {
		final String sql = getQuery("OTDSUBAT_UPDATE_PREPAIDE_ACCOUNT_STATUS");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("STATUS", bean.getStatusDisplay());
		map.put("OFFENDER_FEE_ID", bean.getOffenderBookId());
		return namedParameterJdbcTemplate.update(sql, map);
	}

	@Override
	public Long getOffenderBookId(Long offenderId, String caseLoadId) {
		final String sql = getQuery("SELECT_OFFENDER_BOOK_ID");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("caseLoadId", caseLoadId, "offenderId", offenderId), Long.class);
	}

	@Override
	public Long getOffenderFeeId(Long offnderBookId, String feeCode) {
		final String sql = getQuery("SELECT_OFFENDER_FEE_ID");
		Long result = null;
		try {
			result = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("offenderBookId", offnderBookId, "feeCode", feeCode), Long.class);
		} catch (Exception e) {
			log.error("getOffenderFeeId", e);
		}
		return result;

	}

	@Override
	public String getFeeAccountProfileData(Long offenderFeeId) {
		final String sql = getQuery("SELECT_FEE_ACCOUNT_PROFILE_DATA");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderFeeId", offenderFeeId),
				String.class);
	}

	@Override
	public Integer updateFeeAccountProfiles(FeeAccountProfiles bean) {
		final String sql = getQuery("UPDATE_FEE_ACCOUNT_PROFILE");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", bean.getFeeActStatus());
		map.put("OFFENDERFEEID", bean.getOffenderFeeId());
		return namedParameterJdbcTemplate.update(sql, map);
	}

	@Override
	public List<FeeAccounts> prepaidFeeCodes() {
		List<FeeAccounts> list = new ArrayList<FeeAccounts>();
		final String sql = getQuery("SELECT_PREPAID_FEE_CODES");
		final RowMapper<FeeAccounts> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, FeeAccounts.class, mMapping);
		try {
			list = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		} catch (Exception e) {
			log.error("prepaidFeeCodes" + e);
		}
		return list;
	}

}
