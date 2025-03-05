package net.syscon.s4.cm.programsservices.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import net.syscon.s4.cm.programsservices.OcmxpstmRepository;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.iwp.impl.OcdatpowRepositoryImpl;

@Repository
public class OcmxpstmRepositoryImpl extends RepositoryBase implements OcmxpstmRepository {

	private static Logger logger = LogManager.getLogger(OcdatpowRepositoryImpl.class.getName());


	@Override
	public ReferenceCodes refCodeCondExecuteQuery(final String code) {
		final String sql = getQuery("OCMXPSTM_REF_CODE_CONDS");
		ReferenceCodes bean = new ReferenceCodes();
		try {
			bean = namedParameterJdbcTemplate.queryForObject(sql, createParams("code", code),
					new BeanPropertyRowMapper<ReferenceCodes>(ReferenceCodes.class));
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " refCodeCondExecuteQuery", e);
		}
		return bean;
	}

	public Integer refCodeCondInsertReference(final List<ReferenceCodes> lstReferenceCodes) {
		String sql = getQuery("OCMXPSTM_REFCODE_INSERT_REFERENCE_CODES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (ReferenceCodes referenceCodes : lstReferenceCodes) {
			parameters.add(new BeanPropertySqlParameterSource(referenceCodes));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " refCodeCondInsertReference", e);
		}
		if (lstReferenceCodes.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	@Override
	public Integer refCodeCondUpdateReference(List<ReferenceCodes> lstReferenceCodes) {
		String sql = getQuery("OCMXPSTM_REFCODE_UPDATE_REFERENCE_CODES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (ReferenceCodes referenceCodes : lstReferenceCodes) {
			parameters.add(new BeanPropertySqlParameterSource(referenceCodes));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " refCodeCondUpdateReference", e);
		}
		if (lstReferenceCodes.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

}
