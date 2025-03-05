package net.syscon.s4.inst.movements.maintenance.impl;

import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
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
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.inst.movements.maintenance.OimilocaRepository;
import oracle.jdbc.OracleTypes;

/**
 * Class OimilocaRepositoryImpl
 */
@Repository
public class OimilocaRepositoryImpl extends RepositoryBase implements OimilocaRepository {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OimilocaRepositoryImpl.class.getName());
	private final Map<String, FieldMapper> agyIntLocMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("INTERNAL_LOCATION_ID", new FieldMapper("internalLocationId"))
			.put("CREATE_DATETIME", new FieldMapper("createDateTime"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDateTime"))
			.put("linked_offender_count", new FieldMapper("linkedOffenderCount"))
			.put("linked_offender_count1", new FieldMapper("linkedOffenderCount1")).build();
	
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CODE", new FieldMapper("code"))
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("ACTIVE_FLAG", new FieldMapper("activeFlag")).build();

	/**
	 * Creates new OimilocaRepositoryImpl class Object
	 */
	public OimilocaRepositoryImpl() {
		// OimilocaRepositoryImpl
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao AgencyInternalLocations
	 *
	 * @return List<AgencyInternalLocations>
	 */
	public List<AgencyInternalLocations> intLocLOneExecuteQuery(final AgencyInternalLocations objSearchDao) {
		final String sql = getQuery("OIMILOCA_INTLOCL1_FIND_AGENCY_INTERNAL_LOCATIONS");
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		String preSqlQuery = null;
		final MapSqlParameterSource params = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" WHERE ");
			if (objSearchDao.getAgyLocId() != null) {
				sqlQuery.append(" PARENT_INTERNAL_LOCATION_ID IS NULL AND ");
				sqlQuery.append(" AGY_LOC_ID  = :agyLocId " + " AND ");
				params.addValue("agyLocId", objSearchDao.getAgyLocId());
			}
			if ("N".equals(objSearchDao.getSealFlag())) {
				sqlQuery.append(" UNIT_TYPE IS NULL ");
			}
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("WHERE")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("AND")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		preSqlQuery = preparedSql.concat(" ORDER BY list_seq ");

		final RowMapper<AgencyInternalLocations> agyRowMapper = Row2BeanRowMapper.makeMapping(preSqlQuery,
				AgencyInternalLocations.class, agyIntLocMapping);
		List<AgencyInternalLocations> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(preSqlQuery, params, agyRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstAgencyInternalLocations List<AgencyInternalLocations>
	 *
	 * @return List<Integer>
	 */
	public Integer intLocLOneInsertAgencyInternalLocations(final List<AgencyInternalLocations> lstAgyIntlLoc) {
		final String sql = getQuery("OIMILOCA_INTLOCL1_INSERT_AGENCY_INTERNAL_LOCATIONS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final AgencyInternalLocations agyIntLocations : lstAgyIntlLoc) {
			parameters.add(new BeanPropertySqlParameterSource(agyIntLocations));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstAgyIntlLoc.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstAgencyInternalLocations List<AgencyInternalLocations>
	 */
	public Integer intLocLOneUpdateAgencyInternalLocations(final List<AgencyInternalLocations> lstAgyIntlLoc) {
		final String sql = getQuery("OIMILOCA_INTLOCL1_UPDATE_AGENCY_INTERNAL_LOCATIONS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final AgencyInternalLocations agyIntLocations : lstAgyIntlLoc) {
			parameters.add(new BeanPropertySqlParameterSource(agyIntLocations));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstAgyIntlLoc.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstAgencyInternalLocations List<AgencyInternalLocations>
	 */
	public AgencyInternalLocations intLocLOneDeleteAgencyInternalLocations(
			final List<AgencyInternalLocations> lstAgyIntlLoc) {
		final String sql = getQuery("OIMILOCA_INTLOCL1_DELETE_AGENCY_INTERNAL_LOCATIONS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		final AgencyInternalLocations returnData = new AgencyInternalLocations();
		for (final AgencyInternalLocations agyIntLocations : lstAgyIntlLoc) {
			parameters.add(new BeanPropertySqlParameterSource(agyIntLocations));
		}
		try {
			batchUpdatePreDeletedRows("AGENCY_INTERNAL_LOCATIONS", "INTERNAL_LOCATION_ID = :internalLocationId", parameters);
		} catch (Exception e) {
			logger.error("batchUpdatePreDeletedRows in intLocLOneDeleteAgencyInternalLocations"+e);
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			String error = "Error : " + e.getMessage().toUpperCase();
			returnData.setErrorMessage("Error : " + error);
			returnData.setSealFlag("AGY_INT_LOC_AMENDMENTS");
			return returnData;
		}
		if (lstAgyIntlLoc.size() == returnArray.length) {
			returnData.setSealFlag("1");
			return returnData;
		} else {
			returnData.setSealFlag("0");
			return returnData;
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return ListAgencyLocations>
	 */
	public List<AgencyLocations> rgAgyLocRecordGroup(String userName) {
		final String sql = getQuery("OIMILOCA_FIND_RGAGYLOC");
		final RowMapper<AgencyLocations> mRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				mMapping);
		List<AgencyLocations> returnList = null;
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("P_USER_ID", userName), mRowMapper);
		} catch (Exception e) {
			logger.error("rgAgyLocRecordGroup", e);
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgLevelTypeRecordGroup() {
		final String sql = getQuery("OIMILOCA_FIND_RGLEVELTYPE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		List<ReferenceCodes> returnList = null;
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (Exception e) {
			logger.error("rgLevelTypeRecordGroup", e);
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * intLocLOneOnCheckDeleteMaster
	 *
	 * @param params
	 */
	public List<AgencyInternalLocations> intLocLOneOnCheckDeleteMaster(final AgencyInternalLocations paramBean) {
		final String sql = getQuery("OIMILOCA_INT_LOC_L1_ONCHECKDELETEMASTER");
		final RowMapper<AgencyInternalLocations> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				AgencyInternalLocations.class, agyIntLocMapping);
		List<AgencyInternalLocations> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(sql,
				createParams("INTERNALLOCATIONID", paramBean.getInternalLocationId()), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * intLocLTwoOnCheckDeleteMaster
	 *
	 * @param params
	 */
	public List<AgencyInternalLocations> intLocLTwoOnCheckDeleteMaster(final AgencyInternalLocations paramBean) {
		final String sql = getQuery("OIMILOCA_INT_LOC_L2_ONCHECKDELETEMASTER");
		final RowMapper<AgencyInternalLocations> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				AgencyInternalLocations.class, agyIntLocMapping);
		List<AgencyInternalLocations> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(sql,
				createParams("INTERNALLOCATIONID", paramBean.getInternalLocationId()), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * intLocLTwoExecuteQuery
	 *
	 * @param params
	 */
	public List<AgencyInternalLocations> intLocLTwoExecuteQuery(final AgencyInternalLocations paramBean) {
		final String sql = getQuery("OIMILOCA_INTLOCL2_FIND_AGENCY_INTERNAL_LOCATIONS");
		final RowMapper<AgencyInternalLocations> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				AgencyInternalLocations.class, agyIntLocMapping);
		List<AgencyInternalLocations> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(sql, createParams("AGYLOCID", paramBean.getAgyLocId(),
				"PARENTINTERNALLOCATIONID", paramBean.getParentInternalLocationId()), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * intLocLThreeOnCheckDeleteMaster
	 *
	 * @param params
	 */
	public List<AgencyInternalLocations> intLocLThreeOnCheckDeleteMaster(final AgencyInternalLocations paramBean) {
		final String sql = getQuery("OIMILOCA_INT_LOC_L3_ONCHECKDELETEMASTER");
		final RowMapper<AgencyInternalLocations> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				AgencyInternalLocations.class, agyIntLocMapping);
		List<AgencyInternalLocations> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(sql,
				createParams("INTERNALLOCATIONID", paramBean.getInternalLocationId()), columnRowMapper);
		return returnList;
	}

	public AgencyInternalLocations getResDescValues(final AgencyInternalLocations objSearchDao) {
		Map<String, Object> returnObject = null;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[3];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_INTERNAL_LOCATION_CODE", Types.VARCHAR),
				new SqlParameter("P_PARENT_INTERNAL_LOCATION_ID", Types.NUMERIC),
				new SqlParameter("P_AGY_LOC_ID", Types.VARCHAR), new SqlOutParameter("P_DESC", Types.VARCHAR) };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_INTERNAL_LOCATIONS").withFunctionName("DEFAULT_LOCATION_DESCRIPTION")
				.declareParameters(sqlParameters);
		inParamMap.put("P_INTERNAL_LOCATION_CODE", objSearchDao.getInternalLocationCode());
		inParamMap.put("P_PARENT_INTERNAL_LOCATION_ID", objSearchDao.getParentInternalLocationId());
		inParamMap.put("P_AGY_LOC_ID", objSearchDao.getAgyLocId());
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);

		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			objSearchDao.setSealFlag((String) returnObject.get("P_DESC"));
		} catch (Exception e) {
			logger.error("Exception", e);
		}
		return objSearchDao;
	}

	@Override
	public Integer getInternalLocationId() {
		Integer openAnAccount = 0;
		Map<String, Object> returnObject = null;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[10];
		sqlParameters = new SqlParameter[] { new SqlOutParameter("P_OPEN_AN_ACCOUNT", OracleTypes.NUMBER) };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_INTERNAL_LOCATIONS").withFunctionName("GET_INTERNAL_LOCATION_ID")
				.declareParameters(sqlParameters);
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			openAnAccount = Integer.valueOf(String.valueOf(returnObject.get("P_OPEN_AN_ACCOUNT")));
		} catch (Exception e) {
			logger.error("getInternalLocationId" + e);
		}
		return openAnAccount;
	}

	public Integer updateIntoRelatedTrackingFlags(final AgencyInternalLocations offTrans) {
		Integer genSeq = 0;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("P_INTERNAL_LOCATION_ID", offTrans.getInternalLocationId());
		inParamMap.put("P_TRACKING_FLAG", offTrans.getTrackingFlag());
		try {
			namedParameterJdbcTemplate.update(
					" CALL OMS_OWNER.TAG_INTERNAL_LOCATIONS.UPDATE_RELATED_TRACKING_FLAGS(:P_INTERNAL_LOCATION_ID, :P_TRACKING_FLAG)",
					inParamMap);
			genSeq = 1;
		} catch (Exception e) {
			logger.error("updateIntoRelatedTrackingFlags :" + e);
			return genSeq;
		}
		return genSeq;
	}

	public Integer updateIntoActDeactIntChildLocation(final AgencyInternalLocations offTrans) {
		Integer genSeq = 0;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("P_PARENT_LU_ID", offTrans.getInternalLocationId());
		inParamMap.put("P_ACTIVE_FLAG", offTrans.getActiveFlag());
		try {
			namedParameterJdbcTemplate.update(
					" CALL OMS_OWNER.TAG_INTERNAL_LOCATIONS.ACT_DEACT_INT_CHILD_LOCATION(:P_PARENT_LU_ID, :P_ACTIVE_FLAG)",
					inParamMap);
			genSeq = 1;
		} catch (Exception e) {
			logger.error("updateIntoActDeactIntChildLocation :" + e);
			return genSeq;
		}
		return genSeq;
	}

	public String errorNameValidation(final String errorName) {
		final String sql = getQuery("OIMILOCA_CONSTRAINT_VALIDATIONS");
		String returnData = null;
		try {
			returnData = namedParameterJdbcTemplate.queryForObject(sql, createParams("CONSTRAINTNAME", errorName),
					String.class);
		} catch (Exception e) {
			returnData = null;
			return returnData;
		}
		return returnData;
	}

	@Override
	public AgencyInternalLocations gettingOldData(Integer internalLocationId) {
		final String sql = getQuery("OIMILOCA_GETTING_OLD_DATA");
		final RowMapper<AgencyInternalLocations> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				AgencyInternalLocations.class, agyIntLocMapping);
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("internalLocationId", internalLocationId),
				columnRowMapper);
	}
	
	public List<ReferenceCodes> locationTypeLOVRecordGroup(String domain) {
		final String sql = getQuery("OIMILOCA_FIND_BOTHLOV");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		List<ReferenceCodes> returnList = null;
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("domain", domain), mRowMapper);
		} catch (Exception e) {
			logger.error("getBothValue", e);
		}
		return returnList;
	}
	
}
