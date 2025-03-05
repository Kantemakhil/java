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
import org.springframework.jdbc.core.SqlInOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.TransactionTypes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.im.beans.SysDual;
import net.syscon.s4.inmate.beans.GlTransactions;
import net.syscon.s4.inmate.trust.trustaccounts.OtdaaccoRepository;
import oracle.jdbc.internal.OracleTypes;

/**
 * Class OtdaaccoRepositoryImpl
 * 
 */
@Repository
public class OtdaaccoRepositoryImpl extends RepositoryBase implements OtdaaccoRepository {
	
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OtdaaccoRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> transactionTypesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION",            new FieldMapper("description"))
			.put("CASELOAD_TYPE",          new FieldMapper("caseloadType"))
			.put("TXN_TYPE",               new FieldMapper("txnType"))
			.build();
	
	private final Map<String, FieldMapper> accountCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("ACCOUNT_NAME",           new FieldMapper("accountName"))
			.put("CASELOAD_TYPE",          new FieldMapper("caseloadType"))
			.put("ACCOUNT_CODE",           new FieldMapper("accountCode"))
			.put("CASELOAD_ID",            new FieldMapper("caseloadId"))
			.put("TXN_TYPE",               new FieldMapper("txnType"))
			.put("SUB_ACCOUNT_TYPE",       new FieldMapper("subAccountType"))
			.put("TXN_POSTING_TYPE",       new FieldMapper("txnPostingType"))
			.build();
	
