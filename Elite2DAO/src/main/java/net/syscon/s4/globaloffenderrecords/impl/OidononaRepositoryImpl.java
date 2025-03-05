package net.syscon.s4.globaloffenderrecords.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
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
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SecurityThreatGroups;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.globaloffenderrecords.OidononaRepository;
import net.syscon.s4.im.beans.OffenderNaDetails;
import net.syscon.s4.im.beans.OffenderNonAssociations;
import net.syscon.s4.im.beans.StgRelationships;

/**
 * Class OidononaRepositoryImpl
 * 
 */
@Repository
public class OidononaRepositoryImpl extends RepositoryBase implements OidononaRepository {

	/**
	 * Logger object used to print the log in the file
	 */
	 private static Logger logger = LogManager.getLogger(OidononaRepositoryImpl.class);
	private final Map<String, FieldMapper> referenceCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("EXPIRED_DATE", 				new FieldMapper("expiredDate"))
			.put("DOMAIN", 						new FieldMapper("domain"))
			.put("NS_REASON_CODE", 				new FieldMapper("nsReasonCode"))
			.put("DSP_RECIP_RSN", 				new FieldMapper("dspRecipRsn"))
			.put("UPDATE_ALLOWED_FLAG", 		new FieldMapper("updateAllowedFlag"))
			.put("DSP_DESCRIPTION", 			new FieldMapper("dspDescription"))
			.put("MODIFY_USER_ID",				new FieldMapper("modifyUserId"))
			.put("LIST_SEQ", 					new FieldMapper("listSeq"))
			.put("CODE", 						new FieldMapper("code"))
			.put("ACTIVE_FLA", 					new FieldMapper("activeFla"))
			.put("PARENT_CODE", 				new FieldMapper("parentCode"))
			.put("DESCRIPTION", 				new FieldMapper("description"))
			.put("NEW_CODE", 					new FieldMapper("newCode"))
			.put("RECIP_NS_REASON_CODE", 		new FieldMapper("recipNsReasonCode"))
			.put("NS_TYPE", 					new FieldMapper("nsType"))
			.put("DSP_DESCRIPTION3", 			new FieldMapper("dspDescription3"))
			.build();
	private final Map<String, FieldMapper> securityThreatGroupsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("NULL", 						new FieldMapper("null"))
			.put("RELATED_STG_ID", 				new FieldMapper("relatedStgId"))
			.put("DESCRIPTIO", 					new FieldMapper("descriptio"))
			.build();
	private final Map<String, FieldMapper> offenderNaDetailsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("'1'", 						new FieldMapper("  '1' ")).build();
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("EXPIRED_DATE", 				new FieldMapper("expiredDate"))
			.put("DOMAIN", 						new FieldMapper("domain"))
			.put("ACTIVE_FLAG", 				new FieldMapper("activeFlag"))
			.put("AGY_LOC_ID", 					new FieldMapper("agyLocId"))
			.put("BOOKING_STATUS", 				new FieldMapper("bookingStatus"))
			.put("MODIFY_USER_ID", 				new FieldMapper("modifyUserId"))
			.put("STATUS_DISPLAY", 				new FieldMapper("statusDisplay"))
			.put("LAST_NAME", 					new FieldMapper("lastName"))
			.put("IN_OUT_STATUS", 				new FieldMapper("inOutStatus"))
			.put("FIRST_NAME", 					new FieldMapper("firstName"))
			.put("OFFENDER_BOOK_ID", 			new FieldMapper("offenderBookId"))
			.put("AGENCY_IML_ID", 				new FieldMapper("agencyImlId"))
			.put("LIST_SEQ", 					new FieldMapper("listSeq"))
			.put("OFFENDER_ID_DISPLAY", 		new FieldMapper("offenderIdDisplay"))
			.put("PARENT_CODE", 				new FieldMapper("parentCode"))
			.put("OFFENDER_ID", 				new FieldMapper("offenderId"))
			.put("LOCATION_CODE",	 			new FieldMapper("locationCode"))
			.put("DESCRIPTION", 				new FieldMapper("description"))
			.put("NEW_CODE", 					new FieldMapper("newCode"))
			.put("BIRTH_DATE", 					new FieldMapper("birthDate"))
			.put("MIDDLE_NAME", 				new FieldMapper("middleName"))
			.put("LIVING_UNIT_DESCRIPTION", 	new FieldMapper("livingUnitDescription"))
			.put("CODE", 						new FieldMapper("code"))
			.build();
	private final Map<String, FieldMapper> offenderNonAssociationsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_ID", 				new FieldMapper("offenderId"))
			.put("NS_OFFENDER_ID", 				new FieldMapper("nsOffenderId"))
			.put("OFFENDER_BOOK_ID", 			new FieldMapper("offenderBookId"))
			.put("NS_OFFENDER_BOOK_ID", 		new FieldMapper("nsOffenderBookId"))
			.put("NS_REASON_CODE", 				new FieldMapper("nsReasonCode"))
			.put("NS_LEVEL_CODE", 				new FieldMapper("nsLevelCode"))
			.put("INTERNAL_LOCATION_FLAG", 		new FieldMapper("internalLocationFlag"))
			.put("RECIP_NS_REASON_CODE", 		new FieldMapper("recipNsReasonCode"))
			.put("CREATE_DATETIME", 			new FieldMapper("createDatetime"))
			.put("CREATE_USER_ID", 				new FieldMapper("createUserId"))
			.put("MODIFY_DATETIME", 			new FieldMapper("modifyDatetime"))
			.put("MODIFY_USER_ID", 				new FieldMapper("modifyUserId"))
			.put("SEAL_FLAG", 					new FieldMapper("sealFlag"))
			.build();

