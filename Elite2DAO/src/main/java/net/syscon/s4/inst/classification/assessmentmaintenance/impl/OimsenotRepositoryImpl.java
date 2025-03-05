package net.syscon.s4.inst.classification.assessmentmaintenance.impl;
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
import net.syscon.s4.common.beans.AssessSectionNotifications;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.inst.classification.assessmentmaintenance.OimsenotRepository;
import net.syscon.s4.inst.classification.beans.Assessments;


@Repository
public class OimsenotRepositoryImpl extends RepositoryBase implements OimsenotRepository{

private static Logger logger = LogManager.getLogger(OimslevlRepositoryImpl.class.getName());
private final Map<String, FieldMapper> assesMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("LOW_VALUE", 						new FieldMapper("lowValue"))
.put("CELL_SHARING_ALERT_FLAG", 						new FieldMapper("cellSharingAlertFlag"))
.put("SECT_SCORE_INCLUDE_FLAG", 						new FieldMapper("sectScoreIncludeFlag"))
.put("SEARCH_CRITERIA_FLAG", 						new FieldMapper("searchCriteriaFlag"))
.put("MUTUAL_EXCLUSIVE_FLAG", 						new FieldMapper("mutualExclusiveFlag"))
.put("REVIEW_FLAG", 						new FieldMapper("reviewFlag"))
.put("ASSESSMENT_ID", 						new FieldMapper("assessmentId"))
.put("MEASURE", 						new FieldMapper("measure"))
.put("CASELOAD_TYPE", 						new FieldMapper("caseloadType"))
.put("ASSESSMENT_CODE", 						new FieldMapper("assessmentCode"))
.put("ASSESSMENT_TYPE", 						new FieldMapper("assessmentType"))
.put("SEAL_FLAG", 						new FieldMapper("sealFlag"))
.put("CREATE_DATETIME", 						new FieldMapper("createDatetime"))
.put("DETERMINE_SUP_LEVEL_FLAG", 						new FieldMapper("determineSupLevelFlag"))
.put("PARENT_ASSESSMENT_ID", 						new FieldMapper("parentAssessmentId"))
.put("REQUIRE_APPROVAL_FLAG", 						new FieldMapper("requireApprovalFlag"))
.put("HIGH_VALUE", 						new FieldMapper("highValue"))
.put("EFFECTIVE_DATE", 						new FieldMapper("effectiveDate"))
.put("MODIFY_DATETIME", 						new FieldMapper("modifyDatetime"))
.put("SCORE", 						new FieldMapper("score"))
.put("REQUIRE_EVALUATION_FLAG", 						new FieldMapper("requireEvaluationFlag"))
.put("DESCRIPTION", 						new FieldMapper("description"))
.put("CREATE_USER_ID", 						new FieldMapper("createUserId"))
.put("MEDICAL_FLAG", 						new FieldMapper("medicalFlag"))
.put("SECT_SCORE_OVERRIDE_FLAG", 						new FieldMapper("sectScoreOverrideFlag"))
.put("ACTIVE_FLAG", 						new FieldMapper("activeFlag"))
.put("MODIFY_USER_ID", 						new FieldMapper("modifyUserId"))
.put("LIST_SEQ", 						new FieldMapper("listSeq"))
.put("ASSESSMENT_CLASS", 						new FieldMapper("assessmentClass"))
.put("SCHEDULE_COMPLETE_DAYS", 						new FieldMapper("scheduleCompleteDays"))
.put("OVERRIDEABLE_FLAG", 						new FieldMapper("overrideableFlag"))
.put("ASSESS_COMMENT", 						new FieldMapper("assessComment"))
.put("SCREEN_CODE", 						new FieldMapper("screenCode"))
.put("REVIEW_CYCLE_DAYS", 						new FieldMapper("reviewCycleDays"))
.put("UPDATE_ALLOWED_FLAG", 						new FieldMapper("updateAllowedFlag"))
.put("CALCULATE_TOTAL_FLAG", 						new FieldMapper("calculateTotalFlag"))
.put("CREATE_DATE", 						new FieldMapper("createDate"))
.put("TOTAL_PERCENT", 						new FieldMapper("totalPercent"))
.put("EXPIRY_DATE", 						new FieldMapper("expiryDate"))
.build();
private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("ASSESSMENT_CODE", 						new FieldMapper("assessmentCode"))
.put("SECTION", 						new FieldMapper("section"))
.put("YES", 						   new FieldMapper("yes"))
.put("DESCRIPTION", 						new FieldMapper("description"))
.put("ASSESSMENT_ID", 						new FieldMapper("assessmentId"))
.put("CODE", 						    new FieldMapper("code"))
.build();
private final Map<String, FieldMapper> notifctnsMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("SEAL_FLAG", 						new FieldMapper("sealFlag"))
.put("CREATE_DATETIME", 						new FieldMapper("createDatetime"))
.put("MODIFY_DATETIME", 						new FieldMapper("modifyDatetime"))
.put("SCORE_SEQ", 						new FieldMapper("scoreSeq"))
.put("EXPIRY_DATE", 						new FieldMapper("expiryDate"))
.put("ASSESSMENT_ID", 						new FieldMapper("assessmentId"))
.put("NEXT_ASSESSMENT_ID", 						new FieldMapper("nextAssessmentId"))
.build();


/**
 * Creates new OimsenotRepositoryImpl class Object 
 */
public OimsenotRepositoryImpl() {
}
/**
* Fetch the records from database table
*
* @param objSearchDao Assessments
*
* @return List<Assessments>
*
* @throws SQLException
*/
 public List<Assessments> assessmentsExecuteQuery(final Assessments objSearchDao)  { 
	 final String sql = getQuery("OIMSENOT_ASSESSMENTS_FIND_ASSESSMENTS");
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		String preSqlQuery = null;
		final MapSqlParameterSource params = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" WHERE ");
			if (objSearchDao.getAssessmentId() != null) {
				sqlQuery.append(" PARENT_ASSESSMENT_ID  = :parentAssessmentId " + " AND ");
				params.addValue("parentAssessmentId", objSearchDao.getAssessmentId());
			}
			if (objSearchDao.getAssessmentCode() != null) {
				sqlQuery.append(" ASSESSMENT_CODE  = :assessmentCode " + " AND ");
				params.addValue("assessmentCode", objSearchDao.getAssessmentCode());
			}
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("WHERE")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("AND")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		preSqlQuery = preparedSql.concat(" ORDER BY LIST_SEQ ");
		final RowMapper<Assessments> assesRowMapper = Row2BeanRowMapper.makeMapping(preSqlQuery, Assessments.class,assesMapping);
		return  (ArrayList<Assessments>)namedParameterJdbcTemplate.query(preSqlQuery, params, assesRowMapper);
} 