	private final Map<String, FieldMapper> systemProfilesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PROFILE_CODE",           new FieldMapper("profileCode"))
			.put("PROFILE_TYPE",           new FieldMapper("profileType"))
			.put("CREATE_USER_ID",         new FieldMapper("createUserId"))
			.put("SEAL_FLAG",              new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME",        new FieldMapper("createDatetime"))
			.put("MODIFY_USER_ID",         new FieldMapper("modifyUserId"))
			.put("OLD_TABLE_NAME",         new FieldMapper("oldTableName"))
			.put("PROFILE_VALUE",          new FieldMapper("profileValue"))
			.put("MODIFY_DATETIME",        new FieldMapper("modifyDatetime"))
			.put("PROFILE_VALUE_2",        new FieldMapper("profileValue2"))
			.put("DESCRIPTION",            new FieldMapper("description"))
			.build();
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("ACCOUNT_CODE",           new FieldMapper("accountCode"))
			.put("TXN_TYPE",               new FieldMapper("txnType"))
			.put("CODE",                   new FieldMapper("code"))
			.put("DESCRIPTION",            new FieldMapper("description"))
			.build();
	private final Map<String, FieldMapper> glTransactionsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PAYEE_CLEAR_FLAG",       new FieldMapper("payeeClearFlag"))
			.put("TXN_REFERENCE_NUMBER",   new FieldMapper("txnReferenceNumber"))
			.put("REVERSED_TXN_ID",        new FieldMapper("reversedTxnId"))
			.put("INFO_NUMBER",            new FieldMapper("infoNumber"))
			.put("TXN_ENTRY_TIME",         new FieldMapper("txnEntryTime"))
			.put("PAYEE_NAME_TEXT",        new FieldMapper("payeeNameText"))
			.put("OFFENDER_ID",            new FieldMapper("offenderId"))
			.put("GL_ENTRY_SEQ",           new FieldMapper("glEntrySeq"))
			.put("SEAL_FLAG",              new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME",        new FieldMapper("createDatetime"))
			.put("TXN_TYPE",               new FieldMapper("txnType"))
			.put("MODIFY_DATETIME",        new FieldMapper("modifyDatetime"))
			.put("ACCOUNT_PERIOD_ID",      new FieldMapper("accountPeriodId"))
			.put("TXN_OBJECT_ID",          new FieldMapper("txnObjectId"))
			.put("PAYEE_PERSON_ID",        new FieldMapper("payeePersonId"))
			.put("REVERSED_TXN_ENTRY_SEQ", new FieldMapper("reversedTxnEntrySeq"))
			.put("TXN_ID",                 new FieldMapper("txnId"))
			.put("OFFENDER_BOOK_ID",       new FieldMapper("offenderBookId"))
			.put("CREATE_USER_ID",         new FieldMapper("createUserId"))
			.put("DEDUCTION_ID",           new FieldMapper("deductionId"))
			.put("TXN_ENTRY_DATE",         new FieldMapper("txnEntryDate"))
			.put("RECON_CLEAR_FLAG",       new FieldMapper("reconClearFlag"))
			.put("REVERSED_GL_ENTRY_SEQ",  new FieldMapper("reversedGlEntrySeq"))
			.put("BANK_STATEMENT_DATE",    new FieldMapper("bankStatementDate"))
			.put("MODIFY_USER_ID",         new FieldMapper("modifyUserId"))
			.put("TXN_ENTRY_AMOUNT",       new FieldMapper("txnEntryAmount"))
			.put("LIST_SEQ",               new FieldMapper("listSeq"))
			.put("TXN_LOC_ID",             new FieldMapper("txnLocId"))
			.put("RECEIPT_NUMBER",         new FieldMapper("receiptNumber"))
			.put("REVERSAL_REASON_CODE",   new FieldMapper("reversalReasonCode"))
			.put("CASELOAD_ID",            new FieldMapper("caseloadId"))
			.put("TXN_OBJECT_CODE",        new FieldMapper("txnObjectCode"))
			.put("TXN_ENTRY_SEQ",          new FieldMapper("txnEntrySeq"))
			.put("ACCOUNT_CODE",           new FieldMapper("accountCode"))
			.put("PAYEE_CORPORATE_ID",     new FieldMapper("payeeCorporateId"))
			.put("CREATE_DATE",            new FieldMapper("createDate"))
			.put("TXN_REVERSED_FLAG",      new FieldMapper("txnReversedFlag"))
			.put("TXN_POST_USAGE",         new FieldMapper("txnPostUsage"))
			.put("'1'",                    new FieldMapper("  '1' "))
			.put("TXN_ENTRY_DESC",         new FieldMapper("txnEntryDesc")).build();
	
	private final Map<String, FieldMapper> sysDualMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("SYSDATE",                      new FieldMapper("sysDate"))
			.put("USER",                         new FieldMapper("user"))
			.build();
	
	/**
	 * Creates new OtdaaccoRepositoryImpl class Object
	 */
	public OtdaaccoRepositoryImpl() {
		// OtdaaccoRepositoryImpl
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            GlTransactions
	 *
	 * @return List<GlTransactions>
	 *
	 */
	public List<GlTransactions> glTxn1ExecuteQuery(final GlTransactions objSearchDao) {
		final String sql = getQuery("OTDAACCO_GLTXN1_FIND_GL_TRANSACTIONS");
		final RowMapper<GlTransactions> glTransRowMapper = Row2BeanRowMapper.makeMapping(sql, GlTransactions.class,
				glTransactionsMapping);
		final ArrayList<GlTransactions> returnList = (ArrayList<GlTransactions>) namedParameterJdbcTemplate.query(sql,
				createParams(), glTransRowMapper);
		return returnList;
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
	 */
	public Integer glTxn1InsertGlTransactions(final List<GlTransactions> lstGlTransactions) {
		final String sql = getQuery("OTDAACCO_GLTXN1_INSERT_GL_TRANSACTIONS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final GlTransactions obj : lstGlTransactions) {
			parameters.add(new BeanPropertySqlParameterSource(obj));
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
	 *            SystemProfiles
	 *
	 * @return List<SystemProfiles>
	 *
	 */
	public List<SystemProfiles> systemProfilesExecuteQuery(final SystemProfiles objSearchDao) {
		final String sql = getQuery("OTDAACCO_SYSTEMPROFILES_FIND_SYSTEM_PROFILES");
		final RowMapper<SystemProfiles> sysProfRowMapper = Row2BeanRowMapper.makeMapping(sql, SystemProfiles.class,
				systemProfilesMapping);
		final ArrayList<SystemProfiles> returnList = (ArrayList<SystemProfiles>) namedParameterJdbcTemplate.query(sql,
				createParams(), sysProfRowMapper);
		return returnList;
	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstSystemProfiles
	 *            List<SystemProfiles>
	 *
	 */
	public Integer systemProfilesUpdateSystemProfiles(final List<SystemProfiles> lstSystemProfiles) {
		final String sql = getQuery("OTDAACCO_SYSTEMPROFILES_UPDATE_SYSTEM_PROFILES");
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
	public Integer systemProfilesDeleteSystemProfiles(final List<SystemProfiles> lstSystemProfiles) {
		final String sql = getQuery("OTDAACCO_SYSTEMPROFILES_DELETE_SYSTEM_PROFILES");
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
			logger.error("Exception occured in " + this.getClass().getName() + " in method systemProfilesDeleteSystemProfiles", e);
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
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> cgfkGlTxn1TxnTypeRecordGroup(final String caseLoadId, final String caseLoadType) {
		final String sql = getQuery("OTDAACCO_FIND_CGFKGLTXN1TXNTYPE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql,
					createParams("CASELOADID", caseLoadId, "CASELOADTYPE", caseLoadType), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> cgfkGlTxnAccountCodeRecordGroup(final String txnType, final String caseLoadId,
			final String caseLoadType) {
		final String sql = getQuery("OTDAACCO_FIND_CGFKGLTXNACCOUNTCODE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql,
					createParams("TXNTYPE", txnType, "CASELOADID", caseLoadId, "CASELOADTYPE", caseLoadType),
					mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> cgfkGlTxn1AccountCodeRecordGroup() {
		final String sql = getQuery("OTDAACCO_FIND_CGFKGLTXN1ACCOUNTCODE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkGlTxn1GlTxnTxnTy
	 *
	 * @param params
	 *
	 */
	public TransactionTypes cgfkchkGlTxn1GlTxnTxnTy(final TransactionTypes paramBean) {
		final String sql = getQuery("OTDAACCO_CGFKCHK_GL_TXN1_GL_TXN_TXN_TY");
		final RowMapper<TransactionTypes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, TransactionTypes.class,
				transactionTypesMapping);
		final TransactionTypes returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(paramBean),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkGlTxn1GlTxnAcCod
	 *
	 * @param params
	 *
	 */
	public AccountCodes cgfkchkGlTxn1GlTxnAcCod(final AccountCodes paramBean) {
		final String sql = getQuery("OTDAACCO_CGFKCHK_GL_TXN1_GL_TXN_AC_COD");
		final RowMapper<AccountCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, AccountCodes.class,
				accountCodesMapping);
		final AccountCodes returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(paramBean),
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
		final String sql = getQuery("OTDAACCO_CGWHEN_NEW_FORM_INSTANCE");
		final RowMapper<SysDual> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, SysDual.class, sysDualMapping);
		final ArrayList<SysDual> returnList = (ArrayList<SysDual>) namedParameterJdbcTemplate.query(sql,
				createParams(paramBean), columnRowMapper);
		return returnList;
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
		final String sql = getQuery("OTDAACCO_CGFKCHK_GL_TXN_GL_TXN_AC_CODE");
		final RowMapper<AccountCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, AccountCodes.class,
				accountCodesMapping);
		final AccountCodes returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(paramBean),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgrichkGlTransactions
	 *
	 * @param params
	 *
	 */
	public List<GlTransactions> cgrichkGlTransactions(final GlTransactions paramBean) {
		final String sql = getQuery("OTDAACCO_CGRICHK_GL_TRANSACTIONS");
		final RowMapper<GlTransactions> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, GlTransactions.class,
				glTransactionsMapping);
		final ArrayList<GlTransactions> returnList = (ArrayList<GlTransactions>) namedParameterJdbcTemplate.query(sql,
				createParams(paramBean), columnRowMapper);
		return returnList;
	}

	@Override
	public Integer systemProfilesInsertSystemProfiles(final List<SystemProfiles> lstSystemProfiles) {
		final String sql = getQuery("OTDAACCO_SYSTEMPROFILES_INSERT_SYSTEM_PROFILES");
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

	@Override
	public BigDecimal overDraftAmount(final String caseloadId, final BigDecimal offenderId,
			final BigDecimal accountCodeOne, final BigDecimal txnEntryAmount) {
		final String sql = getQuery("OTDAACCO_OVER_DRAFT");
		BigDecimal returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("PCSLD", caseloadId, "POFFID",
					offenderId, "ACCNTCODE", accountCodeOne, "PAMOUNT", txnEntryAmount), BigDecimal.class);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}

	@Override
	public String overDraftAmountTotal(final String caseloadId, final BigDecimal offenderId,
			final BigDecimal accountCodeOne) {
		final String sql = getQuery("OTDAACCO_OVER_DRAFT_TTT");
		String returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("PCSLD", caseloadId, "POFFID", offenderId, "ACCNTCODE", accountCodeOne), String.class);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}

	@Override
	public String transactionTypesY(final String txnType, final String caseloadId) {
		final String sql = getQuery("OTDAACCO_TRANSACTION_TYPES_Y");
		String returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("PTXNTYPE", txnType, "PCSLD", caseloadId), String.class);
		} catch (Exception e) {
			returnObj = "N";
			return returnObj;
		}
		return returnObj;
	}

	@Override
	public String subAccountType(final BigDecimal accountCodeOne) {
		final String sql = getQuery("OTDAACCO_SUB_ACCOUNT_TYPE");
		String returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("ACCNTCODE", accountCodeOne),
					String.class);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}

	@Override
	public BigDecimal maxOffenderBookId(final String caseloadId, final BigDecimal offenderId) {
		final String sql = getQuery("OTDAACCO_MAX_OFFENDER_BOOK_ID");
		BigDecimal returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("PCSLD", caseloadId, "POFFID", offenderId), BigDecimal.class);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 *
	 * @return Integer
	 */
	public Integer txnIdNextValData() {
		final String sql = getQuery("OTDAACCo_TXN_ID");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Integer.class);
	}

	/**
	 * This method will update the transaction in the table
	 * 
	 * @param offTrans
	 * @return Integer
	 */
	@Override
	public Integer chkOverdrawnData(final String caseloadId, final BigDecimal offenderId, final String subAccountType,
			final BigDecimal txnEntryAmount, final String txnType, final Integer txnEntrySeq, final String checkInd,
			final String moduleName, final Integer txnId, final BigDecimal offenderBookId, final Integer txnFee) {
		Integer genSeq = 0;
		try {
			final Map<String, Object> inParamMap = new HashMap<String, Object>();
			inParamMap.put("p_csld_id", caseloadId);
			inParamMap.put("p_off_id", offenderId);
			inParamMap.put("p_sub_act_type", subAccountType);
			inParamMap.put("trans_amount", txnEntryAmount);
			inParamMap.put("txntype", txnType);
			inParamMap.put("seq_no", txnEntrySeq);
			inParamMap.put("check_ind", checkInd);
			inParamMap.put("mod_name", moduleName);
			inParamMap.put("p_txn_id", txnId);
			inParamMap.put("p_off_bid", offenderBookId);
			inParamMap.put("p_txn_fee", txnFee);
			inParamMap.put("p_setup_caseload", null);
			namedParameterJdbcTemplate
					.update("call OMS_OWNER.TRUST.CHK_OVERDRAWN(:p_csld_id, :p_off_id, :p_sub_act_type, :trans_amount, :txntype, :seq_no, :check_ind,"
							+ " :mod_name, :p_txn_id, :p_off_bid, :p_txn_fee, :p_setup_caseload)", inParamMap);
			genSeq = 1;
		} catch (Exception e) {
			genSeq = 0;
		}
		return genSeq;
	}

	@Override
	public AccountCodes drAccountCodesData(final BigDecimal accountCodeOne) {
		final String sql = getQuery("OTDAACCO_DR_ACCOUNT_CODE");
		final RowMapper<AccountCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, AccountCodes.class,
				accountCodesMapping);
		AccountCodes returnObj = null;
		try {
			returnObj = new AccountCodes();
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("PDRACCOUNTCODE", accountCodeOne),
					columnRowMapper);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}

	@Override
	public AccountCodes crAccountCodesData(final BigDecimal accountCodeTwo) {
		final String sql = getQuery("OTDAACCO_CR_ACCOUNT_CODE");
		final RowMapper<AccountCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, AccountCodes.class,
				accountCodesMapping);
		AccountCodes returnObj = null;
		try {
			returnObj = new AccountCodes();
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("PCRACCOUNTCODE", accountCodeTwo),
					columnRowMapper);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}

	public Integer insertIntoOffenderTransaction(final Integer pTxnNum, final Integer pTxnEntrySeq,
			final String caseloadId, final BigDecimal offenderId, final BigDecimal drOffBookId,
			final String txnPostingType, final String txnType, final String adjustDesc, final BigDecimal txnEntryAmount,
			final Date transDate, final String pDrSubAccountType, final String reconClearFlag) {
		Integer genSeq = 0;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("p_trans_number", pTxnNum);
		inParamMap.put("p_trans_seq", pTxnEntrySeq);
		inParamMap.put("p_csld_id", caseloadId);
		inParamMap.put("p_off_id", offenderId);
		inParamMap.put("p_off_book_id", drOffBookId);
		inParamMap.put("p_trans_post_type", txnPostingType);
		inParamMap.put("p_trans_type", txnType);
		inParamMap.put("p_trans_desc", adjustDesc);
		inParamMap.put("p_trans_amount", txnEntryAmount);
		inParamMap.put("p_trans_date", transDate);
		inParamMap.put("p_sub_act_type", pDrSubAccountType);
		inParamMap.put("p_deduction_flag", null);
		inParamMap.put("p_pre_ded_amount", null);
		inParamMap.put("p_deduction_type", null);
		inParamMap.put("p_payee_corp_id", null);
		inParamMap.put("p_payee_person_id", null);
		inParamMap.put("p_info_number", null);
		inParamMap.put("p_slip_print_flag", "N");
		inParamMap.put("p_allow_overdrawn", reconClearFlag);
		try {
			namedParameterJdbcTemplate.update(
					" call OMS_OWNER.TRUST.INSERT_INTO_OFFENDER_TRANS(:p_trans_number, :p_trans_seq, :p_csld_id, :p_off_id, :p_off_book_id, :p_trans_post_type, "
							+ " :p_trans_type, :p_trans_desc, :p_trans_amount, :p_trans_date, :p_sub_act_type, :p_deduction_flag, :p_pre_ded_amount, :p_deduction_type, "
							+ " :p_payee_corp_id, :p_payee_person_id, :p_info_number, :p_slip_print_flag, :p_allow_overdrawn)",
					inParamMap);
			genSeq = 1;
		} catch (Exception e) {
			logger.error("insertIntoOffenderTransaction :" + e);
			genSeq = 0;
			return genSeq;
		}
		return genSeq;
	}

	/**
	 * This method will update the transaction in the table
	 * 
	 * @param offTrans
	 * @return Integer
	 */
	@Override
	public Integer insertGlTransNew(final String string, final BigDecimal accountCodeOne, final String pDrAccntPosting,
			final String caseloadId, final String txnType, final BigDecimal txnEntryAmount, final Integer pTxnNum,
			final Date transDate, final String adjustDesc, final Integer pTxnEntrySeq, final Integer pGlSeq,
			final BigDecimal offenderId, final BigDecimal drOffBookId, final String txnReferenceNumber) {
		Integer genSeq = 0;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("p_post_type", string);
		inParamMap.put("p_account_code", accountCodeOne);
		inParamMap.put("p_acnt_posting", pDrAccntPosting);
		inParamMap.put("p_csld_id", caseloadId);
		inParamMap.put("p_trans_type", txnType);
		inParamMap.put("p_trans_amount", txnEntryAmount);
		inParamMap.put("p_trans_number", pTxnNum);
		inParamMap.put("p_trans_date", transDate);
		inParamMap.put("p_trans_desc", adjustDesc);
		inParamMap.put("p_trans_seq", pTxnEntrySeq);
		inParamMap.put("p_gl_sqnc", pGlSeq);
		inParamMap.put("p_off_id", offenderId);
		inParamMap.put("p_off_book_id", drOffBookId);
		inParamMap.put("p_info_number", null);
		inParamMap.put("p_payee_person_id", null);
		inParamMap.put("p_payee_corporate_id", null);
		inParamMap.put("p_payee_name_text", null);
		inParamMap.put("p_revr_txn_id", null);
		inParamMap.put("p_revr_txn_entry_seq", null);
		inParamMap.put("p_revr_gl_entry_seq", null);
		inParamMap.put("p_txn_ref_number", txnReferenceNumber);
		inParamMap.put("p_off_ded_id", null);
		try {
			namedParameterJdbcTemplate.update(
					"call OMS_OWNER.TRUST.INSERT_GL_TRANS_NEW(:p_post_type, :p_account_code, :p_acnt_posting, :p_csld_id, :p_trans_type, :p_trans_amount, :p_trans_number,"
							+ " :p_trans_date, :p_trans_desc, :p_trans_seq, :p_gl_sqnc, :p_off_id, :p_off_book_id, :p_info_number, :p_payee_person_id, :p_payee_corporate_id,"
							+ " :p_payee_name_text, :p_revr_txn_id, :p_revr_txn_entry_seq, :p_revr_gl_entry_seq, :p_txn_ref_number, :p_off_ded_id)",
					inParamMap);
			genSeq = 1;
		} catch (Exception e) {
			logger.error("insertGlTransNew :" + e);
			genSeq = 0;
			return genSeq;
		}
		return genSeq;
	}

	@Override
	public Integer financialDoDuctionsFinancial(final String caseloadId, final BigDecimal nbtOffenderId,
			final BigDecimal crOffBookId, final String txnType, final Integer pTxnNum, final Date transDate,
			final String pCrSubAccountType, final String string, final BigDecimal txnEntryAmount,
			final BigDecimal txnEntryAmount2, final Integer pTxnEntrySeq) {
		Integer genSeq = 0;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("P_CSLD_ID", caseloadId);
		inParamMap.put("P_OFF_ID", nbtOffenderId);
		inParamMap.put("P_OFF_BOOK_ID", crOffBookId);
		inParamMap.put("P_TRANS_TYPE", txnType);
		inParamMap.put("P_TRANS_NUMBER", pTxnNum);
		inParamMap.put("P_TRANS_DATE", transDate);
		inParamMap.put("P_SUB_ACT_TYPE", pCrSubAccountType);
		inParamMap.put("P_DED_FLAG", string);
		inParamMap.put("P_RECEIPT_AMOUNT", txnEntryAmount);
		inParamMap.put("P_SHADOW_ID", null);
		inParamMap.put("P_DED_AMOUNT", txnEntryAmount2);
		inParamMap.put("TXN_SEQUENCE", pTxnEntrySeq);
		inParamMap.put("P_INFO_NUMBER", null);
		inParamMap.put("P_MODULE_NAME", "OTDAACCO");
		try {
			namedParameterJdbcTemplate
					.update("call OMS_OWNER.FINANCIAL.DO_DEDUCTIONS_FINANCIAL(:P_CSLD_ID, :P_OFF_ID, :P_OFF_BOOK_ID, :P_TRANS_TYPE, "
							+ ":P_TRANS_NUMBER, :P_TRANS_DATE, :P_SUB_ACT_TYPE, :P_DED_FLAG, :P_RECEIPT_AMOUNT, :P_SHADOW_ID, :P_DED_AMOUNT, "
							+ ":TXN_SEQUENCE, :P_INFO_NUMBER, :P_MODULE_NAME)", inParamMap);
			genSeq = 1;
		} catch (Exception e) {
			logger.error("financialDoDuctionsFinancial :" + e);
			genSeq = 0;
			return genSeq;
		}
		return genSeq;
	}

	/**
	 * This method will update the transaction in the table
	 * 
	 * @param offTrans
	 * @return Integer
	 */
	public String getAcAndSetIndDate(final BigDecimal offenderId, final String caseloadId) {
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

	@Override
	public String checkNavigation(final BigDecimal accountCode) {
		final String sql = getQuery("OTDAACCO_CHECK_NAVIGATION");
		String returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("ACNTCODE", accountCode),
					String.class);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}

	@Override
	public String chkOffAcnt(final BigDecimal accountCode) {
		final String sql = getQuery("OTDAACCO_CHK_OFF_ACNT");
		String returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("ACNTCODE", accountCode),
					String.class);
			if (returnObj != null) {
				returnObj = "Y";
			}
		} catch (Exception e) {
			returnObj = "N";
			return returnObj;
		}
		return returnObj;
	}

	@Override
	public BigDecimal findRootOffenderId(final String offenderIdDisplay) {
		final String sql = getQuery("OTDAACCO_ROOT_OFFENDER_ID");
		BigDecimal returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("POFFIDDSP", offenderIdDisplay),
					BigDecimal.class);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}

	@Override
	public String findOffenderIdDisplay(final Long offenderId) {
		final String sql = getQuery("OTDAACCO_ROOT_OFFENDER_ID");
		String returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("OFFID", offenderId), String.class);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}

	@Override
	public String currentCaseloadValidation(final BigDecimal offenderId, final String caseloadId,
			final BigDecimal acccountCodeOnde) {
		final String sql = getQuery("OTDAACCO_CURRENT_CASELOAD_VALID");
		String returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("DROFFID", offenderId, "PCASLDID", caseloadId, "DRACCOUNTCODE", acccountCodeOnde),
					String.class);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}

