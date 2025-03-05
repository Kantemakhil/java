package net.syscon.s4.inst.transportation.maintenance.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.beans.OffenderExternalMovement;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.inst.transportation.maintenance.OimstripRepository;
import net.syscon.s4.inst.transportation.maintenance.beans.ScheduledTripAssignments;
import net.syscon.s4.inst.transportation.maintenance.beans.ScheduledTrips;
import net.syscon.s4.inst.transportation.maintenance.beans.Trips;

/**
 * Class OimstripRepositoryImpl
 * 
 * @author Vrnda Software Technologies
 * @version 1.0
 */
@Repository
public class OimstripRepositoryImpl extends RepositoryBase implements OimstripRepository {

	private static Logger logger = LogManager.getLogger(OimstripRepositoryImpl.class);

	/**
	 * Creates new OimstripRepositoryImpl class Object
	 */
	public OimstripRepositoryImpl() {
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao Trips
	 *
	 * @return List<Trips>
	 *
	 * @throws SQLException
	 */
	public List<Trips> tripsExecuteQuery(Trips objSearchDao) {
		final String sql = getQuery("OIMSTRIP_TRIPS_FIND_TRIPS");
		final ArrayList<Trips> returnList = (ArrayList<Trips>) namedParameterJdbcTemplate.query(sql, createParams(),
				new BeanPropertyRowMapper<Trips>(Trips.class));
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstTrips List<Trips>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public Integer tripsInsertTrips(final List<Trips> lstTrips) {
		int insertCount = 0;
		String sql = getQuery("OIMSTRIP_TRIPS_INSERT_TRIPS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (Trips trips : lstTrips) {
			parameters.add(new BeanPropertySqlParameterSource(trips));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstTrips.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstTrips List<Trips>
	 *
	 * @throws SQLException
	 */
	public Integer tripsCommit(final List<Trips> lstTrips) {
		int insertCount = 0;
		String sql = getQuery("OIMSTRIP_TRIPS_UPDATE_TRIPS");
		int returnValue = 0;
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (Trips trips : lstTrips) {
			parameters.add(new BeanPropertySqlParameterSource(trips));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstTrips.size() == returnArray.length) {
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
		final String sql = getQuery("OIMSTRIP_SCHEDULEDTRIPS_FIND_SCHEDULED_TRIPS_ONE");
		List<ScheduledTrips> returnList = new ArrayList<>();
		try {

			returnList = (ArrayList<ScheduledTrips>) namedParameterJdbcTemplate.query(sql,
					createParams("tripCode", objSearchDao.getTripCode()),
					new BeanPropertyRowMapper<ScheduledTrips>(ScheduledTrips.class));
		} catch (Exception e) {
			logger.error("retrieve list of trips data: ", e);
		}
		return returnList;
	}

	@Override
	public List<ScheduledTrips> scheduledTripsExecuteQueryExtra(Long scheduledTripId) {
		final String sql = getQuery("SCHEDULED_TRIPS_EXECUTEQUERY_EXTRA");
		List<ScheduledTrips> returnList = new ArrayList<>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("scheduledTripId", scheduledTripId),
					new BeanPropertyRowMapper<ScheduledTrips>(ScheduledTrips.class));
		} catch (Exception e) {
			logger.error("retrieve list of trips data: ", e);
		}
		return returnList;
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
		String sql = getQuery("OIMSTRIP_SCHEDULEDTRIPS_UPDATE_SCHEDULED_TRIPS");
		int returnValue = 0;
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (ScheduledTrips scheduledTrips : lstScheduledTrips) {
			parameters.add(new BeanPropertySqlParameterSource(scheduledTrips));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstScheduledTrips.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao ScheduledTripAssignments
	 *
	 * @return List<ScheduledTripAssignments>
	 *
	 * @throws SQLException
	 */
	public List<ScheduledTripAssignments> scheduledTripAssignmentsExecuteQuery(ScheduledTripAssignments objSearchDao) {
		final String sql = getQuery("OIMSTRIP_SCHEDULEDTRIPASSIGNMENTS_FIND_SCHEDULED_TRIP_ASSIGNMENTS");
		final ArrayList<ScheduledTripAssignments> returnList = (ArrayList<ScheduledTripAssignments>) namedParameterJdbcTemplate
				.query(sql, createParams("scheduledTripId", objSearchDao.getScheduledTripId()),
						new BeanPropertyRowMapper<ScheduledTripAssignments>(ScheduledTripAssignments.class));
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstScheduledTripAssignments List<ScheduledTripAssignments>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public Integer scheduledTripAssignmentsInsertScheduledTripAssignments(
			final List<ScheduledTripAssignments> lstScheduledTripAssignments) {
		int insertCount = 0;
		String sql = getQuery("OIMSTRIP_SCHEDULEDTRIPASSIGNMENTS_INSERT_SCHEDULED_TRIP_ASSIGNMENTS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (ScheduledTripAssignments scheduledTripAssignments : lstScheduledTripAssignments) {
			parameters.add(new BeanPropertySqlParameterSource(scheduledTripAssignments));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstScheduledTripAssignments.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstScheduledTripAssignments List<ScheduledTripAssignments>
	 *
	 * @throws SQLException
	 */
	public Integer scheduledTripAssignmentsDeleteScheduledTripAssignments(
			final List<ScheduledTripAssignments> lstScheduledTripAssignments) {
		int deleteCount = 0;
		String sql = getQuery("OIMSTRIP_SCHEDULEDTRIPASSIGNMENTS_DELETE_SCHEDULED_TRIP_ASSIGNMENTS");
		int returnValue = 0;
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (ScheduledTripAssignments scheduledTripAssignments : lstScheduledTripAssignments) {
			parameters.add(new BeanPropertySqlParameterSource(scheduledTripAssignments));
		}
		try {
			String tableName = "SCHEDULED_TRIP_ASSIGNMENTS";
			String whereCondition = "assigned_id=:assignedId and scheduled_trip_id=:scheduledTripId and assignment_type='VEHICLE'";
			batchUpdatePreDeletedRows(tableName, whereCondition, parameters);
		} catch (Exception e) {
			logger.error(e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstScheduledTripAssignments.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao ScheduledTripAssignments
	 *
	 * @return List<ScheduledTripAssignments>
	 *
	 * @throws SQLException
	 */
	public List<ScheduledTripAssignments> staffAssignmentExecuteQuery(ScheduledTripAssignments objSearchDao) {
		final String sql = getQuery("OIMSTRIP_STAFFASSIGNMENT_FIND_SCHEDULED_TRIP_ASSIGNMENTS");
		final ArrayList<ScheduledTripAssignments> returnList = (ArrayList<ScheduledTripAssignments>) namedParameterJdbcTemplate
				.query(sql, createParams("scheduledTripId", objSearchDao.getScheduledTripId()),
						new BeanPropertyRowMapper<ScheduledTripAssignments>(ScheduledTripAssignments.class));
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstScheduledTripAssignments List<ScheduledTripAssignments>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public Integer staffAssignmentInsertScheduledTripAssignments(
			final List<ScheduledTripAssignments> lstScheduledTripAssignments) {
		int insertCount = 0;
		String sql = getQuery("OIMSTRIP_STAFFASSIGNMENT_INSERT_SCHEDULED_TRIP_ASSIGNMENTS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (ScheduledTripAssignments scheduledTripAssignments : lstScheduledTripAssignments) {
			parameters.add(new BeanPropertySqlParameterSource(scheduledTripAssignments));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstScheduledTripAssignments.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstScheduledTripAssignments List<ScheduledTripAssignments>
	 *
	 * @throws SQLException
	 */
	public Integer staffAssignmentUpdateScheduledTripAssignments(
			final List<ScheduledTripAssignments> lstScheduledTripAssignments) {
		int insertCount = 0;
		String sql = getQuery("OIMSTRIP_STAFFASSIGNMENT_UPDATE_SCHEDULED_TRIP_ASSIGNMENTS");
		int returnValue = 0;
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (ScheduledTripAssignments scheduledTripAssignments : lstScheduledTripAssignments) {
			parameters.add(new BeanPropertySqlParameterSource(scheduledTripAssignments));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstScheduledTripAssignments.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstScheduledTripAssignments List<ScheduledTripAssignments>
	 *
	 * @throws SQLException
	 */
	public Integer staffAssignmentDeleteScheduledTripAssignments(
			final List<ScheduledTripAssignments> lstScheduledTripAssignments) {
		int deleteCount = 0;
		String sql = getQuery("OIMSTRIP_STAFFASSIGNMENT_DELETE_SCHEDULED_TRIP_ASSIGNMENTS");
		int returnValue = 0;
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (ScheduledTripAssignments scheduledTripAssignments : lstScheduledTripAssignments) {
			parameters.add(new BeanPropertySqlParameterSource(scheduledTripAssignments));
		}
		try {
			String tableName = "SCHEDULED_TRIP_ASSIGNMENTS";
			String whereCondition = "assigned_id=:assignedId and scheduled_trip_id=:scheduledTripId and assignment_type='STAFF'";
			batchUpdatePreDeletedRows(tableName, whereCondition, parameters);
		} catch (Exception e) {
			logger.error(e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstScheduledTripAssignments.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgTripTypeRecordGroup() {
		final String sql = getQuery("OIMSTRIP_FIND_RGTRIPTYPE");

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(),
					new BeanPropertyRowMapper<ReferenceCodes>(ReferenceCodes.class));
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<StaffMembers>
	 */
	public List<StaffMembers> rgStaffIdRecordGroup() {
		final String sql = getQuery("OIMSTRIP_FIND_RGSTAFFID");

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(),
					new BeanPropertyRowMapper<StaffMembers>(StaffMembers.class));
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	@Override
	public Integer tagtransportCTrip(String tripCode) {
		final String sql = getQuery("TAGTRANSPORT_C_TRIP");
		Integer tripCodeVal = 0;
		try {
			tripCodeVal = namedParameterJdbcTemplate.queryForObject(sql, createParams("tripCode", tripCode),
					Integer.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " tagtransportCTrip in error ");
		}
		return tripCodeVal;
	}

	@Override
	public List<ScheduledTrips> scheduleGenerateOidgenst(String tripCode) {
		final String sql = getQuery("SCHEDULE_GENERATE_OIDGENST");
		List<ScheduledTrips> searchResult = new ArrayList<>();
		try {
			searchResult = namedParameterJdbcTemplate.query(sql, createParams("tripCode", tripCode),
					new BeanPropertyRowMapper<ScheduledTrips>(ScheduledTrips.class));
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " scheduleGenerateOidgenst in error ");
		}
		return searchResult;
	}

	@Override
	public List<OffenderExternalMovement> offBkgCur(Long sacheduledTripId) {
		final String sql = getQuery("OFF_BKG_CUR");
		List<OffenderExternalMovement> res = new ArrayList<>();
		try {
			res = namedParameterJdbcTemplate.query(sql, createParams("sacheduledTripId", sacheduledTripId),
					new BeanPropertyRowMapper<OffenderExternalMovement>(OffenderExternalMovement.class));

		} catch (Exception e) {
			logger.error(this.getClass().getName() + "offBkgCur  in error ");
		}
		return res;
	}

	@Override
	public List<BigDecimal> offNadmtCur(Long sacheduledTripId) {
		final String sql = getQuery("OFF_NADMT_CUR");
		List<BigDecimal> res = new ArrayList<>();
		try {
//			res = namedParameterJdbcTemplate.query(sql, createParams("sacheduledTripId", sacheduledTripId),
//					BigDecimal.class);
			res=namedParameterJdbcTemplate.queryForList(sql, createParams("sacheduledTripId", sacheduledTripId),BigDecimal.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + "offNadmtCur  in error ");
		}
		return res;
	}

	@Override
	public List<ScheduledTrips> schidCur(String tripCode, Date departureDate) {
		final String sql = getQuery("SCHID_CUR");
		List<ScheduledTrips> res = new ArrayList<>();
		try {
			res = namedParameterJdbcTemplate.query(sql,
					createParams("tripCode", tripCode, "departureDate", departureDate),
					new BeanPropertyRowMapper<ScheduledTrips>(ScheduledTrips.class));
		} catch (Exception e) {
			logger.error(this.getClass().getName() + "schidCur  in error ");
		}
		return res;
	}

	@Override
	public Long ifOffOnTrip(String tripCode, Date departureDate) {
		final String sql = getQuery("IF_OFF_ON_TRIP");
		Long res = null;
		try {
			res = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("tripCode", tripCode, "departureDate", departureDate), Long.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + "ifOffOnTrip  in error ");
		}
		return res;
	}

	@Override
	public Long ifNadtOnTrip(String tripCode, Date departureDate) {
		final String sql = getQuery("IF_NADT_ON_TRIP");
		Long res = null;
		try {
			res = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("tripCode", tripCode, "departureDate", departureDate), Long.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + "ifNadtOnTrip  in error ");
		}
		return res;
	}

	@Override
	public List<BigDecimal> getSchIdCur(String tripCode, Date departureDate) {
		final String sql = getQuery("GET_SCH_ID_CUR");
		List<BigDecimal> res = new ArrayList<>();
		try {
			res = namedParameterJdbcTemplate.query(sql,
					createParams("tripCode", tripCode, "departureDate", departureDate),
					new BeanPropertyRowMapper<BigDecimal>(BigDecimal.class));
		} catch (Exception e) {
			logger.error(this.getClass().getName() + "getSchIdCur  in error ");
		}
		return res;
	}

	@Override
	public List<BigDecimal> getEventIdCur(Long pSchId) {
		final String sql = getQuery("GET_EVENT_ID_CUR");
		List<BigDecimal> res = new ArrayList<>();
		try {
			res = namedParameterJdbcTemplate.query(sql, createParams("pSchId", pSchId),
					new BeanPropertyRowMapper<BigDecimal>(BigDecimal.class));
		} catch (Exception e) {
			logger.error(this.getClass().getName() + "getSchIdCur  in error ");
		}
		return res;
	}

	@Override
	public Integer vOffenderAllSchedules2(Long vEventId) {
		final String sql = getQuery("V_OFFENDER_ALL_SCHEDULES_2");
		int res = 0;
		try {
			res = namedParameterJdbcTemplate.queryForObject(sql, createParams("vEventId", vEventId), int.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + "vOffenderAllSchedules2  in error ");
		}
		return res;
	}

	@Override
	public Integer nonAdmittedInmateMvmts(Long pSchId) {
		final String sql = getQuery("NON_ADMITTED_INMATE_MVMTS");
		int res = 0;
		try {
			res = namedParameterJdbcTemplate.queryForObject(sql, createParams("pSchId", pSchId), int.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + "nonAdmittedInmateMvmts  in error ");
		}
		return res;
	}

	@Override
	public Integer tripsOidgenstInsert(Trips tripsModel) {
		final String sql = getQuery("TRIPS_OIDGENST_INSERT");
		Integer res = 0;
		try {
			res = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("tripCode", tripsModel.getTripCode(), "description", tripsModel.getDescription(),
							"listSeq", tripsModel.getListSeq(), "activeFlag", tripsModel.getActiveFlag(), "expiryDate",
							tripsModel.getExpiryDate(), "startDate", tripsModel.getvSdate(), "endDate",
							tripsModel.getvEdate(), "tripType", tripsModel.getTripType(), "createUserId",
							tripsModel.getCreateUserId()),
					Integer.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + "tripsOidgenstInsert  in error ");
		}
		return res;
	}

	@Override
	public int ifAssignedCur(Long assignedId, Date departureDate, Date completionDate) {
		final String sql = getQuery("IF_ASSIGNED_CUR");
		int vIfAssigne = 0;
		try {
			vIfAssigne = namedParameterJdbcTemplate.queryForObject(sql, createParams("assignedId", assignedId,
					"departureDate", departureDate, "completionDate", completionDate), int.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + "ifAssignedCur  in error ");
		}
		return vIfAssigne;
	}

	@Override
	public void triptableupdate(String tripCode, String modifyUserId) {
		final String sql = getQuery("TRIP_TABLE_UPDATE");
		try {
			namedParameterJdbcTemplate.update(sql, createParams("tripCode", tripCode, "modifyUserId", modifyUserId));
		} catch (Exception e) {
			logger.error(this.getClass().getName() + "triptableupdate  in error ");
		}
	}

}
