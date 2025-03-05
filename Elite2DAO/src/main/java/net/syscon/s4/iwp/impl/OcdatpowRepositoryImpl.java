package net.syscon.s4.iwp.impl;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
import net.syscon.s4.cm.teamsworkflow.beans.TeamMembers;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.OffenderCommunityFile;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.CasePlans;
import net.syscon.s4.im.beans.Teams;
import net.syscon.s4.inst.casemanagement.beans.AssessmentSummaries;
import net.syscon.s4.iwp.OcdatpowRepository;
import net.syscon.s4.pkgs.comunity_pkg.ComunityPkgRepository;
import net.syscon.s4.sa.usersystemsecurity.beans.AssignmentTransfers;
import net.syscon.s4.sa.usersystemsecurity.beans.OffenderWorkAssignments;
import oracle.jdbc.OracleTypes;

@Repository
public class OcdatpowRepositoryImpl extends RepositoryBase implements OcdatpowRepository {

	private static Logger logger = LogManager.getLogger(OcdatpowRepositoryImpl.class.getName());


	private final Map<String, FieldMapper> stfMap = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DSP_LAST_NAME", new FieldMapper("dspLastName")).put("DSP_FIRST_NAME", new FieldMapper("dspFirstName"))
			.put("STAFF_ID", new FieldMapper("staffId")).put("CAL_AGY_LOC_ID", new FieldMapper("intakeAgyLocId"))// AgyLocId
			.put("LAST_NAME", new FieldMapper("lastName")).put("FIRST_NAME", new FieldMapper("firstName"))
			.put("STAFF_NAME", new FieldMapper("staffName")).put("SEX_CODE", new FieldMapper("gender"))
			.put("FROM_DATE", new FieldMapper("locRoleFromDate")).put("SAC_STAFF_ID", new FieldMapper("sacStaffId"))
			.put("OM_TEAM", new FieldMapper("omTeam")).put("COUNT", new FieldMapper("count")).build();

	private final Map<String, FieldMapper> offendersMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_ID_DISPLAY", new FieldMapper("offenderIdDisplay"))
			.put("OFFENDER_ID", new FieldMapper("offenderId"))
			.put("ROOT_OFFENDER_ID", new FieldMapper("rootOffenderId")).put("LAST_NAME", new FieldMapper("lastName"))
			.put("FROM_DATE", new FieldMapper("fromDate")).put("POSITION", new FieldMapper("position"))
			.put("ROLE", new FieldMapper("role")).put("CAL_AGY_LOC_ID", new FieldMapper("calAgyLocId"))
			.put("STATUS", new FieldMapper("status")).put("OFFAS_ID", new FieldMapper("offassId"))
			.put("FIRST_NAME", new FieldMapper("firstName")).build();

	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("AGENCY_LOCATION_TYPE", new FieldMapper("agencyLocationType"))
			.put("DESCRIPTION", new FieldMapper("description")).put("CODE", new FieldMapper("code"))
			.put("LAST_NAME", new FieldMapper("lastName")).put("FIRST_NAME", new FieldMapper("firstName"))
			.put("DSP_AGENCY_LOCATION_TYPE", new FieldMapper("agencyLocationType")).build();

	private final Map<String, FieldMapper> agyLocMap = new ImmutableMap.Builder<String, FieldMapper>()
			.put("AGENCY_LOCATION_TYPE", new FieldMapper("agencyLocationType"))
			.put("AGY_LOC_ID", new FieldMapper("agyLocId")).put("DESCRIPTION", new FieldMapper("description"))
			.put("DSP_DESCRIPTION", new FieldMapper("dspDescription"))
			.put("CAL_AGY_LOC_ID", new FieldMapper("calAgyLocId")).put("CASELOAD_ID", new FieldMapper("caseloadId"))
			.put("COMM_STAFF_ID", new FieldMapper("commStaffId"))
			.put("ROOT_OFFENDER_ID", new FieldMapper("rootOffenderId"))
			.put("OFFENDER_ID", new FieldMapper("offenderId")).put("LAST_NAME", new FieldMapper("dspLastName"))
			.put("FIRST_NAME", new FieldMapper("dspFirstName"))
			.put("OFFENDER_ID_DISPLAY", new FieldMapper("agyLocIdList")).build();