	private final Map<String, FieldMapper> stgRelationshipsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("1", 							new FieldMapper("1")).build();

	private final Map<String, FieldMapper> systemProfilesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("1", 							new FieldMapper("1")).build();
	private final Map<String, FieldMapper> vBlockMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_ID", 					new FieldMapper("offenderId"))
			.put("OFFENDER_ID_DISPLAY", 			new FieldMapper("offenderIdDisplay"))
			.put("LAST_NAME", 						new FieldMapper("lastName"))
			.put("FIRST_NAME", 						new FieldMapper("firstName"))
			.put("MIDDLE_NAME", 					new FieldMapper("middleName"))
			.put("PRISON_LOCATION", 				new FieldMapper("prisonLocation"))
			.put("BIRTH_DATE", 						new FieldMapper("birthDate"))
			.put("OFFENDER_BOOK_ID", 				new FieldMapper("offenderBookId"))
			.put("IN_OUT_STATUS", 					new FieldMapper("inOutStatus"))
			.put("BOOKING_STATUS", 					new FieldMapper("bookingStatus"))
			.put("AGY_LOC_ID", 						new FieldMapper("agyLocId"))
			.put("ACTIVE_FLAG", 					new FieldMapper("activeFlag"))
			.put("STATUS_DISPLAY", 					new FieldMapper("statusDisplay"))
			.put("AGENCY_IML_ID", 					new FieldMapper("agencyImlId"))
			.put("LOCATION_CODE", 					new FieldMapper("locationCode"))
			.put("ROOT_OFFENDER_ID", 				new FieldMapper("rootOffenderId"))
			.build();

