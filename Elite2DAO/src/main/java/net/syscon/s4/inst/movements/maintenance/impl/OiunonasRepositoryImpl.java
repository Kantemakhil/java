package net.syscon.s4.inst.movements.maintenance.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.AgyIntLocProfiles;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.inst.movements.maintenance.OiunonasRepository;

/**
 * Class OiunonasRepositoryImpl
 */
@Repository
public class OiunonasRepositoryImpl extends RepositoryBase implements OiunonasRepository {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OiunonasRepositoryImpl.class.getName());
private final Map<String, FieldMapper> refCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("CODE", 						new FieldMapper("code"))
.put("DESCRIPTION", 				new FieldMapper("description"))
.build();
private final Map<String, FieldMapper> agyIntLocMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("INT_LOC_PROFILE_CODE", 						new FieldMapper("intLocProfileCode"))
.put("INT_LOC_PROFILE_TYPE", 						new FieldMapper("intLocProfileType"))
.put("ROWID", 										new FieldMapper("rowid"))
.build();

/**
 * Creates new OiunonasRepositoryImpl class Object 
 */
public OiunonasRepositoryImpl() {
}
/**
* Fetch the records from database table
*
* @param objSearchDao AgyIntLocProfiles
*
* @return List<AgyIntLocProfiles>
*
* @throws SQLException
*/
	public List<AgyIntLocProfiles> intLocProfExecuteQuery(final AgyIntLocProfiles objSearchDao) {
		final String sql = getQuery("OIUNONAS_INTLOCPROF_FIND_AGY_INT_LOC_PROFILES");
		final RowMapper<AgyIntLocProfiles> AgyIntLocMapper = Row2BeanRowMapper.makeMapping(sql,
				AgyIntLocProfiles.class, agyIntLocMapping);
		return namedParameterJdbcTemplate.query(sql,
				createParams("internalLocationId", objSearchDao.getInternalLocationId()), AgyIntLocMapper);
	} 

/**
*  This method is used to insert the records in the data base tables based on
*
* @param lstAgyIntLocProfiles List<AgyIntLocProfiles>
*
* @return List<Integer>
*
* @throws SQLException
*/
 public Integer intLocProfInsertAgyIntLocProfiles(final List<AgyIntLocProfiles> listObj)  {
	 final String sql = getQuery("OIUNONAS_INTLOCPROF_INSERT_AGY_INT_LOC_PROFILES");
	int[] returnArray = new int[] {};
	final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
	for (final AgyIntLocProfiles bean : listObj) {
		parameters.add(new BeanPropertySqlParameterSource(bean));
	}
	returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
	if (listObj.size() == returnArray.length) {
		return 1;
	} else {
		return 0;
	}

}

/**
* This method is used to update the data base tables based on
*
* @param lstAgyIntLocProfiles List<AgyIntLocProfiles>
*
* @throws SQLException
*/
 public Integer intLocProfUpdateAgyIntLocProfiles(final List<AgyIntLocProfiles> listObj)  {
	final String sql = getQuery("OIUNONAS_INTLOCPROF_UPDATE_AGY_INT_LOC_PROFILES");
	int[] returnArray = new int[] {};
	final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
	for (final AgyIntLocProfiles bean : listObj) {
		parameters.add(new BeanPropertySqlParameterSource(bean));
	}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
	if (listObj.size() == returnArray.length) {
		return 1;
	} else {
		return 0;
	}

}

/**
* This method is used to delete records from  data base tables based on
*
* @param lstAgyIntLocProfiles List<AgyIntLocProfiles>
*
* @throws SQLException
*/
 public Integer intLocProfDeleteAgyIntLocProfiles(final List<AgyIntLocProfiles> listObj)  {
	 final String sql = getQuery("OIUNONAS_INTLOCPROF_DELETE_AGY_INT_LOC_PROFILES");
	int[] returnArray = new int[] {};
	final List<SqlParameterSource> parameters = new ArrayList<>();
	for (final AgyIntLocProfiles bean : listObj) {
		 parameters.add(new BeanPropertySqlParameterSource(bean));
	}
	
	try {
		batchUpdatePreDeletedRows("AGY_INT_LOC_PROFILES", "INTERNAL_LOCATION_ID=:internalLocationId and INT_LOC_PROFILE_TYPE=:intLocProfileType and INT_LOC_PROFILE_CODE=:intLocProfileCode", parameters);
	} catch (Exception e) {
		logger.error("batchUpdatePreDeletedRows in intLocProfDeleteAgyIntLocProfiles"+e);
	}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
	if (listObj.size() == returnArray.length) {
		return 1;
	} else {
		return 0;
	}

}
/**
* Used to capture results from select query
* @return List<M> 
*/
public List<ReferenceCodes> rgNonAssoTypeRecordGroup()  {
 	final String sql = getQuery("OIUNONAS_FIND_RGNONASSOTYPE");
 	final RowMapper<ReferenceCodes>mRowMapper = Row2BeanRowMapper.makeMapping(sql,ReferenceCodes.class, refCodesMapping);
 
	try {
  		return namedParameterJdbcTemplate.query(sql,createParams(),mRowMapper);
  	} catch (EmptyResultDataAccessException e) {
  		logger.error("Exception :", e);
  		return Collections.emptyList();  
	}
}
/**
* Used to capture results from select query
* @return Integer
*/
public Integer preInsert(final List<Long> intLocId,final List<String> intLocProfileCode)  {
 	final String sql = getQuery("OIUNONAS_PRE_INSERT");
  		return namedParameterJdbcTemplate.queryForObject(sql,createParams("INTERNAL_LOCATION_ID",intLocId,
  				"INT_LOC_PROFILE_CODE",intLocProfileCode),Integer.class);
}

}
