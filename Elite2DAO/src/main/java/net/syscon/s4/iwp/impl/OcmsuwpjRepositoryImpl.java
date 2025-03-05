package net.syscon.s4.iwp.impl;

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
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.Corporates;
import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.im.beans.Teams;
import net.syscon.s4.inst.accreditedprograms.beans.VProgramProviders;
import net.syscon.s4.inst.casemanagement.beans.ProgramServices;
import net.syscon.s4.inst.schedules.bean.IntLocUsageLocations;
import net.syscon.s4.inst.schedules.bean.VCorporateAddresses;
import net.syscon.s4.iwp.OcmsuwpjRepository;

/**
 * Class OcmsuwpjRepositoryImpl
 */
@Repository
public class OcmsuwpjRepositoryImpl extends RepositoryBase implements OcmsuwpjRepository {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcmsuwpjRepositoryImpl.class);

	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CORPORATE_NAME", new FieldMapper("corporateName")).put("CORPORATE_ID", new FieldMapper("corporateId"))
			.put("PROVIDER_PARTY_ID", new FieldMapper("providerPartyId")).put("AREA", new FieldMapper("area"))
			.put("COUNTRY", new FieldMapper("country")).put("DESCRIPTION", new FieldMapper("description"))
			.put("CODE", new FieldMapper("code")).put("TEAM_CODE", new FieldMapper("teamCode"))
			.put("ZIP_POSTAL_CODE", new FieldMapper("zipPostalCode")).put("HOUSE", new FieldMapper("house"))
			.put("STREET", new FieldMapper("street")).put("TEAM_ID", new FieldMapper("teamId"))
			.put("placementCorporateId", new FieldMapper("placementCorporateId"))
			.put("party_id", new FieldMapper("partyId"))
			.put("ADDRESS_LINE1", new FieldMapper("streetAddress"))

			.build();
	private final Map<String, FieldMapper> mIntMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("INTERNAL_LOCATION_CODE", new FieldMapper("internalLocationCode")).put("CODE", new FieldMapper("code"))
			.build();

	/**
	 * Creates new OcmsuwpjRepositoryImpl class Object
	 */
	public OcmsuwpjRepositoryImpl() {
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao CourseActivities
	 *
	 * @return List<CourseActivities>
	 *
	 * 
	 */
	public List<CourseActivities> courseActExecuteQuery(CourseActivities objSearchDao) {
		final String sql = getQuery("OCMSUWPJ_COURSEACT_FIND_COURSE_ACTIVITIES");
		final RowMapper<CourseActivities> CourseActivitiesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				CourseActivities.class, mMapping);
		final ArrayList<CourseActivities> returnList = (ArrayList<CourseActivities>) namedParameterJdbcTemplate
				.query(sql, createParams("providerPartyId", objSearchDao.getProviderPartyId()), CourseActivitiesRowMapper);
		return returnList;
	}

	/**
	 * @param
	 *
	 *
	 * 
	 */
	public List<CourseActivities> placementExecuteQuery(CourseActivities objSearchDao) {
		final String sql = getQuery("OCMSUWPJ_FIND_PLACEMENT");
		final RowMapper<CourseActivities> CourseActivitiesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				CourseActivities.class, mMapping);
		final ArrayList<CourseActivities> returnList = (ArrayList<CourseActivities>) namedParameterJdbcTemplate.query(
				sql,
				createParams("p_team_id", objSearchDao.getProviderPartyId(), "crsActyId", objSearchDao.getCrsActyId()),
				CourseActivitiesRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstCourseActivities List<CourseActivities>
	 *
	 * @return List<Integer>
	 *
	 * 
	 */
	public CourseActivities courseActInsertCourseActivities(final List<CourseActivities> lstCourseActivities) {
		String sql = getQuery("OCMSUWPJ_COURSEACT_INSERT_COURSE_ACTIVITIES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		CourseActivities returnData = new CourseActivities();
		for (final CourseActivities courseActivities : lstCourseActivities) {
			parameters.add(new BeanPropertySqlParameterSource(courseActivities));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			String error = "Error : " + e.getMessage();
			if (error.contains("constraint")) {

				error = error.substring(error.indexOf("constraint"), error.indexOf("Detail"))
						.replaceFirst("constraint", "").trim();
				final String tableName = errorNameValidation(error.substring(1, error.length() - 1));
				returnData.setSealFlag(tableName);
				returnData.setSeqOne(BigDecimal.valueOf(2291));
				return returnData;
			}
		}
		if (lstCourseActivities.size() == returnArray.length) {
			returnData.setSealFlag("1");
			return returnData;
		} else {
			returnData.setSealFlag("0");
			return returnData;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstCourseActivities List<CourseActivities>
	 *
	 * 
	 */

	public CourseActivities courseActUpdateCourseActivities(final List<CourseActivities> lstCourseActivities) {
		String sql = getQuery("OCMSUWPJ_COURSEACT_UPDATE_COURSE_ACTIVITIES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		CourseActivities returnData = new CourseActivities();
		for (CourseActivities courseActivities : lstCourseActivities) {
			parameters.add(new BeanPropertySqlParameterSource(courseActivities));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			String error = "Error : " + e.getMessage();
			if (error.contains("constraint")) {

				error = error.substring(error.indexOf("constraint"), error.indexOf("\" on"))
						.replaceFirst("constraint", "").trim();
				final String tableName = errorNameValidation(error.substring(1, error.length()));
				returnData.setSealFlag(tableName);
				returnData.setSeqOne(BigDecimal.valueOf(2292));
				return returnData;
			}
		}

		if (lstCourseActivities.size() == returnArray.length) {
			returnData.setSealFlag("1");
			return returnData;
		} else {
			returnData.setSealFlag("0");
			return returnData;
		}
	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstCourseActivities List<CourseActivities>
	 *
	 */
	public CourseActivities courseActDeleteCourseActivities(final List<CourseActivities> lstCourseActivities) {
		String sql = getQuery("OCMSUWPJ_COURSEACT_DELETE_COURSE_ACTIVITIES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		CourseActivities returnData = new CourseActivities();
		for (CourseActivities courseActivities : lstCourseActivities) {
			parameters.add(new BeanPropertySqlParameterSource(courseActivities));
		}
		try {
			String tableName = "COURSE_ACTIVITIES";
			String whereCondition = "CRS_ACTY_ID=:crsActyId";
			batchUpdatePreDeletedRows(tableName, whereCondition, parameters);
		} catch (Exception e) {
			logger.error(e);
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			String error = "Error : " + e.getMessage().toUpperCase();
			if (error.contains("CSR_ACTY_AREA_CRS_ACTY_FK")) {

				returnData.setSealFlag("course_activity_areas");
				returnData.setSeqOne(BigDecimal.valueOf(2292));
				return returnData;
			}else if(error.contains("CRS_SCH_RULE_CRS_ACTY_FK")) {
				returnData.setSealFlag("COURSE_SCHEDULE_RULES");
				returnData.setSeqOne(BigDecimal.valueOf(2292));
				return returnData;
			} else if (error.contains("CRS_ACTY_PROF_CRS_ACTY_FK")) {
				returnData.setSealFlag("5");
				return returnData;
			}
		}
		if (lstCourseActivities.size() == returnArray.length) {
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
	 * @return List<M>
	 */
	public List<Teams> rgTeamRecordGroup(String user) {
		final String sql = getQuery("OCMSUWPJ_FIND_RGTEAM");
		final RowMapper<Teams> mRowMapper = Row2BeanRowMapper.makeMapping(sql, Teams.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams("createUserId",user), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgBeneficiaryTypeRecordGroup() {
		final String sql = getQuery("OCMSUWPJ_FIND_RGBENEFICIARYTYPE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<Corporates> rgPlacementNameRecordGroup() {
		final String sql = getQuery("OCMSUWPJ_FIND_RGPLACEMENTNAME");
		final RowMapper<Corporates> mRowMapper = Row2BeanRowMapper.makeMapping(sql, Corporates.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<VCorporateAddresses> rgPlacementAddressesRecordGroup(final BigDecimal placementCorporateId) {
		List<VCorporateAddresses> courtList = new ArrayList<VCorporateAddresses>();
		final String sql = getQuery("OCMSUWPJ_FIND_RGPLACEMENTADDRESSES");
		final RowMapper<VCorporateAddresses> mRowMapper = Row2BeanRowMapper.makeMapping(sql, VCorporateAddresses.class,
				mMapping);

		try {
			courtList = namedParameterJdbcTemplate.query(sql,
					createParams("PLACEMENTCORPORATEID", placementCorporateId), mRowMapper);
		} catch (EmptyResultDataAccessException e) {

		}
		return courtList;

	}

	public Integer getCodeUniqueCntCur(final Long pTeamId, final String pCode) {
		Integer returnValue = 0;
		final String sql = getQuery("OCMSUWPJ_GET_CODE_UNIQUE_CNT_CUR");
		try {
			returnValue = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("P_TEAM_ID", pTeamId, "P_CODE", pCode), Integer.class);
		} catch (EmptyResultDataAccessException e) {
		}
		return returnValue;

	}

	public Long getCrsActyIdCur() {
		long returnValue = 0;
		final String sql = getQuery("OCMSUWPJ_GET_CRS_ACTY_ID_CUR");
		try {
			returnValue = namedParameterJdbcTemplate.queryForObject(sql, createParams(), Long.class);
		} catch (EmptyResultDataAccessException e) {
		}
		return returnValue;

	}

	public List<ProgramServices> getProgramId() {
		List<ProgramServices> returnValue = null;
		final String sql = getQuery("OCMSUWPJ_GET_PROGRAM_ID");
		try {
			
			returnValue = namedParameterJdbcTemplate.query(sql, createParams(), Row2BeanRowMapper.makeMapping(sql, ProgramServices.class, mMapping));
		} catch (EmptyResultDataAccessException e) {
			logger.error("getProgramId"+e);
		}
		return returnValue;
	}
	
	

	public CourseActivities getPlacementDetails(final CourseActivities objSearchDao) {
		Map<String, Object> returnObject = null;
		CourseActivities dataAvail = new CourseActivities();
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[3];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_CORPORATE_ID", Types.NUMERIC),
				new SqlParameter("P_SERVICE_ADDRESS_ID", Types.NUMERIC),
				new SqlOutParameter("P_PLACEMENT_NAME", Types.VARCHAR),
				new SqlOutParameter("P_HOUSE_INFO", Types.VARCHAR), new SqlOutParameter("P_STREET_INFO", Types.VARCHAR),
				new SqlOutParameter("P_AREA_INFO", Types.VARCHAR), new SqlOutParameter("P_POSTAL_CODE", Types.VARCHAR),
				new SqlOutParameter("P_COUNTRY", Types.VARCHAR), };
		final SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("OCMSUWPJ").withProcedureName("GET_PLACEMENT_DETAILS")
				.declareParameters(sqlParameters);
		inParamMap.put("P_CORPORATE_ID", objSearchDao.getPlacementCorporateId());
		inParamMap.put("P_SERVICE_ADDRESS_ID", objSearchDao.getServicesAddressId());

		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);

		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			dataAvail.setName((String) returnObject.get("P_PLACEMENT_NAME"));
			dataAvail.setHouseInformation((String) returnObject.get("P_HOUSE_INFO"));
			dataAvail.setStreetInformation((String) returnObject.get("P_STREET_INFO"));
			dataAvail.setAreaInformation((String) returnObject.get("P_AREA_INFO"));
			dataAvail.setPostalCode((String) returnObject.get("P_POSTAL_CODE"));
			dataAvail.setCountry((String) returnObject.get("P_COUNTRY"));
		} catch (final Exception e) {
			return dataAvail;
		}
		return dataAvail;
	}

	@Override
	public Integer getUpdateScheduleDate(final CourseActivities offenderId) {
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("P_CRS_ACTY_ID", offenderId.getCrsActyId());
		inParamMap.put("P_SCHEDULE_END_DATE", offenderId.getScheduleEndDate());
		try {
			namedParameterJdbcTemplate.update(
					" CALL OMS_OWNER.TAG_UNPAID_WORK.UPDATE_ENDDATE(:P_CRS_ACTY_ID, :P_SCHEDULE_END_DATE) ",
					inParamMap);
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}

	public BigDecimal getAllocExists(final CourseActivities bean) {
		Map<String, Object> returnObject = null;
		BigDecimal count = null;
		final Map<String, Object> inParamMap = new HashMap<>();
		SqlParameter[] sqlParameters = new SqlParameter[3];
		sqlParameters = new SqlParameter[] { new SqlParameter("p_crs_acty_id", Types.NUMERIC),
				new SqlOutParameter("p_schedule_end_date", Types.DATE), };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_UNPAID_WORK").withFunctionName("ALLOC_EXISTS").declareParameters(sqlParameters);

		inParamMap.put("p_crs_acty_id", bean.getCrsActyId());
		inParamMap.put("p_schedule_end_date", bean.getScheduleEndDate());

		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);

		returnObject = simpleJDBCCall.execute(inParameter);
		count = (BigDecimal) returnObject.get("return");
		return count;
	}

	public String errorNameValidation(final String errorName) {
		final String sql = getQuery("OCMSUWPJ_CONSTRAINT_VALIDATIONS");
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
	
	public List<VProgramProviders> rgProviderRecordGroup(final String caseloadId,final String caseloadType,final String providerType,String user) {
		final String sql = getQuery("OCMSUWPJ_FIND_RGPROVIDER_FN");
		final RowMapper<VProgramProviders> mRowMapper = Row2BeanRowMapper.makeMapping(sql, VProgramProviders.class,
				mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql,
					createParams("createUserId",user),
					mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("rgProviderRecordGroup :OCMSUWPJ"+e);
			return Collections.emptyList();
		}
	}
	
	public List<IntLocUsageLocations> rgIntLocRecordGroup(final String agyLocId) {
		final String sql = getQuery("OCMSUWPJ_FIND_RGINTLOC");
		final RowMapper<IntLocUsageLocations> rowMaper = Row2BeanRowMapper.makeMapping(sql,
				IntLocUsageLocations.class, mIntMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams("agyLocID", agyLocId),
					rowMaper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("rgIntLocRecordGroup :OCMSUWPJ"+e);
			return Collections.emptyList();
		}
	}
	public List<ProgramServices> rgProgramTypeRecordGroup() {
		final String sql = getQuery("OCMSUWPJ_FIND_RGPROGRAMTYPE");
		final RowMapper<ProgramServices> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ProgramServices.class,
				mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("rgProgramTypeRecordGroup :ocmsuwpj"+e);
			return Collections.emptyList();
		}
	}
	
	public List<VProgramProviders> rgProviderRecordGroupTeam(final String caseloadId, final String caseloadType,
			final String providerType, String user) {

		String sql = getQuery("OCMSUWPJ_FIND_RGPROVIDER_TEAM_INTERNAL_QUERY");

		final RowMapper<VProgramProviders> mRowMapper = Row2BeanRowMapper.makeMapping(sql, VProgramProviders.class,
				mMapping);
		List<VProgramProviders> returnList = new ArrayList<VProgramProviders>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("caseloadid", caseloadId, "caseloadtype",
					caseloadType, "providertype", providerType, "createUserId", user), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("rgProviderRecordGroupTeam :ocmsuwpj" + e);
			return Collections.emptyList();
		}
		return returnList;
	}

	@Override
	public List<Corporates> getPlacementLocationByCaseload(String caseload) {
		final String sql = getQuery("OCMSUWPJ_GET_PLACEMENT_LOCATION_BY_CASELOAD_ID");
		final RowMapper<Corporates> mRowMapper = Row2BeanRowMapper.makeMapping(sql, Corporates.class,
				mMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("caseload",caseload), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("getPlacementLocationByCaseload :ocmsuwpj "+e);
			return Collections.emptyList();
		}
	}

}