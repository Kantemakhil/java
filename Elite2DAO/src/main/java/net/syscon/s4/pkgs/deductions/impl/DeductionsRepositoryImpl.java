package net.syscon.s4.pkgs.deductions.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.cf.deductions.maintenance.beans.TieredTransactionFeeAmounts;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.CaseloadDeductionDetails;
import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.im.beans.SuspendDeductions;
import net.syscon.s4.inmate.beans.CaseloadDeductionProfiles;
import net.syscon.s4.inmate.beans.OffenderDeductions;
import net.syscon.s4.inmate.beans.OffenderSubAccounts;
import net.syscon.s4.pkgs.deductions.DeductionsRepository;

@Repository
public class DeductionsRepositoryImpl extends RepositoryBase implements DeductionsRepository {

	private static Logger logger = LogManager.getLogger(DeductionsRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> mapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("ORDER_PROPOSAL_CONDITION_ID", new FieldMapper("orderProposalConditionId")).build();

	private final Map<String, FieldMapper> offMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CRS_ACTY_ID", new FieldMapper("crsActyId")).build();

	private final Map<String, FieldMapper> cCondDedMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("SENTENCE_SEQ", new FieldMapper("sentenceSeq")).build();

	private final Map<String, FieldMapper> accCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DR_ACCOUNT_CODE", new FieldMapper("drAccountCode"))
			.put("B_TXN_POSTING_TYPE", new FieldMapper("bTxnPostingType"))
			.put("B_POSTING_STATUS_FLAG", new FieldMapper("bPostingStatusFlag"))
			.put("CR_ACCOUNT_CODE", new FieldMapper("crAccountCode"))
			.put("C_TXN_POSTING_TYPE", new FieldMapper("cTxnPostingType"))
			.put("C_POSTING_STATUS_FLAG", new FieldMapper("cPostingStatusFlag"))
			.put("bank_dr_account_code", new FieldMapper("bankDrAccountCode"))
			.put("D_TXN_POSTING_TYPE", new FieldMapper("dTxnPostingType"))
			.put("D_POSTING_STATUS_FLAG", new FieldMapper("dPostingStatusFlag"))
			.put("BANK_CR_ACCOUNT_CODE", new FieldMapper("bankCrAccountCode"))
			.put("E_TXN_POSTING_TYPE", new FieldMapper("txnPostingType"))
			.put("E_POSTING_STATUS_FLAG", new FieldMapper("ePostingStatusFlag"))
			.put("TXN_OPERATION_SEQ", new FieldMapper("txnOperationSeq")).build();

	private final Map<String, FieldMapper> tieredMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("offender_deduction_id", new FieldMapper("deductionId")).build();

	private final Map<String, FieldMapper> susDeductionMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("SUSPEND_DEDUCTION_ID", new FieldMapper("suspendDeductionId"))
			.put("CASELOAD_ID", new FieldMapper("caseloadId")).build();

	@Override
	public List<OffenderDeductions> chkDedCur(final Long pOffId, final Long pShadowId, final String csldType) {
		final String sql = getQuery("CHK_OFFENDER_DEDUCTIONS_CHK_DED_CUR");

		final RowMapper<OffenderDeductions> offenderDeductionRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderDeductions.class, mapping);
		return namedParameterJdbcTemplate.query(sql,
				createParams("P_OFF_ID", pOffId, "CSLD_TYPE", csldType, "P_SHADOW_ID", pShadowId),
				offenderDeductionRowMapper);
	}

