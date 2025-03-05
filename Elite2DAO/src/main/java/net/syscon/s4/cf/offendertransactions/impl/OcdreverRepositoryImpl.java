package net.syscon.s4.cf.offendertransactions.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.cf.deductions.beans.OffFeeBillTransactions;
import net.syscon.s4.cf.deductions.beans.OffFeeBills;
import net.syscon.s4.cf.deductions.beans.offBillingStatements;
import net.syscon.s4.cf.offendertransactions.OcdreverRepository;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
@Repository
public class OcdreverRepositoryImpl extends RepositoryBase implements OcdreverRepository {
	
	private static Logger logger = LogManager.getLogger(OcdreverRepositoryImpl.class);

	private final Map<String, FieldMapper> offfeebilltrnMapping = new ImmutableMap.Builder<String, FieldMapper>()

			.put("CASELOADID", new FieldMapper("caseloadId")).put("FEECODE", new FieldMapper("feeCode"))
			.put("OFFENDER_FEE_ID", new FieldMapper("offenderFeeId"))
			.put("ADJUSTMENTAMOUNT", new FieldMapper("adjustmentAmount"))
			.put("feecodedesc", new FieldMapper("feecodeDesc"))
	        .put("BILLTXNTYPEDESC", new FieldMapper("billTxnTypeDesc")).build();
	
	private final Map<String, FieldMapper> txnTypeMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CODE", new FieldMapper("code")).put("DESCRIPTION", new FieldMapper("description")).build();

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao OffFeeBillTransactions
	 *
	 * @return List<OffFeeBillTransactions>
	 */
	@Override
	public List<OffFeeBillTransactions> offFeeTxnExecuteQuery(final BigDecimal offenderBookId) {
		final String sql = getQuery("OCDREVER_OFF_FEE_BILL_TRN_DATA");
		final RowMapper<OffFeeBillTransactions> feeBillTrnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffFeeBillTransactions.class, offfeebilltrnMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("OFFENDER_BOOK_ID", offenderBookId),
				feeBillTrnRowMapper);
	}

	@Override
	public Integer offTxnCommit(List<OffFeeBillTransactions> insertList) {
		String sql = getQuery("OCDREVER_OFF_BILL_TRN_INSERT_QUERY");
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
		final String sql = getQuery("OCDREVER_OFF_FEE_BILLS_TRANSACTIONS_PRE_INSERT");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("BILL_ID", billId), Integer.class);
	}


	@Override
	public Integer offStmtCommit(List<offBillingStatements> insertList) {
		String sql = getQuery("OCDREVER_OFF_BILL_STMT_INSERT_QUERY");
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
		String sql = getQuery("OCDREVER_UPDATE_OFF_FEE_BILLS");
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
		String sql = getQuery("OCDREVER_UPDATE_OFF_FEE_BILL_TRANSACTIONS");
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
		final String sql = getQuery("OCDREVER_OFF_BILL_STMT_PRE_INSERT_QUERY");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Integer.class);
	}
	
	@Override
	public BigDecimal getOffFeeBillOrginalBalanceOwing(String billId) {
		final String sql = getQuery("OCDREVER_GET_OFF_BILL_ORIGINAL_BALANCE_OWING");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("billId",billId), BigDecimal.class);
	}
	
	@Override
	public List<OffFeeBillTransactions> getAdjustedBills(String billId,Integer billTxnNo) {
		final String sql = getQuery("OCDREVER_GET_ADJUSTED_BILLS");
		try {
			final RowMapper<OffFeeBillTransactions> feeBillTrnRowMapper = Row2BeanRowMapper.makeMapping(sql,
					OffFeeBillTransactions.class, offfeebilltrnMapping);
			return namedParameterJdbcTemplate.query(sql, createParams("BILLID", billId,"BILLTXNNO",billTxnNo),
					feeBillTrnRowMapper);
		}catch (Exception e) {
			logger.error("Exception in " + this.getClass().getName() + "getAdjustedBills method",e);
			return Collections.emptyList();
		}
	}
	
	@Override
	public Integer offFeeBillsUpdateAging(OffFeeBills updateBean) {
		String sql = getQuery("OCDREVER_UPDATE_OFF_FEE_BILL_BILLAGING");
		Integer returnVal = null;
		List<SqlParameterSource> parameters = new ArrayList<>();
			parameters.add(new BeanPropertySqlParameterSource(updateBean));
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("billLdppStartDate", updateBean.getBillExpectedLdppStartDate());
     		paramMap.put("billId", updateBean.getBillId());
     		returnVal = namedParameterJdbcTemplate.update(sql,paramMap);
     		return returnVal;
	}
	
	@Override
	public OffFeeBills getOldDataOffFeeBills(String billId) {
		final String sql = getQuery("GET_OLD_DATA_OFF_FEE_BILLS");
		final RowMapper<OffFeeBills> feeBillTrnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffFeeBills.class, offfeebilltrnMapping);
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("billId",billId),
				feeBillTrnRowMapper);
	}

}