	@Override
	public String closedAccountValidation(final BigDecimal offenderId, final String caseloadId) {
		final String sql = getQuery("OTDAACCO_CLOSED_ACCOUNT_VALID");
		String returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("DROFFID", offenderId, "PCASLDID", caseloadId), String.class);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}

	@Override
	public String drAccountCode(final BigDecimal drAccountCode) {
		final String sql = getQuery("OTDAACCO_P_DR_ACCOUNT_CODE");
		String returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("PDRACCOUNTCODE", drAccountCode),
					String.class);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}

	@Override
	public Integer drAccountCodeCaseloadId(final String caseloadId, final BigDecimal drAccountCode) {
		final String sql = getQuery("OTDAACCO_P_CSLD_ID_OTDAACCO_P_DR_ACCOUNT_CODE");
		Integer returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("PCSLDID", caseloadId, "PDRACCOUNTCODE", drAccountCode), Integer.class);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}

	@Override
	public String crAccountCode(final BigDecimal drAccountCode) {
		final String sql = getQuery("OTDAACCO_P_CR_ACCOUNT_CODE");
		String returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("PDRACCOUNTCODE", drAccountCode),
					String.class);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}

	public String whenCheckBoxChecked(final String caseloadId, final BigDecimal offfenderId, final String txnType) {
		SqlParameter[] sqlParameters = new SqlParameter[] { new SqlParameter("P_CSLD_ID", OracleTypes.VARCHAR),
				new SqlParameter("P_OFF_ID", OracleTypes.NUMBER), new SqlParameter("P_TRANS_TYPE", OracleTypes.VARCHAR),
				new SqlParameter("P_SHADOW_ID", OracleTypes.NUMBER),
				new SqlInOutParameter("P_DED_FLAG", OracleTypes.VARCHAR), };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("DEDUCTIONS").withProcedureName("CHK_OFFENDER_DEDUCTIONS")
				.declareParameters(sqlParameters);
		Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("P_CSLD_ID", caseloadId);
		inParamMap.put("P_OFF_ID", offfenderId);
		inParamMap.put("P_TRANS_TYPE", txnType);
		inParamMap.put("P_SHADOW_ID", 0);
		inParamMap.put("P_DED_FLAG", "N");
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		final Map<String, Object> returnObject = simpleJDBCCall.execute(inParameter);
		if (returnObject.get("P_DED_FLAG") != null) {
			return returnObject.get("P_DED_FLAG").toString();
		}

		return "";
	}
}
