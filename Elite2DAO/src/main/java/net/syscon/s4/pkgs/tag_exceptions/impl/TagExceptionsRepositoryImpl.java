package net.syscon.s4.pkgs.tag_exceptions.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.pkgs.TagExceptions;
import net.syscon.s4.pkgs.tag_exceptions.TagExceptionsRepository;

@Repository
public class TagExceptionsRepositoryImpl extends RepositoryBase implements TagExceptionsRepository {
	Logger logger = LogManager.getLogger(TagExceptionsRepositoryImpl.class);

	@Override
	public Long sidCur() {
		final String sqlname = getQuery("TAG_EXCEPTIONS_SID_CUR");
		try {
			return namedParameterJdbcTemplate.queryForObject(sqlname, createParams(), Long.class);
		} catch (final Exception e) {
			logger.error("sidCur", e);
			return null;
		}

	}

	@Override
	public String moduleNameCur(final Long sid) {
		final String sqlname = getQuery("TAG_EXCEPTIONS_MODULE_NAME_CUR");
		try {
			return namedParameterJdbcTemplate.queryForObject(sqlname, createParams("sid", sid), String.class);
		} catch (final Exception e) {
			logger.error("moduleNameCur", e);
			return null;
		}

	}

	@Override
	public String systemProfilesCur() {
		final String sqlname = getQuery("TAG_EXCEPTIONS_SYSTEM_PROFILES_CUR");
		try {
			return namedParameterJdbcTemplate.queryForObject(sqlname, createParams(), String.class);
		} catch (final Exception e) {
			logger.error("systemProfilesCur", e);
			return null;
		}

	}

	@Override
	public Integer tagExceptionsInsert(final List<TagExceptions> tagExceptionsList) {
		Integer returnValue = 0;
		final String sql = getQuery("TAG_EXCEPTIONS_INSERT");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final TagExceptions orAudit : tagExceptionsList) {
			parameters.add(new BeanPropertySqlParameterSource(orAudit));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
			if (tagExceptionsList.size() == returnArray.length) {
				returnValue = 1;
			}
		} catch (final Exception e) {
			returnValue = 0;
			logger.error("tagExceptionsInsert ", e);
		}
		return returnValue;

	}

	@Override
	public Integer tagExceptionsInsertException(final List<TagExceptions> tagExceptionsList) {
		Integer returnValue = 0;
		final String sql = getQuery("TAG_EXCEPTIONS_INSERT_EXCEPTION");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final TagExceptions orAudit : tagExceptionsList) {
			parameters.add(new BeanPropertySqlParameterSource(orAudit));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
			if (tagExceptionsList.size() == returnArray.length) {
				returnValue = 1;
			}
		} catch (final Exception e) {
			returnValue = 0;
			logger.error("tagExceptionsInsertException ", e);
		}
		return returnValue;

	}

	@Override
	public BigDecimal tagErrorId() {
		final String sqlname = getQuery("TAG_EXCEPTIONS_TAG_ERROR_ID");
		try {
			return namedParameterJdbcTemplate.queryForObject(sqlname, createParams(), BigDecimal.class);
		} catch (final Exception e) {
			logger.error("tagErrorId", e);
			return null;
		}

	}

}
