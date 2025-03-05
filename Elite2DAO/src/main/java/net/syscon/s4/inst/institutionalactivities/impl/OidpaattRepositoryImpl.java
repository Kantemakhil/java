package net.syscon.s4.inst.institutionalactivities.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.VOffenderCourseAttendances;
import net.syscon.s4.im.beans.VPrisonActivities;
import net.syscon.s4.inst.institutionalactivities.OidpaattRepository;

/**
 * Class OidpaattRepositoryImpl
 * 
 */
@Repository
public class OidpaattRepositoryImpl extends RepositoryBase implements OidpaattRepository {

	private final Map<String, FieldMapper> agencyInternalLocationsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("NBT_CRS_SCH_ID", new FieldMapper("nbtCrsSchId"))
			.put("START_TIME", new FieldMapper("startTime"))
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("NBT_INTERNAL_LOCATION_ID", new FieldMapper("nbtInternalLocationId"))
			.put("NBT_EVENT_DATE", new FieldMapper("nbtEventDate"))
			.put("END_TIME", new FieldMapper("endTime"))
			.put("NBT_CRS_ACTY_ID", new FieldMapper("nbtCrsActyId")).build();
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("LIST_SEQ", new FieldMapper("listSeq")).put("SERVICE", new FieldMapper("service"))
			.put("PROGRAM_ID", new FieldMapper("programId")).put("DESCRIPTION", new FieldMapper("description"))
			.put("COE", new FieldMapper("code")).put("CASELOAD_ID", new FieldMapper("caseloadId"))
			.put("AGY_LOC_ID", new FieldMapper("agyLocId")).build();
	private final Map<String, FieldMapper> courseSchedulesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("NBT_CRS_SCH_ID", new FieldMapper("nbtCrsSchId")).put("START_TIME", new FieldMapper(" startTime"))
			.put("DESCRIPTION", new FieldMapper(" description "))
			.put("NBT_INTERNAL_LOCATION_ID", new FieldMapper("nbtInternalLocationId"))
			.put("NBT_EVENT_DATE", new FieldMapper("nbtEventDate")).put("END_TIME", new FieldMapper(" endTime "))
			.put("NBT_CRS_ACTY_ID", new FieldMapper("nbtCrsActyId")).build();
	private final Map<String, FieldMapper> agencyLocationsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("AGY_LOC_ID", new FieldMapper("agyLocId")).put("MODE", new FieldMapper("mode"))
			.put("COUNT", new FieldMapper("count")).put("CASELOAD_ID", new FieldMapper("caseloadId"))
			.put("DESCRIPTION", new FieldMapper("description")).build();
	private final Map<String, FieldMapper> vOffenderCourseAttendancesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("AGY_LOC_ID", new FieldMapper("agyLocId")).put("ACTION_CODE", new FieldMapper("actionCode"))
			.put("PAYFLAG", new FieldMapper("payFlag"))
			.put("PAY_BATCH_ID", new FieldMapper("payBatchId"))
			.build();

	/**
	 * Creates new OidpaattRepositoryImpl class Object
	 */
	public OidpaattRepositoryImpl() {
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            VOffenderCourseAttendances
	 *
	 * @return List<VOffenderCourseAttendances>
	 *
	 */
	public List<VOffenderCourseAttendances> vActAttExecuteQuery(final VOffenderCourseAttendances objSearchDao) {
		final String sql = getQuery("OIDPAATT_VACTATT_FIND_V_OFFENDER_COURSE_ATTENDANCES");
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		String preSqlQuery = null;
		final MapSqlParameterSource params = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" V.EVENT_TYPE = 'INST_ACT' " + " AND ");
			if (objSearchDao.getEventDate() != null) {
				sqlQuery.append(" V.EVENT_DATE  = TO_DATE(:eventDate ::text,'yyyy/mm/dd') " + " and");
				params.addValue("eventDate", new SimpleDateFormat("yyyy/MM/dd").format(objSearchDao.getEventDate()));
			}
			if (objSearchDao.getProgramId() != null) {
				sqlQuery.append(" V.PROGRAM_ID  = :programId " + " AND ");
				params.addValue("programId", objSearchDao.getProgramId());
			}
			if (objSearchDao.getToInternalLocationDesc() != null) {
				sqlQuery.append(" tag_int_loc_int_loc_desc(v.to_internal_location_id)  = :toInternalLocationDesc " + " AND ");
				params.addValue("toInternalLocationDesc", objSearchDao.getToInternalLocationDesc());
			}
			if (objSearchDao.getCrsActyId() != null) {
				sqlQuery.append(" V.CRS_ACTY_ID  = :crsActyId " + " AND ");
				params.addValue("crsActyId", objSearchDao.getCrsActyId());
			}
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("WHERE")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("AND")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		preSqlQuery = preparedSql
				.concat(" ORDER BY CASE V.EVENT_OUTCOME WHEN NULL THEN 9 ELSE 0 END DESC, OFFENDER_NAME ASC ");
		final RowMapper<VOffenderCourseAttendances> VOffenderCourseAttendancesRowMapper = Row2BeanRowMapper
				.makeMapping(preSqlQuery, VOffenderCourseAttendances.class, vOffenderCourseAttendancesMapping);
		List<VOffenderCourseAttendances> returnList = new ArrayList<VOffenderCourseAttendances>();
		returnList = namedParameterJdbcTemplate.query(preSqlQuery, params, VOffenderCourseAttendancesRowMapper);
		return returnList;
	}

