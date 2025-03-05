package net.syscon.s4.pkgs.no_pkg_procudres.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.CaseloadAccountPeriods;
import net.syscon.s4.common.beans.CaseloadAccountSummaries;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.im.beans.CaseloadCurrentAccounts;
import net.syscon.s4.pkgs.no_pkg_procudres.OtmoncoaGenAccountCodesRepository;

@Repository
public class OtmoncoaGenAccountCodesRepositoryImpl extends RepositoryBase implements OtmoncoaGenAccountCodesRepository {

	final Map<String, FieldMapper> accountCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("SEAL_FLAG", new FieldMapper("seal_flag")).build();

	@Override
	public List<String> lCaseloadsCur(final String caseloadId) {
		final String sql = getQuery("L_CASELOADS_CUR");
		return namedParameterJdbcTemplate.queryForList(sql, createParams("p_caseload_id", caseloadId), String.class);
	}

	@Override
	public Integer lCurrentPeriodCur() {
		final String sql = getQuery("L_CURRENT_PERIOD_CUR");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Integer.class);
	}

	@Override
	public int lLastClosedCur(final String caseloadId) {
		Integer retval = 0;
		final String sql = getQuery("L_LAST_CLOSED_CUR");
		try {
			retval = namedParameterJdbcTemplate.queryForObject(sql, createParams("p_caseload_id", caseloadId),
					Integer.class);
		} catch (Exception e) {
			return retval;
		}
		return retval == null ? 0 : retval;
	}

	@Override
	public List<AccountCodes> lCsldCurrAcctBaseCur(final String caseloadId, final Integer accountCode,
			final Integer pAccountPeriodId) {
		final String sql = getQuery("L_CSLD_CURR_ACCT_BASE_CUR");
		final RowMapper<AccountCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, AccountCodes.class,
				accountCodesMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("p_caseload_id", caseloadId, "p_account_code",
				accountCode, "p_account_period_id", pAccountPeriodId), columnRowMapper);
	}

	@Override
	public List<CaseloadCurrentAccounts> lCsldCurrAcctTxnsCur(final String caseloadId, final Integer accountCode,
			final Integer pLastPeriodId, final Integer lCurrentPeriodId) {
		final String sql = getQuery("L_CSLD_CURR_ACCT_TXNS_CUR");
		final RowMapper<CaseloadCurrentAccounts> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				CaseloadCurrentAccounts.class, accountCodesMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("p_caseload_id", caseloadId, "p_account_code",
				accountCode, "p_last_period_id", pLastPeriodId, "p_current_period_id", lCurrentPeriodId),
				columnRowMapper);
	}

	@Override
	public List<CaseloadAccountPeriods> lCsldAcctPeriodsCur(final String caseloadId, final Integer pAccountPeriodId) {
		final String sql = getQuery("L_CSLD_ACCT_PERIODS_CUR");
		final RowMapper<CaseloadAccountPeriods> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				CaseloadAccountPeriods.class, accountCodesMapping);
		return namedParameterJdbcTemplate.query(sql,
				createParams("p_caseload_id", caseloadId, "p_account_period_id", pAccountPeriodId), columnRowMapper);
	}

	@Override
	public List<CaseloadAccountSummaries> lCsldAcctSummCur(final String caseloadId, final Integer accountCode,
			final Integer pAccountPeriodId) {
		final String sql = getQuery("L_CSLD_ACCT_SUMM_CUR");
		final RowMapper<CaseloadAccountSummaries> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				CaseloadAccountSummaries.class, accountCodesMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("p_caseload_id", caseloadId, "p_account_code",
				accountCode, "p_account_period_id", pAccountPeriodId), columnRowMapper);
	}

	@Override
	public void insertCaseloadCurrentAccountsBase(final String caseloadId, final Integer accountCode,
			final Integer lCurrentPeriodId, final String userName) {
		final String sql = getQuery("INSERT_CASELOAD_CURRENT_ACCOUNTS_BASE");
		namedParameterJdbcTemplate.update(sql, createParams("caseload_id", caseloadId, "account_code", accountCode,
				"account_period_id", lCurrentPeriodId, "createUserId", userName));
	}

	@Override
	public void insertCaseloadCurrentAccountsTxns(final List<CaseloadCurrentAccounts> caseloadCurrentAccounts,
			final String userName) {
		final String sql = getQuery("INSERT_CASELOAD_CURRENT_ACCOUNTS_TXNS");
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final CaseloadCurrentAccounts list : caseloadCurrentAccounts) {
			list.setCreateUserId(userName);
			parameters.add(new BeanPropertySqlParameterSource(list));
		}
		namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
	}

	@Override
	public void insertCaseloadAccountPeriods(final List<CaseloadAccountPeriods> caseloadCurrentAccounts,
			final String userName) {
		final String sql = getQuery("INSERT_CASELOAD_ACCOUNT_PERIODS");
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final CaseloadAccountPeriods list : caseloadCurrentAccounts) {
			list.setCreateUserId(userName);
			parameters.add(new BeanPropertySqlParameterSource(list));
		}
		namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
	}

	@Override
	public void insertCaseloadAccountSummaries(final List<CaseloadAccountSummaries> lCsldAcctSummCur,
			final String userName) {
		final String sql = getQuery("INSERT_CASELOAD_ACCOUNT_SUMMARIES");
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final CaseloadAccountSummaries list : lCsldAcctSummCur) {
			list.setCreateUserId(userName);
			parameters.add(new BeanPropertySqlParameterSource(list));
		}
		namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
	}

}
