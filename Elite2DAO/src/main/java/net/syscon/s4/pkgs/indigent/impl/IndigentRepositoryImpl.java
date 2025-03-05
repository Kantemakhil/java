package net.syscon.s4.pkgs.indigent.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.inmate.beans.OffenderSubAccounts;
import net.syscon.s4.pkgs.indigent.IndigentRepository;

@Repository
public class IndigentRepositoryImpl extends RepositoryBase implements IndigentRepository {

	private final Map<String, FieldMapper> offSubAccMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("TRUST_ACCOUNT_CODE", new FieldMapper("trustAccountCode")).put("IND_DATE", new FieldMapper("indDate"))
			.put("IND_DAYS", new FieldMapper("indDays")).build();

	@Override
	public String getChkCaseloadC(final String pCsldId) {
		final String sql = getQuery("CHK_CASELOAD_C");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("P_CSLD_ID", pCsldId), String.class);
	}

	@Override
	public List<OffenderSubAccounts> getChkIndAcCTrstAccCode(final BigDecimal pOffId, final String pCsldId,
			final String csldType) {
		final String sql = getQuery("CHK_IND_AC_C");
		final RowMapper<OffenderSubAccounts> rowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderSubAccounts.class,
				offSubAccMapping);
		return namedParameterJdbcTemplate.query(sql,
				createParams("P_OFF_ID", pOffId, "P_CSLD_ID", pCsldId, "CSLD_TYPE", csldType), rowMapper);
	}

	@Override
	public OffenderSubAccounts getCurChkIndIndDateNDays(final BigDecimal pOffId, final String pCsldId,
			final String pAgyLocId, final Long pTrustAcctCode) {
		final String sql = getQuery("CUR_CHK_IND");
		final RowMapper<OffenderSubAccounts> rowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderSubAccounts.class,
				offSubAccMapping);
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("P_OFF_ID", pOffId, "P_CSLD_ID", pCsldId,
				"P_AGYLOC_ID", pAgyLocId, "P_TRUST_ACCT_CODE", pTrustAcctCode), rowMapper);
	}

	@Override
	public Integer getDiffIndDateNdays(final BigDecimal indDays, final Date indDate) {
		final String sql = getQuery("GET_DIFFERENCE");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("INDDAYS", indDays, "INDDATE", indDate),
				Integer.class);
	}

	@Override
	public SystemProfiles getSystemProfileC() {
		final String sql = getQuery("SYSTEM_PROFILE_C");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), SystemProfiles.class);
	}

	@Override
	public Integer getAccCode() {
		final String sql = getQuery("GET_TRUST_ACCOUNT_CODE_C");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Integer.class);

	}

	@Override
	public Double getSumOffSubActBalC(final BigDecimal pOffId, final Integer lvTrustAccCode) {
		final String sql = getQuery("SUM_OFF_SUB_ACT_BAL_C");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("P_OFF_ID", pOffId, "LV_TRUST_ACCOUNT_CODE", lvTrustAccCode), Double.class);

	}

	@Override
	public Date getMaxIndDateC(final BigDecimal pOffId, final Integer lvTrustAccCode) {
		final String sql = getQuery("MAX_IND_DATE_C");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("P_OFF_ID", pOffId, "LV_TRUST_ACCOUNT_CODE", lvTrustAccCode), Date.class);
	}

	@Override
	public BigDecimal getMaxIndDays(final BigDecimal pOffId, final Integer lvTrustAccCode, final Date lvIndigentDate) {
		final String sql = getQuery("GET_MAX_IND_DAYS");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("P_OFF_ID", pOffId, "LV_TRUST_ACCOUNT_CODE",
				lvTrustAccCode, "LV_INDIGENT_DATE", lvIndigentDate), BigDecimal.class);
	}

	@Override
	public Integer getDiffBtSysNIndDate(final Date indDate) {
		final String sql = getQuery("GET_NO_FM_DATE_SUBTRATION");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("INDDATE", indDate), Integer.class);
	}
}