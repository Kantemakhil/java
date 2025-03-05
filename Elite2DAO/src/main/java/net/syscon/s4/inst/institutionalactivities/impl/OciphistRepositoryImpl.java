package net.syscon.s4.inst.institutionalactivities.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.VOffenderCourseAttendances;
import net.syscon.s4.inst.institutionalactivities.OciphistRepository;
import net.syscon.s4.inst.institutionalactivities.beans.prgPayBatchesBean;

@Repository
public class OciphistRepositoryImpl extends RepositoryBase implements OciphistRepository {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OciphistRepositoryImpl.class.getName());
	private final Map<String, FieldMapper> prgPayBatchMapping = new ImmutableMap.Builder<String, FieldMapper>().build();
	private final Map<String, FieldMapper> offcopurseAttMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("pay_batch_id", new FieldMapper("payBatchId"))
			.put("pay_actual_rate", new FieldMapper("payActualRate"))
			.put("pay_hours", new FieldMapper("payHours"))
			.put("pay_actual_amount", new FieldMapper("payActualAmount"))
			.put("programDescription", new FieldMapper("programDescription"))
			.put("activity_description", new FieldMapper("activityDescription")).build();

	@Override
	public List<prgPayBatchesBean> prgPayBatchesExecuteQuery(prgPayBatchesBean searchBean) {
		final String sql = getQuery("OCIPHIST_GET_PRG_PAY_BATCH_DATA");
		List<prgPayBatchesBean> returnList = new ArrayList<>();
		final RowMapper<prgPayBatchesBean> prgPayBatchRowMapper = Row2BeanRowMapper.makeMapping(sql,
				prgPayBatchesBean.class, prgPayBatchMapping);
		try {
			return namedParameterJdbcTemplate.query(sql,
					createParams("offender_book_id", searchBean.getOffenderBookId()), prgPayBatchRowMapper);
		} catch (Exception e) {
			logger.error("In prgPayBatchesExecuteQuery method : ", e);
		}
		return returnList;
	}

	@Override
	public List<VOffenderCourseAttendances> payDetailsExecuteQuery(prgPayBatchesBean searchBean) {
		final String sql = getQuery("OCIPHIST_GET_PAID_DETAILS_DATA");
		List<VOffenderCourseAttendances> returnList = new ArrayList<>();
		final RowMapper<VOffenderCourseAttendances> offcourseRowMapper = Row2BeanRowMapper.makeMapping(sql,
				VOffenderCourseAttendances.class, offcopurseAttMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("batch_id", searchBean.getBatchId(),
					"offender_book_id", searchBean.getOffenderBookId()), offcourseRowMapper);
		} catch (Exception e) {
			logger.error("In prgPayBatchesExecuteQuery method : ", e);
		}
		return returnList;
	}

}