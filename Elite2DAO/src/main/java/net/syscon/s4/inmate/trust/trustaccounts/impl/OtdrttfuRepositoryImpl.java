package net.syscon.s4.inmate.trust.trustaccounts.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
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
import net.syscon.s4.common.beans.Caseloads;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.im.beans.SysDual;
import net.syscon.s4.inmate.beans.OffenderTrustTransfers;
import net.syscon.s4.inmate.trust.trustaccounts.OtdrttfuRepository;
import oracle.jdbc.OracleTypes;

/**
 * Class OtdrttfuRepositoryImpl
 */
@Repository
public class OtdrttfuRepositoryImpl extends RepositoryBase implements OtdrttfuRepository {

	
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger log = LogManager.getLogger(OtdrttfuRepositoryImpl.class.getName());
	
	private final Map<String, FieldMapper> offenderTrustTransfersMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("MODIFY_DATE", 				new FieldMapper("modifyDate"))
			.put("CREATE_USER_ID", 				new FieldMapper("createUserId"))
			.put("FROM_CASELOAD", 				new FieldMapper("fromCaseload"))
			.put("AMOUNT", 						new FieldMapper("amount"))
			.put("SEAL_FLAG", 					new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", 			new FieldMapper("createDateTime"))
			.put("MODIFY_USER_ID", 				new FieldMapper("modifyUserId"))
			.put("MODIFY_DATETIME", 			new FieldMapper("modifyDateTime"))
			.put("TRANSFER_DATE", 				new FieldMapper("transferDate"))
			.put("POSTED_FLAG", 				new FieldMapper("postedFlag"))
			.put("TXN_ID", 						new FieldMapper("txnId"))
			.put("TO_CASELOAD", 				new FieldMapper("toCaseload"))
			.build();
	private final Map<String, FieldMapper> caseloadsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", 				new FieldMapper("description"))
			.put("FROM_CASELOAD", 				new FieldMapper("fromCaseload"))
			.build();
	private final Map<String, FieldMapper> sysProfMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PROFILE_CODE", 				new FieldMapper("profileCode"))
			.put("PROFILE_TYPE", 				new FieldMapper("profileType"))
			.put("CREATE_USER_ID", 				new FieldMapper("createUserId"))
			.put("SEAL_FLAG", 					new FieldMapper("sealFlag"))
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
			.put("MODIFY_USER_ID",				new FieldMapper("modifyUserId"))			
			.put("TXNAMT",						new FieldMapper("txnEntryAmount"))
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
			.put("STA", 						new FieldMapper("sta"))
			.put("DESCRIPTION", 				new FieldMapper("txnEntryDesc"))
			.put("RECPRODFLG", 				new FieldMapper("receiptProductionFlag"))
			
			.build();
	private final Map<String, FieldMapper> sysDualMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("SYSDATE", 					new FieldMapper("sysDate"))
			.put("USER", 						new FieldMapper("user"))
			.build();
	private final Map<String, FieldMapper> offenderTrustTransferMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_ID", 							new FieldMapper("offenderId"))
			.put("SUB_ACCOUNT_TYPE", 						new FieldMapper("subAcntType"))
			.put("TXN_ENTRY_AMOUNT", 						new FieldMapper("txnEntryAmnt"))
			.put("OFFENDER_BOOK_ID", 						new FieldMapper("offenderBookId"))	
			.put("TXN_TYPE", 								new FieldMapper("txnType"))	
			.put("DESCRIPTION", 							new FieldMapper("txndesc"))	
			.put("RECPRODFLG", 								new FieldMapper("recdoFlag"))	
			.put("TXN_USAGE", 								new FieldMapper("txnUsage"))
			
			.build();
	/**
	 * Creates new OtdrttfuRepositoryImpl class Object
	 */
	public OtdrttfuRepositoryImpl() {
		// OtdrttfuRepositoryImpl
		
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            OffenderTrustTransfers
	 *
	 * @return List<OffenderTrustTransfers>
	 *
	 * 
	 */
	public List<OffenderTrustTransfers> offTtExecuteQuery(final OffenderTrustTransfers objSearchDao) {
		final String sql = getQuery("OTDRTTFU_OFFTT_FIND_OFFENDER_TRUST_TRANSFERS");
		final RowMapper<OffenderTrustTransfers> offTrustRM = Row2BeanRowMapper.makeMapping(sql,
				OffenderTrustTransfers.class, offenderTrustTransfersMapping);
		final ArrayList<OffenderTrustTransfers> returnList = (ArrayList<OffenderTrustTransfers>) namedParameterJdbcTemplate
				.query(sql, createParams("GLOBALCASELOAD",objSearchDao.getToCaseload()), offTrustRM);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstOffTrust
	 *            List<OffenderTrustTransfers>
	 *
	 * @return List<Integer>
	 *
	 * 
	 */
	public Integer offTtInsertOffenderTrustTransfers(final List<OffenderTrustTransfers> lstOffTrust) {
		int returnValue = 0;
		final String sql = getQuery("OTDRTTFU_OFFTT_INSERT_OFFENDER_TRUST_TRANSFERS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderTrustTransfers offTrustTransfer : lstOffTrust) {
			parameters.add(new BeanPropertySqlParameterSource(offTrustTransfer));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffTrust.size() == returnArray.length) {
			returnValue = 1;
		}
		return returnValue;
	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstOffTrust
	 *            List<OffenderTrustTransfers>
	 *
	 * 
	 */
	public Integer offTtUpdateOffenderTrustTransfers(final List<OffenderTrustTransfers> lstOffTrust) {
		final String sql = getQuery("OTDRTTFU_OFFTT_UPDATE_OFFENDER_TRUST_TRANSFERS");
		int returnValue = 0;
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderTrustTransfers offTrustTransfers : lstOffTrust) {
			parameters.add(new BeanPropertySqlParameterSource(offTrustTransfers));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffTrust.size() == returnArray.length) {
			returnValue = 1;
		}
		return returnValue;

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
		final String sql = getQuery("OTDRTTFU_GET_OFFENDER_ID_TXNAMT_CLOSED_FLAG");
		final RowMapper<OffenderTransactions> offTransRowM = Row2BeanRowMapper.makeMapping(sql,
				OffenderTransactions.class, offenderTransactionsMapping);
		final ArrayList<OffenderTransactions> returnList = (ArrayList<OffenderTransactions>) namedParameterJdbcTemplate
				.query(sql, createParams("txnId",objSearchDao.getTxnId(),"frCaseloadId",objSearchDao.getFromCaseloadId(),"toCaseloadId",objSearchDao.getToCaseloadId()), offTransRowM);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstOffTrans
	 *            List<OffenderTransactions>
	 *
	 * @return List<Integer>
	 *
	 * 
	 */
	public Integer offTxnInsertOffenderTransactions(final List<OffenderTransactions> lstOffTrans) {
		int returnValue = 0;
		final String sql = getQuery("OTDRTTFU_OFFTXN_INSERT_OFFENDER_TRANSACTIONS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();

		for (final OffenderTransactions offenderTrans : lstOffTrans) {
			parameters.add(new BeanPropertySqlParameterSource(offenderTrans));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffTrans.size() == returnArray.length) {
			returnValue = 1;
		}
		return returnValue;

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstOffTrans
	 *            List<OffenderTransactions>
	 *
	 * 
	 */
	public Integer offTxnDeleteOffenderTransactions(final List<OffenderTransactions> lstOffTrans) {
		final String sql = getQuery("OTDRTTFU_OFFTXN_DELETE_OFFENDER_TRANSACTIONS");
		int returnValue = 0;
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderTransactions offTrans : lstOffTrans) {
			parameters.add(new BeanPropertySqlParameterSource(offTrans));
		}
		try {
			String tableName = "OFFENDER_TRANSACTIONS";
			String whereClause = null;
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			log.error("Exception occured in " + this.getClass().getName() + " in method offTxnDeleteOffenderTransactions", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffTrans.size() == returnArray.length) {
			returnValue = 1;
		}
		return returnValue;

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
		final String sql = getQuery("OTDRTTFU_SYSPFL_FIND_SYSTEM_PROFILES");
		final RowMapper<SystemProfiles> sysProfRM = Row2BeanRowMapper.makeMapping(sql, SystemProfiles.class,
				sysProfMapping);
		final ArrayList<SystemProfiles> returnList = (ArrayList<SystemProfiles>) namedParameterJdbcTemplate.query(sql,
				createParams(), sysProfRM);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOffTtOffTtCsld
	 *
	 * @param params
	 *
	 */
	public Caseloads cgfkchkOffTtOffTtCsld(final Caseloads paramBean) {
		final String sql = getQuery("OTDRTTFU_CGFKCHK_OFF_TT_OFF_TT_CSLD");
		final RowMapper<Caseloads> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Caseloads.class,
				caseloadsMapping);
		final Caseloads returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("FROMCASELOAD"), columnRowMapper);
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
		final String sql = getQuery("OTDRTTFU_CGWHEN_NEW_FORM_INSTANCE");
		final RowMapper<SysDual> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, SysDual.class, sysDualMapping);
		final ArrayList<SysDual> returnList = (ArrayList<SysDual>) namedParameterJdbcTemplate.query(sql, createParams(),
				columnRowMapper);
		return returnList;
	}

	@Override
	public String getfromCaseloadDesc(final String fromCaseload) {
		final String sql = getQuery("OTDRTTFU_CGFKCHK_OFF_TT_OFF_TT_CSLD");
		String desc =null;
		try{
		desc=namedParameterJdbcTemplate.queryForObject(sql,createParams("FROMCASELOAD",fromCaseload),String.class);
		} catch(Exception e){
			log.error("getfromCaseloadDesc",e);
		}
		return desc;
	}

	@Override
	public Integer getTheCheckNo(final String fromCaseload, final  BigDecimal txnId) {
		final String sql = getQuery("OTDRTTFU_CHEQUE_NUMBER");
		Integer nub = 0;
		try{
		nub=namedParameterJdbcTemplate.queryForObject(sql, createParams("FRCASELOAD",fromCaseload,"TXNID",txnId), Integer.class);
		} catch (EmptyResultDataAccessException e) {
			return nub;
			
		}
		return nub;
	}

	@Override
	public Long getBookId(final Long offenderId) {
		final String sql = getQuery("OTDRTTFU_GET_BOOK_ID");
		Long bkgId = null;
		try{
		bkgId =namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderId",offenderId), Long.class);
		} catch(Exception e){
			log.error("getBookId",e);			
		}
		return bkgId;
	}

	@Override
	public String getActiveFlg(final Long obkgId) {
		final String sql = getQuery("OTDRTTFU_GET_ACTIVE_FLAG");
		String actFlag=null;
		try{
		actFlag =namedParameterJdbcTemplate.queryForObject(sql,createParams("offenderBookId",obkgId),String.class);
		} catch (Exception e) {
			log.error("getActiveFlg",e);
		}
		return actFlag;
	}

	@Override
	public String getAcStatus(final String toCaseloadId, final Long offenderId) {
		final String sql = getQuery("OTDRTTFU_GET_ACT_STATUS");
		String actFlag=null;
		try{
		actFlag =namedParameterJdbcTemplate.queryForObject(sql,createParams("toCaseloadId",toCaseloadId,"offenderId",offenderId),String.class);
		if(actFlag !=null){
			return actFlag;
		} 
		} catch (EmptyResultDataAccessException e) {
			return actFlag;
		}
		return actFlag;
	}

	@Override
	public OffenderTransactions getLastNameFirstNames(final Long offenderId,final String caseLoadType) {
		final String sql = getQuery("OTDRTTFU_GET_LASTNAME_FIRSTNAME");
		OffenderTransactions beanData= new OffenderTransactions();
		final RowMapper<OffenderTransactions> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderTransactions.class, offenderTransactionsMapping);
		try{
		beanData =namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderId",offenderId,
				"caseloadType",caseLoadType), columnRowMapper);
		} catch (Exception e) {
			log.error("getLastNameFirstNames",e);
		}
		return beanData;
	}

	@Override
	public Integer updateOffenderTrustTransfers(final OffenderTrustTransfers obj) {
		final String sql = getQuery("OTDRTTFU_UPDATE_OFFENDER_TRUST_TRANSFERS");
		Integer val=0;
		val=namedParameterJdbcTemplate.update(sql, createParams("txnid",obj.getTxnId(), "modifyUserId", obj.getModifyUserId()));
		return val;
	}

	@Override
	public List<OffenderTransactions> getSubAcntOffIdtxnAmtbkgId(final OffenderTrustTransfers obj) {
		final String sql = getQuery("OTDRTTFU_SUBACNT_OFFID_TXNAMT_BKGID");
		List<OffenderTransactions> returnList= new ArrayList<OffenderTransactions>();
		final RowMapper<OffenderTransactions> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderTransactions.class, offenderTransactionsMapping);
		try{
			returnList =namedParameterJdbcTemplate.query(sql, createParams("txnId",obj.getTxnId()),columnRowMapper);
		} catch(Exception e){
			log.error("getSubAcntOffIdtxnAmtbkgId",e);
			
		}
		return returnList;
	}

