package net.syscon.s4.inst.classification.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
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
import net.syscon.s4.common.beans.CaseloadAgencyLocations;
import net.syscon.s4.common.beans.OffenderAssessments;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.StaffAccessibleCaseloads;
import net.syscon.s4.inst.classification.OcdnoqueRepository;
import net.syscon.s4.inst.classification.beans.AssessmentResults;
import net.syscon.s4.inst.classification.beans.AssessmentSupervisions;
import net.syscon.s4.inst.classification.beans.Assessments;
import net.syscon.s4.inst.classification.beans.StaffMembersV1;
import net.syscon.s4.inst.classification.beans.VOffassAss;

/**
 * Class OcdnoqueRepositoryImpl
 */
@Repository
public class OcdnoqueRepositoryImpl extends RepositoryBase implements OcdnoqueRepository {
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcdnoqueRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> vOffassAssMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CREATION_USER", new FieldMapper("creationUser"))
			.put("ASSESSMENT_SEQ", new FieldMapper("assessmentSeq"))
			.put("REVIEW_COMMITTE_CODE", new FieldMapper("reviewCommitteCode"))
			.put("ASSESSMENT_DATE", new FieldMapper("assessmentDate"))
			.put("REVIEW_PLACE_AGY_LOC_ID", new FieldMapper("reviewPlaceAgyLocId"))
			.put("REVIEW_PLACEMENT_TEXT", new FieldMapper("reviewPlacementText"))
			.put("CREATION_DATE", new FieldMapper("creationDate"))
			.put("ASSESSOR_STAFF_ID", new FieldMapper("assessorStaffId")).build();
	private final Map<String, FieldMapper> assessmentsResultsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description")).put("CODE", new FieldMapper("code")).build();
	private final Map<String, FieldMapper> assessmentsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CODE", new FieldMapper("code")).put("DESCRIPTION", new FieldMapper("description"))
			.put("LISTSEQ", new FieldMapper("listSeq"))
			.put("REQUIRE_APPROVAL_FLAG", new FieldMapper("requireApprovalFlag"))

			.build();
	private final Map<String, FieldMapper> getminandmaxScore = new ImmutableMap.Builder<String, FieldMapper>()
			.put("minScore", new FieldMapper("miniScore")).put("maxScore", new FieldMapper("maxScore"))
			.put("assessment_id", new FieldMapper("assessmentId")).build();

