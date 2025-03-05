package net.syscon.s4.inst.movementexternal.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlInOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.OffenderExternalMovements;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.MovementReasons;
import net.syscon.s4.im.beans.SystemProfiles;
import net.syscon.s4.inst.movementexternal.OidunctaRepository;
import oracle.jdbc.OracleTypes;

/**
 * Class OidunctaRepositoryImpl
 */
@Repository
public class OidunctaRepositoryImpl extends RepositoryBase implements OidunctaRepository {

	private final Map<String, FieldMapper> referenceCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("EXPIRED_DATE", new FieldMapper("expiredDate")).put("DOMAIN", new FieldMapper("domain"))
			.put("FROM_CITY", new FieldMapper("fromCity")).put("ACTIVE_FLAG", new FieldMapper("activeFlag"))
			.put("MOVEMENT_REASON_CODE", new FieldMapper("movementReasonCode")).put("MODE", new FieldMapper("mode"))
			.put("DRV_MOVEMENT_TYPE", new FieldMapper("drvMovementType"))
			.put("MOVEMENT_TYPE", new FieldMapper("movementType"))
			.put("DRV_DIRECTION_CODE", new FieldMapper("drvDirectionCode")).put("LIST_SEQ", new FieldMapper("listSeq"))
			.put("CODE", new FieldMapper("code")).put("DESCRIPTION", new FieldMapper("description"))
			.put("DRV_MOVEMENT_REASON_CODE", new FieldMapper("drvMovementReasonCode"))
			.put("DIRECTION_CODE", new FieldMapper("directionCode")).put("TO_CITY", new FieldMapper("toCity"))
			.put("'1'", new FieldMapper("  '1' ")).build();
	private final Map<String, FieldMapper> movementReasonsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("MOVEMENT_REASON_CODE", new FieldMapper("movementReasonCode")).put("'1'", new FieldMapper("  '1' "))
			.build();
	private final Map<String, FieldMapper> offenderExternalMovementsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("REPORTING_TIME", new FieldMapper("reportingTime")).put("TO_AGY_LOC_ID", new FieldMapper("toAgyLocId"))
			.put("TO_ADDRESS_ID", new FieldMapper("toAddressId")).put("ESCORT_CODE", new FieldMapper("escortCode"))
			.put("APPLICATION_TIME", new FieldMapper("applicationTime")).put("TO_CITY", new FieldMapper("toCity"))
			.put("SEAL_FLAG", new FieldMapper("sealFlag")).put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("TO_PROV_STAT_CODE", new FieldMapper("toProvStatCode"))
			.put("ESCORT_TEXT", new FieldMapper("escortText")).put("PARENT_EVENT_ID", new FieldMapper("parentEventId"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("MOVEMENT_TYPE", new FieldMapper("movementType"))
			.put("TO_COUNTRY_CODE", new FieldMapper("toCountryCode"))
			.put("DIRECTION_CODE", new FieldMapper("directionCode")).put("COMMENT_TEXT", new FieldMapper("commentText"))
			.put("REPORTING_DATE", new FieldMapper("reportingDate"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId")).put("ACTIVE_FLAG", new FieldMapper("activeFlag"))
			.put("OJ_LOCATION_CODE", new FieldMapper("ojLocationCode"))
			.put("FROM_AGY_LOC_ID", new FieldMapper("fromAgyLocId"))
			.put("FROM_ADDRESS_ID", new FieldMapper("fromAddressId"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("APPLICATION_DATE", new FieldMapper("applicationDate"))
			.put("MOVEMENT_TIME", new FieldMapper("movementTime"))
			.put("INTERNAL_SCHEDULE_TYPE", new FieldMapper("internalScheduleType"))
			.put("INTERNAL_SCHEDULE_REASON_CODE", new FieldMapper("internalScheduleReasonCode"))
			.put("EVENT_ID", new FieldMapper("eventId")).put("FROM_CITY", new FieldMapper("fromCity"))
			.put("MOVEMENT_SEQ", new FieldMapper("movementSeq"))
			.put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId")).put("0", new FieldMapper("0"))
			.put("MOVEMENT_REASON_CODE", new FieldMapper("movementReasonCode"))
			.put("NVL(MAX(MOVEMENT_SEQ)", new FieldMapper(" nvl(max(movementSeq)"))
			.put("ARREST_AGENCY_LOC_ID", new FieldMapper("arrestAgencyLocId"))
			.put("MOVEMENT_DATE", new FieldMapper("movementDate")).build();
	private final Map<String, FieldMapper> systemProfilesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PROFILE_CODE", new FieldMapper("profileCode")).put("PROFILE_TYPE", new FieldMapper("profileType"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId")).put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("OLD_TABLE_NAME", new FieldMapper("oldTableName"))
			.put("PROFILE_VALUE", new FieldMapper("profileValue"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("PROFILE_VALUE_2", new FieldMapper("profileValue2")).put("DESCRIPTION", new FieldMapper("description"))
			.build();
	private final Map<String, FieldMapper> agencyLocationsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DRV_FROM_AGY_LOC_ID", new FieldMapper("drvFromAgyLocId"))
			.put("FROM_AGY_LOC_ID", new FieldMapper("fromAgyLocId"))
			.put("DIRECTION_CODE", new FieldMapper("directionCode")).put("TO_AGY_LOC_ID", new FieldMapper("toAgyLocId"))
			.put("DRV_TO_AGY_LOC_ID", new FieldMapper("drvToAgyLocId"))
			.put("DRV_DIRECTION_CODE", new FieldMapper("drvDirectionCode"))
			.put("ACTIVE_FLAG", new FieldMapper("activeFlag")).build();

	/**
	 * Creates new OidunctaRepositoryImpl class Object
	 */
	public OidunctaRepositoryImpl() {
	}

	private static Logger logger = LogManager.getLogger(OidunctaRepositoryImpl.class.getName());

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao OffenderExternalMovements
	 *
	 * @return List<OffenderExternalMovements>
	 *
	 */
	public OffenderExternalMovements offEmExecuteQuery(final OffenderExternalMovements objSearchDao) {
		final OffenderExternalMovements returnList;
		final String sql = getQuery("OIDUNCTA_OFFEM_FIND_OFFENDER_EXTERNAL_MOVEMENTS");
		final RowMapper<OffenderExternalMovements> OffenderExternalMovementsRowMapper = Row2BeanRowMapper
				.makeMapping(sql, OffenderExternalMovements.class, offenderExternalMovementsMapping);
		returnList = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("offenderBookId", objSearchDao.getOffenderBookId()), OffenderExternalMovementsRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstOffenderExternalMovements List<OffenderExternalMovements>
	 *
	 * @return List<Integer>
	 *
	 */
	public Integer offEmInsertOffenderExternalMovements(
			final List<OffenderExternalMovements> lstOffenderExternalMovements) {
		final String sql = getQuery("OIDUNCTA_OFFEM_INSERT_OFFENDER_EXTERNAL_MOVEMENTS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderExternalMovements list : lstOffenderExternalMovements) {
			list.setMovementSeq(getMovementSeq(list.getOffenderBookId().toString()));
			parameters.add(new BeanPropertySqlParameterSource(list));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("Insert: ", e);
		}
		if (lstOffenderExternalMovements.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	public Integer updateOffenderBookings(final List<OffenderExternalMovements> lstOffenderExternalMovements) {
		final String sql = getQuery("OIDUNCTA_OFFEM_UPDATE_OFFENDER_BOOKINGS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderExternalMovements list : lstOffenderExternalMovements) {
			parameters.add(new BeanPropertySqlParameterSource(list));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("UpdateOffenderBokking: ", e);
		}
		if (lstOffenderExternalMovements.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	public Integer updateExternalMovements(final List<OffenderExternalMovements> lstOffenderExternalMovements) {
		final String sql = getQuery("OIDUNCTA_OFFEM_UPDATE_EXTERNALMOVEMENTS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderExternalMovements list : lstOffenderExternalMovements) {
			parameters.add(new BeanPropertySqlParameterSource(list));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("updateExternalMovements: ", e);
		}
		if (lstOffenderExternalMovements.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	public Long getMovementSeq(final String offBookId) {
		Long movementSeq = null;
		final String sql = getQuery("OCDUNCTA_GENERATE_MOVEMENTSEQ");
		movementSeq = namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderBookId", offBookId),
				Long.class);
		return movementSeq;
	}

	public String getToAgyLocId(final String agylocIdDesc) {
		String movementSeq = null;
		final String sql = getQuery("OCDUNCTA_GENERATE_TOAGYLOCID");
		movementSeq = namedParameterJdbcTemplate.queryForObject(sql, createParams("agylocIdDesc", agylocIdDesc),
				String.class);
		return movementSeq;
	}

	public String getToCity(final String toCityDesc) {
		String toCity = null;
		final String sql = getQuery("OCDUNCTA_GENERATE_TOCITY");
		toCity = namedParameterJdbcTemplate.queryForObject(sql, createParams("toCityDesc", toCityDesc), String.class);
		return toCity;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao SystemProfiles
	 *
	 * @return List<SystemProfiles>
	 *
	 */
	public List<SystemProfiles> sysPflExecuteQuery(final SystemProfiles objSearchDao) {
		final String sql = getQuery("OIDUNCTA_SYSPFL_FIND_SYSTEM_PROFILES");
		final RowMapper<SystemProfiles> SystemProfilesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				SystemProfiles.class, systemProfilesMapping);
		final ArrayList<SystemProfiles> returnList = (ArrayList<SystemProfiles>) namedParameterJdbcTemplate.query(sql,
				createParams(), SystemProfilesRowMapper);
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<CityRefCode3.descriptionAgyLocId>
	 */
	public List<AgencyLocations> cgfkOffEmFromAgyLocIdRecordGroup(final String toagyLocId) {
		final String sql = getQuery("OIDUNCTA_FIND_CGFKOFFEMFROMAGYLOCID");
		final RowMapper<AgencyLocations> agyLocIdRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				agencyLocationsMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("toagyLocId", toagyLocId), agyLocIdRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<AgencyLocations>
	 */
	public List<AgencyLocations> cgfkOffEmToAgyLocIdRecordGroup(final String directionCode, final String fromAgyLocId) {
		final String sql = getQuery("OIDUNCTA_FIND_CGFKOFFEMTOAGYLOCID");
		final RowMapper<AgencyLocations> agencyLocationsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				AgencyLocations.class, agencyLocationsMapping);
		try {
			return namedParameterJdbcTemplate.query(sql,
					createParams("directionCode", directionCode, "fromAgyLocId", fromAgyLocId),
					agencyLocationsRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> cgfkOffEmMovementTypeRecordGroup() {
		final String sql = getQuery("OIDUNCTA_FIND_CGFKOFFEMMOVEMENTTYPE");
		final RowMapper<ReferenceCodes> referenceCodesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, referenceCodesMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), referenceCodesRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> cgfkOffEmMovementReasonCoRecordGroup(final String movementType) {
		final String sql = getQuery("OIDUNCTA_FIND_CGFKOFFEMMOVEMENTREASONCO");
		final RowMapper<ReferenceCodes> referenceCodesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, referenceCodesMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("movementType", movementType),
					referenceCodesRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> cgfkOffEmToCityRecordGroup() {
		final String sql = getQuery("OIDUNCTA_FIND_CGFKOFFEMTOCITY");
		final RowMapper<ReferenceCodes> referenceCodesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, referenceCodesMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), referenceCodesRowMapper);
		} catch (Exception e) {
			return Collections.emptyList();
		}
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offEmPreInsert
	 *
	 * @param params
	 *
	 */
	public OffenderExternalMovements offEmPreInsert(final OffenderExternalMovements paramBean) {
		final String sql = getQuery("OIDUNCTA_OFF_EM_PREINSERT");
		OffenderExternalMovements returnObj;
		final RowMapper<OffenderExternalMovements> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderExternalMovements.class, offenderExternalMovementsMapping);
		returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOffEmOffEmRefFro
	 *
	 * @param params
	 *
	 */
	public ReferenceCodes cgfkchkOffEmOffEmRefFro(final ReferenceCodes paramBean) {
		final String sql = getQuery("OIDUNCTA_CGFKCHK_OFF_EM_OFF_EM_REF_FRO");
		ReferenceCodes returnObj;
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOffEmOffEmRefTo
	 *
	 * @param params
	 *
	 */
	public ReferenceCodes cgfkchkOffEmOffEmRefTo(final ReferenceCodes paramBean) {
		final String sql = getQuery("OIDUNCTA_CGFKCHK_OFF_EM_OFF_EM_REF_TO_");
		ReferenceCodes returnObj;
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOffEmOffEmRefMov
	 *
	 * @param params
	 *
	 */
	public ReferenceCodes cgfkchkOffEmOffEmRefMov(final ReferenceCodes paramBean) {
		final String sql = getQuery("OIDUNCTA_CGFKCHK_OFF_EM_OFF_EM_REF_MOV");
		ReferenceCodes returnObj;
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOffEmOffEmMoveRs
	 *
	 * @param params
	 *
	 */
	public MovementReasons cgfkchkOffEmOffEmMoveRs(final MovementReasons paramBean) {
		final String sql = getQuery("OIDUNCTA_CGFKCHK_OFF_EM_OFF_EM_MOVE_RS");
		MovementReasons returnObj;
		final RowMapper<MovementReasons> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, MovementReasons.class,
				movementReasonsMapping);
		returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOffEmOffEmDirecti
	 *
	 * @param params
	 *
	 */
	public ReferenceCodes cgfkchkOffEmOffEmDirecti(final ReferenceCodes paramBean) {
		final String sql = getQuery("OIDUNCTA_CGFKCHK_OFF_EM_OFF_EM_DIRECTI");
		ReferenceCodes returnObj;
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOffEmOffEmMoveRe
	 *
	 * @param params
	 *
	 */
	public ReferenceCodes cgfkchkOffEmOffEmMoveRe(final ReferenceCodes paramBean) {
		final String sql = getQuery("OIDUNCTA_CGFKCHK_OFF_EM_OFF_EM_MOVE_RE");
		ReferenceCodes returnObj;
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOffEmOffEmAgyLoc
	 *
	 * @param params
	 *
	 */
	public List<AgencyLocations> cgfkchkOffEmOffEmAgyLoc() {
		List<AgencyLocations> returnObj = new ArrayList<>();
		final String sql = getQuery("OIDUNCTA_CGFKCHK_OFF_EM_OFF_EM_AGY_LOC");
		final RowMapper<AgencyLocations> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				agencyLocationsMapping);
		returnObj = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOffEmOffEmAgy
	 *
	 * @param params
	 *
	 */
	public AgencyLocations cgfkchkOffEmOffEmAgy(final AgencyLocations paramBean) {
		final String sql = getQuery("OIDUNCTA_CGFKCHK_OFF_EM_OFF_EM_AGY_2");
		AgencyLocations returnObj;
		final RowMapper<AgencyLocations> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				agencyLocationsMapping);
		returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	public String getNextDirectionCode(final Integer offBookId) {
		String value;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[2];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_BOOKING_ID", OracleTypes.INTEGER),
				new SqlInOutParameter("RETURN_VALUE", OracleTypes.VARCHAR) };
		final SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("OMS_MOVEMENTS").withFunctionName("GET_NEXT_DIRECTION_CODE") 
				.declareParameters(sqlParameters);
		inParamMap.put("P_BOOKING_ID", offBookId);
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		value = simpleJDBCCall.executeFunction(String.class, inParameter);
		return value;
	}

	public String getLastMovementReasonCode(final Integer offBookId) {
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		String value;
		SqlParameter[] sqlParameters = new SqlParameter[2];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_BOOKING_ID", OracleTypes.INTEGER),
				new SqlInOutParameter("RETURN_VALUE", OracleTypes.VARCHAR) };
		final SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("OMS_MOVEMENTS").withFunctionName("GET_LAST_MOVEMENT_REASON_CODE") 
				.declareParameters(sqlParameters);
		inParamMap.put("P_BOOKING_ID", offBookId);
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		value = simpleJDBCCall.executeFunction(String.class, inParameter);
		return value;
	}

	public String getLastFromAgyLocId(final Integer offBookId) {
		String value;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[2];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_BOOKING_ID", OracleTypes.INTEGER),
				new SqlInOutParameter("RETURN_VALUE", OracleTypes.VARCHAR) };
		final SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("OMS_MOVEMENTS").withFunctionName("GET_LAST_FROM_AGY_LOC_ID") 
				.declareParameters(sqlParameters);
		inParamMap.put("P_BOOKING_ID", offBookId);
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		value = simpleJDBCCall.executeFunction(String.class, inParameter);
		return value;
	}

	public String getLastToAgyLocId(final Integer offBookId) {
		String value;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[2];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_BOOKING_ID", OracleTypes.INTEGER),
				new SqlInOutParameter("RETURN_VALUE", OracleTypes.VARCHAR) };
		final SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("OMS_MOVEMENTS").withFunctionName("GET_LAST_TO_AGY_LOC_ID") 
				.declareParameters(sqlParameters);
		inParamMap.put("P_BOOKING_ID", offBookId);
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		value = simpleJDBCCall.executeFunction(String.class, inParameter);
		return value;
	}

	public String getLastMovementCode(final Integer offBookId) {
		String value;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[2];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_BOOKING_ID", OracleTypes.INTEGER),
				new SqlInOutParameter("RETURN_VALUE", OracleTypes.VARCHAR) };
		final SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("OMS_MOVEMENTS").withFunctionName("GET_LAST_MOVEMENT_CODE") 
				.declareParameters(sqlParameters);
		inParamMap.put("P_BOOKING_ID", offBookId);
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		value = simpleJDBCCall.executeFunction(String.class, inParameter);
		return value;
	}

	public String getLastFromCity(final Integer offBookId) {
		String value;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[2];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_BOOKING_ID", OracleTypes.INTEGER),
				new SqlInOutParameter("RETURN_VALUE", OracleTypes.VARCHAR) };
		final SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("OMS_MOVEMENTS").withFunctionName("GET_LAST_FROM_CITY")
				.declareParameters(sqlParameters);
		inParamMap.put("P_BOOKING_ID", offBookId);
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		value = simpleJDBCCall.executeFunction(String.class, inParameter);
		return value;
	}

	public Date getLastMovementDateTime(final Integer offBookId) {
		final String sql = getQuery("OIDUNCTA_MOVEMENT_CUR_LASTMOVETIMEDATE");
		final RowMapper<OffenderExternalMovements> offenderRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderExternalMovements.class, offenderExternalMovementsMapping);
		OffenderExternalMovements returnList = new OffenderExternalMovements();
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderBookId", offBookId),
					offenderRowMapper);
		} catch (Exception e) {
			returnList = new OffenderExternalMovements();
		}
		return returnList.getMovementTime();
	}

	public Integer toggleInOutStatus(final Long offBookId) {
		Integer returnVal = null;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[20];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_BOOK_ID", OracleTypes.NUMBER) };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("OMKHEAD").withProcedureName("TOGGLE_IN_OUT_STATUS").declareParameters(sqlParameters); 
		inParamMap.put("P_BOOK_ID", offBookId);
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			 simpleJDBCCall.execute(inParameter);
		} catch (Exception e) {
			logger.error("toggleInOutStatus: ", e);
			returnVal = 1;
		}
		return returnVal;
	}

	@Override
	public OffenderExternalMovements getOldRecordOfOffExeMvmt(OffenderExternalMovements offExeM) {
		final String sqlQuery = getQuery("GET_OLD_RECORD_OF_OFF_EXE_MVMT");
		return namedParameterJdbcTemplate.queryForObject(sqlQuery,
				createParams("offenderBookId", offExeM.getOffenderBookId(), "movementSeq", offExeM.getMovementSeq()),
				new BeanPropertyRowMapper<OffenderExternalMovements>(OffenderExternalMovements.class));
	}

	@Override
	public OffenderBookings getOldOffBkgsBean(final Long offenderBookId) {
		final String sqlQuery = getQuery("GET_OLD_OFF_BKGS_BEAN");
		OffenderBookings bean = new OffenderBookings();
		try {
			bean = namedParameterJdbcTemplate.queryForObject(sqlQuery, createParams("offenderBookId", offenderBookId),
					new BeanPropertyRowMapper<OffenderBookings>(OffenderBookings.class));
		} catch (Exception e) {
			logger.error("getOldOffBkgsBean", e);
		}
		return bean;
	}

}
