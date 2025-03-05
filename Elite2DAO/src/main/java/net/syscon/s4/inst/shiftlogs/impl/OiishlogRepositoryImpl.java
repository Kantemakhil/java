package net.syscon.s4.inst.shiftlogs.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.AgencyInternalLocations;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.SysDual;
import net.syscon.s4.inst.careinplacement.impl.OidciponRepositoryImpl;
import net.syscon.s4.inst.shiftlogs.OiishlogRepository;
import net.syscon.s4.inst.shiftlogs.bean.AgencyShiftLogs;

/**
 * Class OiishlogRepositoryImpl
 */
@Repository
public class OiishlogRepositoryImpl extends RepositoryBase implements OiishlogRepository {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidciponRepositoryImpl.class.getName());

	private static final String MODE = "ENTER-QUERY";

	private final Map<String, FieldMapper> refCodeMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("AGY_ACTIVITY_CODE", new FieldMapper("agyActivityCode"))
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("CODE", new FieldMapper("code"))
			.put("AGY_LOC_ID", new FieldMapper("agyLocId"))
			.build();
	private final Map<String, FieldMapper> agyInLoMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("AGY_LOC_ID", new FieldMapper("agyLocId"))
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("CASELOAD_ID", new FieldMapper("caseloadId"))
			.put("DSP_AGY_LOC_ID", new FieldMapper("dspAgyLocId"))
			.put("DSP_DESCRIPTION2", new FieldMapper("dspDescription2"))
			.put("INTERNAL_LOCATION_ID", new FieldMapper("internalLocationId"))
			.put("INTERNAL_LOCATION_CODE", new FieldMapper("internalLocationCode"))
			.build();
	private final Map<String, FieldMapper> agyShfLogsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("OBSERVATION_TEXT", new FieldMapper("observationTextOne"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("STAFF_ID", new FieldMapper("staffId"))
			.put("LOCK_FLAG", new FieldMapper("lockFlag"))
			.put("INTERNAL_LOCATION_ID3", new FieldMapper("internalLocationId3"))
			.put("INTERNAL_LOCATION_ID2", new FieldMapper("internalLocationId2"))
			.put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("INTERNAL_LOCATION_ID", new FieldMapper("internalLocationId"))
			.put("LOG_DATE", new FieldMapper("logDate"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("SHIFT_LOG_SEQ", new FieldMapper("shiftLogSeq"))
			.put("AGY_ACTIVITY_CODE", new FieldMapper("agyActivityCode"))
			.put("'1'", new FieldMapper("  '1' "))
			.put("LOG_TIME", new FieldMapper("logTime"))
			.put("DSP_AGY_LOC_ID_4", new FieldMapper("dspAgyLocId4"))
			.build();
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("LAST_NAME||'", new FieldMapper("lastName||'"))
			.put("CODE", new FieldMapper("code"))
			.put("FIRST_NAME", new FieldMapper("firstName"))
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("AGY_LOC_ID", new FieldMapper("agyLocId"))
			.put("STAFF_ID", new FieldMapper("staffId"))
			.build();
	private final Map<String, FieldMapper> mmInLocUsgMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("INTERNAL_LOCATION_CODE", new FieldMapper("internalLocationCode"))
			.put("INTERNAL_LOCATION_ID", new FieldMapper("internalLocationId"))
			.build();

	/**
	 * Creates new OiishlogRepositoryImpl class Object
	 */
	public OiishlogRepositoryImpl() {
		
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            AgencyShiftLogs
	 *
	 * @return List<AgencyShiftLogs>
	 *
	 *
	 */
	public List<AgencyShiftLogs> agyShilExecuteQuery(final AgencyShiftLogs objSearchDao) {
		final String sql = getQuery("OIISHLOG_AGYSHIL_FIND_AGENCY_SHIFT_LOGS");
		final RowMapper<AgencyShiftLogs> agyShfLogsRM = Row2BeanRowMapper.makeMapping(sql,
				AgencyShiftLogs.class, agyShfLogsMapping);
		
		String preparedSql = null;
		String orderPrepareSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource valuesList = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" where EXISTS  (SELECT 1  FROM agency_internal_locations agy_intl WHERE  "
					+ " agy_intl.internal_location_id = agency_shift_logs.internal_location_id AND EXISTS (SELECT 1  FROM caseload_agency_locations ca  "
					+ "  WHERE ca.caseload_id = :globalCaseLoadId  	AND ca.agy_loc_id = agy_intl.agy_loc_id  ");
			valuesList.addValue("globalCaseLoadId", objSearchDao.getGlobalCaseLoadId());
			
			if (objSearchDao.getDspAgyLocId4() != null) {
				sqlQuery.append(" and " + "( ca.agy_loc_id = :dspAgyLocId4 )");
				valuesList.addValue("dspAgyLocId4", objSearchDao.getDspAgyLocId4());
			}
			sqlQuery.append(" ))  and  ");
		
			if (objSearchDao.getLogDate() != null) {
				sqlQuery.append(" agency_shift_logs.LOG_DATE >= :logDate  " + " and ");
				valuesList.addValue("logDate",  new Date(objSearchDao.getLogDate().getTime()));
			}
			
			if ( objSearchDao.getLogTime() != null) {
				sqlQuery.append("  agency_shift_logs.LOG_DATE  <= :logTime " + " and ");
				valuesList.addValue("logTime", objSearchDao.getLogTime());
			}
			
			if ( objSearchDao.getInternalLocationId()!= null) {
				sqlQuery.append("  agency_shift_logs.INTERNAL_LOCATION_ID =:internalLocationId  " + " and ");
				valuesList.addValue("internalLocationId", objSearchDao.getInternalLocationId());
			}
			
			if ( objSearchDao.getStaffId()!= null) {
				sqlQuery.append("  agency_shift_logs.STAFF_ID =:staffId " + " and ");
				valuesList.addValue("staffId", objSearchDao.getStaffId());
			}
			if ( objSearchDao.getAgyActivityCode()!= null) {
				sqlQuery.append("agency_shift_logs.AGY_ACTIVITY_CODE =:agencyActivityCode  " + " and ");
				valuesList.addValue("agencyActivityCode", objSearchDao.getAgyActivityCode());
			}
			if ( objSearchDao.getShiftLogSeq() != null) {
				sqlQuery.append("   agency_shift_logs.SHIFT_LOG_SEQ =:shiftLogSeq " + " and ");
				valuesList.addValue("shiftLogSeq", objSearchDao.getShiftLogSeq());
			}
			
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("where")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("and")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		orderPrepareSql = preparedSql.concat(" ORDER BY  TAG_INCIDENTS_SORT_AGENCY_DESC(INTERNAL_LOCATION_ID), LOG_DATE DESC, LOG_TIME DESC ");
		final ArrayList<AgencyShiftLogs> returnList = (ArrayList<AgencyShiftLogs>) namedParameterJdbcTemplate.query(orderPrepareSql, valuesList, agyShfLogsRM);
		return returnList;
		
		
	}
	
	/**
	 * @param
	 *
	 * 
	 *
	 */
	public int preInsert() {
		return 0;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstAgencyShiftLogs
	 *            List<AgencyShiftLogs>
	 *
	 * @return List<Integer>
	 *
	 *
	 */
	public Integer agyShilInsertAgencyShiftLogs(final List<AgencyShiftLogs> lstAgyShtLogs) {
		int returnValue = 0;
		final String sql = getQuery("OIISHLOG_AGYSHIL_INSERT_AGENCY_SHIFT_LOGS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final AgencyShiftLogs agencyShiftLogs : lstAgyShtLogs) {
			parameters.add(new BeanPropertySqlParameterSource(agencyShiftLogs));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstAgyShtLogs.size() == returnArray.length) {
			returnValue = 1;
		}
		return returnValue;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> cgfkAgyShilAgyActivityCodRecordGroup() {
		final String sql = getQuery("OIISHLOG_FIND_CGFKAGYSHILAGYACTIVITYCOD");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		List<ReferenceCodes> lstRef = new ArrayList<>();
		try {
			lstRef = (List<ReferenceCodes>) namedParameterJdbcTemplate.query(sql, createParams("MODE", MODE),
					mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error(" In cgfkAgyShilAgyActivityCodRecordGroup method :" + e);
		}
		return lstRef;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<AgencyLocations>
	 */
	public List<AgencyLocations> rgAgencyRecordGroup(final String caseloadId) {
		final String sql = getQuery("OIISHLOG_FIND_RGAGENCY");
		final RowMapper<AgencyLocations> mRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				mMapping);
		List<AgencyLocations> returnList = new ArrayList<AgencyLocations>();
		try {
			returnList = (List<AgencyLocations>) namedParameterJdbcTemplate.query(sql,
					createParams("CASELOADID", caseloadId,"MODE", MODE), mRowMapper);
		} catch (Exception e) {
			logger.error("In rgAgencyRecordGroup method : ", e);
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<MMInternalLocationUsages>
	 */
	public List<AgencyInternalLocations> rgLocationRecordGroup(final String agyLocId) {
		final String sql = getQuery("OIISHLOG_FIND_RGLOCATION");
		final RowMapper<AgencyInternalLocations> mRowMapper = Row2BeanRowMapper.makeMapping(sql,
				AgencyInternalLocations.class, agyInLoMapping);
		final List<AgencyInternalLocations> lstIncLocRes = new ArrayList<AgencyInternalLocations>();
		List<AgencyInternalLocations> lstInLoc = new ArrayList<>();
		try {
			lstInLoc = (List<AgencyInternalLocations>) namedParameterJdbcTemplate.query(sql,
					createParams("MODE", MODE, "AGENCYNAME", agyLocId), mRowMapper);
			for (final AgencyInternalLocations agcyIntLoc : lstInLoc) {
				agcyIntLoc.setCode(agcyIntLoc.getInternalLocationCode());
				lstIncLocRes.add(agcyIntLoc);
			}
		} catch (EmptyResultDataAccessException e) {
			logger.error(" In rgLocationRecordGroup method :" + e);
		}
		return lstIncLocRes;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<StaffMembers> rgStaffRecordGroup(final String caseloadId) {
		final String sql = getQuery("OIISHLOG_FIND_RGSTAFF");
		final RowMapper<StaffMembers> mRowMapper = Row2BeanRowMapper.makeMapping(sql, StaffMembers.class, mMapping);
		List<StaffMembers> lstStaff = new ArrayList<StaffMembers>();
		final List<StaffMembers> lstStaffRes = new ArrayList<StaffMembers>();
		try {
			lstStaff = (List<StaffMembers>) namedParameterJdbcTemplate.query(sql,
					createParams("CASELOADID", caseloadId), mRowMapper);
			
			for (final StaffMembers objStaff : lstStaff) {
				objStaff.setCode(objStaff.getStaffId());
				lstStaffRes.add(objStaff);
			}
			
		} catch (EmptyResultDataAccessException e) {
			logger.error(" In rgStaffRecordGroup method :" + e);
		}
		return lstStaffRes;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkAgyShilAgyShilAgy
	 *
	 * @param params
	 *
	 */
	public AgencyShiftLogs cgfkchkAgyShilAgyShilAgy(final AgencyShiftLogs paramBean) {
		final String sql = getQuery("OIISHLOG_CGFKCHK_AGY_SHIL_AGY_SHIL_AGY");
		final RowMapper<AgencyShiftLogs> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyShiftLogs.class,
				agyShfLogsMapping);
		return (AgencyShiftLogs) namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkAgyShilAgyShilRef
	 *
	 * @param params
	 *
	 */
	public ReferenceCodes cgfkchkAgyShilAgyShilRef(final ReferenceCodes paramBean) {
		final String sql = getQuery("OIISHLOG_CGFKCHK_AGY_SHIL_AGY_SHIL_REF");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				refCodeMapping);
		return (ReferenceCodes) namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkAgyShilAgyShil
	 *
	 * @param params
	 *
	 */
	public AgencyInternalLocations cgfkchkAgyShilAgyShil(final AgencyInternalLocations paramBean) {
		final String sql = getQuery("OIISHLOG_CGFKCHK_AGY_SHIL_AGY_SHIL_2");
		final RowMapper<AgencyInternalLocations> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				AgencyInternalLocations.class, agyInLoMapping);
		return (AgencyInternalLocations) namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				columnRowMapper);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkAgyShilAgyShilc
	 *
	 * @param params
	 *
	 */
	public List<Object> cgfkchkAgyShilAgyShilc(final AgencyInternalLocations paramBean) {
		final String sql = getQuery("OIISHLOG_CGFKCHK_AGY_SHIL_AGY_SHIL_2_C");
		return (List<Object>) namedParameterJdbcTemplate.queryForList(sql, createParams(), Object.class);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfklkpAgyShilAgyShilAgy
	 *
	 * @param params
	 *
	 */
	public List<AgencyInternalLocations> cgfklkpAgyShilAgyShilAgy(final AgencyInternalLocations paramBean) {
		final String sql = getQuery("OIISHLOG_CGFKLKP_AGY_SHIL_AGY_SHIL_AGY");
		final RowMapper<AgencyInternalLocations> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				AgencyInternalLocations.class, agyInLoMapping);
		return (List<AgencyInternalLocations>) namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgwhenNewFormInstance
	 *
	 * @param params
	 *
	 */
	public List<Object> cgwhenNewFormInstance(final SysDual paramBean) {
		final String sql = getQuery("OIISHLOG_CGWHEN_NEW_FORM_INSTANCE");
		return (List<Object>) namedParameterJdbcTemplate.queryForList(sql, createParams(), Object.class);
	}
	
}
