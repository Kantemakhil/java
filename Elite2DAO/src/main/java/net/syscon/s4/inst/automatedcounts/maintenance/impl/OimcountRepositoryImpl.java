package net.syscon.s4.inst.automatedcounts.maintenance.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.AgencyReportingLocs;
import net.syscon.s4.im.beans.LivingUnits;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.inst.automatedcounts.beans.AgencyCountTypes;
import net.syscon.s4.inst.automatedcounts.maintenance.OimcountRepository;
import net.syscon.s4.inst.movements.beans.VIntLocSummaries;

/**
 * Class OimcountRepositoryImpl
 * 
 */
@Repository
public class OimcountRepositoryImpl extends RepositoryBase implements OimcountRepository {
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OimcountRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> agencyCountTypesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("COUNT_TYPE_ID", new FieldMapper("countTypeId")).put("AGY_LOC_ID", new FieldMapper("agyLocId"))
			.put("HOUSELOC", new FieldMapper("houseLoc")).put("INTLOC", new FieldMapper("intLoc")).build();
	private final Map<String, FieldMapper> referenceCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description")).put("CODE", new FieldMapper("code")).build();
	private final Map<String, FieldMapper> agencyLocationCountsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("'Y'", new FieldMapper(" 'y' ")).build();
	private final Map<String, FieldMapper> livingUnitsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description")).put("ACTIVE_FLAG", new FieldMapper("activeFlag"))
			.put("CODE", new FieldMapper("code")).put("LIVING_UNIT_CODE", new FieldMapper("livingUnitCode"))

			.build();
	private final Map<String, FieldMapper> tempOidcountMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("'Y'", new FieldMapper(" 'y' ")).build();
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("AGY_LOC_ID", new FieldMapper("agyLocId")).put("USER_DESC", new FieldMapper("userDesc"))
			.put("INTERNAL_LOCATION_CODE", new FieldMapper("internalLocationCode"))
			.put("LIVING_UNIT_CODE", new FieldMapper("livingUnitCode"))
			.put("LIVING_UNIT_ID", new FieldMapper("livingUnitId")).put("DESCRIPTION", new FieldMapper("description"))
			.put("CODE", new FieldMapper("code")).build();
	private final Map<String, FieldMapper> agencyLocationsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("HOUSING_LEV_2_CODE", new FieldMapper("housingLev2Code"))
			.put("HOUSING_LEV_1_CODE", new FieldMapper("housingLev1Code"))
			.put("AGY_LOC_ID", new FieldMapper("agyLocId"))
			.put("HOUSING_LEV_3_CODE", new FieldMapper("housingLev3Code")).build();
	private final Map<String, FieldMapper> agencyReportingLocsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description ")).put("ACTIVE_FLAG", new FieldMapper("activeFlag"))
			.build();
	private final Map<String, FieldMapper> agencyCountsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("'Y'", new FieldMapper(" 'y' ")).build();
	private final Map<String, FieldMapper> agencyInternalMvmtLocationsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description")).put("ACTIVE_FLAG", new FieldMapper("activeFlag"))
			.build();
	private final Map<String, FieldMapper> vIntLocSummariesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description")).put("ACTIVE_FLAG", new FieldMapper("activeFlag"))
			.put("INTERNAL_LOCATION_CODE", new FieldMapper("internalLocationCode")).build();
	private final Map<String, FieldMapper> omsModulesMapping = new ImmutableMap.Builder<String, FieldMapper>().build();

