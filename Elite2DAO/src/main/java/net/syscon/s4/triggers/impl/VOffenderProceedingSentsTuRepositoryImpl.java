package net.syscon.s4.triggers.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.inst.legals.beans.VOffenderProceedingSents;
import net.syscon.s4.triggers.VOffenderProceedingSentsTuRepository;

@Repository
public class VOffenderProceedingSentsTuRepositoryImpl extends RepositoryBase
		implements VOffenderProceedingSentsTuRepository {
	private final Logger logger = LogManager.getLogger(VOffenderProceedingSentsTuRepositoryImpl.class.getName());

	@Override
	public Integer delete(final VOffenderProceedingSents newObj) {
		final String sql = getQuery("V_OFFENDER_PROCEEDING_SENTS_TU_DELETE");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		parameters.add(new BeanPropertySqlParameterSource(newObj));
		try {
			String tableName = "Offender_Proceeding_Sents";
			String whereClause = "Offender_proceeding_id = :offenderProceedingId AND Offender_Book_Id  = :offenderBookId AND Sentence_seq  = :New.sentenceSeq";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method delete", e);
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (final Exception e) {
			logger.error("delete", e);
		}
		if (returnArray.length == 1) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Integer insert(final VOffenderProceedingSents newObj) {
		final String sql = getQuery("V_OFFENDER_PROCEEDING_SENTS_TU_INSERT");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		parameters.add(new BeanPropertySqlParameterSource(newObj));
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
