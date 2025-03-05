package net.syscon.s4.triggers.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.stereotype.Repository;

import net.syscon.s4.cf.deductions.beans.OffenderMonDeductions;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.triggers.OffenderMonBeneficiariesTjnRepository;

@Repository
public class OffenderMonBeneficiariesTjnRepositoryImpl extends RepositoryBase implements OffenderMonBeneficiariesTjnRepository {

	private static final Logger logger = LogManager.getLogger(OffenderMonBeneficiariesTjnRepositoryImpl.class);

	@Override
	public Integer insertOffenderMonBeneficiariesTjn(OffenderMonDeductions obj) {
		Integer retVal = null;
		final String sql = getQuery("OFFENDER_MON_BENEFICIARIES_TJN_INSERT");
		try {
			retVal = namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(obj));
		} catch (Exception e) {
			logger.error("Error in Class " + this.getClass().getName() + " insertOffenderMonBeneficiariesTjn error :: " , e);
		}
		return retVal;
	}

	@Override
	public Integer updateOffenderMonBeneficiariesTjn(OffenderMonDeductions obj) {
		Integer retVal = null;
		final String sql = getQuery("OFFENDER_MON_BENEFICIARIES_TJN_UPDATE");
		try {
			retVal = namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(obj));
		} catch (Exception e) {
			logger.error("Error in Class " + this.getClass().getName() + " updateOffenderMonBeneficiariesTjn error :: " , e);
		}
		return retVal;
	}

	@Override
	public Integer deleteOffenderMonBeneficiariesTjn(OffenderMonDeductions obj) {
		Integer retVal = null;
		final String sql = getQuery("OFFENDER_MON_BENEFICIARIES_TJN_DELETE");
		try {
			retVal = namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(obj));
		} catch (Exception e) {
			logger.error("Error in Class " + this.getClass().getName() + " deleteOffenderMonBeneficiariesTjn error :: " , e);
		}
		return retVal;
	}

}
