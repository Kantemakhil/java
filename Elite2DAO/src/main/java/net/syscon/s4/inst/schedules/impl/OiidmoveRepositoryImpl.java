package net.syscon.s4.inst.schedules.impl;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.MovementReasons;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.inst.schedules.OiidmoveRepository;
import net.syscon.s4.inst.schedules.bean.VOffExtMovements;

@Repository
public class OiidmoveRepositoryImpl extends RepositoryBase implements OiidmoveRepository{

	private static Logger logger = LogManager.getLogger(OiidmoveRepositoryImpl.class.getName());
	
private final Map<String, FieldMapper> referenceCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("EXPIRED_DATE", 						new FieldMapper("expiredDate"))
.put("LIST_SEQ", 						new FieldMapper("listSeq"))
.put("DOMAIN", 						new FieldMapper("domain"))
.put("ACTIVE_FLAG", 						new FieldMapper("activeFlag"))
.put("MOVEMENT_REASON_CODE", 						new FieldMapper("movementReasonCode"))
.put("DESCRIPTION", 						new FieldMapper("description"))
.put("DIRECTION_CODE", 						new FieldMapper("directionCode"))
.put("MOVEMENT_TYPE", 						new FieldMapper("movementType"))
.put("'1'", 						new FieldMapper("  '1' "))
.build();
private final Map<String, FieldMapper> vHeaderBlockMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("OFFENDER_ID_DISPLAY", 						new FieldMapper("offenderIdDisplay"))
.put("ACTIVE_FLAG", 						new FieldMapper("activeFlag"))
.put("OFFENDER_ID", 						new FieldMapper("offenderId"))
.put("LAST_NAME", 						new FieldMapper("lastName"))
.put("OFFENDER_BOOK_ID", 						new FieldMapper("offenderBookId"))
.build();
private final Map<String, FieldMapper> movementReasonsMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("MOVEMENT_REASON_CODE", 						new FieldMapper("movementReasonCode"))
.put("'1'", 						new FieldMapper("  '1' "))
.build();
private final Map<String, FieldMapper> offendersMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("OFFENDER_ID_DISPLAY", 						new FieldMapper("offenderIdDisplay"))
.put("ACTIVE_FLAG", 						new FieldMapper("activeFlag"))
.put("OFFENDER_ID", 						new FieldMapper("offenderId"))
.put("LAST_NAME", 						new FieldMapper("lastName"))
.put("OFFENDER_BOOK_ID", 						new FieldMapper("offenderBookId"))
.build();
private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("EXPIRED_DATE", 						new FieldMapper("expiredDate"))
.put("CODE", 						new FieldMapper("code"))
.put("DOMAIN", 						new FieldMapper("domain"))
.put("ACTIVE_FLAG", 						new FieldMapper("activeFlag"))
.put("DESCRIPTION", 						new FieldMapper("description"))
.build();
private final Map<String, FieldMapper> vOffExtMovementsMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("OFFENDER_ID_DISPLAY", 						new FieldMapper("offenderIdDisplay"))
.put("REPORTING_TIME", 						new FieldMapper("reportingTime"))
.put("FROM_ADDRESS_DESC", 						new FieldMapper("fromAddressDesc"))
.put("TO_AGY_LOC_ID", 						new FieldMapper("toAgyLocId"))
.put("TO_ADDRESS_ID", 						new FieldMapper("toAddressId"))
.put("ESCORT_CODE", 						new FieldMapper("escortCode"))
.put("TO_CITY_DESC", 						new FieldMapper("toCityDesc"))
.put("LAST_NAME", 						new FieldMapper("lastName"))
.put("TO_CITY", 						new FieldMapper("toCity"))
.put("TO_PROV_STAT_CODE", 						new FieldMapper("toProvStatCode"))
.put("ESCORT_TEXT", 						new FieldMapper("escortText"))
.put("MOVEMENT_TYPE", 						new FieldMapper("movementType"))
.put("FROM_AGY_LOC_DESC", 						new FieldMapper("fromAgyLocDesc"))
.put("DIRECTION_CODE", 						new FieldMapper("directionCode"))
.put("OFFENDER_BOOK_ID", 						new FieldMapper("offenderBookId"))
.put("COMMENT_TEXT", 						new FieldMapper("commentText"))
.put("REPORTING_DATE", 						new FieldMapper("reportingDate"))
.put("ACTIVE_FLAG", 						new FieldMapper("activeFlag"))
.put("FROM_AGY_LOC_ID", 						new FieldMapper("fromAgyLocId"))
.put("FROM_ADDRESS_ID", 						new FieldMapper("fromAddressId"))
.put("MOVEMENT_TIME", 						new FieldMapper("movementTime"))
.put("INTERNAL_SCHEDULE_TYPE", 						new FieldMapper("internalScheduleType"))
.put("INTERNAL_SCHEDULE_REASON_CODE", 						new FieldMapper("internalScheduleReasonCode"))
.put("FROM_CITY", 						new FieldMapper("fromCity"))
.put("MOVEMENT_SEQ", 						new FieldMapper("movementSeq"))
.put("TO_ADDRESS_DESC", 						new FieldMapper("toAddressDesc"))
.put("MOVEMENT_REASON_CODE", 						new FieldMapper("movementReasonCode"))
.put("TO_AGY_LOC_DESC", 						new FieldMapper("toAgyLocDesc"))
.put("FROM_CITY_DESC", 						new FieldMapper("fromCityDesc"))
.put("ARREST_AGENCY_LOC_ID", 						new FieldMapper("arrestAgencyLocId"))
.put("MOVEMENT_DATE", 						new FieldMapper("movementDate"))
.put("FIRST_NAME", 						new FieldMapper("firstName"))
.put("SEAL_FLAG", 						new FieldMapper("sealFlag"))
.build();

public OiidmoveRepositoryImpl() {
}

/**
* Fetch the records from database table
*
* @param objSearchDao VOffExtMovements
*
* @return List<VOffExtMovements>
*
*/
 public List<VOffExtMovements> offEmExecuteQuery(VOffExtMovements objSearchDao) {
	 final String preSql = queryBuilderFactory
				.getQueryBuilder(getQuery("OIIDMOVE_OFFEM_FIND_V_OFF_EXT_MOVEMENTS"), vOffExtMovementsMapping).build();
		final StringBuffer preparedSql = new StringBuffer(preSql);
		final MapSqlParameterSource params = new MapSqlParameterSource();
		if(preparedSql != null) {
			preparedSql.append("where (exists ( select 'X' from CASELOAD_AGENCY_LOCATIONS CAL where CAL.AGY_LOC_ID in(TO_AGY_LOC_ID, FROM_AGY_LOC_ID) and CAL.CASELOAD_ID = ( select WORKING_CASELOAD_ID from STAFF_MEMBERS where USER_ID = :userID) and CAL.AGY_LOC_ID != 'OUT') and exists ( select 'X' from OFFENDER_BOOKINGS OB where OB.OFFENDER_BOOK_ID = OFFENDER_BOOK_ID and OB.AGY_LOC_ID = CASE DIRECTION_CODE WHEN 'IN' THEN TO_AGY_LOC_ID WHEN 'OUT' THEN FROM_AGY_LOC_ID ELSE null END))  ");
			if(objSearchDao != null) {
				if(objSearchDao.getCreateUserId() != null) {
					params.addValue("userID", objSearchDao.getCreateUserId());
			}
				if(objSearchDao.getMovementDate() != null) {
					preparedSql.append("  AND  MOVEMENT_DATE =:movementDate");
					params.addValue("movementDate", new java.sql.Date(objSearchDao.getMovementDate().getTime()));
					
				}
				if(objSearchDao.getMovementTime() != null) {
					preparedSql.append("  AND To_CHAR(MOVEMENT_TIME,'HH24:MI') = :movementTime ");
					SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");  
				    String strTime= formatter.format(objSearchDao.getMovementTime());
					params.addValue("movementTime", strTime);
				}
				if(objSearchDao.getMovementType() != null && !objSearchDao.getMovementType().trim().equals("")) {
					preparedSql.append(" AND MOVEMENT_TYPE = :movementType ");
					params.addValue("movementType", objSearchDao.getMovementType());
				}
				if(objSearchDao.getMovementReasonCode() != null  && !objSearchDao.getMovementReasonCode().trim().equals("")) {
					preparedSql.append(" AND MOVEMENT_REASON_CODE = :movementReasonCode ");
					params.addValue("movementReasonCode", objSearchDao.getMovementReasonCode());
				}
				if(objSearchDao.getOffenderIdDisplay() != null && !objSearchDao.getOffenderIdDisplay().trim().equals("")) {
					preparedSql.append(" AND OFFENDER_ID_DISPLAY LIKE :offenderIdDisplay ");
					params.addValue("offenderIdDisplay", "%"+objSearchDao.getOffenderIdDisplay());
				}
				if(objSearchDao.getLastName() != null && !objSearchDao.getLastName().trim().equals("")) {
					preparedSql.append(" AND LAST_NAME = :lastName ");
					params.addValue("lastName", objSearchDao.getLastName());
				}
				if(objSearchDao.getFirstName() != null && !objSearchDao.getFirstName().trim().equals("")) {
					preparedSql.append(" AND FIRST_NAME = :firstName ");
					params.addValue("firstName", objSearchDao.getFirstName());
				}
				if(objSearchDao.getDirectionCode() != null && !objSearchDao.getDirectionCode().trim().equals("")) {
					preparedSql.append(" AND DIRECTION_CODE = :directionCode ");
					params.addValue("directionCode", objSearchDao.getDirectionCode());
				}
				if (objSearchDao.getFromMovementDate()!= null && objSearchDao.getToMovementDate() == null) {
					preparedSql.append(" AND date_trunc('D',MOVEMENT_DATE) between :FROM_DATE AND CURRENT_TIMESTAMP ");
					params.addValue("FROM_DATE", objSearchDao.getFromMovementDate());
				}
				else if (objSearchDao.getFromMovementDate()!= null && objSearchDao.getToMovementDate() != null) {
					preparedSql.append(" AND date_trunc('D',MOVEMENT_DATE) between :FROM_DATE AND :TO_DATE ");
					params.addValue("FROM_DATE", objSearchDao.getFromMovementDate());
					params.addValue("TO_DATE", objSearchDao.getToMovementDate());
				}
			}
			preparedSql.append(" ORDER BY MOVEMENT_DATE DESC,MOVEMENT_TIME DESC ");
		}
		final String sql = preparedSql.toString();
		final RowMapper<VOffExtMovements> VOffExtMovementsRowMapper = Row2BeanRowMapper.makeMapping(sql, VOffExtMovements.class,vOffExtMovementsMapping);
		return namedParameterJdbcTemplate.query(sql, params, VOffExtMovementsRowMapper);
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
*  This method is used to insert the records in the data base tables based on
*
* @param lstVOffExtMovements List<VOffExtMovements>
*
* @return List<Integer>
*
* @throws SQLException
*/
 public Integer offEmInsertVOffExtMovements(List<VOffExtMovements> lstVOffExtMovements) {
	String sql = getQuery("OIIDMOVE_OFFEM_INSERT_V_OFF_EXT_MOVEMENTS");
	int[] returnArray = new int[] {};
	List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
	returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
	
	if (lstVOffExtMovements.size() == returnArray.length) {
		return 1;
	} else {
		return 0;
	}

}
/**
* Used to capture results from select query
* @return List<M> 
*/
public List<ReferenceCodes> cgfkOffEmMovementReasonCoRecordGroup() {
 	final String sql = getQuery("OIIDMOVE_FIND_CGFKOFFEMMOVEMENTREASONCO");
 	final RowMapper<ReferenceCodes>mRowMapper = Row2BeanRowMapper.makeMapping(sql,ReferenceCodes.class, mMapping);
 	List<ReferenceCodes> resultList = null;
	try {
		resultList = namedParameterJdbcTemplate.query(sql,createParams(),mRowMapper);
  	} catch (Exception e) {
  		logger.error("cgfkOffEmMovementReasonCoRecordGroup", e);
	}
	return resultList;
}
/**
* Used to capture results from select query
* @return List<M> 
*/
public List<ReferenceCodes> cgfkOffEmMovementTypeRecordGroup() {
 	final String sql = getQuery("OIIDMOVE_FIND_CGFKOFFEMMOVEMENTTYPE");
 	final RowMapper<ReferenceCodes>mRowMapper = Row2BeanRowMapper.makeMapping(sql,ReferenceCodes.class, mMapping);
 	List<ReferenceCodes> resultList = null;
	try {
		resultList = namedParameterJdbcTemplate.query(sql,createParams(),mRowMapper);
  	} catch (Exception e) {
  		logger.error("cgfkOffEmMovementTypeRecordGroup",e);
	}
	return resultList; 
}
	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOffEmOffEmRefMov
	 *
	 * @param params
	 *
	 */
	public ReferenceCodes cgfkchkOffEmOffEmRefMov(ReferenceCodes paramBean) {
		final String sql = getQuery("OIIDMOVE_CGFKCHK_OFF_EM_OFF_EM_REF_MOV");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,ReferenceCodes.class,  referenceCodesMapping);
		ReferenceCodes returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("", paramBean), columnRowMapper);
		return returnObj;
	}
	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOffEmOffEmMoveRs
	 *
	 * @param params
	 *
	 */
	public MovementReasons cgfkchkOffEmOffEmMoveRs(MovementReasons paramBean) {
		final String sql = getQuery("OIIDMOVE_CGFKCHK_OFF_EM_OFF_EM_MOVE_RS");
		final RowMapper<MovementReasons> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,MovementReasons.class,  movementReasonsMapping);
		MovementReasons returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("", paramBean), columnRowMapper);
		return returnObj;
	}
	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOffEmOffEmDirecti
	 *
	 * @param params
	 *
	 */
	public ReferenceCodes cgfkchkOffEmOffEmDirecti(ReferenceCodes paramBean) {
		final String sql = getQuery("OIIDMOVE_CGFKCHK_OFF_EM_OFF_EM_DIRECTI");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,ReferenceCodes.class,  referenceCodesMapping);
		ReferenceCodes returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("ReferenceCodes", paramBean), columnRowMapper);
		return returnObj;
	}
	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOffEmOffEmMoveRe
	 *
	 * @param params
	 *
	 */
	public ReferenceCodes cgfkchkOffEmOffEmMoveRe(ReferenceCodes paramBean) {
		final String sql = getQuery("OIIDMOVE_CGFKCHK_OFF_EM_OFF_EM_MOVE_RE");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,ReferenceCodes.class,  referenceCodesMapping);
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("ReferenceCodes", paramBean), columnRowMapper);
	}
	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOffEmOffEmVHeade
	 *
	 * @param params
	 *
	 */
	public List<Object> cgfkchkOffEmOffEmVHeade(Offenders paramBean) {
		final String sql = getQuery("OIIDMOVE_CGFKCHK_OFF_EM_OFF_EM_V_HEADE");
		final RowMapper<Offenders> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,Offenders.class,  offendersMapping);
		namedParameterJdbcTemplate.queryForObject(sql, createParams("Offenders", paramBean), columnRowMapper);
		return null;
	}
	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfklkpOffEmOffEmVHeade
	 *
	 * @param params
	 *
	 */
	public List<VHeaderBlock> cgfklkpOffEmOffEmVHeade(VHeaderBlock paramBean) {
		final String sql = getQuery("OIIDMOVE_CGFKLKP_OFF_EM_OFF_EM_V_HEADE");
		final RowMapper<VHeaderBlock> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,VHeaderBlock.class,  vHeaderBlockMapping);
		return  namedParameterJdbcTemplate.query(sql, createParams("VHeaderBlock" , paramBean), columnRowMapper);
	}
	

}
