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
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.inst.casemanagement.beans.ProgramServices;
import net.syscon.s4.inst.classification.assessmentmaintenance.OimcsummRepository;
import net.syscon.s4.inst.classification.beans.Assessments;
import net.syscon.s4.inst.classification.beans.VAssOffNeeds;
import net.syscon.s4.inst.classification.beans.VAssTreatProts;
/**
 * Class OimcsummRepositoryImpl
 * @author Arkin Software Technologies 
 * @version 1.0 
 */
@Repository
public class OimcsummRepositoryImpl extends RepositoryBase implements OimcsummRepository{

/**
 * Creates new OimcsummRepositoryImpl class Object 
 */
public OimcsummRepositoryImpl() {
}
/**
* Logger object used to print the log in the file
*/
private static Logger logger = LogManager.getLogger(OimcsummRepositoryImpl.class.getName());
private final Map<String, FieldMapper> vAssTreatProtsMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("OFF_ASS_NEED_ID", 						new FieldMapper("offAssNeedId"))
.put("TREATMENT_ID", 						new FieldMapper("treatmentId"))
.put("CASEWORK_TYPE", 						new FieldMapper("caseworkType"))
.put("REFERRAL_FLAG", 						new FieldMapper("referralFlag"))
.put("PROGRAM_ID", 						new FieldMapper("programId"))
.build();
private final Map<String, FieldMapper> vAssOffNeedsMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("ASSESSMENT_ID", 						new FieldMapper("assessmentId"))
.build();
private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("ASSESSMENT_CODE", 						new FieldMapper("assessmentCode "))
.put("DESCRIPTION", 						new FieldMapper("description"))
.put("'SECTION", 						new FieldMapper("'section"))
.put("ASSESSMENT_ID", 						new FieldMapper("assessmentId "))
.build();
private final Map<String, FieldMapper> assessmentsMapping = new ImmutableMap.Builder<String, FieldMapper>()
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
/**
* Fetch the records from database table
*
* @param objSearchDao Assessments
*
* @return List<Assessments>
*
* @throws SQLException
*/
 public List<Assessments> assessmentsExecuteQuery(Assessments objSearchDao)  {
		final String sql = getQuery("OIMCSUMM_ASSESSMENTS_FIND_ASSESSMENTS");
		final RowMapper<Assessments> AssessmentsRowMapper = Row2BeanRowMapper.makeMapping(sql, Assessments.class,assessmentsMapping);
		final ArrayList<Assessments> returnList = (ArrayList<Assessments>)namedParameterJdbcTemplate.query(sql, createParams("assessmentId", objSearchDao.getAssessmentId()), AssessmentsRowMapper);
		return returnList;
} 

/**
* Fetch the records from database table
*
* @param objSearchDao VAssOffNeeds
*
* @return List<VAssOffNeeds>
*
* @throws SQLException
*/
 public List<VAssOffNeeds> vAssOffNeedsExecuteQuery(VAssOffNeeds objSearchDao)  {
		final String sql = getQuery("OIMCSUMM_VASSOFFNEEDS_FIND_V_ASS_OFF_NEEDS");
		final RowMapper<VAssOffNeeds> VAssOffNeedsRowMapper = Row2BeanRowMapper.makeMapping(sql, VAssOffNeeds.class,vAssOffNeedsMapping);
		final ArrayList<VAssOffNeeds> returnList = (ArrayList<VAssOffNeeds>)namedParameterJdbcTemplate.query(sql, createParams("assessmentId", objSearchDao.getAssessmentId()), VAssOffNeedsRowMapper);
		return returnList;
} 

/**
*  This method is used to insert the records in the data base tables based on
*
* @param lstVAssOffNeeds List<VAssOffNeeds>
*
* @return List<Integer>
*
* @throws SQLException
*/
 public Integer vAssOffNeedsInsertVAssOffNeeds(final List<VAssOffNeeds> lstVAssOffNeeds)  {
	String sql = getQuery("OIMCSUMM_VASSOFFNEEDS_INSERT_V_ASS_OFF_NEEDS");
	List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
	for (VAssOffNeeds vAssOffNeeds : lstVAssOffNeeds) {
		parameters.add(new BeanPropertySqlParameterSource(vAssOffNeeds));
	}
	try {	
		  namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));		
		return 1;
	} catch(Exception e) {
		logger.error("The Exception in vAssOffNeedsInsertVAssOffNeeds   is : {} " , e);
		return 0;
	}
}

