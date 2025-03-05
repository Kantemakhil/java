package net.syscon.s4.pkgs.oidadmis.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.OffenderExternalMovements;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.CaseloadAdmOtherProfiles;
import net.syscon.s4.inst.correspondencetracking.beans.OffenderMailLog;
import net.syscon.s4.pkgs.oidadmis.OidadmisPkgRepository;

@Repository
public class OidadmisPkgRepositoryImpl extends RepositoryBase implements OidadmisPkgRepository {

	private static Logger logger = LogManager.getLogger(OidadmisPkgRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> mapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("ORDER_PROPOSAL_CONDITION_ID", new FieldMapper("orderProposalConditionId")).build();

	@Override
	public List<CaseloadAdmOtherProfiles> getDefaultsCur(final String caseloadId) {
		final String sql = getQuery("GET_DEFAULTS_CUR");
		final RowMapper<CaseloadAdmOtherProfiles> rowMapper = Row2BeanRowMapper.makeMapping(sql,
				CaseloadAdmOtherProfiles.class, mapping);
		return namedParameterJdbcTemplate.query(sql, createParams("p_caseload_id", caseloadId), rowMapper);

	}

	@Override
	public Integer getLvCapacity(final Integer pLivingUnitId) {
		final String sql = getQuery("GET_LV_CAPACITY");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("p_living_unit_id", pLivingUnitId),
				Integer.class);
	}

	@Override
	public OffenderExternalMovements getAdmissionTypeCur(final Long pOffenderBookId) {
		final String sql = getQuery("GET_ADMISSION_TYPE_CUR");
		OffenderExternalMovements retObj = null;
		try {
			retObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("P_OFFENDER_BOOK_ID", pOffenderBookId),
					new BeanPropertyRowMapper<OffenderExternalMovements>(OffenderExternalMovements.class));
		} catch (Exception e) {
			logger.error("getAdmissionTypeCur :" + e);
			retObj = null;
		}
		return retObj;
	}

	@Override
	public String getMvmentReasonCode(final String pMvtReason) {
		final String sql = getQuery("GET_RECAPTURE_CUR");
		String code = null;
		try {
			code = namedParameterJdbcTemplate.queryForObject(sql, createParams("P_MVT_REASON", pMvtReason),
					String.class);
		} catch (Exception e) {
			logger.error("getMvmentReasonCode :" + e);
			code = null;
		}
		return code;
	}

	@Override
	public Integer getMsgNumber(final String pCaseloadId, final Integer pOffenderAge) {
		final String sql = getQuery("GET_MESSAGE_NUMBER");
		Integer msgNum = 0;
		try {
			msgNum = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("P_OFFENDER_AGE", pOffenderAge, "P_CASELOAD_ID", pCaseloadId), Integer.class);
		} catch (Exception e) {
			logger.error("getMsgNumber :", e);
			return 0;
		}
		return msgNum;
	}

	@Override
	public String getTrstFlag(final String pCaseloadId) {
		final String sql = getQuery("GET_TRUST_ACCOUNT_FLAG");
		String trustFlag = null;
		try {
			trustFlag = namedParameterJdbcTemplate.queryForObject(sql, createParams("P_CASELOAD_ID", pCaseloadId),
					String.class);
		} catch (Exception e) {
			logger.error("getTrstFlag :", e);
			return trustFlag;
		}
		return trustFlag;
	}

}
