package net.syscon.s4.inst.schedules.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.LivingUnits;
import net.syscon.s4.inst.legalscreens.bean.VCourtEvents;
import net.syscon.s4.inst.schedules.OiicmociRepository;

/**
 * Class OiicmociRepositoryImpl
 */
@Repository
public class OiicmociRepositoryImpl extends RepositoryBase implements OiicmociRepository {

	private static Logger logger = LogManager.getLogger(OiicmociRepositoryImpl.class.getName());
	private final Map<String, FieldMapper> vCourtEventsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_ID_DISPLAY", new FieldMapper("offenderIdDisplay"))
			.put("CASE_INFO_PREFIX", new FieldMapper("caseInfoPrefix"))
			.put("LIVING_UNIT_ID", new FieldMapper("livingUnitId")).put("MIDDLE_NAME", new FieldMapper("middleName"))
			.put("BOOKING_ACTIVE_FLAG", new FieldMapper("bookingActiveFlag"))
			.put("CHECK_SUM", new FieldMapper("checkSum")).put("LAST_NAME", new FieldMapper("lastName"))
			.put("EVENT_DATE", new FieldMapper("eventDate")).put("LEVEL_4_CODE", new FieldMapper("level4Code"))
			.put("PARENT_EVENT_ID", new FieldMapper("parentEventId"))
			.put("EVENT_STATUS", new FieldMapper("eventStatus")).put("RESULT_CODE", new FieldMapper("resultCode"))
			.put("LEVEL_2_CODE", new FieldMapper("level2Code"))
			.put("COURT_EVENT_TYPE", new FieldMapper("courtEventType"))
			.put("OFFENDER_AGY_LOC_ID", new FieldMapper("offenderAgyLocId"))
			.put("JUDGE_NAME", new FieldMapper("judgeName")).put("START_TIME", new FieldMapper("startTime"))
			.put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId"))
			.put("LEVEL_1_CODE", new FieldMapper("level1Code")).put("COMMENT_TEXT", new FieldMapper("commentText"))
			.put("CASE_INFO_NUMBER", new FieldMapper("caseInfoNumber"))
			.put("EVENT_OUTCOME", new FieldMapper("eventOutcome")).put("CASE_ID", new FieldMapper("caseId"))
			.put("EVENT_ID", new FieldMapper("eventId"))
			.put("OFFENDER_AGY_LOC_DESC", new FieldMapper("offenderAgyLocDesc"))
			.put("AGY_LOC_ID_NAME", new FieldMapper("agyLocIdName"))
			.put("MOVEMENT_REASON_DESC", new FieldMapper("movementReasonDesc"))
			.put("EVENT_TYPE", new FieldMapper("eventType")).put("AGY_LOC_ID", new FieldMapper("agyLocId"))
			.put("MOVEMENT_REASON_CODE", new FieldMapper("movementReasonCode"))
			.put("LEVEL_3_CODE", new FieldMapper("level3Code"))
			.put("COURT_EVENT_TYPE_DESC", new FieldMapper("courtEventTypeDesc"))
			.put("END_TIME", new FieldMapper("endTime")).put("EVENT_CLASS", new FieldMapper("eventClass"))
			.put("BIRTH_DATE", new FieldMapper("birthDate")).put("FIRST_NAME", new FieldMapper("firstName"))
			.put("APPEARANCE_TYPE", 				new FieldMapper("appearanceType"))
            .put("APPEARANCE_LOCATION", 				new FieldMapper("appearanceLocation"))  
			.build();
	private final Map<String, FieldMapper> livingUnitsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("LIVING_UNIT_CODE", 	new FieldMapper("livingUnitCode"))
			.put("LIVING_UNIT_ID", 		new FieldMapper("livingUnitId"))
			.put("ACTIVE_FLAG", 		new FieldMapper("activeFlag")).build();
	private final Map<String, FieldMapper> agencyLocationsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", 	new FieldMapper("description"))
			.put("CODE", 			new FieldMapper("code"))
			.put("ACTIVE_FLAG", 	new FieldMapper("activeFlag")).build();

