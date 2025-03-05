package net.syscon.s4.inst.institutionalactivities.maintenance.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.inst.institutionalactivities.beans.OffenderAllowances;
import net.syscon.s4.inst.institutionalactivities.maintenance.OimallowRepository;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.Allowances;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.OffAllowPayDetails;

@Repository
public class OimallowRepositoryImpl extends RepositoryBase implements OimallowRepository{

	private static Logger logger = LogManager.getLogger(OimallowRepositoryImpl.class.getName());
	
	
	private final Map<String, FieldMapper> allowanceRowMapper = new ImmutableMap.Builder<String, FieldMapper>().build();
			
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("UPDATE_REASON_CODE", new FieldMapper("updateReasonCode"))
			.put("ACTIVE_FLAG", new FieldMapper("activeFlag")).put("ACTIVE_TYPE", new FieldMapper("activeType"))
			.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId")).put("DESCRIPTION", new FieldMapper("description"))
			.put("EFFECTIVE_DATE", new FieldMapper("effectiveDate")).put("EXPIRY_DATE", new FieldMapper("expiryDate"))
			.put("LIST_SEQ", new FieldMapper("listSeq")).put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("REASON_CATEGORY", new FieldMapper("reasonCategory")).
			 put("CODE", new FieldMapper("code")).
			 put("RATE", new FieldMapper("rate")).
			 put("UNIT", new FieldMapper("unit")).
			 put("VERSION_NO", new FieldMapper("versionNo")).
			 put("OFF_ALLOWANCE_ID", new FieldMapper("offAllowanceId")).
			build();
	
	@Override
	public List<Allowances> getAllAllowances() {
		
		List<Allowances> returnList = new ArrayList<Allowances>();

		String sql = getQuery("OIMALLOW_GET_ALLOWANCES_RECORDS");
		final RowMapper<Allowances> rowMapper = Row2BeanRowMapper.makeMapping(sql, Allowances.class,
				allowanceRowMapper);
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), rowMapper);
		} catch (Exception e) {
			returnList = Collections.emptyList();
			logger.error(getClass().getName() + " error in getAllAllowances method :: " + e);
		}

		return returnList;
	}

	@Override
	public Integer insertAllowances(List<Allowances> insertList) {
		String sql = getQuery("OIMALLOW_INSERT_ALLOWANCES_RECORDS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final Allowances allowances : insertList) {
			parameters.add(new BeanPropertySqlParameterSource(allowances));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(getClass().getName() + " error in insertAllowances method :: " + e);
		}
		return (insertList.size() == returnArray.length) ? 1 : 0;
	}
	
	@Override
	public Integer updateAllowances(List<Allowances> updateList) {
		String sql = getQuery("OIMALLOW_UPDATE_ALLOWANCES_RECORDS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final Allowances allowances : updateList) {
			parameters.add(new BeanPropertySqlParameterSource(allowances));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(getClass().getName() + " error in updateAllowances method :: " + e);
		}
		return (updateList.size() == returnArray.length) ? 1 : 0;
	}
	

	@Override
	public List<ReferenceCodes> getUnit() {
		List<ReferenceCodes> returnList = new ArrayList<ReferenceCodes>();

		String sql = getQuery("OIMALLOW_GET_HRS_UNIT");
		final RowMapper<ReferenceCodes> rowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				allowanceRowMapper);
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), rowMapper);
		} catch (Exception e) {
			returnList = Collections.emptyList();
			logger.error(getClass().getName() + " error in getUnit method :: " + e);
		}

		return returnList;
	}
	
	@Override
	public List<OffenderAllowances> getOffenderAllowences() {
		final String sql = getQuery("OIMALLOW_GET_OFF_ALLOWANCES_RECORDS");
		final RowMapper<OffenderAllowances> sentRowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderAllowances.class,
				mMapping);
		List<OffenderAllowances> returnList = new ArrayList<>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), sentRowMapper);
		} catch (Exception e) {
			logger.error("getOffenderAllowenceExecuteQuery", e);
		}
		return returnList;
	}

	@Override
	public List<Allowances> getAllowances() {
		final String sql = getQuery("OIMALLOW_RETRIVE_ALLOWANCES_RECORDS");
		final RowMapper<Allowances> sentRowMapper = Row2BeanRowMapper.makeMapping(sql, Allowances.class,
				allowanceRowMapper);
		List<Allowances> returnList = new ArrayList<>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), sentRowMapper);
		} catch (Exception e) {
			logger.error("getOffenderAllowenceExecuteQuery", e);
		}
		return returnList;
	}

	@Override
	public Integer saveOffAllowPayDetValues(List<OffAllowPayDetails> offAllowPayDetList) {
		String sql = getQuery("OIMALLOW_INSERT_OFF_ALLOWANCES_PAY_RECORDS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffAllowPayDetails offAllowPayDet : offAllowPayDetList) {
			parameters.add(new BeanPropertySqlParameterSource(offAllowPayDet));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(getClass().getName() + " error in saveOffAllowPayDetValues method :: " + e);
		}
		return (offAllowPayDetList.size() == returnArray.length) ? 1 : 0;
	}

	@Override
	public Integer checkOffAllowPay(BigDecimal offenderBookId, BigDecimal offAllowId) {
		String sql = getQuery("OIMALLOW_GET_OFF_ALLOWANCES_PAY_COUNT");
		Integer result = 0;
		try {
			result = namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderBookId",offenderBookId,"offAllowanceId",offAllowId), Integer.class);
		} catch (Exception e) {
			logger.error(getClass().getName() + " error in checkOffAllowPay method :: " + e);
		}
		return result;
	}
	
	

}
