package net.syscon.s4.triggers.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.inst.legals.au.OffenderRequests;
import net.syscon.s4.triggers.OmtoreqdRepository;

@Repository
public class OmtoreqdRepositoryImpl extends RepositoryBase implements OmtoreqdRepository {
	private static final Logger logger = LogManager.getLogger(OmtoffsrcRepositoryImpl.class.getName());

	@Override
	public OffenderRequests getOffenderRequests(final OffenderRequests offenderRequests) {
		final String sql = getQuery("OMTOREQD_GET");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql,
					createParams(":offenderBookId", offenderRequests.getOffenderBookId(), "chargeSeq",
							offenderRequests.getChargeSeq(), "requestSeq", offenderRequests.getRequestSeq()),
					new BeanPropertyRowMapper<OffenderRequests>(OffenderRequests.class));
		} catch (final Exception e) {
			logger.error("getOffenderRequests", e);
			return null;
		}
	}

	@Override
	public Integer save(final OffenderRequests offenderRequests) {
		final String sql = getQuery("OMTOREQD_INSERT");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		parameters.add(new BeanPropertySqlParameterSource(offenderRequests));
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (final Exception e) {
			logger.error("insert", e);
		}
		if (returnArray.length == 1) {
			return 1;
		} else {
			return 0;
		}
	}
}
