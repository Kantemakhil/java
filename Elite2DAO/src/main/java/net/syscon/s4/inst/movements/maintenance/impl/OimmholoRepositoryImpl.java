package net.syscon.s4.inst.movements.maintenance.impl;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.beans.AgencyInternalLocations;
import net.syscon.s4.common.beans.AgyIntLocProfiles;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.LivingUnitProfiles;
import net.syscon.s4.im.beans.LivingUnits;
import net.syscon.s4.inst.classification.beans.AssessmentResults;
import net.syscon.s4.inst.movements.maintenance.OimmholoRepository;
import net.syscon.s4.inst.visitsmanagement.beans.IepLevelBean;
import oracle.jdbc.OracleTypes;

/**
 * Class OimmholoRepositoryImpl
 */
@Repository
public class OimmholoRepositoryImpl extends RepositoryBase implements OimmholoRepository {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OimmholoRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> agyIntLocProf = new ImmutableMap.Builder<String, FieldMapper>()
			.put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.put("AGY_LOC_ID", new FieldMapper("agyLocId"))
			.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("INTERNAL_LOCATION_ID", new FieldMapper("internalLocationId"))
			.put("INT_LOC_PROFILE_TYPE", new FieldMapper("intLocProfileType"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("LIVING_UNIT_ID", new FieldMapper("livingUnitId"))
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("ROWID", new FieldMapper("rowid")).build();
	private final Map<String, FieldMapper> livingUnitProf = new ImmutableMap.Builder<String, FieldMapper>()
			.put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.put("AGY_LOC_ID", new FieldMapper("agyLocId"))
			.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("INTERNAL_LOCATION_ID", new FieldMapper("internalLocationId"))
			.put("INT_LOC_PROFILE_TYPE", new FieldMapper("intLocProfileType"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("LIVING_UNIT_ID", new FieldMapper("livingUnitId"))
			.put("DESCRIPTION", new FieldMapper("description"))
			.build();
	private final Map<String, FieldMapper> livUnitsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CERTIFIED_FLAG", new FieldMapper("certifiedFlag"))
			.put("USER_DESC", new FieldMapper("userDesc"))
			.put("REACH_OPER_CAPACITY_FLAG", new FieldMapper("reachOperCapacityFlag"))
			.put("LIVING_UNIT_ID", new FieldMapper("livingUnitId"))
			.put("LEVEL_4_CODE", new FieldMapper("level4Code"))
			.put("DEACTIVATE_DATE", new FieldMapper("deactivateDate"))
			.put("SECURITY_LEVEL_CODE", new FieldMapper("securityLevelCode"))
			.put("LIVING_UNIT_CODE", new FieldMapper("livingUnitCode"))
			.put("PARENT_LIVING_UNIT_ID", new FieldMapper("parentLivingUnitId"))
			.put("CONTROL_ACTIVE_FLAG", new FieldMapper("controlActiveFlag"))
			.put("LOWEST_LEVEL_FLAG", new FieldMapper("lowestLevelFlag"))
			.put("LEVEL_2_CODE", new FieldMapper("level2Code"))
			.put("HOUSING_UNIT_TYPE", new FieldMapper("housingUnitType"))
			.put("DEACTIVATE_REASON_CODE", new FieldMapper("deactivateReasonCode"))
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("LEVEL_1_CODE", new FieldMapper("level1Code"))
			.put("COMMENT_TEXT", new FieldMapper("commentText"))
			.put("OPERATION_CAPACITY", new FieldMapper("operationCapacity"))
			.put("CAPACITY", new FieldMapper("capacity"))
			.put("ACTIVE_FLAG", new FieldMapper("activeFlag"))
			.put("LIST_SEQ", new FieldMapper("listSeq"))
			.put("ACA_CAP_RATING", new FieldMapper("acaCapRating"))
			.put("LIVING_UNIT_TYPE", new FieldMapper("livingUnitType"))
			.put("CNA_NO", new FieldMapper("cnaNo"))
			.put("AGY_LOC_ID", new FieldMapper("agyLocId"))
			.put("REACTIVATE_DATE", new FieldMapper("reactivateDate"))
			.put("LEVEL_3_CODE", new FieldMapper("level3Code"))
			.put("NO_OF_OCCUPANT", new FieldMapper("noOfOccupant"))
			.build();
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("GETDESCCODE", new FieldMapper("getdesccode"))
			.put("SUPERVISION_LEVEL_TYPE", new FieldMapper("supervisionLevelType"))
			.put("CODE", new FieldMapper("code"))
			.put("LIST_SEQ", new FieldMapper("listSeq"))
			.put("ACTIVE_FLAG", new FieldMapper("activeFlag"))
			.build();
	private final Map<String, FieldMapper> agyLocMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description"))
			.build();

	private final Map<String, FieldMapper> agyIntLocMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("INTERNAL_LOCATION_ID", new FieldMapper("internalLocationId"))
			.put("CREATE_DATETIME", new FieldMapper("createDateTime"))
			.put("OPERATION_CAPACITY", new FieldMapper("operationCapacity"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDateTime")).build();
	/**
	 * Creates new OimmholoRepositoryImpl class Object
	 */
	public OimmholoRepositoryImpl() {
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            LivingUnits
	 *
	 * @return List<LivingUnits>
	 */
	public List<LivingUnits> livUnitsExecuteQuery(final LivingUnits objSearchDao) {
		final String sql = getQuery("OIMMHOLO_LIVUNITS_FIND_LIVING_UNITS");
		final RowMapper<LivingUnits> livUnitsRowMapper = Row2BeanRowMapper.makeMapping(sql, LivingUnits.class,
				livUnitsMapping);
		List<LivingUnits> returnList = namedParameterJdbcTemplate.query(sql,
				createParams("AGYLOCID", objSearchDao.getAgyLocId()), livUnitsRowMapper);
		return returnList;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            LivingUnits
	 *
	 * @return List<LivingUnits>
	 */
	public List<LivingUnits> livUnitsDialogExecuteQuery(final LivingUnits objSearchDao) {
		final String sql = getQuery("OIMMHOLO_LIVUNITS_FIND_LIVING_UNITS_ONE");
		final RowMapper<LivingUnits> livUnitsRowMapper = Row2BeanRowMapper.makeMapping(sql, LivingUnits.class,
				livUnitsMapping);
		List<LivingUnits> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(sql,
				createParams("LIVINGUNITID", objSearchDao.getParentLivingUnitId()), livUnitsRowMapper);
		return returnList;
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
	public String livUnitsInsertLivingUnits(final List<LivingUnits> lstLivingUnits) {
		final String sql = getQuery("OIMMHOLO_LIVUNITS_INSERT_LIVING_UNITS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final LivingUnits livingUnits : lstLivingUnits) {
			parameters.add(new BeanPropertySqlParameterSource(livingUnits));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			String error = "Error : " + e.getMessage();
			if (error!=null && error.contains("constraint")) {
				String conName = error.substring(error.indexOf("constraint"), error.indexOf("Detail"))
						.replaceFirst("constraint", "").trim();
				return conName!=null && !conName.isEmpty() && conName.length()>0 ?conName.substring(1, conName.length() - 1):"";
			}
		}
		if (lstLivingUnits.size() == returnArray.length) {
			return "1";
		} else {
			return null;
		}

	}
	public String getTableName(final String errorName) {
		final String sql = getQuery("OIMMHOLO_CONSTRAINT_VALIDATIONS");
		List<String> returnData = null;
		try {
			returnData =  namedParameterJdbcTemplate.queryForList(sql, createParams("P_CONSTRAINT_NAME", errorName),
					String.class);
		} catch (Exception e) {
			return null;
		}
		return returnData.get(0);
	}
	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstLivingUnits
	 *            List<LivingUnits>
	 */
	public String livUnitsUpdateLivingUnits(final List<LivingUnits> lstLivingUnits) {
		final String sql = getQuery("OIMMHOLO_LIVUNITS_UPDATE_LIVING_UNITS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final LivingUnits livingUnits : lstLivingUnits) {
			parameters.add(new BeanPropertySqlParameterSource(livingUnits));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstLivingUnits.size() == returnArray.length) {
			return "1";
		} else {
			return null;
		}

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            AgyIntLocProfiles
	 *
	 * @return List<AgyIntLocProfiles>
	 */
	public List<AgyIntLocProfiles> usedForExecuteQuery(final AgyIntLocProfiles objSearchDao) {
		final String sql = getQuery("OIMMHOLO_USEDFOR_FIND_AGY_INT_LOC_PROFILES");
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource params = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" WHERE ");
			if (objSearchDao.getInternalLocationId() != null) {
				sqlQuery.append(" INTERNAL_LOCATION_ID = :internalLocationId " + " AND ");
				params.addValue("internalLocationId", objSearchDao.getInternalLocationId());
			}
			if ("UNITFOR".equals(objSearchDao.getSealFlag())) {
				sqlQuery.append(" INT_LOC_PROFILE_TYPE = 'HOU_USED_FOR' ");
			} else if ("UNITATTR".equals(objSearchDao.getSealFlag())) {
				sqlQuery.append(" INT_LOC_PROFILE_TYPE = 'HOU_UNIT_ATT' ");
			} else if ("NONASSOTYP".equals(objSearchDao.getSealFlag())) {
				sqlQuery.append(" INT_LOC_PROFILE_TYPE = 'NON_ASSO_TYP' ");
			} else if ("SUPLVLTYPE".equals(objSearchDao.getSealFlag())) {
				sqlQuery.append(" INT_LOC_PROFILE_TYPE = 'SUP_LVL_TYPE' ");
			}
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("WHERE")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("AND")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		if ("UNITFOR".equals(objSearchDao.getSealFlag())) {
			preparedSql = preparedSql.concat(" ORDER BY INT_LOC_PROFILE_CODE ");
		}
		final RowMapper<AgyIntLocProfiles> agyIntRowMapper = Row2BeanRowMapper.makeMapping(preparedSql,
				AgyIntLocProfiles.class, agyIntLocProf);
		List<AgyIntLocProfiles> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(preparedSql, params, agyIntRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstAgyIntLocProfiles
	 *            List<AgyIntLocProfiles>
	 *
	 * @return List<Integer>
	 */
	public Integer usedForInsertAgyIntLocProfiles(final List<AgyIntLocProfiles> lstAgyIntLocProfiles) {
		final String sql = getQuery("OIMMHOLO_USEDFOR_INSERT_AGY_INT_LOC_PROFILES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final AgyIntLocProfiles agyIntLocProfiles : lstAgyIntLocProfiles) {
			parameters.add(new BeanPropertySqlParameterSource(agyIntLocProfiles));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstAgyIntLocProfiles.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstAgyIntLocProfiles
	 *            List<AgyIntLocProfiles>
	 */
	public Integer usedForUpdateAgyIntLocProfiles(final List<AgyIntLocProfiles> lstAgyIntLocProfiles) {
		final String sql = getQuery("OIMMHOLO_USEDFOR_UPDATE_AGY_INT_LOC_PROFILES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final AgyIntLocProfiles agyIntLocProfiles : lstAgyIntLocProfiles) {
			parameters.add(new BeanPropertySqlParameterSource(agyIntLocProfiles));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstAgyIntLocProfiles.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstAgyIntLocProfiles
	 *            List<AgyIntLocProfiles>
	 */
	public Integer usedForDeleteAgyIntLocProfiles(final List<AgyIntLocProfiles> lstAgyIntLocProfiles) {
		final String sql = getQuery("OIMMHOLO_USEDFOR_DELETE_AGY_INT_LOC_PROFILES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final AgyIntLocProfiles agyIntLocProfiles : lstAgyIntLocProfiles) {
			parameters.add(new BeanPropertySqlParameterSource(agyIntLocProfiles));
		}
		try {
			batchUpdatePreDeletedRows("AGY_INT_LOC_PROFILES", "INTERNAL_LOCATION_ID  = :internalLocationId AND INT_LOC_PROFILE_TYPE  = :intLocProfileType AND INT_LOC_PROFILE_CODE  = :intLocProfileCode", parameters);
		} catch (Exception e) {
			logger.error("batchUpdatePreDeletedRows in usedForDeleteAgyIntLocProfiles"+e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstAgyIntLocProfiles.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            LivingUnitProfiles
	 *
	 * @return List<LivingUnitProfiles>
	 */
	public List<LivingUnitProfiles> luProfExecuteQuery(final LivingUnitProfiles objSearchDao) {
		final String sql = getQuery("OIMMHOLO_LUPROF_FIND_LIVING_UNIT_PROFILES");
		final RowMapper<LivingUnitProfiles> livUnitRowMapper = Row2BeanRowMapper.makeMapping(sql,
				LivingUnitProfiles.class, livingUnitProf);
		List<LivingUnitProfiles> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(sql, createParams(), livUnitRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstLivingUnitProfiles
	 *            List<LivingUnitProfiles>
	 *
	 * @return List<Integer>
	 */
	public Integer luProfInsertLivingUnitProfiles(final List<LivingUnitProfiles> lstLivingUnitProfiles) {
		final String sql = getQuery("OIMMHOLO_LUPROF_INSERT_LIVING_UNIT_PROFILES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final LivingUnitProfiles livingUnitProfiles : lstLivingUnitProfiles) {
			parameters.add(new BeanPropertySqlParameterSource(livingUnitProfiles));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstLivingUnitProfiles.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstLivingUnitProfiles
	 *            List<LivingUnitProfiles>
	 */
	public Integer luProfUpdateLivingUnitProfiles(final List<LivingUnitProfiles> lstLivingUnitProfiles) {
		final String sql = getQuery("OIMMHOLO_LUPROF_UPDATE_LIVING_UNIT_PROFILES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final LivingUnitProfiles livingUnitProfiles : lstLivingUnitProfiles) {
			parameters.add(new BeanPropertySqlParameterSource(livingUnitProfiles));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstLivingUnitProfiles.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstLivingUnitProfiles
	 *            List<LivingUnitProfiles>
	 */
	public Integer luProfDeleteLivingUnitProfiles(final List<LivingUnitProfiles> lstLivingUnitProfiles) {
		final String sql = getQuery("OIMMHOLO_LUPROF_DELETE_LIVING_UNIT_PROFILES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final LivingUnitProfiles livingUnitProfiles : lstLivingUnitProfiles) {
			parameters.add(new BeanPropertySqlParameterSource(livingUnitProfiles));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstLivingUnitProfiles.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<AgencyLocations>
	 */
	public List<AgencyLocations> rgAgyLocLovRecordGroup(String userName) {
		final String sql = getQuery("OIMMHOLO_FIND_RGAGYLOCLOV");
		final RowMapper<AgencyLocations> mRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams("userId",userName), mRowMapper);
		} catch (Exception e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgDeactLuRsnRecordGroup() {
		final String sql = getQuery("OIMMHOLO_FIND_RGDEACTLURSN");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (Exception e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgUsedForRecordGroup() {
		final String sql = getQuery("OIMMHOLO_FIND_RGUSEDFOR");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (Exception e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgHouUnitAttRecordGroup() {
		final String sql = getQuery("OIMMHOLO_FIND_RGHOUUNITATT");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (Exception e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgNonAssoTypeRecordGroup() {
		final String sql = getQuery("OIMMHOLO_FIND_RGNONASSOTYPE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (Exception e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<AssessmentResults>
	 */
	public List<AssessmentResults> rgSupLvlTypeRecordGroup() {
		final String sql = getQuery("OIMMHOLO_FIND_RGSUPLVLTYPE");
		final RowMapper<AssessmentResults> mRowMapper = Row2BeanRowMapper.makeMapping(sql, AssessmentResults.class,
				mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (Exception e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgHouUnTypeRecordGroup() {
		final String sql = getQuery("OIMMHOLO_FIND_RGHOUUNTYPE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (Exception e) {
			return Collections.emptyList();
		}
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * defaultAgyrsn
	 *
	 * @param params
	 *
	 */
	public AgencyLocations defaultAgyrsn(final AgencyLocations paramBean) {
		final String sql = getQuery("OIMMHOLO_DEFAULT_AGYRSN");
		final RowMapper<AgencyLocations> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				agyLocMapping);
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * defaultAgyrsn
	 *
	 * @param params
	 *
	 */
	public AgencyLocations defaultAgyrsnOne(final AgencyLocations paramBean) {
		final String sql = getQuery("OIMMHOLO_DEFAULT_AGYRSN");
		final RowMapper<AgencyLocations> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				agyLocMapping);
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
	}

	public String cellBlockData(final LivingUnits searchdao) {
		String value = "";
		try {
			final Map<String, Object> inParamMap = new HashMap<String, Object>();
			SqlParameter[] sqlParameters = new SqlParameter[2];
			sqlParameters = new SqlParameter[] { new SqlParameter("P_LEVEL", OracleTypes.NUMBER),
					new SqlParameter("P_AGY_LOC_ID", OracleTypes.VARCHAR),
					new SqlOutParameter("RETURN_VALUE", OracleTypes.VARCHAR) };
			SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
					.withCatalogName("OIMMHOLO").withFunctionName("GET_NEW_LU_TYPE").declareParameters(sqlParameters);
			inParamMap.put("P_LEVEL", searchdao.getCapacity());
			inParamMap.put("P_AGY_LOC_ID", searchdao.getAgyLocId());
			final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
			value = simpleJDBCCall.executeFunction(String.class, inParameter);
		} catch (Exception e) {
			logger.error("Exception : cellBlockData:", e);
			value = null;
		}
		return value;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @return Date
	 * @Param paramBean
	 */
	@Override
	public String getCellDescription(final String cellData) {
		final String sql = getQuery("OIMMHOLO_CELL_BLOCK_DATA");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("CELLBLOCK", cellData), String.class);
	}

	public BigDecimal getLivingUnitId() {
		final String sql = getQuery("OIMMHOLO_GET_NEXT_LU_ID");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), BigDecimal.class);
	}

	public Long getCurrentLevel(final BigDecimal livingUintId) {
		final String sql = getQuery("OIMMHOLO_GET_CURRENT_LEVEL");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("LIVINGUNITID", livingUintId), Long.class);
	}

	public String getNewLuType(final Long currentLevel, final String agyLocId) {
		final String sql = getQuery("OIMMHOLO_GET_NEW_LU_TYPE");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("p_level", currentLevel, "AGYLOCID", agyLocId), String.class);
	}

	public Integer updateParentCapAndCna(final BigDecimal livingUnitId) {
		Integer openAnAccount = null;
		Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("P_LIVING_UNIT_ID", livingUnitId);
		try {
			namedParameterJdbcTemplate.update("call OMS_OWNER.OIMMHOLO.UPDATE_PARENT_CAP_AND_CNA(:P_LIVING_UNIT_ID)",
					inParamMap);
			openAnAccount = 1;
		} catch (Exception e) {
			openAnAccount = 0;
			return openAnAccount;
		}
		return openAnAccount;
	}

	public Integer actDeactChildLu(final LivingUnits object) {
		Integer openAnAccount = null;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("P_PARENT_LU_ID", object.getLivingUnitId());
		inParamMap.put("P_ACTIVE_FLAG", object.getActiveFlag());
		if("Y".equals(object.getActiveFlag()) && !"UPDATE".equals(object.getCode())) {
			inParamMap.put("P_REASON_CODE", object.getDeactivateReasonCodeTemp());
		} else {			
			inParamMap.put("P_REASON_CODE", object.getDeactivateReasonCode());
		}
		try {
			namedParameterJdbcTemplate.update(
					"call OMS_OWNER.OIMMHOLO.ACT_DEACT_CHILD_LU(:P_PARENT_LU_ID, :P_ACTIVE_FLAG, :P_REASON_CODE)",
					inParamMap);
			openAnAccount = 1;
		} catch (Exception e) {
			openAnAccount = 0;
			return openAnAccount;
		}
		return openAnAccount;
	}

	public LivingUnits getResDescValues(final LivingUnits objSearchDao) {
		Map<String, Object> returnObject = null;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[3];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_PARENT_LU_ID", Types.NUMERIC),
				new SqlParameter("P_AGY_LOC_ID", Types.VARCHAR), new SqlParameter("P_LIVING_UNIT_CODE", Types.VARCHAR),
				new SqlOutParameter("P_DESC", Types.VARCHAR) };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("OIMMHOLO").withFunctionName("DEFAULT_LIVING_UNIT_DESC")
				.declareParameters(sqlParameters);
		inParamMap.put("P_PARENT_LU_ID", objSearchDao.getLivingUnitId());
		inParamMap.put("P_AGY_LOC_ID", objSearchDao.getAgyLocId());
		inParamMap.put("P_LIVING_UNIT_CODE", objSearchDao.getLivingUnitCode());
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			objSearchDao.setDescription((String) returnObject.get("P_DESC"));
		} catch (Exception e) {
			logger.error("Exception", e);
		}
		return objSearchDao;
	}

	public Long getActiveFlagValidation(final Integer livingUintId) {
		final String sql = getQuery("OIMMHOLO_ACTIVE_FLAG_VALIDATION");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("LIVINGUNITID", livingUintId), Long.class);
	}

	public Long getFlagValidation(final Integer livingUintId) {
		final String sql = getQuery("OIMMHOLO_NO_OF_OCCUPANT");
		Long returnValue = null;
		try {
			returnValue = namedParameterJdbcTemplate.queryForObject(sql, createParams("LIVINGUNITID", livingUintId),
					Long.class);
			if (returnValue == null) {
				returnValue = 0L;
				return returnValue;
			}
		} catch (Exception e) {
			if (returnValue == null) {
				returnValue = 0L;
				return returnValue;
			}
		}
		return returnValue;
	}

	public Long checkInheritAttributes(final Integer livingUintId) {
		final String sql = getQuery("OIMMHOLO_LIVING_UNIT_PROFILES_EDITABLE");
		Long returnValue = null;
		try {
			returnValue = namedParameterJdbcTemplate.queryForObject(sql, createParams("LIVINGUNITID", livingUintId),
					Long.class);
		} catch (Exception e) {
			if (returnValue == null) {
				returnValue = 0L;
				return returnValue;
			}
		}
		return returnValue;
	}

	public List<LivingUnitProfiles> attributesData(final Integer livingUintId, final String attributes) {
		final String sql = getQuery("OIMMHOLO_OMS_MISCELLANEOUS_P_REF_DOMAIN");
		final RowMapper<LivingUnitProfiles> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				LivingUnitProfiles.class, livingUnitProf);
		List<LivingUnitProfiles> returnValue = new ArrayList<>();
		try {
			returnValue = namedParameterJdbcTemplate.query(sql,
					createParams("LIVINGUNITID", livingUintId, "REFDOMAIN", attributes), columnRowMapper);
		} catch (Exception e) {
			return returnValue;
		}
		return returnValue;
	}

	public Integer butChangeEvent(final LivingUnits object) {
		Integer openAnAccount = null;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("P_LU_ID", object.getLivingUnitId());
		inParamMap.put("P_PARENT_LU_ID", object.getParentLivingUnitId());
		try {
			namedParameterJdbcTemplate.update(
					"call OMS_OWNER.OIMMHOLO.INSERT_CHILD_ALL_LU_PROFILES(:P_LU_ID, :P_PARENT_LU_ID)", inParamMap);
			openAnAccount = 1;
		} catch (Exception e) {
			openAnAccount = 0;
			return openAnAccount;
		}
		return openAnAccount;
	}

	public void gettingAgyintlocprofilest1(final Integer agyIntLocAmendmentId) {
		final String sql = getQuery("GETTING_AGYINTLOCPROFILEST1");
		namedParameterJdbcTemplate.queryForObject(sql, createParams("AGY_INT_LOC_AMENDMENT_ID", agyIntLocAmendmentId),
				Void.class);
	}

	@Override
	public List<AgencyInternalLocations> gettingOldData(Integer internalLocationId) {
		final String sql = getQuery("OIMMHOLO_GETTING_OLD_DATA");
		final RowMapper<AgencyInternalLocations> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				AgencyInternalLocations.class, agyIntLocMapping);
		List<AgencyInternalLocations> agyIntloc=new ArrayList<AgencyInternalLocations>();
		try {
			agyIntloc=namedParameterJdbcTemplate.query(sql, createParams("internalLocationId",internalLocationId),columnRowMapper);
		}catch (Exception e) {
			logger.error("gettingOldData", e);
		}
		return agyIntloc;
	}


	@Override

	public LivingUnits getLivingUnitDetails(Integer livingUintId) {
		final String sql = getQuery("OIMMHOLO_LIVUNITS_FIND");
		final RowMapper<LivingUnits> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, LivingUnits.class,
				livUnitsMapping);
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("livingUnitId",livingUintId),
				columnRowMapper);
	}

	@Override
	public List<AgyIntLocProfiles> gettingOldDataFromAgyIntLocAmendments(Integer row_id) {
		final String sql = getQuery("OIMMHOLO_GETTING_OLD_DATA_AGY_INT_LOC_AMENDMENTS");
		final RowMapper<AgyIntLocProfiles> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				AgyIntLocProfiles.class, agyIntLocMapping);
		List<AgyIntLocProfiles> agyIntloc=new ArrayList<AgyIntLocProfiles>();
		try {
			agyIntloc=namedParameterJdbcTemplate.query(sql, createParams("row_id",row_id),columnRowMapper);
		}catch (Exception e) {
			logger.error("gettingOldDataFromAgyIntLocAmendments", e);
		}
		return agyIntloc;
	}





	public AgencyInternalLocations getOldDataAgencyInternalLocation(Long internalLocId) {
		final String sql = getQuery("OIMMHOLO_AGENCY_INTERNAL_LOCATIONS_OLD_DATA");
		AgencyInternalLocations bean = new AgencyInternalLocations();
		try {
			bean = namedParameterJdbcTemplate.queryForObject(sql, createParams("internalLocId", internalLocId),
					new BeanPropertyRowMapper<AgencyInternalLocations>(AgencyInternalLocations.class));
		} catch (Exception e) {
			logger.error("getOldDataAgencyInternalLocation", e);
		}
		return bean;
	}

	@Override
	public Integer iepLevelCommit(final AgyIntLocProfiles lstAgyIntLocProfiles) {
		final String sql = getQuery("OIMMHOLO_IEP_LEVEL_COMMIT");
		Integer returnValue=null;
		try {
			returnValue = namedParameterJdbcTemplate.update(sql,createParams("internalLocationId",lstAgyIntLocProfiles.getInternalLocationId(),"iepLevelCode",lstAgyIntLocProfiles.getIepLevelCode(),"createUserId",lstAgyIntLocProfiles.getCreateUserId(),"modifyUserId",lstAgyIntLocProfiles.getModifyUserId(),"sealFlag",lstAgyIntLocProfiles.getSealFlag()));
		}catch (Exception e) {
			logger.error(this.getClass().getName()+ "iepLevelCommit", e);
		}
		return returnValue;
	}

	@Override
	public AgyIntLocProfiles getIepdetails(Long internalLocationId) {
		final String sql = getQuery("OIMMHOLO_FETCH_IEP_DETAILS");
		AgyIntLocProfiles profiles=new AgyIntLocProfiles();
		try {
			profiles=namedParameterJdbcTemplate.queryForObject(sql,new MapSqlParameterSource("internalLocationId", internalLocationId), new BeanPropertyRowMapper<AgyIntLocProfiles>(AgyIntLocProfiles.class));
		}catch (EmptyResultDataAccessException e) {
			profiles.setIepLevelCode(ApplicationConstants.EMPTYDATA);
			logger.error(this.getClass().getName()+ "iepLevelCommit", e);
		}
		catch (Exception e) {
			logger.error(this.getClass().getName()+ "iepLevelCommit", e);
		}
		return profiles;
	}

	@Override
	public Integer iepLevelCommitUpdate(final Long internalLocationId,String iepLevelCode,String modifyUserId) {
		final String sql = getQuery("OIMMHOLO_UPDATE_IEP_CODE");
		Integer returnValue=null;
		try {
			returnValue = namedParameterJdbcTemplate.update(sql,createParams("internalLocationId",internalLocationId,"iepLevelCode",iepLevelCode,"modifyUserId",modifyUserId));
		}catch (Exception e) {
			logger.error(this.getClass().getName()+ "iepLevelCommit", e);
		}
		return returnValue;
	}



	@Override
	public Integer iepLevelDelete(final Long internalLocationId,String userName) {
		final String sql = getQuery("OIMMHOLO_DELETE_IEP_CODE");
		Integer returnValue=null;
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			inputMap.put("internalLocationId", internalLocationId);
			inputMap.put("modify_user_id", userName);
			updatePreDeletedRow("living_unit_iep_levels", "internal_location_id =:internalLocationId", inputMap);
		} catch (Exception e) {
			logger.error("batchUpdatePreDeletedRows in iepLevelDelete"+e);
		}
		try {
			returnValue = namedParameterJdbcTemplate.update(sql,createParams("internalLocationId",internalLocationId));
		}catch (Exception e) {
			logger.error(this.getClass().getName()+ "iepLevelCommit", e);
		}
		return returnValue;
	}

	@Override
	public String getIEPCodeForInternalLocation(Long internalLocationId) {
		String iepCode=null;
		try {
			iepCode= namedParameterJdbcTemplate.queryForObject(getQuery("OIMMHOLO_FETCH_IEP_LEVEL_CODE"), new MapSqlParameterSource("internalLocationId", internalLocationId), String.class);
		}catch (EmptyResultDataAccessException e) {
			logger.error(this.getClass().getName()+" getCurrentiepLevel"+e);
			return null;
		}catch (Exception e) {
			logger.error(this.getClass().getName()+" getCurrentiepLevel"+e);
			return null;
		}
		return iepCode;
	}

	@Override
 	public IepLevelBean getFacilityIepLevel(String agyLocId) {
 		final String sql = getQuery("OIMMHOLO_FETCH_FACILITY_IEP_LEVEL");
 		IepLevelBean profiles=new IepLevelBean();
 		try {
 			profiles=namedParameterJdbcTemplate.queryForObject(sql,new MapSqlParameterSource("agyLocId", agyLocId), new BeanPropertyRowMapper<IepLevelBean>(IepLevelBean.class));
 		}catch (EmptyResultDataAccessException e) {
 			profiles.setIepLevelCode(ApplicationConstants.EMPTYDATA);
 			logger.error(this.getClass().getName()+ "getIepdetails", e);
 		}
 		catch (Exception e) {
 			logger.error(this.getClass().getName()+ "getIepdetails", e);
 		}
 		return profiles;
 	}
	
	@Override
 	public Long getparentLivingUnitId(Long internalLocationId) {
 		final String sql = getQuery("OIMMHOLO_GET_PARENT_LIVING_UNIT_ID");
 		Long parentLivingId=null;
 		try {
 			parentLivingId=namedParameterJdbcTemplate.queryForObject(sql,new MapSqlParameterSource("internalLocationId", internalLocationId), Long.class);
 		}catch (EmptyResultDataAccessException e) {
 			logger.error(this.getClass().getName()+ "getIepdetails", e);
 		}
 		catch (Exception e) {
 			logger.error(this.getClass().getName()+ "getIepdetails", e);
 		}
 		return parentLivingId;
 	}
	
	@Override
	public Integer updateChildLivingUnits(LivingUnits livingUnits) {
		final String sql = getQuery("OIMMHOLO_LIVUNITS_UPDATE_CHILD_LIVING_UNITS");
		Integer retVal = 0;
		try {
			retVal = namedParameterJdbcTemplate.update(sql,
					createParams("activeFlag", livingUnits.getActiveFlag(), "deactivateDate",
							livingUnits.getDeactivateDate(), "agyLocId", livingUnits.getAgyLocId(), "livingUnitId",
							livingUnits.getLivingUnitId(), "deactivateReasonCode",
							livingUnits.getDeactivateReasonCode(),"modifyUserId",livingUnits.getModifyUserId()));
		} catch (Exception e) {
			logger.error(e);
		}
		return retVal;
	}

	@Override
	public String getLiveingUnitIdIepcode(Long internalLocationId) {
		String iepCode=null;
		try {
			iepCode= namedParameterJdbcTemplate.queryForObject(getQuery("OIMMHOLO_GET_LIVEINGUNITID_IEP_CODE"), new MapSqlParameterSource("internalLocationId", internalLocationId), String.class);
		}catch (EmptyResultDataAccessException e) {
			logger.error(this.getClass().getName()+" getLiveingUnitIdIepcode"+e);
			return null;
		}catch (Exception e) {
			logger.error(this.getClass().getName()+" getLiveingUnitIdIepcode"+e);
			return null;
		}
		return iepCode;
	}

	@Override
	public String getAgencyUnitIdIepcode(String agyLocId) {
		String iepCode=null;
		try {
			iepCode= namedParameterJdbcTemplate.queryForObject(getQuery("OIMMHOLO_GET_AGENCY_IEP_CODE"), new MapSqlParameterSource("agyLocId", agyLocId), String.class);
		}catch (EmptyResultDataAccessException e) {
			logger.error(this.getClass().getName()+" getAgencyUnitIdIepcode"+e);
			return null;
		}catch (Exception e) {
			logger.error(this.getClass().getName()+" getAgencyUnitIdIepcode"+e);
			return null;
		}
		return iepCode;
	}
	
	
	@Override
	public String getDefaultIepCode() {
		String iepCode=null;
		try {
			iepCode= namedParameterJdbcTemplate.queryForObject(getQuery("OIMMHOLO_GET_DEFAULT_IEP_CODE"),createParams() ,String.class);
		}catch (EmptyResultDataAccessException e) {
			logger.error(this.getClass().getName()+" getDefaultIepCode"+e);
			return null;
		}catch (Exception e) {
			logger.error(this.getClass().getName()+" getDefaultIepCode"+e);
			return null;
		}
		return iepCode;
	}
}
