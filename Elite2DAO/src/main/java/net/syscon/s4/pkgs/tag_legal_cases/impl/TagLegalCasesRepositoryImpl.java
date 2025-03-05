package net.syscon.s4.pkgs.tag_legal_cases.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.Orders;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.OffenderCases;
import net.syscon.s4.inst.casemanagement.beans.WorkFlowLogs;
import net.syscon.s4.inst.casemanagement.beans.WorkFlows;
import net.syscon.s4.inst.legals.au.OffenderBailConditions;
import net.syscon.s4.inst.legals.beans.CourtEvent;
import net.syscon.s4.inst.legals.beans.OcdapealBean;
import net.syscon.s4.inst.legals.beans.OffenderBailDetails;
import net.syscon.s4.inst.legals.beans.OffenderCaseIdentifiers;
import net.syscon.s4.inst.legals.beans.OffensesOutcome;
import net.syscon.s4.inst.legalscreens.maintenance.bean.OffenderCharges;
import net.syscon.s4.inst.schedules.bean.CourtEvents;
import net.syscon.s4.pkgs.GOffenceRecBean;
import net.syscon.s4.pkgs.tag_legal_cases.TagLegalCasesRepository;

@Repository
public class TagLegalCasesRepositoryImpl extends RepositoryBase implements TagLegalCasesRepository {

	private static Logger logger = LogManager.getLogger(TagLegalCasesRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> checkDataMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DISPOSITION", new FieldMapper("disposition"))
			.put("RESULT", new FieldMapper("result"))
//			.put("RANGE", new FieldMapper("range"))
			.put("OFFENSE_DATE", new FieldMapper("offenseDate"))
			.put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId"))
			.put("SENTENCE_SEQ", new FieldMapper("sentenceSeq"))
			.put("OFFENDER_CHARGE_ID", new FieldMapper("offenderChargeId"))
			.put("OFFENCE_DATE", new FieldMapper("offenseDate"))
			.put("OFFENCE_RANGE_DATE", new FieldMapper("offenceRangeDate"))
			.put("RESULT_CODE1_DESC", new FieldMapper("resultCode1Desc"))
			.put("RESULT_CODE2_DESC", new FieldMapper("resultCode2Desc"))
			.put("RESULT_CODE_1_INDICATOR", new FieldMapper("resultCode1Indicator"))
			.put("APPLY_FLAG", new FieldMapper("applyFlag"))
			.put("CHECK_SUM", new FieldMapper("checkSum"))
			.put("DESCRIPTION", new FieldMapper("offenseDescription")).build();
	
	private final Map<String, FieldMapper> getLinkcasesDetailsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CASE_ID", new FieldMapper("caseId")).put("CASE_SEQ", new FieldMapper("case_Seq"))
			.put("AGY_LOC", new FieldMapper("agy_loc_id")).put("INFO_NUMBER", new FieldMapper("caseInfoNumber"))
			.put("DESCRIPTION", new FieldMapper("description")).put("START_DATE", new FieldMapper("beginDate"))
			.put("INFO_PREFIX", new FieldMapper("caseInfoPrefix")).build();

	private final Map<String, FieldMapper> offChrgMapping = new ImmutableMap.Builder<String, FieldMapper>()

			.put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId"))
			.put("OFFENDER_CHARGE_ID", new FieldMapper("offenderChargeId"))
			.put("STATUTE_CODE", new FieldMapper("statuteCode")).put("OFFENCE_CODE", new FieldMapper("offenceCode"))
			.put("NO_OF_OFFENCES", new FieldMapper("noOfOffences")).put("OFFENCE_DATE", new FieldMapper("offenceDate"))
			.put("OFFENCE_RANGE_DATE", new FieldMapper("offenceRangeDate"))
			.put("PLEA_CODE", new FieldMapper("pleaCode")).put("PROPERTY_VALUE", new FieldMapper("propertyValue"))
			.put("TOTAL_PROPERTY_VALUE", new FieldMapper("totalPropertyValue"))
			.put("CJIT_OFFENCE_CODE_1", new FieldMapper("cjitOffenceCode1"))
			.put("CJIT_OFFENCE_CODE_2", new FieldMapper("cjitOffenceCode2"))
			.put("CJIT_OFFENCE_CODE_3", new FieldMapper("cjitOffenceCode3"))
			.put("CHARGE_STATUS", new FieldMapper("chargeStatus"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("RESULT_CODE_1", new FieldMapper("resultCode1")).put("RESULT_CODE_2", new FieldMapper("resultCode2"))
			.put("RESULT_CODE_1_INDICATOR", new FieldMapper("resultCode1Indicator"))
			.put("RESULT_CODE_2_INDICATOR", new FieldMapper("resultCode2Indicator"))
			.put("CASE_ID", new FieldMapper("caseId")).put("MOST_SERIOUS_FLAG", new FieldMapper("mostSeriousFlag"))
			.put("CHARGE_SEQ", new FieldMapper("chargeSeq")).put("ORDER_ID", new FieldMapper("orderId"))
			.put("LIDS_OFFENCE_NUMBER", new FieldMapper("lidsOffenceNumber"))
			.put("OFFENCE_TYPE", new FieldMapper("offenceType")).put("SEAL_FLAG", new FieldMapper("sealFlag")).build();

	private final Map<String, FieldMapper> mapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId")).put("EVENT_ID", new FieldMapper("eventId"))
			.put("OFFENDER_CHARGE_ID", new FieldMapper("offenderChargeId")).put("CASE_ID", new FieldMapper("caseId"))
			.put("OFFENCE_CODE", new FieldMapper("offenceCode")).put("STATUTE_CODE", new FieldMapper("statuteCode"))
			.put("MOST_SERIOUS_FLAG", new FieldMapper("mostSeriousFlag")).put("PLEA_CODE", new FieldMapper("pleaCode"))
			.put("PROPERTY_VALUE", new FieldMapper("propertyValue")).put("OFFENCE_DATE", new FieldMapper("offenceDate"))
			.put("OFFENCE_RANGE_DATE", new FieldMapper("offenceRangeDate"))
			.put("RESULT_CODE_1", new FieldMapper("resultCode1"))
			.put("RESULT_CODE_1_INDICATOR", new FieldMapper("resultCode1Indicator"))
			.put("RESULT_CODE_2", new FieldMapper("resultCode2"))
			.put("RESULT_CODE_2_INDICATOR", new FieldMapper("resultCode2Indicator"))
			.put("CJIT_OFFENCE_CODE_1", new FieldMapper("cjitOffenceCode1"))
			.put("CJIT_OFFENCE_CODE_2", new FieldMapper("cjitOffenceCode2"))
			.put("CJIT_OFFENCE_CODE_3", new FieldMapper("cjitOffenceCode3"))
			.put("NO_OF_OFFENCES", new FieldMapper("noOfOffences"))
			.put("TOTAL_PROPERTY_VALUE", new FieldMapper("totalPropertyValue"))
			.put("CHARGE_STATUS", new FieldMapper("chargeStatus")).put("CHECK_SUM", new FieldMapper("checkSum"))
			.put("OFFENCE_TYPE", new FieldMapper("offenceType")).build();

	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>().build();

	@Override
	public Long lWorkflowCur(final String from, final Integer eventId) {
		final String sql = getQuery("WORK_FLOW_INSERT_L_WORKFLOW_CUR");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("P_FROM", from, "P_EVENT_ID", eventId),
				Long.class);
	}

	@Override
	public Integer insertWorkFlows(final WorkFlows workFlows) {
		final String sql = getQuery("WORK_FLOW_INSERT_INSERT_WORK_FLOWS");
		return namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(workFlows));
	}

	@Override
	public Long getWorkFlowId() {
		final String sql = getQuery("WORK_FLOW_INSERT_GET_WORK_FLOW_ID");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Long.class);
	}