	@Override
	public OffenderTransactions getTxnTypeDescTxnUsageRecProdFlag(final OffenderTrustTransfers obj) {
		final String sql = getQuery("OTDRTTFU_TXNTYPE_DES_TXNUSAGE_RECPRODFLG");
		OffenderTransactions data= new OffenderTransactions();
		final RowMapper<OffenderTransactions> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderTransactions.class, offenderTransactionsMapping);
		try{
			data =namedParameterJdbcTemplate.queryForObject(sql, createParams("globalCaseload",obj.getCaseloadId(),
				"caseloadType",obj.getCaseloadType(),"subacntType",obj.getSubAcntType()), columnRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return data;
		}
		return data;
	}

	@Override
	public void updateOffenderTrustTransfersWithClosedFlagN(final OffenderTrustTransfers obj) {
		final String sql = getQuery("OTDRTTFU_UPDATE_OFFENDER_TRUST_TRANSFERS_WITH_CLOSED_FLAG_N");
		final Integer val= namedParameterJdbcTemplate.update(sql, createParams("tocaseload",obj.getToCaseload(),"offenderId",obj.getOffenderId()));
	}

	@Override
	public Integer insertOffenderTrustAcounts(final OffenderTrustTransfers obj) {
		final String sql = getQuery("OTDRTTFU_INSERT_INTO_OFFENDER_TRANS_FORM");
		Integer val=0;
		val=namedParameterJdbcTemplate.update(sql, createParams("P_csld_id",obj.getCaseloadId(),"P_off_id",obj.getOffenderId(), "create_user_id", obj.getCreateUserId()));
		return val;
	}

