package net.syscon.s4.inst.casemanagement.impl;

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
import net.syscon.s4.common.beans.CaseNotePermissions;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.OffenderCaseNotes;
import net.syscon.s4.inst.casemanagement.OidcnoteRepository;

/**
 * Class OidcnoteRepositoryImpl
 */
@Repository
public class OidcnoteRepositoryImpl extends RepositoryBase implements OidcnoteRepository {

	private static Logger logger = LogManager.getLogger(OidcnoteRepositoryImpl.class);

	private final Map<String, FieldMapper> referenceCodeMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description")).put("CODE", new FieldMapper("code"))
			.put("SEQVALUE", new FieldMapper("seqValue"))
			.put("DISPLAY", new FieldMapper("canDisplay"))
			.build();
	private final Map<String, FieldMapper> offenderCaseNotesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId"))
			.put("CONTACT_DATE", new FieldMapper("contactDate")).put("CONTACT_TIME", new FieldMapper("contactTime"))
			.put("CASE_NOTE_TYPE", new FieldMapper("caseNoteType"))
			.put("CASE_NOTE_SUB_TYPE", new FieldMapper("caseNoteSubType")).put("STAFF_ID", new FieldMapper("staffId"))
			.put("CASE_NOTE_TEXT", new FieldMapper("caseNoteText"))
			.put("AMENDMENT_FLAG", new FieldMapper("amendmentFlag")).put("IWP_FLAG", new FieldMapper("iwpFlag"))
			.put("CHECK_BOX1", new FieldMapper("checkBox1")).put("CHECK_BOX2", new FieldMapper("checkBox2"))
			.put("CHECK_BOX3", new FieldMapper("checkBox3")).put("CHECK_BOX4", new FieldMapper("checkBox4"))
			.put("CHECK_BOX5", new FieldMapper("checkBox5")).put("EVENT_ID", new FieldMapper("eventId"))
			.put("CASE_NOTE_ID", new FieldMapper("caseNoteId"))
			.put("NOTE_SOURCE_CODE", new FieldMapper("noteSourceCode"))
			.put("DATE_CREATION", new FieldMapper("dateCreation")).put("TIME_CREATION", new FieldMapper("timeCreation"))
			.put("SEAL_FLAG", new FieldMapper("sealFlag")).put("OBJECT_TYPE", new FieldMapper("objectType"))
			.put("OBJECT_ID", new FieldMapper("objectId")).put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId")).put("STAFF_NAME", new FieldMapper("staffName"))
			.build();

	private final Map<String, FieldMapper> staffmembersMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("STAFF_ID", new FieldMapper("staffId")).put("STAFF_NAME", new FieldMapper("staffName"))
			.put("USER_ID", new FieldMapper("userId")).build();

	/**
	 * Creates new OidcnoteRepositoryImpl class Object
	 */
	public OidcnoteRepositoryImpl() {
		// OidcnoteRepositoryImpl
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao OffenderCaseNotes
	 *
	 * @return List<OffenderCaseNotes>
	 *
	 * @
	 */
	public List<OffenderCaseNotes> offNotesExecuteQuery(OffenderCaseNotes objSearchDao) {
		final String sql = getQuery("OIDCNOTE_OFFNOTES_FIND_OFFENDER_CASE_NOTES");
		final RowMapper<OffenderCaseNotes> OffenderCaseNotesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderCaseNotes.class, offenderCaseNotesMapping);
		String preparedSql = null;
		String preSql = null;
		List<OffenderCaseNotes> returnList = new ArrayList<OffenderCaseNotes>();
		final MapSqlParameterSource inParameterSource = new MapSqlParameterSource();
		final StringBuffer sqlQuery = new StringBuffer();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" WHERE ");
			if (objSearchDao.getOffenderBookId() != null) {
				sqlQuery.append("OFC.OFFENDER_BOOK_ID = :OFFENDER_BOOK_ID" + " AND ");
				inParameterSource.addValue("OFFENDER_BOOK_ID", objSearchDao.getOffenderBookId());
			}
			if (objSearchDao.getContactDate() != null) {
				sqlQuery.append("OFC.CONTACT_DATE = :CONTACT_DATE" + " AND ");
				inParameterSource.addValue("CONTACT_DATE", objSearchDao.getContactDate());
			}
			if (objSearchDao.getFromDate() != null && objSearchDao.getToDate() != null) {
				sqlQuery.append("OFC.CONTACT_DATE between to_date('"
						+ new java.sql.Date(objSearchDao.getFromDate().getTime()) + "','yyyy/MM/dd') and to_date('"
						+ new java.sql.Date(objSearchDao.getToDate().getTime()) + "','yyyy/MM/dd')+1 " + " AND ");
			} else if (objSearchDao.getFromDate() != null && objSearchDao.getToDate() == null) {
				sqlQuery.append("OFC.CONTACT_DATE >= to_date('"
						+ new java.sql.Date(objSearchDao.getFromDate().getTime()) + "','yyyy/MM/dd')" + " AND ");
			} else if (objSearchDao.getFromDate() == null && objSearchDao.getToDate() != null) {
				sqlQuery.append("CONTACT_DATE <= to_date('" + new java.sql.Date(objSearchDao.getToDate().getTime())
						+ "','yyyy/MM/dd')" + " AND ");
			}

