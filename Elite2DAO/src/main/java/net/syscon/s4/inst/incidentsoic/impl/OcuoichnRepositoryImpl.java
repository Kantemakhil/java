package net.syscon.s4.inst.incidentsoic.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.AgencyInternalLocations;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.Dual;
import net.syscon.s4.im.beans.OffenderDetail;
import net.syscon.s4.im.beans.OffenderNaDetails;
import net.syscon.s4.im.incidentsoic.beans.OicHearingNotices;
import net.syscon.s4.im.incidentsoic.beans.OicHearings;
import net.syscon.s4.inst.incidentsoic.OcuoichnRepository;

/**
 * Class OcuoichnRepositoryImpl
 */
@Repository
public class OcuoichnRepositoryImpl extends RepositoryBase implements OcuoichnRepository {
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcuoichnRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> oichearingmapping = new ImmutableMap.Builder<String, FieldMapper>().build();

	public static final String ENTERQUERY = "ENTER-QUERY";

	private final Map<String, FieldMapper> staffMembersMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CREATE_DATETIME", new FieldMapper("createDatetime")).put("STAFF_ID", new FieldMapper("staffId"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId")).put("DESCRIPTION", new FieldMapper("description"))
			.put("CODE", new FieldMapper("code")).put("DOMAIN", new FieldMapper("domain"))
			.put("PARENT_DOMAIN", new FieldMapper("parentDomainId")).put("PARENT_CODE", new FieldMapper("parentCode"))
			.put("ACTIVE_FLAG", new FieldMapper("activeFlag")).put("DSP_NAME", new FieldMapper("dspName")).build();

	private final Map<String, FieldMapper> agencyInternalLocationsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("INTERNAL_LOCATION_ID", new FieldMapper("internalLocationId"))
			.put("INTERNAL_LOCATION_CODE", new FieldMapper("internalLocationCode"))
			.put("AGY_LOC_ID", new FieldMapper("agyLocId"))
			.put("INTERNAL_LOCATION_TYPE", new FieldMapper("internalLocationType"))
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("SECURITY_LEVEL_CODE", new FieldMapper("securityLevelCode"))
			.put("CAPACITY", new FieldMapper("capacity")).put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("PARENT_INTERNAL_LOCATION_ID", new FieldMapper("parentInternalLocationId"))
			.put("ACTIVE_FLAG", new FieldMapper("activeFlag")).put("LIST_SEQ", new FieldMapper("listSeq"))
			.put("CREATE_DATETIME", new FieldMapper("createDateTime"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDateTime"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId")).put("CNA_NO", new FieldMapper("cnaNo"))
			.put("CERTIFIED_FLAG", new FieldMapper("certifiedFlag"))
			.put("DEACTIVATE_DATE", new FieldMapper("deactiveDate"))
			.put("REACTIVATE_DATE", new FieldMapper("reactiveDate"))
			.put("DEACTIVATE_REASON_CODE", new FieldMapper("deactiveReasonCode"))
			.put("COMMENT_TEXT", new FieldMapper("commentText")).put("USER_DESC", new FieldMapper("userDesc"))
			.put("ACA_CAP_RATING", new FieldMapper("acaCapRating")).put("UNIT_TYPE", new FieldMapper("unitType"))
			.put("OPERATION_CAPACITY", new FieldMapper("operationCapacity"))
			.put("NO_OF_OCCUPANT", new FieldMapper("noOfOccupant"))
			.put("TRACKING_FLAG", new FieldMapper("trackingFlag")).put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.put("'1'", new FieldMapper("'1'")).build();

	private final Map<String, FieldMapper> referenceCodeMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId")).put("DESCRIPTION", new FieldMapper("description"))
			.put("CODE", new FieldMapper("code")).put("DOMAIN", new FieldMapper("domain"))
			.put("PARENT_DOMAIN", new FieldMapper("parentDomainId")).put("PARENT_CODE", new FieldMapper("parentCode"))
			.put("ACTIVE_FLAG", new FieldMapper("activeFlag")).build();

	private final Map<String, FieldMapper> oicHearingsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("COMMENT_TEXT", new FieldMapper("commentText")).put("HEARING_DATE", new FieldMapper("hearingDate"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("SCHEDULE_DATE", new FieldMapper("scheduleDate")).put("HEARING_TIME", new FieldMapper("hearingTime"))
			.put("REPRESENTATIVE_TEXT", new FieldMapper("representativeText"))
			.put("TAPE_NUMBER", new FieldMapper("tapeNumber")).put("SCHEDULE_TIME", new FieldMapper("scheduleTime"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId")).put("EVENT_ID", new FieldMapper("eventId"))
			.put("SEAL_FLAG", new FieldMapper("sealFlag")).put("CREATE_DATETIME", new FieldMapper("createDateTime"))
			.put("OIC_HEARING_TYPE", new FieldMapper("oicHearingType"))
			.put("INTERNAL_LOCATION_ID", new FieldMapper("internalLocationId"))
			.put("EVENT_STATUS", new FieldMapper("eventStatus")).put("DELIVERY_DATE", new FieldMapper("deliveryDate"))
			.put("OIC_INCIDENT_ID", new FieldMapper("oicIncidentId"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDateTime"))
			.put("VISIT_JUSTICE_TEXT", new FieldMapper("visitJusticeText"))
			.put("OIC_HEARING_ID", new FieldMapper("oicHearingId"))
			.put("OIC_NOTICE_SEQ", new FieldMapper("oicNoticeSeq"))
			.put("DELIVERY_TIME", new FieldMapper("deliveryTime"))
			.put("DELIVERY_STAFF_ID", new FieldMapper("deliveryStaffId"))
			.put("HEARING_STAFF_ID", new FieldMapper("hearingStaffId")).build();

	private final Map<String, FieldMapper> oicHearingNoticesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("COMMENT_TEXT", new FieldMapper("commentText")).put("HEARING_DATE", new FieldMapper("hearingDate"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("SCHEDULE_DATE", new FieldMapper("scheduleDate")).put("HEARING_TIME", new FieldMapper("hearingTime"))
			.put("REPRESENTATIVE_TEXT", new FieldMapper("representativeText"))
			.put("TAPE_NUMBER", new FieldMapper("tapeNumber")).put("SCHEDULE_TIME", new FieldMapper("scheduleTime"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId")).put("EVENT_ID", new FieldMapper("eventId"))
			.put("SEAL_FLAG", new FieldMapper("sealFlag")).put("CREATE_DATETIME", new FieldMapper("createDateTime"))
			.put("OIC_HEARING_TYPE", new FieldMapper("oicHearingType"))
			.put("INTERNAL_LOCATION_ID", new FieldMapper("internalLocationId"))
			.put("EVENT_STATUS", new FieldMapper("eventStatus")).put("DELIVERY_DATE", new FieldMapper("deliveryDate"))
			.put("OIC_INCIDENT_ID", new FieldMapper("oicIncidentId"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDateTime"))
			.put("VISIT_JUSTICE_TEXT", new FieldMapper("visitJusticeText"))
			.put("OIC_HEARING_ID", new FieldMapper("oicHearingId"))
			.put("OIC_NOTICE_SEQ", new FieldMapper("oicNoticeSeq"))
			.put("DELIVERY_TIME", new FieldMapper("deliveryTime"))
			.put("DELIVERY_STAFF_ID", new FieldMapper("deliveryStaffId"))
			.put("HEARING_STAFF_ID", new FieldMapper("hearingStaffId")).build();
	
	private final Map<String, FieldMapper> intLocUsgMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("LOCCODE", new FieldMapper("locCode"))
			.put("INTERNAL_LOCATION_ID", new FieldMapper("internalLocationId"))
			.put("LIST_SEQ", new FieldMapper("listSeq"))
			.build();

	/**
	 * Creates new OcuoichnRepositoryImpl class Object
	 */
	public OcuoichnRepositoryImpl() {
	
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @param caseLoadId
	 * @return List<RecordGroup>
	 */
	public List<StaffMembers> rgAgyIncpStaffIdRecordGroup(final String caseLoadId) {
		final String sql = getQuery("OCUOICHN_FIND_RGAGYINCPSTAFFID");
		final RowMapper<StaffMembers> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql, StaffMembers.class,
				staffMembersMapping);
		ArrayList<StaffMembers> refList = new ArrayList<StaffMembers>();
		try {
			refList = (ArrayList<StaffMembers>) namedParameterJdbcTemplate.query(sql,
					createParams("CASELOADID", caseLoadId, "MODE", ENTERQUERY), referenceCodeRowMapper);
		} catch (Exception e) {
			logger.error(e);
		}
		return refList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<RecordGroup>
	 */
	public List<ReferenceCodes> rgHearingTypeRecordGroup() {
		final String sql = getQuery("OCUOICHN_FIND_RGHEARINGTYPE");
		final RowMapper<ReferenceCodes> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, referenceCodeMapping);
		ArrayList<ReferenceCodes> refList = new ArrayList<ReferenceCodes>();
		try {
			refList = (ArrayList<ReferenceCodes>) namedParameterJdbcTemplate.query(sql,
					createParams("MODE", ENTERQUERY), referenceCodeRowMapper);
		} catch (Exception e) {
			logger.error(e);
		}
		return refList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<RecordGroup>
	 */
	public List<AgencyInternalLocations> rgInternalLocationsRecordGroup(final String caseloadId) {
		final String sql = getQuery("OCUOICHN_FIND_RGINTERNALLOCATIONS");
		final RowMapper<AgencyInternalLocations> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,
				AgencyInternalLocations.class, agencyInternalLocationsMapping);
		ArrayList<AgencyInternalLocations> refList = new ArrayList<AgencyInternalLocations>();
		try {
			refList = (ArrayList<AgencyInternalLocations>) namedParameterJdbcTemplate.query(sql,
					createParams("CASELOADID", caseloadId, "MODE", ENTERQUERY), referenceCodeRowMapper);
		} catch (Exception e) {
			logger.error(e);
		}
		return refList;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao OicHearings
	 *
	 * @return List<OicHearings>
	 *
	 */
	public List<OicHearings> oicHearSearchOicHearings(final OicHearings objSearchDao) {
		final String sql = getQuery("OCUOICHN_OICHEAR_FIND_OIC_HEARINGS");
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource params = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" where ");
			if (objSearchDao != null && objSearchDao.getOicIncidentId() != null) {
				sqlQuery.append("OIC_INCIDENT_ID = :OIC_INCIDENT_ID" + " and ");
				params.addValue("OIC_INCIDENT_ID", objSearchDao.getOicIncidentId());
			}
			if (objSearchDao.getOicHearingId() != null) {
				sqlQuery.append("OIC_HEARING_ID = :OIC_HEARING_ID" + " and ");
				params.addValue("OIC_HEARING_ID", objSearchDao.getOicHearingId());
			}
			if (objSearchDao.getOicHearingType() != null) {
				sqlQuery.append("OIC_HEARING_TYPE = :OIC_HEARING_TYPE" + " and ");
				params.addValue("OIC_HEARING_TYPE", objSearchDao.getOicHearingType());
			}
			if (objSearchDao.getScheduleDate() != null) {
				sqlQuery.append("SCHEDULE_DATE = :SCHEDULE_DATE" + " and ");
				params.addValue("SCHEDULE_DATE", objSearchDao.getScheduleDate());
			}
			if (objSearchDao.getScheduleTime() != null) {
				sqlQuery.append("SCHEDULE_TIME = :SCHEDULE_TIME" + " and ");
				params.addValue("SCHEDULE_TIME", objSearchDao.getScheduleTime());
			}
			if (objSearchDao.getHearingDate() != null) {
				sqlQuery.append("HEARING_DATE = :HEARING_DATE" + " and ");
				params.addValue("HEARING_DATE", objSearchDao.getHearingDate());
			}
			if (objSearchDao.getHearingTime() != null) {
				sqlQuery.append("HEARING_TIME = :HEARING_TIME" + " and ");
				params.addValue("HEARING_TIME", objSearchDao.getHearingTime());
			}
			if (objSearchDao.getHearingStaffId() != null) {
				sqlQuery.append("HEARING_STAFF_ID = :HEARING_STAFF_ID" + " and ");
				params.addValue("HEARING_STAFF_ID", objSearchDao.getHearingStaffId());
			}
			if (objSearchDao.getVisitJusticeText() != null) {
				sqlQuery.append("VISIT_JUSTICE_TEXT = :VISIT_JUSTICE_TEXT" + " and ");
				params.addValue("VISIT_JUSTICE_TEXT", objSearchDao.getVisitJusticeText());
			}
			if (objSearchDao.getCommentText() != null) {
				sqlQuery.append("COMMENT_TEXT = :COMMENT_TEXT" + " and ");
				params.addValue("COMMENT_TEXT", objSearchDao.getCommentText());
			}
			if (objSearchDao.getTapeNumber() != null) {
				sqlQuery.append("TAPE_NUMBER = :TAPE_NUMBER" + " and ");
				params.addValue("TAPE_NUMBER", objSearchDao.getTapeNumber());
			}
			if (objSearchDao.getCreateDateTime() != null) {
				sqlQuery.append("CREATE_DATETIME = :CREATE_DATETIME" + " and ");
				params.addValue("CREATE_DATETIME", objSearchDao.getCreateDateTime());
			}
			if (objSearchDao.getCreateUserId() != null) {
				sqlQuery.append("CREATE_USER_ID = :CREATE_USER_ID" + " and ");
				params.addValue("CREATE_USER_ID", objSearchDao.getCreateUserId());
			}
			if (objSearchDao.getModifyDateTime() != null) {
				sqlQuery.append("MODIFY_DATETIME = :MODIFY_DATETIME" + " and ");
				params.addValue("MODIFY_DATETIME", objSearchDao.getModifyDateTime());
			}
			if (objSearchDao.getModifyUserId() != null) {
				sqlQuery.append("MODIFY_USER_ID = :MODIFY_USER_ID" + " and ");
				params.addValue("MODIFY_USER_ID", objSearchDao.getModifyUserId());
			}
			if (objSearchDao.getInternalLocationId() != null) {
				sqlQuery.append("INTERNAL_LOCATION_ID = :INTERNAL_LOCATION_ID" + " and ");
				params.addValue("INTERNAL_LOCATION_ID", objSearchDao.getInternalLocationId());
			}
			if (objSearchDao.getRepresentativeText() != null) {
				sqlQuery.append("REPRESENTATIVE_TEXT = :REPRESENTATIVE_TEXT" + " and ");
				params.addValue("REPRESENTATIVE_TEXT", objSearchDao.getRepresentativeText());
			}
			if (objSearchDao.getEventId() != null) {
				sqlQuery.append("EVENT_ID = :EVENT_ID" + " and ");
				params.addValue("EVENT_ID", objSearchDao.getEventId());
			}
			if (objSearchDao.getEventStatus() != null) {
				sqlQuery.append("EVENT_STATUS = :EVENT_STATUS" + " and ");
				params.addValue("EVENT_STATUS", objSearchDao.getEventStatus());
			}
			if (objSearchDao.getSealFlag() != null) {
				sqlQuery.append("SEAL_FLAG = :SEAL_FLAG" + " and ");
				params.addValue("SEAL_FLAG", objSearchDao.getSealFlag());
			}
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("where")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("and")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		preparedSql = preparedSql.concat(" order by  HEARING_DATE desc, HEARING_TIME desc ");
		final RowMapper<OicHearings> OicHearingsRowMapper = Row2BeanRowMapper.makeMapping(sql, OicHearings.class,
				oicHearingsMapping);
		final ArrayList<OicHearings> returnList = (ArrayList<OicHearings>) namedParameterJdbcTemplate.query(preparedSql,
				params, OicHearingsRowMapper);
		return returnList;
	}

	/**
	 * @param
	 *
	 */
	public int preInsert() {
		return 0;
	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstOicHearings List<OicHearings>
	 *
	 */
	public Integer oicHearUpdateOicHearings(final List<OicHearings> lstOicHearings) {
		final String sql = getQuery("OCUOICHN_OICHEAR_UPDATE_OIC_HEARINGS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OicHearings oicHearings : lstOicHearings) {
			parameters.add(new BeanPropertySqlParameterSource(oicHearings));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOicHearings.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstOicHearings List<OicHearings>
	 *
	 */
	public Integer oicHearDeleteOicHearings(final List<OicHearings> lstOicHearings) {
		final String sql = getQuery("OCUOICHN_OICHEAR_DELETE_OIC_HEARINGS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OicHearings oicHearings : lstOicHearings) {
			parameters.add(new BeanPropertySqlParameterSource(oicHearings));
		}
		try {
			String tableName = "OIC_HEARINGS";
			String whereClause = "OIC_HEARING_ID  = :oicHearingId";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method oicHearDeleteOicHearings", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOicHearings.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao OicHearingNotices
	 *
	 * @return List<OicHearingNotices>
	 *
	 */
	public List<OicHearingNotices> oicHearNotiSearchOicHearingNotices(final OicHearingNotices objSearchDao) {
		final String sql = getQuery("OCUOICHN_OICHEARNOTI_FIND_OIC_HEARING_NOTICES");
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource params = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" where ");

			if (objSearchDao.getOicHearingId() != null) {
				sqlQuery.append("OIC_HEARING_ID = :OIC_HEARING_ID" + " and ");
				params.addValue("OIC_HEARING_ID", objSearchDao.getOicHearingId());
			}
			if (objSearchDao.getOicNoticeSeq() != null) {
				sqlQuery.append("OIC_NOTICE_SEQ = :OIC_NOTICE_SEQ" + " and ");
				params.addValue("OIC_NOTICE_SEQ", objSearchDao.getOicNoticeSeq());
			}
			if (objSearchDao.getDeliveryDate() != null) {
				sqlQuery.append("DELIVERY_DATE = :DELIVERY_DATE" + " and ");
				params.addValue("DELIVERY_DATE", objSearchDao.getDeliveryDate());
			}
			if (objSearchDao.getDeliveryTime() != null) {
				sqlQuery.append("DELIVERY_TIME = :DELIVERY_TIME" + " and ");
				params.addValue("DELIVERY_TIME", objSearchDao.getDeliveryTime());
			}
			if (objSearchDao.getDeliveryStaffId() != null) {
				sqlQuery.append("DELIVERY_STAFF_ID = :DELIVERY_STAFF_ID" + " and ");
				params.addValue("DELIVERY_STAFF_ID", objSearchDao.getDeliveryStaffId());
			}
			if (objSearchDao.getCreateDateTime() != null) {
				sqlQuery.append("CREATE_DATETIME = :CREATE_DATETIME" + " and ");
				params.addValue("CREATE_DATETIME", objSearchDao.getCreateDateTime());
			}
			if (objSearchDao.getCreateUserId() != null) {
				sqlQuery.append("CREATE_USER_ID = :CREATE_USER_ID" + " and ");
				params.addValue("CREATE_USER_ID", objSearchDao.getCreateUserId());
			}
			if (objSearchDao.getModifyDateTime() != null) {
				sqlQuery.append("MODIFY_DATETIME = :MODIFY_DATETIME" + " and ");
				params.addValue("MODIFY_DATETIME", objSearchDao.getModifyDateTime());
			}
			if (objSearchDao.getModifyUserId() != null) {
				sqlQuery.append("MODIFY_USER_ID = :MODIFY_USER_ID" + " and ");
				params.addValue("MODIFY_USER_ID", objSearchDao.getModifyUserId());
			}
			if (objSearchDao.getCommentText() != null) {
				sqlQuery.append("COMMENT_TEXT = :COMMENT_TEXT" + " and ");
				params.addValue("COMMENT_TEXT", objSearchDao.getCommentText());
			}
			if (objSearchDao.getSealFlag() != null) {
				sqlQuery.append("SEAL_FLAG = :SEAL_FLAG" + " and ");
				params.addValue("SEAL_FLAG", objSearchDao.getSealFlag());
			}
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("where")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("and")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		preparedSql = preparedSql.concat(" order by  DELIVERY_DATE desc, DELIVERY_TIME desc ");
		final RowMapper<OicHearingNotices> OicHearingNoticesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OicHearingNotices.class, oicHearingNoticesMapping);
		final ArrayList<OicHearingNotices> returnList = (ArrayList<OicHearingNotices>) namedParameterJdbcTemplate
				.query(preparedSql, params, OicHearingNoticesRowMapper);
		return returnList;
	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstOicHearingNotices List<OicHearingNotices>
	 *
	 */
	public Integer oicHearNotiUpdateOicHearingNotices(final List<OicHearingNotices> lstOicHearingNotices) {
		final String sql = getQuery("OCUOICHN_OICHEARNOTI_UPDATE_OIC_HEARING_NOTICES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OicHearingNotices oicHearingNotices : lstOicHearingNotices) {
			parameters.add(new BeanPropertySqlParameterSource(oicHearingNotices));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOicHearingNotices.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstOicHearingNotices List<OicHearingNotices>
	 *
	 */
	public Integer oicHearNotiDeleteOicHearingNotices(final List<OicHearingNotices> lstOicHearingNotices) {
		final String sql = getQuery("OCUOICHN_OICHEARNOTI_DELETE_OIC_HEARING_NOTICES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OicHearingNotices oicHearingNotices : lstOicHearingNotices) {
			parameters.add(new BeanPropertySqlParameterSource(oicHearingNotices));
		}
		try {
			String tableName = "OIC_HEARING_NOTICES";
			String whereClause = "OIC_HEARING_ID  = :oicHearingId and OIC_NOTICE_SEQ = :oicNoticeSeq";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method oicHearNotiDeleteOicHearingNotices", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOicHearingNotices.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * ocuoichnOicHearOnCheckDeleteMasteroicHearNotiCur
	 *
	 * @param params
	 *
	 */
	public List<Object> oicHearOnCheckDeleteMasteroicHearNotiCur(final OicHearingNotices paramBean) {
		final String sql = getQuery("OCUOICHN_OIC_HEAR_ONCHECKDELETEMASTER_OIC_HEAR_NOTI_CUR");
		final List<Object> returnObj = (List<Object>) namedParameterJdbcTemplate.queryForList(sql,
				createParams("OICHEARINGID", paramBean.getOicHearingId()), Object.class);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * ocuoichnOicHearPreInsertgetEventIdCur
	 *
	 * @param params
	 *
	 */
	public Object oicHearPreInsertgetEventIdCur(final Dual paramBean) {
		final String sql = getQuery("OCUOICHN_OIC_HEAR_PREINSERT_GET_EVENT_ID_CUR");
		final Object returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), Object.class);
		return returnObj;
	}

	public Integer oicHearNotiInsertOicHearingNotices(final List<OicHearingNotices> lstOicHearingNotices) {
		final String sql = getQuery("OCUOICHN_OICHEARNOTI_INSERT_OIC_HEARING_NOTICES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OicHearingNotices oicHearingNotices : lstOicHearingNotices) {
			parameters.add(new BeanPropertySqlParameterSource(oicHearingNotices));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOicHearingNotices.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to insert the data base tables based on
	 *
	 * @param lstOicHearings List<OicHearings>
	 *
	 */
	public Integer oicHearInsertOicHearings(final List<OicHearings> lstOicHearings) {
		final String sql = getQuery("OCUOICHN_OICHEAR_INSERT_OIC_HEARINGS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OicHearings oicHearings : lstOicHearings) {
			if (oicHearings.getEventId() == null && oicHearings.getScheduleDate() != null) {
				oicHearings.setEventId(getEventIdSeq());
			}
			parameters.add(new BeanPropertySqlParameterSource(oicHearings));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOicHearings.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to get max OIC_HEARING_ID from OIC_HEARINGS table
	 * 
	 * getMaxOicHearId
	 *
	 */
	public Integer getMaxOicHearId() {
		final String sql = getQuery("OCUOICHN_FIND_MAXOICHEARID");
		Integer returnList;
		returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams(), Integer.class);
		return returnList;
	}

	/**
	 * This method is used to get max OIC_NOTICE_SEQ from OIC_HEARING_NOTICES table
	 * 
	 * getMaxOicNoticeSeq
	 *
	 */
	public Integer getMaxOicNoticeSeq() {
		final String sql = getQuery("OCUOICHN_FIND_MAXOICNOTICESEQ");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Integer.class);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * oicHearCheckScheduleConflict
	 *
	 *
	 */
	public Integer oicHearCheckScheduleConflict(final OicHearings objSearchDao) {
		final String sql = getQuery("OCUOICHN_CHECK_SCHEDULE_CONFLICT");
		Integer returnList;
		returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("OFFENDER_BOOK_ID",
				objSearchDao.getOffenderBookId(), "EVENT_DATE", objSearchDao.getHearingDate()), Integer.class);
		return returnList;

	}

	private Integer getEventIdSeq() {
		final String sql = getQuery("OCUOICHN_OIC_HEAR_PREINSERT_GET_EVENT_ID_CUR");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Integer.class);
	}

	@Override
	public Integer getOffenderBookId(Integer oicIncidentId) {
		final String sql = getQuery("OCUOICHN_GET_OFFENDER_BOOK_ID");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("oicIncidentId", oicIncidentId),
					Integer.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + "error in checkNonAssociations" + e.getMessage());
			return null;
		}
	}

	@Override
	public Integer getIncidentId(Integer offenderBookId) {
		final String sql = getQuery("OCUOICHN_GET_INCIDENTID");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderBookId", offenderBookId),
					Integer.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + "error in checkNonAssociations" + e.getMessage());
			return null;
		}
	}

	@Override
	public List<OicHearings> getNonAssocationVisitSchedule(Integer oicIncidentId, OicHearings oicHearings) {
		final String sql = getQuery("OCUOICHN_NON_ASSOCATION_VISIT_SCHEDULE");
		String i = oicHearings.getInternalLocationIdDes();
		int locationId = Integer.parseInt(i);
		try {
			RowMapper<OicHearings> mapper = Row2BeanRowMapper.makeMapping(sql, OicHearings.class, oichearingmapping);
			return namedParameterJdbcTemplate.query(sql, createParams("oicIncidentId", oicIncidentId, "hearing_date",
					oicHearings.getHearingDate(), "internal_location_id", locationId), mapper);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + "error in checkNonAssociations" + e.getMessage());
			return null;
		}
	}

	@Override
	public List<OffenderDetail> getNonAssocationVisitScheduleOffenderDetails(Integer oicIncidentId) {
		final String sql = getQuery("OCUOICHN_NON_ASSOCATION_VISIT_SCHEDULE_OFFENDER_DETAILS");
		try {
			RowMapper<OffenderDetail> mapper = Row2BeanRowMapper.makeMapping(sql, OffenderDetail.class,
					oichearingmapping);
			return namedParameterJdbcTemplate.query(sql, createParams("oicIncidentId", oicIncidentId), mapper);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + "error in checkNonAssociations" + e.getMessage());
			return null;
		}
	}

	@Override
	public List<OffenderNaDetails> getNonAssocationOffenderList(Integer offenderBookId, Integer internalLocation) {

		try {
			final String sql = getQuery("OCUOICHN_INDIVIDUAL_NON_ASSOCATION_OFFENDERS_LIST");
			final RowMapper<OffenderNaDetails> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
					OffenderNaDetails.class, intLocUsgMapping);
			return namedParameterJdbcTemplate.query(sql,
					createParams("offenderBookId", offenderBookId, "internalLocationId", internalLocation),
					columnRowMapper);
		} catch (Exception e) {
			logger.error("getNonAssociationOffenderList:" + e);
		}
		return null;

	}

}
