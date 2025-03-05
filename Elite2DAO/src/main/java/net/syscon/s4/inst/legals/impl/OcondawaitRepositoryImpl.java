package net.syscon.s4.inst.legals.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.OffenderSentConditions;
import net.syscon.s4.im.beans.Teams;
import net.syscon.s4.inst.legals.OcondawaitRepository;
import net.syscon.s4.inst.legals.beans.OffenderAllocationsSentences;
import net.syscon.s4.inst.legals.beans.OffenderCondTransfer;

@Repository
public class OcondawaitRepositoryImpl extends RepositoryBase implements OcondawaitRepository {

	private static Logger logger = LogManager.getLogger(OcondawaitRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> lovMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CODE", new FieldMapper("code")).put("DESCRIPTION", new FieldMapper("description")).build();
	private final Map<String, FieldMapper> sentenceMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("ID", new FieldMapper("Id")).put("FORM_INFO_JSON", new FieldMapper("formInfoJson"))
			.put("FORM_IDENTIFIER", new FieldMapper("formIdentifier"))
			.put("OFFENDER_ID_DISPLAY", new FieldMapper("offenderIdDisplay"))
			.put("LAST_NAME", new FieldMapper("lastName")).put("FIRST_NAME", new FieldMapper("firstName")).build();
	private final Map<String, FieldMapper> conditionTypeMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("OFFENDER_SENT_CONDITION_ID", new FieldMapper("offenderSentConditionId"))
			.put("COMM_CONDITION_TYPE", new FieldMapper("commConditionType"))
			.put("COMM_CONDITION_CODE", new FieldMapper("commConditionCode"))
			.put("CATEGORY_TYPE", new FieldMapper("categoryType")).put("SENTENCE_SEQ", new FieldMapper("sentenceSeq"))
			.put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId"))
			.put("LONG_COMMENT_TEXT", new FieldMapper("longCommentText")).build();

	@Override
	public List<ReferenceCodes> rgLocationRecGroup(String caseLoadId) {
		String sql = getQuery("OCONDAWAIT_RG_LOCATION_REC_GROUP");
		try {
			final RowMapper<ReferenceCodes> lovRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
					lovMapping);
			return namedParameterJdbcTemplate.query(sql, createParams("caseLoadID", caseLoadId), lovRowMapper);
		} catch (Exception e) {
			logger.error("Exception in OcondawaitRepositoryImpl class rgLocationRecGroup : ", e);
			return Collections.emptyList();
		}
	}

	@Override
	public List<Teams> rgTeamRecGroup(String caseLoadId, String userName) {
		String sql = getQuery("OCONDAWAIT_RG_TEAM_REC_GROUP");
		try {
			final RowMapper<Teams> lovRowMapper = Row2BeanRowMapper.makeMapping(sql, Teams.class, lovMapping);
			return namedParameterJdbcTemplate.query(sql,
					createParams("caseLoadId", caseLoadId, "createUserId", userName), lovRowMapper);
		} catch (Exception e) {
			logger.error("Exception in OcondawaitRepositoryImpl class rgTeamRecGroup : ", e);
			return Collections.emptyList();
		}
	}

	@Override
	public List<OffenderCondTransfer> getSentenceData(OffenderAllocationsSentences searchBean) {
		String sql = getQuery("OCONDAWAIT_GET_SENTENCE_DATA");
		try {
			final RowMapper<OffenderCondTransfer> sentenceRowMapper = Row2BeanRowMapper.makeMapping(sql,
					OffenderCondTransfer.class, sentenceMapping);
			return namedParameterJdbcTemplate.query(sql,
					createParams("CASELOADID", searchBean.getCaseLoadId(), "TEAMID", searchBean.getTeamId()),
					sentenceRowMapper);
		} catch (Exception e) {
			logger.error("Exception in OcondawaitRepositoryImpl class getSentenceData : ", e);
			return Collections.emptyList();
		}
	}

	@Override
	public List<OffenderCondTransfer> getAwaitingConditions(OffenderAllocationsSentences searchBean) {
		String sql = getQuery("OCONDAWAIT_CHECK_CONDITIONS_ASSIGNED");
		try {
			final RowMapper<OffenderCondTransfer> sentenceRowMapper = Row2BeanRowMapper.makeMapping(sql,
					OffenderCondTransfer.class, sentenceMapping);
			return namedParameterJdbcTemplate.query(sql,
					createParams("SENTENCESEQ", searchBean.getNo(), "OFFENDERBOOKID", searchBean.getOffenderBookId()),
					sentenceRowMapper);
		} catch (Exception e) {
			logger.error("Exception in OcondawaitRepositoryImpl class getAwaitingConditions : ", e);
			return Collections.emptyList();
		}
	}

