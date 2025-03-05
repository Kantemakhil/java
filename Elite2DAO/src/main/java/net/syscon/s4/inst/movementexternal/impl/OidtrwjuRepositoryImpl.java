package net.syscon.s4.inst.movementexternal.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.OffenderExternalMovements;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.Dual;
import net.syscon.s4.im.intake.beans.MovementReasons;
import net.syscon.s4.inst.movementexternal.OidtrwjuRepository;
import oracle.jdbc.OracleTypes;

@Repository
public class OidtrwjuRepositoryImpl extends RepositoryBase implements OidtrwjuRepository {
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger log = LogManager.getLogger(OidtrwjuRepositoryImpl.class.getName());
	
	private final Map<String, FieldMapper> movementreasonsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("MOVEMENT_REASON_CODE",   new FieldMapper("movementReasonCode"))
			.put("MOVEMENT_TYPE",          new FieldMapper("movementType"))
			.put("DESCRIPTION",            new FieldMapper("description"))
			.put("ACTIVE_FLAG", 		   new FieldMapper("activeFlag")).build();
	private final Map<String, FieldMapper> offenderExternalMovementsMapping1 = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PROFILE_CODE",           new FieldMapper("profileCode"))
			.put("REPORTING_TIME",         new FieldMapper("reportingTime"))
			.put("TO_AGY_LOC_ID",          new FieldMapper("toAgyLocId"))
			.put("TO_ADDRESS_ID",          new FieldMapper("toAddressId"))
			.put("ESCORT_CODE",            new FieldMapper("escortCode"))
			.put("APPLICATION_TIME",       new FieldMapper("applicationTime"))
			.put("TO_CITY",  			   new FieldMapper("toCity"))
			.put("SEAL_FLAG",			   new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME",		   new FieldMapper("createDatetime"))
			.put("TO_PROV_STAT_CODE",      new FieldMapper("toProvStatCode"))
			.put("ESCORT_TEXT", 		   new FieldMapper("escortText"))
			.put("PARENT_EVENT_ID", 	   new FieldMapper("parentEventId"))
			.put("MODIFY_DATETIME", 	   new FieldMapper("modifyDatetime"))
			.put("PROFILE_VALUE", 		   new FieldMapper("profileValue"))
			.put("MOVEMENT_TYPE", 		   new FieldMapper("movementType"))
			.put("TO_COUNTRY_CODE", 	   new FieldMapper("toCountryCode"))
			.put("DESCRIPTION", 		   new FieldMapper("description"))
			.put("DIRECTION_CODE", 		   new FieldMapper("directionCode"))
			.put("OFFENDER_BOOK_ID", 	   new FieldMapper("offenderBookId"))
			.put("COMMENT_TEXT", 		   new FieldMapper("commentText"))
			.put("REPORTING_DATE", 		   new FieldMapper("reportingDate"))
			.put("CREATE_USER_ID", 		   new FieldMapper("createUserId"))
			.put("PROFILE_TYPE", 		   new FieldMapper("profileType"))
			.put("ACTIVE_FLAG", 		   new FieldMapper("activeFlag"))
			.put("OJ_LOCATION_CODE", 	   new FieldMapper("ojLocationCode"))
			.put("FROM_AGY_LOC_ID",        new FieldMapper("fromAgyLocId"))
			.put("FROM_ADDRESS_ID", 	   new FieldMapper("fromAddressId"))
			.put("MODIFY_USER_ID", 		   new FieldMapper("modifyUserId"))
			.put("APPLICATION_DATE", 	   new FieldMapper("applicationDate"))
			.put("OLD_TABLE_NAME", 		   new FieldMapper("oldTableName"))
			.put("MOVEMENT_TIME", 		   new FieldMapper("movementTime"))
			.put("INTERNAL_SCHEDULE_TYPE", new FieldMapper("internalScheduleType"))
			.put("INTERNAL_SCHEDULE_REASON_CODE", new FieldMapper("internalScheduleReasonCode"))
			.put("EVENT_ID",			   new FieldMapper("eventId"))
			.put("PROFILE_VALUE_2", 	   new FieldMapper("profileValue2"))
			.put("FROM_CITY", 			   new FieldMapper("fromCity"))
			.put("MOVEMENT_SEQ", 		   new FieldMapper("movementSeq"))
			.put("MOVEMENT_REASON_CODE",   new FieldMapper("movementReasonCode"))
			.put("ARREST_AGENCY_LOC_ID",   new FieldMapper("arrestAgencyLocId"))
			.put("MOVEMENT_DATE", 		   new FieldMapper("movementDate")).build();
	private final Map<String, FieldMapper> systemProfilesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PROFILE_CODE",   		   new FieldMapper("profileCode"))
			.put("REPORTING_TIME", 		   new FieldMapper("reportingTime"))
			.put("TO_AGY_LOC_ID",     	   new FieldMapper("toAgyLocId"))
			.put("TO_ADDRESS_ID",   	   new FieldMapper("toAddressId"))
			.put("ESCORT_CODE",   		   new FieldMapper("escortCode"))
			.put("APPLICATION_TIME",  	   new FieldMapper("applicationTime"))
			.put("TO_CITY",                new FieldMapper("toCity"))
			.put("SEAL_FLAG",              new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME",        new FieldMapper("createDatetime"))
			.put("TO_PROV_STAT_CODE",      new FieldMapper("toProvStatCode"))
			.put("ESCORT_TEXT",            new FieldMapper("escortText"))
			.put("PARENT_EVENT_ID",        new FieldMapper("parentEventId"))
			.put("MODIFY_DATETIME",        new FieldMapper("modifyDatetime"))
			.put("PROFILE_VALUE",          new FieldMapper("profileValue"))
			.put("MOVEMENT_TYPE",          new FieldMapper("movementType"))
			.put("TO_COUNTRY_CODE",        new FieldMapper("toCountryCode"))
			.put("DESCRIPTION",            new FieldMapper("description"))
			.put("DIRECTION_CODE",         new FieldMapper("directionCode"))
			.put("OFFENDER_BOOK_ID",       new FieldMapper("offenderBookId"))
			.put("COMMENT_TEXT",           new FieldMapper("commentText"))
			.put("REPORTING_DATE",         new FieldMapper("reportingDate"))
			.put("CREATE_USER_ID",         new FieldMapper("createUserId"))
			.put("PROFILE_TYPE",           new FieldMapper("profileType"))
			.put("ACTIVE_FLAG", 		   new FieldMapper("activeFlag"))
			.put("OJ_LOCATION_CODE",       new FieldMapper("ojLocationCode"))
			.put("FROM_AGY_LOC_ID",        new FieldMapper("fromAgyLocId"))
			.put("FROM_ADDRESS_ID",        new FieldMapper("fromAddressId"))
			.put("MODIFY_USER_ID",         new FieldMapper("modifyUserId"))
			.put("APPLICATION_DATE",       new FieldMapper("applicationDate"))
			.put("OLD_TABLE_NAME",         new FieldMapper("oldTableName"))
			.put("MOVEMENT_TIME",          new FieldMapper("movementTime"))
			.put("INTERNAL_SCHEDULE_TYPE", new FieldMapper("internalScheduleType"))
			.put("INTERNAL_SCHEDULE_REASON_CODE", new FieldMapper("internalScheduleReasonCode"))
			.put("EVENT_ID",               new FieldMapper("eventId"))
			.put("PROFILE_VALUE_2",        new FieldMapper("profileValue2"))
			.put("FROM_CITY",              new FieldMapper("fromCity"))
			.put("MOVEMENT_SEQ",           new FieldMapper("movementSeq"))
			.put("MOVEMENT_REASON_CODE",   new FieldMapper("movementReasonCode"))
			.put("ARREST_AGENCY_LOC_ID",   new FieldMapper("arrestAgencyLocId"))
			.put("MOVEMENT_DATE",          new FieldMapper("movementDate")).build();
	private final Map<String, FieldMapper> agencyLocationsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("TO_AGY_LOC_ID",          new FieldMapper("agyLocId"))
			.put("DSP_DESCRIPTION2",       new FieldMapper("description"))
			.put("ACTIVE_FLAG", 		   new FieldMapper("activeFlag"))
			.build();
	/**
	 * Creates new OidtrwjuRepositoryImpl class Object
	 */
	public OidtrwjuRepositoryImpl() {
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<RecordGroup>
	 */
	public List<MovementReasons> cgfkOffEmMovementReasonCoRecordGroup() {
		final String sql = getQuery("OIDTRWJU_FIND_CGFKOFFEMMOVEMENTREASONCO");
		final RowMapper<MovementReasons> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,
				MovementReasons.class, movementreasonsMapping);
		List<MovementReasons> refList = new ArrayList<MovementReasons>();
		try {
			refList = namedParameterJdbcTemplate.query(sql, referenceCodeRowMapper);
		} catch (Exception e) {
			log.error("cgfkOffEmMovementReasonCoRecordGroup",e);
		}
		return refList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @param agencyLocations
	 * 
	 * @return List<RecordGroup>
	 */
	public List<AgencyLocations> cgfkOffEmToAgyLocIdRecordGroup(final String agyLocId) {
		final String sql = getQuery("OIDTRWJU_FIND_CGFKOFFEMTOAGYLOCID");
		final RowMapper<AgencyLocations> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,
				AgencyLocations.class, agencyLocationsMapping);
		List<AgencyLocations> refList = new ArrayList<AgencyLocations>();
		try {
			refList = namedParameterJdbcTemplate.query(sql, createParams("agyLocId", agyLocId),
					referenceCodeRowMapper);
		} catch (Exception e) {
			log.error("cgfkOffEmToAgyLocIdRecordGroup",e);
		}
		return refList;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            OffenderExternalMovements
	 *
	 * @return List<OffenderExternalMovements>
	 */
	public List<OffenderExternalMovements> offEmSearchOffenderExternalMovements(
			final OffenderExternalMovements objSearchDao) {
		final String sql = getQuery("OIDTRWJU_OFFEM_FIND_OFFENDER_EXTERNAL_MOVEMENTS");
		final RowMapper<OffenderExternalMovements> OffenderExternalMovementsRowMapper = Row2BeanRowMapper
				.makeMapping(sql, OffenderExternalMovements.class, offenderExternalMovementsMapping1);
		List<OffenderExternalMovements> reflist = new ArrayList<OffenderExternalMovements>();
		reflist = namedParameterJdbcTemplate.query(sql, createParams("offenderBookId", objSearchDao.getOffenderBookId()), OffenderExternalMovementsRowMapper);
		return reflist;
	}

	/**
	 * @param
	 *
	 * @throws SQLException
	 *
	 */
	public int preInsert() {
		return 0;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstOffenderExternalMovements
	 *            List<OffenderExternalMovements>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public Integer offEmInsertOffenderExternalMovements(
			final List<OffenderExternalMovements> lstOffenderExternalMovements) {
		String sql = getQuery("OIDTRWJU_OFFEM_INSERT_OFFENDER_EXTERNAL_MOVEMENTS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderExternalMovements offenderExternalMovements : lstOffenderExternalMovements) {
			parameters.add(new BeanPropertySqlParameterSource(offenderExternalMovements));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));

		if (lstOffenderExternalMovements.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}
	

	public Integer offExternalMovmentssgetMaxBookIdMovmentSeq(final Long offenderBookId) {
		final String sql = getQuery("OIDTRWJU_GET_MAX_VALUE");
		Integer refList;
		refList = namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderBookId", offenderBookId),
				Integer.class);
		return refList;

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            SystemProfiles
	 *
	 * @return List<SystemProfiles>
	 */
	public List<SystemProfiles> sysPflSearchSystemProfiles(final SystemProfiles objSearchDao) {
		final String sql = getQuery("OIDTRWJU_SYSPFL_FIND_SYSTEM_PROFILES");
		final RowMapper<SystemProfiles> SystemProfilesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				SystemProfiles.class, systemProfilesMapping);
		List<SystemProfiles> returnList = new ArrayList<SystemProfiles>();
		returnList = namedParameterJdbcTemplate.query(sql, createParams("profileType", objSearchDao.getProfileType(),
				"profileCode", objSearchDao.getProfileCode()), SystemProfilesRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offempreinsertc
	 *
	 * @param params
	 *
	 */
	public OffenderExternalMovements offempreinsertc(final OffenderExternalMovements paramBean) {
		final String sql = getQuery("OIDTRWJU_OFF_EM_PREINSERT_C");
		final RowMapper<OffenderExternalMovements> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderExternalMovements.class, offenderExternalMovementsMapping1);
		OffenderExternalMovements returnObj = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("OFFENDERBOOKID", paramBean.getOffenderBookId()), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * oidtrwjuCgfkchkoffemoffemrefmovc
	 *
	 * @param params
	 *
	 */
	public List<Object> cgfkchkoffemoffemrefmovc(final ReferenceCodes paramBean) {
		final String sql = getQuery("OIDTRWJU_CGFKCHK_OFF_EM_OFF_EM_REF_MOV_C");
		List<Object> returnObj = namedParameterJdbcTemplate.queryForList(sql,
				createParams("MOVEMENTREASONCODE", paramBean.getCode()), Object.class);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkoffemoffemmoversc
	 *
	 * @param params
	 *
	 */
	public List<Object> cgfkchkoffemoffemmoversc(final MovementReasons paramBean) {
		final String sql = getQuery("OIDTRWJU_CGFKCHK_OFF_EM_OFF_EM_MOVE_RS_C");
		List<Object> returnObj = namedParameterJdbcTemplate.queryForList(sql,
				createParams("MovementType", paramBean.getMovementType()), Object.class);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkoffemoffemmovetoc
	 *
	 * @param params
	 *
	 */
	public List<Object> cgfkchkoffemoffemmovetoc(final MovementReasons paramBean) {
		final String sql = getQuery("OIDTRWJU_CGFKCHK_OFF_EM_OFF_EM_MOVE2_C");
		List<Object> returnObj = namedParameterJdbcTemplate.queryForList(sql, createParams("MOVEMENTTYPE",
				paramBean.getMovementType(), "MOVEMENTREASONCODE", paramBean.getMovementReasonCode()), Object.class);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkoffemoffemagylocc
	 *
	 * @param params
	 *
	 */
	public List<Object> cgfkchkoffemoffemagylocc(final AgencyLocations paramBean) {
		final String sql = getQuery("OIDTRWJU_CGFKCHK_OFF_EM_OFF_EM_AGY_LOC_C");
		List<Object> returnObj = namedParameterJdbcTemplate.queryForList(sql,
				createParams("TOAGYLOCID", paramBean.getAgyLocId(), "AGYLOCID", paramBean.getAgyLocId()), Object.class);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgwhennewforminstancec
	 *
	 * @param params
	 *
	 */
	public List<Dual> cgwhennewforminstancec(final Dual paramBean) {
		final String sql = getQuery("OIDTRWJU_CGWHEN_NEW_FORM_INSTANCE_C");
		final List<Dual> returnList = namedParameterJdbcTemplate.queryForList(sql, createParams(paramBean), Dual.class);
		return returnList;
	}

	@Override
	public Integer offEmUpdateOffenderExternalMovements(final List<OffenderExternalMovements> updateList) {
		    String sql = getQuery("OIDTRWJU_OFFEM_UPDATE_OFFENDER_EXTERNAL_MOVEMENTS");
			int[] returnArray = new int[] {};
			List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
			for (final OffenderExternalMovements offenderExternalMovements : updateList) {
				parameters.add(new BeanPropertySqlParameterSource(offenderExternalMovements));
			}
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));

			if (updateList.size() == returnArray.length) {
				return 1;
			} else {
				return 0;
			}

		}

	@Override
	public Integer checkWaitListAndLocation(final OffenderExternalMovements bean) {
		final String sql = getQuery("OIDTRWJU_CHECKWAITLIST_AND_LOCATIONS");
		return  namedParameterJdbcTemplate.queryForObject(sql,createParams("OFFENDER_BOOK_ID",bean.getOffenderBookId(), 
							"MOVEMENT_DATE",bean.getMovementDate()), Integer.class);
			 
	}


	@Override
	public Integer suspendAllocations(final OffenderExternalMovements bean) {
		Integer returnValue = 0;
		try{
			Map<String, Object> inParamMap = new HashMap<>();
			SqlParameter[] sqlParameters = new SqlParameter[2];
			sqlParameters = new SqlParameter[] {
					new SqlParameter("P_OFFENDER_BOOK_ID", OracleTypes.NUMBER),
					new SqlParameter("P_DATE", OracleTypes.DATE)
					};
			SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER").withCatalogName("TAG_PRISON_ACTIVITIES").
												withProcedureName("SUSPEND_ALLOCATIONS").declareParameters(sqlParameters);
			inParamMap.put("P_OFFENDER_BOOK_ID", bean.getOffenderBookId());
			inParamMap.put("P_DATE", bean.getMovementDate());
			SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
				 simpleJDBCCall.execute(inParameter);
			returnValue =   1;
		}catch(Exception e){
			log.error("Exception in suspendAllocations: ",e);
			returnValue =   0;
		}
		return returnValue;
	}

	@Override
	public Integer endWaitlistAndAllocations(final OffenderExternalMovements bean) {
		Integer returnValue = 0;
		try{
			Map<String, Object> inParamMap = new HashMap<>();
			SqlParameter[] sqlParameters = new SqlParameter[3];
			sqlParameters = new SqlParameter[] {
					new SqlParameter("P_OFFENDER_BOOK_ID", OracleTypes.NUMBER),
					new SqlParameter("P_DATE", OracleTypes.DATE),
					new SqlParameter("P_END_REASON", OracleTypes.VARCHAR)
					
					};
			SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER").withCatalogName("TAG_PRISON_ACTIVITIES").
												withProcedureName("END_WAITLIST_AND_ALLOCATIONS").declareParameters(sqlParameters);
			inParamMap.put("P_OFFENDER_BOOK_ID", bean.getOffenderBookId());
			inParamMap.put("P_DATE", bean.getMovementDate());
			inParamMap.put("P_END_REASON", "TRF");
			SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
				simpleJDBCCall.execute(inParameter);
			returnValue =   1;
		}catch(Exception e){
			log.error("Exception in endWaitlistAndAllocations: ",e);
			returnValue =   0;
		}
		return returnValue;
	}

	@Override
	public OffenderExternalMovements getOffenderExternalMovements(Long offenderBookId, Long movementSeq) {
		final String sql = getQuery("OIDTRWJU_OLD_OFFENDER_EXTERNAL_MOVEMENTS");
		OffenderExternalMovements retObj=null;
		try {
			retObj=  namedParameterJdbcTemplate.queryForObject(sql,createParams("OFFENDER_BOOK_ID",offenderBookId,"MOVEMENT_SEQ",movementSeq), new BeanPropertyRowMapper<OffenderExternalMovements>(OffenderExternalMovements.class));
		} catch (Exception e) {
			log.error(e);
		}
		return retObj;
	}

}
