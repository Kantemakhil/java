package net.syscon.s4.triggers.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.OffenderActionPlans;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.CasePlans;
import net.syscon.s4.inst.casemanagement.beans.OffenderCaseConditions;
import net.syscon.s4.inst.casemanagement.beans.OffenderCriminogenicNeeds;
import net.syscon.s4.triggers.CasePlansT3Repository;

/**
 * Class CasePlansT3RepositoryImpl
 * 
 */
@Repository
public class CasePlansT3RepositoryImpl extends RepositoryBase implements CasePlansT3Repository {

	private final Map<String, FieldMapper> offenderCaseConditionsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("offender_sent_condition_id", new FieldMapper("offenderSentConditionId"))
			.put("objective", new FieldMapper("objective"))
			.put("comm_condition_type", new FieldMapper("commConditionType"))
			.put("comm_condition_code", new FieldMapper("commConditionCode"))
			.put("length", new FieldMapper("length"))
			.put("length_unit", new FieldMapper("lengthUnit"))
			.put("start_date", new FieldMapper("startDate"))
			.put("end_date", new FieldMapper("endDate"))
			.put("off_case_cond_id", new FieldMapper("offCaseCondId"))
			.put("condition_status", new FieldMapper("conditionStatus"))
			.put("category_type", new FieldMapper("categoryType")).build();

	private final Map<String, FieldMapper> offenderActionPlansMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("casework_type", new FieldMapper("caseworkType"))
			.put("program_id", new FieldMapper("programId"))
			.put("notes", new FieldMapper("notes"))
			.put("start_date", new FieldMapper("startDate"))
			.put("end_date", new FieldMapper("endDate")).build();

	private final Map<String, FieldMapper> offenderCriminogenicNeedsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("objective", new FieldMapper("objective"))
			.put("target_date", new FieldMapper("targetDate"))
			.put("end_date", new FieldMapper("endDate"))
			.put("status_code", new FieldMapper("statusCode"))
			.put("off_crim_need_id", new FieldMapper("offCrimNeedId")).build();


	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(CasePlansT3RepositoryImpl.class);

	final Map<String, FieldMapper> casePlansMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("SEAL_FLAG", new FieldMapper("sealFlag")).build();