	/**
	 * Creates new OidononaRepositoryImpl class Object
	 */
	public OidononaRepositoryImpl() {
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            OffenderNonAssociations
	 *
	 * @return List<OffenderNonAssociations>
	 *
	 * @throws SQLException
	 */
	public List<OffenderNonAssociations> offNaExecuteQuery(final OffenderNonAssociations objOffenderAlerts) {
		final String sql = getQuery("OIDONONA_OFFNA_FIND_OFFENDER_NON_ASSOCIATIONS");
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource params = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objOffenderAlerts != null) {
			sqlQuery.append(" WHERE ");

			if (objOffenderAlerts.getOffenderIdDisplay() != null && objOffenderAlerts.getLastName() != null &&
					objOffenderAlerts.getFirstName() != null && objOffenderAlerts.getLivingUnitDescription() != null) {
				sqlQuery.append(" ((OFFENDER_ID) IN (SELECT ROOT_OFFENDER_ID FROM V_HEADER_BLOCK_FN(:USERID) V_HEADER_BLOCK WHERE OFFENDER_ID_DISPLAY  LIKE :OFFENDERIDDISPLAY "
						+ " AND  LAST_NAME  LIKE :LASTNAME AND FIRST_NAME  LIKE :FIRSTNAME AND LIVING_UNIT_DESCRIPTION LIKE :LIVINGUNITDESCRIPTION)) ");
				params.addValue("OFFENDERIDDISPLAY", objOffenderAlerts.getOffenderIdDisplay());
				params.addValue("LASTNAME", objOffenderAlerts.getLastName());
				params.addValue("FIRSTNAME", objOffenderAlerts.getFirstName());
				params.addValue("LIVINGUNITDESCRIPTION", objOffenderAlerts.getLivingUnitDescription());
				params.addValue("USERID", objOffenderAlerts.getCreateUserId());		}
		}
		preparedSql = sqlQuery.toString().trim();
		final RowMapper<OffenderNonAssociations> offenderNonAssociationsRowMapper = Row2BeanRowMapper.makeMapping(preparedSql,
				OffenderNonAssociations.class, offenderNonAssociationsMapping);
		List<OffenderNonAssociations> returnList = new ArrayList<OffenderNonAssociations>();
		returnList= namedParameterJdbcTemplate.query(preparedSql, params, offenderNonAssociationsRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstOffenderNonAssociations
	 *            List<OffenderNonAssociations>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public Integer offNaInsertOffenderNonAssociations(final List<OffenderNonAssociations> lstNonAssociations) {
		final String sql = getQuery("OIDONONA_OFFNA_INSERT_OFFENDER_NON_ASSOCIATIONS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderNonAssociations offenderNonAssociations : lstNonAssociations) {
			parameters.add(new BeanPropertySqlParameterSource(offenderNonAssociations));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstNonAssociations.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            OffenderNaDetails
	 *
	 * @return List<OffenderNaDetails>
	 *
	 * @throws SQLException
	 */
	public List<OffenderNaDetails> offNadExecuteQuery(final OffenderNaDetails objSearchDao) {
		final String sql = getQuery("OIDONONA_OFFNAD_FIND_OFFENDER_NA_DETAILS");
		final RowMapper<OffenderNaDetails> OffenderNaDetailsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderNaDetails.class, offenderNaDetailsMapping);
		List<OffenderNaDetails> returnList = new ArrayList<OffenderNaDetails>();
		String preparedSql = null;
		final MapSqlParameterSource inParameterSource = new MapSqlParameterSource();
		final StringBuffer sqlQuery = new StringBuffer();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" WHERE  ");
			if (objSearchDao.getOffenderId() != null) {
				sqlQuery.append("OFFENDER_ID = :OFFENDER_ID" + " AND ");
				inParameterSource.addValue("OFFENDER_ID", objSearchDao.getOffenderId());
			}
			if (objSearchDao.getNsOffenderBookId() != null) {
				sqlQuery.append("NS_OFFENDER_BOOK_ID = :NS_OFFENDER_BOOK_ID" + " AND ");
				inParameterSource.addValue("NS_OFFENDER_BOOK_ID", objSearchDao.getNsOffenderBookId());
			}
			if (objSearchDao.getNsType() != null) {
				sqlQuery.append("NS_TYPE = :NS_TYPE" + " AND ");
				inParameterSource.addValue("NS_TYPE", objSearchDao.getNsType());
			}
			if (objSearchDao.getNsReasonCode() != null) {
				sqlQuery.append("NS_REASON_CODE = :NS_REASON_CODE" + " AND ");
				inParameterSource.addValue("NS_REASON_CODE", objSearchDao.getNsReasonCode());
			}
			if (objSearchDao.getNsEffectiveDate()!= null) {
				sqlQuery.append("NS_EFFECTIVE_DATE = :NS_EFFECTIVE_DATE" + " AND ");
				inParameterSource.addValue("NS_EFFECTIVE_DATE", objSearchDao.getNsEffectiveDate());
			}
			if (objSearchDao.getNsExpiryDate()!= null) {
				sqlQuery.append("NS_EXPIRY_DATE = :NS_EXPIRY_DATE" + " AND ");
				inParameterSource.addValue("NS_EXPIRY_DATE", objSearchDao.getNsExpiryDate());
			}
			if (objSearchDao.getAuthorizedStaff()!= null) {
				sqlQuery.append("AUTHORIZED_STAFF = :AUTHORIZED_STAFF" + " AND ");
				inParameterSource.addValue("AUTHORIZED_STAFF", objSearchDao.getAuthorizedStaff());
			}
			if (objSearchDao.getCommentText()!= null) {
				sqlQuery.append("COMMENT_TEXT = :COMMENT_TEXT" + " AND ");
				inParameterSource.addValue("COMMENT_TEXT", objSearchDao.getCommentText());
			}
			preparedSql = sqlQuery.toString().trim();
			if (preparedSql.endsWith("WHERE")) {
				preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
			}
			if (preparedSql.endsWith("AND")) {
				preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
			}
			preparedSql = preparedSql.concat(" ORDER BY TYPE_SEQ DESC ");
		}
		try{
		 returnList = namedParameterJdbcTemplate.query(preparedSql, inParameterSource, OffenderNaDetailsRowMapper);
		}catch(Exception e){
			logger.error("offNadExecuteQuery",e);
			
		}
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstOffenderNaDetails
	 *            List<OffenderNaDetails>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public Integer offNadInsertOffenderNaDetails(final List<OffenderNaDetails> lstOffenderNaDetails) {
		final String sql = getQuery("OIDONONA_OFFNAD_INSERT_OFFENDER_NA_DETAILS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderNaDetails offenderNaDetails : lstOffenderNaDetails) {
			parameters.add(new BeanPropertySqlParameterSource(offenderNaDetails));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenderNaDetails.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstOffenderNaDetails
	 *            List<OffenderNaDetails>
	 *
	 * @throws SQLException
	 */
	public Integer offNadUpdateOffenderNaDetails(final OffenderNaDetails obj) {
		final String sql = getQuery("OIDONONA_OFFNAD_UPDATE_OFFENDER_NA_DETAILS");
		Integer val= null;
		val= namedParameterJdbcTemplate.update(sql, createParams("nsType",obj.getNsType(),"nsEffectiveDate",obj.getNsEffectiveDate(),
				"nsExpiryDate",obj.getNsExpiryDate(),"authorizedStaff",obj.getAuthorizedStaff(),"commentText",obj.getCommentText(),
				"nsReasonCode",obj.getNsReasonCode(),
				"offenderId",
				obj.getOffenderId(),"nsOffenderId",obj.getNsOffenderId(),"typeSeq",obj.getTypeSeq(),"modifyUserId",obj.getModifyUserId()));
		return val;

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            StgRelationships
	 *
	 * @return List<StgRelationships>
	 *
	 * @throws SQLException
	 */
	public List<StgRelationships> stgRelationshipsExecuteQuery(final StgRelationships objSearchDao) {
		final String sql = getQuery("OIDONONA_GET_STG_GROUP_ENEMY");
		final RowMapper<StgRelationships> StgRelationshipsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				StgRelationships.class, stgRelationshipsMapping);
		List<StgRelationships> returnList = new ArrayList<StgRelationships>();
		 returnList = namedParameterJdbcTemplate.query(sql, createParams("offenderBookId",objSearchDao.getOffenderBookId()), StgRelationshipsRowMapper);
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
	 * @throws SQLException
	 */
	public List<SystemProfiles> sysPflExecuteQuery(final SystemProfiles objSearchDao) {
		final String sql = getQuery("OIDONONA_SYSPFL_FIND_SYSTEM_PROFILES");
		final RowMapper<SystemProfiles> SystemProfilesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				SystemProfiles.class, systemProfilesMapping);
		final ArrayList<SystemProfiles> returnList = (ArrayList<SystemProfiles>) namedParameterJdbcTemplate.query(sql,
				createParams(), SystemProfilesRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstSystemProfiles
	 *            List<SystemProfiles>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public Integer sysPflInsertSystemProfiles(final List<SystemProfiles> lstSystemProfiles) {
		final String sql = getQuery("OIDONONA_SYSPFL_INSERT_SYSTEM_PROFILES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstSystemProfiles.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstSystemProfiles
	 *            List<SystemProfiles>
	 *
	 * @throws SQLException
	 */
	public Integer sysPflDeleteSystemProfiles(final List<SystemProfiles> lstSystemProfiles) {
		final String sql = getQuery("OIDONONA_SYSPFL_DELETE_SYSTEM_PROFILES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final SystemProfiles systemProfiles : lstSystemProfiles) {
			parameters.add(new BeanPropertySqlParameterSource(systemProfiles));
		}
		try {
			String tableName = "SYSTEM_PROFILES";
			String whereClause = null;
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method sysPflDeleteSystemProfiles", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstSystemProfiles.size() == returnArray.length) {
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
	public List<VHeaderBlock> cgfkOffNaDspOffenderIdDiRecordGroup() {
		final String sql = getQuery("OIDONONA_FIND_CGFKOFFNADSPOFFENDERIDDI");
		final RowMapper<VHeaderBlock> mRowMapper = Row2BeanRowMapper.makeMapping(sql, VHeaderBlock.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (final EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> cgfkOffNadDspDescription3RecordGroup() {
		final String sql = getQuery("OIDONONA_FIND_CGFKOFFNADDSPDESCRIPTION3");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (final EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> offNaDspRecipRsnRecordGroup() {
		final String sql = getQuery("OIDONONA_FIND_OFFNADSPRECIPRSN");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (final EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> cgfkOffNadDspDescriptionRecordGroup() {
		final String sql = getQuery("OIDONONA_FIND_CGFKOFFNADDSPDESCRIPTION");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (final EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}


	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOffNadOffNadRef
	 *
	 * @param params
	 *
	 */
	public List<ReferenceCodes> cgfkchkOffNadOffNadRef(final ReferenceCodes paramBean) {
		final String sql = getQuery("OIDONONA_CGFKCHK_OFF_NAD_OFF_NAD_REF_C");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		final ArrayList<ReferenceCodes> returnList = (ArrayList<ReferenceCodes>) namedParameterJdbcTemplate.query(sql,
				createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfklkpOffNadOffNadRef
	 *
	 * @param params
	 *
	 */
	public List<ReferenceCodes> cgfklkpOffNadOffNadRef(final ReferenceCodes paramBean) {
		final String sql = getQuery("OIDONONA_CGFKLKP_OFF_NAD_OFF_NAD_REF_C");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		final ArrayList<ReferenceCodes> returnList = (ArrayList<ReferenceCodes>) namedParameterJdbcTemplate.query(sql,
				createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOffNadOffNadRe2
	 *
	 * @param params
	 *
	 */
	public List<ReferenceCodes> cgfkchkOffNadOffNadRe2(final ReferenceCodes paramBean) {
		final String sql = getQuery("OIDONONA_CGFKCHK_OFF_NAD_OFF_NAD_RE2");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		final ArrayList<ReferenceCodes> returnList = (ArrayList<ReferenceCodes>) namedParameterJdbcTemplate.query(sql,
				createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfklkpOffNadOffNadRe2
	 *
	 * @param params
	 *
	 */
	public List<ReferenceCodes> cgfklkpOffNadOffNadRe2(final ReferenceCodes paramBean) {
		final String sql = getQuery("OIDONONA_CGFKLKP_OFF_NAD_OFF_NAD_RE2");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		final ArrayList<ReferenceCodes> returnList = (ArrayList<ReferenceCodes>) namedParameterJdbcTemplate.query(sql,
				createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgrichkOffenderNonAssociat
	 *
	 * @param params
	 *
	 */
	public List<OffenderNaDetails> cgrichkOffenderNonAssociat(final OffenderNaDetails paramBean) {
		final String sql = getQuery("OIDONONA_CGRICHK_OFFENDER_NON_ASSOCIAT");
		final RowMapper<OffenderNaDetails> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderNaDetails.class,
				offenderNaDetailsMapping);
		final ArrayList<OffenderNaDetails> returnList = (ArrayList<OffenderNaDetails>) namedParameterJdbcTemplate
				.query(sql, createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offNaValidateRsn
	 *
	 * @param params
	 *
	 */
	public List<ReferenceCodes> offNaValidateRsn(final ReferenceCodes paramBean) {
		final String sql = getQuery("OIDONONA_OFF_NA_VALIDATE_RSN");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		final ArrayList<ReferenceCodes> returnList = (ArrayList<ReferenceCodes>) namedParameterJdbcTemplate.query(sql,
				createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offNaChkRsn
	 *
	 * @param params
	 *
	 */
	public List<ReferenceCodes> offNaChkRsn(final ReferenceCodes paramBean) {
		final String sql = getQuery("OIDONONA_OFF_NA_CHK_RSN");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		final ArrayList<ReferenceCodes> returnList = (ArrayList<ReferenceCodes>) namedParameterJdbcTemplate.query(sql,
				createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * getStgGroupDescription
	 *
	 * @param params
	 *
	 */
	public List<Object> getStgGroupDescription(final SecurityThreatGroups paramBean) {
		final String sql = getQuery("OIDONONA_GET_STG_GROUP_DESCRIPTION");
		final RowMapper<Object> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Object.class,
				securityThreatGroupsMapping);
		List<Object> returnList = new ArrayList<Object>();
		returnList = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		return returnList;
	}

	public List<VHeaderBlock> getlastFirstName(final Long nsOffenderId,final Long offenderId,String userName) {
		final String sql = getQuery("OIDONONA_GETLASTFIRSTNAME");
		final RowMapper<VHeaderBlock> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, VHeaderBlock.class,
				vBlockMapping);
		List<VHeaderBlock> returnList= new ArrayList<VHeaderBlock>();
		returnList = namedParameterJdbcTemplate.query(sql, createParams("nsOffenderId",nsOffenderId,"offenderId",offenderId,"USERID",userName),columnRowMapper);
		return returnList;
	}

	public String getstgDesc(final BigDecimal relatedStgId) {
		final String sql = getQuery("OIDONONA_GET_NBT_RELATED_STGID_DESCRIPTION");
		try {
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("relatedStgId",relatedStgId), String.class);
		} catch (Exception e) {
		return null;
		}
		
	}

	public Integer getNbtActiveFlg(final Long offenderId, final Long nsOffenderId) {
		final String sql = getQuery("OIDONONA_GET_ACTIVEFLG");
		Integer count = null;
		count= namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderId",offenderId,"nsOffenderId",nsOffenderId), Integer.class);
		return count;
	}

	public Integer compareEffectiveDatec(final String effectiveDate) {
		final String sql = getQuery("OIDONONA_COMPAREEFFECTIVEDATEC");
		Integer desc=0;
		try{
		desc=namedParameterJdbcTemplate.queryForObject(sql, createParams("effectiveDate",effectiveDate),Integer.class);
		} catch(Exception e){
			logger.error("compareEffectiveDatec",e);
			
		}
		return desc;
	}

	public Integer perInsert(final Long offenderId, final Long nsOffenderId) {
		final String sql = getQuery("OIDONONA_PERINSERT");
		Integer desc=0;
		try{
		desc=namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderId",offenderId,"nsOffenderId",nsOffenderId),Integer.class);
		} catch(Exception e){
			logger.error("compareEffectiveDatec",e);
			
		}
		return desc;
	}

	public Integer offNadInsertList(final List<OffenderNonAssociations> insertList) {
		final String sql = getQuery("OIDONONA_OFFNAD_INSERT_OFFENDER_NA_DETAILS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderNonAssociations offenderNonAssociations : insertList) {
			parameters.add(new BeanPropertySqlParameterSource(offenderNonAssociations));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (insertList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	public Integer offNaInsertOffenderNonAssociations(final OffenderNonAssociations obj) {
		return null;
	}

	public Integer updateOffenderNonAssociation(final OffenderNonAssociations obj) {
		final String sql = getQuery("OIDONONA_OFFNA_UPDATE_OFFENDER_NON_ASSOCIATIONS");
		Integer val= null;
		val= namedParameterJdbcTemplate.update(sql, createParams("nsReasonCode",obj.getRecipNsReasonCode(),"offenderId",
				obj.getOffenderId(),"nsOffenderId",obj.getNsOffenderId(),"modifyUserId",obj.getModifyUserId()));
		return val;
	}

	public Integer getMaxVal(final Long rootOffenderId, final Long nsOffenderId) {
		final String sql = getQuery("OIDONONA_GETMAXVAL");
		Integer val= null;
		val= namedParameterJdbcTemplate.queryForObject(sql, createParams("rootOffenderId",rootOffenderId,
				"nsOffenderId",nsOffenderId),Integer.class);
		return val;
	}

	public Integer updateOffenderNonAssociationfromWeb(final OffenderNonAssociations obj) {
		final String sql = getQuery("OIDONONA_UPDATEOFFENDERNONASSOCIATIONFROMWEB");
		Integer val= null;
		val= namedParameterJdbcTemplate.update(sql, createParams("nsReasonCode",obj.getRecipNsReasonCode(),"offenderId",
				obj.getOffenderId(),"nsOffenderId",obj.getNsOffenderId(),"modifyUserId",obj.getModifyUserId()));
		return val;
	}

	public Integer updateOffenderNaDetails(final OffenderNonAssociations obj) {
		final String sql = getQuery("OIDONONA_UPDATEOFFENDERNADETAILS");
		Integer val= null;
		val= namedParameterJdbcTemplate.update(sql, createParams("nsType",obj.getNsType(),"nsEffectiveDate",obj.getNsEffectiveDate(),
				"nsExpiryDate",obj.getNsExpiryDate(),"authorizedStaff",obj.getAuthorizedStaff(),"commentText",obj.getCommentText(),
				"nsReasonCode",obj.getNsReasonCode(),
				"offenderId",
				obj.getOffenderId(),"nsOffenderId",obj.getNsOffenderId(),"typeSeq",obj.getTypeSeq()));
		return val;
	}
	
	public Long getRootOffenderId(final String offIdDisplay,String userName) {
		final String sql = getQuery("OIDONONA_GET_ROOT_ID");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderIdDisplay", offIdDisplay, "USERID",userName), Long.class);
		
	}
	
	public Integer offNadUpdateOffenderNaDetailsDouble(final OffenderNaDetails obj) {
		final String sql = getQuery("OIDONONA_OFFNAD_UPDATE_OFFENDER_NA_DETAILS_DOUBLE");
		Integer val= null;
		val= namedParameterJdbcTemplate.update(sql, createParams("nsType",obj.getNsType(),"nsEffectiveDate",obj.getNsEffectiveDate(),
				"nsExpiryDate",obj.getNsExpiryDate(),"authorizedStaff",obj.getAuthorizedStaff(),"commentText",obj.getCommentText(),
				"offenderId",obj.getOffenderId(),"nsOffenderId",obj.getNsOffenderId(),"modifyUserId",obj.getModifyUserId()));
		return val;

	}
	
	@Override
	public Integer updateOffenderNonAssociationfromWebDouble(OffenderNonAssociations obj) {
		final String sql = getQuery("OIDONONA_UPDATEOFFENDERNONASSOCIATIONFROMWEBDOUBLE");
		Integer val= null;
		val= namedParameterJdbcTemplate.update(sql, createParams("nsReasonCode",obj.getNsReasonCode(),"offenderId",
				obj.getOffenderId(),"nsOffenderId",obj.getNsOffenderId(),"modifyUserId",obj.getModifyUserId()));
		return val;
	}
	
	@Override
	public void updateOffenderNonAssociationfromWebReciprocalReason(OffenderNaDetails obj) {
		final String sql = getQuery("OIDONONA_UPDATEOFFENDERNONASSOCIATIONRECIPROCALREASON");
		namedParameterJdbcTemplate.update(sql, createParams("nsReasonCode",obj.getNsReasonCode(),"modifyUserId",obj.getModifyUserId(),"nsOffenderId",obj.getNsOffenderId(),"offenderId",
				obj.getOffenderId()));
	}

	@Override
	public List<ReferenceCodes> getActiveStaffMembers() {
		final String sql = getQuery("OIDONONA_GET_ACTIVE_STAFF_MEMBERS");
		List<ReferenceCodes> codeList= null;
		final RowMapper<ReferenceCodes> rowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, referenceCodesMapping);
		try {
			codeList=namedParameterJdbcTemplate.query(sql, createParams(), rowMapper);
		} catch (final EmptyResultDataAccessException e) {
			logger.error(this.getClass().getName()+" In getActiveStaffMembers Method and Exception is", e.getMessage());
		}
		return codeList;
	}

}
