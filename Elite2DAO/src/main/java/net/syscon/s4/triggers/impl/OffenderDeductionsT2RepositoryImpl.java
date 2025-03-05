package net.syscon.s4.triggers.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.inmate.beans.OffenderObligationHty;
import net.syscon.s4.inmate.trust.financialsmaintenance.impl.OtdcntacRepositoryImpl;
import net.syscon.s4.triggers.OffenderDeductionsT2Repository;

@Repository
public class OffenderDeductionsT2RepositoryImpl extends RepositoryBase implements OffenderDeductionsT2Repository{
	
	private static Logger logger = LogManager.getLogger(OtdcntacRepositoryImpl.class.getName());
	@Override
	public Integer insertOffenderObligationHty(OffenderObligationHty obj) {
		final String sql = getQuery("OFFENDER_DEDUCTIONS_T2_INSERT_OFFENDER_OBLIGATION_HTY");
		namedParameterJdbcTemplate.update(sql,
				new BeanPropertySqlParameterSource(obj));
		
		return null;
	}

	@Override
	public Long nextDedSeqC(Long offenderDeductionId) {
		Long retVal=null;
		final String sql = getQuery("OFFENDER_DEDUCTIONS_T2_NEXT_DED_SEQ_C");
		try{
			retVal=namedParameterJdbcTemplate.queryForObject(sql,createParams("offender_deduction_id",offenderDeductionId),Long.class);
		
		}catch (Exception e) {
			logger.error(e);
		}
		return retVal;
	}

}
