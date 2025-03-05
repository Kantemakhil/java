package net.syscon.s4.sa.admin.impl;

import java.sql.SQLException;
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
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.sa.admin.OmunvaryRepository;
import net.syscon.s4.sa.admin.beans.NameSynonyms;

/**
 * Class OmunvaryRepositoryImpl
 */
@Repository
public class OmunvaryRepositoryImpl extends RepositoryBase implements OmunvaryRepository {
	
	private static Logger logger = LogManager.getLogger(OmunvaryRepositoryImpl.class.getName());
	/**
	 * Logger object used to print the log in the file
	 */
	private final Map<String, FieldMapper> nameSynMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("NAME", new FieldMapper("name")).put("BASE_NAME", new FieldMapper("baseName"))
			.put("SEAL_FLAG", new FieldMapper("sealFlag")).put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId")).build();

	/**
	 * Creates new OmunvaryRepositoryImpl class Object
	 */
	public OmunvaryRepositoryImpl() {
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            NameSynonyms
	 *
	 * @return List<NameSynonyms>
	 *
	 * @throws SQLException
	 */
	public List<NameSynonyms> nameSynonymsExecuteQuery(final NameSynonyms objSearchDao) {
		final String sql = getQuery("OMUNVARY_NAMESYNONYMS_FIND_NAME_SYNONYMS");
		final RowMapper<NameSynonyms> NameSynMapper = Row2BeanRowMapper.makeMapping(sql, NameSynonyms.class,
				nameSynMapping);
		String preparedSql = null;
		final MapSqlParameterSource inParameterSource = new MapSqlParameterSource();
		final StringBuffer sqlQuery = new StringBuffer();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			if (objSearchDao.getName() != null && !objSearchDao.getName().isEmpty()
					&& !("".equals(objSearchDao.getName().trim()))) {
				sqlQuery.append(" NAME = :NAME" + " AND ");
				inParameterSource.addValue("NAME", objSearchDao.getName().trim());
			}
			if (objSearchDao.getBaseName() != null && !objSearchDao.getBaseName().isEmpty()
					&& !("".equals(objSearchDao.getBaseName().trim()))) {
				sqlQuery.append(" BASE_NAME = :BASE_NAME" + " AND ");
				inParameterSource.addValue("BASE_NAME", objSearchDao.getBaseName().trim());
			}
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("WHERE")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("AND")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		return namedParameterJdbcTemplate.query(preparedSql, inParameterSource, NameSynMapper);

	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstNameSynonyms
	 *            List<NameSynonyms>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public String nameSynonymsInsertNameSynonyms(final List<NameSynonyms> listObj) {
		final String sql = getQuery("OMUNVARY_NAMESYNONYMS_INSERT_NAME_SYNONYMS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final NameSynonyms nameSynonyms : listObj) {
			parameters.add(new BeanPropertySqlParameterSource(nameSynonyms));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			String constraintName = "Error : " + e.getMessage();
			if (constraintName.contains("constraint")) {
				constraintName = constraintName
						.substring(constraintName.indexOf("constraint"), constraintName.indexOf("Detail"))
						.replaceFirst("constraint", "").trim();
				return constraintName.substring(1, constraintName.length() - 1);
			}
		}
		if (listObj.size() == returnArray.length) {
			return "1";
		} else {
			return null;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstNameSynonyms
	 *            List<NameSynonyms>
	 *
	 * @throws SQLException
	 */
	public String nameSynonymsDeleteNameSynonyms(final List<NameSynonyms> listObj) {
		final String sql = getQuery("OMUNVARY_NAMESYNONYMS_DELETE_NAME_SYNONYMS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final NameSynonyms nameSynonyms : listObj) {
			parameters.add(new BeanPropertySqlParameterSource(nameSynonyms));
		}
		try {
			String tableName = "NAME_SYNONYMS";
			String whereClause = "NAME = :name";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method nameSynonymsDeleteNameSynonyms", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (listObj.size() == returnArray.length) {
			return "1";
		} else {
			return null;
		}

	}

}
