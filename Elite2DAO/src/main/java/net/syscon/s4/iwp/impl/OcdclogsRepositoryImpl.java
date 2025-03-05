package net.syscon.s4.iwp.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.cm.casemanagement.maintenance.beans.EventMeasures;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.OracleSimpleJdbcCall;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.Areas;
import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.im.beans.OffenderCaseNotes;
import net.syscon.s4.im.beans.OffenderNonAssociations;
import net.syscon.s4.inst.schedules.bean.OffenderIndSchedules;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules2;
import net.syscon.s4.inst.workflow.maintenance.beans.StaffLocationRoles;
import net.syscon.s4.iwp.OcdclogsRepository;
import oracle.jdbc.internal.OracleTypes;

/**
 * Class OcdclogsRepositoryImpl
 * 
 */
@Repository
public class OcdclogsRepositoryImpl extends RepositoryBase implements OcdclogsRepository {

	private static Logger logger = LogManager.getLogger(OcdclogsRepositoryImpl.class.getName());
	private final JdbcTemplate jdbcTemplate;

	/**
	 * Creates new OcdclogsRepositoryImpl class Object
	 */
	public OcdclogsRepositoryImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	private final Map<String, FieldMapper> referenceCodeMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description")).put("CODE", new FieldMapper("code"))
			.put("SEQVALUE", new FieldMapper("seqValue"))
			.put("DISPLAY", new FieldMapper("canDisplay"))
			.put("STAFFID", new FieldMapper("staffId")).put("ACTIVE_FLAG", new FieldMapper("activeFlag")).build();
	
	private final Map<String, FieldMapper> refMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description")).put("CODE", new FieldMapper("code"))
			.put("ACTIVE_FLAG", new FieldMapper("activeFlag")).build();

	private final Map<String, FieldMapper> agyLocMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description")).put("CODE", new FieldMapper("code"))
			.put("ACTIVE_FLAG", new FieldMapper("activeFlag")).build();

	private final Map<String, FieldMapper> vOffenderAllSchedulesExeMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("EVENT_TYPE", new FieldMapper("eventType")).put("EVENT_SUB_TYPE", new FieldMapper("eventSubType"))
			.put("EMAIL_FLAG",                   new FieldMapper("emailFlag"))
			.put("SMS_FLAG",                     new FieldMapper("smsFlag"))
			.put("SMS_SCHEDULE_HOURS_BEFORE",    new FieldMapper("smsScheduleHoursBefore"))
			.put("EMAIL_SCHEDULE_HOURS_BEFORE",  new FieldMapper("emailScheduleHoursBefore"))
			.put("EMAIL_FLAG_TEMP",              new FieldMapper("emailFlagTemp"))
			.put("SMS_FLAG_TEMP",                new FieldMapper("smsFlagTemp"))
			.put("NON_ASSOCIATION_FLAG",                new FieldMapper("nonAssociationFlag"))
			.put("INCHARGE_STAFF_NAME",                new FieldMapper("inChargeStaffName"))
			
			.build();

	private final Map<String, FieldMapper> schMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("EVENT_DATE", new FieldMapper("eventDate")).put("START_TIME", new FieldMapper("startTime"))
			.put("AGY_LOC_DESC", new FieldMapper("agyLocDesc")).build();

	private final Map<String, FieldMapper> vOffenderAllSchedulesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId")).build();

	private final Map<String, FieldMapper> mmmMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description")).build();

	private final Map<String, FieldMapper> offenderCaseNoteMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description")).build();

	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description")).build();
	
	private final Map<String, FieldMapper> eventMeasuresMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("EVENT_MEASURE_ID",            new FieldMapper("eventMeasureId"))
			.put("EVENT_TYPE",                  new FieldMapper("eventType"))
			.put("EVENT_SUB_TYPE",              new FieldMapper("eventSubType"))
			.put("MEASURES_STANDARD_FLAG",      new FieldMapper("measuresStandardFlag"))
			.put("LIST_SEQ",                    new FieldMapper("listSeq"))
			.put("EMAIL_FLAG",                   new FieldMapper("emailFlag"))
			.put("SMS_FLAG",                   new FieldMapper("smsFlag"))
			.put("NON_ASSOCIATION_FLAG",       new FieldMapper("nonAssociationFlag"))
			.build();
	
	private final Map<String, FieldMapper> staffmembersMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("STAFF_ID", new FieldMapper("staffId")).put("STAFF_NAME", new FieldMapper("staffName"))
			.put("USER_ID", new FieldMapper("userId")).build();
	
