package net.syscon.s4.pkgs.tag_reference_codes.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.pkgs.tag_reference_codes.TagReferenceCodesRepository;

@Repository
public class TagReferenceCodesRepositoryImpl extends RepositoryBase implements TagReferenceCodesRepository {
	private static Logger logger = LogManager.getLogger(TagReferenceCodesRepositoryImpl.class.getName());

	@Override
	public ReferenceCodes domainCursor(final String domain, final String refCode) {
		final String sql = getQuery("DOMAIN_CUR");
		ReferenceCodes bean = new ReferenceCodes();
		try {
			bean = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("p_domain", domain, "p_ref_code", refCode), ReferenceCodes.class);
		} catch (Exception e) {
			logger.error("domainCursor" + e);
		}
		return bean;
	}

	@Override
	public String domainCursorToString(final String domain, final String refCode) {
		final String sql = getQuery("DOMAIN_CUR");
		String description = null;
		try {
			description = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("p_domain", domain, "p_ref_code", refCode), String.class);
		} catch (Exception e) {
			logger.error("domainCursor" + e);
		}
		return description;
	}
	
	@Override
	public String getDescCode(final String domain, final String code) {
		final String sql = getQuery("GET_DESC_CODE");
		String retVal = null;
		try {
			retVal = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("p_domain", domain, "p_ref_code", code), String.class);
		} catch (Exception e) {
			logger.error("getDescCode", e);
			retVal = null;
		}
		return retVal;
	}

	@Override
	public String defaultDomain(final String domain) {
		final String sql = getQuery("DEFAULT_DOMAIN");
		String retVal = null;
		try {
			retVal = namedParameterJdbcTemplate.queryForObject(sql, createParams("P_DOMAIN", domain), String.class);
		} catch (Exception e) {
			logger.error("defaultDomain :" + e);
		}
		return retVal;
	}
}