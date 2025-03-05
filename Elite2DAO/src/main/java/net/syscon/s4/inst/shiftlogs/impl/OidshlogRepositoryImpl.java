package net.syscon.s4.inst.shiftlogs.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlInOutParameter;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.AgencyInternalLocations;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMemberRoles;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.inst.careinplacement.impl.OidciponRepositoryImpl;
import net.syscon.s4.inst.shiftlogs.OidshlogRepository;
import net.syscon.s4.inst.shiftlogs.bean.AgencyShiftLogs;
import net.syscon.s4.inst.shiftlogs.bean.OffendersShiftLog;
import oracle.jdbc.OracleTypes;

/**
 * Class OidshlogRepositoryImpl
 */
@Repository
public class OidshlogRepositoryImpl extends RepositoryBase implements OidshlogRepository {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidciponRepositoryImpl.class.getName());

	private static final String MODE = "ENTER-QUERY";

	private final Map<String, FieldMapper> saffMemMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("STAFF_ID", new FieldMapper("staffId"))
			.put("CASELOAD_ID", new FieldMapper("caseloadId"))
			.put("LAST_NAME||", new FieldMapper("lastName||"))
			.put("FIRST_NAME", new FieldMapper("firstName"))
			.build();
	private final Map<String, FieldMapper> refCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("AGY_ACTIVITY_CODE", new FieldMapper("agyActivityCode"))
			.put("DESCRIPTION", new FieldMapper("description"))
			.build();
	private final Map<String, FieldMapper> agyIntLocMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("AGY_LOC_I", new FieldMapper("agyLocI"))
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("INTERNAL_LOCATION_CODE", new FieldMapper("internalLocationCode"))
			.put("CASELOAD_ID", new FieldMapper("caseloadId"))
			.put("DESCRIPTIO", new FieldMapper("descriptio"))
			.put("INTERNAL_LOCATION_ID", new FieldMapper("internalLocationId"))
			.build();
	private final Map<String, FieldMapper> agyShfLogMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("OBSERVATION_TEXT", new FieldMapper("observationDetails"))
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
			.put("LOG_TIME", new FieldMapper("logTime"))
			.put("OBSERVATION_DETAILS", new FieldMapper("observationDetails"))
			.build();
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CODE", new FieldMapper("code"))
			.put("LAST_NAME||'", new FieldMapper("lastName||'"))
			.put("AGY_LOC_ID", new FieldMapper("agyLocId"))
			.put("FIRST_NAME", new FieldMapper("firstName"))
			.put("DESCRTIPTION", new FieldMapper("description"))
			.put("STAFF_ID", new FieldMapper("staffId"))
			.put("DSP_NAME", new FieldMapper("dspName"))
			.build();
	private final Map<String, FieldMapper> sysProfMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("'Y'", new FieldMapper(" 'y' "))
			.put("PROFILE_VALUE", new FieldMapper(" profileValue "))
			.build();
	private final Map<String, FieldMapper> mmIncLocUsgMap = new ImmutableMap.Builder<String, FieldMapper>()
			.put("INTERNAL_LOCATION_CODE", new FieldMapper("internalLocationCode"))
			.put("INTERNAL_LOCATION_ID", new FieldMapper("internalLocationId"))
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("offenderFullName", new FieldMapper("offenderFullName"))
			.put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId"))
			.build();
	private final Map<String, FieldMapper> staffMemRMap = new ImmutableMap.Builder<String, FieldMapper>()
			.put("'Y'", new FieldMapper(" 'y' "))
			.put("PROFILE_VALUE", new FieldMapper(" profileValue "))
			.build();

	/**
	 * Creates new OidshlogRepositoryImpl class Object
	 */
	public OidshlogRepositoryImpl() {
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
		final String sql = getQuery("OIDSHLOG_AGYSHIL_FIND_AGENCY_SHIFT_LOGS");
		final RowMapper<AgencyShiftLogs> agShiftLogsRM = Row2BeanRowMapper.makeMapping(sql, AgencyShiftLogs.class,
				agyShfLogMapping);
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource valuesList = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" where ");
			if (objSearchDao.getStaffId() != null) {
				sqlQuery.append("STAFF_ID =:staffId  " + " and ");
				valuesList.addValue("staffId", objSearchDao.getStaffId());
			}
			if (objSearchDao.getLogDate() != null) {
				sqlQuery.append(" LOG_DATE = TRUNC(:logDate) " + " and ");
				valuesList.addValue("logDate", new java.sql.Date(objSearchDao.getLogDate().getTime()));
			}
			if (objSearchDao.getInternalLocationId() != null) {
				sqlQuery.append(" INTERNAL_LOCATION_ID =:internalLocationId " + " and ");
				valuesList.addValue("internalLocationId", objSearchDao.getInternalLocationId());
			}
			if (objSearchDao.getLogTime() != null) {
				sqlQuery.append("  TO_CHAR(LOG_TIME,'HH24:MI') =:logTime " + " and ");
				final SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
				final String strTime = formatter.format(objSearchDao.getLogTime());
				valuesList.addValue("logTime", strTime);
			}
			if (objSearchDao.getAgyActivityCode() != null) {
				sqlQuery.append(" AGY_ACTIVITY_CODE =:agencyActivityCode " + " and ");
				valuesList.addValue("agencyActivityCode", objSearchDao.getAgyActivityCode());
			}
			if (objSearchDao.getLockFlag() != null) {
				sqlQuery.append(" LOCK_FLAG =:lockFlag " + " and ");
				valuesList.addValue("lockFlag", objSearchDao.getLockFlag());
			}
			if (objSearchDao.getShiftLogSeq() != null) {
				sqlQuery.append(" SHIFT_LOG_SEQ =:shiftLogSeq " + " and ");
				valuesList.addValue("shiftLogSeq", objSearchDao.getShiftLogSeq());
			}

			if (objSearchDao.getDspAgyLocId4() != null && !objSearchDao.getDspAgyLocId4().equals("")) {
				sqlQuery.append(
						" INTERNAL_LOCATION_ID IN (SELECT INTERNAL_LOCATION_ID  FROM AGENCY_INTERNAL_LOCATIONS  WHERE AGY_LOC_ID = :globalCaseLoadId )");
				valuesList.addValue("globalCaseLoadId", objSearchDao.getDspAgyLocId4());
			} else {
				if (objSearchDao.getGlobalCaseLoadId() != null) {
					sqlQuery.append(
							" INTERNAL_LOCATION_ID IN (SELECT AIL.INTERNAL_LOCATION_ID  FROM AGENCY_INTERNAL_LOCATIONS AIL "
									+ "	WHERE EXISTS (SELECT 1  FROM AGENCY_LOCATIONS LOC  WHERE LOC.AGY_LOC_ID = AIL.AGY_LOC_ID  AND EXISTS "
									+ "	(SELECT 1 FROM caseload_agency_locations cal WHERE cal.agy_loc_id = loc.agy_loc_id   AND cal.caseload_id = :globalCaseLoadId) )  )");

					valuesList.addValue("globalCaseLoadId", objSearchDao.getGlobalCaseLoadId());
				}
			}
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("where")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("and")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		
		preparedSql = preparedSql.concat(" ORDER BY SHIFT_LOG_SEQ DESC ");

		final ArrayList<AgencyShiftLogs> returnList = (ArrayList<AgencyShiftLogs>) namedParameterJdbcTemplate
				.query(preparedSql, valuesList, agShiftLogsRM);
		return returnList;
	}

	/**
	 * @param
	 *
	 * 
	 *
	 */
	public BigDecimal preInsert() {
		final String sql = getQuery("OIDSHLOG_PRE_INSERT_LOG_SEQ");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), BigDecimal.class);
	}

	/**
	 * @param
	 *
	 * 
	 *
	 */
	public BigDecimal preInsertWithoutLock() {
		final String sql = getQuery("OIDSHLOG_SHIFT_LOG_WITHOUT_LOCK_SEQ");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), BigDecimal.class);
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstAgencyShiftLogs
	 *            List<AgencyShiftLogs>
	 *
	 * @return List<Integer>
	 *
	 * 
	 */
	public Integer agyShilInsertAgencyShiftLogs(final List<AgencyShiftLogs> lstAgyShtLogs) {
		int inValue = 0;
		final String sql = getQuery("OIDSHLOG_AGYSHIL_INSERT_AGENCY_SHIFT_LOGS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		for (final AgencyShiftLogs agencyShiftLogs : lstAgyShtLogs) {
			String agecyDate=formatter.format(agencyShiftLogs.getLogDate());
			Date date=null;
			try {
				date=formatter.parse(agecyDate);
			} catch (ParseException e) {
				logger.error("In agyShilInsertAgencyShiftLogs method : ", e);
			}
			agencyShiftLogs.setLogDate(date);
			parameters.add(new BeanPropertySqlParameterSource(agencyShiftLogs));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstAgyShtLogs.size() == returnArray.length) {
			inValue = 1;
		}
		return inValue;
	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstAgencyShiftLogs
	 *            List<AgencyShiftLogs>
	 *
	 * 
	 */
	@SuppressWarnings("deprecation")
	public Integer agyShilUpdateAgencyShiftLogs(final List<AgencyShiftLogs> lstAgyShtLogs) {
		final String sql = getQuery("OIDSHLOG_AGYSHIL_UPDATE_AGENCY_SHIFT_LOGS");
		int returnValue = 0;
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		for (final AgencyShiftLogs agencyShiftLogs : lstAgyShtLogs) {
			agencyShiftLogs.setLogDate(new Date(formatter.format(agencyShiftLogs.getLogDate())));
			parameters.add(new BeanPropertySqlParameterSource(agencyShiftLogs));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstAgyShtLogs.size() == returnArray.length) {
			returnValue = 1;
		}
		return returnValue;

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstAgencyShiftLogs
	 *            List<AgencyShiftLogs>
	 *
	 * 
	 */
	public Integer agyShilDeleteAgencyShiftLogs(final List<AgencyShiftLogs> lstAgyShfLogs) {
		final String sql = getQuery("OIDSHLOG_AGYSHIL_DELETE_AGENCY_SHIFT_LOGS");
		int returnValue = 0;
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final AgencyShiftLogs agencyShiftLogs : lstAgyShfLogs) {
			parameters.add(new BeanPropertySqlParameterSource(agencyShiftLogs));
		}
		try {
			String tableName = "AGENCY_SHIFT_LOGS";
			String whereCondition = "SHIFT_LOG_SEQ=:shiftLogSeq";
			batchUpdatePreDeletedRows(tableName, whereCondition, parameters);
		} catch (Exception e) {
			logger.error(e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstAgyShfLogs.size() == returnArray.length) {
			returnValue = 1;
		}

		return returnValue;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<AgencyLocations> cgfkAgyShilDspAgyLocId4RecordGroup(final String caseloadId) {
		final String sql = getQuery("OIDSHLOG_FIND_AGY");
		final RowMapper<AgencyLocations> mRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				mMapping);
		List<AgencyLocations> returnList = new ArrayList<AgencyLocations>();
		try {
			returnList = (List<AgencyLocations>) namedParameterJdbcTemplate.query(sql,
					createParams("CASELOADID", caseloadId, "MODE", MODE), mRowMapper);
			if(returnList != null && returnList.size() > 0){
				returnList.forEach(e ->{
					if("Y".equals(e.getActiveFlag())) {
						e.setCanDisplay(true);
					} else {
						e.setCanDisplay(false);
					}
				});
			}

		} catch (Exception e) {
			logger.error("In cgfkAgyShilDspAgyLocId4RecordGroup method : ", e);
		}
		return returnList;

	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<StaffMembers> cgfkAgyShilStaffIdRecordGroup(final String caseloadId) {
		final String sql = getQuery("OIDSHLOG_FIND_CGFKAGYSHILSTAFFID");
		final RowMapper<StaffMembers> mRowMapper = Row2BeanRowMapper.makeMapping(sql, StaffMembers.class, mMapping);
		List<StaffMembers> lstStaff = new ArrayList<StaffMembers>();
		final List<StaffMembers> lstStaffRes = new ArrayList<StaffMembers>();
		try {
			lstStaff = (List<StaffMembers>) namedParameterJdbcTemplate.query(sql,
					createParams("CASELOADID", caseloadId), mRowMapper);

			for (final StaffMembers objStaff : lstStaff) {
				objStaff.setCode(objStaff.getStaffId());
				if("ACTIVE".equals(objStaff.getStatus())) {
					objStaff.setCanDisplay(true);
				} else {
					objStaff.setCanDisplay(false);
				}
				lstStaffRes.add(objStaff);
			}
		} catch (EmptyResultDataAccessException e) {
			logger.error(" In cgfkAgyShilStaffIdRecordGroup method :" + e);
		}
		return lstStaffRes;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<MMInternalLocationUsages>
	 */
	public List<AgencyInternalLocations> cgfkAgyShilDspAgyLocId3RecordGroup(final String agyLocId) {
		final String sql = getQuery("OIDSHLOG_FIND_CGFKAGYSHILDSPAGYLOCID3");
		final RowMapper<AgencyInternalLocations> mRowMapper = Row2BeanRowMapper.makeMapping(sql,
				AgencyInternalLocations.class, mmIncLocUsgMap);

		List<AgencyInternalLocations> lstInLoc = new ArrayList<>();
		final List<AgencyInternalLocations> lstIncLocRes = new ArrayList<AgencyInternalLocations>();
		try {
			lstInLoc = (List<AgencyInternalLocations>) namedParameterJdbcTemplate.query(sql,
					createParams("DSPAGYLOCID4", agyLocId), mRowMapper);

			for (final AgencyInternalLocations agcyIntLoc : lstInLoc) {
				agcyIntLoc.setCode(agcyIntLoc.getInternalLocationCode());
				lstIncLocRes.add(agcyIntLoc);
			}
		} catch (EmptyResultDataAccessException e) {
			logger.error(" In cgfkAgyShilAgyActivityCodRecordGroup method :" + e);
		}
		return lstIncLocRes;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> cgfkAgyShilAgyActivityCodRecordGroup() {
		final String sql = getQuery("OIDSHLOG_FIND_CGFKAGYSHILAGYACTIVITYCOD");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		List<ReferenceCodes> lstRef = new ArrayList<>();
		try {
			lstRef = (List<ReferenceCodes>) namedParameterJdbcTemplate.query(sql, createParams(),
					mRowMapper);
			if(lstRef != null && lstRef.size() > 0){
				lstRef.forEach(e ->{
					if("Y".equals(e.getActiveFlag())) {
						e.setCanDisplay(true);
					} else {
						e.setCanDisplay(false);
					}
				});
			}

		} catch (EmptyResultDataAccessException e) {
			logger.error(" In cgfkAgyShilAgyActivityCodRecordGroup method :" + e);
		}
		return lstRef;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * agyShilPostQuery
	 *
	 * @param params
	 *
	 */
	public List<StaffMembers> agyShilPostQuery(final StaffMembers paramBean) {
		final String sql = getQuery("OIDSHLOG_AGY_SHIL_POSTQUERY");
		final RowMapper<StaffMembers> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, StaffMembers.class,
				saffMemMapping);
		return (List<StaffMembers>) namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * agyShilPreUpdate
	 *
	 * @param params
	 *
	 */
	public SystemProfiles agyShilPreUpdate(final SystemProfiles paramBean) {
		final String sql = getQuery("OIDSHLOG_AGY_SHIL_PREUPDATE");
		final RowMapper<SystemProfiles> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, SystemProfiles.class,
				sysProfMapping);
		return (SystemProfiles) namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * agyShilPreUpdate
	 *
	 * @param params
	 *
	 */
	public StaffMemberRoles agyShilPreUpdate(final StaffMemberRoles paramBean) {
		final String sql = getQuery("OIDSHLOG_AGY_SHIL_PREUPDATE");
		final RowMapper<StaffMemberRoles> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, StaffMemberRoles.class,
				staffMemRMap);
		return (StaffMemberRoles) namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
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
		final String sql = getQuery("OIDSHLOG_CGFKCHK_AGY_SHIL_AGY_SHIL_REF");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				refCodesMapping);
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
		final String sql = getQuery("OIDSHLOG_CGFKCHK_AGY_SHIL_AGY_SHIL_2");
		final RowMapper<AgencyInternalLocations> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				AgencyInternalLocations.class, agyIntLocMapping);
		return (AgencyInternalLocations) namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				columnRowMapper);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkAgyShilAgyShilSta
	 *
	 * @param params
	 *
	 */
	public StaffMembers cgfkchkAgyShilAgyShilSta(final StaffMembers paramBean) {
		final String sql = getQuery("OIDSHLOG_CGFKCHK_AGY_SHIL_AGY_SHIL_STA");
		final RowMapper<StaffMembers> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, StaffMembers.class,
				saffMemMapping);
		return (StaffMembers) namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * defaultAgency
	 *
	 * @param params
	 *
	 */
	public List<AgencyInternalLocations> defaultAgency(final AgencyInternalLocations paramBean) {
		final String sql = getQuery("OIDSHLOG_DEFAULT_AGENCY");
		final RowMapper<AgencyInternalLocations> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				AgencyInternalLocations.class, agyIntLocMapping);
		return (ArrayList<AgencyInternalLocations>) namedParameterJdbcTemplate.query(sql, createParams(),
				columnRowMapper);
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return
	 */
	public StaffMembers agyShilWhenNewRecordInstance(final String userId) {
		final String sql = getQuery("OIDSHLOG_AGY_SHIL_WHENNEWRECORDINSTANCE");
		final RowMapper<StaffMembers> mRowMapper = Row2BeanRowMapper.makeMapping(sql, StaffMembers.class,
				saffMemMapping);
		StaffMembers objStaff = new StaffMembers();
		try {
			objStaff = (StaffMembers) namedParameterJdbcTemplate.queryForObject(sql, createParams("USERID",userId), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error(" In getStaffId method :" + e);
		}
		return objStaff;
	}

	/**
	 * This method is retrieves internalLocationId based on code
	 * 
	 *
	 * @param params
	 *
	 */
	public BigDecimal internalLocationId(final String intLocCode, final String agyLocId) {
		final String sql = getQuery("OIDSHLOG_SHIFT_INTERNAL_LOCATION_ID");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("internalLocationCode", intLocCode, "agyLocId", agyLocId), BigDecimal.class);
	}

	/**
	 * This method will update the observation text in AgencyShiftLogs
	 */
	public Integer saveObservationText(final AgencyShiftLogs agyShtLog) {
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		Map<String, Object> returnObject = null;
		SqlParameter[] sqlParameters = new SqlParameter[5];
		Integer value = 0;
		sqlParameters = new SqlParameter[] { new SqlParameter("P_SHIFT_LOG_SEQ", OracleTypes.NUMBER),
				new SqlParameter("P_OBSERVATION_TEXT", OracleTypes.VARCHAR) };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("OIDSHLOG").withProcedureName("SAVE_OBSERVATION_TEXT")
				.declareParameters(sqlParameters);
		inParamMap.put("P_SHIFT_LOG_SEQ", agyShtLog.getShiftLogSeq());
		inParamMap.put("P_OBSERVATION_TEXT", agyShtLog.getObservationDetails());
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			if (returnObject.isEmpty()) {
				value = 1;
			}
		} catch (Exception e) {
			value = 0;
			logger.error("saveObservationText: ", e);
		}
		return value;
	}

	/**
	 * This method will get the observation text from AgencyShiftLogs
	 */
	public String getObservationText(final AgencyShiftLogs agyShtLog) {
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[5];
		String returnValue = null;
		Map<String, Object> returnObject = null;
		sqlParameters = new SqlParameter[] { new SqlParameter("P_SHIFT_LOG_SEQ", OracleTypes.NUMBER),
				new SqlOutParameter("P_OBSERVATION_TEXT", OracleTypes.VARCHAR) };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("OIDSHLOG").withProcedureName("GET_OBSERVATION_TEXT").declareParameters(sqlParameters);
		inParamMap.put("P_SHIFT_LOG_SEQ", agyShtLog.getShiftLogSeq());
		inParamMap.put("P_OBSERVATION_TEXT", agyShtLog.getObservationDetails());
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			returnValue = returnObject.get("P_OBSERVATION_TEXT").toString();
		} catch (Exception e) {
			logger.error("getObservationText: ", e);
			return null;
		}
		return returnValue;
	}

	/**
	 * verifies lock for the record
	 * 
	 * @return AgencyShiftLogs
	 */
	public AgencyShiftLogs setLock() {
		final AgencyShiftLogs agyShtLogs = new AgencyShiftLogs();
		final String strStatus = getGroupPrivilege();
		String sql = null;
		String vCur = "N";
		if (strStatus != null && strStatus.equals("A")) {

			sql = getQuery("OIDSHLOG_AGY_SHIL_PREUPDATE_LOCK");
			final String shlogLockCur = namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);

			if (shlogLockCur != null && shlogLockCur.equals("ON")) {
				sql = getQuery("OIDSHLOG_AGY_SHIL_PREUPDATE_ROLE");
				String spRoleCur = namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);

				if (spRoleCur != null) {
					sql = getQuery("OIDSHLOG_AGY_SHIL_PREUPDATE_MEMBER_ROLES");
					final String smrRoleCur = namedParameterJdbcTemplate.queryForObject(sql,
							createParams("LV_SHLOG_ROLE", spRoleCur), String.class);

					vCur = smrRoleCur;

					if (vCur != null && vCur.equals("N")) {
						agyShtLogs.setInsertAllowed(true);
						agyShtLogs.setUpdateAllowed(false);
						agyShtLogs.setDeleteAllowed(false);
					} else if (vCur != null && vCur.equals("Y")) {
						agyShtLogs.setInsertAllowed(true);
						agyShtLogs.setUpdateAllowed(true);
						agyShtLogs.setDeleteAllowed(true);
					}
				} else {
					agyShtLogs.setUpdateAllowed(true);
					agyShtLogs.setDeleteAllowed(true);
				}
			} else if (shlogLockCur != null && shlogLockCur.equals("OFF")) {
				agyShtLogs.setUpdateAllowed(true);
				agyShtLogs.setDeleteAllowed(true);
			}

			sql = getQuery("OIDSHLOG_AGY_SHIL_PREUPDATE_BACKDT");
			final String shlogBackDtCur = namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);

			if (shlogBackDtCur != null) {
				final Integer lvHours = Integer.valueOf(shlogBackDtCur);

				if (lvHours == 0) {
					agyShtLogs.setLogDateAllowed(false);
					agyShtLogs.setLogTimeAllowed(false);
				} else {
					agyShtLogs.setLogDateAllowed(true);
					agyShtLogs.setLogTimeAllowed(true);
				}

			}

		} else {
			agyShtLogs.setInsertAllowed(true);
			agyShtLogs.setUpdateAllowed(false);
			agyShtLogs.setDeleteAllowed(false);
		}

		return agyShtLogs;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * getGroupPrivilege
	 *
	 *
	 */
	public String getGroupPrivilege() {
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[3];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_MODULE_NAME", OracleTypes.NUMBER),
				new SqlInOutParameter("RETURN_VALUE", OracleTypes.VARCHAR) };
		final SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_SECURITY").withFunctionName("GET_GROUP_PRIVILEGE")
				.declareParameters(sqlParameters);
		inParamMap.put("P_MODULE_NAME", "OIDSHLOG");
		inParamMap.put("RETURN_VALUE", OracleTypes.VARCHAR);
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		return simpleJDBCCall.executeFunction(String.class, inParameter);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 *
	 * @param params
	 *
	 */
	public String getBackDateHours() {
		final String sql = getQuery("OIDSHLOG_AGY_SHIL_PREUPDATE_BACKDT");
		String profValue = null;
		profValue = namedParameterJdbcTemplate.queryForObject(sql,createParams(),String.class);
		return profValue;

	}

	/**
	 * @param
	 *
	 * 
	 *
	 */
	public String checkBoxHideData() {
		final String sql = getQuery("CHECK_BOX_HIDE_DATA");
		String checkBxHide = null;
		try {
			checkBxHide = namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);
		} catch (Exception e) {
			return checkBxHide;
		}
		return checkBxHide;
	}

	/**
	 * @param
	 *
	 * 
	 *
	 */
	public Integer checkBoxShlogData(final String userid) {
		final String sql = getQuery("CHECK_BOX_SHLOG_ROLE_DATA");
		Integer checkBxHide = 0;
		try {
			checkBxHide = namedParameterJdbcTemplate.queryForObject(sql, createParams("USER_ID",userid), Integer.class);
		} catch (Exception e) {
			return checkBxHide;
		}
		return checkBxHide;
	}

	public String getLocationValue(final BigDecimal internalLocationId) {
		final String sql = getQuery("OIDSHLOG_FIND_AGENCYLIST_POSTQUERY");
		String location = null;
		try {
			location = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("internalLocationId", internalLocationId), String.class);
		} catch (Exception e) {
			return location;
		}
		return location;
	}

	public List<AgencyInternalLocations> getLocationListPostQuery(String agyLocId) {
		String sql = getQuery("OIDSHLOG_FIND_GETLOCATIONSLIST_POSTQUERY");
		final RowMapper<AgencyInternalLocations> mRowMapper = Row2BeanRowMapper.makeMapping(sql,
				AgencyInternalLocations.class, mmIncLocUsgMap);
		if(agyLocId != null) {
			sql = sql + " where agy_loc_id =:agyLocId";
		}

		List<AgencyInternalLocations> lstInLoc = new ArrayList<>();
		try {
			lstInLoc = (List<AgencyInternalLocations>) namedParameterJdbcTemplate.query(sql, createParams("agyLocId",agyLocId),
					mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error(" In cgfkAgyShilAgyActivityCodRecordGroup method :" + e);
		}
		return lstInLoc;
	}

	@Override
	public List<VHeaderBlock> relatedOffendersExcuteQuery(Integer internalLocationId) {
		final String sql = getQuery("OIDSHLOG_FIND_RELATED_OFFENDERS_QUERY");

		final RowMapper<VHeaderBlock> mRowMapper = Row2BeanRowMapper.makeMapping(sql,
				VHeaderBlock.class, mmIncLocUsgMap);

		List<VHeaderBlock> lstInLoc = new ArrayList<>();
		try {
			lstInLoc =  namedParameterJdbcTemplate.query(sql, createParams("internalLocationId" , internalLocationId),
					mRowMapper);

		} catch (EmptyResultDataAccessException e) {
			logger.error(" In cgfkAgyShilAgyActivityCodRecordGroup method :" + e);
		}
		return lstInLoc;
	}

	@Override
	public Integer offShilInsertAgencyShiftLogs(List<OffendersShiftLog> lstAgyShift) {
		final String sql = getQuery("OIDSHLOG_INSERT_OFFENDER_SHIFT_LOGS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (OffendersShiftLog offShiftLog : lstAgyShift) {
			parameters.add(new BeanPropertySqlParameterSource(offShiftLog));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("offenderMailLogInsert", e);
			if (e.getMessage().contains("offenders_shift_log_pk")) {
				return 2;
			}
		}
		if (returnArray.length > 0) {
			return 1;
		} else {
			return 0;
		}
}

	@Override
	public List<OffendersShiftLog> OffendersShiftLogExcuteQuery(BigDecimal shiftLogSeq , Long internalLocationId) {
		final String sql = getQuery("OIDSHLOG_FIND_OFFENDER_SHIFT_LOGS");

		final RowMapper<OffendersShiftLog> mRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffendersShiftLog.class, mmIncLocUsgMap);

		List<OffendersShiftLog> lstInLoc = new ArrayList<>();
		try {
			lstInLoc =  namedParameterJdbcTemplate.query(sql, createParams("shiftLogSeq" , shiftLogSeq ,"internalLocationId" ,internalLocationId),
					mRowMapper);

		} catch (EmptyResultDataAccessException e) {
			logger.error(" In cgfkAgyShilAgyActivityCodRecordGroup method :" + e);
		}
		return lstInLoc;
	}

	@Override
	public Integer offShilUpdateAgencyShiftLogs(List<OffendersShiftLog> updateList) {
		final String sql = getQuery("OIDSHLOG_UPDATE_OFFENDER_SHIFT_LOGS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffendersShiftLog sentenceTerms : updateList) {
			parameters.add(new BeanPropertySqlParameterSource(sentenceTerms));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("offShilUpdateAgencyShiftLogs : ", e);
			if (e.getMessage().contains("offenders_shift_log_pk")) {
				return 2;
			}
		}
		if (updateList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}
	
	
	
	
}
