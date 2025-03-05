package net.syscon.s4.inst.automatedcounts.impl;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.inst.automatedcounts.OidcountRepository;
import net.syscon.s4.inst.automatedcounts.beans.AgencyCountTypes;
import net.syscon.s4.inst.automatedcounts.beans.AgencyCounts;
import net.syscon.s4.inst.automatedcounts.beans.AgencyLocationCounts;
import net.syscon.s4.inst.automatedcounts.beans.AgencyReportingLocs;
import net.syscon.s4.inst.automatedcounts.beans.LockedModules;
import net.syscon.s4.inst.automatedcounts.beans.SalesMaintenances;
import net.syscon.s4.inst.automatedcounts.beans.TempOidcount;
import oracle.jdbc.OracleTypes;

/**
 * Class OidcountRepositoryImpl
 */
@Repository
public class OidcountRepositoryImpl extends RepositoryBase implements OidcountRepository {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidcountRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> agencyCountTypesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("AGY_LOC_ID", new FieldMapper("agyLocId")).put("'9999'", new FieldMapper("'9999'"))
			.put("REPORTING_LOC_ID", new FieldMapper("reportingLocId"))
			.put("COUNT_TYPE_ID", new FieldMapper("countTypeId"))
			.put("COUNT_TYPE_CODE", new FieldMapper("countTypeCode"))
			.put("INITIATED_DATE", new FieldMapper("nitiatedDate ")).put("'Y'", new FieldMapper("'y'"))
			.put("DECODE(SCHEDULED_TIME", new FieldMapper("decode(scheduledTime"))
			.put("SCHEDULED_TIME)", new FieldMapper("scheduledTime)"))
			.put("CASELOAD_ID)", new FieldMapper("caseLoadId)")).put("SESSION_ID)", new FieldMapper("sessionID)"))
			.build();

	private final Map<String, FieldMapper> salesMaintenancesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PROFILE_VALUE", new FieldMapper("profileValue ")).build();