	@Override
	public Integer insertOffenderSubAccounts(final OffenderTrustTransfers obj) {
		final String sql = getQuery("OTDRTTFU_INSERT_OFFENDER_SUB_ACCOUNTS");
		Integer val=0;
		val=namedParameterJdbcTemplate.update(sql, createParams("caseloadId",obj.getCaseloadId(),"offenderId",obj.getOffenderId(),
				"txnId",obj.getTxnId(), "caseloadType",obj.getCaseloadType(), "createUserId", obj.getCreateUserId()));
		return val;


	}

	@Override
	public BigDecimal getNextVal() {
		final String sql = getQuery("OTDRTTFU_GET_NEXT_VAL");
		BigDecimal txnId = BigDecimal.ZERO;
		txnId = namedParameterJdbcTemplate.queryForObject(sql, createParams(), BigDecimal.class);
		return txnId;
	}

	
	public Date createDefaultDeductions(final OffenderTrustTransfers obj) {
		Date vdate=null;
		Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("p_csld_id", obj.getCaseloadId());
		inParamMap.put("p_off_id", obj.getOffenderId());
		try {
			namedParameterJdbcTemplate
					.update("call OMS_OWNER.DEDUCTIONS.CREATE_DEFAULT_DEDUCTIONS(:p_csld_id, :p_off_id)", inParamMap);
		} catch(Exception e){
			log.error("createDefaultDeductions",e);
		}
		return vdate;
	}
	

