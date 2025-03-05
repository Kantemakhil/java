package net.syscon.s4.inst.automatedcounts.impl;

import java.util.ArrayList;
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
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AgencyLocationCounts;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.AgencyReportingLocs;
import net.syscon.s4.im.beans.LivingUnits;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.inst.automatedcounts.OidremcsRepository;

/**
 * Class OidremcsRepositoryImpl
 */
@Repository
public class OidremcsRepositoryImpl extends RepositoryBase implements OidremcsRepository {
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidremcsRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> mmMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("LIVING_UNIT_CODE", new FieldMapper("livingUnitCode"))
			.put("LIVING_UNIT_ID", new FieldMapper("livingUnitId")).build();
	private final Map<String, FieldMapper> mmmmMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("INTERNAL_LOCATION_CODE", new FieldMapper("internalLocationCode"))
			.put("INTERNAL_LOCATION_ID", new FieldMapper("internalLocationId")).build();
	private final Map<String, FieldMapper> agencyLocationCountsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CONDUCTED_DATETIME", new FieldMapper("conductedDatetime"))
			.put("REPORTED_COUNT", new FieldMapper("reportedCount")).put("AGY_SEQ", new FieldMapper("agySeq"))
			.put("ACTUAL_COUNT", new FieldMapper("actualCount"))
			.put("VERIFIED_USER_ID", new FieldMapper("verifiedUserId"))
			.put("VERIFIED_DATETIME", new FieldMapper("verifiedDatetime"))
			.put("RSN_CODE_USERID", new FieldMapper("rsnCodeUserid"))
			.put("DATE_SUBMITTED", new FieldMapper("dateSubmitted")).put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("RECOUNT_RSN_CODE", new FieldMapper("recountRsnCode"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("RCNT_DATETIME", new FieldMapper("rcntDatetime"))
			.put("CONDUCTED_BY_USERID", new FieldMapper("conductedByUserid"))
			.put("COMMENT_TEXT", new FieldMapper("commentText")).put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("RSN_CODE_DATETIME", new FieldMapper("rsnCodeDatetime"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("RECOUNT_TOTAL", new FieldMapper("recountTotal"))
			.put("REPORTING_LOC_ID", new FieldMapper("reportingLocId"))
			.put("RCNT_IN_PROGRESS_FLAG", new FieldMapper("rcntInProgressFlag"))
			.put("RCNT_CONDUCTED_BY", new FieldMapper("rcntConductedBy"))
			.put("ENTERED_BY_USERID", new FieldMapper("enteredByUserid"))
			.put("DISCREP_RSN_CODE", new FieldMapper("discrepRsnCode"))
			.put("COUNT_TYPE_ID", new FieldMapper("countTypeId")).build();
	private final Map<String, FieldMapper> mmmMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("LIVING_UNIT_CODE", new FieldMapper("livingUnitCode"))
			.put("LIVING_UNIT_ID", new FieldMapper("livingUnitId")).build();
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("FIRST_NAME", new FieldMapper("firstName")).put("LIVING_UNIT_CODE", new FieldMapper("livingUnitCode"))
			.put("LIVING_UNIT_ID", new FieldMapper("livingUnitId")).put("USER_ID", new FieldMapper("userId")).build();
	private final Map<String, FieldMapper> agencyLocationsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("INITCAP(HOUSING_LEV_2_CODE)", new FieldMapper("initcap(housingLev2Code)"))
			.put("INITCAP(HOUSING_LEV_1_CODE)", new FieldMapper(" initcap(housingLev1Code)"))
			.put("INITCAP(HOUSING_LEV_3_CODE", new FieldMapper("initcap(housingLev3Code"))
			.put("L_HOUSE_LEV_2_CODE", new FieldMapper("lHouseLev2Code"))
			.put("L_HOUSE_LEV_3_CODE", new FieldMapper("lHouseLev3Code ")).build();
	private final Map<String, FieldMapper> offenderBookingsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("L_HOUSE_LEV_3_CODE", new FieldMapper("lHouseLev3Code "))
			.put("CHECK_FLAG", new FieldMapper("checkFlag")).build();

	private final Map<String, FieldMapper> agencyReportingLocsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("COUNT_TYPE_ID", new FieldMapper(" countTypeId")).put("AGY_SEQ", new FieldMapper("agySeq"))
			.put("LOCATION_TYPE", new FieldMapper("locationType")).put("LOCATION1_ID", new FieldMapper("location1Id"))
			.put("LOCATION2_ID", new FieldMapper(" location2Id")).put("LOCATION3_ID", new FieldMapper("location3Id"))
			.put("LIST_SEQ ", new FieldMapper("listSeq")).put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("CREATE_DATETIME", new FieldMapper(" createDatetime"))
			.put("ACTIVE_FLAG", new FieldMapper("activeFlag")).put("EXPIRY_DATE", new FieldMapper("expiryDate"))
			.put("SEAL_FLAG", new FieldMapper("sealFlag")).put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId")).build();
	private final Map<String, FieldMapper> omsModulesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("L_HOUSE_LEV_3_CODE", new FieldMapper("lHouseLev3Code ")).build();

	private final Map<String, FieldMapper> livingUnitsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("LIVING_UNIT_ID1", new FieldMapper("livingUnitId"))
			.put("LIVING_UNIT_ID2", new FieldMapper("livingUnitId"))
			.put("LIVING_UNIT_CODE", new FieldMapper("livingUnitCode"))
			.put("LIVING_UNIT_CODE2", new FieldMapper("livingUnitCode"))
			.put("DESCRIPTION", new FieldMapper("description")).put("LOCATION_CODE", new FieldMapper("locationCode"))
			.put("AGENCY_IML_ID", new FieldMapper("agencyImlId")).build();

	private final Map<String, FieldMapper> staffmembersMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("USER_ID", new FieldMapper("userId")).put("FIRST_NAME", new FieldMapper("firstName"))
			.put("LAST_NAME", new FieldMapper("lastName")).build();

	/**
	 * Creates new OidremcsRepositoryImpl class Object
	 */
	public OidremcsRepositoryImpl() {
		
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao AgencyLocationCounts
	 *
	 * @return List<AgencyLocationCounts>
	 *
	 * @
	 */
	public List<AgencyLocationCounts> subRemCntExecuteQuery(AgencyLocationCounts objSearchDao) {
		final String sql = getQuery("OIDREMCS_SUBREMCNT_FIND_AGENCY_LOCATION_COUNTS");
		final RowMapper<AgencyLocationCounts> AgencyLocationCountsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				AgencyLocationCounts.class, agencyLocationCountsMapping);
		List<AgencyLocationCounts> returnList = new ArrayList<AgencyLocationCounts>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), AgencyLocationCountsRowMapper);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstAgencyLocationCounts List<AgencyLocationCounts>
	 *
	 * @return List<Integer>
	 *
	 * 
	 */
	public Integer subRemCntInsertsubRemCnt(final List<AgencyLocationCounts> lstAgencyLocationCounts) {
		final String sql = getQuery("OIDREMCS_SUBREMCNT_INSERT_AGENCY_LOCATION_COUNTS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final AgencyLocationCounts agencyLocationCounts : lstAgencyLocationCounts) {
			parameters.add(new BeanPropertySqlParameterSource(agencyLocationCounts));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstAgencyLocationCounts.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	public Integer subRemCntUpdatesubRemCnt(final List<AgencyLocationCounts> updateList) {
		final String sql = getQuery("OIDREMCS_SUBREMCNT_UPDATE_AGENCY_LOCATION_COUNTS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final AgencyLocationCounts agencyLocationCounts : updateList) {
			parameters.add(new BeanPropertySqlParameterSource(agencyLocationCounts));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (updateList.size() == returnArray.length) {
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
	public List<LivingUnits> cgfkHousingLevel1RecordGroup(final Integer countTypeId) {
		final String sql = getQuery("OIDREMCS_FIND_CGFKHOUSINGLEVEL1");
		final RowMapper<LivingUnits> MRowMapper = Row2BeanRowMapper.makeMapping(sql, LivingUnits.class,
				livingUnitsMapping);
		List<LivingUnits> returnList = new ArrayList<LivingUnits>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("COUNTTYPEID", countTypeId), MRowMapper);
		} catch (Exception e) {
			logger.error("cgfkHousingLevel1RecordGroup", e);
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<MM>
	 */
	public List<LivingUnits> cgfkHousingLevel2RecordGroup(final Integer countTypeId, final Integer livingUnitId) {
		final String sql = getQuery("OIDREMCS_FIND_CGFKHOUSINGLEVEL2");
		final RowMapper<LivingUnits> MMRowMapper = Row2BeanRowMapper.makeMapping(sql, LivingUnits.class,
				livingUnitsMapping);
		List<LivingUnits> returnList = new ArrayList<LivingUnits>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("COUNTTYPEID", countTypeId, "NBTLIVUNITID1", livingUnitId), MMRowMapper);
		} catch (Exception e) {
			logger.error("cgfkHousingLevel2RecordGroup", e);
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<MMM>
	 */
	public List<LivingUnits> cgfkHousingLevel3RecordGroup(final Integer countTypeId, final Integer livingUnitId) {
		final String sql = getQuery("OIDREMCS_FIND_CGFKHOUSINGLEVEL3");
		final RowMapper<LivingUnits> MMMRowMapper = Row2BeanRowMapper.makeMapping(sql, LivingUnits.class,
				livingUnitsMapping);
		List<LivingUnits> returnList = new ArrayList<LivingUnits>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("COUNTTYPEID", countTypeId, "NBTLIVUNITID2", livingUnitId), MMMRowMapper);
		} catch (Exception e) {
			logger.error("cgfkHousingLevel2RecordGroup", e);
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<MMMM>
	 */
	public List<LivingUnits> cgfkInitLocCodeRecordGroup(final Integer countTypeId) {
		final String sql = getQuery("OIDREMCS_FIND_CGFKINITLOCCODE");
		final RowMapper<LivingUnits> MMMMRowMapper = Row2BeanRowMapper.makeMapping(sql, LivingUnits.class,
				livingUnitsMapping);
		List<LivingUnits> returnList = new ArrayList<LivingUnits>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("COUNTTYPEID", countTypeId), MMMMRowMapper);
		} catch (Exception e) {
			logger.error("cgfkInitLocCodeRecordGroup", e);
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<StaffMembers> cgfkConductedByRecordGroup() {
		final String sql = getQuery("OIDREMCS_FIND_CGFKCONDUCTEDBY");
		final RowMapper<StaffMembers> mRowMapper = Row2BeanRowMapper.makeMapping(sql, StaffMembers.class,
				staffmembersMapping);
		List<StaffMembers> returnList = new ArrayList<StaffMembers>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (Exception e) {
			logger.error("cgfkConductedByRecordGroup", e);
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * oidremcsWhenNewFormInstanceWHEN-NEW-FORM-INSTANCE
	 *
	 * @param params
	 *
	 */
	public AgencyLocations oidremcsWhenNewFormInstance(final AgencyLocations paramBean) {
		final String sql = getQuery("OIDREMCS_OIDREMCS_WHENNEWFORMINSTANCE_WHENNEWFORMINSTANCE");
		final RowMapper<AgencyLocations> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				agencyLocationsMapping);
		AgencyLocations returnObj = new AgencyLocations();
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		} catch (Exception e) {
			logger.error("oidremcsWhenNewFormInstance", e);
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * createFormGlobals
	 *
	 * @param params
	 *
	 */
	public OmsModules createFormGlobals(final OmsModules paramBean) {
		final String sql = getQuery("OIDREMCS_CREATE_FORM_GLOBALS");
		final RowMapper<OmsModules> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OmsModules.class,
				omsModulesMapping);
		OmsModules returnObj = new OmsModules();
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		} catch (Exception e) {
			logger.error("oidremcsWhenNewFormInstance", e);
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * updateCountRecordUPDATE_COUNT_RECORD
	 *
	 * @param params
	 *
	 */
	public AgencyReportingLocs updateCountRecord(final AgencyReportingLocs paramBean) {
		final String sql = getQuery("OIDREMCS_UPDATE_COUNT_RECORD_UPDATE_COUNT_RECORD");
		final RowMapper<AgencyReportingLocs> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				AgencyReportingLocs.class, agencyReportingLocsMapping);
		AgencyReportingLocs returnObj = new AgencyReportingLocs();
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		} catch (Exception e) {
			logger.error("updateCountRecord", e);
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * housingLev1ValHOUSING_LEV_1_VAL
	 *
	 * @param params
	 *
	 */
	public List<AgencyReportingLocs> housingLev1Val(final AgencyReportingLocs paramBean) {
		final String sql = getQuery("OIDREMCS_HOUSING_LEV_1_VAL_HOUSING_LEV_1_VAL");
		final RowMapper<AgencyReportingLocs> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				AgencyReportingLocs.class, agencyReportingLocsMapping);
		List<AgencyReportingLocs> returnList = new ArrayList<AgencyReportingLocs>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		} catch (Exception e) {
			logger.error("housingLev1Val", e);
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * housingLev2ValHOUSING_LEV_2_VAL
	 *
	 * @param params
	 *
	 */
	public List<AgencyReportingLocs> housingLev2Val(final AgencyReportingLocs paramBean) {
		final String sql = getQuery("OIDREMCS_HOUSING_LEV_2_VAL_HOUSING_LEV_2_VAL");
		final RowMapper<AgencyReportingLocs> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				AgencyReportingLocs.class, agencyReportingLocsMapping);
		List<AgencyReportingLocs> returnList = new ArrayList<AgencyReportingLocs>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		} catch (Exception e) {
			logger.error("housingLev2Val", e);
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * calculateActualCount
	 *
	 * @param params
	 *
	 */
	public List<OffenderBookings> calculateActualCount(final OffenderBookings paramBean) {
		final String sql = getQuery("OIDREMCS_CALCULATE_ACTUAL_COUNTCALCULATE_ACTUAL_COUNT");
		final RowMapper<OffenderBookings> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderBookings.class,
				offenderBookingsMapping);
		List<OffenderBookings> returnList = new ArrayList<OffenderBookings>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		} catch (Exception e) {
			logger.error("calculateActualCount", e);
		}
		return returnList;
	}

	@Override
	public Integer getActualCount(final Integer livingUnitId) {
		final String sql = getQuery("OIDREMCS_GET_ACTUAL_COUNT");
		Integer returnList = 0;
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("L_LIV_UNIT_ID", livingUnitId),
					Integer.class);
		} catch (Exception e) {
			logger.error("calculateActualCount", e);
		}
		return returnList;
	}

	@Override
	public Integer getAgySeq(final AgencyLocationCounts searchBean) {
		final String sql = getQuery("OIDREMCS_UPDATE_COUNT_RECORDUPDATE_COUNT_RECORD");
		Integer returnObject = 0;
		try {
			returnObject = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("LIV_UNIT_ID", searchBean.getLivingUnitId(), "LIV_UNIT_ID2",
							searchBean.getLivingUnitId2(), "LIV_UNIT_ID3", searchBean.getLivingUnitId3(),
							"COUNT_TYPE_ID", searchBean.getCountTypeId()),
					Integer.class);
		} catch (EmptyResultDataAccessException e) {
			return returnObject;
		}
		return returnObject;
	}

	@Override
	public Integer getInternalLoc(final AgencyLocationCounts searchBean) {
		final String sql = getQuery("OIDREMCS_UPDATE_COUNT_RECORDUPDATE_COUNT_VALUE");
		Integer returnObject = 0;
		try {
			returnObject = namedParameterJdbcTemplate.queryForObject(sql, createParams("INTERNAL_LOCATION_ID",
					searchBean.getInternalLocationId(), "COUNT_TYPE_ID", searchBean.getCountTypeId()), Integer.class);
		} catch (EmptyResultDataAccessException e) {
			return returnObject;
		}
		return returnObject;
	}

	@Override
	public String checkLocValidation(final AgencyLocationCounts searchBean) {
		final String sql = getQuery("OIDREMCS_UPDATE_COUNT_RECORDUPDATE_COUNT_RECORD_L_CHK");
		String returnObject = null;
		try {
			returnObject = namedParameterJdbcTemplate
					.queryForObject(sql,
							createParams("COUNT_TYPE_ID", searchBean.getCountTypeId(), "AGY_SEQ",
									searchBean.getAgySeq(), "REPORTING_LOC_ID", searchBean.getReportingLocId()),
							String.class);
		} catch (EmptyResultDataAccessException e) {
			return returnObject;
		}
		return returnObject;
	}

	public String getCountLcheckProc(final AgencyLocationCounts searchBean) {
		final String sql = getQuery("OIDREMCS_COUNT_RECORDUPDATE_COUNT_RECORD");
		String returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate
					.queryForObject(sql,
							createParams("COUNT_TYPE_ID", searchBean.getCountTypeId(), "AGY_SEQ",
									searchBean.getAgySeq(), "REPORTING_LOC_ID", searchBean.getReportingLocId()),
							String.class);
		} catch (EmptyResultDataAccessException e) {
			return returnObj;
		}
		return returnObj;
	}

	@Override
	public Integer getInternalLocationCount(final Integer livingUnitId) {
		final String sql = getQuery("OIDREMCS_GET_INTERNAL_LOCATION_COUNT");
		Integer returnList = 0;
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("L_LIV_UNIT_ID", livingUnitId),
					Integer.class);
		} catch (Exception e) {
			logger.error("calculateActualCount", e);
		}
		return returnList;
	}

	@Override
	public Integer getLivingUnit3(final Integer livingUnitId, final Integer livingUnitId2) {
		final String sql = getQuery("OIDREMCS_GET_LIVING_UNIT3");
		Integer returnList = 0;
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("NBT_LIV_UNIT_ID1", livingUnitId, "NBT_LIV_UNIT_ID2", livingUnitId2), Integer.class);
		} catch (Exception e) {
			logger.error("calculateActualCount", e);
		}
		return returnList;
	}

