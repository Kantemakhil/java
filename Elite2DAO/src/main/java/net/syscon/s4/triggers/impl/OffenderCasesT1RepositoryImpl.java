package net.syscon.s4.triggers.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.im.beans.OffenderCases;
import net.syscon.s4.triggers.OffenderCasesT1Repository;
@Repository
public class OffenderCasesT1RepositoryImpl extends RepositoryBase implements OffenderCasesT1Repository{

	private static Logger logger = LogManager.getLogger(OffenderCasesT1RepositoryImpl.class.getName());
	
	@Override
	public OffenderCases getOffenderCases(final Long caseId) {
		final String sql = getQuery("GET_OFFENDER_CASES_CASE_ID");
		return  namedParameterJdbcTemplate
				.queryForObject(sql, createParams("CASE_ID",caseId),new BeanPropertyRowMapper<OffenderCases>(OffenderCases.class));
		
	}
	
	@Override
	public String getProfileValue() {
		final String sql = getQuery("GET_PROFILE_VALUE_CASE_OBLIGN");
		String description = null;
		try {
			description = namedParameterJdbcTemplate.queryForObject(sql, createParams(),String.class);
		} catch (Exception e) {
			logger.error("getProfileValue :" + e);
		}
		return description;
	}
	
	@Override
	public Integer updateOffenderDeductions(final String caseInfoNumber, final Long caseId) {
		final String sql = getQuery("UPDATE_OFFENDER_DEDUCTIONS");
		try {
			return namedParameterJdbcTemplate.update(sql, createParams("CASE_INFO_NUMBER", caseInfoNumber,
					"CASE_ID", caseId));
		} catch (DataAccessException e) {
			logger.error("updateOffenderDeductions :" + e);
		}
		return 0;
	}
	
	@Override
	public Integer updateOffenderPaymentPlans(final String caseInfoNumber, final Long caseId) {
		final String sql = getQuery("UPDATE_OFFENDER_PAYMENT_PLANS");
		try {
			 return namedParameterJdbcTemplate.update(sql, createParams("CASE_INFO_NUMBER", caseInfoNumber,
					"CASE_ID", caseId));
		} catch (DataAccessException e) {
			logger.error("updateOffenderPaymentPlans :" + e);
		}
		return 0;
	}
}

