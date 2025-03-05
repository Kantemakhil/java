package net.syscon.s4.inst.incidentsoic.maintenance.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.ReferenceDomains;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.inst.incidentsoic.maintenance.OimsrlucRepository;
import net.syscon.s4.iwp.impl.OcdatpowRepositoryImpl;

@Repository
public class OimsrlucRepositoryImpl extends RepositoryBase  implements OimsrlucRepository {
	
	private static Logger logger = LogManager.getLogger(OcdatpowRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> referenceDomainsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OWNER_CODE", 						new FieldMapper("ownerCode"))
			.put("MODIFY_USER_ID", 						new FieldMapper("modifyUserId"))
			.put("OLD_CODE_TABLE", 						new FieldMapper("oldCodeTable"))
			.put("DOMAIN", 						new FieldMapper("domain"))
			.put("APPLN_CODE", 						new FieldMapper("applnCode"))
			.put("CODE_LENGTH", 						new FieldMapper("codeLength"))
			.put("PARENT_DOMAIN", 						new FieldMapper("parentDomain"))
			.put("SEAL_FLAG", 						new FieldMapper("sealFlag"))
			.put("DOMAIN_STATUS", 						new FieldMapper("domainStatus"))
			.put("MODIFY_DATETIME", 						new FieldMapper("modifyDatetime"))
			.put("DESCRIPTION", 						new FieldMapper("description"))
			.put("SUPER_SET_DOMAIN", 						new FieldMapper("superSetDomain"))
			.put("'1'", 						new FieldMapper("  '1' "))
			.build();

	@Override
	public List<ReferenceDomains> refDmnExcuteQuery() {
		final String sql = getQuery("OIMSRLUC_REF_CODE_DOMAIN");
		final RowMapper<ReferenceDomains> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceDomains.class, referenceDomainsMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}
	
	public Integer refCodeCondInsertReference(final List<ReferenceCodes> lstReferenceCodes) {
		String sql = getQuery("OIMSRLUC_REFCODE_INSERT_STAFF_REPORTS_MAINT");
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
		String sql = getQuery("OIMSRLUC_REFCODE_UPDATE_STAFF_REPORTS_MAINT");
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

	@Override
	public ReferenceCodes refCodeCondExecuteQuery(final String reportType) {
		final String sql = getQuery("OIMSRLUC_REF_CODE_CONDS");
		ReferenceCodes bean = new ReferenceCodes();
		try {
			bean = namedParameterJdbcTemplate.queryForObject(sql, createParams("reportType", reportType),
					new BeanPropertyRowMapper<ReferenceCodes>(ReferenceCodes.class));
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " refCodeCondExecuteQuery", e);
		}
		return bean;
	}

	@Override
	public List<ReferenceCodes> unitLovExecuteQuery() {
		final String sql = getQuery("OIMSRLUC_REF_CODE_UNIT_DOMAIN");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, referenceDomainsMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}
	
}
