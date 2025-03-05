package net.syscon.s4.pkgs.trust_main.impl;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.beans.Caseloads;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.pkgs.trust_main.TrustMainRepository;

@Repository
public class TrustMainRepositoryImpl extends RepositoryBase implements TrustMainRepository {

//	private static Logger logger = LogManager.getLogger(TrustMainRepositoryImpl.class.getName());
	// This CURSOR app_code is migrated from oracle
	@Override
	public String getTstHdrQryApplnCode(final String pFormName) {
		final String sql = getQuery("TRUST_HEADER_QUERY_GET_APPLN_CODE");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("P_FORM_NAME", pFormName), String.class);
	}

	// Purpose: GET_CASELOAD_DETAILS Function - Used to return the caseload_id based
	// upon the community_trust_caseload.
	@Override
	public Caseloads getCaseloadsDetails(final String pCaseloadId) {
		final String sql = getQuery("TRUST_HEADER_QUERY_GET_CASELOADS_DETAILS");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("P_CASELOAD_ID", pCaseloadId),
				new BeanPropertyRowMapper<Caseloads>(Caseloads.class));

	}

}