			if (objSearchDao.getContactTime() != null) {
				sqlQuery.append("OFC.CONTACT_TIME = :CONTACT_TIME" + " AND ");
				inParameterSource.addValue("CONTACT_TIME", objSearchDao.getContactTime());
			}

		}
		inParameterSource.addValue("user",objSearchDao.getCreateUserId());

		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith(" WHERE")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("AND")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		preSql = preparedSql.concat(" ORDER BY OFC.CONTACT_DATE DESC  ,OFC.CONTACT_TIME DESC");
		try {
			returnList = namedParameterJdbcTemplate.query(preSql, inParameterSource, OffenderCaseNotesRowMapper);
		} catch (Exception e) {
			logger.error("offNotesExecuteQuery", e);
			
		}
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstOffenderCaseNotes List<OffenderCaseNotes>
	 *
	 * @return List<Integer>
	 *
	 * @
	 */
	public Integer offNotesInsertOffenderCaseNotes(final List<OffenderCaseNotes> lstOffenderCaseNotes) {
		String sql = getQuery("OIDCNOTE_OFFNOTES_INSERT_OFFENDER_CASE_NOTES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderCaseNotes offenderCaseNotes : lstOffenderCaseNotes) {
			parameters.add(new BeanPropertySqlParameterSource(offenderCaseNotes));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenderCaseNotes.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstOffenderCaseNotes List<OffenderCaseNotes>
	 *
	 * @
	 */
	public Integer offNotesUpdateOffenderCaseNotes(final List<OffenderCaseNotes> lstOffenderCaseNotes) {
		String sql = getQuery("OIDCNOTE_OFFNOTES_UPDATE_OFFENDER_CASE_NOTES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderCaseNotes offenderCaseNotes : lstOffenderCaseNotes) {
			parameters.add(new BeanPropertySqlParameterSource(offenderCaseNotes));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenderCaseNotes.size() == returnArray.length) {
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
	 * @
	 */
	public Integer offNotesDeleteOffenderCaseNotes(final List<OffenderCaseNotes> lstOffenderCaseNotes) {
		String sql = getQuery("OIDCNOTE_OFFNOTES_DELETE_OFFENDER_CASE_NOTES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderCaseNotes offenderCaseNotes : lstOffenderCaseNotes) {
			parameters.add(new BeanPropertySqlParameterSource(offenderCaseNotes));
		}
		try {
			String tableName = "OFFENDER_CASE_NOTES";
			String whereClause = "OFFENDER_BOOK_ID=:offenderBookId";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method offNotesDeleteOffenderCaseNotes", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenderCaseNotes.size() == returnArray.length) {
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
	public List<ReferenceCodes> rgnoteSourceRecordGroup() {
		final String mode = "ENTER-QUERY";
		final String sql = getQuery("OIDCNOTE_FIND_RGNOTESOURCE");
		List<ReferenceCodes> returnList = new ArrayList<ReferenceCodes>();
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodeMapping);
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("MODE", mode), mRowMapper);
		} catch (Exception e) {
			logger.error("rgnoteSourceRecordGroup", e);
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<MM>
	 */
	public List<ReferenceCodes> rgCasenoteTypeRecordGroup(final String caseloadType, final String userName) {
		final String sql = getQuery("OIDCNOTE_FIND_RGCASENOTETYPE");
		List<ReferenceCodes> returnList = new ArrayList<ReferenceCodes>();
		final RowMapper<ReferenceCodes> mMRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodeMapping);
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("CASELOADTYPE", caseloadType, "WORKCASELOAD", caseloadType, "user", userName), mMRowMapper);
		} catch (Exception e) {
			logger.error("rgCasenoteTypeRecordGroup", e);
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<MMM>
	 */
	public List<ReferenceCodes> rgCasenoteSubtypeRecordGroup(final String caseNoteType, final String userName,String caseloadType) {
		final String sql = getQuery("OIDCNOTE_FIND_RGCASENOTESUBTYPE");
		final RowMapper<ReferenceCodes> mMMRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodeMapping);
		List<ReferenceCodes> returnList = new ArrayList<ReferenceCodes>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("CASENOTETYPE", caseNoteType, "CASELOADTYPE", caseloadType, "user", userName),
					mMMRowMapper);
		} catch (Exception e) {
			logger.error("rgCasenoteSubtypeRecordGroup", e);
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<StaffMembers> rgStaffnameRecordGroup(String userName) {
		final String sql = getQuery("OIDCNOTE_FIND_RGSTAFFNAME");
		final RowMapper<StaffMembers> mRowMapper = Row2BeanRowMapper.makeMapping(sql, StaffMembers.class,
				staffmembersMapping);
		List<StaffMembers> returnList = new ArrayList<StaffMembers>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("USER_NAME",userName), mRowMapper);
		} catch (Exception e) {
			logger.error("rgStaffnameRecordGroup", e);
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offBkgOnCheckDeleteMaster
	 *
	 * @param params
	 *
	 */
	public List<OffenderCaseNotes> offBkgOnCheckDeleteMaster(final OffenderCaseNotes paramBean) {
		final String sql = getQuery("OIDCNOTE_OFF_BKG_ONCHECKDELETEMASTER");
		final RowMapper<OffenderCaseNotes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderCaseNotes.class,
				offenderCaseNotesMapping);
		List<OffenderCaseNotes> returnList = new ArrayList<OffenderCaseNotes>();
		try {
			returnList = (ArrayList<OffenderCaseNotes>) namedParameterJdbcTemplate.query(sql, createParams(),
					columnRowMapper);
		} catch (Exception e) {
			logger.error("offBkgOnCheckDeleteMaster", e);
		}
		return returnList;
	}

	@Override
	public StaffMembers getStaffnameQuery(String userName) {
		final String sql = getQuery("OIDCNOTE_FIND_STAFFNAME");
		final RowMapper<StaffMembers> mRowMapper = Row2BeanRowMapper.makeMapping(sql, StaffMembers.class,
				staffmembersMapping);
		StaffMembers returnList = new StaffMembers();
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("USER_NAME", userName),
					mRowMapper);
		} catch (Exception e) {
			logger.error("rgStaffnameRecordGroup", e);
		}
		return returnList;
	}

	@Override
	public Integer getcaseNoteId() {
		final String sql = getQuery("OIDCNOTE_GETCASE_NOTE_ID");
		Integer returnList = 0;
		returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams(), Integer.class);
		return returnList;
	}

	@Override
	public String getmoduleName(final String caseNoteType, final String caseNoteSubType) {
		String sql = getQuery("OIDCNOTE_GET_MODULENAME");
		String moduleNmae = "";
		try {
		moduleNmae = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("CASENOTE_TYPE", caseNoteType, "CASENOTE_SUB_TYPE", caseNoteSubType), String.class);
		}catch(Exception e) {
			logger.error("Error in Class " + this.getClass().getName() +"In getmoduleName method",e);
		}
		return moduleNmae;
	}
	
	@Override
	public String checkCasenoteSubType(final String caseNoteType, final String caseNoteCode, final String userName) {
		String sql = getQuery("OIDCNOTE_CHECK_CASENOTE_SUBTYPE");
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
	public List<CaseNotePermissions> getCaseNoteLovs(String user) {
		final String sql = getQuery("OIDCNOTE_GET_CASE_NOTES");
		final RowMapper<CaseNotePermissions> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, CaseNotePermissions.class,
				offenderCaseNotesMapping);
		List<CaseNotePermissions> returnList = new ArrayList<CaseNotePermissions>();
		try {
			returnList =  namedParameterJdbcTemplate.query(sql, createParams("user",user),
					columnRowMapper);
		} catch (Exception e) {
			logger.error("getCaseNoteLovs", e);
		}
		return returnList;

	}
}
