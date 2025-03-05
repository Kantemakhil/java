package net.syscon.s4.inst.schedules.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.inst.casemanagement.beans.InternalScheduleReasons;
import net.syscon.s4.inst.schedules.OidsiappRepository;
import net.syscon.s4.inst.schedules.bean.IntLocUsageLocations;
import net.syscon.s4.inst.schedules.bean.OffenderIndSchedules;
import net.syscon.s4.inst.schedules.bean.OffenderSchedulePersons;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules;

/**
 * Class OidsiappRepositoryImpl
 */
@Repository
public class OidsiappRepositoryImpl extends RepositoryBase implements OidsiappRepository {

	public static final String MODE = "ENTER-QUERY";
	
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidsiappRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> vHdrBlkMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_ID", new FieldMapper(" offenderId ")).build();
	
	private final Map<String, FieldMapper> vOffAllSchMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("AGY_LOC_ID", new FieldMapper("agyLocId"))
			.put("SICK_NOTE_EXPIRY_DATE", new FieldMapper("sickNoteExpiryDate"))
			.put("TO_INT_LOC_LEVEL_1_CODE", new FieldMapper("toIntLocLevel1Code"))
			.put("LU_LEVEL_3_CODE", new FieldMapper("luLevel3Code"))
			.put("TO_CITY_NAME", new FieldMapper("toCityName"))
			.put("PROV_STATE_DESC", new FieldMapper("provStateDesc"))
			.put("TO_AGY_LOC_ID", new FieldMapper("toAgyLocId"))
			.put("TO_ADDRESS_ID", new FieldMapper("toAddressId"))
			.put("UNPAID_WORK_SUPERVISOR", new FieldMapper("unpaidWorkSupervisor"))
			.put("LIVING_UNIT_ID", new FieldMapper("livingUnitId"))
			.put("UNPAID_WORK_ACTION", new FieldMapper("unpaidWorkAction"))
			.put("AGENCY_IML_LEVEL_1_CODE", new FieldMapper("agencyImlLevel1Code"))
			.put("ESCORT_CODE", new FieldMapper("escortCode"))
			.put("OFFENDER_ID", new FieldMapper("offenderId"))
			.put("EVENT_DATE", new FieldMapper("eventDate"))
			.put("TO_INT_LOC_USER_DESC", new FieldMapper("toIntLocUserDesc"))
			.put("EVENT_STATUS_DESC", new FieldMapper("eventStatusDesc"))
			.put("TO_INTERNAL_LOCATION_DESC", new FieldMapper("toInternalLocationDesc"))
			.put("AGY_LOC_DESC", new FieldMapper("agyLocDesc"))
			.put("LU_LEVEL_2_CODE", new FieldMapper("luLevel2Code"))
			.put("FROM_CITY_CODE", new FieldMapper("fromCityCode"))
			.put("EVENT_SUB_TYPE", new FieldMapper("eventSubType"))
			.put("DETAILS", new FieldMapper("details"))
			.put("CHECK_BOX_1", new FieldMapper("checkBox1"))
			.put("BUSY_DATE_FLAG", new FieldMapper("busyDateFlag"))
			.put("EVENT_OUTCOME_DESC", new FieldMapper("eventOutcomeDesc"))
			.put("SCHEDULED_TRIP_ID", new FieldMapper("scheduledTripId"))
			.put("EVENT_SUB_TYPE_DESC", new FieldMapper("eventSubTypeDesc"))
			.put("HIDDEN_COMMENT_TEXT", new FieldMapper("hiddenCommentText"))
			.put("IN_CHARGE_STAFF_NAME", new FieldMapper("inChargeStaffName"))
			.put("TO_INT_LOC_LEVEL_2_CODE", new FieldMapper("toIntLocLevel2Code"))
			.put("TRANSPORT_CODE", new FieldMapper("transportCode"))
			.put("COMMENT_TEXT", new FieldMapper("commentText"))
			.put("EVENT_TYPE_DESC", new FieldMapper("eventTypeDesc"))
			.put("AGENCY_IML_LEVEL_2_CODE", new FieldMapper("agencyImlLevel2Code"))
			.put("IN_TIME", new FieldMapper("inTime"))
			.put("ACTIVE_FLAG", new FieldMapper("activeFlag"))
			.put("OFF_PRGREF_ID", new FieldMapper("offPrgrefId"))
			.put("SICK_NOTE_RECEIVED_DATE", new FieldMapper("sickNoteReceivedDate"))
			.put("CONTACT_PERSON_NAME", new FieldMapper("contactPersonName"))
			.put("OUT_TIME", new FieldMapper("outTime"))
			.put("AGREED_TRAVEL_HOUR", new FieldMapper("agreedTravelHour"))
			.put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId"))
			.put("EVENT_TYPE", new FieldMapper("eventType"))
			.put("REFERENCE_ID", new FieldMapper("referenceId"))
			.put("PROV_STATE_CODE", new FieldMapper("provStateCode"))
			.put("LU_LEVEL_1_CODE", new FieldMapper("luLevel1Code"))
			.put("BOOKING_NO", new FieldMapper("bookingNo"))
			.put("AGENCY_IML_DESC", new FieldMapper("agencyImlDesc"))
			.put("TO_INT_LOC_LEVEL_3_CODE", new FieldMapper("toIntLocLevel3Code"))
			.put("CHECK_BOX_2", new FieldMapper("checkBox2"))
			.put("TO_AGY_LOC_DESC", new FieldMapper("toAgyLocDesc"))
			.put("END_TIME", new FieldMapper("endTime"))
			.put("TO_LOC", new FieldMapper("toLoc"))
			.put("EVENT_CLASS", new FieldMapper("eventClass"))
			.put("RETURN_DATE", new FieldMapper("returnDate"))
			.build();
	
