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
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.TransactionTypes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.inmate.beans.GlTransactions;
import net.syscon.s4.inmate.trust.generalledger.OcdcashrRepository;

/**
 * Class OcdcashrRepositoryImpl
 */
@Repository
public class OcdcashrRepositoryImpl extends RepositoryBase implements OcdcashrRepository {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcdcashrRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> accountCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("ACCOUNT_NAME", 					new FieldMapper("accountName"))
			.put("TXN_POSTING_TYPE", 				new FieldMapper("txnPostingType"))
			.put("ACCOUNT_TYPE", 					new FieldMapper("accountType"))
			.put("CASELOAD_TYPE", 					new FieldMapper("caseloadType"))
			.put("ACCOUNT_CODE", 					new FieldMapper("accountCode"))
			.put("SUB_ACCOUNT_TYPE", 				new FieldMapper("subAccountType"))
			.put("CASELOAD_ID", 					new FieldMapper("caseloadId"))
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
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("SUB_ACCOUNT_TYPE", 				new FieldMapper("subAccountType"))
			.put("TXN_POSTING_TYPE", 				new FieldMapper("txnPostingType"))
			.put("ACCOUNT_TYPE", 					new FieldMapper("accountType"))
			.put("CODE", 							new FieldMapper("code"))
			.put("DESCRIPTION", 					new FieldMapper("description"))
			.put("TXN_TYPE", 						new FieldMapper("txnType"))
			.build();
	private final Map<String, FieldMapper> glTransactionsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PAYEE_CLEAR_FLAG", 				new FieldMapper("payeeClearFlag"))
			.put("TXN_REFERENCE_NUMBER", 			new FieldMapper("txnReferenceNumber"))
			.put("REVERSED_TXN_ID", 				new FieldMapper("reversedTxnId"))
			.put("INFO_NUMBER", 					new FieldMapper("infoNumber"))
			.put("TXN_ENTRY_TIME", 					new FieldMapper("txnEntryTime"))
			.put("PAYEE_NAME_TEXT", 				new FieldMapper("payeeNameText"))
			.put("OFFENDER_ID", 					new FieldMapper("offenderId"))
			.put("GL_ENTRY_SEQ", 					new FieldMapper("glEntrySeq"))
			.put("SEAL_FLAG", 						new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", 				new FieldMapper("createDatetime"))
			.put("TXN_TYPE", 						new FieldMapper("txnType"))
			.put("MODIFY_DATETIME", 				new FieldMapper("modifyDatetime"))
			.put("ACCOUNT_PERIOD_ID", 				new FieldMapper("accountPeriodId"))
			.put("TXN_OBJECT_ID", 					new FieldMapper("txnObjectId"))
			.put("PAYEE_PERSON_ID", 				new FieldMapper("payeePersonId"))
			.put("REVERSED_TXN_ENTRY_SEQ", 			new FieldMapper("reversedTxnEntrySeq"))
			.put("TXN_ID", 							new FieldMapper("txnId"))
			.put("OFFENDER_BOOK_ID", 				new FieldMapper("offenderBookId"))
			.put("CREATE_USER_ID", 					new FieldMapper("createUserId"))
			.put("DEDUCTION_ID", 					new FieldMapper("deductionId"))
			.put("TXN_ENTRY_DATE", 					new FieldMapper("txnEntryDate"))
			.put("RECON_CLEAR_FLAG", 				new FieldMapper("reconClearFlag"))
			.put("REVERSED_GL_ENTRY_SEQ", 			new FieldMapper("reversedGlEntrySeq"))
			.put("BANK_STATEMENT_DATE", 			new FieldMapper("bankStatementDate"))
			.put("MODIFY_USER_ID", 					new FieldMapper("modifyUserId"))
			.put("TXN_ENTRY_AMOUNT", 				new FieldMapper("txnEntryAmount"))
			.put("LIST_SEQ", 						new FieldMapper("listSeq"))
			.put("TXN_LOC_ID", 						new FieldMapper("txnLocId"))
			.put("RECEIPT_NUMBER", 					new FieldMapper("receiptNumber"))
			.put("REVERSAL_REASON_CODE", 			new FieldMapper("reversalReasonCode"))
			.put("CASELOAD_ID", 					new FieldMapper("caseloadId"))
			.put("TXN_OBJECT_CODE", 				new FieldMapper("txnObjectCode"))
			.put("TXN_ENTRY_SEQ", 					new FieldMapper("txnEntrySeq"))
			.put("ACCOUNT_CODE", 					new FieldMapper("accountCode"))
			.put("PAYEE_CORPORATE_ID", 				new FieldMapper("payeeCorporateId"))
			.put("CREATE_DATE", 					new FieldMapper("createDate"))
			.put("TXN_REVERSED_FLAG", 				new FieldMapper("txnReversedFlag"))
			.put("TXN_POST_USAGE", 					new FieldMapper("txnPostUsage"))
			.put("TXN_ENTRY_DESC", 					new FieldMapper("txnEntryDesc"))
			.build();

	/**
	 * Creates new OcdcashrRepositoryImpl class Object
	 */
	public OcdcashrRepositoryImpl() {
		// OcdcashrRepositoryImpl
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            GlTransactions
	 *
	 * @return List<GlTransactions>
	 *
	 * 
	 */
	public List<GlTransactions> glTxnExecuteQuery(final GlTransactions objSearchDao) {
		final String sql = getQuery("OCDCASHR_GLTXN_FIND_GL_TRANSACTIONS");
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource params = new MapSqlParameterSource();
		sqlQuery.append(sql);
		String profileValue = profileValueData();
		if (objSearchDao != null) {
			sqlQuery.append(" where ");
			sqlQuery.append(
					" GL_TRANSACTIONS.CASELOAD_ID = :CASELOADID AND EXISTS (SELECT '1' FROM GL_TRANSACTIONS GL1 "
							+ " WHERE GL1.ACCOUNT_CODE =  :ACCOUNTCODE AND GL1.TXN_ID = GL_TRANSACTIONS.TXN_ID "
							+ " AND GL1.TXN_ID = GL_TRANSACTIONS.TXN_ID AND GL1.TXN_ENTRY_SEQ = GL_TRANSACTIONS.TXN_ENTRY_SEQ) "
							+ " AND GL_TRANSACTIONS.TXN_ID > (SELECT coalesce(MAX(GL2.TXN_ID),0) FROM GL_TRANSACTIONS GL2 "
							+ " WHERE GL2.ACCOUNT_CODE =  :ACCOUNTCODE AND GL2.CASELOAD_ID = :CASELOADID ");
			params.addValue("CASELOADID", objSearchDao.getCaseloadId());
			params.addValue("ACCOUNTCODE", objSearchDao.getAccountCode());
			if ("COMM".equals(objSearchDao.getCaseloadType())) {
				sqlQuery.append(" AND GL2.TXN_TYPE = 'CR1' ");
			} else if ("INST".equals(objSearchDao.getCaseloadType())) {
				sqlQuery.append(" AND GL2.TXN_TYPE = 'CR' ");
			}
			if ("Y".equals(profileValue)) {
				sqlQuery.append(" AND GL2.CREATE_USER_ID = :user ) AND GL_TRANSACTIONS.CREATE_USER_ID = :user ");
				params.addValue("user", objSearchDao.getCreateUserId());
			} else {
				sqlQuery.append(" ) ");
			}
		}
		preparedSql = sqlQuery.toString().trim();
		final RowMapper<GlTransactions> GlTransactionsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				GlTransactions.class, glTransactionsMapping);
		List<GlTransactions> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(preparedSql, params, GlTransactionsRowMapper);
		return returnList;
	}

	private String profileValueData() {
		final String sql = getQuery("OCDCASHR_PROFILE_VALUE_DATA");
		String returnFlag = null;
		try {
			returnFlag = namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);
		} catch (Exception e) {
			returnFlag = "N";
			return returnFlag;
		}
		return returnFlag;
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
	 * 
	 */
	public Integer glTxnInsertGlTransactions(final List<GlTransactions> lstGlTransactions) {
		final String sql = getQuery("OCDCASHR_GLTXN_INSERT_GL_TRANSACTIONS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final GlTransactions systemProfiles : lstGlTransactions) {
			parameters.add(new BeanPropertySqlParameterSource(systemProfiles));
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
	 * 
	 */
	public List<SystemProfiles> sysPflExecuteQuery(final SystemProfiles objSearchDao) {
		final String sql = getQuery("OCDCASHR_SYSPFL_FIND_SYSTEM_PROFILES");
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
	 * 
	 */
	public Integer sysPflInsertSystemProfiles(final List<SystemProfiles> lstSystemProfiles) {
		final String sql = getQuery("OCDCASHR_SYSPFL_INSERT_SYSTEM_PROFILES");
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
	 * 
	 */
	public Integer sysPflDeleteSystemProfiles(final List<SystemProfiles> lstSystemProfiles) {
		final String sql = getQuery("OCDCASHR_SYSPFL_DELETE_SYSTEM_PROFILES");
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
	 * Used to capture results from select query
	 * 
	 * @return List<AccountCodes>
	 */
	public List<AccountCodes> cgfkGlTxnAccountCodeRecordGroup(final String caseloadId, final String caseloadType) {
		final String sql = getQuery("OCDCASHR_FIND_CGFKGLTXNACCOUNTCODE");
		final RowMapper<AccountCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, AccountCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql,
					createParams("CASELOADID", caseloadId, "CASELOADTYPE", caseloadType), mRowMapper);
		} catch (Exception e) {
			return Collections.emptyList();
		}
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
		final String sql = getQuery("OCDCASHR_CGFKCHK_GL_TXN_GL_TXN_AC_CODE");
		final RowMapper<AccountCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, AccountCodes.class,
				accountCodesMapping);
		final AccountCodes returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	@Override
	public String receiptNumber(final Long txnId, final Long txnEntrySeq) {
		final String sql = getQuery("OCDCASHR_RECEIPT_NUMBER");
		String returnFlag = null;
		try {
			returnFlag = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("TXNID", txnId, "ENTRYSEQ", txnEntrySeq), String.class);
		} catch (Exception e) {
			return returnFlag;
		}
		return returnFlag;
	}

	@Override
	public String offenderIdDisplayData(final BigDecimal offenderId) {
		final String sql = getQuery("OCDCASHR_OFFENDER_ID_DISPLAY");
		String offenderIdDisp = null;
		try {
			offenderIdDisp = namedParameterJdbcTemplate.queryForObject(sql, createParams("OFFENDERID", offenderId),
					String.class);
		} catch (Exception e) {
			return offenderIdDisp;
		}
		return offenderIdDisp;
	}

	@Override
	public BigDecimal txnEntryAmountData(final Double amtEnt, final Double txnAmt) {
		final String sql = getQuery("OCDCASHR_TXN_ENTRY_AMOUNT");
		BigDecimal txnEntryAmount = null;
		try {
			txnEntryAmount = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("AMTENT", amtEnt, "TXNAMT", txnAmt), BigDecimal.class);
		} catch (Exception e) {
			return txnEntryAmount;
		}
		return txnEntryAmount;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 *
	 * @return List<TransactionTypes>
	 * @param String
	 *            modName, String caseloadId, String caseloadType
	 */
	@Override
	public List<TransactionTypes> txnTypeDescriptionData(final String modName, final String caseloadId,
			final String caseloadType) {
		final String sql = getQuery("OCDCASHR_TXN_TYPE_DESCRIPTION");
		final RowMapper<TransactionTypes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, TransactionTypes.class,
				mMapping);
		List<TransactionTypes> txnEntryAmount = new ArrayList<>();
		try {
			txnEntryAmount = namedParameterJdbcTemplate.query(sql,
					createParams("MODULENAME", modName, "CASELOADID", caseloadId, "CASELOADTYPE", caseloadType),
					columnRowMapper);
		} catch (Exception e) {
			return txnEntryAmount;
		}
		return txnEntryAmount;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 *
	 * @return Integer
	 */
	public Integer txnIdNextValData() {
		final String sql = getQuery("OCDCBENE_TXN_ID");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Integer.class);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 *
	 * @return BigDecimal
	 * @param BigDecimal
	 *            acCode, String caseloadId
	 */
	@Override
	public BigDecimal bankCrAccountCodeData(final BigDecimal acCode, final String caseloadId) {
		final String sql = getQuery("OCDCASHR_BANK_CR_ACCOUNT_CODE");
		BigDecimal txnEntryAmount = null;
		try {
			txnEntryAmount = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("ACCODE", acCode, "CSLDID", caseloadId), BigDecimal.class);
		} catch (Exception e) {
			return txnEntryAmount;
		}
		return txnEntryAmount;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 *
	 * @return BigDecimal
	 * @param BigDecimal
	 *            acCode, String caseloadId
	 */
	@Override
	public BigDecimal crAccountCodeData(final BigDecimal acCode, final String caseloadId) {
		final String sql = getQuery("OCDCASHR_CR_ACCOUNT_CODE");
		BigDecimal txnEntryAmount = null;
		try {
			txnEntryAmount = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("ACCODE", acCode, "CSLDID", caseloadId), BigDecimal.class);
		} catch (Exception e) {
			return txnEntryAmount;
		}
		return txnEntryAmount;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 *
	 * @return BigDecimal
	 * @param BigDecimal
	 *            acCode, String caseloadId
	 */
	@Override
	public BigDecimal drAccountCodeData(final BigDecimal acCode, final String caseloadId) {
		final String sql = getQuery("OCDCASHR_DR_ACCOUNT_CODE");
		BigDecimal txnEntryAmount = null;
		try {
			txnEntryAmount = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("ACCODE", acCode, "CSLDID", caseloadId), BigDecimal.class);
		} catch (Exception e) {
			return txnEntryAmount;
		}
		return txnEntryAmount;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 *
	 * @return AccountCodes
	 * @param BigDecimal
	 *            acCode
	 */
	@Override
	public AccountCodes accountNameTxnPostingType(final BigDecimal nbtAcCode) {
		final String sql = getQuery("OCDCASHR_ACCOUNT_NAME_TXN_POSTING_TYPE");
		final RowMapper<AccountCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, AccountCodes.class,
				mMapping);
		AccountCodes accountCodeData = new AccountCodes();
		try {
			accountCodeData = namedParameterJdbcTemplate.queryForObject(sql, createParams("NBTACCODE", nbtAcCode),
					columnRowMapper);
		} catch (Exception e) {
			return accountCodeData;
		}
		return accountCodeData;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 *
	 * @return AccountCodes
	 * @param BigDecimal
	 *            acCode
	 */
	@Override
	public AccountCodes txnPostingTypeData(final BigDecimal acCode) {
		final String sql = getQuery("OCDCASHR_TXN_POSTING_TYPE");
		final RowMapper<AccountCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, AccountCodes.class,
				mMapping);
		AccountCodes accountCodeData = new AccountCodes();
		try {
			accountCodeData = namedParameterJdbcTemplate.queryForObject(sql, createParams("ACCODE", acCode),
					columnRowMapper);
		} catch (Exception e) {
			return accountCodeData;
		}
		return accountCodeData;
	}

	/**
	 * This method will update the transaction in the table
	 * 
	 * @param String
	 *            string, BigDecimal accountCodeOne, String pDrAccntPosting,
	 *            String caseloadId, String txnType, BigDecimal txnEntryAmount,
	 *            Integer pTxnNum, Date transDate, String adjustDesc, Integer
	 *            pTxnEntrySeq, Integer pGlSeq, BigDecimal offenderId,
	 *            BigDecimal drOffBookId, String txnReferenceNumber
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
	public String profileValData() {
		final String sql = getQuery("OCDCASHR_PROFILE_VALUE_DATA");
		String returnFlag = null;
		try {
			returnFlag = namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);
		} catch (Exception e) {
			returnFlag = "N";
			return returnFlag;
		}
		return returnFlag;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 *
	 * @return BigDecimal
	 * @param BigDecimal
	 *            acCode, String caseloadId
	 */
	@Override
	public String txnAmountData(final String caseloadId, final String caseloadType, final BigDecimal accountCode,
			final String lvMultiCash, final String userName) {
		final String sql = getQuery("OCDCASHR_TXN_AMOUNT");
		String txnEntryAmount = null;
		try {
			txnEntryAmount = namedParameterJdbcTemplate.queryForObject(sql, createParams("CASEID", caseloadId,
					"CASETYPE", caseloadType, "ACCD", accountCode, "LVMULTICASH", lvMultiCash, "CURRENT_USER", userName), String.class);
		} catch (Exception e) {
			return txnEntryAmount;
		}
		return txnEntryAmount;
	}

	@Override
	public String txnAmountDataSlashes(final String returnAmount) {
		final String sql = getQuery("OCDCASHR_TXN_AMOUNT_SLASHES");
		String txnEntryAmount = null;
		try {
			txnEntryAmount = namedParameterJdbcTemplate.queryForObject(sql, createParams("TXNAMOUNT", returnAmount),
					String.class);
		} catch (Exception e) {
			return txnEntryAmount;
		}
		return txnEntryAmount;
	}
	
	@Override
	public SystemProfiles getOldRecords(SystemProfiles beanRef) {
		SystemProfiles returnObj = new SystemProfiles();
		final String sql = getQuery("OCDCASHR_GET_OLD_RECORDS_SYSTEMPROFILES");
		returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("profile_type",beanRef.getProfileType(),
				"profile_code",beanRef.getProfileCode()),SystemProfiles.class);
		if (returnObj != null) {
			return returnObj;
		}
		return returnObj;
	}

}