	@Override
	public Integer genTrustRcptNmbr(String seqId) {
		try {
			final String sql = getQuery("GEN_TRUST_TRANS");
			if (sql != null) {
			final String preparedSql = sql.replace("#SEQ", seqId);
			return namedParameterJdbcTemplate.queryForObject(preparedSql, createParams(), Integer.class);
			}
		}catch (Exception e) {
			log.error("genTrustRcptNmbr", e);
		}
		return null;
	}

	@Override
	public Integer insertintoOffenderTrans(final OffenderTrustTransfers obj) {
		
		Integer val = 0;
		final String sql = getQuery("OTDRTTFU_INSERT_OFFENDER_TRANSACTIONS");
		try {
		val = namedParameterJdbcTemplate.update(sql, createParams("txnId",obj.getTxnId(),"txnseq",obj.getTxnEntrySeq(),
				"tocaseloadId", obj.getToCaseload(), "offenderId",obj.getOffenderId(),"offenderBookId",obj.getOffenderBookId(),
				"txnType",obj.getTxnType(),"txnDesc",obj.getTxndesc(),"txnentryAmnt",obj.getTxnEntryAmnt(),
				 "subAcntType", obj.getSubAcntType(), "receiptNum", obj.getReceiptNumber(),"frCaseload",obj.getFromCaseload(), "create_user_id", obj.getCreateUserId()));
		}catch(Exception e){
			log.error("insertintoOffenderTrans",e);
		}
		return val;
	}

