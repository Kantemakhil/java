package net.syscon.s4.cf.offendertransactions.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.cf.offendertransactions.OcdcrefuRepository;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.im.beans.SysDual;
import net.syscon.s4.im.beans.TransactionOperation;
import net.syscon.s4.inmate.trust.generalledger.impl.OtdbacreRepositoryImpl;
import oracle.jdbc.OracleTypes;

/**
 * Class OcdcrefuRepositoryImpl
 * 
 */
@Repository
public class OcdcrefuRepositoryImpl extends RepositoryBase implements OcdcrefuRepository {

	/**
	 * Creates new OcdcrefuRepositoryImpl class Object
	 */
	public OcdcrefuRepositoryImpl() {
		/*OcdcrefuRepositoryImpl*/
	}

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OtdbacreRepositoryImpl.class.getName());
	private final Map<String, FieldMapper> offenderTransactionsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DEDUCTION_FLAG", new FieldMapper("deductionFlag"))
			.put("REMITTER_NAME", new FieldMapper("remitterName"))
			.put("TXN_ENTRY_DESC", new FieldMapper("txnEntryDesc")).put("INFO_NUMBER", new FieldMapper("infoNumber"))
			.put("APPLY_SPENDING_LIMIT_AMOUNT", new FieldMapper("applySpendingLimitAmount"))
			.put("OFFENDER_ID", new FieldMapper("offenderId")).put("HOLD_CLEAR_FLAG", new FieldMapper("holdClearFlag"))
			.put("HOLD_NUMBER", new FieldMapper("holdNumber")).put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("ORG_TXN_TYPE", new FieldMapper("orgTxnType")).put("CASELOAD_ID", new FieldMapper("caseloadId"))
			.put("TXN_ID", new FieldMapper("txnId")).put("REMITTER_ID", new FieldMapper("remitterId"))
			.put("PAYEE_CODE", new FieldMapper("payeeCode")).put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("TXN_ENTRY_AMOUNT", new FieldMapper("txnEntryAmount"))
			.put("HOLD_UNTIL_DATE", new FieldMapper("holdUntilDate"))
			.put("SLIP_PRINTED_FLAG", new FieldMapper("slipPrintedFlag"))
			.put("ADJUST_ACCOUNT_CODE", new FieldMapper("adjustAccountCode"))
			.put("MODIFY_DATE", new FieldMapper("modifyDate")).put("TXN_ENTRY_SEQ", new FieldMapper("txnEntrySeq"))
			.put("DEDUCTION_TYPE", new FieldMapper("deductionType"))
			.put("ROOT_OFFENDER_ID", new FieldMapper("rootOffenderId"))
			.put("PAYEE_CORPORATE_ID", new FieldMapper("payeeCorporateId"))
			.put("SUB_ACCOUNT_TYPE", new FieldMapper("subAccountType"))
			.put("PRE_WITHHOLD_AMOUNT", new FieldMapper("preWithholdAmount"))
			.put("TXN_POSTING_TYPE", new FieldMapper("txnPostingType"))
			.put("CR_ACCOUNT_CODE", new FieldMapper("crAccountCode"))
			.put("DR_ACCOUNT_CODE", new FieldMapper("drAcountCode")).build();

	private final Map<String, FieldMapper> transactionalOperationsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CHEQUEPRODUCTIONFLAG", new FieldMapper("chequeProductionFlag"))
			.put("CR_ACCOUNT_CODE", new FieldMapper("crAccountCode")).build();

	private final Map<String, FieldMapper> sysDualMapping = new ImmutableMap.Builder<String, FieldMapper>().build();

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            OffenderTransactions
	 *
	 * @return List<OffenderTransactions>
	 *
	 */
	public Double offTxnExecuteQuery(final OffenderTransactions objSearchDao) {
		final String sql = getQuery("OCDCREFU_OFFTXN_FIND_OFFENDER_TRANSACTIONS_BALANCE");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("rootOffenderId",
				objSearchDao.getRootOffenderId(), "caseLoadId", objSearchDao.getCaseloadId()), Double.class);

	}

	/**
	 * Fetch the Description from database table
	 *
	 * @return String description
	 *
	 */

	@Override
	public String getDescription() {
		final String sql = getQuery("OCDCREFU_OFFTXN_FIND_OFFENDER_TRANSACTIONS_DESCRIPTION");

		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);
	}

	@Override
	public OffenderTransactions offTxnValidateQuery(final OffenderTransactions searchBean) {
		final String sql = getQuery("OCDCREFU_OFFTXN_FIND_OFFENDER_TRANSACTIONS_CRACCOUNTCODE_DRACOUNTCODE");
		final RowMapper<OffenderTransactions> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderTransactions.class, offenderTransactionsMapping);
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("caseLoadId", searchBean.getCaseloadId()),
				columnRowMapper);
	}

	@Override
	public Double offTxngetMinPayment(final OffenderTransactions searchBean) {
		final String sql = getQuery("OCDCREFU_OFFTXN_FIND_OFFENDER_TRANSACTIONS_GETMINPAYMENT");
		Double minPayment = null;

		minPayment = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("accountCode", searchBean.getDrAcountCode()), Double.class);

		return minPayment;
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
	 */
	public Integer offTxnInsertOffenderTransactions1(final List<OffenderTransactions> lstOffenderTransactions) {
		int insertCount = 0;
		final String sql = getQuery("OCDCREFU_OFFTXN_INSERT_OFFENDER_TRANSACTIONS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
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
	 * This method is execute offTxnInsertOffenderTransactions
	 * 
	 * cgwhenNewFormInstance
	 *
	 * @param params
	 *
	 */

	@Override
	public Integer offTxnInsertOffenderTransactions(final OffenderTransactions commitBean) {
		final String sql = getQuery("OCDCREFU_OFFTXN_INSERT_OFFENDER_TRANSACTIONS");
		Integer val;
		try {
			val = namedParameterJdbcTemplate.update(sql, createParams(

					"txnId", commitBean.getTxnId(), "txnEntrySeq", commitBean.getTxnEntrySeq(), "caseloadId",
					commitBean.getCaseloadId(), "offenderId", commitBean.getRootOffenderId(), "offenderBookId",
					commitBean.getOffenderBookId(), "txnPostingType", commitBean.getTxnPostingType(), "txnType",
					commitBean.getTxnType(), "txnEntryDesc", commitBean.getTxnEntryDesc(), "txnEntryAmount",
					commitBean.getTxnEntryAmount(), "subAccountType", commitBean.getSubAccountType(), "slipPrintedFlag",
					commitBean.getSlipPrintedFlag()));
		} catch (final Exception e) {
			logger.error("Exception in method offTxnInsertOffenderTransactions " + e);
			return 0;
		}

		return val;
	}

	/**
	 * This method is execute genTrustTrans
	 * 
	 * cgwhenNewFormInstance
	 *
	 * @param params
	 *
	 */

	@Override
	public Integer genTrustTrans(final String seqId) {
		try {
			final String sql = getQuery("OTDRECEI_GEN_TRUST_TRANS");
			if (sql != null) {
				final String preparedSql = sql.replace("#SEQ", seqId);
				return namedParameterJdbcTemplate.queryForObject(preparedSql, createParams(), Integer.class);
			}
		} catch (final Exception e) {
			logger.error("OTDRDTFU_MAIN_PROCESS_TXN_ID_CUR", e);
		}
		return null;
	}

	/**
	 * This method is execute processGlTransNew
	 * 
	 * cgwhenNewFormInstance
	 *
	 * @param params
	 *
	 */

	@Override
	public Integer processGlTransNew(final OffenderTransactions offenderTransaction) {

		Integer genSeq = 0;
		final Map<String, Object> inParamMap = new HashMap<>();
		inParamMap.put("P_CSLD_ID", offenderTransaction.getCaseloadId());
		inParamMap.put("P_TRANS_TYPE", "REF");
		inParamMap.put("P_OPERATION_TYPE", null);
		inParamMap.put("P_TRANS_AMOUNT", offenderTransaction.getTxnEntryAmount());
		inParamMap.put("P_TRANS_NUMBER", offenderTransaction.getTxnId());
		inParamMap.put("P_TRANS_DATE", offenderTransaction.getTxnEntryDate());
		inParamMap.put("P_TRANS_DESC", offenderTransaction.getTxnEntryDesc());
		inParamMap.put("P_TRANS_SEQ", offenderTransaction.getTxnEntrySeq());
		inParamMap.put("P_MODULE_NAME", "OCDCREFU");
		inParamMap.put("P_OFF_ID", offenderTransaction.getRootOffenderId());
		inParamMap.put("P_OFF_BOOK_ID", offenderTransaction.getOffenderBookId());
		inParamMap.put("P_SUB_ACT_TYPE_DR", null);
		inParamMap.put("P_SUB_ACT_TYPE_CR", null);
		inParamMap.put("P_PAYEE_PERS_ID", null);
		inParamMap.put("P_PAYEE_CORP_ID", null);
		inParamMap.put("P_PAYEE_NAME_TEXT", null);
		inParamMap.put("P_GL_SQNC", 0);
		inParamMap.put("P_OFF_DED_ID", null);
		try {
			namedParameterJdbcTemplate
					.update("call OMS_OWNER.TRUST.PROCESS_GL_TRANS_NEW(:P_CSLD_ID, :P_TRANS_TYPE, :P_OPERATION_TYPE, :P_TRANS_AMOUNT, :P_TRANS_NUMBER, :P_TRANS_DATE, :P_TRANS_DESC,"
							+ " :P_TRANS_SEQ, :P_MODULE_NAME, :P_OFF_ID, :P_OFF_BOOK_ID, :P_SUB_ACT_TYPE_DR, :P_SUB_ACT_TYPE_CR, :P_PAYEE_PERS_ID, :P_PAYEE_CORP_ID, :P_PAYEE_NAME_TEXT,"
							+ " :P_GL_SQNC, :P_OFF_DED_ID)", inParamMap);
			genSeq = 2;
		} catch (final Exception e) {
			logger.error("processGlTransNew", e);
		}
		return genSeq;

	}

	/**
	 * This method is execute updateOffenderBalance
	 * 
	 * cgwhenNewFormInstance
	 *
	 * @param params
	 *
	 */

	@Override
	public OffenderTransactions updateOffenderBalance(final OffenderTransactions offenderTransactions,
			final Date tansDate) {
		try {
			final SqlParameter[] sqlParameters = new SqlParameter[] {
					new SqlParameter("p_csld_id", OracleTypes.VARCHAR),
					new SqlParameter("p_off_id", OracleTypes.NUMBER),
					new SqlParameter("p_trans_post_type", OracleTypes.VARCHAR),
					new SqlParameter("p_trans_date", OracleTypes.DATE),
					new SqlParameter("p_trans_number", OracleTypes.NUMBER),
					new SqlParameter("p_trans_type", OracleTypes.VARCHAR),
					new SqlParameter("p_trans_amount", OracleTypes.NUMBER),
					new SqlParameter("p_sub_act_type", OracleTypes.VARCHAR),
					new SqlParameter("p_allow_overdrawn", OracleTypes.VARCHAR), };
			final SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
					.withCatalogName("TRUST").withProcedureName("UPDATE_OFFENDER_BALANCE")
					.declareParameters(sqlParameters);
			final Map<String, Object> inParamMap = new HashMap<>();
			inParamMap.put("p_csld_id", offenderTransactions.getCaseloadId());
			inParamMap.put("p_off_id", offenderTransactions.getRootOffenderId());
			inParamMap.put("p_trans_post_type", "DR");
			inParamMap.put("p_trans_date", tansDate);
			inParamMap.put("p_trans_number", offenderTransactions.getTxnId());
			inParamMap.put("p_trans_type", "REF");
			inParamMap.put("p_trans_amount", offenderTransactions.getTxnEntryAmount());
			inParamMap.put("p_sub_act_type", "REG");
			inParamMap.put("p_allow_overdrawn", "N");
			final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
			simpleJDBCCall.execute(inParameter);
		} catch (final Exception e) {
			offenderTransactions.setSealFlag("9");
		}
		return offenderTransactions;
	}

	/**
	 * This method is execute a sql query ocdcrefuChkProdflagAcccode
	 * 
	 * cgwhenNewFormInstance
	 *
	 * @param params
	 *
	 */

	@Override
	public TransactionOperation ocdcrefuChkProdflagAcccode(final OffenderTransactions offenderTransactions) {
		final String sql = getQuery("OCDCREFU_CHK_PRODFLAG_ACCCODE");
		final RowMapper<TransactionOperation> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				TransactionOperation.class, transactionalOperationsMapping);
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("caseLoadId", offenderTransactions.getCaseloadId()), columnRowMapper);
	}

	
	public List<OffenderTransactions> offBkgOnCheckDeleteMaster(final OffenderTransactions paramBean) {
		final String sql = getQuery("OCDCREFU_OFF_BKG_ONCHECKDELETEMASTER");
		final RowMapper<OffenderTransactions> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderTransactions.class, offenderTransactionsMapping);
		final ArrayList<OffenderTransactions> returnList = (ArrayList<OffenderTransactions>) namedParameterJdbcTemplate
				.query(sql, createParams(), columnRowMapper);
		return returnList;
	}

	

	/**
	 * This method is execute insertIntoChkData
	 * 
	 * @param params
	 *            OffenderTransactions commitBean
	 *
	 */

	@Override
	public void insertIntoChkData(final OffenderTransactions commitBean) {
		Map<String, Object> val;
		final SqlParameter[] sqlParameters = new SqlParameter[] { new SqlParameter("P_CSLD_ID", OracleTypes.VARCHAR),
				new SqlParameter("P_TRANS_NUMBER", OracleTypes.NUMBER),
				new SqlParameter("P_TRANS_AMOUNT", OracleTypes.NUMBER),
				new SqlParameter("P_CHEQUE_FLAG", OracleTypes.VARCHAR),
				new SqlParameter("P_START_TXN_ID", OracleTypes.NUMBER),
				new SqlParameter("P_END_TXN_ID", OracleTypes.NUMBER),
				new SqlParameter("P_PERS_PAYEE_ID", OracleTypes.NUMBER),
				new SqlParameter("P_CORP_PAYEE_ID", OracleTypes.NUMBER),
				new SqlParameter("P_PAYEE_NAME", OracleTypes.VARCHAR),
				new SqlParameter("P_OFFENDER_PAYEE", OracleTypes.NUMBER),
				new SqlParameter("P_SINGLE_ENTRY", OracleTypes.VARCHAR),
				new SqlParameter("P_BANK_ACT_CODE", OracleTypes.NUMBER),
				new SqlParameter("P_MODULE_NAME", OracleTypes.VARCHAR),
				new SqlParameter("P_TRANS_TYPE", OracleTypes.VARCHAR), };
		final SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TRUST").withProcedureName("INSERT_INTO_CHEQUE_DATA").declareParameters(sqlParameters);
		final Map<String, Object> inParamMap = new HashMap<String, Object>();

		inParamMap.put("P_CSLD_ID", commitBean.getCaseloadId());
		inParamMap.put("P_TRANS_NUMBER", commitBean.getTxnId());
		inParamMap.put("P_TRANS_AMOUNT", commitBean.getTxnEntryAmount());
		inParamMap.put("P_CHEQUE_FLAG", "O");
		inParamMap.put("P_START_TXN_ID", commitBean.getTxnId());
		inParamMap.put("P_END_TXN_ID", commitBean.getTxnId());
		inParamMap.put("P_PERS_PAYEE_ID", null);
		inParamMap.put("P_CORP_PAYEE_ID", null);
		inParamMap.put("P_PAYEE_NAME", commitBean.getPayeeName());
		inParamMap.put("P_OFFENDER_PAYEE", commitBean.getRootOffenderId());
		inParamMap.put("P_SINGLE_ENTRY", "1");
		inParamMap.put("P_BANK_ACT_CODE", commitBean.getCrAccountCode());
		inParamMap.put("P_MODULE_NAME", "OCDCREFU");
		inParamMap.put("P_TRANS_TYPE", commitBean.getTxnType());

		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			simpleJDBCCall.execute(inParameter);
		} catch (final Exception e) {
			logger.error("Exception in method insertIntoChkData " + e);
		}

	}

	/**
	 * This method is execute insertBankChequeBeneficiaries
	 * 
	 * @param params
	 *            OffenderTransactions commitBean
	 * 
	 */

	@Override
	public void insertBankChequeBeneficiaries(final OffenderTransactions commitBean) {
		final String sql = getQuery("INSERT_BANK_CHEQUE_BENEFICIARIES");
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		try {
			namedParameterJdbcTemplate.update(sql, createParams(

					"transnumber", commitBean.getTxnId(), "vamount", commitBean.getTxnEntryAmount(), "personId", null,
					"corporateid", null, "txnId", null, "offenderId", commitBean.getRootOffenderId(), "amount",
					commitBean.getTxnEntryAmount(), "offenderDeductionId", null));
		} catch (final Exception e) {
			logger.error("Exception in method offTxnInsertOffenderTransactions " + e);

		}

	}

}
