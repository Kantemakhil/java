package net.syscon.s4.inst.classification.impl;

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
import net.syscon.s4.common.beans.OffenderAssessments;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.inst.classification.OidcapprRepository;
import net.syscon.s4.inst.classification.beans.Assessments;
import net.syscon.s4.inst.classification.beans.VOffassAss;

/**
 * Class OidcapprRepositoryImpl
 */
@Repository
public class OidcapprRepositoryImpl extends RepositoryBase implements OidcapprRepository {
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidcapprRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> vOffassAssMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("APPROVED_SUP_LEVEL_TYPE", new FieldMapper("approvedSupLevelType"))
			.put("COMMITTE_COMMENT_TEXT", new FieldMapper("committeCommentText"))
			.put("OVERRIDE_COMMENT_TEXT", new FieldMapper("overrideCommentText"))
			.put("ASSESSMENT_SEQ", new FieldMapper("assessmentSeq"))
			.put("CASELOAD_TYPE", new FieldMapper("caseloadType"))
			.put("PLACE_AGY_LOC_ID", new FieldMapper("placeAgyLocId"))
			.put("ASSESS_COMMITTE_CODE", new FieldMapper("assessCommitteCode"))
			.put("CALC_SUP_LEVEL_TYPE", new FieldMapper("calcSupLevelType"))
			.put("REQUIRE_APPROVAL_FLAG", new FieldMapper("requireApprovalFlag")).put("SCORE", new FieldMapper("score"))
			.put("ASSESS_STATUS", new FieldMapper("assessStatus"))
			.put("OVERRIDE_STAFF_ID", new FieldMapper("overrideStaffId"))
			.put("NEXT_REVIEW_DATE", new FieldMapper("nextReviewDate"))
			.put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId"))
			.put("ASSESS_COMMENT_TEXT", new FieldMapper("assessCommentText"))
			.put("ASSESSMENT_TYPE_ID", new FieldMapper("assessmentTypeId"))
			.put("OVERRIDE_REASON_TEXT", new FieldMapper("overrideReasonText"))
			.put("MEDICAL_FLAG", new FieldMapper("medicalFlag"))
			.put("EVALUATION_RESULT_CODE", new FieldMapper("evaluationResultCode"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("REVIEW_SUP_LEVEL_TYPE", new FieldMapper("reviewSupLevelType"))
			.put("ASSESS_STAFF_ID", new FieldMapper("assessStaffId"))
			.put("EVALUATION_DATE", new FieldMapper("evaluationDate"))
			.put("REVIEW_SUP_LEVEL_TEXT", new FieldMapper("reviewSupLevelText"))
			.put("OVERRIDED_SUP_LEVEL_TYPE", new FieldMapper("overridedSupLevelType"))
			.put("CREATION_USER", new FieldMapper("creationUser"))
			.put("REVIEW_COMMITTE_CODE", new FieldMapper("reviewCommitteCode"))
			.put("ASSESSMENT_DATE", new FieldMapper("assessmentDate"))
			.put("REVIEW_PLACE_AGY_LOC_ID", new FieldMapper("reviewPlaceAgyLocId"))
			.put("REVIEW_PLACEMENT_TEXT", new FieldMapper("reviewPlacementText"))
			.put("CREATION_DATE", new FieldMapper("creationDate")).build();
	private final Map<String, FieldMapper> assessmentsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("LIST_SEQ", new FieldMapper("listSeq"))
			.put("DETERMINE_SUP_LEVEL_FLAG", new FieldMapper("determineSupLevelFlag"))
			.put("ACTIVE_FLAG", new FieldMapper("activeFlag")).put("DESCRIPTION", new FieldMapper("description"))
			.put("MODE", new FieldMapper("mode")).put("ASSESSMENT_TYPE_ID", new FieldMapper("assessmentTypeId"))
			.put("REQUIRE_APPROVAL_FLAG", new FieldMapper("requireApprovalFlag")).build();
	private final Map<String, FieldMapper> offenderAssessmentsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("APPROVED_SUP_LEVEL_TYPE", new FieldMapper("approvedSupLevelType"))
			.put("COMMITTE_COMMENT_TEXT", new FieldMapper("committeCommentText"))
			.put("OVERRIDE_USER_ID", new FieldMapper("overrideUserId"))
			.put("OVERRIDE_COMMENT_TEXT", new FieldMapper("overrideCommentText"))
			.put("ASSESSMENT_SEQ", new FieldMapper("assessmentSeq"))
			.put("ASSESSMENT_CREATE_LOCATION", new FieldMapper("assessmentCreateLocation"))
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
			.put("ASSESSMENT_TYPE_ID", new FieldMapper("assessmentTypeId"))
			.put("OVERRIDE_REASON_TEXT", new FieldMapper("overrideReasonText"))
			.put("EVALUATION_RESULT_CODE", new FieldMapper("evaluationResultCode"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("REVIEW_SUP_LEVEL_TYPE", new FieldMapper("reviewSupLevelType"))
			.put("ASSESSOR_STAFF_ID", new FieldMapper("assessorStaffId"))
			.put("ASSESS_STAFF_ID", new FieldMapper("assessStaffId"))
			.put("EVALUATION_DATE", new FieldMapper("evaluationDate"))
			.put("REVIEW_SUP_LEVEL_TEXT", new FieldMapper("reviewSupLevelText"))
			.put("OVERRIDED_SUP_LEVEL_TYPE", new FieldMapper("overridedSupLevelType"))
			.put("CREATION_USER", new FieldMapper("creationUser"))
			.put("REVIEW_COMMITTE_CODE", new FieldMapper("reviewCommitteCode"))
			.put("ASSESSMENT_DATE", new FieldMapper("assessmentDate"))
			.put("REVIEW_PLACE_AGY_LOC_ID", new FieldMapper("reviewPlaceAgyLocId"))
			.put("OVERRIDE_REASON", new FieldMapper("overrideReason"))
			.put("REVIEW_PLACEMENT_TEXT", new FieldMapper("reviewPlacementText"))
			.put("CREATION_DATE", new FieldMapper("creationDate")).build();
	private final Map<String, FieldMapper> referenceCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("REVIEW_SUP_LEVEL_TYPE", new FieldMapper("reviewSupLevelType"))
			.put("DOMAIN", new FieldMapper("domain")).put("ACTIVE_FLAG", new FieldMapper("activeFlag"))
			.put("NEW_COD", new FieldMapper("newCod"))
			.put("EVALUATION_RESULT_CODE", new FieldMapper("evaluationResultCode"))
			.put("ASSESS_COMMITTE_CODE", new FieldMapper("assessCommitteCode")).put("MODE", new FieldMapper("mode"))
			.put("DOMAI", new FieldMapper("domai")).put("PARENT_CODE", new FieldMapper("parentCode"))
			.put("DESCRIPTION", new FieldMapper("description")).put("NEW_CODE", new FieldMapper("newCode"))
			.put("LIST_SE", new FieldMapper("listSe")).put("DESCRIPTIO", new FieldMapper("descriptio")).build();
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CODE", new FieldMapper("code")).put("DOMAIN", new FieldMapper("domain"))
			.put("AGY_LOC_ID", new FieldMapper("agyLocId")).put("DESCRIPTION", new FieldMapper("description"))
			.put("NEW_CODE", new FieldMapper("newCode")).put("GETDESCCODE", new FieldMapper("getdesccode"))
			.put("SUPERVISION_LEVEL_TYPE", new FieldMapper("supervisionLevelType"))
			.put("SEQVALUE", new FieldMapper("seqValue")).build();
	private final Map<String, FieldMapper> agencyLocationsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("REVIEW_PLACE_AGY_LOC_ID", new FieldMapper("reviewPlaceAgyLocId"))
			.put("DESCRIPTION", new FieldMapper("description")).put("CASELOAD_TYPE", new FieldMapper("caseloadType"))
			.put("MODE", new FieldMapper("mode")).build();

	/**
	 * Creates new OidcapprRepositoryImpl class Object
	 */
	public OidcapprRepositoryImpl() {
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao VOffassAss
	 *
	 * @return List<VOffassAss>
	 *
	 */
	public List<VOffassAss> offAssExecuteQuery(final VOffassAss objSearchDao) {
		final String sql = getQuery("OIDCAPPR_OFFASS_FIND_V_OFFASS_ASS");
		final RowMapper<VOffassAss> vOffassAssMap = Row2BeanRowMapper.makeMapping(sql, VOffassAss.class,
				vOffassAssMapping);
		final ArrayList<VOffassAss> returnList = (ArrayList<VOffassAss>) namedParameterJdbcTemplate.query(sql,
				createParams("OFFENDERBOOKID", objSearchDao.getOffenderBookId()), vOffassAssMap);
		return returnList;
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
	 * Fetch the records from database table
	 *
	 * @param objSearchDao OffenderAssessments
	 *
	 * @return List<OffenderAssessments>
	 *
	 */
	public List<OffenderAssessments> offAss1ExecuteQuery(final OffenderAssessments objSearchDao) {
		final String sql = getQuery("OIDCAPPR_OFFASS1_FIND_OFFENDER_ASSESSMENTS");
		final RowMapper<OffenderAssessments> OffenderAssessmentsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderAssessments.class, offenderAssessmentsMapping);
		final ArrayList<OffenderAssessments> returnList = (ArrayList<OffenderAssessments>) namedParameterJdbcTemplate
				.query(sql, createParams("OFFENDERBOOKID", objSearchDao.getOffenderBookId(), "ASSESSMENTSEQ",
						objSearchDao.getAssessmentSeq()), OffenderAssessmentsRowMapper);
		return returnList;
	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstOffenderAssessments List<OffenderAssessments>
	 *
	 */
	public Integer offAss1UpdateOffenderAssessments(final List<OffenderAssessments> list) {
		final String sql = getQuery("OIDCAPPR_OFFASS1_UPDATE_OFFENDER_ASSESSMENTS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderAssessments offenderAssessments : list) {
			parameters.add(new BeanPropertySqlParameterSource(offenderAssessments));
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
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> cgfkOffAss1ReviewCommitteRecordGroup(final String caseLoadType) {
		final String sql = getQuery("OIDCAPPR_FIND_CGFKOFFASS1REVIEWCOMMITTE");
		List<ReferenceCodes> returnList = new ArrayList<ReferenceCodes>();
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("userName", caseLoadType), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("", e);
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<AgencyLocations> cgfkOffAss1ReviewPlaceAgyRecordGroup(final String caseLoadType) {
		final String sql = getQuery("OIDCAPPR_FIND_CGFKOFFASS1REVIEWPLACEAGY");
		List<AgencyLocations> returnList = new ArrayList<AgencyLocations>();
		final RowMapper<AgencyLocations> mRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				mMapping);

		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("userName", caseLoadType), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("", e);
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> cgfkOffAss1ReviewSupLevelRecordGroup(final Integer assTypeId) {
		final String sql = getQuery("OIDCAPPR_FIND_CGFKOFFASS1REVIEWSUPLEVEL");
		List<ReferenceCodes> returnList = new ArrayList<ReferenceCodes>();
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("ASSESSMENTTYPEID", assTypeId), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("", e);
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> cgfkOffAss1EvaluationResulRecordGroup() {
		final String sql = getQuery("OIDCAPPR_FIND_CGFKOFFASS1EVALUATIONRESUL");
		List<ReferenceCodes> returnList = new ArrayList<ReferenceCodes>();
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("", e);
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offAss1PostQuery
	 *
	 * @param params
	 *
	 */
	public Assessments offAss1PostQuery(final Assessments paramBean) {
		final String sql = getQuery("OIDCAPPR_OFF_ASS1_POSTQUERY");
		final RowMapper<Assessments> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Assessments.class,
				assessmentsMapping);
		Assessments returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(paramBean),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOffAss1OffAssEval
	 *
	 * @param params
	 *
	 */
	public ReferenceCodes cgfkchkOffAss1OffAssEval(final ReferenceCodes paramBean) {
		final String sql = getQuery("OIDCAPPR_CGFKCHK_OFF_ASS1_OFF_ASS_EVAL");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		ReferenceCodes returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(paramBean),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOffAss1OffAssR2
	 *
	 * @param params
	 *
	 */
	public ReferenceCodes cgfkchkOffAss1OffAssR2(final ReferenceCodes paramBean) {
		final String sql = getQuery("OIDCAPPR_CGFKCHK_OFF_ASS1_OFF_ASS_R2");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		ReferenceCodes returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(paramBean),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOffAss1OffAssAgy
	 *
	 * @param params
	 *
	 */
	public AgencyLocations cgfkchkOffAss1OffAssAgy(final AgencyLocations paramBean) {
		final String sql = getQuery("OIDCAPPR_CGFKCHK_OFF_ASS1_OFF_ASS_AGY");
		final RowMapper<AgencyLocations> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				agencyLocationsMapping);
		AgencyLocations returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(paramBean),
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
	public Assessments postQueryForOffAss(final Assessments paramBean) {
		final String sql = getQuery("OIDCAPPR_POST_QUERY_FOR_OFF_ASS");
		final RowMapper<Assessments> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Assessments.class,
				assessmentsMapping);
		Assessments returnObj = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("ASSESSMENTTYPEID", paramBean.getAssessmentId()), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * getComSupDef
	 *
	 * @param params
	 *
	 */
	public ReferenceCodes getComSupDef(final ReferenceCodes paramBean) {
		final String sql = getQuery("OIDCAPPR_GET_COM_SUP_DEF");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		ReferenceCodes returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(paramBean),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * getInstSupDef
	 *
	 * @param params
	 *
	 */
	public ReferenceCodes getInstSupDef(final ReferenceCodes paramBean) {
		final String sql = getQuery("OIDCAPPR_GET_INST_SUP_DEF");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		ReferenceCodes returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(paramBean),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * getRefCodeDesc
	 *
	 * @param params
	 *
	 */
	public ReferenceCodes getRefCodeDesc(final ReferenceCodes paramBean) {
		final String sql = getQuery("OIDCAPPR_GET_REF_CODE_DESC");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		ReferenceCodes returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(paramBean),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOffAssOffAssAss
	 *
	 * @param params
	 *
	 */
	public Assessments cgfkchkOffAssOffAssAss(final Assessments paramBean) {
		final String sql = getQuery("OIDCAPPR_CGFKCHK_OFF_ASS_OFF_ASS_ASS_F");
		final RowMapper<Assessments> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Assessments.class,
				assessmentsMapping);
		Assessments returnObj = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("ASSESSMENTTYPEID", paramBean.getAssessmentId()), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOffAssOffAssRef
	 *
	 * @param params
	 *
	 */
	public ReferenceCodes cgfkchkOffAssOffAssRef(final ReferenceCodes paramBean) {
		final String sql = getQuery("OIDCAPPR_CGFKCHK_OFF_ASS_OFF_ASS_REF_A");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		ReferenceCodes returnObj = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("ASSESSCOMMITTECODE", paramBean.getCode()), columnRowMapper);
		return returnObj;
	}
	
	@Override
	public List<Assessments> getAssessments(OffenderAssessments paramBean) {
		final String sql = getQuery("OIDCAPPR_GET_ASSESSMENTS");
		final RowMapper<Assessments> assessmentsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				Assessments.class, assessmentsMapping);
		final ArrayList<Assessments> returnList = (ArrayList<Assessments>) namedParameterJdbcTemplate
				.query(sql, createParams("offenderBookId", paramBean.getOffenderBookId(), "assessmentSeq",
						paramBean.getAssessmentSeq()), assessmentsRowMapper);
		return returnList;
	}
}
