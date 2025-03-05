package net.syscon.s4.inst.automatedcounts.impl;

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
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.inst.automatedcounts.OidrecorRepository;
import net.syscon.s4.inst.automatedcounts.beans.AgencyCounts;
import net.syscon.s4.inst.automatedcounts.beans.AgencyLocationCounts;
import net.syscon.s4.inst.automatedcounts.beans.SalesMaintenances;

@Repository
public class OidrecorRepositoryImpl extends RepositoryBase implements OidrecorRepository {
	
	private static Logger logger = LogManager.getLogger(OidrecorRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> salesMaintenancesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PROFILE_VALUE", new FieldMapper(" profileValue ")).put("*", new FieldMapper(" * ")).build();
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CODE", new FieldMapper("code ")).build();
	private final Map<String, FieldMapper> systemProfilesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PROFILE_VALUE", new FieldMapper(" profileValue ")).put("*", new FieldMapper(" * ")).build();
	private final Map<String, FieldMapper> agencyCountsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("TOTAL_ACTUAL", new FieldMapper("totalActual"))
			.put("TOTAL_FEMALE_OUT", new FieldMapper("totalFemaleOut"))
			.put("INITIATED_DATE", new FieldMapper("initiatedDate"))
			.put("RSN_CODE_USERID", new FieldMapper("rsnCodeUserid")).put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("TOTAL_FEMALE", new FieldMapper("totalFemale"))
			.put("RECOUNT_RSN_CODE", new FieldMapper("recountRsnCode"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime")).put("TOTAL_OTHER", new FieldMapper("totalOther"))
			.put("TOTAL_OTHER_OUT", new FieldMapper("totalOtherOut"))
			.put("CONDUCTED_BY_USERID", new FieldMapper("conductedByUserid"))
			.put("COMMENT_TEXT", new FieldMapper("commentText")).put("OUT_TOTAL", new FieldMapper("outTotal"))
			.put("TOTAL_MALE_OUT", new FieldMapper("totalMaleOut"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("RSN_CODE_DATETIME", new FieldMapper("rsnCodeDatetime"))
			.put("PARENT_REPORTING_LOC_ID", new FieldMapper("parentReportingLocId"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("TOTAL_REPORTED", new FieldMapper("totalReported"))
			.put("COMPLETION_DATE", new FieldMapper("completionDate"))
			.put("REPORTING_LOC_ID", new FieldMapper("reportingLocId"))
			.put("COUNT_IN_PROGRESS", new FieldMapper("countInProgress")).put("OUTCOME", new FieldMapper("outcome"))
			.put("DISCREP_RSN_CODE", new FieldMapper("discrepRsnCode"))
			.put("COUNT_TYPE_ID", new FieldMapper("countTypeId")).put("TOTAL_MALE", new FieldMapper("totalMale"))
			.build();

	/**
	 * Creates new OidrecorRepositoryImpl class Object
	 */
	public OidrecorRepositoryImpl() {
		
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao AgencyCounts
	 *
	 * @return List<AgencyCounts>
	 *
	 * 
	 */
	public List<AgencyCounts> agencyCountsExecuteQuery(AgencyCounts objSearchDao) {
		final String sql = getQuery("OIDRECOR_AGENCYCOUNTS_FIND_AGENCY_COUNTS");
		final RowMapper<AgencyCounts> AgencyCountsRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyCounts.class,
				agencyCountsMapping);
		final ArrayList<AgencyCounts> returnList = (ArrayList<AgencyCounts>) namedParameterJdbcTemplate.query(sql,
				createParams(), AgencyCountsRowMapper);
		return returnList;
	}

	/**
	 * @param
	 *
	 *
	 * 
	 */
	public int PRE_INSERT() {
		return 0;
	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstAgencyCounts List<AgencyCounts>
	 *
	 * 
	 */
	public Integer agencyCountsUpdateAgencyCounts(final List<AgencyCounts> lstAgencyCounts) {
		String sql = getQuery("OIDRECOR_AGENCYCOUNTS_UPDATE_AGENCY_COUNTS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (AgencyCounts agencyCounts : lstAgencyCounts) {
			parameters.add(new BeanPropertySqlParameterSource(agencyCounts));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstAgencyCounts.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstAgencyCounts List<AgencyCounts>
	 *
	 * 
	 */
	public Integer agencyCountsDeleteAgencyCounts(final List<AgencyCounts> lstAgencyCounts) {
		String sql = getQuery("OIDRECOR_AGENCYCOUNTS_DELETE_AGENCY_COUNTS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (AgencyCounts agencyCounts : lstAgencyCounts) {
			parameters.add(new BeanPropertySqlParameterSource(agencyCounts));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstAgencyCounts.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}
	
	public Integer agencyLocationCountsDeleteAgencyCounts(final AgencyLocationCounts list) {
		final String sql = getQuery("OIDRECOR_AGENCYLOCATIONCOUNTS_DELETE_AGENCY_COUNTS");
		int[] returnArray = new int[] {};
		final List<AgencyLocationCounts> insertList = new ArrayList<AgencyLocationCounts>();
		insertList.add(list);
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final AgencyLocationCounts alcList : insertList) {
			parameters.add(new BeanPropertySqlParameterSource(alcList));
		}
		try {
			String tableName = "AGENCY_LOCATION_COUNTS";
			String whereClause = "COUNT_TYPE_ID = :countTypeId";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method agencyLocationCountsDeleteAgencyCounts", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (insertList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> cgfkRecountRsnRecordGroup() {
		final String sql = getQuery("OIDRECOR_FIND_CGFKRECOUNTRSN");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * createFormGlobalsCREATE_FORM_GLOBALS
	 *
	 * @param params
	 *
	 */
	public OmsModules createFormGlobalscreateFormGlobals(OmsModules paramBean) {
		final String sql = getQuery("OIDRECOR_CREATE_FORM_GLOBALS_CREATE_FORM_GLOBALS");
		final RowMapper<OmsModules> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OmsModules.class, mMapping);
		OmsModules returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
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
	public SystemProfiles printSystemList(SystemProfiles paramBean) {
		final String sql = getQuery("OIDRECOR_PRINT_LIST");
		final RowMapper<SystemProfiles> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, SystemProfiles.class,
				systemProfilesMapping);
		SystemProfiles returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * printSystemList
	 *
	 * @param params
	 *
	 */
	public SalesMaintenances printList(SalesMaintenances paramBean) {
		final String sql = getQuery("OIDRECOR_PRINT_LIST");
		final RowMapper<SalesMaintenances> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, SalesMaintenances.class,
				salesMaintenancesMapping);
		SalesMaintenances returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
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
	public SystemProfiles printList(SystemProfiles paramBean) {
		final String sql = getQuery("OIDRECOR_PRINT_LIST");
		final RowMapper<SystemProfiles> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, SystemProfiles.class,
				systemProfilesMapping);
		SystemProfiles returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
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
	public AgencyLocations defaultAgyLocDEFAULT_AGY_LOC(AgencyLocations paramBean) {
		final String sql = getQuery("OIDRECOR_DEFAULT_AGY_LOC_DEFAULT_AGY_LOC");
		final RowMapper<AgencyLocations> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				agencyCountsMapping);
		AgencyLocations returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	@Override
	public Integer agencyCountsInsertAgencyCounts(List<AgencyCounts> lstAgencyCounts) {
		Integer returnVal = null;
		try {
			String sql = getQuery("OIDRECOR_AGENCYCOUNTS_INSERT_AGENCY_COUNTS");
			int[] returnArray = new int[] {};
			List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
			for (AgencyCounts agencyCounts : lstAgencyCounts) {
				parameters.add(new BeanPropertySqlParameterSource(agencyCounts));
			}
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
			if (lstAgencyCounts.size() == returnArray.length) {
				returnVal = 1;
			} else {
				returnVal = 0;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return returnVal;
	}

}