	public Date trunc(final Date date) {
		if (date != null) {
			final Calendar calender = Calendar.getInstance();
			calender.setTime(date);
			calender.set(Calendar.HOUR, 0);
			calender.set(Calendar.MINUTE, 0);
			calender.set(Calendar.SECOND, 0);
			calender.set(Calendar.MILLISECOND, 0);
			final Date returnDate = calender.getTime();
			return returnDate;
		}
		return null;
	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstVOffenderCourseAttendances
	 *            List<VOffenderCourseAttendances>
	 *
	 */
	public Integer vActAttUpdateVOffenderCourseAttendances(
			final List<VOffenderCourseAttendances> lstVOffenderCourseAttendances) {
		String sql = getQuery("OIDPAATT_VACTATT_UPDATE_V_OFFENDER_COURSE_ATTENDANCES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (VOffenderCourseAttendances vOffenderCourseAttendances : lstVOffenderCourseAttendances) {
			parameters.add(new BeanPropertySqlParameterSource(vOffenderCourseAttendances));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstVOffenderCourseAttendances.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgPsActPerfRecordGroup() {
		final String sql = getQuery("OIDPAATT_FIND_RGPSACTPERF");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgOutcomesRecordGroup() {
		final String sql = getQuery("OIDPAATT_FIND_RGOUTCOMES");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<VPrisonActivities> rgServicesRecordGroup(final String agyLocId) {
		final String sql = getQuery("OIDPAATT_FIND_RGSERVICES");
		final RowMapper<VPrisonActivities> mRowMapper = Row2BeanRowMapper.makeMapping(sql, VPrisonActivities.class,
				mMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("agyLocId", agyLocId), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<AgencyLocations> rgAgyLocRecordGroup(final String caseloadId) {
		final String sql = getQuery("OIDPAATT_FIND_RGAGYLOC");
		final RowMapper<AgencyLocations> mRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				mMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("caseloadId", caseloadId), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * defaultAgency
	 *
	 * @param params
	 *
	 */
	public List<AgencyLocations> defaultAgency(AgencyLocations paramBean) {
		final String sql = getQuery("OIDPAATT_DEFAULT_AGENCY");
		final RowMapper<AgencyLocations> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				agencyLocationsMapping);
		final ArrayList<AgencyLocations> returnList = (ArrayList<AgencyLocations>) namedParameterJdbcTemplate.query(sql,
				createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * defaultAgency
	 *
	 * @param params
	 *
	 */
	public List<AgencyLocations> defaultAgencyOne(final AgencyLocations paramBean) {
		final String sql = getQuery("OIDPAATT_DEFAULT_AGENCY_ONE");
		final RowMapper<AgencyLocations> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				agencyLocationsMapping);
		final ArrayList<AgencyLocations> returnList = (ArrayList<AgencyLocations>) namedParameterJdbcTemplate.query(sql,
				createParams(), columnRowMapper);
		return returnList;
	}

	public String getDefAttOutcome() {
		final String sql = getQuery("OIDPAATT_DEFAULT_ATTENDANCE_OUTCOME");
		String returnList = null;
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);
		} catch(Exception e) {
			return returnList;
		}
		return returnList;
	}
	
	public String getDefAttPerformance() {
		final String sql = getQuery("OIDPAATT_DEFAULT_ATTENDANCE_PERFORMANCE");
		String returnList = null;
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);
		} catch(Exception e) {
			return returnList;
		}
		return returnList;
	}
	
	public Integer insertCrsAttendance(final List<VOffenderCourseAttendances> lstVOffenderCourseAttendances) {
		String sql = getQuery("OIDPAATT_INSERT_OFFENDER_COURSE_ATTENDANCES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (VOffenderCourseAttendances vOffenderCourseAttendances : lstVOffenderCourseAttendances) {
			parameters.add(new BeanPropertySqlParameterSource(vOffenderCourseAttendances));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstVOffenderCourseAttendances.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	
}
