package net.syscon.s4.pkgs.tag_header.impl;

import java.math.BigDecimal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.pkgs.tag_header.TagHeaderStatusRepository;

@Repository
public class TagHeaderStatusRepositoryImpl extends RepositoryBase implements TagHeaderStatusRepository {

	Logger logger = LogManager.getLogger(TagHeaderStatusRepositoryImpl.class);
	
	@Override
	public String cnoteCur(Long offenderBookId, String caseNoteType) {
		final String sqlname = getQuery("TAG_HEADER_CNOTE_CUR");
		try {
			return namedParameterJdbcTemplate.queryForObject(sqlname, createParams("offenderBookId", offenderBookId, "caseNoteType", caseNoteType), String.class);
		} catch (final Exception e) {
			logger.error("cnoteCur", e);
			return null;
		}
	}

	@Override
	public String getTypeCur(String currentUser) {
		final String sqlname = getQuery("TAG_HEADER_GET_TYPE_CUR");
		try {
			return namedParameterJdbcTemplate.queryForObject(sqlname, createParams("currentUser", currentUser), String.class);
		} catch (final Exception e) {
			logger.error("getTypeCur", e);
			return null;
		}
	}

	@Override
	public int getCountCur(BigDecimal rootOffenderId) {
		final String sqlname = getQuery("TAG_HEADER_GET_COUNT_CUR");
		try {
			return namedParameterJdbcTemplate.queryForObject(sqlname, createParams("rootOffenderId", rootOffenderId), Integer.class);
		} catch (final Exception e) {
			logger.error("getCountCur", e);
			return 0;
		}
	}

	@Override
	public Long checkMaxBooking(BigDecimal rootOffenderId) {
		final String sqlname = getQuery("TAG_HEADER_CHECK_MAX_BOOKING");
		try {
			return namedParameterJdbcTemplate.queryForObject(sqlname, createParams("rootOffenderId", rootOffenderId), Long.class);
		} catch (final Exception e) {
			logger.error("checkMaxBooking", e);
			return 0L;
		}
	}

}
