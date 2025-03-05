package net.syscon.s4.triggers.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.pkgs.OffenderAssociatedParties;
import net.syscon.s4.triggers.OffenderAssocPEventNotifs;
import net.syscon.s4.triggers.OffenderAssociatedPartiesT1Repository;

@Repository
public class OffenderAssociatedPartiesT1RepositoryImpl extends RepositoryBase
		implements OffenderAssociatedPartiesT1Repository {
	private final Logger logger = LogManager.getLogger(OffenderAssociatedPartiesT1RepositoryImpl.class);
	private final Map<String, FieldMapper> mapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("Y", new FieldMapper("lCheckExistFlag")).build();

	@Override
	public OffenderAssociatedParties getOffenderAssociatedParties(final Long associatedPartyId) {
		final String sql = getQuery("OFFENDER_ASSOCIATED_PARTIES_T1_OFFENDER_ASSOCIATED_PARTIES_GET");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams(":associatedPartyId", associatedPartyId),
					new BeanPropertyRowMapper<OffenderAssociatedParties>(OffenderAssociatedParties.class));
		} catch (final Exception e) {
			logger.error("getOffenderAssociatedParties", e);
			return null;
		}
	}

	@Override
	public OffenderAssocPEventNotifs lCheckExistCur(final Long offenderBookId) {
		final String sql = getQuery("OFFENDER_ASSOCIATED_PARTIES_T1_L_CHECK_EXIST_CUR");
		final RowMapper<OffenderAssocPEventNotifs> rowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderAssocPEventNotifs.class, mapping);
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams(":offenderBookId", offenderBookId),
					rowMapper);
		} catch (final Exception e) {
			logger.error("getOffenderAssociatedParties", e);
			return null;
		}
	}

	@Override
	public Long trgEventId() {
		final String sql = getQuery("OFFENDER_ASSOCIATED_PARTIES_T1_TRG_EVENT_ID");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Long.class);
		} catch (final Exception e) {
			logger.error("trgEventId", e);
			return null;
		}
	}

	@Override
	public Integer save(final OffenderAssocPEventNotifs offObj) {
		Integer returnValue = 0;
		final String sql = getQuery("OFFENDER_ASSOCIATED_PARTIES_T1_OFFENDER_ASSOC_P_EVENT_NOTIFS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		parameters.add(new BeanPropertySqlParameterSource(offObj));
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
			if (1 == returnArray.length) {
				returnValue = 1;
			}
		} catch (final Exception e) {
			returnValue = 0;
			logger.error("save ", e);
		}
		return returnValue;

	}

}
