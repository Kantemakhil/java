package net.syscon.s4.inst.casemanagement.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlInOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.CasePlans;
import net.syscon.s4.im.beans.Dual;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.inst.casemanagement.OcdiplanRepository;
import net.syscon.s4.inst.casemanagement.beans.AssessmentSummaries;
import net.syscon.s4.inst.casemanagement.beans.CasePlanStaff;
import net.syscon.s4.inst.casemanagement.beans.CaseReviewPeriods;
import net.syscon.s4.inst.casemanagement.beans.CaseworkSteps;
import net.syscon.s4.inst.casemanagement.beans.CommunityConditions;
import net.syscon.s4.inst.casemanagement.beans.OffApV1;
import net.syscon.s4.inst.casemanagement.beans.OffApV2;
import net.syscon.s4.inst.casemanagement.beans.OffenderCaseConditions;
import net.syscon.s4.inst.casemanagement.beans.OffenderCriminogenicNeeds;
import net.syscon.s4.inst.casemanagement.beans.PlanDetails;
import net.syscon.s4.inst.casemanagement.beans.ProgramServices;
import net.syscon.s4.inst.casemanagement.beans.StaffMembersV2;
import net.syscon.s4.inst.casemanagement.beans.VSummaryCasePlans;
import net.syscon.s4.inst.casemanagement.beans.WorkFlowLogs;
import net.syscon.s4.inst.casemanagement.beans.WorkFlows;
import net.syscon.s4.inst.classification.beans.StaffMembersV1;
import net.syscon.s4.inst.legals.beans.OffenderSentConditions;
import oracle.jdbc.OracleTypes;

/**
 * Class OcdiplanRepositoryImpl
 */
@Repository
public class OcdiplanRepositoryImpl extends RepositoryBase implements OcdiplanRepository {

	/**
	 * Creates new OcdiplanRepositoryImpl class Object
	 */
	public OcdiplanRepositoryImpl() {
	}

