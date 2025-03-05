package net.syscon.s4.inst.institutionalactivities.impl;

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
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.VOffenderCourseAttendances;
import net.syscon.s4.inst.institutionalactivities.OcupdetaRepository;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.OffAllowPayDetails;

@Repository
public class OcupdetaRepositoryImpl extends RepositoryBase implements OcupdetaRepository {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcupdetaRepositoryImpl.class.getName());
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
			.build();

	@Override
	public List<VOffenderCourseAttendances> unpaidAttendanceExecuteQuery(VOffenderCourseAttendances searchBean) {
		String sql = getQuery("OCUPDETA_GET_UNPAID_ATTENDANCE_RECORDS_QUERY");
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
			sqlQuery.append(" offender_id_display = :offender_id_display " + " AND");
			inParameterSource.addValue("offender_id_display", searchBean.getOffenderIdDisplay());
		}
		if (searchBean.getProgramId() != null) {
			sqlQuery.append(" program_id = :program_id " + " AND");
			inParameterSource.addValue("program_id", searchBean.getProgramId());
		}
		if (searchBean.getCrsActyId() != null) {
			sqlQuery.append(" crs_acty_id = :crs_acty_id " + " AND");
			inParameterSource.addValue("crs_acty_id", searchBean.getCrsActyId());
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("AND")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		preparedSql = preparedSql + "order by offender_id_display ,event_type,programDescription,activityDescription ";
		final RowMapper<VOffenderCourseAttendances> OffCourseAttendRowMapper = Row2BeanRowMapper.makeMapping(sql,
				VOffenderCourseAttendances.class, offenderCourseAttendenceMapping);
		return namedParameterJdbcTemplate.query(preparedSql, inParameterSource, OffCourseAttendRowMapper);
	}

	@Override
	public Integer generatePay(List<VOffenderCourseAttendances> updateList) {
		String sql = getQuery("OCUPDETA_GENERATE_PAYAMOUNT");
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
	public List<OffAllowPayDetails> getUnpaidAllowanceDetailsChildQry(VOffenderCourseAttendances searchBean) {
		String sql = getQuery("OCUPDETA_GET_OFFENDER_ALLOWANCE_RECORDS_CHILD_QUERY");
		final StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource inParameterSource = new MapSqlParameterSource();
		String preparedSql = null;
		sqlQuery.append(sql);
		SimpleDateFormat form = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		if (searchBean.getStartTime() == null && searchBean.getEndTime() != null) {
			sqlQuery.append("OFF_ALLOWANCE_DAY <= TO_TIMESTAMP(:END_TIME,'DD/MM/YYYY')" + " AND");
			String endTime = form.format(searchBean.getEndTime());
			inParameterSource.addValue("END_TIME", endTime);
		}
		if (searchBean.getStartTime() != null && searchBean.getEndTime() != null) {
			sqlQuery.append(
					" OFF_ALLOWANCE_DAY >= TO_TIMESTAMP(:START_TIME,'DD/MM/YYYY') and OFF_ALLOWANCE_DAY <= TO_TIMESTAMP(:END_TIME,'DD/MM/YYYY') "
							+ " AND");
			String startTime = form.format(searchBean.getStartTime());
			String endTime = form.format(searchBean.getEndTime());
			inParameterSource.addValue("START_TIME", startTime);
			inParameterSource.addValue("END_TIME", endTime);
		}
		if (searchBean.getOffenderIdDisplay() != null) {
			sqlQuery.append(" offender_id_display = :offender_id_display " + " AND");
			inParameterSource.addValue("offender_id_display", searchBean.getOffenderIdDisplay());
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("AND")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}	
			
		final RowMapper<OffAllowPayDetails> OffCourseAttendRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffAllowPayDetails.class, offenderCourseAttendenceMapping);
		return namedParameterJdbcTemplate.query(preparedSql, inParameterSource, OffCourseAttendRowMapper);
	}

	@Override
	public Integer generatePayAllow(List<VOffenderCourseAttendances> updateList) {
		String sql = getQuery("OCUPDETA_GENERATE_PAY_ALLOW_AMOUNT");
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
	public List<OffAllowPayDetails> getUnpaidGenerateDetailsPayData(VOffenderCourseAttendances searchBean) {
		String sql = getQuery("OCUPDETA_GET_OFFENDER_ALLOWANCE_GENERATE_RECORDS_CHILD_QUERY");
		final StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource inParameterSource = new MapSqlParameterSource();
		String preparedSql = null;
		sqlQuery.append(sql);
		SimpleDateFormat form = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		if (searchBean.getStartTime() == null && searchBean.getEndTime() != null) {
			sqlQuery.append("OFF_ALLOWANCE_DAY <= TO_TIMESTAMP(:END_TIME,'DD/MM/YYYY')" + " AND");
			String endTime = form.format(searchBean.getEndTime());
			inParameterSource.addValue("END_TIME", endTime);
		}
		if (searchBean.getStartTime() != null && searchBean.getEndTime() != null) {
			sqlQuery.append(
					" OFF_ALLOWANCE_DAY >= TO_TIMESTAMP(:START_TIME,'DD/MM/YYYY') and OFF_ALLOWANCE_DAY <= TO_TIMESTAMP(:END_TIME,'DD/MM/YYYY') "
							+ " AND");
			String startTime = form.format(searchBean.getStartTime());
			String endTime = form.format(searchBean.getEndTime());
			inParameterSource.addValue("START_TIME", startTime);
			inParameterSource.addValue("END_TIME", endTime);
		}
		if (searchBean.getOffenderIdDisplay() != null) {
			sqlQuery.append(" offender_id_display = :offender_id_display " + " AND");
			inParameterSource.addValue("offender_id_display", searchBean.getOffenderIdDisplay());
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("AND")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}	
			
		final RowMapper<OffAllowPayDetails> OffCourseAttendRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffAllowPayDetails.class, offenderCourseAttendenceMapping);
		return namedParameterJdbcTemplate.query(preparedSql, inParameterSource, OffCourseAttendRowMapper);
	}
}
