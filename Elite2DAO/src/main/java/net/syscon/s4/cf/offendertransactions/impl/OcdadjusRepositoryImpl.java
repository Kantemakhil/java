package net.syscon.s4.cf.offendertransactions.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.cf.deductions.beans.OffFeeBillTransactions;
import net.syscon.s4.cf.deductions.beans.OffFeeBills;
import net.syscon.s4.cf.deductions.beans.offBillingStatements;
import net.syscon.s4.cf.offendertransactions.OcdadjusRepository;
import net.syscon.s4.common.beans.TransactionTypes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.VTrustHeader;

@Repository
public class OcdadjusRepositoryImpl extends RepositoryBase implements OcdadjusRepository {
	private static Logger logger = LogManager.getLogger(OcdbreciRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> offfeebilltrnMapping = new ImmutableMap.Builder<String, FieldMapper>()
			
			.put("CASELOADID", new FieldMapper("caseloadId"))
			.put("FEECODE", new FieldMapper("feeCode"))
			.put("OFFENDER_FEE_ID", new FieldMapper("offenderFeeId"))
			.put("ADJUSTMENTAMOUNT", new FieldMapper("adjustmentAmount"))
			.put("FEE_ACT_STATUS", new FieldMapper("feeActStatus"))
			.put("CASELOADDESC", new FieldMapper("caseloadDesc"))
			.put("FEECODEDESC", new FieldMapper("feecodeDesc"))
			.build();
	private final Map<String, FieldMapper> txnTypeMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CODE", new FieldMapper("code"))
			.put("DESCRIPTION", new FieldMapper("description"))
			.build();

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            OffFeeBillTransactions
	 *
	 * @return List<OffFeeBillTransactions>
	 */
	@Override
	public List<OffFeeBillTransactions> offFeeExecuteQuery(final BigDecimal offenderBookId) {
		final String sql = getQuery("OCDADJUS_OFF_FEE_BILL_TRN_DATA");
		final RowMapper<OffFeeBillTransactions> feeBillTrnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffFeeBillTransactions.class, offfeebilltrnMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("OFFENDER_BOOK_ID",offenderBookId), feeBillTrnRowMapper);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            OffFeeBillTransactions
	 *
	 * @return List<OffFeeBillTransactions>
	 */
	@Override
	public List<TransactionTypes> rgAdjustType() {
		final String sql = getQuery("OCDADJUS_GET_ADJUST_TYPE_DATA");
		final RowMapper<TransactionTypes> tcnTypeRowMapper = Row2BeanRowMapper.makeMapping(sql, TransactionTypes.class,
				txnTypeMapping);
		return namedParameterJdbcTemplate.query(sql, createParams(), tcnTypeRowMapper);
	}

	@Override
	public Integer offTxnCommit(List<OffFeeBillTransactions> insertList) {
		String sql = getQuery("OCDADJUS_OFF_BILL_TRN_INSERT_QUERY");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<>();
		for (OffFeeBillTransactions bean : insertList) {
			parameters.add(new BeanPropertySqlParameterSource(bean));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (insertList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}
	public Integer getBillTranId(final String billId) {
		final String sql = getQuery("OCDADJUS_OFF_FEE_BILLS_TRANSACTIONS_PRE_INSERT");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("BILL_ID",billId), Integer.class);
	}

	public String getbillEndDayPfVal() {
		final String sql = getQuery("OCDADJUS_GET_SYS_PFL_VALUE");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public Integer getCasePlanId(final VTrustHeader bean) {
		final String sql = getQuery("OCDADJUS_GET_CASEPLAN_ID");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql,
					createParams("OFFENDERBOOKID", bean.getOffenderBookId()), Integer.class);
		} catch (EmptyResultDataAccessException e) {
			return 0;
		}
	}
	@Override
	public Integer offStmtCommit(List<offBillingStatements> insertList) {
		String sql = getQuery("OCDADJUS_OFF_BILL_STMT_INSERT_QUERY");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<>();
		for (offBillingStatements bean : insertList) {
			parameters.add(new BeanPropertySqlParameterSource(bean));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (insertList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}
	@Override
	public Integer offFeeBillsUpdate(List<offBillingStatements> updateBean) {
		String sql = getQuery("OCDADJUS_UPDATE_OFF_FEE_BILLS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<>();
		for (offBillingStatements bean : updateBean) {
			parameters.add(new BeanPropertySqlParameterSource(bean));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (returnArray.length == 1) {
			return 1;
		} else {
			return 0;
		}
	}
	@Override
	public Integer offFeeBillsTxnUpdate(List<offBillingStatements> updateBean) {
		String sql = getQuery("OCDADJUS_UPDATE_OFF_FEE_BILL_TRANSACTIONS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<>();
		for (offBillingStatements bean : updateBean) {
			parameters.add(new BeanPropertySqlParameterSource(bean));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (returnArray.length == 1) {
			return 1;
		} else {
			return 0;
		}
	}

	public Integer stmtPreInsert() {
		final String sql = getQuery("OCDADJUS_OFF_BILL_STMT_PRE_INSERT_QUERY");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Integer.class);
	}
	
	
	@Override
	public Integer getOffAdjustTxnId() {
		final String sql = getQuery("OCDADJSU_ADJUST_TRANSACTION_PREINSERT");
		Integer obj = null;
		Integer returnval = null;
		obj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), Integer.class);
		if (obj != null) {
			returnval = obj;
		}
		return returnval;
	}

	@Override
	public BigDecimal getPostQuery(final String billId) {
		final String sql = getQuery("OCDADJSU_POST_EXECUTEQUERY");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("bill_id",billId), BigDecimal.class);
	}

}
