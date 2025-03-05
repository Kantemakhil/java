package net.syscon.s4.triggers.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.inmate.beans.OffenderDeductions;
import net.syscon.s4.triggers.OffenderBeneficiariesT2Repository;
@Repository
public class OffenderBeneficiariesT2RepositoryImpl extends RepositoryBase implements OffenderBeneficiariesT2Repository{
	
	private static Logger logger = LogManager.getLogger(OffenderBeneficiariesT2RepositoryImpl.class.getName());
	
	@Override
	public OffenderDeductions dedAmountType(Long offenderDeductionId) {
		OffenderDeductions returnObj=null;
		final String sql = getQuery("OFFENDER_BENEFICIARIES_T2_DED_AMOUNT_TYPE");
		try {
		returnObj=  namedParameterJdbcTemplate
				.queryForObject(sql, createParams("offender_deduction_id",offenderDeductionId),new BeanPropertyRowMapper<OffenderDeductions>(OffenderDeductions.class));
		}catch (Exception e) {
			logger.error(e);
		}
		return returnObj;
	}
}