	/**
	 * Creates new OiicmociRepositoryImpl class Object
	 */
	public OiicmociRepositoryImpl() {
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao VCourtEvents
	 *
	 * @return List<VCourtEvents>
	 *
	 *
	 */
	public List<VCourtEvents> offSchExecuteQuery(VCourtEvents objSearchDao) {
		final String sql = queryBuilderFactory
				.getQueryBuilder(getQuery("OIICMOCI_OFFSCH_FIND_V_COURT_EVENTS"), vCourtEventsMapping).build();
		List<VCourtEvents> returnList = null;
		String preparedSql = null;
		StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource params = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" WHERE ");
			if (objSearchDao.getEventDate() != null) {
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				String eventDate = sdf.format(objSearchDao.getEventDate());
				sqlQuery.append(" to_char(EVENT_DATE, 'DD/MM/YYYY') = :eventDate AND ");
				params.addValue("eventDate", eventDate);
			}
			if (objSearchDao.getStartTime() != null) {
				sqlQuery.append(" To_CHAR(START_TIME,'HH24:MI') = :startTime AND ");
				SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
				String strTime = formatter.format(objSearchDao.getStartTime());
				params.addValue("startTime", strTime);
			}
			if (objSearchDao.getAgyLocIdName() != null && !objSearchDao.getAgyLocIdName().trim().equals("")) {
				sqlQuery.append(" OFFENDER_AGY_LOC_ID = :agyLocIdName AND ");
				params.addValue("agyLocIdName", objSearchDao.getAgyLocIdName());
			}
			if (objSearchDao.getLevel1Code() != null && !objSearchDao.getLevel1Code().trim().equals("")) {
				sqlQuery.append(
						" LEVEL_1_CODE = (SELECT LIVING_UNIT_CODE FROM LIVING_UNITS WHERE LIVING_UNIT_ID = (:level1Code::bigint) ) AND ");
				params.addValue("level1Code", objSearchDao.getLevel1Code());
			}
			if (objSearchDao.getLevel2Code() != null && !objSearchDao.getLevel2Code().trim().equals("")) {
				sqlQuery.append(
						" LEVEL_2_CODE = (SELECT LIVING_UNIT_CODE FROM LIVING_UNITS WHERE LIVING_UNIT_ID = (:level2Code::bigint) ) AND ");
				params.addValue("level2Code", objSearchDao.getLevel2Code());
			}
			if (objSearchDao.getLevel3Code() != null && !objSearchDao.getLevel3Code().trim().equals("")) {
				sqlQuery.append(
						" LEVEL_3_CODE = (SELECT LIVING_UNIT_CODE FROM LIVING_UNITS WHERE LIVING_UNIT_ID =  :level3Code::bigint ) AND ");
				params.addValue("level3Code", objSearchDao.getLevel3Code());
			}
			if (objSearchDao.getOffenderIdDisplay() != null && !objSearchDao.getOffenderIdDisplay().trim().equals("")) {
				sqlQuery.append("OFFENDER_ID_DISPLAY = :offenderIdDisplay");
				params.addValue("offenderIdDisplay", objSearchDao.getOffenderIdDisplay());
			}
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("WHERE")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("AND")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		sqlQuery.delete(0, sqlQuery.length());
		sqlQuery.append(preparedSql);
		sqlQuery.append(" order by START_TIME ,LAST_NAME,FIRST_NAME ");
		preparedSql = sqlQuery.toString().trim();

		final RowMapper<VCourtEvents> VCourtEventsRowMapper = Row2BeanRowMapper.makeMapping(preparedSql,
				VCourtEvents.class, vCourtEventsMapping);
		returnList = namedParameterJdbcTemplate.query(preparedSql, params, VCourtEventsRowMapper);
		return returnList;
	}

	/**
	 * @param
	 *
	 * 
	 *
	 */
	public int PRE_INSERT() {
		return 0;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<AgencyLocations>
	 */
	public List<AgencyLocations> rgAgyLocIdRecordGroup(String caseLoadId) {
		final String sql = getQuery("OIICMOCI_FIND_RGAGYLOCID");
		final RowMapper<AgencyLocations> agencyLocationsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				AgencyLocations.class, agencyLocationsMapping);
		List<AgencyLocations> returnList = null;
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("CASELOADID", caseLoadId),
					agencyLocationsRowMapper);
		} catch (Exception e) {
			logger.error("rgAgyLocIdRecordGroup", e);
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<LivingUnits>
	 */
	public List<LivingUnits> rgLu1RecordGroup(final String agyLocId) {
		final String sql = getQuery("OIICMOCI_FIND_RGLU1");
		final RowMapper<LivingUnits> livingUnitsRowMapper = Row2BeanRowMapper.makeMapping(sql, LivingUnits.class,
				livingUnitsMapping);
		List<LivingUnits> returnList = null;
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("AGYLOCID", agyLocId), livingUnitsRowMapper);
		} catch (Exception e) {
			logger.error("rgLu1RecordGroup", e);
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<LivingUnits>
	 */
	public List<LivingUnits> rgLu2RecordGroup(final String agyLocId, final Integer livingUnit) {
		final String sql = getQuery("OIICMOCI_FIND_RGLU2");
		final RowMapper<LivingUnits> livingUnitsRowMapper = Row2BeanRowMapper.makeMapping(sql, LivingUnits.class,
				livingUnitsMapping);
		List<LivingUnits> returnList = null;
		try {
			return namedParameterJdbcTemplate.query(sql,
					createParams("AGYLOCID", agyLocId, "NBTLIVINGUNITID1", livingUnit), livingUnitsRowMapper);
		} catch (Exception e) {
			logger.error("rgLu2RecordGroup", e);
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<LivingUnits>
	 */
	public List<LivingUnits> rgLu3RecordGroup(final String agyLocId, final Integer livingUnit) {
		final String sql = getQuery("OIICMOCI_FIND_RGLU3");
		final RowMapper<LivingUnits> livingUnitsRowMapper = Row2BeanRowMapper.makeMapping(sql, LivingUnits.class,
				livingUnitsMapping);
		List<LivingUnits> returnList = new ArrayList<LivingUnits>();
		try {
			return namedParameterJdbcTemplate.query(sql,
					createParams("AGYLOCID", agyLocId, "NBTLIVINGUNITID2", livingUnit), livingUnitsRowMapper);
		} catch (Exception e) {
			logger.error("rgLu3RecordGroup", e);
		}
		return returnList;
	}

}