/**
* This method is used to update the data base tables based on
*
* @param lstVAssOffNeeds List<VAssOffNeeds>
*
* @throws SQLException
*/
 public Integer vAssOffNeedsUpdateVAssOffNeeds(final List<VAssOffNeeds> lstVAssOffNeeds)  {
	String sql = getQuery("OIMCSUMM_VASSOFFNEEDS_UPDATE_V_ASS_OFF_NEEDS");
	int[] returnArray = new int[] {};
	List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
	for (VAssOffNeeds vAssOffNeeds : lstVAssOffNeeds) {
		parameters.add(new BeanPropertySqlParameterSource(vAssOffNeeds));
	}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
	if (lstVAssOffNeeds.size() == returnArray.length) {
		return 1;
	} else {
		return 0;
	}

}

/**
* This method is used to delete records from  data base tables based on
*
* @param lstVAssOffNeeds List<VAssOffNeeds>
*
* @throws SQLException
*/
 public Integer vAssOffNeedsDeleteVAssOffNeeds(final List<VAssOffNeeds> lstVAssOffNeeds)  {
	String sql = getQuery("OIMCSUMM_VASSOFFNEEDS_DELETE_V_ASS_OFF_NEEDS");
	int[] returnArray = new int[] {};
	List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
	for (VAssOffNeeds vAssOffNeeds : lstVAssOffNeeds) {
		 parameters.add(new BeanPropertySqlParameterSource(vAssOffNeeds));
	}
	try {
		String tableName = "ASSESSED_OFFENDER_NEEDS";
		String whereClause = "OFF_ASS_NEED_ID=:offAssNeedId";
		batchUpdatePreDeletedRows(tableName, whereClause , parameters);
	} catch (Exception e) {
		logger.error("Exception occured in " + this.getClass().getName() + " in method vAssOffNeedsDeleteVAssOffNeeds", e);
	}
	try {
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
	}catch (Exception e) {
		logger.error("Exception occured in " + this.getClass().getName() + " in method vAssOffNeedsDeleteVAssOffNeeds", e);
	}
	
	if (lstVAssOffNeeds.size() == returnArray.length) {
		return 1;
	} else {
		return 0;
	}

}

/**
* Fetch the records from database table
*
* @param objSearchDao VAssTreatProts
*
* @return List<VAssTreatProts>
*
* @throws SQLException
*/
 public List<VAssTreatProts> vAssTreatProtsExecuteQuery(VAssTreatProts objSearchDao)  {
		final String sql = getQuery("OIMCSUMM_VASSTREATPROTS_FIND_V_ASS_TREAT_PROTS");
		final RowMapper<VAssTreatProts> VAssTreatProtsRowMapper = Row2BeanRowMapper.makeMapping(sql, VAssTreatProts.class,vAssTreatProtsMapping);
		final ArrayList<VAssTreatProts> returnList = (ArrayList<VAssTreatProts>)namedParameterJdbcTemplate.query(sql, createParams("offAssNeedId",objSearchDao.getOffAssNeedId()), VAssTreatProtsRowMapper);
		return returnList;
} 
/**
* @param 
*
* @throws SQLException 
*
*/
public int PRE_INSERT()  {
return 0;
}

/**
*  This method is used to insert the records in the data base tables based on
*
* @param lstVAssTreatProts List<VAssTreatProts>
*
* @return List<Integer>
*
* @throws SQLException
*/
 public Integer vAssTreatProtsInsertVAssTreatProts(final List<VAssTreatProts> lstVAssTreatProts)  {
	String sql = getQuery("OIMCSUMM_VASSTREATPROTS_INSERT_V_ASS_TREAT_PROTS");
	List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
	for (VAssTreatProts vAssTreatProts : lstVAssTreatProts) {
		parameters.add(new BeanPropertySqlParameterSource(vAssTreatProts));
	}
	try {	
		 namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));		
		return 1;
	} catch(Exception e) {
		logger.error("The Exception in vAssTreatProtsInsertVAssTreatProts   is : {} " , e);
		return 0;
	}

}

