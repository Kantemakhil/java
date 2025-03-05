package net.syscon.s4.inst.demographicsbiometrics.impl;

import java.sql.Types;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.LivingUnitProfile;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.LivingUnits;
import net.syscon.s4.im.beans.OmuavbedLivUnitsQuery;
import net.syscon.s4.im.beans.TempLivingUnitProfiles;
import net.syscon.s4.inst.demographicsbiometrics.OmuavbedRepository;
import oracle.jdbc.OracleTypes;

/**
 * Class OmuavbedRepositoryImpl
 */
@Repository
public class OmuavbedRepositoryImpl extends RepositoryBase implements OmuavbedRepository {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OmuavbedRepositoryImpl.class);

	private final Map<String, FieldMapper> tempLivingUnitProfilesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PROFILE_CODE", new FieldMapper("profileCode"))
			.put("CERTIFIED_FLAG", new FieldMapper("certifiedFlag"))
			.put("USER_DESC", new FieldMapper("userDesc"))
			.put("REACH_OPER_CAPACITY_FLAG", new FieldMapper("reachOperCapacityFlag"))
			.put("LIVING_UNIT_ID", new FieldMapper("livingUnitId"))
			.put("LEVEL_4_CODE", new FieldMapper("level4Code"))
			.put("DEACTIVATE_DATE", new FieldMapper("deactivateDate"))
			.put("SECURITY_LEVEL_CODE", new FieldMapper("securityLevelCode"))
			.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("LIVING_UNIT_CODE", new FieldMapper("livingUnitCode"))
			.put("PARENT_LIVING_UNIT_ID", new FieldMapper("parentLivingUnitId"))
			.put("CONTROL_ACTIVE_FLAG", new FieldMapper("controlActiveFlag"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("LOWEST_LEVEL_FLAG", new FieldMapper("lowestLevelFlag"))
			.put("LEVEL_2_CODE", new FieldMapper("level2Code"))
			.put("HOUSING_UNIT_TYPE", new FieldMapper("housingUnitType"))
			.put("DEACTIVATE_REASON_CODE", new FieldMapper("deactivateReasonCode"))
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("LEVEL_1_CODE", new FieldMapper("level1Code"))
			.put("COMMENT_TEXT", new FieldMapper("commentText"))
			.put("PROFILE_TYPE", new FieldMapper("profileType"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("OPERATION_CAPACITY", new FieldMapper("operationCapacity"))
			.put("CAPACITY", new FieldMapper("capacity"))
			.put("ACTIVE_FLAG", new FieldMapper("activeFlag"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("LIST_SEQ", new FieldMapper("listSeq"))
			.put("ACA_CAP_RATING", new FieldMapper("acaCapRating"))
			.put("LIVING_UNIT_TYPE", new FieldMapper("livingUnitType"))
			.put("CNA_NO", new FieldMapper("cnaNo"))
			.put("AGY_LOC_ID", new FieldMapper("agyLocId"))
			.put("REACTIVATE_DATE", new FieldMapper("reactivateDate"))
			.put("LEVEL_3_CODE", new FieldMapper("level3Code"))
			.put("NO_OF_OCCUPANT", new FieldMapper("noOfOccupant"))
			.build();
	private final Map<String, FieldMapper> livingUnitsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PROFILE_CODE", new FieldMapper("profileCode"))
			.put("CERTIFIED_FLAG", new FieldMapper("certifiedFlag"))
			.put("USER_DESC", new FieldMapper("userDesc"))
			.put("REACH_OPER_CAPACITY_FLAG", new FieldMapper("reachOperCapacityFlag"))
			.put("LIVING_UNIT_ID", new FieldMapper("livingUnitId"))
			.put("LEVEL_4_CODE", new FieldMapper("level4Code"))
			.put("DEACTIVATE_DATE", new FieldMapper("deactivateDate"))
			.put("SECURITY_LEVEL_CODE", new FieldMapper("securityLevelCode"))
			.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("LIVING_UNIT_CODE", new FieldMapper("livingUnitCode"))
			.put("PARENT_LIVING_UNIT_ID", new FieldMapper("parentLivingUnitId"))
			.put("CONTROL_ACTIVE_FLAG", new FieldMapper("controlActiveFlag"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("LOWEST_LEVEL_FLAG", new FieldMapper("lowestLevelFlag"))
			.put("LEVEL_2_CODE", new FieldMapper("level2Code"))
			.put("HOUSING_UNIT_TYPE", new FieldMapper("housingUnitType"))
			.put("DEACTIVATE_REASON_CODE", new FieldMapper("deactivateReasonCode"))
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("LEVEL_1_CODE", new FieldMapper("level1Code"))
			.put("COMMENT_TEXT", new FieldMapper("commentText"))
			.put("PROFILE_TYPE", new FieldMapper("profileType"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("OPERATION_CAPACITY", new FieldMapper("operationCapacity"))
			.put("CAPACITY", new FieldMapper("capacity"))
			.put("ACTIVE_FLAG", new FieldMapper("activeFlag"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("LIST_SEQ", new FieldMapper("listSeq"))
			.put("ACA_CAP_RATING", new FieldMapper("acaCapRating"))
			.put("LIVING_UNIT_TYPE", new FieldMapper("livingUnitType"))
			.put("CNA_NO", new FieldMapper("cnaNo"))
			.put("AGY_LOC_ID", new FieldMapper("agyLocId"))
			.put("REACTIVATE_DATE", new FieldMapper("reactivateDate"))
			.put("LEVEL_3_CODE", new FieldMapper("level3Code"))
			.put("NO_OF_OCCUPANT", new FieldMapper("noOfOccupant"))
			.build();
	private final Map<String, FieldMapper> agencyLocationsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("P_AGY_LOC_ID", new FieldMapper("pAgyLocId"))
			.put("HOUSING_LEV_4_CODE", new FieldMapper("housingLev4Code"))
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("HOUSING_LEV_1_CODE", new FieldMapper("housingLev1Code"))
			.put("HOUSING_LEV_3_CODE", new FieldMapper("housingLev3Code"))
			.put("HOUSING_LEV_2_CODE", new FieldMapper("housingLev2Code")).build();

	private final Map<String, FieldMapper> livingUnitProfilesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("LIVING_UNIT_ID", new FieldMapper("livingUnitId"))
			.put("PROFILE_ID", new FieldMapper("profileId"))
			.put("AGY_LOC_ID", new FieldMapper("agyLocId"))
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("INT_LOC_PROFILE_TYPE", new FieldMapper("intLocProfileType"))
			.put("INT_LOC_PROFILE_CODE", new FieldMapper("intLocProfileCode"))
			.build();


	/**
	 * Creates new OmuavbedRepositoryImpl class Object
	 */
	public OmuavbedRepositoryImpl() {
		// OmuavbedRepositoryImpl
	}

	/**
	 * Used to capture results from select query
	 *
	 * @return List<RecordGroup>
	 */
	public List<LivingUnits> rgLivingUnitPagyRecordGroup(final Long livingUnitId, final String level1Code) {
		final String sql = getQuery("OMUAVBED_FIND_RGLIVINGUNIT_PAGY");
		final RowMapper<LivingUnits> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql, LivingUnits.class,
				livingUnitsMapping);
		try {
			return namedParameterJdbcTemplate.query(sql,
					createParams("LEVEL1ID", livingUnitId, "AGY_LOC_ID", level1Code), referenceCodeRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 *
	 * @return List<RecordGroup>
	 */
	public List<LivingUnits> rgLivingUnitLocIdRecordGroup(final Integer livingUnitId, final String level2Code) {
		final String sql = getQuery("OMUAVBED_FIND_RGLIVINGUNIT_LOCID");
		final RowMapper<LivingUnits> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql, LivingUnits.class,
				livingUnitsMapping);
		try {
			return namedParameterJdbcTemplate.query(sql,
					createParams("LEVEL2ID", livingUnitId, "AGY_LOC_ID", level2Code), referenceCodeRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 *
	 * @return List<RecordGroup>
	 */
	public List<LivingUnits> rgLivingUnitLevelIdRecordGroup(final Integer livingUnitId, final String level3Code) {
		final String sql = getQuery("OMUAVBED_FIND_RGLIVINGUNIT_LEVELID");
		final RowMapper<LivingUnits> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql, LivingUnits.class,
				livingUnitsMapping);
		try {
			return namedParameterJdbcTemplate.query(sql,
					createParams("LEVEL3ID", livingUnitId, "AGY_LOC_ID", level3Code), referenceCodeRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 *
	 * @return List<LivingUnits>
	 */
	public List<LivingUnits> rgLivingUnitRecordGroup(final String agencyLocationId) {
		final String sql = getQuery("OMUAVBED_FIND_RGLIVINGUNIT");
		final RowMapper<LivingUnits> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql, LivingUnits.class,
				livingUnitsMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("AGY_LOC_ID", agencyLocationId),
					referenceCodeRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 *
	 * @return List<RecordGroup>
	 */
	public List<ReferenceCodes> rgLivingUnitTypeRecordGroup() {
		final String sql = getQuery("OMUAVBED_FIND_RGLIVINGUNITTYPE");
		final RowMapper<ReferenceCodes> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, livingUnitsMapping);
		List<ReferenceCodes> returnList = new ArrayList<>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), referenceCodeRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error(e);
			return Collections.emptyList();
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 *
	 * @return List<RecordGroup>
	 */
	public List<ReferenceCodes> rgUsedForRecordGroup() {
		final String sql = getQuery("OMUAVBED_FIND_RGUSEDFOR");
		final RowMapper<ReferenceCodes> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, livingUnitsMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), referenceCodeRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 *
	 * @return List<RecordGroup>
	 */
	public List<ReferenceCodes> rgAttributesRecordGroup() {
		final String sql = getQuery("OMUAVBED_FIND_RGATTRIBUTES");
		final RowMapper<ReferenceCodes> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, livingUnitsMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), referenceCodeRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            LivingUnits
	 *
	 * @return List<LivingUnits>
	 */
	public List<LivingUnits> livingUnitsTypeSearchLivingUnits(final LivingUnits objSearchDao) {
		final String sql = getQuery("OMUAVBED_LIVINGUNITSTYPE_FIND_LIVING_UNITS");
		final RowMapper<LivingUnits> LivingUnitsRowMapper = Row2BeanRowMapper.makeMapping(sql, LivingUnits.class,
				livingUnitsMapping);
		final ArrayList<LivingUnits> returnList = (ArrayList<LivingUnits>) namedParameterJdbcTemplate.query(sql,
				createParams(), LivingUnitsRowMapper);
		return returnList;
	}

	/**
	 * @param
	 *
	 * @
	 *
	 */
	public int PRE_INSERT() {
		return 0;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstLivingUnits
	 *            List<LivingUnits>
	 *
	 * @return List<Integer>
	 */
	public Integer livingUnitsTypeInsertLivingUnits(final List<LivingUnits> lstLivingUnits) {
		final String sql = getQuery("OMUAVBED_LIVINGUNITSTYPE_INSERT_LIVING_UNITS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (LivingUnits livingUnits : lstLivingUnits) {
			parameters.add(new BeanPropertySqlParameterSource(livingUnits));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstLivingUnits.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstLivingUnits
	 *            List<LivingUnits> @
	 */
	public Integer livingUnitsTypeUpdateLivingUnits(final List<LivingUnits> lstLivingUnits) {
		final String sql = getQuery("OMUAVBED_LIVINGUNITSTYPE_UPDATE_LIVING_UNITS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (LivingUnits livingUnits : lstLivingUnits) {
			parameters.add(new BeanPropertySqlParameterSource(livingUnits));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstLivingUnits.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            TempLivingUnitProfiles
	 *
	 * @return List<TempLivingUnitProfiles>
	 */
	public List<TempLivingUnitProfiles> livuprofuforExecuteQuery(final TempLivingUnitProfiles objSearchDao) {
		final String sql = getQuery("OMUAVBED_LIVUPROFUFOR_FIND_TEMP_LIVING_UNIT_PROFILES");
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource params = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" where ");
			if (objSearchDao.getProfileCode() != null) {
				sqlQuery.append(" PROFILE_CODE =:profileCode  " + " and ");
				params.addValue("profileCode", objSearchDao.getProfileCode());
			}
			if (objSearchDao.getProfileType() != null) {
				sqlQuery.append(" PROFILE_TYPE =:profileType  " + " and ");
				params.addValue("profileType", objSearchDao.getProfileType());
			}
			if (objSearchDao.getCreateUserId() != null) {
				sqlQuery.append(" CREATE_USER_ID =:createUserId  " + " and ");
				params.addValue("createUserId", objSearchDao.getCreateUserId());
			}
			if (objSearchDao.getCreateDatetime() != null) {
				sqlQuery.append(" CREATE_DATETIME =:createDatetime  " + " and ");
				params.addValue("createDatetime", objSearchDao.getCreateDatetime());
			}
			if (objSearchDao.getModifyUserId() != null) {
				sqlQuery.append(" MODIFY_USER_ID =:modifyUserId  " + " and ");
				params.addValue("modifyUserId", objSearchDao.getModifyUserId());
			}
			if (objSearchDao.getModifyDatetime() != null) {
				sqlQuery.append(" MODIFY_DATETIME =:modifyDatetime  " + " and ");
				params.addValue("modifyDatetime", objSearchDao.getModifyDatetime());
			}
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("where")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("and")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		final RowMapper<TempLivingUnitProfiles> TempLivingUnitProfilesRowMapper = Row2BeanRowMapper
				.makeMapping(preparedSql, TempLivingUnitProfiles.class, tempLivingUnitProfilesMapping);
		ArrayList<TempLivingUnitProfiles> returnList = new ArrayList<>();
		returnList = (ArrayList<TempLivingUnitProfiles>) namedParameterJdbcTemplate.query(preparedSql, params,
				TempLivingUnitProfilesRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstTempLivingUnitProfiles
	 *            List<TempLivingUnitProfiles>
	 *
	 * @return List<Integer>
	 */

	public Integer livuprofuforInsertTempLivingUnitProfiles(
			final List<TempLivingUnitProfiles> lstTempLivingUnitProfiles) {
		final String sql = getQuery("OMUAVBED_LIVUPROFUFOR_INSERT_TEMP_LIVING_UNIT_PROFILES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (TempLivingUnitProfiles tempLivingUnitProfiles : lstTempLivingUnitProfiles) {
			parameters.add(new BeanPropertySqlParameterSource(tempLivingUnitProfiles));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));

		if (lstTempLivingUnitProfiles.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstTempLivingUnitProfiles
	 *            List<TempLivingUnitProfiles>
	 */
	public Integer livuProfUforUpdateTempLivingUnitProfiles(
			final List<TempLivingUnitProfiles> lstTempLivingUnitProfiles) {
		final String sql = getQuery("OMUAVBED_LIVUPROFUFOR_UPDATE_TEMP_LIVING_UNIT_PROFILES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (TempLivingUnitProfiles tempLivingUnitProfiles : lstTempLivingUnitProfiles) {
			parameters.add(new BeanPropertySqlParameterSource(tempLivingUnitProfiles));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstTempLivingUnitProfiles.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstTempLivingUnitProfiles
	 *            List<TempLivingUnitProfiles>
	 */
	public Integer livuProfUforDeleteTempLivingUnitProfiles(
			final List<TempLivingUnitProfiles> lstTempLivingUnitProfiles) {
		final String sql = getQuery("OMUAVBED_LIVUPROFUFOR_DELETE_TEMP_LIVING_UNIT_PROFILES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (TempLivingUnitProfiles tempLivingUnitProfiles : lstTempLivingUnitProfiles) {
			parameters.add(new BeanPropertySqlParameterSource(tempLivingUnitProfiles));
		}
		try {
			String tableName = "TEMP_LIVING_UNIT_PROFILES";
			String whereClause = null;
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method livuProfUforDeleteTempLivingUnitProfiles", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));

		if (lstTempLivingUnitProfiles.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            OmuavbedLivUnitsQuery
	 *
	 * @return List<OmuavbedLivUnitsQuery>
	 */
	// OMUAVBED_LIVINGUNITSTYPE_FIND_LIVING_UNITS
	public List<OmuavbedLivUnitsQuery> livingunitsExecuteQuery(final OmuavbedLivUnitsQuery objSearchDao) {
		Map<String, Object> returnObject = null;
		final List<OmuavbedLivUnitsQuery> lListObj = new ArrayList<OmuavbedLivUnitsQuery>();
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[20];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_OFFENDER_BOOK_ID", Types.NUMERIC),
				new SqlParameter("P_OFFENDER_ID", Types.NUMERIC), new SqlParameter("P_CASELOAD_ID", Types.VARCHAR),
				new SqlParameter("P_AGY_LOC_ID", Types.VARCHAR), new SqlParameter("P_LIVING_UNIT_TYPE", Types.VARCHAR),
				new SqlParameter("P_LEVEL_1_CODE", Types.VARCHAR), new SqlParameter("P_LEVEL_2_CODE", Types.VARCHAR),
				new SqlParameter("P_LEVEL_3_CODE", Types.VARCHAR), new SqlParameter("P_LEVEL_4_CODE", Types.VARCHAR),
				new SqlOutParameter("resultset", OracleTypes.CURSOR) };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("OMUAVBED").withProcedureName("LIV_UNITS_QUERY").declareParameters(sqlParameters);
		inParamMap.put("P_OFFENDER_BOOK_ID", objSearchDao.getpOffenderBookId());
		inParamMap.put("P_OFFENDER_ID", objSearchDao.getpOffenderId());
		inParamMap.put("P_CASELOAD_ID", objSearchDao.getpCaseloadId());
		inParamMap.put("P_AGY_LOC_ID", objSearchDao.getpAgyLocId());
		inParamMap.put("P_LIVING_UNIT_TYPE", objSearchDao.getpLivingUnitType());
		inParamMap.put("P_LEVEL_1_CODE", objSearchDao.getpLevel1Code());
		inParamMap.put("P_LEVEL_2_CODE", objSearchDao.getpLevel2Code());
		inParamMap.put("P_LEVEL_3_CODE", objSearchDao.getpLevel3Code());
		inParamMap.put("P_LEVEL_4_CODE", objSearchDao.getpLevel4Code());
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = (Map<String, Object>) simpleJDBCCall.withoutProcedureColumnMetaDataAccess()
					.execute(inParameter);
			final List<Map<String, String>> list = (List<Map<String, String>>) returnObject.get("resultset");
			for (int i = 0; i < list.size(); i++) {
				final Map<String, String> childMap = list.get(i);
				final OmuavbedLivUnitsQuery bean = new OmuavbedLivUnitsQuery();
				bean.setLivingUnitId(Long.parseLong(String.valueOf(childMap.get("LIVING_UNIT_ID"))));
				bean.setDescription(childMap.get("DESCRIPTION"));
				bean.setCapacity(Long.parseLong(String.valueOf(childMap.get("CAPACITY"))));
				bean.setUnitAtCapacity(childMap.get("UNIT_AT_CAPACITY"));
				bean.setNoOfOccupant(Long.parseLong(String.valueOf(childMap.get("NO_OF_OCCUPANT"))));
				bean.setNoOfAvailable(Long.parseLong(String.valueOf(childMap.get("NO_OF_AVAILABLE"))));
				bean.setPrisonerConflict(childMap.get("PRISONER_CONFLICT"));
				bean.setSecurityConflict(childMap.get("SECURITY_CONFLICT"));
				bean.setCellSharingConflict(childMap.get("CELL_SHARING_CONFLICT"));
				lListObj.add(bean);
			}
		} catch (Exception e) {
			logger.error("", e);
		}
		return lListObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 *
	 * omuavbedOmuavbedWhenNewFormInstancelevelCur
	 *
	 * @param params
	 */
	public List<AgencyLocations> whenNewFormInstancelevelCur(final String pAgyLocId) {
		final String sql = getQuery("OMUAVBED_WHENNEWFORMINSTANCE_LEVEL_CUR");
		final RowMapper<AgencyLocations> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				agencyLocationsMapping);
		List<AgencyLocations> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(sql, createParams("pAgyLocId", pAgyLocId), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 *
	 * livingUnitProfilesHouUsedFor
	 *
	 * @param pAgyLocId.
	 *            usedFor
	 */
	public List<String> livingUnitProfilesHouUsedFor(final String pAgyLocId, final String usedFor, final String profileTypeOne) {
		final String sql = getQuery("OMUAVBED_FIND_LIVING_UNIT_PROFILES_HOU_USED_FOR");
		List<String> returnList = new ArrayList<>();
		try {
			returnList = namedParameterJdbcTemplate.queryForList(sql,
					createParams("agyLocId", pAgyLocId, "usedFor", usedFor, "usedForOne", profileTypeOne), String.class);
		} catch (Exception e) {
			return Collections.emptyList();
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 *
	 * livingUnitProfilesHouUnitAtt
	 *
	 * @param pAgyLocId.
	 *            attribute
	 */
	public List<String> livingUnitProfilesHouUnitAtt(final String pAgyLocId, final String attribute, final String attributeOne) {
		final String sql = getQuery("OMUAVBED_FIND_LIVING_UNIT_PROFILES_HOU_UNIT_ATT");
		List<String> returnList = new ArrayList<>();
		try {
			returnList = namedParameterJdbcTemplate.queryForList(sql,
					createParams("agyLocId", pAgyLocId, "attribute", attribute, "attributeOne", attributeOne), String.class);
		} catch (Exception e) {
			return Collections.emptyList();
		}
		return returnList;
	}


	/**
	 * This method is return all LIVING_UNIT_PROFILES based on LIVING_UNIT_ID
	 *
	 * livingUnitProfilesByLivingUnitId
	 *
	 * @param plivingUnitId
	 *
	 */
	public List<LivingUnitProfile> livingUnitProfilesByLivingUnitId(final String plivingUnitId) {
		final String sql = getQuery("OMUAVBED_FIND_LIVING_UNIT_PROFILES_LIVING_UNIT_ID");
		List<LivingUnitProfile> returnList = new ArrayList<>();
		try {



			final RowMapper<LivingUnitProfile> livingUnitProfileRowMapper = Row2BeanRowMapper.makeMapping(
					sql, LivingUnitProfile.class, livingUnitProfilesMapping);

			MapSqlParameterSource params = new MapSqlParameterSource();
			params.addValue("livingUnitId", plivingUnitId);

			returnList = namedParameterJdbcTemplate.query(sql,params ,livingUnitProfileRowMapper);
		} catch (Exception e) {
			return Collections.emptyList();
		}
		return returnList;
	}

	public AgencyLocations gettingLabels(final String agyLocId) {
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		AgencyLocations result = new AgencyLocations();
		SqlParameter[] sqlParameters = new SqlParameter[10];
		Map<String, Object> returnObject = null;
		sqlParameters = new SqlParameter[] { new SqlParameter("p_agy_loc_id", OracleTypes.VARCHAR),
				new SqlOutParameter("p_level_1_code", OracleTypes.VARCHAR),
				new SqlOutParameter("p_level_2_code", OracleTypes.VARCHAR),
				new SqlOutParameter("p_level_3_code", OracleTypes.VARCHAR),
				new SqlOutParameter("p_level_4_code", OracleTypes.VARCHAR) };
		final SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_ESTABLISHMENT").withProcedureName("GET_HOUSING_LABELS").declareParameters(sqlParameters);
		inParamMap.put("p_agy_loc_id", agyLocId);
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			result.setHousingLev1Code(String.valueOf(returnObject.get("p_level_1_code")));
			result.setHousingLev2Code(String.valueOf(returnObject.get("p_level_2_code")));
			result.setHousingLev3Code(String.valueOf(returnObject.get("p_level_3_code")));
			result.setHousingLev4Code(String.valueOf(returnObject.get("p_level_4_code")));
		} catch (Exception e) {
			return result;
		}
		return result;
	}
	
	public String getLabelDescription(final String pAgyLocId) {
		final String sql = getQuery("OMUAVBED_FIND_GETDESCCODE");
		String returnList = null;
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("BLOCK", pAgyLocId), String.class);
		} catch (Exception e) {
			return returnList;
		}
		return returnList;
	}
}
