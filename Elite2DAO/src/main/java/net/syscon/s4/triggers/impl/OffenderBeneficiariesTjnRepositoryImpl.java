package net.syscon.s4.triggers.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.inmate.beans.OffenderBeneficiaries;
import net.syscon.s4.triggers.OffenderBeneficiariesTjnRepository;

@Repository
public class OffenderBeneficiariesTjnRepositoryImpl extends RepositoryBase implements OffenderBeneficiariesTjnRepository {

	private static final Logger logger = LogManager.getLogger(OffenderBeneficiariesTjnRepositoryImpl.class);

	@Override
	public Integer insertOffenderBeneficiariesTjn(OffenderBeneficiaries obj) {
			Integer retVal = null;
		final String sql = getQuery("OFFENDER_BENEFICIARIES_TJN_INSERT");
		try {
			retVal = namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(obj));
		} catch (Exception e) {
			logger.error("Error in Class " + this.getClass().getName() + " insertOffenderBeneficiariesTjn error :: " , e);
		}
		return retVal;
	}

	@Override
	public Integer updateOffenderBeneficiariesTjn(OffenderBeneficiaries obj) {
		Integer retVal = null;
		final String sql = getQuery("OFFENDER_BENEFICIARIES_TJN_UPDATE");
		try {
			retVal = namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(obj));
		} catch (Exception e) {
			logger.error("Error in Class " + this.getClass().getName() + " updateOffenderBeneficiariesTjn error :: " , e);
		}
		return retVal;
	}

	@Override
	public Integer deleteOffenderBeneficiariesTjn(OffenderBeneficiaries obj) {
		Integer retVal = null;
		final String sql = getQuery("OFFENDER_BENEFICIARIES_TJN_DELETE");
		try {
			retVal = namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(obj));
		} catch (Exception e) {
			logger.error("Error in Class " + this.getClass().getName() + " deleteOffenderBeneficiariesTjn error :: " , e);
		}
		return retVal;
	}

}