	@Override
	public String chkReceiptType(final Long offenderDeductionId, final String pTransType) {
		final String sql = getQuery("CHK_OFFENDER_DEDUCTIONS_CHK_RECEIPT_TYPE");
		String pDedFlag = null;
		Integer count = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("DED_ID", offenderDeductionId, "P_TRANS_TYPE", pTransType), Integer.class);
		if (count > 0) {
			pDedFlag = "Y";
		} else {
			pDedFlag = "N";
		}
		return pDedFlag;
	}

	@Override
	public List<OffenderSubAccounts> getMinSubIndValues(final Integer accountCode, final Long offenderId,
			final String caseloadId) {
		final String sql = getQuery("GET_MIN_SUB_IND_VALUES");
		final RowMapper<OffenderSubAccounts> rowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderSubAccounts.class,
				mapping);
		return namedParameterJdbcTemplate.query(sql,
				createParams("p_csld_id", caseloadId, "p_off_id", offenderId, "p_acnt_code", accountCode), rowMapper);
	}

	@Override
	public Double getMinBalance(final Integer accountCode, final Long offenderId, final String caseloadId) {
		final String sql = getQuery("GET_MIN_BALANCE");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("p_csld_id", caseloadId, "p_off_id", offenderId, "p_acnt_code", accountCode),
				Double.class);
	}

	@Override
	public Double getMinBalanceOne(final Integer accountCode, final String caseloadId) {
		final String sql = getQuery("GET_MIN_BALANCE_ONE");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("p_csld_id", caseloadId, "p_acnt_code", accountCode), Double.class);
	}

	@Override
	public void updateOffenderSubAccountsInd(final Integer accountCode, final Long offenderId, final String caseloadId,
			final String userName) {
		final String sql = getQuery("UPDATE_OFFENDER_SUB_ACCOUNTS_IND");
		final Map<String, Object> inParamMap = new HashMap<String, Object>();

		inParamMap.put("p_csld_id", caseloadId);
		inParamMap.put("p_off_id", offenderId);
		inParamMap.put("p_acnt_code", accountCode);
		inParamMap.put("MODIFYUSERID", userName);
		namedParameterJdbcTemplate.update(sql, inParamMap);
	}

	@Override
	public void updateOffenderSubAccountsIndOne(final Integer accountCode, final Long offenderId, final String caseloadId,
			final String userName) {
		final String sql = getQuery("UPDATE_OFFENDER_SUB_ACCOUNTS_ONE_ACC");
		final Map<String, Object> inParamMap = new HashMap<String, Object>();

		inParamMap.put("p_csld_id", caseloadId);
		inParamMap.put("p_off_id", offenderId);
		inParamMap.put("p_acnt_code", accountCode);
		inParamMap.put("modifyUserId", userName);
		try {
		namedParameterJdbcTemplate.update(sql, inParamMap);
		}catch (Exception e) {
			logger.error("updateOffenderSubAccountsOne"+e);
		}
	}

	@Override
	public List<CaseloadDeductionProfiles> cCondDedCursor(final String pCsldId, final String pConditionType,
			final String pConditionCode, final String pConCategoryType, final BigDecimal corporateId,
			final String csldType) {
		final String sql = getQuery("GET_C_COND_DED_CURSOR");
		final RowMapper<CaseloadDeductionProfiles> rowMapper = Row2BeanRowMapper.makeMapping(sql,
				CaseloadDeductionProfiles.class, cCondDedMapping);
		return namedParameterJdbcTemplate.query(sql,
				createParams("pCsldId", pCsldId, "pConditionType", pConditionType, "pConditionCode", pConditionCode,
						"pConCategoryType", pConCategoryType, "corporateId", corporateId, "csldType", csldType),
				rowMapper);

	}

	@Override
	public Long uniUi2Select(final String caseloadId, final String deductionType, final BigDecimal pOffId,
			final String pInfoNumber) {
		final String sql = getQuery("GET_UNI_UI2_SELECT");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("pOffId", pOffId, "pInfoNumber", pInfoNumber,
				"caseloadId", caseloadId, "deductionType", deductionType), Long.class);
	}

	@Override
	public Long vDedIdSelect() {
		final String sql = getQuery("GET_VDED_ID_SELECT");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Long.class);
	}

	@Override
	public BigDecimal vOdpSelect(final String deductionType, final BigDecimal pOffId) {
		final String sql = getQuery("GET_V_ODP_SELECT");
		BigDecimal retNo = null;
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("deductionType", deductionType, "pOffId", pOffId), BigDecimal.class);
	}

	@Override
	public BigDecimal lvGroupIdSelect(final String deductionType) {
		final String sql = getQuery("GET_LV_GROUP_ID_SELECT");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("deductionType", deductionType),
				BigDecimal.class);
	}

	@Override
	public void insertOffenderDeductions(final OffenderDeductions offDed) {
		final String sql = getQuery("INSERT_OFFENDER_DEDUCTIONS");
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		parameters.add(new BeanPropertySqlParameterSource(offDed));
		namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
	}

	@Override
	public void insertOffenderBeneficiaries(final BigDecimal pOffId, final BigDecimal pTotalAmount,
			final String pCsldId, final String pConditionType, final String pConditionCode,
			final String pConCategoryType, final Date vSysdate, final String userName) {
		final String sql = getQuery("INSERT_OFFENDER_BENEFICIARIES");
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("p_off_id", pOffId);
		inParamMap.put("p_total_amount", pTotalAmount);
		inParamMap.put("p_csld_id", pCsldId);
		inParamMap.put("p_condition_type", pConditionType);

		inParamMap.put("p_condition_code", pConditionCode);
		inParamMap.put("p_con_category_type", pConCategoryType);
		inParamMap.put("v_sysdate", vSysdate);
		inParamMap.put("createUserId", userName);
		namedParameterJdbcTemplate.update(sql, inParamMap);
	}

	@Override
	public List<CaseloadDeductionDetails> cReceiptCursor(final BigDecimal pOffId, final String pCsldId,
			final String csldType) {
		final String sql = getQuery("GET_C_RECEIPT_CURSOR");
		final RowMapper<CaseloadDeductionDetails> rowMapper = Row2BeanRowMapper.makeMapping(sql,
				CaseloadDeductionDetails.class, cCondDedMapping);

		return namedParameterJdbcTemplate.query(sql,
				createParams("pCsldId", pCsldId, "pOffId", pOffId, "csldType", csldType), rowMapper);

	}

	@Override
	public Integer existFlagSelect(final Long offenderDeductionId, final String receiptTxnType) {
		final String sql = getQuery("GET_EXIST_FLAG_SELECT");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("offenderDeductionId", offenderDeductionId, "receiptTxnType", receiptTxnType),
				Integer.class);
	}

	@Override
	public void insertOffenderDeductionReceipts(final Long offenderDeductionId, final String receiptTxnType,
			final BigDecimal percentage, final BigDecimal flatRate, final String userName) {
		final String sql = getQuery("INSERT_OFFENDER_DEDUCTION_RECEIPTS");
		final Map<String, Object> inParamMap = new HashMap<String, Object>();

		inParamMap.put("offenderDeductionId", offenderDeductionId);
		inParamMap.put("receiptTxnType", receiptTxnType);

		inParamMap.put("percentage", percentage);
		inParamMap.put("flatRate", flatRate);
		inParamMap.put("createUserId", userName);
		namedParameterJdbcTemplate.update(sql, inParamMap);
	}

	@Override
	public Integer chkCaseloadC(final String caseloadId) {
		final String sql = getQuery("CHK_CASE_LOAD_C");
		Integer retVal ;
		try {
			retVal = namedParameterJdbcTemplate.queryForObject(sql, createParams("p_csld_id", caseloadId),
					Integer.class);
		} catch (Exception e) {
			logger.error("chkCaseloadC", e);
			retVal = 0;
		}
		return retVal;

	}

	@Override
	public List<OffenderSubAccounts> chkIndAcC(final Long offenderId, final String caseloadId, final String csldType) {
		final String sql = getQuery("CHK_IND_ACC");
		List<OffenderSubAccounts> retList = null;

		final RowMapper<OffenderSubAccounts> rowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderSubAccounts.class,
				offMapping);
		try {
			retList = namedParameterJdbcTemplate.query(sql,
					createParams("p_off_id", offenderId, "p_csld_id", caseloadId, "csld_type", csldType), rowMapper);
		} catch (Exception e) {
			logger.error("chkIndAcC", e);
		}
		return retList;
	}

	@Override
	public List<SystemProfiles> systemProfileC() {
		final String sql = getQuery("SYSTEM_PROFILE_C");
		final RowMapper<SystemProfiles> rowMapper = Row2BeanRowMapper.makeMapping(sql, SystemProfiles.class, mapping);
		List<SystemProfiles> retList = new ArrayList<SystemProfiles>();
		try {
			retList = namedParameterJdbcTemplate.query(sql, createParams(), rowMapper);
		} catch (Exception e) {
			logger.error("systemProfileC", e);
			retList = null;
		}
		return retList;
	}

	@Override
	public Integer getTrustAccountCodeC() {
		final String sql = getQuery("GET_TRUST_ACCOUNT_CODE_C");
		Integer retVal = null;
		try {
			retVal = namedParameterJdbcTemplate.queryForObject(sql, createParams(), Integer.class);
		} catch (Exception e) {
			logger.error("getTrustAccountCodeC", e);
			retVal = null;
		}
		return retVal;
	}

	@Override
	public Double sumOffSubActBalC(final Integer lvTrustAccountCode, final Long offenderId) {
		final String sql = getQuery("SUM_OFF_SUBACT_BAL_C");
		Double retVal = null;
		try {
			retVal = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("lv_trust_account_code", lvTrustAccountCode, "p_off_id", offenderId), Double.class);
		} catch (Exception e) {
			logger.error("sumOffSubActBalC", e);
			retVal = null;
		}
		return retVal;
	}

	@Override
	public Date maxIndDateC(final Integer lvTrustAccountCode, final Long offenderId) {
		final String sql = getQuery("MAX_IND_DATE_C");
		Date retVal = null;
		try {
			retVal = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("lv_trust_account_code", lvTrustAccountCode, "p_off_id", offenderId), Date.class);
		} catch (Exception e) {
			logger.error("maxIndDateC", e);
			retVal = null;
		}
		return retVal;

	}

	@Override
	public void updateOffenderSubAccounts(final Date lvIndigentDate, final String lvIndigentDaysLimit,
			final Integer lvTrustAccountCode, final Long offenderId, final String userName) {
		final String sql = getQuery("UPDATE_OFFENDER_SUB_ACCOUNTS");
		final Map<String, Object> inParamMap = new HashMap<String, Object>();

		inParamMap.put("lv_indigent_date", lvIndigentDate);
		inParamMap.put("lv_indigent_days_limit", lvIndigentDaysLimit);

		inParamMap.put("lv_trust_account_code", lvTrustAccountCode);
		inParamMap.put("p_off_id", offenderId);
		inParamMap.put("p_admission_date", null);
		inParamMap.put("modifyUserId", userName);
		try {
			namedParameterJdbcTemplate.update(sql, inParamMap);
		} catch (Exception e) {
			logger.error("updateOffenderSubAccounts", e);
		}

	}

	@Override
	public void updateOffenderSubAccountsOne(final Integer lvTrustAccountCode, final Long offenderId,
			final String userName) {
		final String sql = getQuery("UPDATE_OFFENDER_SUB_ACCOUNTS_ONE");
		final Map<String, Object> inParamMap = new HashMap<String, Object>();

		inParamMap.put("lv_trust_account_code", lvTrustAccountCode);
		inParamMap.put("p_off_id", offenderId);
		inParamMap.put("modifyUserId", userName);

		try {
			namedParameterJdbcTemplate.update(sql, inParamMap);
		} catch (Exception e) {
			logger.error("updateOffenderSubAccountsOne", e);
		}
	}

	@Override
	public List<CaseloadDeductionProfiles> defaultDeductionCursor(final String pCsldId, final Integer pOffId,
			final String csldType, final BigDecimal corporateId) {
		final String sql = getQuery("DEFAULT_DEDUCTION_CURSOR");

		final RowMapper<CaseloadDeductionProfiles> mRowMapper = Row2BeanRowMapper.makeMapping(sql,
				CaseloadDeductionProfiles.class, accCodesMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("p_csld_id", pCsldId, "csld_type", csldType,
				"p_off_id", pOffId, "corporate_id", corporateId), mRowMapper);
	}

	@Override
	public BigDecimal maxOdpCursor(final String deductionType, final Integer pOffId) {
		final String sql = getQuery("MAX_ODP_CURSOR");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("lv_ded_type", deductionType, "p_off_id", pOffId), BigDecimal.class);
	}

	@Override
	public Integer offenderDeductionsInser(final CaseloadDeductionProfiles eachDed, final String userName) {
		final String sql = getQuery("OFFENDER_DEDUCTIONS_INSER");
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("p_off_id", eachDed.getpOffId());
		inParamMap.put("ded_type", eachDed.getDeductionType());
		inParamMap.put("max_monthly", eachDed.getMaxMonthlyAmount());
		inParamMap.put("max_total", eachDed.getMaxTotalAmount());

		inParamMap.put("csld_id", eachDed.getCaseloadId());
		inParamMap.put("p_csld_id", eachDed.getCaseloadId());
		inParamMap.put("lv_odp", eachDed.getLvOdp());
		inParamMap.put("v_sysdate", eachDed.getvSysdat());
		inParamMap.put("max_recursive", eachDed.getMaxRecursiveAmount());
		inParamMap.put("credit_limit", eachDed.getCoLimitAmount());

		inParamMap.put("fifo", eachDed.getFifoFlag());
		inParamMap.put("person_id", eachDed.getPayeePersonId());
		inParamMap.put("corp_id", eachDed.getCorpId());
		inParamMap.put("createUserId", userName);
		return namedParameterJdbcTemplate.update(sql, inParamMap);
	}

	@Override
	public Integer offenderBeneficiariesInsert(final String pCsldId, final Integer pOffId, final Date vSysdate,
			final String userName) {
		final String sql = getQuery("OFFENDER_BENEFICIARIES_INSERT");
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("p_off_id", pOffId);
		inParamMap.put("p_csld_id", pCsldId);
		inParamMap.put("v_sysdate", vSysdate);
		inParamMap.put("createUserId", userName);
		return namedParameterJdbcTemplate.update(sql, inParamMap);

	}

	@Override
	public Integer offenderBeneficiariesInsertOne(final String pCsldId, final Integer pOffId, final Date vSysdate,
			final String userName) {
		final String sql = getQuery("OFFENDER_BENEFICIARIES_INSERT_ONE");
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("p_off_id", pOffId);
		inParamMap.put("p_csld_id", pCsldId);
		inParamMap.put("v_sysdate", vSysdate);
		inParamMap.put("createUserId", userName);
		return namedParameterJdbcTemplate.update(sql, inParamMap);
	}

	@Override
	public List<CaseloadDeductionDetails> cReceiptCursor(final String pCsldId, final Integer pOffId,
			final String csldType) {
		final String sql = getQuery("C_RECEIPT_CURSOR_A");
		final RowMapper<CaseloadDeductionDetails> mRowMapper = Row2BeanRowMapper.makeMapping(sql,
				CaseloadDeductionDetails.class, accCodesMapping);

		return namedParameterJdbcTemplate.query(sql,
				createParams("p_csld_id", pCsldId, "csld_type", csldType, "p_off_id", pOffId), mRowMapper);
	}

	@Override
	public Integer offenderDeductionReceiptsSelectOperation(final Long offenderDeductionId,
			final String receiptTxnType) {
		final String sql = getQuery("OFFENDER_DEDUCTION_RECEIPTS_SELECT_OPERATION");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("offender_deduction_id", offenderDeductionId, "receipt_txn_type", receiptTxnType),
				Integer.class);
	}

	@Override
	public Integer offenderDeductionReceiptsInsert(final CaseloadDeductionDetails csldDedDtais, final String userName) {
		final String sql = getQuery("OFFENDER_DEDUCTION_RECEIPTS_INSERT");
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("offender_deduction_id", csldDedDtais.getOffenderDeductionId());
		inParamMap.put("receipt_txn_type", csldDedDtais.getReceiptTxnType());

		inParamMap.put("percentage", csldDedDtais.getPercentage());
		inParamMap.put("flat_rate", csldDedDtais.getFlatRate());
		inParamMap.put("createUserId", userName);
		return namedParameterJdbcTemplate.update(sql, inParamMap);
	}

	@Override
	public List<OffenderDeductions> cColFeeCursor(final Integer pOffId, final String pCsldId, final String csldType) {
		final String sql = getQuery("C_COL_FEE_CURSOR");
		final RowMapper<OffenderDeductions> mRowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderDeductions.class,
				accCodesMapping);
		return namedParameterJdbcTemplate.query(sql,
				createParams("p_csld_id", pCsldId, "csld_type", csldType, "p_off_id", pOffId), mRowMapper);
	}

	@Override
	public Integer offenderTxnFeeDetailsSelect(final Long offenderDeductionId, final String receiptDeductionType) {
		final String sql = getQuery("OFFENDER_TXN_FEE_DETAILS_SELECT");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("offender_deduction_id", offenderDeductionId,
				"receipt_deduction_type", receiptDeductionType), Integer.class);
	}

	@Override
	public Integer offenderTxnFeeDetailsInsert(final Long offenderDeductionId, final String receiptDeductionType,
			final String userName) {
		final String sql = getQuery("OFFENDER_TXN_FEE_DETAILS_INSERT");
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("offender_deduction_id", offenderDeductionId);
		inParamMap.put("receipt_deduction_type", receiptDeductionType);
		inParamMap.put("createUserId", userName);
		return namedParameterJdbcTemplate.update(sql, inParamMap);
	}

	@Override
	public List<TieredTransactionFeeAmounts> cFeeAmtCursor(final Integer pOffId, final String pCsldId) {
		final String sql = getQuery("C_RECEIPT_CURSOR");
		final RowMapper<TieredTransactionFeeAmounts> mRowMapper = Row2BeanRowMapper.makeMapping(sql,
				TieredTransactionFeeAmounts.class, tieredMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("p_csld_id", pCsldId, "p_off_id", pOffId),
				mRowMapper);
	}

	@Override
	public Integer offenderTierTxnFeeAmountsInsert(final TieredTransactionFeeAmounts tieredTranFeeAms) {
		final String sql = getQuery("OFFENDER_TIER_TXN_FEE_AMOUNTS_INSERT");
		final Map<String, Object> inParamMap = new HashMap<String, Object>();

		inParamMap.put("offender_deduction_id", tieredTranFeeAms.getDeductionId());
		inParamMap.put("from_amount", tieredTranFeeAms.getFromAmount());

		inParamMap.put("to_amount", tieredTranFeeAms.getToAmount());
		inParamMap.put("percentage", tieredTranFeeAms.getPercentage());
		inParamMap.put("fee_amount", tieredTranFeeAms.getFromAmount());
		inParamMap.put("createUserId", tieredTranFeeAms.getCreateUserId());
		return namedParameterJdbcTemplate.update(sql, inParamMap);
	}

	@Override
	public Integer updateDeductionStatus(final SuspendDeductions obj, final String userName) {
		final String sql = getQuery("UPDATE_DEDUCTION_STATUS");
		String dedType = null;
		return namedParameterJdbcTemplate.update(sql,
				createParams("P_SUSPEND_FLAG",obj.getSuspendedFlag() , "P_CASELOAD_ID", obj.getCaseloadId(), "P_SUS_DED_ID",
						obj.getSuspendDeductionId(), "P_DEDUCTION_TYPE", obj.getDeductionType(), "modifyUserId", userName));
	}

	@Override
	public List<SuspendDeductions> getSuspendDeductionsDetails() {
		final String sql = getQuery("SUSPEND_OFF_DEDUCTIONS_C_SUS_DED");
		List<SuspendDeductions> retList = new ArrayList<SuspendDeductions>();
		final RowMapper<SuspendDeductions> rowMapper = Row2BeanRowMapper.makeMapping(sql, SuspendDeductions.class,
				susDeductionMapping);
		try {
			retList = namedParameterJdbcTemplate.query(sql, createParams(), rowMapper);
		} catch (Exception e) {
			logger.error("getSuspendDeductionsDetails :" + e);
		}
		return retList;
	}

}