	private final Map<String, FieldMapper> tempOidcountMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DATE_SUBMITTED", new FieldMapper("dateSubmitted")).put("TOTAL_FEMALE", new FieldMapper("totalFemale"))
			.put("DISCREPANCY_COUNT", new FieldMapper("discrepancyCount"))
			.put("REPORTED_COUNT", new FieldMapper("reportedCount"))
			.put("COUNT_TYPE_ID", new FieldMapper("countTypeId"))
			.put("COUNT_TYPE_CODE", new FieldMapper("countTypeCode"))
			.put("ENTERED_BY_USERID", new FieldMapper("enteredByUserid"))
			.put("REPORTING_LOC_ID", new FieldMapper("reportingLocId"))
			.put("TOTAL_OTHER", new FieldMapper("totalOther")).put("SCHEDULED_TIME", new FieldMapper("scheduledTime"))
			.put("TOTAL_MALE", new FieldMapper("totalMale"))
			.put("LOCATION_DESCRIPTION", new FieldMapper("locationDescription"))
			.put("AGY_LOC_ID", new FieldMapper("agyLocId")).put("ACTUAL_COUNT", new FieldMapper("actualCount"))
			.put("COUNT_TEMP", new FieldMapper("countTemp"))
			.put("ACTUAL_COUNT_TEMP", new FieldMapper("actualCountTemp"))
			.put("REPORTED_COUNT_TEMP", new FieldMapper("reportedCountTemp"))
			.put("LOWEST_LOCATION_ID", new FieldMapper("lowestLocationId"))
			.put("ROW_ID", new FieldMapper("rowId"))
			.put("USER_ID", new FieldMapper("userId")).build();

	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DECODE(SCHEDULED_TIME", new FieldMapper("decode(scheduledTime"))
			.put("COUNT_TYPE_CODE", new FieldMapper("countTypeCode")).put("CODE", new FieldMapper("code"))
			.put("SCHEDULED_TIME", new FieldMapper("scheduledTime")).put("AGY_LOC_ID", new FieldMapper("agyLocId"))
			.put("COUNT_TYPE_ID", new FieldMapper("countTypeId")).build();

	private final Map<String, FieldMapper> systemProfilesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PROFILE_VALUE", new FieldMapper("profileValue ")).build();

	private final Map<String, FieldMapper> agencyCountsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("AGY_LOC_ID", new FieldMapper("agyLocId")).put("REPORTING_LOC_ID", new FieldMapper("reportingLocId"))
			.put("'9999'", new FieldMapper("'9999'")).put("COUNT_TYPE_ID", new FieldMapper("countTypeId"))
			.put("COUNT_IN_PROGRESS", new FieldMapper("countInProgress")).put("OUTCOME", new FieldMapper("outcome"))
			.put("COUNT_TYPE_CODE", new FieldMapper("countTypeCode"))
			.put("INITIATED_DATE", new FieldMapper("initiatedDate")).put("'Y'", new FieldMapper("'y'"))
			.put("SESSION_ID", new FieldMapper("sessionId")).put("AGY_SEQ", new FieldMapper("agySeq"))
			.put("DECODE(SCHEDULED_TIME", new FieldMapper("decode(scheduledTime"))
			.put("SCHEDULED_TIME)", new FieldMapper("scheduledTime)"))
			.put("COMMENTTEXT)", new FieldMapper("commentText)")).build();
	private final Map<String, FieldMapper> agencyReportingLocsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("AGY_LOC_ID", new FieldMapper("agyLocId")).put("REPORTING_LOC_ID", new FieldMapper("reportingLocId"))
			.put("'9999'", new FieldMapper("'9999'")).put("COUNT_TYPE_ID", new FieldMapper("countTypeId"))
			.put("COUNT_IN_PROGRESS", new FieldMapper("countInProgress ")).put("OUTCOME", new FieldMapper(" outcome"))
			.put("COUNT_TYPE_CODE", new FieldMapper("countTypeCode"))
			.put("INITIATED_DATE", new FieldMapper("initiatedDate ")).put("'Y'", new FieldMapper("'y'"))
			.put("AGY_SEQ", new FieldMapper(" agySeq "))
			.put("DECODE(SCHEDULED_TIME", new FieldMapper("decode(scheduledTime"))
			.put("SCHEDULED_TIME)", new FieldMapper("scheduledTime)")).build();
	private final Map<String, FieldMapper> caseloadAgencyLocationsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("AGY_LOC_ID", new FieldMapper("agyLocId")).put("DESCRIPTION", new FieldMapper("description")).build();

	/**
	 * Creates new OidcountRepositoryImpl class Object
	 */
	public OidcountRepositoryImpl() {
	
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> cgfkAgyLocIdRecordGroup(final String caseLoadId) {
		final String sql = getQuery("OIDCOUNT_FIND_CGFKAGYLOCID");
		List<ReferenceCodes> returnList = new ArrayList<ReferenceCodes>();
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("CASELOADID", caseLoadId), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error(e);
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> cgfkCountTypesRecordGroup(final String agyLocId) {
		final String sql = getQuery("OIDCOUNT_FIND_CGFKCOUNTTYPES");
		List<ReferenceCodes> returnList = new ArrayList<ReferenceCodes>();
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("AGYLOCID", agyLocId), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("", e);
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<AgencyCountTypes> cgfkScheduledTimeRecordGroup(final String agyLocId, final String countTypeId) {
		final String sql = getQuery("OIDCOUNT_FIND_CGFKSCHEDULEDTIME");
		List<AgencyCountTypes> returnList = new ArrayList<AgencyCountTypes>();
		final RowMapper<AgencyCountTypes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyCountTypes.class,
				mMapping);

		try {
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("AGYLOCID", agyLocId, "COUNTTYPECODE", countTypeId), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("", e);
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * getGlobalSessionId
	 *
	 *
	 */
	public Long getGlobalSessionId() {
		final String sql = getQuery("OIDCOUNT_GLOBAL_SESSION_ID");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Long.class);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgwhenNewFormInstanceCGWHEN_NEW_FORM_INSTANCE
	 *
	 * @param params
	 *
	 */
	public Integer cgwhenNewFormInstanceCgLockedModules(final LockedModules paramBean) {
		final String sql = getQuery("OIDCOUNT_CGWHEN_NEW_FORM_INSTANCECG_LOCKED_MODULES");
		final Integer returnList = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("agyLocId", paramBean.getAgyLocId(), "SESSION_ID", paramBean.getSessionId()),
				Integer.class);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgwhenNewFormInstanceCgSessionId
	 *
	 * @param params
	 *
	 */
	public String cgwhenNewFormInstanceCgSessionId(final Integer sessionId) {
		final String sql = getQuery("OIDCOUNT_CGWHEN_NEW_FORM_INSTANCECG_LOCKED_MODULES_SESSION_ID");
		String returnList = null;
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("SESSION_ID", sessionId),
					String.class);
		} catch (EmptyResultDataAccessException e) {
			returnList = null;
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgwhenNewFormInstanceLockedModules
	 *
	 * @param params
	 *
	 */
	public String cgwhenNewFormInstanceLockedModules() {
		final String sql = getQuery("OIDCOUNT_CGWHEN_NEW_FORM_INSTANCE_COUNT_LOCKED_MODULES");
		String returnList = null;
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);
		} catch (EmptyResultDataAccessException e) {
			returnList = null;
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgwhenNewFormInstanceCgCaseload
	 *
	 * @param params
	 *
	 */
	public String cgwhenNewFormInstanceCgCaseload(final String userId) {
		final String sql = getQuery("OIDCOUNT_CGWHEN_NEW_FORM_INSTANCE_CG_CASELOAD");
		String returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("USER_ID",userId), String.class);
		} catch (EmptyResultDataAccessException e) {
			returnObj = null;
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgwhenNewFormInstanceCgPsessionId
	 *
	 * @param params
	 *
	 */
	public List<TempOidcount> cgwhenNewFormInstanceCgPsessionId(final String caseloadId) {
		final String sql = getQuery("OIDCOUNT_CGWHEN_NEW_FORM_INSTANCE_CG_P_SESSION_ID");
		List<TempOidcount> returnList = new ArrayList<TempOidcount>();
		final RowMapper<TempOidcount> mRowMapper = Row2BeanRowMapper.makeMapping(sql, TempOidcount.class,
				tempOidcountMapping);
		try {
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("CASELOADID", caseloadId), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return returnList;
		} catch (Exception e) {
			logger.error(" ", e);
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * printListProfileTypeClient
	 *
	 * @param params
	 *
	 */
	public SystemProfiles printListProfileTypeClient(final SystemProfiles paramBean) {
		final String sql = getQuery("OIDCOUNT_PRINT_LIST_PROFILE_TYPE_CLIENT");
		final RowMapper<SystemProfiles> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, SystemProfiles.class,
				systemProfilesMapping);
		final SystemProfiles returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(paramBean),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * printList
	 *
	 * @param params
	 *
	 */
	public SalesMaintenances printListSalesMaintenances(final SalesMaintenances paramBean) {
		final String sql = getQuery("OIDCOUNT_PRINT_LIST_SALES_MAINTENANCES");
		final RowMapper<SalesMaintenances> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, SalesMaintenances.class,
				salesMaintenancesMapping);
		final SalesMaintenances returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(paramBean),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * printList
	 *
	 * @param params
	 *
	 */
	public SystemProfiles printListSystemProfiles(final SystemProfiles paramBean) {
		final String sql = getQuery("OIDCOUNT_PRINT_LIST_SYSTEM_PROFILES");
		final RowMapper<SystemProfiles> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, SystemProfiles.class,
				systemProfilesMapping);
		final SystemProfiles returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(paramBean),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * defaultAgyLocDEFAULT_AGY_LOC
	 *
	 * @param params
	 *
	 */
	public String defaultAgyLocSessionId(final Integer sessionId) {
		final String sql = getQuery("OIDCOUNT_DEFAULT_AGY_LOC_SESSION_ID");
		String returnValue = null;
		List<String> returnObj = namedParameterJdbcTemplate.queryForList(sql, createParams("SESSIONID", sessionId),
				String.class);
		if (returnObj.size() == 1) {
			returnValue = returnObj.get(0);
			return returnValue;
		} else {
			returnObj = null;
			return returnValue;
		}

	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * defaultAgyLocDEFAULT_AGY_LOC
	 *
	 * @param params
	 *
	 */
	public AgencyLocations defaultAgyLocGlobalCaseload(final String caseLoadId) {
		final String sql = getQuery("OIDCOUNT_DEFAULT_AGY_LOC_GLOBAL_CASELOAD");
		AgencyLocations returnList = new AgencyLocations();
		final RowMapper<AgencyLocations> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				caseloadAgencyLocationsMapping);
		List<AgencyLocations> returnObj = namedParameterJdbcTemplate.query(sql, createParams("CASELOAD_ID", caseLoadId),
				columnRowMapper);
		if (returnObj.size() == 1) {
			returnList = returnObj.get(0);
			return returnList;
		} else {
			return returnList;
		}
	}

	public AgencyLocations defaultAgyLocGlobalCaseloadElseCondtion(final String agyLocId) {
		final String sql = getQuery("OIDCOUNT_DEFAULT_AGY_LOCDEFAULT_AGY_LOC_ELSE_CONDTION");
		AgencyLocations returnList = new AgencyLocations();
		final RowMapper<AgencyLocations> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				caseloadAgencyLocationsMapping);
		final List<AgencyLocations> returnObj = namedParameterJdbcTemplate.query(sql,
				createParams("AGY_LOC_ID", agyLocId), columnRowMapper);
		if (returnObj.size() == 1) {
			returnList = returnObj.get(0);
			return returnList;
		} else {
			return returnList;
		}
	}

	public Integer getCountWhenNewFormInstanceFromCaseloadAgencyLocations(final String userId) {
		final String sql = getQuery("OIDCOUNT_CGWHEN_NEW_FORM_INSTANCECG$WHEN_NEW_FORM_INSTANCE");
		final Integer returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("USER_ID", userId), Integer.class);
		return returnObj;

	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgkeyListval
	 *
	 * @param params
	 *
	 */
	public List<AgencyCountTypes> cgkeyListvalAgencyCountTypes(final AgencyCountTypes paramBean) {
		final String sql = getQuery("OIDCOUNT_CGKEY_LISTVAL_AGENCY_COUNT_TYPES");
		final RowMapper<AgencyCountTypes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyCountTypes.class,
				agencyCountTypesMapping);
		final ArrayList<AgencyCountTypes> returnList = (ArrayList<AgencyCountTypes>) namedParameterJdbcTemplate
				.query(sql, createParams(paramBean), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * createInitiatedRecords
	 *
	 * @param params
	 *
	 */
	public AgencyCounts createInitiatedRecords(final AgencyCounts paramBean) {
		final String sql = getQuery("OIDCOUNT_CREATE_INITIATED_RECORDS_REPORTING_LOC_ID");
		final RowMapper<AgencyCounts> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyCounts.class,
				agencyCountsMapping);
		AgencyCounts returnValues = new AgencyCounts();
		try {
			returnValues = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("PCOUNTTYPEID", paramBean.getCountTypeId()), columnRowMapper);
		} catch(Exception e) {
			return returnValues;
		}
		return returnValues;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * createRecountRecords
	 *
	 * @param params
	 *
	 */
	public AgencyReportingLocs createRecountRecords(final AgencyReportingLocs paramBean) {
		final String sql = getQuery("OIDCOUNT_CREATE_RECOUNT_RECORDS");
		final RowMapper<AgencyReportingLocs> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				AgencyReportingLocs.class, agencyReportingLocsMapping);
		final AgencyReportingLocs returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(paramBean),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * * This method is execute a sql query when trigger event is called
	 * 
	 * deleteInitiateRecords
	 *
	 * @param params
	 *
	 */
	public Integer deleteInitiateRecords(final AgencyCountTypes paramBean) {
		final String sql = getQuery("OIDCOUNT_DELETE_INITIATE_RECORDS");
		List<AgencyCounts> returnList = new ArrayList<AgencyCounts>();
		Integer returnValue = 0;
		final RowMapper<AgencyCounts> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyCounts.class,
				agencyCountsMapping);

		returnList = (ArrayList<AgencyCounts>) namedParameterJdbcTemplate.query(sql,
				createParams("COUNTTYPEID", paramBean.getCountTypeId(), "SESSIONID", paramBean.getSessionId()),
				columnRowMapper);
		if (returnList.size() > 0) {
			returnValue = returnList.get(0).getReportingLocId();
			return returnValue;
		}
		return returnValue;

	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * checkExistingCountSession
	 *
	 * @param params
	 *
	 */
	public List<TempOidcount> checkExistingCountSession(final Integer sessionId) {
		final String sql = getQuery("OIDCOUNT_CHECK_EXISTING_COUNT_SESSIONl");
		final RowMapper<TempOidcount> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, TempOidcount.class,
				tempOidcountMapping);
		final ArrayList<TempOidcount> returnList = (ArrayList<TempOidcount>) namedParameterJdbcTemplate.query(sql,
				createParams("SESSION_ID", sessionId), columnRowMapper);
		return returnList;
	}

	public Integer checkExistingGetCount(final Integer sessionId) {
		final String sql = getQuery("OIDCOUNT_CHECK_EXISTING_COUNT_SESSION");
		Integer returnValue = 0;
		try {
			returnValue = namedParameterJdbcTemplate.queryForObject(sql, createParams("SESSION_ID", sessionId),
					Integer.class);
		} catch (EmptyResultDataAccessException e) {
			returnValue = 0;
		}
		return returnValue;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * refreshCountREFRESH_COUNT
	 *
	 * @param params
	 *
	 */
	public List<TempOidcount> refreshCountOfTempOidCount(final Integer sessionId) {
		final String sql = getQuery("OIDCOUNT_REFRESH_COUNT_REFRESH_COUNT_TEMP_OID_COUNT");
		final RowMapper<TempOidcount> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, TempOidcount.class,
				tempOidcountMapping);
		final ArrayList<TempOidcount> returnList = (ArrayList<TempOidcount>) namedParameterJdbcTemplate.query(sql,
				createParams("P_SESSION_ID", sessionId), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgkeyExit
	 *
	 * @param params
	 *
	 */
	public Integer cgkeyExit(final AgencyReportingLocs paramBean) {
		final String sql = getQuery("OIDCOUNT_CGKEY_EXIT_CGKEY_EXIT");
		final Integer returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams(paramBean),
				Integer.class);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * checkCountExists
	 *
	 * @param params
	 *
	 */
	public Object checkCountExists(final AgencyReportingLocs paramBean) {
		final String sql = getQuery("OIDCOUNT_CHECK_COUNT_EXISTS");
		final RowMapper<AgencyReportingLocs> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				AgencyReportingLocs.class, agencyReportingLocsMapping);
		final AgencyReportingLocs returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(paramBean),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * countLockedMoulesCursor
	 *
	 * @param AgencyCountTypes
	 *
	 */
	public String countLockedMoulesCursor(final AgencyCountTypes bean) {
		String createdUserId = null;
		final String sql = getQuery("OIDCOUNT_COUNT_LOCKED_MODULES");
		try {
			createdUserId = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("AGYLOCID", bean.getAgyLocId(), "GLOBALSESSIONID", bean.getSessionId()), String.class);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return createdUserId;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * countAgencyReportinglocsCursor
	 *
	 * @param AgencyCountTypes
	 *
	 */
	public Integer countAgencyReportinglocsCursor(final AgencyCountTypes bean) {
		final String sql = getQuery("OIDCOUNT_COUNT_AGENCY_REPORTING_LOCS");
		final Integer returnList = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("COUNTTYPEID", bean.getCountTypeId()), Integer.class);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * countAgencyCountsTypesCursor
	 *
	 * @param AgencyCountTypes
	 *
	 */
	public Integer countAgencyCountsTypesCursor(final AgencyCountTypes bean) {
		final String sql = getQuery("OIDCOUNT_COUNT_AGENCY_COUNTS_TYPES_CURSOR");
		final Integer returnList = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("AGYLOCID", bean.getAgyLocId(), "COUNTTYPEID", bean.getCountTypeId()), Integer.class);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * countAgencyCountsTypesCursor
	 *
	 * @param AgencyCountTypes
	 *
	 */
	public String checkCountExistsFunction(final AgencyCountTypes bean) {
		final String sql = getQuery("OIDCOUNT_CHECK_COUNT_EXISTS");
		String scheduleTime = null;
		if (bean.getScheduledTime() != null) {
			scheduleTime = bean.getScheduledTime();
		} else {
			scheduleTime = "NA";
		}
		String data = null;
		List<String> returnList = new ArrayList<String>();
		try {
			returnList = namedParameterJdbcTemplate.queryForList(sql, createParams("P_AGY_LOC_ID",
					bean.getAgyLocId(), "P_COUNT_TYPE_ID", bean.getCountTypeId(), "P_SCHEDULED_TIME", scheduleTime),
					String.class);
		} catch(Exception e) {
			return data;
		}
		if(returnList.size() > 0) {
			data = returnList.get(0);
		}
		return data;
	}

	public Integer insertTheDataInLockedModules(final AgencyCountTypes bean) {
		final String sql = getQuery("OIDCOUNT_INSERT_LOCKED_MODULES");
		final AgencyCountTypes returnValues = preInsertOfLockedModules(bean);
		Integer returnArray = 0;
		Map<String, Comparable> namedParameters = new HashMap<String, Comparable>();
		namedParameters.put("caseloadId", bean.getCaseLoadId());
		namedParameters.put("agyLocId", returnValues.getAgyLocId());
		namedParameters.put("sessionId", returnValues.getSessionId());
		namedParameters.put("createUserId", bean.getCreateUserId());
		namedParameters.put("userId", bean.getCreateUserId());
		returnArray = namedParameterJdbcTemplate.update(sql, namedParameters);
		return returnArray;

	}

	public AgencyCountTypes preInsertOfLockedModules(final AgencyCountTypes paramBean) {
		final String sql = getQuery("OIDCOUNT_PRE_INSERT_LOCKED_MODULES");
		AgencyCountTypes returnObj = new AgencyCountTypes();
		final RowMapper<AgencyCountTypes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyCountTypes.class,
				agencyCountTypesMapping);
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("CASELOAD_ID", paramBean.getCaseLoadId(), "AGY_LOC_ID", paramBean.getAgyLocId(),
							"SESSION_ID", paramBean.getSessionId(), "userId", paramBean.getCreateUserId()),
					columnRowMapper);
		} catch (Exception e) {
			logger.error("preInsertOfLockedModules() : ", e);
			returnObj = new AgencyCountTypes();
		}
		return returnObj;
	}

	public Integer insertTheDataInAgencyCounts(final AgencyCounts bean) {
		final String sql = getQuery("OIDCOUNT_INSERT_AGENCY_COUNTS");
		int[] returnArray = new int[] {};
		final List<AgencyCounts> insertList = new ArrayList<AgencyCounts>();
		insertList.add(bean);
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final AgencyCounts alcList : insertList) {
			parameters.add(new BeanPropertySqlParameterSource(alcList));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (insertList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * preInsertOfAagencyLocationCounts
	 *
	 * @param paramBean
	 *
	 */
	public List<Integer> preInsertOfAagencyLocationCounts(final AgencyCounts paramBean) {
		final String sql = getQuery("OIDCOUNT_PRE_INSERT_OF_AGENCY_LOCATION_COUNTS");
		final ArrayList<Integer> returnList = (ArrayList<Integer>) namedParameterJdbcTemplate.queryForList(sql,
				createParams("COUNT_TYPE_ID", paramBean.getCountTypeId()), Integer.class);
		return returnList;
	}

	public Integer insertTheDataInAgencyLocationCounts(final AgencyLocationCounts list) {
		final String sql = getQuery("OIDCOUNT_INSERT_AGENCY_LOCATION_COUNTS");
		int[] returnArray = new int[] {};
		final List<AgencyLocationCounts> insertList = new ArrayList<AgencyLocationCounts>();
		insertList.add(list);
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final AgencyLocationCounts alcList : insertList) {
			parameters.add(new BeanPropertySqlParameterSource(alcList));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (insertList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	public Integer gettingMaxValueOfReportinglocidInAgenctLocations() {
		final String sql = getQuery("OIDCOUNT_GET_AGENCY_LOCATION_COUNTS_NEXT_VALUE");
		final Integer returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams(), Integer.class);
		return returnList;
	}

	public List<TempOidcount> initiateCountSetup(final AgencyCountTypes paramBean) {
		final String sql = getQuery("OIDCOUNT_INITIATE_COUNT_SETUP");
		final RowMapper<TempOidcount> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, TempOidcount.class,
				tempOidcountMapping);
		final ArrayList<TempOidcount> returnList = (ArrayList<TempOidcount>) namedParameterJdbcTemplate.query(sql,
				createParams("P_AGY_LOC_ID", paramBean.getAgyLocId(), "P_COUNT_TYPE_ID", paramBean.getCountTypeId(),
						"SESSION_ID", paramBean.getSessionId(), "P_SCHEDULED_TIME", paramBean.getScheduledTime()),
				columnRowMapper);
		return returnList;
	}

	public Integer getCountTypeIdFromDb(final AgencyCountTypes bean) {
		final String sql = getQuery("OIDCOUNT_GET_COUNT_TYPE_ID");
		final List<Integer> returnList = namedParameterJdbcTemplate.queryForList(sql,
				createParams("AGYLOCID", bean.getAgyLocId(), "COUNTTYPECODE", bean.getCountTypeCode()), Integer.class);
		if (returnList.size() > 0) {
			return returnList.get(0);
		} else {
			return 0;
		}
	}

	public Integer deleteInitiateRecordsOfAgencyLocationCounts(final Integer reportingLocId, String modifyUserId) {
		final String sql = getQuery("OIDCOUNT_DELETE_INITIATE_RECORDS_AGENCY_LOCATION_COUNTS");
		Integer returnArray = 0;
		final Map<String, Long> namedParameters = new HashMap<String, Long>();
		namedParameters.put("reportingLocId", Long.valueOf(reportingLocId.toString()));
		try {
			String tableName = "AGENCY_LOCATION_COUNTS";
			String whereClause = "REPORTING_LOC_ID = :reportingLocId";
			Map<String , Object> inputMap = new HashMap<String, Object>();
			inputMap.put("reportingLocId", reportingLocId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereClause , inputMap);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteDedDetails", e);
		}
		returnArray = namedParameterJdbcTemplate.update(sql, namedParameters);
		return returnArray;

	}

	public Integer deleteInitiateRecordsOfAgencyCounts(final Integer reportingLocId, String modifyUserId) {
		final String sql = getQuery("OIDCOUNT_DELETE_INITIATE_RECORDS_AGENCY_COUNTS");
		Integer returnArray = 0;
		final Map<String, Integer> namedParameters = new HashMap<String, Integer>();
		namedParameters.put("reportingLocId", reportingLocId);
		try {
			String tableName = "AGENCY_COUNTS";
			String whereClause = "REPORTING_LOC_ID = :reportingLocId";
			Map<String , Object> inputMap = new HashMap<String, Object>();
			inputMap.put("reportingLocId", reportingLocId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereClause , inputMap);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteInitiateRecordsOfAgencyCounts", e);
		}
		returnArray = namedParameterJdbcTemplate.update(sql, namedParameters);
		return returnArray;
	}

	@Transactional
	public Integer deleteInitiateRecordsOfLockedModules(final AgencyCountTypes bean) {
		final String sql = getQuery("OIDCOUNT_DELETE_INITIATE_RECORDS_LOCKED_MODULES");
		int[] returnArray = new int[] {};
		final List<AgencyCountTypes> deleteList = new ArrayList<AgencyCountTypes>();
		deleteList.add(bean);
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final AgencyCountTypes lmList : deleteList) {
			parameters.add(new BeanPropertySqlParameterSource(lmList));
		}
		try {
			String tableName = "LOCKED_MODULES";
			String whereClause = "SESSION_ID = :sessionId";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteInitiateRecordsOfLockedModules", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (deleteList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	public Integer cancelCountProcedure(final AgencyCountTypes paramBean) {
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[1];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_SESSION_ID", OracleTypes.NUMBER) };
		final SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("OIDCOUNT").withProcedureName("CANCEL_COUNT").declareParameters(sqlParameters);
		inParamMap.put("P_SESSION_ID", paramBean.getSessionId());
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		simpleJDBCCall.execute(inParameter);
		return 0;
	}

	public Integer submitCountSetTempOidcount(final AgencyCountTypes paramBean) {
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[10];
		Integer id = Integer.valueOf(paramBean.getSessionId().toString());
		sqlParameters = new SqlParameter[] { new SqlParameter("P_SESSION_ID", OracleTypes.NUMBER),
				new SqlParameter("P_AGY_LOC_ID", OracleTypes.VARCHAR),
				new SqlParameter("P_REPORTING_LOC_ID", OracleTypes.NUMBER),
				new SqlParameter("P_COUNT_TYPE_ID", OracleTypes.NUMBER),
				new SqlParameter("P_SCHEDULED_TIME", OracleTypes.VARCHAR) };
		final SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("OIDCOUNT").withProcedureName("SUBMIT_COUNT_JOB").declareParameters(sqlParameters);
		inParamMap.put("P_SESSION_ID", id);
		inParamMap.put("P_AGY_LOC_ID", paramBean.getAgyLocId());
		inParamMap.put("P_REPORTING_LOC_ID", paramBean.getReportingLocId());
		inParamMap.put("P_COUNT_TYPE_ID", paramBean.getCountTypeId());
		if (paramBean.getScheduledTime() != null && !paramBean.getScheduledTime().trim().equals("")) {
			inParamMap.put("P_SCHEDULED_TIME", paramBean.getScheduledTime());
		} else {
			inParamMap.put("P_SCHEDULED_TIME", "NA");
		}
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			long start = System.currentTimeMillis();
			simpleJDBCCall.execute(inParameter);
			long end = System.currentTimeMillis();
			NumberFormat formatter = new DecimalFormat("#0.00000");
			logger.info("submitCountSetTempOidcount Execution time is " + formatter.format((end - start) / 1000d)
			+ "  Inputs are P_SESSION_ID "+ id + " P_AGY_LOC_ID "+paramBean.getAgyLocId() + " P_REPORTING_LOC_ID "+paramBean.getReportingLocId()
			+ " P_COUNT_TYPE_ID "+ paramBean.getCountTypeId() + " P_SCHEDULED_TIME " + paramBean.getScheduledTime());
		} catch (Exception e) {
			logger.error("submitCountSetTempOidcount", e);
			return 1;
		}
		return 0;
	}

	public Integer setTempOidcount(final AgencyCountTypes paramBean) {
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[10];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_SESSION_ID", OracleTypes.NUMBER),
				new SqlParameter("P_AGY_LOC_ID", OracleTypes.VARCHAR),
				new SqlParameter("P_REPORTING_LOC_ID", OracleTypes.NUMBER),
				new SqlParameter("P_COUNT_TYPE_ID", OracleTypes.NUMBER),
				new SqlParameter("P_SCHEDULED_TIME", OracleTypes.VARCHAR) };
		final SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("OIDCOUNT").withProcedureName("SUBMIT_COUNT_JOB").declareParameters(sqlParameters);
		inParamMap.put("P_SESSION_ID", paramBean.getSessionId());
		inParamMap.put("P_AGY_LOC_ID", paramBean.getAgyLocId());
		inParamMap.put("P_REPORTING_LOC_ID", paramBean.getReportingLocId());
		inParamMap.put("P_COUNT_TYPE_ID", paramBean.getCountTypeId());
		inParamMap.put("P_SCHEDULED_TIME", paramBean.getScheduledTime());
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			simpleJDBCCall.execute(inParameter);
		} catch (Exception e) {
			return 1;
		}
		return 0;
	}

	public Integer checkRemoveDeadJobsProcedure(final Integer sessionId) {
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[1];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_SESSION_ID", OracleTypes.NUMBER) };
		final SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("OIDCOUNT").withProcedureName("CHECK_REMOVE_DEAD_JOBS")
				.declareParameters(sqlParameters);
		inParamMap.put("P_SESSION_ID", sessionId);
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		simpleJDBCCall.execute(inParameter);
		return 0;
	}

	public TempOidcount refreshCount(final Integer sessionId) {
		final String sql = getQuery("OIDCOUNT_REFRESH_COUNT");
		final RowMapper<TempOidcount> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, TempOidcount.class,
				tempOidcountMapping);
		TempOidcount returnList = new TempOidcount();
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("SESSION_ID", sessionId), columnRowMapper);
		} catch(Exception e) {
			return returnList;
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * refreshCountUserCancelledCur
	 *
	 * @param params
	 *
	 */
	public String refreshCountUserCancelledCur(final Integer sessionId, final String userId) {
		final String sql = getQuery("OIDCOUNT_REFRESH_COUNT_USER_CANCELLED_CUR");
		String returnList = null;
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("P_USER_ID", userId, "P_SESSION_ID", sessionId), String.class);
		} catch (EmptyResultDataAccessException e) {
			returnList = null;
		} catch (Exception e) {
			logger.error(" ", e);
		}
		return returnList;
	}

	@Override
	public Integer getTimerValue() {
		final String sql = getQuery("GET_TIMER_VALUE");
		Integer returnList = null;
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams(), Integer.class);
		} catch(Exception e) {
			return returnList;	
		}
		return returnList;
	}

