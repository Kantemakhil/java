package net.syscon.s4.pkgs.trust.impl;

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
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.InstitutionMiniBalances;
import net.syscon.s4.common.beans.OtddisbuProcessTransactionsBean;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.TransactionTypes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.im.beans.BankChequeData;
import net.syscon.s4.im.beans.ChkFreezeDisbursements;
import net.syscon.s4.im.beans.OffenderDeductionReceipts;
import net.syscon.s4.im.beans.OffenderLimits;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.inmate.beans.CaseloadDeductionProfiles;
import net.syscon.s4.inmate.beans.GlTransactions;
import net.syscon.s4.inmate.beans.OffenderBeneficiaries;
import net.syscon.s4.inmate.beans.OffenderDeductions;
import net.syscon.s4.inmate.beans.OffenderSubAccounts;
import net.syscon.s4.pkgs.trust.TrustRepository;

@Repository
public class TrustRepositoryImpl extends RepositoryBase implements TrustRepository {

	private static Logger logger = LogManager.getLogger(TrustRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> corpBenMapping = new ImmutableMap.Builder<String, FieldMapper>().build();
	private final Map<String, FieldMapper> mapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("ORDER_PROPOSAL_CONDITION_ID", new FieldMapper("orderProposalConditionId")).build();

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

	@Override
	public List<OffenderBeneficiaries> getCorpBeneficiariesRecord(final BigDecimal corporateId) {
		final String sql = getQuery("CALCULATE_BENEFICIARIES_TOTAL_SERVICE_CORP_BENEFICIARIES_RECORD");
		final RowMapper<OffenderBeneficiaries> offBenRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderBeneficiaries.class, corpBenMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("CORPORATEID", corporateId), offBenRowMapper);

	}

