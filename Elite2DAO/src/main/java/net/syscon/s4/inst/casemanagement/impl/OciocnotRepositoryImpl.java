package net.syscon.s4.inst.casemanagement.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.inst.casemanagement.OciocnotRepository;
import net.syscon.s4.inst.casemanagement.beans.VOffenderCaseNotes;

/**
 * Class OciocnotRepositoryImpl
 * 
 */
@Repository
public class OciocnotRepositoryImpl extends RepositoryBase implements OciocnotRepository {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OciocnotRepositoryImpl.class.getName());


	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("WORK_TYPE", new FieldMapper("workType")).put("GETDESCCODE", new FieldMapper("getdesccode"))
			.put("WORK_SUB_TYPE", new FieldMapper("workSubType")).build();
	private final Map<String, FieldMapper> vOffenderCaseNotesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_ID_DISPLAY", new FieldMapper("offenderIdDisplay"))
			.put("IWP_FLAG", new FieldMapper("iwpFlag")).put("CONTACT_TYPE", new FieldMapper("contactType"))
			.put("AMENDMENT_FLAG", new FieldMapper("amendmentFlag"))
			.put("CASE_NOTE_TYPE", new FieldMapper("caseNoteType")).put("CONTACT_DATE", new FieldMapper("contactDate"))
			.put("LAST_NAME", new FieldMapper("lastName")).put("NOTE_SOURCE_CODE", new FieldMapper("noteSourceCode"))
			.put("NOTE_SOURCE", new FieldMapper("noteSource"))
			.put("CONTACT_SUB_TYPE", new FieldMapper("contactSubType"))
			.put("TIME_CREATION", new FieldMapper("timeCreation")).put("CHECK_BOX1", new FieldMapper("checkBox1"))
			.put("CHECK_BOX3", new FieldMapper("checkBox3")).put("STAFF_NAME", new FieldMapper("staffName"))
			.put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId")).put("CHECK_BOX5", new FieldMapper("checkBox5"))
			.put("STAFF_ID", new FieldMapper("staffId")).put("CONTACT_TIME", new FieldMapper("contactTime"))
			.put("EVENT_ID", new FieldMapper("eventId")).put("CASE_NOTE_SUB_TYPE", new FieldMapper("caseNoteSubType"))
			.put("CASE_NOTE_TEXT", new FieldMapper("caseNoteText"))
			.put("DATE_CREATION", new FieldMapper("dateCreation")).put("CASE_NOTE_ID", new FieldMapper("caseNoteId"))
			.put("CHECK_BOX2", new FieldMapper("checkBox2")).put("CHECK_BOX4", new FieldMapper("checkBox4"))
			.put("FIRST_NAME", new FieldMapper("firstName")).build();


	/**
	 * Creates new OciocnotRepositoryImpl class Object
	 */
	public OciocnotRepositoryImpl() {
		// OciocnotRepositoryImpl
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            VOffenderCaseNotes
	 *
	 * @return List<VOffenderCaseNotes>
	 *
	 */
	public List<VOffenderCaseNotes> caseNoteExecuteQuery(final VOffenderCaseNotes objSearchDao) {
		final String sql = getQuery("OCIOCNOT_CASENOTE_FIND_V_OFFENDER_CASE_NOTES");
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource params = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" WHERE ");
			sqlQuery.append(
					"offender_book_id IN ( select CN.OFFENDER_BOOK_ID from offender_case_notes CN, CASE_PLANS CP left join case_plan_staff_roles cpsr on (CP.offender_book_id =cpsr.offender_book_id and CP.case_plan_id =cpsr.case_plan_id) where CP.OFFENDER_BOOK_ID = CN.OFFENDER_BOOK_ID and case when CP.caseload_type ='INST' then cpsr.staff_id =:STAFFID and cpsr.cn_officer='Y' else CP.SAC_STAFF_ID = :STAFFID end and CP.END_DATE is null) AND ");
			params.addValue("STAFFID", objSearchDao.getStaffId());
			sqlQuery.append("DATE_TRUNC('D',V_OFFENDER_CASE_NOTES.CONTACT_DATE) BETWEEN  to_date('"
					+ new java.sql.Date(objSearchDao.getContactDate().getTime()) + "','yyyy/MM/dd') and to_date('"
					+ new java.sql.Date(objSearchDao.getDateCreation().getTime()) + "','yyyy/MM/dd') " + " AND ");

		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith(" WHERE")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("AND")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		preparedSql = preparedSql.concat(" ORDER BY CONTACT_DATE DESC,  LAST_NAME");
		final RowMapper<VOffenderCaseNotes> vOffCaseNotesMap = Row2BeanRowMapper.makeMapping(sql,
				VOffenderCaseNotes.class, vOffenderCaseNotesMapping);
		final ArrayList<VOffenderCaseNotes> returnList = (ArrayList<VOffenderCaseNotes>) namedParameterJdbcTemplate
				.query(preparedSql, params, vOffCaseNotesMap);
		return returnList;
	}

	/**
	 * @param
	 *
	 */
	public int preInsert() {
		return 0;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgTypeRecordGroup() {
		final String sql = getQuery("OCIOCNOT_FIND_RGTYPE");
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
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgSubTypeRecordGroup() {
		final String sql = getQuery("OCIOCNOT_FIND_RGSUBTYPE");
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
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgStaffNameRecordGroup() {
		final String sql = getQuery("OCIOCNOT_FIND_RGSTAFFNAME");
		List<ReferenceCodes> returnList = new ArrayList<ReferenceCodes>();
		final RowMapper<ReferenceCodes> mMRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				mMapping);

		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), mMRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("", e);
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgLocationRecordGroup(final String caseLoadId, String userName) {
		final String sql = getQuery("OCIOCNOT_FIND_RGLOCATION");
		List<ReferenceCodes> returnList = new ArrayList<ReferenceCodes>();
		final RowMapper<ReferenceCodes> mMMRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				mMapping);

		try {
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("CASELOADID", caseLoadId, "userId", userName), mMMRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("", e);
		}
		return returnList;
	}

	/**
	 * Used to get staffId from Staff Members
	 * 
	 * @return Integer
	 */
	public Integer toGetStaffId(String userName) {
		final String sql = getQuery("OCIOCNOT_GET_STAFF_ID");
		Integer returnValue = 0;
		try {
			returnValue = namedParameterJdbcTemplate.queryForObject(sql, createParams("userId", userName),
					Integer.class);
		} catch (EmptyResultDataAccessException e) {
			logger.error("", e);
		}
		return returnValue;
	}

	/**
	 * Used to get first name, last name from Staff Members
	 * 
	 * @return StaffMembers
	 */
	public StaffMembers toGetFirstAndLastName(final Integer staffId) {
		final String sql = getQuery("OCIOCNOT_GET_FIRST_LAST_NAME");
		StaffMembers returnList = new StaffMembers();
		final RowMapper<StaffMembers> staffMap = Row2BeanRowMapper.makeMapping(sql, StaffMembers.class, mMapping);
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("PSTAFFID", staffId), staffMap);
		} catch (EmptyResultDataAccessException e) {
			logger.error("", e);
		}
		return returnList;
	}

	public Integer checkPrevExists(String userName) {
		final String sql = getQuery("OCIOCNOT_CHECK_PRIVILEGE_EXISTS");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("userId", userName), Integer.class);
	}

}
