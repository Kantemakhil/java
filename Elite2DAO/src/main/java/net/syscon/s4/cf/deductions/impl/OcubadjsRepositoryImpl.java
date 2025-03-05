package net.syscon.s4.cf.deductions.impl;

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
import net.syscon.s4.cf.deductions.OcubadjsRepository;
import net.syscon.s4.cf.deductions.beans.OffFeeBillTransactions;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;

@Repository
public class OcubadjsRepositoryImpl extends RepositoryBase implements OcubadjsRepository {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcubadjsRepositoryImpl.class);

	private final Map<String, FieldMapper> refMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CODE", new FieldMapper("code")).put("DESCRIPTION", new FieldMapper("description")).build();
	private final Map<String, FieldMapper> billDetMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("BILL_ID", new FieldMapper("billId")).put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("OFFENDER_FEE_ID", new FieldMapper("offenderFeeId"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId")).put("BILL_DATE", new FieldMapper("billDate"))
			.put("BILL_GENERATE_DATETIME", new FieldMapper("billGenerateDatetime"))
			.put("BILL_GENERATE_AMOUNT", new FieldMapper("billGenerateAmount"))
			.put("BILL_GENERATE_STATUS", new FieldMapper("billGenerateStatus"))
			.put("BILL_GENERATE_STAFF_ID", new FieldMapper("billGenerateStaffId"))
			.put("BILL_GENERATE_USER", new FieldMapper("billGenerateUser"))
			.put("BILL_EXPECTED_STATEMENT_DATE", new FieldMapper("billExpectedStatementDate"))
			.put("BILL_EXPECTED_AR_DUE_DATE", new FieldMapper("billExpectedArDueDate"))
			.put("BILL_EXPECTED_LDPP_START_DATE", new FieldMapper("billExpectedLdppStartDate"))
			.put("BILL_EXPECTED_LDPP_END_DATE", new FieldMapper("billExpectedLdppEndDate"))
			.put("STATEMENT_GENERATED_FLAG", new FieldMapper("statementGeneratedFlag"))
			.put("BILLING_STATEMENT_ID", new FieldMapper("billingStatementId"))
			.put("SEAL_FLAG", new FieldMapper("sealFlag")).build();

	private final Map<String, FieldMapper> roleIdMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("ROLE_ID", new FieldMapper("roleId"))

			.build();

	/**
	 * Creates new OtdocfeeRepositoryImpl class Object
	 */
	public OcubadjsRepositoryImpl() {
		// OcufovdtRepositoryImpl
	}

	@Override
	public List<ReferenceCodes> adjustmentTypeRecordGroup() {
		final String sql = getQuery("OCUBADJS_ADJUSTMENT_TYPE_RG");
		List<ReferenceCodes> returnList = new ArrayList<>();
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				refMapping);

		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (final EmptyResultDataAccessException e) {
			logger.error("overrideTypeRecordGroup ", e);
			return Collections.emptyList();
		}
		return returnList;
	}

	@Override
	public Integer getstaffId(String userId) {
		final String sql = getQuery("OCUBADJS_GET_CURRENT_STAFFDETAIL");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("userId", userId), Integer.class);
	}

	public Integer getBillTranId(final String billId) {
		final String sql = getQuery("OCUBADJS_OFF_FEE_BILLS_TRANSACTIONS_PRE_INSERT");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("BILL_ID", billId), Integer.class);
	}

	@Override
	public Integer offFeeBillTransInsertQuery(OffFeeBillTransactions ofFeebillTranObj) {
		final String sql = getQuery("OCUBADJS_OFF_FEE_BILLS_TRANSACTIONS_INSERT");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<>();
		parameters.add(new BeanPropertySqlParameterSource(ofFeebillTranObj));
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));

		if (returnArray.length > 0) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public List<OffFeeBillTransactions> billAdjustDetailsExecuteQuery(OffFeeBillTransactions searchBean) {
		final String sql = getQuery("OCUBADJS_FIND_BILL_OVERRIDE_DETAILS");
		final RowMapper<OffFeeBillTransactions> mapper = Row2BeanRowMapper.makeMapping(sql,
				OffFeeBillTransactions.class, billDetMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("billId", searchBean.getBillId()), mapper);
	}

	@Override
	public String getSelectedOverrideType(OffFeeBillTransactions searchBean) {
		final String sql = getQuery("OCUBADJS_FIND_GET_BILL_TXN_USAGE");
		String overrideType = null;
		try {
			overrideType = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("billTxnType", searchBean.getBillTxnType()), String.class);
		} catch (final EmptyResultDataAccessException e) {
			logger.error("overrideTypeRecordGroup ", e);
			return overrideType;
		}
		return overrideType;
	}

	@Override
	public OffFeeBillTransactions getOriginalBalanceOwing(OffFeeBillTransactions searchBean) {
		final String sql = getQuery("OCUBADJS_GET_ORIGINAL_BALANCE_OWING");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("billId", searchBean.getBillId()),
				new BeanPropertyRowMapper<OffFeeBillTransactions>(OffFeeBillTransactions.class));

	}
	
	@Override
	public OffFeeBillTransactions getLatestRec(OffFeeBillTransactions searchBean) {
		final String sql = getQuery("OCUBADJS_GET_LATEST_RECORD");
		OffFeeBillTransactions obj = new OffFeeBillTransactions();
		try {
			obj = namedParameterJdbcTemplate.queryForObject(sql, createParams("BILLID", searchBean.getBillId()),
					new BeanPropertyRowMapper<OffFeeBillTransactions>(OffFeeBillTransactions.class));
		} catch (final EmptyResultDataAccessException e) {
			logger.error("getLatestRec ", e);
		}
		return obj;
	}

}