	@Override
	public Integer getMonAmt(final Long offenderDeductionId, final BigDecimal corporateId) {
		final String sql = getQuery("CALCULATE_BENEFICIARIES_TOTAL_MON_AMT");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("offenderDeductionId", offenderDeductionId, "corporateId", corporateId), Integer.class);
	}

	@Override
	public Integer getRecMonth(final Long offenderDeductionId) {
		final String sql = getQuery("CALCULATE_BENEFICIARIES_TOTAL_MON_AMT");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderDeductionId", offenderDeductionId),
				Integer.class);
	}

	@Override
	public String chkAccountStatusSelect(final String caseLoadId, final BigDecimal offenderId) {
		final String sql = getQuery("CHK_ACCOUNT_STATUS_SELECT");
		String openAnAccount = null;
		try {
			openAnAccount = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("caseLoadId", caseLoadId, "offenderId", offenderId), String.class);
		} catch (Exception e) {
			logger.error("chkAccountStatusSelect", e);
			openAnAccount = null;
		}
		return openAnAccount;
	}

	@Override
	public Integer tempFlagSelectOperation(final ChkFreezeDisbursements chkFreeze) {
		final String sql = getQuery("TEMP_FLAG_SELECT_OPERATION");
		Integer count = 0;

		try {
			count = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("acntCode", chkFreeze.getpAcntCode(), "txnType", chkFreeze.getpTxnType(), "caseLoadId",
							chkFreeze.getpCsldId(), "moduleName", chkFreeze.getpModuleName(), "caseLoadType",
							chkFreeze.getpCsldType()),
					Integer.class);
		} catch (Exception e) {
			logger.error("tempFlagSelectOperation", e);
			count = 0;
		}
		return count;

	}

	@Override
	public Integer frzFlagSelectOperation(final String caseLoadId, final Long offenderId, final Date date) {
		final String sql = getQuery("FRZ_FLAG_SELECT_OPERATION");
		Integer count = 0;

		try {
			count = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("caseLoadId", caseLoadId, "offenderId", offenderId, "date", date), Integer.class);
		} catch (Exception e) {
			logger.error("frzFlagSelectOperation", e);
			count = 0;
		}
		return count;
	}

	@Override
	public String csldTypeC(final String caseLoadId) {
		final String sql = getQuery("GET_CASE_LOAD_TYPE");
		String caseLoadType = null;
		try {
			caseLoadType = namedParameterJdbcTemplate.queryForObject(sql, createParams("caseLoadId", caseLoadId),
					String.class);
		} catch (Exception e) {
			logger.error("csldTypeC", e);
			caseLoadType = null;
		}
		return caseLoadType;
	}

	public String getLowHighFlag() {
		final String sql = getQuery("GET_LOW_HIGH_FLAG_INTO_PROFILE_VALUE");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);
	}

	@Override
	public List<OffenderDeductionReceipts> getRateCur(final String disbuType, final Long dedId) {
		final String sql = getQuery("GET_GET_RATE_CUR");
		final RowMapper<OffenderDeductionReceipts> rowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderDeductionReceipts.class, mapping);
		return namedParameterJdbcTemplate.query(sql, createParams("DISBU_TYPE", disbuType, "DED_ID", dedId), rowMapper);

	}

	@Override
	public String getVTxnRefNum(final Long transNumber, final Long transSeq) {
		final String sql = getQuery("GET_V_TXN_REF_NUM");
		String desc = null;
		try {
			desc = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("transNumber", transNumber, "transSeq", transSeq), String.class);
		} catch (Exception e) {
			logger.error("getVTxnRefNum", e);
			desc = null;
		}
		return desc;
	}

	@Override
	public Integer glTransInsert(final GlTransactions glTra) {
		final String sql = getQuery("GL_TRANS_INSERT");
		Integer returnArray = null;
		List<GlTransactions> list = new ArrayList<GlTransactions>();
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();

//		for (final GlTransactions ab : glTra) {
//			parameters.add(new BeanPropertySqlParameterSource(ab));
//		}
		try {
			returnArray = namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(glTra));

		} catch (final Exception e) {
			logger.error("glTransInsert:", e);
			return 0;
		}
		return returnArray;

	}

	@Override
	public Long insertIntoChequeDataSelectOperation(final String caseloadId, final String moduleName,
			final String transType,String userId) {
		final String sql = getQuery("INSERT_INTO_CHEQUE_DATA_SELECT_OPERATION_FN");
		Long bankCode = 0L;

		try {
			bankCode = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("caseloadId", caseloadId, "moduleName", moduleName, "transType", transType,"userId",userId),
					Long.class);

		} catch (Exception e) {
			logger.error("InsertIntoChequeDataSelectOperation", e);
			bankCode = 0L;
		}
		return bankCode;
	}

	@Override
	public Integer insertIntoChequeDataInsertOperation(final BankChequeData cheData) {
		String sql = getQuery("INSERT_INTO_CHEQUE_DATA_INSERT_OPERATION");
		int[] returnArray = new int[] {};

		List<BankChequeData> list = new ArrayList<BankChequeData>();
		list.add(cheData);

		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();

		for (final BankChequeData ocn : list) {
			parameters.add(new BeanPropertySqlParameterSource(ocn));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (final Exception e) {
			logger.error("InsertIntoChequeDataInsertOperation:", e);
		}
		if (list.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public void updateOffenderSubAccounts(final Date txnEntryDate, final Integer txnId, final Double postingAmount,
			final String caseloadId, final Long offenderId, final String subAccountType, final String caseLoadType,
			final String userName) {
		final String sql = getQuery("UPDATE_OFFENDER_SUB_ACCOUNTS_A");
		Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("txnEntryDate", txnEntryDate);
		inParamMap.put("txnId", txnId);
		inParamMap.put("postingAmount", postingAmount);
		inParamMap.put("caseloadId", caseloadId);
		inParamMap.put("offenderId", offenderId);
		inParamMap.put("subAccountType", subAccountType);
		inParamMap.put("caseLoadType", caseLoadType);
		inParamMap.put("modifyUserId", userName);
		namedParameterJdbcTemplate.update(sql, inParamMap);
	}

	@Override
	public void updateOffenderTrustAccount(final Date txnEntryDate, final Double postingAmount, final String caseloadId,
			final Long offenderId, final String userName) {
		final String sql = getQuery("UPDATE_OFFENDER_TRUST_ACCOUNTS_B");
		Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("txnEntryDate", txnEntryDate);
		inParamMap.put("postingAmount", postingAmount);
		inParamMap.put("caseloadId", caseloadId);
		inParamMap.put("offenderId", offenderId);
		inParamMap.put("modifyUserId", userName);
		namedParameterJdbcTemplate.update(sql, inParamMap);

	}

	@Override
	public Double getBalanceFromOffenderAccounts(final String caseloadId, final Long offenderId,
			final String subAccountType, final String caseLoadType) {
		final String sql = getQuery("GET_BALANCE_FROM_OFFENDER_SUB_ACOUNTS");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("caseloadId", caseloadId, "offenderId",
				offenderId, "subAccountType", subAccountType, "caseLoadType", caseLoadType), Double.class);
	}

	@Override
	public void updateOffenderSubAccountsWhenOverridenFlagN(final Date txnEntryDate, final Integer txnId,
			final Double postingAmount, final String caseloadId, final Long offenderId, final String subAccountType,
			final String caseLoadType, final String userName) {
		final String sql = getQuery("UPDATE_SUB_ACCOUNTS_WHEN_OVERRIDDEN_FLAGN");
		Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("txnEntryDate", txnEntryDate);
		inParamMap.put("txnId", txnId);
		inParamMap.put("pstng_amount", postingAmount);
		inParamMap.put("caseloadId", caseloadId);
		inParamMap.put("offenderId", offenderId);
		inParamMap.put("subAccountType", subAccountType);
		inParamMap.put("caseLoadType", caseLoadType);
		inParamMap.put("modifyUserId", userName);
		namedParameterJdbcTemplate.update(sql, inParamMap);
	}

	@Override
	public List<AccountCodes> getSubActNdTxnPostTypesDr(final OtddisbuProcessTransactionsBean proTxn1) {
		final String sql = getQuery("GET_SUB_ACT_ND_TXN_POST_TYPES");
		final RowMapper<AccountCodes> rowMapper = Row2BeanRowMapper.makeMapping(sql, AccountCodes.class, mapping);
		List<AccountCodes> retList = new ArrayList<AccountCodes>();
		try {
			retList = namedParameterJdbcTemplate
					.query(sql,
							createParams("p_module_name", proTxn1.getpModuleName(), "p_txn_type", proTxn1.getpTxnType(),
									"csld_id", proTxn1.getpCaseloadId(), "csld_type", proTxn1.getCaseLoadType()),
							rowMapper);
		} catch (Exception e) {
			logger.error("getSubActNdTxnPostTypesDr", e);
			retList = null;
		}
		return retList;
	}

	@Override
	public List<AccountCodes> getSubActNdTxnPostTypesCr(final OtddisbuProcessTransactionsBean proTxn1) {
		final String sql = getQuery("GET_SUB_ACT_ND_TXN_POST_TYPES_CR");
		final RowMapper<AccountCodes> rowMapper = Row2BeanRowMapper.makeMapping(sql, AccountCodes.class, mapping);
		List<AccountCodes> retList = new ArrayList<AccountCodes>();
		try {
			retList = namedParameterJdbcTemplate
					.query(sql,
							createParams("p_module_name", proTxn1.getpModuleName(), "p_txn_type", proTxn1.getpTxnType(),
									"csld_id", proTxn1.getpCaseloadId(), "csld_type", proTxn1.getCaseLoadType()),
							rowMapper);
		} catch (Exception e) {
			logger.error("getSubActNdTxnPostTypesCr", e);
			retList = null;
		}
		return retList;
	}

	@Override
	public List<AccountCodes> postingCCursor(final Object pSubActTypeDr, final String pSubActTypeCr,
			final String csldType, final String pTransType, final String pModuleName, final Object pOperationType,
			final String pCsldId) {
		final String sql = getQuery("POSTING_C_CURSOR");
		List<AccountCodes> retList = new ArrayList<AccountCodes>();
		final RowMapper<AccountCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, AccountCodes.class,
				accCodesMapping);

		try {
			retList = namedParameterJdbcTemplate.query(sql,
					createParams("p_csld_id", pCsldId, "csld_type", csldType, "p_operation_type", pOperationType,
							"p_module_name", pModuleName, "p_trans_type", pTransType, "p_sub_act_type_cr",
							pSubActTypeCr, "p_sub_act_type_dr", pSubActTypeDr),
					mRowMapper);
		} catch (Exception e) {
			logger.error("postingCCursor", e);
			//retList = null;
		}
		return retList;
	}

	@Override
	public String getCaseLoadType(final String caseloadId) {
		final String sql = getQuery("GET_CASELOAD_TYPE_A");
		String retVal = null;
		try {
			retVal = namedParameterJdbcTemplate.queryForObject(sql, createParams("caseloadId", caseloadId),
					String.class);
		} catch (Exception e) {
			logger.error(e);
		}
		return retVal;
	}

	@Override
	public TransactionTypes getTransTypes(final String txnType, final String caseLoadType) {
		final String sql = getQuery("GET_TRANS_TYPES");
		final RowMapper<TransactionTypes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, TransactionTypes.class,
				accCodesMapping);
		try {
			return namedParameterJdbcTemplate.queryForObject(sql,
					createParams("txnType", txnType, "caseLoadType", caseLoadType), columnRowMapper);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<String> checkCaselodexist(final String caseloadId) {
		final String sql = getQuery("CHECK_CASELOD_EXIST");
		try {
			return namedParameterJdbcTemplate.queryForList(sql, createParams("caseloadId", caseloadId), String.class);
		} catch (Exception e) {
			logger.error("checkCaselodexist", e);
			return null;
		}

	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @return SystemProfiles
	 * @param SystemProfiles paramBean
	 */
	public SystemProfiles getsysProfiles(final SystemProfiles paramBean) {
		final String sql = getQuery("GET_SYS_PROFILES");
		final RowMapper<SystemProfiles> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, SystemProfiles.class,
				accCodesMapping);
		final SystemProfiles returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				columnRowMapper);
		return returnObj;
	}

	@Override
	public String getagyLocId(final BigDecimal offenderId, final String caseLoadType, final String userId) {
		final String sql = getQuery("GET_AGY_LOC_ID");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("offenderId", offenderId, "caseLoadType", caseLoadType,"userId",userId), String.class);

	}

	@Override
	public InstitutionMiniBalances getInstMiniBalance(final String caseloadId, final String agyLocId,
			final BigDecimal accountCode) {
		final String sql = getQuery("GET_INST_MINI_BALANCE");
		final RowMapper<InstitutionMiniBalances> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				InstitutionMiniBalances.class, accCodesMapping);
		final InstitutionMiniBalances returnObj = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("caseloadId", caseloadId, "agyLocId", agyLocId, "accountCode", accountCode),
				columnRowMapper);
		return returnObj;
	}

	@Override
	public BigDecimal getDrAccountCode(final String caseloadId, final String moduleName, final String txnType) {
		final String sql = getQuery("GET_DR_ACCOUNT_CODE");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("moduleName", moduleName, "txnType", txnType, "caseLoadId", caseloadId), BigDecimal.class);
	}

	@Override
	public OffenderSubAccounts getOffSubAccounts(final String caseloadId, final BigDecimal offenderId,
			final BigDecimal drAccountCode) {
		final String sql = getQuery("GET_OFF_SUB_ACCOUNTS");
		final RowMapper<OffenderSubAccounts> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderSubAccounts.class, accCodesMapping);
		final OffenderSubAccounts returnObj = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("caseloadId", caseloadId, "offenderId", offenderId, "drAccountCode", drAccountCode),
				columnRowMapper);
		return returnObj;
	}

	@Override
	public OffenderSubAccounts getOffSubAccountstwo(final String caseloadId, final BigDecimal offenderId,
			final BigDecimal drAccountCode) {
		final String sql = getQuery("GET_OFF_SUB_ACCOUNTS_TWO");

		final OffenderSubAccounts returnObj = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("caseloadId", caseloadId, "offenderId", offenderId, "drAccountCode", drAccountCode),
				new BeanPropertyRowMapper<OffenderSubAccounts>(OffenderSubAccounts.class));
		return returnObj;
	}

	@Override
	public BigDecimal getIndDays(final String caseloadId, final BigDecimal offenderId, final BigDecimal drAccountCode) {
		final String sql = getQuery("GET_IND_DAYS");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("caseloadId", caseloadId, "offenderId", offenderId, "drAccountCode", drAccountCode),
				BigDecimal.class);
	}

	@Override
	public String lCheckCaseloadCur(final String pCsldId) {
		String retVal = null;
		final String sql = getQuery("CHK_OVERDRAWN_L_CASELOAD_EXISTS");
		try {
			retVal = namedParameterJdbcTemplate.queryForObject(sql, createParams("p_csld_id", pCsldId), String.class);
		} catch (Exception e) {
			logger.error(e);
		}
		return retVal;
	}

	@Override
	public String lAgyLocIdCur(final BigDecimal pOffId, final String lvCsldType, final String userName) {
		String retVal = null;
		final String sql = getQuery("CHK_OVERDRAWN_L_AGY_LOC_ID_CUR");
		try {
			retVal = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("p_offender_id", pOffId, "p_caseload_type", lvCsldType,"userId",userName), String.class);
		} catch (Exception e) {
			logger.error(e);
		}
		return retVal;
	}

	@Override
	public InstitutionMiniBalances lMinBalanceCur(final String pCsldId, final String lAgyLocId,
			final BigDecimal trustacccode) {
		InstitutionMiniBalances retVal = null;
		final String sql = getQuery("CHK_OVERDRAWN_L_MIN_BALANCE_CUR");
		try {
			retVal = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("p_caseload_id", pCsldId, "p_agy_loc_id", lAgyLocId, "p_trust_account_code",
							trustacccode),
					new BeanPropertyRowMapper<InstitutionMiniBalances>(InstitutionMiniBalances.class));
		} catch (Exception e) {
			logger.error(e);
		}
		return retVal;
	}

	@Override
	public SystemProfiles lSystemProfilesCur(final String pProfileType, final String pProfileCode) {
		SystemProfiles retVal = null;
		final String sql = getQuery("CHK_OVERDRAWN_L_SYSTEM_PROFILES_CUR");
		try {
			retVal = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("p_profile_type", pProfileType, "p_profile_code", pProfileCode),
					new BeanPropertyRowMapper<SystemProfiles>(SystemProfiles.class));
		} catch (Exception e) {
			logger.error(e);
		}
		return retVal;
	}

	@Override
	public BigDecimal lIndDays(final String pCsldId, final BigDecimal pOffId, final BigDecimal trustacccode) {
		BigDecimal retVal = null;
		final String sql = getQuery("CHK_OVERDRAWN_L_IND_DAYS");
		try {
			retVal = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("p_csld_id", pCsldId, "p_off_id", pOffId, "trustacccode", trustacccode),
					BigDecimal.class);
		} catch (Exception e) {
			logger.error(e);
		}
		return retVal;
	}

	@Override
	public TransactionTypes transactionTypesC(final String csldType, final String lvSetupCsldType,String txntype) {
		TransactionTypes retVal = null;
		final String sql = getQuery("CHK_OVERDRAWN_TRANSACTION_TYPES_C");
		try {
			retVal = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("lv_setup_csld_type", lvSetupCsldType, "csld_type", csldType,"txntype",txntype),
					new BeanPropertyRowMapper<TransactionTypes>(TransactionTypes.class));
		} catch (Exception e) {
			logger.error(e);
		}
		return retVal;
	}

	@Override
	public String getCrob(final String obligationtype, final String lvSetupCsldType, final String csldType) {
		final String sql = getQuery("CHK_OVERDRAWN_CROB");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("obligationtype", obligationtype,
				"lv_setup_csld_type", lvSetupCsldType, "csld_type", csldType), String.class);
	}

	@Override
	public CaseloadDeductionProfiles indigentflag(final String pCsldId, final String obligationtype) {
		final String sql = getQuery("CHK_OVERDRAWN_INDIGENTFLAG");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("p_csld_id", pCsldId, "obligationtype", obligationtype),
				new BeanPropertyRowMapper<CaseloadDeductionProfiles>(CaseloadDeductionProfiles.class));
	}

	@Override
	public Integer getCount() {
		final String sql = getQuery("CHK_OVERDRAWN_COUNT");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Integer.class);
	}

	@Override
	public List<OffenderSubAccounts> chkIndAcOthersC(final BigDecimal pOffId, final String pCsldId,
			final BigDecimal trstcode) {
		List<OffenderSubAccounts> returnList = null;
		final String sql = getQuery("CCHK_OVERDRAWN_HK_IND_AC_OTHERS_C");
		final RowMapper<OffenderSubAccounts> tempOidcountRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderSubAccounts.class, accCodesMapping);
		try {
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("p_off_id", pOffId, "p_csld_id", pCsldId, "trstcode", trstcode),
					tempOidcountRowMapper);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return returnList;
	}

	@Override
	public OffenderLimits getOffenderLimits(final String caseloadid, final BigDecimal offenderid,
			final String limittype) {
		OffenderLimits retVal = null;
		final String sql = getQuery("CCHK_OVERDRAWN_OFFENDER_LIMITS");
		try {
			retVal = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("caseloadid", caseloadid, "offenderid", offenderid, "limittype", limittype),
					new BeanPropertyRowMapper<OffenderLimits>(OffenderLimits.class));
		} catch (Exception e) {
			logger.error(e);
		}
		return retVal;
	}

	@Override
	public OffenderLimits getOffenderLimitsTwo(final String caseloadid, final String limittype) {
		OffenderLimits retVal = null;
		final String sql = getQuery("CCHK_OVERDRAWN_OFFENDER_LIMITS_TWO");
		try {
			retVal = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("caseloadid", caseloadid, "limittype", limittype),
					new BeanPropertyRowMapper<OffenderLimits>(OffenderLimits.class));
		} catch (Exception e) {
			logger.error(e);
		}
		return retVal;
	}

	@Override
	public Integer getWeekDay() {
		final String sql = getQuery("CHK_OVERDRAWN_WEEKDAY");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Integer.class);
	}

	@Override
	public Date getFromDate(final Integer weekday) {
		final String sql = getQuery("CHK_OVERDRAWN_FROMDATE");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("weekday", weekday), Date.class);
	}

	@Override
	public Date getToDate(final Integer weekday) {
		final String sql = getQuery("CHK_OVERDRAWN_TODATE");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("weekday", weekday), Date.class);
	}

	@Override
	public Date getFromDateTwo() {
		final String sql = getQuery("CHK_OVERDRAWN_FROMDATE_TWO");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Date.class);
	}

	@Override
	public Date getToDateTwo() {
		final String sql = getQuery("CHK_OVERDRAWN_TODATE_TWO");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Date.class);
	}

	@Override
	public BigDecimal getTotalTaken(final BigDecimal pOffenderId, final String dedType, final String caseloadId,
			final Date fromDate, final Date toDate) {
		BigDecimal retVal = null;
		final String sql = getQuery("CHK_OVERDRAWN_GET_TOTAL_TAKEN");
		try {
			retVal = namedParameterJdbcTemplate.queryForObject(sql, createParams("p_offender_id", pOffenderId,
					"ded_type", dedType, "caseloadid", caseloadId, "from_date", fromDate, "to_date", toDate),
					BigDecimal.class);
		} catch (Exception e) {
			logger.error(e + " getTotalTaken No Data found", e);
		}
		return retVal;
	}

	@Override
	public BigDecimal getTotalReversed(final BigDecimal pOffenderId, final String dedType, final String caseloadId,
			final Date fromDate, final Date toDate) {
		BigDecimal retVal = null;
		final String sql = getQuery("CHK_OVERDRAWN_TOTAL_REVERSED");
		try {
			retVal = namedParameterJdbcTemplate.queryForObject(sql, createParams("p_offender_id", pOffenderId,
					"ded_type", dedType, "caseloadid", caseloadId, "from_date", fromDate, "to_date", toDate),
					BigDecimal.class);
		} catch (Exception e) {
			logger.error(e + " getTotalTaken No Data found", e);
		}
		return retVal;
	}

	@Override
	public BigDecimal getAmountWrittenOff(final BigDecimal offenderId, final String limittype, final String caseloadid,
			final Date fromDate, final Date toDate) {
		BigDecimal retVal = null;
		final String sql = getQuery("CHK_OVERDRAWN_AMOUNT_WRITTEN_OFF");
		try {
			retVal = namedParameterJdbcTemplate.queryForObject(sql, createParams("caseloadid", caseloadid, "offenderid",
					offenderId, "fromdate", fromDate, "todate", toDate, "limittype", limittype), BigDecimal.class);
		} catch (Exception e) {
			logger.error(e + " getTotalTaken No Data found", e);
		}
		return retVal;
	}

	@Override
	public AccountCodes getAccountCodes(final String formName, final String obligationtype,
			final String lvSetupcsldType, final String csldType, final String pSetupCaseload, final String caseload) {
		AccountCodes retVal = null;
		final String sql = getQuery("CHK_OVERDRAWN_ACCOUNT_CODES");
		try {
			retVal = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("form_name", formName, "obligationtype", obligationtype, "lv_setupcsld_type",
							lvSetupcsldType, "csld_type", csldType, "p_setup_caseload", pSetupCaseload, "caseload",
							caseload),
					new BeanPropertyRowMapper<AccountCodes>(AccountCodes.class));
		} catch (Exception e) {
			logger.error(e);
		}
		return retVal;
	}

	@Override
	public OffenderDeductions getOffenderDeductions(final String caseload, final BigDecimal offenderid,
			final String obligationtype) {
		OffenderDeductions retVal = null;
		final String sql = getQuery("CHK_OVERDRAWN_OFFENDER_DEDUCTIONS");
		try {
			retVal = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("caseload", caseload, "offenderid", offenderid, "obligationtype", obligationtype),
					new BeanPropertyRowMapper<OffenderDeductions>(OffenderDeductions.class));
		} catch (Exception e) {
			logger.error(e);
		}
		return retVal;
	}

	@Override
	public CaseloadDeductionProfiles getCaseloadDeductionProfiles(final String caseload, final String obligationtype) {
		final String sql = getQuery("CHK_OVERDRAWN_CASELOAD_DEDUCTION_PROFILES");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("caseload", caseload, "obligationtype", obligationtype),
				new BeanPropertyRowMapper<CaseloadDeductionProfiles>(CaseloadDeductionProfiles.class));
	}

	@Override
	public Integer getLlCnt(final BigDecimal offenderid, final String obligationtype, final String infonum) {
		Integer retVal = null;
		final String sql = getQuery("CHK_OVERDRAWN_LL_CNT");
		try {
			retVal = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("offenderid", offenderid, "obligationtype", obligationtype, "infonum", infonum),
					Integer.class);
		} catch (Exception e) {
			logger.error(e);
		}
		return retVal;
	}

	@Override
	public BigDecimal getPrtyNmbr(final BigDecimal offenderid, final String obligationtype) {
		BigDecimal retVal = null;
		final String sql = getQuery("CHK_OVERDRAWN_PRTY_NMBR");
		try {
			retVal = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("offenderid", offenderid, "obligationtype", obligationtype), BigDecimal.class);
		} catch (Exception e) {
			logger.error(e);
		}
		return retVal;
	}

	@Override
	public Long getOffdedid() {
		Long retVal = null;
		final String sql = getQuery("CHK_OVERDRAWN_OFFDEDID");
		try {
			retVal = namedParameterJdbcTemplate.queryForObject(sql, createParams(), Long.class);
		} catch (Exception e) {
			logger.error(e);
		}
		return retVal;
	}

	@Override
	public Integer insertOffenderDeductions(final OffenderDeductions searchBean) {
		Integer retVal = null;
		final String sql = getQuery("CHK_OVERDRAWN_INSERT_OFFENDER_DEDUCTIONS");
		try {
			retVal = namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(searchBean));
		} catch (Exception e) {
			logger.error(e);
		}
		return retVal;
	}

	@Override
	public Integer insertOffenderDeductionReceipts(final String caseload, final BigDecimal offenderid,
			final Long offdedid) {
		Integer retVal = null;
		final String sql = getQuery("CHK_OVERDRAWN_INSERT_OFFENDER_DEDUCTION_RECEIPTS");
		try {
			retVal = namedParameterJdbcTemplate.update(sql,
					createParams("caseload", caseload, "offenderid", offenderid, "offdedid", offdedid));
		} catch (Exception e) {
			logger.error(e);
		}
		return retVal;
	}

	@Override
	public Integer insertOffenderBeneficiaries(final BigDecimal offenderid, final Long offdedid,
			final BigDecimal payeecorp, final String userName) {
		Integer retVal = null;
		final String sql = getQuery("CHK_OVERDRAWN_INSERT_OFFENDER_BENEFICIARIES");
		try {
			retVal = namedParameterJdbcTemplate.update(sql, createParams("offdedid", offdedid, "offenderid", offenderid,
					"payeecorp", payeecorp, "createUserId", userName));
		} catch (Exception e) {
			logger.error(e);
		}
		return retVal;
	}

	@Override
	public BigDecimal getOffBookId(final BigDecimal pOffId) {
		BigDecimal retVal = null;
		final String sql = getQuery("CHK_OVERDRAWN_OFF_BOOK_ID");
		try {
			retVal = namedParameterJdbcTemplate.queryForObject(sql, createParams("p_off_id", pOffId), BigDecimal.class);
		} catch (Exception e) {
			logger.error(e);
		}
		return retVal;
	}

	@Override
	public Integer insertOffenderTransactions(final BigDecimal pTransNumber, final Long pTransSeq, final String pCsldId,
			final BigDecimal pOffId, final BigDecimal offBookId, final String pTransPostType, final String pTransType,
			final String pTransDesc, final Double pTransAmount, final Date pTransDate, final String pSubActType,
			final String pSlipPrintFlag, final Double pPreDedAmount, final String pDeductionFlag,
			final String pPayeeCode, final BigDecimal pPayeeCorpId, final BigDecimal pPayeePersonId,
			final String pPayeeNameText, final String pDeductionType, final String pInfoNumber, final String userName) {
		Integer retVal = null;
		final String sql = getQuery("CHK_OVERDRAWN_OFFENDER_TRANSACTIONS");
		try {
			retVal = namedParameterJdbcTemplate.update(sql,
					createParams("p_trans_number", pTransNumber, "p_trans_seq", pTransSeq, "p_csld_id", pCsldId,
							"p_off_id", pOffId, "off_book_id", offBookId, "p_trans_post_type", pTransPostType,
							"p_trans_type", pTransType, "p_trans_desc", pTransDesc, "p_trans_amount", pTransAmount,
							"p_trans_date", pTransDate, "p_sub_act_type", pSubActType, "p_slip_print_flag",
							pSlipPrintFlag, "p_pre_ded_amount", pPreDedAmount, "p_deduction_flag", pDeductionFlag,
							"p_payee_code", pPayeeCode, "p_payee_corp_id", pPayeeCorpId, "p_payee_person_id",
							pPayeePersonId, "p_payee_name_text", pPayeeNameText, "p_deduction_type", pDeductionType,
							"p_info_number", pInfoNumber, "createUserId", userName));
		} catch (Exception e) {
			logger.error(e);
		}
		return retVal;
	}

	@Override
	public AccountCodes postingC(final String pSubActTypeDr, final String pSubActTypeCr, final String setupCsldType,
			final String pTransType, final String pModuleName, final String pOperationType, final String pSetupCsldId) {
		final String sql = getQuery("CHK_OVERDRAWN_POSTING_C");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("p_sub_act_type_dr", pSubActTypeDr, "setup_csld_type", setupCsldType, "p_sub_act_type_cr",
						pSubActTypeCr, "p_trans_type", pTransType, "p_module_name", pModuleName, "p_operation_type",
						pOperationType, "p_setup_csld_id", pSetupCsldId),
				new BeanPropertyRowMapper<AccountCodes>(AccountCodes.class));
	}

	@Override
	public Integer updateOffenderDeductions(final BigDecimal maxlimit, final Double overdrawn, final Date todayDate,
			final Long offdeductionid, final String userName) {
		Integer retVal = null;
		final String sql = getQuery("CHK_OVERDRAWN_UPDATE_OFFENDER_DEDUCTIONS");
		try {
			retVal = namedParameterJdbcTemplate.update(sql, createParams("overdrawn", overdrawn, "maxlimit", maxlimit,
					"today_date", todayDate, "offdeductionid", offdeductionid, "modifyUserId", userName));
		} catch (Exception e) {
			logger.error(e);
		}
		return retVal;
	}

	@Override
	public Integer updateOffenderBeneficiaries(final Double overdrawn, final Date todayDate, final Long offdeductionid,
			final String userName) {
		Integer retVal = null;
		final String sql = getQuery("CHK_OVERDRAWN_UPDATE_OFFENDER_BENEFICIARIES");
		try {
			retVal = namedParameterJdbcTemplate.update(sql, createParams("overdrawn", overdrawn, "today_date",
					todayDate, "offdeductionid", offdeductionid, "modifyUserId", userName));
		} catch (Exception e) {
			logger.error(e);
		}
		return retVal;
	}

	@Override
	public String trustCommunityCsld(final String caseloadId) {
		final String sql = getQuery("TRUST_COMMUNITY_CSLD");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("P_CSLD_ID", caseloadId), String.class);
	}

	@Override
	public Long getOffenderBookIdCurForInsert(final BigDecimal pOffId, final String pCsldId) {
		final String sql = getQuery("PROCESS_TRANSACTION_GET_OFFENDER_BOOK_ID_CUR");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("P_OFF_ID", pOffId, "P_CSLD_ID", pCsldId),
				Long.class);
	}

	@Override
	public Integer insertOffenderTransactions(final OffenderTransactions offTrans) {
		final String sql = getQuery("PROCESS_TRANSACTION_INSERT_OFFENDER_TRANSACTIONS");
		try {
			return namedParameterJdbcTemplate.update(sql, createParams("p_trans_number", offTrans.getTxnId(),
					"p_trans_seq", offTrans.getTxnEntrySeq(), "p_csld_id", offTrans.getCaseloadId(), "p_off_id",
					offTrans.getOffenderId(), "off_book_id", offTrans.getOffenderBookId(), "p_trans_post_type",
					offTrans.getTxnPostingType(), "p_trans_type", offTrans.getTxnType(), "p_trans_desc",
					offTrans.getTxnEntryDesc(), "p_trans_amount", offTrans.getTxnEntryAmount(), "p_trans_date",
					offTrans.getTxnEntryDate(), "p_sub_act_type", offTrans.getSubAccountType(), "p_slip_print_flag",
					offTrans.getSlipPrintedFlag(), "p_pre_ded_amount", offTrans.getPreWithholdAmount(),
					"p_deduction_flag", offTrans.getDeductionFlag(), "p_payee_corp_id", offTrans.getPayeeCorporateId(),
					"p_payee_person_id", offTrans.getPayeePersonId(), "p_deduction_type", offTrans.getDeductionType(),
					"p_info_number", offTrans.getInfoNumber(), "createUserId", offTrans.getCreateUserId()));
		} catch (Exception e) {
			logger.error("insertOffenderTransactions" + e);
			return 0;
		}
	}

	@Override
	public AccountCodes getDrTxnOpr(String disbuType, String modName, String csldId, String caseloadtype) {
		String sql = getQuery("GET_DR_TXN_OPR");
		return namedParameterJdbcTemplate
				.queryForObject(
						sql, createParams("disbu_type", disbuType, "mod_name", modName, "csld_id", csldId,
								"caseloadtype", caseloadtype),
						new BeanPropertyRowMapper<AccountCodes>(AccountCodes.class));
	}

	@Override
	 public List<OffenderDeductions> getTxnFeeType(Long offId, String csldId, String caseloadtype) {
		String sql = getQuery("GET_TXN_FEE_TYPE_A");
		RowMapper<OffenderDeductions> mapper = Row2BeanRowMapper.makeMapping(sql, OffenderDeductions.class,
				corpBenMapping);
		return namedParameterJdbcTemplate.query(sql,
				createParams("off_id", offId, "csld_id", csldId, "caseloadtype", caseloadtype), mapper);
	}

	@Override
	public BigDecimal gettingLvOffBalance(String caseloadId, String csldType, Long pOffId, Integer accountCode) {
		String sql = getQuery("GETTING_OFFENDER_SUB_BAL");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("p_account_code",accountCode,"p_off_id",
				pOffId,"p_org_csld_id",caseloadId,"p_csld_type",csldType), BigDecimal.class);
	}

	@Override
	public BigDecimal getSubBal(String pCsldId, Long pOffId, String deductionType,String caseloadType) {
		String sql = getQuery("GETTING_SUB_BAL");
		BigDecimal returnVal = null ;
		try {
			returnVal = namedParameterJdbcTemplate.queryForObject(sql, createParams("sub_ac_type",deductionType,"caseload",
					pCsldId,"off_id",pOffId,"csld_type",caseloadType), BigDecimal.class);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return returnVal;
	}

	@Override
	public BigDecimal gettingSubBal(String deductionType, Long pOffId, String pCsldId, String caseloadType) {
		String sql = getQuery("GETTING_SUB_BAL_MORE");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("sub_ac_type",deductionType,"off_id",
				pOffId,"caseload",pCsldId,"csld_type",caseloadType), BigDecimal.class);
	}

	@Override
	public BigDecimal gettingVMinBal(String pCsldId, String deductionType, String pTransType) {
		String sql = getQuery("GETTING_V_MIN_BAL");
		BigDecimal returnVal = null;

		try {
			 returnVal = namedParameterJdbcTemplate.queryForObject(sql, createParams("p_csld_id",pCsldId,"p_ded_type",
					deductionType,"p_txn_type",pTransType), BigDecimal.class);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return returnVal;
	}

}