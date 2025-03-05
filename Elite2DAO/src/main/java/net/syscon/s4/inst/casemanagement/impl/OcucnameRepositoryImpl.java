package net.syscon.s4.inst.casemanagement.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.OffenderCaseNotes;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.inst.casemanagement.OcucnameRepository;

/**
 * Class OcucnameRepositoryImpl
 */
@Repository
public class OcucnameRepositoryImpl extends RepositoryBase implements OcucnameRepository {


private final Map<String, FieldMapper> offenderCaseNotesMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("IWP_FLAG", 						new FieldMapper("iwpFlag"))
.put("AMENDMENT_FLAG", 					new FieldMapper("amendmentFlag"))
.put("CASE_NOTE_TYPE", 					new FieldMapper("caseNoteType"))
.put("CONTACT_DATE", 					new FieldMapper("contactDate"))
.put("NOTE_SOURCE_CODE", 				new FieldMapper("noteSourceCode"))
.put("SEAL_FLAG", 						new FieldMapper("sealFlag"))
.put("TIME_CREATION", 					new FieldMapper("timeCreation"))
.put("CREATE_DATETIME", 				new FieldMapper("createDatetime"))
.put("MODIFY_DATETIME", 				new FieldMapper("modifyDatetime"))
.put("CHECK_BOX1", 						new FieldMapper("checkBox1"))
.put("CHECK_BOX3", 						new FieldMapper("checkBox3"))
.put("OFFENDER_BOOK_ID", 				new FieldMapper("offenderBookId"))
.put("CREATE_USER_ID", 					new FieldMapper("createUserId"))
.put("CHECK_BOX5", 						new FieldMapper("checkBox5"))
.put("MODIFY_USER_ID", 					new FieldMapper("modifyUserId"))
.put("STAFF_ID", 						new FieldMapper("staffId"))
.put("CONTACT_TIME", 					new FieldMapper("contactTime"))
.put("EVENT_ID", 						new FieldMapper("eventId"))
.put("CASE_NOTE_SUB_TYPE", 				new FieldMapper("caseNoteSubType"))
.put("CASE_NOTE_TEXT", 					new FieldMapper("caseNoteText"))
.put("OBJECT_ID", 						new FieldMapper("objectId"))
.put("DATE_CREATION", 					new FieldMapper("dateCreation"))
.put("OBJECT_TYPE", 					new FieldMapper("objectType"))
.put("CASE_NOTE_ID", 					new FieldMapper("caseNoteId"))
.put("CHECK_BOX2", 						new FieldMapper("checkBox2"))
.put("CHECK_BOX4", 						new FieldMapper("checkBox4"))
.build();
private final Map<String, FieldMapper> omsModulesMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("MODULE_NAME", 						new FieldMapper("moduleName"))
.put("MODULE_TYPE", 						new FieldMapper("moduleType"))
.put("DESCRIPTION", 						new FieldMapper("description"))
.build();


private static Logger logger = LogManager.getLogger(OcucnameRepositoryImpl.class.getName());


	/**
	 * Creates new OcucnameRepositoryImpl class Object
	 */
	public OcucnameRepositoryImpl() {
		// OcucnameRepositoryImpl
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            OffenderCaseNotes
	 *
	 * @return List<OffenderCaseNotes>
	 *
	 * @throws SQLException
	 */
	public List<OffenderCaseNotes> offCaseNoteExecuteQuery(final OffenderCaseNotes objSearchDao) {
		final String sql = getQuery("OCUCNAME_OFFCASENOTE_FIND_OFFENDER_CASE_NOTES");
		final RowMapper<OffenderCaseNotes> OffenderCaseNotesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderCaseNotes.class, offenderCaseNotesMapping);
		final ArrayList<OffenderCaseNotes> returnList = (ArrayList<OffenderCaseNotes>) namedParameterJdbcTemplate
				.query(sql, createParams("P_CNOTE_BOOK_ID", objSearchDao.getOffenderBookId(), "P_CASE_NOTE_ID",
						objSearchDao.getCaseNoteId()), OffenderCaseNotesRowMapper);
		return returnList;
	}

	/**
	 * @param
	 *
	 * @throws SQLException
	 *
	 */
	public int preInsert() {
		return 0;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * ocucnameWhenNewFormInstanceWHEN-NEW-FORM-INSTANCE
	 *
	 * @param params
	 *
	 */
	public OffenderCaseNotes ocucnameWhenNewFormInstancewhenNewFormInstance(final OffenderCaseNotes paramBean) {
		final String sql = getQuery("OCUCNAME_OCUCNAME_WHENNEWFORMINSTANCE_WHENNEWFORMINSTANCE");
		final RowMapper<OffenderCaseNotes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderCaseNotes.class,
				offenderCaseNotesMapping);
		final OffenderCaseNotes returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * createFormGlobals
	 *
	 * @param params
	 *
	 */
	public OmsModules createFormGlobals(final OmsModules paramBean) {
		final String sql = getQuery("OCUCNAME_CREATE_FORM_GLOBALS");
		final RowMapper<OmsModules> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OmsModules.class,
				omsModulesMapping);
		final OmsModules returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstOffenderCaseNotes
	 *            List<OffenderCaseNotes>
	 *
	 * @
	 */
	public Integer offNotesUpdateOffenderCaseNotes(final List<OffenderCaseNotes> lstOffenderCaseNotes) {
		final String sql = getQuery("OCUCNAME_UPDATE_OFFENDER_CASE_NOTES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderCaseNotes offenderCaseNotes : lstOffenderCaseNotes) {
			parameters.add(new BeanPropertySqlParameterSource(offenderCaseNotes));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " offNotesUpdateOffenderCaseNotes  " + e);
		}
		if (lstOffenderCaseNotes.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

}
