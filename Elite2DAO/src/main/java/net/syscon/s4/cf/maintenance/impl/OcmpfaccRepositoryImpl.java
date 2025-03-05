package net.syscon.s4.cf.maintenance.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.cf.maintenance.OcmpfaccRepository;
import net.syscon.s4.cf.maintenance.beans.FeeAccounts;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.inmate.beans.DeductionTypes;
import net.syscon.s4.sa.usersystemsecurity.beans.StaffLocationRoles;
@Repository
public class OcmpfaccRepositoryImpl extends RepositoryBase implements OcmpfaccRepository {
	private final Map<String, FieldMapper> feeAccountsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.build();
	private final Map<String, FieldMapper> accountCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CODE", new FieldMapper("code"))
			.put("DESCRIPTION", new FieldMapper("description"))
			.build();
	private final Map<String, FieldMapper> deductionTypesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CODE", new FieldMapper("code"))
			.put("DESCRIPTION", new FieldMapper("description"))
			.build();

	/**
	 * Creates new OcmcoactRepositoryImpl class Object
	 */
	public OcmpfaccRepositoryImpl() {
		// OcmpfaccRepositoryImpl
	}

	@Override
	public List<AccountCodes> getAccCodes() {
		final String sql = getQuery("OCMPFACC_GET_ACCOUNT_CODES");
		final RowMapper<AccountCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, AccountCodes.class,
				accountCodesMapping);
		return namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
	}

	@Override
	public List<DeductionTypes> getFeeCodes() {
		final String sql = getQuery("OCMPFACC_GET_FEE_CODES");
		final RowMapper<DeductionTypes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, DeductionTypes.class,
				deductionTypesMapping);
		return namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
	}

	@Override
	public List<FeeAccounts> feeAccountsExecuteQuery() {
		final String sql = getQuery("OCMPFACC_GET_FEE_ACCOUNTS");
		List<FeeAccounts> returnobj=new ArrayList<FeeAccounts>();
		try {
			
			final RowMapper<FeeAccounts> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, FeeAccounts.class,
					feeAccountsMapping);
			
			returnobj= namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		}catch (Exception e) {
			return returnobj;
		}
		return returnobj;
	}

	@Override
	public Integer preInsert(final FeeAccounts bean) {
		final String sql = getQuery("OCMPFACC_PRE_INSERT");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("accountCode", bean.getAccountCode(), "feeCode", bean.getFeeCode()), Integer.class);
	}

	@Override
	public FeeAccounts insertFeeAccounts(final List<FeeAccounts> insertList) {
		final String sql = getQuery("OCMPFACC_INSERT_FEE_ACCOUNTS");
		int[] returnArray = new int[] {};
		FeeAccounts returnData = new FeeAccounts();
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final FeeAccounts offPlan : insertList) {
			parameters.add(new BeanPropertySqlParameterSource(offPlan));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (insertList.size() == returnArray.length) {
			returnData.setSealFlag("1");
			return returnData;
		} else {

			returnData.setSealFlag("0");
			return returnData;
		}
		
//		}
	}

	@Override
	public FeeAccounts updateFeeAccounts(final List<FeeAccounts> updateList) {
		final String sql = getQuery("OCMPFACC_UPDATE_FEE_ACCOUNTS");
		int[] returnArray = new int[] {};
		FeeAccounts returnData = new FeeAccounts();
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final FeeAccounts offPlan : updateList) {
			parameters.add(new BeanPropertySqlParameterSource(offPlan));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (updateList.size() == returnArray.length) {
			returnData.setSealFlag("1");
			return returnData;
		} else {
			returnData.setSealFlag("0");
			return returnData;
		}
	}@Override
	public Integer preInsertListSeq(final FeeAccounts bean) {
		final String sql = getQuery("OCMPFACC_PRE_INSERT_LISTSEQ");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("listSeq", bean.getListSeq()), Integer.class);
	}
	@Override
	public Integer preUpdateListSeq(final FeeAccounts bean) {
		final String sql = getQuery("OCMPFACC_PRE_UPDATE_LISTSEQ");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("listSeq", bean.getListSeq(), "ACCOUNT_CODE",
				bean.getAccountCode(), "FEE_CODE", bean.getFeeCode()), Integer.class);
	}
}
