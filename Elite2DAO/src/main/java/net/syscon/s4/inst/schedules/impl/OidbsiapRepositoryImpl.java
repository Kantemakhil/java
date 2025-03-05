package net.syscon.s4.inst.schedules.impl;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.AgencyInternalLocations;
import net.syscon.s4.common.beans.AgyIntLocProfiles;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.OffenderNaDetails;
import net.syscon.s4.im.beans.OffenderNonAssociations;
import net.syscon.s4.inst.casemanagement.beans.InternalScheduleReasons;
import net.syscon.s4.inst.schedules.OidbsiapRepository;
import net.syscon.s4.inst.schedules.bean.OffenderIndSchedules;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules2;
import net.syscon.s4.pkgs.non_association.NonAssociationRepository;
import oracle.jdbc.OracleTypes;

/**
 * Class OidbsiapRepositoryImpl
 */
@Repository
public class OidbsiapRepositoryImpl extends RepositoryBase implements OidbsiapRepository {
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidbsiapRepositoryImpl.class.getName());

private final Map<String, FieldMapper> vHeaderBlockMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("OFFENDER_ID", 						new FieldMapper(" offenderId "))
.build();
private final Map<String, FieldMapper> vOffenderAllSchedules2Mapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("TO_INTERNAL_LOCATION_ID", 			new FieldMapper("toInternalLocationId"))
.put("CREDITED_HOURS", 						new FieldMapper("creditedHours"))
.put("UNEXCUSED_ABSENCE_FLAG", 				new FieldMapper("unexcusedAbsenceFlag"))
.put("UNDERSTANDING_CODE", 					new FieldMapper("understandingCode"))
.put("PERFORMANCE_CODE", 					new FieldMapper("performanceCode"))
.put("TA_ID", 								new FieldMapper("taId"))
.put("TO_CITY_CODE", 						new FieldMapper("toCityCode"))
.put("CONFIRM_FLAG", 						new FieldMapper("confirmFlag"))
.put("CHECK_SUM", 							new FieldMapper("checkSum"))
.put("APPLICATION_TIME", 					new FieldMapper("applicationTime"))
.put("RECORD_SOURCE", 						new FieldMapper("recordSource"))
.put("SCHEDULE_MOVEMENT_TIME", 				new FieldMapper("scheduleMovementTime"))
.put("TO_CORPORATE_ID", 					new FieldMapper("toCorporateId"))
.put("EVENT_STATUS", 						new FieldMapper("eventStatus"))
.put("IN_CHARGE_STAFF_ID", 					new FieldMapper("inChargeStaffId"))
.put("PIECE_WORK", 							new FieldMapper("pieceWork"))
.put("UNPAID_WORK_BEHAVIOUR", 				new FieldMapper("unpaidWorkBehaviour"))
.put("START_TIME", 							new FieldMapper("startTime"))
.put("DIRECTION_CODE", 						new FieldMapper("directionCode"))
.put("TO_ADDRESS_OWNER_CLASS", 				new FieldMapper("toAddressOwnerClass"))
.put("OFFENDER_BOOK_ID", 					new FieldMapper("offenderBookId"))
.put("RETURN_TIME", 						new FieldMapper("returnTime"))
.put("APPLICATION_DATE", 					new FieldMapper("applicationDate"))
.put("EVENT_OUTCOME", 						new FieldMapper("eventOutcome"))
.put("EVENT_ID", 							new FieldMapper("eventId"))
.put("ENGAGEMENT_CODE", 					new FieldMapper("engagementCode"))
.put("OUTCOME_REASON_CODE", 				new FieldMapper("outcomeReasonCode"))
.put("AGY_LOC_ID", 							new FieldMapper("agyLocId"))
.put("SICK_NOTE_EXPIRY_DATE", 				new FieldMapper("sickNoteExpiryDate"))
.put("TO_AGY_LOC_ID", 						new FieldMapper("toAgyLocId"))
.put("TO_ADDRESS_ID", 						new FieldMapper("toAddressId"))
.put("UNPAID_WORK_SUPERVISOR", 				new FieldMapper("unpaidWorkSupervisor"))
.put("UNPAID_WORK_ACTION", 				    new FieldMapper("unpaidWorkAction"))
.put("ESCORT_CODE", 						new FieldMapper("escortCode"))
.put("EVENT_DATE", 							new FieldMapper("eventDate"))
.put("FROM_CITY_CODE", 						new FieldMapper("fromCityCode"))
.put("EVENT_SUB_TYPE", 						new FieldMapper("eventSubType"))
.put("DETAILS", 							new FieldMapper("details"))
.put("CHECK_BOX_1", 						new FieldMapper("checkBox1"))
.put("SCHEDULED_TRIP_ID", 					new FieldMapper("scheduledTripId"))
.put("HIDDEN_COMMENT_TEXT", 				new FieldMapper("hiddenCommentText"))
.put("TRANSPORT_CODE", 						new FieldMapper("transportCode"))
.put("COMMENT_TEXT", 						new FieldMapper("commentText"))
.put("IN_TIME", 							new FieldMapper("inTime"))
.put("OFF_PRGREF_ID", 						new FieldMapper("offPrgrefId"))
.put("SICK_NOTE_RECEIVED_DATE", 			new FieldMapper("sickNoteReceivedDate"))
.put("CONTACT_PERSON_NAME", 				new FieldMapper("contactPersonName"))
.put("OUT_TIME", 							new FieldMapper("outTime"))
.put("AGREED_TRAVEL_HOUR", 					new FieldMapper("agreedTravelHour"))
.put("EVENT_TYPE", 							new FieldMapper("eventType"))
.put("REFERENCE_ID", 						new FieldMapper("referenceId"))
.put("PROV_STATE_CODE", 					new FieldMapper("provStateCode"))
.put("CHECK_BOX_2", 						new FieldMapper("checkBox2"))
.put("END_TIME", 							new FieldMapper("endTime"))
.put("EVENT_CLASS", 						new FieldMapper("eventClass"))
.put("RETURN_DATE", 						new FieldMapper("returnDate"))
.build();
private final Map<String, FieldMapper> agencyLocationsMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("CODE", 								new FieldMapper("code"))
.put("DESCRIPTION", 						new FieldMapper("description"))
.build();
private final Map<String, FieldMapper> vNameSearchMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("AGY_LOC_ID", 						new FieldMapper("agyLocId"))
.put("LAST_NAME", 						new FieldMapper("lastName"))
.put("OFFENDER_BOOK_ID", 				new FieldMapper("offenderBookId"))
.put("FIRST_NAME", 						new FieldMapper("firstName"))
.put("NBT_OFFENDER_ID_DISPLAY", 		new FieldMapper("nbtOffenderIdDisplay"))
.build();
private final Map<String, FieldMapper> agencyInternalLocationsMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("DESCRIPTION", 				new FieldMapper("description"))
.put("CODE", 						new FieldMapper("code"))
.build();
private final Map<String, FieldMapper> internalScheduleReasonsMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("DESCRIPTION", 				new FieldMapper("description"))
.put("CODE", 						new FieldMapper("code"))
.build();
@Autowired
private NonAssociationRepository nonAssociationRepository;

	/**
	 * Creates new OidbsiapRepositoryImpl class Object
	 */
	public OidbsiapRepositoryImpl() {
		// OidbsiapRepositoryImpl
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            VOffenderAllSchedules2
	 *
	 * @return List<VOffenderAllSchedules2>
	 *
	 * @
	 */
	public List<VOffenderAllSchedules2> offSchExecuteQuery(final VOffenderAllSchedules2 objSearchDao) {
		final String sql = getQuery("OIDBSIAP_OFFSCH_FIND_V_OFFENDER_ALL_SCHEDULES_2");
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource params = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" where ");
			if (objSearchDao.getAgyLocId() != null) {
				sqlQuery.append(" AGY_LOC_ID =:agyLocId  " + " and ");
				params.addValue("agyLocId", objSearchDao.getAgyLocId());
			}
			if (objSearchDao.getEventDate() != null) {
				sqlQuery.append(" EVENT_DATE =:eventDate  " + " and ");
				params.addValue("eventDate", objSearchDao.getEventDate());
			}
			if (objSearchDao.getEventSubType() != null && !"".equals(objSearchDao.getEventSubType())) {
				sqlQuery.append(" EVENT_SUB_TYPE =:eventSubType  " + " and ");
				params.addValue("eventSubType", objSearchDao.getEventSubType());
			}
			if (objSearchDao.getToInternalLocationId() != null) {
				sqlQuery.append(" TO_INTERNAL_LOCATION_ID =:toInternalLocationId  " + " and ");
				params.addValue("toInternalLocationId", objSearchDao.getToInternalLocationId());
			}
			if (objSearchDao.getStartTime() != null) {
				sqlQuery.append(" START_TIME =:startTime  " + " and ");
				params.addValue("startTime", objSearchDao.getStartTime());
			}

		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("where")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("and")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		if (objSearchDao != null && objSearchDao.getCaseloadId() != null) {
			preparedSql = preparedSql
					+ "  AND OFFENDER_BOOK_ID IN ( SELECT off_bkg.offender_book_id FROM offender_bookings off_bkg WHERE off_bkg.active_flag ='Y'  AND off_bkg.agy_loc_id IN ( SELECT cal.agy_loc_id FROM caseload_agency_locations cal WHERE cal.caseload_id = '"
					+ objSearchDao.getCaseloadId()
					+ "' )) AND EVENT_CLASS = 'INT_MOV' AND EVENT_TYPE = 'APP' AND EVENT_STATUS IN ('SCH','CANC')";
			// ORDER BY ocdccide.sort_on_associated_offender(OFFENDER_BOOK_ID)
		}
		final RowMapper<VOffenderAllSchedules2> VOffenderAllSchedules2RowMapper = Row2BeanRowMapper
				.makeMapping(preparedSql, VOffenderAllSchedules2.class, vOffenderAllSchedules2Mapping);
		final ArrayList<VOffenderAllSchedules2> returnList = (ArrayList<VOffenderAllSchedules2>) namedParameterJdbcTemplate
				.query(preparedSql, params, VOffenderAllSchedules2RowMapper);
		return returnList;
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
	 * @param lstVOffenderAllSchedules2
	 *            List<VOffenderAllSchedules2>
	 *
	 * @return List<Integer>
	 *
	 * @
	 */
	public Integer offSchInsertVOffenderAllSchedules2(final List<OffenderIndSchedules> lstOffenderIndSchedules) {
		final String sql = getQuery("OIDBSIAP_INSERT_OFFENDER_IND_SCHEDULES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderIndSchedules ofIndSchObj : lstOffenderIndSchedules) {
			parameters.add(new BeanPropertySqlParameterSource(ofIndSchObj));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenderIndSchedules.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstVOffenderAllSchedules2
	 *            List<VOffenderAllSchedules2>
	 *
	 * @
	 */
	public Integer offSchUpdateVOffenderAllSchedules2(final List<OffenderIndSchedules> lstOffenderIndSchedules) {
		final String sql = getQuery("OIDBSIAP_UPDATE_OFFENDER_IND_SCHEDULES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderIndSchedules ofIndSchObj : lstOffenderIndSchedules) {
			parameters.add(new BeanPropertySqlParameterSource(ofIndSchObj));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenderIndSchedules.size() == returnArray.length) {
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
	public List<InternalScheduleReasons> rgSchInternalScheduleRecordGroup() {
		final String sql = getQuery("OIDBSIAP_FIND_RGSCHINTERNALSCHEDULE");
		final RowMapper<InternalScheduleReasons> intSchReasonRowMapper = Row2BeanRowMapper.makeMapping(sql,
				InternalScheduleReasons.class, internalScheduleReasonsMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, intSchReasonRowMapper);
		} catch (Exception e) {
			logger.error("In method rgSchInternalScheduleRecordGroup : ", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<AgencyLocations> rgAgyLocRecordGroup(final String caseloadId) {
		final String sql = getQuery("OIDBSIAP_FIND_RGAGYLOC");
		final RowMapper<AgencyLocations> agyLocRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				agencyLocationsMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("CASELOADID", caseloadId), agyLocRowMapper);
		} catch (Exception e) {
			logger.error("In method rgAgyLocRecordGroup : ", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<MMInternalLocationUsages>
	 */
	public List<AgencyInternalLocations> rgInternalMoveLocationsRecordGroup(final String agyLocId) {
		final String sql = getQuery("OIDBSIAP_FIND_RGINTERNALMOVELOCATIONS");
		final RowMapper<AgencyInternalLocations> agyIntLocRowMapper = Row2BeanRowMapper.makeMapping(sql,
				AgencyInternalLocations.class, agencyInternalLocationsMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("mode", "ENTER-QUERY", "AGENCYLOCID", agyLocId),
					agyIntLocRowMapper);
		} catch (Exception e) {
			logger.error("In method rgInternalMoveLocationsRecordGroup : ", e);
			return Collections.emptyList();
		}
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
		final String sql = getQuery("OIDBSIAP_CHECK_NA");
		final RowMapper<VHeaderBlock> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, VHeaderBlock.class,
				vHeaderBlockMapping);
		final ArrayList<VHeaderBlock> returnList = (ArrayList<VHeaderBlock>) namedParameterJdbcTemplate.query(sql,
				createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            getOffDetailsByBookId
	 *
	 * @return List<VHeaderBlock>
	 *
	 * @
	 */
	public VHeaderBlock getOffDetailsByBookId(final VHeaderBlock objSearchDao) {
		Map<String, Object> returnObject = null;
		final VHeaderBlock bean = new VHeaderBlock();
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[5];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_OFFENDER_BOOK_ID", OracleTypes.NUMBER),
				new SqlOutParameter("P_OFFENDER_ID_DISPLAY", OracleTypes.VARCHAR),
				new SqlOutParameter("P_LAST_NAME", OracleTypes.VARCHAR),
				new SqlOutParameter("P_FIRST_NAME", OracleTypes.VARCHAR),
				new SqlOutParameter("P_AGY_LOC_ID", OracleTypes.VARCHAR), };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("OSINAMES").withProcedureName("GET_OFF_DETAILS_BY_BOOK_ID")
				.declareParameters(sqlParameters);

		inParamMap.put("P_OFFENDER_BOOK_ID", objSearchDao.getOffenderBookId());
		inParamMap.put("P_OFFENDER_ID_DISPLAY", objSearchDao.getOffenderIdDisplay());
		inParamMap.put("P_LAST_NAME", objSearchDao.getLastName());
		inParamMap.put("P_FIRST_NAME", objSearchDao.getFirstName());
		inParamMap.put("P_AGY_LOC_ID", objSearchDao.getAgyLocId());
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			returnObject = (Map<String, Object>) simpleJDBCCall.withoutProcedureColumnMetaDataAccess()
					.execute(inParameter);

			bean.setOffenderBookId(returnObject.get("P_OFFENDER_BOOK_ID") != null
					? new BigDecimal(String.valueOf(returnObject.get("P_OFFENDER_BOOK_ID"))) : null);
			bean.setOffenderIdDisplay(returnObject.get("P_OFFENDER_ID_DISPLAY") != null
					? String.valueOf(returnObject.get("P_OFFENDER_ID_DISPLAY")) : null);
			bean.setLastName(
					returnObject.get("P_LAST_NAME") != null ? String.valueOf(returnObject.get("P_LAST_NAME")) : null);
			bean.setFirstName(
					returnObject.get("P_FIRST_NAME") != null ? String.valueOf(returnObject.get("P_FIRST_NAME")) : null);
			bean.setAgyLocId(
					returnObject.get("P_AGY_LOC_ID") != null ? String.valueOf(returnObject.get("P_AGY_LOC_ID")) : null);

		} catch (Exception e) {
			logger.error("In method getOffDetailsByBookId", e);
		}
		return bean;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            getLivingUnitDesc
	 *
	 * @return VHeaderBlock
	 *
	 * @
	 */
	public VHeaderBlock getLivingUnitDesc(final VHeaderBlock objSearchDao) {
		Map<String, Object> returnObject = null;
		final VHeaderBlock bean = new VHeaderBlock();
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[5];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_OFFENDER_BKG_ID", OracleTypes.NUMBER),
				new SqlOutParameter("P_UNIT_DESC", OracleTypes.VARCHAR), };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("OIDBSIAP").withProcedureName("GET_LIVING_UNIT_DESC").declareParameters(sqlParameters);

		inParamMap.put("P_OFFENDER_BKG_ID", objSearchDao.getOffenderBookId());
		inParamMap.put("P_UNIT_DESC", objSearchDao.getLivingUnitDescription());
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			returnObject = (Map<String, Object>) simpleJDBCCall.withoutProcedureColumnMetaDataAccess()
					.execute(inParameter);

			bean.setOffenderBookId(returnObject.get("P_OFFENDER_BKG_ID") != null
					? new BigDecimal(String.valueOf(returnObject.get("P_OFFENDER_BKG_ID"))) : null);
			bean.setLivingUnitDescription(
					returnObject.get("P_UNIT_DESC") != null ? String.valueOf(returnObject.get("P_UNIT_DESC")) : null);

		} catch (Exception e) {
			logger.error("In method getLivingUnitDesc", e);
		}
		return bean;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offSchCheckScheduleConflict
	 *
	 *
	 */
	public Integer offSchCheckScheduleConflict(final VOffenderAllSchedules2 objSearchDao) {
		final String sql = getQuery("OIDBSIAP_CHECK_SCHEDULE_CONFLICT");
		Integer returnList;
		returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("OFFENDER_BOOK_ID",
				objSearchDao.getOffenderBookId(), "EVENT_DATE", objSearchDao.getEventDate()), Integer.class);
		return returnList;

	}
	@Override
	public List<AgyIntLocProfiles> getNonAssociationConfigForLocation(final BigDecimal locationId) {
		final String sql = getQuery("OIDBSIAPP_GET_NONASSOCIATION_CONFIG_FOR_LOCATION");
		final RowMapper<AgyIntLocProfiles> AgyIntLocMapper = Row2BeanRowMapper.makeMapping(sql,
				AgyIntLocProfiles.class, agencyInternalLocationsMapping);
		return namedParameterJdbcTemplate.query(sql,
				createParams("internalLocationId", locationId), AgyIntLocMapper);
	}
	
	@Override
	public List<OffenderNaDetails> getNonAssociationOffenders(final BigDecimal offenderBookId, final List<AgyIntLocProfiles> profileCodeList) {
		String sql = getQuery("OIDBSIAPP_SCH_NONASSOCIATION_OFFENDERS");
		List<OffenderNaDetails> returnList = new ArrayList<OffenderNaDetails>();
		final RowMapper<OffenderNaDetails> VAcpProgressRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderNaDetails.class, agencyInternalLocationsMapping);
		List<String> profileCodeListNew=new ArrayList<>();
		for(AgyIntLocProfiles ob:profileCodeList) {
			profileCodeListNew.add(ob.getIntLocProfileCode());
		}
		try {
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("offenderBookId", offenderBookId,"profileCodeList",profileCodeListNew),
					VAcpProgressRowMapper);
			
		} catch (Exception e) {
			logger.error(this.getClass().getName()+" getNonAssociationOffenders", e);
			return returnList;
		}
		return returnList;
	}
	
	@Override
	public List<OffenderIndSchedules> getNonAssociationScheduleConflicts(final BigDecimal offenderBookID, final Date eventDate, final BigDecimal toInterLocationId) {
		final String sql = getQuery("OIDBIAPP_CHECK_NONASSOCOATION_SCHEDULE");
		List<OffenderIndSchedules> returnList = new ArrayList<>();
		final RowMapper<OffenderIndSchedules> rowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderIndSchedules.class, internalScheduleReasonsMapping);
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("offenderBookID",offenderBookID,"eventDate",eventDate,"toInternalLocationId",toInterLocationId),rowMapper);
		} catch (Exception e) {
			logger.error("in checkOffenderScheduleConflicts method : ", e);   
		}
		return returnList;
	}
	
	@Override
	public List<OffenderNonAssociations> getNsOffenderBookId(BigDecimal offenderBookId){
		List<OffenderNonAssociations> associationsList=new ArrayList<>();
		try {
		String sql=getQuery("OIDBIAPP_GET_NS_OFFENDER_BOOK_ID");
		RowMapper<OffenderNonAssociations> mapper=Row2BeanRowMapper.makeMapping(sql,OffenderNonAssociations.class,agencyLocationsMapping);
		associationsList= namedParameterJdbcTemplate.query(sql,createParams("offenderBookId",offenderBookId),mapper);
		}catch (Exception e) {
			logger.error("in getNsOffenderBookId method : ", e);
		}
		return associationsList;
	}
	
	
	@Override
	public List<OffenderNaDetails> checkNonAssociationOffendersWithLivingUnit(BigDecimal offenderBookId,
			BigDecimal livingUnitId, String livingUnitCode) {
		List<OffenderNaDetails> offenderNaDetailsList = null;
		try {
			List<AgyIntLocProfiles> agyIntLocProfilesList = null;
			if (offenderBookId != null && livingUnitId != null) {
				agyIntLocProfilesList = nonAssociationRepository.getNonAssociationConfigForLocation(livingUnitId);
			}
			if (offenderBookId != null && livingUnitCode != null) {
				agyIntLocProfilesList = nonAssociationRepository.getNonAssociationConfigForLocationCode(livingUnitCode);
			}
			if (agyIntLocProfilesList != null) {
				offenderNaDetailsList =getNonAssociationOffenders(offenderBookId, agyIntLocProfilesList);
			}
		} catch(Exception e){
			logger.error("Exeption in checkNonAssociationOffendersWithLivingUnit " , e);
		}
		return offenderNaDetailsList;
	}
	
	@Override
	public String getOffenderName(Long offenderBookId) {
		final String sql=getQuery("OIDBSIAP_GET_OFFENDER_NAME");
		String offenderName="";
		try {
			offenderName=namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderBookId",offenderBookId), String.class);
		}catch (Exception e) {
			logger.error(this.getClass().getName()+" Exeption in getOffenderName " , e);
		}
		return offenderName;
	}

}
