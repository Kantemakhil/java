package net.syscon.s4.inst.movementexternal.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
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
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.BedAssignmentHistories;
import net.syscon.s4.im.beans.Dual;
//import net.syscon.s4.im.intake.beans.MovementReasons;
import net.syscon.s4.im.beans.MovementReasons;
import net.syscon.s4.inst.legals.beans.OffenderSentenceAdjustment;
import net.syscon.s4.inst.movementexternal.OidreleaRepository;
import net.syscon.s4.inst.movementexternal.beans.OffenderEscapes;
import net.syscon.s4.inst.schedules.bean.OffenderReleaseDetails;
import oracle.jdbc.OracleTypes;

/**
 * Class OidreleaRepositoryImpl
 */
@Repository
public class OidreleaRepositoryImpl extends RepositoryBase implements OidreleaRepository {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidreleaRepositoryImpl.class.getName());
	
	private final Map<String, FieldMapper> sysDualMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("SYSDATE", 						new FieldMapper("sysDate"))
			.put("USER", 							new FieldMapper("user"))
			.build();
	private final Map<String, FieldMapper> referenceCodeMappingOfAllColumns = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CREATE_DATETIME", 				new FieldMapper("createDatetime"))
			.put("CREATE_USER_ID", 					new FieldMapper("createUserId"))
			.put("DESCRIPTION", 					new FieldMapper("description"))
			.put("CODE", 							new FieldMapper("code"))
			.put("DOMAIN", 							new FieldMapper("domain"))
			.put("PARENT_DOMAIN", 					new FieldMapper("parentDomainId"))
			.put("PARENT_CODE", 					new FieldMapper("parentCode"))
			.put("ACTIVE_FLAG", 					new FieldMapper("activeFlag"))
			.build();
	private final Map<String, FieldMapper> offenderExternalMovementsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PROFILE_CODE", 					new FieldMapper("profileCode"))
			.put("REPORTING_TIME", 					new FieldMapper("reportingTime"))
			.put("TO_AGY_LOC_ID", 					new FieldMapper("toAgyLocId"))
			.put("TO_ADDRESS_ID", 					new FieldMapper("toAddressId"))
			.put("ESCORT_CODE", 					new FieldMapper("escortCode"))
			.put("APPLICATION_TIME", 				new FieldMapper("applicationTime"))
			.put("TO_CITY", 						new FieldMapper("toCity"))
			.put("SEAL_FLAG", 						new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", 				new FieldMapper("createDatetime"))
			.put("TO_PROV_STAT_CODE", 				new FieldMapper("toProvStatCode"))
			.put("ESCORT_TEXT", 					new FieldMapper("escortText"))
			.put("PARENT_EVENT_ID", 				new FieldMapper("parentEventId"))
			.put("MODIFY_DATETIME", 				new FieldMapper("modifyDatetime"))
			.put("PROFILE_VALUE", 					new FieldMapper("profileValue"))
			.put("MOVEMENT_TYPE", 					new FieldMapper("movementType"))
			.put("TO_COUNTRY_CODE", 				new FieldMapper("toCountryCode"))
			.put("DESCRIPTION", 					new FieldMapper("description"))
			.put("DIRECTION_CODE", 					new FieldMapper("directionCode"))
			.put("OFFENDER_BOOK_ID", 				new FieldMapper("offenderBookId"))
			.put("COMMENT_TEXT", 					new FieldMapper("commentText"))
			.put("REPORTING_DATE", 					new FieldMapper("reportingDate"))
			.put("CREATE_USER_ID", 					new FieldMapper("createUserId"))
			.put("PROFILE_TYPE", 					new FieldMapper("profileType"))
			.put("ACTIVE_FLAG", 					new FieldMapper("activeFlag"))
			.put("OJ_LOCATION_CODE", 				new FieldMapper("ojLocationCode"))
			.put("FROM_AGY_LOC_ID", 				new FieldMapper("fromAgyLocId"))
			.put("FROM_ADDRESS_ID", 				new FieldMapper("fromAddressId"))
			.put("MODIFY_USER_ID", 					new FieldMapper("modifyUserId"))
			.put("APPLICATION_DATE", 				new FieldMapper("applicationDate"))
			.put("OLD_TABLE_NAME", 					new FieldMapper("oldTableName"))
			.put("MOVEMENT_TIME", 					new FieldMapper("movementTime"))
			.put("INTERNAL_SCHEDULE_TYPE", 			new FieldMapper("internalScheduleType"))
			.put("INTERNAL_SCHEDULE_REASON_CODE", 	new FieldMapper("internalScheduleReasonCode"))
			.put("EVENT_ID", 						new FieldMapper("eventId"))
			.put("PROFILE_VALUE_2", 				new FieldMapper("profileValue2"))
			.put("FROM_CITY", 						new FieldMapper("fromCity"))
			.put("MOVEMENT_SEQ", 					new FieldMapper("movementSeq"))
			.put("MOVEMENT_REASON_CODE", 			new FieldMapper("movementReasonCode"))
			.put("ARREST_AGENCY_LOC_ID", 			new FieldMapper("arrestAgencyLocId"))
			.put("MOVEMENT_DATE",					new FieldMapper("movementDate"))
			.build();
	private final Map<String, FieldMapper> systemProfilesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PROFILE_CODE", 					new FieldMapper("profileCode"))
			.put("REPORTING_TIME", 					new FieldMapper("reportingTime"))
			.put("TO_AGY_LOC_ID", 					new FieldMapper("toAgyLocId"))
			.put("TO_ADDRESS_ID", 					new FieldMapper("toAddressId"))
			.put("ESCORT_CODE", 					new FieldMapper("escortCode"))
			.put("APPLICATION_TIME", 				new FieldMapper("applicationTime"))
			.put("TO_CITY", 						new FieldMapper("toCity"))
			.put("SEAL_FLAG", 						new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", 				new FieldMapper("createDatetime"))
			.put("TO_PROV_STAT_CODE", 				new FieldMapper("toProvStatCode"))
			.put("ESCORT_TEXT", 					new FieldMapper("escortText"))
			.put("PARENT_EVENT_ID", 				new FieldMapper("parentEventId"))
			.put("MODIFY_DATETIME", 				new FieldMapper("modifyDatetime"))
			.put("PROFILE_VALUE", 					new FieldMapper("profileValue"))
			.put("MOVEMENT_TYPE", 					new FieldMapper("movementType"))
			.put("TO_COUNTRY_CODE", 				new FieldMapper("toCountryCode"))
			.put("DESCRIPTION", 					new FieldMapper("description"))
			.put("DIRECTION_CODE",					new FieldMapper("directionCode"))
			.put("OFFENDER_BOOK_ID", 				new FieldMapper("offenderBookId"))
			.put("COMMENT_TEXT", 					new FieldMapper("commentText"))
			.put("REPORTING_DATE", 					new FieldMapper("reportingDate"))
			.put("CREATE_USER_ID", 					new FieldMapper("createUserId"))
			.put("PROFILE_TYPE", 					new FieldMapper("profileType"))
			.put("ACTIVE_FLAG", 					new FieldMapper("activeFlag"))
			.put("OJ_LOCATION_CODE", 				new FieldMapper("ojLocationCode"))
			.put("FROM_AGY_LOC_ID", 				new FieldMapper("fromAgyLocId"))
			.put("FROM_ADDRESS_ID", 				new FieldMapper("fromAddressId"))
			.put("MODIFY_USER_ID", 					new FieldMapper("modifyUserId"))
			.put("APPLICATION_DATE", 				new FieldMapper("applicationDate"))
			.put("OLD_TABLE_NAME", 					new FieldMapper("oldTableName"))
			.put("MOVEMENT_TIME", 					new FieldMapper("movementTime"))
			.put("INTERNAL_SCHEDULE_TYPE", 			new FieldMapper("internalScheduleType"))
			.put("INTERNAL_SCHEDULE_REASON_CODE", 	new FieldMapper("internalScheduleReasonCode"))
			.put("EVENT_ID", 						new FieldMapper("eventId"))
			.put("PROFILE_VALUE_2", 				new FieldMapper("profileValue2"))
			.put("FROM_CITY", 						new FieldMapper("fromCity"))
			.put("MOVEMENT_SEQ", 					new FieldMapper("movementSeq"))
			.put("MOVEMENT_REASON_CODE", 			new FieldMapper("movementReasonCode"))
			.put("ARREST_AGENCY_LOC_ID", 			new FieldMapper("arrestAgencyLocId"))
			.put("MOVEMENT_DATE", 					new FieldMapper("movementDate"))
			.build();
	private final Map<String, FieldMapper> movementReasonsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("MOVE_RSN.UPDATE_ALLOWED_FLAG", 	new FieldMapper("moveRsn.updateAllowedFlag"))
			.put("DSP_DESCRIPTION", 				new FieldMapper("description"))
			.put("MOVEMENT_REASON_CODE", 			new FieldMapper("movementReasonCode"))
			.put("DSP_EXPIRY_DATE", 				new FieldMapper("expiryDate"))
			.put("DSP_OPEN_CONTACT_FLAG", 			new FieldMapper("openContactFlag"))
			.put("MOVEMENT_TYPE", 					new FieldMapper("movementType"))
			.put("DSP_LIST_SEQ ", 					new FieldMapper("listSeq"))
			.put("DSP_CLOSE_CONTACT_FLAG", 			new FieldMapper("closeContactFlag"))
			.put("DSP_ACTIVE_FLAG",	 				new FieldMapper("activeFlag"))
			.put("DSP_CREATE_USER_ID", 				new FieldMapper("createUserId"))
			.put("'1'", 							new FieldMapper("'1'"))
			.build();
	private final Map<String, FieldMapper> movementReasonsMappingWithTableAliasName = new ImmutableMap.Builder<String, FieldMapper>()
			.put("MOVE_RSN.UPDATE_ALLOWED_FLAG", 	new FieldMapper("moveRsn.updateAllowedFlag"))
			.put("MOVE_RSN.DESCRIPTION", 			new FieldMapper("moveRsn.description"))
			.put("MOVEMENT_REASON_CODE", 			new FieldMapper("movementReasonCode"))
			.put("MOVE_RSN.EXPIRY_DATE", 			new FieldMapper("moveRsn.expiryDate"))
			.put("MOVE_RSN.OPEN_CONTACT_FLAG", 		new FieldMapper("moveRsn.openContactFlag"))
			.put("MOVEMENT_TYPE", 					new FieldMapper("movementType"))
			.put("MOVE_RSN.LIST_SEQ --", 			new FieldMapper("moveRsn.listSeq --"))
			.put("MOVE_RSN.CLOSE_CONTACT_FLAG", 	new FieldMapper("moveRsn.closeContactFlag"))
			.put("MOVE_RSN.ACTIVE_FLAG", 			new FieldMapper("moveRsn.activeFlag"))
			.put("MOVE_RSN.CREATE_USER_ID", 		new FieldMapper("moveRsn.createUserId"))
			.put("'1'", 							new FieldMapper("'1'"))
			.build();

	/**
	 * Creates new OidreleaRepositoryImpl class Object
	 */
	public OidreleaRepositoryImpl() {
		// OidreleaRepositoryImpl
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<RecordGroup>
	 */
	public List<MovementReasons> cgfkOffEmMovementReasonCoRecordGroup() {
		final String sql = queryBuilderFactory
				.getQueryBuilder(getQuery("OIDRELEA_FIND_CGFKOFFEMMOVEMENTREASONCO"), movementReasonsMapping).build();
		final RowMapper<MovementReasons> movementReasonsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				MovementReasons.class, movementReasonsMapping);
		List<MovementReasons> refList = new ArrayList<MovementReasons>();
		refList = namedParameterJdbcTemplate.query(sql, movementReasonsRowMapper);
		return refList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<RecordGroup>
	 */
	public List<ReferenceCodes> rgMovementReasonCodeRecordGroup() {
		final String sql = getQuery("OIDRELEA_FIND_RGMOVEMENTREASONCODE");
		final RowMapper<ReferenceCodes> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, referenceCodeMappingOfAllColumns);
		List<ReferenceCodes> refList = new ArrayList<ReferenceCodes>();
		refList = namedParameterJdbcTemplate.query(sql, createParams(), referenceCodeRowMapper);
		return refList;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            OffenderExternalMovements
	 *
	 * @return List<OffenderExternalMovements>
	 *
	 * @
	 */
	public List<OffenderExternalMovements> offEmSearchOffenderExternalMovements(
			final OffenderExternalMovements objSearchDao) {
		final String sql = getQuery("OIDRELEA_OFFEM_FIND_OFFENDER_EXTERNAL_MOVEMENTS");
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		sqlQuery.append(sql);
		final MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		if (objSearchDao != null) {
			sqlQuery.append(" where ");
			if (objSearchDao != null && objSearchDao.getOffenderBookId() != 0) {
				sqlQuery.append("OFFENDER_BOOK_ID =:offenderBookID " + " and ");
				parameterSource.addValue("offenderBookID", objSearchDao.getOffenderBookId());
			}
			if (objSearchDao != null && objSearchDao.getMovementSeq() != 0) {
				sqlQuery.append("MOVEMENT_SEQ = :moventSeq" + " and ");
				parameterSource.addValue("moventSeq", objSearchDao.getMovementSeq());
			}
			if (objSearchDao != null && objSearchDao.getMovementDate() != null) {
				sqlQuery.append("MOVEMENT_DATE =:moventDate " + " and ");
				parameterSource.addValue("moventDate", new Date(objSearchDao.getMovementDate().getTime()));
			}
			if (objSearchDao != null && objSearchDao.getMovementTime() != null) {
				sqlQuery.append("MOVEMENT_TIME =:moventTime " + " and ");
				parameterSource.addValue("moventTime", new Date(objSearchDao.getMovementTime().getTime()));
			}
			if (objSearchDao != null && objSearchDao.getInternalScheduleType() != null) {
				sqlQuery.append("INTERNAL_SCHEDULE_TYPE =:internalScheduleType " + " and ");
				parameterSource.addValue("internalScheduleType", objSearchDao.getInternalScheduleType());
			}
			if (objSearchDao != null && objSearchDao.getInternalScheduleReasonCode() != null) {
				sqlQuery.append("INTERNAL_SCHEDULE_REASON_CODE =:internalScheduleCode " + " and ");
				parameterSource.addValue("internalScheduleCode", objSearchDao.getInternalScheduleReasonCode());
			}

			if (objSearchDao != null && objSearchDao.getDirectionCode() != null) {
				sqlQuery.append("DIRECTION_CODE =:directionCode " + " and ");
				parameterSource.addValue("directionCode", objSearchDao.getDirectionCode());
			}
			if (objSearchDao != null && objSearchDao.getArrestAgencyLocId() != null) {
				sqlQuery.append("ARREST_AGENCY_LOC_ID =:arrestAgencyLocId " + " and ");
				parameterSource.addValue("arrestAgencyLocId", objSearchDao.getArrestAgencyLocId());
			}
			if (objSearchDao != null && objSearchDao.getToProvStatCode() != null) {
				sqlQuery.append("TO_PROV_STAT_CODE =:toProvStatCode " + " and ");
				parameterSource.addValue("toProvStatCode", objSearchDao.getToProvStatCode());
			}
			if (objSearchDao != null && objSearchDao.getEscortCode() != null) {
				sqlQuery.append("ESCORT_CODE =:escortCode " + " and ");
				parameterSource.addValue("escortCode", objSearchDao.getEscortCode());
			}
			if (objSearchDao != null && objSearchDao.getFromAgyLocId() != null) {
				sqlQuery.append("FROM_AGY_LOC_ID =:fromAgyLocId " + " and ");
				parameterSource.addValue("fromAgyLocId", objSearchDao.getFromAgyLocId());
			}
			if (objSearchDao != null && objSearchDao.getToAgyLocId() != null) {
				sqlQuery.append("TO_AGY_LOC_ID =:toagyLocId " + " and ");
				parameterSource.addValue("toagyLocId", objSearchDao.getToAgyLocId());
			}
			if (objSearchDao != null && objSearchDao.getActiveFlag() != null) {
				sqlQuery.append("ACTIVE_FLAG =:activeFlag " + " and ");
				parameterSource.addValue("activeFlag", objSearchDao.getActiveFlag());
			}
			if (objSearchDao != null && objSearchDao.getEscortText() != null) {
				sqlQuery.append("ESCORT_TEXT =:escortText " + " and ");
				parameterSource.addValue("escortText", objSearchDao.getEscortText());
			}
			if (objSearchDao != null && objSearchDao.getCommentText() != null) {
				sqlQuery.append("COMMENT_TEXT =:commentText " + " and ");
				parameterSource.addValue("commentText", objSearchDao.getCommentText());
			}
			if (objSearchDao != null && objSearchDao.getReportingDate() != null) {
				sqlQuery.append("REPORTING_DATE =:reportingDate " + " and ");
				parameterSource.addValue("reportingDate", objSearchDao.getReportingDate());
			}
			if (objSearchDao != null && objSearchDao.getToCity() != null) {
				sqlQuery.append("TO_CITY =:toCity " + " and ");
				parameterSource.addValue("toCity", objSearchDao.getToCity());
			}
			if (objSearchDao != null && objSearchDao.getFromCity() != null) {
				sqlQuery.append("FROM_CITY =:fromCity " + " and ");
				parameterSource.addValue("fromCity", objSearchDao.getFromCity());
			}
			if (objSearchDao != null && objSearchDao.getReportingTime() != null) {
				sqlQuery.append("REPORTING_TIME = :reportingTime " + " and ");
				parameterSource.addValue("reportingTime", new Date(objSearchDao.getReportingTime().getTime()));
			}
			if (objSearchDao != null && objSearchDao.getEventId() != null) {
				sqlQuery.append("EVENT_ID =:eventId " + " and ");
				parameterSource.addValue("eventId", objSearchDao.getEventId());
			}
			if (objSearchDao != null && objSearchDao.getParentEventId() != null) {
				sqlQuery.append("PARENT_EVENT_ID =:parentEventId " + " and ");
				parameterSource.addValue("parentEventId", objSearchDao.getParentEventId());
			}
			if (objSearchDao != null && objSearchDao.getToCountryCode() != null) {
				sqlQuery.append("TO_COUNTRY_CODE =:toCountyCode " + " and ");
				parameterSource.addValue("toCountyCode", objSearchDao.getToCountryCode());
			}
			if (objSearchDao != null && objSearchDao.getOjLocationCode() != null) {
				sqlQuery.append("OJ_LOCATION_CODE =:ojLocationCode " + " and ");
				parameterSource.addValue("ojLocationCode", objSearchDao.getOjLocationCode());
			}
			if (objSearchDao != null && objSearchDao.getApplicationDate() != null) {
				sqlQuery.append("APPLICATION_DATE =:applicationDate " + " and ");
				parameterSource.addValue("ojLocationCode", new Date(objSearchDao.getApplicationDate().getTime()));
			}
			if (objSearchDao != null && objSearchDao.getApplicationTime() != null) {
				sqlQuery.append("APPLICATION_TIME =:applicationTime " + " and ");
				parameterSource.addValue("applicationTime", new Date(objSearchDao.getApplicationTime().getTime()));
			}
			if (objSearchDao != null && objSearchDao.getToAddressId() != null) {
				sqlQuery.append("TO_ADDRESS_ID =:toAddressId " + " and ");
				parameterSource.addValue("toAddressId", objSearchDao.getToAddressId());
			}
			if (objSearchDao != null && objSearchDao.getFromAddressId() != null) {
				sqlQuery.append("FROM_ADDRESS_ID =:fromAddressId " + " and ");
				parameterSource.addValue("fromAddressId", objSearchDao.getFromAddressId());
			}
			if (objSearchDao != null && objSearchDao.getSealFlag() != null) {
				sqlQuery.append("SEAL_FLAG =:sealFlag " + " and ");
				parameterSource.addValue("sealFlag", objSearchDao.getSealFlag());
			}
			if (objSearchDao != null && objSearchDao.getCreateDatetime() != null) {
				sqlQuery.append("CREATE_DATETIME =:createDateTime " + " and ");
				parameterSource.addValue("createDateTime", new Date(objSearchDao.getCreateDatetime().getTime()));
			}
			if (objSearchDao != null && objSearchDao.getCreateUserId() != null) {
				sqlQuery.append("CREATE_USER_ID =:createUserId " + " and ");
				parameterSource.addValue("createUserId", objSearchDao.getCreateUserId());
			}
			if (objSearchDao != null && objSearchDao.getModifyDatetime() != null) {
				sqlQuery.append("MODIFY_DATETIME =:modifyDateTime " + " and ");
				parameterSource.addValue("modifyDateTime", new Date(objSearchDao.getModifyDatetime().getTime()));
			}
			if (objSearchDao != null && objSearchDao.getModifyUserId() != null) {
				sqlQuery.append("MODIFY_USER_ID =:modifyUserId " + " and ");
				parameterSource.addValue("modifyUserId", objSearchDao.getModifyUserId());
			}
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("where")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("and")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		final RowMapper<OffenderExternalMovements> OffenderExternalMovementsRowMapper = Row2BeanRowMapper
				.makeMapping(preparedSql, OffenderExternalMovements.class, offenderExternalMovementsMapping);
		List<OffenderExternalMovements> returnList = new ArrayList<OffenderExternalMovements>();
		returnList = namedParameterJdbcTemplate.query(preparedSql, parameterSource, OffenderExternalMovementsRowMapper);

		return returnList;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            SystemProfiles
	 *
	 * @return List<SystemProfiles>
	 *
	 * @
	 */
	public List<SystemProfiles> sysPflSearchSystemProfiles(final SystemProfiles objSearchDao) {
		final String sql = getQuery("OIDRELEA_SYSPFL_FIND_SYSTEM_PROFILES");
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		sqlQuery.append(sql);
		final MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		if (objSearchDao != null) {
			sqlQuery.append(" where ");
			if (objSearchDao != null && objSearchDao.getProfileType() != null) {
				sqlQuery.append("PROFILE_TYPE =:profileType " + " and ");
				parameterSource.addValue("profileType", objSearchDao.getProfileType());
			}
			if (objSearchDao != null && objSearchDao.getProfileCode() != null) {
				sqlQuery.append("PROFILE_CODE =:profileCode " + " and ");
				parameterSource.addValue("profileCode", objSearchDao.getProfileCode());
			}
			if (objSearchDao != null && objSearchDao.getDescription() != null) {
				sqlQuery.append("DESCRIPTION =:description " + " and ");
				parameterSource.addValue("description", objSearchDao.getDescription());
			}
			if (objSearchDao != null && objSearchDao.getProfileValue() != null) {
				sqlQuery.append("PROFILE_VALUE =:profileValue " + " and ");
				parameterSource.addValue("profileValue", objSearchDao.getProfileValue());
			}
			if (objSearchDao != null && objSearchDao.getProfileValue2() != null) {
				sqlQuery.append("PROFILE_VALUE_2 =:profileValue2 " + " and ");
				parameterSource.addValue("profileValue2", objSearchDao.getProfileValue2());
			}
			if (objSearchDao != null && objSearchDao.getOldTableName() != null) {
				sqlQuery.append("OLD_TABLE_NAME =:oldTableName " + " and ");
				parameterSource.addValue("oldTableName", objSearchDao.getOldTableName());
			}
			if (objSearchDao != null && objSearchDao.getModifyUserId() != null) {
				sqlQuery.append("MODIFY_USER_ID =:modifyUserId " + " and ");
				parameterSource.addValue("modifyUserId", objSearchDao.getModifyUserId());
			}
			if (objSearchDao != null && objSearchDao.getCreateDateTime() != null) {
				sqlQuery.append("CREATE_DATETIME =:createDatetime " + " and ");
				parameterSource.addValue("createDatetime", objSearchDao.getCreateDateTime());
			}
			if (objSearchDao != null && objSearchDao.getCreateUserId() != null) {
				sqlQuery.append("CREATE_USER_ID =:createUserId " + " and ");
				parameterSource.addValue("createUserId", objSearchDao.getCreateUserId());
			}
			if (objSearchDao != null && objSearchDao.getModifyDateTime() != null) {
				sqlQuery.append("MODIFY_DATETIME =:modifyDatetime " + " and ");
				parameterSource.addValue("modifyDatetime", objSearchDao.getModifyDateTime());
			}
			if (objSearchDao != null && objSearchDao.getSealFlag() != null) {
				sqlQuery.append("SEAL_FLAG =:sealFlag " + " and ");
				parameterSource.addValue("sealFlag", objSearchDao.getSealFlag());
			}
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("where")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("and")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}

		final RowMapper<SystemProfiles> SystemProfilesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				SystemProfiles.class, systemProfilesMapping);
		final ArrayList<SystemProfiles> returnList = (ArrayList<SystemProfiles>) namedParameterJdbcTemplate
				.query(preparedSql, parameterSource, SystemProfilesRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * oidreleaOffEmPreInsertc
	 *
	 * @param params
	 *
	 */
	public String offEmPreInsertc(final OffenderExternalMovements paramBean) {
		final String sql = getQuery("OIDRELEA_OFF_EM_PREINSERT_C");
		String obj = null;
		obj = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("OFFENDER_BOOK_ID", paramBean.getOffenderBookId()), String.class);
		return obj;
	}
	
	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * oidreleaOffEmPreInsertc
	 *
	 * @param params
	 *
	 */
	public String offEmPostInsertc(final OffenderExternalMovements paramBean) {
		final String sql = getQuery("OIDRELEA_POST_QUERY");
		String obj = null;
		try {
		obj = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("OFFENDERBOOKID", paramBean.getOffenderBookId()), String.class);
		if (obj == null) {
			obj = "C";
		}
		} catch(Exception e) {
			obj = "C";
			return obj;
		}
		return obj;
	}
	/**
	 * Fetch the records from database table
	 * 
	 * OffenderExternalMovements
	 *
	 * @param lstOffenderExternalMovements
	 */
	public Integer postInsert(final OffenderExternalMovements lstOffenderExternalMovements) {
		final String sql = getQuery("OIDRELEA_POST_QUERY_UPDATE");
		Integer returnArray = null;
		try {
		returnArray = namedParameterJdbcTemplate.update(sql,
				new BeanPropertySqlParameterSource(lstOffenderExternalMovements));
		} catch(Exception e) {
			
			returnArray = null;
			return returnArray;
		}
		return returnArray;
	}
	
	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * gettingProfileValue
	 *
	 * @param params
	 *
	 */
	public String gettingProfileValue() {
		final String sql = getQuery("OIDRELEA_GET_PROFILE_VALUE");
		String obj = null;
		try {
		obj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);
		} catch(Exception e) {
			obj = null;
			return obj;
		}
		return obj;
	}
	
	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * oidreleaOffEmPreInsertc
	 *
	 * @param params
	 *
	 */
	public String getClosedFlag(final String movementCode) {
		final String sql = getQuery("OIDRELEA_GET_CLOSE_CONTACT_FLAG");
		String obj = null;
		try {
		obj = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("MOVEMENTREASONCODE", movementCode), String.class);
		} catch(Exception e) {
			obj = null;
			return obj;
		}
		return obj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * oidreleaCgfkchkOffEmOffEmRefMovc
	 *
	 * @param params
	 *
	 */
	public List<Object> cgfkchkOffEmOffEmRefMovc(final ReferenceCodes paramBean) {
		final String sql = getQuery("OIDRELEA_CGFKCHK_OFF_EM_OFF_EM_REF_MOV_C");
		List<Object> returnObj = null;
		returnObj = namedParameterJdbcTemplate.queryForList(sql, createParams("CODE", paramBean.getCode()),
				Object.class);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * oidreleaCgfkchkOffEmOffEmMoveRsc
	 *
	 * @param params
	 *
	 */
	public List<Object> cgfkchkOffEmOffEmMoveRsc(final MovementReasons paramBean) {
		final String sql = getQuery("OIDRELEA_CGFKCHK_OFF_EM_OFF_EM_MOVE_RS_C");
		List<Object> returnObj = null;
		returnObj = namedParameterJdbcTemplate.queryForList(sql,
				createParams("MOVEMENTREASONCODE", paramBean.getMovementReasonCode()), Object.class);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * oidreleaCgfkchkOffEmOffEmMove2c
	 *
	 * @param params
	 *
	 */
	public List<MovementReasons> cgfkchkOffEmOffEmMovec(final MovementReasons paramBean) {
		final String sql = getQuery("OIDRELEA_CGFKCHK_OFF_EM_OFF_EM_MOVE_C");
		final RowMapper<MovementReasons> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, MovementReasons.class,
				movementReasonsMappingWithTableAliasName);
		final List<MovementReasons> returnObj = namedParameterJdbcTemplate.query(sql, createParams("MOVEMENTTYPE",
				paramBean.getMovementType(), "MOVEMENTREASONCODE", paramBean.getMovementReasonCode()), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * oidreleaCgwhenNewFormInstancec
	 *
	 * @param params
	 *
	 */
	public List<Dual> cgwhenNewFormInstancec(final Dual paramBean) {
		final String sql = getQuery("OIDRELEA_CGWHEN_NEW_FORM_INSTANCE_C");
		final RowMapper<Dual> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Dual.class, sysDualMapping);
		final List<Dual> returnList = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * oidreleaCallToShowFingerprint
	 *
	 * @param params
	 *
	 */
	public List<SystemProfiles> callToShowFingerprint(final SystemProfiles paramBean) {
		final String sql = getQuery("OIDRELEA_CALL_TO_SHOW_FINGERPRINT");
		final RowMapper<SystemProfiles> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, SystemProfiles.class,
				systemProfilesMapping);
		List<SystemProfiles> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * List<OffenderExternalMovements>
	 *
	 * @param lstOffenderExternalMovements
	 */
	public Integer offEmInsertOffenderExternalMovements(
			final List<OffenderExternalMovements> lstOffenderExternalMovements) {
		final String sql = getQuery("OIDRELEA_OFFEM_INSERT_OFFENDER_EXTERNAL_MOVEMENTS");
		int[] returnArray = new int[] {};
		Long movementseq = null;
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		String userId = lstOffenderExternalMovements.get(0).getCreateUserId();
		for (final OffenderExternalMovements offexternal : lstOffenderExternalMovements) {
			if (offexternal.getOffenderBookId() != 0) {
				movementseq = offexternal.getOffenderBookId();
				movementseq = agencyIncidentPartiesPreSeqPreInsertcDAO(movementseq);
				offexternal.setMovementSeq(movementseq);
				offexternal.setDirectionCode("OUT");
				offexternal.setMovementType("REL");
				offexternal.setToAgyLocId("OUT");
				offexternal.setCreateUserId(userId);
				if(offexternal.getActiveFlag()==null) {
					offexternal.setActiveFlag("Y");
				}
			}
		}
		for (final OffenderExternalMovements offenderExternalMovements : lstOffenderExternalMovements) {
			parameters.add(new BeanPropertySqlParameterSource(offenderExternalMovements));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		
		try {
			String escapePermission=getEscapePermission();
			//Integer count=getEscapeCount(lstOffenderExternalMovements.get(0).getOffenderBookId()!=null?lstOffenderExternalMovements.get(0).getOffenderBookId().longValue():null);
			if(escapePermission!=null && lstOffenderExternalMovements!=null && lstOffenderExternalMovements.get(0)!=null && lstOffenderExternalMovements.get(0).getMovementReasonCode()!=null && escapePermission.equals("ESCP") && lstOffenderExternalMovements.get(0).getMovementReasonCode().equals("ESCP")) {
				saveCustodyAdjustment(lstOffenderExternalMovements.get(0).getOffenderBookId(),lstOffenderExternalMovements.get(0).getMovementDate());
			}
		}catch (Exception e) {
			logger.error("in " + this.getClass().getName() + " in offEmInsertOffenderExternalMovements ", e);
		}
		if (lstOffenderExternalMovements.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
		
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @param offenderbookid
	 */
	public Long agencyIncidentPartiesPreSeqPreInsertcDAO(final Long offenderbookid) {
		final String sql = getQuery("OIDRELEA_OFF_EM_PREINSERT_C");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("OFFENDER_BOOK_ID", offenderbookid),
				Long.class);
	}

	/**
	 * Fetch the records from database table
	 * 
	 * OffenderExternalMovements
	 *
	 * @param lstOffenderExternalMovements
	 */
	public Integer offEmUpdateOffenderExternalMovements(final OffenderExternalMovements lstOffenderExternalMovements) {
		final String sql = getQuery("OIDRELEA_OFFEM_UPDATE_OFFENDER_EXTERNAL_MOVEMENTS");
		Integer returnArray = null;
		returnArray = namedParameterJdbcTemplate.update(sql,
				new BeanPropertySqlParameterSource(lstOffenderExternalMovements));
		return returnArray;
	}

	/**
	 * Insert the records from database table
	 * 
	 * VHeaderBlock
	 *
	 * @param vblock
	 */
	public Integer offBookingUpdateOffenderExternalMovements(final VHeaderBlock vblock) {
		final String sql = getQuery("OIDRELEA_OFFEM_UPDATE_OFFENDER_BOOKINGS");
		int[] returnArray = new int[] {};
		final ArrayList<VHeaderBlock> lstOffenderExternalMovements = new ArrayList<VHeaderBlock>();
		lstOffenderExternalMovements.add(vblock);
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final VHeaderBlock offenderExternalMovements : lstOffenderExternalMovements) {
			parameters.add(new BeanPropertySqlParameterSource(offenderExternalMovements));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenderExternalMovements.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * Insert the records from database table
	 * 
	 * VHeaderBlock
	 *
	 * @param vblock
	 */
	public Integer offBkgUpdateOffenderExternalMovements(final VHeaderBlock vblock) {
		final String sql = getQuery("OIDRELEA_UPDATE_OFFENDER_BOOKINGS");
		int[] returnArray = new int[] {};
		final ArrayList<VHeaderBlock> lstOffenderExternalMovements = new ArrayList<VHeaderBlock>();
		lstOffenderExternalMovements.add(vblock);
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final VHeaderBlock offenderExternalMovements : lstOffenderExternalMovements) {
			parameters.add(new BeanPropertySqlParameterSource(offenderExternalMovements));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenderExternalMovements.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * This method is execute a Functiion when trigger event is called
	 * 
	 * omsMovementsCheckActiveSentence
	 *
	 * @param searchdao
	 *
	 */
	public Integer omsMovementsCheckActiveSentence(final OffenderExternalMovements searchdao) {
		Integer value = new Integer(0);
		try {
			Map<String, Object> inParamMap = new HashMap<String, Object>();
			SqlParameter[] sqlParameters = new SqlParameter[2];
			sqlParameters = new SqlParameter[] { new SqlParameter("P_OFFENDER_BOOK_ID", OracleTypes.NUMBER),
					new SqlOutParameter("RETURN_VALUE", OracleTypes.NUMBER) };
			SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
					.withCatalogName("OMS_MOVEMENTS").withFunctionName("CHECK_ACTIVE_SENTENCE")
					.declareParameters(sqlParameters);
			inParamMap.put("P_OFFENDER_BOOK_ID", searchdao.getOffenderBookId());
			SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
			BigDecimal valueObj = (BigDecimal) simpleJDBCCall.executeFunction(BigDecimal.class, inParameter);
			value = Integer.valueOf(valueObj.toString());
		} catch (Exception e) {
			logger.error("In method omsMovementsCheckActiveSentence :" + e);
		}
		return value;
	}

	/**
	 * This method is execute a Procedure when trigger event is called
	 * 
	 * offExtMvntsReleaseDateCheck
	 *
	 * @param paramBean
	 *
	 */
	public OffenderExternalMovements offExtMvntsReleaseDateCheck(final OffenderExternalMovements paramBean) {
		final OffenderExternalMovements bean = new OffenderExternalMovements();
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[20];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_OFFENDER_BOOK_ID", OracleTypes.NUMBER),
				new SqlOutParameter("P_RELEASE_DATE", OracleTypes.DATE),
				new SqlOutParameter("P_MOVEMENT_REASON_CODE", OracleTypes.VARCHAR) };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("OMS_MOVEMENTS").withProcedureName("RELEASE_DATE_CHECK")
				.declareParameters(sqlParameters);
		inParamMap.put("P_OFFENDER_BOOK_ID", paramBean.getOffenderBookId());
		inParamMap.put("P_RELEASE_DATE", OracleTypes.DATE);
		inParamMap.put("P_MOVEMENT_REASON_CODE", OracleTypes.VARCHAR);
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			 simpleJDBCCall.execute(inParameter);
		} catch (Exception e) {
			logger.error("offExtMvntsReleaseDateCheck", e);
		}
		return bean;
	}

	/**
	 * This method is execute a Function when trigger event is called
	 * 
	 * omsMovementsCheckActiveCases
	 *
	 * @param searchdao
	 *
	 */
	public String omsMovementsCheckActiveCases(final OffenderExternalMovements searchdao) {
		String value = "";
		try {
			Map<String, Object> inParamMap = new HashMap<String, Object>();
			SqlParameter[] sqlParameters = new SqlParameter[2];
			sqlParameters = new SqlParameter[] { new SqlParameter("P_OFFENDER_BOOK_ID", OracleTypes.NUMBER),
					new SqlOutParameter("RETURN_VALUE", OracleTypes.VARCHAR) };
			SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
					.withCatalogName("TAG_TERMINATION").withFunctionName("CHK_ACTIVE_CASES")
					.declareParameters(sqlParameters);
			inParamMap.put("P_OFFENDER_BOOK_ID", searchdao.getOffenderBookId());
			SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
			value = simpleJDBCCall.executeFunction(String.class, inParameter);
		} catch (Exception e) {
			logger.error("In method omsMovementsCheckActiveCases :" + e);
		}
		return value;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * oidreleaCgfkchkOffEmOffEmMoveRsc
	 *
	 * @param params
	 *
	 */
	public String escapeCursor(final String reasnCode) {
		final String sql = getQuery("OIDRELEA_ESCAPE_CURSOR");
		String returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("MOVEMENT_REASON_CODE", reasnCode),
					String.class);
		} catch (Exception e) {
			logger.error("In method escapeCursor", e);
			return returnObj;
		}
		return returnObj;
	}

	/**
	 * Insert the records from database table
	 * 
	 * List<OffenderEscapes>
	 *
	 * @param offEscList
	 */
	public Integer postInsertEscape(final List<OffenderEscapes> offEscList) {
		final String sql = getQuery("OIDRELEA_INSERT_ESCAPE");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();

		for (final OffenderEscapes obj : offEscList) {
			parameters.add(new BeanPropertySqlParameterSource(obj));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (offEscList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * Fetch the records from database table
	 * 
	 * @param offenderBookId
	 */
	public List<OffenderExternalMovements> movementDateComparison(final Long offenderBookId) {
		final String sql = getQuery("OIDRELEA_MOVEMENTDATE_COMPARISON");
		final RowMapper<OffenderExternalMovements> mRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderExternalMovements.class, offenderExternalMovementsMapping);
		List<OffenderExternalMovements> returnList = new ArrayList<>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("offenderBookId", offenderBookId),
					mRowMapper);
		} catch (Exception e) {
			logger.error("rgProposedEmploymentRecordGroup", e);
			return returnList;
		}
		return returnList;
	}

	@Override
	public OffenderBookings gettingOldData(BigDecimal offenderBookId) {
		final String sql = getQuery("OIDRELEA_GETTING_OLD_DATA");
		OffenderBookings returnObj=new OffenderBookings();
		returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderBokkId",offenderBookId ),
				new BeanPropertyRowMapper<OffenderBookings>(OffenderBookings.class));
		return returnObj;
	}

    @Override
	public BedAssignmentHistories getBedAh(Long offenderBookId) {
		BedAssignmentHistories retObj=null;
		final String sql = getQuery("OIDRELEA_BED_ASSIGNMENT_HISTORIES_QUERY");
		try {
			retObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderBookId", offenderBookId),
					new BeanPropertyRowMapper<BedAssignmentHistories>(BedAssignmentHistories.class));
		} catch (Exception e) {
			logger.error("getBedAh", e);
		}
		return retObj;
	}
	
	@Override
	public Integer updateBedAh(final BedAssignmentHistories object) {
		final String sql = getQuery("OIDRELEA_BED_ASSIGNMENT_HISTORIES");
		try {
			namedParameterJdbcTemplate.update(sql,new BeanPropertySqlParameterSource(object));
		}  catch (Exception e) {
			logger.error("updateBedAh", e);
		}
		return null;
	}
	@Override
	public MovementReasons gettingOldDataOffenderExternal(Long offenderBookId, Long movementSeq) {
		final String sql = getQuery("OIDRELEA_GETTING_OFFENDER_EXTERNAL_MOVEMENTS_RECORD");
		MovementReasons returnObj=new MovementReasons();
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderbookId",offenderBookId,"movementSeq", movementSeq),
					new BeanPropertyRowMapper<MovementReasons>(MovementReasons.class));
		} catch (Exception e) {
			logger.error("gettingOldDataOffenderExternal", e);
			return null;
		}
		return returnObj;
	}
	
	@Override
	public String getCustodyStatus() {
		final String sql = getQuery("OIDRELEA_GET_CUSTODY_STATUS");
		String returnData = "";
		try {
			returnData = namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);
		} catch (Exception e) {
			logger.error("In getCustodyStatus", e);
			return returnData;
		}
		return returnData;
	}
	
	
	
	public Integer saveCustodyAdjustment(Long offenderBookId,Date releaseDate ) {
		final String sql = getQuery("OIDRELEA_SAVE_CUSTODY_BOOKINGS");
		OffenderSentenceAdjustment adjustments=new OffenderSentenceAdjustment();
		adjustments.setAdjustCode("ESCP");
		adjustments.setOffenderBookId(offenderBookId);
		adjustments.setObjectId(1l);
		adjustments.setObjectType("BOOKING");
		adjustments.setAdjustDate(new Date());
		adjustments.setAdjustFromDate(releaseDate);
		adjustments.setCreateUserId("OMS_OWNER");
		adjustments.setCreateDatetime(new Date());
		adjustments.setSealFlag("Y");
		adjustments.setAdjustToDate(null);
		adjustments.setAdjustDays(0);
		Integer returnArray = null;
		returnArray = namedParameterJdbcTemplate.update(sql,new BeanPropertySqlParameterSource(adjustments));
		return returnArray;
	}
	
	
	public String getEscapePermission() {
		final String sql = getQuery("OIDRELEA_FETCH_ESCAPE_ADJUSTMENTS");
		String returnData = "";
		try {
			returnData = namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);
		} catch (Exception e) {
			logger.error("In getEscapePermission", e);
			return returnData;
		}
		return returnData;
	}
	
	public Integer getEscapeCount(Long offenderBookId) {
		final String sql = getQuery("OIDCUSTAD_RETRIEVE_ESCAPE_VALUES");
		Integer returnData = null;
		try {
			returnData = namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderBookId",offenderBookId), Integer.class);
		} catch (Exception e) {
			logger.error("In getEscapePermission", e);
			return returnData;
		}
		return returnData;
	}
	
	/**
	 * Fetch the records from database table
	 * 
	 * @param offenderBookId
	 */
	public List<OffenderReleaseDetails> getOffenderreleaseSchedule(final Long offenderBookId) {
		final String sql = getQuery("OIDRELEA_GET_OFFENDER_RELEASE_SCHEDULE");
		final RowMapper<OffenderReleaseDetails> mRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderReleaseDetails.class, offenderExternalMovementsMapping);
		List<OffenderReleaseDetails> returnList = new ArrayList<>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("offenderBookId", offenderBookId),
					mRowMapper);
		} catch (Exception e) {
			logger.error("rgProposedEmploymentRecordGroup", e);
			return returnList;
		}
		return returnList;
	}

	@Override
	public String getOffenderCommentText(Integer offenderBookId) {
		final String sql = getQuery("OIDRELEA_GET_COMMENT_TEXT");
		String commentText = null;
		try {
			commentText = namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderBookId", offenderBookId),
					String.class);
		} catch (Exception e) {
			logger.error("In getEscapePermission", e);
			return commentText;
		}
		return commentText;
	}
	
}
