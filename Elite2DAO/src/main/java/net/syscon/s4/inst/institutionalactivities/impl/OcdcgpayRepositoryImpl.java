package net.syscon.s4.inst.institutionalactivities.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.OffenderCourseAttendance;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.VOffenderCourseAttendances;
import net.syscon.s4.inst.institutionalactivities.OcdcgpayRepository;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.OffAllowPayDetails;

@Repository
public class OcdcgpayRepositoryImpl extends RepositoryBase implements OcdcgpayRepository {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcdcgpayRepositoryImpl.class.getName());
	private final Map<String, FieldMapper> offenderCourseAttendenceMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("POFFENDERIDDISPLAY", new FieldMapper("pOffenderIdDisplay"))
			.put("pay_system_rate", new FieldMapper("paySystemRate"))
			.put("amount", new FieldMapper("amount"))
			.put("pay_system_unit", new FieldMapper("paySystemUnit"))
			.put("programDescription", new FieldMapper("programDescription"))
			.put("activityDescription", new FieldMapper("activityDescription"))
			.put("STARTTIME", new FieldMapper("startTime"))
			.put("ENDTIME", new FieldMapper("endTime"))
			.put("pay_hours", new FieldMapper("payHours"))
			.put("pay_actual_rate", new FieldMapper("payActualRate"))
			.put("pay_actual_amount", new FieldMapper("payActualAmount"))
			.put("parent_crs_acty_id", new FieldMapper("parentCrsActyId"))
			.put("prev_allow_version_rate", new FieldMapper("preAllowanceVersionRate"))
			.build();

	@Override
	public List<VOffenderCourseAttendances> unpaidAttendanceExecuteQuery(VOffenderCourseAttendances searchBean) {
		String sql = getQuery("OCDCGPAY_GET_UNPAID_ATTENDANCE_RECORDS_QUERY");
		final StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource inParameterSource = new MapSqlParameterSource();
		String preparedSql = null;
		sqlQuery.append(sql);
		SimpleDateFormat form = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		if (searchBean.getStartTime() == null && searchBean.getEndTime() != null) {
			sqlQuery.append(" event_date <= TO_TIMESTAMP(:END_TIME,'DD/MM/YYYY')" + " AND");
			String endTime = form.format(searchBean.getEndTime());
			inParameterSource.addValue("END_TIME", endTime);
		}
		if (searchBean.getStartTime() != null && searchBean.getEndTime() != null) {
			sqlQuery.append(
					" event_date >= TO_TIMESTAMP(:START_TIME,'DD/MM/YYYY') and event_date <= TO_TIMESTAMP(:END_TIME,'DD/MM/YYYY') "
							+ " AND");
			String startTime = form.format(searchBean.getStartTime());
			String endTime = form.format(searchBean.getEndTime());
			inParameterSource.addValue("START_TIME", startTime);
			inParameterSource.addValue("END_TIME", endTime);
		}
		if (searchBean.getOffenderIdDisplay() != null) {
			sqlQuery.append(" LTRIM(OFFENDER_ID_DISPLAY::text,'0') LIKE LTRIM(:offender_id_display::text,'0')" + " AND ");
			inParameterSource.addValue("offender_id_display" , searchBean.getOffenderIdDisplay().trim() + "%");
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("AND")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		preparedSql = preparedSql
				+ " group by offender_id_display,last_name,first_name,offender_book_id,et ,programDescription,activityDescription,program_id,crs_acty_id,off_prgref_id,parent_crs_acty_id order by offender_id_display,event_type,programDescription,activityDescription";
		final RowMapper<VOffenderCourseAttendances> OffCourseAttendRowMapper = Row2BeanRowMapper.makeMapping(sql,
				VOffenderCourseAttendances.class, offenderCourseAttendenceMapping);
		return namedParameterJdbcTemplate.query(preparedSql, inParameterSource, OffCourseAttendRowMapper);
	}

	@Override
	public Integer generatePay(List<VOffenderCourseAttendances> updateList) {
		String sql = getQuery("OCDCGPAY_GENERATE_PAY");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<>();
		for (VOffenderCourseAttendances bean : updateList) {
			parameters.add(new BeanPropertySqlParameterSource(bean));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(e.getMessage());
			return 0;
		}
		if (updateList.size() == returnArray.length) {
			return updateList.get(0).getPayBatchId();
		} else {
			return 0;
		}
	}

	@Override
	public Integer getBatchId() {
		final String sql = getQuery("OCDCGPAY_GET_BATH_ID");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Integer.class);
		} catch (Exception e) {
			logger.error("In getBatchId method : ", e);
			return 0;
		}
	}

	@Override
	public List<OffenderCourseAttendance> getFromToDates() {
		final String sql = getQuery("OCDCGPAY_GET_DATES");
		List<OffenderCourseAttendance> returnList = new ArrayList<>();
		final RowMapper<OffenderCourseAttendance> OffCourseAttendRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderCourseAttendance.class, offenderCourseAttendenceMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), OffCourseAttendRowMapper);
		} catch (Exception e) {
			logger.error("In getFromToDates method : ", e);
			return returnList;
		}
	}

	@Override
	public Integer insertPrgPayBatches(VOffenderCourseAttendances object) {
		final String sql = getQuery("OCDCGPAY_INSERT_PRG_PAY_BATCHES");
		try {
			return namedParameterJdbcTemplate.update(sql,
					createParams("batchId", object.getPayBatchId(), "fromDate", object.getFromDate(), "toDate",
							object.getToDate(), "batchPayAmount", object.getTotalAmount(), "createDatetime",
							object.getCreateDatetime(), "createUserId", object.getCreateUserId()));
		} catch (final Exception e) {
			logger.error("insertPrgPayBatches", e);
		}
		return 0;
	}

	@Override
	public BigDecimal getSystemPayRate(String eventType, long programId, long crsActyId, long offPrgRefId) {
		final String sql = getQuery("OCDCGPAY_GET_SYSTEM_PAY_RATE");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("event_type", eventType, "program_id",
					programId, "crs_acty_id", crsActyId, "off_prgref_id", offPrgRefId), BigDecimal.class);
		} catch (Exception e) {
			logger.error("In getSystemPayRate method : ", e);
			return BigDecimal.ZERO;
		}
	}

	@Override
	public Integer insertPrgPayBatchOffCrs(List<VOffenderCourseAttendances> updateList) {
		String sql = getQuery("OCDCGPAY_INSET_PRG_PAY_BATCH_OFF_CRS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<>();
		for (VOffenderCourseAttendances bean : updateList) {
			parameters.add(new BeanPropertySqlParameterSource(bean));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(e.getMessage());
			return 0;
		}
		if (updateList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public List<OffAllowPayDetails> getUnpaidAllowanceDetailsQry(VOffenderCourseAttendances searchBean) {
		String sql = getQuery("OCDCGPAY_GET_OFFENDER_ALLOWANCE_RECORDS_QUERY");
		final StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource inParameterSource = new MapSqlParameterSource();
		String preparedSql = null;
		sqlQuery.append(sql);
		SimpleDateFormat form = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		if (searchBean.getStartTime() == null && searchBean.getEndTime() != null) {
			sqlQuery.append(" max_off_allowance_day <= TO_TIMESTAMP(:END_TIME,'DD/MM/YYYY')" + " AND");
			String endTime = form.format(searchBean.getEndTime());
			inParameterSource.addValue("END_TIME", endTime);
		}
		if (searchBean.getStartTime() != null && searchBean.getEndTime() != null) {
			sqlQuery.append(" max_off_allowance_day >= TO_TIMESTAMP(:START_TIME,'DD/MM/YYYY')"
					+ " and current_date >=  TO_TIMESTAMP(:END_TIME,'DD/MM/YYYY') "
							+ " AND");
			String startTime = form.format(searchBean.getStartTime());
			String endTime = form.format(searchBean.getEndTime());
			inParameterSource.addValue("START_TIME", startTime);
			inParameterSource.addValue("END_TIME", endTime);
		}
		if (searchBean.getOffenderIdDisplay() != null) {
			sqlQuery.append(" LTRIM(OFFENDER_ID_DISPLAY::text,'0') LIKE LTRIM(:offender_id_display::text,'0')" + " AND ");
			inParameterSource.addValue("offender_id_display" , searchBean.getOffenderIdDisplay().trim() + "%");
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("AND")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}		
		final RowMapper<OffAllowPayDetails> OffCourseAttendRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffAllowPayDetails.class, offenderCourseAttendenceMapping);
		List<OffAllowPayDetails> query = null;
		try {
		query  = namedParameterJdbcTemplate.query(preparedSql, inParameterSource, OffCourseAttendRowMapper);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return query;
	}
	
	
	@Override
	public Integer insertPayBatchOffAllow(List<VOffenderCourseAttendances> updateList) {
		String sql = getQuery("OCDCGPAY_INSERT_PAY_BATCH_OFF_ALLOWANCES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<>();
		for (VOffenderCourseAttendances bean : updateList) {
			parameters.add(new BeanPropertySqlParameterSource(bean));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(e.getMessage());
			return 0;
		}
		if (updateList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Integer generateAllowPay(List<VOffenderCourseAttendances> updateList) {
		String sql = getQuery("OCDCGPAY_GENERATE_ALLOW_PAY");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<>();
		for (VOffenderCourseAttendances bean : updateList) {
			parameters.add(new BeanPropertySqlParameterSource(bean));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(e.getMessage());
			return 0;
		}
		if (updateList.size() == returnArray.length) {
			return updateList.get(0).getPayBatchId();
		} else {
			return 0;
		}
	}
}