	private final Map<String, FieldMapper> summaries = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_ID", new FieldMapper("offenderId")).build();

	public OcdatpowRepositoryImpl() {
		// OcdatpowRepositoryImpl
	}

	public List<OffenderBookings> postQueryForOffbkg1(final OffenderBookings ofbkngs) {
		final String qury = getQuery("OCDATPOW_CGFKCHK_OFF_BKG_OFF_BKG_OFF_N_");
		final RowMapper<OffenderBookings> mapper = Row2BeanRowMapper.makeMapping(qury, OffenderBookings.class,
				agyLocMap);
		return namedParameterJdbcTemplate.query(qury, createParams("OFFENDER_BOOK_ID", ofbkngs.getOffenderBookId()),
				mapper);
	}

	public List<OffenderBookings> offBkg1ExecuteQuery(final OffenderBookings objSearchDao) {
		final String sql = getQuery("OCDATPOW_OFFBKG1_FIND_OFFENDER_BOOKINGS");
		final RowMapper<OffenderBookings> mapper = Row2BeanRowMapper.makeMapping(sql, OffenderBookings.class,
				agyLocMap);
		return namedParameterJdbcTemplate.query(sql,
				createParams("commstaffrole", objSearchDao.getCommStaffRole(), "commonstaffId",
						objSearchDao.getCommStaffId(), "intakeCaseLoadId", objSearchDao.getIntakeAgyLocId(), "position",
						objSearchDao.getPosition()),
				mapper);

	}

