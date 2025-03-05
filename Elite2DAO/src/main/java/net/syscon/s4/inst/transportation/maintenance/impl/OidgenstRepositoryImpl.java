package net.syscon.s4.inst.transportation.maintenance.impl;

import java.sql.Date;
import java.sql.SQLException;
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
import net.syscon.s4.inst.transportation.maintenance.OidgenstRepository;
import net.syscon.s4.inst.transportation.maintenance.beans.ScheduledTripParameters;
import net.syscon.s4.inst.transportation.maintenance.beans.ScheduledTrips;

@Repository
public class OidgenstRepositoryImpl extends RepositoryBase implements OidgenstRepository {

	private static Logger logger = LogManager.getLogger(OidgenstRepositoryImpl.class.getName());

	/**
	 * Creates new OidgenstRepositoryImpl class Object
	 */
	public OidgenstRepositoryImpl() {
	}

	private final Map<String, FieldMapper> mapper = new ImmutableMap.Builder<String, FieldMapper>()
			.put("code", new FieldMapper("code")).put("vMdate", new FieldMapper("vmdate")).build();

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao ScheduledTripParameters
	 *
	 * @return List<ScheduledTripParameters>
	 *
	 * @throws SQLException
	 */
	public List<ScheduledTripParameters> schPlannerExecuteQuery(ScheduledTripParameters objSearchDao) {
		final String sql = getQuery("OIDGENST_SCHPLANNER_FIND_SCHEDULED_TRIP_PARAMETERS");
		List<ScheduledTripParameters> list = new ArrayList<>();
		final RowMapper<ScheduledTripParameters> mRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ScheduledTripParameters.class, mapper);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("tripCode", objSearchDao.getTripCode()),
					mRowMapper);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " schPlannerExecuteQuery in error ", e);
		}
		return list;
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstScheduledTripParameters List<ScheduledTripParameters>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public Integer schPlannerInsertScheduledTripParameters(
			final List<ScheduledTripParameters> lstScheduledTripParameters) {
		int insertCount = 0;
		String sql = getQuery("OIDGENST_SCHPLANNER_INSERT_SCHEDULED_TRIP_PARAMETERS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (ScheduledTripParameters scheduledTripParameters : lstScheduledTripParameters) {
			parameters.add(new BeanPropertySqlParameterSource(scheduledTripParameters));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " schPlannerInsertScheduledTripParameters in error ");
		}
		if (lstScheduledTripParameters.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstScheduledTripParameters List<ScheduledTripParameters>
	 *
	 * @throws SQLException
	 */
	public Integer schPlannerUpdateScheduledTripParameters(
			final List<ScheduledTripParameters> lstScheduledTripParameters) {
		int insertCount = 0;
		String sql = getQuery("OIDGENST_SCHPLANNER_UPDATE_SCHEDULED_TRIP_PARAMETERS");
		int returnValue = 0;
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (ScheduledTripParameters scheduledTripParameters : lstScheduledTripParameters) {
			parameters.add(new BeanPropertySqlParameterSource(scheduledTripParameters));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstScheduledTripParameters.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao ScheduledTrips
	 *
	 * @return List<ScheduledTrips>
	 *
	 * @throws SQLException
	 */
	public List<ScheduledTrips> scheduledTripsExecuteQuery(ScheduledTrips objSearchDao) {
		final String sql = getQuery("OIDGENST_SCHEDULEDTRIPS_FIND_SCHEDULED_TRIPS");
		List<ScheduledTrips> list = new ArrayList<>();
		final RowMapper<ScheduledTrips> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ScheduledTrips.class, mapper);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("TRIP_CODE", objSearchDao.getTripCode()),
					mRowMapper);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " scheduledTripsExecuteQuery in error ", e);
		}
		return list;
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstScheduledTrips List<ScheduledTrips>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public Integer scheduledTripsInsertScheduledTrips(final List<ScheduledTrips> lstScheduledTrips) {
		int insertCount = 0;
		String sql = getQuery("OIDGENST_SCHEDULEDTRIPS_INSERT_SCHEDULED_TRIPS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (ScheduledTrips scheduledTrips : lstScheduledTrips) {
			parameters.add(new BeanPropertySqlParameterSource(scheduledTrips));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " scheduledTripsInsertScheduledTrips in error ");
		}
		if (lstScheduledTrips.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstScheduledTrips List<ScheduledTrips>
	 *
	 * @throws SQLException
	 */
	public Integer scheduledTripsUpdateScheduledTrips(final List<ScheduledTrips> lstScheduledTrips) {
		int insertCount = 0;
		String sql = getQuery("OIDGENST_SCHEDULEDTRIPS_UPDATE_SCHEDULED_TRIPS");
		int returnValue = 0;
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (ScheduledTrips scheduledTrips : lstScheduledTrips) {
			parameters.add(new BeanPropertySqlParameterSource(scheduledTrips));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " scheduledTripsUpdateScheduledTrips in error ");
		}
		if (lstScheduledTrips.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstScheduledTrips List<ScheduledTrips>
	 *
	 * @throws SQLException
	 */
	public Integer scheduledTripsDeleteScheduledTrips(final List<ScheduledTrips> lstScheduledTrips) {
		int deleteCount = 0;
		String sql = getQuery("OIDGENST_SCHEDULEDTRIPS_DELETE_SCHEDULED_TRIPS");
		int returnValue = 0;
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (ScheduledTrips scheduledTrips : lstScheduledTrips) {
			parameters.add(new BeanPropertySqlParameterSource(scheduledTrips));
		}
		try {
			String tableName = "SCHEDULED_TRIPS";
			String whereCondition = "scheduled_trip_id = :scheduledTripId";
			batchUpdatePreDeletedRows(tableName, whereCondition, parameters);
		} catch (Exception e) {
			logger.error(e);
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " scheduledTripsDeleteScheduledTrips in error ");
		}
		if (lstScheduledTrips.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<Routes>
	 */
	@Override
	public List<ReferenceCodes> rgRouteRecordGroup() {
		final String sql = getQuery("OIDGENST_FIND_RGROUTE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mapper);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (Exception e) {
			return Collections.emptyList();
		}
	}

	@Override
	public Integer scheduledTripsvalidate(ScheduledTrips bean) {
		final String sql = getQuery("OIDGENST_SCHEDULEDTRIPS_IF_EXIST_CUR");
		Integer count = null;
		try {
			count = namedParameterJdbcTemplate.queryForObject(sql, createParams("trip_code", bean.getTripCode(),
					"start_date", bean.getStartDate(), "end_date", bean.getEndDate()), Integer.class);
		} catch (Exception e) {
			return 0;
		}
		return count;
	}

	@Override
	public Integer scheduledGenerateCount(ScheduledTrips bean) {
		final String sql = getQuery("OIDGENST_SCHEDULED_TRIPS_IF_EXIST_CUR");
		Integer count = null;
		try {
			count = namedParameterJdbcTemplate.queryForObject(sql, createParams("trip_code", bean.getTripCode(),
					"start_date", bean.getStartDate(), "end_date", bean.getEndDate()), Integer.class);
		} catch (Exception e) {
			return 0;
		}
		return count;
	}

	@Override
	public Date scheduledGenerateDate(ScheduledTrips bean) {
		final String sql = getQuery("OIDGENST_SCHEDULED_TRIPS_MIN_SDATE_CUR");
		Date Dep = null;
		try {
			Dep = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("trip_code", bean.getTripCode()), Date.class);
		} catch (Exception e) {
			return Dep;
		}
		return Dep;
	}

	@Override
	public Integer ifExistCur(ScheduledTrips bean) {
		final String sql = getQuery("OIDGENST_IF_EXIST_CUR");
		Integer count = null;
		try {
			count = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("trip_code", bean.getTripCode(), "departure_date", bean.getDepartureDate()),
					Integer.class);
		} catch (Exception e) {
			return 0;
		}
		return count;
	}

	@Override
	public Integer oidgenst_if_sch_cur(ScheduledTrips bean) {
		final String sql = getQuery("OIDGENST_IF_SCH_CUR");
		Integer count = null;
		try {
			count = namedParameterJdbcTemplate.queryForObject(sql, createParams("start_date", bean.getStartDate(),
					"end_date", bean.getEndDate(), "trip_code", bean.getTripCode()), Integer.class);
		} catch (Exception e) {
			return 0;
		}
		return count;
	}

	@Override
	public Integer oidgenstUpdateTrips(ScheduledTrips bean) {
		final String sql = getQuery("OIDGENST_UPDATE_TRIPS");
		Integer count = null;
		try {
			count = namedParameterJdbcTemplate.queryForObject(sql, createParams("start_date", bean.getStartDate(),
					"end_date", bean.getEndDate(), "trip_code", bean.getTripCode()), Integer.class);
		} catch (Exception e) {
			return 0;
		}
		return count;
	}

	@Override
	public Long getSchedSeq() {
		final String sql = getQuery("OIDGENST_GETSCHEDSEQ");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Long.class);
		} catch (Exception e) {
			return 0l;
		}
	}

	@Override
	public String getWeekDay(ScheduledTrips bean) {
		final String sql = getQuery("OIDGENST_GETWEEKDAY");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql,
					createParams("departure_date", bean.getDepartureDate()), String.class);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public String getEndDay(ScheduledTrips bean) {
		final String sql = getQuery("OIDGENST_GETENDDAY");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql,
					createParams("completion_date", bean.getCompletionDate()), String.class);
		} catch (Exception e) {
			return null;
		}

	}

	@Override
	public Date getMaxDate(ScheduledTrips bean) {
		final String sql = getQuery("OIDGENST_MAX_TIME");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("trip_code", bean.getTripCode()),
					Date.class);
		} catch (Exception e) {
			return null;
		}
	}
}
