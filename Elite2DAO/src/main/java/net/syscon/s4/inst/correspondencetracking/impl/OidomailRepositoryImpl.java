package net.syscon.s4.inst.correspondencetracking.impl;

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
import net.syscon.s4.inst.correspondencetracking.OidomailRepository;
import net.syscon.s4.inst.correspondencetracking.beans.OffMailRestrictions;
import net.syscon.s4.inst.correspondencetracking.beans.OffenderMailLog;

@Repository
public class OidomailRepositoryImpl extends RepositoryBase implements OidomailRepository {

	private static Logger logger = LogManager.getLogger(OidomailRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> mailLogMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId")).build();

	@Override
	public List<OffenderMailLog> mailLogExecuteQuery(Long offenderBookId) {
		final String sql = getQuery("OIDOMAIL_FIND_OFFENDER_MAIL_LOGS");
		List<OffenderMailLog> mailLogsList = new ArrayList<OffenderMailLog>();
		try {
			mailLogsList = namedParameterJdbcTemplate.query(sql, createParams("OFFENDERBOOKID", offenderBookId),
					new BeanPropertyRowMapper<OffenderMailLog>(OffenderMailLog.class));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method mailLogExecuteQuery", e);
		}
		return mailLogsList;
	}

	@Override
	public List<OffMailRestrictions> mailRestrictionExecuteQuery(Long offenderBookId) {
		final String sql = getQuery("OIDOMAIL_FIND_OFFENDER_MAIL_RESTRICTIONS");
		List<OffMailRestrictions> mailRestrictionList = new ArrayList<OffMailRestrictions>();
		try {
			mailRestrictionList = namedParameterJdbcTemplate.query(sql, createParams("OFFENDERBOOKID", offenderBookId),
					new BeanPropertyRowMapper<OffMailRestrictions>(OffMailRestrictions.class));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method mailRestrictionExecuteQuery", e);
		}
		return mailRestrictionList;
	}

	@Override
	public Integer offenderMailLogInsert(List<OffenderMailLog> insertList) {
		final String sql = getQuery("OIDOMAIL_INSERT_OFFENDER_MAIL_LOGS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (OffenderMailLog offMailLog : insertList) {
			parameters.add(new BeanPropertySqlParameterSource(offMailLog));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("offenderMailLogInsert", e);
		}
		if (returnArray.length > 0) {
			return 1;
		} else {
			return 0;
		}

	}

	@Override
	public Integer offenderMailLogInsertUpdate(List<OffenderMailLog> updateList) {
		final String sql = getQuery("OIDOMAIL_UPDATE_OFFENDER_MAIL_LOGS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (OffenderMailLog offMailLog : updateList) {
			parameters.add(new BeanPropertySqlParameterSource(offMailLog));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("offenderMailLogInsertUpdate", e);
		}
		if (returnArray.length > 0) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Integer offenderMailLogInsertDelete(List<OffenderMailLog> deleteList) {
		final String sql = getQuery("OIDOMAIL_DELETE_OFFENDER_MAIL_LOGS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (OffenderMailLog offMailLog : deleteList) {
			parameters.add(new BeanPropertySqlParameterSource(offMailLog));
		}
		try {
			String tableName = "OFFENDER_MAIL_LOGS";
			String whereClause = "offender_book_id=:offenderBookId and LOG_SEQ = :logSeq";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method offenderMailLogInsertDelete", e);
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("offenderMailLogInsertDelete", e);
		}
		if (returnArray.length > 0) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Integer offMailRestrictionsInsert(List<OffMailRestrictions> insertList) {
		final String sql = getQuery("OIDOMAIL_INSERT_OFFENDER_MAIL_RESTRICTIONS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (OffMailRestrictions OffMailRestrictions : insertList) {
			parameters.add(new BeanPropertySqlParameterSource(OffMailRestrictions));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("offMailRestrictionsInsert", e);
		}
		if (returnArray.length > 0) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Integer offMailRestrictionsUpdate(List<OffMailRestrictions> updateList) {
		final String sql = getQuery("OIDOMAIL_UPDATE_OFFENDER_MAIL_RESTRICTIONS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (OffMailRestrictions offMailRestrictions : updateList) {
			parameters.add(new BeanPropertySqlParameterSource(offMailRestrictions));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("offMailRestrictionsUpdate", e);
		}
		if (returnArray.length > 0) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Integer offMailRestrictionsDelete(List<OffMailRestrictions> deleteList) {
		final String sql = getQuery("OIDOMAIL_DELETE_OFFENDER_MAIL_RESTRICTIONS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (OffMailRestrictions offMailRestrictions : deleteList) {
			parameters.add(new BeanPropertySqlParameterSource(offMailRestrictions));
		}
		try {
			String tableName = "OFFENDER_MAIL_RESTRICTIONS";
			String whereClause = "offender_book_id=:offenderBookId and  restriction_seq = :restrictionSeq";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method offMailRestrictionsDelete", e);
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("offMailRestrictionsDelete", e);
		}
		if (returnArray.length > 0) {
			return 1;
		} else {
			return 0;
		}
	}
	
	@Override
	public Long getOffenderMailLogSequence(Long offenderBookId) {
		final String sql = getQuery("OIDOMAIL_FIND_OFFENDER_MAIL_LOGS_SEQUENCE");
		 return namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderBookId", offenderBookId), Long.class);	
		
	}

}