	@Override
	public List<Object> checklcheck(final Integer livingUnitId, final Integer livingUnitId2) {
		final String sql = getQuery("OIDREMCS_LCHECK1");
		Row2BeanRowMapper.makeMapping(sql, Object.class,
				offenderBookingsMapping);
		List<Object> returnList = new ArrayList<Object>();
		try {
			returnList = namedParameterJdbcTemplate.queryForList(sql,
					createParams("LIVINGUNITID1", livingUnitId, "LIVINGUNITID2", livingUnitId2), Object.class);
		} catch (Exception e) {
			logger.error("calculateActualCount", e);
		}
		return returnList;
	}

	@Override
	public Map<String, Object> getHousingLocationLovNames(Integer countTypeId) {
		String sql = getQuery("OIDREMCS_GET_HOUSING_LOCATION_LOV_NAMES");
		return namedParameterJdbcTemplate.queryForMap(sql, createParams("COUNTTYPEID", countTypeId));
	}

	@Override
	public int getAgencyReportingLocsOne(int livUnitId) {
		String sql = getQuery("GET_AGENCY_REPORTING_LOCS_ONE");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("livUnitId", livUnitId), Integer.class);
	}

	@Override
	public String getAgencyReportingLocsTwo(int livUnitId) {
		String sql = getQuery("GET_AGENCY_REPORTING_LOCS_TWO");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("livUnitId", livUnitId), String.class);
	}

	@Override
	public int getAgencyReportingLocsThree(Integer livingUnitIdOne, Integer livingUnitIdTwo) {
		String sql = getQuery("GET_AGENCY_REPORTING_LOCS_THREE");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("livUnitId1", livingUnitIdOne, "livUnitId2", livingUnitIdTwo), Integer.class);
	}

	@Override
	public String getAgencyReportingLocsFour(Integer livingUnitIdOne, Integer livingUnitIdTwo) {
		String sql = getQuery("GET_AGENCY_REPORTING_LOCS_FOUR");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("livUnitId1", livingUnitIdOne, "livUnitId2", livingUnitIdTwo), String.class);
	}

	/**
	 * This method is used to update the records in the data base tables based on
	 *
	 * @param lstAgencyLocationCounts List<AgencyLocationCounts>
	 *
	 * @return List<Integer>
	 *
	 * @
	 */
	public Integer resubmitCountDeleteQuery(final List<AgencyLocationCounts> lstAgencyLocationCounts) {
		final String sql = getQuery("OIDREMCS_RESUBMITCOUNT_DELETE_QUERY");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final AgencyLocationCounts agyLocObj : lstAgencyLocationCounts) {
			parameters.add(new BeanPropertySqlParameterSource(agyLocObj));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstAgencyLocationCounts.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}
	
	public String getLabelDescription(final String data) {
		final String sql = getQuery("OIDREMCS_GET_LABEL_DESCRIPTION");
		String returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate
					.queryForObject(sql, createParams("CODE", data), String.class);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}
}
