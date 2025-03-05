package net.syscon.s4.pkgs.oidintmv.impl;

import java.util.ArrayList;
import java.util.HashMap;
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
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules;
import net.syscon.s4.pkgs.oidintmv.OidintmvPkgRepository;

@Repository
public class OidintmvPkgRepositoryImpl extends RepositoryBase implements OidintmvPkgRepository {

	private static Logger logger = LogManager.getLogger(OidintmvPkgRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> mapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("event_id", new FieldMapper("eventId")).put("offender_id", new FieldMapper("offenderId"))
			.put("offender_book_id", new FieldMapper("offenderBookId"))
			.put("offender_id_display", new FieldMapper("offenderIdDisplay"))
			.put("offender_last_name", new FieldMapper("offenderLastName"))
			.put("offender_first_name", new FieldMapper("offenderFirstName"))
			.put("living_unit_id", new FieldMapper("livingUnitId"))
			.put("living_unit_desc", new FieldMapper("livingUnitDesc")).put("event_type", new FieldMapper("eventType"))
			.put("event_type_desc", new FieldMapper("eventTypeDesc"))
			.put("event_sub_type", new FieldMapper("eventSubType"))
			.put("event_sub_type_desc", new FieldMapper("eventSubTypeDesc"))
			.put("agency_iml_id", new FieldMapper("agencyImlId"))
			.put("agency_iml_desc", new FieldMapper("agencyImlDesc"))
			.put("to_internal_location_id", new FieldMapper("toInternalLocationId"))
			.put("to_int_loc_user_desc", new FieldMapper("toIntLocUserDesc"))
			.put("reference_id", new FieldMapper("referenceId")).put("start_time", new FieldMapper("startTime"))
			.build();

	public List<VOffenderAllSchedules> getSchedules(final VOffenderAllSchedules objSearchDao) {
		final String sql = getQuery("GET_SCHEDULES_CUR");
		final Map<String, Object> valuesList = new HashMap<String, Object>();
		valuesList.put("p_agy_loc_id", objSearchDao.getAgyLocId());
		valuesList.put("p_move_date", objSearchDao.getEventDate());
		valuesList.put("p_from_time", objSearchDao.getStartTime());
		valuesList.put("p_to_time", objSearchDao.getEndTime());
		valuesList.put("p_living_unit_level_1", objSearchDao.getLuLevel1Code());
		valuesList.put("p_living_unit_level_2", objSearchDao.getLuLevel2Code());
		valuesList.put("p_living_unit_level_3", objSearchDao.getLuLevel3Code());
		valuesList.put("p_agency_iml_level_1", objSearchDao.getAgencyImlLevel1Code());
		valuesList.put("p_agency_iml_level_2", objSearchDao.getAgencyImlLevel2Code());
		valuesList.put("p_agency_iml_level_3", objSearchDao.getAgencyImlLevel3Code());
		valuesList.put("p_to_int_loc_level_1", objSearchDao.getToIntLocLevel1Code());
		valuesList.put("p_to_int_loc_level_2", objSearchDao.getToIntLocLevel2Code());
		valuesList.put("p_to_int_loc_level_3", objSearchDao.getToIntLocLevel3Code());
		valuesList.put("p_offender_id_display", objSearchDao.getOffenderIdDisplay());
		valuesList.put("p_event_type", objSearchDao.getEventType());
		valuesList.put("p_event_sub_type", objSearchDao.getEventSubType());
		valuesList.put("p_to_int_loc_id", objSearchDao.getToInternalLocationId());

		final RowMapper<VOffenderAllSchedules> VOffAllScheduleMapper = Row2BeanRowMapper.makeMapping(sql,
				VOffenderAllSchedules.class, mapping);
		List<VOffenderAllSchedules> returnList = new ArrayList<>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, valuesList, VOffAllScheduleMapper);
		} catch (Exception e) {
			logger.error("getSchedules :" + e);
		}
		return returnList;
	}

	@Override
	public List<VOffenderAllSchedules> getUnschedulesCur(final VOffenderAllSchedules objSearchDao) {
		final String sql = getQuery("GET_UNSCHEDULES_CUR");
		final Map<String, Object> valuesList = new HashMap<String, Object>();
		valuesList.put("p_agy_loc_id", objSearchDao.getAgyLocId());
		valuesList.put("p_living_unit_level_1", objSearchDao.getLuLevel1Code());
		valuesList.put("p_living_unit_level_2", objSearchDao.getLuLevel2Code());
		valuesList.put("p_living_unit_level_3", objSearchDao.getLuLevel3Code());
		valuesList.put("p_agency_iml_level_1", objSearchDao.getAgencyImlLevel1Code());
		valuesList.put("p_agency_iml_level_2", objSearchDao.getAgencyImlLevel2Code());
		valuesList.put("p_agency_iml_level_3", objSearchDao.getAgencyImlLevel3Code());
		valuesList.put("p_offender_id_display", objSearchDao.getOffenderIdDisplay());

		final RowMapper<VOffenderAllSchedules> VOffenderAllSchedulesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				VOffenderAllSchedules.class, mapping);
		List<VOffenderAllSchedules> returnList = new ArrayList<>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, valuesList, VOffenderAllSchedulesRowMapper);
		} catch (Exception e) {
			logger.error("getUnschedulesCur :" + e);
		}
		return returnList;
	}

}
