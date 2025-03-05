package net.syscon.s4.triggers.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.stereotype.Repository;

import net.syscon.s4.cm.casemanagement.maintenance.beans.EventMeasures;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.triggers.EventMeasuresTjnRepository;
@Repository
public class EventMeasuresTjnRepositoryImpl extends RepositoryBase implements EventMeasuresTjnRepository {

	private static Logger logger = LogManager.getLogger(EventMeasuresTjnRepositoryImpl.class.getName());

	@Override
	public Integer eventMeasuresTjnInsert(final EventMeasures newObj) {
		String sql = getQuery("EVENT_MEASURES_TJN_INSERT");
		Integer retVal = 0;
		try {
			retVal = namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(newObj));
			logger.info(this.getClass()+" eventMeasuresTjnInsert method executed" );
		} catch (Exception e) {
			logger.error("eventMeasuresTjnInsert : ", e);
		}
		return retVal;
	}

	@Override
	public Integer eventMeasuresTjnUpdate(final EventMeasures oldObj) {
		String sql = getQuery("EVENT_MEASURES_TJN_UPDATE");
		Integer retVal = 0;
		try {
			retVal = namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(oldObj));
			logger.info(this.getClass()+" eventMeasuresTjnUpdate method executed" );
		} catch (Exception e) {
			logger.error("eventMeasuresTjnUpdate : ", e);
		}
		return retVal;
	}

	@Override
	public Integer eventMeasuresTjnDelete(final EventMeasures oldObj) {
		String sql = getQuery("EVENT_MEASURES_TJN_DELETE");
		Integer retVal = 0;
		try {
			retVal = namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(oldObj));
			logger.info(this.getClass()+" eventMeasuresTjnDelete method executed" );
		} catch (Exception e) {
			logger.error("eventMeasuresTjnDelete : ", e);
		}
		return retVal;
	}

}
