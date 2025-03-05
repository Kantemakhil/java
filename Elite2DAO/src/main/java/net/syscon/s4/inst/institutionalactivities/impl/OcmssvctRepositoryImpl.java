package net.syscon.s4.inst.institutionalactivities.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
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
import net.syscon.s4.cm.teamsworkflow.beans.TeamMembers;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.CourseActivityParties;
import net.syscon.s4.im.beans.Dual;
import net.syscon.s4.inst.institutionalactivities.OcmssvctRepository;

/**
 * Class OcmssvctRepositoryImpl
 */
@Repository
public class OcmssvctRepositoryImpl extends RepositoryBase implements OcmssvctRepository {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcmssvctRepositoryImpl.class.getName());
	private final Map<String, FieldMapper> mmteamMembersMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("STAFF_ID", new FieldMapper("staffId")).put("LAST_NAME", new FieldMapper("lastName"))
			.put("FIRST_NAME", new FieldMapper("firstName")).put("CODE", new FieldMapper("code"))
			.put("DESCRIPTION", new FieldMapper("description")).build();
	private final Map<String, FieldMapper> curseActivityMaping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CRS_ACTY_PARTY_ID", new FieldMapper("crsActyPartyId"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId")).put("CRS_ACTY_ID", new FieldMapper("crsActyId"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId")).put("STAFF_ID", new FieldMapper("staffId"))
			.put("REGISTRATION_TEXT", new FieldMapper("registrationText"))
			.put("PARTY_ROLE", new FieldMapper("partyRole")).put("PERSON_ID", new FieldMapper("personId"))
			.put("LAST_NAME", new FieldMapper("lastName")).put("CONTACT_TEXT", new FieldMapper("contactText"))
			.put("SEAL_FLAG", new FieldMapper("sealFlag")).put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime")).put("FIRST_NAME", new FieldMapper("firstName"))
			.put("PARTY_ROLE_TEXT", new FieldMapper("partyRoleText")).build();
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DISTINCT", new FieldMapper("distinct")).put("FIRST_NAME", new FieldMapper("firstName ")).build();

	/**
	 * Creates new OcmssvctRepositoryImpl class Object
	 */
	public OcmssvctRepositoryImpl() {
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao CourseActivityParties
	 *
	 * @return List<CourseActivityParties>
	 *
	 * @throws SQLException
	 */
	public List<CourseActivityParties> crsActPtyExecuteQuery(final CourseActivityParties objSearchDao) {
		final String sql = getQuery("OCMSSVCT_CRSACTPTY_FIND_COURSE_ACTIVITY_PARTIES");
		final RowMapper<CourseActivityParties> crseActivRowMapr = Row2BeanRowMapper.makeMapping(sql,
				CourseActivityParties.class, curseActivityMaping);
		return (ArrayList<CourseActivityParties>) namedParameterJdbcTemplate.query(sql,
				createParams("crsActyId", objSearchDao.getCrsActyId()), crseActivRowMapr);

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao CourseActivityParties
	 *
	 * @return List<CourseActivityParties>
	 *
	 * @throws SQLException
	 */
	public List<CourseActivityParties> extConExecuteQuery(final CourseActivityParties objSearchDao) {
		final String sql = getQuery("OCMSSVCT_EXTCONPTY_FIND_COURSE_ACTIVITY_PARTIES");
		final RowMapper<CourseActivityParties> crseActivRowMapr = Row2BeanRowMapper.makeMapping(sql,
				CourseActivityParties.class, curseActivityMaping);
		return (ArrayList<CourseActivityParties>) namedParameterJdbcTemplate.query(sql,
				createParams("crsActyId", objSearchDao.getCrsActyId()), crseActivRowMapr);

	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstCourseActivityParties List<CourseActivityParties>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public Integer crsActPtyInsertCourseActivityParties(final List<CourseActivityParties> list) {

		final String sql = getQuery("OCMSSVCT_CRSACTPTY_INSERT_COURSE_ACTIVITY_PARTIES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final CourseActivityParties object : list) {
			parameters.add(new BeanPropertySqlParameterSource(object));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));

		if (list.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstCourseActivityParties List<CourseActivityParties>
	 *
	 * @throws SQLException
	 */
	public Integer crsActPtyUpdateCourseActivityParties(final List<CourseActivityParties> list) {
		final String sql = getQuery("OCMSSVCT_CRSACTPTY_UPDATE_COURSE_ACTIVITY_PARTIES");

		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final CourseActivityParties object : list) {
			parameters.add(new BeanPropertySqlParameterSource(object));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (list.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstCourseActivityParties List<CourseActivityParties>
	 *
	 * @throws SQLException
	 */
	public CourseActivityParties crsActPtyDeleteCourseActivityParties(final List<CourseActivityParties> list) {
		final CourseActivityParties returnData = new CourseActivityParties();
		final String sql = getQuery("OCMSSVCT_CRSACTPTY_DELETE_COURSE_ACTIVITY_PARTIES");

		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final CourseActivityParties object : list) {
			parameters.add(new BeanPropertySqlParameterSource(object));
		}
		try {
			String tableName = "COURSE_ACTIVITY_PARTIES";
			String whereClause = "CRS_ACTY_PARTY_ID  = :crsActyPartyId";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method crsActPtyDeleteCourseActivityParties", e);
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			String error = "Error : " + e.getMessage();
			if (error.contains("constraint")) {
				error = error.substring(error.indexOf("constraint"), error.indexOf("\" on"))
						.replaceFirst("constraint", "").trim();
				final String tableName = errorNameValidation(error.substring(1, error.length()));
				returnData.setServerCode(2292);
				returnData.setSealFlag(tableName);
				return returnData;
			}
		}

		if (list.size() == returnArray.length) {
			returnData.setReturnValue(1);
			return returnData;
		} else {
			returnData.setReturnValue(0);
			return returnData;
		}

	}

	public String errorNameValidation(final String errorName) {
		final String sql = getQuery("OCMSSVCT_CONSTRAINT_VALIDATIONS");
		String returnData = null;
		try {
			returnData = namedParameterJdbcTemplate.queryForObject(sql, createParams("CONSTRAINTNAME", errorName),
					String.class);
		} catch (Exception e) {
			returnData = null;
			return returnData;
		}
		return returnData;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	//
	public List<ReferenceCodes> rgStaffNameInstRecordGroup(final String caseloadType, final String providerPartyCode) {
		final String sql = getQuery("OCMSSVCT_FIND_RGSTAFFNAMEINST");
		final RowMapper<ReferenceCodes> mrowMaper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				mmteamMembersMapping);

		try {
			return namedParameterJdbcTemplate.query(sql,
					createParams("caseloadType", caseloadType, "providerPartyId", providerPartyCode), mrowMaper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("rgStaffNameInstRecordGroup :Ocmssvct" + e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<MMTeamMembers>
	 */
	public List<ReferenceCodes> rgStaffNameCommRecordGroup(final Long providerId) {
		final String sql = getQuery("OCMSSVCT_FIND_RGSTAFFNAMECOMM");
		final RowMapper<ReferenceCodes> mrowMaper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				mmteamMembersMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams("pproviderpartyid", providerId), mrowMaper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("rgStaffNameCommRecordGroup :ocmssvct" + e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<MMTeamMembersMTeamMembers>
	 */
	public List<TeamMembers> rgTeamMembersRecordGroup() {
		final String sql = getQuery("OCMSSVCT_FIND_RGTEAMMEMBERS");
		final RowMapper<TeamMembers> mrowMaper = Row2BeanRowMapper.makeMapping(sql, TeamMembers.class,
				mmteamMembersMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mrowMaper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("rgStaffNameCommRecordGroup :Ocmssvct" + e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<MMTeamMembersMTeamMembersM>
	 */
	public List<ReferenceCodes> rgStaffNameInstProgRecordGroup(final String providerPartyCode, final Long programId) {
		final String sql = getQuery("OCMSSVCT_FIND_RGSTAFFNAMEINSTPROG");
		final RowMapper<ReferenceCodes> mRowMaper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				mmteamMembersMapping);

		try {
			return namedParameterJdbcTemplate.query(sql,
					createParams("providerPartyCode", providerPartyCode, "programId", programId), mRowMaper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("rgStaffNameInstProgRecordGroup :Ocmssvct" + e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<MMTeamMembersMTeamMembersMMTeamMembers>
	 */
	public List<ReferenceCodes> rgStaffNameCommProgRecordGroup(final Long providerPartyId, final Long programId) {
		final String sql = getQuery("OCMSSVCT_FIND_RGSTAFFNAMECOMMPROG");
		final RowMapper<ReferenceCodes> mRowMaper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				mmteamMembersMapping);

		try {
			return namedParameterJdbcTemplate.query(sql,
					createParams("providerPartyId", providerPartyId, "programId", programId), mRowMaper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("rgStaffNameCommProgRecordGroup :Ocmssvct" + e);
			return Collections.emptyList();
		}
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * crsActPtyPreInsert
	 *
	 * @param params
	 *
	 */
	public Object crsActPtyPreInsert() {
		final String sql = getQuery("OCMSSVCT_CRS_ACT_PTY_PREINSERT");
		final RowMapper<Dual> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Dual.class, mMapping);
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * extConPreInsert
	 *
	 * @param params
	 *
	 */
	public Object extConPreInsert() {
		final String sql = getQuery("OCMSSVCT_EXT_CON_PREINSERT");
		final RowMapper<Dual> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Dual.class, mMapping);
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
	}

	public List<StaffMembers> getStaffMemberRecord(final BigDecimal staffId) {
		final String sql = getQuery("OCMSSVCT_STAFF_RECORD");
		final RowMapper<StaffMembers> mRowMaper = Row2BeanRowMapper.makeMapping(sql, StaffMembers.class,
				mmteamMembersMapping);
		return (ArrayList<StaffMembers>) namedParameterJdbcTemplate.query(sql, createParams("staffId", staffId),
				mRowMaper);

	}

	public Long getCrtPartyId() {
		final String sql = getQuery("OCMSSVCT_CRS_ACT_PTY_PREINSERT");
		Long returnObj = null;
		returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), Long.class);
		return returnObj;
	}

}
