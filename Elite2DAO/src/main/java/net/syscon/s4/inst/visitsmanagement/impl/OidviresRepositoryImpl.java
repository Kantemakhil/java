package net.syscon.s4.inst.visitsmanagement.impl;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.Images;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.TagImages;
import net.syscon.s4.inst.booking.beans.OffenderContactPersons;
import net.syscon.s4.inst.visitsmanagement.OidviresRepository;
import net.syscon.s4.inst.visitsmanagement.beans.OffenderRestrictions;
import oracle.jdbc.internal.OracleTypes;

@Repository
public class OidviresRepositoryImpl extends RepositoryBase implements OidviresRepository{

	private static Logger logger = LogManager.getLogger(OidviresRepositoryImpl.class.getName());


private final Map<String, FieldMapper> mmMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("MIDDLE_NAME", 						new FieldMapper("lastName"))
.put("FIRST_NAME", 						new FieldMapper("firstName"))
.put("NAME", 						new FieldMapper("description"))
.put("CODE", 						new FieldMapper("code"))
.put("STATUS", 						new FieldMapper("status"))
.build();
private final Map<String, FieldMapper> imagesMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("IMAGE_ID", 						new FieldMapper("imageId"))
.put("IMAGE_OBJECT_TYPE", 						new FieldMapper("imageObjectType"))
.put("IMAGE_OBJECT_ID", 						new FieldMapper("imageObjectId"))
.put("IMAGE_THUMBNAIL", 						new FieldMapper("imageThumbnail"))
.put("EXPIRY_DATE", 						new FieldMapper("expiryDate"))
.put("ORIENTATION_TYPE", 						new FieldMapper("orientationType"))
.put("EMERGENCY_CONTACT_FLAG", 						new FieldMapper("emergencyContactFlag"))
.build();
private final Map<String, FieldMapper> offenderRestrictionsMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("SEAL_FLAG", 						new FieldMapper("sealFlag"))
.put("CREATE_DATETIME", 						new FieldMapper("createDatetime"))
.put("EFFECTIVE_DATE", 						new FieldMapper("effectiveDate"))
.put("MODIFY_DATETIME", 						new FieldMapper("modifyDatetime"))
.put("EXPIRY_DATE", 						new FieldMapper("expiryDate"))
.build();
private final Map<String, FieldMapper> tagImagesMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("PERSON_ID", 						new FieldMapper("personId"))
.build();
private final Map<String, FieldMapper> offenderContactPersonsMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId"))
.put("PERSON_ID", new FieldMapper("personId"))
.put("CONTACT_TYPE", new FieldMapper("contactType")) 
.put("RELATIONSHIP_TYPE", new FieldMapper("relationshipType"))
.put("APPROVED_VISITOR_FLAG", new FieldMapper("approvedVisitorFlag"))
.put("CASELOAD_TYPE", new FieldMapper("caseloadType"))	
.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
.put("COMMENT_TEXT", new FieldMapper("commentText"))  
.put("CASE_INFO_NUMBER", new FieldMapper("caseInfoNumber")) 	
.put("AWARE_OF_CHARGES_FLAG", new FieldMapper("awareOfChargesFlag"))
.put("CAN_BE_CONTACTED_FLAG", new FieldMapper("canBeContactedFlag")) 
.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
.put("CREATE_USER_ID", new FieldMapper("createUserId")) 
.put("EMERGENCY_CONTACT_FLAG", new FieldMapper("emergencyContactFlag"))
.put("NEXT_OF_KIN_FLAG", new FieldMapper("nextOfKinFlag")) 
.put("ACTIVE_FLAG", new FieldMapper("activeFlag")) 
.put("EXPIRY_DATE", new FieldMapper("expiryDate"))
.put("OFFENDER_CONTACT_PERSON_ID", new FieldMapper("offenderContactPersonId"))
.put("CONTACT_ROOT_OFFENDER_ID", new FieldMapper("contactRootOffenderId"))
.put("SEAL_FLAG", new FieldMapper("sealFlag"))
.put("LAST_NAME", new FieldMapper("lastName"))
.put("FIRST_NAME", new FieldMapper("firstName"))
.put("MIDDLE_NAME", new FieldMapper("middleName"))
.put("BIRTHDATE", new FieldMapper("birthDate"))
.put("VISIT_BAN", new FieldMapper("visitBan"))
.put("RESTRICTION", new FieldMapper("restriction"))
.put("LIVING_UNIT_DESCRIPTION", new FieldMapper("livingUnitDescription"))
.build();
private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("DESCRIPTION", 						new FieldMapper("description"))
.put("CODE", 						new FieldMapper("code"))
.build();


/**
 * Creates new OidviresRepositoryImpl class Object 
 */
public OidviresRepositoryImpl() {
//	OidviresRepositoryImpl
}

/**
* Fetch the records from database table
*
* @param objSearchDao OffenderRestrictions
*
* @return List<OffenderRestrictions>
*
* @throws SQLException
*/
 public List<OffenderRestrictions> offVisitRestExecuteQuery(final OffenderRestrictions objSearchDao) {
		final String sql = getQuery("OIDVIRES_OFFVISITREST_FIND_OFFENDER_RESTRICTIONS");
		final RowMapper<OffenderRestrictions> OffenderRestrictionsRowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderRestrictions.class,offenderRestrictionsMapping);
		final ArrayList<OffenderRestrictions> returnList = (ArrayList<OffenderRestrictions>)namedParameterJdbcTemplate.query(sql, createParams("offenderBookId",objSearchDao.getOffenderBookId()), OffenderRestrictionsRowMapper);
		return returnList;
} 
/**
* @param 
*
* @throws SQLException 
*
*/
public int PRE_INSERT() {
return 0;
}