	public Date getStartDate(final OffenderBookings paramBean) {
		final String sql = getQuery("OCDATPOW_FIND_STARTDATE");
		Date startDate = null;
		try {
			startDate = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("OFFENDERBOOKID", paramBean.getOffenderBookId()), Date.class);
		} catch (final Exception e) {
			logger.error(e);
			return startDate;
		}
		return startDate;
	}

	public List<TeamMembers> vOffDetExecuteQuery(TeamMembers searchBean) {
		final String sql = getQuery("OCDATPOW_VOFFDET_FIND_V_OM_TEAM_MEMBERS");

		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource params = new MapSqlParameterSource();
		sqlQuery.append(sql);
		sqlQuery.append(" WHERE ");
		if (searchBean != null) {
			sqlQuery.append("  v_om_team_members.cal_agy_loc_id = :calAgyLocId ");
			params.addValue("calAgyLocId", searchBean.getAgyLocId());

			sqlQuery.append(
					"  AND (('Y' = 'N' AND coalesce(:in_staff_qry_position, v_om_team_members.position) = v_om_team_members.position) ");
			params.addValue("in_staff_qry_position", searchBean.getPosition());

			sqlQuery.append(
					"  OR 'Y' = 'Y') AND (('Y' = 'N' AND coalesce(:in_staff_qry_role, v_om_team_members.role) = v_om_team_members.role)  OR 'Y' = 'Y')	EXCEPT  ");
			params.addValue("in_staff_qry_role", searchBean.getRole());

			sqlQuery.append(
					"  (     SELECT staff_id,staff_name,position,role,sex_code,schedule_type,cal_agy_loc_id,from_date, ( SELECT count(*) FROM teams\r\n"
							+ "    WHERE ACTIVE_FLAG = 'Y' AND TEAM_ID IN ( SELECT TM.TEAM_ID FROM TEAM_MEMBERS TM, TEAM_FUNCTIONS TF, STAFF_LOCATION_ROLES SLR WHERE \r\n"
							+ "    TM.STAFF_ID = v_om_team_members.staff_id AND TM.ACTIVE_FLAG = 'Y' AND TF.FUNCTION_TYPE = 'OM' AND TF.TEAM_ID = TM.TEAM_ID AND TM.STAFF_ID =\r\n"
							+ "    SLR.SAC_STAFF_ID AND TM.AGY_LOC_ID = SLR.CAL_AGY_LOC_ID AND TM.LOC_ROLE_FROM_DATE = SLR.FROM_DATE AND TM.POSITION = SLR.POSITION AND TM.ROLE = SLR.ROLE AND \r\n"
							+ "    ( TM.POSITION = v_om_team_members.position OR v_om_team_members.position IS NULL ) AND ( TM.ROLE = v_om_team_members.role OR v_om_team_members.role IS NULL )))\r\n"
							+ "    as count FROM v_om_team_members v_om_team_members WHERE v_om_team_members.staff_id = :SAC_STAFF_ID  ");
			params.addValue("SAC_STAFF_ID", searchBean.getStaffId());

			sqlQuery.append("  AND v_om_team_members.position = :POSITION  ");
			params.addValue("POSITION", searchBean.getPosition());

			sqlQuery.append("  AND v_om_team_members.role = :ROLE ) ");
			params.addValue("ROLE", searchBean.getRole());
		}

		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("WHERE")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("AND")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		preparedSql = preparedSql.concat(" ORDER BY STAFF_NAME ");

		final RowMapper<TeamMembers> mapper = Row2BeanRowMapper.makeMapping(preparedSql, TeamMembers.class, stfMap);
		return namedParameterJdbcTemplate.query(preparedSql, params, mapper);

	}

	public List<ReferenceCodes> rgPositionRecordGroup() {
		final String sql = getQuery("OCDATPOW_FIND_RGPOSITION");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error(e);
			return Collections.emptyList();
		}
	}

	public List<ReferenceCodes> rgRoleRecordGroup() {
		final String sql = getQuery("OCDATPOW_FIND_RGROLE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error(e);
			return Collections.emptyList();
		}
	}

	public List<ReferenceCodes> rgSexCodeRecordGroup() {
		final String sql = getQuery("OCDATPOW_FIND_RGSEXCODE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error(e);
			return Collections.emptyList();
		}
	}

	public List<ReferenceCodes> rgScheduleTypeRecordGroup() {
		final String sql = getQuery("OCDATPOW_FIND_RGSCHEDULETYPE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error(e);
			return Collections.emptyList();
		}
	}

	public List<Teams> rgTeamRecordGroup(final String position, final String role, final Long staffid) {
		final String sql = getQuery("OCDATPOW_FIND_RGTEAM");
		final RowMapper<Teams> mRowMapper = Row2BeanRowMapper.makeMapping(sql, Teams.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql,
					createParams("STAFFID", staffid, "POSITION", position, "ROLE", role), mRowMapper);
		} catch (Exception e) {
			logger.error("rgTeamRecordGroup:"+e);
			return Collections.emptyList();
		}
	}

	public List<TeamMembers> cgfkStaffLrDspDescriptionRecordGroup(final String caseLoadType) {
		final String sql = getQuery("OCDATPOW_FIND_CGFKSTAFFLRDSPDESCRIPTION");
		final RowMapper<TeamMembers> mRowMapper = Row2BeanRowMapper.makeMapping(sql, TeamMembers.class, mMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("CASELOADTYPE", caseLoadType), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error(e);
			return Collections.emptyList();
		}
	}

	public List<StaffMembers> cgfkStaffLrDspLastNameRecordGroup() {
		final String sql = getQuery("OCDATPOW_FIND_CGFKSTAFFLRDSPLASTNAME");
		final RowMapper<StaffMembers> mRowMapper = Row2BeanRowMapper.makeMapping(sql, StaffMembers.class, mMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error(e);
			return Collections.emptyList();
		}
	}

	public List<AgencyLocations> cgfkchkStaffLrStaffLrAgy(final AgencyLocations paramBean) {
		final String sql = getQuery("OCDATPOW_CGFKCHK_STAFF_LR_STAFF_LR_AGY");
		final RowMapper<AgencyLocations> mapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class, agyLocMap);
		return namedParameterJdbcTemplate.query(sql, createParams(agyLocMap), mapper);// AgencyLocations
	}

	public List<AgencyLocations> cgfklkpStaffLrStaffLrAgy(final AgencyLocations paramBean) {
		final String sql = getQuery("OCDATPOW_CGFKLKP_STAFF_LR_STAFF_LR_AGY");
		final RowMapper<AgencyLocations> mapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class, agyLocMap);
		return namedParameterJdbcTemplate.query(sql, createParams(agyLocMap), mapper);

	}

	public List<StaffMembers> cgfkchkStaffLrStaffLrSta(final StaffMembers paramBean) {
		final String sql = getQuery("OCDATPOW_CGFKCHK_STAFF_LR_STAFF_LR_STA");
		final RowMapper<StaffMembers> mapper = Row2BeanRowMapper.makeMapping(sql, StaffMembers.class, stfMap);
		return namedParameterJdbcTemplate.query(sql, createParams(), mapper);
	}

	public List<StaffMembers> cgfklkpStaffLrStaffLrSta(final StaffMembers paramBean) {
		final String sql = getQuery("OCDATPOW_CGFKLKP_STAFF_LR_STAFF_LR_STA");
		final RowMapper<StaffMembers> mapper = Row2BeanRowMapper.makeMapping(sql, StaffMembers.class, stfMap);
		return namedParameterJdbcTemplate.query(sql, createParams(), mapper);

	}

	@Override
	public long getOfficerId(final TeamMembers searchBean) {
		final String sql = getQuery("OCDATPOW_GET_OFFICER_ID");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), long.class);

	}

	public List<OffenderBookings> lkpStaffLrStaffLrAgy(final OffenderBookings bean) {
		final String sql = getQuery("AGENCY_LOCATIONS");
		final RowMapper<OffenderBookings> mRowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderBookings.class,
				offendersMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("DSP_DESCRIPTION", bean.getIntakeAgyLocId()),
				mRowMapper);
	}

	public Integer casePlaneCur(final OffenderBookings bookings) {
		final String sql = getQuery("CASE_PLAN_CUR");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("offenderBookId", bookings.getOffenderBookId()), Integer.class);

	}

	public Integer nextIdCur(final OffenderBookings bookings) {
		final String sql = getQuery("NEXT_ID_CUR");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("offenderBookId", bookings.getOffenderBookId()), Integer.class);

	}

	@Override
	public Date vsSacCur(final TeamMembers bean) {
		final String sql = getQuery("VS_SAC_CUR");
		Date date = null;
		try {
			date = namedParameterJdbcTemplate.queryForObject(sql, createParams("STAFF_ID", bean.getStaffId(),
					"AGY_LOC_ID", bean.getIntakeAgyLocId(), "POSITION", bean.getPosition(), "ROLE", bean.getRole()),
					Date.class);
		} catch (Exception e) {
			logger.error(e);
		}

		return date;
	}


	public Integer getV_Id() {
		final String sql = getQuery("V_ID");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Integer.class);

	}

	public String getCreateUserCur(final OffenderBookings bean) {
		final Integer v_id = getV_Id();
		final String sql = getQuery("CREATE_USER_CUR");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("P_OFFENDER_BOOK_ID", bean.getOffenderBookId(), "V_ID", v_id), String.class);

	}

	public Object getInstInfo(final OffenderBookings bean) {
		final Integer v_id = getV_Id();
		final String sql = getQuery("GET_INST_INFO");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("P_OFFENDER_BOOK_ID", bean.getOffenderBookId(), "V_ID", v_id), Object.class);

	}


	public List<OffenderWorkAssignments> vsGetPrevAssignCur(final OffenderBookings bookings) {
		final String query = getQuery("VS_GET_PREV_ASSIGN_CUR");
		RowMapper<OffenderWorkAssignments> mRowMapper = Row2BeanRowMapper.makeMapping(query,
				OffenderWorkAssignments.class, offendersMapping);
		return namedParameterJdbcTemplate.query(query, createParams("offender_book_id", bookings.getOffenderBookId(),
				"sac_staff_id", bookings.getCommStaffId()), mRowMapper);

	}

	public Date vsSaccalCur(final TeamMembers bean) {
		final String query = getQuery("VS_SACCAL_CUR");
		return namedParameterJdbcTemplate.queryForObject(query, createParams("sac_staff_id", bean.getStaffId(),
				"agy_loc_id", bean.getIntakeAgyLocId(), "position", bean.getPosition(), "role", bean.getRole()),
				Date.class);
	}

	public Long asStraSeq() {
		final String sql = getQuery("ASSTRA_SEQ");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Long.class);

	}

	public Integer casePlanesinsert(final List<CasePlans> bean) {
		final String sql = getQuery("CASE_PLANS_INSERT");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final CasePlans cases : bean) {
			cases.setCasePlanId(cases.getCasePlanId() + 1);
			cases.setCasePlanStatus("ACTIVE");
			parameters.add(new BeanPropertySqlParameterSource(cases));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (bean.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	public Integer casePlanesUpdate(final List<CasePlans> bean) {
		final String sql = getQuery("CASE_PLANES_UPDATE");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final CasePlans cases : bean) {
			cases.setCasePlanStatus("REVISED");
			parameters.add(new BeanPropertySqlParameterSource(cases));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (bean.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	public Integer assignmentTransfersInsert(final List<AssignmentTransfers> insertList) {
		final String query = getQuery("ASSIGNMENT_TRANSFERS_INSERT");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final AssignmentTransfers transfer : insertList) {
			parameters.add(new BeanPropertySqlParameterSource(transfer));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(query, parameters.toArray(new SqlParameterSource[0]));
		if (insertList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	public Integer assignmentTransfersUpdate(final List<AssignmentTransfers> updateList) {
		final String query = getQuery("ASSIGNMENT_TRANSFERS_UPDATE");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final AssignmentTransfers transfer : updateList) {
			parameters.add(new BeanPropertySqlParameterSource(transfer));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(query, parameters.toArray(new SqlParameterSource[0]));
		if (updateList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	public boolean omMandatory() {
		final String sql = getQuery("OM_TEAM_MANDATORY");
		String result = null;
		result = namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);
		if (result.equals("Y")) {
			return true;
		} else
			return false;
	}

	public boolean omMandatoryGrid() {
		final String sql = getQuery("OM_TEAM_MANDATORY");
		String result = null;
		result = namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);
		if (result.equals("Y")) {
			return true;
		} else
			return false;
	}

	public List<AssessmentSummaries> getassessment(final Long OffenderBookId, final Long CasePlaneid) {
		final String sql = getQuery("ASSESSMENT_SUMMARIES_SELECT_DETAILS");
		final RowMapper<AssessmentSummaries> mapper = Row2BeanRowMapper.makeMapping(sql, AssessmentSummaries.class,
				summaries);
		return namedParameterJdbcTemplate.query(sql,
				createParams("offenderBookId", OffenderBookId, "casePlanId", CasePlaneid), mapper);

	}

	public Boolean cgnbtSkillSubTy(final BigDecimal staffId, final Long offenderBookId) {
		final String sql = getQuery("V_OM_TEAM_MEMBERS");
		try {
			final String result = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("staffid", staffId, "offenderBookId", offenderBookId), String.class);
			if (result.equals("Y")) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			logger.error(e);
			return false;
		}
	}

	public List<OffenderCommunityFile> fileInfoCur(final Long offenderId) {
		final String sql = getQuery("FILE_INFO_CUR");
		final RowMapper<OffenderCommunityFile> mapper = Row2BeanRowMapper.makeMapping(sql, OffenderCommunityFile.class,
				summaries);
		return namedParameterJdbcTemplate.query(sql, createParams("OFFENDER_ID", offenderId), mapper);
	}

	public List<OffenderBookings> vsGetOffIdCur(Long offender_Book_id, Long root_offender_id) {
		final String sql = getQuery("VS_GET_OFF_ID_CUR");
		final RowMapper<OffenderBookings> mapper = Row2BeanRowMapper.makeMapping(sql, OffenderBookings.class,
				summaries);
		return namedParameterJdbcTemplate.query(sql,
				createParams("OFFENDER_BOOK_ID", offender_Book_id, "ROOT_OFFENDER_ID", root_offender_id), mapper);
	}

	public boolean lvProfileValue() {
		final String sql = getQuery("LV_PROFILE_VALUE");
		String result = null;
		result = namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);
		if (result.equals("Y")) {
			return true;
		} else
			return false;
	}


	public Integer updateOffenderCommunityFiles(final OffenderCommunityFile bean) {
		final String sql = getQuery("UPDATE_OFFENDER_COMMUNITY_FILES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (returnArray.length == 1) {
			return 1;
		} else {
			return 0;
		}
	}

}