/**
* Fetch the records from database table
*
* @param objSearchDao AssessSectionNotifications
*
* @return List<AssessSectionNotifications>
*
* @throws SQLException
*/
 public List<AssessSectionNotifications> assessSectionNotificationsExecuteQuery(final AssessSectionNotifications objSearchDao)  {
		final String sql = getQuery("OIMSENOT_ASSESSSECTIONNOTIFICATIONS_FIND_ASSESS_SECTION_NOTIFICATIONS");
		final RowMapper<AssessSectionNotifications> assessNotifcRow = Row2BeanRowMapper.makeMapping(sql, AssessSectionNotifications.class,notifctnsMapping);
		return (ArrayList<AssessSectionNotifications>)namedParameterJdbcTemplate.query(sql, createParams("ASSESSMENT_ID",objSearchDao.getAssessmentId()), assessNotifcRow);
} 
 
 /**
 *  This method is used to insert the records in the data base tables based on
 *
 * @param lstAssessSectionNotifications List<AssessSectionNotifications>
 *
 * @return List<Integer>
 *
 * @throws SQLException
 */
 @Override
	public Integer assessSectionNotificationsInsertAssessSectionNotifications(final List<AssessSectionNotifications> list)  {
		final String sql = getQuery("OIMSENOT_ASSESSSECTIONNOTIFICATIONS_INSERT_ASSESS_SECTION_NOTIFICATIONS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		
		for(final AssessSectionNotifications notfictn :list ) {
			parameters.add(new BeanPropertySqlParameterSource(notfictn));
		}
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		
		if (list.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}
	

/**
* This method is used to update the data base tables based on
*
* @param lstAssessSectionNotifications List<AssessSectionNotifications>
*
* @throws SQLException
*/
 public Integer assessSectionNotificationsUpdateAssessSectionNotifications(final List<AssessSectionNotifications> listNotifcn)  {
	final String sql = getQuery("OIMSENOT_ASSESSSECTIONNOTIFICATIONS_UPDATE_ASSESS_SECTION_NOTIFICATIONS");
	int[] returnArray = new int[] {};
	final List<SqlParameterSource> parameters = new ArrayList<>();
	for (final AssessSectionNotifications section : listNotifcn) {
		parameters.add(new BeanPropertySqlParameterSource(section));
	}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
	if (listNotifcn.size() == returnArray.length) {
		return 1;
	} else {
		return 0;
	}

}

/**
* This method is used to delete records from  data base tables based on
*
* @param lstAssessSectionNotifications List<AssessSectionNotifications>
*
* @throws SQLException
*/
 public Integer assessSectionNotificationsDeleteAssessSectionNotifications(final List<AssessSectionNotifications> list)  {
	final String sql = getQuery("OIMSENOT_ASSESSSECTIONNOTIFICATIONS_DELETE_ASSESS_SECTION_NOTIFICATIONS");
	int[] returnArray = new int[] {};
	final List<SqlParameterSource> parameters = new ArrayList<>();
	for (final AssessSectionNotifications notifications : list) {
		 parameters.add(new BeanPropertySqlParameterSource(notifications));
	}
	try {
		String tableName = "ASSESS_SECTION_NOTIFICATIONS";
		String whereClause = "ASSESSMENT_ID = :assessmentId and SCORE_SEQ = :scoreSeq";
		batchUpdatePreDeletedRows(tableName, whereClause , parameters);
	} catch (Exception e) {
		logger.error("Exception occured in " + this.getClass().getName() + " in method assessSectionNotificationsDeleteAssessSectionNotifications", e);
	}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
	
	if (list.size() == returnArray.length) {
		return 1;
	} else {
		return 0;
	}

}
/**
* Used to capture results from select query
* @return List<M> 
*/
public List<Assessments> cgfkNextSectionRecordGroup(final String parentId,final String assesmentId)  {
 	final String sql = getQuery("OIMSENOT_FIND_CGFKNEXTSECTION");
 	final RowMapper<Assessments>mRowMapper = Row2BeanRowMapper.makeMapping(sql,Assessments.class, mMapping);
	try {
  		return namedParameterJdbcTemplate.query(sql,createParams("parentId",parentId ,"ASSESSMENTID",assesmentId),mRowMapper);
  	} catch (EmptyResultDataAccessException e) {
  		logger.error("Exception in cgfkNextSectionRecordGroup: oimsenot", e);
  		return Collections.emptyList();  
	}
}
/**
* Used to capture results from select query
* @return List<M> 
*/
public List<Assessments> cgfkSectionCodeRecordGroup( final Long assessmentId)  {
 	final String sql = getQuery("OIMSENOT_FIND_CGFKSECTIONCODE");
 	final RowMapper<Assessments>mRowMapper = Row2BeanRowMapper.makeMapping(sql,Assessments.class, mMapping);
	try {
  		return namedParameterJdbcTemplate.query(sql,createParams("ASSESSMENTID",assessmentId),mRowMapper);
  	} catch (EmptyResultDataAccessException e) {
  		logger.error("Exception in cgfkSectionCodeRecordGroup: oimsenot", e);
  		return Collections.emptyList();  
	}
}
/**
* Used to capture results from select query
* @return List<M> 
*/
public List<ReferenceCodes> cgfkScoreTypeRecordGroup()  {
 	final String sql = getQuery("OIMSENOT_FIND_CGFKSCORETYPE");
 	final RowMapper<ReferenceCodes>mRowMapper = Row2BeanRowMapper.makeMapping(sql,ReferenceCodes.class, mMapping);
	try {
  		return namedParameterJdbcTemplate.query(sql,createParams(),mRowMapper);
  	} catch (EmptyResultDataAccessException e) {
  		logger.error("Exception in cgfkScoreTypeRecordGroup: oimsenot", e);
  		return Collections.emptyList();  
	}
}
/**
* Used to capture results from select query
* @return List<M> 
*/
public List<ReferenceCodes> cgfkNextSectionFlagRecordGroup()  {
 	final String sql = getQuery("OIMSENOT_FIND_CGFKNEXTSECTIONFLAG");
 	final RowMapper<ReferenceCodes>mRowMapper = Row2BeanRowMapper.makeMapping(sql,ReferenceCodes.class, mMapping);
	try {
  		return namedParameterJdbcTemplate.query(sql,createParams(),mRowMapper);
  	} catch (EmptyResultDataAccessException e) {
  		logger.error("Exception in cgfkNextSectionFlagRecordGroup: oimsenot", e);
  		return Collections.emptyList();  
	}
}
	
	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * assessSectionNotificationsPreInsert
	 *
	 * @param params
	 *
	 */
	public AssessSectionNotifications assessSectionNotificationsPreInsert(final AssessSectionNotifications paramBean)  {
		final String sql = getQuery("OIMSENOT_ASSESS_SECTION_NOTIFICATIONS_PREINSERT");
		final RowMapper<AssessSectionNotifications> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,AssessSectionNotifications.class,  notifctnsMapping);
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("ASSESSMENTID",paramBean.getAssessmentId()), columnRowMapper);
	}
	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * assessSectionNotificationsPostQuery
	 *
	 * @param params
	 *
	 */
	public Assessments assessSectionNotificationsPostQuery(final AssessSectionNotifications paramBean)  {
		final String sql = getQuery("OIMSENOT_ASSESS_SECTION_NOTIFICATIONS_POSTQUERY");
		final RowMapper<Assessments> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,Assessments.class,  assesMapping);
		return  namedParameterJdbcTemplate.queryForObject(sql, createParams("NEXT_ASSESSMENT_ID",paramBean.getNextAssessmentId()), columnRowMapper);
	}
	
	
	

	

	
	
	
	 


}