/**
*  This method is used to insert the records in the data base tables based on
*
* @param lstOffenderRestrictions List<OffenderRestrictions>
*
* @return List<Integer>
*
* @throws SQLException
*/
 public Integer offVisitRestInsertOffenderRestrictions(final List<OffenderRestrictions> lstOffenderRestrictions) {
	String sql = getQuery("OIDVIRES_OFFVISITREST_INSERT_OFFENDER_RESTRICTIONS");
	int[] returnArray = new int[] {};
	List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
	for (OffenderRestrictions personIdentifiers : lstOffenderRestrictions) {
		parameters.add(new BeanPropertySqlParameterSource(personIdentifiers));
	}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
	if (lstOffenderRestrictions.size() == returnArray.length) {
		return 1;
	} else {
		return 0;
	}

}

/**
* This method is used to update the data base tables based on
*
* @param lstOffenderRestrictions List<OffenderRestrictions>
*
* @throws SQLException
*/
 public Integer offVisitRestUpdateOffenderRestrictions(final List<OffenderRestrictions> lstOffenderRestrictions) {
	String sql = getQuery("OIDVIRES_OFFVISITREST_UPDATE_OFFENDER_RESTRICTIONS");
	int[] returnArray = new int[] {};
	List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
	for (OffenderRestrictions personIdentifiers : lstOffenderRestrictions) {
		parameters.add(new BeanPropertySqlParameterSource(personIdentifiers));
	}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
	if (lstOffenderRestrictions.size() == returnArray.length) {
		return 1;
	} else {
		return 0;
	}


}

/**
* Fetch the records from database table
*
* @param objSearchDao OffenderContactPersons
*
* @return List<OffenderContactPersons>
*
* @throws SQLException
*/
 public List<OffenderContactPersons> offAuthVisitorsExecuteQuery(final OffenderContactPersons offenderBookId) {
		final String sql = getQuery("OIDVIRES_OFFAUTHVISITORS_FIND_OFFENDER_CONTACT_PERSONS");
		final RowMapper<OffenderContactPersons> OffenderContactPersonsRowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderContactPersons.class,offenderContactPersonsMapping);
		final List<OffenderContactPersons> returnList = namedParameterJdbcTemplate.query(sql, createParams("offenderBookId", offenderBookId.getOffenderBookId()), OffenderContactPersonsRowMapper);
		return returnList;
} 
 


