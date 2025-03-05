package net.syscon.s4.triggers.impl;

import java.math.BigDecimal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.stereotype.Repository;

import net.syscon.s4.cf.deductions.beans.OffenderMonDeductions;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.inmate.beans.OffenderBeneficiaries;
import net.syscon.s4.inmate.beans.OffenderDeductions;
import net.syscon.s4.triggers.OffenderBeneficiariesT1Repository;

@Repository
public class OffenderBeneficiariesT1RepositoryImpl extends RepositoryBase implements OffenderBeneficiariesT1Repository{
	
	private Logger logger = LogManager.getLogger(OffenderBeneficiariesT1RepositoryImpl.class);
	
	@Override
	public OffenderDeductions dedAmountType(Long offenderDeductionId) {
		final String sql = getQuery("OFFENDER_BENEFICIARIES_T1_DED_AMOUNT_TYPE");
		OffenderDeductions obj = new OffenderDeductions();
		try {
			obj = namedParameterJdbcTemplate.queryForObject(sql, createParams("offender_deduction_id", offenderDeductionId),
					new BeanPropertyRowMapper<OffenderDeductions>(OffenderDeductions.class));
		} catch (Exception e) {
			logger.error("Error in Class " + this.getClass().getName() + " dedAmountType error :: " , e);
		}
		return obj;
	}

	@Override
	public String thisMonthBeneficiary(Long beneficiaryId) {
		final String sql = getQuery("OFFENDER_BENEFICIARIES_T1_THIS_MONTH_BENEFICIARY");
		String obj=null;
		try {
			obj = namedParameterJdbcTemplate.queryForObject(sql, createParams("beneficiary_id", beneficiaryId),
					String.class);
		} catch (Exception e) {
			logger.error("Error in Class " + this.getClass().getName() + " thisMonthBeneficiary error :: " , e);
		}
		return obj;
	}

	@Override
	public Integer updateOffenderMonBeneficiaries(Long beneficiaryId, BigDecimal receivedAmount) {
		Integer retVal=null;
		final String sql = getQuery("OFFENDER_BENEFICIARIES_T1_UPDATE_OFFENDER_MON_BENEFICIARIES");
		try {
			retVal=namedParameterJdbcTemplate.update(sql,createParams("received_amount",receivedAmount,"beneficiary_id",beneficiaryId));	
		} catch(Exception e) {
			logger.error("Error in Class " + this.getClass().getName() + " updateOffenderMonBeneficiaries error :: " , e);
		}
		return retVal;
	}

	

	@Override
	public Integer insertOffenderMonBeneficiaries(OffenderBeneficiaries obj) {
		Integer retVal=null;
		final String sql = getQuery("OFFENDER_BENEFICIARIES_T1_INSERT_OFFENDER_MON_BENEFICIARIES");
		try {
			retVal=namedParameterJdbcTemplate.update(sql,new BeanPropertySqlParameterSource(obj));	
		} catch(Exception e) {
			logger.error("Error in Class " + this.getClass().getName() + " insertOffenderMonBeneficiaries error :: " , e);
		}
		return retVal;
	}
	
	@Override
	public OffenderMonDeductions getOffenderMonBeneficiaries(Long beneficiaryId) {
		String sql = getQuery("OFFENDER_BENEFICIARIES_T1_GET_OFFENDER_MON_BENEFICIARIES");
		OffenderMonDeductions obj = new OffenderMonDeductions();
		try {
			obj = namedParameterJdbcTemplate.queryForObject(sql, createParams("beneficiaryId", beneficiaryId),
					new BeanPropertyRowMapper<OffenderMonDeductions>(OffenderMonDeductions.class));
		} catch (Exception e) {
			logger.error("Error in Class " + this.getClass().getName() + " getOffenderMonBeneficiaries error :: " , e);
		}
		return obj;
		}
}
