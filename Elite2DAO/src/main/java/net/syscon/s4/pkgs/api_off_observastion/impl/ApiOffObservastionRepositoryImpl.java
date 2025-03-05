package net.syscon.s4.pkgs.api_off_observastion.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.pkgs.ApiOffObsStaging;
import net.syscon.s4.pkgs.api_off_observastion.ApiOffObservastionRepository;

@Repository
public class ApiOffObservastionRepositoryImpl extends RepositoryBase implements ApiOffObservastionRepository {
	private final Logger logger = LogManager.getLogger(ApiOffObservastionRepositoryImpl.class);

	@Override
	public Long lvSeq() {
		final String sql = getQuery("API_OFF_OBSERVASTION_LV_SEQ");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Long.class);
		} catch (final Exception e) {
			logger.error("lvSeq", e);
			return null;
		}
	}

	@Override
	public Integer logging(final ApiOffObsStaging apiOffObsStaging) {
		Integer returnValue = 0;
		final String sql = getQuery("API_OFF_OBSERVASTION_LOGGING");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		parameters.add(new BeanPropertySqlParameterSource(apiOffObsStaging));
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
			if (1 == returnArray.length) {
				returnValue = 1;
			}
		} catch (final Exception e) {
			returnValue = 0;
			logger.error("logging ", e);
		}
		return returnValue;

	}

}