	@Override
	public Integer offenderGriminogenicNeedsCount(final Long OffenderBookId,final Long lVCpIdO) {
		final String sql = getQuery("OFFENDER_CRIMINOGENIC_NEEDS_COUNT");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("offender_book_id", OffenderBookId,
				"lv_cp_id_o", lVCpIdO), Integer.class);
	}

	@Override
	public List<OffenderCriminogenicNeeds> getOffendercriminogenicNeeds(final Long OffenderBookId,final Long lVCpIdO) {
		final String sql = getQuery("GET_OFFENDER_CRIMINOGENIC_NEEDS_NEW");
		List<OffenderCriminogenicNeeds> list= new ArrayList<OffenderCriminogenicNeeds>();
		final RowMapper<OffenderCriminogenicNeeds> offenderCriminogenicNeedsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderCriminogenicNeeds.class, offenderCriminogenicNeedsMapping);
		try {
		list = namedParameterJdbcTemplate.query(sql,
				createParams("offender_book_id", OffenderBookId,
						"lv_cp_id_o", lVCpIdO),
				offenderCriminogenicNeedsRowMapper);
	}
		catch(Exception e) {
			logger.error("getOffendercriminogenicNeeds :"+e);
		}
		return list;
	}

	@Override
	public Integer crimNeedIdNextval() {
		final String sql = getQuery("CRIM_NEED_ID_NEXTVAL");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Integer.class);
	}

	@Override
	public Integer offenderActionPlansCount(final Long lVCpIdO) {
		final String sql = getQuery("OFFENDER_ACTION_PLANS_COUNT");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("v_cn_id", lVCpIdO), Integer.class);
	}

	@Override
	public List<OffenderActionPlans> getOffenderActionPlans(final Long vCnId) {
		final String sql = getQuery("GET_OFFENDER_ACTION_PLANS");
		final RowMapper<OffenderActionPlans> offenderActionPlansRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderActionPlans.class, offenderActionPlansMapping);
		return namedParameterJdbcTemplate.query(sql,
				createParams("v_cn_id", vCnId),
				offenderActionPlansRowMapper);
	}

	@Override
	public Integer offenderCaseConditionsCount(final Long OffenderBookId,final Long lVCpIdO) {
		final String sql = getQuery("OFFENDER_CASE_CONDITIONS_COUNT");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("offender_book_id", OffenderBookId,
				"lv_cp_id_o", lVCpIdO), Integer.class);
	}

	@Override
	public List<OffenderCaseConditions> getOffenderCaseConditions(final Long OffenderBookId,final Long lVCpIdO) {
		final String sql = getQuery("GET_OFFENDER_CASE_CONDITIONS");
		final RowMapper<OffenderCaseConditions> offenderCaseConditionsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderCaseConditions.class, offenderCaseConditionsMapping);
		return namedParameterJdbcTemplate.query(sql,
				createParams("offender_book_id", OffenderBookId,
						"lv_cp_id_o", lVCpIdO),
				offenderCaseConditionsRowMapper);
	}

	@Override
	public Integer caseCondsDdCur() {
		final String sql = getQuery("CASE_CONDS_ID_CUR");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Integer.class);
	}

	@Override
	public Integer insertOffenderCaseConditions(final OffenderCaseConditions offenderCaseConditions,final CasePlans casePlans,final Integer lvOffCaseCondId) {
		final String sql = getQuery("CASE_PLAN_TRIGGER_THREE_INSERT_OFFENDER_CASE_CONDITIONS");
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		Integer retVal = 0;

		inParamMap.put("v_off_cc_id", lvOffCaseCondId);
		inParamMap.put("v_obi", casePlans.getOffenderBookId());
		inParamMap.put("v_caseplan_id", casePlans.getCasePlanId());
		inParamMap.put("offender_sent_condition_id", offenderCaseConditions.getOffenderSentConditionId());
		inParamMap.put("objective", offenderCaseConditions.getObjective());
		inParamMap.put("comm_condition_type", offenderCaseConditions.getCommConditionType());
		inParamMap.put("comm_condition_code", offenderCaseConditions.getCommConditionCode());
		inParamMap.put("length", offenderCaseConditions.getLength());
		inParamMap.put("length_unit", offenderCaseConditions.getLengthUnit());
		inParamMap.put("start_date", offenderCaseConditions.getStartDate());
		inParamMap.put("end_date", offenderCaseConditions.getEndDate());
		inParamMap.put("condition_status", offenderCaseConditions.getConditionStatus());
		inParamMap.put("category_type", offenderCaseConditions.getCategoryType());
		inParamMap.put("createUserId", casePlans.getCreateUserId());
		try {
			retVal = namedParameterJdbcTemplate.update(sql, inParamMap);
		} catch (Exception e) {
			retVal = 0;
			logger.error("insertOffenderActionPlans", e);
		}
		return retVal;
	}

	@Override
	public Integer insertOffenderActionPlans(final OffenderActionPlans offenderActionPlans,final Integer lvOffCaseCondId) {
		final String sql = getQuery("INSERT_OFFENDER_ACTION_PLANS");
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		Integer retVal = 0;

		inParamMap.put("casework_type", offenderActionPlans.getCaseworkType());
		inParamMap.put("lv_off_case_cond_id", lvOffCaseCondId);
		inParamMap.put("program_id", offenderActionPlans.getProgramId());
		inParamMap.put("notes", offenderActionPlans.getNotes());
		inParamMap.put("start_date", offenderActionPlans.getStartDate());
		inParamMap.put("end_date", offenderActionPlans.getEndDate());
		inParamMap.put("createUserId", offenderActionPlans.getCreateUserId());
		try {
			retVal = namedParameterJdbcTemplate.update(sql, inParamMap);
		} catch (Exception e) {
			retVal = 0;
			logger.error("insertOffenderActionPlans", e);
		}
		return retVal;
	}

	@Override
	public Integer insertOffenderCriminogenicNeeds(final OffenderCriminogenicNeeds offenderCriminogenicNeeds,final CasePlans casePlans,final Integer lvOffCaseCondId) {
		final String sql = getQuery("INSERT_OFFENDER_CRIMINOGENIC_NEEDS");
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		Integer retVal = 0;

		inParamMap.put("lv_off_case_cond_id", lvOffCaseCondId);
		inParamMap.put("offender_book_id", casePlans.getOffenderBookId());
		inParamMap.put("case_plan_id", casePlans.getCasePlanId());
		inParamMap.put("assessed_need_code", offenderCriminogenicNeeds.getAssessedNeedCode());
		inParamMap.put("objective", offenderCriminogenicNeeds.getObjective());
		inParamMap.put("target_date", offenderCriminogenicNeeds.getTargetDate());
		inParamMap.put("end_date", offenderCriminogenicNeeds.getEndDate());
		inParamMap.put("status_code", offenderCriminogenicNeeds.getStatusCode());
		inParamMap.put("createUserId", casePlans.getCreateUserId());
		try {
			retVal = namedParameterJdbcTemplate.update(sql, inParamMap);
		} catch (Exception e) {
			retVal = 0;
			logger.error("insertWorkFlows", e);
		}
		return retVal;
	}

	@Override
	public Integer insertOffenderActionPlans2(final OffenderActionPlans offenderActionPlans,final Integer lvOffCaseCondId) {
		final String sql = getQuery("INSERT_OFFENDER_ACTION_PLANS2");
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		Integer retVal = 0;

		inParamMap.put("casework_type", offenderActionPlans.getCaseworkType());
		inParamMap.put("lv_off_case_cond_id", lvOffCaseCondId);
		inParamMap.put("program_id", offenderActionPlans.getProgramId());
		inParamMap.put("notes", offenderActionPlans.getNotes());
		inParamMap.put("start_date", offenderActionPlans.getStartDate());
		inParamMap.put("end_date", offenderActionPlans.getEndDate());
		inParamMap.put("createUserId", offenderActionPlans.getCreateUserId());
		try {
			retVal = namedParameterJdbcTemplate.update(sql, inParamMap);
		} catch (Exception e) {
			retVal = 0;
			logger.error("insertOffenderCriminogenicNeeds", e);
		}
		return retVal;
	}
}


