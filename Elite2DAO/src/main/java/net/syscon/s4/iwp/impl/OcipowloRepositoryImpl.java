package net.syscon.s4.iwp.impl;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.cm.primaryofficeragentassignment.beans.VAssignedOffenders;
import net.syscon.s4.cm.primaryofficeragentassignment.beans.VStaffLocation;
import net.syscon.s4.common.beans.Images;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.iwp.OcipowloRepository;
import oracle.jdbc.OracleTypes;

@Repository
public class OcipowloRepositoryImpl extends RepositoryBase implements OcipowloRepository {

	/**
	 * Creates new OcipowloRepositoryImpl class Object
	 */
	public OcipowloRepositoryImpl() {
	}

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcipowloRepositoryImpl.class);
	private final Map<String, FieldMapper> referenceCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("ROLE", new FieldMapper("role")).put("SCHEDULE_TYPE", new FieldMapper("scheduleType"))
			.put("POSITION", new FieldMapper("position")).put("CODE", new FieldMapper("code"))
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("DSP_DESCRIPTION", new FieldMapper("dspDescription"))
			.put("DSP_DESCRIPTION4", new FieldMapper("dspDescription4"))
			.put("DSP_DESCRIPTION3", new FieldMapper("dspDescription3")).build();

	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("AGENCY_LOCATION_TYPE", new FieldMapper("agencyLocationType"))
			.put("DESCRIPTION", new FieldMapper("description")).put("CODE", new FieldMapper("code")).build();

	private final Map<String, FieldMapper> agencyLocationsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("AGENCY_LOCATION_TYPE", new FieldMapper("agencyLocationType"))
			.put("AGY_LOC_ID", new FieldMapper("agyLocId")).put("DESCRIPTION", new FieldMapper("description"))
			.put("DSP_DESCRIPTION", new FieldMapper("dspDescription"))
			.put("CAL_AGY_LOC_ID", new FieldMapper("calAgyLocId")).put("CASELOAD_ID", new FieldMapper("caseloadId"))
			.build();

	private final Map<String, FieldMapper> vStaffLocationMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CAL_AGY_LOC_ID", new FieldMapper("calAgyLocId")).put("FROM_DATE", new FieldMapper("fromDate"))
			.put("POSITION", new FieldMapper("position")).put("ROLE", new FieldMapper("role"))
			.put("SAC_STAFF_ID", new FieldMapper("staff_id"))

			.build();

	private final Map<String, FieldMapper> vAssignedOffendersMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CAL_AGY_LOC_ID", new FieldMapper("calAgyLocId"))
			.put("POSITION", new FieldMapper("position"))
			.put("SAC_STAFF_ID", new FieldMapper("staffId"))
			.put("ROLE", new FieldMapper("role"))
			.build();

	 
	/**
	 * Used to capture results from select query
	 * 
	 * @return  List<AgencyLocations>
	 */
	public List<AgencyLocations> cgfkStaffLr1DspDescriptionRecordGroup(final String caseLoadId) {
		final String sql = getQuery("OCIPOWLO_FIND_CGFKSTAFFLR1DSPDESCRIPTION");
		List<AgencyLocations> returnObj = new ArrayList<AgencyLocations>();
		final RowMapper<AgencyLocations> mRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,mMapping);
		try {
			returnObj = namedParameterJdbcTemplate.query(sql, createParams("CASELOAD_ID", caseLoadId), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("DSPDescript", e);
		}
		return returnObj;
	}
	/**
	 * Used to capture results from select query
	 * 
	 * @return  List<ReferenceCodes>
	 */
	public List<ReferenceCodes> positionLovRecordGroup() {
		final String sql = getQuery("OCIPOWLO_FIND_CGFKVOFFDETDSPDESCRIPT3");
		List<ReferenceCodes> returnObj = new ArrayList<ReferenceCodes>();
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			returnObj = namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("DSPDescript", e);
		}
		return returnObj;
	}
     
	/**
	 * Used to capture results from select query
	 * 
	 * @return  List<ReferenceCodes>
	 */
	public List<ReferenceCodes> roleLovRecordGroup() {
		final String sql = getQuery("OCIPOWLO_FIND_CGFKVOFFDETDSPDESCRIPT2");
		List<ReferenceCodes> returnObj = new ArrayList<ReferenceCodes>();
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			returnObj = namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("DSPDescript", e);
		}
		return returnObj;
	}
     
	/**
	 * Used to capture results from select query
	 * 
	 * @return  List<ReferenceCodes>
	 */
	public List<ReferenceCodes> scheduleTypeLovRecordGroup() {
		final String sql = getQuery("OCIPOWLO_FIND_CGFKVOFFDETDSPDESCRIPTION");
		List<ReferenceCodes> returnObj = new ArrayList<ReferenceCodes>();
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			returnObj = namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("DspDescription", e);
		}
		return returnObj;

	}
	
	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            VStaffLocation
	 *
	 * @return List<VStaffLocation>
	 *
	 */

	public List<VStaffLocation> vOffDetExecuteQuery(VStaffLocation objSearchDao) {
		final String sql = getQuery("OCIPOWLO_VOFFDET_FIND_V_STAFF_LOCATION");
		final RowMapper<VStaffLocation> VStaffLocationRowMapper = Row2BeanRowMapper.makeMapping(sql,
				VStaffLocation.class, vStaffLocationMapping);
		final ArrayList<VStaffLocation> returnList = (ArrayList<VStaffLocation>) namedParameterJdbcTemplate.query(sql,
				createParams("AGY_LOC_ID", objSearchDao.getCalAgyLocId()), VStaffLocationRowMapper);
		return returnList;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            VAssignedOffenders
	 *
	 * @return List<VAssignedOffenders>
	 *
	 */
	public List<VAssignedOffenders> vAssOffExecuteQuery(VAssignedOffenders objSearchDao) {
		final String sql = getQuery("OCIPOWLO_VASSOFF_FIND_V_ASSIGNED_OFFENDERS");
		final RowMapper<VAssignedOffenders> VAssignedOffendersRowMapper = Row2BeanRowMapper.makeMapping(sql,
				VAssignedOffenders.class, vAssignedOffendersMapping);
		final ArrayList<VAssignedOffenders> returnList = (ArrayList<VAssignedOffenders>) namedParameterJdbcTemplate
				.query(sql,
						createParams("calAgyLocId", objSearchDao.getCalAgyLocId(), "role", objSearchDao.getRole(),
								"position", objSearchDao.getPosition(), "staff_id", objSearchDao.getSacStaffId(),"username",objSearchDao.getCreateUserId()),
						VAssignedOffendersRowMapper);
		return returnList;
	}

	
	public long populateNoOffenders(final VStaffLocation objSearchDao) {
		long returnVal1 = 0;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		Map<String, Object> returnVal = null;
		SqlParameter[] sqlParameters = new SqlParameter[3];
		sqlParameters = new SqlParameter[] { new SqlParameter("p_agy_loc_id", Types.VARCHAR),
				new SqlParameter("p_staff_id", Types.INTEGER), new SqlParameter("p_position", Types.VARCHAR),
				new SqlParameter("p_role", Types.VARCHAR), new SqlParameter("p_from_date", Types.DATE),
				new SqlOutParameter("RETURN_VAL", OracleTypes.NUMBER) };
		final SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("COMUNITY_PKG").withFunctionName("GET_OFFICER_PO").declareParameters(sqlParameters);
		inParamMap.put("p_agy_loc_id", objSearchDao.getCalAgyLocId());
		inParamMap.put("p_staff_id", objSearchDao.getStaffId());
		inParamMap.put("p_position", objSearchDao.getPosition());
		inParamMap.put("p_role", objSearchDao.getRole());
		inParamMap.put("p_from_date", objSearchDao.getFromDate());

		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnVal = simpleJDBCCall.execute(inParameter);
			returnVal1 = ((BigDecimal) returnVal.get("RETURN_VAL")).longValue();
		} catch (final Exception e) {
			returnVal1 = 0;
		}
		return returnVal1;
	}

	
	public BigDecimal populateCurrentWorkload(VStaffLocation objSearchDao) {
		BigDecimal dataAvail = null;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		Map<String, Object> returnVal = null;
		SqlParameter[] sqlParameters = new SqlParameter[3];
		sqlParameters = new SqlParameter[] { new SqlParameter("p_agy_loc_id", Types.VARCHAR),
				new SqlParameter("p_staff_id", Types.INTEGER), new SqlParameter("p_position", Types.VARCHAR),
				new SqlParameter("p_role", Types.VARCHAR), new SqlParameter("p_from_date", Types.DATE),
				new SqlOutParameter("RETURN_VAL", OracleTypes.NUMBER) };
                  
		if (objSearchDao.getStaffId() != null) {

			final SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
					.withCatalogName("PIMS_WEIGHT").withFunctionName("OFFICER_WORK").declareParameters(sqlParameters);
			inParamMap.put("p_agy_loc_id", objSearchDao.getCalAgyLocId());
			inParamMap.put("p_staff_id", objSearchDao.getStaffId());
			inParamMap.put("p_position", objSearchDao.getPosition());
			inParamMap.put("p_role", objSearchDao.getRole());
			inParamMap.put("p_from_date", objSearchDao.getFromDate());

			final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);

			try {
				returnVal =   simpleJDBCCall.execute(inParameter);
				dataAvail = ((BigDecimal) returnVal.get("RETURN_VAL"));

			} catch (final Exception e) {
				dataAvail = null;
			}
		}
		return dataAvail;

	}

	
	/**
	 * Used to capture results from select query
	 * @param paramBean
	 * @return Integer
	 */
	public Integer curGetHpCase(VAssignedOffenders paramBean) {
		final String sql = getQuery("OCDORASS_CUR_GET_HP_CASE");
		Integer noOffenders = null;
		noOffenders = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("offenderBookId", paramBean.getOffenderBookId()), Integer.class);
		return noOffenders;
	}
	/**
	 * Used to capture results from select query
	 * @param paramBean
	 * @return Integer
	 */
	public Integer curGetYCase(VAssignedOffenders paramBean) {
		final String sql = getQuery("OCDORASS_CUR_GET_Y_CASE");
		Integer noOffenders = null;
		noOffenders = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("offenderBookId", paramBean.getOffenderBookId()), Integer.class);
		return noOffenders;
	}
	/**
	 * Used to capture results from select query
	 * @param paramBean
	 * @return Integer
	 */
	public Integer curGetACase(VAssignedOffenders paramBean) {
		final String sql = getQuery("OCDORASS_CUR_GET_A_CASE");
		Integer noOffenders = null;
		noOffenders = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("offenderBookId", paramBean.getOffenderBookId()), Integer.class);
		return noOffenders;
	}

	
	
	
	
	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkStaffLr1StaffLrAg
	 *
	 * @param params
	 *
	 */
	public AgencyLocations cgfkchkStaffLr1StaffLrAg(AgencyLocations paramBean) {
		final String sql = getQuery("OCIPOWLO_CGFKCHK_STAFF_LR1_STAFF_LR_AG");
		final RowMapper<AgencyLocations> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				agencyLocationsMapping);
		AgencyLocations returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfklkpStaffLr1StaffLrAg
	 *
	 * @param params
	 *
	 */
	public List<AgencyLocations> cgfklkpStaffLr1StaffLrAg(AgencyLocations paramBean) {
		final String sql = getQuery("OCIPOWLO_CGFKLKP_STAFF_LR1_STAFF_LR_AG");
		final RowMapper<AgencyLocations> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				agencyLocationsMapping);
		final ArrayList<AgencyLocations> returnList = (ArrayList<AgencyLocations>) namedParameterJdbcTemplate.query(sql,
				createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkVOffDetVOffDet
	 *
	 * @param params
	 *
	 */
	public ReferenceCodes cgfkchkVOffDetVOffDet(ReferenceCodes paramBean) {
		final String sql = getQuery("OCIPOWLO_CGFKCHK_V_OFF_DET_V_OFF_DET_R");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		ReferenceCodes returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfklkpVOffDetVOffDet
	 *
	 * @param params
	 *
	 */
	public ReferenceCodes cgfklkpVOffDetVOffDet(ReferenceCodes paramBean) {
		final String sql = getQuery("OCIPOWLO_CGFKLKP_V_OFF_DET_V_OFF_DET_R");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		ReferenceCodes returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkVOffDetVOffDe2
	 *
	 * @param params
	 *
	 */
	public ReferenceCodes cgfkchkVOffDetVOffDe2(ReferenceCodes paramBean) {
		final String sql = getQuery("OCIPOWLO_CGFKCHK_V_OFF_DET_V_OFF_DE2");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		ReferenceCodes returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfklkpVOffDetVOffDe2
	 *
	 * @param params
	 *
	 */
	public ReferenceCodes cgfklkpVOffDetVOffDe2(ReferenceCodes paramBean) {
		final String sql = getQuery("OCIPOWLO_CGFKLKP_V_OFF_DET_V_OFF_DE2");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		ReferenceCodes returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkVOffDetVOffDe3
	 *
	 * @param params
	 *
	 */
	public ReferenceCodes cgfkchkVOffDetVOffDe3(ReferenceCodes paramBean) {
		final String sql = getQuery("OCIPOWLO_CGFKCHK_V_OFF_DET_V_OFF_DE3");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		ReferenceCodes returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * cgfklkpVOffDetVOffDe3
	 * @param params
	 *
	 */
	public ReferenceCodes cgfklkpVOffDetVOffDe3(ReferenceCodes paramBean) {
		final String sql = getQuery("OCIPOWLO_CGFKLKP_V_OFF_DET_V_OFF_DE3");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		ReferenceCodes returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		
		return returnObj;
	}
	
	/**
	 * Used to capture results from select query
	 * @param Integer
	 * @return Images  
	 */
	public Images imageData(final Integer offenderBoookId) {
		final String sql = getQuery("OCCIPOWLO_GET_IMAGE_DATA");
		Images returnObj = new Images();
		final RowMapper<Images> mRowMapper = Row2BeanRowMapper.makeMapping(sql, Images.class, mMapping);
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("OFFENDERBOOKID", offenderBoookId), mRowMapper);
		} catch (Exception e) {
			logger.error("imageData", e);
			return null;
		}
		return returnObj;
	}
	
}
