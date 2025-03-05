package net.syscon.s4.inst.workflow.maintenance.impl;
import java.sql.SQLException;
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
import net.syscon.s4.cm.teamsworkflow.beans.Work;
import net.syscon.s4.common.beans.InternetAddresses;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.IwpTemplates;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.inst.workflow.maintenance.OcmworksRepository;
import net.syscon.s4.inst.workflow.maintenance.beans.WorkFunction;
import net.syscon.s4.inst.workflow.maintenance.beans.WorkIwpTemplate;
import net.syscon.s4.inst.workflow.maintenance.beans.WorkTrigger;
/**
 * Class OcmworksRepositoryImpl
 */
@Repository
public class OcmworksRepositoryImpl extends RepositoryBase implements OcmworksRepository{

	/**
	* Logger object used to print the log in the file
	*/
	private static Logger logger = LogManager.getLogger(OcmworksRepositoryImpl.class.getName());
	private final Map<String, FieldMapper> workIwpTemplatesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("WORK_ID", 						new FieldMapper("workId"))
			.build();
	private final Map<String, FieldMapper> internetAddressesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("WORK_ID", 						new FieldMapper("workId"))
			.put("RETURN_ADDRESS", 						new FieldMapper("returnAddress"))
			.build();
	private final Map<String, FieldMapper> workFunctionsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("WORK_ID", 						new FieldMapper("workId"))
			.build();
	private final Map<String, FieldMapper> omsModulesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("MODULE_NAME", 						new FieldMapper("moduleName"))
			.put("DESCRIPTION", 						new FieldMapper("description"))
			.put("CODE", 						new FieldMapper("code"))
			.build();
	private final Map<String, FieldMapper> workTriggersMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("WORK_ID", 						new FieldMapper("workId"))
			.build();
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", 						new FieldMapper("description"))
			.put("TEMPLATE_NAME", 						new FieldMapper("templateName"))
			.put("CODE", 						new FieldMapper("code"))
			.put("ACTIVE_FLAG", 						new FieldMapper("activeFlag"))
			.build();
	private final Map<String, FieldMapper> worksMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", 						new FieldMapper("description"))
			.put("TEMPLATE_NAME", 						new FieldMapper("templateName"))
			.put("CASENOTE_FLAG", 						new FieldMapper("casenoteFlag"))

			.put("templateCount", 						new FieldMapper("templateCount"))
			.put("triggerCount", 						new FieldMapper("triggerCount"))
			.put("functionCount", 						new FieldMapper("functionCount"))
			.put("emailRecipientsCount", 				new FieldMapper("emailRecipientsCount"))
			.put("emailReturnCount", 					new FieldMapper("emailReturnCount"))
			.put("CASE_NOTE_TEXT", 					    new FieldMapper("caseNoteText"))
			.build();

