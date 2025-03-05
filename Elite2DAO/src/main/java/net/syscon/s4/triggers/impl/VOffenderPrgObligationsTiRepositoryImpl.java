package net.syscon.s4.triggers.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import net.syscon.s4.cm.programsservices.VOffenderPrgObligations;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.triggers.VOffenderPrgObligationsTiRepository;

@Repository
public class VOffenderPrgObligationsTiRepositoryImpl extends RepositoryBase
		implements VOffenderPrgObligationsTiRepository {
	private final Logger logger = LogManager.getLogger(VOffenderPrgObligationsTiRepositoryImpl.class);

	@Override
	public Integer insert(final VOffenderPrgObligations offenPrgOblig) {
		final String sql = getQuery("V_OFFENDER_PRG_OBLIGATIONS_TI_INSERT");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		parameters.add(new BeanPropertySqlParameterSource(offenPrgOblig));
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

	@Override
	public Integer update(final VOffenderPrgObligations offenPrgOblig) {
		final String sql = getQuery("V_OFFENDER_PRG_OBLIGATIONS_TI_UPDARE");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		parameters.add(new BeanPropertySqlParameterSource(offenPrgOblig));
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (final Exception e) {
			logger.error("update", e);
		}
		if (returnArray.length == 1) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Integer delete1(final VOffenderPrgObligations offenderPrgObligationHty) {
		final String sql = getQuery("V_OFFENDER_PRG_OBLIGATIONS_TI_DELETE1");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		parameters.add(new BeanPropertySqlParameterSource(offenderPrgObligationHty));
		try {
			String tableName = "offender_prg_obligation_hty";
			String whereClause = "offender_prg_obligation_id = :offenderPrgObligationId";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method delete1", e);
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (final Exception e) {
			logger.error("delete1", e);
		}
		if (returnArray.length == 1) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Integer delete2(final VOffenderPrgObligations offenPrgOblig) {
		final String sql = getQuery("V_OFFENDER_PRG_OBLIGATIONS_TI_DELETE2");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		parameters.add(new BeanPropertySqlParameterSource(offenPrgOblig));
		try {
			String tableName = "offender_prg_obligations";
			String whereClause = "offender_prg_obligation_id = :offenderPrgObligationId";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method delete2", e);
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (final Exception e) {
			logger.error("delete2", e);
		}
		if (returnArray.length == 1) {
			return 1;
		} else {
			return 0;
		}
	}
}
