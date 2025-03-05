package net.syscon.s4.triggers.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.triggers.AgencyInternalLocationsT1Repository;
import net.syscon.s4.triggers.AgyIntLocAmendments;

@Repository
public class AgencyInternalLocationsT1RepositoryImpl extends RepositoryBase
		implements AgencyInternalLocationsT1Repository {
	private static Logger logger = LogManager.getLogger(AgencyInternalLocationsT1RepositoryImpl.class);

	@Override
	public Integer insertAgyIntLocAmendments(final AgyIntLocAmendments agyIntLocAmendments) {
		final String sql = getQuery("INSERT_AGY_INT_LOC_AMENDMENTS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		parameters.add(new BeanPropertySqlParameterSource(agyIntLocAmendments));
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (final Exception e) {
			logger.error("save", e);
		}
		if (returnArray.length == 1) {
			return 1;
		} else {
			return 0;
		}
	}

}