	/**
	 * Creates new OcmworksRepositoryImpl class Object
	 */
	public OcmworksRepositoryImpl() {
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            Works
	 *
	 * @return List<Works>
	 *
	 * @throws SQLException
	 */
	public List<Work> wfWorkTypesExecuteQuery(Work objSearchDao) {
		final String sql = getQuery("OCMWORKS_WFWORKTYPES_FIND_WORKS");
		final RowMapper<Work> WorksRowMapper = Row2BeanRowMapper.makeMapping(sql, Work.class, worksMapping);
		String preparedSql = null;
		final MapSqlParameterSource inParameterSource = new MapSqlParameterSource();
		final StringBuffer sqlQuery = new StringBuffer();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			if (objSearchDao.getWorkflowType() != null && !objSearchDao.getWorkflowType().isEmpty()) {
				sqlQuery.append(" WORKFLOW_TYPE = :WORKFLOW_TYPE" + " AND ");
				inParameterSource.addValue("WORKFLOW_TYPE", objSearchDao.getWorkflowType());
			}
			if (objSearchDao.getWorkType() != null && !objSearchDao.getWorkType().isEmpty()) {
				sqlQuery.append(" WORK_TYPE = :WORK_TYPE" + " AND ");
				inParameterSource.addValue("WORK_TYPE", objSearchDao.getWorkType());
			}
			if (objSearchDao.getWorkSubType() != null && !objSearchDao.getWorkSubType().isEmpty()) {
				sqlQuery.append(" WORK_SUB_TYPE = :WORK_SUB_TYPE" + " AND ");
				inParameterSource.addValue("WORK_SUB_TYPE", objSearchDao.getWorkSubType());
			}
			if (objSearchDao.getCaseloadType() != null && !objSearchDao.getCaseloadType().isEmpty()) {
				sqlQuery.append(" CASELOAD_TYPE = :CASELOAD_TYPE" + " AND ");
				inParameterSource.addValue("CASELOAD_TYPE", objSearchDao.getCaseloadType());
			}
			if (objSearchDao.getModuleName() != null && !objSearchDao.getModuleName().isEmpty()) {
				sqlQuery.append(" MODULE_NAME = :MODULE_NAME" + " AND ");
				inParameterSource.addValue("MODULE_NAME", objSearchDao.getModuleName());
			}
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("WHERE")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("AND")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		preparedSql = preparedSql + " ORDER BY ACTIVE_FLAG DESC, WORK_TYPE, WORK_SUB_TYPE";
		return namedParameterJdbcTemplate.query(preparedSql, inParameterSource, WorksRowMapper);

	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstWorks
	 *            List<Works>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public String wfWorkTypesInsertWorks(final List<Work> lstWorks) {
		String sql = getQuery("OCMWORKS_WFWORKTYPES_INSERT_WORKS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<>();
		for (Work works : lstWorks) {
			parameters.add(new BeanPropertySqlParameterSource(works));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			return e.getMessage().toUpperCase();
		}
		if (lstWorks.size() == returnArray.length) {
			return "1";
		} else {
			return null;
		}
	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstWorks
	 *            List<Works>
	 *
	 * @throws SQLException
	 */
	public Integer wfWorkTypesUpdateWorks(final List<Work> lstWorks) {
		String sql = getQuery("OCMWORKS_WFWORKTYPES_UPDATE_WORKS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<>();
		for (Work works : lstWorks) {
			parameters.add(new BeanPropertySqlParameterSource(works));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstWorks.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstWorks
	 *            List<Works>
	 *
	 * @throws SQLException
	 */
	public Integer wfWorkTypesDeleteWorks(final List<Work> lstWorks) {
		String sql = getQuery("OCMWORKS_WFWORKTYPES_DELETE_WORKS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<>();
		for (Work works : lstWorks) {
			parameters.add(new BeanPropertySqlParameterSource(works));
		}
		try {
			String tableName = "WORKS";
			String whereCondition = "WORK_ID=:workId";
			batchUpdatePreDeletedRows(tableName, whereCondition, parameters);
		} catch (Exception e) {
			logger.error(e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstWorks.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            WorkIwpTemplates
	 *
	 * @return List<WorkIwpTemplates>
	 *
	 * @throws SQLException
	 */
	public List<WorkIwpTemplate> wfIwpTemplatesExecuteQuery(WorkIwpTemplate objSearchDao) {
		final String sql = getQuery("OCMWORKS_WFIWPTEMPLATES_FIND_WORK_IWP_TEMPLATES");
		final RowMapper<WorkIwpTemplate> WorkIwpTemplatesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				WorkIwpTemplate.class, workIwpTemplatesMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("workId", objSearchDao.getWorkId()),
				WorkIwpTemplatesRowMapper);
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstWorkIwpTemplates
	 *            List<WorkIwpTemplates>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public String wfIwpTemplatesInsertWorkIwpTemplates(final List<WorkIwpTemplate> listObj) {
		String sql = getQuery("OCMWORKS_WFIWPTEMPLATES_INSERT_WORK_IWP_TEMPLATES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<>();
		for (WorkIwpTemplate workIwpTemplates : listObj) {
			parameters.add(new BeanPropertySqlParameterSource(workIwpTemplates));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			return e.getMessage().toUpperCase();
		}
		if (listObj.size() == returnArray.length) {
			return "1";
		} else {
			return null;
		}
	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstWorkIwpTemplates
	 *            List<WorkIwpTemplates>
	 *
	 * @throws SQLException
	 */
	public Integer wfIwpTemplatesUpdateWorkIwpTemplates(final List<WorkIwpTemplate> lstWorkIwpTemplates) {
		String sql = getQuery("OCMWORKS_WFIWPTEMPLATES_UPDATE_WORK_IWP_TEMPLATES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<>();
		for (WorkIwpTemplate workIwpTemplates : lstWorkIwpTemplates) {
			parameters.add(new BeanPropertySqlParameterSource(workIwpTemplates));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstWorkIwpTemplates.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstWorkIwpTemplates
	 *            List<WorkIwpTemplates>
	 *
	 * @throws SQLException
	 */
	public Integer wfIwpTemplatesDeleteWorkIwpTemplates(final List<WorkIwpTemplate> lstWorkIwpTemplates) {
		String sql = getQuery("OCMWORKS_WFIWPTEMPLATES_DELETE_WORK_IWP_TEMPLATES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<>();
		for (WorkIwpTemplate workIwpTemplates : lstWorkIwpTemplates) {
			parameters.add(new BeanPropertySqlParameterSource(workIwpTemplates));
		}
		try {
			String tableName = "WORK_IWP_TEMPLATES";
			String whereCondition = "WORK_ID=:workId  and TEMPLATE_ID=:templateId";
			batchUpdatePreDeletedRows(tableName, whereCondition, parameters);
		} catch (Exception e) {
			logger.error(e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstWorkIwpTemplates.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            WorkTriggers
	 *
	 * @return List<WorkTriggers>
	 *
	 * @throws SQLException
	 */
	public List<WorkTrigger> wfTriggersExecuteQuery(WorkTrigger objSearchDao) {
		final String sql = getQuery("OCMWORKS_WFTRIGGERS_FIND_WORK_TRIGGERS");
		final RowMapper<WorkTrigger> WorkTriggersRowMapper = Row2BeanRowMapper.makeMapping(sql, WorkTrigger.class,
				workTriggersMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("workId", objSearchDao.getWorkId()),
				WorkTriggersRowMapper);
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstWorkTriggers
	 *            List<WorkTriggers>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public String wfTriggersInsertWorkTriggers(final List<WorkTrigger> listObj) {
		String sql = getQuery("OCMWORKS_WFTRIGGERS_INSERT_WORK_TRIGGERS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<>();
		for (WorkTrigger workTriggers : listObj) {
			parameters.add(new BeanPropertySqlParameterSource(workTriggers));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			return e.getMessage().toUpperCase();
		}
		if (listObj.size() == returnArray.length) {
			return "1";
		} else {
			return null;
		}
	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstWorkTriggers
	 *            List<WorkTriggers>
	 *
	 * @throws SQLException
	 */
	public Integer wfTriggersUpdateWorkTriggers(final List<WorkTrigger> lstWorkTriggers) {
		String sql = getQuery("OCMWORKS_WFTRIGGERS_UPDATE_WORK_TRIGGERS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<>();
		for (WorkTrigger workTriggers : lstWorkTriggers) {
			parameters.add(new BeanPropertySqlParameterSource(workTriggers));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstWorkTriggers.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstWorkTriggers
	 *            List<WorkTriggers>
	 *
	 * @throws SQLException
	 */
	public Integer wfTriggersDeleteWorkTriggers(final List<WorkTrigger> lstWorkTriggers) {
		String sql = getQuery("OCMWORKS_WFTRIGGERS_DELETE_WORK_TRIGGERS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<>();
		for (WorkTrigger workTriggers : lstWorkTriggers) {
			parameters.add(new BeanPropertySqlParameterSource(workTriggers));
		}
		try {
			String tableName = "WORK_TRIGGERS";
			String whereCondition = "TRIGGER_NAME=:triggerName and WORK_ID=:workId";
			batchUpdatePreDeletedRows(tableName, whereCondition, parameters);
		} catch (Exception e) {
			logger.error(e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstWorkTriggers.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            WorkFunctions
	 *
	 * @return List<WorkFunctions>
	 *
	 * @throws SQLException
	 */
	public List<WorkFunction> wfFunctionsExecuteQuery(WorkFunction objSearchDao) {
		final String sql = getQuery("OCMWORKS_WFFUNCTIONS_FIND_WORK_FUNCTIONS");
		final RowMapper<WorkFunction> WorkFunctionsRowMapper = Row2BeanRowMapper.makeMapping(sql, WorkFunction.class,
				workFunctionsMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("workId", objSearchDao.getWorkId()),
				WorkFunctionsRowMapper);
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstWorkFunctions
	 *            List<WorkFunctions>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public String wfFunctionsInsertWorkFunctions(final List<WorkFunction> listObj) {
		String sql = getQuery("OCMWORKS_WFFUNCTIONS_INSERT_WORK_FUNCTIONS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<>();
		for (WorkFunction workFunctions : listObj) {
			parameters.add(new BeanPropertySqlParameterSource(workFunctions));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			return e.getMessage().toUpperCase();
		}
		if (listObj.size() == returnArray.length) {
			return "1";
		} else {
			return null;
		}
	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstWorkFunctions
	 *            List<WorkFunctions>
	 *
	 * @throws SQLException
	 */
	public Integer wfFunctionsUpdateWorkFunctions(final List<WorkFunction> lstWorkFunctions) {
		String sql = getQuery("OCMWORKS_WFFUNCTIONS_UPDATE_WORK_FUNCTIONS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<>();
		for (WorkFunction workFunctions : lstWorkFunctions) {
			parameters.add(new BeanPropertySqlParameterSource(workFunctions));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstWorkFunctions.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstWorkFunctions
	 *            List<WorkFunctions>
	 *
	 * @throws SQLException
	 */
	public Integer wfFunctionsDeleteWorkFunctions(final List<WorkFunction> lstWorkFunctions) {
		String sql = getQuery("OCMWORKS_WFFUNCTIONS_DELETE_WORK_FUNCTIONS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<>();
		for (WorkFunction workFunctions : lstWorkFunctions) {
			parameters.add(new BeanPropertySqlParameterSource(workFunctions));
		}
		try {
			String tableName = "WORK_FUNCTIONS";
			String whereCondition = "FUNCTION_TYPE=:functionType and WORK_ID=:workId";
			batchUpdatePreDeletedRows(tableName, whereCondition, parameters);
		} catch (Exception e) {
			logger.error(e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstWorkFunctions.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            InternetAddresses
	 *
	 * @return List<InternetAddresses>
	 *
	 * @throws SQLException
	 */
	public List<InternetAddresses> wfEmailRecipientsExecuteQuery(InternetAddresses objSearchDao) {
		final String sql = getQuery("OCMWORKS_WFEMAILRECIPIENTS_FIND_INTERNET_ADDRESSES");
		final RowMapper<InternetAddresses> InternetAddressesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				InternetAddresses.class, internetAddressesMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("ownerId", objSearchDao.getOwnerId()),
				InternetAddressesRowMapper);
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstInternetAddresses
	 *            List<InternetAddresses>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public String wfEmailRecipientsInsertInternetAddresses(final List<InternetAddresses> listObj) {
		String sql = getQuery("OCMWORKS_WFEMAILRECIPIENTS_INSERT_INTERNET_ADDRESSES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<>();
		for (InternetAddresses internetAddresses : listObj) {
			parameters.add(new BeanPropertySqlParameterSource(internetAddresses));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			return e.getMessage();
		}
		if (listObj.size() == returnArray.length) {
			return "1";
		} else {
			return null;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstInternetAddresses
	 *            List<InternetAddresses>
	 *
	 * @throws SQLException
	 */
	public Integer wfEmailRecipientsUpdateInternetAddresses(final List<InternetAddresses> lstInternetAddresses) {
		String sql = getQuery("OCMWORKS_WFEMAILRECIPIENTS_UPDATE_INTERNET_ADDRESSES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<>();
		for (InternetAddresses internetAddresses : lstInternetAddresses) {
			parameters.add(new BeanPropertySqlParameterSource(internetAddresses));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstInternetAddresses.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstInternetAddresses
	 *            List<InternetAddresses>
	 *
	 * @throws SQLException
	 */
	public Integer wfEmailRecipientsDeleteInternetAddresses(final List<InternetAddresses> lstInternetAddresses) {
		String sql = getQuery("OCMWORKS_WFEMAILRECIPIENTS_DELETE_INTERNET_ADDRESSES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<>();
		for (InternetAddresses internetAddresses : lstInternetAddresses) {
			parameters.add(new BeanPropertySqlParameterSource(internetAddresses));
		}
		try {
			String tableName = "INTERNET_ADDRESSES";
			String whereCondition = "INTERNET_ADDRESS_ID=:internetAddressId";
			batchUpdatePreDeletedRows(tableName, whereCondition, parameters);
		} catch (Exception e) {
			logger.error(e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstInternetAddresses.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            Works
	 *
	 * @return List<Works>
	 *
	 * @throws SQLException
	 */
	public List<Work> wfWorkEmailExecuteQuery(Work objSearchDao) {
		final String sql = getQuery("OCMWORKS_WFWORKEMAIL_FIND_WORKS");
		final RowMapper<Work> WorksRowMapper = Row2BeanRowMapper.makeMapping(sql, Work.class, worksMapping);
		return namedParameterJdbcTemplate.query(sql, createParams(), WorksRowMapper);
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstWorks
	 *            List<Works>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public Integer wfWorkEmailInsertWorks(final List<Work> lstWorks) {
		String sql = getQuery("OCMWORKS_WFWORKEMAIL_INSERT_WORKS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<>();
		for (Work works : lstWorks) {
			parameters.add(new BeanPropertySqlParameterSource(works));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstWorks.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstWorks
	 *            List<Works>
	 *
	 * @throws SQLException
	 */
	public Integer wfWorkEmailUpdateWorks(final List<Work> lstWorks) {
		String sql = getQuery("OCMWORKS_WFWORKEMAIL_UPDATE_WORKS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<>();
		for (Work works : lstWorks) {
			parameters.add(new BeanPropertySqlParameterSource(works));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstWorks.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstWorks
	 *            List<Works>
	 *
	 * @throws SQLException
	 */
	public Integer wfWorkEmailDeleteWorks(final List<Work> lstWorks) {
		String sql = getQuery("OCMWORKS_WFWORKEMAIL_DELETE_WORKS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<>();
		for (Work works : lstWorks) {
			parameters.add(new BeanPropertySqlParameterSource(works));
		}
		try {
			String tableName = "WORKS";
			String whereCondition = "WORK_ID=:workId";
			batchUpdatePreDeletedRows(tableName, whereCondition, parameters);
		} catch (Exception e) {
			logger.error(e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstWorks.size() == returnArray.length) {
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
	public List<ReferenceCodes> rgWorkflowTypeRecordGroup() {
		final String sql = getQuery("OCMWORKS_FIND_RGWORKFLOWTYPE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("Exception rgWorkflowTypeRecordGroup:", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgAgyLocTypeRecordGroup() {
		final String sql = getQuery("OCMWORKS_FIND_RGAGYLOCTYPE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("Exception rgAgyLocTypeRecordGroup:", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgWorkTypeRecordGroup() {
		final String sql = getQuery("OCMWORKS_FIND_RGWORKTYPE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("Exception rgWorkTypeRecordGroup:", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgWorkSubTypeRecordGroup(final String workType) {
		final String sql = getQuery("OCMWORKS_FIND_RGWORKSUBTYPE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams("PARENT_CODE",workType), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("Exception rgWorkSubTypeRecordGroup:", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<OmsModules> rgModulesRecordGroup() {
		final String sql = getQuery("OCMWORKS_FIND_RGMODULES");
		final RowMapper<OmsModules> mRowMapper = Row2BeanRowMapper.makeMapping(sql, OmsModules.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("Exception rgModulesRecordGroup:", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<IwpTemplates> rgTemplatesRecordGroup() {
		final String sql = getQuery("OCMWORKS_FIND_RGTEMPLATES");
		final RowMapper<IwpTemplates> mRowMapper = Row2BeanRowMapper.makeMapping(sql, IwpTemplates.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("Exception rgTemplatesRecordGroup:", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgTriggerNameRecordGroup() {
		final String sql = getQuery("OCMWORKS_FIND_RGTRIGGERNAME");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("Exception rgTriggerNameRecordGroup:", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgFunctionRecordGroup() {
		final String sql = getQuery("OCMWORKS_FIND_RGFUNCTION");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("Exception rgFunctionRecordGroup:", e);
			return Collections.emptyList();
		}
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * wfWorkTypesPostQuery
	 *
	 * @param params
	 *
	 */
	public List<OmsModules> wfWorkTypesPostQuery(OmsModules paramBean) {
		final String sql = getQuery("OCMWORKS_WF_WORK_TYPES_POSTQUERY");
		final RowMapper<OmsModules> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OmsModules.class,
				omsModulesMapping);
		return namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * wfWorkTypesOnCheckDeleteMaster
	 *
	 * @param params
	 *
	 */
	public List<WorkIwpTemplate> wfWorkTypesOnCheckDeleteMaster(WorkIwpTemplate paramBean) {
		final String sql = getQuery("OCMWORKS_WF_WORK_TYPES_ONCHECKDELETEMASTER");
		final RowMapper<WorkIwpTemplate> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, WorkIwpTemplate.class,
				workIwpTemplatesMapping);
		return namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * wfWorkTypesOnCheckDeleteMaster
	 *
	 * @param params
	 *
	 */
	public List<WorkTrigger> wfWorkTypesOnCheckDeleteMaster(WorkTrigger paramBean) {
		final String sql = getQuery("OCMWORKS_WF_WORK_TYPES_ONCHECKDELETEMASTER");
		final RowMapper<WorkTrigger> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, WorkTrigger.class,
				workTriggersMapping);
		return namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * wfWorkTypesOnCheckDeleteMaster
	 *
	 * @param params
	 *
	 */
	public List<WorkFunction> wfWorkTypesOnCheckDeleteMaster(WorkFunction paramBean) {
		final String sql = getQuery("OCMWORKS_WF_WORK_TYPES_ONCHECKDELETEMASTER");
		final RowMapper<WorkFunction> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, WorkFunction.class,
				workFunctionsMapping);
		return namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * wfWorkTypesOnCheckDeleteMaster
	 *
	 * @param params
	 *
	 */
	public List<InternetAddresses> wfWorkTypesOnCheckDeleteMasters(InternetAddresses paramBean) {
		final String sql = getQuery("OCMWORKS_WF_WORK_TYPES_ONCHECKDELETEMASTER");
		final RowMapper<InternetAddresses> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, InternetAddresses.class,
				internetAddressesMapping);
		return namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * wfWorkTypesOnCheckDeleteMaster
	 *
	 * @param params
	 *
	 */
	public List<InternetAddresses> wfWorkTypesOnCheckDeleteMaster(InternetAddresses paramBean) {
		final String sql = getQuery("OCMWORKS_WF_WORK_TYPES_ONCHECKDELETEMASTER");
		final RowMapper<InternetAddresses> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, InternetAddresses.class,
				internetAddressesMapping);
		return namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * wfEmailReturnPreInsert
	 *
	 * @param params
	 *
	 */
	public List<InternetAddresses> wfEmailReturnPreInsert(InternetAddresses paramBean) {
		final String sql = getQuery("OCMWORKS_WF_EMAIL_RETURN_PREINSERT");
		final RowMapper<InternetAddresses> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, InternetAddresses.class,
				internetAddressesMapping);
		return namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * recipients
	 *
	 * @param params
	 *
	 */
	public List<InternetAddresses> recipients(InternetAddresses paramBean) {
		final String sql = getQuery("OCMWORKS_RECIPIENTS");
		final RowMapper<InternetAddresses> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, InternetAddresses.class,
				internetAddressesMapping);
		return namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
	}

	@Override
	public List<InternetAddresses> wfEmailReturnExecuteQuery(InternetAddresses searchRecord) {
		final String sql = getQuery("OCMWORKS_WFEMAILRETURNS_FIND_INTERNET_ADDRESSES");
		final RowMapper<InternetAddresses> InternetAddressesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				InternetAddresses.class, internetAddressesMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("ownerId", searchRecord.getOwnerId()),
				InternetAddressesRowMapper);
	}

	@Override
	public Integer getCountOffenderAssociated(String workType, String workSubType) {
		Map<String, Object> returnObject = null;
		Integer count = 0;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[3];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_CASE_NOTE_TYPE", Types.VARCHAR),
				new SqlParameter("P_CASE_NOTE_SUB_TYPE", Types.VARCHAR),
				new SqlOutParameter("RETURN_VALUE", Types.INTEGER) };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("OCMWORKS").withFunctionName("GET_COUNT_OFFENDER_ASSOCIATED")
				.declareParameters(sqlParameters);
		inParamMap.put("P_CASE_NOTE_TYPE", workType);
		inParamMap.put("P_CASE_NOTE_SUB_TYPE", workSubType);
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);

		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			count = Integer.valueOf(returnObject.get("RETURN_VALUE").toString());
		} catch (Exception e) {
			logger.error("Exception in getCountOffenderAssociated: Ocmworks", e);
		}
		return count;
	}

	@Override
	public Integer getCountIwpDocuments(final WorkIwpTemplate bean) {
		Map<String, Object> returnObject = null;
		Integer count = 0;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[3];
		sqlParameters = new SqlParameter[] { new SqlParameter("p_template_id", Types.INTEGER),
				new SqlOutParameter("RETURN_VALUE", Types.INTEGER) };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("OCMWORKS").withFunctionName("GET_COUNT_IWP_DOCUMENTS")
				.declareParameters(sqlParameters);
		inParamMap.put("p_template_id", bean.getTemplateId());
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);

		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			count = Integer.valueOf(returnObject.get("RETURN_VALUE").toString());
		} catch (Exception e) {
			logger.error("Exception in getCountIwpDocuments: Ocmworks", e);
		}
		return count;
	}
	@Override
	public Integer checkdays(final WorkTrigger bean) {
		Map<String, Object> returnObject = null;
		Integer count = 0;
		final Map<String, Object> inParamMap = new HashMap<>();
		SqlParameter[] sqlParameters = new SqlParameter[3];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_TRIGGER_NAME", Types.VARCHAR),
				new SqlOutParameter("RETURN_VALUE", Types.INTEGER) };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("OCMWORKS").withFunctionName("GET_PREVIOUS_DAYS")
				.declareParameters(sqlParameters);
		inParamMap.put("P_TRIGGER_NAME", bean.getTriggerName());
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);

		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			count = Integer.valueOf(returnObject.get("RETURN_VALUE").toString());
		} catch (Exception e) {
			logger.error("Exception in checkdays: Ocmworks", e);
		}
		return count;
	}
	@Override
	public Work onCheckdeleteMaster(Work bean) {
		final String sql = getQuery("OCMWORKS_ON_CHECK_DELETE_MASTER_QUERY");
		final RowMapper<Work> worksRowMapper = Row2BeanRowMapper.makeMapping(sql,
				Work.class, worksMapping);
		List<Work> returnList = namedParameterJdbcTemplate.query(sql, createParams("work_id", bean.getWorkId()),
				worksRowMapper);
		return returnList.get(0);
	}
	
}
