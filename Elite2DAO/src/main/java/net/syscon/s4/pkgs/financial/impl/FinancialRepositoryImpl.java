package net.syscon.s4.pkgs.financial.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.cf.deductions.beans.OffenderMonDeductions;
import net.syscon.s4.cf.deductions.maintenance.beans.OffenderTierTxnFeeAmounts;
import net.syscon.s4.cf.deductions.maintenance.beans.TieredTransactionFeeAmounts;
import net.syscon.s4.cf.offendertransactions.beans.OffenderCreditPriorPayments;
import net.syscon.s4.cf.offendertransactions.beans.PaymentPlanTransaction;
import net.syscon.s4.common.beans.OffenderPaymentSchedules;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.im.beans.TransactionOperation;
import net.syscon.s4.inmate.beans.BeneficiaryTransactions;
import net.syscon.s4.inmate.beans.OffenderBeneficiaries;
import net.syscon.s4.inmate.beans.OffenderDeductions;
import net.syscon.s4.inmate.beans.OffenderSubAccounts;
import net.syscon.s4.pkgs.financial.FinancialRepository;

@Repository
public class FinancialRepositoryImpl extends RepositoryBase implements FinancialRepository {

	private static Logger logger = LogManager.getLogger(FinancialRepositoryImpl.class.getName());
	final Map<String, FieldMapper> courtCasesMapping = new ImmutableMap.Builder<String, FieldMapper>().build();

	private static final String GETINDDATE = "GETINDDATE";

	@Override
	public String updateOffenderBeneficiaries(final BigDecimal deductionId, final BigDecimal payeePersonId,
			final BigDecimal payeeCorporateId, final BigDecimal txnEntryAmount, final String userName) {
		final String sql = getQuery("UPDATE_OFFENDER_BENEFICIARIES");
		String openAnAccount = null;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("P_DED_ID", deductionId);
		inParamMap.put("P_PER_ID", payeePersonId);
		inParamMap.put("P_CORP_ID", payeeCorporateId);
		inParamMap.put("P_UNKNOWN_ID", null);
		inParamMap.put("P_AMOUNT_TO_BEN", txnEntryAmount);
		inParamMap.put("modifyUserId", userName);
		try {
			namedParameterJdbcTemplate.update(sql, inParamMap);
			openAnAccount = GETINDDATE;
		} catch (Exception e) {
			logger.error("updateOffenderBeneficiaries :" + e);
		}
		return openAnAccount;
	}


	@Override
	public Integer offenderPaymentSchedulesUpdate(final PaymentPlanTransaction plan,Long pRecursiveAmt) {
		Integer liReturn = 0;
		final String sql = getQuery("OFFENDER_PAYMENT_SCHEDULES_UPDATE");
		try {
			liReturn = namedParameterJdbcTemplate.update(sql, createParams("V_PAYMENT_AMOUNT", plan.getTransactionAmount(),
					"P_RECURSIVE_AMT", pRecursiveAmt, "C_PAYMENT_PLAN_ID", plan.getPaymentPlanId(), "C_PAYMENT_PLAN_SEQ", plan.getPaymentPlanSeq(), "C_PAYMENT_DATE", plan.getPaymentDate(),"MODIFY_USER_ID",plan.getModifyUserId()));
		} catch (Exception e) {
			logger.error("Error: Cannot update OFFENDER_PAYMENT_SCHEDULES" + e);
			return liReturn;
		}
		return liReturn;
	}