	@Override
	public void deleteCancelRecords(Long sessionId, String modifyUserId) {
		final String sql = getQuery("DELETE_CANCEL_RECORDS");
		try {
			String tableName = "TEMP_OIDCOUNT";
			String whereClause = "SESSION_ID = :sessionId AND AGY_LOC_ID = 'CANCEL'";
			Map<String , Object> inputMap = new HashMap<String, Object>();
			inputMap.put("sessionId", sessionId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereClause , inputMap);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteCancelRecords", e);
		}
		namedParameterJdbcTemplate.update(sql, createParams("sessionId", sessionId));
	}
	@Override
	public Integer getlCountExistingValue(Long sessionId) {
		final String sql = getQuery("GET_LCOUNTEXISTING_VALUE");
		Integer returnList = null;
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("sessionId",sessionId), Integer.class);
		} catch(Exception e) {
			return returnList;	
		}
		return returnList;
	}
	@Override
	public Integer getlCountExistingValueCancel(Long sessionId) {
		final String sql = getQuery("GET_LCOUNTEXISTING_VALUE_CANCEL");
		Integer returnList = null;
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("sessionId",sessionId), Integer.class);
		} catch(Exception e) {
			return returnList;	
		}
		return returnList;
	}
	@Override
	public Integer insertCancelCountValue(Long sessionId) {
		final String sql = getQuery("CANCEL_COUNT_INSERT_VALUE");
		Integer returnList = null;
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("sessionId",sessionId), Integer.class);
		} catch(Exception e) {
			return returnList;	
		}
		return returnList;
	}
	@Override
	public Integer insertOidTempCountBean(final AgencyCountTypes bean) {
		final String sql = getQuery("INSERT_PROCEDURE_MIGRATE_TEMPCOUNT_DOCUMENT_INSERT");
		int[] returnArray = new int[] {};
		final List<AgencyCountTypes> deleteList = new ArrayList<AgencyCountTypes>();
		deleteList.add(bean);
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final AgencyCountTypes lmList : deleteList) {
			parameters.add(new BeanPropertySqlParameterSource(lmList));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (deleteList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}
	@Override
	public List<Long> getListReportingLocId(Long sessionId) {
		final String sql = getQuery("GET_LIST_OF_REPORTING_LOC_ID");
		List<Long> resultList = new ArrayList<>();
		try {
			resultList = namedParameterJdbcTemplate.queryForList(sql, createParams("sessionId", sessionId), Long.class);
		} catch (Exception e) {
			return resultList;
		}
		return resultList;
	}
	@Override
	public List<TempOidcount> getLTempCountCurser(final Long sessionId) {
		final String sql = getQuery("GET_LTEMP_COUNT_CURSER");
		final RowMapper<TempOidcount> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, TempOidcount.class,
				tempOidcountMapping);
		final ArrayList<TempOidcount> returnList = (ArrayList<TempOidcount>) namedParameterJdbcTemplate.query(sql,
				createParams("sessionId", sessionId), columnRowMapper);
		return returnList;
	}
	@Override
	public Integer deleteRecordFromOidTempCount(final Long sessionId, String modifyUserId) {
		final String sql = getQuery("OIDCOUNT_TEMP_COUNT_DELETERECORD");
		Integer returnArray = 0;
		final Map<String, Long> namedParameters = new HashMap<String, Long>();
		namedParameters.put("sessionId", sessionId);
		try {
			String tableName = "temp_oidcount";
			String whereClause = "session_id = :sessionId";
			Map<String , Object> inputMap = new HashMap<String, Object>();
			inputMap.put("sessionId", sessionId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereClause , inputMap);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteDedDetails", e);
		}
		returnArray = namedParameterJdbcTemplate.update(sql, namedParameters);
		return returnArray;

	}

	@Override
	public Integer getLtermintaedSessionFlag(Long sessionId) {
		final String sql = getQuery("GET_LTERMINATED_SESSIONCOUNT");
		Integer returnList = null;
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("sessionId",sessionId), Integer.class);
		} catch(Exception e) {
			return returnList;	
		}
		return returnList;
	}
	@Override
	public Integer deleteAgencyLocationCount(final Long reportLocId, String modifyUserId) {
		final String sql = getQuery("OIDCOUNT_AGYLOC_COUNT_REPORT_DELETERECORD");
		Integer returnArray = 0;
		final Map<String, Long> namedParameters = new HashMap<String, Long>();
		namedParameters.put("reportLocId", reportLocId);
		try {
			String tableName = "agency_location_counts";
			String whereClause = "reporting_loc_id = :reportLocId";
			Map<String , Object> inputMap = new HashMap<String, Object>();
			inputMap.put("reportLocId", reportLocId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereClause , inputMap);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteAgencyLocationCount", e);
		}
		returnArray = namedParameterJdbcTemplate.update(sql, namedParameters);
		return returnArray;

	}
	@Override
	public Integer deleteAgencyCounts(final Long reportLocId, String modifyUserId) {
		final String sql = getQuery("OIDCOUNT_AGENCY_COUNT_REPORT_DELETERECORD");
		Integer returnArray = 0;
		final Map<String, Long> namedParameters = new HashMap<String, Long>();
		namedParameters.put("reportLocId", reportLocId);
		try {
			String tableName = "agency_counts";
			String whereClause = "reporting_loc_id = :reportLocId";
			Map<String , Object> inputMap = new HashMap<String, Object>();
			inputMap.put("reportLocId", reportLocId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereClause , inputMap);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteAgencyCounts", e);
		}
		returnArray = namedParameterJdbcTemplate.update(sql, namedParameters);
		return returnArray;

	}
	@Override
	public Integer getAgencyLcountInitCurser(Integer lowestLocationId,String agyLocId) {
		final String sql = getQuery("OCDCOUNT_GET_LCOUNT_INIT_CURSER");
		Integer returnList = null;
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("lowestLocationId",lowestLocationId,"agyLocId",agyLocId), Integer.class);
		} catch(Exception e) {
			return returnList;	
		}
		return returnList;
	}
	
	@Override
	public Integer getLcountLivingUnitCurser(Integer lowestLocationId,String agyLocId) {
		final String sql = getQuery("OCDCOUNT_GET_LCOUNT_LIVING_UNIT_CURSER");
		Integer returnList = null;
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("lowestLocationId",lowestLocationId,"agyLocId",agyLocId), Integer.class);
		} catch(Exception e) {
			return returnList;	
		}
		return returnList;
	}
	
	@Override
	public Integer updateTempOidcount(final Integer actualCount,Long rowId) {
		final String sql = getQuery("OCDCOUNT_UPDATE_TEMP_OIDCOUNT_ACTUALCOUNT_VALUE");
		Integer returnArray = 0;
		final Map<String, Number> namedParameters = new HashMap<String, Number>();
		namedParameters.put("actualCount", actualCount);
		namedParameters.put("rowId", rowId);
		returnArray = namedParameterJdbcTemplate.update(sql, namedParameters);
		return returnArray;
	}
	@Override
	public Integer getLcountMaleInitCurser(Integer lowestLocationId,String agyLocId) {
		final String sql = getQuery("OCDCOUNT_GET_LCOUNT_INIT_MALE_CURSER");
		Integer returnList = null;
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("lowestLocationId",lowestLocationId,"agyLocId",agyLocId), Integer.class);
		} catch(Exception e) {
			return returnList;	
		}
		return returnList;
	}

	@Override
	public Integer getLcountLivingMaleInitCurser(Integer lowestLocationId, String agyLocId) {
		final String sql = getQuery("OCDCOUNT_GET_LCOUNT_LIVING_MALE_INIT_CURSER");
		Integer returnList = null;
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("lowestLocationId",lowestLocationId,"agyLocId",agyLocId), Integer.class);
		} catch(Exception e) {
			return returnList;	
		}
		return returnList;
	}

	@Override
	public Integer updateLcountMaleOidcount(Integer lActualCount, Long rowId) {
		final String sql = getQuery("OIDCOUNT_TOTAL_MALE_UPDATE");
		Integer returnArray = 0;
		final Map<String, Number> namedParameters = new HashMap<String, Number>();
		namedParameters.put("lTotalMale", lActualCount);
		namedParameters.put("rowId", rowId);
		returnArray = namedParameterJdbcTemplate.update(sql, namedParameters);
		return returnArray;
	}
	
	@Override
	public Integer getLcountFeMaleInitCurser(Integer lowestLocationId,String agyLocId) {
		final String sql = getQuery("OCDCOUNT_GET_LCOUNT_INIT_FEMALE_CURSER");
		Integer returnList = null;
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("lowestLocationId",lowestLocationId,"agyLocId",agyLocId), Integer.class);
		} catch(Exception e) {
			return returnList;	
		}
		return returnList;
	}

	@Override
	public Integer getLcountLivingFeMaleInitCurser(Integer lowestLocationId, String agyLocId) {
		final String sql = getQuery("OCDCOUNT_GET_LCOUNT_LIVING_FEMALE_INIT_CURSER");
		Integer returnList = null;
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("lowestLocationId",lowestLocationId,"agyLocId",agyLocId), Integer.class);
		} catch(Exception e) {
			return returnList;	
		}
		return returnList;
	}

	@Override
	public Integer updateLcountFeMaleOidcount(Integer lTotalFeMale, Long rowId) {
		final String sql = getQuery("OIDCOUNT_TOTAL_FEMALE_UPDATE");
		Integer returnArray = 0;
		final Map<String, Number> namedParameters = new HashMap<String, Number>();
		namedParameters.put("lTotalFeMale", lTotalFeMale);
		namedParameters.put("rowId", rowId);
		returnArray = namedParameterJdbcTemplate.update(sql, namedParameters);
		return returnArray;
	}

	@Override
	public Integer getLcountOtherInitCurser(Integer lowestLocationId, String agyLocId) {
		final String sql = getQuery("OCDCOUNT_GET_LCOUNT_INIT_OTHER_CURSER");
		Integer returnList = null;
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("lowestLocationId",lowestLocationId,"agyLocId",agyLocId), Integer.class);
		} catch(Exception e) {
			return returnList;	
		}
		return returnList;
	}

	@Override
	public Integer getLcountLivingOtherInitCurser(Integer lowestLocationId, String agyLocId) {
		final String sql = getQuery("OCDCOUNT_GET_LCOUNT_LIVING_OTHER_INIT_CURSER");
		Integer returnList = null;
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("lowestLocationId",lowestLocationId,"agyLocId",agyLocId), Integer.class);
		} catch(Exception e) {
			return returnList;	
		}
		return returnList;
	}

	@Override
	public Integer updateLcountOtherOidcount(Integer lTotalOther, Long rowId) {
		final String sql = getQuery("OIDCOUNT_TOTAL_OTHER_UPDATE");
		Integer returnArray = 0;
		final Map<String, Number> namedParameters = new HashMap<String, Number>();
		namedParameters.put("lTotalOther", lTotalOther);
		namedParameters.put("rowId", rowId);
		returnArray = namedParameterJdbcTemplate.update(sql, namedParameters);
		return returnArray;
	}

	@Override
	public Integer getLcountLoutInitCurser(Integer lowestLocationId, String agyLocId) {
		final String sql = getQuery("OCDCOUNT_GET_LCOUNT_INIT_OUTTOTAL_CURSER");
		Integer returnList = null;
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("lowestLocationId",lowestLocationId,"agyLocId",agyLocId), Integer.class);
		} catch(Exception e) {
			return returnList;	
		}
		return returnList;
	}

	@Override
	public Integer getLcountLivingLoutInitCurser(Integer lowestLocationId, String agyLocId) {
		final String sql = getQuery("OCDCOUNT_GET_LCOUNT_LIVING_OUTTOTAL_INIT_CURSER");
		Integer returnList = null;
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("lowestLocationId",lowestLocationId,"agyLocId",agyLocId), Integer.class);
		} catch(Exception e) {
			return returnList;	
		}
		return returnList;
	}

	@Override
	public Integer updateLcountLoutOidcount(Integer lOutTotal, Long rowId) {
		final String sql = getQuery("OIDCOUNT_TOTAL_OUTTOTAL_UPDATE");
		Integer returnArray = 0;
		final Map<String, Number> namedParameters = new HashMap<String, Number>();
		namedParameters.put("lOutTotal", lOutTotal);
		namedParameters.put("rowId", rowId);
		returnArray = namedParameterJdbcTemplate.update(sql, namedParameters);
		return returnArray;
	}

	@Override
	public Integer getLcountLTotalMaleOutInitCurser(Integer lowestLocationId, String agyLocId) {
		final String sql = getQuery("OCDCOUNT_GET_LCOUNT_INIT_TOTALMALEOUT_CURSER");
		Integer returnList = null;
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("lowestLocationId",lowestLocationId,"agyLocId",agyLocId), Integer.class);
		} catch(Exception e) {
			return returnList;	
		}
		return returnList;
	}

	@Override
	public Integer getLcountLivingLTotalMaleOutInitCurser(Integer lowestLocationId, String agyLocId) {
		final String sql = getQuery("OCDCOUNT_GET_LCOUNT_LIVING_TOTALMALEOUT_INIT_CURSER");
		Integer returnList = null;
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("lowestLocationId",lowestLocationId,"agyLocId",agyLocId), Integer.class);
		} catch(Exception e) {
			return returnList;	
		}
		return returnList;
	}

	@Override
	public Integer updateLcountLTotalMaleOutOidcount(Integer lTotalMaleOut, Long rowId) {
		final String sql = getQuery("OIDCOUNT_TOTAL_TOTALMALEOUT_UPDATE");
		Integer returnArray = 0;
		final Map<String, Number> namedParameters = new HashMap<String, Number>();
		namedParameters.put("lTotalMaleOut", lTotalMaleOut);
		namedParameters.put("rowId", rowId);
		returnArray = namedParameterJdbcTemplate.update(sql, namedParameters);
		return returnArray;
	}

	@Override
	public Integer getLcountLTotalFemaleOutInitCurser(Integer lowestLocationId, String agyLocId) {
		final String sql = getQuery("OCDCOUNT_GET_LCOUNT_INIT_TOTALFEMALEOUT_CURSER");
		Integer returnList = null;
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("lowestLocationId",lowestLocationId,"agyLocId",agyLocId), Integer.class);
		} catch(Exception e) {
			return returnList;	
		}
		return returnList;
	}

	@Override
	public Integer getLcountLivingLTotalFemaleOutInitCurser(Integer lowestLocationId, String agyLocId) {
		final String sql = getQuery("OCDCOUNT_GET_LCOUNT_LIVING_TOTALFEMALEOUT_INIT_CURSER");
		Integer returnList = null;
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("lowestLocationId",lowestLocationId,"agyLocId",agyLocId), Integer.class);
		} catch(Exception e) {
			return returnList;	
		}
		return returnList;
	}

	@Override
	public Integer updateLcountLTotalFemaleOutOidcount(Integer lOutTotalFemaleOut, Long rowId) {
		final String sql = getQuery("OIDCOUNT_TOTAL_TOTALFEMALEOUT_UPDATE");
		Integer returnArray = 0;
		final Map<String, Number> namedParameters = new HashMap<String, Number>();
		namedParameters.put("lOutTotalFemaleOut", lOutTotalFemaleOut);
		namedParameters.put("rowId", rowId);
		returnArray = namedParameterJdbcTemplate.update(sql, namedParameters);
		return returnArray;
	}

	@Override
	public Integer getLcountLTotalOtherOutInitCurser(Integer lowestLocationId, String agyLocId) {
		final String sql = getQuery("OCDCOUNT_GET_LCOUNT_INIT_TOTALOTHEROUT_CURSER");
		Integer returnList = null;
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("lowestLocationId",lowestLocationId,"agyLocId",agyLocId), Integer.class);
		} catch(Exception e) {
			return returnList;	
		}
		return returnList;
	}

	@Override
	public Integer getLcountLivingLTotalOtherOutInitCurser(Integer lowestLocationId, String agyLocId) {
		final String sql = getQuery("OCDCOUNT_GET_LCOUNT_LIVING_TOTALOTHEROUT_INIT_CURSER");
		Integer returnList = null;
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("lowestLocationId",lowestLocationId,"agyLocId",agyLocId), Integer.class);
		} catch(Exception e) {
			return returnList;	
		}
		return returnList;
	}

	@Override
	public Integer updateLcountLTotalOtherOutOidcount(Integer lTotalOtherOut, Long rowId) {
		final String sql = getQuery("OIDCOUNT_TOTAL_TOTALOTHEROUT_UPDATE");
		Integer returnArray = 0;
		final Map<String, Number> namedParameters = new HashMap<String, Number>();
		namedParameters.put("lTotalOtherOut", lTotalOtherOut);
		namedParameters.put("rowId", rowId);
		returnArray = namedParameterJdbcTemplate.update(sql, namedParameters);
		return returnArray;
	}

}