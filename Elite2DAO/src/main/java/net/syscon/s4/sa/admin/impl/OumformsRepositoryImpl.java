package net.syscon.s4.sa.admin.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.FormAccessibleForms;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.sa.admin.OumformsRepository;
import net.syscon.s4.sa.admin.beans.AccessibleFormTables;
import net.syscon.s4.sa.admin.beans.AllTabColumns;

/**
 * Class OumformsRepositoryImpl
 * 
 */
@Repository
public class OumformsRepositoryImpl extends RepositoryBase implements OumformsRepository {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OumformsRepositoryImpl.class);

	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("MODULE_NAME", new FieldMapper("moduleName"))
			.put("DISTINCT", new FieldMapper("distinct"))
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("CODE", new FieldMapper("code")).build();
	private final Map<String, FieldMapper> omsModulesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("MODULE_NAME", new FieldMapper("moduleName"))
			.build();
	private final Map<String, FieldMapper> formAccessibleFormsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.build();
	private final Map<String, FieldMapper> accessibleFormTablesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.build();

	/**
	 * Creates new OumformsRepositoryImpl class Object
	 * 
	 */
	public OumformsRepositoryImpl() {
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            OmsModules
	 *
	 * @return List<OmsModules>
	 *
	 */
	public List<OmsModules> omsModExecuteQuery(final OmsModules objSearchDao) {
		final String sql = getQuery("OUMFORMS_OMSMOD_FIND_OMS_MODULES");
		final RowMapper<OmsModules> omsModulesRowMapper = Row2BeanRowMapper.makeMapping(sql, OmsModules.class,
				omsModulesMapping);
		List<OmsModules> returnList = new ArrayList<>();

		final StringBuffer sqlQuery = new StringBuffer();
		String preSqlQuery = null;
		final MapSqlParameterSource params = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objSearchDao != null && objSearchDao.getModuleName() != null) {
			sqlQuery.append("WHERE MODULE_NAME  = :moduleName ");
			params.addValue("moduleName", objSearchDao.getModuleName());
		}

		preSqlQuery = sqlQuery.toString().trim();
		try {
			returnList = namedParameterJdbcTemplate.query(preSqlQuery, params, omsModulesRowMapper);
		} catch (final EmptyResultDataAccessException e) {
			logger.error(e.getLocalizedMessage());
		}
		return returnList;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            FormAccessibleForms
	 *
	 * @return List<FormAccessibleForms>
	 *
	 * @throws SQLException
	 */
	public List<FormAccessibleForms> fafExecuteQuery(final FormAccessibleForms objSearchDao) {
		final String sql = getQuery("OUMFORMS_FAF_FIND_FORM_ACCESSIBLE_FORMS");
		final RowMapper<FormAccessibleForms> formAccessibleFormsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				FormAccessibleForms.class, formAccessibleFormsMapping);
		List<FormAccessibleForms> returnList = new ArrayList<>();
		final StringBuffer sqlQuery = new StringBuffer();
		String preSqlQuery = null;
		final MapSqlParameterSource params = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objSearchDao != null && objSearchDao.getOriginatingForm() != null) {
			sqlQuery.append(" WHERE ORIGINATING_FORM  = :originatingForm ");
			params.addValue("originatingForm", objSearchDao.getOriginatingForm());
		}

		preSqlQuery = sqlQuery.toString().trim();
		preSqlQuery = preSqlQuery.concat(" ORDER BY LIST_SEQ ");
		try {
			returnList = namedParameterJdbcTemplate.query(preSqlQuery, params, formAccessibleFormsRowMapper);
		} catch (final EmptyResultDataAccessException e) {
			logger.error(e.getLocalizedMessage());
		}
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstFormAccessibleForms
	 *            List<FormAccessibleForms>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public Integer fafInsertFormAccessibleForms(final List<FormAccessibleForms> lstFormAccessibleForms) {
		final String sql = getQuery("OUMFORMS_FAF_INSERT_FORM_ACCESSIBLE_FORMS");
		int[] returnArray = null;
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final FormAccessibleForms formAccessibleForms : lstFormAccessibleForms) {
			parameters.add(new BeanPropertySqlParameterSource(formAccessibleForms));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (final DataAccessException e) {
			logger.error("fafInsertFormAccessibleForms: ", e);
		}
		if (returnArray != null && lstFormAccessibleForms.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstFormAccessibleForms
	 *            List<FormAccessibleForms>
	 *
	 * @throws SQLException
	 */
	public Integer fafUpdateFormAccessibleForms(final List<FormAccessibleForms> lstFormAccessibleForms) {
		final String sql = getQuery("OUMFORMS_FAF_UPDATE_FORM_ACCESSIBLE_FORMS");
		int[] returnArray = null;
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final FormAccessibleForms formAccessibleForms : lstFormAccessibleForms) {
			parameters.add(new BeanPropertySqlParameterSource(formAccessibleForms));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (final DataAccessException e) {
			logger.error("fafUpdateFormAccessibleForms: ", e);
		}
		if (returnArray != null && lstFormAccessibleForms.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstFormAccessibleForms
	 *            List<FormAccessibleForms>
	 *
	 * @throws SQLException
	 */
	public Integer fafDeleteFormAccessibleForms(final List<FormAccessibleForms> lstFormAccessibleForms) {
		final String sql = getQuery("OUMFORMS_FAF_DELETE_FORM_ACCESSIBLE_FORMS");
		int[] returnArray = null;
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final FormAccessibleForms formAccessibleForms : lstFormAccessibleForms) {
			parameters.add(new BeanPropertySqlParameterSource(formAccessibleForms));
		}
		try {
			String tableName = "FORM_ACCESSIBLE_FORMS";
			String whereClause = "ORIGINATING_FORM = :originatingForm and DESTINATION_FORM = :destinationForm";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method fafDeleteFormAccessibleForms", e);
		}
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (returnArray != null && lstFormAccessibleForms.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            AccessibleFormTables
	 *
	 * @return List<AccessibleFormTables>
	 *
	 * @throws SQLException
	 */
	public List<AccessibleFormTables> accessTabExecuteQuery(final AccessibleFormTables objSearchDao) {
		final String sql = getQuery("OUMFORMS_ACCESSTAB_FIND_ACCESSIBLE_FORM_TABLES");
		final RowMapper<AccessibleFormTables> accessibleFormTablesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				AccessibleFormTables.class, accessibleFormTablesMapping);
		List<AccessibleFormTables> returnList = new ArrayList<>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("originatingForm",
					objSearchDao.getOriginatingForm(), "destinationForm", objSearchDao.getDestinationForm()),
					accessibleFormTablesRowMapper);
		} catch (Exception e) {
			logger.error("Exception : OUMFORMS", e);
		}
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstAccessibleFormTables
	 *            List<AccessibleFormTables>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public AccessibleFormTables accessTabInsertAccessibleFormTables(
			final List<AccessibleFormTables> lstAccessibleFormTables) {
		final String sql = getQuery("OUMFORMS_ACCESSTAB_INSERT_ACCESSIBLE_FORM_TABLES");
		int[] returnArray = null;
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		AccessibleFormTables returnData = new AccessibleFormTables();
		for (final AccessibleFormTables accessibleFormTable : lstAccessibleFormTables) {
			parameters.add(new BeanPropertySqlParameterSource(accessibleFormTable));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (final DataAccessException e) {
			String error = "Error : " + e.getMessage();
			if (error.contains("constraint")) {
				error = error.substring(error.indexOf("constraint"), error.indexOf("Detail"))
						.replaceFirst("constraint", "").trim();
				final String tableName = errorNameValidation( error.substring(1, error.length()-1));
				returnData.setSealFlag(tableName);
				returnData.setListSeq(BigDecimal.valueOf(2291));
				return returnData;
			}
		}
		if (lstAccessibleFormTables.size() == returnArray.length) {
			returnData.setSealFlag("1");
			return returnData;
		} else {
			returnData.setSealFlag("0");
			return returnData;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstAccessibleFormTables
	 *            List<AccessibleFormTables>
	 *
	 * @throws SQLException
	 */
	public AccessibleFormTables accessTabDeleteAccessibleFormTablesTwo(
			final List<AccessibleFormTables> lstAccessibleFormTables) {
		final String sql = getQuery("OUMFORMS_ACCESSTAB_DELETE_ACCESSIBLE_FORM_TABLES_TWO");
		int[] returnArray = null;
		final List<SqlParameterSource> parameters = new ArrayList<>();
		AccessibleFormTables returnData = new AccessibleFormTables();
		for (final AccessibleFormTables accessibleFormTables : lstAccessibleFormTables) {
			parameters.add(new BeanPropertySqlParameterSource(accessibleFormTables));
		}
		try {
			String tableName = "ACCESSIBLE_FORM_TABLES";
			String whereClause = "ORIGINATING_FORM = :originatingForm and DESTINATION_FORM = :destinationForm and TABLE_NAME=:tableName";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method accessTabDeleteAccessibleFormTablesTwo", e);
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (final DataAccessException e) {
			String error = "Error : " + e.getMessage();
			if (error.contains("constraint")) {
				error =  error.substring(error.indexOf("constraint"), error.indexOf("\" on"))
						.replaceFirst("constraint", "").trim();
					final String tableName = errorNameValidation( error.substring(1, error.length()));

				returnData.setSealFlag(tableName);
				returnData.setListSeq(BigDecimal.valueOf(2292));
				return returnData;
			}
		}
		if (lstAccessibleFormTables.size() == returnArray.length) {
			returnData.setSealFlag("1");
			return returnData;
		} else {
			returnData.setSealFlag("0");
			return returnData;
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgModuleNameRecordGroup() {
		final String sql = getQuery("OUMFORMS_FIND_RGMODULENAME");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		List<ReferenceCodes> returnList = new ArrayList<>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (final EmptyResultDataAccessException e) {
			logger.error("", e);
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<AllTabColumns> rgTableNameRecordGroup() {
		final String sql = getQuery("OUMFORMS_FIND_RGTABLENAME");
		final RowMapper<AllTabColumns> mRowMapper = Row2BeanRowMapper.makeMapping(sql, AllTabColumns.class, mMapping);
		List<AllTabColumns> returnList = new ArrayList<>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (final EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<OmsModules> rgDestinationFormRecordGroup() {
		final String sql = getQuery("OUMFORMS_FIND_RGDESTINATIONFORM");
		final RowMapper<OmsModules> mRowMapper = Row2BeanRowMapper.makeMapping(sql, OmsModules.class, mMapping);
		List<OmsModules> returnList = new ArrayList<>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (final EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
		return returnList;
	}

	public String errorNameValidation(final String errorName) {
		final String sql = getQuery("OUMFORMS_CONSTRAINT_VALIDATIONS");
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

	public AccessibleFormTables accessTabDeleteAccessibleFormTables(
			final List<AccessibleFormTables> lstAccessibleFormTables) {
		final String sql = getQuery("OUMFORMS_ACCESSTAB_DELETE_ACCESSIBLE_FORM_TABLES");
		int[] returnArray = null;
		final List<SqlParameterSource> parameters = new ArrayList<>();
		AccessibleFormTables returnData = new AccessibleFormTables();
		for (final AccessibleFormTables accessibleFormTables : lstAccessibleFormTables) {
			parameters.add(new BeanPropertySqlParameterSource(accessibleFormTables));
		}
		try {
			String tableName = "ACCESSIBLE_FORM_TABLES";
			String whereClause = "ORIGINATING_FORM = :originatingForm and DESTINATION_FORM = :destinationForm";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method accessTabDeleteAccessibleFormTables", e);
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (final DataAccessException e) {
			String error = "Error : " + e.getMessage();
			if (error.contains("constraint")) {
				error =  error.substring(error.indexOf("constraint"), error.indexOf("\" on"))
						.replaceFirst("constraint", "").trim();
					final String tableName = errorNameValidation( error.substring(1, error.length()));

				returnData.setSealFlag(tableName);
				returnData.setListSeq(BigDecimal.valueOf(2292));
				return returnData;
			}
		}
		if (lstAccessibleFormTables.size() == returnArray.length) {
			returnData.setSealFlag("1");
			return returnData;
		} else {
			returnData.setSealFlag("0");
			return returnData;
		}
	}

}
