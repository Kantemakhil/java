package net.syscon.s4.iwp.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
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
import net.syscon.s4.cm.courtcasesandorders.maintenance.impl.OimsreqsRepositoryImpl;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SentenceCalcTypes;
import net.syscon.s4.common.beans.SentenceTerms;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.inst.legals.beans.OffenderProceedings;
import net.syscon.s4.inst.legals.beans.OffenderSentConditions;
import net.syscon.s4.inst.schedules.bean.CourtEvents;
import net.syscon.s4.iwp.OcdenforRepository;

/**
 * Class OcdenforRepositoryImpl
 * 
 * @author Arkin Software Technologies
 * @version 1.0
 */
@Repository
public class OcdenforRepositoryImpl extends RepositoryBase implements OcdenforRepository {

	/**
	 * Creates new OcdenforRepositoryImpl class Object
	 */
	public OcdenforRepositoryImpl() {
	}

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OimsreqsRepositoryImpl.class.getName());
	
	private final Map<String, FieldMapper> offenderProceedingsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId"))
			.put("OFFENDER_PROCEEDING_ID", new FieldMapper("offenderProceedingId"))
			.put("PROCEEDING_TYPE", new FieldMapper("proceedingType"))
			.put("START_DATE", new FieldMapper("startDate"))
			.put("PROCEEDING_AGY_LOC_ID", new FieldMapper("proceedingAgyLocId"))
			.put("COMMENT_TEXT", new FieldMapper("commentText"))
			.put("OUTCOME_DATE", new FieldMapper("outcomeDate"))
			.put("PROCEEDING_STATUS", new FieldMapper("proceedingStatus"))
			.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("TEAM_RESPONSIBLE",  new FieldMapper("teamResponsible"))
			.put("STAFF_RESPONSIBLE", new FieldMapper("staffResponsible"))
			.put("SENTENCE_SEQ",	  new FieldMapper("sentenceSeq"))
			.put("ORDER_TYPE",		  new FieldMapper("orderType"))
			.put("PROCEEDING_PURSUANT_ACT",	  new FieldMapper("proceedingPursuantAct"))
			.put("CRT_ACTION_RECOMMENDATION",		  new FieldMapper("crtActionRecommendation"))
			
			.put("TEAM_ID",  new FieldMapper("teamId"))
			.put("STAFF_ID", new FieldMapper("staffId"))
			
			.build();
	
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("AGENCY_LOCATION_TYPE", new FieldMapper("agencyLocationType"))
			.put("CODE", new FieldMapper("code"))
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("LAST_NAME", new FieldMapper("lastName"))
			.put("FIRST_NAME", new FieldMapper("firstName")).build();
	
	private final Map<String, FieldMapper> conditionTypeMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CREATE_DATETIME", 	new FieldMapper("description"))			
			.put("CODE",  			    new FieldMapper("code"))
			.put("program",  			    new FieldMapper("program"))
			.put("PROGRAM_METHOD",  			    new FieldMapper("programMethod"))
			.put("COURSEPROFILESACTS",  			    new FieldMapper("courseProfilesActs"))
			.put("APPOINTMENTSACTS",  			    new FieldMapper("appointmentsActs"))
			.put("APPOINTMENTSSA",  			    new FieldMapper("appointmentsSa"))
			.put("allocation_flag", new FieldMapper("allocationFlag"))
			.put("plan_of_action_flag", new FieldMapper("planOfActionFlag"))
			.put("COMM_PROJ_ALLOC_FLAG", new FieldMapper("commProjAllocFlag"))
			.put("assigned_officer", new FieldMapper("assignedOfficer"))	
			.put("LINKED_TO_OTHER_PROCEEDING", new FieldMapper("linkedToOtherProceeding"))
			.put("LINKED_COUNT", new FieldMapper("linkedCount"))
			
			.build();
	
	private final Map<String, FieldMapper> offenderProceedingsCondMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFF_PROCEEDING_COND_ID", new FieldMapper("offProceedingCondId"))
			.put("OFFENDER_PROCEEDING_ID", new FieldMapper("offenderProceedingId"))
			.put("OFFENDER_SENT_CONDITION_ID", new FieldMapper("offenderSentConditionId"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.build();
	
	public List<AgencyLocations> rgAgyLocsRecordGroup(String proceedingType) {
		logger.info(this.getClass().getName() + " rgAgyLocsRecordGroup and proceedingType is : {}", proceedingType);
		final String sql = getQuery("OCDENFOR_FIND_RGAGYLOCS");
		try {
			final RowMapper<AgencyLocations> mRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class, mMapping);
		
			return namedParameterJdbcTemplate.query(sql,
					createParams("proceedingType", proceedingType, "proceedingType", proceedingType), mRowMapper);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(this.getClass().getName() + " rgAgyLocsRecordGroup and Exception is : {}", e.getMessage());
			return null;
		}
	}
			
	@Override
	public List<ReferenceCodes> rgTeamResponsibleRecordGroup(String userId, String agylocId) {
		logger.info(this.getClass().getName() + " rgTeamResponsibleRecordGroup and userId, agylocId : {}", userId, agylocId);
		final String sql = getQuery("OCDENFOR_RG_TEAM_RESPONSIBLE");
		try {
			final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
			return namedParameterJdbcTemplate.query(sql, createParams("userId", userId, "agylocId", agylocId), mRowMapper);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(this.getClass().getName() + " rgTeamResponsibleRecordGroup and Exception is : {}", e.getMessage());
			return null;
		}
	}

	@Override
	public List<ReferenceCodes> rgStaffResponsibleRecordGroup(String caseloadId, String teamResponsible) {
		logger.info(this.getClass().getName() + " rgStaffResponsibleRecordGroup and caseloadId, teamResponsible : {}", caseloadId, teamResponsible);
		String sql = "";
		try {
			if(!teamResponsible.equals("null") && !teamResponsible.equals("") && !teamResponsible.equals("undefined")) {
				sql = getQuery("OCDENFOR_RG_STAFF_RESPONSIBLE");
			} else {
				sql = getQuery("OCDENFOR_RG_ALL_STAFF_RESPONSIBLE");
			}
			final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
			return namedParameterJdbcTemplate.query(sql, createParams("teamResponsible",teamResponsible, "caseloadId", caseloadId), mRowMapper);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(this.getClass().getName() + " rgStaffResponsibleRecordGroup and Exception is : {}", e.getMessage());
			return null;
		}
	}
	
	@Override
	public List<OffenderProceedings> actionsExecuteQuery(OffenderProceedings searchBean) {
		logger.info(this.getClass().getName() + " actionsExecuteQuery and OffenderProceedings search data : {}", searchBean);
		final String sql = getQuery("OCDENFOR_ACTIONS_EXECUTE_QUERY");
		List<OffenderProceedings> returnList = new ArrayList<OffenderProceedings>();
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource params = new MapSqlParameterSource();
		sqlQuery.append(sql);
		params.addValue("offenderBookId", searchBean.getOffenderBookId());
		params.addValue("orderType", searchBean.getOrderType());
		if(searchBean!= null && searchBean.getSentenceSeq()!= null) {
			sqlQuery.append("  and sentence_seq = :sentenceSeq::int");
			params.addValue("sentenceSeq", searchBean.getSentenceSeq());
		}
		preparedSql = sqlQuery.toString().trim(); 
		try {
			final RowMapper<OffenderProceedings> actionsRowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderProceedings.class,
					offenderProceedingsMapping);
			returnList = namedParameterJdbcTemplate.query(preparedSql, params, actionsRowMapper);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(this.getClass().getName() + " actionsExecuteQuery and Exception is : {}", e.getMessage());
		}
		return returnList;
	}

	@Override
	public Integer insertCourtActions(List<OffenderProceedings> insertList) {
		String sql = getQuery("OCDENFOR_INSERT_COURT_ACTIONS");
		int[] returnArray = new int[] {};
		int result = 0;
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		try {
			for (final OffenderProceedings offenderProceedings : insertList) {
				parameters.add(new BeanPropertySqlParameterSource(offenderProceedings));
			}
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
			if (insertList.size() == returnArray.length) {
				result = 1;
			} else {
				result = 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(this.getClass().getName() + " insertCourtActions and Exception is : {}", e.getMessage());
		}
		return result;
	}

	@Override
	public Integer updateCourtActions(List<OffenderProceedings> updateList) {
		String sql = getQuery("OCDENFOR_UPDATE_COURT_ACTIONS");
		int[] returnArray = new int[] {};
		int result = 0;
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		try {
			for (OffenderProceedings offenderProceedings : updateList) {
				parameters.add(new BeanPropertySqlParameterSource(offenderProceedings));
			}
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
			if (updateList.size() == returnArray.length) {
				result = 1;
			} else {
				result = 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(this.getClass().getName() + " updateCourtActions and Exception is : {}", e.getMessage());
		}
		return result;
	}
	
	@Override
	public BigDecimal getNextProceedingId() {
		final String sql = getQuery("OCDENFOR_GET_NEXT_PROCEEDING_ID");
		BigDecimal nextProceedingId = null;
		try {
			nextProceedingId = namedParameterJdbcTemplate.queryForObject(sql,
					createParams(), BigDecimal.class);
		} catch (final Exception e) {
			logger.error(this.getClass().getName() + " getNextProceedingId and Exception is : {}", e.getMessage());
			return nextProceedingId;
		}
		return nextProceedingId;
	}
	
	@Override
	public Integer getScheduleCount(CourtEvents object) { 
		final String sql = getQuery("OCDENFOR_SCHEDULE_COUNT");
		Integer count=null;
		try {
			count = namedParameterJdbcTemplate.queryForObject(sql, createParams("offender_book_id",object.getOffenderBookId(),"event_date",object.getEventDate()), Integer.class);	
		} catch (Exception e) {
			logger.error(e);
		}
		return count;
	}

	@Override
	public List<OffenderSentConditions> getConditionTypeGridData(OffenderSentConditions searchBean) {	
			String sql = null;
			if (searchBean != null && searchBean.getSealFlag() != null && "Y".equals(searchBean.getSealFlag())) {
				sql = getQuery("OCDENFOR_GET_CONDITION_TYPE_GRID");
			}
			final RowMapper<OffenderSentConditions> conditionTypeRowMapper = Row2BeanRowMapper.makeMapping(sql,
					OffenderSentConditions.class, conditionTypeMapping);
			String preparedSql = null;
			final MapSqlParameterSource inParameterSource = new MapSqlParameterSource();
			final StringBuffer sqlQuery = new StringBuffer();
			sqlQuery.append(sql);
			if (searchBean != null) {
				sqlQuery.append(" WHERE ");

				if (searchBean.getSentenceSeq() != null) {
					sqlQuery.append(" SENTENCE_SEQ = :SENTENCE_SEQ" + " AND ");
					inParameterSource.addValue("SENTENCE_SEQ", searchBean.getSentenceSeq());
				}

				if (searchBean.getCommConditionType() != null) {
					sqlQuery.append(" COMM_CONDITION_TYPE = :COMM_CONDITION_TYPE" + " AND ");
					inParameterSource.addValue("COMM_CONDITION_TYPE", searchBean.getCommConditionType());
				}
				if (searchBean.getCategoryType() != null) {
					sqlQuery.append(" CATEGORY_TYPE = :CATEGORY_TYPE" + " AND ");
					inParameterSource.addValue("CATEGORY_TYPE", searchBean.getCategoryType());
				}
				if (searchBean.getOffenderBookId() != 0) {
					sqlQuery.append("OFFENDER_BOOK_ID = :OFFENDER_BOOK_ID" + " AND ");
					inParameterSource.addValue("OFFENDER_BOOK_ID", searchBean.getOffenderBookId());

				}
				if (searchBean.getObjectId() != null) {
					sqlQuery.append(" OBJECT_ID = :OBJECT_ID" + " AND ");
					inParameterSource.addValue("OBJECT_ID", searchBean.getObjectId());

				}
				if (searchBean.getObjectType() != null) {
					sqlQuery.append(" OBJECT_TYPE = :OBJECT_TYPE" + " AND ");
					inParameterSource.addValue("OBJECT_TYPE", searchBean.getObjectType());
				}

			}
			inParameterSource.addValue("offenderProceedingId", searchBean.getOffenderProceedingId());
			preparedSql = sqlQuery.toString().trim();
			if (preparedSql.endsWith("WHERE")) {
				preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
			}
			if (preparedSql.endsWith("AND")) {
				preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
			}
			if (searchBean != null && searchBean.getSealFlag() != null && "Y".equals(searchBean.getSealFlag())) {
				preparedSql = preparedSql + " ORDER BY category_type";
			} else {
				preparedSql = preparedSql + "  GROUP BY category_type,comm_condition_type ORDER BY category_type";
			}

			return namedParameterJdbcTemplate.query(preparedSql, inParameterSource, conditionTypeRowMapper);
		}

	@Override
	public BigDecimal getNextOffProceedingCondId() {
		final String sql = getQuery("OCDENFOR_GET_NEXT_OFF_PROCEEDING_COND_ID");
		BigDecimal nextProceedingId = null;
		try {
			nextProceedingId = namedParameterJdbcTemplate.queryForObject(sql,
					createParams(), BigDecimal.class);
		} catch (final Exception e) {
			logger.error(this.getClass().getName() + " getNextOffProceedingCondId and Exception is : {}", e.getMessage());
			return nextProceedingId;
		}
		return nextProceedingId;
	}
	
	@Override
	public Integer insertOffenderProceedingCondition(List<OffenderSentConditions> insertList) {
		String sql = getQuery("OCDENFOR_INSERT_OFFENDER_PROCEEDING_CONDITIONS");
		int[] returnArray = new int[] {};
		int result = 0;
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		try {
			for (final OffenderSentConditions offenderProceedings : insertList) {
				parameters.add(new BeanPropertySqlParameterSource(offenderProceedings));
			}
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
			if (insertList.size() == returnArray.length) {
				result = 1;
			} else {
				result = 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(this.getClass().getName() + " insertOffenderProceedingCondition and Exception is : {}", e.getMessage());
		}
		return result;
	}

	@Override
	public List<OffenderSentConditions> getExistingOffenderProceedingData(
			OffenderSentConditions offenderSentConditions) {
		final String sql = getQuery("OCDENFOR_GET_OFFENDER_PROCEEDING_DATA_BASED_ON_ID");
		final RowMapper<OffenderSentConditions> sentRowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderSentConditions.class,
				offenderProceedingsCondMapping);
		List<OffenderSentConditions> returnList = new ArrayList<>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("offenderProceedingId",offenderSentConditions.getOffenderProceedingId()),
					sentRowMapper);
		} catch (Exception e) {
			logger.error("getExistingOffenderProceedingData", e);
		}

		return returnList;
	}

	@Override
	public Integer deleteExistingOffenderProceedingData(List<OffenderSentConditions> resultData) {
		final String sql = getQuery("OCDENFOR_DELETE_OFFENDER_PROCEEDING_DATA_BASED_ON_ID");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderSentConditions offenderSentConditions : resultData) {
			parameters.add(new BeanPropertySqlParameterSource(offenderSentConditions));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("deleteExistingOffenderProceedingData : ", e);
		}
		if (resultData.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public String getPersutHideShowValue(String code) {
		final String sql = getQuery("OCDENFOR_GET_LEGAL_SETTING_PERSUET_HIDE_SHOW_VALUE");
		String count=null;
		try {
			count = namedParameterJdbcTemplate.queryForObject(sql, createParams("code",code), String.class);	
		} catch (Exception e) {
			logger.error(e);
		}
		return count;
	}
	
	
}