	@Override
	public Integer offenderCondTransferInsert(List<OffenderCondTransfer> insertList) {
		final String sql = getQuery("OCONDWAIT_INSERT_OFFENDER_COND_TRANSFER");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final OffenderCondTransfer list : insertList) {
			parameters.add(new BeanPropertySqlParameterSource(list));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("offenderCondTransferInsert: ", e);
		}
		if (insertList.size() == returnArray.length) {
			return 1;
		} else {

			return 0;
		}
	}

	@Override
	public List<ReferenceCodes> getStaffDetails(String caseLoadId) {
		String sql = getQuery("OCONDWAIT_GET_STAFF_ID");
		try {
			final RowMapper<ReferenceCodes> lovRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
					lovMapping);
			return namedParameterJdbcTemplate.query(sql, createParams("caseLoadID", caseLoadId), lovRowMapper);
		} catch (Exception e) {
			logger.error("Exception in OcondawaitRepositoryImpl class getStaffDetails : ", e);
			return Collections.emptyList();
		}
	}

	@Override
	public List<ReferenceCodes> getTeamMemberDetails(Integer teamId) {
		String sql = getQuery("OCONDWAIT_GET_TEAM_MEMBERS_ID");
		try {
			final RowMapper<ReferenceCodes> lovRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
					lovMapping);
			return namedParameterJdbcTemplate.query(sql, createParams("teamId", teamId), lovRowMapper);
		} catch (Exception e) {
			logger.error("Exception in OcondawaitRepositoryImpl class getTeamMemberDetails : ", e);
			return Collections.emptyList();
		}
	}

	@Override
	public List<OffenderCondTransfer> getAssignedConditions(OffenderAllocationsSentences searchBean) {
		String sql = getQuery("OCONDTRF_GET_ASSIGNED_CONDITIONS");
		try {
			final RowMapper<OffenderCondTransfer> conditionRowMapper = Row2BeanRowMapper.makeMapping(sql,
					OffenderCondTransfer.class, conditionTypeMapping);
			return namedParameterJdbcTemplate.query(sql,
					createParams("SENTENCESEQ", searchBean.getNo(), "OFFENDERBOOKID", searchBean.getOffenderBookId(),
							"STAFFID", searchBean.getStaffId(), "TEAMID", searchBean.getTeamId(), "TEAMMEMBERID",
							searchBean.getTeamMemberId(), "CASELOADID", searchBean.getCaseLoadId(),"ORDERTYPE",searchBean.getOrderType()),
					conditionRowMapper);
		} catch (Exception e) {
			logger.error("Exception in OcondawaitRepositoryImpl class getAssignedConditions : ", e);
			return Collections.emptyList();
		}
	}

	@Override
	public List<OffenderAllocationsSentences> getAssignedCondOffenders(OffenderAllocationsSentences searchBean) {
		String sql = getQuery("OCNDAWAIT_ASSIGNED_OFFENDER_CONDITIONS");
		try {
			final RowMapper<OffenderAllocationsSentences> sentenceRowMapper = Row2BeanRowMapper.makeMapping(sql,
					OffenderAllocationsSentences.class, sentenceMapping);
			return namedParameterJdbcTemplate.query(
					sql, createParams("CASELOADID", searchBean.getCaseLoadId(), "STAFFID", searchBean.getStaffId(),
							"TEAMID", searchBean.getTeamId(), "TEAMMEMBERID", searchBean.getTeamMemberId()),
					sentenceRowMapper);
		} catch (Exception e) {
			logger.error("Exception in OcondawaitRepositoryImpl class getAssignedCondOffenders : ", e);
			return Collections.emptyList();
		}
	}

	@Override
	public List<OffenderAllocationsSentences> getTransferredCondOffenders(OffenderAllocationsSentences searchBean) {
		String sql = getQuery("OCONDAWAIT_GET_TRANFERRED_COND_OFFENDERS");
		try {
			final RowMapper<OffenderAllocationsSentences> sentenceRowMapper = Row2BeanRowMapper.makeMapping(sql,
					OffenderAllocationsSentences.class, sentenceMapping);
			return namedParameterJdbcTemplate.query(sql,
					createParams("TEAMID", searchBean.getTeamId(), "CASELOADID", searchBean.getCaseLoadId()),
					sentenceRowMapper);
		} catch (Exception e) {
			logger.error("Exception in OcondawaitRepositoryImpl class getTransferredCondOffenders : ", e);
			return Collections.emptyList();
		}
	}

	@Override
	public List<OffenderAllocationsSentences> getTransferredConditons(OffenderAllocationsSentences searchBean) {
		String sql = getQuery("OCONAWAIT_GET_TRANFERRED_CONDITIONS");
		try {
			final RowMapper<OffenderAllocationsSentences> sentenceRowMapper = Row2BeanRowMapper.makeMapping(sql,
					OffenderAllocationsSentences.class, sentenceMapping);
			return namedParameterJdbcTemplate
					.query(sql,
							createParams("CASELOADID", searchBean.getCaseLoadId(), "OFFENDERBOOKID",
									searchBean.getOffenderBookId(), "TEAMID", searchBean.getTeamId(),"SENTENCESEQ",searchBean.getNo(),"ORDERTYPE",searchBean.getOrderType()),
							sentenceRowMapper);
		} catch (Exception e) {
			logger.error("Exception in OcondawaitRepositoryImpl class getTransferredConditons : ", e);
			return Collections.emptyList();
		}
	}

	@Override
	public List<OffenderSentConditions> checkCondExitsIntkAgyLocId(OffenderAllocationsSentences searchBean) {
		String sql = getQuery("OCONDAWAIT_CHECK_CONDITIONS_EXISTS_INTAKE");
		try {
			final RowMapper<OffenderSentConditions> conditionRowMapper = Row2BeanRowMapper.makeMapping(sql,
					OffenderSentConditions.class, conditionTypeMapping);
			return namedParameterJdbcTemplate.query(sql,
					createParams("SENTENCESEQ", searchBean.getNo(), "OFFENDERBOOKID", searchBean.getOffenderBookId(),
							"CASELOADID", searchBean.getCaseLoadId(), "TEAMID", searchBean.getTeamId(),"ORDERTYPE",searchBean.getOrderType()),
					conditionRowMapper);
		} catch (Exception e) {
			logger.error("Exception in OcondawaitRepositoryImpl class checkCondExitsIntkAgyLocId : ", e);
			return Collections.emptyList();
		}

	}
	
	@Override
	public Integer offenderCondTransferInsertCasePlan(List<OffenderCondTransfer> insertList) {
		final String sql = getQuery("OCONDWAIT_OFFENDER_COND_TRANSFERINSERT_CASEPLAN");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final OffenderCondTransfer list : insertList) {
			parameters.add(new BeanPropertySqlParameterSource(list));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("offenderCondTransferInsert: ", e);
		}
		if (insertList.size() == returnArray.length) {
			return 1;
		} else {

			return 0;
		}
	}
	
	@Override
	public List<Teams> rgTransferTeamRecGroup(String caseLoadId) {
		String sql = getQuery("OCONDAWAIT_TEAM_LOV_FOR_OCONDTRF");
		try {
			final RowMapper<Teams> lovRowMapper = Row2BeanRowMapper.makeMapping(sql, Teams.class, lovMapping);
			return namedParameterJdbcTemplate.query(sql,
					createParams("caseLoadId", caseLoadId), lovRowMapper);
		} catch (Exception e) {
			logger.error("Exception in OcondawaitRepositoryImpl class rgTeamRecGroup : ", e);
			return Collections.emptyList();
		}
	}
	
	@Override
	public List<OffenderCondTransfer> getInstConditionsForOffenders() {
		String sql = getQuery("OCONDAWAIT_GET_INST_ASSIGNED_CONDITIONS_FOR_OFFENDERS");
		try {
			return namedParameterJdbcTemplate.query(sql, new BeanPropertyRowMapper<OffenderCondTransfer>(OffenderCondTransfer.class) );
		} catch (Exception e) {
			logger.error("Exception in OcondawaitRepositoryImpl class getInstConditionsForOffenders : ", e);
			return Collections.emptyList();
		}
	}
	
	@Override
	public Integer updateInstConditonsToCOMM(List<OffenderCondTransfer> updateList) {
		final String sql = getQuery("OCONDAWAIT_UPDATE_INST_CONDITIONS_TO_COMM");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final OffenderCondTransfer list : updateList) {
			parameters.add(new BeanPropertySqlParameterSource(list));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("Exception in OcondawaitRepositoryImpl class updateInstConditonsToCOMM : ", e);
		}
		if (updateList.size() == returnArray.length) {
			return 1;
		} else {

			return 0;
		}
	}
}