/**
*  This method is used to insert the records in the data base tables based on
*
* @param lstOffenderContactPersons List<OffenderContactPersons>
*
* @return List<Integer>
*
* @throws SQLException
*/
 public Integer offAuthVisitorsInsertOffenderContactPersons(final List<OffenderContactPersons> lstOffenderContactPersons) {
	String sql = getQuery("OIDVIRES_OFFAUTHVISITORS_INSERT_OFFENDER_CONTACT_PERSONS");
	int[] returnArray = new int[] {};
	List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
	for (OffenderContactPersons personIdentifiers : lstOffenderContactPersons) {
		parameters.add(new BeanPropertySqlParameterSource(personIdentifiers));
	}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
	if (lstOffenderContactPersons.size() == returnArray.length) {
		return 1;
	} else {
		return 0;
	}

}

/**
* This method is used to update the data base tables based on
*
* @param lstOffenderContactPersons List<OffenderContactPersons>
*
* @throws SQLException
*/
 public Integer offAuthVisitorsUpdateOffenderContactPersons(final List<OffenderContactPersons> lstOffenderContactPersons) {
	String sql = getQuery("OIDVIRES_OFFAUTHVISITORS_UPDATE_OFFENDER_CONTACT_PERSONS");
	int[] returnArray = new int[] {};
	List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
	for (OffenderContactPersons personIdentifiers : lstOffenderContactPersons) {
		parameters.add(new BeanPropertySqlParameterSource(personIdentifiers));
	}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
	if (lstOffenderContactPersons.size() == returnArray.length) {
		return 1;
	} else {
		return 0;
	}

}

/**
* This method is used to delete records from  data base tables based on
*
* @param lstOffenderContactPersons List<OffenderContactPersons>
*
* @throws SQLException
*/
 public Integer offAuthVisitorsDeleteOffenderContactPersons(final List<OffenderContactPersons> lstOffenderContactPersons) {
	String sql = getQuery("OIDVIRES_OFFAUTHVISITORS_DELETE_OFFENDER_CONTACT_PERSONS");
	int[] returnArray = new int[] {};
	List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
	for (OffenderContactPersons personIdentifiers : lstOffenderContactPersons) {
		parameters.add(new BeanPropertySqlParameterSource(personIdentifiers));
	}
	try {
		String tableName = "OFFENDER_CONTACT_PERSONS";
		String whereCondition = "OFFENDER_BOOK_ID = :offenderBookId AND PERSON_ID = :personId";
		batchUpdatePreDeletedRows(tableName, whereCondition, parameters);
	} catch (Exception e) {
		logger.error(e);
	}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
	if (lstOffenderContactPersons.size() == returnArray.length) {
		return 1;
	} else {
		return 0;
	}

}

/**
* Fetch the records from database table
*
* @param objSearchDao Images
*
* @return List<Images>
*
* @throws SQLException
*/
 public List<Images> imageVisitExecuteQuery(final Long imageObjectId, final String type,String userName) {
		final String sql = getQuery("OIDVIRES_IMAGEVISIT_FIND_IMAGES");
		StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource params = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if(imageObjectId != null) {
			sqlQuery.append(" WHERE ");
				if(type.equalsIgnoreCase("PERSON")) {
					sqlQuery.append(" IMAGE_OBJECT_ID = :imageObjectId ");
				} else {
					sqlQuery.append(" IMAGE_OBJECT_ID = (select offender_book_id from v_name_search2_fn(:userName) where root_offender_id = :imageObjectId) ");
					params.addValue("userName", userName);
				}
				params.addValue("imageObjectId", imageObjectId);
				sqlQuery.append(" AND ACTIVE_FLAG = 'Y' ");
		}
		final String preparedSql = sqlQuery.toString().trim();
		final RowMapper<Images> ImagesRowMapper = Row2BeanRowMapper.makeMapping(sql, Images.class,imagesMapping);
		final List<Images> returnList = namedParameterJdbcTemplate.query(preparedSql, params, ImagesRowMapper);
		return returnList;
} 

