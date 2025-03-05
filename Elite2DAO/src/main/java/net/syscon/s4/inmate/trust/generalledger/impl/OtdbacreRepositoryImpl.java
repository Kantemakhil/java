package net.syscon.s4.inmate.trust.generalledger.impl;

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
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.im.beans.SysDual;
import net.syscon.s4.inmate.beans.BankChequeRegisters;
import net.syscon.s4.inmate.beans.BankClearReconcilesTmp;
import net.syscon.s4.inmate.beans.BankReconAudits;
import net.syscon.s4.inmate.beans.GlTransactions;
import net.syscon.s4.inmate.trust.generalledger.OtdbacreRepository;
import oracle.jdbc.OracleTypes;

/**
 * Class OtdbacreRepositoryImpl
 */
@Repository
public class OtdbacreRepositoryImpl extends RepositoryBase implements OtdbacreRepository {
	
	
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OtdbacreRepositoryImpl.class.getName());
	
	private final Map<String, FieldMapper> accountCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("ACCOUNT_NAME", new FieldMapper("accountName")).put("CASELOAD_TYPE", new FieldMapper("caseloadType"))
			.put("ACCOUNT_CODE", new FieldMapper("accountCode")).put("CASELOAD_ID", new FieldMapper("caseloadId"))
			.put("CODE", new FieldMapper("code")).put("DESCRIPTION", new FieldMapper("description")).build();
	private final Map<String, FieldMapper> bankClearReconcilesTmpMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("TXN_ENTRY_DATE", new FieldMapper("txnEntryDate"))
			.put("REFERENCE_NO_TYPE", new FieldMapper("referenceNoType"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("TXN_ENTRY_AMOUNT", new FieldMapper("txnEntryAmount"))
			.put("CASELOAD_ID", new FieldMapper("caseloadId")).put("GL_ENTRY_SEQ", new FieldMapper("glEntrySeq"))
			.put("SEAL_FLAG", new FieldMapper("sealFlag")).put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("TXN_ENTRY_SEQ", new FieldMapper("txnEntrySeq")).put("REFERENCE_NO", new FieldMapper("referenceNo"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("TXN_POST_USAGE", new FieldMapper("txnPostUsage")).put("TXN_ID", new FieldMapper("txnId"))
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("ROW_ID", new FieldMapper("rowId"))
			.build();
	private final Map<String, FieldMapper> bankReconAuditsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("BANK_UNPOSTED_AMOUNT", new FieldMapper("bankUnpostedAmount"))
			.put("BOOK_CLEARED_COUNT", new FieldMapper("bookClearedCount"))
			.put("BALANCE_DISCREPANCY", new FieldMapper("balanceDiscrepancy"))
			.put("AMNT_PLUS_COMMENT", new FieldMapper("amntPlusComment"))
			.put("BOOK_OUTSTANDING_COUNT", new FieldMapper("bookOutstandingCount"))
			.put("CLEARING_DISCREPANCY", new FieldMapper("clearingDiscrepancy"))
			.put("CLOSING_BOOK_BALANCE", new FieldMapper("closingBookBalance"))
			.put("RECON_ID", new FieldMapper("reconId")).put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.put("BANK_CLEARED_COUNT", new FieldMapper("bankClearedCount"))
			.put("RECON_DATETIME", new FieldMapper("reconDatetime"))
			.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("AMNT_LESS_ADJ", new FieldMapper("amntLessAdj"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("BANK_UNPOSTED_COUNT", new FieldMapper("bankUnpostedCount")).put("STATUS", new FieldMapper("status"))
			.put("AMNT_LESS_COMMENT", new FieldMapper("amntLessComment"))
			.put("BOOK_OUTSTANDING_AMOUNT", new FieldMapper("bookOutstandingAmount"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("BANK_STATEMENT_STATUS", new FieldMapper("bankStatementStatus"))
			.put("BANK_BALANCE", new FieldMapper("bankBalance"))
			.put("BANK_STATEMENT_DATE", new FieldMapper("bankStatementDate"))
			.put("ADJ_BOOK_BAL", new FieldMapper("adjBookBal")).put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("LIST_SEQ", new FieldMapper("listSeq")).put("GL_OPEN_BAL", new FieldMapper("glOpenBal"))
			.put("RECON_USER_ID", new FieldMapper("reconUserId")).put("CASELOAD_ID", new FieldMapper("caseloadId"))
			.put("BOOK_CLEARED_AMOUNT", new FieldMapper("bookClearedAmount"))
			.put("ADJUSTMENT_GL_NUMBER", new FieldMapper("adjustmentGlNumber"))
			.put("ACCOUNT_CODE", new FieldMapper("accountCode"))
			.put("ADJUSTMENT_COMMENT", new FieldMapper("adjustmentComment"))
			.put("CLOSING_BANK_BALANCE", new FieldMapper("closingBankBalance"))
			.put("CREATE_DATE", new FieldMapper("createDate"))
			.put("BANK_CLEARED_AMOUNT", new FieldMapper("bankClearedAmount"))
			.put("AMNT_PLUS_ADJ", new FieldMapper("amntPlusAdj")).build();
	private final Map<String, FieldMapper> systemProfilesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PROFILE_CODE", new FieldMapper("profileCode")).put("PROFILE_TYPE", new FieldMapper("profileType"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId")).put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("OLD_TABLE_NAME", new FieldMapper("oldTableName"))
			.put("PROFILE_VALUE", new FieldMapper("profileValue"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("PROFILE_VALUE_2", new FieldMapper("profileValue2")).put("DESCRIPTION", new FieldMapper("description"))
			.build();
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("ACCOUNT_CODE", new FieldMapper("accountCode")).build();
	private final Map<String, FieldMapper> glTransactionsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("TXN_ENTRY_DATE", new FieldMapper("txnEntryDate"))
			.put("REFERENCE_NO_TYPE", new FieldMapper("referenceNoType"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("TXN_ENTRY_AMOUNT", new FieldMapper("txnEntryAmount"))
			.put("CASELOAD_ID", new FieldMapper("caseloadId")).put("GL_ENTRY_SEQ", new FieldMapper("glEntrySeq"))
			.put("SEAL_FLAG", new FieldMapper("sealFlag")).put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("TXN_ENTRY_SEQ", new FieldMapper("txnEntrySeq")).put("REFERENCE_NO", new FieldMapper("referenceNo"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("TXN_POST_USAGE", new FieldMapper("txnPostUsage")).put("TXN_ID", new FieldMapper("txnId"))
			.put("DESCRIPTION", new FieldMapper("description")).build();
	private final Map<String, FieldMapper> omsModulesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("'LAN'", new FieldMapper(" 'lan'")).put("REPORT_CODE", new FieldMapper("reportCode"))
			.put("DECOD", new FieldMapper("decod")).put("'PORTRAIT')", new FieldMapper(" 'portrait') "))
			.put("'LANDSCAPE'", new FieldMapper(" 'landscape'")).build();
	private final Map<String, FieldMapper> bankReconMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("RECON_CLEAR_FLAG", new FieldMapper("reconClearFlag"))
			.put("BANK_STATEMENT_DATE", new FieldMapper("bankStatementDate"))
			.put("TXN_ENTRY_DATE", new FieldMapper("transactionDate"))
			.put("LESSOUT_CREDITS", new FieldMapper("cgnbtPayeeNameTextOne"))
			.put("PLUSS_OUT_DEBITS", new FieldMapper("cgnbtPayeeNameTextTwo"))
			.put("REFERENCE_NO_TYPE", new FieldMapper("txnReferenceNumber"))
			.build();

	/**
	 * Creates new OtdbacreRepositoryImpl class Object
	 */
	public OtdbacreRepositoryImpl() {
		// OtdbacreRepositoryImpl
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            GlTransactions
	 *
	 * @return List<GlTransactions>
	 *
	 * @
	 */
	public List<GlTransactions> glTxnExecuteQuery(final GlTransactions objSearchDao) {
		final String sql = getQuery("OTDBACRE_GLTXN_FIND_GL_TRANSACTIONS");
		final RowMapper<GlTransactions> GlTransactionsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				GlTransactions.class, glTransactionsMapping);
		final ArrayList<GlTransactions> returnList = (ArrayList<GlTransactions>) namedParameterJdbcTemplate.query(sql,
				createParams(), GlTransactionsRowMapper);
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
	 * @param lstGlTransactions
	 *            List<GlTransactions>
	 *
	 * @return List<Integer>
	 *
	 * @
	 */
	public Integer glTxnInsertGlTransactions(final List<GlTransactions> lstGlTransactions) {
		final String sql = getQuery("OTDBACRE_GLTXN_INSERT_GL_TRANSACTIONS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstGlTransactions.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstGlTransactions
	 *            List<GlTransactions>
	 *
	 * @
	 */
	public Integer glTxnUpdateGlTransactions(final List<GlTransactions> lstGlTransactions) {
		final String sql = getQuery("OTDBACRE_GLTXN_UPDATE_GL_TRANSACTIONS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final GlTransactions glTransactions : lstGlTransactions) {
			parameters.add(new BeanPropertySqlParameterSource(glTransactions));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstGlTransactions.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            BankReconAudits
	 *
	 * @return List<BankReconAudits>
	 *
	 * @
	 */
	public List<BankReconAudits> bankRcExecuteQuery(final BankReconAudits objSearchDao) {
		final String sql = getQuery("OTDBACRE_BANKRC_FIND_BANK_RECON_AUDITS");
		final RowMapper<BankReconAudits> BankReconAuditsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				BankReconAudits.class, bankReconAuditsMapping);
		final ArrayList<BankReconAudits> returnList = (ArrayList<BankReconAudits>) namedParameterJdbcTemplate.query(sql,
				createParams(), BankReconAuditsRowMapper);
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
	 * @
	 */
	public List<SystemProfiles> sysPflExecuteQuery(final SystemProfiles objSearchDao) {
		final String sql = getQuery("OTDBACRE_SYSPFL_FIND_SYSTEM_PROFILES");
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
	 * @
	 */
	public Integer sysPflInsertSystemProfiles(final List<SystemProfiles> lstSystemProfiles) {
		final String sql = getQuery("OTDBACRE_SYSPFL_INSERT_SYSTEM_PROFILES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
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
	 * @
	 */
	public Integer sysPflDeleteSystemProfiles(final List<SystemProfiles> lstSystemProfiles) {
		final String sql = getQuery("OTDBACRE_SYSPFL_DELETE_SYSTEM_PROFILES");
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
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            BankClearReconcilesTmp
	 *
	 * @return List<BankClearReconcilesTmp>
	 *
	 * @
	 */
	public List<BankClearReconcilesTmp> bcrTmpExecuteQuery(final BankClearReconcilesTmp objSearchDao) {
		final String sql = getQuery("OTDBACRE_BCRTMP_FIND_BANK_CLEAR_RECONCILES_TMP");
		final RowMapper<BankClearReconcilesTmp> BankClearReconcilesTmpRowMapper = Row2BeanRowMapper.makeMapping(sql,
				BankClearReconcilesTmp.class, bankClearReconcilesTmpMapping);
		final ArrayList<BankClearReconcilesTmp> returnList = (ArrayList<BankClearReconcilesTmp>) namedParameterJdbcTemplate
				.query(sql, createParams("CASELOAD_ID", objSearchDao.getCaseloadId()), BankClearReconcilesTmpRowMapper);
		return returnList;
	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstBankClearReconcilesTmp
	 *            List<BankClearReconcilesTmp>
	 *
	 * @
	 */
	public Integer bcrTmpUpdateBankClearReconcilesTmp(final List<BankClearReconcilesTmp> lstBankClearReconcilesTmp) {
		final String sql = getQuery("OTDBACRE_BCRTMP_UPDATE_BANK_CLEAR_RECONCILES_TMP");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final BankClearReconcilesTmp bankClearReconcilesTmp : lstBankClearReconcilesTmp) {
			parameters.add(new BeanPropertySqlParameterSource(bankClearReconcilesTmp));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstBankClearReconcilesTmp.size() == returnArray.length) {
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
	public List<AccountCodes> cgfkGlTxnAccountCodeRecordGroup(final String caseloadId, final String caseloadType,String userName) {
		final String sql = getQuery("OTDBACRE_FIND_CGFKGLTXNACCOUNTCODE_FN");
		final RowMapper<AccountCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, AccountCodes.class,
				accountCodesMapping);

		try {
			return namedParameterJdbcTemplate.query(sql,
					createParams("CASELOADID", caseloadId, "CASELOADTYPE", caseloadType,"userId",userName), mRowMapper);
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
		final String sql = getQuery("OTDBACRE_CREATE_FORM_GLOBALS_CREATE_FORM_GLOBALS");
		final RowMapper<OmsModules> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OmsModules.class,
				omsModulesMapping);
		final OmsModules returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkGlTxnGlTxnAcCode
	 *
	 * @param params
	 *
	 */
	public AccountCodes cgfkchkGlTxnGlTxnAcCode(final AccountCodes paramBean) {
		final String sql = getQuery("OTDBACRE_CGFKCHK_GL_TXN_GL_TXN_AC_CODE");
		final RowMapper<AccountCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, AccountCodes.class,
				accountCodesMapping);
		final AccountCodes returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
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
		final String sql = getQuery("OTDBACRE_CGWHEN_NEW_FORM_INSTANCE");
		final List<Object> returnList = (List<Object>) namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				Object.class);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgwhenNewFormInstance
	 *
	 * @param params
	 *
	 */
	public BankReconAudits getPmaxDate(final String caseloadId, final Integer accCode) {
		final String sql = getQuery("OTDBACRE_GET_P_MAX_DATE");
		final RowMapper<BankReconAudits> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, BankReconAudits.class,
				bankReconAuditsMapping);
		final BankReconAudits returnList = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("CASELOAD_ID", caseloadId, "ACCOUNT_CODE", accCode), columnRowMapper);
		return returnList;
	}

	public List<BankChequeRegisters> postQuery(final Long txnId, final Long txnEntrySeq, final Long glEntrySeq,
			final String caseloadId) {
		final String sql = getQuery("OTDBACRE_POSTQUERY");
		final RowMapper<BankChequeRegisters> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				BankChequeRegisters.class, bankReconMapping);
		List<BankChequeRegisters> returnList = new ArrayList<BankChequeRegisters>();
		returnList = namedParameterJdbcTemplate.query(sql, createParams("txnId", txnId, "txnEntrySeq", txnEntrySeq,
				"glEntrySeq", glEntrySeq, "caseloadId", caseloadId), columnRowMapper);
		return returnList;
	}

	public Integer compareEffectiveDatec(final String effectiveDate, final String maxDate) {
		final String sql = getQuery("OTDBACRE_COMPAREEFFECTIVEDATEC");
		Integer value= 0;
		try {
			value =namedParameterJdbcTemplate.queryForObject(sql, createParams("effectiveDate",effectiveDate,"maxDate",maxDate), Integer.class);
		}catch (Exception e) {
			logger.error("Exception in method compareEffectiveDatec "+e);
		}
		return value;
	}

	public List<GlTransactions> getBalancecrdrList(Integer accountCode, String caseloadId) {
		final String sql = getQuery("OTDBACRE_GET_BALANCES_CREDITS_DEBITS");
		final RowMapper<GlTransactions> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				GlTransactions.class, bankReconMapping);
		List<GlTransactions> returnList = new ArrayList<GlTransactions>();
		returnList = namedParameterJdbcTemplate.query(sql, createParams("accountCode",accountCode,"caseloadId",caseloadId),columnRowMapper);
		
		return returnList;
	}

	public BigDecimal getCurrentBalfromCaseloadCurrentTxns(Integer accountCode, String caseloadId) {
		final String sql = getQuery("OTDBACRE_GET_BALANCES_CURRENT_BALANCE");
		BigDecimal balance= null;
		balance = namedParameterJdbcTemplate.queryForObject(sql, createParams("accountCode",accountCode,"caseloadId",caseloadId), BigDecimal.class);
		return balance;
	}

	public String getchcqueFlag(Long txnId, String txnEnterySeq, String glEntrySeq, Date cgnbtBankStatementDate) {
		final String sql = getQuery("OTDBACRE_GETCHCQUEFLAG");
		String val = null;
		try {		
		
		val = namedParameterJdbcTemplate.queryForObject(sql, createParams("txnId",txnId,"txnEnterySeq",txnEnterySeq,
				"glEntrySeq",glEntrySeq), String.class);
		} catch (EmptyResultDataAccessException e) {
			return "N";
			
		}
		return val;
	}

	public Integer updateGlTransactions(Date cgnbtBankStatementDate, Long txnId) {
		final String sql = getQuery("OTDBACRE_UPDATEGLTRANSACTIONSWITHY");
		Integer val = 0;
		val= namedParameterJdbcTemplate.update(sql,createParams("cgnbtBankStatementDate",cgnbtBankStatementDate,"txnId",txnId));
		return val;
		
	}

	public Integer updateGlTransactionswithN( Long txnId) {
		final String sql = getQuery("OTDBACRE_UPDATEGLTRANSACTIONSWITHN");
		Integer val = 0;
		val= namedParameterJdbcTemplate.update(sql,createParams("txnId",txnId));
		return val;
		
}

	public Integer updateBankReconAudits(final BankReconAudits searchBean) {
		final String sql = getQuery("OTDBACRE_UPDATEBANKRECONAUDITS");
		Integer val = 0;
		try{
		val= namedParameterJdbcTemplate.update(sql, createParams("accountCode",searchBean.getAccountCode(),"bankStatemntDate",searchBean.getBankStatementDate(),
				"amountBankBal",searchBean.getBankBalance(),"amntAdjustMinus",searchBean.getAmntLessAdj(),"amntAdjustPlus",searchBean.getAmntPlusAdj(),
				"plusComment",searchBean.getAmntPlusComment(), "minusComment",searchBean.getAmntLessComment()));
		} catch(Exception e){
			logger.error("Exception in method updateBankReconAudits "+e);
			return 0;
		}
		return val;
	}

	public Integer insertBankReconAudits(final BankReconAudits searchBean) {
		final String sql = getQuery("OTDBACRE_INSERTBANKRECONAUDITS");
		Integer val= 0;
		try{
		val=namedParameterJdbcTemplate.update(sql, createParams("caseloadId",searchBean.getCaseloadId(),"accountCode",searchBean.getAccountCode()
				,"bankStatemntDate",searchBean.getBankStatementDate(), "amountBankBal",searchBean.getBankBalance(),
				"amntAdjustMinus",searchBean.getAmntLessAdj(),"amntAdjustPlus",searchBean.getAmntPlusAdj(),"plusComment",searchBean.getAmntPlusComment(),
				"minusComment",searchBean.getAmntLessComment(),"createUserId",searchBean.getCreateUserId()));
		} catch(Exception e){
			logger.error("Exception in method insertBankReconAudits "+e);
			return 0;
		}
		return val;
	}

	public BigDecimal getTrustBal(Long accountCode, String caseloadId) {
		final String sql = getQuery("OTDBACRE_GET_BALANCES_CURRENT_BALANCE");
		BigDecimal trusBal = BigDecimal.ZERO;
		trusBal = namedParameterJdbcTemplate.queryForObject(sql, createParams("accountCode",accountCode,"caseloadId",caseloadId), BigDecimal.class);
		return trusBal;
	}

	public Integer fetchTemp(BankClearReconcilesTmp searchRecord) {
		Integer returnValue=null;
		Map<String, Object> returnObject = null;
		Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[4];
		sqlParameters = new SqlParameter[] {
				new SqlParameter("P_CASELOAD_ID", OracleTypes.VARCHAR),
				new SqlParameter("P_ACCOUNT_CODE", OracleTypes.NUMBER),
				new SqlParameter("P_SELECT_MODE", OracleTypes.VARCHAR),
				new SqlParameter("P_BANK_STATEMENT_DATE", OracleTypes.DATE),
				new SqlParameter("P_LAST_RECON_DATE", OracleTypes.DATE),
				};
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("OTDBACRE").withProcedureName("FETCH_BANK_CLEAR_RECONCILE").declareParameters(sqlParameters);
		inParamMap.put("P_CASELOAD_ID", searchRecord.getCaseloadId());
		inParamMap.put("P_ACCOUNT_CODE", searchRecord.getAccountCode());
		inParamMap.put("P_SELECT_MODE", searchRecord.getCgNbtAccountCode());
		inParamMap.put("P_BANK_STATEMENT_DATE", searchRecord.getCgnbtBankStatementDate());
		inParamMap.put("P_LAST_RECON_DATE", searchRecord.getLastReconciledDate());
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		returnObject =  simpleJDBCCall.execute(inParameter);
		if (returnObject!=null){
			returnValue =1;
		}
		return returnValue;
	}
	
}
