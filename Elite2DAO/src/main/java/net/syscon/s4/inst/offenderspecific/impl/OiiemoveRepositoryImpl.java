package net.syscon.s4.inst.offenderspecific.impl;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlInOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.OffenderExternalMovements;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.inst.offenderspecific.OiiemoveRepository;
import oracle.jdbc.OracleTypes;
/**
 * Class OiiemoveRepositoryImpl
 */
@Repository
public class OiiemoveRepositoryImpl extends RepositoryBase implements OiiemoveRepository{
	
	public static final String ENTER_QUERY = "ENTER-QUERY";
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OiiemoveRepositoryImpl.class.getName());


         private final Map<String, FieldMapper> refCodeMapping = new ImmutableMap.Builder<String, FieldMapper>()
        .put("EXPIRED_DATE", 						new FieldMapper("expiredDate"))
        .put("LIST_SEQ", 						    new FieldMapper("listSeq"))
        .put("DOMAIN", 						        new FieldMapper("domain"))
        .put("ACTIVE_FLAG", 					    new FieldMapper("activeFlag"))
        .put("MOVEMENT_REASON_CODE", 			    new FieldMapper("movementReasonCode"))
        .put("DESCRIPTION", 						new FieldMapper("description"))
        .put("DIRECTION_CODE", 						new FieldMapper("directionCode"))
        .put("MOVEMENT_TYPE", 						new FieldMapper("movementType"))
        .build();
        private final Map<String, FieldMapper> offExtMovMapping = new ImmutableMap.Builder<String, FieldMapper>()
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
        .put("MOVEMENT_DATE", 		   new FieldMapper("movementDate"))
        .build();
         private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
        .put("EXPIRED_DATE", 				new FieldMapper("expiredDate"))
        .put("CODE", 						new FieldMapper("code"))
        .put("DOMAIN", 						new FieldMapper("domain"))
        .put("DESCRIPTION", 				new FieldMapper("description"))
        .build();
         private final Map<String, FieldMapper> sysProfMapping = new ImmutableMap.Builder<String, FieldMapper>()
        .put("PROFILE_VALUE_2", 			new FieldMapper(" profileValue2 "))
        .build();
         private final Map<String, FieldMapper> offBookMapping = new ImmutableMap.Builder<String, FieldMapper>()
        .put("OFFENDER_ID", 			new FieldMapper("offenderId"))
        .put("OFFENDER_BOOK_ID", 		new FieldMapper("offenderBookId"))
        .put("HEIGHT_CM", 				new FieldMapper("heightCm"))
        .put("WEIGHT_KG", 				new FieldMapper("weightKg"))
        .build();
         private final Map<String, FieldMapper> agyLocMapping = new ImmutableMap.Builder<String, FieldMapper>()
        .put("AGY_LOC_ID", new FieldMapper("agyLocId"))
        .put("DESCRIPTION", new FieldMapper("description"))
        .build();



	/**
	 * Creates new OiiemoveRepositoryImpl class Object
	 */
	public OiiemoveRepositoryImpl() {
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            OffenderExternalMovements
	 *
	 * @return List<OffenderExternalMovements>
	 *
	 *
	 */
	public List<OffenderExternalMovements> offEmExecuteQuery(final OffenderExternalMovements objSearchDao) {

		final String sql = getQuery("OIIEMOVE_OFFEM_FIND_OFFENDER_EXTERNAL_MOVEMENTS");

		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource valuesList = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" where ");

			if (objSearchDao.getOffenderBookId() != null) {
				sqlQuery.append(
						"OFFENDER_BOOK_ID =  :offenderBookId ORDER BY movement_seq  DESC");
				valuesList.addValue("offenderBookId", objSearchDao.getOffenderBookId());
			}
			if (objSearchDao.getRootOffenderId() != null) {
				sqlQuery.append(
						"OFFENDER_BOOK_ID  IN (SELECT OFFENDER_BOOK_ID FROM OFFENDER_BOOKINGS WHERE ROOT_OFFENDER_ID = :rootOffenderId)"
								+ "  ORDER BY movement_seq  DESC");
				valuesList.addValue("rootOffenderId", objSearchDao.getRootOffenderId());

			}
		}
		preparedSql = sqlQuery.toString().trim();

		final RowMapper<OffenderExternalMovements> offExMovRMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderExternalMovements.class, offExtMovMapping);
		List<OffenderExternalMovements> returnList = new ArrayList<OffenderExternalMovements>();
		returnList = namedParameterJdbcTemplate.query(preparedSql, valuesList, offExMovRMapper);
		return returnList;
	}

	/**
	 * @param
	 *
	 *
	 */
	public int preInsert() {
		return 0;
	}

	/**
	 * Used to getBooking no for the Offender
	 * 
	 * @return String
	 */
	public String getOffenderBookingNo(final Long offBookId) {
		final String sql = getQuery("OIIEMOVE_OFF_EM1_POSTQUERY");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("OFFENDERBOOKID", offBookId), String.class);
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgOffEmMovementReasonCoRecordGroup() {
		final String sql = getQuery("OIIEMOVE_FIND_RGOFFEMMOVEMENTREASONCO");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("MODE", ENTER_QUERY), mRowMapper);
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgOffEmMovementTypeRecordGroup() {
		final String sql = getQuery("OIIEMOVE_FIND_RGOFFEMMOVEMENTTYPE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("MODE", ENTER_QUERY), mRowMapper);
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgOffEm1DirectionCodeRecordGroup() {
		final String sql = getQuery("OIIEMOVE_FIND_RGOFFEM1DIRECTIONCODE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("MODE", ENTER_QUERY), mRowMapper);
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodesM>
	 */
	public List<ReferenceCodes> rgOffEm1MovementTypeRecordGroup() {
		final String sql = getQuery("OIIEMOVE_FIND_RGOFFEM1MOVEMENTTYPE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("MODE", ENTER_QUERY), mRowMapper);
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgOffEm1MovementReasonCRecordGroup() {
		final String sql = getQuery("OIIEMOVE_FIND_RGOFFEM1MOVEMENTREASONC");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("MODE", ENTER_QUERY), mRowMapper);
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgOffEmDirectionCodeRecordGroup() {
		final String sql = getQuery("OIIEMOVE_FIND_RGOFFEMDIRECTIONCODE");
		List<ReferenceCodes> lstRef = new ArrayList<>();
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			lstRef = (List<ReferenceCodes>) namedParameterJdbcTemplate.query(sql, createParams("MODE", ENTER_QUERY), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("In rgOffEmDirectionCodeRecordGroup method : ", e);
		}
		return lstRef;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offBkgOnCheckDeleteMaster
	 *
	 * @param params
	 *
	 */
	public OffenderExternalMovements offBkgOnCheckDeleteMaster(final OffenderExternalMovements paramBean) {
		final String sql = getQuery("OIIEMOVE_OFF_BKG_ONCHECKDELETEMASTER");
		final RowMapper<OffenderExternalMovements> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderExternalMovements.class, offExtMovMapping);
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offEm1PostQuery
	 *
	 * @param params
	 *
	 */
	public OffenderBookings offEm1PostQuery(final OffenderBookings paramBean) {
		final String sql = getQuery("OIIEMOVE_OFF_EM1_POSTQUERY");
		final RowMapper<OffenderBookings> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderBookings.class,
				offBookMapping);
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
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
		final String sql = getQuery("OIIEMOVE_CGFKCHK_OFF_EM_OFF_EM_REF_MOV");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				refCodeMapping);
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * getProfileValue
	 *
	 * @param params
	 *
	 */
	public SystemProfiles getProfileValue(final SystemProfiles paramBean) {
		final String sql = getQuery("OIIEMOVE_GET_PROFILE_VALUE_2");
		final RowMapper<SystemProfiles> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, SystemProfiles.class,
				sysProfMapping);
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
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
		final String sql = getQuery("OIIEMOVE_CGFKCHK_OFF_EM_OFF_EM_DIRECTI");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				refCodeMapping);
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
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
		final String sql = getQuery("OIIEMOVE_CGFKCHK_OFF_EM_OFF_EM_MOVE_RE");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				refCodeMapping);
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOffEm1OffEmRefMo
	 *
	 * @param params
	 *
	 */
	public ReferenceCodes cgfkchkOffEm1OffEmRefMo(final ReferenceCodes paramBean) {
		final String sql = getQuery("OIIEMOVE_CGFKCHK_OFF_EM1_OFF_EM_REF_MO");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				refCodeMapping);
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOffEm1OffEmDirect
	 *
	 * @param params
	 *
	 */
	public ReferenceCodes cgfkchkOffEm1OffEmDirect(final ReferenceCodes paramBean) {
		final String sql = getQuery("OIIEMOVE_CGFKCHK_OFF_EM1_OFF_EM_DIRECT");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				refCodeMapping);
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOffEm1OffEmMov2
	 *
	 * @param params
	 *
	 */
	public ReferenceCodes cgfkchkOffEm1OffEmMov2(final ReferenceCodes paramBean) {
		final String sql = getQuery("OIIEMOVE_CGFKCHK_OFF_EM1_OFF_EM_MOV2");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				refCodeMapping);
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
	}

	public String agyLocationDescription(final String agyLocId) {
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[2];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_AGY_LOC_ID", OracleTypes.VARCHAR),
				new SqlInOutParameter("P_RETURN", OracleTypes.VARCHAR) };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_ESTABLISHMENT").withFunctionName("GET_AGY_LOC_DESC")
				.declareParameters(sqlParameters);
		inParamMap.put("P_AGY_LOC_ID", agyLocId);
		inParamMap.put("P_RETURN", "true");
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		return simpleJDBCCall.executeFunction(String.class, inParameter);

	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<AgencyLocations>
	 */
	public List<AgencyLocations> alAgyLocIdRgRecordGroup() {
		final String sql = getQuery("OIIEMOVE_FIND_ALAGYLOCIDRG");
		final RowMapper<AgencyLocations> agyLocRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				agyLocMapping);
	 return namedParameterJdbcTemplate.query(sql, createParams("MODE", ENTER_QUERY),
						agyLocRowMapper);
				
			
	}
	
	/**
	 * Used to get City Description based on City Code
	 * 
	 * @return String
	 */
	public String getCityDescFromCode(final String toCity) {
		final String sql = getQuery("OIIEMOVE_FIND_CITY_DESCRIPTION");
		try{
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("toCity", toCity), String.class);
		} catch (Exception e) {
			return "";
		}
	} 

	/**
	 * Used to getFullAddress based on addressId
	 * 
	 * @return String
	 */
	public String getFullAddress(final BigDecimal addressId) {
		final String sql = getQuery("OIIEMOVE_FIND_ADDRESS_DESCRIPTION");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("addressId", addressId), String.class);
	} 


}
