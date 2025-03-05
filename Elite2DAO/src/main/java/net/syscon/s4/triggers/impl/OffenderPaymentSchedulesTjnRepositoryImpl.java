package net.syscon.s4.triggers.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.beans.OffenderPaymentSchedules;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.triggers.OffenderPaymentSchedulesTjnRepository;

@Repository
public class OffenderPaymentSchedulesTjnRepositoryImpl extends RepositoryBase implements OffenderPaymentSchedulesTjnRepository {
	
	private static final Logger logger = LogManager.getLogger(OffenderBeneficiariesTjnRepositoryImpl.class);

	@Override
	public Integer insertOffenderPaymentSchedulesTjn(OffenderPaymentSchedules obj) {
		Integer retVal = null;
		final String sql = getQuery("OFFENDER_PAYMENT_SCHEDULES_TJN_INSERT");
		try {
			retVal = namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(obj));
		} catch (Exception e) {
			logger.error("Error in Class" + this.getClass().getName() + "insertOffenderPaymentSchedulesTjn error ::" , e);
		}
		return retVal;
	}

	@Override
	public Integer updateOffenderPaymentSchedulesTjn(OffenderPaymentSchedules obj) {
		Integer retVal = null;
		final String sql = getQuery("OFFENDER_PAYMENT_SCHEDULES_TJN_UPDATE");
		try {
			retVal =  namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(obj));
		} catch (Exception e) {
			logger.error("Error in Class" + this.getClass().getName() + "updateOffenderPaymentSchedulesTjn error ::" , e);
		}
		return retVal;
	}

	@Override
	public Integer deleteOffenderPaymentSchedulesTjn(OffenderPaymentSchedules obj) {
		Integer retVal = null;
		final String sql = getQuery("OFFENDER_PAYMENT_SCHEDULES_TJN_DELETE");
		try {
			retVal =  namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(obj));
		} catch (Exception e) {
			logger.error("Error in Class" + this.getClass().getName() + "deleteOffenderPaymentSchedulesTjn error ::" , e);
		}
		return retVal;
	}

}
