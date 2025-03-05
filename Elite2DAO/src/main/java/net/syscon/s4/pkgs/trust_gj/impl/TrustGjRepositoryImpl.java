package net.syscon.s4.pkgs.trust_gj.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.pkgs.trust_gj.TrustGjRepository;
@Repository("TrustGjRepositoryImpl_pkg")
public class TrustGjRepositoryImpl extends RepositoryBase implements TrustGjRepository{
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>().build();

	@Override
	public Long accountPeriodCur(final Date date) {
		final String sql = getQuery("ACCOUNT_PERIOD_CUR");
		//final RowMapper<Long> mRowMapper = Row2BeanRowMapper.makeMapping(sql, Long.class, mMapping);
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("P_DATE",date), Long.class);
	}
	
	@Override
	public BigDecimal trustgtSelect(final String caseLoadId,final  Integer pAcccountPeriodId) {
		final String sql = getQuery("TRUSTGT_SELECT");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("P_CASELOAD_ID",caseLoadId,"P_ACCCOUNT_PERIOD_ID",pAcccountPeriodId), BigDecimal.class);
	}
	
	@Override
	public BigDecimal trustgtSelectMax(final String caseLoadId) {
		final String sql = getQuery("TRUSTGT_SELECT_MAX");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("P_CASELOAD_ID",caseLoadId), BigDecimal.class);

	}
	
	@Override
	public String lockRecordCur(final String caseLoadId,final Date txnEntryDate) {
		final String sql = getQuery("LOCK_RECORD_CUR");
		//final RowMapper<Integer> mRowMapper = Row2BeanRowMapper.makeMapping(sql, Integer.class, mMapping);
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("P_CASELOAD_ID",caseLoadId,"P_TXN_DATE",txnEntryDate),String.class);
	}
	
	@Override
	public Integer caseloadAccountPeriods(final String caseLoadId, final Date txnEntryDate, final String userName) {
		final String sql = getQuery("TRUST_GJ_CASELOAD");
		return namedParameterJdbcTemplate.update(sql,
				createParams("P_CASELOAD_ID", caseLoadId, "P_TXN_DATE", txnEntryDate, "modifyUserId", userName));
	}

	@Override
	public BigDecimal getCurrentBalance(String caseLoadId, Integer accountCode,String userId) {
		final String sql = getQuery("TRUSTGT_GET_CURRENT_BALANCE_FN");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("P_CASELOAD_ID", caseLoadId, "P_ACCOUNT_CODE", accountCode,"userId",userId),BigDecimal.class);
	}
}