	/**
	 * Used to capture results from select query 1
	 */
	public List<ReferenceCodes> rgCasenoteTypeRecordGroup(final String caseloadType,String username) {
		final String sql = getQuery("OCDCLOGS_FIND_RGCASENOTETYPE");
		List<ReferenceCodes> returnList = new ArrayList<ReferenceCodes>();

		final RowMapper<ReferenceCodes> rowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodeMapping);
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("CASELOADTYPE", caseloadType, "WORKCASELOAD", caseloadType, "user",username), rowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("rgCasenoteTypeRecordGroup", e);
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query 2
	 */
	public List<ReferenceCodes> rgCasenoteSubtypeRecordGroup(final String caseNoteType,String username,String caseloadType) {
		final String sql = getQuery("OCDCLOGS_FIND_RGCASENOTESUBTYPE");
		List<ReferenceCodes> returnList = new ArrayList<ReferenceCodes>();

		final RowMapper<ReferenceCodes> rowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodeMapping);

		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("CASENOTETYPE", caseNoteType, "caseloadType", caseloadType, "user",username), rowMapper);
		} catch (Exception e) {
			logger.error("rgCasenoteSubtypeRecordGroup", e);
		}
		return returnList;
	}

	public List<OffenderCaseNotes> rgCasenotestaffNameRecordGroup(String caseloadId, String agyLocId,
			Long offenderBookId) {
		final String sql = getQuery("OCDCLOGS_FIND_RGCASE_NOTE_STAFF_NAME");
		List<OffenderCaseNotes> returnList = new ArrayList<OffenderCaseNotes>();

		final RowMapper<OffenderCaseNotes> rowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderCaseNotes.class,
				refMapping);

		try {
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("caseloadId", caseloadId, "agyLocId", agyLocId, "offenderBookId", offenderBookId),
					rowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("rgCasenotestaffNameRecordGroup", e);
		}
		return returnList;
	}

	/**
	 * Fetch the records from database table 3
	 */

	public List<OffenderCaseNotes> offNotesExecuteQuery(OffenderCaseNotes ocn) {
		final String sql = getQuery("OCDCLOGS_OFFNOTES_FIND_OFFENDER_CASE_NOTES");

		List<OffenderCaseNotes> returnList = new ArrayList<OffenderCaseNotes>();

		String preparedSql = null;

		final StringBuffer sqlQuery = new StringBuffer(sql);
		final MapSqlParameterSource inParameterSource = new MapSqlParameterSource();

		sqlQuery.append(" WHERE");

		if (ocn.getOffenderBookId() != null) {
			sqlQuery.append(" offender_book_id = :offenderBookId " + " AND ");
			inParameterSource.addValue("offenderBookId", ocn.getOffenderBookId());
		}

		if (ocn.getFromDate() != null && ocn.getToDate() != null) {
			sqlQuery.append(" CONTACT_DATE::date BETWEEN :NBT_FROM_DATE::date AND :NBT_TO_DATE::date " + " AND");
			inParameterSource.addValue("NBT_FROM_DATE", ocn.getFromDate());
			inParameterSource.addValue("NBT_TO_DATE", ocn.getToDate());
		}

		if (ocn.getFromDate() != null && ocn.getToDate() == null) {
			sqlQuery.append(" CONTACT_DATE::date >= :NBT_FROM_DATE::date" + " AND");
			inParameterSource.addValue("NBT_FROM_DATE", ocn.getFromDate());
		}

		if (ocn.getCaseNoteText() != null) {
			sqlQuery.append(" CASE_NOTE_TEXT  LIKE :CASE_NOTE_TEXT ");
			inParameterSource.addValue("CASE_NOTE_TEXT", "%" + ocn.getCaseNoteText() + "%");
		}
		inParameterSource.addValue("user",ocn.getCreateUserId());

		preparedSql = sqlQuery.toString().trim();

		if (preparedSql.endsWith("WHERE")) {
			preparedSql = preparedSql.trim().substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("AND")) {
			preparedSql = preparedSql.trim().substring(0, preparedSql.length() - 3);
		}
		preparedSql = preparedSql.concat(" ORDER BY  CONTACT_DATE DESC, CONTACT_TIME DESC ");

		final RowMapper<OffenderCaseNotes> OffenderCaseNotesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderCaseNotes.class, offenderCaseNoteMapping);

		try {
			returnList = namedParameterJdbcTemplate.query(preparedSql, inParameterSource, OffenderCaseNotesRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("offNotesExecuteQuery", e);
		}

		return returnList;
	}

	public Integer validateNoteTypeSubType(final OffenderCaseNotes objSearch) {
		final String sql = getQuery("OCDCLOGS_TYPE_SUB_TYPE");
		int count = 0;

		try {
			count = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("Type", objSearch.getCaseNoteType(), "subType", objSearch.getCaseNoteSubType()),
					Integer.class);

		} catch (Exception e) {
			logger.error("validateNoteTypeSubType", e);
		}
		return count;
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstOffenderCaseNotes List<OffenderCaseNotes>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public Integer offNotesInsertOffenderCaseNotes(List<OffenderCaseNotes> list) {
		String sql = getQuery("OCDCLOGS_OFFNOTES_INSERT_OFFENDER_CASE_NOTES");
		int[] returnArray = new int[] {};

		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();

		for (final OffenderCaseNotes ocn : list) {
			parameters.add(new BeanPropertySqlParameterSource(ocn));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (final Exception e) {
			logger.error("offNotesInsertOffenderCaseNotes:", e);
		}

		if (list.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Integer getcaseNoteId() {
		final String sql = getQuery("OCDCLOGS_GETCASE_NOTE_ID");
		Integer returnList = 0;
		returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams(), Integer.class);
		return returnList;
	}

	@Override
	public Integer getStaffId(String userName) {
		final String sql = getQuery("OCDCLOGS_GET_STAFF_ID");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("USER_NAME", userName), Integer.class);

	}

	@Override
	public Integer checkPrivilegeExists(String lvRoleCode,String userName) {
		final String sql = getQuery("OCDCLOGS_CHE_PRI_EXISTS");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("lvRoleCode", lvRoleCode,"userName",userName), Integer.class);

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstOffenderCaseNotes List<OffenderCaseNotes>
	 *
	 * @throws SQLException
	 */
	public Integer offNotesUpdateOffenderCaseNotes(final List<OffenderCaseNotes> list) {
		String sql = getQuery("OCDCLOGS_OFFNOTES_UPDATE_OFFENDER_CASE_NOTES");
		int[] returnArray = new int[] {};

		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();

		for (OffenderCaseNotes offenderCaseNotes : list) {
			parameters.add(new BeanPropertySqlParameterSource(offenderCaseNotes));
		}

		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("offNotesUpdateOffenderCaseNotes:", e);
		}
		if (list.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstOffenderCaseNotes List<OffenderCaseNotes>
	 *
	 * @throws SQLException
	 */
	public Integer offNotesDeleteOffenderCaseNotes(OffenderCaseNotes obj) {
		String sql = getQuery("OCDCLOGS_OFFNOTES_DELETE_OFFENDER_CASE_NOTES");
		List<OffenderCaseNotes> list = new ArrayList<OffenderCaseNotes>();
		list.add(obj);

		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();

		for (OffenderCaseNotes offenderCaseNotes : list) {
			parameters.add(new BeanPropertySqlParameterSource(offenderCaseNotes));
		}
		try {
			String tableName = "OFFENDER_CASE_NOTES";
			String whereCondition = "OFFENDER_BOOK_ID=:offenderBookId AND CASE_NOTE_ID  = :caseNoteId";
			batchUpdatePreDeletedRows(tableName, whereCondition, parameters);
		} catch (Exception e) {
			logger.error(e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));

		if (list.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao VOffenderAllSchedules
	 *
	 * @return List<VOffenderAllSchedules>
	 *
	 * @throws SQLException
	 */
	public List<VOffenderAllSchedules> offSchExecuteQuery(VOffenderAllSchedules ocn) {

		final String sql = getQuery("OCDCLOGS_OFFSCH_FIND_V_OFFENDER_ALL_SCHEDULES");
		List<VOffenderAllSchedules> returnList = new ArrayList<VOffenderAllSchedules>();

		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer(sql);
		final MapSqlParameterSource inParameterSource = new MapSqlParameterSource();
		if (ocn.getOffenderBookId() != null) {
			sqlQuery.append(" ois.offender_book_id = :offenderBookId " + " AND ");
			inParameterSource.addValue("offenderBookId", ocn.getOffenderBookId());
		}
		    if (ocn.getCtrlpsFromDate() != null) {
			sqlQuery.append(
					" ( event_status IN ('SCH', 'COMP','EXP', 'CANC')) "
							+ " AND");
			if (ocn.getCtrlpsFromDate() != null && ocn.getCtrlpsToDate() == null) {
				sqlQuery.append(" ois.EVENT_DATE >= :NBT_FROM_DATE " + " AND");
				inParameterSource.addValue("NBT_FROM_DATE", ocn.getCtrlpsFromDate());
			}

			if (ocn.getCtrlpsFromDate() != null && ocn.getCtrlpsToDate() != null) {
				sqlQuery.append(" ois.EVENT_DATE BETWEEN to_date('"
						+ new java.sql.Date(ocn.getCtrlpsFromDate().getTime()) + "','yyyy/MM/dd')  AND  to_date('"
						+ new java.sql.Date(ocn.getCtrlpsToDate().getTime()) + "','yyyy/MM/dd') " + " AND");
			}

			if (ocn.getCtrlpsStartTime() != null && ocn.getCtrlpsEndTime() == null) {
				sqlQuery.append(
						" COMBINE_DATE_TIME ( ois.EVENT_DATE,ois.START_TIME )>= COMBINE_DATE_TIME ( :NBT_FROM_DATE,:NBT_FROM_TIME) "
								+ " AND");
				inParameterSource.addValue("NBT_FROM_DATE", ocn.getCtrlpsFromDate());
				inParameterSource.addValue("NBT_FROM_TIME", ocn.getCtrlpsStartTime());
			}

			if (ocn.getCtrlpsStartTime() != null && ocn.getCtrlpsEndTime() != null) {
				sqlQuery.append(
						"COMBINE_DATE_TIME ( ois.EVENT_DATE, ois.START_TIME )>= COMBINE_DATE_TIME ( :NBT_FROM_DATE,:NBT_FROM_TIME) "
								+ "AND  ( COMBINE_DATE_TIME ( ois.EVENT_DATE, ois.START_TIME ) <= COMBINE_DATE_TIME ( :NBT_TO_DATE,:NBT_TO_TIME)  OR START_TIME IS NULL)"
								+ " AND");
				inParameterSource.addValue("NBT_FROM_DATE", ocn.getCtrlpsFromDate());
				inParameterSource.addValue("NBT_FROM_TIME", ocn.getCtrlpsStartTime());
				inParameterSource.addValue("NBT_TO_DATE", ocn.getCtrlpsToDate());
				inParameterSource.addValue("NBT_TO_TIME", ocn.getCtrlpsEndTime());
			}
			if (ocn.getCtrlpsOutCome() != null && ocn.getCtrlpsOutCome() != "PENDING") {
				sqlQuery.append(" ois.EVENT_OUTCOME= :NBT_EVENT_OUTCOME " + " AND");
				inParameterSource.addValue("NBT_EVENT_OUTCOME", ocn.getCtrlpsOutCome());
			}

			if (ocn.getCtrlpsOutCome() != null && ocn.getCtrlpsOutCome() == "PENDING") {
				sqlQuery.append(" ois.EVENT_OUTCOME IS NULL" + " AND");
			}
		} else {
			sqlQuery.append(
					" event_status IN ('SCH','COMP','EXP','CANC' )"
							+ " AND ( ( event_date BETWEEN trunc(sysdate) AND trunc(add_months(current_timestamp, 2)) )"
							+ " OR ( ( event_outcome IS NULL OR event_outcome IN ( 'FTA','FTC') ) AND event_date <= trunc(add_months(current_timestamp, 2))"
							+ " AND NOT EXISTS (  SELECT 'X'  FROM court_events c  WHERE c.event_date <= current_timestamp"
							+ "AND ois.event_id = c.event_id  ) ) ) " + "AND");
		}

		preparedSql = sqlQuery.toString().trim();

		if (preparedSql.endsWith("WHERE")) {
			preparedSql = preparedSql.trim().substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("AND")) {
			preparedSql = preparedSql.trim().substring(0, preparedSql.length() - 3);
		}

		preparedSql = preparedSql.concat(" ORDER BY  EVENT_DATE DESC , START_TIME ,EVENT_TYPE ");

		final RowMapper<VOffenderAllSchedules> VOffenderAllSchedulesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				VOffenderAllSchedules.class, vOffenderAllSchedulesExeMapping);

		try {
			returnList = namedParameterJdbcTemplate.query(preparedSql, inParameterSource,
					VOffenderAllSchedulesRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("offSchExecuteQuery", e);
		}
		/*if (ocn.getEventOutcome() != null) {
			if (ocn.getEventType() != "UW" && ocn.getEventType() != "ACP" && ocn.getEventType() != "SA"
					&& ocn.getEventType() != "DRR") {
				ocn.setNbtUpdOutcomeFlag("true");
			} else {
				ocn.setNbtUpdOutcomeFlag("false");
			}
		}*/
		return returnList;
	}

	public List<VOffenderAllSchedules> schExecuteQuery(final BigDecimal offenderBookId) {
		final String sql = getQuery("OCDCLOGS_SCH_EXECUTE_QUERY");

		List<VOffenderAllSchedules> returnList = new ArrayList<VOffenderAllSchedules>();

		final RowMapper<VOffenderAllSchedules> VOffenderAllSchedulesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				VOffenderAllSchedules.class, schMapping);

		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("offenderBookId", offenderBookId),
					VOffenderAllSchedulesRowMapper);
		} catch (Exception e) {
			logger.error("schExecuteQuery", e);
		}
		return returnList;
	}

	@Override
	public Integer offIndSchInsert(List<OffenderIndSchedules> insertList) {
		int returnValue = 0;
		final String sql = getQuery("OCDCLOGS_SCH_INSERT_OFFENDER_IND_SCHEDULES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderIndSchedules offAllSch : insertList) {
			parameters.add(new BeanPropertySqlParameterSource(offAllSch));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("offIndSchInsert", e);
		}
		if (insertList.size() == returnArray.length) {
			returnValue = 1;
		}
		return returnValue;
	}

	@Override
	public BigDecimal offSchEventId() {
		final String sql = getQuery("OCDCLOGS_OFF_SCH_EVENT_ID");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), BigDecimal.class);
	}

	@Override
	public BigDecimal inChargeStaffId(String staffName) {
		final String sql = getQuery("OCDCLOGS_OFF_SCH_STAFF_ID");
		String lName = "";
		String fName = "";
		BigDecimal returnVal=null;

		final String[] inputArray = staffName.split(",");

		if (inputArray.length > 0) {
			if (inputArray[0] != null && !inputArray[0].equals(""))
				lName = inputArray[0].trim();
			if (inputArray.length > 1 && inputArray[1] != null && !inputArray[1].equals(""))
				fName = inputArray[1].trim();
		}
		try {
			returnVal=namedParameterJdbcTemplate.queryForObject(sql, createParams("lName", lName, "fName", fName),
				BigDecimal.class);
		} catch(Exception e) {
			logger.error(e);
		}
		return returnVal;

	}

	@Override
	public Long checkForSheduleConflict(VOffenderAllSchedules offsch) {
		Long result = null;
		String query = getQuery("OCDCLOGS_OFF_SCH_PE_INSERT");
		SimpleDateFormat dateFormate = new SimpleDateFormat("yyyy/MM/dd");
		String eventDate = dateFormate.format(offsch.getEventDate());
		try {
			result = namedParameterJdbcTemplate.queryForObject(query,
					createParams("OFFENDER_BOOK_ID", offsch.getOffenderBookId(), "EVENT_DATE", eventDate), Long.class);
		} catch (Exception e) {
			logger.error(e);
		}
		return result;

	}

	@Override
	public BigDecimal offSchPostInsert(BigDecimal eventId) {
		BigDecimal returnVal=null;
		String query = getQuery("OCDCLOGS_OFF_SCH_POST_INSERT");
		try {
			returnVal = namedParameterJdbcTemplate.queryForObject(query, createParams("EVENT_ID", eventId),
					BigDecimal.class);
		} catch (Exception e) {
			logger.error(e);
		}
		return returnVal;
	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstVOffenderAllSchedules List<VOffenderAllSchedules>
	 *
	 * @throws SQLException
	 */
	public Integer offSchDeleteVOffenderAllSchedules(final List<VOffenderAllSchedules> lstVOffenderAllSchedules) {
		int deleteCount = 0;
		String sql = getQuery("OCDCLOGS_OFFSCH_DELETE_V_OFFENDER_ALL_SCHEDULES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (VOffenderAllSchedules vOffenderAllSchedules : lstVOffenderAllSchedules) {
			parameters.add(new BeanPropertySqlParameterSource(vOffenderAllSchedules));
		}
		try {
			String tableName = "V_OFFENDER_ALL_SCHEDULES";
			String whereCondition = null;
			batchUpdatePreDeletedRows(tableName, whereCondition, parameters);
		} catch (Exception e) {
			logger.error(e);
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("offIndSchInsert", e);
		}
		for (int i = 0; i < returnArray.length; i++) {
			deleteCount = deleteCount++;
		}
		if (lstVOffenderAllSchedules.size() == deleteCount) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public StaffMembers lvLoginUserStaffName(String userName) {
		StaffMembers staffName = new StaffMembers();
		final String sql = getQuery("OCDCLOGS_FIND_LOGING_STAFF_ID");
		final RowMapper<StaffMembers> rowMapper = Row2BeanRowMapper.makeMapping(sql, StaffMembers.class,
				staffmembersMapping);
		try {
			staffName = namedParameterJdbcTemplate.queryForObject(sql, createParams("USER_NAME",userName), rowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("lvLoginUserStaffId", e);
		}
		return staffName;
	}

	/**
	 * Used to capture results from select query
	 */
	public List<ReferenceCodes> rgEventOutcomeRecordGroup(String eventType, String eventSubType,
			String nbtUpdOutcomeFlag) {
		final String sql = getQuery("OCDCLOGS_FIND_RGEVENTOUTCOME");
		List<ReferenceCodes> returnList = new ArrayList<ReferenceCodes>();

		final RowMapper<ReferenceCodes> rowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodeMapping);

		try {
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("et", eventType, "est", eventSubType, "nbtUpdOutcomeFlag", nbtUpdOutcomeFlag),
					rowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("rgEventOutcomeRecordGroup", e);
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<AgencyLocations> rgLocationRecordGroup() {
		final String sql = getQuery("OCDCLOGS_FIND_RGLOCATION");

		List<AgencyLocations> recordList = new ArrayList<AgencyLocations>();

		final RowMapper<AgencyLocations> mRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				agyLocMapping);

		try {
			recordList = namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
		return recordList;
	}

	/**
	 * Used to capture results from select query
	 */
	public List<ReferenceCodes> rgScheduleTypeRecordGroup() {
		final String sql = getQuery("OCDCLOGS_FIND_RGSCHEDULETYPE");
		List<ReferenceCodes> returnList = new ArrayList<ReferenceCodes>();

		final RowMapper<ReferenceCodes> rowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodeMapping);

		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), rowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("rgScheduleTypeRecordGroup", e);
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgScheduleSubTypeRecordGroup(final String eventType) {
		final String sql = getQuery("OCDCLOGS_FIND_RGSCHEDULESUBTYPE");
		List<ReferenceCodes> returnList = new ArrayList<ReferenceCodes>();
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodeMapping);

		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("eventType", eventType), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("rgScheduleSubTypeRecordGroup", e);
		}
		return returnList;
	}

	/* okToDeleteRecord */
	@Override
	public Integer okToDeleteRecord(Integer cni) {
		Integer resultValue = 0;

		SqlParameterSource args = new MapSqlParameterSource().addValue("P_KEY_ID", cni)
				.addValue("P_TABLE_NAME", "OFFENDER_CASE_NOTES").addValue("P_COLUMN_NAME", "CASE_NOTE_ID")
				.addValue("P_EXCLUDE_TABLE", null).addValue("P_OWNER", "OMS_OWNER");

		SimpleJdbcCall jdbcCall = new OracleSimpleJdbcCall(jdbcTemplate).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_UTILS").withFunctionName("OK_TO_DELETE_RECORD")
				.withoutProcedureColumnMetaDataAccess();

		jdbcCall.declareParameters(new SqlOutParameter("RETURN_VALUE", Types.BOOLEAN),
				new SqlParameter("P_KEY_ID", Types.INTEGER), new SqlParameter("P_TABLE_NAME", Types.VARCHAR),
				new SqlParameter("P_COLUMN_NAME", Types.VARCHAR), new SqlParameter("P_EXCLUDE_TABLE", Types.VARCHAR),
				new SqlParameter("P_OWNER", Types.VARCHAR));

		Boolean result = jdbcCall.executeFunction(Boolean.class, args);
		if (result) {
			resultValue = 1;
		} else {
			resultValue = 0;
		}

		return resultValue;
	}

	/*
	 * Post Insert
	 */
	public Integer postInsert(OffenderCaseNotes obj) {
		int count = 0;
		Map<String, Object> returnObject = null;

		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[3];

		sqlParameters = new SqlParameter[] { new SqlParameter("P_OFFENDER_BOOK_ID", OracleTypes.NUMBER),
				new SqlParameter("P_CASENOTE_ID", OracleTypes.NUMBER),
				new SqlParameter("P_SCH_ID", OracleTypes.NUMBER) };

		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_CONTACT_LOG").withProcedureName("INS_NOTE_SCH_ASSOCIATION")
				.declareParameters(sqlParameters);
		inParamMap.put("P_OFFENDER_BOOK_ID", obj.getOffenderBookId());
		inParamMap.put("P_CASENOTE_ID", obj.getCaseNoteId());
		inParamMap.put("P_SCH_ID", null);

		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			if (returnObject.size() == 0) {
				count = 1;
			}
		} catch (Exception e) {
			logger.error("postInsert", e);
			count = 0;
		}
		return count;
	}

	@Override
	public Integer insNoteSchAssociation(BigDecimal OffenderBookId, BigDecimal eventId) {
		int count = 0;
		Map<String, Object> returnObject = null;

		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[3];

		sqlParameters = new SqlParameter[] { new SqlParameter("P_OFFENDER_BOOK_ID", OracleTypes.NUMBER),
				new SqlParameter("P_CASENOTE_ID", OracleTypes.NUMBER),
				new SqlParameter("P_SCH_ID", OracleTypes.NUMBER) };

		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_CONTACT_LOG").withProcedureName("INS_NOTE_SCH_ASSOCIATION")
				.declareParameters(sqlParameters);
		inParamMap.put("P_OFFENDER_BOOK_ID", OffenderBookId);
		inParamMap.put("P_CASENOTE_ID", null);
		inParamMap.put("P_SCH_ID", eventId);

		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			if (returnObject.size() == 0) {
				count = 1;
			}
		} catch (Exception e) {
			count = 0;
		}
		return count;
	}

	@Override
	public Integer updateSchedule1(VOffenderAllSchedules offsch) {
		int count = 0;
		Map<String, Object> returnObject = null;

		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[13];

		sqlParameters = new SqlParameter[] { new SqlParameter("P_EVENT_DATE", OracleTypes.DATE),
				new SqlParameter("P_START_TIME", OracleTypes.DATE), new SqlParameter("P_END_TIME", OracleTypes.DATE),
				new SqlParameter("P_AGY_LOC_ID", OracleTypes.VARCHAR),
				new SqlParameter("P_IN_CHARGE_STAFF_ID", OracleTypes.NUMBER),
				new SqlParameter("P_COMMENT_TEXT", OracleTypes.VARCHAR),
				new SqlParameter("P_EVENT_OUTCOME", OracleTypes.VARCHAR),
				new SqlParameter("P_EVENT_ID", OracleTypes.NUMBER), new SqlParameter("P_FLAG", OracleTypes.VARCHAR),
				new SqlParameter("P_OFF_BOOK_ID", OracleTypes.NUMBER),
				new SqlParameter("P_EVENT_TYPE", OracleTypes.VARCHAR), new SqlParameter("P_REF_ID", OracleTypes.NUMBER),
				new SqlParameter("P_EVENT_STATUS", OracleTypes.VARCHAR), };

		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_CONTACT_LOG").withProcedureName("UPDATE_SCHEDULE")
				.declareParameters(sqlParameters);

		inParamMap.put("P_EVENT_DATE", offsch.getEventDate());
		inParamMap.put("P_START_TIME", offsch.getStartTime());
		inParamMap.put("P_END_TIME", offsch.getEndTime());
		inParamMap.put("P_AGY_LOC_ID", offsch.getToAgyLocId());
		inParamMap.put("P_IN_CHARGE_STAFF_ID", offsch.getInChargeStaffId());
		inParamMap.put("P_COMMENT_TEXT", offsch.getCommentText());
		inParamMap.put("P_EVENT_OUTCOME", offsch.getEventOutcome());
		inParamMap.put("P_EVENT_ID", offsch.getEventId());
		inParamMap.put("P_FLAG", offsch.getUnexcusedAbsenceFlag());
		inParamMap.put("P_OFF_BOOK_ID", null);
		inParamMap.put("P_EVENT_TYPE", null);
		inParamMap.put("P_REF_ID", null);
		inParamMap.put("P_EVENT_STATUS", offsch.getEventStatus());

		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			if (returnObject.size() == 0) {
				count = 1;
			}
		} catch (Exception e) {
			count = 0;
		}
		return count;
	}

	@Override
	public Integer adjustUa(VOffenderAllSchedules offsch) {
		int count = 0;
		Map<String, Object> returnObject = null;

		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[6];

		sqlParameters = new SqlParameter[] { new SqlParameter("P_EVENT_ID", OracleTypes.NUMBER),
				new SqlParameter("P_EVENT_TYPE", OracleTypes.VARCHAR),
				new SqlParameter("P_EVENT_SUB_TYPE", OracleTypes.VARCHAR),
				new SqlParameter("P_OLD_OUTCOME", OracleTypes.VARCHAR),
				new SqlParameter("P_NEW_OUTCOME", OracleTypes.VARCHAR),
				new SqlParameter("P_COUNT_FLAG", OracleTypes.VARCHAR), };

		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_MULTIPLE_FAILURE").withProcedureName("ADJUST_UA")
				.declareParameters(sqlParameters);

		inParamMap.put("P_EVENT_ID", offsch.getEventId());
		inParamMap.put("P_EVENT_TYPE", offsch.getEventType());
		inParamMap.put("P_EVENT_SUB_TYPE", offsch.getEventSubType());
		inParamMap.put("P_OLD_OUTCOME", offsch.getEventOutcomeDb());
		inParamMap.put("P_NEW_OUTCOME", offsch.getEventOutcome());
		inParamMap.put("P_COUNT_FLAG", offsch.getUnexcusedAbsenceFlag());

		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			if (returnObject.size() == 0) {
				count = 1;
			}
		} catch (Exception e) {
			count = 0;
		}
		return count;
	}

	@Override
	public Integer updateSchedule2(VOffenderAllSchedules offsch) {
		int count = 0;
		Map<String, Object> returnObject = null;

		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("P_EVENT_DATE", offsch.getEventDate());
		inParamMap.put("P_START_TIME", offsch.getStartTime());
		inParamMap.put("P_END_TIME", offsch.getEndTime());
		inParamMap.put("P_AGY_LOC_ID", offsch.getToAgyLocId());
		inParamMap.put("P_IN_CHARGE_STAFF_ID", offsch.getInChargeStaffId());
		inParamMap.put("P_COMMENT_TEXT", offsch.getCommentText());
		inParamMap.put("P_EVENT_OUTCOME", offsch.getEventOutcome());
		inParamMap.put("P_EVENT_ID", offsch.getEventId());
		inParamMap.put("P_FLAG", offsch.getUnexcusedAbsenceFlag());
		inParamMap.put("P_OFF_BOOK_ID", offsch.getOffenderBookId());
		inParamMap.put("P_EVENT_TYPE", offsch.getEventType());
		inParamMap.put("P_REF_ID", offsch.getReferenceId());
		inParamMap.put("P_EVENT_STATUS", offsch.getEventStatus());
		try {
			namedParameterJdbcTemplate
			.update("call OMS_OWNER.TAG_CONTACT_LOG.UPDATE_SCHEDULE(:P_EVENT_DATE, :P_START_TIME, :P_END_TIME ,:P_AGY_LOC_ID, :P_IN_CHARGE_STAFF_ID, :P_COMMENT_TEXT, :P_EVENT_OUTCOME ,:P_EVENT_ID , :P_FLAG, :P_OFF_BOOK_ID, :P_EVENT_TYPE ,:P_REF_ID, :P_EVENT_STATUS)", inParamMap);
			count = 1;
			//objSearchDao.setReturnValue(1);
		} catch (Exception e) {
			logger.error("Exceptiong: deleteOffenderAssignment", e);
			count = 0;
			//objSearchDao.setReturnValue(0);
		}
		
		
		
		
		/*final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			if (returnObject.size() == 0) {
				count = 1;
			}
		} catch (Exception e) {
			count = 0;
		}*/
		return count;
	}

	@Override
	public Integer delNoteSchAssociation(VOffenderAllSchedules offSch) {
		int count = 0;
		Map<String, Object> returnObject = null;

		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[2];

		sqlParameters = new SqlParameter[] { new SqlParameter("P_OFFENDER_BOOK_ID", OracleTypes.NUMBER),
				new SqlParameter("P_SCH_ID", OracleTypes.NUMBER) };

		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_CONTACT_LOG").withProcedureName("DEL_NOTE_SCH_ASSOCIATION")
				.declareParameters(sqlParameters);
		inParamMap.put("P_OFFENDER_BOOK_ID", offSch.getOffenderBookId());
		inParamMap.put("P_SCH_ID", offSch.getEventId());

		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			if (returnObject.size() == 0) {
				count = 1;
			}
		} catch (Exception e) {
			count = 0;
		}
		return count;
	}

	/* okToDeleteRecord */
	@Override
	public Integer okToDeleteRecordDelOperation(VOffenderAllSchedules offSch) {
		Integer resultValue = 0;

		SqlParameterSource args = new MapSqlParameterSource().addValue("P_KEY_ID", offSch.getEventId())
				.addValue("P_TABLE_NAME", "OFFENDER_IND_SCHEDULES").addValue("P_COLUMN_NAME", "EVENT_ID")
				.addValue("P_EXCLUDE_TABLE", null).addValue("P_OWNER", "OMS_OWNER");

		SimpleJdbcCall jdbcCall = new OracleSimpleJdbcCall(jdbcTemplate).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_UTILS").withFunctionName("OK_TO_DELETE_RECORD")
				.withoutProcedureColumnMetaDataAccess();

		jdbcCall.declareParameters(new SqlOutParameter("RETURN_VALUE", Types.BOOLEAN),
				new SqlParameter("P_KEY_ID", Types.INTEGER), new SqlParameter("P_TABLE_NAME", Types.VARCHAR),
				new SqlParameter("P_COLUMN_NAME", Types.VARCHAR), new SqlParameter("P_EXCLUDE_TABLE", Types.VARCHAR),
				new SqlParameter("P_OWNER", Types.VARCHAR));

		Boolean result = jdbcCall.executeFunction(Boolean.class, args);
		if (result) {
			resultValue = 1;
		} else {
			resultValue = 0;
		}
		return resultValue;
	}

	public Integer deleteSchedule(VOffenderAllSchedules offSch) {
		int count = 0;
		Map<String, Object> returnObject = null;

		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[1];

		sqlParameters = new SqlParameter[] { new SqlParameter("P_EVENT_ID", OracleTypes.NUMBER), };

		/*SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_SCHEDULE").withProcedureName("DELETE_SCHEDULE").declareParameters(sqlParameters);*/
		inParamMap.put("P_EVENT_ID", offSch.getEventId());

		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			
			namedParameterJdbcTemplate
			.update("call OMS_OWNER.TAG_SCHEDULE.DELETE_SCHEDULE(:P_EVENT_ID)", inParamMap);
			count = 1;
			/*returnObject = simpleJDBCCall.execute(inParameter);
			if (returnObject.size() == 0) {
				count = 1;
			}*/
		} catch (Exception e) {
			count = 0;
		}
		return count;
	}

	@Override
	public Integer deleteVirtualSchedule(VOffenderAllSchedules offSch) {
		int count = 0;
		Map<String, Object> returnObject = null;

		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[3];

		sqlParameters = new SqlParameter[] { new SqlParameter("P_OFF_BOOK_ID", OracleTypes.NUMBER),
				new SqlParameter("P_EVENT_TYPE", OracleTypes.VARCHAR),
				new SqlParameter("P_REF_ID", OracleTypes.NUMBER) };

		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_CONTACT_LOG").withProcedureName("DELETE_VIRTUAL_SCHEDULE")
				.declareParameters(sqlParameters);
		inParamMap.put("P_OFF_BOOK_ID", offSch.getOffenderBookId());
		inParamMap.put("P_EVENT_TYPE", offSch.getEventType());
		inParamMap.put("P_REF_ID", offSch.getReferenceId());

		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			if (returnObject.size() == 0) {
				count = 1;
			}
		} catch (Exception e) {
			count = 0;
		}
		return count;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgnoteSourceRecordGroup() {
		final String sql = getQuery("OCDCLOGS_FIND_RGNOTESOURCE");
		List<ReferenceCodes> returnArray = new ArrayList<ReferenceCodes>();
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			returnArray = namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("getModuleName :", e);
		}
		return returnArray;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<MMMM>
	 */
	public List<StaffLocationRoles> rgStaffnameRecordGroup() {
		final String sql = getQuery("OCDCLOGS_FIND_RGSTAFFNAME");
		List<StaffLocationRoles> returnArray = new ArrayList<StaffLocationRoles>();
		final RowMapper<StaffLocationRoles> mMMMRowMapper = Row2BeanRowMapper.makeMapping(sql, StaffLocationRoles.class,
				mmmMapping);

		try {
			returnArray = namedParameterJdbcTemplate.query(sql, createParams(), mMMMRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("getModuleName :", e);
		}
		return returnArray;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 */
	public List<OffenderCaseNotes> offBkgOnCheckDeleteMaster(OffenderCaseNotes paramBean) {
		final String sql = getQuery("OCDCLOGS_OFF_BKG_ONCHECKDELETEMASTER");
		final RowMapper<OffenderCaseNotes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderCaseNotes.class,
				offenderCaseNoteMapping);
		final ArrayList<OffenderCaseNotes> returnList = (ArrayList<OffenderCaseNotes>) namedParameterJdbcTemplate
				.query(sql, createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 */
	public List<VOffenderAllSchedules> offBkgOnCheckDeleteMaster(VOffenderAllSchedules paramBean) {
		final String sql = getQuery("OCDCLOGS_OFF_BKG_ONCHECKDELETEMASTER");
		final RowMapper<VOffenderAllSchedules> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				VOffenderAllSchedules.class, vOffenderAllSchedulesMapping);
		final ArrayList<VOffenderAllSchedules> returnList = (ArrayList<VOffenderAllSchedules>) namedParameterJdbcTemplate
				.query(sql, createParams(), columnRowMapper);
		return returnList;
	}

	@Override
	public String getModuleName(OffenderCaseNotes offNotes) {
		final String sql = getQuery("OCDCLOGS_GET_M_NAME");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("CASENOTETYPE", offNotes.getCaseNoteType(),
				"CASENOTESUBTYPE", offNotes.getCaseNoteSubType()), String.class);
	}

	public List<Areas> rglovOutComeRecordGroup() {
		final String sql = getQuery("OCDCLOGS_FIND_LOV_OUTCOME");
		List<Areas> returnList = new ArrayList<Areas>();
		final RowMapper<Areas> rowMapper = Row2BeanRowMapper.makeMapping(sql, Areas.class, referenceCodeMapping);
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), rowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("rglovOutComeRecordGroup", e);   
		}
		return returnList;
	}
	
	@Override
	public String getOutcomeUpdatedFlag(VOffenderAllSchedules offSch) {
		String outComeDescription = null;
		Map<String, Object> returnObject = null;

		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[2];

		sqlParameters = new SqlParameter[] { new SqlParameter("p_event_type", OracleTypes.VARCHAR),
				new SqlParameter("P_EVENT_SUB_TYPE", OracleTypes.VARCHAR),
				new SqlParameter("P_EVENT_OUTCOME", OracleTypes.VARCHAR),
				new SqlParameter("RETURN", OracleTypes.VARCHAR) };

		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_CONTACT_LOG").withFunctionName("CHECK_UPD_OUTCOME_LOG_VALID")
				.declareParameters(sqlParameters);
		inParamMap.put("P_EVENT_TYPE", offSch.getEventType());
		inParamMap.put("P_EVENT_SUB_TYPE", offSch.getEventSubType());
		inParamMap.put("P_EVENT_OUTCOME", offSch.getEventOutcome());
		

		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			outComeDescription=(String) returnObject.get("RETURN");
			
		} catch (Exception e) {
			outComeDescription = null;
		}
		return outComeDescription;
	}

	@Override
	public Integer getStaffIdOne(Integer caseNoteId) {
		final String sql = getQuery("OCDCLOGS_GET_STAFF_ID_ONE");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("casenoteid", caseNoteId), Integer.class);
	}

	@Override
	public String getcaseNoteTextData(OffenderCaseNotes caseNotesObj) {
		final String sql = getQuery("OCDCLOGS_GET_DEFAULT_CASE_NOTE_TEXT");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("caseNoteType", caseNotesObj.getCaseNoteType(),"caseNoteSubType",caseNotesObj.getCaseNoteSubType()), String.class);
	}
	
	@Override
	public List<OffenderIndSchedules> checkOffenderScheduleConflicts(OffenderNonAssociations offNa,VOffenderAllSchedules vOffSch) {
		final String sql = getQuery("OCDCLOGS_CHECK_SCHEDULE_CONFLICTS");
		List<OffenderIndSchedules> returnList = new ArrayList();
		final RowMapper<OffenderIndSchedules> rowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderIndSchedules.class, referenceCodeMapping);
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("offenderBookID",offNa.getNsOffenderBookId(),"eventDate",vOffSch.getEventDate(),
					                                                         "toAgyLocId",vOffSch.getToAgyLocId()),rowMapper);
		} catch (Exception e) {
			logger.error("in checkOffenderScheduleConflicts method : ", e);   
		}
		return returnList;
	}
	@Override
	public VOffenderAllSchedules2 getOldData(Long EventId) {
		final String sql = getQuery("GET_OLD_DATA_V_OFFENDER_ALL_SCHEDULES_2");
		VOffenderAllSchedules2 bean = new VOffenderAllSchedules2();
		try {
			bean = namedParameterJdbcTemplate.queryForObject(sql, createParams("EVENT_ID", EventId),
					new BeanPropertyRowMapper<VOffenderAllSchedules2>(VOffenderAllSchedules2.class));
		} catch (Exception e) {
			logger.error("getOldData", e);
		}
		return bean;
	}
	
	@Override
	public List<EventMeasures> getEmailSmsFlag(VOffenderAllSchedules beanOne) {
		final String sql = getQuery("OCDCLOGS__SCHEDULE_FIND_EVENT_MEASURES");
		final RowMapper<EventMeasures> EventMeasuresRowMapper = Row2BeanRowMapper.makeMapping(sql, EventMeasures.class,
				eventMeasuresMapping);
		final ArrayList<EventMeasures> returnList = (ArrayList<EventMeasures>) namedParameterJdbcTemplate.query(sql,
				createParams("eventType",beanOne.getEventType(),"eventSubType",beanOne.getEventSubType(),"offenderBookId",beanOne.getOffenderBookId()), EventMeasuresRowMapper);
		return returnList;
	}
	
	@Override
	public String getCancelFlag(VOffenderAllSchedules beanOne) {	
		final String sql = getQuery("OCDCLOGS_GET_CANCEL_FLAG_FOR_OUTCOME");
		String returnValue=null;
		try {			
			returnValue = namedParameterJdbcTemplate.queryForObject(sql, createParams("eventType",beanOne.getEventType(),"eventSubType",beanOne.getEventSubType(),"eventOutCome", beanOne.getEventOutcome()),
					String.class);
		} catch(Exception e) {
			logger.error("OCDCLOGS :getCancelFlag "+e.getStackTrace());
		}
		return returnValue;
	}
	
	@Override
	public String checkCasenoteSubType(final String caseNoteType, final String caseNoteCode, final String userName) {
		String sql = getQuery("OCDCLOGS_CHECK_CASENOTE_SUBTYPE");
		String subTypeFlag = "";
		try {
			subTypeFlag = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("CASENOTETYPE", caseNoteType, "workSubType", caseNoteCode, "user", userName), String.class);
		}catch(Exception e) {
			logger.error("Error in Class " + this.getClass().getName() +"In checkCasenoteSubType method",e);
		}
		return subTypeFlag;
	}

	@Override
	public List<AgencyLocations> getAllCorporates() {
		final String sql = getQuery("OCDCLOGS_ALL_CORPORATES");

		List<AgencyLocations> recordList = new ArrayList<AgencyLocations>();

		final RowMapper<AgencyLocations> mRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				agyLocMapping);
		try {
			recordList = namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
		return recordList;
	}
	
	@Override
	public String getAgencyLocationDescription(final String agyLocId) {
		String sql = getQuery("OCDCLOGS_GET_AGENCY_LOCATION_DESCRIPTION");
		String subTypeFlag = "";
		try {
			subTypeFlag = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("agyLocId", agyLocId), String.class);
		}catch(Exception e) {
			logger.error("Error in Class " + this.getClass().getName() +"In checkCasenoteSubType method",e);
		}
		return subTypeFlag;
	}
}