	@Override
	public Integer getStaffId() {
		final String sql = getQuery("WORK_FLOW_INSERT_GET_STAFF_ID");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Integer.class);
	}

	@Override
	public Integer insertWorkFlowLogs(final WorkFlowLogs workFlowLogs) {
		final String sql = getQuery("WORK_FLOW_INSERT_WORK_FLOW_LOGS");
		return namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(workFlowLogs));
	}

	@Override
	public Long getLWorkseq(final Long lWorkflowId) {
		final String sql = getQuery("WORK_FLOW_INSERT_L_WORK_SEQ");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("P_WORK_FLOW_ID", lWorkflowId), Long.class);
	}

	@Override
	public WorkFlowLogs getWorkFlowLogs(final Long lWorkflowId) {
		final String sql = getQuery("WORK_FLOW_INSERT_L_MOD_ACTION_CUR");
		WorkFlowLogs retVal = null;
		try {
			retVal = namedParameterJdbcTemplate.queryForObject(sql, createParams("P_WORK_FLOW_ID", lWorkflowId),
					WorkFlowLogs.class);
		} catch (Exception e) {
			logger.error(e);
		}
		return retVal;
	}

	@Override
	public Integer updateCourtEvents(final Integer eventId, final String userName) {
		final String sql = getQuery("WORK_FLOW_INSERT_UPDATE_COURT_EVENTS");
		return namedParameterJdbcTemplate.update(sql, createParams("P_EVENT_ID", eventId, "modifyUserId", userName));
	}

	@Override
	public Integer eventChargesInsert(final CourtEvent courtEvent) {
		final String sql = getQuery("Event_Charges_Insert");
		Integer retVal = null;
		try {
			retVal = namedParameterJdbcTemplate.update(sql,
					createParams("p_event_id", courtEvent.getEventId(), "p_outcome_reason_code",
							courtEvent.getOutcomeReasonCode(), "p_result_code_1_ind",
							courtEvent.getResultCode1Indicator(), "p_offender_book_id", courtEvent.getOffenderBookId(),
							"p_case_id", courtEvent.getCaseId(), "createUserId", courtEvent.getCreateUserId()));
		} catch (Exception e) {
			logger.error("eventChargesInsert :", e);
		}
		return retVal;
	}

	@Override
	public Boolean isOrderExist(final BigDecimal offenderBookId, final Long eventId) {
		final String sql = getQuery("GET_ORDERS_DETAILS");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("offenderBookId", offenderBookId, "eventId", eventId), Boolean.class);
	}

	public CourtEvents getAgyLocIdEventDate(final Long eventId) {
		final String sql = getQuery("GET_AGY_LOC_ID_EVENT_DATE");
		CourtEvents retObj = null;
		try {
			retObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("eventId", eventId),
					CourtEvents.class);
		} catch (Exception e) {
			logger.error("getVOffenderAllSchedules :", e);
		}
		return retObj;
	}

	public List<Orders> ordersInsertCasePlans(final Orders lstOrders) {
		final String sql = getQuery("INSERT_INTO_ORDERS");
		int[] returnArray = new int[] {};
		final List<Orders> returnList = new ArrayList<>();
		Long eid = lstOrders.getEventId();
		eid = eid + 1;
		lstOrders.setEventId(eid);
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		parameters.add(new BeanPropertySqlParameterSource(lstOrders));
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (returnArray.length == 1) {
			return returnList;
		}
		return returnList;
	}

	@Override
	public String getConditionDesc(final String commConditionCode, final String commConditionType,
			final Long offenderBookId) {
		final String sql = getQuery("Get_Condition_Desc");
		String returnDesc = null;
		try {
			returnDesc = namedParameterJdbcTemplate.queryForObject(sql, createParams("P_COM_TYPE", commConditionType,
					"P_COM_CODE", commConditionCode, "P_OFF_BKG_ID", offenderBookId), String.class);
		} catch (Exception e) {
			logger.error("getConditionDesc :", e);
		}
		return returnDesc;
	}

	@Override
	public Date getBookingStartDate(final Long offenderBookId) {
		final String sql = getQuery("NEXT_EVENTS_INSERT_GET_BOOKING_START_DATE");
		Date retVal = null;
		try {
			retVal = namedParameterJdbcTemplate.queryForObject(sql, createParams("P_OFFENDER_BOOK_ID", offenderBookId),
					Date.class);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return retVal;
	}

	@Override
	public Date getLatestMovement(final Long offenderBookId) {
		final String sql = getQuery("NEXT_EVENTS_INSERT_GET_GET_LATEST_MOVEMENT");
		Date retVal = null;
		try {
			retVal = namedParameterJdbcTemplate.queryForObject(sql, createParams("P_OFFENDER_BOOK_ID", offenderBookId),
					Date.class);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return retVal;
	}

	@Override
	public Integer getEventId() {
		final String sql = getQuery("NEXT_EVENTS_INSERT_EVENT_ID");
		Integer eventId = null;
		try {
			eventId = namedParameterJdbcTemplate.queryForObject(sql, createParams(), Integer.class);
		} catch (Exception e) {
			logger.error(e);
		}
		return eventId;
	}

	@Override
	public Integer insertCourtEvents(final CourtEvents courtEvents) {
		final String sql = getQuery("NEXT_EVENTS_INSERT_INSER_INTO_COURT_EVENTS");
		Integer returnValue = null;
		try {
			returnValue = namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(courtEvents));
		} catch (Exception e) {
			logger.error(e);
		}
		return returnValue;

	}

	@Override
	public Integer insertCourtEventCharges(final OffenderCharges offenderCharges) {
		final String sql = getQuery("NEXT_EVENTS_INSERT_INSER_INTO_COURT_EVENT_CHARGES");
		Integer returnValue = null;
		try {
			returnValue = namedParameterJdbcTemplate.update(sql,
					createParams("P_EVENT_ID", offenderCharges.getEventId(), "P_OFFENDER_BOOK_ID",
							offenderCharges.getOffenderBookId(), "P_CASE_ID", offenderCharges.getCaseId(),
							"P_OUTCOME_REASON_CODE", null, "P_RESULT_CODE_1_IND", null, "createUserId",
							offenderCharges.getCreateUserId()));
		} catch (Exception e) {
			logger.error(e);
		}
		return returnValue;
	}

	@Override
	public Integer insertOffenderCharges(final GOffenceRecBean obj) {
		final String sql = getQuery("INSERT_OFFENDER_CHARGES");
		Integer retVal = null;
		try {
			retVal = namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(obj));
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return retVal;
	}

	@Override
	public Long getOffChargeId() {
		final String sql = getQuery("GET_OFFENDER_CHARGE_ID");
		Long offChargId = null;
		try {
			offChargId = namedParameterJdbcTemplate.queryForObject(sql, createParams(), Long.class);
		} catch (Exception e) {
			logger.error("getOffChargeId :", e);
		}
		return offChargId;
	}

	@Override
	public Integer insertCourtEventCharges(final Integer eventId, final Long vOffenderChargeId,
			final Long offenderChargeId, final String pleaCode, final String resultcode1, final String resultCode2,
			final String resultCode1Indicator, final String resultCode2Indicator, final Long propertyValue,
			final String mostSeriousFlag, final Long noOfOffence, final Long totalPropertyValue, final Date offenceDate,
			final Date offenceRangeDate, final String cjitOffenceCode1, final String cjitOffenceCode2,
			final String cjitOffenceCode3, final String userName) {
		final String sql = getQuery("INSERT_COURT_EVENT_CHARGES");
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		Integer retVal = 0;
		inParamMap.put("EVENTID", eventId);
		inParamMap.put("VOFFENDERCHARGEID", vOffenderChargeId);
		inParamMap.put("OFFENDERCHARGEID", offenderChargeId);
		inParamMap.put("PLEACODE", pleaCode);
		inParamMap.put("RESULTCODE1", resultcode1);
		inParamMap.put("RESULTCODE2", resultCode2);
		inParamMap.put("RESULTCODE1INDICATOR", resultCode1Indicator);
		inParamMap.put("RESULTCODE2INDICATOR", resultCode2Indicator);
		inParamMap.put("PROPERTYVALUE", propertyValue);
		inParamMap.put("MOSTSERIOUSFLAG", mostSeriousFlag);
		inParamMap.put("NOOFOFFENCES", noOfOffence);
		inParamMap.put("TOTALPROPERTYVALUE", totalPropertyValue);
		inParamMap.put("OFFENCEDATE", offenceDate);
		inParamMap.put("OFFENCERANGEDATE", offenceRangeDate);
		inParamMap.put("CJITOFFENCECODE1", cjitOffenceCode1);
		inParamMap.put("CJITOFFENCECODE2", cjitOffenceCode2);
		inParamMap.put("CJITOFFENCECODE3", cjitOffenceCode3);
		inParamMap.put("createUserId", userName);

		try {
			retVal = namedParameterJdbcTemplate.update(sql, inParamMap);
		} catch (Exception e) {
			logger.error("insertCourtEventCharges", e);
			retVal = 0;
		}
		return retVal;
	}

	@Override
	public List<OffensesOutcome> pRefCurs(final Long OffenderBookId, final Long SetenceSeq) {
		final String sql = getQuery("SENTENCE_SEQ_QUERY");
		List<OffensesOutcome> returnList = new ArrayList<OffensesOutcome>();
		final RowMapper<OffensesOutcome> mRowMapper = Row2BeanRowMapper.makeMapping(sql, OffensesOutcome.class,
				checkDataMapping);
		try {
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("p_offender_book_id", OffenderBookId, "p_sentence_seq", SetenceSeq), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("pRefCurs :", e);
		}
		return returnList;
	}

	@Override
	public Integer updateCasesAgyLocId(final String agyLocId, final Long caseId, final String userName) {
		final String sql = getQuery("UPDATE_OFFENDER_CASES");
		return namedParameterJdbcTemplate.update(sql,
				createParams("P_AGY_LOC_ID", agyLocId, "P_CASE_ID", caseId, "modifyUserId", userName));
	}

	@Override
	public Integer updateOrderCourtDate(final Long offenderBookId, final Integer eventId, final Date eventDate,
			final String userName) {
		final String sql = getQuery("UPDATE_ORDER_COURT_DATE");
		Integer retVal = null;
		try {
			retVal = namedParameterJdbcTemplate.update(sql, createParams("P_EVENT_DATE", eventDate,
					"P_OFFENDER_BOOK_ID", offenderBookId, "P_EVENT_ID", eventId, "modifyUserId", userName));
		} catch (Exception e) {
			retVal = 0;
			logger.error("updateOrderCourtDate :", e);
		}
		return retVal;
	}

	@Override
	public List<OffenderCharges> getOffencesQuery(final Integer pEventId, final String pOffenceCode,
			final String pStatuteCode, final String pPleaCode, final Date pOffenceDate, final Date pOffenceRangeDate,
			final String pResultCode1, final String pResultCode2, final String pOffenceType) {
		final String sql = getQuery("OFFENCES_QUERY");
		List<OffenderCharges> retObj = new ArrayList<OffenderCharges>();
		final RowMapper<OffenderCharges> rowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderCharges.class, mapping);
		try {
			retObj = namedParameterJdbcTemplate.query(sql,
					createParams("P_EVENT_ID", pEventId, "P_OFFENCE_CODE", pOffenceCode, "P_STATUTE_CODE", pStatuteCode,
							"P_PLEA_CODE", pPleaCode, "P_OFFENCE_DATE", pOffenceDate, "P_OFFENCE_RANGE_DATE",
							pOffenceRangeDate, "P_RESULT_CODE_1", pResultCode1, "P_RESULT_CODE_2", pResultCode2,
							"P_OFFENCE_TYPE", pOffenceType),
					rowMapper);
		} catch (Exception e) {
			logger.error("getOffencesQuery :" + e);
			retObj = null;
		}
		return retObj;
	}

	@Override
	public String getOffeCodeDesc(final String pOffenceCode, final String pStatuteCode) {
		final String sql = getQuery("DESCRIPTION_CUR_ONE");
		String desc = null;
		try {
			desc = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("P_OFFENCE_CODE", pOffenceCode, "P_STATUTE_CODE", pStatuteCode), String.class);
		} catch (Exception e) {
			logger.error("getOffeCodeDesc :" + e);
			desc = null;
		}
		return desc;
	}

	@Override
	public String getResultCodeDesc(final String pResultCode) {
		final String sql = getQuery("DESCRIPTION_CUR_SECOND");
		String desc = null;
		try {
			desc = namedParameterJdbcTemplate.queryForObject(sql, createParams("P_RESULT_CODE", pResultCode),
					String.class);
		} catch (Exception e) {
			logger.error("getResultCodeDesc :" + e);
			desc = null;
		}
		return desc;
	}

	public List<OffenderCases> getLinkcaseDetails(final BigDecimal pCaseSeq, final Long pOffenderBookId,
			final Integer pCombinedCaseId) {
		List<OffenderCases> resultList = new ArrayList<OffenderCases>();
		final String sql = getQuery("GET_LINKCASE_DETAILS");
		final RowMapper<OffenderCases> rowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderCases.class,
				getLinkcasesDetailsMapping);
		try {
			resultList = namedParameterJdbcTemplate.query(sql, createParams("P_CASE_SEQ", pCaseSeq,
					"P_OFFENDER_BOOK_ID", pOffenderBookId, "P_COMBINED_CASE_ID", pCombinedCaseId), rowMapper);
		} catch (Exception e) {
			logger.error("getLinkcaseDetails" + e);
		}
		return resultList;
	}

	@Override
	public List<OffenderCharges> getOffCharges(final Long pCaseId, final Long pOffBookId) {
		final String sql = getQuery("GET_OFF_CHARGES");
		List<OffenderCharges> returnList = new ArrayList<OffenderCharges>();
		final RowMapper<OffenderCharges> rowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderCharges.class,
				offChrgMapping);
		try {
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("P_OFF_BOOK_ID", pOffBookId, "L_CASE_ID", pCaseId), rowMapper);
		} catch (Exception e) {
			logger.error("getOffCharges :" + e);
			returnList = null;
		}
		return returnList;
	}

	@Override
	public Long getLinkDetails(final Long pCaseId) {
		final String sql = getQuery("GET_LINK_DETAILS");
		Long retCaseId = null;
		try {
			retCaseId = namedParameterJdbcTemplate.queryForObject(sql, createParams("P_CASE_ID", pCaseId), Long.class);
		} catch (Exception e) {
			logger.error("getLinkDetails :" + e);
			retCaseId = null;
		}
		return retCaseId;
	}

	@Override
	public Integer updateOffeCases(final Long pCaseId1, final String pCaseStatus, final Integer pStaffId,
			final Long pCaseId, final String userName) {
		final String sql = getQuery("UPDATE_OFFENDER_CASES");
		Integer retVal = 0;
		try {
			retVal = namedParameterJdbcTemplate.update(sql, createParams("P_CASE_ID1", pCaseId1, "PCASESTATUS",
					pCaseStatus, "PSTAFFID", pStaffId, "P_CASE_ID", pCaseId, "modifyUserId", userName));
		} catch (DataAccessException dae) {
			logger.error("updateOffeCases :" + dae);
			retVal = 0;
		}
		return retVal;
	}

	@Override
	public String getTerminationStatus(final String pReasonCode) {
		final String sql = getQuery("GET_ACTIVE_TYPE");
		String type = null;
		try {
			type = namedParameterJdbcTemplate.queryForObject(sql, createParams("P_REASON_CODE", pReasonCode),
					String.class);
		} catch (Exception e) {
			logger.error("getActiveTypeForLinkCases :" + e);
			type = null;
		}
		return type;
	}

	@Override
	public Integer insertLinkCaseTxns(final Long pCaseId, final Long pCaseId1, final Long pOffenderChargeId,
			final Integer pEventId, final String userName) {
		final String sql = getQuery("INSERT_INTO_LINK_CASE_TXNS_ONE");
		Integer retVal = 0;
		try {
			retVal = namedParameterJdbcTemplate.update(sql, createParams("P_CASE_ID", pCaseId, "P_CASE_ID1", pCaseId1,
					"POFFENDER_CHARGE_ID", pOffenderChargeId, "P_EVENT_ID", pEventId, "createUserId", userName));
		} catch (DataAccessException dae) {
			logger.error("insertLinkCaseTxns :" + dae);
			retVal = 0;
		}
		return retVal;
	}

	@Override
	public Integer insertCourtEventCharges(final OffenderCharges bean, final Integer pEventId) {
		final String sql = getQuery("INSERT_INTO_COURT_EVENT_CHARGES");
		Integer retVal = 0;
		final Map<String, Object> map = new HashMap<String, Object>();
		map.put("P_EVENT_ID", pEventId);
		map.put("OFFENDER_CHARGE_ID", bean.getOffenderChargeId());
		map.put("PLEA_CODE", bean.getPleaCode());
		map.put("RESULT_CODE_1", bean.getResultCode1());
		map.put("RESULT_CODE_2", bean.getResultCode2());
		map.put("RESULT_CODE_1_INDICATOR", bean.getResultCode1Indicator());
		map.put("RESULT_CODE_2_INDICATOR", bean.getResultCode2Indicator());
		map.put("MOST_SERIOUS_FLAG", bean.getMostSeriousFlag());
		map.put("CREATE_DATETIME", bean.getCreateDatetime());
		map.put("CREATE_USER_ID", bean.getCreateUserId());
		map.put("MODIFY_DATETIME", bean.getModifyDatetime());
		map.put("PROPERTY_VALUE", bean.getPropertyValue());
		map.put("TOTAL_PROPERTY_VALUE", bean.getTotalPropertyValue());
		map.put("NO_OF_OFFENCES", bean.getNoOfOffences());
		map.put("OFFENCE_DATE", bean.getOffenceDate());
		map.put("OFFENCE_RANGE_DATE", bean.getOffenceRangeDate());

		try {
			retVal = namedParameterJdbcTemplate.update(sql, map);
		} catch (DataAccessException dae) {
			logger.error("insertCourtEventCharges :" + dae);
			retVal = 0;
		}
		return retVal;
	}

	@Override
	public Integer insertLinkCaseTxnsSecond(final Long pCaseId, final Long pCaseId1, final Long pOffenderChargeId,
			final String userName) {
		final String sql = getQuery("INSERT_INTO_LINK_CASE_TXNS_SECOND");
		Integer retVal = 0;
		try {
			retVal = namedParameterJdbcTemplate.update(sql, createParams("P_CASE_ID", pCaseId, "P_CASE_ID1", pCaseId1,
					"POFFENDER_CHARGE_ID", pOffenderChargeId, "createUserId", userName));
		} catch (DataAccessException dae) {
			logger.error("insertLinkCaseTxnsSecond :" + dae);
			retVal = 0;
		}
		return retVal;
	}

	@Override
	public Integer updateOffenderCharges(final Long pCaseId1, final Long pCaseId, final Long pOffBookId,
			final String userName) {
		final String sql = getQuery("UPDATE_OFFENDER_CHARGES");
		Integer retVal = 0;
		try {
			retVal = namedParameterJdbcTemplate.update(sql, createParams("P_CASE_ID1", pCaseId1, "P_CASE_ID", pCaseId,
					"P_OFF_BOOK_ID", pOffBookId, "modifyUserId", userName));
		} catch (DataAccessException dae) {
			logger.error("updateOffenderCharges :" + dae);
			retVal = 0;
		}
		return retVal;
	}

	@Override
	public List<OffenderCharges> getChargeDetails(final Long pCaseId, final Long lChargeId, final Long pOffBookId) {
		final String sql = getQuery("GET_CHARGE_DETAILS");
		List<OffenderCharges> returnList = new ArrayList<OffenderCharges>();
		final RowMapper<OffenderCharges> rowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderCharges.class,
				offChrgMapping);
		try {
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("P_OFF_BOOK_ID", pOffBookId, "L_CHARGE_ID", lChargeId, "P_CASE_ID", pCaseId),
					rowMapper);
		} catch (Exception e) {
			logger.error("getChargeDetails :" + e);
			returnList = null;
		}
		return returnList;
	}

	@Override
	public List<OffenderCharges> eventChgUpCur(final Long eventId) {
		final String sql = getQuery("SELECT_EVENT_CHARGES");
		final RowMapper<OffenderCharges> mRowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderCharges.class,
				checkDataMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("p_event_id", eventId), mRowMapper);

	}

	@Override
	public Integer updateCourteventcharges(final String outcomeReasonCode, final String resultCodeIndicator,
			final Long offenderChardid, final String userName) {
		final String sql = getQuery("UPDATE_COURT_EVENT_CHARGES_DUP");
		int count = 0;
		try {
			count = namedParameterJdbcTemplate.update(sql,
					createParams("p_outcome_reason_code", outcomeReasonCode, "p_result_code_1_indicator",
							resultCodeIndicator, "offender_charge_id", offenderChardid, "modifyUserId", userName));
		} catch (EmptyResultDataAccessException e) {
			logger.error("clearCourseAttendances", e);
		}
		return count;
	}

	@Override
	public OffenderCharges selectStatus(final Long offenderCgargeId) {
		final String sql = getQuery("SELECT_STATUS_CURSOR");
		final RowMapper<OffenderCharges> mRowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderCharges.class,
				checkDataMapping);
		OffenderCharges offeChrg = new OffenderCharges();
		try {
			offeChrg = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("p_offender_charge_id", offenderCgargeId), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("selectStatus", e);
		}
		return offeChrg;
	}

	@Override
	public Integer updateOffenderCharges(final OffenderCharges offchrge, final Long offenderChargeId) {
		final String sql = getQuery("OFFENDER_CHARGES_UPDATE");
		Integer count = null;
		try {
			count = namedParameterJdbcTemplate.update(sql,
					createParams("v_result_code", offchrge.getResultCode1(), "v_disposition_code",
							offchrge.getResultCode1Indicator(), "v_charge_status", offchrge.getChargeStatus(),
							"offender_charge_id", offenderChargeId, "modifyUserId", offchrge.getModifyUserId()));
		} catch (Exception e) {
			logger.error("updateOffenderCharges", e);
		}
		return count;
	}

	@Override
	public Integer offenderChargesUpdateOne(final String outcomeReasonCode, final String resultCodeIndicator,
			final String chargeStatus, final Long offenderChargeId, final String userName) {
		final String sql = getQuery("UPDATE_OFFENDER_CHARGESONE");
		Integer count = null;
		try {
			count = namedParameterJdbcTemplate.update(sql,
					createParams("p_outcome_reason_code", outcomeReasonCode, "p_result_code_1_indicator",
							resultCodeIndicator, "p_charge_status", chargeStatus, "p_offender_charge_id",
							offenderChargeId, "modifyUserId", userName));
		} catch (Exception e) {
			logger.error("offenderChargesUpdateOne", e);
		}
		return count;
	}

	@Override
	public String isOrderExist(final Long offenderBookId, final Long eventId) {
		final String sql = getQuery("IS_ORDER_EXIST");
		final String result = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("p_offender_book_id", offenderBookId, "p_event_id", eventId), String.class);
		return result;
	}

	@Override
	public CourtEvents eventCur(final Long eventId) {
		final String sql = getQuery("EVENT_CUR");
		final RowMapper<CourtEvents> mRowMapper = Row2BeanRowMapper.makeMapping(sql, CourtEvents.class,
				checkDataMapping);
		CourtEvents courseEvent = new CourtEvents();
		try {
			courseEvent = namedParameterJdbcTemplate.queryForObject(sql, createParams("p_event_id", eventId),
					mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("eventCur", e);
		}
		return courseEvent;
	}

	@Override
	public Integer insertOrders(final CourtEvents events) {
		final String sql = getQuery("ORDER_INSERT_QUERY");
		Integer count = null;
		count = namedParameterJdbcTemplate.update(sql, createParams("createUserId", events.getCreateUserId()));
		return count;
	}

	@Override
	public String lAgyLoc(final Long lEventId) {
		final String sql = getQuery("L_AGY_LOC");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("l_event_id", lEventId), String.class);
	}

	@Override
	public String lCourtType(final String pAgyLocId) {
		final String sql = getQuery("L_COURT_TYPE");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("p_agy_loc_id", pAgyLocId), String.class);
	}

	@Override
	public Integer getSentences(final Integer offenderBookId, final Integer caseId) {
		final String sql = getQuery("GET_SENTENCES");
		Integer retVal = null;
		try {
			retVal = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("p_off_book_id", offenderBookId, "p_case_id", caseId), Integer.class);
		} catch (Exception dae) {
			logger.error("getSentences :" + dae);
		}
		return retVal;
	}

	@Override
	public List<OffensesOutcome> chargeSentencesQuery(final Long caseId, final Long offenderBookId) {
		final String sql = getQuery("CHARGE_SENTENCES_QUERY_IF_INST");
		List<OffensesOutcome> list = new ArrayList<>();
		final RowMapper<OffensesOutcome> mRowMapper = Row2BeanRowMapper.makeMapping(sql, OffensesOutcome.class,
				checkDataMapping);
		try {
			list = namedParameterJdbcTemplate.query(sql,
					createParams("p_offender_book_id", offenderBookId, "p_case_id", caseId), mRowMapper);
		} catch (Exception e) {
			logger.error("CHARGE_SENTENCES_QUERY :" + e);
		}
		return list;
	}

	@Override
	public List<OffensesOutcome> chargeSentencesQueryComm(final Long caseId, final Long offenderBookId,
			final Long pSentenceSeq) {
		final String sql = getQuery("CHARGE_SENTENCES_QUERY_IF_COMM");
		List<OffensesOutcome> list = new ArrayList<>();
		final RowMapper<OffensesOutcome> mRowMapper = Row2BeanRowMapper.makeMapping(sql, OffensesOutcome.class,
				checkDataMapping);
		try {
			list = namedParameterJdbcTemplate.query(sql, createParams("p_offender_book_id", offenderBookId, "p_case_id",
					caseId, "p_sentence_seq", pSentenceSeq), mRowMapper);
		} catch (Exception e) {
			logger.error("CHARGE_SENTENCES_QUERY_COMM :" + e);
		}
		return list;
	}

	@Override
	public List<OffensesOutcome> chargeSentencesQueryCommOne(final Long caseId, final Long offenderBookId,
			final Long pSentenceSeq) {
		final String sql = getQuery("CHARGE_SENTENCES_QUERY_ONE");
		List<OffensesOutcome> list = new ArrayList<>();
		final RowMapper<OffensesOutcome> mRowMapper = Row2BeanRowMapper.makeMapping(sql, OffensesOutcome.class,
				checkDataMapping);
		try {
			list = namedParameterJdbcTemplate.query(sql, createParams("p_offender_book_id", offenderBookId, "p_case_id",
					caseId, "p_sentence_seq", pSentenceSeq), mRowMapper);
		} catch (Exception e) {
			logger.error("CHARGE_SENTENCES_QUERY_ONE:" + e);
		}
		return list;
	}

	@Override
	public Integer offenderSentenceCharges(final OffensesOutcome off) {
		final String sql = getQuery("OFFENDER_SENTENCE_CHARGES");
		return namedParameterJdbcTemplate.update(sql,
				createParams("p_offender_book_id", off.getOffenderBookId(), "p_sentence_seq", off.getSentenceSeq(),
						"p_id", off.getOffenderChargeId(), "createUserId", off.getCreateUserId()));

	}

	@Override
	public List<Object[]> unlinkCur(final CourtEvent court) {
		final String sql = getQuery("UNLINK_CUR");
		final RowMapper<Object[]> mRowMapper = Row2BeanRowMapper.makeMapping(sql, Object[].class, checkDataMapping);
		return namedParameterJdbcTemplate.query(sql,
				createParams("p_case_id", court.getCaseId(), "p_case_id1", court.getCaseIdl()), mRowMapper);
	}

	@Override
	public Integer offenderCharges(final Long offenderChargeId, final Long cseId, final String userName) {
		final String sql = getQuery("OFFENDER_CHARGES");
		return namedParameterJdbcTemplate.update(sql,
				createParams("offender_charge_id", offenderChargeId, "p_case_id", cseId, "modifyUserId", userName));
	}

	@Override
	public Integer courtEventCharges(final Long offenderChargeId, final Long eventId) {
		final String sql = getQuery("COURT_EVENT_CHARGES");
		return namedParameterJdbcTemplate.update(sql,
				createParams("offender_charge_id", offenderChargeId, "event_id", eventId));
	}

	@Override
	public Integer linkCaseTxns(final Long caseload, final Long caseload1) {
		final String sql = getQuery("LINK_CASE_TXNS");
		return namedParameterJdbcTemplate.update(sql, createParams("p_case_id", caseload, "p_case_id1", caseload1));
	}

	@Override
	public Integer offenderCasesUpdateFirst(final Long caseload, final Long offenderBookId, final String userName) {
		final String sql = getQuery("OFFENDER_CASES_UPDATE_FIRST");
		return namedParameterJdbcTemplate.update(sql,
				createParams("p_case_id", caseload, "p_off_book_id", offenderBookId, "modifyUserId", userName));
	}

	@Override
	public Integer lSentEventCur() {
		final String sql = getQuery("L_SENT_EVENT_CUR");
		return namedParameterJdbcTemplate.update(sql, createParams());
	}

	@Override
	public Integer offenderSentencesHty(final Long pOffenderBookId, final Long pSentenceSeq) {
		final String sql = getQuery("OFFENDER_SENTENCES_HTY");
		return namedParameterJdbcTemplate.update(sql,
				createParams("p_offender_book_id", pOffenderBookId, "p_sentence_seq", pSentenceSeq));
	}

	@Override
	public List<Long> chgCur(final Long pOffenderBookId, final Long pSentenceSeq) {
		final String sql = getQuery("CHG_CUR");
		final RowMapper<Long> mRowMapper = Row2BeanRowMapper.makeMapping(sql, Long.class, checkDataMapping);
		return namedParameterJdbcTemplate.query(sql,
				createParams("p_offender_book_id", pOffenderBookId, "p_sentence_seq", pSentenceSeq), mRowMapper);
	}

	@Override
	public Integer offenderChargesHty(final Long vChgId) {
		final String sql = getQuery("OFFENDER_CHARGES_HTY");
		return namedParameterJdbcTemplate.update(sql, createParams("v_chg_id", vChgId));
	}

	@Override
	public Integer offenderSentenceTermsHty(final Long pOffenderBookId, final Long pSentenceSeq) {
		final String sql = getQuery("OFFENDER_SENTENCE_TERMS_HTY");
		return namedParameterJdbcTemplate.update(sql,
				createParams("p_offender_book_id", pOffenderBookId, "p_sentence_seq", pSentenceSeq));
	}

	@Override
	public Integer offenderSentConditionsHty(final Long pOffenderBookId, final Long pSentenceSeq) {
		final String sql = getQuery("OFFENDER_SENT_CONDITIONS_HTY");
		return namedParameterJdbcTemplate.update(sql,
				createParams("p_offender_book_id", pOffenderBookId, "p_sentence_seq", pSentenceSeq));
	}

	@Override
	public Integer getLAppExists(final Long pAppealId, final Long pOffChg, final Long pOffBkgId) {
		final String sql = getQuery("L_APP_EXISTS");
		Integer retVal = 0;
		try {
			retVal = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("P_APPEAL_ID", pAppealId, "P_OFF_CHG", pOffChg, "P_OFF_BKG_ID", pOffBkgId),
					Integer.class);
		} catch (Exception e) {
			logger.error("getLAppExists :" + e);
		}
		return retVal;
	}

	@Override
	public List<Long> getLSentCur(final Long pOffChg, final Long pOffBkgId) {
		final String sql = getQuery("L_SENT_CUR");
		List<Long> retVal = new ArrayList<Long>();
		final RowMapper<Long> mRowMapper = Row2BeanRowMapper.makeMapping(sql, Long.class, mMapping);
		try {
			retVal = namedParameterJdbcTemplate.query(sql,
					createParams("P_OFFENDER_CHARGE", pOffChg, "P_OFF_BKG_ID", pOffBkgId), mRowMapper);
		} catch (Exception e) {
			logger.error("getLSentCur :" + e);
		}
		return retVal;
	}

	@Override
	public Integer insertIntoAppealOffenderCharges(final OcdapealBean appealData) {
		final String sql = getQuery("INSERT_INTO_APPEAL_OFFENDER_CHARGES");
		Integer retVal = 0;
		try {
			retVal = namedParameterJdbcTemplate.update(sql,
					createParams("P_APPEAL_ID", appealData.getAppealId(), "P_OFF_BKG_ID",
							appealData.getOffenderBookId(), "P_OFF_CHG", appealData.getOffenderChargeId(), "P_ORDER_ID",
							null, "CREATEUSERID", appealData.getCreateUserId()));
		} catch (DataAccessException e) {
			logger.error("insertIntoAppealOffenderCharges :" + e);
		}
		return retVal;
	}

	@Override
	public Integer updateOfCourtEventCharges(final OcdapealBean appealData) {
		final String sql = getQuery("UPDATE_OF_COURT_EVENT_CHARGES");
		Integer retVal = 0;
		try {
			retVal = namedParameterJdbcTemplate.update(sql, createParams("P_EVENT_ID", appealData.getEventId(),
					"P_OFF_CHG", appealData.getOffenderChargeId(), "MODIFYUSERID", appealData.getModifyUserId()));
		} catch (DataAccessException e) {
			logger.error("updateCourtEventCharges :" + e);
		}
		return retVal;
	}

	@Override
	public Integer updateOfOffenderCharges(final Long pOffChg, final String modifyUserId) {
		final String sql = getQuery("UPDATE_OF_OFFENDER_CHARGES");
		Integer retVal = 0;
		try {
			retVal = namedParameterJdbcTemplate.update(sql,
					createParams("MODIFYUSERID", modifyUserId, "P_OFF_CHG", pOffChg));
		} catch (DataAccessException e) {
			logger.error("updateOfOffenderCharges :" + e);
		}
		return retVal;
	}

	@Override
	public Integer insertOfAppealOffenderSentences(final OcdapealBean appealData, final Long lSentSeq) {
		final String sql = getQuery("INSERT_OF_APPEAL_OFFENDER_SENTENCES");
		Integer retVal = 0;
		try {
			retVal = namedParameterJdbcTemplate.update(sql,
					createParams("P_APPEAL_ID", appealData.getAppealId(), "P_OFF_BKG_ID",
							appealData.getOffenderBookId(), "L_SENT_SEQ", lSentSeq, "CREATEUSERID",
							appealData.getCreateUserId()));
		} catch (DataAccessException e) {
			logger.error("insertOfAppealOffenderSentences :" + e);
		}
		return retVal;
	}

	@Override
	public Integer deleteOfAppealOffenderCharges(final OcdapealBean appealData) {
		final String sql = getQuery("DELETE_OF_APPEAL_OFFENDER_CHARGES");
		Integer retVal = 0;
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "APPEAL_OFFENDER_CHARGES AOC";
			String whereCondition = "AOC.APPEAL_ID = :P_APPEAL_ID AND AOC.OFFENDER_CHARGE_ID = :P_OFF_CHG AND AOC.OFFENDER_BOOK_ID = :P_OFF_BKG_ID";
			inputMap.put("P_APPEAL_ID", appealData.getAppealId());
			inputMap.put("P_OFF_CHG", appealData.getOffenderChargeId());
			inputMap.put("P_OFF_BKG_ID", appealData.getOffenderBookId());
			inputMap.put("modifyUserId", appealData.getModifyUserId());
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteOfAppealOffenderCharges " + e.getMessage());
		}
		try {
			retVal = namedParameterJdbcTemplate.update(sql, createParams("P_APPEAL_ID", appealData.getAppealId(),
					"P_OFF_CHG", appealData.getOffenderChargeId(), "P_OFF_BKG_ID", appealData.getOffenderBookId()));
		} catch (DataAccessException e) {
			logger.error("deleteOfAppealOffenderCharges :" + e);
		}
		return retVal;
	}

	@Override
	public Integer updateOfCourtEventChargesOne(final OcdapealBean appealData) {
		final String sql = getQuery("UPDATE_OF_COURT_EVENT_CHARGES_ONE");
		return namedParameterJdbcTemplate.update(sql, createParams("MODIFYUSERID", appealData.getModifyUserId(),
				"P_EVENT_ID", appealData.getEventId(), "P_OFF_CHG", appealData.getOffenderChargeId()));
	}

	@Override
	public Integer updateOfOffenderChargesOne(final Long pOffChg, final String modifyUserId) {
		final String sql = getQuery("UPDATE_OF_OFFENDER_CHARGES_ONE");
		return namedParameterJdbcTemplate.update(sql, createParams("MODIFYUSERID", modifyUserId, "P_OFF_CHG", pOffChg));
	}

	@Override
	public Integer deleteOfAppealOffenderSentencesOne(final OcdapealBean appealData, final Long lSentSeq) {
		final String sql = getQuery("DELETE_OF_APPEAL_OFFENDER_SENTENCES_ONE");
		try {
			String tabaleName = "APPEAL_OFFENDER_SENTENCES AOS";
			String whereCondition = "WHERE AOS.APPEAL_ID = :P_APPEAL_ID AND AOS.OFFENDER_BOOK_ID = :P_OFF_BKG_ID AND AOS.SENTENCE_SEQ = :L_SENT_SEQ AND NOT EXISTS ( SELECT NULL FROM APPEAL_OFFENDER_CHARGES AOC, OFFENDER_SENTENCE_CHARGES OSC WHERE AOC.OFFENDER_CHARGE_ID = OSC.OFFENDER_CHARGE_ID AND AOC.OFFENDER_BOOK_ID = OSC.OFFENDER_BOOK_ID AND OSC.SENTENCE_SEQ = :L_SENT_SEQ)";
			Map<String, Object> inputMap = new HashMap<String, Object>();
			inputMap.put("P_APPEAL_ID", appealData.getAppealId());
			inputMap.put("P_OFF_BKG_ID", appealData.getOffenderBookId());
			inputMap.put("L_SENT_SEQ", lSentSeq);
			inputMap.put("modifyUserId", appealData.getModifyUserId());
			updatePreDeletedRow(tabaleName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(e);
		}
		return namedParameterJdbcTemplate.update(sql, createParams("P_APPEAL_ID", appealData.getAppealId(),
				"P_OFF_BKG_ID", appealData.getOffenderBookId(), "L_SENT_SEQ", lSentSeq));
	}

	@Override
	public String getRcDescription(final String domain, final String code) {
		final String sql = getQuery("GET_RC_DESCRIPTION");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("P_DOMAIN", domain, "P_CODE", code),
				String.class);
	}

	@Override
	public Integer lEventCur(final Integer pEventId, final Integer pCaseId, final String pEventType) {
		final String sql = getQuery("L_EVENT_CUR");
		return namedParameterJdbcTemplate.update(sql,
				createParams("P_EVENT_ID", pEventId, "P_CASE_ID", pCaseId, "P_EVENT_TYPE", pEventType));
	}

	@Override
	public List<OffenderBailDetails> lBailCur(final Integer lEventRec) {
		final String sql = getQuery("L_BAIL_CUR");
		final RowMapper<OffenderBailDetails> rowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderBailDetails.class,
				checkDataMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("P_PR_EVENT_ID", lEventRec), rowMapper);
	}

	@Override
	public void offenderBailDetailsInsert(final OffenderBailDetails offBail) {
		String sql = getQuery("LV_REC_BAIL_INSERT");
		namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(offBail));
	}

	@Override
	public List<OffenderBailConditions> lBailCondCur(final Integer lEventRec) {
		final String sql = getQuery("L_BAIL_COND_CUR");
		final RowMapper<OffenderBailConditions> rowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderBailConditions.class, checkDataMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("P_PR_EVENT_ID", lEventRec), rowMapper);
	}

	@Override
	public void offenderBailConditionsInsert(final OffenderBailConditions offBailCon) {
		final String sql = getQuery("OFFENDER_BAIL_CONDITIONS_INSERT");
		namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(offBailCon));
	}

	@Override
	public void offenderBailDetailsUpdate(final Integer pEventId) {
		final String sql = getQuery("OFFENDER_BAIL_DETAILS_UPDATE");
		final Map<String, Object> inParam = new HashMap<String, Object>();

		inParam.put("P_EVENT_ID", pEventId);
		namedParameterJdbcTemplate.update(sql, inParam);
	}

	@Override
	public void offenderBailConditionsDelete(final Integer pEventId,String modifyUserId) {
		final String sql = getQuery("OFFENDER_BAIL_CONDITIONS_DELETE");
		final Map<String, Object> inParam = new HashMap<String, Object>();

		inParam.put("P_EVENT_ID", pEventId);
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_bail_conditions";
			String whereCondition = "event_id = :P_EVENT_ID";
			inputMap.put("P_EVENT_ID", pEventId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in offenderBailConditionsDelete " + e.getMessage());
		}
		namedParameterJdbcTemplate.update(sql, inParam);
	}

	@Override
	public Integer caseIdentifiersInsert(OffenderCaseIdentifiers offCaseIden) {
		Integer returnValue = 0;
	final String sql = getQuery("CASE_IDENTIFIERS_INSERT");
	int[] returnArray = new int[] {};
	final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		parameters.add(new BeanPropertySqlParameterSource(offCaseIden));
	try {
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (1 == returnArray.length) {
			returnValue = 1;
		}
	} catch (final Exception e) {
		returnValue = 0;
		logger.error("caseIdentifiersInsert ", e);
	}
	return returnValue;
	}
	
	@Override
	public Integer ordersDelete(final Long offBkgId, final Long eventId,String modifyUserId) {
		final String sql = getQuery("ORDERS_DELETE");
		try {
			String tableName = "orders";
			String whereCondition = " offender_book_id = :offBkgId AND event_id = :eventId AND order_type = 'AUTO'";
			Map<String, Object> inputMap = new HashMap<String, Object>();
			inputMap.put("offBkgId", offBkgId);
			inputMap.put("eventId", eventId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(e);
		}
		return namedParameterJdbcTemplate.update(sql,
				createParams("offBkgId",offBkgId,"eventId",eventId));
	}
}