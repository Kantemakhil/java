package net.syscon.s4.triggers.impl;

import java.math.BigDecimal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.inmate.beans.OffenderDeductions;
import net.syscon.s4.triggers.OffenderDeductionsT1Repository;
@Repository
public class OffenderDeductionsT1RepositoryImpl extends RepositoryBase implements OffenderDeductionsT1Repository{
	private static Logger logger = LogManager.getLogger(OffenderDeductionsT1RepositoryImpl.class);
	@Override
	public BigDecimal getNextSeq(Long offenderDeductionId) {
		final String sqlname = getQuery("OFFENDER_CHARGES_VINE_INTF_TRG_CUR_OFF");
		return (BigDecimal) namedParameterJdbcTemplate.queryForObject(sqlname, createParams("offender_deduction_id", offenderDeductionId), BigDecimal.class);
	}
	
	@Override
	public Integer insOffOblHty(OffenderDeductions bean) {
		Integer returnData = 0;
		final String sql = getQuery("OFFENDER_DEDUCTIONS_T1_INSERT");
		try {
			returnData = namedParameterJdbcTemplate.update(sql,
					createParams("offenderDeductionId", bean.getOffenderDeductionId(), "deductionSeq", bean.getDeductionSeq(),"informationNumber", bean.getInformationNumber(),
							"deductionType",bean.getDeductionType(),"modifiedUserId",bean.getModifyUserId(),
							"maxTotalAmount", bean.getMaxTotalAmount()));
		} catch (Exception e) {
			logger.error("Exception in insOffOblHty:", e);
		}
		return returnData;
	}
	@Override
	public Integer updOffOblHty(OffenderDeductions bean) {
		Integer returnData = 0;
		final String sql = getQuery("OFFENDER_DEDUCTIONS_T1_UPDATE");
		try {
			returnData = namedParameterJdbcTemplate.update(sql,
					createParams("offenderDeductionId", bean.getOffenderDeductionId(), "deductionSeq", bean.getDeductionSeq(),"informationNumber", bean.getInformationNumber(),
							"deductionType",bean.getDeductionType(),"modifiedUserId",bean.getModifyUserId(),
							"maxTotalAmount", bean.getMaxTotalAmount(),"oldMaxTotalAmount", bean.getOldMaxTotalAmount()));
		} catch (Exception e) {
			logger.error("Exception in updOffOblHty :", e);
		}
		return returnData;
	}
	@Override
	public OffenderDeductions getMaxTotalAmt(OffenderDeductions bean) {
		final String sqlname = getQuery("OFFENDER_DEDUCTIONS_T1_GETMAX_TOTAL_AMT");
		return namedParameterJdbcTemplate.queryForObject(sqlname, createParams("offender_deduction_id", bean.getOffenderDeductionId()), OffenderDeductions.class);
	}
	
}
