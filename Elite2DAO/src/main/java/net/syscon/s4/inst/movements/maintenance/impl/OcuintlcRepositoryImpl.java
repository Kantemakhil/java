package net.syscon.s4.inst.movements.maintenance.impl;

import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.beans.AgencyInternalLocations;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.inst.movements.maintenance.OcuintlcRepository;
import oracle.jdbc.OracleTypes;

/**
 * Class OcuintlcRepositoryImpl
 * 
 */
@Repository
public class OcuintlcRepositoryImpl extends RepositoryBase implements OcuintlcRepository {
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcuintlcRepositoryImpl.class.getName());

	/**
	 * Creates new OcuintlcRepositoryImpl class Object
	 */
	public OcuintlcRepositoryImpl() {
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            TagInternalLocationsQueryInternalLocations
	 *
	 * @return List<TagInternalLocationsQueryInternalLocations>
	 */
	public List<AgencyInternalLocations> intLocExecuteQuery(final AgencyInternalLocations objSearchDao) {
		List<AgencyInternalLocations> returnList = null;
		Map<String, Object> returnObject = null;
		final Map<String, Object> inParamMap = new HashMap<>();
		SqlParameter[] sqlParameters = new SqlParameter[20];
		sqlParameters = new SqlParameter[] { new SqlOutParameter("P_RESULT_SET", OracleTypes.CURSOR),
				new SqlParameter("P_LEVEL", OracleTypes.INTEGER), new SqlParameter("P_LOCATION_CODE", Types.VARCHAR),
				new SqlParameter("P_DESCRIPTION", Types.VARCHAR),
				new SqlParameter("P_INTERNAL_LOCATION_ID", Types.INTEGER),
				new SqlParameter("P_AGY_LOC_ID", Types.VARCHAR),
				new SqlParameter("P_SHOW_LIVING_UNITS", Types.VARCHAR), };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_INTERNAL_LOCATIONS").withProcedureName("QUERY_INTERNAL_LOCATIONS")
				.declareParameters(sqlParameters);
		inParamMap.put("P_RESULT_SET", OracleTypes.CURSOR);
		inParamMap.put("P_LEVEL", null);
		inParamMap.put("P_LOCATION_CODE", "");
		inParamMap.put("P_DESCRIPTION", "");
		inParamMap.put("P_INTERNAL_LOCATION_ID", objSearchDao.getInternalLocationId());
		inParamMap.put("P_AGY_LOC_ID", objSearchDao.getAgyLocId());
		inParamMap.put("P_SHOW_LIVING_UNITS", objSearchDao.getTrackingFlag());
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			returnList = new ArrayList<>();
			final List<Map<String, String>> list = (List<Map<String, String>>) returnObject.get("P_RESULT_SET");
			for (int i = 0; i < list.size(); i++) {
				final Map<String, String> childMap = list.get(i);
				final AgencyInternalLocations bean = new AgencyInternalLocations();
				bean.setInternalLocationCode(String.valueOf(childMap.get("INTERNAL_LOCATION_CODE")));
				bean.setDescription(String.valueOf(childMap.get("DESCRIPTION")));
				bean.setLevelCode(String.valueOf(childMap.get("LOCATION_LEVEL")));
				bean.setInternalLocationId(Integer.valueOf(String.valueOf(childMap.get("INTERNAL_LOCATION_ID"))));
				returnList.add(bean);
			}
		} catch (Exception e) {
			logger.error("intLocExecuteQuery", e);
		}

		return returnList;
	}
}