	private final Map<String, FieldMapper> caseworkStepsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("0", new FieldMapper("0")).put("NVL(MAX(ASSESSMENT_SEQ)", new FieldMapper(" nvl(max(assessmentSeq)"))
			.put("NVL(MAX(CASEWORK_SEQ)", new FieldMapper(" nvl(max(caseworkSeq)"))
			.put("NVL(MAX(DETAIL_SEQ)", new FieldMapper(" nvl(max(detailSeq)")).build();
	private final Map<String, FieldMapper> caseReviewPeriodsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("ROLE", new FieldMapper("role")).put("FROM_DATE", new FieldMapper("fromDate"))
			.put("POSITION", new FieldMapper("position")).put("ASSESSED_NEED_CODE", new FieldMapper("assessedNeedCode"))
			.put("REVIEW_PERIOD", new FieldMapper("reviewPeriod")).put("DESCRIPTION", new FieldMapper("description"))
			.put("CAL_AGY_LOC_ID", new FieldMapper("calAgyLocId")).put("STATUS_CODE", new FieldMapper("statusCode"))
			.put("STAFF_NAME", new FieldMapper("staffName")).put("SAC_STAFF_ID", new FieldMapper("sacStaffId"))
			.put("INTAKE_AGY_LOC_ID", new FieldMapper(" intakeAgyLocId ")).build();
	private final Map<String, FieldMapper> vHeaderBlockMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("ROLE", new FieldMapper("role")).put("FROM_DATE", new FieldMapper("fromDate"))
			.put("POSITION", new FieldMapper("position")).put("ASSESSED_NEED_CODE", new FieldMapper("assessedNeedCode"))
			.put("REVIEW_PERIOD", new FieldMapper("reviewPeriod")).put("DESCRIPTION", new FieldMapper("description"))
			.put("CAL_AGY_LOC_ID", new FieldMapper("calAgyLocId")).put("STATUS_CODE", new FieldMapper("statusCode"))
			.put("STAFF_NAME", new FieldMapper("staffName")).put("SAC_STAFF_ID", new FieldMapper("sacStaffId"))
			.put("INTAKE_AGY_LOC_ID", new FieldMapper(" intakeAgyLocId ")).build();
	private final Map<String, FieldMapper> communityConditionsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("COMM_CONDITION_CODE", new FieldMapper("commConditionCode"))
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("MAX(MODIFY_DATETIME)", new FieldMapper(" max(modifyDatetime) "))
			.put("CASE_PLAN_ID", new FieldMapper("casePlanId"))
			.put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId"))
			.put("COMM_CONDITION_TYPE", new FieldMapper("commConditionType"))
			.put("CATEGORY_TYPE", new FieldMapper("categoryType")).build();
	private final Map<String, FieldMapper> workFlowsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("MAX(WORK_FLOW_ID)", new FieldMapper(" max(workFlowId) ")).build();
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("LIST_SEQ", new FieldMapper("listSeq")).put("DESCRIPTION", new FieldMapper("description"))
			.put("PROGRAM_CODE", new FieldMapper("programCode")).put("NULL", new FieldMapper("null "))
			.put("-1", new FieldMapper("-1 ")).put("PROGRAM_ID", new FieldMapper("programId"))
			.put("1", new FieldMapper("1 ")).build();
	private final Map<String, FieldMapper> agencyLocationsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("FROM_DATE", new FieldMapper("fromDate"))
			.put("INST_CAL_AGY_LOC_ID", new FieldMapper("instCalAgyLocId"))
			.put("AGY_LOC_ID", new FieldMapper("agyLocId")).put("STATUS_CODE", new FieldMapper("statusCode"))
			.put("INTAKE_AGY_LOC_ID", new FieldMapper(" intakeAgyLocId ")).put("LIST_SEQ", new FieldMapper("listSeq"))
			.put("ROLE", new FieldMapper("role")).put("POSITION", new FieldMapper("position"))
			.put("ASSESSED_NEED_CODE", new FieldMapper("assessedNeedCode"))
			.put("REVIEW_PERIOD", new FieldMapper("reviewPeriod")).put("CAL_AGY_LOC_ID", new FieldMapper("calAgyLocId"))
			.put("DSP_DESCRIPTION4", new FieldMapper("dspDescription4")).put("STAFF_NAME", new FieldMapper("staffName"))
			.put("SAC_STAFF_ID", new FieldMapper("sacStaffId")).build();
	private final Map<String, FieldMapper> vSummaryCasePlanMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CASEWORK_TYPE", new FieldMapper("caseworkType")).put("NOTES", new FieldMapper("notes"))
			.put("CASE_PLAN_ID", new FieldMapper("casePlanId")).put("PROGRAM_DESC", new FieldMapper("programDesc"))
			.put("CASEWORK_TYPE_DESC", new FieldMapper("caseworkTypeDesc")).put("ISSUE", new FieldMapper("issue"))
			.put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId")).put("TYPE", new FieldMapper("type")).build();
	private final Map<String, FieldMapper> casePlansMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CREATION_USER", new FieldMapper("creationUser")).put("AGY_LOC_ID", new FieldMapper("agyLocId"))
			.put("SAC_STAFF_I", new FieldMapper("sacStaffI"))
			.put("AUTO_ASSESS_MODIFY_DATETIME", new FieldMapper("autoAssessModifyDatetime"))
			.put("CAL_AGY_LOC_ID", new FieldMapper("calAgyLocId"))
			.put("PROFILE_VALUE", new FieldMapper(" profileValue "))
			.put("INST_POSITION", new FieldMapper("instPosition")).put("0)", new FieldMapper("0)"))
			.put("INST_SAC_STAFF_I", new FieldMapper("instSacStaffI"))
			.put("CREATION_DATE", new FieldMapper("creationDate"))		
			.put("staffName", new FieldMapper("officer"))
			//.put("", new FieldMapper("officer"))			
			.put("custodialAgyLoc", new FieldMapper("custodialLocation"))	
			.put("status", new FieldMapper("casePlanStatusDesc"))
			.put("supLevel", new FieldMapper("supervisionLevelDesc"))
			.put("casePlanUserId", new FieldMapper("casePlanUserId"))
			.put("reviewFlag", new FieldMapper("reviewFlag"))
			.put("workFlowStatus", new FieldMapper("workFlowStatus"))
			.put("CASE_PLAN_STATUS", new FieldMapper("casePlanStatus"))
			.put("communityAgyLoc", new FieldMapper("calAgyLocIdDesc"))
			.put("communityOfficer", new FieldMapper("communityStaffName"))			
			.put("staff_list", new FieldMapper("staffList"))
			.build();
	private final Map<String, FieldMapper> staffMembersMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("INST_SAC_STAFF_I", new FieldMapper("instSacStaffI")).put("END_DATE", new FieldMapper("  endDate "))
			.put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId"))
			.put("CASE_PLAN_STATUS", new FieldMapper("casePlanStatus ")).put("STAFF_ID", new FieldMapper("staffId"))
			.build();
	private final Map<String, FieldMapper> referenceCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("FROM_DATE", new FieldMapper("fromDate")).put("CASE_PLAN_STATUS", new FieldMapper("casePlanStatus"))
			.put("DSP_DESCRIPTION", new FieldMapper("dspDescription")).put("MODE", new FieldMapper("mode"))
			.put("STATUS_CODE", new FieldMapper("statusCode"))
			.put("INTAKE_AGY_LOC_ID", new FieldMapper(" intakeAgyLocId ")).put("LIST_SEQ", new FieldMapper("listSeq"))
			.put("ROLE", new FieldMapper("role")).put("SUPERVISION_LEVEL", new FieldMapper("supervisionLevel"))
			.put("POSITION", new FieldMapper("position")).put("CODE", new FieldMapper("code"))
			.put("ASSESSED_NEED_CODE", new FieldMapper("assessedNeedCode"))
			.put("DESCRIPTION", new FieldMapper("description")).put("REVIEW_PERIOD", new FieldMapper("reviewPeriod"))
			.put("CAL_AGY_LOC_ID", new FieldMapper("calAgyLocId")).put("STAFF_NAME", new FieldMapper("staffName"))
			.put("SAC_STAFF_ID", new FieldMapper("sacStaffId")).build();
	private final Map<String, FieldMapper> staffMembersV1Mapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("NAME", new FieldMapper(" name ")).put("STAFF_ID", new FieldMapper(" staffId")).build();
	private final Map<String, FieldMapper> assessmentSummariesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("0", new FieldMapper("0")).put("NVL(MAX(ASSESSMENT_SEQ)", new FieldMapper(" nvl(max(assessmentSeq)"))
			.put("NVL(MAX(CASEWORK_SEQ)", new FieldMapper(" nvl(max(caseworkSeq)"))
			.put("NVL(MAX(DETAIL_SEQ)", new FieldMapper(" nvl(max(detailSeq)")).build();
	private final Map<String, FieldMapper> staffMembersV2Mapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("FROM_DATE", new FieldMapper("fromDate"))
			.put("INST_CAL_AGY_LOC_ID", new FieldMapper("instCalAgyLocId")).put("MODE", new FieldMapper("mode"))
			.put("ROLE", new FieldMapper("role")).put("POSITION", new FieldMapper("position"))
			.put("INST_ROLE", new FieldMapper("instRole")).put("DESCRIPTION", new FieldMapper("description"))
			.put("INST_POSITION", new FieldMapper("instPosition")).put("CAL_AGY_LOC_ID", new FieldMapper("calAgyLocId"))
			.put("INST_FROM_DATE", new FieldMapper("instFromDate"))
			.put("INST_SAC_STAFF_ID", new FieldMapper("instSacStaffId"))
			//.put("STAFF_NAME", 						new FieldMapper("staffName"))
			.put("SAC_STAFF_ID", new FieldMapper("sacStaffId")).build();
	private final Map<String, FieldMapper> offenderCaseConditionsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_SENT_CONDITION_ID", new FieldMapper("offenderSentConditionId"))
			//.put("COMM_CONDITION_CODE", 						new FieldMapper("commConditionCode"))
			.put("LENGTH", new FieldMapper("length")).build();
	private final Map<String, FieldMapper> offApV2Mapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CASEWORK_TYPE", new FieldMapper("caseworkType"))
			//.put("PROGRAM_CATEGORY", 						new FieldMapper("programCategory"))
			.put("OFF_CASE_COND_ID", new FieldMapper("offCaseCondId"))
			//.put("CASEWORK_TYPE_DESC", 						new FieldMapper("caseworkTypeDesc"))
			.build();
	private final Map<String, FieldMapper> planDetailsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("0", new FieldMapper("0")).put("NVL(MAX(ASSESSMENT_SEQ)", new FieldMapper(" nvl(max(assessmentSeq)"))
			.put("NVL(MAX(CASEWORK_SEQ)", new FieldMapper(" nvl(max(caseworkSeq)"))
			.put("NVL(MAX(DETAIL_SEQ)", new FieldMapper(" nvl(max(detailSeq)")).build();
	private final Map<String, FieldMapper> offApV1Mapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CASEWORK_TYPE", new FieldMapper("caseworkType"))
			//.put("PROGRAM_CATEGORY", 						new FieldMapper("programCategory"))
			//.put("CASEWORK_TYPE_DESC", 						new FieldMapper("caseworkTypeDesc"))
			.build();
	private final Map<String, FieldMapper> systemProfilesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("INST_SAC_STAFF_I", new FieldMapper("instSacStaffI")).put("END_DATE", new FieldMapper("  endDate "))
			.put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId"))
			.put("CASE_PLAN_STATUS", new FieldMapper("casePlanStatus ")).build();
	private final Map<String, FieldMapper> offenderCriminogenicNeedsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("SEAL_FLAG", new FieldMapper("sealFlag")).put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("MAX(MODIFY_DATETIME)", new FieldMapper(" max(modifyDatetime) "))
			.put("TARGET_DATE", new FieldMapper("targetDate")).put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("ASSESSED_NEED_CODE", new FieldMapper("assessedNeedCode"))
			.put("STATUS_CODE", new FieldMapper("statusCode")).put("CASE_PLAN_ID", new FieldMapper("casePlanId"))
			.put("ROW_ID", new FieldMapper("rowId")).build();
	private final Map<String, FieldMapper> dualMapping = new ImmutableMap.Builder<String, FieldMapper>().build();
	private final Map<String, FieldMapper> omsModulesMapping = new ImmutableMap.Builder<String, FieldMapper>().build();
	private final Map<String, FieldMapper> workFlowLogsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.build();
	private final Map<String, FieldMapper> wrkFlsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OBJECT_CODE", new FieldMapper("objectCode")).put("WORK_FLOW_ID", new FieldMapper("workFlowId"))
			.put("OBJECT_SEQ", new FieldMapper("objectSeq")).put("OBJECT_ID", new FieldMapper("objectId")).build();