	@Override
	public void updateOffenderBalance(final OffenderTrustTransfers obj) {
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
			inParamMap.put("P_CSLD_ID", obj.getCaseloadId());
			inParamMap.put("P_OFF_ID", obj.getOffenderId());
			inParamMap.put("P_TRANS_POST_TYPE", "CR");
			inParamMap.put("P_TRANS_DATE", obj.getTxnEntryDate());
			inParamMap.put("P_TRANS_NUMBER", obj.getTxnId());
			inParamMap.put("P_TRANS_TYPE", obj.getTxnType());
			inParamMap.put("P_TRANS_AMOUNT", obj.getTxnEntryAmnt());
			inParamMap.put("P_SUB_ACT_TYPE", obj.getSubAcntType());
			inParamMap.put("P_ALLOW_OVERDRAWN", "N");
		
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		simpleJDBCCall.execute(inParameter);
	}
	
	@Override
	public Integer processGlTransNew(final OffenderTrustTransfers bean) {
		Integer genSeq = 0;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
			inParamMap.put("P_CSLD_ID", bean.getToCaseload());
			inParamMap.put("P_TRANS_TYPE", bean.getTxnType());
			inParamMap.put("P_OPERATION_TYPE", null);
			inParamMap.put("P_TRANS_AMOUNT", bean.getTxnEntryAmnt());
			inParamMap.put("P_TRANS_NUMBER", bean.getTxnId());
			inParamMap.put("P_TRANS_DATE", bean.getTxnEntryDate());
			inParamMap.put("P_TRANS_DESC", bean.getTxndesc());
			inParamMap.put("P_TRANS_SEQ", bean.getTxnEntrySeq());
			inParamMap.put("P_MODULE_NAME", "OTDRTTFU");
			inParamMap.put("P_OFF_ID", bean.getOffenderId());
			inParamMap.put("P_OFF_BOOK_ID", bean.getOffenderBookId());
			inParamMap.put("P_SUB_ACT_TYPE_DR", null);
			inParamMap.put("P_SUB_ACT_TYPE_CR", bean.getSubAcntType());
			inParamMap.put("P_PAYEE_PERS_ID", null);
			inParamMap.put("P_PAYEE_CORP_ID",null);
			inParamMap.put("P_PAYEE_NAME_TEXT", null);
			inParamMap.put("P_GL_SQNC", 0);
			inParamMap.put("P_OFF_DED_ID", null);
		
		namedParameterJdbcTemplate
				.update("call OMS_OWNER.TRUST.PROCESS_GL_TRANS_NEW(:P_CSLD_ID, :P_TRANS_TYPE, :P_OPERATION_TYPE, :P_TRANS_AMOUNT, :P_TRANS_NUMBER, :P_TRANS_DATE, :P_TRANS_DESC,"
						+ " :P_TRANS_SEQ, :P_MODULE_NAME, :P_OFF_ID, :P_OFF_BOOK_ID, :P_SUB_ACT_TYPE_DR, :P_SUB_ACT_TYPE_CR, :P_PAYEE_PERS_ID, :P_PAYEE_CORP_ID, :P_PAYEE_NAME_TEXT,"
						+ " :P_GL_SQNC,:P_OFF_DED_ID)", inParamMap);
		// , :P_OFF_DED_ID
		genSeq = 1;
		return genSeq;
	}