/**
* This method is used to delete records from  data base tables based on
*
* @param lstImages List<Images>
*
* @throws SQLException
*/
 public Integer imageVisitDeleteImages(final List<net.syscon.s4.im.beans.Images>  lstImages) {
	String sql = getQuery("OIDVIRES_IMAGEVISIT_DELETE_IMAGES");
	int[] returnArray = new int[] {};
	List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
	for (net.syscon.s4.im.beans.Images images : lstImages) {
		parameters.add(new BeanPropertySqlParameterSource(images));
	}
	try {
		String tableName = "IMAGES";
		batchUpdatePreDeletedRows(tableName, null, parameters);
	} catch (Exception e) {
		logger.error(e);
	}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
	if (lstImages.size() == returnArray.length) {
		return 1;
	} else {
		return 0;
	}

}
/**
* Used to capture results from select query
* @return List<M> 
*/
public List<ReferenceCodes> rgAuthPriRelationshipTypeRecordGroup(final String contactType) {
 	final String sql = getQuery("OIDVIRES_FIND_RGAUTHPRIRELATIONSHIPTYPE");
 	final RowMapper<ReferenceCodes>MRowMapper = Row2BeanRowMapper.makeMapping(sql,ReferenceCodes.class, mMapping);
 
	try {
  		return namedParameterJdbcTemplate.query(sql,createParams("contactType", contactType),MRowMapper);
  	} catch (Exception e) {
  		return Collections.emptyList();  
	}
}
/**
* Used to capture results from select query
* @return List<MM> 
*/
public List<ReferenceCodes> rgAuthVisRelationshipTypeRecordGroup() {
 	final String sql = getQuery("OIDVIRES_FIND_RGAUTHVISRELATIONSHIPTYPE");
 	final RowMapper<ReferenceCodes>MMRowMapper = Row2BeanRowMapper.makeMapping(sql,ReferenceCodes.class, mmMapping);
 
	try {
  		return namedParameterJdbcTemplate.query(sql,createParams(),MMRowMapper);
  	} catch (EmptyResultDataAccessException e) {
  		return Collections.emptyList();  
	}
}
/**
* Used to capture results from select query
* @return List<M> 
*/
public List<ReferenceCodes> rgAuthVisContactTypeRecordGroup() {
 	final String sql = getQuery("OIDVIRES_FIND_RGAUTHVISCONTACTTYPE");
 	final RowMapper<ReferenceCodes>mRowMapper = Row2BeanRowMapper.makeMapping(sql,ReferenceCodes.class, mMapping);
 
	try {
  		return namedParameterJdbcTemplate.query(sql,createParams(),mRowMapper);
  	} catch (EmptyResultDataAccessException e) {
  		return Collections.emptyList();  
	}
}
/**
* Used to capture results from select query
* @return List<MM> 
*/
public List<StaffMembers> rgStaffIdRecordGroup(final String agyLocId) {
 	final String sql = getQuery("OIDVIRES_FIND_RGSTAFFID");
 	final RowMapper<StaffMembers>mMRowMapper = Row2BeanRowMapper.makeMapping(sql,StaffMembers.class, mmMapping);
 
	try {
  		return namedParameterJdbcTemplate.query(sql,createParams("agyLocId", agyLocId),mMRowMapper);
  	} catch (Exception e) {
  		return Collections.emptyList();  
	}
}
/**
* Used to capture results from select query
* @return List<M> 
*/
public List<ReferenceCodes> rgOffRestrictionTypeRecordGroup() {
 	final String sql = getQuery("OIDVIRES_FIND_RGOFFRESTRICTIONTYPE");
 	final RowMapper<ReferenceCodes>mRowMapper = Row2BeanRowMapper.makeMapping(sql,ReferenceCodes.class, mMapping);
 
	try {
  		return namedParameterJdbcTemplate.query(sql,createParams(),mRowMapper);
  	} catch (EmptyResultDataAccessException e) {
  		return Collections.emptyList();  
	}
}
	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offBkgOnCheckDeleteMaster
	 *
	 * @param params
	 *
	 */
	public List<OffenderRestrictions> offBkgOnCheckDeleteMaster(final OffenderRestrictions paramBean) {
		final String sql = getQuery("OIDVIRES_OFF_BKG_ONCHECKDELETEMASTER");
		final RowMapper<OffenderRestrictions> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,OffenderRestrictions.class,  offenderRestrictionsMapping);
		final ArrayList<OffenderRestrictions> returnList = (ArrayList<OffenderRestrictions>)namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		return returnList;
	}
	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offBkgOnCheckDeleteMaster
	 *
	 * @param params
	 *
	 */
	public List<OffenderContactPersons> offBkgOnCheckDeleteMaster(final OffenderContactPersons paramBean) {
		final String sql = getQuery("OIDVIRES_OFF_BKG_ONCHECKDELETEMASTER");
		final RowMapper<OffenderContactPersons> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,OffenderContactPersons.class,  offenderContactPersonsMapping);
		final ArrayList<OffenderContactPersons> returnList = (ArrayList<OffenderContactPersons>)namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		return returnList;
	}
	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offBkgOnCheckDeleteMaster
	 *
	 * @param params
	 *
	 */

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offAuthVisitorsOnCheckDeleteMaster
	 *
	 * @param params
	 *
	 */
	public List<TagImages> offAuthVisitorsOnCheckDeleteMaster(final TagImages paramBean) {
		final String sql = getQuery("OIDVIRES_OFF_AUTH_VISITORS_ONCHECKDELETEMASTER");
		final RowMapper<TagImages> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,TagImages.class,  tagImagesMapping);
		final ArrayList<TagImages> returnList = (ArrayList<TagImages>)namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		return returnList;
	}
	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offAuthVisitOffOnCheckDeleteMaster
	 *
	 * @param params
	 *
	 */
	public List<Images> offAuthVisitOffOnCheckDeleteMaster(final Images paramBean) {
		final String sql = getQuery("OIDVIRES_OFF_AUTH_VISIT_OFF_ONCHECKDELETEMASTER");
		final RowMapper<Images> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,Images.class,  imagesMapping);
		final ArrayList<Images> returnList = (ArrayList<Images>)namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		return returnList;
	}
	@Override
	public List<OffenderContactPersons> offVisitingExecuteQuery(final OffenderContactPersons objOffenderContactPersons)
			 {
		final String sql = getQuery("OIDVIRES_OFFVISITING_FIND_OFFENDER_CONTACT_PERSONS");
		final RowMapper<OffenderContactPersons> OffenderContactPersonsRowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderContactPersons.class,offenderContactPersonsMapping);
		final List<OffenderContactPersons> returnList = namedParameterJdbcTemplate.query(sql, createParams("offenderBookId", objOffenderContactPersons.getOffenderBookId(),"userId",objOffenderContactPersons.getCreateUserId()), OffenderContactPersonsRowMapper);
		return returnList;
	}
	@Override
	public Integer oidviresOffauthvisitoffInsertOffenderContactPersons(
			final List<OffenderContactPersons> lstOffenderContactPersons) {
		String sql = getQuery("OIDVIRES_OFFAUTHVISITOFF_INSERT_OFFENDER_CONTACT_PERSONS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (OffenderContactPersons personIdentifiers : lstOffenderContactPersons) {
			parameters.add(new BeanPropertySqlParameterSource(personIdentifiers));
		}
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenderContactPersons.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}
	@Override
	public Integer oidviresOffauthvisitoffUpdateOffenderContactPersons(
			final List<OffenderContactPersons> lstOffenderContactPersons) {
		String sql = getQuery("OIDVIRES_OFFAUTHVISITOFF_UPDATE_OFFENDER_CONTACT_PERSONS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (OffenderContactPersons personIdentifiers : lstOffenderContactPersons) {
			parameters.add(new BeanPropertySqlParameterSource(personIdentifiers));
		}
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenderContactPersons.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}
	@Override
	public Integer oidviresOffauthvisitoffDeleteOffenderContactPersons(
			final List<OffenderContactPersons> lstOffenderContactPersons) {
		String sql = getQuery("OIDVIRES_OFFAUTHVISITOFF_DELETE_OFFENDER_CONTACT_PERSONS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (OffenderContactPersons personIdentifiers : lstOffenderContactPersons) {
			parameters.add(new BeanPropertySqlParameterSource(personIdentifiers));
		}
		try {
			String tableName = "OFFENDER_CONTACT_PERSONS";
			String whereCondition = "OFFENDER_BOOK_ID = :offenderBookId AND PERSON_ID IS NULL AND CONTACT_ROOT_OFFENDER_ID = :contactRootOffenderId";
			batchUpdatePreDeletedRows(tableName, whereCondition, parameters);
		} catch (Exception e) {
			logger.error(e);
		}
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenderContactPersons.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}
	@Override
	public Integer oidviresFindTagVisitsGetStaffId(String username) {
		final String sql = getQuery("OIDVIRES_FIND_TAG_VISITS_GET_STAFF_ID");
		Integer result = 0; 
		try{
			result = namedParameterJdbcTemplate.queryForObject(sql, createParams("username",username), Integer.class);
			
		} catch (Exception e) {
			
		}
		return result;
	}

	@Override
	public List<String> oidviresIsOffenderBanRestriction(final Long offenderBookId) {
		final String sql = getQuery("OIDVIRES_IS_OFFENDER_BAN_RESTRICTION");
		List<String> returnList = new ArrayList<String>(); 
			try {
			 returnList = namedParameterJdbcTemplate.queryForList(sql, createParams("offenderBookId", offenderBookId), String.class);
			} catch(Exception e) {
				returnList = Collections.emptyList();
			}
			
			
		return returnList;
	}

	@Override
	public List<ReferenceCodes> oidviresIsPersonBanRestriction(Long personId) {
		final String sql = getQuery("OIDVIRES_IS_PERSON_BAN_RESTRICTION");
		List<ReferenceCodes> returnList = new ArrayList<ReferenceCodes>(); 
			try {
				final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,ReferenceCodes.class,  mMapping);
			 returnList = namedParameterJdbcTemplate.query(sql, createParams("personId", personId), columnRowMapper);
			} catch(Exception e) {
				returnList = Collections.emptyList();
			}
			
			
		return returnList;
	}

	@Override
	public String chkNaBetweenOffenders(final Long glbOffBkgId, final Long visOffBkgId) {
		String retValue = "N";
		Map<String, Object> returnMap = null;

		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[3];

		sqlParameters = new SqlParameter[] { new SqlParameter("P_OFFENDER_BOOK_ID", OracleTypes.NUMBER),
				new SqlParameter("P_VIS_OFFENDER_BOOK_ID", OracleTypes.NUMBER),
				new SqlOutParameter("P_CONFLICT_FLAG", OracleTypes.VARCHAR) };

		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("NON_ASSOCIATION").withProcedureName("CHK_NA_BETWEEN_OFFENDERS")
				.declareParameters(sqlParameters);
		inParamMap.put("P_OFFENDER_BOOK_ID", glbOffBkgId);
		inParamMap.put("P_VIS_OFFENDER_BOOK_ID", visOffBkgId);

		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		returnMap = simpleJDBCCall.execute(inParameter);
		
		if(!returnMap.isEmpty()) {
			retValue = returnMap.get("P_CONFLICT_FLAG") != null ? (String) returnMap.get("P_CONFLICT_FLAG") : null;
		}

		return retValue;
	}
	
}
	
	


