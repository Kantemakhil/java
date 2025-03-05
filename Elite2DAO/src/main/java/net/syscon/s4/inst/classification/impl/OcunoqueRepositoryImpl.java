package net.syscon.s4.inst.classification.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.beans.AssessSectionNotifications;
import net.syscon.s4.common.beans.OffenderAssessments;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.inst.casemanagement.beans.OffenderCriminogenicNeeds;
import net.syscon.s4.inst.classification.OcunoqueRepository;
import net.syscon.s4.inst.classification.beans.AssessmentSupervisions;
import net.syscon.s4.inst.classification.beans.Assessments;
import net.syscon.s4.inst.classification.beans.OffenderAssessmentItems;
import net.syscon.s4.inst.classification.beans.VAssOffNeeds;
import net.syscon.s4.inst.classification.beans.VAssTreatProts;
import net.syscon.s4.inst.classification.beans.VOffassAss;

/**
 * Class OcunoqueRepositoryImpl
 */
@Repository
public class OcunoqueRepositoryImpl extends RepositoryBase implements OcunoqueRepository {

	/**
	 * Creates new OcunoqueRepositoryImpl class Object
	 */
	public OcunoqueRepositoryImpl() {
	}

	private final Map<String, FieldMapper> Mapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("ASSESSMENT_SEQ", new FieldMapper("assessmentSeq")).put("MAX_SCOR", new FieldMapper("maxScor"))
			.put("'X'", new FieldMapper(" 'x' "))
			.put("SUPERVISION_LEVEL_TYPE", new FieldMapper(" supervisionLevelType")).build();
	private final Map<String, FieldMapper> internalStatusReasonsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("ASSESSMENT_CODE", new FieldMapper("assessmentCode"))
			.put("INT_STS_REASON_CODE", new FieldMapper(" intStsReasonCode ")).build();
	private final Map<String, FieldMapper> assessmentsMappings = new ImmutableMap.Builder<String, FieldMapper>()
			.put("'X'", new FieldMapper(" 'x' ")).put("PARENT_ASSESSMENT_ID", new FieldMapper("parentAssessmentId"))
			.build();
	private final Map<String, FieldMapper> assessmentsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("MEDICAL_FLAG", new FieldMapper("medicalFlag"))
			.put("SECT_SCORE_OVERRIDE_FLAG", new FieldMapper("sectScoreOverrideFlag"))
			.put("COMMENT_TEXT", new FieldMapper(" commentText")).put("ACTIVE_FLAG", new FieldMapper("activeFlag"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId")).put("MIN_SCORE", new FieldMapper(" minScore"))
			.put("RANK", new FieldMapper(" rank "))
			.put("SCHEDULE_COMPLETE_DAYS", new FieldMapper("scheduleCompleteDays"))
			.put("PARENT_ASSESSMENT_ID", new FieldMapper("parentAssessmentId"))
			.put("OVERRIDEABLE_FLAG", new FieldMapper("overrideableFlag"))
			.put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId"))
			.put("PREV_ASSESS1_ID", new FieldMapper("prevAssess1Id"))
			.put("NEXT_ASSESSMENT_ID", new FieldMapper("nextAssessmentId"))
			.put("REQUIRE_APPROVAL_FLAG", new FieldMapper(" requireApprovalFlag "))
			.put("SCREEN_CODE", new FieldMapper("screenCode")).put("ASSESSMENT_SEQ", new FieldMapper("assessmentSeq"))
			.put("REVIEW_CYCLE_DAYS", new FieldMapper("reviewCycleDays")).put("MAX_SCOR", new FieldMapper("maxScor"))
			.put("MAX_SCORE", new FieldMapper(" maxScore"))
			.put("CALCULATE_TOTAL_FLAG", new FieldMapper("calculateTotalFlag"))
			.put("CREATE_DATE", new FieldMapper("createDate")).put("BOOKMARK_NAME", new FieldMapper("ansBookMark"))
			.build();
	private final Map<String, FieldMapper> vOffassAssMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("REVIEW_SUP_LEVEL_TYP", new FieldMapper("reviewSupLevelTyp"))
			.put("ASSESSMENT_SEQ", new FieldMapper("assessmentSeq"))
			.put("CASELOAD_TYPE", new FieldMapper("caseloadType")).put("HPQC#24907", new FieldMapper("hpqc#24907"))
			.put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId"))
			.put("REQUIRE_APPROVAL_FLA", new FieldMapper("requireApprovalFla")).build();
	private final Map<String, FieldMapper> offenderAssessmentsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("COMMENT_TEXT", new FieldMapper(" commentText")).put("SCORE", new FieldMapper(" score "))
			.put("ASSESSMENT_SEQ", new FieldMapper("assessmentSeq")).put("ITEM_SEQ", new FieldMapper(" itemSeq"))
//.put("ASSESS_COMMENT_TEXT", 						new FieldMapper(" assessCommentText "))
			.put("'Y'", new FieldMapper("  'y'")).put("RANK", new FieldMapper(" rank "))
			.put("ASSESSMENT_ID", new FieldMapper("assessmentId"))
			.put("PARENT_ASSESSMENT_ID", new FieldMapper("parentAssessmentId"))
			.put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId")).build();
	private final Map<String, FieldMapper> offenderAssessmentItemsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("NEXT_ASSESSMENT_ID", new FieldMapper("nextAssessmentId"))
			.put("REQUIRE_APPROVAL_FLAG", new FieldMapper(" requireApprovalFlag "))
			.put("ASSESSMENT_SEQ", new FieldMapper("assessmentSeq")).put("MAX_SCOR", new FieldMapper("maxScor"))
			.put("'X'", new FieldMapper(" 'x' ")).put("MAX_SCORE", new FieldMapper(" maxScore"))
			.put("SECT_SCORE_OVERRIDE_FLAG", new FieldMapper(" sectScoreOverrideFlag ")).build();
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CODE", new FieldMapper("code")).build();
	private final Map<String, FieldMapper> assessmentSupervisionsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("REQUIRE_APPROVAL_FLAG", new FieldMapper(" requireApprovalFlag "))
			.put("ASSESSMENT_SEQ", new FieldMapper("assessmentSeq")).put("MAX_SCOR", new FieldMapper("maxScor"))
			.put("'X'", new FieldMapper(" 'x' "))
			.put("SECT_SCORE_OVERRIDE_FLAG", new FieldMapper(" sectScoreOverrideFlag ")).build();
	private final Map<String, FieldMapper> assessSectionNotificationsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("NEXT_ASSESSMENT_ID", new FieldMapper("nextAssessmentId"))
			.put("REQUIRE_APPROVAL_FLAG", new FieldMapper(" requireApprovalFlag "))
			.put("ASSESSMENT_SEQ", new FieldMapper("assessmentSeq")).put("MAX_SCOR", new FieldMapper("maxScor"))
			.put("'X'", new FieldMapper(" 'x' ")).put("MAX_SCORE", new FieldMapper(" maxScore"))
			.put("SECT_SCORE_OVERRIDE_FLAG", new FieldMapper(" sectScoreOverrideFlag ")).build();
	
	private final Map<String, FieldMapper> notifctnsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("SEAL_FLAG", 						new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", 						new FieldMapper("createDatetime"))
			.put("MODIFY_DATETIME", 						new FieldMapper("modifyDatetime"))
			.put("SCORE_SEQ", 						new FieldMapper("scoreSeq"))
			.put("EXPIRY_DATE", 						new FieldMapper("expiryDate"))
			.put("ASSESSMENT_ID", 						new FieldMapper("assessmentId"))
			.put("NEXT_ASSESSMENT_ID", 						new FieldMapper("nextAssessmentId"))
			.put("ASS_CODE_TEMP", 						new FieldMapper("assCodeTemp"))
			.put("wait_list_count", 						new FieldMapper("waitListCount"))
			.build();

	private static Logger logger = LogManager.getLogger(OcunoqueRepositoryImpl.class.getName());

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao Assessments
	 *
	 * @return List<Assessments>
	 *
	 * @throws SQLException
	 */
	public List<Assessments> assessExecuteQuery(final Assessments objSearchDao) {
		final String sql = getQuery("OCUNOQUE_ASSESS_FIND_ASSESSMENTS");
		final RowMapper<Assessments> AssessmentsRowMapper = Row2BeanRowMapper.makeMapping(sql, Assessments.class,
				assessmentsMapping);
		final ArrayList<Assessments> returnList = (ArrayList<Assessments>) namedParameterJdbcTemplate.query(sql,
				createParams("assessmentId", objSearchDao.getAssessmentId()), AssessmentsRowMapper);
		return returnList;
	}

	public List<Assessments> assessQuestionsExecuteQuery(final Assessments objSearchDao) {
		final String sql = getQuery("OCUNOQUE_ASSESS_FIND_ASSESSMENTS_QUESTIONS");
		final RowMapper<Assessments> AssessmentsRowMapper = Row2BeanRowMapper.makeMapping(sql, Assessments.class,
				assessmentsMapping);
		final ArrayList<Assessments> returnList = (ArrayList<Assessments>) namedParameterJdbcTemplate.query(sql,
				createParams("assessmentId", objSearchDao.getAssessmentId()), AssessmentsRowMapper);
		return returnList;
	}

	public List<Assessments> answersExecuteQuery(final Assessments objSearchDao) {
		final String sql = getQuery("OCUNOQUE_ASSESS_FIND_ASSESSMENTS_ANSWERS");
		final RowMapper<Assessments> AssessmentsRowMapper = Row2BeanRowMapper.makeMapping(sql, Assessments.class,
				assessmentsMapping);
		final ArrayList<Assessments> returnList = (ArrayList<Assessments>) namedParameterJdbcTemplate.query(sql,
				createParams("assessmentId", objSearchDao.getAssessmentId()), AssessmentsRowMapper);
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgRankRecordGroup() {
		final String sql = getQuery("OCUNOQUE_FIND_RGRANK");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * assessOnCheckDeleteMaster
	 *
	 * @param params
	 *
	 */
	public List<Assessments> assessOnCheckDeleteMaster(final Assessments paramBean) {
		final String sql = getQuery("OCUNOQUE_ASSESS_ONCHECKDELETEMASTER");
		final RowMapper<Assessments> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Assessments.class,
				assessmentsMapping);
		final ArrayList<Assessments> returnList = (ArrayList<Assessments>) namedParameterJdbcTemplate.query(sql,
				createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * assess1PostQuery
	 *
	 * @param params
	 *
	 */
	public OffenderAssessmentItems assess1PostQuery(final OffenderAssessmentItems paramBean) {
		final String sql = getQuery("OCUNOQUE_ASSESS1_POSTQUERY");
		final RowMapper<OffenderAssessmentItems> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderAssessmentItems.class, offenderAssessmentItemsMapping);
		OffenderAssessmentItems returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * assess1OnCheckDeleteMaster
	 *
	 * @param params
	 *
	 */
	public List<Assessments> assess1OnCheckDeleteMaster(final Assessments paramBean) {
		final String sql = getQuery("OCUNOQUE_ASSESS1_ONCHECKDELETEMASTER");
		final RowMapper<Assessments> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Assessments.class,
				assessmentsMapping);
		final ArrayList<Assessments> returnList = (ArrayList<Assessments>) namedParameterJdbcTemplate.query(sql,
				createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * assess1WhenNewRecordInstance
	 *
	 * @param params
	 *
	 */
	public OffenderAssessmentItems assess1WhenNewRecordInstance(final OffenderAssessmentItems paramBean) {
		final String sql = getQuery("OCUNOQUE_ASSESS1_WHENNEWRECORDINSTANCE");
		final RowMapper<OffenderAssessmentItems> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderAssessmentItems.class, offenderAssessmentItemsMapping);
		OffenderAssessmentItems returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * rankSupLevel
	 *
	 * @param params
	 *
	 */
	public AssessmentSupervisions rankSupLevel(final AssessmentSupervisions paramBean) {
		final String sql = getQuery("OCUNOQUE_RANK_SUP_LEVEL");
		final RowMapper<AssessmentSupervisions> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				AssessmentSupervisions.class, assessmentSupervisionsMapping);
		AssessmentSupervisions returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * getCommentText
	 *
	 * @param params
	 *
	 */
	public OffenderAssessments getCommentText(final OffenderAssessments paramBean) {
		final String sql = getQuery("OCUNOQUE_GET_COMMENT_TEXT");
		OffenderAssessments returnObj = new OffenderAssessments();
		final RowMapper<OffenderAssessments> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderAssessments.class, offenderAssessmentsMapping);
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderBookId",
					paramBean.getOffenderBookId(), "assessmentSeq", paramBean.getAssessmentSeq()), columnRowMapper);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * checkForUserAnswer
	 *
	 * @param params
	 *
	 */
	public OffenderAssessmentItems checkForUserAnswer(final OffenderAssessmentItems paramBean) {
		final String sql = getQuery("OCUNOQUE_CHECK_FOR_USER_ANSWER");
		final RowMapper<OffenderAssessmentItems> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderAssessmentItems.class, offenderAssessmentItemsMapping);
		OffenderAssessmentItems returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * setRowLock
	 *
	 * @param params
	 *
	 */
	public OffenderAssessmentItems setRowLock(final OffenderAssessmentItems paramBean) {
		final String sql = getQuery("OCUNOQUE_SET_ROW_LOCK");
		final RowMapper<OffenderAssessmentItems> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderAssessmentItems.class, offenderAssessmentItemsMapping);
		OffenderAssessmentItems returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * setRowLock
	 *
	 * @param params
	 *
	 */
	public OffenderAssessments setRowLock(final OffenderAssessments paramBean) {
		final String sql = getQuery("OCUNOQUE_SET_ROW_LOCK");
		final RowMapper<OffenderAssessments> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderAssessments.class, offenderAssessmentsMapping);
		OffenderAssessments returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * addRemoveAnswer
	 *
	 * @param params
	 *
	 */
	public Long addRemoveAnswerGetItemSeqCur(final OffenderAssessments paramBean) {
		Long nextItemSeq = null;
		final String sql = getQuery("OCUNOQUE_ADD_REMOVE_ANSWER_GET_NEXT_ITEM_SEQ_CUR");
		nextItemSeq = namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderBookId",
				paramBean.getOffenderBookId(), "asessmentSeq", paramBean.getAssessmentSeq()), Long.class);
		return nextItemSeq;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * addRemoveAnswerget_next_item_seq_cur
	 *
	 * @param params
	 *
	 */
	public VOffassAss inactivatePrviousAssessment(final OffenderAssessments paramBean, final String userId) {
		VOffassAss returnList = new VOffassAss();
		final String sql = getQuery("OCUNOQUE_INACTIVATE_PRVIOUS_ASSESSMENT");
		final RowMapper<VOffassAss> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, VOffassAss.class,
				vOffassAssMapping);
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderBookId",
					paramBean.getOffenderBookId(), "assessmentSeq", paramBean.getAssessmentSeq(), "USERID", userId),
					columnRowMapper);
		} catch (Exception e) {
			return returnList;
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * initiateSaveProcess
	 *
	 * @param params
	 *
	 */
	public OffenderAssessmentItems initiateSaveProcess(OffenderAssessmentItems paramBean) {
		final String sql = getQuery("OCUNOQUE_INITIATE_SAVE_PROCESS");
		final RowMapper<OffenderAssessmentItems> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderAssessmentItems.class, offenderAssessmentItemsMapping);
		OffenderAssessmentItems returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * saveAssessmentItems
	 *
	 * @param params
	 *
	 */
	public AssessmentSupervisions getAssessmentScore(final OffenderAssessments paramBean) {
		final String sql = getQuery("OCUNOQUE_SAVE_ASSESSMENT_ITEMS");
		AssessmentSupervisions returnObj = new AssessmentSupervisions();
		final RowMapper<AssessmentSupervisions> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				AssessmentSupervisions.class, offenderAssessmentItemsMapping);
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderBookId",
					paramBean.getOffenderBookId(), "assessmentSeq", paramBean.getAssessmentSeq()), columnRowMapper);
		} catch (Exception e) {
			logger.error("getAssessmentScore: ", e);
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * saveAssessmentItems
	 *
	 * @param params
	 *
	 */
	public AssessmentSupervisions getAssessmentSupervisionLevel(final AssessmentSupervisions paramBean) {
		final String sql = getQuery("OCUNOQUE_SAVE_ASSESSMENT_ITEMS_SUPERVISION_LEVEL_TYPE");
		final RowMapper<AssessmentSupervisions> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				AssessmentSupervisions.class, assessmentSupervisionsMapping);
		AssessmentSupervisions returnObj = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("assessmentId", paramBean.getAssessmentId(), "assessScore", paramBean.getScore()),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * saveAssessmentItems
	 *
	 * @param params
	 *
	 */
	public AssessmentSupervisions saveAssessmentItemsGetMaxScore(final OffenderAssessments paramBean) {
		final String sql = getQuery("OCUNOQUE_SAVE_ASSESSMENT_ITEMS_NEW_GET_MAX_SCORE");
		final RowMapper<AssessmentSupervisions> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				AssessmentSupervisions.class, assessmentSupervisionsMapping);
		AssessmentSupervisions returnObj = new AssessmentSupervisions();
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("assessTypeId", paramBean.getAssessmentTypeId()), columnRowMapper);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * saveAssessmentItemsget_current_total_score_cur
	 *
	 * @param params
	 *
	 */
	public AssessmentSupervisions saveAssessmentItemsGetMinScore(final OffenderAssessments paramBean) {
		final String sql = getQuery("OCUNOQUE_SAVE_ASSESSMENT_ITEMS_NEW_GET_MIN_SCORE");
		final RowMapper<AssessmentSupervisions> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				AssessmentSupervisions.class, assessmentSupervisionsMapping);
		AssessmentSupervisions returnObj = new AssessmentSupervisions();
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("assessTypeId", paramBean.getAssessmentTypeId()), columnRowMapper);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * saveAssessmentItemsget_current_total_score_cur
	 *
	 * @param params
	 *
	 */
	public AssessmentSupervisions saveAssessmentItemsGetSuperLevelType(final OffenderAssessments paramBean) {
		final String sql = getQuery("OCUNOQUE_SAVE_ASSESSMENT_ITEMS_GET_SUPERLEVELTYPE");
		AssessmentSupervisions returnList = new AssessmentSupervisions();
		final RowMapper<AssessmentSupervisions> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				AssessmentSupervisions.class, assessmentSupervisionsMapping);
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("assessTypeId", paramBean.getAssessmentTypeId(), "totalScore", paramBean.getScore()),
					columnRowMapper);
		} catch (Exception e) {
			return returnList;
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * saveAssessmentItems
	 *
	 * @param params
	 *
	 */
	public Assessments saveAssessmentItems(final Assessments paramBean) {
		final String sql = getQuery("OCUNOQUE_SAVE_ASSESSMENT_ITEMS");
		final RowMapper<Assessments> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Assessments.class,
				assessmentsMapping);
		Assessments returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * calculateRankScore
	 *
	 * @param params
	 *
	 */
	public OffenderAssessmentItems calculateRankScore(final OffenderAssessmentItems paramBean) {
		final String sql = getQuery("OCUNOQUE_CALCULATE_RANK_SCORE");
		final RowMapper<OffenderAssessmentItems> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderAssessmentItems.class, offenderAssessmentItemsMapping);
		OffenderAssessmentItems returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * calculateRankScoreFromWvr
	 *
	 * @param params
	 *
	 */
	public OffenderAssessmentItems calculateRankScoreFromWvr(final OffenderAssessmentItems paramBean) {
		final String sql = getQuery("OCUNOQUE_CALCULATE_RANK_SCORE_FROM_WVR");
		final RowMapper<OffenderAssessmentItems> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderAssessmentItems.class, offenderAssessmentItemsMapping);
		OffenderAssessmentItems returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * calculateRankScoreFromWvritem_seq_cur
	 *
	 * @param params
	 *
	 */
	public OffenderAssessmentItems getRankScoreFromListval(final OffenderAssessmentItems paramBean) {
		final String sql = getQuery("OCUNOQUE_GET_RANK_SCORE_FROM_LISTVAL");
		final RowMapper<OffenderAssessmentItems> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderAssessmentItems.class, offenderAssessmentItemsMapping);
		OffenderAssessmentItems returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * getRankScoreFromListvalitem_seq_cur
	 *
	 * @param params
	 *
	 */
	public OffenderAssessmentItems checkRankAssessments(final OffenderAssessmentItems paramBean) {
		final String sql = getQuery("OCUNOQUE_CHECK_RANK_ASSESSMENTS");
		final RowMapper<OffenderAssessmentItems> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderAssessmentItems.class, offenderAssessmentItemsMapping);
		OffenderAssessmentItems returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * checkRankAssesFromSections
	 *
	 * @param params
	 *
	 */
	public OffenderAssessmentItems checkRankAssesFromSections(final OffenderAssessmentItems paramBean) {
		final String sql = getQuery("OCUNOQUE_CHECK_RANK_ASSES_FROM_SECTIONS");
		final RowMapper<OffenderAssessmentItems> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderAssessmentItems.class, offenderAssessmentItemsMapping);
		OffenderAssessmentItems returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * chkRankAssesFromQuestions
	 *
	 * @param params
	 *
	 */
	public OffenderAssessmentItems chkRankAssesFromQuestions(final OffenderAssessmentItems paramBean) {
		final String sql = getQuery("OCUNOQUE_CHK_RANK_ASSES_FROM_QUESTIONS");
		final RowMapper<OffenderAssessmentItems> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderAssessmentItems.class, offenderAssessmentItemsMapping);
		OffenderAssessmentItems returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * chkRankQuesFromDifBlk
	 *
	 * @param params
	 *
	 */
	public OffenderAssessmentItems chkRankQuesFromDifBlk(final OffenderAssessmentItems paramBean) {
		final String sql = getQuery("OCUNOQUE_CHK_RANK_QUES_FROM_DIF_BLK");
		final RowMapper<OffenderAssessmentItems> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderAssessmentItems.class, offenderAssessmentItemsMapping);
		OffenderAssessmentItems returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * saveAssessmentItemsNew
	 *
	 * @param params
	 *
	 */
	public OffenderAssessmentItems saveAssessmentItemsNew(final OffenderAssessmentItems paramBean) {
		final String sql = getQuery("OCUNOQUE_SAVE_ASSESSMENT_ITEMS_NEW_GET_CURRENT_TOTAL_SCORE_CUR");
		final RowMapper<OffenderAssessmentItems> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderAssessmentItems.class, offenderAssessmentItemsMapping);
		OffenderAssessmentItems returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * saveAssessmentItemsNew
	 *
	 * @param params
	 *
	 */
	public AssessmentSupervisions saveAssessmentItemsNew(final AssessmentSupervisions paramBean) {
		final String sql = getQuery("OCUNOQUE_SAVE_ASSESSMENT_ITEMS_NEW");
		final RowMapper<AssessmentSupervisions> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				AssessmentSupervisions.class, assessmentSupervisionsMapping);
		AssessmentSupervisions returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * saveAssessmentItemsNewget_current_total_score_cur
	 *
	 * @param params
	 *
	 */
	public OffenderAssessmentItems saveAssessmentItemsGetCurrentTotalScore(final OffenderAssessments paramBean) {
		final String sql = getQuery("OCUNOQUE_SAVE_ASSESSMENT_ITEMS_NEW_GET_CURRENT_TOTAL_SCORE_CUR");
		final RowMapper<OffenderAssessmentItems> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderAssessmentItems.class, assessmentSupervisionsMapping);
		OffenderAssessmentItems returnObj = new OffenderAssessmentItems();
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("offenderBookId", paramBean.getOffenderBookId(), "assSeq",
							paramBean.getAssessmentSeq(), "assessmentTypeId", paramBean.getAssessmentTypeId()),
					columnRowMapper);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * saveAssessmentItemsNew
	 *
	 * @param params
	 *
	 */
	public Assessments saveAssessmentItemsNew(final Assessments paramBean) {
		final String sql = getQuery("OCUNOQUE_SAVE_ASSESSMENT_ITEMS_NEW");
		final RowMapper<Assessments> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Assessments.class,
				assessmentsMapping);
		Assessments returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * saveProcessFromSections
	 *
	 * @param params
	 *
	 */
	public OffenderAssessmentItems saveProcessFromSections(final OffenderAssessmentItems paramBean) {
		final String sql = getQuery("OCUNOQUE_SAVE_PROCESS_FROM_SECTIONS");
		final RowMapper<OffenderAssessmentItems> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderAssessmentItems.class, offenderAssessmentItemsMapping);
		OffenderAssessmentItems returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * overrideFlagRule
	 *
	 * @param params
	 *
	 */
	public OffenderAssessmentItems overrideFlagRule(final OffenderAssessmentItems paramBean) {
		final String sql = getQuery("OCUNOQUE_OVERRIDE_FLAG_RULE");
		final RowMapper<OffenderAssessmentItems> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderAssessmentItems.class, offenderAssessmentItemsMapping);
		OffenderAssessmentItems returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * overrideFlagRule
	 *
	 * @param params
	 *
	 */
	public AssessmentSupervisions overrideFlagRule(final AssessmentSupervisions paramBean) {
		final String sql = getQuery("OCUNOQUE_OVERRIDE_FLAG_RULE");
		final RowMapper<AssessmentSupervisions> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				AssessmentSupervisions.class, assessmentSupervisionsMapping);
		AssessmentSupervisions returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * overrideFlagRule
	 *
	 * @param params
	 *
	 */
	public Assessments overrideFlagRule(final Assessments paramBean) {
		final String sql = getQuery("OCUNOQUE_OVERRIDE_FLAG_RULE");
		final RowMapper<Assessments> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Assessments.class,
				assessmentsMapping);
		Assessments returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * skipSectionMsg
	 *
	 * @param params
	 *
	 */
	public OffenderAssessmentItems skipSectionMsg(final OffenderAssessmentItems paramBean) {
		final String sql = getQuery("OCUNOQUE_SKIP_SECTION_MSG");
		final RowMapper<OffenderAssessmentItems> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderAssessmentItems.class, offenderAssessmentItemsMapping);
		OffenderAssessmentItems returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * skipSectionTotal
	 *
	 * @param params
	 *
	 */
	public OffenderAssessmentItems skipSectionTotal(final OffenderAssessmentItems paramBean) {
		final String sql = getQuery("OCUNOQUE_SKIP_SECTION_TOTAL");
		final RowMapper<OffenderAssessmentItems> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderAssessmentItems.class, offenderAssessmentItemsMapping);
		OffenderAssessmentItems returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * checkForAllAnswers
	 *
	 * @param params
	 *
	 */
	public List<Assessments> checkForAllAnswers(final Assessments paramBean) {
		final String sql = getQuery("OCUNOQUE_CHECK_FOR_ALL_ANSWERS");
		final RowMapper<Assessments> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Assessments.class,
				assessmentsMapping);
		final ArrayList<Assessments> returnList = (ArrayList<Assessments>) namedParameterJdbcTemplate.query(sql,
				createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * checkForAllAnswers
	 *
	 * @param params
	 *
	 */
	public OffenderAssessmentItems checkForAllAnswers(final OffenderAssessmentItems paramBean) {
		final String sql = getQuery("OCUNOQUE_CHECK_FOR_ALL_ANSWERS");
		final RowMapper<OffenderAssessmentItems> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderAssessmentItems.class, offenderAssessmentItemsMapping);
		OffenderAssessmentItems returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * initiateSaveProcessgetanscountcur
	 *
	 * @param params
	 *
	 */
	public Integer initiateSaveProcessgetanscountcur(final OffenderAssessments paramBean) {
		final String sql = getQuery("OCUNOQUE_INITIATESAVEPROCESS_GET_ANS_COUNT_CUR");
		Integer returnList = null;
		returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderBookId",
				paramBean.getOffenderBookId(), "assessmentSeq", paramBean.getAssessmentSeq()), Integer.class);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * checkForUserAnswergetanscur
	 *
	 * @param params
	 *
	 */
	public OffenderAssessmentItems checkForUserAnswergetanscur(final OffenderAssessmentItems paramBean) {
		final String sql = getQuery("OCUNOQUE_CHECK_FOR_USER_ANSWER");
		OffenderAssessmentItems returnObj = new OffenderAssessmentItems();
		final RowMapper<OffenderAssessmentItems> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderAssessmentItems.class, offenderAssessmentItemsMapping);
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("offenderBookId", paramBean.getOffenderBookId(), "assessmentSeq",
							paramBean.getAssessmentSeq(), "assessmentId", paramBean.getAssessmentId(),
							"parentAssessmentId", paramBean.getParentAssessmentId()),
					columnRowMapper);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * getAnsIfExistsCur
	 *
	 * @param params
	 *
	 */
	public OffenderAssessmentItems getAnsIfExistsCur(final OffenderAssessmentItems paramBean) {
		final String sql = getQuery("GET_ANS_IF_EXISTS_CUR");
		OffenderAssessmentItems returnObj = new OffenderAssessmentItems();
		final RowMapper<OffenderAssessmentItems> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderAssessmentItems.class, offenderAssessmentItemsMapping);
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("offenderBookId", paramBean.getOffenderBookId(), "assessmentSeq",
							paramBean.getAssessmentSeq(), "assessmentId", paramBean.getAssessmentId(), "itemSeq",
							paramBean.getItemSeq()),
					columnRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return returnObj;
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * deleteOffenderAssessmentItems
	 *
	 * @param params
	 *
	 */
	@Override
	public Integer deleteOffenderAssessmentItems(final Assessments answersObj) {
		List<Assessments> listAnswers = new ArrayList<>();
		listAnswers.add(answersObj);
		final String sql = getQuery("OCUNOQUE_REMOVE_ANSWER");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final Assessments list : listAnswers) {
			parameters.add(new BeanPropertySqlParameterSource(list));
		}
		try {
			String tableName = "OFFENDER_ASSESSMENT_ITEMS";
			String whereClause = "OFFENDER_BOOK_ID = :offenderBookId AND ASSESSMENT_SEQ = :assessmentSeq AND ASSESSMENT_ID = :assessmentId AND PARENT_ASSESSMENT_ID = :parentAssessmentId";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteOffenderAssessmentItems", e);
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("deleteOffenderAssessmentItems: ", e);
		}
		if (listAnswers.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * insertOffenderAssessmentItems
	 *
	 * @param params
	 *
	 */
	@Override
	public Integer insertOffenderAssessmentItems(Assessments listAnswers) {
		int count = 0;
		final String sql = getQuery("INSERT_ASSESSMENT_ITEMS");
		try {
			count = namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(listAnswers));
		} catch (Exception e) {
			logger.error("insertOffenderAssessmentItems: ", e);
		}
		return count;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * updateOffenderAssessments
	 *
	 * @param params
	 *
	 */
	@Override
	public Integer updateOffenderAssessments(final List<OffenderAssessments> listAnswers) {
		final String sql = getQuery("UPDATE_OFFENDER_ASSESSMENTS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderAssessments list : listAnswers) {
			parameters.add(new BeanPropertySqlParameterSource(list));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("updateOffenderAssessments: ", e);
		}
		if (listAnswers.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * existsOffenderAssessments
	 *
	 * @param params
	 *
	 */
	@Override
	public Integer existsOffenderAssessments(final OffenderAssessments paramBean) {
		final String sql = getQuery("SELECT_OFFENDER_ASSESSMENTS_EXITSTS");
		Integer returnList = null;
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderBookId",
					paramBean.getOffenderBookId(), "assessmentSeq", paramBean.getAssessmentSeq()), Integer.class);
		} catch (Exception e) {
			return 0;
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * inActivateOffenderAssessments
	 *
	 * @param params
	 *
	 */
	@Override
	public Integer inActivateOffenderAssessments(final List<OffenderAssessments> listAnswers) {
		final String sql = getQuery("INACTIVATE_OFFENDER_ASSESSMENTS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderAssessments list : listAnswers) {
			parameters.add(new BeanPropertySqlParameterSource(list));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("updateOffenderAssessments: ", e);
		}
		if (listAnswers.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * addRemoveAnswer
	 *
	 * @param params
	 *
	 */
	public Long getAssesmentSeq(final Long offBookId) {
		Long nextItemSeq = null;
		final String sql = getQuery("INACTIVATE_MAX_ASSESSMENT_SEQ");
		try {
			nextItemSeq = namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderBookId", offBookId),
					Long.class);
		} catch (Exception e) {
			logger.error("getAssesmentSeq: ", e);
		}
		if (nextItemSeq != null && nextItemSeq > 0) {
			return nextItemSeq;
		} else {
			return 0L;
		}
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * addRemoveAnswer
	 *
	 * @param params
	 *
	 */
	public Integer checkAssExist(final Assessments bean) {
		final String sql = getQuery("OCUNOQUE_CHECK_ASSESSMENTS_EXIST_OR_NOT");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("OFFENDERBOOKID", bean.getOffenderBookId(),
				"PARENT_ASSESSMENT_ID", bean.getParentAssessmentId(), "assessmentSeq", bean.getAssessmentSeq()),
				Integer.class);
	}

	@Override
	public Integer removeExistingAssItems(final Assessments answersObj) {
		List<Assessments> listAnswers = new ArrayList<>();
		listAnswers.add(answersObj);
		final String sql = getQuery("OCUNOQUE_REMOVE_EXISTING_ANSWER_REMOVE");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final Assessments list : listAnswers) {
			parameters.add(new BeanPropertySqlParameterSource(list));
		}
		try {
			String tableName = "OFFENDER_ASSESSMENT_ITEMS";
			String whereClause = "offender_book_id =:offenderBookId   and PARENT_ASSESSMENT_ID =:parentAssessmentId and ITEM_SEQ = :itemSeq";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method removeExistingAssItems", e);
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("removeExistingAssItems: ", e);
		}
		if (listAnswers.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Integer updateOffenderAssessmentItems(Assessments listAnswers) {
		final String sql = getQuery("UPDATE_OFFENDER_ASSESSMENTS_ITEMS");
		Integer count = 0;
		try {
			count = namedParameterJdbcTemplate.update(sql,
					createParams("score", listAnswers.getScore(), "offenderBookId", listAnswers.getOffenderBookId(),
							"assessmentSeq", listAnswers.getAssessmentSeq(), "itemseq", listAnswers.getItemSeq(), "modifyUserId", listAnswers.getModifyUserId()));
		} catch (Exception e) {
			logger.error("updateOffenderAssessmentItems: ", e);
		}
		return count;
	}

	@Override
	public String getQuery(Assessments assessments) {
		final String sql = getQuery("GET_EXECUTE_QUERY");
		String query = "";
		try {
			query = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("bookmarkName", assessments.getAnsBookMark()), String.class);
		} catch (Exception e) {
			logger.error("getQuery: ", e);
		}
		return query;
	}

	@Override
	public Long getOffenderAge(Assessments assessments) {
		String query = assessments.getQuery();
		Long returnValue = 0l;
		query=query.contains("?")?query.replace("?", ":offender_book_id"):query;
		try {
			returnValue = namedParameterJdbcTemplate.queryForObject(query,createParams("offender_book_id",assessments.getOffenderBookId()), Long.class);

		
	} catch (BadSqlGrammarException e) {
		returnValue = 0l;
	} catch (Exception e) {
			logger.error("getOffenderAge: ", e);
		}
		return returnValue;
	}

	public String getBookMarkAnswers(Assessments assessments) {
		String query = assessments.getQuery();
		String returnValue = ApplicationConstants.EMPTYDATA;
		query = query.contains("?") ? query.replace("?", ":offender_book_id") : query;
		try {
			returnValue = namedParameterJdbcTemplate.queryForObject(query,
					createParams("offender_book_id", assessments.getOffenderBookId()), String.class);
		} catch (BadSqlGrammarException e) {
			returnValue = ApplicationConstants.EMPTYDATA;
		} catch (Exception e) {
			logger.error(this.getClass().getName() + "getBookMarkAnswers: ", e);
		}
		return returnValue;
	}

	@Override
	public String getSupervisionLevelTypeForScore(Long assesmentId, Long score) {

		final String sql = getQuery("OCUNOQUE_GET_SUPERVISION_LEVEL_TYPE");
		String query = null;
		try {
			query = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("assesmentId", assesmentId, "score", score), String.class);
		} catch (Exception e) {
			logger.error("getQuery: ", e);
		}
		return query;
	}

	public List<AssessSectionNotifications> assessSectionNotificationsExecuteQuery(
			final AssessSectionNotifications objSearchDao) {
		final String sql = getQuery("OCUNOQUE_ASSESS_SECTION_NOTIFICATIONS_EXECUTEQUERY");
		List<AssessSectionNotifications> list = new ArrayList<AssessSectionNotifications>();
		final RowMapper<AssessSectionNotifications> assessNotifcRow = Row2BeanRowMapper.makeMapping(sql,
				AssessSectionNotifications.class, notifctnsMapping);
		try {
			list = namedParameterJdbcTemplate.query(sql, createParams("ASSESSMENT_ID", objSearchDao.getAssessmentId()),
					assessNotifcRow);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " assessSectionNotificationsExecuteQuery in error" + e);
		}
		return list;
	}

	@Override
	public String getAssmentEnforceFlag(Long assessmentId) {
		final String sql = getQuery("OCUNOQUE_ENFORCE_MIN_MAX_FLAG");
		String str=null;
		try {
			str=namedParameterJdbcTemplate.queryForObject(sql, createParams("assessmentId",assessmentId), String.class);
		}catch (Exception e) {
			logger.error(this.getClass().getName() + " getAssmentEnforceFlag in error" + e);
		}
		return str;
	}
	@Override
	public Long casePlanCount(Long offenderBookId) {
		final String sql = getQuery("OCUNOQUE_GET_COUNT_CASEPLAN");
		Long count = 0l;
		try {
			count = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("OFFENDERBOOKID",offenderBookId),long.class);
		} catch (Exception e) {
			logger.error("updateOffenderAssessmentItems: ", e);
		}
		return count;
	}

	@Override
	public List<VAssOffNeeds> getCriminogenicNeeds(Long assesmentId) {
		final String sql = getQuery("OCUNOQUE_GET_CRIMINOGENIC_NEEDS_DATA");
		List<VAssOffNeeds> list = new ArrayList<VAssOffNeeds>();
		final RowMapper<VAssOffNeeds> criminogenicNeed = Row2BeanRowMapper.makeMapping(sql, VAssOffNeeds.class,
				notifctnsMapping);
		try {
			list = namedParameterJdbcTemplate.query(sql, createParams("ASSESSMENT_ID", assesmentId), criminogenicNeed);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " getCriminogenicNeeds in error" + e);
		}
		return list;
	}

	@Override
	public List<VAssTreatProts> getTreatmentProtocols(Long offenderBookId,Long preSectionScore,Long offAssNeedId) {
		final String sql = getQuery("OCUNOQUE_GET_TREATMENT_PROTOCOLS_DATA");
		List<VAssTreatProts> list = new ArrayList<VAssTreatProts>();
		final RowMapper<VAssTreatProts> treatmentProtocols = Row2BeanRowMapper.makeMapping(sql, VAssTreatProts.class,
				notifctnsMapping);
		try {
			list = namedParameterJdbcTemplate.query(sql, createParams("offenderBookId",offenderBookId,"perSectionScore" ,preSectionScore,"OFF_ASS_NEED_ID", offAssNeedId),
					treatmentProtocols);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " getCriminogenicNeeds in error" + e);
		}
		return list;
	}
	
	@Override
	public Long getoffCrimNeedIdSeq() {
		String sql = getQuery("OCUNOQUE_GET_SEQ");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Long.class);
		
	}
	
	public Integer offCriNeedsInsertOffenderCriminogenicNeeds(
			final List<OffenderCriminogenicNeeds> lstOffenderCriminogenicNeeds) {
		final String sql = getQuery("OCUNOQUE_OFFCRINEEDS_INSERT_OFFENDER_CRIMINOGENIC_NEEDS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderCriminogenicNeeds offenderCriminogenicNeeds : lstOffenderCriminogenicNeeds) {
			parameters.add(new BeanPropertySqlParameterSource(offenderCriminogenicNeeds));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("offCriNeedsInsertOffenderCriminogenicNeeds: ", e);
		}
		if (lstOffenderCriminogenicNeeds.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}
	
}
