package net.syscon.s4.inst.programswithoutschedules.impl;

import java.sql.Types;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.Dual;
import net.syscon.s4.im.beans.OffenderCaseNotes;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.inst.programswithoutschedules.OcdpnoteRepository;

@Repository
public class OcdpnoteRepositoryImpl extends RepositoryBase implements OcdpnoteRepository {
	
	private static Logger logger = LogManager.getLogger(OcdpnoteRepositoryImpl.class);
	
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("WORK_SUB_TYPE", new FieldMapper("workSubType"))
			.put("CODE", new FieldMapper("code")).build();

	private final Map<String, FieldMapper> offenderCaseNotesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDERBOOKID", new FieldMapper("offenderBookId"))
			.put("CONTACTDATE", new FieldMapper("contactDate"))
			.put("CONTACTTIME", new FieldMapper("contactTime"))
			.put("CASENOTETYPE", new FieldMapper("caseNoteType"))
			.put("CASENOTESUBTYPE", new FieldMapper("caseNoteSubType"))
			.put("STAFFID", new FieldMapper("staffId"))
			.put("CASENOTETEXT", new FieldMapper("caseNoteText"))
			.put("AMENDMENTFLAG", new FieldMapper("amendmentFlag"))
			.put("IWPFLAG", new FieldMapper("iwpFlag"))
			.put("CHECKBOX1", new FieldMapper("checkBox1"))
			.put("CHECKBOX2", new FieldMapper("checkBox2"))
			.put("CHECKBOX3", new FieldMapper("checkBox3"))
			.put("CHECKBOX4", new FieldMapper("checkBox4"))
			.put("CHECKBOX5", new FieldMapper("checkBox5"))
			.put("EVENTID", new FieldMapper("eventId"))
			.put("CASENOTEID", new FieldMapper("caseNoteId"))
			.put("NOTESOURCECODE", new FieldMapper("noteSourceCode"))
			.put("DATECREATION", new FieldMapper("dateCreation"))
			.put("TIMECREATION", new FieldMapper("timeCreation"))
			.put("SEALFLAG", new FieldMapper("sealFlag"))
			.put("OBJECTTYPE", new FieldMapper("objectType"))
			.put("OBJECTID", new FieldMapper("objectId"))
			.put("CREATEDATETIME", new FieldMapper("createDatetime"))
			.put("CREATEUSERID", new FieldMapper("createUserId"))
			.put("MODIFYDATETIME", new FieldMapper("modifyDatetime"))
			.put("MODIFYUSERID", new FieldMapper("modifyUserId")).build();

