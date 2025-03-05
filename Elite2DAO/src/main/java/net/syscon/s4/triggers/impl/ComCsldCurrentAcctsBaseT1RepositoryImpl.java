package net.syscon.s4.triggers.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.triggers.ComCsldCurrentAccountsBase;
import net.syscon.s4.triggers.ComCsldCurrentAcctsBaseT1Repository;

@Repository
public class ComCsldCurrentAcctsBaseT1RepositoryImpl extends RepositoryBase
		implements ComCsldCurrentAcctsBaseT1Repository {
	private final Logger logger = LogManager.getLogger(ComCsldCurrentAcctsBaseT1RepositoryImpl.class.getName());

	@Override
	public ComCsldCurrentAccountsBase getComCsldCurrentAccountsBase(
			final ComCsldCurrentAccountsBase comCsldCurrentAccountsBase) {
		final String sql = getQuery("COM_CSLD_CURRENT_ACCTS_BASE_T1_GET");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql,
					createParams(":caseloadId", comCsldCurrentAccountsBase.getCaseloadId(), ":accountCode",
							comCsldCurrentAccountsBase.getAccountCode()),
					new BeanPropertyRowMapper<ComCsldCurrentAccountsBase>(ComCsldCurrentAccountsBase.class));
		} catch (final Exception e) {
			logger.error("getComCsldCurrentAccountsBase", e);
			return null;
		}
	}

	@Override
	public String lBankChqBooksExistsCur(final String caseloadId, final String accountCode) {
		final String sql = getQuery("COM_CSLD_CURRENT_ACCTS_BASE_T1_L_BANK_CHQ_BOOKS_EXISTS_CUR");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql,
					createParams(":caseloadId", caseloadId, ":accountCode", accountCode), String.class);
		} catch (final Exception e) {
			logger.error("lBankChqBooksExistsCur", e);
			return null;
		}
	}

	@Override
	public Integer update(final ComCsldCurrentAccountsBase comCsldCurrentAccountsBase) {
		final String sql = getQuery("COM_CSLD_CURRENT_ACCTS_BASE_T1_UPDATE");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		parameters.add(new BeanPropertySqlParameterSource(comCsldCurrentAccountsBase));
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

}
