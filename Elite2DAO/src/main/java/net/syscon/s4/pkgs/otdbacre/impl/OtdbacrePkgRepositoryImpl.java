package net.syscon.s4.pkgs.otdbacre.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.inmate.beans.GlTransactions;
import net.syscon.s4.pkgs.otdbacre.OtdbacrePkgRepository;

@Repository
public class OtdbacrePkgRepositoryImpl extends RepositoryBase implements OtdbacrePkgRepository {

	private final static Logger logger = LogManager.getLogger(OtdbacrePkgRepositoryImpl.class.getName());

	@Override
	public void deleteBankClearReconcilesTmp(final String caseloadId) {
		final String sql = getQuery("DELETE_BANK_CLEAR_RECONCILES_TMP");
		final Map<String, Object> inParamMap = new HashMap<String, Object>();

		inParamMap.put("p_caseload_id", caseloadId);
		try {
			namedParameterJdbcTemplate.update(sql, inParamMap);
		} catch (Exception e) {
			logger.error("deleteBankClearReconcilesTmp", e);
		}
	}

	@Override
	public String getProfileValue(final String profileType, final String profileCode) {
		final String sql = getQuery("GET_PROFILE_VALUE");
		String retVal = null;
		try {
			retVal = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("p_profile_type", profileType, "p_profile_code", profileCode), String.class);
		} catch (Exception e) {
			logger.error("getInterfaceFlag", e);
			retVal = null;
		}
		return retVal;
	}

	@Override
	public void insertBankClearReconcilesTmp(final GlTransactions glTransactions) {
		final String sql = getQuery("INSERT_BANK_CLEAR_RECONCILES_TMP");
		final Map<String, Object> inParamMap = new HashMap<String, Object>();

		inParamMap.put("P_CASELOAD_ID", glTransactions.getCaseloadId());
		inParamMap.put("P_ACCOUNT_CODE", glTransactions.getAccountCode());

		inParamMap.put("L_VOID_FLAG", glTransactions.getVoidFlag());
		inParamMap.put("P_SELECT_MODE", glTransactions.getSelectMode());

		inParamMap.put("P_LAST_RECON_DATE", glTransactions.getLastReconDate());
		inParamMap.put("P_BANK_STATEMENT_DATE", glTransactions.getBankStatementDate());

		inParamMap.put("L_INTERFACE_FLAG", glTransactions.getInterfaceFlag());
		inParamMap.put("L_FUTURE_FLAG", glTransactions.getFutureFlag());
		inParamMap.put("createUserId", glTransactions.getCreateUserId());

		try {
			namedParameterJdbcTemplate.update(sql, inParamMap);
		} catch (Exception e) {
			logger.error("insertBankClearReconcilesTmp", e);
		}

	}

	@Override
	public void updateBankClearReconcilesTmp(final String caseloadId, final BigDecimal accountCode,
			final String userName) {
		final String sql = getQuery("UPDATE_BANK_CLEAR_RECONCILES_TMP");
		Map<String, Object> inParamMap = new HashMap<String, Object>();

		inParamMap.put("P_CASELOAD_ID", caseloadId);
		inParamMap.put("P_ACCOUNT_CODE", accountCode);
		inParamMap.put("modifyUserId", userName);

		try {
			namedParameterJdbcTemplate.update(sql, inParamMap);
		} catch (Exception e) {
			logger.error("updateBankClearReconcilesTmp", e);
		}

	}

	@Override
	public void updateBankClearReconcilesTmpOne(final String userName) {
		final String sql = getQuery("UPDATE_BANK_CLEAR_RECONCILES_TMP_ONE");
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("modifyUserId", userName);

		try {
			namedParameterJdbcTemplate.update(sql, inParamMap);
		} catch (Exception e) {
			logger.error("updateBankClearReconcilesTmpOne", e);
		}
	}

}