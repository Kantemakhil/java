package net.syscon.s4.triggers.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.triggers.OffenderDeductionsHty;
import net.syscon.s4.triggers.OffenderDeductionsThtyRepository;

@Repository
public class OffenderDeductionsThtyRepositoryImpl extends RepositoryBase implements OffenderDeductionsThtyRepository {
	private static Logger logger = LogManager.getLogger(OffenderDeductionsThtyRepositoryImpl.class);
	private final Map<String, FieldMapper> mmMapping = new ImmutableMap.Builder<String, FieldMapper>().build();

	@Override
	public Integer insertingUpdateDelete(final List<OffenderDeductionsHty> offenderDeducHty) {
		final String sql = getQuery("OFFENDER_DEDUCTIONS_THTY_INSERT");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderDeductionsHty obj : offenderDeducHty) {
			parameters.add(new BeanPropertySqlParameterSource(obj));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (final Exception e) {
			logger.error("inserting", e);
		}
		if (offenderDeducHty.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public OffenderDeductionsHty getOffenderDeductionsHty(final OffenderDeductionsHty offenderDeducThty) {
		final String sql = getQuery("OFFENDER_DEDUCTIONS_HTY_OLD_RECORD");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams(), new BeanPropertyRowMapper<OffenderDeductionsHty>(OffenderDeductionsHty.class));
//			return namedParameterJdbcTemplate.queryForObject(sql, createParams(), OffenderDeductionsHty.class);
		} catch (final Exception e) {
			logger.error("getOffenderDeductionsHty  ", e);
			return new OffenderDeductionsHty();
		}
	}

}
