package net.syscon.s4.triggers.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.print.DocFlavor.INPUT_STREAM;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import net.syscon.s4.cf.deductions.beans.FeeAccountProfiles;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.triggers.OffFeeAccountProfileT2Repository;

@Repository
public class OffFeeAccountProfileT2RepositoryImpl extends RepositoryBase implements OffFeeAccountProfileT2Repository {
	
	private final Logger logger = LogManager.getLogger(this.getClass());

	@Override
	public Integer OffFeeAccProfileT2InsertA(FeeAccountProfiles feeAccountProfiles) {
		final String sql = getQuery("OFF_FEE_ACCOUNT_PROFILE_T2_INSERT_A");
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		Integer retVal = 0;
		inParamMap.put("OFFENDER_FEE_ID", feeAccountProfiles.getOffenderFeeId());
		inParamMap.put("AMOUNT", feeAccountProfiles.getAmount());
		inParamMap.put("DAY_OF_MONTH", feeAccountProfiles.getDayOfMonth());
		inParamMap.put("START_DATE", feeAccountProfiles.getStartDate());
		inParamMap.put("EFFECTIVE_DATE", feeAccountProfiles.getEffectiveDate());
		inParamMap.put("EXPIRY_DATE", feeAccountProfiles.getExpiryDate());
		inParamMap.put("FEE_ACT_STATUS", feeAccountProfiles.getFeeActStatus());
		inParamMap.put("STATUS_EFFECTIVE_DATE", feeAccountProfiles.getStatusEffectiveDate());
		inParamMap.put("COMMENT_TEXT", feeAccountProfiles.getCommentText());
		inParamMap.put("createUserId", feeAccountProfiles.getCreateUserId());
		try {
			retVal = namedParameterJdbcTemplate.update(sql, inParamMap);
		} catch (Exception e) {
			retVal = 0;
			logger.error("Error in Class " + this.getClass().getName() + " OffFeeAccProfileT2InsertA error :: " + e);
		}
		return retVal;
	}
	
	@Override
	public Integer OffFeeAccProfileT2InsertP(FeeAccountProfiles feeAccountProfiles) {
		final String sql = getQuery("OFF_FEE_ACCOUNT_PROFILE_T2_INSERT_P");
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		Integer retVal = 0;
		inParamMap.put("OFFENDER_FEE_ID", feeAccountProfiles.getOffenderFeeId());
		inParamMap.put("AMOUNT", feeAccountProfiles.getAmount());
		inParamMap.put("DAY_OF_MONTH", feeAccountProfiles.getDayOfMonth());
		inParamMap.put("START_DATE", feeAccountProfiles.getStartDate());
		inParamMap.put("EFFECTIVE_DATE", feeAccountProfiles.getEffectiveDate());
		inParamMap.put("EXPIRY_DATE", feeAccountProfiles.getExpiryDate());
		inParamMap.put("FEE_ACT_STATUS", feeAccountProfiles.getFeeActStatus());
		inParamMap.put("STATUS_EFFECTIVE_DATE", feeAccountProfiles.getStatusEffectiveDate());
		inParamMap.put("createUserId", feeAccountProfiles.getCreateUserId());
		try {
			retVal = namedParameterJdbcTemplate.update(sql, inParamMap);
		} catch (Exception e) {
			retVal = 0;
			logger.error("Error in Class " + this.getClass().getName() + " OffFeeAccProfileT2InsertP error :: " + e);
		}
		return retVal;
	}

	@Override
	public String OldOffFeeActStatus(BigDecimal offenderFeeId) {
		final String sql = getQuery("OFF_FEE_ACCOUNT_PROFILE_T2_OLD_FEE_ACT_STATUS");
		String OldFeeActStatus = null ;
		try {
			OldFeeActStatus = namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderFeeId",offenderFeeId), String.class);
		} catch (Exception e) {
			logger.error("Error in Class " + this.getClass().getName() + " OldOffFeeActStatus error :: " + e);
		}
		return OldFeeActStatus;
	}
}
