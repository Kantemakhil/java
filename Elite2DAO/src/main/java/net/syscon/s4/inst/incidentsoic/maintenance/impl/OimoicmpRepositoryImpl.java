package net.syscon.s4.inst.incidentsoic.maintenance.impl;
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
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.inst.incidentsoic.maintenance.OicSanctionLimits;
import net.syscon.s4.inst.incidentsoic.maintenance.OimoicmpRepository;
/**
 * Class OimoicmpRepositoryImpl
 * @author Arkin Software Technologies 
 * @version 1.0 
 */
@Repository
public class OimoicmpRepositoryImpl extends RepositoryBase implements OimoicmpRepository{
	
	private static Logger logger = LogManager.getLogger(OimoicmpRepositoryImpl.class.getName());

/**
 * Creates new OimoicmpRepositoryImpl class Object 
 */
public OimoicmpRepositoryImpl() {
}
private final Map<String, FieldMapper> oicSanctionLimitsMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("MAX_DAYS", 						new FieldMapper("maxDays"))
.put("CREATE_USER_ID", 						new FieldMapper("createUserId"))
.put("SEAL_FLAG", 						new FieldMapper("sealFlag"))
.put("CREATE_DATETIME", 						new FieldMapper("createDatetime"))
.put("OIC_HEARING_TYPE", 						new FieldMapper("oicHearingType"))
.put("MODIFY_USER_ID", 						new FieldMapper("modifyUserId"))
.put("OIC_SANCTION_CODE", 						new FieldMapper("oicSanctionCode"))
.put("MODIFY_DATETIME", 						new FieldMapper("modifyDatetime"))
.put("MAX_MONTH", 						new FieldMapper("maxMonth"))
.put("DESCRIPTION", 						new FieldMapper("description"))
.put("COMPENSATION_AMOUNT", 						new FieldMapper("compensationAmount"))
.put("ROW_ID", 						new FieldMapper("rowId"))
.build();
private final Map<String, FieldMapper> systemProfilesMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("PROFILE_CODE", 						new FieldMapper("profileCode"))
.put("PROFILE_TYPE", 						new FieldMapper("profileType"))
.put("CREATE_USER_ID", 						new FieldMapper("createUserId"))
.put("SEAL_FLAG", 						new FieldMapper("sealFlag"))
.put("CREATE_DATETIME", 						new FieldMapper("createDatetime"))
.put("MODIFY_USER_ID", 						new FieldMapper("modifyUserId"))
.put("OLD_TABLE_NAME", 						new FieldMapper("oldTableName"))
.put("PROFILE_VALUE", 						new FieldMapper("profileValue"))
.put("MODIFY_DATETIME", 						new FieldMapper("modifyDatetime"))
.put("PROFILE_VALUE_2", 						new FieldMapper("profileValue2"))
.put("DESCRIPTION", 						new FieldMapper("description"))
.build();
private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("CODE", 						new FieldMapper("code"))
.put("DESCRIPTION", 						new FieldMapper("description"))
.build();

/**
* Fetch the records from database table
*
* @param objSearchDao OicSanctionLimits
*
* @return List<OicSanctionLimits>
*
* @throws SQLException
*/
 public List<OicSanctionLimits> oicSlExecuteQuery(OicSanctionLimits objSearchDao)  {
		final String sql = getQuery("OIMOICMP_OICSL_FIND_OIC_SANCTION_LIMITS");
		final RowMapper<OicSanctionLimits> OicSanctionLimitsRowMapper = Row2BeanRowMapper.makeMapping(sql, OicSanctionLimits.class,oicSanctionLimitsMapping);
		final ArrayList<OicSanctionLimits> returnList = (ArrayList<OicSanctionLimits>)namedParameterJdbcTemplate.query(sql, createParams(), OicSanctionLimitsRowMapper);
		return returnList;
} 


/**
*  This method is used to insert the records in the data base tables based on
*
* @param lstOicSanctionLimits List<OicSanctionLimits>
*
* @return List<Integer>
*
* @throws SQLException
*/
 public Integer oicSlInsertOicSanctionLimits(final List<OicSanctionLimits> lstOicSanctionLimits)  {
	String sql = getQuery("OIMOICMP_OICSL_INSERT_OIC_SANCTION_LIMITS");
	int[] returnArray = new int[] {};
	List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
	for (OicSanctionLimits oicSanctionLimits : lstOicSanctionLimits) {
		parameters.add(new BeanPropertySqlParameterSource(oicSanctionLimits));
	}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
	
	if (lstOicSanctionLimits.size() == returnArray.length) {
		return 1;
	} else {
		return 0;
	}

}

/**
* This method is used to update the data base tables based on
*
* @param lstOicSanctionLimits List<OicSanctionLimits>
*
* @throws SQLException
*/
 public Integer oicSlUpdateOicSanctionLimits(final List<OicSanctionLimits> lstOicSanctionLimits)  {
 	String sql = getQuery("OIMOICMP_OICSL_UPDATE_OIC_SANCTION_LIMITS");
	int[] returnArray = new int[] {};
	List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
	for (OicSanctionLimits oicSanctionLimits : lstOicSanctionLimits) {
		parameters.add(new BeanPropertySqlParameterSource(oicSanctionLimits));
	}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
	if (lstOicSanctionLimits.size() == returnArray.length) {
		return 1;
	} else {
		return 0;
	}

}

/**
* This method is used to delete records from  data base tables based on
*
* @param lstOicSanctionLimits List<OicSanctionLimits>
*
* @throws SQLException
*/
 public Integer oicSlDeleteOicSanctionLimits(final List<OicSanctionLimits> lstOicSanctionLimits)  {
 	String sql = getQuery("OIMOICMP_OICSL_DELETE_OIC_SANCTION_LIMITS");
	int[] returnArray = new int[] {};
	List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
	for (OicSanctionLimits oicSanctionLimits : lstOicSanctionLimits) {
		 parameters.add(new BeanPropertySqlParameterSource(oicSanctionLimits));
	}
	try {
		String tableName = "OIC_SANCTION_LIMITS";
		String whereClause = "ROW_ID = (:rowId::bigint)";
		batchUpdatePreDeletedRows(tableName, whereClause , parameters);
	} catch (Exception e) {
		logger.error("Exception occured in " + this.getClass().getName() + " in method oicSlDeleteOicSanctionLimits", e);
	}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
	
	if (lstOicSanctionLimits.size() == returnArray.length) {
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
* @throws SQLException
*/
 public List<SystemProfiles> sysPflExecuteQuery(SystemProfiles objSearchDao)  {
		final String sql = getQuery("OIMOICMP_SYSPFL_FIND_SYSTEM_PROFILES");
		final RowMapper<SystemProfiles> SystemProfilesRowMapper = Row2BeanRowMapper.makeMapping(sql, SystemProfiles.class,systemProfilesMapping);
		final ArrayList<SystemProfiles> returnList = (ArrayList<SystemProfiles>)namedParameterJdbcTemplate.query(sql, createParams(), SystemProfilesRowMapper);
		return returnList;
} 
/**
* @param 
*
* @throws SQLException 
*
*/
public int PRE_INSERT()  {
return 0;
}
/**
* Used to capture results from select query
* @return List<M> 
*/
public List<ReferenceCodes> cgfkOicSlOicSanctionCodeRecordGroup()  {
 	final String sql = getQuery("OIMOICMP_FIND_CGFKOICSLOICSANCTIONCODE");
 	final RowMapper<ReferenceCodes>mRowMapper = Row2BeanRowMapper.makeMapping(sql,ReferenceCodes.class, mMapping);
 
	try {
  		return namedParameterJdbcTemplate.query(sql,createParams(),mRowMapper);
  	} catch (EmptyResultDataAccessException e) {
  		return Collections.emptyList();  
	}
}
/**
* Used to capture results from select query
* @return List<M> 
*/
public List<ReferenceCodes> cgfkOicSlOicHearingTypeRecordGroup()  {
 	final String sql = getQuery("OIMOICMP_FIND_CGFKOICSLOICHEARINGTYPE");
 	final RowMapper<ReferenceCodes>mRowMapper = Row2BeanRowMapper.makeMapping(sql,ReferenceCodes.class, mMapping);
 
	try {
  		return namedParameterJdbcTemplate.query(sql,createParams(),mRowMapper);
  	} catch (EmptyResultDataAccessException e) {
  		return Collections.emptyList();  
	}
}




}
