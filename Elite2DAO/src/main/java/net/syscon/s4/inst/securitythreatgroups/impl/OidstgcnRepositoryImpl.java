package net.syscon.s4.inst.securitythreatgroups.impl;

import java.util.ArrayList;
import java.util.Collections;
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
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.im.beans.StgCaseNotes;
import net.syscon.s4.inst.securitythreatgroups.OidstgcnRepository;

/**
 * Class OidstgcnRepositoryImpl
 * 
 */
@Repository
public class OidstgcnRepositoryImpl extends RepositoryBase implements OidstgcnRepository {

	private static Logger logger = LogManager.getLogger(OidstgcnRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> refCodsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description")).build();
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description ")).build();
	private final Map<String, FieldMapper> stgCseNtsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("STG_ID", new FieldMapper("stgId")).put("NOTE_SEQ", new FieldMapper("noteSeq"))
			.put("NOTE_DATE", new FieldMapper("noteDate")).put("NOTE_TIME", new FieldMapper("noteTime"))
			.put("NOTE_TYPE", new FieldMapper("noteType")).put("NOTE_SUBTYPE", new FieldMapper("noteSubtype"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId")).put("TEXT", new FieldMapper("text"))
			.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId")).put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.build();
	private final Map<String, FieldMapper> omsModulesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("MODULE_NAME", new FieldMapper("moduleName")).put("DESCRIPTION", new FieldMapper("description"))
			.put("MODULE_TYPE", new FieldMapper("moduleType"))
			.put("PRINT_FORMAT_CODE", new FieldMapper("printFormatCode"))
			.put("PREVIEW_FLAG", new FieldMapper("previewFlag")).put("DEFAULT_COPY", new FieldMapper("defaultCopy"))
			.put("APPLN_CODE", new FieldMapper("applnCode")).put("HELP_DIRECTORY", new FieldMapper("helpDirectory"))
			.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId")).put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.put("OUTPUT_TYPE", new FieldMapper("outputType")).build();

	/**
	 * Creates new OidstgcnRepositoryImpl class Object
	 */
	public OidstgcnRepositoryImpl() {
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao StgCaseNotes
	 *
	 * @return List<StgCaseNotes>
	 */
	public List<StgCaseNotes> stgCaseNotesExecuteQuery(final StgCaseNotes objSearchDao) {
		final String sql = getQuery("OIDSTGCN_STGCASENOTES_FIND_STG_CASE_NOTES");
		final RowMapper<StgCaseNotes> StgCNtsRowMapper = Row2BeanRowMapper.makeMapping(sql, StgCaseNotes.class,
				stgCseNtsMapping);
		List<StgCaseNotes> returnList = new ArrayList<StgCaseNotes>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("STG_ID", objSearchDao.getStgId()),
					StgCNtsRowMapper);
		} catch (Exception e) {
			logger.error("stgCaseNotesExecuteQuery", e);
		}
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstStgCaseNotes List<StgCaseNotes>
	 *
	 * @return List<Integer>
	 *
	 */
	public Integer stgCaseNotesInsertStgCaseNotes(final List<StgCaseNotes> lstStgCaseNotes) {
		final String sql = getQuery("OIDSTGCN_STGCASENOTES_INSERT_STG_CASE_NOTES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final StgCaseNotes stgCaseNotes : lstStgCaseNotes) {
			parameters.add(new BeanPropertySqlParameterSource(stgCaseNotes));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstStgCaseNotes.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstStgCaseNotes List<StgCaseNotes>
	 *
	 */
	public Integer stgCaseNotesUpdateStgCaseNotes(final List<StgCaseNotes> lstStgCaseNotes) {
		final String sql = getQuery("OIDSTGCN_STGCASENOTES_UPDATE_STG_CASE_NOTES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final StgCaseNotes stgCaseNotes : lstStgCaseNotes) {
			parameters.add(new BeanPropertySqlParameterSource(stgCaseNotes));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstStgCaseNotes.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstStgCaseNotes List<StgCaseNotes>
	 *
	 */
	public Integer stgCaseNotesDeleteStgCaseNotes(final List<StgCaseNotes> lstStgCaseNotes) {
		final String sql = getQuery("OIDSTGCN_STGCASENOTES_DELETE_STG_CASE_NOTES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final StgCaseNotes stgCaseNotes : lstStgCaseNotes) {
			parameters.add(new BeanPropertySqlParameterSource(stgCaseNotes));
		}
		try {
			String tableName = "STG_CASE_NOTES";
			String whereCondition = "STG_ID =:stgId AND NOTE_SEQ =:noteSeq";
			batchUpdatePreDeletedRows(tableName, whereCondition, parameters);
		} catch (Exception e) {
			logger.error(e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstStgCaseNotes.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * rgNoteTypeRecordGroup
	 * 
	 * @return List<ReferenceCodes>
	 * 
	 */
	public List<ReferenceCodes> rgNoteTypeRecordGroup() {
		final String sql = getQuery("OIDSTGCN_FIND_RGNOTETYPE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		List<ReferenceCodes> returnList = new ArrayList<ReferenceCodes>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (Exception e) {
			logger.error("rgNoteTypeRecordGroup", e);
		}
		return returnList;
	}

	/**
	 * rgNoteReasonRecordGroup
	 * 
	 * @return List<ReferenceCodes>
	 * 
	 */
	public List<ReferenceCodes> rgNoteReasonRecordGroup() {
		final String sql = getQuery("OIDSTGCN_FIND_RGNOTEREASON");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		List<ReferenceCodes> returnList = new ArrayList<ReferenceCodes>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (Exception e) {
			logger.error("rgNoteReasonRecordGroup", e);
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * stgCaseNotesPreInsert
	 *
	 * @param STGID
	 *
	 */
	@Override
	public Long identifierSeqData(final Long stgId) {
		Long returnObj = null;
		final String sql = getQuery("OIDSTGCN_STG_CASE_NOTES_PREINSERT_PREINSERT");
		returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("STGID", stgId), Long.class);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * stgCaseNotesPostQuery
	 *
	 * List<ReferenceCodes>
	 *
	 */
	public List<ReferenceCodes> stgCaseNotesPostQuery(final ReferenceCodes paramBean) {
		final String sql = getQuery("OIDSTGCN_STG_CASE_NOTES_POSTQUERY_POSTQUERY");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				refCodsMapping);
		List<ReferenceCodes> returnList = new ArrayList<ReferenceCodes>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		} catch (Exception e) {
			logger.error("stgCaseNotesPostQueryPOSTQUERY", e);
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * createFormGlobals List<OmsModules>
	 *
	 */
	public List<OmsModules> createFormGlobals(final OmsModules paramBean) {
		final String sql = getQuery("OIDSTGCN_CREATE_FORM_GLOBALS");
		final RowMapper<OmsModules> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OmsModules.class,
				omsModulesMapping);
		List<OmsModules> returnList = new ArrayList<OmsModules>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		} catch (Exception e) {
			logger.error("createFormGlobals", e);
		}

		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * createFormGlobals List<String>
	 *
	 */

	public List<String> getParentCodes() {
		final String sql = getQuery("OIDSTGCN_GET_PARENT_CODE");
		try {
			return namedParameterJdbcTemplate.queryForList(sql, createParams(), String.class);
		} catch (Exception e) {
			logger.error("getParentCodes", e);
			return Collections.emptyList();
		}
	}

}
