
package net.syscon.s4.globalconfiguration.impl;

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
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.globalconfiguration.OumsypflRepository;
import net.syscon.s4.im.beans.Dual;

/**
 * Class OumsypflRepositoryImpl
 */
@Repository
public class OumsypflRepositoryImpl extends RepositoryBase implements OumsypflRepository {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OumsypflRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> referCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("LIST_SEQ", new FieldMapper("listSeq")).put("CODE", new FieldMapper("code"))
			.put("DESCRIPTION", new FieldMapper("description")).put("PROFILE_TYPE", new FieldMapper("profileType"))
			.build();
	private final Map<String, FieldMapper> sysProfMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PROFILE_CODE", new FieldMapper("profileCode")).put("PROFILE_TYPE", new FieldMapper("profileType"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId")).put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", new FieldMapper("createDateTime"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("OLD_TABLE_NAME", new FieldMapper("oldTableName"))
			.put("PROFILE_VALUE", new FieldMapper("profileValue"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDateTime"))
			.put("PROFILE_VALUE_2", new FieldMapper("profileValue2")).put("DESCRIPTION", new FieldMapper("description"))
			.build();
	private final Map<String, FieldMapper> dualMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("USER", new FieldMapper("user")).put("SYSDATE", new FieldMapper("sysDate"))
			.put("PROFILE_VALUE", new FieldMapper("profileValue")).build();

	/**
	 * Creates new OumsypflRepositoryImpl class Object
	 */
	public OumsypflRepositoryImpl() {
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao SystemProfiles
	 *
	 * @return List<SystemProfiles>
	 *
	 */
	public List<SystemProfiles> sysPflExecuteQuery(final SystemProfiles objSearchDao) {
		final String sql = getQuery("OUMSYPFL_SYSPFL_FIND_SYSTEM_PROFILES");
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource params = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" where ");

			if (objSearchDao.getProfileType() != null && objSearchDao.getProfileType().trim().length() != 0) {
				sqlQuery.append(" PROFILE_TYPE = :PROFILE_TYPE " + " and");
				params.addValue("PROFILE_TYPE", objSearchDao.getProfileType());
			}
			if (objSearchDao.getProfileCode() != null && objSearchDao.getProfileCode().trim().length() != 0) {
				sqlQuery.append(" PROFILE_CODE = :PROFILE_CODE " + " and");
				params.addValue("PROFILE_CODE", objSearchDao.getProfileCode());
			}
			if (objSearchDao.getDescription() != null && objSearchDao.getDescription().trim().length() != 0) {
				sqlQuery.append(" DESCRIPTION = :DESCRIPTION " + " and");
				params.addValue("DESCRIPTION", objSearchDao.getDescription());
			}
			if (objSearchDao.getProfileValue() != null && objSearchDao.getProfileValue().trim().length() != 0) {
				sqlQuery.append(" PROFILE_VALUE = :PROFILE_VALUE " + " and");
				params.addValue("PROFILE_VALUE", objSearchDao.getProfileValue());
			}
			if (objSearchDao.getProfileValue2() != null && objSearchDao.getProfileValue2().trim().length() != 0) {
				sqlQuery.append(" PROFILE_VALUE_2 = :PROFILE_VALUE_2 " + " and");
				params.addValue("PROFILE_VALUE_2", objSearchDao.getProfileValue2());
			}
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("where")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("and")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		preparedSql = preparedSql.concat("  ORDER BY PROFILE_TYPE ,PROFILE_CODE ");
		final RowMapper<SystemProfiles> sysProfRowMapper = Row2BeanRowMapper.makeMapping(sql, SystemProfiles.class,
				sysProfMapping);
		final ArrayList<SystemProfiles> returnList = (ArrayList<SystemProfiles>) namedParameterJdbcTemplate
				.query(preparedSql, params, sysProfRowMapper);
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
	 * This method is used to update the data base tables based on
	 *
	 * @param lstSystemProfiles List<SystemProfiles>
	 *
	 */
	public Integer sysPflUpdateSystemProfiles(final List<SystemProfiles> lstSystemProfiles) {
		final String sql = getQuery("OUMSYPFL_SYSPFL_UPDATE_SYSTEM_PROFILES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final SystemProfiles systemProfiles : lstSystemProfiles) {
			parameters.add(new BeanPropertySqlParameterSource(systemProfiles));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstSystemProfiles.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstSystemProfiles List<SystemProfiles>
	 *
	 */
	public Integer sysPflDeleteSystemProfiles(final List<SystemProfiles> lstSystemProfiles) {
		final String sql = getQuery("OUMSYPFL_SYSPFL_DELETE_SYSTEM_PROFILES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final SystemProfiles systemProfiles : lstSystemProfiles) {
			parameters.add(new BeanPropertySqlParameterSource(systemProfiles));
		}
		try {
			String tableName = "SYSTEM_PROFILES";
			String whereClause = "PROFILE_TYPE  = :profileType and PROFILE_CODE  = :profileCode";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method sysPflDeleteSystemProfiles", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstSystemProfiles.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> cgfkSysPflProfileTypeRecordGroup() {
		final String sql = getQuery("OUMSYPFL_FIND_CGFKSYSPFLPROFILETYPE");
		final RowMapper<ReferenceCodes> referenceCodesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, referCodesMapping);
		List<ReferenceCodes> returnList = new ArrayList<>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), referenceCodesRowMapper);
		} catch (Exception e) {
			logger.error("", e);
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkSysPflSystemProfil
	 *
	 * @param params
	 *
	 */
	public ReferenceCodes cgfkchkSysPflSystemProfil(final ReferenceCodes paramBean) {
		final String sql = getQuery("OUMSYPFL_CGFKCHK_SYS_PFL_SYSTEM_PROFIL"); 
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referCodesMapping);
		final ReferenceCodes returnObj = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("PROFILETYPE", paramBean.getCode()), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgwhenNewFormInstance
	 *
	 * @param params
	 *
	 */
	public Dual cgwhenNewFormInstance(final String createUserId) {
		final String sql = getQuery("OUMSYPFL_CGWHEN_NEW_FORM_INSTANCE");
		final RowMapper<Dual> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Dual.class, dualMapping);
		final Dual returnList = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("createUserId", createUserId), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstSystemProfiles List<SystemProfiles>
	 *
	 * @return List<Integer>
	 *
	 */
	public Integer sysPflInsertSystemProfiles(final List<SystemProfiles> lstSystemProfiles) {
		final String sql = getQuery("OUMSYPFL_SYSPFL_INSERT_SYSTEM_PROFILES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final SystemProfiles systemProfiles : lstSystemProfiles) {
			parameters.add(new BeanPropertySqlParameterSource(systemProfiles));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstSystemProfiles.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao SystemProfiles
	 *
	 * @return List<SystemProfiles>
	 *
	 */
	public List<SystemProfiles> getSystemProfileRecords(final SystemProfiles objSearchDao) {
		final String sql = getQuery("OUMSYPFL_SYSPFL_FIND_UNQIUE_SYSTEM_PROFILES");
		final RowMapper<SystemProfiles> sysProfRowMapper = Row2BeanRowMapper.makeMapping(sql, SystemProfiles.class,
				sysProfMapping);
		final ArrayList<SystemProfiles> returnList = (ArrayList<SystemProfiles>) namedParameterJdbcTemplate.query(sql,
				createParams("profileType", objSearchDao.getProfileType(), "profileCode",
						objSearchDao.getProfileCode()),
				sysProfRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @return Object
	 * @param Dual paramBean
	 */
	public String whenNewFormInstance(String value) {
		final String sql = getQuery("OUMSYPFL_SYSPFL_EMAIL");
		final String returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("PROFILE_VALUE", value),
				String.class);
		return returnObj;
	}

}