	private final Map<String, FieldMapper> intLocUsgMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("LOCCODE", new FieldMapper("locCode"))
			.put("INTERNAL_LOCATION_ID", new FieldMapper("internalLocationId"))
			.put("LIST_SEQ", new FieldMapper("listSeq"))
			.put("CODE", new FieldMapper("code"))
			.build();
	
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("CODE", new FieldMapper("code"))
			.build();
	
	private final Map<String, FieldMapper> offSchPerMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("'1'", new FieldMapper("  '1' ")).build();
	
	/**
	 * Creates new OidsiappRepositoryImpl class Object
	 */
	public OidsiappRepositoryImpl() {
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            VOffenderAllSchedules
	 *
	 * @return List<VOffenderAllSchedules>
	 *
	 * @
	 */
	public List<VOffenderAllSchedules> offSchExecuteQuery(final VOffenderAllSchedules objSearchDao) {
		List<VOffenderAllSchedules> scheduleList=new ArrayList<>();
		final String sql = getQuery("OIDSIAPP_OFFSCH_FIND_V_OFFENDER_ALL_SCHEDULES");
		final RowMapper<VOffenderAllSchedules> vOffAllSchRM = Row2BeanRowMapper.makeMapping(sql,
				VOffenderAllSchedules.class, vOffAllSchMapping);
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource valuesList = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" where ");
			if (objSearchDao.getOffenderBookId() != null) {
				sqlQuery.append(
						"OFFENDER_BOOK_ID =  :offenderBookId AND EVENT_TYPE = 'APP'  AND EVENT_CLASS = 'INT_MOV' "
						+ " AND EVENT_STATUS = 'SCH' AND EVENT_SUB_TYPE != 'VIS' AND BOOKING_ACTIVE_FLAG = 'Y'  ORDER BY EVENT_DATE DESC, START_TIME DESC");
				valuesList.addValue("offenderBookId", objSearchDao.getOffenderBookId());
			}
		}
		preparedSql = sqlQuery.toString().trim();
		try {
			scheduleList=namedParameterJdbcTemplate.query(preparedSql, valuesList, vOffAllSchRM);
		}catch (Exception e) {
			logger.error("Error in Class " + this.getClass().getName() +"In offSchExecuteQuery method",e);
		}
        return scheduleList;
	}

	/**
	 * @param
	 *
	 * @
	 *
	 */
	public int preInsert() {
		return 0;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstVOffAllSch
	 *            List<VOffenderAllSchedules>
	 *
	 * @return List<Integer>
	 *
	 */
	public Integer offSchInsertVOffenderAllSchedules(final List<OffenderIndSchedules> lstOffAllSch) {
		int returnValue = 0;
		final String sql = getQuery("OIDSIAPP_SCH_INSERT_OFFENDER_IND_SCHEDULES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();

		for (final OffenderIndSchedules offenderEscapes : lstOffAllSch) {
			parameters.add(new BeanPropertySqlParameterSource(offenderEscapes));
		}
		try {
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		}catch (Exception e) {
			logger.error("Error in Class " + this.getClass().getName() +"In offSchInsertVOffenderAllSchedules method",e);
		}
		if (lstOffAllSch.size() == returnArray.length) {
			returnValue = 1;
		}

		return returnValue;

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstVOffAllSch
	 *            List<VOffenderAllSchedules>
	 *
	 */
	public Integer offSchUpdateVOffenderAllSchedules(final List<OffenderIndSchedules> lstOffAllSch) {
		final String sql = getQuery("OIDSIAPP_SCH_UPDATE_V_OFFENDER_ALL_SCHEDULES_2");
		int returnValue = 0;
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderIndSchedules vOffAllSch : lstOffAllSch) {
			parameters.add(new BeanPropertySqlParameterSource(vOffAllSch));
		}
		try {
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		}catch (Exception e) {
			logger.error("Error in Class " + this.getClass().getName() +"In offSchUpdateVOffenderAllSchedules method",e);
		}
		if (lstOffAllSch.size() == returnArray.length) {
			returnValue = 1;
		}

		return returnValue;

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstVOffAllSch
	 *            List<VOffenderAllSchedules>
	 *
	 * @
	 */
	public Integer offSchDeleteVOffenderAllSchedules(final List<OffenderIndSchedules> lstOffAllSch) {
		final String sql = getQuery("OIDSIAPP_SCH_DELETE_V_OFFENDER_ALL_SCHEDULES_2");
		int returnValue = 0;
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderIndSchedules vOffAllSch : lstOffAllSch) {
			parameters.add(new BeanPropertySqlParameterSource(vOffAllSch));
		}
		try {
			String tableName = "V_OFFENDER_ALL_SCHEDULES_2";
			String whereCondition = "EVENT_ID = :eventId";
			batchUpdatePreDeletedRows(tableName, whereCondition, parameters);
		} catch (Exception e) {
			logger.error(e);
		}
		try {
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		}catch (Exception e) {
			logger.error("Error in Class " + this.getClass().getName() +"In offSchDeleteVOffenderAllSchedules method",e);
		}
		if (lstOffAllSch.size() == returnArray.length) {
			returnValue = 1;
		}
		return returnValue;

	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<InternalLocationUsages>
	 */
	public List<IntLocUsageLocations> rgInternalMoveLocationsRecordGroup(final String agyLocId) {
		final String sql = getQuery("OIDSIAPP_FIND_RGINTERNALMOVELOCATIONS");
		final RowMapper<IntLocUsageLocations> intLocUsgRM = Row2BeanRowMapper.makeMapping(sql,
				IntLocUsageLocations.class, intLocUsgMapping);
		List<IntLocUsageLocations> lstLoc = new ArrayList<IntLocUsageLocations>();
		try {
			if (agyLocId != null) {
				lstLoc = (List<IntLocUsageLocations>) namedParameterJdbcTemplate.query(sql,
						createParams("MODE", MODE, "AGYLOCID", agyLocId), intLocUsgRM);
			}
		} catch (EmptyResultDataAccessException e) {
			logger.error("Error in Class " + this.getClass().getName() +"In rgInternalMoveLocationsRecordGroup method : ", e);
		}
		return lstLoc;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return ListInternalScheduleReasonsM>
	 */
	public List<InternalScheduleReasons> rgSchInternalScheduleRecordGroup() {
		final String sql = getQuery("OIDSIAPP_FIND_RGSCHINTERNALSCHEDULE");
		final RowMapper<InternalScheduleReasons> mRowMapper = Row2BeanRowMapper.makeMapping(sql,
				InternalScheduleReasons.class, mMapping);
		List<InternalScheduleReasons> lstInternal = new ArrayList<>();
		try {
			lstInternal = (List<InternalScheduleReasons>) namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("Error in Class " + this.getClass().getName() +"In rgSchInternalScheduleRecordGroup method : ", e);
		}
		return lstInternal;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offBkgOnCheckDeleteMaster
	 *
	 * @param params
	 *
	 */
	public List<VOffenderAllSchedules> offBkgOnCheckDeleteMaster(final VOffenderAllSchedules paramBean) {
		List<VOffenderAllSchedules> offSchList=new ArrayList<>();
		final String sql = getQuery("OIDSIAPP_OFF_BKG_ONCHECKDELETEMASTER");
		final RowMapper<VOffenderAllSchedules> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				VOffenderAllSchedules.class, vOffAllSchMapping);
		try {
			offSchList= namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		}catch (Exception e) {
			logger.error("Error in Class " + this.getClass().getName() +"In offBkgOnCheckDeleteMaster method : ", e);
		}
		return offSchList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgrichkOffenderSchedules
	 *
	 * @param params
	 *
	 */
	public OffenderSchedulePersons cgrichkOffenderSchedules(final OffenderSchedulePersons paramBean) {
		OffenderSchedulePersons persons=new OffenderSchedulePersons();
		final String sql = getQuery("OIDSIAPP_CGRICHK_OFFENDER_SCHEDULES");
		final RowMapper<OffenderSchedulePersons> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderSchedulePersons.class, offSchPerMapping);
		try {
		persons= namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		}catch (Exception e) {
			logger.error("Error in Class " + this.getClass().getName() +"In offBkgOnCheckDeleteMaster method : ", e);
		}
		return persons;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * checkNa
	 *
	 * @param params
	 *
	 */
	public List<VHeaderBlock> checkNa(final VHeaderBlock paramBean) {
		List<VHeaderBlock> vHeaderList=new ArrayList<>();
		final String sql = getQuery("OIDSIAPP_CHECK_NA");
		final RowMapper<VHeaderBlock> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, VHeaderBlock.class,
				vHdrBlkMapping);
		try {
		vHeaderList =namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		}catch (Exception e) {
			logger.error("Error in Class " + this.getClass().getName() +"In checkNa method : ", e);
		}
		return vHeaderList;
	}

	/**
	 * Before inserting the record it verifying whether any other schedules are
	 * assigned to the offender
	 * 
	 * @param searchRecord
	 * @return Integer
	 */
	public Integer checkScheduleConflict(final VOffenderAllSchedules objVOffSch) {
		try {
		final String sql = getQuery("OIDSIAPP_CHECK_SCHEDULE_CONFLICT");
		Integer returnList = 0;
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String eventDate=sdf.format(objVOffSch.getEventDate());
		if (objVOffSch.getEventDate() != null && objVOffSch.getOffenderBookId() != null) {
			returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderbookid",
					objVOffSch.getOffenderBookId(), "EVENT_DATE",eventDate), Integer.class);
			if (returnList != null && returnList > 0) {
				returnList = 1;
			}
		}
		return returnList;
		} catch(Exception e) {
			logger.error("Error in Class " + this.getClass().getName() +"Exception in checkScheduleConflict " + e);
		}
		return null;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * crtEvePreInsert
	 *
	 * @param params
	 *
	 */
	public Integer offSchPreInsert() {
		Integer sch=null;
		final String sql = getQuery("OIDSIAPP_OFF_SCH_PREINSERT_QUERY");
		try {
		sch= namedParameterJdbcTemplate.queryForObject(sql, createParams(), Integer.class);
		}catch (Exception e) {
			logger.error("Error in Class " + this.getClass().getName() +"Exception in offSchPreInsert " + e);
		}
		return sch;
	}

	/**
	 * This method is retrieves internalLocationId based on code
	 * 
	 * crtEvePreInsert
	 *
	 * @param params
	 *
	 */
	public Integer offSchInternalLocationId(final String intLocCode, final String agyLocId) {
		Integer locationId=null;
		final String sql = getQuery("OIDSIAPP_SCH_INTERNAL_LOCATION_ID");
		try {
		locationId= namedParameterJdbcTemplate.queryForObject(sql,
				createParams("internalLocationCode", intLocCode, "agyLocId", agyLocId), Integer.class);
		}catch (Exception e) {
			logger.error("Error in Class " + this.getClass().getName() +"Exception in offSchPreInsert " + e);
		}
		return locationId;
	}
	
	@Override
	public List<OffenderIndSchedules> getOldRecord(Integer eventId) {
		List<OffenderIndSchedules> scheduleList=new ArrayList<>();
		final String sql = getQuery("OIDSIAPP_SCH_UPDATE_QUERY");
		final RowMapper<OffenderIndSchedules> vOffAllSchRM = Row2BeanRowMapper.makeMapping(sql,
				OffenderIndSchedules.class, vOffAllSchMapping);
		try {
			scheduleList= namedParameterJdbcTemplate.query(sql,
				createParams("eventId",eventId),vOffAllSchRM);
		}catch (Exception e) {
			logger.error("Error in Class " + this.getClass().getName() +"Exception in getOldRecord " + e);
		}
		return scheduleList;
	}
	/**
	 * This method is retrieves internalLocationId based on code
	 * 
	 * crtEvePreInsert
	 *
	 * @param params
	 *
	 */
	public String gettingLovDescription(final String code) {
		String desc="";
		final String sql = getQuery("OIDSIAPP_GET_LOV_DESCRIPTION");
		try {
		desc= namedParameterJdbcTemplate.queryForObject(sql,
				createParams("CODE", code), String.class);
		}catch (Exception e) {
			logger.error("Error in Class " + this.getClass().getName() +"Exception in gettingLovDescription " + e);	
		}
		return desc;
	}
	
	@Override
	public List<OffenderIndSchedules> getNonAssociationScheduleConflicts(final BigDecimal offenderBookID, final Date eventDate, final BigDecimal toInterLocationId) {
		final String sql = getQuery("OIDSIAPP_CHECK_NONASSOCOATION_SCHEDULE");
		List<OffenderIndSchedules> returnList = new ArrayList();
		final RowMapper<OffenderIndSchedules> rowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderIndSchedules.class, offSchPerMapping);
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("offenderBookID",offenderBookID,"eventDate",eventDate,"toInternalLocationId",toInterLocationId),rowMapper);
		} catch (Exception e) {
			logger.error("Error in Class " + this.getClass().getName() +"in checkOffenderScheduleConflicts method : ", e);   
		}
		return returnList;
	}

	@Override
	public Integer getLocationId(String userDesc, String agyLocId) {
		Integer internalLocationId =null;
		final String sql = getQuery("GET_INTERNAL_LOCATION_ID");
		try {
			internalLocationId = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("agyLocId", agyLocId, "userDesc", userDesc), Integer.class);
		}catch (Exception e) {
			logger.error("Error in Class " + this.getClass().getName() +"Exception in gettingLocationId " + e);	
		}
		return internalLocationId;
	}
}