	private final Map<String, FieldMapper> staffMembersMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("STAFF_ID", new FieldMapper("staffId")).put("DESCRIPTION", new FieldMapper("description")).build();
	private final Map<String, FieldMapper> offenderAssessmentsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("APPROVED_SUP_LEVEL_TYPE", new FieldMapper("approvedSupLevelType"))
			.put("LAST_NAME||'", new FieldMapper(" lastName||'"))
			.put("COMMITTE_COMMENT_TEXT", new FieldMapper("committeCommentText"))
			.put("OVERRIDE_USER_ID", new FieldMapper("overrideUserId"))
			.put("OVERRIDE_COMMENT_TEXT", new FieldMapper("overrideCommentText"))
			.put("CALCSUPLEVELTYPEDESC", new FieldMapper("calcSupLevelTypeDesc"))
			.put("OVERRIDEDSUPLEVELTYPEDESC", new FieldMapper("overridedSupLevelTypeDesc"))
			.put("STAFF_ID", new FieldMapper(" staffId "))
			.put("ASSESSMENT_CREATE_LOCATION", new FieldMapper("assessmentCreateLocation"))
			.put("MAX(ASSESSMENT_DATE)", new FieldMapper(" max(assessmentDate) "))
			.put("PLACE_AGY_LOC_ID", new FieldMapper("placeAgyLocId")).put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.put("ASSESS_COMMITTE_CODE", new FieldMapper("assessCommitteCode"))
			.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("CALC_SUP_LEVEL_TYPE", new FieldMapper("calcSupLevelType"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime")).put("SCORE", new FieldMapper("score"))
			.put("ASSESS_STATUS", new FieldMapper("assessStatus"))
			.put("OVERRIDE_STAFF_ID", new FieldMapper("overrideStaffId"))
			.put("NEXT_REVIEW_DATE", new FieldMapper("nextReviewDate"))
			.put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId"))
			.put("ASSESS_COMMENT_TEXT", new FieldMapper("assessCommentText"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("OVERRIDE_REASON_TEXT", new FieldMapper("overrideReasonText"))
			.put("EVALUATION_RESULT_CODE", new FieldMapper("evaluationResultCode"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("REVIEW_SUP_LEVEL_TYPE", new FieldMapper("reviewSupLevelType"))
			.put("ASSESS_STAFF_ID", new FieldMapper("assessStaffId"))
			.put("EVALUATION_DATE", new FieldMapper("evaluationDate"))
			.put("REVIEW_SUP_LEVEL_TEXT", new FieldMapper("reviewSupLevelText"))
			.put("OVERRIDED_SUP_LEVEL_TYPE", new FieldMapper("overridedSupLevelType")).put("0", new FieldMapper("0"))
			.put("CREATION_USER", new FieldMapper("creationUser"))
			.put("REVIEW_COMMITTE_CODE", new FieldMapper("reviewCommitteCode"))
			.put("ASSESSMENT_DATE", new FieldMapper("assessmentDate"))
			.put("REVIEW_PLACE_AGY_LOC_ID", new FieldMapper("reviewPlaceAgyLocId"))
			.put("OVERRIDE_REASON", new FieldMapper("overrideReason"))
			.put("REVIEW_PLACEMENT_TEXT", new FieldMapper("reviewPlacementText"))
			.put("CREATION_DATE", new FieldMapper("creationDate")).build();
	private final Map<String, FieldMapper> staffMembersV1Mapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("NAME", new FieldMapper(" name ")).put("STAFF_ID", new FieldMapper(" staffId")).build();
	private final Map<String, FieldMapper> staffAccessibleCaseloadsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("UPDATE_ALLOWED_FLAG", new FieldMapper("updateAllowedFlag")).put("0)", new FieldMapper("0) ")).build();
	private final Map<String, FieldMapper> systemProfilesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PROFILE_VALUE", new FieldMapper(" profileValue ")).build();
	private final Map<String, FieldMapper> agencyLocationsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description")).put("CODE", new FieldMapper("code"))
			.put("LAST_NAME||'", new FieldMapper(" lastName||'")).put("'||FIRST_NAM", new FieldMapper("'||firstNam"))
			.put("PLACE_AGY_LOC_ID", new FieldMapper("placeAgyLocId")).put("USER_ID", new FieldMapper("userId"))
			.put("STAFF_ID", new FieldMapper(" staffId "))
			.put("MAX(ASSESSMENT_DATE)", new FieldMapper(" max(assessmentDate) "))
			.put("LISTSEQ", new FieldMapper("listSeq"))
			.put("ASSESSMENT_CREATE_LOCATION", new FieldMapper("assessmentCreateLocation")).build();
	private final Map<String, FieldMapper> caseloadAgencyLocationsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("UPDATE_ALLOWED_FLAG", new FieldMapper("updateAllowedFlag")).put("0)", new FieldMapper("0) ")).build();
	private final Map<String, FieldMapper> referenceCodeMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description")).put("CODE", new FieldMapper("code"))
			.put("DOMAIN", new FieldMapper("domain")).put("PARENT_DOMAIN", new FieldMapper("parentDomain"))
			.put("PARENT_CODE", new FieldMapper("parentCode")).put("ACTIVE_FLAG", new FieldMapper("activeFlag"))
			.put("SEQVALUE", new FieldMapper("seqValue")).build();

	/**
	 * Creates new OcdnoqueRepositoryImpl class Object
	 */
	public OcdnoqueRepositoryImpl() {
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao VOffassAss
	 *
	 * @return List<VOffassAss>
	 *
	 * @
	 */
	public List<VOffassAss> offAssExecuteQuery(final VOffassAss objSearchDao) {
		final String sql = getQuery("OCDNOQUE_OFFASS_FIND_V_OFFASS_ASS");
		final RowMapper<VOffassAss> VOffassAssRowMapper = Row2BeanRowMapper.makeMapping(sql, VOffassAss.class,
				vOffassAssMapping);
		String preparedSql = null;
		final MapSqlParameterSource inParameterSource = new MapSqlParameterSource();
		final StringBuffer sqlQuery = new StringBuffer();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			if (objSearchDao.getOffenderBookId() != null) {
				sqlQuery.append("  V.OFFENDER_BOOK_ID = :OFFENDER_BOOK_ID ");
				inParameterSource.addValue("OFFENDER_BOOK_ID", objSearchDao.getOffenderBookId());
			}
			if (objSearchDao.getProgramId() != null) {
				sqlQuery.append(
						" AND assessment_type_id in (select assessment_id  from assessments where assessment_id in (select pa.assessment_id   from program_assessments pa where pa.program_id = :PROGRAM_ID ) and screen_code IN ('EVAL' , 'BOTH')) ");
				inParameterSource.addValue("PROGRAM_ID", objSearchDao.getProgramId());
			}
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("WHERE")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("AND")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		preparedSql = preparedSql + " ORDER BY V.ASSESSMENT_SEQ DESC";
		return namedParameterJdbcTemplate.query(preparedSql, inParameterSource, VOffassAssRowMapper);
	}

	/**
	 * @param
	 *
	 * @
	 *
	 */
	public int preInsert() {
		return 0;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao OffenderAssessments
	 *
	 * @return List<OffenderAssessments>
	 *
	 * @
	 */
	public List<OffenderAssessments> offAss1ExecuteQuery(final OffenderAssessments objSearchDao) {
		final String sql = getQuery("OCDNOQUE_OFFASS1_FIND_OFFENDER_ASSESSMENTS");
		ArrayList<OffenderAssessments> returnList = new ArrayList<OffenderAssessments>();
		final RowMapper<OffenderAssessments> OffenderAssessmentsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderAssessments.class, offenderAssessmentsMapping);
		try {
			returnList = (ArrayList<OffenderAssessments>) namedParameterJdbcTemplate.query(sql,
					createParams("OFFENDER_BOOK_ID", objSearchDao.getOffenderBookId(), "ASSESSMENT_SEQ",
							objSearchDao.getAssessmentSeq()),
					OffenderAssessmentsRowMapper);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " offAss1ExecuteQuery: ", e);
		}
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstOffenderAssessments List<OffenderAssessments>
	 *
	 * @return List<Integer>
	 *
	 * @
	 */
	public Integer offAss1InsertOffenderAssessments(final List<OffenderAssessments> lstOffenderAssessments) {
		final String sql = getQuery("OCDNOQUE_OFFASS1_INSERT_OFFENDER_ASSESSMENTS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderAssessments offenderAssessments : lstOffenderAssessments) {
			parameters.add(new BeanPropertySqlParameterSource(offenderAssessments));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("offAss1InsertOffenderAssessments: ", e);
		}
		if (lstOffenderAssessments.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstOffenderAssessments List<OffenderAssessments>
	 *
	 * @
	 */
	public Integer offAss1UpdateOffenderAssessments(final List<OffenderAssessments> lstOffenderAssessments) {
		final String sql = getQuery("OCDNOQUE_OFFASS1_UPDATE_OFFENDER_ASSESSMENTS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderAssessments offenderAssessments : lstOffenderAssessments) {
			parameters.add(new BeanPropertySqlParameterSource(offenderAssessments));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenderAssessments.size() == returnArray.length) {
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
	public List<Assessments> rgAssessmentTypeIdRecordGroup(final BigDecimal programid, final String userID) {
		final String sql = getQuery("OCDNOQUE_FIND_RGASSESSMENTTYPEID");
		final RowMapper<Assessments> assRowMapper = Row2BeanRowMapper.makeMapping(sql, Assessments.class,
				assessmentsMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("PROGRAMID", programid, "USER_ID", userID),
					assRowMapper);
		} catch (Exception e) {
			logger.error("In method rgAssessmentTypeIdRecordGroup", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<Assessments> rgAssessmentTypeIdRecordGroupWithoutProgramid(final String userID) {
		final String sql = getQuery("OCDNOQUE_FIND_RGASSESSMENTTYPEIDWITHOUTPROGRAMID");
		final RowMapper<Assessments> assRowMapper = Row2BeanRowMapper.makeMapping(sql, Assessments.class,
				assessmentsMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("UserID", userID), assRowMapper);
		} catch (Exception e) {
			logger.error("In method rgAssessmentTypeIdRecordGroupWithoutProgramid", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgAssessCommitteCodeRecordGroup(final String userId) {
		final String sql = getQuery("OCDNOQUE_FIND_RGASSESSCOMMITTECODE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodeMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams("MODE", "ENTER-QUERY", "USERID", userId),
					mRowMapper);
		} catch (Exception e) {
			logger.error("In method rgAssessCommitteCodeRecordGroup", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<AgencyLocations> rgAgencyLocationsRecordGroup(final String userId) {
		final String sql = getQuery("OCDNOQUE_FIND_RGAGENCYLOCATIONS");
		final RowMapper<AgencyLocations> agyLocRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				agencyLocationsMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("MODE", "ENTER-QUERY", "USERID", userId),
					agyLocRowMapper);
		} catch (Exception e) {
			logger.error("In method rgAgencyLocationsRecordGroup", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<StaffMembers> rgStaffMembersRecordGroup() {
		final String sql = getQuery("OCDNOQUE_FIND_RGSTAFFMEMBERS");
		final RowMapper<StaffMembers> staffMemRowMapper = Row2BeanRowMapper.makeMapping(sql, StaffMembers.class,
				staffMembersMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams("MODE", "ENTER-QUERY"), staffMemRowMapper);
		} catch (Exception e) {
			logger.error("In method rgStaffMembersRecordGroup", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<AssessmentResults> rgOverridedSupLevelTypeRecordGroup(final Integer assessmentId) {
		final String sql = getQuery("OCDNOQUE_FIND_RGOVERRIDEDSUPLEVELTYPE");
		final RowMapper<AssessmentResults> mRowMapper = Row2BeanRowMapper.makeMapping(sql, AssessmentResults.class,
				assessmentsResultsMapping);
		try {
			return namedParameterJdbcTemplate.query(sql,
					createParams("ASSESSMENTTYPEID", assessmentId, "MODE", "ENTER-QUERY"), mRowMapper);
		} catch (Exception e) {
			logger.error("In method rgOverridedSupLevelTypeRecordGroup", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<AgencyLocations> rgPlaceAgyLocIdRecordGroup(final String userId) {
		final String sql = getQuery("OCDNOQUE_FIND_RGPLACEAGYLOCID");
		final RowMapper<AgencyLocations> mRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				agencyLocationsMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("USERID", userId), mRowMapper);
		} catch (Exception e) {
			logger.error("In method rgPlaceAgyLocIdRecordGroup", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgOverrideReasonRecordGroup() {
		final String sql = getQuery("OCDNOQUE_FIND_RGOVERRIDEREASON");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodeMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("MODE", "ENTER-QUERY"), mRowMapper);
		} catch (Exception e) {
			logger.error("In method rgOverrideReasonRecordGroup", e);
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
	public List<VOffassAss> offBkgOnCheckDeleteMaster(final VOffassAss paramBean) {
		final String sql = getQuery("OCDNOQUE_OFF_BKG_ONCHECKDELETEMASTER");
		final RowMapper<VOffassAss> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, VOffassAss.class,
				vOffassAssMapping);
		final ArrayList<VOffassAss> returnList = (ArrayList<VOffassAss>) namedParameterJdbcTemplate.query(sql,
				createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offAssPostQuery
	 *
	 * @param params
	 *
	 */
	public Integer offAssPostQuery(final OffenderAssessments paramBean) {
		final String sql = getQuery("OCDNOQUE_OFF_ASS_POSTQUERY");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("ASSESSMENTTYPEID", paramBean.getAssessmentTypeId()), Integer.class);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offAss1PostQuery
	 *
	 * @param params
	 *
	 */
	public Assessments offAss1PostQuery(final OffenderAssessments paramBean) {
		final String sql = getQuery("OCDNOQUE_OFF_ASS1_POSTQUERY");
		final RowMapper<Assessments> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Assessments.class,
				assessmentsMapping);
		final Assessments returnObj = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("ASSESSMENT_ID", paramBean.getAssessmentTypeId()), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offAss1PreInsert
	 *
	 * @param params
	 *
	 */
	public Integer offAss1PreInsert(final OffenderAssessments paramBean) {
		final String sql = getQuery("OCDNOQUE_OFF_ASS1_PREINSERT_GETSEQ");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("OFFENDERBOOKID", paramBean.getOffenderBookId()), Integer.class);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offAss1PreInsert
	 *
	 * @param params
	 *
	 */
	public StaffMembers offAss1PreInsertGetStaffId(final String userId) {
		final String sql = getQuery("OCDNOQUE_OFF_ASS1_PREINSERT_GET_ASSESS_STAFF_ID");
		final RowMapper<StaffMembers> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, StaffMembers.class,
				staffMembersMapping);
		final StaffMembers returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("USERID", userId),
				columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkAssessmentTypeId
	 *
	 * @param params
	 *
	 */
	public Assessments cgfkchkAssessmentTypeId(final Assessments paramBean) {
		final String sql = getQuery("OCDNOQUE_CGFKCHK_ASSESSMENT_TYPE_ID");
		final RowMapper<Assessments> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Assessments.class,
				assessmentsMapping);
		final Assessments returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkAssessmentTypeId1
	 *
	 * @param params
	 *
	 */
	public Assessments cgfkchkAssessmentTypeId1(final Assessments paramBean) {
		final String sql = getQuery("OCDNOQUE_CGFKCHK_ASSESSMENT_TYPE_ID1");
		final RowMapper<Assessments> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Assessments.class,
				assessmentsMapping);
		final Assessments returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkAgyLocId
	 *
	 * @param params
	 *
	 */
	public AgencyLocations cgfkchkAgyLocId(final AgencyLocations paramBean) {
		final String sql = getQuery("OCDNOQUE_CGFKCHK_AGY_LOC_ID");
		final RowMapper<AgencyLocations> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				agencyLocationsMapping);
		final AgencyLocations returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkNbtStaffId
	 *
	 * @param params
	 *
	 */
	public StaffMembers cgfkchkNbtStaffId(final StaffMembers paramBean) {
		final String sql = getQuery("OCDNOQUE_CGFKCHK_NBT_STAFF_ID");
		final RowMapper<StaffMembers> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, StaffMembers.class,
				staffMembersMapping);
		final StaffMembers returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * updatePrevass
	 *
	 * @param params
	 *
	 */
	public List<VOffassAss> updatePrevass(final VOffassAss paramBean) {
		final String sql = getQuery("OCDNOQUE_UPDATE_PREVASS");
		final RowMapper<VOffassAss> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, VOffassAss.class,
				vOffassAssMapping);
		final ArrayList<VOffassAss> returnList = (ArrayList<VOffassAss>) namedParameterJdbcTemplate.query(sql,
				createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * getStaffId
	 *
	 * @param params
	 *
	 */
	public List<StaffMembersV1> getStaffId(final StaffMembersV1 paramBean) {
		final String sql = getQuery("OCDNOQUE_GET_STAFF_ID");
		final RowMapper<StaffMembersV1> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, StaffMembersV1.class,
				staffMembersV1Mapping);
		final ArrayList<StaffMembersV1> returnList = (ArrayList<StaffMembersV1>) namedParameterJdbcTemplate.query(sql,
				createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * setCasenoteAccess
	 *
	 * @param params
	 *
	 */
	public CaseloadAgencyLocations setCasenoteAccess(final CaseloadAgencyLocations paramBean) {
		final String sql = getQuery("OCDNOQUE_SET_CASENOTE_ACCESS");
		final RowMapper<CaseloadAgencyLocations> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				CaseloadAgencyLocations.class, caseloadAgencyLocationsMapping);
		final CaseloadAgencyLocations returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * setCasenoteAccess
	 *
	 * @param params
	 *
	 */
	public StaffAccessibleCaseloads setCasenoteAccess(final StaffAccessibleCaseloads paramBean) {
		final String sql = getQuery("OCDNOQUE_SET_CASENOTE_ACCESS");
		final RowMapper<StaffAccessibleCaseloads> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				StaffAccessibleCaseloads.class, staffAccessibleCaseloadsMapping);
		final StaffAccessibleCaseloads returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * getCnoteAllupdProfile
	 *
	 * @param params
	 *
	 */
	public SystemProfiles getCnoteAllupdProfile(final SystemProfiles paramBean) {
		final String sql = getQuery("OCDNOQUE_GET_CNOTE_ALLUPD_PROFILE");
		final RowMapper<SystemProfiles> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, SystemProfiles.class,
				systemProfilesMapping);
		final SystemProfiles returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * postQueryForOffAss
	 *
	 * @param params
	 *
	 */
	public Assessments postQueryForOffAss(final VOffassAss searchRecord) {
		final String sql = getQuery("OCDNOQUE_POST_QUERY_FOR_OFF_ASS");
		final RowMapper<Assessments> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Assessments.class,
				assessmentsMapping);
		final Assessments returnObj = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("ASSESSMENTTYPEID", searchRecord.getAssessmentTypeId()), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * securityCheckNew
	 *
	 * @param params
	 *
	 */
	public CaseloadAgencyLocations securityCheckNew(final CaseloadAgencyLocations paramBean) {
		final String sql = getQuery("OCDNOQUE_SECURITY_CHECK_NEW");
		final RowMapper<CaseloadAgencyLocations> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				CaseloadAgencyLocations.class, caseloadAgencyLocationsMapping);
		final CaseloadAgencyLocations returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * securityCheckNew
	 *
	 * @param params
	 *
	 */
	public StaffAccessibleCaseloads securityCheckNew(final StaffAccessibleCaseloads paramBean) {
		final String sql = getQuery("OCDNOQUE_SECURITY_CHECK_NEW");
		final RowMapper<StaffAccessibleCaseloads> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				StaffAccessibleCaseloads.class, staffAccessibleCaseloadsMapping);
		final StaffAccessibleCaseloads returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * postQueryForOffAss1
	 *
	 * @param params
	 *
	 */
	public AgencyLocations postQueryForOffAss1(final AgencyLocations paramBean) {
		final String sql = getQuery("OCDNOQUE_POST_QUERY_FOR_OFF_ASS1");
		final RowMapper<AgencyLocations> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				agencyLocationsMapping);
		final AgencyLocations returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * postQueryForOffAss1
	 *
	 * @param params
	 *
	 */
	public StaffMembers postQueryForOffAss1(final StaffMembers paramBean) {
		final String sql = getQuery("OCDNOQUE_POST_QUERY_FOR_OFF_ASS1");
		final RowMapper<StaffMembers> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, StaffMembers.class,
				staffMembersMapping);
		final StaffMembers returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * postQueryForOffAss1
	 *
	 * @param params
	 *
	 */
	public String postQueryForOffAss1(final OffenderAssessments paramBean) {
		final String sql = getQuery("OCDNOQUE_POST_QUERY_FOR_OFF_ASS1");
		final String returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("OFFENDER_BOOK_ID",
				paramBean.getOffenderBookId(), "ASSESSMENT_SEQ", paramBean.getAssessmentSeq()), String.class);
		return returnObj;
	}

	/**
	 * * This method is used to update the data base tables based on
	 *
	 * @param lstOffenderAssessments List<OffenderAssessments>
	 *
	 * @
	 */
	public Integer offAss1PostInsertUpdateQuery(final List<OffenderAssessments> lstOffenderAssessments) {
		final String sql = getQuery("OCDNOQUE_OFFASS1_POST_INSERT_UPDATE_OFFENDER_ASSESSMENTS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderAssessments offenderAssessments : lstOffenderAssessments) {
			parameters.add(new BeanPropertySqlParameterSource(offenderAssessments));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenderAssessments.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Used to getMaxAssessmentDateCur based on addressId
	 * 
	 * @return String
	 */
	public String getMaxAssessmentDateCur(final OffenderAssessments paramBean) {
		final String sql = getQuery("OCDNOQUE_GET_MAX_ASSESSMENT_DATE_CUR");
		String returnObj = null;
		returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("OFFENDER_BOOK_ID",
				paramBean.getOffenderBookId(), "ASSESSMENT_TYPE_ID", paramBean.getAssessmentTypeId()), String.class);
		return returnObj;
	}

	@Override
	public List<AssessmentSupervisions> scoreRange() {
		final String sql = getQuery("OCDNOQUE_SCORE_RANGE");
		List<AssessmentSupervisions> returnList = new ArrayList<AssessmentSupervisions>();
		final RowMapper<AssessmentSupervisions> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				AssessmentSupervisions.class, getminandmaxScore);
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		} catch (Exception e) {
			logger.error(e);
		}
		return returnList;

	}

	@Override
	public String getWorkingCaseLoadType(String userId) {
		final String sql = getQuery("OCDNOQUE_GET_WORKING_CASELOAD_TYPE");
		String caseLoadType = null;
		try {
			caseLoadType = namedParameterJdbcTemplate.queryForObject(sql, createParams("UserID",userId), String.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+" getWorkingCaseLoadType"+e);
		}
		return caseLoadType;

	}

	@Override
	public List<ReferenceCodes> assessmentDetailsAuthority(String parentCode) {
			final String sql = getQuery("OCDNOQUE_GET_AUTHORITY");
			final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
					referenceCodeMapping);
			try {
				return namedParameterJdbcTemplate.query(sql, createParams("parentcode",parentCode),mRowMapper);
			} catch (Exception e) {
				logger.error("In method assessmentDetailsAuthority", e);
				return Collections.emptyList();
			
		}
	}

	@Override
	public List<Assessments> rgAssessmentTypeEVALRecordGroup() {
		final String sql = getQuery("OCDNOQUE_GET_EVAL_RECORDS");
		List<Assessments> returnList = new ArrayList<Assessments>();
		final RowMapper<Assessments> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Assessments.class,
				getminandmaxScore);
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		} catch (Exception e) {
			logger.error(e);
		}
		return returnList;
	}
	
	
	
	

}
