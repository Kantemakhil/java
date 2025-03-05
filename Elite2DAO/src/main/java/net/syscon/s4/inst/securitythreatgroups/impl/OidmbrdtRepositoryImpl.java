package net.syscon.s4.inst.securitythreatgroups.impl;

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
import net.syscon.s4.common.beans.FormAccessibleForms;
import net.syscon.s4.common.beans.OffenderAssessments;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SecurityThreatGroups;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.OffenderStgAffiliations;
import net.syscon.s4.im.beans.OffenderStgDetails;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.inst.classification.beans.Assessments;
import net.syscon.s4.inst.securitythreatgroups.OidmbrdtRepository;
import oracle.jdbc.internal.OracleTypes;

/**
 * Class OidmbrdtRepositoryImpl
 */
@Repository
public class OidmbrdtRepositoryImpl extends RepositoryBase implements OidmbrdtRepository {
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidmbrdtRepositoryImpl.class.getName());

	/**
	 * Creates new OidmbrdtRepositoryImpl class Object
	 */
	public OidmbrdtRepositoryImpl() {
	}

	private final Map<String, FieldMapper> offenderStgDetailsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("VAL_DATE", new FieldMapper("valDate"))
			.put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId"))
			.put("STG_SEQ", new FieldMapper("stgSeq"))
			.put("ACTION_CODE", new FieldMapper("actionCode"))
			.build();
	private final Map<String, FieldMapper> assessmentsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("LAST_NAME", new FieldMapper("lastName"))
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("FIRST_NAME", new FieldMapper("firstName"))
			.put("ASSESSMENT_TYPE_ID", new FieldMapper("assessmentTypeId"))
			.put("ASSESSOR_STAFF_ID", new FieldMapper("assessorStaffId"))
			.build();
	private final Map<String, FieldMapper> staffMembersMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("LAST_NAME", new FieldMapper("lastName"))
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("FIRST_NAME", new FieldMapper("firstName"))
			.put("ASSESSMENT_TYPE_ID", new FieldMapper("assessmentTypeId"))
			.put("ASSESSOR_STAFF_ID", new FieldMapper("assessorStaffId"))
			.build();
	private final Map<String, FieldMapper> offenderAssessmentsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("APPROVED_SUP_LEVEL_TYPE", 			new FieldMapper("approvedSupLevelType"))
			.put("LAST_NAME||'", 						new FieldMapper(" lastName||'"))
			.put("COMMITTE_COMMENT_TEXT", 				new FieldMapper("committeCommentText"))
			.put("OVERRIDE_USER_ID", 					new FieldMapper("overrideUserId"))
			.put("OVERRIDE_COMMENT_TEXT", 				new FieldMapper("overrideCommentText"))
			.put("CALCSUPLEVELTYPEDESC", 				new FieldMapper("calcSupLevelTypeDesc"))
			.put("OVERRIDEDSUPLEVELTYPEDESC", 			new FieldMapper("overridedSupLevelTypeDesc"))
			.put("STAFF_ID", 							new FieldMapper(" staffId "))
			.put("ASSESSMENT_CREATE_LOCATION", 			new FieldMapper("assessmentCreateLocation"))
			.put("ASSESSMENT_DATE", 				    new FieldMapper("assessmentDate"))
			.put("PLACE_AGY_LOC_ID", 					new FieldMapper("placeAgyLocId"))
			.put("SEAL_FLAG", 							new FieldMapper("sealFlag"))
			.put("ASSESS_COMMITTE_CODE", 				new FieldMapper("assessCommitteCode"))
			.put("CREATE_DATETIME", 					new FieldMapper("createDatetime"))
			.put("CALC_SUP_LEVEL_TYPE", 				new FieldMapper("calcSupLevelType"))
			.put("MODIFY_DATETIME", 					new FieldMapper("modifyDatetime"))
			.put("SCORE", 								new FieldMapper("score"))
			.put("ASSESS_STATUS", 						new FieldMapper("assessStatus"))
			.put("OVERRIDE_STAFF_ID", 					new FieldMapper("overrideStaffId"))
			.put("NEXT_REVIEW_DATE", 					new FieldMapper("nextReviewDate"))
			.put("OFFENDER_BOOK_ID", 					new FieldMapper("offenderBookId"))
			.put("ASSESS_COMMENT_TEXT", 				new FieldMapper("assessCommentText"))
			.put("CREATE_USER_ID", 						new FieldMapper("createUserId"))
			.put("OVERRIDE_REASON_TEXT", 				new FieldMapper("overrideReasonText"))
			.put("EVALUATION_RESULT_CODE", 				new FieldMapper("evaluationResultCode"))
			.put("MODIFY_USER_ID", 						new FieldMapper("modifyUserId"))
			.put("REVIEW_SUP_LEVEL_TYPE", 				new FieldMapper("reviewSupLevelType"))
			.put("ASSESS_STAFF_ID", 					new FieldMapper("assessStaffId"))
			.put("EVALUATION_DATE", 					new FieldMapper("evaluationDate"))
			.put("REVIEW_SUP_LEVEL_TEXT", 				new FieldMapper("reviewSupLevelText"))
			.put("OVERRIDED_SUP_LEVEL_TYPE", 			new FieldMapper("overridedSupLevelType"))
			.put("0", 									new FieldMapper("0"))
			.put("CREATION_USER", 						new FieldMapper("creationUser"))
			.put("REVIEW_COMMITTE_CODE", 				new FieldMapper("reviewCommitteCode"))
			.put("REVIEW_PLACE_AGY_LOC_ID", 			new FieldMapper("reviewPlaceAgyLocId"))
			.put("OVERRIDE_REASON", 					new FieldMapper("overrideReason"))
			.put("REVIEW_PLACEMENT_TEXT", 				new FieldMapper("reviewPlacementText"))
			.put("CREATION_DATE", 						new FieldMapper("creationDate"))
			.put("ASSESSMENT_TYPE_ID", 					new FieldMapper("assessmentTypeId"))
			.put("ASSESSMENT_TYPE_CODE", 				new FieldMapper("assessmentTypeCode"))
			.build();

	
	private final Map<String, FieldMapper> securityThreatGroupsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("STG_ID", new FieldMapper("stgId"))
			.build();
	private final Map<String, FieldMapper> omsModulesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESTINATION_FORM", new FieldMapper("destinationForm"))
			.put("DESCRIPTION", new FieldMapper("description"))
			.build();
	private final Map<String, FieldMapper> offenderStgAffiliationsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId"))
			.build();
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("DESCRIPTION1", new FieldMapper("description1"))
			.put("STG_CODE", new FieldMapper("stgCode"))
			.put("STG_ID", new FieldMapper("stgId"))
			.put("CODE", new FieldMapper("code"))
			.build();
	private final Map<String, FieldMapper> systemProfilesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PROFILE_VALUE", new FieldMapper("profileValue"))
			.build();
	private final Map<String, FieldMapper> formsaccMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("LIST_SEQ", new FieldMapper("listSeq"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.put("ORIGINATING_FORM", new FieldMapper("originatingForm"))
			.put("DESTINATION_FORM", new FieldMapper("destinationForm"))
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("STG_ID", new FieldMapper("stgId"))
			.put("CHECK_FLAG", new FieldMapper("checkFlag"))
			.build();

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            OffenderAssessments
	 *
	 * @return List<OffenderAssessments>
	 *
	 * @throws SQLException
	 */
	public List<OffenderAssessments> offenderAssessmentsExecuteQuery(final OffenderAssessments objSearchDao) {
		final String sql = getQuery("OIDMBRDT_OFFENDERASSESSMENTS_FIND_OFFENDER_ASSESSMENTS");
		final RowMapper<OffenderAssessments> rowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderAssessments.class,
				offenderAssessmentsMapping);
		String preparedSql = null;
		final MapSqlParameterSource inParameterSource = new MapSqlParameterSource();
		final StringBuffer sqlQuery = new StringBuffer();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" WHERE ");
			if (objSearchDao.getOffenderBookId() != 0) {
				sqlQuery.append("OFFENDER_BOOK_ID = :OFFENDER_BOOK_ID" + " AND ");
				inParameterSource.addValue("OFFENDER_BOOK_ID", objSearchDao.getOffenderBookId());
			}
			if (objSearchDao.getAssessmentDate() != null) {
				sqlQuery.append("ASSESSMENT_DATE::date = :ASSESSMENT_DATE" + " AND ");
				inParameterSource.addValue("ASSESSMENT_DATE", objSearchDao.getAssessmentDate());
			}
			if (objSearchDao.getScore() != null) {
				sqlQuery.append("SCORE = :SCORE" + " AND ");
				inParameterSource.addValue("SCORE", objSearchDao.getScore());
			}
			if (objSearchDao.getAssessmentTypeCode() != null) {
				sqlQuery.append(
						"ASSESSMENT_TYPE_ID IN (SELECT ASSESSMENT_ID FROM ASSESSMENTS WHERE upper(DESCRIPTION) LIKE upper(:ASSESSMENT_TYPE_CODE )||'%')"
								+ " AND ");
				inParameterSource.addValue("ASSESSMENT_TYPE_CODE",  objSearchDao.getAssessmentTypeCode() );
			}
			if (objSearchDao.getOverrideUserId() != null && objSearchDao.getOverrideUserId().trim().length() > 0) {
				sqlQuery.append("OVERRIDE_USER_ID =  :OVERRIDE_USER_ID" + " AND ");
				inParameterSource.addValue("OVERRIDE_USER_ID", objSearchDao.getOverrideUserId().trim());
			}
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("WHERE")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("AND")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		preparedSql = preparedSql
				+ " AND  ASSESSMENT_TYPE_ID IN (SELECT ASSESSMENT_ID FROM ASSESSMENTS WHERE ASSESSMENT_CODE = oms_miscellaneous_get_profile_value( 'CLIENT',  'MBR_ASSESS')) ORDER BY ASSESSMENT_DATE DESC ";
		List<OffenderAssessments> returnList = namedParameterJdbcTemplate.query(preparedSql, inParameterSource,
				rowMapper);
		return returnList;
	}

	/**
	 * @param
	 *
	 * @throws SQLException
	 *
	 */
	public int preInsert() {
		return 0;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            OffenderStgAffiliations
	 *
	 * @return List<OffenderStgAffiliations>
	 *
	 * @throws SQLException
	 */
	public List<OffenderStgAffiliations> offenderStgAffiliationsExecuteQuery(
			final OffenderStgAffiliations objSearchDao) {
		final String sql = getQuery("OIDMBRDT_OFFENDERSTGAFFILIATIONS_FIND_OFFENDER_STG_AFFILIATIONS");
		final RowMapper<OffenderStgAffiliations> OffenderStgAffiliationsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderStgAffiliations.class, offenderStgAffiliationsMapping);
		final ArrayList<OffenderStgAffiliations> returnList = (ArrayList<OffenderStgAffiliations>) namedParameterJdbcTemplate
				.query(sql, createParams("OFFENDER_BOOK_ID", objSearchDao.getOffenderBookId()),
						OffenderStgAffiliationsRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstOffenderStgAffiliations
	 *            List<OffenderStgAffiliations>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public Integer offenderStgAffiliationsInsert(final List<OffenderStgAffiliations> insertList) {
		final String sql = getQuery("OIDMBRDT_OFFENDERSTGAFFILIATIONS_INSERT_OFFENDER_STG_AFFILIATIONS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderStgAffiliations obj : insertList) {
			parameters.add(new BeanPropertySqlParameterSource(obj));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (insertList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstOffenderStgAffiliations
	 *            List<OffenderStgAffiliations>
	 *
	 * @throws SQLException
	 */
	public Integer offenderStgAffiliationsUpdateOffenderStgAffiliations(
			final List<OffenderStgAffiliations> updateList) {
		final String sql = getQuery("OIDMBRDT_OFFENDERSTGAFFILIATIONS_UPDATE_OFFENDER_STG_AFFILIATIONS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderStgAffiliations listObj : updateList) {
			parameters.add(new BeanPropertySqlParameterSource(listObj));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (updateList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            OffenderStgDetails
	 *
	 * @return List<OffenderStgDetails>
	 *
	 * @throws SQLException
	 */
	public List<OffenderStgDetails> offenderStgDetailsExecuteQuery(final OffenderStgDetails objSearchDao) {
		final String sql = getQuery("OIDMBRDT_OFFENDERSTGDETAILS_FIND_OFFENDER_STG_DETAILS");
		final RowMapper<OffenderStgDetails> OffenderStgDetailsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderStgDetails.class, offenderStgDetailsMapping);
		final ArrayList<OffenderStgDetails> returnList = (ArrayList<OffenderStgDetails>) namedParameterJdbcTemplate
				.query(sql, createParams("OFFENDER_BOOK_ID", objSearchDao.getOffenderBookId(), "STG_SEQ",
						objSearchDao.getStgSeq()), OffenderStgDetailsRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstOffenderStgDetails
	 *            List<OffenderStgDetails>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public Integer offenderStgDetailsInsertOffenderStgDetails(final List<OffenderStgDetails> lstOffenderStgDetails) {
		final String sql = getQuery("OIDMBRDT_OFFENDERSTGDETAILS_INSERT_OFFENDER_STG_DETAILS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenderStgDetails.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstOffenderStgDetails
	 *            List<OffenderStgDetails>
	 *
	 * @throws SQLException
	 */
	public Integer offenderStgDetailsUpdateOffenderStgDetails(final List<OffenderStgDetails> lstOffenderStgDetails) {
		final String sql = getQuery("OIDMBRDT_OFFENDERSTGDETAILS_UPDATE_OFFENDER_STG_DETAILS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderStgDetails offenderStgDetails : lstOffenderStgDetails) {
			parameters.add(new BeanPropertySqlParameterSource(offenderStgDetails));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenderStgDetails.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            FormAccessibleForms
	 *
	 * @return List<FormAccessibleForms>
	 *
	 * @throws SQLException
	 */
	public List<FormAccessibleForms> formAccessibleFormsExecuteQuery(String userName) {
		final String sql = getQuery("OIDMBRDT_FORMACCESSIBLEFORMS_FIND_FORM_ACCESSIBLE_FORMS");
		final RowMapper<FormAccessibleForms> FormAccessibleFormsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				FormAccessibleForms.class, formsaccMapping);
		final ArrayList<FormAccessibleForms> returnList = (ArrayList<FormAccessibleForms>) namedParameterJdbcTemplate
				.query(sql, createParams("USER_NAME",userName), FormAccessibleFormsRowMapper);
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<SecurityThreatGroups> rgGroupRecordGroup() {
		final String sql = getQuery("OIDMBRDT_FIND_RGGROUP");
		final RowMapper<SecurityThreatGroups> mRowMapper = Row2BeanRowMapper.makeMapping(sql,
				SecurityThreatGroups.class, mMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("Exception : Oidmbrdt:", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgReasonRecordGroup() {
		final String sql = getQuery("OIDMBRDT_FIND_RGREASON");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("Exception : Oidmbrdt:", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgExpReasonRecordGroup() {
		final String sql = getQuery("OIDMBRDT_FIND_RGEXPREASON");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("Exception : Oidmbrdt:", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<SecurityThreatGroups> rgStg1RecordGroup() {
		final String sql = getQuery("OIDMBRDT_FIND_RGSTG1");
		final RowMapper<SecurityThreatGroups> mRowMapper = Row2BeanRowMapper.makeMapping(sql,
				SecurityThreatGroups.class, mMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("Exception : Oidmbrdt:", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<SecurityThreatGroups> rgStg2RecordGroup() {
		final String sql = getQuery("OIDMBRDT_FIND_RGSTG2");
		final RowMapper<SecurityThreatGroups> mRowMapper = Row2BeanRowMapper.makeMapping(sql,
				SecurityThreatGroups.class, mMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("Exception : Oidmbrdt:", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<SecurityThreatGroups> rgStg3RecordGroup() {
		final String sql = getQuery("OIDMBRDT_FIND_RGSTG3");
		final RowMapper<SecurityThreatGroups> mRowMapper = Row2BeanRowMapper.makeMapping(sql,
				SecurityThreatGroups.class, mMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("Exception : Oidmbrdt:", e);
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
	public List<OffenderStgAffiliations> offBkgOnCheckDeleteMaster(final OffenderStgAffiliations paramBean) {
		final String sql = getQuery("OIDMBRDT_OFF_BKG_ONCHECKDELETEMASTER");
		final RowMapper<OffenderStgAffiliations> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderStgAffiliations.class, offenderStgAffiliationsMapping);
		final ArrayList<OffenderStgAffiliations> returnList = (ArrayList<OffenderStgAffiliations>) namedParameterJdbcTemplate
				.query(sql, createParams(), columnRowMapper);
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
	public List<OffenderAssessments> offBkgOnCheckDeleteMaster(final OffenderAssessments paramBean) {
		final String sql = getQuery("OIDMBRDT_OFF_BKG_ONCHECKDELETEMASTER");
		final RowMapper<OffenderAssessments> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderAssessments.class, offenderAssessmentsMapping);
		final ArrayList<OffenderAssessments> returnList = (ArrayList<OffenderAssessments>) namedParameterJdbcTemplate
				.query(sql, createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offenderAssessmentsPostQuery
	 *
	 * @param params
	 *
	 */
	public Assessments offenderAssessmentsPostQueryGetDesc(final OffenderAssessments paramBean) {
		final String sql = getQuery("OIDMBRDT_OFFENDER_ASSESSMENTS_POSTQUERY_GET_DESCRIPTION");
		final RowMapper<Assessments> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Assessments.class,
				assessmentsMapping);
		final Assessments returnObj = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("ASSESSMENTTYPEID", paramBean.getAssessmentTypeId()), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offenderAssessmentsPostQuery
	 *
	 * @param params
	 *
	 */
	public StaffMembers offenderAssessmentsPostQuery(final OffenderAssessments paramBean) {
		final String sql = getQuery("OIDMBRDT_OFFENDER_ASSESSMENTS_POSTQUERY_GET_NAME");
		final RowMapper<StaffMembers> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, StaffMembers.class,
				staffMembersMapping);
		final StaffMembers returnObj = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("ASSESSORSTAFFID", paramBean.getAssessorStaffId()), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offenderStgAffiliationsPreInsert
	 *
	 * @param params
	 *
	 */
	public Integer offenderStgAffiliationsPreInsert(final OffenderStgAffiliations paramBean) {
		final String sql = getQuery("OIDMBRDT_OFFENDER_STG_AFFILIATIONS_PREINSERT");
		Integer seqMaxValue = null;
		seqMaxValue = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("P_OFFENDER_BOOK_ID", paramBean.getOffenderBookId()), Integer.class);
		return seqMaxValue;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offenderStgAffiliationsPostQuery
	 *
	 * @param params
	 *
	 */
	public SecurityThreatGroups offenderStgAffiliationsPostQuery(final OffenderStgAffiliations paramBean) {
		final String sql = getQuery("OIDMBRDT_OFFENDER_STG_AFFILIATIONS_POSTQUERY");
		final RowMapper<SecurityThreatGroups> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				SecurityThreatGroups.class, securityThreatGroupsMapping);
		final SecurityThreatGroups returnObject = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("STGID", paramBean.getStgId()), columnRowMapper);
		return returnObject;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offenderStgAffiliationsOnCheckDeleteMaster
	 *
	 * @param params
	 *
	 */
	public List<OffenderStgDetails> offenderStgAffiliationsOnCheckDeleteMaster(final OffenderStgDetails paramBean) {
		final String sql = getQuery("OIDMBRDT_OFFENDER_STG_AFFILIATIONS_ONCHECKDELETEMASTER");
		final RowMapper<OffenderStgDetails> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderStgDetails.class, offenderStgDetailsMapping);
		final ArrayList<OffenderStgDetails> returnList = (ArrayList<OffenderStgDetails>) namedParameterJdbcTemplate
				.query(sql, createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offenderStgDetailsPostQuery
	 *
	 * @param params
	 *
	 */
	public OffenderStgDetails offenderStgDetailsPostQuery(final OffenderStgDetails paramBean) {
		final String sql = getQuery("OIDMBRDT_OFFENDER_STG_DETAILS_POSTQUERY");
		OffenderStgDetails returnObj = new OffenderStgDetails();
		final RowMapper<OffenderStgDetails> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderStgDetails.class, offenderStgDetailsMapping);
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("P_OFF_BOOK_ID", paramBean.getOffenderBookId(), "P_STG_SEQ", paramBean.getStgSeq(),
							"P_ACTION", paramBean.getActionCode()),
					columnRowMapper);
		} catch (EmptyResultDataAccessException e) {
			returnObj = new OffenderStgDetails();
			return returnObj;
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * formAccessibleFormsPostQuery
	 *
	 * @param params
	 *
	 */
	public OmsModules formAccessibleFormsPostQuery(final FormAccessibleForms paramBean) {
		final String sql = getQuery("OIDMBRDT_FORM_ACCESSIBLE_FORMS_POSTQUERY");
		final RowMapper<OmsModules> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OmsModules.class,
				omsModulesMapping);
		final OmsModules returnObj = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("DESTINATIONFORM", paramBean.getDestinationForm()), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * createFormGlobals
	 *
	 * @param params
	 *
	 */
	public List<OmsModules> createFormGlobals(final OmsModules paramBean) {
		final String sql = getQuery("OIDMBRDT_CREATE_FORM_GLOBALS");
		final RowMapper<OmsModules> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OmsModules.class,
				omsModulesMapping);
		final ArrayList<OmsModules> returnList = (ArrayList<OmsModules>) namedParameterJdbcTemplate.query(sql,
				createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * populateRg
	 *
	 * @param params
	 *
	 */
	public SystemProfiles populateRg() {
		final String sql = getQuery("OIDMBRDT_POPULATE_RG");
		final RowMapper<SystemProfiles> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, SystemProfiles.class,
				systemProfilesMapping);
		SystemProfiles returnObj = new SystemProfiles();
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("Exception : Oidmbrdt:", e);
			returnObj = new SystemProfiles();
			return returnObj;
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * checkGroupInsertCHECK_GROUP_INSERT
	 *
	 * @param params
	 *
	 */
	public Integer checkGroupInsert(final OffenderStgAffiliations paramBean) {
		final String sql = getQuery("OIDMBRDT_INSERT_CHECK_GROUP_INSERT");
		Integer returnValue = null;
		try {
			returnValue = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("P_OFFENDER_BOOK_ID", paramBean.getOffenderBookId(), "P_STG_ID", paramBean.getStgId()),
					Integer.class);
		} catch (EmptyResultDataAccessException e) {
			return 0;
		}
		return returnValue;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * checkGroupFlagCHECK_GROUP_FLAG
	 *
	 * @param params
	 *
	 */
	public List<Integer> checkGroupFlag(final OffenderStgAffiliations paramBean) {
		final String sql = getQuery("OIDMBRDT_CHECK_GROUP_FLAG");
		ArrayList<Integer> returnList = new ArrayList<>();
		try {
			returnList = (ArrayList<Integer>) namedParameterJdbcTemplate.queryForList(sql,
					createParams("P_OFFENDER_BOOK_ID", paramBean.getOffenderBookId(), "P_STG_ID", paramBean.getStgId(),
							"P_STG_SEQ", paramBean.getStgSeq()),
					Integer.class);
		} catch (EmptyResultDataAccessException e) {
		}
		return returnList;
	}

	public String checkDataAvailable(final String destinationForm, final String offenderBookId,
			final String offenderId) {
		final SqlParameter[] sqlParameters = new SqlParameter[] { new SqlParameter("P_ORIG_FORM", OracleTypes.VARCHAR),
				new SqlParameter("P_DEST_FORM", OracleTypes.VARCHAR), new SqlParameter("P_BOOK_ID", OracleTypes.NUMBER),
				new SqlParameter("P_OFFENDER_ID", OracleTypes.NUMBER),
				new SqlOutParameter("P_DATA_AVAIL", OracleTypes.VARCHAR) };
		final SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("OMS_FORM_ACCESS").withProcedureName("CHECK_DATA_AVAILABLE")
				.declareParameters(sqlParameters);
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("P_ORIG_FORM", "OIDMBRDT");
		inParamMap.put("P_DEST_FORM", destinationForm);
		inParamMap.put("P_BOOK_ID", offenderBookId);
		inParamMap.put("P_OFFENDER_ID", offenderId);
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		final Map<String, Object> returnObject = simpleJDBCCall.execute(inParameter);
		if (returnObject.get("P_DATA_AVAIL") != null) {
			return returnObject.get("P_DATA_AVAIL").toString();
		}
		return null;
	}
}
