package net.syscon.s4.sa.audit.impl;
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
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlInOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.sa.audit.OuiausesRepository;
import net.syscon.s4.sa.audit.SysTagAuditFormGetsessiondetail;

@Repository
public class OuiausesRepositoryImpl extends RepositoryBase implements OuiausesRepository{
	
	private static Logger logger = LogManager.getLogger(OuiausesRepositoryImpl.class.getName());
	
	private final Map<String, FieldMapper> omsModuuleMap = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CODE", 						new FieldMapper("code"))
			.put("DESCRIPTION", 						new FieldMapper("description"))
			.put("DATES", 						new FieldMapper("date"))
			.put("TIMES", 						new FieldMapper("time"))
			.build();


/**
* Fetch the records from database table
*
* @param objSearchDao SysTagAuditFormGetsessiondetail
*
* @return List<SysTagAuditFormGetsessiondetail>
*
* @throws SQLException
*/
 public List<SysTagAuditFormGetsessiondetail> getSessionDetailExecuteQuery(final SysTagAuditFormGetsessiondetail objSearchDao)  {
	Map<String, Object> returnObject = null;
	List<SysTagAuditFormGetsessiondetail> listObj=new ArrayList<SysTagAuditFormGetsessiondetail>();
	final Map<String, Object> inParamMap = new HashMap<String, Object>();
	SqlParameter[] sqlParameters = new SqlParameter[20];
	sqlParameters = new SqlParameter[] {new SqlInOutParameter("FORM_CURSOR", Types.REF_CURSOR),
			new SqlParameter("P_SESSIONID", Types.NUMERIC),
			};

	final SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource)
			.withCatalogName("TAG_AUDIT").withProcedureName("FORM_GETSESSIONDETAIL").withoutProcedureColumnMetaDataAccess()
			.returningResultSet("FORM_CURSOR", BeanPropertyRowMapper.newInstance(SysTagAuditFormGetsessiondetail.class))
			.declareParameters(sqlParameters);

	inParamMap.put("P_SESSIONID", objSearchDao.getSessionid());
	
	final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);

	try {
		returnObject = simpleJDBCCall.execute(inParameter);
		listObj = (List<SysTagAuditFormGetsessiondetail>) returnObject.get("FORM_CURSOR");
		 
	} catch (Exception e) {
		logger.error("Exception :", e);
	}
	
	return listObj;
 }
 
 public List<SysTagAuditFormGetsessiondetail> getDateAndTime(final Long sessionId)  {
	 	final String sql = getQuery("OUIAUSES_GET_DATE_TIME");
	 	final RowMapper<SysTagAuditFormGetsessiondetail> mRowMapper = Row2BeanRowMapper.makeMapping(sql, SysTagAuditFormGetsessiondetail.class, omsModuuleMap);
	 
		try {
	  		return namedParameterJdbcTemplate.query(sql,createParams("sessionId",sessionId),mRowMapper);
	  	} catch (EmptyResultDataAccessException e) {
	  		return Collections.emptyList();  
		}
	}
 
	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * createFormGlobals
	 *
	 * @param params
	 *
	 */
	public List<OmsModules> createFormGlobals(final OmsModules paramBean)  {
		final String sql = getQuery("OUIAUSES_CREATE_FORM_GLOBALS");
		final RowMapper<OmsModules> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,OmsModules.class,  omsModuuleMap);
		final ArrayList<OmsModules> returnList = (ArrayList<OmsModules>)namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		return returnList;
	}

}
