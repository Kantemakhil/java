package net.syscon.s4.triggers.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import net.syscon.s4.cf.deductions.maintenance.beans.FeeOverrideDetails;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.triggers.OffFeeAccountOverridesThtyRepository;
@Repository
public class OffFeeAccountOverridesThtyRepositoryImpl extends RepositoryBase implements OffFeeAccountOverridesThtyRepository {
	private final Logger logger = LogManager.getLogger(OffFeeAccountOverridesThtyRepositoryImpl.class);

	
	@Override
	public Integer OffFeeAccountOvverRideHistory(FeeOverrideDetails feeOverrideDetails) {
		final String sql = getQuery("OFF_FEE_ACCOUNT_OVERRIDE_THTY_INSERT_DATA");
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		Integer retVal = 0;
		inParamMap.put("operation", feeOverrideDetails.getOperation());
		inParamMap.put("OFF_FEE_OVERRIDE_ID", feeOverrideDetails.getOffFeeOverrideId());
		inParamMap.put("OFFENDER_FEE_ID", feeOverrideDetails.getOffenderFeeId());
		inParamMap.put("OVERRIDE_TYPE", feeOverrideDetails.getOverrideType());
		inParamMap.put("OVERRIDE_START_DATE", feeOverrideDetails.getOverrideStartDate());
		inParamMap.put("OVERRIDE_END_DATE", feeOverrideDetails.getOverrideEndDate());
		inParamMap.put("OVERRIDE_AMOUNT", feeOverrideDetails.getOverrideAmount());
		inParamMap.put("COMMENT_TEXT", feeOverrideDetails.getCommentText());
		inParamMap.put("PRIORITY_INDICATOR", feeOverrideDetails.getPriorityIndicator());
		inParamMap.put("ADDED_BY_STAFF_ID", feeOverrideDetails.getAddedByStaffId());
		inParamMap.put("BILL_GENERATED_FLAG", feeOverrideDetails.getBillGeneratedFlag());
		inParamMap.put("CREATE_USER_ID", feeOverrideDetails.getCreateUserId());
		try {
			retVal = namedParameterJdbcTemplate.update(sql, inParamMap);
		} catch (Exception e) {
			retVal = 0;
			logger.error("Error in Class " + this.getClass().getName() + " OffFeeAccProfileT2InsertA error :: " + e);
		}
		return retVal;
	}
	
}
