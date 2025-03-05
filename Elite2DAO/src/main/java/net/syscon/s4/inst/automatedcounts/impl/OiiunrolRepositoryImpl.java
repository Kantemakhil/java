package net.syscon.s4.inst.automatedcounts.impl;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlInOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.VLivingUnitOffenders;
import net.syscon.s4.inst.automatedcounts.OiiunrolRepository;
import oracle.jdbc.OracleTypes;
/**
 * Class OiiunrolRepositoryImpl
 */
@Repository
public class OiiunrolRepositoryImpl extends RepositoryBase implements OiiunrolRepository{

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OiiunrolRepositoryImpl.class.getName());
private final Map<String, FieldMapper> vLivingUnitOffendersMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("OFFENDER_BOOK_ID", 				new FieldMapper("offenderBookId"))
.put("OFFENDER_ID_DISPLAY", 			new FieldMapper("offenderIdDisplay"))
.put("ACTIVE_FLAG", 					new FieldMapper("activeFlag"))
.put("AGENCY_IML_ID", 					new FieldMapper("agencyImlId"))
.put("ALERT_FLAG", 						new FieldMapper("alertFlag"))
.put("LIVING_UNIT_ID", 					new FieldMapper("livingUnitId"))
.put("LIVING_UNIT_DESC", 				new FieldMapper("livingUnitDesc"))
.put("IN_OUT_STATUS", 					new FieldMapper("inOutStatus"))
.put("OFFENDER_ID", 					new FieldMapper("offenderId"))
.put("LAST_NAME", 						new FieldMapper("lastName"))
.put("AGY_LOC_ID", 						new FieldMapper("agyLocId"))
.put("AGENCY_IML_DESC", 				new FieldMapper("agencyImlDesc"))
.put("ROOT_LIVING_UNIT_ID", 			new FieldMapper("rootLivingUnitId"))
.put("PARENT_LIVING_UNIT_ID", 			new FieldMapper("parentLivingUnitId"))
.put("FIRST_NAME", 						new FieldMapper("firstName"))
.build();

	/**
	 * Creates new OiiunrolRepositoryImpl class Object
	 */
	public OiiunrolRepositoryImpl() {
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            VLivingUnitOffenders
	 *
	 * @return List<VLivingUnitOffenders>
	 *
	 * @throws SQLException
	 */
	public List<VLivingUnitOffenders> rollListExecuteQuery(final VLivingUnitOffenders objSearchDao) {
		final String sql = getQuery("OIIUNROL_ROLLLIST_FIND_V_LIVING_UNIT_OFFENDERS");
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource params = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" where ");
			if (objSearchDao.getAgyLocId() != null) {
				sqlQuery.append(" AGY_LOC_ID =:agyLocId  " + " and ");
				params.addValue("agyLocId", objSearchDao.getAgyLocId());
			}
			if (objSearchDao.getRootLivingUnitId() != null) {
				sqlQuery.append(" ROOT_LIVING_UNIT_ID =:rootLivingUnitId  ");
				params.addValue("rootLivingUnitId", objSearchDao.getRootLivingUnitId());
			}
			if (objSearchDao.getCellType() != null) {
				if ("CC".equals(objSearchDao.getCellType())) {
					sqlQuery.append(" AND IN_OUT_STATUS = 'IN' AND AGENCY_IML_ID IS NULL");
				}
				if ("IL".equals(objSearchDao.getCellType())) {
					sqlQuery.append(" AND IN_OUT_STATUS = 'IN' AND AGENCY_IML_ID IS NOT NULL");
				}
				if ("CO".equals(objSearchDao.getCellType())) {
					sqlQuery.append(" AND IN_OUT_STATUS = 'OUT'");
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
		preparedSql = preparedSql + " ORDER BY LIVING_UNIT_DESC,LAST_NAME,FIRST_NAME";
		final RowMapper<VLivingUnitOffenders> VLivingUnitOffendersRowMapper = Row2BeanRowMapper.makeMapping(preparedSql,
				VLivingUnitOffenders.class, vLivingUnitOffendersMapping);
		final ArrayList<VLivingUnitOffenders> returnList = (ArrayList<VLivingUnitOffenders>) namedParameterJdbcTemplate
				.query(preparedSql, params, VLivingUnitOffendersRowMapper);
		return returnList;
	}

	/**
	 * @param
	 *
	 * @throws SQLException
	 *
	 */
	public int preInsert() {
		return 0;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * chkNonAssociation
	 *
	 *
	 */
	public String getExtLocation(final VLivingUnitOffenders searchBean) {
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[1];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_OFFENDER_BOOK_ID", OracleTypes.NUMBER),
				new SqlInOutParameter("RETURN_VALUE", OracleTypes.VARCHAR) };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_BOOKING").withFunctionName("GET_EXT_LOCATION").declareParameters(sqlParameters);
		inParamMap.put("P_OFFENDER_BOOK_ID", searchBean.getOffenderBookId());
		inParamMap.put("RETURN_VALUE", OracleTypes.VARCHAR);
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		String value = null;
		try {
			value = simpleJDBCCall.executeFunction(String.class, inParameter);
		} catch (Exception e) {
			logger.error("In method getExtLocation",e);
			return value;
		}
		return value;
	}
}