	/**
	 * Creates new OcdpnoteRepositoryImpl class Object
	 */
	public OcdpnoteRepositoryImpl() {
		// OcdpnoteRepositoryImpl
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            OffenderCaseNotes
	 *
	 * @return List<OffenderCaseNotes>
	 *
	 * 
	 */
	public List<OffenderCaseNotes> offenderCaseNotesExecuteQuery(final OffenderCaseNotes objSearchDao) {
		final String sql = getQuery("OCDPNOTE_OFFENDERCASENOTES_FIND_OFFENDER_CASE_NOTES");
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
			sqlQuery.append("STAFF_ID = (SELECT STAFF_ID FROM STAFF_MEMBERS WHERE USER_ID = upper('" + objSearchDao.getCreateUserId().toUpperCase() + "'))"
					+ " AND ");
			if (objSearchDao.getOffenderBookId() != null) {
				sqlQuery.append("OFFENDER_BOOK_ID = :offenderBookId" + " AND ");
									inParameterSource.addValue("offenderBookId", objSearchDao.getOffenderBookId());
			}
			
			if(objSearchDao.getObjectId()!=null && objSearchDao.getObjectType()!=null) {
				sqlQuery.append("OBJECT_ID= :objectId" + " AND ");
				inParameterSource.addValue("objectId", objSearchDao.getObjectId());
				
				sqlQuery.append("OBJECT_TYPE= :objectType" + " AND ");
				inParameterSource.addValue("objectType", objSearchDao.getObjectType());
				
				sqlQuery.append("CASE_NOTE_TYPE= :caseNOteType" + " AND ");
				inParameterSource.addValue("caseNOteType", objSearchDao.getCaseNoteType());
				
			}
			if (objSearchDao.getFromDate() != null && objSearchDao.getToDate() != null) {
				sqlQuery.append("CONTACT_DATE between to_date('"
						+ new java.sql.Date(objSearchDao.getFromDate().getTime()) + "','yyyy/MM/dd') and to_date('"
						+ new java.sql.Date(objSearchDao.getToDate().getTime()) + "','yyyy/MM/dd') " + " AND ");
			} else if (objSearchDao.getFromDate() != null && objSearchDao.getToDate() == null) {
				sqlQuery.append("CONTACT_DATE >= to_date('" + new java.sql.Date(objSearchDao.getFromDate().getTime())
						+ "','yyyy/MM/dd')" + " AND ");
			} else if (objSearchDao.getFromDate() == null && objSearchDao.getToDate() != null) {
				sqlQuery.append("CONTACT_DATE <= to_date('" + new java.sql.Date(objSearchDao.getToDate().getTime())
						+ "','yyyy/MM/dd')" + " AND ");
			}


		}
		
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith(" WHERE")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("AND")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		preSql = preparedSql.concat(" ORDER BY CONTACT_DATE DESC  ,CONTACT_TIME DESC");
		try {
			returnList = namedParameterJdbcTemplate.query(preSql, inParameterSource, OffenderCaseNotesRowMapper);
		} catch (final Exception e) {
			logger.error("offNotesExecuteQuery", e);
			
		}
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstOffenderCaseNotes
	 *            List<OffenderCaseNotes>
	 *
	 * @return List<Integer>
	 *
	 * 
	 */
	public Integer offenderCaseNotesInsertOffenderCaseNotes(final List<OffenderCaseNotes> lstOffenderCaseNotes) {
		final String sql = getQuery("OCDPNOTE_OFFENDERCASENOTES_INSERT_OFFENDER_CASE_NOTES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderCaseNotes offenderCaseNote : lstOffenderCaseNotes) {
			parameters.add(new BeanPropertySqlParameterSource(offenderCaseNote));
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
	 * @param lstOffenderCaseNotes
	 *            List<OffenderCaseNotes>
	 *
	 * 
	 */
	public Integer offenderCaseNotesUpdateOffenderCaseNotes(final List<OffenderCaseNotes> lstOffenderCaseNotes) {
		final String sql = getQuery("OCDPNOTE_OFFENDERCASENOTES_UPDATE_OFFENDER_CASE_NOTES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
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
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgSubTypeRecordGroup() {
		final String sql = getQuery("OCDPNOTE_FIND_RGSUBTYPE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (final EmptyResultDataAccessException e) {
			logger.error("In method rgSubTypeRecordGroup", e);
			return Collections.emptyList();
		}
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offenderCaseNotesWhenNewRecordInstance
	 *
	 * @param params
	 *
	 */
	public List<Object> offenderCaseNotesWhenNewRecordInstance(final Dual paramBean) {
		final String sql = getQuery("OCDPNOTE_OFFENDER_CASE_NOTES_WHENNEWRECORDINSTANCE");
		final RowMapper<Object> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Object.class, mMapping);
		final List<Object> returnList = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * createFormGlobals
	 *
	 * @param params
	 *
	 */
	public List<OmsModules> createFormGlobals(final OmsModules paramBean) {
		final String sql = getQuery("OCDPNOTE_CREATE_FORM_GLOBALS");
		final RowMapper<OmsModules> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OmsModules.class, mMapping);
		final ArrayList<OmsModules> returnList = (ArrayList<OmsModules>) namedParameterJdbcTemplate.query(sql,
				createParams(), columnRowMapper);
		return returnList;
	}

	public List<ReferenceCodes> ocdpnoteGlobalUserAndCaseloadtype(String userName) {
		final String sql = getQuery("OCDPNOTE_GLOBAL_USER_AND_CASELOADTYPE");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				mMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("userName", userName), columnRowMapper);
	}

	@Override
	public List<ReferenceCodes> ocdpnoteStaffMemberName() {
		final String sql = getQuery("OCDPNOTE_STAFF_MEMBER_NAME");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				mMapping);
		return namedParameterJdbcTemplate.query(sql, columnRowMapper);
	}

	@Override
	public String getModuleName(final OffenderCaseNotes objOffenderCaseNotes) {
		Map<String, Object> returnObject = null;
		String facility = null;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[3];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_CONTACT_TYPE", Types.VARCHAR),
				new SqlParameter("P_CONTACT_SUB_TYPE", Types.VARCHAR),new SqlOutParameter("P_MODULENAME", Types.VARCHAR) };
		final SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_CONTACT_LOG").withProcedureName("GET_MODULENAME").declareParameters(sqlParameters);
		inParamMap.put("P_CONTACT_TYPE", objOffenderCaseNotes.getCaseNoteType());
		inParamMap.put("P_CONTACT_SUB_TYPE", objOffenderCaseNotes.getCaseNoteSubType());

		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);

		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			facility = (String) returnObject.get("P_MODULENAME");
		} catch (final Exception e) {
			facility = null;
		}
		return facility;
	}

}
