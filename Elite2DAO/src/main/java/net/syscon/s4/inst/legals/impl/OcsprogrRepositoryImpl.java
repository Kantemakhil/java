package net.syscon.s4.inst.legals.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.cm.programsservices.VOffenderSentCondActs;
import net.syscon.s4.cm.teamsworkflow.beans.TeamMembers;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.SentenceCalcTypes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.inst.accreditedprograms.beans.VProgramProviders;
import net.syscon.s4.inst.casemanagement.beans.ProgramServices;
import net.syscon.s4.inst.legals.OcsprogrRepository;

@Repository
public class OcsprogrRepositoryImpl extends RepositoryBase implements OcsprogrRepository {

	private static Logger logger = LogManager.getLogger(OcsprogrRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description")).put("CODE", new FieldMapper("code"))
			.put("ACTIVE_FLAG", new FieldMapper("activeFlag")).build();

	private final Map<String, FieldMapper> offExecMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("LAST_NAME", new FieldMapper("dspLastName")).put("FIRST_NAME", new FieldMapper("dspFirstName"))
			.put("OFFENDER_ID_DISPLAY", new FieldMapper("offenderIdDisplay"))
			.put("OFFENDER_START_DATE", new FieldMapper("offenderStartDate"))
			.put("OFFENDER_END_DATE", new FieldMapper("offenderEndDate"))
			.put("STATUS", new FieldMapper("bookingStatus"))
			.put("REFERRAL_DATE", new FieldMapper("referralDate")).build();

	private final Map<String, FieldMapper> mmteamMembersMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description")).put("TEAM_CODE", new FieldMapper("teamCode"))
			.put("TEAM_ID", new FieldMapper("teamId")).put("LIST_SEQ", new FieldMapper("teamMemberId"))
			.put("CODE", new FieldMapper("code")).build();

	public List<ReferenceCodes> rgRefCodeRecordGroup() {
		final String sql = getQuery("OCSPROGR_PROVIDER_TYPE_REF_CODE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("Exception in Class " + this.getClass().getName() + "rgRefCodeRecordGroup :" + e.getMessage());
			return Collections.emptyList();
		}
	}

	public List<ProgramServices> rgAccProgramRecordGroup() {
		final String sql = getQuery("OCSPROGR_PROVIDER_REFGROUP");
		final RowMapper<ProgramServices> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ProgramServices.class,
				mMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("Exception in Class " + this.getClass().getName() + "rgAccProgramRecordGroup :" + e.getMessage());
			return Collections.emptyList();
		}
	}

	@Override
	public List<TeamMembers> rgRefCodeProviderGroup(String userId) {
		final String sql = getQuery("OCSPROGR_INTERNAL_PROVIDER");
		final RowMapper<TeamMembers> mMTeamMembersMRowMapper = Row2BeanRowMapper.makeMapping(sql, TeamMembers.class,
				mmteamMembersMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("userId" , userId), mMTeamMembersMRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("Exception in Class " + this.getClass().getName() + "rgRefCodeProviderGroup :" + e.getMessage());
			return Collections.emptyList();
		}
	}

	public List<VProgramProviders> rgProviderRecordGroupTeam() {
		final String sql = getQuery("OCSPROGR_PROVIDER_EXTERNAL");
		final RowMapper<VProgramProviders> mRowMapper = Row2BeanRowMapper.makeMapping(sql, VProgramProviders.class,
				mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql,
					createParams(),
					mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("Exception in Class " + this.getClass().getName() + "rgProviderRecordGroupTeam :" + e.getMessage());
			return Collections.emptyList();
		}
	}

	@Override
	public List<OffenderBookings> offExecQuery(List<String> statusList, String programId, String intProviderPartyId,
			String extProviderPartyId,String currentCaseload) {
		final String sql = getQuery("OCSPROGR_OFFENDERS_PROG");
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		final RowMapper<OffenderBookings> mRowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderBookings.class,
				offExecMapping);
		final MapSqlParameterSource params = new MapSqlParameterSource();
		List<OffenderBookings> returnList = new ArrayList<OffenderBookings>();
		try {
			sqlQuery.append(sql);
			params.addValue("currentCaseload", currentCaseload);
			if(statusList!= null && !statusList.isEmpty()) {
				sqlQuery.append(" status in( ");
			     for (String i : statusList){
			    	 sqlQuery.append(" '"+i+"',");
			     }
			     sqlQuery.deleteCharAt(sqlQuery.lastIndexOf(","));
			     sqlQuery.append(") and");
			}
			if (programId != null && !programId.trim().equals("")) {
				sqlQuery.append(" program_id = :programId::numeric and");
				params.addValue("programId", programId);
			}
			if (extProviderPartyId != null && !extProviderPartyId.trim().equals("")) {
				sqlQuery.append(" crs_acty_id IN (SELECT CRS_ACTY_ID FROM COURSE_ACTIVITIES WHERE PROVIDER_PARTY_ID = :extProviderPartyId::numeric ) and ");
//						+ "=:extProviderPartyId::numeric and ");
				params.addValue("extProviderPartyId", extProviderPartyId);
			}
			if (intProviderPartyId != null && !intProviderPartyId.trim().equals("")) {
				sqlQuery.append(" CRS_ACTY_ID IN (SELECT CRS_ACTY_ID FROM V_COURSE_ACTIVITIES WHERE provider_name = :intProviderPartyId  AND course_class = 'COURSE') and ");
				params.addValue("intProviderPartyId", intProviderPartyId);
			}
			preparedSql = sqlQuery.toString().trim(); 
			if (preparedSql.endsWith("and")) { 
				preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
			}
			returnList = namedParameterJdbcTemplate.query(preparedSql, params, mRowMapper);
		} catch (Exception e) {
			logger.error("Exception in Class " + this.getClass().getName() + "offExecQuery :" + e.getMessage());
		}
		return returnList;
	}

	@Override
	public 	List<OffenderBookings> getOffPrgData(OffenderBookings searchBean){
		final String sql = getQuery("OCSPROGR_LEGEL_EXECUTEQUERY");
		final RowMapper<OffenderBookings> mRowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderBookings.class,
				offExecMapping);
		List<OffenderBookings> returnList = new ArrayList<OffenderBookings>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("offender_book_id", searchBean.getOffenderBookId() , "offenderPrgObligationId" ,searchBean.getOffenderPrgObligationId()),
					mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
		return returnList;
	}
}
