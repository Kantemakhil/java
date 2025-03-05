package net.syscon.s4.pkgs.tag_qm_pc.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.pkgs.QmCompositions;
import net.syscon.s4.pkgs.QmObjects;
import net.syscon.s4.pkgs.QmProcesses;
import net.syscon.s4.pkgs.tag_qm_pc.TagQmPcRepository;

@Repository
public class TagQmPcRepositoryImpl extends RepositoryBase implements TagQmPcRepository {
	private final Logger logger = LogManager.getLogger(TagQmPcRepositoryImpl.class);

	@Override
	public Integer prInsQmProcesses(final QmProcesses qmProcesses) {
		Integer returnValue = 0;
		final String sql = getQuery("PRINS_QM_PROCESSES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		parameters.add(new BeanPropertySqlParameterSource(qmProcesses));
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
			if (1 == returnArray.length) {
				returnValue = 1;
			}
		} catch (final Exception e) {
			returnValue = 0;
			logger.error("prInsQmProcesses ", e);
		}
		return returnValue;

	}

	@Override
	public Integer prInsQmCompositions(final QmCompositions qmCompositions) {
		Integer returnValue = 0;
		final String sql = getQuery("PRINS_QM_COMPOSITIONS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		parameters.add(new BeanPropertySqlParameterSource(qmCompositions));
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
			if (1 == returnArray.length) {
				returnValue = 1;
			}
		} catch (final Exception e) {
			returnValue = 0;
			logger.error("prInsQmCompositions ", e);
		}
		return returnValue;

	}

	@Override
	public Integer prInsQmObjects(final QmObjects qmObjects) {
		Integer returnValue = 0;
		final String sql = getQuery("PRINS_QM_OBJECTS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		parameters.add(new BeanPropertySqlParameterSource(qmObjects));
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
			if (1 == returnArray.length) {
				returnValue = 1;
			}
		} catch (final Exception e) {
			returnValue = 0;
			logger.error("prInsQmObjects ", e);
		}
		return returnValue;

	}

	@Override
	public Integer prUpdQmCompositions(final QmCompositions qmCompositions) {
		Integer returnValue = 0;
		final String sql = getQuery("PRUPD_QM_COMPOSITIONS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		parameters.add(new BeanPropertySqlParameterSource(qmCompositions));
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
			if (1 == returnArray.length) {
				returnValue = 1;
			}
		} catch (final Exception e) {
			returnValue = 0;
			logger.error("prUpdQmCompositions ", e);
		}
		return returnValue;

	}

	@Override
	public Long qmProcessesCur(final String name) {
		final String sql = getQuery("PRINS_QM_PROCESSES_CUR");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams(":name", name), Long.class);
		} catch (final Exception e) {
			logger.error("qmProcessesCur :" + e);
			return null;
		}
	}
}
