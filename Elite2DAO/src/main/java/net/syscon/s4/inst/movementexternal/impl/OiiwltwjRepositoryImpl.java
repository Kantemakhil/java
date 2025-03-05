package net.syscon.s4.inst.movementexternal.impl;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.OffenderIndSchedule;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.inst.movementexternal.OiiwltwjRepository;
import net.syscon.s4.inst.movementexternal.beans.VTransferWaitingLists2;
import net.syscon.s4.inst.schedules.bean.OffenderIndSchWaitLists;
import oracle.jdbc.OracleTypes;
/**
 * Class OiiwltwjRepositoryImpl
 * @author Arkin Software Technologies 
 * @version 1.0 
 */
@Repository
public class OiiwltwjRepositoryImpl extends RepositoryBase implements OiiwltwjRepository{

/**
 * Creates new OiiwltwjRepositoryImpl class Object 
 */
public OiiwltwjRepositoryImpl() {
}
private final Map<String, FieldMapper> referenceCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("WAIT_LIST_STATUS", 						new FieldMapper("code"))
.put("PARENT_CODE", 						new FieldMapper(" parentCode "))
.put("DSP_DESCRIPTION", 						new FieldMapper("description"))
.build();
private final Map<String, FieldMapper> vHeaderBlockMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("AGY_LOC_ID", 						new FieldMapper("agyLocId"))
.put("ROOT_OFFENDER_ID", 						new FieldMapper("rootOffenderId"))
.put("FIRST_NAME", 						new FieldMapper("firstName"))
.put("OFFENDER_BOOK_ID", 						new FieldMapper("offenderBookId"))
.build();
private final Map<String, FieldMapper> vTransferWaitingLists2Mapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("OFFENDER_BOOK_ID", 						new FieldMapper("offenderBookId"))
.put("OFFENDER_ID_DISPLAY", 						new FieldMapper("offenderIdDisplay"))
.put("TRANSFER_PRIORITY", 						new FieldMapper("transferPriority"))
.put("ACTIVE_FLAG", 						new FieldMapper("activeFlag"))
.put("AGENCY_LOCATION_TO", 						new FieldMapper("agencyLocationTo"))
.put("EVENT_ID", 						new FieldMapper("eventId"))
.put("CHECK_SUM", 						new FieldMapper("checkSum"))
.put("LAST_NAME", 						new FieldMapper("lastName"))
.put("WAIT_LIST_STATUS", 						new FieldMapper("waitListStatus"))
.put("APPROVED_FLAG", 						new FieldMapper("approvedFlag"))
.put("OUTCOME_REASON_CODE", 						new FieldMapper("outcomeReasonCode"))
.put("REQUESTED_DATE", 						new FieldMapper("requestedDate"))
.build();
private final Map<String, FieldMapper> agencyLocationsMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("AGY_LOC_ID", 						new FieldMapper("agyLocId"))
.put("DESCRIPTION", 					new FieldMapper("description"))
.put("AGENCY_LOCATION_TO", 				new FieldMapper("agencyLocationTo"))
.put("CODE", 							new FieldMapper("code"))
.put("ACTIVE_FLAG", 					new FieldMapper("activeFlag"))
.build();

/**
* Fetch the records from database table
*
* @param objSearchDao VTransferWaitingLists2
*
* @return List<VTransferWaitingLists2>
*
* @throws SQLException
*/
 public List<VTransferWaitingLists2> vTwlExecuteQuery(String caseLoad,String userName) {
		final String sql = getQuery("OIIWLTWJ_VTWL_FIND_V_TRANSFER_WAITING_LISTS_2");
		
		final RowMapper<VTransferWaitingLists2> VTransferWaitingLists2RowMapper = Row2BeanRowMapper.makeMapping(sql, VTransferWaitingLists2.class,vTransferWaitingLists2Mapping);
		final ArrayList<VTransferWaitingLists2> returnList = (ArrayList<VTransferWaitingLists2>)namedParameterJdbcTemplate.query(sql, createParams("CASELOADID",caseLoad,"USERID",userName), VTransferWaitingLists2RowMapper);
		return returnList;
} 
/**
* @param 
*
* @throws SQLException 
*
*/
public int PRE_INSERT() {
return 0;
}

/**
* This method is used to update the data base tables based on
*
* @param lstVTransferWaitingLists2 List<VTransferWaitingLists2>
*
* @throws SQLException
*/
 public Integer vTwlUpdateVTransferWaitingLists2(final List<VTransferWaitingLists2> lstVTransferWaitingLists2) {
 	int insertCount=0;
	String sql = getQuery("OIIWLTWJ_VTWL_UPDATE_V_TRANSFER_WAITING_LISTS_2");
	List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
	for (VTransferWaitingLists2 vTransferWaitingLists2 : lstVTransferWaitingLists2) {
		parameters.add(new BeanPropertySqlParameterSource(vTransferWaitingLists2));
	}
		 namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
	if (lstVTransferWaitingLists2.size() == insertCount) {
		return 1;
	} else {
		return 0;
	}

}
/**
* Used to capture results from select query
* @return List<ReferenceCodes> 
*/
public List<ReferenceCodes> cgfkVTwlDspDescriptionRecordGroup() {
 	final String sql = getQuery("OIIWLTWJ_FIND_CGFKVTWLDSPDESCRIPTION");
 	final RowMapper<ReferenceCodes>referenceCodesRowMapper = Row2BeanRowMapper.makeMapping(sql,ReferenceCodes.class, referenceCodesMapping);
 
	try {
  		return namedParameterJdbcTemplate.query(sql,createParams(),referenceCodesRowMapper);
  	} catch (EmptyResultDataAccessException e) {
  		return Collections.emptyList();  
	}
}
/**
* Used to capture results from select query
* @return List<AgencyLocations> 
*/
public List<AgencyLocations> cgfkVTwlAgencyLocationToRecordGroup() {
 	final String sql = getQuery("OIIWLTWJ_FIND_CGFKVTWLAGENCYLOCATIONTO");
 	final RowMapper<AgencyLocations>agencyLocationsRowMapper = Row2BeanRowMapper.makeMapping(sql,AgencyLocations.class, agencyLocationsMapping);
 
	try {
  		return namedParameterJdbcTemplate.query(sql,createParams(),agencyLocationsRowMapper);
  	} catch (EmptyResultDataAccessException e) {
  		return Collections.emptyList();  
	}
}
/**
* Used to capture results from select query
* @return List<ReferenceCodes> 
*/
public List<ReferenceCodes> cgfkVTwlDspDescription3RecordGroup() {
 	final String sql = getQuery("OIIWLTWJ_FIND_CGFKVTWLDSPDESCRIPTION3");
 	final RowMapper<ReferenceCodes>referenceCodesRowMapper = Row2BeanRowMapper.makeMapping(sql,ReferenceCodes.class, referenceCodesMapping);
 
	try {
  		return namedParameterJdbcTemplate.query(sql,createParams(),referenceCodesRowMapper);
  	} catch (EmptyResultDataAccessException e) {
  		return Collections.emptyList();  
	}
}
/**
* Used to capture results from select query
* @return List<ReferenceCodes> 
*/
public List<ReferenceCodes> rgCancelReasonRecordGroup() {
 	final String sql = getQuery("OIIWLTWJ_FIND_RGCANCELREASON");
 	final RowMapper<ReferenceCodes>referenceCodesRowMapper = Row2BeanRowMapper.makeMapping(sql,ReferenceCodes.class, referenceCodesMapping);
 
	try {
  		return namedParameterJdbcTemplate.query(sql,createParams(),referenceCodesRowMapper);
  	} catch (EmptyResultDataAccessException e) {
  		return Collections.emptyList();  
	}
}
	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkVTwlVTwlAgyLoc
	 *
	 * @param params
	 *
	 */
	public AgencyLocations cgfkchkVTwlVTwlAgyLoc(AgencyLocations paramBean) {
		final String sql = getQuery("OIIWLTWJ_CGFKCHK_V_TWL_V_TWL_AGY_LOC_F");
		final RowMapper<AgencyLocations> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,AgencyLocations.class,  agencyLocationsMapping);
		AgencyLocations returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("AGENCYLOCATIONTO", paramBean.getAgyLocId()), columnRowMapper);
		return returnObj;
	}
	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkVTwlVTwlVOffBkg
	 *
	 * @param params
	 *
	 */
	public VHeaderBlock cgfkchkVTwlVTwlVOffBkg(VHeaderBlock paramBean) {
		final String sql = getQuery("OIIWLTWJ_CGFKCHK_V_TWL_V_TWL_V_OFF_BKG");
		final RowMapper<VHeaderBlock> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,VHeaderBlock.class,  vHeaderBlockMapping);
		final VHeaderBlock returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("OFFENDERBOOKID", paramBean.getOffenderBookId(),"USERID",paramBean.getCreateUserId()), columnRowMapper);
		return returnList;
	}
	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * refreshCheckSum
	 *
	 * @param params
	 *
	 */
	public List<VTransferWaitingLists2> refreshCheckSum(VTransferWaitingLists2 paramBean) {
		final String sql = getQuery("OIIWLTWJ_REFRESH_CHECK_SUM");
		final RowMapper<VTransferWaitingLists2> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,VTransferWaitingLists2.class,  vTransferWaitingLists2Mapping);
		final ArrayList<VTransferWaitingLists2> returnList = (ArrayList<VTransferWaitingLists2>)namedParameterJdbcTemplate.query(sql, createParams("VTransferWaitingLists2", paramBean), columnRowMapper);
		return returnList;
	}
	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * getParentCode
	 *
	 * @param params
	 *
	 */
	public ReferenceCodes getParentCode(ReferenceCodes paramBean) {
		final String sql = getQuery("OIIWLTWJ_GET_PARENT_CODE");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,ReferenceCodes.class,  referenceCodesMapping);
		ReferenceCodes returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("ReferenceCodes", paramBean), columnRowMapper);
		return returnObj;
	}
	
	public Integer oiiwltwjUpdateScheduleInfoOffenderIndSchedulesSsh(final List<OffenderIndSchedule> lstOffenders) {
		final String sql = getQuery("OIIWLTWJ_UPDATE_SCHEDULE_INFO_OFFENDER_IND_SCHEDULES_SSH");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderIndSchedule offenders : lstOffenders) {
			parameters.add(new BeanPropertySqlParameterSource(offenders));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenders.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}
	
	public Integer oiiwltwjUpdateScheduleInfoOffenderIndSchedules(final List<OffenderIndSchedule> lstOffenders) {
		final String sql = getQuery("OIIWLTWJ_UPDATE_SCHEDULE_INFO_OFFENDER_IND_SCHEDULES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderIndSchedule offenders : lstOffenders) {
			parameters.add(new BeanPropertySqlParameterSource(offenders));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenders.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}
	
	public Integer oiiwltwjUpdateScheduleInfoOffenderIndSchedulesWaitList(final List<OffenderIndSchWaitLists> lstOffenders) {
		final String sql = getQuery("OIIWLTWJ_UPDATE_SCHEDULE_INFO_OFFENDER_IND_SCHEDULES_WAIT_LIST");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderIndSchWaitLists offenders : lstOffenders) {
			parameters.add(new BeanPropertySqlParameterSource(offenders));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenders.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}
	@Override
	public String tagScheduleLockEventWl(Long eventId, Long checkSum) {
		
		SqlParameter[] sqlParameters = new SqlParameter[2];
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		sqlParameters = new SqlParameter[] { new SqlParameter("P_EVENT_ID", OracleTypes.NUMBER),
				new SqlParameter("P_CHECK_SUM", OracleTypes.INTEGER)};
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_SCHEDULE").withProcedureName("LOCK_EVENT_WL")
				.declareParameters(sqlParameters);
		inParamMap.put("P_EVENT_ID", eventId);
		inParamMap.put("P_CHECK_SUM", checkSum);
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		
			 simpleJDBCCall.execute(inParameter);
		
		return "1";
	}
	


}	
	