package net.syscon.s4.cm.programsservices.impl;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.cm.programsservices.OcuschprRepository;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.iwp.beans.VAcpSchedules;

@Repository
public class OcuschprRepositoryImpl extends RepositoryBase implements OcuschprRepository {

	private static Logger logger = LogManager.getLogger(OcuschprRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> vAcpSchedulesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CRS_ACTY_ID", new FieldMapper("crsActyId")).build();

	public List<VAcpSchedules> vAcpSchedulesExecuteQuery(VAcpSchedules objSearchDao) {
		if (objSearchDao != null && objSearchDao.getCatchUpSessionFlag().equals("N")) {
			return getcourseSchedule(objSearchDao);
		} else {
			return getcourseScheduleOne(objSearchDao);
		}
	}

	public List<VAcpSchedules> getcourseSchedule(final VAcpSchedules objSearchDao) {

		final String sql = getQuery("OCUSCHPR_OCDPATTE_GET_COURSE_SCHEDULE");
		final RowMapper<VAcpSchedules> OffenderRowMapper = Row2BeanRowMapper.makeMapping(sql, VAcpSchedules.class,
				vAcpSchedulesMapping);
		List<VAcpSchedules> list = null;
		try {
			final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
			final String strDate = formatter.format(objSearchDao.getScheduleDate());
			list = namedParameterJdbcTemplate.query(sql,
					createParams("p_schedule_date", strDate, "p_phase_provider_party_id",
							objSearchDao.getPhaseProviderPartyId(), "p_phase_provider_party_code",
							objSearchDao.getPhaseProviderPartyCode(), "p_service_id", objSearchDao.getProgramId()),
					OffenderRowMapper);
		} catch (final Exception e) {
			logger.error("getOffenderDetails", e.getMessage());
		}
		return list;
	}

	public List<VAcpSchedules> getcourseScheduleOne(final VAcpSchedules objSearchDao) {

		final String sql = getQuery("OCUSCHPR_OCDPATTE_GET_COURSE_SCHEDULE_ONE");
		final RowMapper<VAcpSchedules> OffenderRowMapper = Row2BeanRowMapper.makeMapping(sql, VAcpSchedules.class,
				vAcpSchedulesMapping);
		List<VAcpSchedules> list = null;
		try {
			final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
			final String strDate = formatter.format(objSearchDao.getScheduleDate());
			list = namedParameterJdbcTemplate.query(sql,
					createParams("p_schedule_date", strDate, "p_phase_provider_party_id",
							objSearchDao.getPhaseProviderPartyId(), "p_phase_provider_party_code",
							objSearchDao.getPhaseProviderPartyCode(), "p_service_id", objSearchDao.getProgramId(),
							"p_catch_up_session", objSearchDao.getCatchUpSessionFlag()),
					OffenderRowMapper);
		} catch (final Exception e) {
			logger.error("getOffenderDetails", e.getMessage());
		}
		return list;
	}

}
