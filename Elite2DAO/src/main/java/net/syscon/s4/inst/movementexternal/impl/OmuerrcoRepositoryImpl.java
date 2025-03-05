package net.syscon.s4.inst.movementexternal.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.SysDual;
import net.syscon.s4.inst.movementexternal.OmuerrcoRepository;
import net.syscon.s4.inst.movementexternal.beans.OffenderEscapes;

/**
 * Class OmuerrcoRepositoryImpl
 */
@Repository
public class OmuerrcoRepositoryImpl extends RepositoryBase implements OmuerrcoRepository {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidescapRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> offEscMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("ESCAPE_MOVEMENT_REASON", 			new FieldMapper("escapeMovementReason"))
			.put("IN_COMPANY_FLAG", 				new FieldMapper("inCompanyFlag"))
			.put("ESCAPE_ESCORT_CODE",			    new FieldMapper("escapeEscortCode"))
			.put("RECPATURE_TIME", 					new FieldMapper("recpatureTime"))
			.put("RECPATURE_COMMENT_TEXT", 			new FieldMapper("recpatureCommentText"))
			.put("ADJUST_SENTENCE_FLAG", 			new FieldMapper("adjustSentenceFlag"))
			.put("READMISSION_DATE", 				new FieldMapper("readmissionDate"))
			.put("ESCAPE_CIRCUMSTANCE_CODE", 		new FieldMapper("escapeCircumstanceCode"))
			.put("READMISS_COMMENT_TEXT", 			new FieldMapper("readmissCommentText"))
			.put("SEAL_FLAG", 						new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", 				new FieldMapper("createDatetime"))
			.put("SECURITY_BREACH_CODE", 			new FieldMapper("securityBreachCode"))
			.put("MODIFY_DATETIME", 				new FieldMapper("modifyDatetime"))
			.put("ESCAPE_AGY_LOC_ID", 				new FieldMapper("escapeAgyLocId"))
			.put("LAST_SEEN_DATE", 					new FieldMapper("lastSeenDate"))
			.put("ESCAPE_TIME",			 			new FieldMapper("escapeTime"))
			.put("OFFENDER_BOOK_ID", 				new FieldMapper("offenderBookId"))
			.put("RECAPTURE_DATE", 					new FieldMapper("recaptureDate"))
			.put("CREATE_USER_ID", 					new FieldMapper("createUserId"))
			.put("OFFENDER_ADJUST_ID", 				new FieldMapper("offenderAdjustId"))
			.put("MODIFY_USER_ID", 					new FieldMapper("modifyUserId"))
			.put("RECAPTURE_MOVEMENT_REASON", 		new FieldMapper("recaptureMovementReason"))
			.put("INCIDENT_NUMBER", 				new FieldMapper("incidentNumber"))
			.put("ARREST_AGY_CODE", 				new FieldMapper("arrestAgyCode"))
			.put("READMISSION_TIME", 				new FieldMapper("readmissionTime"))
			.put("ESCAPE_DATE", 					new FieldMapper("escapeDate"))
			.put("READMISS_AGY_LOC_ID", 			new FieldMapper("readmissAgyLocId"))
			.put("ESCAPE_COMMENT_TEXT", 			new FieldMapper("escapeCommentText"))
			.put("LAST_SEEN_TIME", 					new FieldMapper("lastSeenTime"))
			.put("ESCAPE_ID", 						new FieldMapper("escapeId"))
			.put("ESCAPE_REGISTRATION_REF", 		new FieldMapper("escapeRegistrationRef"))
			.build();
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CODE",         new FieldMapper("code"))
			.put("DESCRIPTION",  new FieldMapper("description"))
			.build();
	
	/**
	 * Creates new OmuerrcoRepositoryImpl class Object
	 */
	public OmuerrcoRepositoryImpl() {
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            OffenderEscapes
	 *
	 * @return List<OffenderEscapes>
	 *
	 */
	public List<OffenderEscapes> offEscExecuteQuery(final OffenderEscapes objSearchDao) {
		final String sql = getQuery("OMUERRCO_OFFESC_FIND_OFFENDER_ESCAPES");
		final RowMapper<OffenderEscapes> offEscRowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderEscapes.class,
				offEscMapping);
		return namedParameterJdbcTemplate.query(sql, createParams(), offEscRowMapper);
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
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstOffenderEscapes
	 *            List<OffenderEscapes>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public Integer offEscInsertOffenderEscapes(final List<OffenderEscapes> lstOffEscapes) {
		int insertCount = 0;
		final String sql = getQuery("OMUERRCO_OFFESC_INSERT_OFFENDER_ESCAPES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderEscapes offenderEscapes : lstOffEscapes) {
			parameters.add(new BeanPropertySqlParameterSource(offenderEscapes));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffEscapes.size() == returnArray.length) {
			insertCount = lstOffEscapes.get(0).getEscapeId();
		}
		return insertCount;

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstOffenderEscapes
	 *            List<OffenderEscapes>
	 *
	 * @throws SQLException
	 */
	public Integer offEscUpdateOffenderEscapes(final List<OffenderEscapes> lstOffEscapes) {
		final String sql = getQuery("OMUERRCO_OFFESC_UPDATE_OFFENDER_ESCAPES");
		int returnValue = 0;
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderEscapes offenderEscapes : lstOffEscapes) {
			parameters.add(new BeanPropertySqlParameterSource(offenderEscapes));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffEscapes.size() == returnArray.length) {
			return returnValue = 1;
		}
		return returnValue;

	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> cgfkOffEscSecurityBreachCRecordGroup() {
		final String sql = getQuery("OMUERRCO_FIND_CGFKOFFESCSECURITYBREACHC");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		List<ReferenceCodes> lstRefRecords = new ArrayList<>();
		try {
			lstRefRecords = (List<ReferenceCodes>) namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("In cgfkOffEscSecurityBreachCRecordGroup method : ", e);
		}
		return lstRefRecords;

	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> cgfkOffEscArrestAgyCodeRecordGroup() {
		final String sql = getQuery("OMUERRCO_FIND_CGFKOFFESCARRESTAGYCODE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		List<ReferenceCodes> lstRefRecords = new ArrayList<>();
		try {
			lstRefRecords = (List<ReferenceCodes>) namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("In cgfkOffEscArrestAgyCodeRecordGroup method : ", e);
		}
		return lstRefRecords;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offEscPreInsert
	 *
	 * @param params
	 *
	 */
	public Integer offEscPreInsert() {
		final String sql = getQuery("OMUERRCO_OFF_ESC_PREINSERT_");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Integer.class);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOffEscOffEscRef
	 *
	 * @param params
	 *
	 */
	public String cgfkchkOffEscOffEscRef(final String escDesc) {
		final String sql = getQuery("OMUERRCO_CGFKCHK_OFF_ESC_OFF_ESC_REF_S_");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("SECURITYBREACHCODE", escDesc),
				String.class);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOffEscOffEscRef
	 *
	 * @param params
	 *
	 */
	public String cgfkchkOffArrestCodeRef(final String arrDesc) {
		final String sql = getQuery("OMUERRCO_CGFKCHK_OFF_ESC_OFF_ESC_REF_A_");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("ARRESTAGYCODE", arrDesc), String.class);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgwhenNewFormInstance
	 *
	 * @param params
	 *
	 */
	public List<Object> cgwhenNewFormInstance(final SysDual paramBean) {
		final String sql = getQuery("OMUERRCO_CGWHEN_NEW_FORM_INSTANCE");
		return namedParameterJdbcTemplate.queryForList(sql, createParams(), Object.class);
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<AgencyLocations>
	 */
	public List<AgencyLocations> cgfkOffEscEscapeAgyLocIdRecordGroup() {
		final String sql = getQuery("OMUERRCO_FIND_CGFKOFFESCESCAPEAGYLOCID");
		final RowMapper<AgencyLocations> mRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				mMapping);
		List<AgencyLocations> lstAgyRecords = new ArrayList<>();
		try {
			lstAgyRecords = (ArrayList<AgencyLocations>) namedParameterJdbcTemplate.query(sql, createParams(),
					mRowMapper);
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
		final String sql = getQuery("OMUERRCO_FIND_CGFKOFFESCESCAPEESCORTCOD");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);

	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return ListReferenceCodesM>
	 */
	public List<ReferenceCodes> cgfkOffEscEscapeCircumstanRecordGroup() {
		final String sql = getQuery("OMUERRCO_FIND_CGFKOFFESCESCAPECIRCUMSTAN");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);

	}

}
