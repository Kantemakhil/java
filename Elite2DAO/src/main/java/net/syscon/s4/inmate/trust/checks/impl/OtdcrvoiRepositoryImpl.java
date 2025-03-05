package net.syscon.s4.inmate.trust.checks.impl;

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
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.BankChequeData;
import net.syscon.s4.im.beans.CaseloadCurrentAccounts;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.inmate.beans.BankChequeRegisters;
import net.syscon.s4.inmate.beans.BeneficiaryTransactions;
import net.syscon.s4.inmate.trust.checks.OtdcrvoiRepository;
import oracle.jdbc.internal.OracleTypes;

/**
 * Class OtdcrvoiRepositoryImpl
 */
@Repository
public class OtdcrvoiRepositoryImpl extends RepositoryBase implements OtdcrvoiRepository {

	private static Logger logger = LogManager.getLogger(OtdcrvoiRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> refCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("ACCOUNT_CODE",          new FieldMapper("accountCode"))
			.put("CASELOAD_ID",           new FieldMapper("caseloadId"))
			.put("CODE",                  new FieldMapper("code"))
			.put("DESCRIPTION",           new FieldMapper("description"))
			.build();
	
	private final Map<String, FieldMapper> bankChequeRegistersMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CREATE_USER_ID",        new FieldMapper("createUserId"))
			.put("END_TXN_ID",            new FieldMapper("endTxnId"))
			.put("MODIFY_USER_ID",        new FieldMapper("modifyUserId"))
			.put("LIST_SEQ",              new FieldMapper("listSeq"))
			.put("CHEQUE_PAID_DATE",      new FieldMapper("chequePaidDate"))
			.put("CHEQUE_NUMBER",         new FieldMapper("chequeNumber"))
			.put("ORIGIN_TYPE",           new FieldMapper("originType"))
			.put("RCNL_CLR",              new FieldMapper("rcnlClr"))
			.put("CHEQUE_STATUS",         new FieldMapper("chequeStatus"))
			.put("CASELOAD_ID",           new FieldMapper("caseLoadId"))
			.put("SEAL_FLAG",             new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME",       new FieldMapper("createDatetime"))
			.put("ACCOUNT_CODE",          new FieldMapper("accountCode"))
			.put("TRANSACTION_DATE",      new FieldMapper("transactionDate"))
			.put("REASON_TEXT",           new FieldMapper("reasonText"))
			.put("MODIFY_DATETIME",       new FieldMapper("modifyDatetime"))
			.put("CREATE_DATE",           new FieldMapper("createDate"))
			.put("START_TXN_ID",          new FieldMapper("startTxnId"))
			.put("TXN_ID",                new FieldMapper("txnId"))
			.build();
	
	private final Map<String, FieldMapper> bankChequeDataMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("SEAL_FLAG",              new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME",        new FieldMapper("createDatetime"))
			.put("MODIFY_DATETIME",        new FieldMapper("modifyDatetime"))
			.put("CHEQUE_FLAG",            new FieldMapper("chequeFlag"))
			.put("PAYEE_PERSON_ID",        new FieldMapper("payeePersonId"))
			.put("PAYEE_CORPORATE_ID",     new FieldMapper("payeeCorporateId"))
			.put("CHEQUE_DATE",            new FieldMapper("chequeDate"))
			.put("TXN_ID",                 new FieldMapper("txnId"))
			.put("START_TXN_ID",           new FieldMapper("startTxnId"))
			.put("OFFENDER_ID",           new FieldMapper("offenderId"))
			.build();
	
	private final Map<String, FieldMapper> caseloadCurrentAccountsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("ACCOUNT_NAME",           new FieldMapper("accountName"))
			.put("ACCOUNT_CODE",           new FieldMapper("accountCode"))
			.put("CASELOAD_ID",            new FieldMapper("caseloadId"))
			.build();
	private final Map<String, FieldMapper> cOneMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("TXN_ENTRY_SEQ"	,new FieldMapper("txnEntrySeq"))
			.put("GL_ENTRY_SEQ"		,new FieldMapper("glEntrySeq"))
			.put("CASELOAD_ID"		,new FieldMapper("caseloadId"))
			.put("ACCOUNT_CODE"		,new FieldMapper("accountCode"))
			.put("TXN_ENTRY_AMOUNT"	,new FieldMapper("txnEntryAmount"))
			.put("TXN_TYPE"			,new FieldMapper("txnType"))
			.put("TXN_POST_USAGE"	,new FieldMapper("txnPostUsage"))
			.put("TXN_POSTING_TYPE"	,new FieldMapper("txnPostingType"))
			.put("OFFENDER_ID"		,new FieldMapper("offenderId"))
			.put("OFFENDER_BOOK_ID"	,new FieldMapper("offenderBookId"))
			.put("PAYEE_PERSON_ID"	,new FieldMapper("payeePersonId"))
			.put("PAYEE_CORPORATE_ID",new FieldMapper("payeeCorporateId"))
			.build();
	private final Map<String, FieldMapper> cOffMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("TXN_ENTRY_SEQ",		new FieldMapper("txnEntrySeq"))
			.put("CASELOAD_ID",			new FieldMapper("caseloadId"))
			.put("TXN_POSTING_TYPE",	new FieldMapper("txnPostingType"))
			.put("SUB_ACCOUNT_TYPE",	new FieldMapper("subAccountType"))
			.put("TXN_ENTRY_AMOUNT",	new FieldMapper("txnEntryAmount"))
			.put("TXN_TYPE",			new FieldMapper("txnType"))
			.put("OFFENDER_ID",			new FieldMapper("offenderId"))
			.put("OFFENDER_BOOK_ID",	new FieldMapper("offenderBookId"))
			.put("PAYEE_PERSON_ID",		new FieldMapper("payeePersonId"))
			.put("PAYEE_CORPORATE_ID",	new FieldMapper("payeeCorporateId"))
			.build();
	
	private final Map<String, FieldMapper> cChequeMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("TXN_ID",		new FieldMapper("txnId"))
			.put("PERSON_ID",			new FieldMapper("personId"))
			.put("CORPORATE_ID",	new FieldMapper("corporateId"))
			.put("AMOUNT",	new FieldMapper("amount"))
			.put("OFFENDER_DEDUCTION_ID",	new FieldMapper("offenderDeductionId"))
			.build();

	/**
	 * Creates new OtdcrvoiRepositoryImpl class Object
	 */
	public OtdcrvoiRepositoryImpl() {
		// OtdcrvoiRepositoryImpl
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            BankChequeRegisters
	 *
	 * @return List<BankChequeRegisters>
	 *
	 */
	public List<BankChequeRegisters> bankCrExecuteQuery(final BankChequeRegisters objSearchDao) {
		final String sql = getQuery("OTDCRVOI_BANKCR_FIND_BANK_CHEQUE_REGISTERS");
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource params = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" where ");
			if (objSearchDao.getCaseLoadId() != null) {
				sqlQuery.append("CASELOAD_ID = :GLOBALCASELOADID" + " and ");
				params.addValue("GLOBALCASELOADID", objSearchDao.getCaseLoadId());
			}
			if (objSearchDao.getAccountCode() != null) {
				sqlQuery.append("ACCOUNT_CODE = :ACCOUNT_CODE" + " and ");
				params.addValue("ACCOUNT_CODE", objSearchDao.getAccountCode());
			}
			if (objSearchDao.getChequeNumber() != null) {
				sqlQuery.append("CHEQUE_NUMBER = :CHEQUE_NUMBER" + " and ");
				params.addValue("CHEQUE_NUMBER", objSearchDao.getChequeNumber());
			}
			if (objSearchDao.getCreateDate() != null) {
				sqlQuery.append("CREATE_DATE = :CREATE_DATE" + " and ");
				params.addValue("CREATE_DATE", objSearchDao.getCreateDate());
			}
			if (objSearchDao.getTxnId() != null) {
				sqlQuery.append("TXN_ID = :TXN_ID" + " and ");
				params.addValue("TXN_ID", objSearchDao.getTxnId());
			}
			if (objSearchDao.getChequeStatus() != null) {
				sqlQuery.append("CHEQUE_STATUS = :CHEQUE_STATUS" + " and ");
				params.addValue("CHEQUE_STATUS", objSearchDao.getChequeStatus());
			}
			if (objSearchDao.getReasonText() != null) {
				sqlQuery.append("REASON_TEXT = :REASON_TEXT" + " and ");
				params.addValue("REASON_TEXT", objSearchDao.getReasonText());
			}
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("where")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("and")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		preparedSql = preparedSql.concat(" ORDER BY CHEQUE_NUMBER DESC, ACCOUNT_CODE ");
		final RowMapper<BankChequeRegisters> bankChequeRegMap = Row2BeanRowMapper.makeMapping(sql,
				BankChequeRegisters.class, bankChequeRegistersMapping);
		final ArrayList<BankChequeRegisters> returnList = (ArrayList<BankChequeRegisters>) namedParameterJdbcTemplate
				.query(preparedSql, params, bankChequeRegMap);
		return returnList;
	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstBankChequeRegisters
	 *            List<BankChequeRegisters>
	 *
	 */
	public Integer bankCrUpdateBankChequeRegisters(final List<BankChequeRegisters> list) {
		final String sql = getQuery("OTDCRVOI_BANKCR_UPDATE_BANK_CHEQUE_REGISTERS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final BankChequeRegisters bankChequeReg : list) {
			parameters.add(new BeanPropertySqlParameterSource(bankChequeReg));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (list.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            BankChequeData
	 *
	 * @return List<BankChequeData>
	 *
	 */
	public List<BankChequeData> bnkCdExecuteQuery(final BankChequeData objSearchDao) {
		final String sql = getQuery("OTDCRVOI_BNKCD_FIND_BANK_CHEQUE_DATA");
		final RowMapper<BankChequeData> bankChequeDataMap = Row2BeanRowMapper.makeMapping(sql, BankChequeData.class,
				bankChequeDataMapping);
		final ArrayList<BankChequeData> returnList = (ArrayList<BankChequeData>) namedParameterJdbcTemplate.query(sql,
				createParams("TXN_ID", objSearchDao.getTxnId()), bankChequeDataMap);
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> cgfkBankCrChequeStatusRecordGroup() {
		final String sql = getQuery("OTDCRVOI_FIND_CGFKBANKCRCHEQUESTATUS");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				refCodesMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> cgfkBankCrAccountCodeRecordGroup(final String caseloadId,String userName) {
		final String sql = getQuery("OTDCRVOI_FIND_CGFKBANKCRACCOUNTCODE");
		final RowMapper<ReferenceCodes> mMRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				refCodesMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams("caseloadId", caseloadId,"userId",userName), mMRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * bankCrOnCheckDeleteMaster
	 *
	 * @param params
	 *
	 */
	public BankChequeData bankCrOnCheckDeleteMaster(final BankChequeData paramBean) {
		final String sql = getQuery("OTDCRVOI_BANK_CR_ONCHECKDELETEMASTER");
		final RowMapper<BankChequeData> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, BankChequeData.class,
				bankChequeDataMapping);
		BankChequeData returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(paramBean),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkBankCrBankCrRef
	 *
	 * @param params
	 *
	 */
	public ReferenceCodes cgfkchkBankCrBankCrRef(final ReferenceCodes paramBean) {
		final String sql = getQuery("OTDCRVOI_CGFKCHK_BANK_CR_BANK_CR_REF_C");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				refCodesMapping);
		ReferenceCodes returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(paramBean),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkBankCrBankCrGlAc
	 *
	 * @param params
	 *
	 */
	public Object cgfkchkBankCrBankCrGlAc(final CaseloadCurrentAccounts paramBean) {
		final String sql = getQuery("OTDCRVOI_CGFKCHK_BANK_CR_BANK_CR_GL_AC");
		final RowMapper<CaseloadCurrentAccounts> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				CaseloadCurrentAccounts.class, caseloadCurrentAccountsMapping);
		CaseloadCurrentAccounts returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(paramBean),
				columnRowMapper);
		return returnObj;
	}

	@Override
	public String chkAccountStatus(String caseloadId, BigDecimal offenderId) {
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

	@Override
	public String whenValidateGetCheckTxnType(final BigDecimal txnId) {
		final String sql = getQuery("OTDCRVOI_WHEN_VALIDATE_GET_CHECK_TXN_TYPE");
		String returnValue = null;
		try {
			returnValue = namedParameterJdbcTemplate.queryForObject(sql, createParams("txnId", txnId), String.class);
		} catch (Exception e) {
			logger.error("OTDCRVOI_WHEN_VALIDATE_GET_CHECK_TXN_TYPE :", e);
		}
		return returnValue;
	}

	@Override
	public String whenValidateGetCheckClearTxn(final BigDecimal txnId) {
		final String sql = getQuery("OTDCRVOI_WHEN_VALIDATE_GET_CHECK_CLEAR_TXN");
		String returnValue = null;
		try {
			returnValue = namedParameterJdbcTemplate.queryForObject(sql, createParams("txnId", txnId), String.class);
		} catch (Exception e) {
			logger.error("OTDCRVOI_WHEN_VALIDATE_GET_CHECK_CLEAR_TXN :", e);
		}
		return returnValue;
	}

	@Override
	public List<String> verifyTxnTypeCount(final BigDecimal txnId) {
		final String sql = getQuery("OTDCRVOI_VERIFY_TXN_TYPE_COUNT");
		List<String> resultList = new ArrayList<>();
		try {
			resultList = namedParameterJdbcTemplate.queryForList(sql, createParams("txnId", txnId), String.class);
		} catch (Exception e) {
			logger.error("OTDCRVOI_VERIFY_TXN_TYPE_COUNT :", e);
		}
		return resultList;
	}

	@Override
	public List<OffenderTransactions> getCOneOffenderDetail(final BigDecimal txnId, final String chequeFlag,
			final String payeeNameText) {
		final String sql = getQuery("OTDCRVOI_GET_C_ONE_OFFENDER_DETAIL");
		final RowMapper<OffenderTransactions> cOneMap = Row2BeanRowMapper.makeMapping(sql, OffenderTransactions.class,
				cOneMapping);
		final List<OffenderTransactions> returnList = namedParameterJdbcTemplate.query(sql,
				createParams("txnId", txnId, "chequeFlag", chequeFlag, "payeeNameText", payeeNameText), cOneMap);
		return returnList;
	}

	@Override
	public void updateOffenderTrustAccountsReOpen(final Long offenderId, final String caseloadId) {
		final String sql = getQuery("OTDCRVOI_UPDATE_OFFENDER_TRUST_ACCOUNTS_RE_OPEN");
		namedParameterJdbcTemplate.update(sql, createParams("offenderId", offenderId, "caseloadId", caseloadId));

	}

	@Override
	public void trustInsertIntoOffenderTrans(BigDecimal pTransNumber, Integer pTransSeq, String pCsldId, Long pOffId,
			Long pOffBookId, String pTransPostType, String pTransType, String pTransDesc, Double pTransAmount,
			Date pTransDate, String pSubActType, String pDeductionFlag, String pPreDedAmount, String pDeductionType,
			String pPayeeCorpId, String pPayeePersonId, String pInfoNumber, String pSlipPrintFlag,
			String pAllowOverdrawn) {
		final String sql = getQuery("OTDCRVOI_TRUST_INSERT_INTO_OFFENDER_TRANS");
		Map<String, Object> inParamMap = new HashMap<>();
		inParamMap.put("P_TRANS_NUMBER", pTransNumber);
		inParamMap.put("P_TRANS_SEQ", pTransSeq);
		inParamMap.put("P_CSLD_ID", pCsldId);
		inParamMap.put("P_OFF_ID", pOffId);
		inParamMap.put("P_OFF_BOOK_ID", pOffBookId);
		inParamMap.put("P_TRANS_POST_TYPE", pTransPostType);
		inParamMap.put("P_TRANS_TYPE", pTransType);
		inParamMap.put("P_TRANS_DESC", pTransDesc);
		inParamMap.put("P_TRANS_AMOUNT", pTransAmount);
		inParamMap.put("P_TRANS_DATE", pTransDate);
		inParamMap.put("P_SUB_ACT_TYPE", pSubActType);
		inParamMap.put("P_DEDUCTION_FLAG", pDeductionFlag);
		inParamMap.put("P_PRE_DED_AMOUNT", pPreDedAmount);
		inParamMap.put("P_DEDUCTION_TYPE", pDeductionType);
		inParamMap.put("P_PAYEE_CORP_ID", pPayeeCorpId);
		inParamMap.put("P_PAYEE_PERSON_ID", pPayeePersonId);
		inParamMap.put("P_INFO_NUMBER", pInfoNumber);
		inParamMap.put("P_SLIP_PRINT_FLAG", pSlipPrintFlag);
		inParamMap.put("P_ALLOW_OVERDRAWN", pAllowOverdrawn);

		namedParameterJdbcTemplate.update(sql, inParamMap);

	}

	@Override
	public BigDecimal genTrustTrans(String seqId) {
		try {
			final String sql = getQuery("OTDCRVOI_GEN_TRUST_TRANS");
			if (sql != null) {
				final String preparedSql = sql.replace("#SEQ", seqId);
				return namedParameterJdbcTemplate.queryForObject(preparedSql, createParams(), BigDecimal.class);
			}
		} catch (Exception e) {
			logger.error("OTDRDTFU_MAIN_PROCESS_TXN_ID_CUR", e);
		}
		return null;
	}

	@Override
	public void processGlTransNew(String pCsldId, String pTransType, String pOperationType, Double pTransAmount,
			BigDecimal pTransNumber, Date pTransDate, String pTransDesc, Integer pTransSeq, String pModuleName,
			Long pOffId, Long pOffBookId, String pSubActTypeDr, String pSubActTypeCr, String pPayeePersId,
			String pPayeeCorpId, String pPayeeNameText, Integer pGlSqnc, BigDecimal pOffDedId) {

		String query = getQuery("OTDCRVOI_TRUST_PROCESS_GL_TRANS_NEW");

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
	public void updateGlTransactionReversedFlag(final BigDecimal txnId, final Integer txnEntrySeq,
			final Long glEntrySeq) {
		String query = getQuery("OTDCRVOI_UPDATE_GL_TRANSACTION_REVERSED_FLAG");
		namedParameterJdbcTemplate.update(query,
				createParams("txnId", txnId, "txnEntrySeq", txnEntrySeq, "glEntrySeq", glEntrySeq));
	}

	@Override
	public void insertGlTransNew(String pPostType, Integer pAccountCode, String pAcntPosting, String pCsldId,
			String pTransType, Double pTransAmount, BigDecimal pTransNumber, Date pTransDate, String pTransDesc,
			Integer pTransSeq, Integer pGlSqnc, Long pOffId, Long pOffBookId, String pInfoNumber, String pPayeePersonId,
			String pPayeeCorporateId, String pPayeeNameText, String pRevrTxnId, String pRevrTxnEntrySeq,
			String pRevrGlEntrySeq, String pTxnRefNumber, String pOffDedId) {
		String query = getQuery("OTDCRVOI_TRUST_INSERT_GL_TRANS_NEW");

		Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("P_POST_TYPE", pPostType);
		inParamMap.put("P_ACCOUNT_CODE", pAccountCode);
		inParamMap.put("P_ACNT_POSTING", pAcntPosting);
		inParamMap.put("P_CSLD_ID", pCsldId);
		inParamMap.put("P_TRANS_TYPE", pTransType);
		inParamMap.put("P_TRANS_AMOUNT", pTransAmount);
		inParamMap.put("P_TRANS_NUMBER", pTransNumber);
		inParamMap.put("P_TRANS_DATE", pTransDate);
		inParamMap.put("P_TRANS_DESC", pTransDesc);
		inParamMap.put("P_TRANS_SEQ", pTransSeq);
		inParamMap.put("P_GL_SQNC", pGlSqnc);
		inParamMap.put("P_OFF_ID", pOffId);
		inParamMap.put("P_OFF_BOOK_ID", pOffBookId);
		inParamMap.put("P_INFO_NUMBER", pInfoNumber);
		inParamMap.put("P_PAYEE_PERSON_ID", pPayeePersonId);
		inParamMap.put("P_PAYEE_CORPORATE_ID", pPayeeCorporateId);
		inParamMap.put("P_PAYEE_NAME_TEXT", pPayeeNameText);
		inParamMap.put("P_REVR_TXN_ID", pRevrTxnId);
		inParamMap.put("P_REVR_TXN_ENTRY_SEQ", pRevrTxnEntrySeq);
		inParamMap.put("P_REVR_GL_ENTRY_SEQ", pRevrGlEntrySeq);
		inParamMap.put("P_TXN_REF_NUMBER", pTxnRefNumber);
		inParamMap.put("P_OFF_DED_ID", pOffDedId);

		namedParameterJdbcTemplate.update(query, inParamMap);

	}

	@Override
	public void updateGlTransactionReversedFlagAndValues(String vTxnReversedFlag, BigDecimal reversedTxnId,
			Integer reversedTxnEntrySeq, Long reversedGlEntrySeq, BigDecimal txnId, Integer txnEntrySeq,
			Integer glEntrySeq) {
		String query = getQuery("OTDCRVOI_UPDATE_GL_TRANSACTION_REVERSED_FLAG_AND_VALUES");
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("vTxnReversedFlag", vTxnReversedFlag);
		params.addValue("reversedTxnId", reversedTxnId);
		params.addValue("reversedTxnEntrySeq", reversedTxnEntrySeq);
		params.addValue("reversedGlEntrySeq", reversedGlEntrySeq);
		params.addValue("txnId", txnId);
		params.addValue("txnEntrySeq", txnEntrySeq);
		params.addValue("glEntrySeq", glEntrySeq);
		namedParameterJdbcTemplate.update(query, params);

	}

	@Override
	public List<OffenderTransactions> cOffTxnOffenderDetail(BigDecimal txnId, String chequeFlag, String payeeNameText) {
		final String sql = getQuery("OTDCRVOI_C_OFF_TXN_OFFENDER_DETAIL");
		final RowMapper<OffenderTransactions> cOneMap = Row2BeanRowMapper.makeMapping(sql, OffenderTransactions.class,
				cOffMapping);
		final List<OffenderTransactions> returnList = namedParameterJdbcTemplate.query(sql,
				createParams("txnId", txnId, "chequeFlag", chequeFlag, "payeeNameText", payeeNameText), cOneMap);
		return returnList;
	}

	@Override
	public void insertIntoOffenderTransactionsViaQuery(BigDecimal txnId, Integer txnEntrySeq, String txnPostingType,
			BigDecimal oldTxnId, Integer oldTxnEntrySeq) {
		final String sql = getQuery("OTDCRVOI_INSERT_INTO_OFFENDER_TRANSACTIONS_VIA_QUERY");
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("txnId", txnId);
		paramMap.put("txnEntrySeq", txnEntrySeq);
		paramMap.put("txnPostingType", txnPostingType);
		paramMap.put("oldTxnId", oldTxnId);
		paramMap.put("oldTxnEntrySeq", oldTxnEntrySeq);
		namedParameterJdbcTemplate.update(sql, paramMap);

	}

	@Override
	public void otdcrvoiUpdateOffenderTransactionTxnAdjecenmentFlag(BigDecimal txnId, Integer txnEntrySeq) {
		final String sql = getQuery("OTDCRVOI_UPDATE_OFFENDER_TRANSACTION_TXN_ADJECENMENT_FLAG");
		namedParameterJdbcTemplate.update(sql, createParams("txnId", txnId, "txnEntrySeq", txnEntrySeq));

	}

	@Override
	public void updateOffenderBalance(String pCsldId, Long pOffId, String pTransPostType, Date pTransDate,
			BigDecimal pTransNumber, String pTransType, Double pTransAmount, String pSubActType,
			String pAllowOverdrawn) {
		final String sql = getQuery("OTDCRVOI_UPDATE_OFFENDER_BALANCE");
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
	public void deductionGetAcAndSetIndDate(Long offenderId, String caseloadId) {
		final String sql = getQuery("OTDCRVOI_DEDUCTION_GET_AC_AND_SET_IND_DATE");
		Map<String, Object> param = new HashMap<>();
		param.put("P_OFF_ID", offenderId);
		param.put("P_CSLD_ID", caseloadId);
		namedParameterJdbcTemplate.update(sql, param);
	}

	@Override
	public List<BeneficiaryTransactions> cChequeBeneficiaries(BigDecimal pChequeTxnId) {
		final String sql = getQuery("OTDCRVOI_GET_BENEFICIARIES_INFORMATION");
		final RowMapper<BeneficiaryTransactions> cOneMap = Row2BeanRowMapper.makeMapping(sql,
				BeneficiaryTransactions.class, cChequeMapping);
		final List<BeneficiaryTransactions> returnList = namedParameterJdbcTemplate.query(sql,
				createParams("chequeTxnId", pChequeTxnId), cOneMap);
		return returnList;
	}

	@Override
	public void updateBeneficiaryTransactions(Long txnId, BigDecimal personId, BigDecimal corporateId,
			BigDecimal offenderDeductionId) {
		final String sql = getQuery("OTDCRVOI_UPDATE_BENEFICIARY_TRANSACTIONS");
		namedParameterJdbcTemplate.update(sql, createParams("txnId", txnId, "personId", personId, "corporateId",
				corporateId, "offenderDeductionId", offenderDeductionId));
	}

	@Override
	public List<BigDecimal> cGetAccountCode(Long txnId, BigDecimal personId, BigDecimal corporateId,
			BigDecimal offenderDeductionId) {
		final String sql = getQuery("OTDCRVOI_C_GET_ACCOUNT_CODE");
		return namedParameterJdbcTemplate.queryForList(sql, createParams("txnId", txnId, "personId", personId,
				"corporateId", corporateId, "offenderDeductionId", offenderDeductionId), BigDecimal.class);
	}

	public BankChequeData getOffenderId(final Long txnId) {
		final String sql = getQuery("OTDCRVOI_BNKCD_FIND_BANK_CHEQUE_DATA");
		final RowMapper<BankChequeData> bankChequeDataMap = Row2BeanRowMapper.makeMapping(sql, BankChequeData.class,
				bankChequeDataMapping);
		BankChequeData returnList = new BankChequeData();
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("TXNID", txnId),
					bankChequeDataMap);
		} catch (Exception e) {
			return returnList;
		}
		return returnList;
	}
}