	@Override
	public Long seqCur(final PaymentPlanTransaction plan) {
		final String sql = getQuery("SEQ_CUR");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("NI_PLNID", plan.getPaymentPlanId(),
				"NI_PLNSEQ", plan.getPaymentPlanSeq(), "DI_PAYDATE", plan.getPaymentDate()), Long.class);
	}

	@Override
	public Integer paymentPlanTransactionsInsert(final PaymentPlanTransaction plan) {
		final String sql = getQuery("PAYMENT_PLAN_TRANSACTIONS_INSERT");
		return namedParameterJdbcTemplate.update(sql,
				createParams("createUserId", plan.getCreateUserId(), "ni_plnid", plan.getPaymentPlanId(), "ni_plnseq",
						plan.getPaymentPlanSeq(), "di_paydate", plan.getPaymentDate(), "di_txndate",
						plan.getTransactionDate(), "ni_txnamt", plan.getTransactionAmount(), "vi_infono",
						plan.getInformationNumber(), "ni_grpid", plan.getGroupId(),"n_txnseq",plan.getTransactionSeq()));
	}

	@Override
	public Integer finnacialSelect(final Long paymentPlanId) {
		Integer vStillOweAmt = 0;
		final String sql = getQuery("GET_V_STILL_AMOUNT");
		try {
			vStillOweAmt = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("C_PAYMENT_PLAN_ID", paymentPlanId), Integer.class);
		} catch (Exception e) {
			logger.error(e);
		}
		return vStillOweAmt;
	}

	@Override
	public Integer offenderPaymentPlansUpdate(final Long paymentPlanId, final String userName) {
		final String sql = getQuery("OFFENDER_PAYMENT_PLANS_UPDATE");
		return namedParameterJdbcTemplate.update(sql,
				createParams("c_payment_plan_id", paymentPlanId, "modifyUserId", userName));
	}

	@Override
	public List<Object[]> cBenTxn(final Long txnId, final Long txnEntrySeq, final long glEntrySeq) {
		final String sql = getQuery("C_BEN_TXN");
		final RowMapper<Object[]> courtCasesRowMapper = Row2BeanRowMapper.makeMapping(sql, Object[].class,
				courtCasesMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("p_rev_txn_id", txnId, "p_rev_txn_entry_seq",
				txnEntrySeq, "p_rev_gl_entry_seq", glEntrySeq), courtCasesRowMapper);
	}

	@Override
	public String insertIntoBenTransactionsSelect(final Long txnId) {
		final String sql = getQuery("INSERT_INTO_BEN_TRANSACTIONS_SELECT");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("P_TXN_ID", txnId), String.class);
	}

	@Override
	public TransactionOperation getTransactionOperationData(String csldType, String pCsldId) {
		final String sql = getQuery("GET_TRANSACTION_OPERATION_DATA");
		TransactionOperation obj = new TransactionOperation();
		
		final RowMapper<TransactionOperation> rowMapper = Row2BeanRowMapper.makeMapping(sql, TransactionOperation.class,
				courtCasesMapping);
		try {
			
			obj = namedParameterJdbcTemplate.queryForObject(sql, createParams("CSLD_TYPE", csldType, "P_CSLD_ID", pCsldId),
					rowMapper);
		} catch (Exception e) {
			logger.error("getTransactionOperationData :" + e);
		}
		return obj;

	}

	@Override
	public AccountCodes getAccountCodesData(String pSubActType, String csldType) {
		final String sql = getQuery("GET_ACCOUNT_CODES_DATA");
		final RowMapper<AccountCodes> rowMapper = Row2BeanRowMapper.makeMapping(sql, AccountCodes.class,
				courtCasesMapping);
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("P_SUB_ACT_TYPE", pSubActType, "CSLD_TYPE", csldType), rowMapper);
	}

	@Override
	public String getProfileValue() {
		String profileValue = "N";
		final String sql = getQuery("GET_PROFILE_VALUE_A");
		try {
			profileValue = namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);
		} catch (Exception e) {
			profileValue = "N";
		}
		return profileValue;
	}

	@Override
	public String getOverrideFlag(Long pOffId) {
		String overrideFlag = "N";
		final String sql = getQuery("GET_OVERRIDE_FLAG");
		try {
			overrideFlag = namedParameterJdbcTemplate.queryForObject(sql, createParams("P_OFF_ID", pOffId),
					String.class);
		} catch (Exception e) {
			overrideFlag = "N";
		}
		return overrideFlag;
	}

	@Override
	public String getAgyLoc(Long pOffBookId) {
		final String sql = getQuery("GET_AGY_LOC");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("P_OFF_BOOK_ID", pOffBookId), String.class);
	}

	@Override
	public List<OffenderDeductions> doDeductionC(String pCsldId, Long pOffId, String pTransType, Long pInfoNumber,
			String csldType, String offIndFlag, Long pShadowId) {
		final String sql = getQuery("DO_DEDUCTION_C");
		final RowMapper<OffenderDeductions> rowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderDeductions.class,
				courtCasesMapping);
		return namedParameterJdbcTemplate.query(sql,
				createParams("P_SHADOW_ID", pShadowId, "P_CSLD_ID", pCsldId, "P_OFF_ID", pOffId, "P_TRANS_TYPE",
						pTransType, "P_INFO_NUMBER", pInfoNumber, "CSLD_TYPE", csldType, "OFF_IND_FLAG",
						offIndFlag),
				rowMapper);
	}

	@Override
	public OffenderDeductions deductionPirorityCur(Long pOffId, int pExtPri, String pCsldId) {
		final String sql = getQuery("DEDUCTION_PIRORITY_CUR");
		final RowMapper<OffenderDeductions> rowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderDeductions.class,
				courtCasesMapping);
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("P_OFF_ID", pOffId, "P_CSLD_ID", pCsldId, "P_EXT_PRI", pExtPri), rowMapper);
	}

	@Override
	public String getMethodFlag() {
		String profileValue = "M1";
		final String sql = getQuery("GET_METHOD_FLAG");
		try {
			profileValue = namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);
		} catch (Exception e) {
			profileValue = "M1";
		}
		return profileValue;
	}

	@Override
	public Integer lTbdCountCur(Long offenderDeductionId) {
		final String sql = getQuery("L_TBD_COUNT_CUR");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("P_OFFENDER_DEDUCTION_ID", offenderDeductionId), Integer.class);
	}

	@Override
	public Integer chkDedBenCur(Long offenderDeductionId) {
		final String sql = getQuery("CHK_DED_BEN_CUR");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("P_OFFENDER_DEDUCTION_ID", offenderDeductionId), Integer.class);
	}

	@Override
	public Date getDefaultEndDate(Long offenderDeductionId) {
		final String sql = getQuery("GET_DEFAULT_ENDDATE");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("DED_ID", offenderDeductionId), Date.class);
	}

	@Override
	public String checkCollectionFee(Long offenderDeductionId, String dedType) {
		final String sql = getQuery("CHECK_COLLECTION_FEE");
		String result = null;
		try {
			
			result = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("CF_OFFENDER_DEDUCTION_ID", offenderDeductionId, "DED_TYPE", dedType), String.class);
		} catch (Exception e) {
			logger.error("checkCollectionFee :" + e);
		}
		return result;
	}

	@Override
	public TieredTransactionFeeAmounts getTieredFeeAmounts(String pCsldId, String deductionType, String pTransType,
			BigDecimal pReceiptAmount) {
		final String sql = getQuery("GET_TIERED_FEE_AMOUNTS");
		final RowMapper<TieredTransactionFeeAmounts> rowMapper = Row2BeanRowMapper.makeMapping(sql,
				TieredTransactionFeeAmounts.class, courtCasesMapping);
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("P_CSLD_ID", pCsldId, "DED_TYPE",
				deductionType, "P_TRANS_TYPE", pTransType, "P_RECEIPT_AMOUNT", pReceiptAmount), rowMapper);
	}

	@Override
	public BigDecimal getOverrideAmount(Long offenderDeductionId, String dedType) {
		final String sql = getQuery("GET_OVERRIDE_AMOUNT");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("DED_ID", offenderDeductionId),
				BigDecimal.class);
	}

	@Override
	public BigDecimal getMinTrustBalance(String pCsldId, String deductionType, String pTransType) {
		final String sql = getQuery("GET_MIN_TRUST_BALANCE");
		BigDecimal minTrustBalance;
		try {
			minTrustBalance = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("P_CSLD_ID", pCsldId, "DED_TYPE", deductionType, "P_TRANS_TYPE", pTransType),
					BigDecimal.class);
		} catch (Exception e) {
			minTrustBalance = BigDecimal.ZERO;
		}
		return minTrustBalance;
	}

	@Override
	public List<OffenderBeneficiaries> getLTbdCheckCur(Long offenderDeductionId, String overrideFlag) {
		final String sql = getQuery("GET_L_TBD_CHECK_CUR");
		final RowMapper<OffenderBeneficiaries> rowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderBeneficiaries.class, courtCasesMapping);
		return namedParameterJdbcTemplate.query(sql,
				createParams("P_OFFENDER_DEDUCTION_ID", offenderDeductionId, "P_OVERRIDE_FLAG", overrideFlag),
				rowMapper);
	}

	@Override
	public String getSubActType(Integer accountCode, String csldType) {
		final String sql = getQuery("GET_SUB_ACCOUNT_TYPE");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("CODE_ACNT", accountCode, "CSLD_TYPE", csldType), String.class);
	}

	@Override
	public OffenderDeductions getOffenderDeductions(Long offenderDeductionId) {
		final String sql = getQuery("GET_OFFENDER_DEDUCTIONS");
		final RowMapper<OffenderDeductions> rowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderDeductions.class,
				courtCasesMapping);
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("DED_ID", offenderDeductionId), rowMapper);
	}

	@Override
	public void updateOverrideAmount(Long offenderDeductionId,String userName) {
		final String sql = getQuery("UPDTAE_OVERRIDE_AMOUNT");
		namedParameterJdbcTemplate.update(sql, createParams("DED_ID", offenderDeductionId,"MODIFY_USER_ID",userName));

	}

	@Override
	public OffenderBeneficiaries getOffenderBeneficiaries(BigDecimal offenderDeductionId, BigDecimal personId,
			BigDecimal corporateId) {
		OffenderBeneficiaries retObj = null;
		final String sql = getQuery("GET_OFFENDER_BENEFICIARIES");
		try {
			retObj = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("OFFENDER_DEDUCTION_ID", offenderDeductionId, "PERSON_ID", personId, "CORPORATE_ID",
							corporateId),
					new BeanPropertyRowMapper<OffenderBeneficiaries>(OffenderBeneficiaries.class));
		} catch (Exception e) {
			logger.error(e);
		}
		return retObj;
	}

	@Override
	public String lvDedLock(BigDecimal pOffId, String pCsldId) {
		String lvDedLock = null;
		final String sql = getQuery("GET_LEV_DED_LOCK");
		try {
			lvDedLock = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("offenderId", pOffId, "caseloadId", pCsldId), String.class);
		} catch (Exception e) {
			logger.error(e);
		}
		return lvDedLock;
	}

	@Override
	public String lvBenLock(BigDecimal pOffId) {
		String lvBenLock = null;
		final String sql = getQuery("GET_LEV_BEN_LOCK");
		try {
			lvBenLock = namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderId", pOffId),
					String.class);
		} catch (Exception e) {
			logger.error(e);
		}
		return lvBenLock;

	}

	@Override
	public String getCfppFlag(BigDecimal pTransNumber) {
		String lvCfppFlag = null;
		final String sql = getQuery("GET_LV_CFPP_FLAG");
		try {
			lvCfppFlag = namedParameterJdbcTemplate.queryForObject(sql, createParams("pTxnId", pTransNumber),
					String.class);
			if (lvCfppFlag == null) {
				lvCfppFlag = "N";
			}
		} catch (Exception e) {
			logger.error(e);
			lvCfppFlag = "N";
		}
		return lvCfppFlag;
	}

	@Override
	public BigDecimal getTrustAccCode(String pCsldId, String moduleName, String pTransType) {
		final String sql = getQuery("GET_TRUSTACCODE");
		BigDecimal trustAccCode = null;
		try {
			trustAccCode = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("caseloadId", pCsldId, "modName", moduleName, "txnType", pTransType),
					BigDecimal.class);
		} catch (Exception e) {
			logger.error(e);
		}
		return trustAccCode;
	}

	@Override
	public OffenderSubAccounts getAmountPMinbal(String pCsldId, BigDecimal offenderId, BigDecimal trustAccCode) {
		final String sql = getQuery("GET_OFFENDERSUBACCOUNTS_PAMOUNT_PMINBAL");
		OffenderSubAccounts retObj = null;
		try {
			retObj = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("caseloadId", pCsldId, "pOffId", offenderId, "trustAccCode", trustAccCode),
					new BeanPropertyRowMapper<OffenderSubAccounts>(OffenderSubAccounts.class));
		} catch (Exception e) {
			logger.error(e);
		}
		return retObj;
	}

	@Override
	public String chkCaseloadC(String pCsldId) {
		String chkCaseloadC = null;
		final String sql = getQuery("CHK_CASELOAD_C_FINANCIAL");
		try {
			chkCaseloadC = namedParameterJdbcTemplate.queryForObject(sql, createParams("caseloadId", pCsldId),
					String.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in chkCaseloadC " + e);
		}
		return chkCaseloadC;
	}

	@Override
	public BigDecimal getInstMiniBal(String pCsldId, String pAgyLocId, BigDecimal trustAccCode) {
		final String sql = getQuery("GET_INSTITUTIONALMINIBALANCE_PMINBAL");
		BigDecimal minBal = null;

		try {
			minBal = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("caseloadId", pCsldId, "pAgyLocId", pAgyLocId, "trustAccCode", trustAccCode),
					BigDecimal.class);
			if (minBal == null) {
				minBal = BigDecimal.ZERO;
			}
		} catch (Exception e) {
			logger.error(e);
			minBal = BigDecimal.ZERO;
		}
		return minBal;
	}

	@Override
	public SystemProfiles systemProfileC() {
		final String sql = getQuery("SYSTEM_PROFILE_C");
		SystemProfiles retObj = null;
		try {
			retObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
					new BeanPropertyRowMapper<SystemProfiles>(SystemProfiles.class));
		} catch (Exception e) {
			logger.error(e);
		}
		return retObj;
	}

	@Override
	public String getRecOverpayFlag() {
		String getRecOverpayFlag = null;
		final String sql = getQuery("GET_REC_OVERPAY_FLAG");
		try {
			getRecOverpayFlag = namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);
			if (getRecOverpayFlag == null) {
				getRecOverpayFlag = "N";
			}
		} catch (Exception e) {
			logger.error(e);
			getRecOverpayFlag = "N";
		}
		return getRecOverpayFlag;
	}

	@Override
	public OffenderDeductions getActiveCollectionFee(BigDecimal offenderId, String pCsldId) {
		final String sql = getQuery("GET_ACTIVE_COLLECTION_FEE");
		OffenderDeductions retObj = new OffenderDeductions();
		try {
			retObj = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("pOffId", offenderId, "caseloadId", pCsldId),
					new BeanPropertyRowMapper<OffenderDeductions>(OffenderDeductions.class));
		} catch (Exception e) {
			logger.error(e);
			retObj.setOffenderDeductionId(0L);
		}
		return retObj;
	}

	@Override
	public BigDecimal lOffenderBookIdCur(BigDecimal pOffId,String userId) {
		final String sql = getQuery("L_OFFENDER_BOOK_ID_CUR");
		BigDecimal minBal = null;
		try {
			minBal = namedParameterJdbcTemplate.queryForObject(sql, createParams("P_ROOT_OFFENDER_ID", pOffId,"USERID",userId),
					BigDecimal.class);
		} catch (Exception e) {
			logger.error(e);
		}
		return minBal;
	}

	@Override
	public Date lPaymentPlanEnddateCur(Long pOffDedId) {
		final String sql = getQuery("L_PAYMENT_PLAN_ENDDATE_CUR");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("P_OFFENDER_DEDUCTION_ID", pOffDedId),
				Date.class);
	}

	@Override
	public Date lSentenceEnddateCur(Date pDate, Long offBookId) {
		final String sql = getQuery("L_SENTENCE_ENDDATE_CUR");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("P_OFFENDER_BOOK_ID", offBookId, "P_SYSDATE", pDate), Date.class);
	}

	@Override
	public Date addMonths(Date pDate, Integer interval) {
		final String sql = getQuery("ADD_MONTHS");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("DIFFTIME", interval, "P_DATE", pDate),
				Date.class);
	}

	@Override
	public Long lBeneficiaryPaidCur(Long pOffDedId, Long pBenficiaryId, Long pPriority) {
		final String sql = getQuery("L_BENEFICIARY_PAID_CUR");
		Long sumAmount = null;
		try {
			sumAmount = namedParameterJdbcTemplate.queryForObject(sql, createParams("P_OFFENDER_DEDUCTION_ID",
					pOffDedId, "P_BENEFICIARY_ID", pBenficiaryId, "P_PRIORITY", pPriority), Long.class);
		} catch (Exception e) {
			logger.error(e);
		}
		return sumAmount;
	}

	@Override
	public OffenderCreditPriorPayments getCfppLocation(Long pTransNumber) {
		final String sql = getQuery("GET_CFPP_TXN_DESC_LOCATION");
		OffenderCreditPriorPayments retObj = null;
		try {
			retObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("P_TRANS_NUMBER", pTransNumber),
					new BeanPropertyRowMapper<OffenderCreditPriorPayments>(OffenderCreditPriorPayments.class));
		} catch (Exception e) {
			logger.error(e);
		}
		return retObj;
	}

	@Override
	public String lvTxnEntryDesc(BigDecimal amntOwing, String lvCppLocation, String lvCppCommentText) {
		String lvTxnEntryDescpAmount = null;
		final String sql = getQuery("LV_TXN_ENTRY_DESC");
		try {
			lvTxnEntryDescpAmount = namedParameterJdbcTemplate.queryForObject(sql, createParams("P_AMNT_OWING",
					amntOwing, "LV_CPP_LOCATION", lvCppLocation, "LV_CPP_COMMENT_TEXT", lvCppCommentText),
					String.class);
		} catch (Exception e) {
			logger.error(e);
		}
		return lvTxnEntryDescpAmount;
	}

	@Override
	public OffenderDeductions lDeductionCur(Long offenderDeductionId) {
		final String sql = getQuery("L_DEDUCTION_CUR");
		OffenderDeductions retObj = null;
		try {
			retObj = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("P_OFFENDER_DEDUCTION_ID", offenderDeductionId),
					new BeanPropertyRowMapper<OffenderDeductions>(OffenderDeductions.class));
		} catch (Exception e) {
			logger.error(e);
		}
		return retObj;
	}

	@Override
	public String lRecursiveOverpayCur() {
		String lRecursiveOverpay = null;
		final String sql = getQuery("L_RECURSIVE_OVERPAY_CUR");
		try {
			lRecursiveOverpay = namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);
		} catch (Exception e) {
			logger.error(e);
		}
		return lRecursiveOverpay;
	}

	@Override
	public Long lOffenderBookIdCurOne(String pCsldId, BigDecimal pRootOffId) {
		Long lOffenderBookIdCur = 0L;
		final String sql = getQuery("L_OFFENDER_BOOK_ID_CUR_ONE");
		try {
			lOffenderBookIdCur = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("P_ROOT_OFFENDER_ID", pRootOffId, "P_CASELOAD_ID", pCsldId), Long.class);
		} catch (Exception e) {
			logger.error(e);
		}
		return lOffenderBookIdCur;
	}

	@Override
	public List<OffenderBeneficiaries> lOverrideCure(Long offenderDeductionId) {
		final String sql = getQuery("L_OVERRIDE_CUR");
		final RowMapper<OffenderBeneficiaries> rowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderBeneficiaries.class, courtCasesMapping);
		List<OffenderBeneficiaries> offBen = new ArrayList<OffenderBeneficiaries>();
		try {
			offBen = namedParameterJdbcTemplate.query(sql, createParams("P_OFFENDER_DEDUCTION_ID", offenderDeductionId),
					rowMapper);
		} catch (Exception e) {
			logger.error(e);
		}
		return offBen;
	}

	@Override
	public String insertBenTxnsgetCfppFlag(Long pTxnId) {
		String cfppFlag = null;
		final String sql = getQuery("INSERT_INTO_BEN_TRANSACTIONS_CFPP_FLAG");
		try {
			cfppFlag = namedParameterJdbcTemplate.queryForObject(sql, createParams("P_TXN_ID", pTxnId), String.class);
		} catch (Exception e) {
			logger.error(e);
		}
		return cfppFlag;
	}

	@Override
	public BigDecimal cfppPaymentPerCur(Long pDeductionId, BigDecimal pPersonId) {
		final String sql = getQuery("CFPP_PAYMENT_PER_CUR");
		BigDecimal overrideAmount = null;
		try {
			overrideAmount = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("P_DED_ID", pDeductionId, "P_PER_ID", pPersonId), BigDecimal.class);
		} catch (Exception e) {
			logger.error("Unabled to retrieve CFPP amount " + e);
		}
		return overrideAmount;
	}

	@Override
	public BigDecimal cfppPaymentCorpCur(Long pDeductionId, BigDecimal pPersonId) {
		final String sql = getQuery("CFPP_PAYMENT_CORP_CUR");
		BigDecimal overrideAmount = null;
		try {
			overrideAmount = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("P_DED_ID", pDeductionId, "P_CORP_ID", pPersonId), BigDecimal.class);
		} catch (Exception e) {
			logger.error("Unabled to retrieve CFPP amount " + e);
		}
		return overrideAmount;
	}

	public String vTxnEntryDesc(BigDecimal cfppPayment, String pTxnEntrySeq) {
		String lvTxnEntryDescpAmount = null;
		final String sql = getQuery("LV_TXN_ENTRY_DESC");
		try {
			lvTxnEntryDescpAmount = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("CFPP_PAYMENT", cfppPayment, "P_TXN_ENTRY_DESC", pTxnEntrySeq), String.class);
		} catch (Exception e) {
			logger.error(e);
		}
		return lvTxnEntryDescpAmount;
	}

	@Override
	public Integer insertIntoBenficiaryTransactions(BeneficiaryTransactions obj) {
		final String sql = getQuery("INSERT_INTO_BENEFICIARY_TRANSACTIONS");
		Integer liReturn = 0;
		try {
			liReturn = namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(obj));
		} catch (Exception e) {
			logger.error("Error: FINANCIAL.do_beneficiary_transactions" + e);
		}
		return liReturn;
	}

	@Override
	public void updateIntoBeneficiaryTransactions(Long pRevTxnId, Long pRevEntrySeq, Long pRevGlSeq, Long pRevBenSeq) {
		final String sql = getQuery("UPDATE_BENEFICIARY_TRANSACTIONS");
		namedParameterJdbcTemplate.update(sql, createParams("P_REV_TXN_ID", pRevTxnId, "P_REV_ENTRY_SEQ", pRevEntrySeq,
				"P_REV_GL_SEQ", pRevGlSeq, "P_REV_BEN_SEQ", pRevBenSeq));
	}

	@Override
	public List<OffenderBeneficiaries> lPriorityCur(Long pOffDedId) {
		final String sql = getQuery("L_PRIORITY_CUR");
		final RowMapper<OffenderBeneficiaries> rowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderBeneficiaries.class, courtCasesMapping);
		List<OffenderBeneficiaries> offBen = new ArrayList<OffenderBeneficiaries>();
		try {
			offBen = namedParameterJdbcTemplate.query(sql, createParams("P_OFFENDER_DEDUCTION_ID", pOffDedId),
					rowMapper);
		} catch (Exception e) {
			logger.error(e);
		}
		return offBen;
	}

	@Override
	public List<OffenderBeneficiaries> lBeneficiaryCur(Long pOffDedId, Long pPriority, String pRecFlag,
			String pMonthlyFlag) {
		final String sql = getQuery("L_BENEFICIARY_CUR");
		final RowMapper<OffenderBeneficiaries> rowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderBeneficiaries.class, courtCasesMapping);
		List<OffenderBeneficiaries> offBen = new ArrayList<OffenderBeneficiaries>();
		try {
			offBen = namedParameterJdbcTemplate.query(sql, createParams("P_OFFENDER_DEDUCTION_ID", pOffDedId,
					"P_PRIORITY", pPriority, "P_RECURSIVE_FLAG", pRecFlag, "P_MONTHLY_FLAG", pMonthlyFlag), rowMapper);
		} catch (Exception e) {
			logger.error(e);
		}
		return offBen;
	}

	public void updateOffenderDeductionsFinOffBeneficiaries(Long pOffDedId,String userName) {
		final String sql = getQuery("UPDATE_OFFENDER_DEDUCTIONS_FIN_OFFENDER_BENEFICIARIES");
		try {
			namedParameterJdbcTemplate.update(sql, createParams("P_OFF_DED_ID", pOffDedId,"modifyUserId",userName));
		} catch (Exception e) {
			logger.error(e);
		}
	}

	@Override
	public void updateOffenderDeductionsFinOffDed(Long pOffDedId, Long pAmtToDeduct, String userName) {
		final String sql = getQuery("UPDATE_OFFENDER_DEDUCTIONS_FIN_OFFENDER_BENEFICIARIES");
		try {
			namedParameterJdbcTemplate.update(sql, createParams("P_OFF_DED_ID", pOffDedId, "P_AMOUNT_TO_DEDUCT",
					pAmtToDeduct, "MODIFY_USER_ID", userName));
		} catch (Exception e) {
			logger.error("Error: Cannot update Offender Deduction" + e);
		}
	}

	@Override
	public OffenderMonDeductions lLastRecursivePaidCur(Long pOffDedId) {
		final String sql = getQuery("L_LAST_RECURSIVE_PAID_CUR");
		OffenderMonDeductions retObj = null;
		try {
			retObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("P_OFFENDER_DEDUCTION_ID", pOffDedId),
					new BeanPropertyRowMapper<OffenderMonDeductions>(OffenderMonDeductions.class));
		} catch (Exception e) {
			logger.error(e);
		}
		return retObj;
	}

	@Override
	public Integer updateOffMonDeductions(Long lAmtToApply, Long deductionAmt, Long pOffDedId, String modifyUserName) {
		Integer liReturn = 0;
		final String sql = getQuery("UPDATE_OFFENDER_MON_DEDUCTIONS_FIN");
		try {
			liReturn = namedParameterJdbcTemplate.update(sql,
					createParams("P_OFF_DED_ID", pOffDedId, "L_AMOUNT_TO_APPLY", lAmtToApply, "DEDUCTION_AMOUNT",
							deductionAmt, "modifyUserId", modifyUserName));
		} catch (Exception e) {
			logger.error("Error: Cannot update OFFENDER_MON_DEDUCTION" + e);
			return liReturn;
		}
		return liReturn;
	}

	@Override
	public Integer insertOffMonDeductions(OffenderMonDeductions insertobj) {
		Integer liReturn = 0;
		final String sql = getQuery("INSERT_INTO_OFFENDER_MON_DEDUCTIONS_FIN");
		try {
			liReturn = namedParameterJdbcTemplate.update(sql,
					createParams("P_OFF_DED_ID", insertobj.getOffenderDeducttionId(), "P_AMOUNT_TO_DEDUCT",
							insertobj.getDeductionAmount(), "MONTHLY_DEDUCTION_DATE",
							insertobj.getMonthlyDeductionDate(), "USERNAME", insertobj.getCreateUserId()));
		} catch (Exception e) {
			logger.error("Error: Cannot Insert OFFENDER_MON_DEDUCTION" + e);
		}
		return liReturn;
	}

	@Override
	public Integer monthsBetweenUpdateFin(Date lRecursiveSartdate, Date lRecursiveEndDate) {
		Integer monthsBetween = 0;
		final String sql = getQuery("MONTHS_BETWEEN_UPDATE_DEDUCTIONS_FIN");
		monthsBetween = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("L_RECURSIVE_START_DATE", lRecursiveSartdate, "L_RECURSIVE_END_DATE", lRecursiveEndDate),
				Integer.class);
		return monthsBetween;
	}

	public Integer updateOffMonDeductionsOne(Long pAmtDeduct, Long pOffDedId, String modifyUserName) {
		Integer liReturn = 0;
		final String sql = getQuery("UPDATE_OFFENDER_MON_DEDUCTIONS_FIN_ONE");
		try {
			liReturn = namedParameterJdbcTemplate.update(sql, createParams("P_OFF_DED_ID", pOffDedId,
					"P_AMOUNT_TO_DEDUCT", pAmtDeduct, "MODIFY_USER_ID", modifyUserName));
		} catch (Exception e) {
			logger.error("Error: Cannot update OFFENDER_MON_DEDUCTION" + e);
			return liReturn;
		}
		return liReturn;
	}

	@Override
	public List<OffenderPaymentSchedules> paymentPeriod(String pInfoNumber, BigDecimal pGroupId, Long pOffId) {
		final String sql = getQuery("PAYMENT_PERIOD");
		List<OffenderPaymentSchedules> retObj = null;
		try {
			retObj = namedParameterJdbcTemplate.query(sql, createParams("P_OFF_ID", pOffId,"P_GRP_ID",pGroupId,"P_INFO_NUMBER",pInfoNumber),
					new BeanPropertyRowMapper<OffenderPaymentSchedules>(OffenderPaymentSchedules.class));
		} catch (Exception e) {
			logger.error(e);
		}
		return retObj;
	}

	@Override
	public String chkDedBenCurOne(Long cfOffDedId) {
			final String sql = getQuery("CHK_DED_BEN_CUR_ONE");
			String chkDed = null;
			try {
				chkDed = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("CF_OFFENDER_DEDUCTION_ID", cfOffDedId), String.class);
			} catch (Exception e) {
				logger.error(e);
			}
			return chkDed;
	}

	@Override
	public OffenderTierTxnFeeAmounts getRpercFlatFee(Long offDedId) {
		final String sql = getQuery("GET_RPERCENT_FLATFEE");
		OffenderTierTxnFeeAmounts retObj = null;
		try {
			retObj = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("CF_OFFENDER_DEDUCTION_ID", offDedId),
					new BeanPropertyRowMapper<OffenderTierTxnFeeAmounts>(OffenderTierTxnFeeAmounts.class));
		} catch (Exception e) {
			logger.error(e);
		}
		return retObj;
	}
	
	@Override
	public OffenderBeneficiaries getOffenderBeneficiariesById(Long beneficiaryId) {
		final String sql = getQuery("GET_OFFENDER_BENEFICIARIES_BYID");
		OffenderBeneficiaries retObj = null;
		try {
			retObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("BENEFICIARY_ID", beneficiaryId),
					new BeanPropertyRowMapper<OffenderBeneficiaries>(OffenderBeneficiaries.class));
		} catch (Exception e) {
			logger.error("getOffenderBeneficiariesById :", e);
		}
		return retObj;
	}
	
	@Override
	public Long lBeneficiaryMonOwingCur(Long pOffDedId, Long beneficiaryId) {
		final String sql = getQuery("L_BENEFICIARY_MON_OWING_CUR");
		Long retVal = 0l;
		try {
			retVal = namedParameterJdbcTemplate.queryForObject(sql, createParams("P_OFFENDER_DEDUCTION_ID",pOffDedId,"P_BENEFICIARY_ID",beneficiaryId), Long.class);
		} catch (Exception e) {
			logger.error("lBeneficiaryMonOwingCur :", e);
		}
		return retVal;
	}
}