	/**
	 * Creates new OimcountRepositoryImpl class Object
	 */
	public OimcountRepositoryImpl() {
		
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao AgencyLocations
	 *
	 * @return List<AgencyLocations>
	 *
	 * @throws SQLException
	 */
	public List<AgencyLocations> agencyLocationsExecuteQuery(final AgencyLocations objSearchDao) {
		final String sql = getQuery("OIMCOUNT_AGENCYLOCATIONS_FIND_AGENCY_LOCATIONS");
		final RowMapper<AgencyLocations> AgylocRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				agencyLocationsMapping);

		String preparedSql = null;
		final MapSqlParameterSource inParameterSource = new MapSqlParameterSource();
		final StringBuffer sqlQuery = new StringBuffer();
		sqlQuery.append(sql);
		if (objSearchDao != null) {

			if (objSearchDao != null && objSearchDao.getAgyLocId() != null) {
				sqlQuery.append(" AGY_LOC_ID = :AGY_LOC_ID" + " AND ");
				inParameterSource.addValue("AGY_LOC_ID", objSearchDao.getAgyLocId());
			}
			if (objSearchDao.getCaseloadId() != null) {
				sqlQuery.append(
						" AGENCY_LOCATION_TYPE IN ('INST' ,'HOSP','JAIL') AND AGY_LOC_ID NOT IN ('OUT', 'TRN')  AND DEACTIVATION_DATE IS NULL AND AGY_LOC_ID IN "
								+ "(SELECT CAL.AGY_LOC_ID FROM CASELOAD_AGENCY_LOCATIONS CAL, CASELOADS CAS  WHERE CAS.CASELOAD_ID = CAL.CASELOAD_ID AND "
								+ "CAL.CASELOAD_ID =  :CASELOAD_ID  AND CAL.AGY_LOC_ID NOT IN ('OUT','TRN','CRT') )");
				inParameterSource.addValue("CASELOAD_ID", objSearchDao.getCaseloadId());
			}
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("WHERE")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("AND")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		preparedSql = preparedSql + " ORDER BY AGY_LOC_ID";
		return namedParameterJdbcTemplate.query(preparedSql, inParameterSource, AgylocRowMapper);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao AgencyCountTypes
	 *
	 * @return List<AgencyCountTypes>
	 *
	 * @throws SQLException
	 */
	public List<AgencyCountTypes> agencyCountTypesExecuteQuery(final AgencyCountTypes objSearchDao) {
		final String sql = getQuery("OIMCOUNT_AGENCYCOUNTTYPES_FIND_AGENCY_COUNT_TYPES");
		final RowMapper<AgencyCountTypes> AgencyCountTypesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				AgencyCountTypes.class, agencyCountTypesMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("CASELOADID", objSearchDao.getCaseLoadId()),
				AgencyCountTypesRowMapper);
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstAgencyCountTypes List<AgencyCountTypes>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public Integer agencyCountTypesInsertAgencyCountTypes(final List<AgencyCountTypes> listObj) {
		final String sql = getQuery("OIMCOUNT_AGENCYCOUNTTYPES_INSERT_AGENCY_COUNT_TYPES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final AgencyCountTypes agencyCountTypes : listObj) {
			parameters.add(new BeanPropertySqlParameterSource(agencyCountTypes));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (listObj.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstAgencyCountTypes List<AgencyCountTypes>
	 *
	 * @throws SQLException
	 */
	public Integer agencyCountTypesUpdateAgencyCountTypes(final List<AgencyCountTypes> listObj) {
		final String sql = getQuery("OIMCOUNT_AGENCYCOUNTTYPES_UPDATE_AGENCY_COUNT_TYPES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final AgencyCountTypes agencyCountTypes : listObj) {
			parameters.add(new BeanPropertySqlParameterSource(agencyCountTypes));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (listObj.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstAgencyCountTypes List<AgencyCountTypes>
	 *
	 * @throws SQLException
	 */
	public Integer agencyCountTypesDeleteAgencyCountTypes(final List<AgencyCountTypes> listObj) {
		final String sql = getQuery("OIMCOUNT_AGENCYCOUNTTYPES_DELETE_AGENCY_COUNT_TYPES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final AgencyCountTypes agencyCountTypes : listObj) {
			parameters.add(new BeanPropertySqlParameterSource(agencyCountTypes));
		}
		try {
			String tableName = "AGENCY_COUNT_TYPES";
			String whereClause = "COUNT_TYPE_ID=:countTypeId";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method agencyCountTypesDeleteAgencyCountTypes", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (listObj.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao AgencyReportingLocs
	 *
	 * @return List<AgencyReportingLocs>
	 *
	 * @throws SQLException
	 */
	public List<AgencyReportingLocs> agencyReportingLocsHousExecuteQuery(final AgencyReportingLocs objSearchDao) {
		final String sql = getQuery("OIMCOUNT_AGENCYREPORTINGLOCSHOUS_FIND_AGENCY_REPORTING_LOCS");
		final RowMapper<AgencyReportingLocs> AgencyReportingLocsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				AgencyReportingLocs.class, agencyReportingLocsMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("COUNT_TYPE_ID", objSearchDao.getCountTypeId()),
				AgencyReportingLocsRowMapper);
	}

	public List<AgencyReportingLocs> agencyReportingLocsInitExecuteQuery(final AgencyReportingLocs objSearchDao) {
		final String sql = getQuery("OIMCOUNT_AGENCYREPORTINGLOCSINIT_FIND_AGENCY_REPORTING_LOCS");
		final RowMapper<AgencyReportingLocs> AgencyReportingLocsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				AgencyReportingLocs.class, agencyReportingLocsMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("COUNT_TYPE_ID", objSearchDao.getCountTypeId()),
				AgencyReportingLocsRowMapper);
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstAgencyReportingLocs List<AgencyReportingLocs>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public Integer agencyReportingLocsHousInsertAgencyReportingLocs(final AgencyReportingLocs bean) {
		final String sql = getQuery("OIMCOUNT_AGENCYREPORTINGLOCSHOUS_INSERT_AGENCY_REPORTING_LOCS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		parameters.add(new BeanPropertySqlParameterSource(bean));
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (returnArray.length == 1) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstAgencyReportingLocs List<AgencyReportingLocs>
	 *
	 * @throws SQLException
	 */
	public Integer agencyReportingLocsHousUpdateAgencyReportingLocs(final List<AgencyReportingLocs> listObj) {
		final String sql = getQuery("OIMCOUNT_AGENCYREPORTINGLOCSHOUS_UPDATE_AGENCY_REPORTING_LOCS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final AgencyReportingLocs bean : listObj) {
			parameters.add(new BeanPropertySqlParameterSource(bean));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (listObj.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstAgencyReportingLocs List<AgencyReportingLocs>
	 *
	 * @throws SQLException
	 */
	public Integer agencyReportingLocsHousDeleteAgencyReportingLocs(final List<AgencyReportingLocs> listObj) {
		final String sql = getQuery("OIMCOUNT_AGENCYREPORTINGLOCSHOUS_DELETE_AGENCY_REPORTING_LOCS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final AgencyReportingLocs bean : listObj) {
			parameters.add(new BeanPropertySqlParameterSource(bean));
		}
		try {
			String tableName = "AGENCY_REPORTING_LOCS";
			String whereClause = "COUNT_TYPE_ID=:countTypeId and AGY_SEQ=:agySeq and LOCATION_TYPE=:locationType";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method agencyReportingLocsHousDeleteAgencyReportingLocs", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (listObj.size() == returnArray.length) {
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
	public List<AgencyLocations> cgfkAgyLocIdRecordGroup(final String caseloadid) {
		List<AgencyLocations> returnList = new ArrayList<>();
		final String sql = getQuery("OIMCOUNT_FIND_CGFKAGYLOCID");
		final RowMapper<AgencyLocations> mRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				agencyLocationsMapping);

		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("CASELOADID", caseloadid), mRowMapper);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> cgfkCountTypesRecordGroup() {
		List<ReferenceCodes> returnList = new ArrayList<>();
		final String sql = getQuery("OIMCOUNT_FIND_CGFKCOUNTTYPES");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);

		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<LivingUnits> cgfkHousingLevel1RecordGroup(final String agyLocId) {
		final String sql = getQuery("OIMCOUNT_FIND_CGFKHOUSINGLEVEL1");
		List<LivingUnits> returnList = new ArrayList<>();
		final RowMapper<LivingUnits> mRowMapper = Row2BeanRowMapper.makeMapping(sql, LivingUnits.class,
				livingUnitsMapping);

		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("AGYLOCID", agyLocId), mRowMapper);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<LivingUnits> cgfkHousingLevel2RecordGroup(final String agyLocId, final String locCode) {
		List<LivingUnits> returnList = new ArrayList<>();
		final String sql = getQuery("OIMCOUNT_FIND_CGFKHOUSINGLEVEL2");
		final RowMapper<LivingUnits> mRowMapper = Row2BeanRowMapper.makeMapping(sql, LivingUnits.class,
				livingUnitsMapping);

		try {
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("AGYLOCID", agyLocId, "LIVING_UNIT_ID", Integer.valueOf(locCode)), mRowMapper);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<LivingUnits> cgfkHousingLevel3RecordGroup(final String agyLocId, final String locCode) {
		List<LivingUnits> returnList = new ArrayList<>();
		final String sql = getQuery("OIMCOUNT_FIND_CGFKHOUSINGLEVEL3");
		final RowMapper<LivingUnits> mRowMapper = Row2BeanRowMapper.makeMapping(sql, LivingUnits.class,
				livingUnitsMapping);
		System.out.println("cgfkHousingLevel3RecordGroup locationId)" + Integer.valueOf(locCode));
		try {
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("AGYLOCID", agyLocId, "LIVING_UNIT_ID", Integer.valueOf(locCode)), mRowMapper);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<VIntLocSummaries> cgfkInitLocCodeRecordGroup(final String agylLocId) {
		List<VIntLocSummaries> returnList = new ArrayList<>();
		final String sql = getQuery("OIMCOUNT_FIND_CGFKINITLOCCODE");
		final RowMapper<VIntLocSummaries> mRowMapper = Row2BeanRowMapper.makeMapping(sql, VIntLocSummaries.class,
				mMapping);

		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("AGY_LOC_ID", agylLocId), mRowMapper);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * agencyLocationsOnCheckDeleteMaster
	 *
	 * @param params
	 *
	 */
	public List<AgencyCountTypes> agencyLocationsOnCheckDeleteMaster(final AgencyCountTypes paramBean) {
		final String sql = getQuery("OIMCOUNT_AGENCY_LOCATIONS_ONCHECKDELETEMASTER");
		final RowMapper<AgencyCountTypes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyCountTypes.class,
				agencyCountTypesMapping);
		return namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * agencyLocationsWhenValidateItem
	 *
	 * @param params
	 *
	 */
	public List<AgencyLocations> agencyLocationsWhenValidateItem(final AgencyLocations paramBean) {
		final String sql = getQuery("OIMCOUNT_AGENCY_LOCATIONS_WHENVALIDATEITEM");
		final RowMapper<AgencyLocations> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				agencyLocationsMapping);
		return namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * agencyLocationsWhenNewRecordInstance
	 *
	 * @param params
	 *
	 */
	public AgencyLocations agencyLocationsWhenNewRecordInstance(final AgencyLocations paramBean) {
		final String sql = getQuery("OIMCOUNT_AGENCY_LOCATIONS_WHENNEWRECORDINSTANCE");
		final RowMapper<AgencyLocations> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				agencyLocationsMapping);
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("AGYLOCID1", paramBean.getAgyLocId()),
				columnRowMapper);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * agencyCountTypesPreInsert
	 *
	 * @param params
	 *
	 */
	public Integer agencyCountTypesPreInsert(final List<String> agyLocId, final List<String> countTypeCode,
			final List<String> scheduleTime) {
		final String sql = getQuery("OIMCOUNT_AGENCY_COUNT_TYPES_PREINSERT");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("P_AGY_LOC_ID", agyLocId,
				"P_COUNT_TYPE_CODE", countTypeCode, "P_SCHEDULED_TIME", scheduleTime), Integer.class);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * agencyCountTypesPostQuery
	 *
	 * @param params
	 *
	 */
	public List<ReferenceCodes> agencyCountTypesPostQuery(final ReferenceCodes paramBean) {
		final String sql = getQuery("OIMCOUNT_AGENCY_COUNT_TYPES_POSTQUERY");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		return namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * agencyCountTypesOnCheckDeleteMaster
	 *
	 * @param params
	 *
	 */
	public Integer agencyCountTypesOnCheckDeleteMasters(final List<Integer> countTypeId) {
		final String sql = getQuery("OIMCOUNT_AGENCY_COUNT_TYPES_ONCHECKDELETEMASTER");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("COUNTTYPEID", countTypeId), Integer.class);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * agencyCountTypesOnCheckDeleteMaster
	 *
	 * @param params
	 *
	 */
	public List<AgencyReportingLocs> agencyCountTypesOnCheckDeleteMaster(final AgencyReportingLocs paramBean) {
		final String sql = getQuery("OIMCOUNT_AGENCY_COUNT_TYPES_ONCHECKDELETEMASTER");
		final RowMapper<AgencyReportingLocs> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				AgencyReportingLocs.class, agencyReportingLocsMapping);
		return namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * agencyCountTypesPreUpdate
	 *
	 * @param params
	 *
	 */
	public Integer agencyCountTypesPreUpdate(final List<String> agyLocId, final List<String> countTypeCode,
			final List<String> scheduleTime, final List<Integer> countTypeId) {
		final String sql = getQuery("OIMCOUNT_AGENCY_COUNT_TYPES_PREUPDATE");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("P_AGY_LOC_ID", agyLocId,
				"P_COUNT_TYPE_CODE", countTypeCode, "P_SCHEDULED_TIME", scheduleTime, "COUNT_TYPE_ID", countTypeId),
				Integer.class);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * agencyReportingLocsHousPostQuery
	 *
	 * @param params
	 *
	 */
	public LivingUnits agencyReportingLocsHousPostQuery(final Integer livingUnitId) {
		final String sql = getQuery("OIMCOUNT_AGENCY_REPORTING_LOCS_HOUS_POSTQUERY");
		LivingUnits retBean = new LivingUnits();
		final RowMapper<LivingUnits> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, LivingUnits.class,
				livingUnitsMapping);
		try {
			retBean = namedParameterJdbcTemplate.queryForObject(sql, createParams("P_LIVING_UNIT_ID", livingUnitId),
					columnRowMapper);
		} catch (Exception e) {
			logger.error("agencyReportingLocsHousPostQuery", e);
		}
		return retBean;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * agencyReportingLocsHousPreInsert
	 *
	 * @param params
	 *
	 */
	public Integer agencyReportingLocsHousPreInsert(final AgencyReportingLocs paramBean) {
		final String sql = getQuery("OIMCOUNT_AGENCY_REPORTING_LOCS_HOUS_PREINSERT");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("P_COUNT_TYPE_ID", paramBean.getCountTypeId(), "P_LOCATION1_ID",
						paramBean.getLocation1Id(), "P_LOCATION2_ID", paramBean.getLocation2Id(), "P_LOCATION3_ID",
						paramBean.getLocation3Id()),
				Integer.class);

	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * agencyReportingLocsHousPreInsert
	 *
	 * @param params
	 *
	 */
	public List<LivingUnits> agencyReportingLocsHousPreInsert(final LivingUnits paramBean) {
		final String sql = getQuery("OIMCOUNT_AGENCY_REPORTING_LOCS_HOUS_PREINSERT");
		final RowMapper<LivingUnits> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, LivingUnits.class,
				livingUnitsMapping);
		return namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * agencyReportingLocsHousWhenNewRecordInstance
	 *
	 * @param params
	 *
	 */
	public Integer agencyReportingLocsHousWhenNewRecordInstance(final AgencyReportingLocs paramBean) {
		final String sql = getQuery("OIMCOUNT_AGENCY_REPORTING_LOCS_HOUS_WHENNEWRECORDINSTANCE");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("COUNTTYPEID", paramBean.getCountTypeId()),
				Integer.class);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * agencyReportingLocsInitPreInsert
	 *
	 * @param params
	 *
	 */
	public Integer agencyReportingLocsInitPreInsert(final List<String> locType, final List<Integer> countTypeId,
			final List<Integer> locId) {
		final String sql = getQuery("OIMCOUNT_AGENCY_REPORTING_LOCS_INIT_PREINSERT");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("P_LOCATION_TYPE", locType, "P_COUNT_TYPE_ID", countTypeId, "P_LOCATION1_ID", locId),
				Integer.class);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * agencyReportingLocsInitPostQuery
	 *
	 * @param params
	 *
	 */
	public VIntLocSummaries agencyReportingLocsInitPostQuery(final AgencyReportingLocs paramBean) {
		final String sql = getQuery("OIMCOUNT_AGENCY_REPORTING_LOCS_INIT_POSTQUERY");
		final RowMapper<VIntLocSummaries> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, VIntLocSummaries.class,
				agencyInternalMvmtLocationsMapping);
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("P_AGENCY_IML_ID", paramBean.getLocation1Id()), columnRowMapper);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * agencyReportingLocsInitPostQuery
	 *
	 * @param params
	 *
	 */
	public List<VIntLocSummaries> agencyReportingLocsInitPostQuery(final VIntLocSummaries paramBean) {
		final String sql = getQuery("OIMCOUNT_AGENCY_REPORTING_LOCS_INIT_POSTQUERY");
		final RowMapper<VIntLocSummaries> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, VIntLocSummaries.class,
				vIntLocSummariesMapping);
		return namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * agencyReportingLocsInitWhenNewRecordInstance
	 *
	 * @param params
	 *
	 */
	public List<AgencyCountTypes> agencyReportingLocsInitWhenNewRecordInstance(final AgencyCountTypes paramBean) {
		final String sql = getQuery("OIMCOUNT_AGENCY_REPORTING_LOCS_INIT_WHENNEWRECORDINSTANCE");
		final RowMapper<AgencyCountTypes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyCountTypes.class,
				agencyCountTypesMapping);
		return namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * createFormGlobalsCREATE_FORM_GLOBALS
	 *
	 * @param params
	 *
	 */
	public List<OmsModules> createFormGlobals(final OmsModules paramBean) {
		final String sql = getQuery("OIMCOUNT_CREATE_FORM_GLOBALS_CREATE_FORM_GLOBALS");
		final RowMapper<OmsModules> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OmsModules.class,
				omsModulesMapping);
		return namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * processCopy
	 *
	 * @param params
	 *
	 */
	public List<AgencyReportingLocs> processCopy(final AgencyReportingLocs paramBean) {
		final String sql = getQuery("OIMCOUNT_PROCESS_COPY");
		final RowMapper<AgencyReportingLocs> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				AgencyReportingLocs.class, agencyReportingLocsMapping);
		return namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
	}

	@Override
	public Integer acctypeCheckboxChenged(final AgencyCountTypes bean) {
		final String sql = getQuery("OIMCOUNT_ACCOUNTTYPES_CHECKBOX_CHANGED_EVENT");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("COUNT_TYPE_ID", bean.getCountTypeId()),
				Integer.class);
	}

	@Override
	public Integer housPreInsertGetSeq(final AgencyReportingLocs bean) {
		final String sql = getQuery("OIMCOUNT_AGENCY_REPORTING_LOCS_HOUS_PREINSERT_GETMAXSEQ");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("COUNT_TYPE_ID", bean.getCountTypeId()),
				Integer.class);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgkeyDelrec
	 *
	 * @param params
	 *
	 */
	public List<LivingUnits> livingUnitsQuery(final String agylocId) {
		final String sql = getQuery("OIMCOUNT_LIVING_UNITS_QUERY");
		final RowMapper<LivingUnits> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, LivingUnits.class,
				livingUnitsMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("AGY_LOC_ID", agylocId), columnRowMapper);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgkeyDelrec
	 *
	 * @param params
	 *
	 */
	public List<LivingUnits> livingUnitsQueryOne(final String agylocId) {
		final String sql = getQuery("OIMCOUNT_LIVING_UNITS_QUERY_LOCATIONONE");
		final RowMapper<LivingUnits> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, LivingUnits.class,
				livingUnitsMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("AGY_LOC_ID", agylocId), columnRowMapper);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgkeyDelrec
	 *
	 * @param params
	 *
	 */
	public List<LivingUnits> livingUnitsQueryTwo(final String agyLocId, final String locCode) {
		List<LivingUnits> returnList = new ArrayList<>();
		final String sql = getQuery("OIMCOUNT_LIVING_UNITS_QUERY_LOCATIONTWO");
		final RowMapper<LivingUnits> mRowMapper = Row2BeanRowMapper.makeMapping(sql, LivingUnits.class,
				livingUnitsMapping);

		try {
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("AGYLOCID", agyLocId, "LOCATION1ID", locCode), mRowMapper);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return returnList;
	}

	@Override
	public Integer cgkeyDelrec(final List<Integer> countTypeId) {
		final String sql = getQuery("OIMCOUNT_CGKEY_DELREC");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("countTypeId", countTypeId), Integer.class);
	}

	@Override
	public Integer cgkeyDelrecAlc(final List<Integer> countTypeId) {
		final String sql = getQuery("OIMCOUNT_CGKEY_DELREC_L_CHECK_ALC_REC_CUR");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("counttypeid", countTypeId), Integer.class);
	}

	@Override
	public Integer cgkeyDelrecAc(final List<Integer> countTypeId) {
		final String sql = getQuery("OIMCOUNT_CGKEY_DELREC_L_CHECK_AC_REC_CUR");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("countTypeId", countTypeId), Integer.class);
	}

	@Override
	public Integer getCountTypeId() {
		final String sql = getQuery("OIMCOUNT_AGENCY_COUNT_TYPES_PREINSERTPRE_GETCOUNTTYPEID");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Integer.class);
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstAgencyCountTypes List<AgencyCountTypes>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public Integer agencyCountReportsCommit(final List<AgencyCountTypes> listObj) {
		final String sql = getQuery("OIMCOUNT_AGENCYCOUNTTYPES_INSERT_AGENCY_COUNT_TYPES_COUNTTYPEID");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final AgencyCountTypes agencyCountTypes : listObj) {
			parameters.add(new BeanPropertySqlParameterSource(agencyCountTypes));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (listObj.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstAgencyReportingLocs List<AgencyReportingLocs>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public Integer agencyReportingLocsInsert(final AgencyReportingLocs beanObj) {
		final String sql = getQuery("OIMCOUNT_AGENCYREPORTINGLOCSHOUS_INSERT_AGENCY_REPORTING_LOCS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		parameters.add(new BeanPropertySqlParameterSource(beanObj));
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (returnArray.length == 1) {
			return 1;
		} else {
			return 0;
		}

	}
	
	public String getLabelDescription(final String data) {
		final String sql = getQuery("OIMCOUNT_GET_LABEL_DESCRIPTION");
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
