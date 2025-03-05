package net.syscon.s4.triggers.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.stereotype.Repository;

import net.syscon.s4.cm.casemanagement.maintenance.beans.EventMeasureOutcomes;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.triggers.EventMeasureOutcomesTjnRepository;

@Repository
public class EventMeasureOutcomesTjnRepositoryImpl extends RepositoryBase implements EventMeasureOutcomesTjnRepository {

	private static Logger logger = LogManager.getLogger(EventMeasuresTjnRepositoryImpl.class.getName());

	@Override
	public Integer eventMeasuresOutcomesTjnInsert(final EventMeasureOutcomes newObj) {
		String sql = getQuery("EVENT_MEASURE_OUTCOMES_TJN_INSERT");
		Integer retVal = 0;
		try {
			retVal = namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(newObj));
			logger.info(this.getClass() + " eventMeasuresOutcomesTjnInsert method executed");
		} catch (Exception e) {
			logger.error("eventMeasuresOutcomesTjnInsert : ", e);
		}
		return retVal;
	}

	@Override
	public Integer eventMeasuresOutcomesTjnUpdate(final EventMeasureOutcomes oldObj) {
		String sql = getQuery("EVENT_MEASURE_OUTCOMES_TJN_UPDATE");
		Integer retVal = 0;
		try {
			retVal = namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(oldObj));
			logger.info(this.getClass() + " eventMeasuresOutcomesTjnUpdate method executed");
		} catch (Exception e) {
			logger.error("eventMeasuresOutcomesTjnUpdate : ", e);
		}
		return retVal;
	}

	@Override
	public Integer eventMeasuresOutcomesTjnDelete(final EventMeasureOutcomes oldObj) {
		String sql = getQuery("EVENT_MEASURE_OUTCOMES_TJN_DELETE");
		Integer retVal = 0;
		try {
			retVal = namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(oldObj));
			logger.info(this.getClass() + " eventMeasuresOutcomesTjnDelete method executed");
		} catch (Exception e) {
			logger.error("eventMeasuresOutcomesTjnDelete : ", e);
		}
		return retVal;
	}
}