	private static Logger logger = LogManager.getLogger(OcdiplanRepositoryImpl.class.getName());

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao CasePlans
	 *
	 * @return List<CasePlans>
	 *
	 * 
	 */
	public List<CasePlans> casPlnExecuteQuery(final CasePlans objSearchDao) {
		final String sql = getQuery("OCDIPLAN_CASPLN_FIND_CASE_PLANS");
		final RowMapper<CasePlans> CasePlansRowMapper = Row2BeanRowMapper.makeMapping(sql, CasePlans.class,
				casePlansMapping);
		final ArrayList<CasePlans> returnList = (ArrayList<CasePlans>) namedParameterJdbcTemplate.query(sql,
				createParams("offenderBookId", objSearchDao.getOffenderBookId()), CasePlansRowMapper);
		return returnList;
	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstCasePlans List<CasePlans>
	 *
	 * 
	 */
	public Integer casePlanUpdate(final List<CasePlans> lstCasePlans) {
		final String sql = getQuery("OCDIPLAN_CASPLN_UPDATE_CASE_PLANS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final CasePlans casePlans : lstCasePlans) {
			parameters.add(new BeanPropertySqlParameterSource(casePlans));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstCasePlans.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstCasePlans List<CasePlans>
	 *
	 * 
	 */
	public Integer casePlanOldRecUpdate(final List<CasePlans> lstCasePlans) {
		final String sql = getQuery("OCDIPLAN_CASPLN_UPDATE_OLD_CASE_PLANS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final CasePlans casePlans : lstCasePlans) {
			parameters.add(new BeanPropertySqlParameterSource(casePlans));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstCasePlans.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	@Override
	public Long getNxtCasePlanId(final CasePlans objSearchDao) {
		Long returnVal = null;
		final String sql = getQuery("OCDIPLAN_GET_NXT_CASEPLAN_ID");
		returnVal = namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderBookId",
				objSearchDao.getOffenderBookId(), "v_case_plan_id", objSearchDao.getCasePlanId()), Long.class);
		return returnVal;
	}

	public Integer casePlanInsert(final List<CasePlans> lstCasePlans) {
		final String sql = getQuery("OCDIPLAN_CASPLN_INSERT_CASE_PLANS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final CasePlans casePlans : lstCasePlans) {
			parameters.add(new BeanPropertySqlParameterSource(casePlans));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("insertCasePlans: ", e);
		}
		if (lstCasePlans.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao OffenderCriminogenicNeeds
	 *
	 * @return List<OffenderCriminogenicNeeds>
	 *
	 * 
	 */
	public List<OffenderCriminogenicNeeds> offCriNeedsExecuteQuery(final OffenderCriminogenicNeeds objSearchDao) {
		final String sql = getQuery("OCDIPLAN_OFFCRINEEDS_FIND_OFFENDER_CRIMINOGENIC_NEEDS");
		final RowMapper<OffenderCriminogenicNeeds> OffenderCriminogenicNeedsRowMapper = Row2BeanRowMapper
				.makeMapping(sql, OffenderCriminogenicNeeds.class, offenderCriminogenicNeedsMapping);
		final ArrayList<OffenderCriminogenicNeeds> returnList = (ArrayList<OffenderCriminogenicNeeds>) namedParameterJdbcTemplate
				.query(sql, createParams("offenderBookId", objSearchDao.getOffenderBookId(), "casePlanId",
						objSearchDao.getCasePlanId()), OffenderCriminogenicNeedsRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstOffenderCriminogenicNeeds List<OffenderCriminogenicNeeds>
	 *
	 * @return List<Integer>
	 *
	 * 
	 */
	public Integer offCriNeedsInsertOffenderCriminogenicNeeds(
			final List<OffenderCriminogenicNeeds> lstOffenderCriminogenicNeeds) {
		final String sql = getQuery("OCDIPLAN_OFFCRINEEDS_INSERT_OFFENDER_CRIMINOGENIC_NEEDS");
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

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstOffenderCriminogenicNeeds List<OffenderCriminogenicNeeds>
	 *
	 * 
	 */
	public Integer offCriNeedsUpdateOffenderCriminogenicNeeds(
			final List<OffenderCriminogenicNeeds> lstOffenderCriminogenicNeeds) {
		final String sql = getQuery("OCDIPLAN_OFFCRINEEDS_UPDATE_OFFENDER_CRIMINOGENIC_NEEDS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderCriminogenicNeeds offenderCriminogenicNeeds : lstOffenderCriminogenicNeeds) {
			parameters.add(new BeanPropertySqlParameterSource(offenderCriminogenicNeeds));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenderCriminogenicNeeds.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao OffApV1
	 *
	 * @return List<OffApV1>cgfkCasPlnDspStaffNameRecordGroup
	 *
	 * 
	 */
	public List<OffApV1> offActionPlansV1ExecuteQuery(final OffApV1 objSearchDao) {
		final String sql = getQuery("OCDIPLAN_OFFACTIONPLANSV1_FIND_OFF_AP_V1");
		final RowMapper<OffApV1> OffApV1RowMapper = Row2BeanRowMapper.makeMapping(sql, OffApV1.class, offApV1Mapping);
		final ArrayList<OffApV1> returnList = (ArrayList<OffApV1>) namedParameterJdbcTemplate.query(sql,
				createParams("offCrimNeedId", objSearchDao.getOffCrimNeedId()), OffApV1RowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstOffApV1 List<OffApV1>
	 *
	 * @return List<Integer>
	 *
	 * 
	 */
	@Override
	public Integer offActionPlansV1InsertOffApV1(final List<OffApV1> lstOffApV1) {
		final String sql = getQuery("OCDIPLAN_OFFACTIONPLANSV1_INSERT_OFF_AP_V1");
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffApV1 offApV1 : lstOffApV1) {
			parameters.add(new BeanPropertySqlParameterSource(offApV1));
		}
		try {
			namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("Alert Insert Exception : ", e);
			return 0;
		}
		return 1;
	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstOffApV1 List<OffApV1>
	 *
	 * 
	 */
	public Integer offActionPlansV1UpdateOffApV1(final List<OffApV1> lstOffApV1) {
		final String sql = getQuery("OCDIPLAN_OFFACTIONPLANSV1_UPDATE_OFF_AP_V1");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffApV1 offApV1 : lstOffApV1) {
			parameters.add(new BeanPropertySqlParameterSource(offApV1));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffApV1.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstOffApV1 List<OffApV1>
	 *
	 * 
	 */
	public Integer offActionPlansV1DeleteOffApV1(final List<OffApV1> lstOffApV1) {
		final String sql = getQuery("OCDIPLAN_OFFACTIONPLANSV1_DELETE_OFF_AP_V1");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffApV1 offApV1 : lstOffApV1) {
			parameters.add(new BeanPropertySqlParameterSource(offApV1));
		}
		try {
			String tableName = "OFF_AP_V1";
			String whereClause = "OFF_ACTION_PLAN_ID  = :offActionPlanId and OFF_CRIM_NEED_ID  = :offCrimNeedId";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method offActionPlansV1DeleteOffApV1", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffApV1.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao OffenderCaseConditions
	 *
	 * @return List<OffenderCaseConditions>
	 *
	 * 
	 */
	public List<OffenderCaseConditions> offCaseCondsExecuteQuery(final OffenderCaseConditions objSearchDao) {
		final String sql = getQuery("OCDIPLAN_OFFCASECONDS_FIND_OFFENDER_CASE_CONDITIONS");
		final RowMapper<OffenderCaseConditions> OffenderCaseConditionsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderCaseConditions.class, offenderCaseConditionsMapping);
		final ArrayList<OffenderCaseConditions> returnList = (ArrayList<OffenderCaseConditions>) namedParameterJdbcTemplate
				.query(sql, createParams("offenderBookId", objSearchDao.getOffenderBookId(), "casePlanId",
						objSearchDao.getCasePlanId()), OffenderCaseConditionsRowMapper);
		return returnList;
	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstOffenderCaseConditions List<OffenderCaseConditions>
	 *
	 * 
	 */
	public Integer offCaseCondsUpdateOffenderCaseConditions(
			final List<OffenderCaseConditions> lstOffenderCaseConditions) {
		final String sql = getQuery("OCDIPLAN_OFFCASECONDS_UPDATE_OFFENDER_CASE_CONDITIONS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderCaseConditions offenderCaseConditions : lstOffenderCaseConditions) {
			parameters.add(new BeanPropertySqlParameterSource(offenderCaseConditions));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenderCaseConditions.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao OffApV2
	 *
	 * @return List<OffApV2>
	 *
	 * 
	 */
	public List<OffApV2> offActionPlansV2ExecuteQuery(final OffApV2 objSearchDao) {
		final String sql = getQuery("OCDIPLAN_OFFACTIONPLANSV2_FIND_OFF_AP_V2");
		final RowMapper<OffApV2> OffApV2RowMapper = Row2BeanRowMapper.makeMapping(sql, OffApV2.class, offApV2Mapping);
		final ArrayList<OffApV2> returnList = (ArrayList<OffApV2>) namedParameterJdbcTemplate.query(sql,
				createParams("offCaseCondId", objSearchDao.getOffCaseCondId()), OffApV2RowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstOffApV2 List<OffApV2>
	 *
	 * @return List<Integer>
	 *
	 * 
	 */
	public Integer offActionPlansV2InsertOffApV2(final List<OffApV2> lstOffApV2) {
		final String sql = getQuery("OCDIPLAN_OFFACTIONPLANSV2_INSERT_OFF_AP_V2");
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffApV2 offApV2 : lstOffApV2) {
			parameters.add(new BeanPropertySqlParameterSource(offApV2));
		}
		try {
			namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("Alert Insert Exception : ", e);
			return 0;
		}
		return 1;

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstOffApV2 List<OffApV2>
	 *
	 * 
	 */
	public Integer offActionPlansV2UpdateOffApV2(final List<OffApV2> lstOffApV2) {
		final String sql = getQuery("OCDIPLAN_OFFACTIONPLANSV2_UPDATE_OFF_AP_V2");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffApV2 offApV2 : lstOffApV2) {
			parameters.add(new BeanPropertySqlParameterSource(offApV2));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffApV2.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstOffApV2 List<OffApV2>
	 *
	 * 
	 */
	public Integer offActionPlansV2DeleteOffApV2(final List<OffApV2> lstOffApV2) {
		final String sql = getQuery("OCDIPLAN_OFFACTIONPLANSV2_DELETE_OFF_AP_V2");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffApV2 offApV2 : lstOffApV2) {
			parameters.add(new BeanPropertySqlParameterSource(offApV2));
		}
		try {
			String tableName = "OFF_AP_V2";
			String whereClause = "OFF_ACTION_PLAN_ID  = :offActionPlanId";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method offActionPlansV2DeleteOffApV2", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffApV2.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao VSummaryCasePlan
	 *
	 * @return List<VSummaryCasePlan>
	 *
	 * 
	 */
	public List<VSummaryCasePlans> vSummaryCasePlanExecuteQuery(final VSummaryCasePlans objSearchDao) {
		final String sql = getQuery("OCDIPLAN_VSUMMARYCASEPLAN_FIND_V_SUMMARY_CASE_PLAN");
		final RowMapper<VSummaryCasePlans> VSummaryCasePlanRowMapper = Row2BeanRowMapper.makeMapping(sql,
				VSummaryCasePlans.class, vSummaryCasePlanMapping);
		final ArrayList<VSummaryCasePlans> returnList = (ArrayList<VSummaryCasePlans>) namedParameterJdbcTemplate.query(
				sql, createParams("offenderBookId", objSearchDao.getOffenderBookId(),"caseplanId", objSearchDao.getCasePlanId()), VSummaryCasePlanRowMapper);
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgCaseplanAssRecordGroup() {
		final String sql = getQuery("OCDIPLAN_FIND_RGCASEPLANASS");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<Dual> rgCaseInfoRecordGroup() {
		final String sql = getQuery("OCDIPLAN_FIND_RGCASEINFO");
		final RowMapper<Dual> mRowMapper = Row2BeanRowMapper.makeMapping(sql, Dual.class, mMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> cgfkCasPlnDspDescriptionRecordGroup() {
		final String sql = getQuery("OCDIPLAN_FIND_CGFKCASPLNDSPDESCRIPTION");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<AgencyLocations> cgfkCasPlnDspDescription4RecordGroup() {
		final String sql = getQuery("OCDIPLAN_FIND_CGFKCASPLNDSPDESCRIPTION4");
		final RowMapper<AgencyLocations> mRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgCrimNeedsStsRecordGroup() {
		final String sql = getQuery("OCDIPLAN_FIND_RGCRIMNEEDSSTS");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgCaseworkRecordGroup() {
		final String sql = getQuery("OCDIPLAN_FIND_RGCASEWORK");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<MM>
	 */
	public List<ReferenceCodes> rgPrgCategoryRecordGroup() {
		final String sql = getQuery("OCDIPLAN_FIND_RGPRGCATEGORY");
		final RowMapper<ReferenceCodes> mMRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mMRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ProgramServices> rgProgramIdRecordGroup(final String programCategory) {
		final String sql = getQuery("OCDIPLAN_FIND_RGPROGRAMID");
		final RowMapper<ProgramServices> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ProgramServices.class,
				mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams("programCategory", programCategory), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ProgramServices> rgProgramId2RecordGroup() {
		final String sql = getQuery("OCDIPLAN_FIND_RGPROGRAMID2");
		final RowMapper<ProgramServices> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ProgramServices.class,
				mMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
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
	public CasePlans offBkgOnCheckDeleteMaster(final CasePlans paramBean) {
		final String sql = getQuery("OCDIPLAN_OFF_BKG_ONCHECKDELETEMASTER");
		final RowMapper<CasePlans> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, CasePlans.class,
				casePlansMapping);
		CasePlans returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offBkgOnCheckDeleteMaster
	 *
	 * @param params
	 *
	 */
	public List<VSummaryCasePlans> offBkgOnCheckDeleteMaster(final VSummaryCasePlans paramBean) {
		final String sql = getQuery("OCDIPLAN_OFF_BKG_ONCHECKDELETEMASTER");
		final RowMapper<VSummaryCasePlans> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, VSummaryCasePlans.class,
				vSummaryCasePlanMapping);
		final ArrayList<VSummaryCasePlans> returnList = (ArrayList<VSummaryCasePlans>) namedParameterJdbcTemplate
				.query(sql, createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * casPlnPostQuery
	 *
	 * @param params
	 *
	 */
	public StaffMembersV2 casPlnPostQuerySacStaffId(final CasePlans paramBean) {
		final String sql = getQuery("OCDIPLAN_CAS_PLN_POSTQUERY_SAC_STAFF_ID");
		StaffMembersV2 returnObj = new StaffMembersV2();
		final RowMapper<StaffMembersV2> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, StaffMembersV2.class,
				staffMembersV2Mapping);
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("sacStaffId", paramBean.getSacStaffId(), "calAgyLocId", paramBean.getCalAgyLocId(),
							"fromDate", paramBean.getFromDate(), "position", paramBean.getPosition(), "role",
							paramBean.getRole()),
					columnRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return returnObj;
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * casPlnPostQuery
	 *
	 * @param params
	 *
	 */
	public AgencyLocations casPlnPostQuery(final AgencyLocations paramBean) {
		final String sql = getQuery("OCDIPLAN_CAS_PLN_POSTQUERY_CALAGYLOCID");
		final RowMapper<AgencyLocations> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				agencyLocationsMapping);
		AgencyLocations returnObj = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("calAgyLocId", paramBean.getCode()), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * casPlnOnCheckDeleteMaster
	 *
	 * @param params
	 *
	 */
	public List<OffenderCriminogenicNeeds> casPlnOnCheckDeleteMaster(final OffenderCriminogenicNeeds paramBean) {
		final String sql = getQuery("OCDIPLAN_CAS_PLN_ONCHECKDELETEMASTER");
		final RowMapper<OffenderCriminogenicNeeds> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderCriminogenicNeeds.class, offenderCriminogenicNeedsMapping);
		final ArrayList<OffenderCriminogenicNeeds> returnList = (ArrayList<OffenderCriminogenicNeeds>) namedParameterJdbcTemplate
				.query(sql, createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * casPlnOnCheckDeleteMaster
	 *
	 * @param params
	 *
	 */
	public List<OffenderCaseConditions> casPlnOnCheckDeleteMaster(final OffenderCaseConditions paramBean) {
		final String sql = getQuery("OCDIPLAN_CAS_PLN_ONCHECKDELETEMASTER");
		final RowMapper<OffenderCaseConditions> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderCaseConditions.class, offenderCaseConditionsMapping);
		final ArrayList<OffenderCaseConditions> returnList = (ArrayList<OffenderCaseConditions>) namedParameterJdbcTemplate
				.query(sql, createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offCriNeedsPreInsert
	 *
	 * @param params
	 *
	 */
	public Object offCriNeedsPreInsert(final Dual paramBean) {
		final String sql = getQuery("OCDIPLAN_OFF_CRI_NEEDS_PREINSERT");
		final RowMapper<Dual> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Dual.class, dualMapping);
		Dual returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offCriNeedsOnCheckDeleteMaster
	 *
	 * @param params
	 *
	 */
	public List<OffApV1> offCriNeedsOnCheckDeleteMaster(final OffApV1 paramBean) {
		final String sql = getQuery("OCDIPLAN_OFF_CRI_NEEDS_ONCHECKDELETEMASTER");
		final RowMapper<OffApV1> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OffApV1.class, offApV1Mapping);
		final ArrayList<OffApV1> returnList = (ArrayList<OffApV1>) namedParameterJdbcTemplate.query(sql, createParams(),
				columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offCriNeedsPostQuery
	 *
	 * @param params
	 *
	 */
	public ReferenceCodes offCriNeedsPostQueryStatus(final ReferenceCodes paramBean) {
		final String sql = getQuery("OCDIPLAN_OFF_CRI_NEEDS_POSTQUERY_CP_FACT_STS");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		ReferenceCodes returnObj = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("statusCode", paramBean.getCode()), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offCriNeedsPostQuery
	 *
	 * @param params
	 *
	 */
	public ReferenceCodes offCriNeedsPostQuery(final ReferenceCodes paramBean) {
		final String sql = getQuery("OCDIPLAN_OFF_CRI_NEEDS_POSTQUERY_CASEPLAN_ASS");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		ReferenceCodes returnObj = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("assessedNeedCode", paramBean.getCode()), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offActionPlansV1PreInsert
	 *
	 * @param params
	 *
	 */
	public Object offActionPlansV1PreInsert(final Dual paramBean) {
		final String sql = getQuery("OCDIPLAN_OFF_ACTION_PLANS_V1_PREINSERT");
		final RowMapper<Dual> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Dual.class, dualMapping);
		Dual returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offCaseCondsPostQuery
	 *
	 * @param params
	 *
	 */
	public CommunityConditions offCaseCondsPostQueryCode(final OffenderCaseConditions paramBean) {
		final String sql = getQuery("OCDIPLAN_OFF_CASE_CONDS_POSTQUERY_FOR_CODE");
		final RowMapper<CommunityConditions> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				CommunityConditions.class, communityConditionsMapping);
		CommunityConditions returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("commConditioonType", paramBean.getCommConditionType(), "commConditionCode",
							paramBean.getCommConditionCode(), "categoryType", paramBean.getCategoryType()),
					columnRowMapper);
		} catch (Exception e) {
			logger.error(e);
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offCaseCondsPostQuery
	 *
	 * @param params
	 *
	 */
	public OffenderCaseConditions offCaseCondsGetLatestDatePostQuery(final BigDecimal casePlanId,
			final BigDecimal offenderBookId) {
		final String sql = getQuery("OCDIPLAN_OFF_CASE_CONDS_POSTQUERY_TO_GET_LATEST_DATE");
		final RowMapper<OffenderCaseConditions> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderCaseConditions.class, offenderCaseConditionsMapping);
		OffenderCaseConditions returnList = null;
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("casePlanId", casePlanId, "offenderBookId", offenderBookId), columnRowMapper);
		} catch (Exception e) {
			logger.error(e);
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offCaseCondsOnCheckDeleteMaster
	 *
	 * @param params
	 *
	 */
	public List<OffApV2> offCaseCondsOnCheckDeleteMaster(final OffApV2 paramBean) {
		final String sql = getQuery("OCDIPLAN_OFF_CASE_CONDS_ONCHECKDELETEMASTER");
		final RowMapper<OffApV2> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OffApV2.class, offApV2Mapping);
		final ArrayList<OffApV2> returnList = (ArrayList<OffApV2>) namedParameterJdbcTemplate.query(sql, createParams(),
				columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offActionPlansV2PreInsert
	 *
	 * @param params
	 *
	 */
	public Object offActionPlansV2PreInsert(final Dual paramBean) {
		final String sql = getQuery("OCDIPLAN_OFF_ACTION_PLANS_V2_PREINSERT");
		final RowMapper<Dual> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Dual.class, dualMapping);
		Dual returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
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
	public OmsModules createFormGlobals(final OmsModules paramBean) {
		final String sql = getQuery("OCDIPLAN_CREATE_FORM_GLOBALS");
		final RowMapper<OmsModules> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OmsModules.class,
				omsModulesMapping);
		OmsModules returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
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
		final String sql = getQuery("OCDIPLAN_GET_STAFF_ID");
		final RowMapper<StaffMembersV1> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, StaffMembersV1.class,
				staffMembersV1Mapping);
		final ArrayList<StaffMembersV1> returnList = (ArrayList<StaffMembersV1>) namedParameterJdbcTemplate.query(sql,
				createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * setCasPlnDefaults
	 *
	 * @param params
	 *
	 */
	public CaseReviewPeriods setCasPlnDefaults(final CaseReviewPeriods paramBean) {
		final String sql = getQuery("OCDIPLAN_SET_CAS_PLN_DEFAULTS");
		final RowMapper<CaseReviewPeriods> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, CaseReviewPeriods.class,
				caseReviewPeriodsMapping);
		CaseReviewPeriods returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * setCasPlnDefaults
	 *
	 * @param params
	 *
	 */
	public ReferenceCodes setCasPlnDefaults(final ReferenceCodes paramBean) {
		final String sql = getQuery("OCDIPLAN_SET_CAS_PLN_DEFAULTS");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		ReferenceCodes returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * setCasPlnDefaults
	 *
	 * @param params
	 *
	 */
	public AgencyLocations setCasPlnDefaults(final AgencyLocations paramBean) {
		final String sql = getQuery("OCDIPLAN_SET_CAS_PLN_DEFAULTS");
		final RowMapper<AgencyLocations> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				agencyLocationsMapping);
		AgencyLocations returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * setCasPlnDefaults
	 *
	 * @param params
	 *
	 */
	public List<VHeaderBlock> setCasPlnDefaults(final VHeaderBlock paramBean) {
		final String sql = getQuery("OCDIPLAN_SET_CAS_PLN_DEFAULTS");
		final RowMapper<VHeaderBlock> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, VHeaderBlock.class,
				vHeaderBlockMapping);
		final ArrayList<VHeaderBlock> returnList = (ArrayList<VHeaderBlock>) namedParameterJdbcTemplate.query(sql,
				createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfklkpCasPlnCasPlnStatu
	 *
	 * @param params
	 *
	 */
	public List<ReferenceCodes> cgfklkpCasPlnCasPlnStatu(final ReferenceCodes paramBean) {
		final String sql = getQuery("OCDIPLAN_CGFKLKP_CAS_PLN_CAS_PLN_STATU");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		final ArrayList<ReferenceCodes> returnList = (ArrayList<ReferenceCodes>) namedParameterJdbcTemplate.query(sql,
				createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfklkpCasPlnCasPlnAlFk
	 *
	 * @param params
	 *
	 */
	public List<AgencyLocations> cgfklkpCasPlnCasPlnAlFk(final AgencyLocations paramBean) {
		final String sql = getQuery("OCDIPLAN_CGFKLKP_CAS_PLN_CAS_PLN_AL_FK");
		final RowMapper<AgencyLocations> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				agencyLocationsMapping);
		final ArrayList<AgencyLocations> returnList = (ArrayList<AgencyLocations>) namedParameterJdbcTemplate.query(sql,
				createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkCasPlnCasPlnAlFk
	 *
	 * @param params
	 *
	 */
	public AgencyLocations cgfkchkCasPlnCasPlnAlFk(final AgencyLocations paramBean) {
		final String sql = getQuery("OCDIPLAN_CGFKCHK_CAS_PLN_CAS_PLN_AL_FK");
		final RowMapper<AgencyLocations> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				agencyLocationsMapping);
		AgencyLocations returnObj = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("instCalAgyLocId", paramBean.getCode()), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkCasPlnCasPlnStatu
	 *
	 * @param params
	 *
	 */
	public ReferenceCodes cgfkchkCasPlnCasPlnStatu(final ReferenceCodes paramBean) {
		final String sql = getQuery("OCDIPLAN_CGFKCHK_CAS_PLN_CAS_PLN_STATU");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		ReferenceCodes returnObj = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("casePlanStatus", paramBean.getCode()), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkCasPlnCasPlnVsm2
	 *
	 * @param params
	 *
	 */
	public StaffMembersV2 cgfkchkCasPlnCasPlnVsm2(final CasePlans paramBean) {
		final String sql = getQuery("OCDIPLAN_CGFKCHK_CAS_PLN_CAS_PLN_VSM2");
		StaffMembersV2 returnObj = new StaffMembersV2();
		final RowMapper<StaffMembersV2> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, StaffMembersV2.class,
				staffMembersV2Mapping);
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("instSacStaffId", paramBean.getInstSacStaffId(), "instCalAgyLocId",
							paramBean.getInstCalAgyLocId(), "instFromDate", paramBean.getInstFromDate(), "instPosition",
							paramBean.getInstPosition(), "instRole", paramBean.getInstRole()),
					columnRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return returnObj;
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkCasPlnCasPlnSuper
	 *
	 * @param params
	 *
	 */
	public ReferenceCodes cgfkchkCasPlnCasPlnSuper(final ReferenceCodes paramBean) {
		ReferenceCodes returnObj = null;
		try {
			final String sql = getQuery("OCDIPLAN_CGFKCHK_CAS_PLN_CAS_PLN_SUPER");
			final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
					referenceCodesMapping);
			returnObj = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("supervisionLevel", paramBean.getCode()), columnRowMapper);
			} catch(Exception e) {
				logger.error(e);
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * getSequenceNo
	 *
	 * @param params
	 *
	 */
	public PlanDetails getSequenceNo(final PlanDetails paramBean) {
		final String sql = getQuery("OCDIPLAN_GET_SEQUENCE_NO");
		final RowMapper<PlanDetails> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, PlanDetails.class,
				planDetailsMapping);
		PlanDetails returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * getSequenceNo
	 *
	 * @param params
	 *
	 */
	public AssessmentSummaries getSequenceNo(final AssessmentSummaries paramBean) {
		final String sql = getQuery("OCDIPLAN_GET_SEQUENCE_NO");
		final RowMapper<AssessmentSummaries> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				AssessmentSummaries.class, assessmentSummariesMapping);
		AssessmentSummaries returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * getSequenceNo
	 *
	 * @param params
	 *
	 */
	public CaseworkSteps getSequenceNo(final CaseworkSteps paramBean) {
		final String sql = getQuery("OCDIPLAN_GET_SEQUENCE_NO");
		final RowMapper<CaseworkSteps> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, CaseworkSteps.class,
				caseworkStepsMapping);
		CaseworkSteps returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * checkReviewDate
	 *
	 * @param params
	 *
	 */
	public Integer checkReviewDate(final String supervisionLevel) {
		final String sql = getQuery("OCDIPLAN_CHECK_REVIEW_DATE");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("SUPER_VISION_LEVEL", supervisionLevel),
				Integer.class);
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
		final String sql = getQuery("OCDIPLAN_GET_CNOTE_ALLUPD_PROFILE");
		final RowMapper<SystemProfiles> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, SystemProfiles.class,
				systemProfilesMapping);
		SystemProfiles returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * nextReviewDate
	 *
	 * @param params
	 *
	 */
	public CaseReviewPeriods nextReviewDate(final CaseReviewPeriods paramBean) {
		final String sql = getQuery("OCDIPLAN_NEXT_REVIEW_DATE");
		final RowMapper<CaseReviewPeriods> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, CaseReviewPeriods.class,
				caseReviewPeriodsMapping);
		CaseReviewPeriods returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * verificationVERIFICATION
	 *
	 * @param params
	 *
	 */
	public List<WorkFlows> verificationVERIFICATION(final WorkFlows paramBean) {
		final String sql = getQuery("OCDIPLAN_VERIFICATION_VERIFICATION");
		final RowMapper<WorkFlows> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, WorkFlows.class,
				workFlowsMapping);
		final ArrayList<WorkFlows> returnList = (ArrayList<WorkFlows>) namedParameterJdbcTemplate.query(sql,
				createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * verificationVERIFICATION
	 *
	 * @param params
	 *
	 */
	public WorkFlowLogs verificationVERIFICATION(final WorkFlowLogs paramBean) {
		final String sql = getQuery("OCDIPLAN_VERIFICATION_VERIFICATION");
		final RowMapper<WorkFlowLogs> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, WorkFlowLogs.class,
				workFlowLogsMapping);
		WorkFlowLogs returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * insertCaseplanRecordINSERT_CASEPLAN_RECORD
	 *
	 * @param params
	 *
	 */
	public CasePlans insertCaseplanRecordINSERT_CASEPLAN_RECORD(final CasePlans paramBean) {
		final String sql = getQuery("OCDIPLAN_INSERT_CASEPLAN_RECORD_INSERT_CASEPLAN_RECORD");
		final RowMapper<CasePlans> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, CasePlans.class,
				casePlansMapping);
		CasePlans returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * authorizedUser
	 *
	 * @param params
	 *
	 */
	public List<StaffMembers> authorizedUser(final StaffMembers paramBean) {
		final String sql = getQuery("OCDIPLAN_AUTHORIZED_USER");
		final RowMapper<StaffMembers> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, StaffMembers.class,
				staffMembersMapping);
		final ArrayList<StaffMembers> returnList = (ArrayList<StaffMembers>) namedParameterJdbcTemplate.query(sql,
				createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * authorizedUser
	 *
	 * @param params
	 *
	 */
	public CasePlans authorizedUser(final CasePlans paramBean) {
		final String sql = getQuery("OCDIPLAN_AUTHORIZED_USER");
		final RowMapper<CasePlans> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, CasePlans.class,
				casePlansMapping);
		CasePlans returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * authorizedUser
	 *
	 * @param params
	 *
	 */
	public SystemProfiles authorizedUser(final SystemProfiles paramBean) {
		final String sql = getQuery("OCDIPLAN_AUTHORIZED_USER");
		final RowMapper<SystemProfiles> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, SystemProfiles.class,
				systemProfilesMapping);
		SystemProfiles returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * defaultStaffValidation
	 *
	 * @param params
	 *
	 */
	public List<StaffMembers> defaultStaffValidation(final StaffMembers paramBean) {
		final String sql = getQuery("OCDIPLAN_DEFAULT_STAFF_VALIDATION");
		final RowMapper<StaffMembers> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, StaffMembers.class,
				staffMembersMapping);
		final ArrayList<StaffMembers> returnList = (ArrayList<StaffMembers>) namedParameterJdbcTemplate.query(sql,
				createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * defaultStaffValidation
	 *
	 * @param params
	 *
	 */
	public CasePlans getAutherizedUser(final CasePlans paramBean) {
		final String sql = getQuery("OCDIPLAN_AUTHORIZED_USER");
		final RowMapper<CasePlans> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, CasePlans.class,
				casePlansMapping);
		CasePlans returnObj = new CasePlans();
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("OFFENDERBOOKID", paramBean.getOffenderBookId()), columnRowMapper);
		} catch (Exception e) {
			returnObj = null;
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * defaultStaffValidation
	 *
	 * @param params
	 *
	 */
	public SystemProfiles defaultStaffValidation(final SystemProfiles paramBean) {
		final String sql = getQuery("OCDIPLAN_DEFAULT_STAFF_VALIDATION");
		final RowMapper<SystemProfiles> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, SystemProfiles.class,
				systemProfilesMapping);
		SystemProfiles returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * fetchLastDatetime
	 *
	 * @param params
	 *
	 */
	public List<OffenderCriminogenicNeeds> fetchLastDatetime(final OffenderCriminogenicNeeds paramBean) {
		final String sql = getQuery("OCDIPLAN_FETCH_LAST_DATETIME");
		final RowMapper<OffenderCriminogenicNeeds> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderCriminogenicNeeds.class, offenderCriminogenicNeedsMapping);
		final ArrayList<OffenderCriminogenicNeeds> returnList = (ArrayList<OffenderCriminogenicNeeds>) namedParameterJdbcTemplate
				.query(sql, createParams(), columnRowMapper);
		return returnList;
	}

	public String getnbtConSys(final String conditionStatus) {
		final String sql = getQuery("OCDIPLAN_GET_STATUS_DESC");
		String value = null;
		try {
			value = namedParameterJdbcTemplate.queryForObject(sql, createParams("code", conditionStatus), String.class);
		} catch (Exception e) {
			logger.error(e);
		}
		return value;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * oidadmisCgfkchkOffBkgsOffBkgStafc
	 *
	 * @param params
	 *
	 */
	public StaffMembers cgfkchkOffBkgsOffBkgStafc(final StaffMembers paramBean) {
		final String sql = getQuery("OCDIPLAN_CASE_PLANS_OFFICER_PREINSERT");
		final RowMapper<StaffMembers> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, StaffMembers.class,
				staffMembersMapping);
		StaffMembers returnList = new StaffMembers();
		returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("staffId", paramBean.getStaffId()),
				columnRowMapper);
		return returnList;
	}

	@Override
	public OffenderCriminogenicNeeds offCriNeedsGetLatestDatePostQuery(final BigDecimal casePlanId,
			final BigDecimal offenderBookId) {
		final String sql = getQuery("OCDIPLAN_OFF_CRIM_NEEDS_POSTQUERY_TO_GET_LATEST_DATE");
		final RowMapper<OffenderCriminogenicNeeds> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderCriminogenicNeeds.class, offenderCriminogenicNeedsMapping);
		final OffenderCriminogenicNeeds returnList = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("casePlanId", casePlanId, "offenderBookId", offenderBookId), columnRowMapper);
		return returnList;
	}

	@Override
	public Integer getStaffId(final String user) {
		Integer returnVal = 0;
		final String sql = getQuery("OCDIPLAN_DEFAULT_STAFF_ID");
		returnVal = namedParameterJdbcTemplate.queryForObject(sql, createParams("userId", user), Integer.class);
		return returnVal;
	}

	@Override
	public Integer getRoleValue() {
		Integer returnVal = null;
		final String sql = getQuery("OCDIPLAN_GET_ROLE_VALUE");
		returnVal = namedParameterJdbcTemplate.queryForObject(sql, createParams(), Integer.class);
		return returnVal;
	}

	@Override
	public Integer getverifyUserRole(final Integer profileVal, final String user) {
		Integer returnVal = null;
		final String sql = getQuery("OCDIPLAN_VERIFY_USERROLE");
		returnVal = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("profileVal", profileVal, "userId", user), Integer.class);
		return returnVal;
	}

	@Override
	public Long getworkFlowId(final CasePlans objSearchDao) {
		Long returnVal = null;
		final String sql = getQuery("OCDIPLAN_WORK_FLOW_ID");
		returnVal = namedParameterJdbcTemplate.queryForObject(sql, createParams("off_bk_id",
				objSearchDao.getOffenderBookId(), "v_case_plan_id", objSearchDao.getCasePlanId()), Long.class);
		return returnVal;
	}

	@Override
	public List<WorkFlowLogs> workFlExecuteQuery(final CasePlans workFlowLst) {
		List<WorkFlowLogs> returnList = new ArrayList<>();
		final String sql = getQuery("OCDIPLAN_WORKFL_FIND_WORK_FLOW_LOGS");
		final RowMapper<WorkFlowLogs> workFlowRM = Row2BeanRowMapper.makeMapping(sql, WorkFlowLogs.class,
				wrkFlsMapping);
		returnList = namedParameterJdbcTemplate.query(sql, createParams("workFlowId", workFlowLst.getWorkFlowId()),
				workFlowRM);
		return returnList;
	}

	@Override
	public String getSupLevel(final Long offBookId) {
		final String value;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[2];
		sqlParameters = new SqlParameter[] { new SqlInOutParameter("RETURN_VALUE", OracleTypes.VARCHAR),
				new SqlParameter("P_OFFENDER_BOOK_ID", OracleTypes.INTEGER) };
		final SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("PIMS_WEIGHT").withFunctionName("GET_SUP_LEVEL").declareParameters(sqlParameters);
		inParamMap.put("P_OFFENDER_BOOK_ID", offBookId);
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		value = simpleJDBCCall.executeFunction(String.class, inParameter);
		return value;
	}

	@Override
	public Integer whenNextbuttonUpdates(final OffenderCriminogenicNeeds searchRecord) {
		final String sql = getQuery("OCDIPLAN_CASPLN_INSERT_CASE_PLANS_NEXT_REVIEW_DATE");
		try {
			return namedParameterJdbcTemplate.update(sql,
					createParams("offenderBookId", searchRecord.getOffenderBookId(),"modifiedUserId", searchRecord.getCreateUserId()));
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public Integer getReviewPeriodFromCaseReviewPeriods(final CaseReviewPeriods searchRecord) {
		final String sql = getQuery("GET_REVIEW_PERIOD_FROM_CASE_REVIEW_PERIODS");
		try {
			return namedParameterJdbcTemplate.update(sql,
					createParams("V_SUPERVISION_LEVEL", searchRecord.getSupervisionLevel()));
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public String getDescriptionFromReferenceCodes(final ReferenceCodes searchRecord) {
		final String sql = getQuery("GET_DESCRIPTION_FROM_REFERENCE_CODES");
		String returnObj = new String();
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("P_DOMAIN", searchRecord.getDomain(), "P_CODE", searchRecord.getCode()), String.class);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}

	@Override
	public String getDescriptionFromAgencyLocations(final AgencyLocations searchRecord) {
		final String sql = getQuery("GET_DESCRIPTION_FROM_AGENCY_LOCATIONS");
		String returnObj = new String();
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("P_AGY_LOC_ID", searchRecord.getAgyLocId()), String.class);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}

	@Override
	public String getIntakeAgyLocIdFromvHeaderBlock(final VHeaderBlock searchRecord) {
		final String sql = getQuery("GET_INTAKE_AGY_LOC_ID_FROM_V_HEADER_BLOCK");
		String returnObj = new String();
		try {
			return namedParameterJdbcTemplate.queryForObject(sql,
					createParams("P_OFFENDER_BOOK_ID", searchRecord.getOffenderBookId(),"USERID",searchRecord.getCreateuserId()), String.class);
		} catch (Exception e) {
			return returnObj;
		}
	}
	public List<OffenderSentConditions> getOffSentConditions(final BigDecimal offBookId) {
		final String sql = getQuery("GET_OFFENDER_SENT_CONDITIONS_CASEPLAN");
		List<OffenderSentConditions> returnList = new ArrayList<OffenderSentConditions>();
		final RowMapper<OffenderSentConditions> OffenderCaseConditionsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderSentConditions.class, offenderCaseConditionsMapping);
		try {
			returnList=namedParameterJdbcTemplate.query(sql, createParams("OFFENDER_BOOK_ID", offBookId), OffenderCaseConditionsRowMapper);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " Error in getOffSentConditions method " + e.getMessage());
		}
		return returnList;
	}
	
	
	@Override
	public Long getoffCrimNeedIdSeq() {
		String sql = getQuery("OCDIPLAN_GET_SEQ");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Long.class);
		
	}

	@Override
	public List<CasePlanStaff> getCasePlanStaffDetails(Long offenderBookId, Long casePlanId) {
		String sql = getQuery("OCDIPLAN_GET_STAFF_DETAILS");
		List<CasePlanStaff> returnList = new ArrayList<CasePlanStaff>();
		try {
			returnList = namedParameterJdbcTemplate.queryForList(sql,
					createParams("casePlanId", casePlanId, "offenderBookId", offenderBookId), CasePlanStaff.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " getCasePlanStaffDetails " + e);
		}

		return returnList;
	}
@Override
	public String getStaffName(String userId) {
		String sql = getQuery("OCDIPLAN_GET_STAFF_NAME");
		String staffName = null;
		try {
			staffName = namedParameterJdbcTemplate.queryForObject(sql, createParams("userId", userId), String.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " getStaffName " + e);
		}
		return staffName;
	}

@Override
public List<StaffMembers> getUserIdOfAssignedStaff(Long offenderBookId, Long casePlanId) {
	String sql = getQuery("OCDIPLAN_GET_USER_ID_OF_ASSIGNED_STAFF");
	List<StaffMembers> returnList = new ArrayList<StaffMembers>();
	final RowMapper<StaffMembers> staffMembersMapping = Row2BeanRowMapper
			.makeMapping(sql, StaffMembers.class, staffMembersV1Mapping);
	try {
		returnList = (List<StaffMembers>) namedParameterJdbcTemplate.query(sql,
				createParams("casePlanId", casePlanId, "offenderBookId", offenderBookId), staffMembersMapping);
	} catch (Exception e) {
		logger.error(this.getClass().getName() + " getUserIdOfAssignedStaff " + e);
	}

	return returnList;
}


@Override
public List<StaffMembers> getUserIdOfAssignedStaffForCpOwn(Long offenderBookId, Long casePlanId) {
	String sql = getQuery("OCDIPLAN_GET_USER_ID_OF_ASSIGNED_STAFF_FOR_CP_OWN");
	List<StaffMembers> returnList = new ArrayList<StaffMembers>();
	final RowMapper<StaffMembers> staffMembersMapping = Row2BeanRowMapper.makeMapping(sql, StaffMembers.class,
			staffMembersV1Mapping);
	try {
		returnList = (List<StaffMembers>) namedParameterJdbcTemplate.query(sql,
				createParams("casePlanId", casePlanId, "offenderBookId", offenderBookId), staffMembersMapping);
	} catch (Exception e) {
		logger.error(this.getClass().getName() + " getUserIdOfAssignedStaffForCpOwn " + e);
	}
	return returnList;
}

public List<CasePlans> maxCaseplanIdRecord(final CasePlans objSearchDao) {
	final String sql = getQuery("OCDIPLAN_CASPLN_FIND_CASE_PLAN_DETAILS_MAX_CASEPLAN_ID");
	final RowMapper<CasePlans> CasePlansRowMapper = Row2BeanRowMapper.makeMapping(sql, CasePlans.class,
			casePlansMapping);
	final ArrayList<CasePlans> returnList = (ArrayList<CasePlans>) namedParameterJdbcTemplate.query(sql,
			createParams("offenderBookId", objSearchDao.getOffenderBookId(),"casePlanId",objSearchDao.getCasePlanId()), CasePlansRowMapper);
	return returnList;
}
}