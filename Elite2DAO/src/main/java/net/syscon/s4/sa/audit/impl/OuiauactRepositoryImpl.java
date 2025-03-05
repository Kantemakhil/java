package net.syscon.s4.sa.audit.impl;
import static java.util.Comparator.comparingLong;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

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
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.sa.audit.OuiauactRepository;
import net.syscon.s4.sa.audit.SysTagAuditFormGetUserDetail;
/**
 * Class OuiauactRepositoryImpl
 
 */
@Repository
public class OuiauactRepositoryImpl extends RepositoryBase implements OuiauactRepository{


/**
* Logger object used to print the log in the file
*/
private static Logger logger = LogManager.getLogger(OuiauactRepositoryImpl.class);
private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("INITCAP(LAST_NAME", 						new FieldMapper("initcap(lastName"))
.put("'||FIRST_NAME", 						new FieldMapper("'||firstName"))
.build();
private final Map<String, FieldMapper> userAuditMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("STAMP", new FieldMapper("stamp"))
.put("SESSION_ID", new FieldMapper("sessionId"))
.put("OS_USER", new FieldMapper("osUser"))
.put("DB_USER", new FieldMapper("dbUser"))
.put("CLIENTIP", new FieldMapper("clientip"))
.build();

/**
 * Creates new OuiauactRepositoryImpl class Object 
 */
public OuiauactRepositoryImpl() {
	//OuiauactRepositoryImpl

}
/**
* Fetch the records from database table
*
* @param objSearchDao SysTagAuditFormGetuserdetail
*
* @return List<SysTagAuditFormGetuserdetail>
*
* @throws SQLException
*/
 public List<SysTagAuditFormGetUserDetail> getUserDetailExecuteQuery(SysTagAuditFormGetUserDetail objSearchDao)  {
	 Map<String, Object> returnObject = null;
		List<SysTagAuditFormGetUserDetail> listObj=new ArrayList<SysTagAuditFormGetUserDetail>();
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[20];
		List<SysTagAuditFormGetUserDetail> returnList = new ArrayList<SysTagAuditFormGetUserDetail>();
		sqlParameters = new SqlParameter[] {new SqlInOutParameter("FORM_CURSOR", Types.REF_CURSOR),
				new SqlParameter("P_USERNAME", Types.VARCHAR),
				new SqlParameter("P_DATEFROM", Types.DATE),
				new SqlParameter("P_DATETO", Types.DATE),
				new SqlParameter("P_TIMEFROM", Types.DATE),
				new SqlParameter("P_TIMETO", Types.DATE),
				};

		final SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource)
				.withCatalogName("TAG_AUDIT").withProcedureName("FORM_GETUSERDETAIL").withoutProcedureColumnMetaDataAccess()
				.returningResultSet("FORM_CURSOR", BeanPropertyRowMapper.newInstance(SysTagAuditFormGetUserDetail.class))
				.declareParameters(sqlParameters);

		inParamMap.put("P_USERNAME", objSearchDao.getUserId());
		inParamMap.put("P_DATEFROM", objSearchDao.getFromDate());
		inParamMap.put("P_DATETO", objSearchDao.getToDate());
		inParamMap.put("P_TIMEFROM", objSearchDao.getFromTime());
		inParamMap.put("P_TIMETO", objSearchDao.getToTime());
		
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);

		try {
			returnObject = simpleJDBCCall.execute(inParameter);
		listObj = (List<SysTagAuditFormGetUserDetail>) returnObject.get("FORM_CURSOR");
			
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		
		return listObj;
 }
/**
* Used to capture results from select query
* @return List<M> 
*/
public List<StaffMembers> rgStfMemberRecordGroup()  {
 	final String sql = getQuery("OUIAUACT_FIND_RGSTFMEMBER");
 	final RowMapper<StaffMembers>mRowMapper = Row2BeanRowMapper.makeMapping(sql,StaffMembers.class, mMapping);
 
	try {
  		return namedParameterJdbcTemplate.query(sql,createParams(),mRowMapper);
  	} catch (EmptyResultDataAccessException e) {
  		logger.error("rgStfMemberRecordGroup :", e);
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
	public List<OmsModules> createFormGlobals(OmsModules paramBean)  {
		final String sql = getQuery("OUIAUACT_CREATE_FORM_GLOBALS");
		final RowMapper<OmsModules> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,OmsModules.class,  mMapping);
		final ArrayList<OmsModules> returnList = (ArrayList<OmsModules>)namedParameterJdbcTemplate.query(sql, createParams(paramBean), columnRowMapper);
		return returnList;
	}
	@Override
	public List<SysTagAuditFormGetUserDetail> getuserActivityDetails(
			SysTagAuditFormGetUserDetail objSearchDao) {
		 List<SysTagAuditFormGetUserDetail> userAuditList = new ArrayList<SysTagAuditFormGetUserDetail>();
		final String sql = getQuery("OUIADACT_FIND_DETAILS");
		final RowMapper<SysTagAuditFormGetUserDetail> auditUserRowMapper = Row2BeanRowMapper.makeMapping(sql, SysTagAuditFormGetUserDetail.class, userAuditMapping);
		userAuditList = (ArrayList<SysTagAuditFormGetUserDetail>) namedParameterJdbcTemplate.query(sql,
				createParams("P_USERNAME", objSearchDao.getUserName(), "P_DATEFROM", objSearchDao.getFromDate(),
						"P_DATETO", objSearchDao.getToDate(), "P_TIMEFROM", objSearchDao.getFromTime(),
						"P_TIMETO", objSearchDao.getToTime()), auditUserRowMapper);	
		
		List<SysTagAuditFormGetUserDetail> returnList = userAuditList.stream()
               .collect(collectingAndThen(toCollection(() -> new TreeSet<>(comparingLong(SysTagAuditFormGetUserDetail::getSessionId))),
                                          ArrayList::new));
		return returnList;
	}

}
