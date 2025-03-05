package net.syscon.s4.triggers.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.inst.automatedcounts.beans.TempOidcount;
import net.syscon.s4.triggers.AgencyLocationCountsT1Repository;

@Repository
public class AgencyLocationCountsT1RepositoryImpl extends RepositoryBase implements AgencyLocationCountsT1Repository {
	private static Logger logger = LogManager.getLogger(AgencyLocationCountsT1RepositoryImpl.class);

	@Override
	public Integer update(final TempOidcount tempOidcoun) {
		final String sql = getQuery("AGENCY_LOCATION_COUNTS_T1_UPDATE");
		Integer returnVal = null;
		try {
			returnVal = namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(tempOidcoun));
		} catch (final Exception e) {
			logger.error("update", e);
		}
		return returnVal;
	}
	
	@Override
	public Integer getRecountTotal(final TempOidcount tempOidcoun) {
		final String sql = getQuery("GET_REPORTING_LOCATION_RECOUNT_TOTAL");
		Integer returnVal = null;
		try {
			returnVal = namedParameterJdbcTemplate.queryForObject(sql, new BeanPropertySqlParameterSource(tempOidcoun), Integer.class);
		} catch (final Exception e) {
			logger.error("update", e);
		}
		return returnVal;
	}
}
