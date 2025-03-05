package net.syscon.s4.inst.movementexternal.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.inst.movementexternal.OidescapRepository;
import net.syscon.s4.inst.movementexternal.beans.OffenderEscapes;

/**
 * Class OidescapRepositoryImpl
 */
@Repository
public class OidescapRepositoryImpl extends RepositoryBase implements OidescapRepository {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidescapRepositoryImpl.class.getName());

	/**
	 * Creates new OidescapRepositoryImpl class Object
	 */
	public OidescapRepositoryImpl() {
	}

	private final Map<String, FieldMapper> offEscMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("ESCAPE_MOVEMENT_REASON", new FieldMapper("escapeMovementReason"))
			.put("IN_COMPANY_FLAG", new FieldMapper("inCompanyFlag"))
			.put("ESCAPE_ESCORT_CODE", new FieldMapper("escapeEscortCode"))
			.put("RECPATURE_TIME", new FieldMapper("recpatureTime"))
			.put("RECPATURE_COMMENT_TEXT", new FieldMapper("recpatureCommentText"))
			.put("ADJUST_SENTENCE_FLAG", new FieldMapper("adjustSentenceFlag"))
			.put("READMISSION_DATE", new FieldMapper("readmissionDate"))
			.put("ESCAPE_CIRCUMSTANCE_CODE", new FieldMapper("escapeCircumstanceCode"))
			.put("READMISS_COMMENT_TEXT", new FieldMapper("readmissCommentText"))
			.put("SEAL_FLAG", new FieldMapper("sealFlag")).put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("SECURITY_BREACH_CODE", new FieldMapper("securityBreachCode"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("ESCAPE_AGY_LOC_ID", new FieldMapper("escapeAgyLocId"))
			.put("LAST_SEEN_DATE", new FieldMapper("lastSeenDate")).put("ESCAPE_TIME", new FieldMapper("escapeTime"))
			.put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId"))
			.put("RECAPTURE_DATE", new FieldMapper("recaptureDate"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("OFFENDER_ADJUST_ID", new FieldMapper("offenderAdjustId"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("RECAPTURE_MOVEMENT_REASON", new FieldMapper("recaptureMovementReason"))
			.put("INCIDENT_NUMBER", new FieldMapper("incidentNumber"))
			.put("ARREST_AGY_CODE", new FieldMapper("arrestAgyCode"))
			.put("READMISSION_TIME", new FieldMapper("readmissionTime"))
			.put("ESCAPE_DATE", new FieldMapper("escapeDate"))
			.put("READMISS_AGY_LOC_ID", new FieldMapper("readmissAgyLocId"))
			.put("ESCAPE_COMMENT_TEXT", new FieldMapper("escapeCommentText"))
			.put("LAST_SEEN_TIME", new FieldMapper("lastSeenTime")).put("ESCAPE_ID", new FieldMapper("escapeId"))
			.put("ESCAPE_REGISTRATION_REF", new FieldMapper("escapeRegistrationRef")).build();
	private final Map<String, FieldMapper> sysProfMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PROFILE_CODE", new FieldMapper("profileCode")).put("PROFILE_TYPE", new FieldMapper("profileType"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId")).put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("OLD_TABLE_NAME", new FieldMapper("oldTableName"))
			.put("PROFILE_VALUE", new FieldMapper("profileValue"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("PROFILE_VALUE_2", new FieldMapper("profileValue2")).put("DESCRIPTION", new FieldMapper("description"))
			.build();
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CODE", new FieldMapper("code")).put("AGY_LOC_ID", new FieldMapper("agyLocId"))
			.put("DESCRIPTION", new FieldMapper("description")).put("LIST_SEQ", new FieldMapper("listSeq"))
			.put("ACTIVE_FLAG", new FieldMapper("activeFlag")).build();

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao OffenderEscapes
	 *
	 * @return List<OffenderEscapes>
	 *
	 * @throws SQLException
	 */
	public List<OffenderEscapes> offEscExecuteQuery(final OffenderEscapes objSearchDao) {
		final String sql = getQuery("OIDESCAP_OFFESC_FIND_OFFENDER_ESCAPES");
		final RowMapper<OffenderEscapes> offEscRowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderEscapes.class,
				offEscMapping);
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource valuesList = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" where ");
			if (objSearchDao.getNbtOffenderId() != null) {
				sqlQuery.append(
						"OFFENDER_BOOK_ID in (  SELECT OFFENDER_BOOK_ID   FROM OFFENDER_BOOKINGS   WHERE OFFENDER_ID = :nbtOffenderId) Order By ESCAPE_DATE DESC ");
				valuesList.addValue("nbtOffenderId", objSearchDao.getNbtOffenderId());
			}
		}
		preparedSql = sqlQuery.toString().trim();
		return namedParameterJdbcTemplate.query(preparedSql, valuesList, offEscRowMapper);
	}

	/**
	 * @param
	 *
	 *
	 */
	public Integer offEscPreInsert() {
		final String sql = getQuery("OIDESCAP_OFFESC_PRE_INSERT");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Integer.class);
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstOffEsc List<OffenderEscapes>
	 *
	 * @return Integer
	 *
	 */
	public Integer offEscInsertOffenderEscapes(final List<OffenderEscapes> lstOffEsc) {
		Integer returnValue = 0;
		final String sql = getQuery("OIDESCAP_OFFESC_INSERT_OFFENDER_ESCAPES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();

		for (final OffenderEscapes offenderEscapes : lstOffEsc) {
			parameters.add(new BeanPropertySqlParameterSource(offenderEscapes));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
			if (lstOffEsc.size() == returnArray.length) {
				returnValue = lstOffEsc.get(0).getEscapeId();
			}
		} catch (Exception e) {
			logger.error(" Save method failure: ", e);
		}
		return returnValue;

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstOffEsc List<OffenderEscapes>
	 *
	 * @throws SQLException
	 */
	public Integer offEscUpdateOffenderEscapes(final List<OffenderEscapes> lstOffEsc) {
		final String sql = getQuery("OIDESCAP_OFFESC_UPDATE_OFFENDER_ESCAPES");
		int returnValue = 0;
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderEscapes offenderEscapes : lstOffEsc) {
			parameters.add(new BeanPropertySqlParameterSource(offenderEscapes));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffEsc.size() == returnArray.length) {
			returnValue = 1;
		}
		return returnValue;

	}

	/**
	 * This method is used to delete the data base tables based on
	 *
	 * @param lstOffEsc List<OffenderEscapes>
	 *
	 */
	public Integer offEscDeleteOffenderEscapes(final List<OffenderEscapes> lstOffEsc) {
		final String sql = getQuery("OIDESCAP_OFFESC_DELETE_OFFENDER_ESCAPES");
		int returnValue = 0;
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderEscapes offenderEscapes : lstOffEsc) {
			parameters.add(new BeanPropertySqlParameterSource(offenderEscapes));
		}
		try {
			batchUpdatePreDeletedRows("OFFENDER_ESCAPES", "ESCAPE_ID  = :escapeId", parameters);
		} catch (Exception e) {
			logger.error("batchUpdatePreDeletedRows in offEscDeleteOffenderEscapes"+e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffEsc.size() == returnArray.length) {
			returnValue = 1;
		}
		return returnValue;

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
		final String sql = getQuery("OIDESCAP_SYSPFL_FIND_SYSTEM_PROFILES");
		final RowMapper<SystemProfiles> sysProRM = Row2BeanRowMapper.makeMapping(sql, SystemProfiles.class,
				sysProfMapping);
		return namedParameterJdbcTemplate.query(sql, createParams(), sysProRM);
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<AgencyLocations>
	 */
	public List<AgencyLocations> cgfkOffEscEscapeAgyLocIdRecordGroup() {
		final String sql = getQuery("OIDESCAP_FIND_CGFKOFFESCESCAPEAGYLOCID");
		final RowMapper<AgencyLocations> mRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				mMapping);
		List<AgencyLocations> lstAgyRecords = new ArrayList<AgencyLocations>();
		try {
			lstAgyRecords = (List<AgencyLocations>) namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);

		} catch (Exception e) {
			logger.error("In cgfkOffEscEscapeAgyLocIdRecordGroup method : ", e);
		}
		return lstAgyRecords;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return ListReferenceCodesM>
	 */
	public List<ReferenceCodes> cgfkOffEscEscapeEscortCodRecordGroup() {
		final String sql = getQuery("OIDESCAP_FIND_CGFKOFFESCESCAPEESCORTCOD");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);

	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return ListReferenceCodesM>
	 */
	public List<ReferenceCodes> cgfkOffEscEscapeCircumstanRecordGroup() {
		final String sql = getQuery("OIDESCAP_FIND_CGFKOFFESCESCAPECIRCUMSTAN");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);

	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> cgfkOffEscSecurityBreachCRecordGroup() {
		final String sql = getQuery("OIDESCAP_FIND_CGFKOFFESCSECURITYBREACHC");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);

	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodesM>
	 */
	public List<ReferenceCodes> cgfkOffEscArrestAgyCodeRecordGroup() {
		final String sql = getQuery("OIDESCAP_FIND_CGFKOFFESCARRESTAGYCODE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
	}

	/**
	 * Used to getBooking no for the Offender
	 * 
	 * @return String
	 */
	public String getOffenderBookingNo(final OffenderEscapes searchRecord) {
		final String sql = getQuery("OIDESCAP_OFF_ESC_POSTQUERY");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("OFFENDERBOOKID",
				searchRecord.getOffenderBookId(), "OFFENDERIDDISPLAY", searchRecord.getOffenderIdDisplay(),"USERID",searchRecord.getCreateUserId()),
				String.class);
	}

	/**
	 * Used to getMax escape Date no for the Offender
	 * 
	 * @return Date
	 */
	public Date getMaxEscapeDate(final Integer offenderBookId) {
		final String sql = getQuery("OIDESCAP_OFF_ESC_MAX_ESCAPE_DATE");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("OFFENDERBOOKID", offenderBookId),
				Date.class);
	}

	/**
	 * Used todspEscapeReason for the Offender
	 * 
	 * @return Date
	 */
	public String getreasonDescPreQuery(final String reasonCode) {
		final String sql = getQuery("OIDESCAP_ESCAPE_CUR_PRE_QUERY");
		String returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("MOVEMENT_REASON_CODE", reasonCode),
					String.class);
		} catch (Exception e) {
			logger.error("In method getreasonDescPreQuery", e);
			return returnObj;
		}
		return returnObj;
	}

	/**
	 * Used to dspRecaptureReason for the Offender
	 * 
	 * @return Date
	 */
	public String getRecaptureReasonDescPreQuery(final OffenderEscapes searchRecord) {
		final String sql = getQuery("OIDESCAP_RECAPTURE_CUR_PRE_QUERY");
		String returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("RECAPTURE_MOVEMENT_REASON", searchRecord.getRecaptureMovementReason(),
							"ESCAPE_MOVEMENT_REASON", searchRecord.getEscapeMovementReason()),
					String.class);
		} catch (Exception e) {
			logger.error("In method getRecaptureReasonDescPreQuery", e);
			return returnObj;
		}
		return returnObj;
	}
	
	@Override
	public String getOffenderFromLocation(Integer offenderBookId) {
		final String sql = getQuery("OIDESCAP_OFF_ESCAPE_FROM_LOCATION");
		String fromLocation = null;
		try {
			fromLocation = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("offenderBookId", offenderBookId), String.class);
		} catch (Exception e) {
			logger.error(e);
			fromLocation = null;
		}

		return fromLocation;
	}

}