/**
* This method is used to update the data base tables based on
*
* @param lstVAssTreatProts List<VAssTreatProts>
*
* @throws SQLException
*/
 public Integer vAssTreatProtsUpdateVAssTreatProts(final List<VAssTreatProts> lstVAssTreatProts)  {
	String sql = getQuery("OIMCSUMM_VASSTREATPROTS_UPDATE_V_ASS_TREAT_PROTS");
	int[] returnArray = new int[] {};
	List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
	for (VAssTreatProts vAssTreatProts : lstVAssTreatProts) {
		parameters.add(new BeanPropertySqlParameterSource(vAssTreatProts));
	}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
	if (lstVAssTreatProts.size() == returnArray.length) {
		return 1;
	} else {
		return 0;
	}

}

/**
* This method is used to delete records from  data base tables based on
*
* @param lstVAssTreatProts List<VAssTreatProts>
*
* @throws SQLException
*/
 public Integer vAssTreatProtsDeleteVAssTreatProts(final List<VAssTreatProts> lstVAssTreatProts)  {
	String sql = getQuery("OIMCSUMM_VASSTREATPROTS_DELETE_V_ASS_TREAT_PROTS");
	int[] returnArray = new int[] {};
	List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
	for (VAssTreatProts vAssTreatProts : lstVAssTreatProts) {
		 parameters.add(new BeanPropertySqlParameterSource(vAssTreatProts));
	}
	try {
		String tableName = "ASSESSED_TREATMENT_PROTOCOLS";
		String whereClause = "TREATMENT_ID = :treatmentId";
		batchUpdatePreDeletedRows(tableName, whereClause , parameters);
	} catch (Exception e) {
		logger.error("Exception occured in " + this.getClass().getName() + " in method vAssTreatProtsDeleteVAssTreatProts", e);
	}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
	
	if (lstVAssTreatProts.size() == returnArray.length) {
		return 1;
	} else {
		return 0;
	}

}
/**
* Used to capture results from select query
* @return List<M> 
*/
public List<ReferenceCodes> rgCaseworkRecordGroup()  {
 	final String sql = getQuery("OIMCSUMM_FIND_RGCASEWORK");
 	final RowMapper<ReferenceCodes>mRowMapper = Row2BeanRowMapper.makeMapping(sql,ReferenceCodes.class, mMapping);
 
	try {
  		return namedParameterJdbcTemplate.query(sql,createParams(),mRowMapper);
  	} catch (EmptyResultDataAccessException e) {
  		return Collections.emptyList();  
	}
}
/**
* Used to capture results from select query
* @return List<M> 
*/
public List<ProgramServices> rgProgramIdRecordGroup(String prograimCategory)  {
 	final String sql = getQuery("OIMCSUMM_FIND_RGPROGRAMID");
 	final RowMapper<ProgramServices>mRowMapper = Row2BeanRowMapper.makeMapping(sql,ProgramServices.class, mMapping);
 
	try {
  		return namedParameterJdbcTemplate.query(sql,createParams("PROGRAMCATEGORY",prograimCategory),mRowMapper);
  	} catch (EmptyResultDataAccessException e) {
  		return Collections.emptyList();  
	}
}
/**
* Used to capture results from select query
* @return List<M> 
*/
public List<ReferenceCodes> rgCaseplanAssRecordGroup()  {
 	final String sql = getQuery("OIMCSUMM_FIND_RGCASEPLANASS");
 	final RowMapper<ReferenceCodes>mRowMapper = Row2BeanRowMapper.makeMapping(sql,ReferenceCodes.class, mMapping);
 
	try {
  		return namedParameterJdbcTemplate.query(sql,createParams(),mRowMapper);
  	} catch (EmptyResultDataAccessException e) {
  		return Collections.emptyList();  
	}
}
/**
* Used to capture results from select query
* @return List<M> 
*/
public List<Assessments> cgfkNextSectionRecordGroup()  {
 	final String sql = getQuery("OIMCSUMM_FIND_CGFKNEXTSECTION");
 	final RowMapper<Assessments>mRowMapper = Row2BeanRowMapper.makeMapping(sql,Assessments.class, mMapping);
 
	try {
  		return namedParameterJdbcTemplate.query(sql,createParams(),mRowMapper);
  	} catch (EmptyResultDataAccessException e) {
  		return Collections.emptyList();  
	}
}
/**
* Used to capture results from select query
* @return List<M> 
*/
public List<ReferenceCodes> cgfkScoreTypeRecordGroup()  {
 	final String sql = getQuery("OIMCSUMM_FIND_CGFKSCORETYPE");
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
public List<ReferenceCodes> rgPrgCategoryRecordGroup()  {
 	final String sql = getQuery("OIMCSUMM_FIND_RGPRGCATEGORY");
 	final RowMapper<ReferenceCodes>mMRowMapper = Row2BeanRowMapper.makeMapping(sql,ReferenceCodes.class, mMapping);
 
	try {
  		return namedParameterJdbcTemplate.query(sql,createParams(),mMRowMapper);
  	} catch (EmptyResultDataAccessException e) {
  		return Collections.emptyList();  
	}
}
	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * assessmentsOnCheckDeleteMaster
	 *
	 * @param params
	 *
	 */
	public List<VAssOffNeeds> assessmentsOnCheckDeleteMaster(VAssOffNeeds paramBean)  {
		final String sql = getQuery("OIMCSUMM_ASSESSMENTS_ONCHECKDELETEMASTER");
		final RowMapper<VAssOffNeeds> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,VAssOffNeeds.class,  vAssOffNeedsMapping);
		final ArrayList<VAssOffNeeds> returnList = (ArrayList<VAssOffNeeds>)namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		return returnList;
	}
	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * vAssOffNeedsPreInsert
	 *
	 * @param params
	 *
	 */
	public Long vAssOffNeedsPreInsert()  {
		final String sql = getQuery("OIMCSUMM_V_ASS_OFF_NEEDS_PREINSERT");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				Long.class);
	}
	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * vAssOffNeedsOnCheckDeleteMaster
	 *
	 * @param params
	 *
	 */
	public List<VAssTreatProts> vAssOffNeedsOnCheckDeleteMaster(VAssTreatProts paramBean)  {
		final String sql = getQuery("OIMCSUMM_V_ASS_OFF_NEEDS_ONCHECKDELETEMASTER");
		final RowMapper<VAssTreatProts> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,VAssTreatProts.class,  vAssTreatProtsMapping);
		final ArrayList<VAssTreatProts> returnList = (ArrayList<VAssTreatProts>)namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		return returnList;
	}
	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * vAssTreatProtsPreInsert
	 *
	 * @param params
	 *
	 */
	public Long vAssTreatProtsPreInsert()  {
		final String sql = getQuery("OIMCSUMM_V_ASS_TREAT_PROTS_PREINSERT");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				Long.class);
	}
	

	@Override
	public List<OmsModules> createFormGlobals(OmsModules paramBean) {
		return null;
	}

	
	/**
	* Used to capture results from select query
	* @return List<M> 
	*/
	public List<Assessments> cgfkSectionCodeRecordGroup( final Long assessmentId)  {
	 	final String sql = getQuery("OIMCSUMM_FIND_CGFKSECTIONCODE");
	 	final RowMapper<Assessments>mRowMapper = Row2BeanRowMapper.makeMapping(sql,Assessments.class, mMapping);
	 
		try {
	  		return namedParameterJdbcTemplate.query(sql,createParams("ASSESSMENTID",assessmentId),mRowMapper);
	  	} catch (EmptyResultDataAccessException e) {
	  		logger.error("Exception in cgfkSectionCodeRecordGroup: oimsenot", e);
	  		return Collections.emptyList();  
		}
	}

}