	@Override
	public String getProfileVal() {
		final String sql =getQuery("OTDRTTFU_GET_PROFILE_VALUE");
		String profileVal =null;
		try{
			profileVal =namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);
		}catch (Exception e) {
			log.error("getProfileVal",e);
		}
		return profileVal;
	}

	@Override
	public Integer doDeductionsFinancial(final OffenderTrustTransfers bean) {
		Integer doFinacial = 0;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
			inParamMap.put("P_CSLD_ID", bean.getToCaseload());
			inParamMap.put("P_OFF_ID", bean.getOffenderId());
			inParamMap.put("P_OFF_BOOK_ID", bean.getOffenderBookId());
			inParamMap.put("P_TRANS_TYPE", bean.getTxnType());
			inParamMap.put("P_TRANS_NUMBER", bean.getTxnId());
			inParamMap.put("P_TRANS_DATE", bean.getTxnEntryDate());
			inParamMap.put("P_SUB_ACT_TYPE", bean.getSubAcntType());
			inParamMap.put("P_DED_FLAG","Y");
			inParamMap.put("P_RECEIPT_AMOUNT", bean.getTxnEntryAmnt());
			inParamMap.put("P_SHADOW_ID",null);
			inParamMap.put("P_DED_AMOUNT", bean.getTxnEntryAmnt());
			inParamMap.put("TXN_SEQUENCE", bean.getTxnEntrySeq());
			inParamMap.put("P_INFO_NUMBER", null);
			inParamMap.put("P_MODULE_NAME", null);
		
		namedParameterJdbcTemplate
				.update("call OMS_OWNER.FINANCIAL.DO_DEDUCTIONS_FINANCIAL(:P_CSLD_ID, :P_OFF_ID, :P_OFF_BOOK_ID, "
						+ ":P_TRANS_TYPE, :P_TRANS_NUMBER, :P_TRANS_DATE, :P_SUB_ACT_TYPE,"
						+ " :P_DED_FLAG, :P_RECEIPT_AMOUNT, :P_SHADOW_ID, :P_DED_AMOUNT, :TXN_SEQUENCE, "
						+ ":P_INFO_NUMBER, :P_MODULE_NAME)", inParamMap);
		// , :P_OFF_DED_ID
		doFinacial = 1;
		return doFinacial;
}

	public void deductionGetAcAndSetIndDate(final Long offenderId, final String caseloadId) {
		final String sql = getQuery("OTDRTTFU_DEDUCTIONS_GET_AC_AND_SET_IND_DATE");
		namedParameterJdbcTemplate.update(sql, createParams("P_OFF_ID", offenderId, "P_CSLD_ID", caseloadId));
	}
}
