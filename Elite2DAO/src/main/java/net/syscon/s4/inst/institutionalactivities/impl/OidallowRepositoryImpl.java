package net.syscon.s4.inst.institutionalactivities.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
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
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.inst.institutionalactivities.OidallowRepository;
import net.syscon.s4.inst.institutionalactivities.beans.OffenderAllowances;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.Allowances;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.OffAllowPayDetails;

@Repository
public class OidallowRepositoryImpl extends RepositoryBase implements OidallowRepository {

	private static Logger logger = LogManager.getLogger(OidallowRepositoryImpl.class.getName());

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
			 put("paid_flag", new FieldMapper("paidFlag")).
			build();
	
	private final Map<String, FieldMapper> allowanceRowMapper = new ImmutableMap.Builder<String, FieldMapper>().build();

	@Override
	public List<OffenderAllowances> getOffenderAllowenceExecuteQuery(OffenderAllowances searchBean) {
		final String sql = getQuery("OIDALLOW_GET_OFFENDER_ALLOWANCE_GRID_DATA");
		final RowMapper<OffenderAllowances> sentRowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderAllowances.class,
				mMapping);
		List<OffenderAllowances> returnList = new ArrayList<>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("offenderBookId",searchBean.getOffenderBookId()), sentRowMapper);
		} catch (Exception e) {
			logger.error("getOffenderAllowenceExecuteQuery", e);
		}

		return returnList;
	}

	@Override
	public Integer insertOffenderAllowances(List<OffenderAllowances> insertList) {
		final String sql = getQuery("OIDALLOW_INSERT_OFFENDER_ALLOWANCE_DATA");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final OffenderAllowances list : insertList) {
			parameters.add(new BeanPropertySqlParameterSource(list));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("insertOffenderAllowances: ", e);
		}
		if (insertList.size() == returnArray.length) {
			return 1;
		} else {

			return 0;
		}
	}

	@Override
	public Integer updateOffenderAllowances(List<OffenderAllowances> updateList) {
		final String sql = getQuery("OIDALLOW_UPDATE_OFFENDER_ALLOWANCE_DATA");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderAllowances object : updateList) {
			parameters.add(new BeanPropertySqlParameterSource(object));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("updateOffenderAllowances: ", e);
		}
		if (updateList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	@Override
	public Integer deleteOffenderAllowances(List<OffenderAllowances> deleteList) {
		final String sql = getQuery("OIDALLOW_DELETE_OFFENDER_ALLOWANCE_DATA");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderAllowances sentenceCalcTypes : deleteList) {
			parameters.add(new BeanPropertySqlParameterSource(sentenceCalcTypes));
		}
		try {
			String tableName = "OFFENDER_ALLOWANCES";
			String whereClause = "OFF_ALLOWANCE_ID=:offAllowenceId";
			batchUpdatePreDeletedRows(tableName, whereClause, parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteOffenderAllowances",
					e);
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("deleteOffenderAllowances : ", e);
		}
		if (deleteList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public List<Allowances> getAllowenceLovData() {
		List<Allowances> returnList = new ArrayList<Allowances>();

		String sql = getQuery("OIDALLOW_GET_ALLOWENCE_TYPE_LOW_DATA");
		final RowMapper<Allowances> rowMapper = Row2BeanRowMapper.makeMapping(sql, Allowances.class,
				mMapping);
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), rowMapper);
		} catch (Exception e) {
			returnList = Collections.emptyList();
			logger.error(getClass().getName() + " error in getAllowenceLovData method :: " + e);
		}

		return returnList;
	}

	@Override
	public List<Allowances> getRateVersionData(String allowanceType) {
		List<Allowances> returnList = new ArrayList<Allowances>();

		String sql = getQuery("OIDALLOW_GET_ALLOWENCE_TYPE_UNIT_RATE_DATA");
		final RowMapper<Allowances> rowMapper = Row2BeanRowMapper.makeMapping(sql, Allowances.class,
				mMapping);
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("allowanceType",allowanceType), rowMapper);
		} catch (Exception e) {
			returnList = Collections.emptyList();
			logger.error(getClass().getName() + " error in getRateVersionData method :: " + e);
		}

		return returnList;
	}
	
	@Override
	public Date getLastPaidDate(BigDecimal offenderBookId,BigDecimal offAllowanceId) {
		String sql = getQuery("OIDALLOW_GET_LAST_PAID_DATE");
		Date paidDate = null;
		try {
			paidDate = namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderBookId", offenderBookId, "offAllowanceId",offAllowanceId), Date.class);
		} catch (Exception e) {
			logger.error(getClass().getName() + " error in getLastPaidDate method :: " + e);
		}
		return paidDate;
	}

	@Override
	public Integer saveOffAllowPayDetails(List<OffAllowPayDetails> offAllowPayDetList) {
		String sql = getQuery("OIDALLOW_INSERT_OFF_ALLOWANCES_PAY_RECORDS");
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
	public List<OffenderAllowances> getOffenderAllowences(BigDecimal offenderBookId) {
		final String sql = getQuery("OIDALLOW_GET_OFF_ALLOWANCES_RECORDS");
		final RowMapper<OffenderAllowances> sentRowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderAllowances.class,
				mMapping);
		List<OffenderAllowances> returnList = new ArrayList<>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("offenderBookId", offenderBookId), sentRowMapper);
		} catch (Exception e) {
			logger.error("getOffenderAllowenceExecuteQuery", e);
		}
		return returnList;
	}

	@Override
	public List<Allowances> getAllowances() {
		final String sql = getQuery("OIDALLOW_RETRIVE_ALLOWANCES_RECORDS");
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
	public Integer checkOffAllowPay(BigDecimal offenderBookId, BigDecimal offAllowId, Date OffAllowStartDt) {
		String sql = getQuery("OIDALLOW_GET_OFF_ALLOWANCES_PAY_COUNT");
		Integer result = 0;
		try {
			result = namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderBookId",offenderBookId,"offAllowanceId",offAllowId, "OffAllowStartDt",OffAllowStartDt), Integer.class);
		} catch (Exception e) {
			logger.error(getClass().getName() + " error in checkOffAllowPay method :: " + e);
		}
		return result;
	}
	
	@Override
	public Integer deleteOffAllowancePayDetails(List<OffenderAllowances> deleteList) {
		final String sql = getQuery("OIDALLOW_DELETE_OFF_ALOWANCES_PAY_DETAILS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderAllowances sentenceCalcTypes : deleteList) {
			parameters.add(new BeanPropertySqlParameterSource(sentenceCalcTypes));
		}
		try {
			String tableName = "OFF_ALOWANCES_PAY_DETAILS";
			String whereClause = "off_allowance_id=:offAllowanceId and offender_book_id = :offenderBookId";
			batchUpdatePreDeletedRows(tableName, whereClause, parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteOffAllowancePayDetails",
					e);
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("deleteOffAllowancePayDetails : ", e);
		}
		if (deleteList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}
	
	public Integer deleteRemainingOffAllowancePayDetails(final OffenderAllowances deletObj) {
		final String sql = getQuery("OIDALLOW_DELETE_REMAINING_OFF_ALOWANCES_PAY_DETAILS");
		try {
			String tableName = "off_alowances_pay_details";
			String whereClause = "off_allowance_id = :offAllowanceId and off_allowance_day > :endDate and off_allowance_day <= current_timestamp and pay_flag = 'N'";
			Map<String , Object> inputMap = new HashMap<String, Object>();
			inputMap.put("offAllowanceId", deletObj.getOffAllowanceId());
			inputMap.put("endDate", deletObj.getEndDate());
			inputMap.put("modifyUserId", deletObj.getModifyUserId());
			updatePreDeletedRow(tableName, whereClause , inputMap);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteRemainingOffAllowancePayDetails", e);
		}
		try {
			return namedParameterJdbcTemplate.update(sql, createParams("offAllowanceId", deletObj.getOffAllowanceId(), 
					"endDate", deletObj.getEndDate()));
		} catch (final Exception e) {
			logger.error("deleteRemainingOffAllowancePayDetails", e);
		}
		return 0;
	}
}
