package net.syscon.s4.cm.programsservices.maintenance.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.cm.programsservices.maintenance.OcmsvacpRepository;
import net.syscon.s4.cm.teamsworkflow.beans.TeamMembers;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.im.beans.VAddresses;
import net.syscon.s4.inst.accreditedprograms.beans.VCoursePhaseOfferings;
import net.syscon.s4.inst.accreditedprograms.beans.VProgramProviders;
import net.syscon.s4.inst.casemanagement.beans.ProgramServices;

/**
 * Class OcmsvacpRepositoryImpl
 * 
 * @version 1.0
 */
@Repository()
public class OcmsvacpRepositoryImpl extends RepositoryBase implements OcmsvacpRepository {


	/**
	 * Creates new OcmsvacpRepositoryImpl class Object
	 */
	public OcmsvacpRepositoryImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcmsvacpRepositoryImpl.class);
	private final Map<String, FieldMapper> mmteamMembersMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", 						new FieldMapper("description"))
			.put("TEAM_CODE", 							new FieldMapper("teamCode"))
			.put("TEAM_ID", 							new FieldMapper("teamId"))
			.put("LIST_SEQ", 							new FieldMapper("teamMemberId"))
			.put("CODE", 								new FieldMapper("code"))
			.build();
	private final Map<String, FieldMapper> vCoursePhaseOfferingsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", 						new FieldMapper("description"))
			.put("TEAM_CODE", 							new FieldMapper("teamCode"))
			.put("TEAM_ID", 							new FieldMapper("teamId"))
			.put("CODE", 								new FieldMapper("code"))
			.put("COURSE_PHASE_ID", 					new FieldMapper("coursePhaseId"))
			.put("OFFERING_FLAG", 						new FieldMapper("offeringFlag"))
			.put("PH_LIST_SEQ", 						new FieldMapper("phListSeq"))
			.put("PH_DESCRIPTION", 						new FieldMapper("phDescription"))
			.put("PH_CAPACITY", 						new FieldMapper("phCapacity"))
			.put("PH_NO_OF_SESSIONS", 					new FieldMapper("phNoOfSessions"))
			.put("PH_SESSION_LENGTH", 					new FieldMapper("phSessionLength"))
			.put("PH_MODULE_FLAG", 						new FieldMapper("phModuleFlag"))
			.put("CP_CASELOAD_TYPE", 					new FieldMapper("cpCaseLoadType"))
			.put("CP_CASELOAD_TYPE_DESC", 				new FieldMapper("cpCaseLoadTypeDesc"))
			.put("CP_INTERNAL_LOCATION_ID", 			new FieldMapper("crsInternalLocationId"))
			.put("CP_INTERNAL_LOCATION_DESC", 			new FieldMapper("cpInternalLocationDesc"))
			.put("CP_SERVICES_ADDRESS_ID", 				new FieldMapper("cpServicesAddressId"))
			.put("CP_LIST_SEQ", 						new FieldMapper("cpListSeq"))
			.put("CP_ACTIVE_FLAG", 						new FieldMapper("cpActiveFlag"))
			.put("CP_EXPIRY_DATE",						new FieldMapper("cpExpiryDate"))
			.put("CP_START_DATE", 						new FieldMapper("cpStartDate"))
			.put("CP_END_DATE", 						new FieldMapper("cpEndDate"))
			.put("CP_NO_OF_SESSIONS", 					new FieldMapper("cpNoOfSessions"))
			.put("CP_SESSION_LENGTH", 					new FieldMapper("cpSessionLength"))
			.put("CP_CAPACITY", 						new FieldMapper("cpCapacity"))
			.put("CP_PLACEMENT_CORPORATE_ID", 			new FieldMapper("cpPlacementCorporateId"))
			.put("CP_COMMENT_TEXT", 					new FieldMapper("cpCommentText"))
			.put("CP_COURSE_ACTIVITY_TYPE", 			new FieldMapper("cpCourseActivityType"))
			.put("CP_CHECK_SUM", 						new FieldMapper("cpCheckSum"))
			.put("PROGRAM_ID", 							new FieldMapper("programId"))
			.put("PROGRAM_PHASE_ID", 					new FieldMapper("programPhaseId"))
			.put("COURSE_ID", 							new FieldMapper("courseId"))
			.put("COURSE_CASELOAD_TYPE", 				new FieldMapper("courseCaseLoadType"))
			.put("PROVIDER_PARTY_CLASS",	 			new FieldMapper("providerPartyClass"))
			.put("PROVIDER_PARTY_ID", 					new FieldMapper("providerPartyId"))
			.put("PROVIDER_PARTY_CODE", 				new FieldMapper("providerPartyCode"))
			.build();
	private final Map<String, FieldMapper> courseActivitiesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", 						new FieldMapper("description"))
			.put("TEAM_CODE", 							new FieldMapper("teamCode"))
			.put("TEAM_ID", 							new FieldMapper("teamId"))
			.put("CRS_ACTY_ID", 						new FieldMapper("crsActyId"))
			.put("CASELOAD_ID", 						new FieldMapper("caseloadId"))
			.put("ACTIVE_FLAG", 						new FieldMapper("activeFlag"))
			.put("EXPIRY_DATE", 						new FieldMapper("expiryDate"))
			.put("CAPACITY", 							new FieldMapper("capacity"))
			.put("SCHEDULE_START_DATE", 				new FieldMapper("scheduleStartDate"))
			.put("SCHEDULE_END_DATE", 					new FieldMapper("scheduleEndDate"))
			.put("CASELOAD_TYPE", 						new FieldMapper("caseloadType"))
			.put("PROGRAM_ID", 							new FieldMapper("programId"))
			.put("PROVIDER_PARTY_ID", 					new FieldMapper("providerPartyId"))
			.put("PROVIDER_PARTY_CODE", 				new FieldMapper("providerPartyCode"))
			.put("COMMENT_TEXT", 						new FieldMapper("commentText"))
			.put("CODE", 								new FieldMapper("code"))
			.put("HOLIDAY_FLAG", 						new FieldMapper("holidayFlag"))
			.put("COURSE_CLASS", 						new FieldMapper("courseClass"))
			.put("COURSE_ACTIVITY_TYPE", 				new FieldMapper("courseActivityType"))
			.put("CREATE_DATETIME", 					new FieldMapper("createDatetime"))
			.put("CREATE_USER_ID", 						new FieldMapper("createUserId"))
			.put("MODIFY_DATETIME", 					new FieldMapper("modifyDatetime"))
			.put("MODIFY_USER_ID", 						new FieldMapper("modifyUserId"))
			.put("AGY_LOC_ID", 							new FieldMapper("agyLocId"))
			.put("SERVICES_ADDRESS_ID", 				new FieldMapper("servicesAddressId"))
			.put("INTERNAL_LOCATION_ID", 				new FieldMapper("internalLocationId"))
			.put("LIST_SEQ", 							new FieldMapper("listSeq"))
			.put("AGENCY_LOCATION_TYPE", 				new FieldMapper("agencyLocationType"))
			.put("PROVIDER_TYPE", 						new FieldMapper("providerType"))
			.put("NO_OF_SESSIONS", 						new FieldMapper("noOfSessions"))
			.put("SESSION_LENGTH", 						new FieldMapper("sessionLength"))
			.put("MULTI_PHASE_SCHEDULING_FLAG", 		new FieldMapper("multiPhaseSchedulingFlag"))
			.put("SCHEDULE_NOTES", 						new FieldMapper("scheduleNotes"))
			.put("SEAL_FLAG", 							new FieldMapper("sealFlag"))
			.put("ALLOW_DOUBLE_BOOK_FLAG", 				new FieldMapper("allowDoubleBookFlag"))
			.put("PROVIDER_PARTY_CLASS", 				new FieldMapper("providerPartyClass"))
			.put("INTERNAL_LOCATION_ID_VAL", 				new FieldMapper("internalLocationIdVal"))
			.build();
	private final Map<String, FieldMapper> mminternalLocationUsagesmmmMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("STREET_INFORMATION", 					new FieldMapper("streetInformation"))
			.put("FULL_ADDRESS", 						new FieldMapper("fullAddress"))
			.put("DESCRIPTION", 						new FieldMapper("description"))
			.put("SUITE_NUMBER", 						new FieldMapper("suiteNumber"))
			.put("ZIP_POSTAL_CODE", 					new FieldMapper("zipPostalCode"))
			.put("AREA", 								new FieldMapper("area"))
			.put("COUNTRY", 							new FieldMapper("country"))
			.put("CODE", 								new FieldMapper("code"))
			.put("ADDRESS_LINE1", new FieldMapper("streetAddress"))
			.build();
	private final Map<String, FieldMapper> mminternalLocationUsagesmmMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("STREET_INFORMATION", 					new FieldMapper("streetInformation"))
			.put("FULL_ADDRESS", 						new FieldMapper("fullAddress"))
			.put("DESCRIPTION", 						new FieldMapper("description"))
			.put("SUITE_NUMBER", 						new FieldMapper("suiteNumber"))
			.put("ZIP_POSTAL_CODE", 					new FieldMapper("zipPostalCode"))
			.put("AREA", 								new FieldMapper("area"))
			.put("COUNTRY", 							new FieldMapper("country"))
			.put("CODE", 								new FieldMapper("code"))
			.put("ADDRESS_LINE1", new FieldMapper("streetAddress"))
			.build();

	private final Map<String, FieldMapper> mminternalLocationUsagesmMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CORPORATE_NAME", 						new FieldMapper("corporateName"))
			.put("STREET_INFORMATION", 					new FieldMapper("streetInformation"))
			.put("FULL_ADDRESS", 						new FieldMapper("fullAddress"))
			.put("SUITE_NUMBER", 						new FieldMapper("suiteNumber"))
			.put("ZIP_POSTAL_CODE", 					new FieldMapper("zipPostalCode"))
			.put("AREA", 								new FieldMapper("area"))
			.put("ADDRESS_ID", 							new FieldMapper("addressId"))
			.put("COUNTRY", 							new FieldMapper("country"))
			.put("ADDR", 								new FieldMapper("addr"))
			.put("AGENCY", 								new FieldMapper("agency"))
			.put("DESCRIPTION",	 						new FieldMapper("description"))
			.put("CODE", 								new FieldMapper("code"))
			.put("ADDRESS_LINE1", new FieldMapper("streetAddress"))
			.build();
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DECODE(PARTY_CLASS", 					new FieldMapper("decode(partyClass"))
			.put("'INTINST'", 							new FieldMapper("intinst"))
			.put("PROGRAM_CODE", 						new FieldMapper("programCode"))
			.put("DESCRIPTION", 						new FieldMapper("description"))
			.put("PARTY_CODE", 							new FieldMapper("partyCode"))
			.put("'TEAM'", 								new FieldMapper("team"))
			.put("NULL", 								new FieldMapper("null"))
			.put("PARTY_NAME", 							new FieldMapper("partyName"))
			.put("NBT_TYPE", 							new FieldMapper("nbtType"))
			.put("PARTY_ID", 							new FieldMapper("partyId"))
			.put("CORPORATE_NAME", 			new FieldMapper("corporateName"))
			.put("CORPORATE_ID", 			new FieldMapper("corporateId"))
			.put("PROVIDER_PARTY_ID", 		new FieldMapper("providerPartyId"))
			.put("AREA", 					new FieldMapper("area"))
			.put("COUNTRY", 				new FieldMapper("country"))
			.put("CODE", 					new FieldMapper("code"))
			.put("TEAM_CODE", 				new FieldMapper("teamCode"))
			.put("ZIP_POSTAL_CODE", 		new FieldMapper("zipPostalCode"))
			.put("HOUSE", 					new FieldMapper("house"))
			.put("STREET", 					new FieldMapper("street"))
			.put("TEAM_ID", 				new FieldMapper("teamId"))
			.put("LIST_SEQ", 							new FieldMapper("listSeq"))
			.put("CP_SERVICES_ADDRESS_ID", 				new FieldMapper("cpServicesAddressId"))
			.put("ACTIVE_FLAG", 				new FieldMapper("activeFlag"))
			.put("ADDRESS_LINE1", new FieldMapper("streetAddress"))
			.build();
	private final Map<String, FieldMapper> mminternalLocationUsagesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", 						new FieldMapper("description"))
			.put("CODE", 								new FieldMapper("code"))
			.put("INTERNAL_LOCATION_ID", 				new FieldMapper("internalLocationId"))
			.build();

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao CourseActivities
	 *
	 * @return List<CourseActivities>
	 *
	 * 
	 */
	public List<CourseActivities> crsActExecuteQuery(CourseActivities objSearchDao) {
		final String sql = getQuery("OCMSVACP_CRSACT_FIND_COURSE_ACTIVITIES");
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		List<CourseActivities> returnList;
		final MapSqlParameterSource params = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" WHERE");
			if (objSearchDao.getCourseActivityType() != null
					&& !objSearchDao.getCourseActivityType().trim().equals("")) {
				sqlQuery.append(" COURSE_ACTIVITY_TYPE = :courseActivityType and");
				params.addValue("courseActivityType", objSearchDao.getCourseActivityType());

			}
			if (objSearchDao.getProviderPartyId() != null) {
				sqlQuery.append(" PROVIDER_PARTY_CLASS in ('CORP','TEAM') and");
				sqlQuery.append(" PROVIDER_PARTY_ID = :providerPartyId and");
				params.addValue("providerPartyId", objSearchDao.getProviderPartyId());
			}

			if (objSearchDao.getProviderPartyCode() != null && !objSearchDao.getProviderPartyCode().trim().equals("")) {
				sqlQuery.append(" PROVIDER_PARTY_CLASS in ('AGY') and");
				sqlQuery.append(" PROVIDER_PARTY_CODE = :providerPartyCode and");
				params.addValue("providerPartyCode", objSearchDao.getProviderPartyCode());
				sqlQuery.append(" PROVIDER_PARTY_ID is null and");
			}
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("WHERE")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("and")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		preparedSql = preparedSql.concat(" ORDER BY active_flag desc, schedule_start_date desc");
		final RowMapper<CourseActivities> CourseActivitiesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				CourseActivities.class, courseActivitiesMapping);
		returnList = (ArrayList<CourseActivities>) namedParameterJdbcTemplate.query(preparedSql, params,
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
	public Integer crsActInsertCourseActivities(final List<CourseActivities> lstCourseActivities) {
		String sql = getQuery("OCMSVACP_CRSACT_INSERT_COURSE_ACTIVITIES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final CourseActivities courseActivities : lstCourseActivities) {
			parameters.add(new BeanPropertySqlParameterSource(courseActivities));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("Error in Class " + this.getClass().getName() + " crsActInsertCourseActivities error :: " + e);
		}
		if (lstCourseActivities.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}
	
	public List<VCoursePhaseOfferings> addressExecuteQuery(VCoursePhaseOfferings objSearchDao) {
		final String sql = getQuery("ADDRESS_FIND_BY_OWN");
		final RowMapper<VCoursePhaseOfferings> CourseActivitiesRowMapper = Row2BeanRowMapper.makeMapping(sql, VCoursePhaseOfferings.class,
				mMapping);
		List<VCoursePhaseOfferings> returnList = new ArrayList<VCoursePhaseOfferings>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("courseId", objSearchDao.getCourseId(),"programPhaseId",objSearchDao.getProgramPhaseId()), CourseActivitiesRowMapper);
		} catch (Exception e) {
			logger.error("", e);
		}
		return returnList;
	}
	
	public List<VAddresses> addressExecuteQueryDialog(VAddresses objSearchDao) {
		final String sql = getQuery("ADDRESS_FIND_BY_OWN_DIALOG");
		final RowMapper<VAddresses> CourseActivitiesRowMapper = Row2BeanRowMapper.makeMapping(sql, VAddresses.class,
				mMapping);
		List<VAddresses> returnList = new ArrayList<VAddresses>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("ProviderPartyId", objSearchDao.getOwnerId()), CourseActivitiesRowMapper);
		} catch (Exception e) {
			logger.error("", e);
		}
		return returnList;
	}
	


	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstCourseActivities List<CourseActivities>
	 *
	 * 
	 */
	public Integer crsActUpdateCourseActivities(final List<CourseActivities> lstCourseActivities) {
		String sql = getQuery("OCMSVACP_CRSACT_UPDATE_COURSE_ACTIVITIES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (CourseActivities courseActivities : lstCourseActivities) {
			parameters.add(new BeanPropertySqlParameterSource(courseActivities));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstCourseActivities.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstCourseActivities List<CourseActivities>
	 *
	 * 
	 */
	public Integer crsActDeleteCourseActivities(final List<CourseActivities> lstCourseActivities) {
		String sql = getQuery("OCMSVACP_CRSACT_DELETE_COURSE_ACTIVITIES");
		int[] returnArray = new int[] {};
		CourseActivities  returnData= new CourseActivities();
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (CourseActivities courseActivities : lstCourseActivities) {
			parameters.add(new BeanPropertySqlParameterSource(courseActivities));
		}
		try {
			String tableName = "COURSE_ACTIVITIES";
			String whereClause = "CRS_ACTY_ID = :crsActyId";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method crsActDeleteCourseActivities", e);
		}
		try {
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		}
		catch(Exception e) {
			String error = "Error : " + e.getMessage();
			if (error.contains("csr_acty_area_crs_acty_fk")) {
				returnData.setSealFlag(error);
				returnData.setListSeq(2292L);
				return 3;
			} else {
				returnData.setSealFlag(error);
				returnData.setListSeq(2292L);
				return 3;
			}
		}
		if (lstCourseActivities.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao VCoursePhaseOfferings
	 *
	 * @return List<VCoursePhaseOfferings>
	 *
	 * 
	 */
	public List<VCoursePhaseOfferings> vCrsPhsExecuteQuery(CourseActivities objSearchDao) {
		final String sql = getQuery("OCMSVACP_VCRSPHS_FIND_V_COURSE_PHASE_OFFERINGS");
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		List<VCoursePhaseOfferings> returnList;
		final MapSqlParameterSource params = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" WHERE");
			if (objSearchDao.getCrsActyId() != null) {
				sqlQuery.append(" COURSE_ID = :courseId and");
				params.addValue("courseId", objSearchDao.getCrsActyId());
			}

		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("WHERE")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("and")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		preparedSql = preparedSql.concat(" ORDER BY offering_flag desc, coalesce(cp_list_seq, ph_list_seq)");
		final RowMapper<VCoursePhaseOfferings> VCoursePhaseOfferingsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				VCoursePhaseOfferings.class, vCoursePhaseOfferingsMapping);
		returnList = (ArrayList<VCoursePhaseOfferings>) namedParameterJdbcTemplate.query(preparedSql, params,
				VCoursePhaseOfferingsRowMapper);
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
	 * @param lstVCoursePhaseOfferings List<VCoursePhaseOfferings>
	 *
	 * 
	 */
	public VCoursePhaseOfferings vCrsPhsUpdateVCoursePhaseOfferings(final List<VCoursePhaseOfferings> lstVCoursePhaseOfferings) {
		String sql = getQuery("OCMSVACP_VCRSPHS_UPDATE_V_COURSE_PHASE_OFFERINGS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		VCoursePhaseOfferings returnData = new VCoursePhaseOfferings();
		for (VCoursePhaseOfferings vCoursePhaseOfferings : lstVCoursePhaseOfferings) {
			parameters.add(new BeanPropertySqlParameterSource(vCoursePhaseOfferings));
		}try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0] ));
		}catch (Exception e){
			String error = "Error : " + e.getMessage();
			if (error.contains("OMS_OWNER")) {
				error = error.substring(error.indexOf("OMS_OWNER."), error.indexOf(")")).replaceFirst("OMS_OWNER.", "");
				returnData.setSealFlag(error);
				returnData.setListSeq(BigDecimal.valueOf(2292));
				return returnData;
			}
		}
		if (lstVCoursePhaseOfferings.size() == returnArray.length) {
			returnData.setSealFlag("1");
			return returnData;
		} else {
			returnData.setSealFlag("0");
			return returnData;
		}

	}
	@Override
	public String errorNameValidation(final String errorName) {
		final String sql = getQuery("OCMSVACP_CONSTRAINT_VALIDATIONS_NEW");
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
	
	public String errorNameValidation1(final String errorName) {
		final String sql = getQuery("OCMSVACP_CONSTRAINT_VALIDATIONS_CRS_ACT_DELETE");
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
	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgRefCodeRecordGroup() {
		final String sql = getQuery("OCMSVACP_FIND_RGREFCODE");
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
	public List<VProgramProviders> rgProviderRecordGroup(String providerType, String caseLoadType, String caseLoadId,String userId) {
		final String sql = getQuery("OCMSVACP_FIND_RGPROVIDER_FN");
		final RowMapper<VProgramProviders> mRowMapper = Row2BeanRowMapper.makeMapping(sql, VProgramProviders.class,
				mMapping);
		try {
			return namedParameterJdbcTemplate.query(sql,
					createParams("providerType", providerType, "caseLoadType", caseLoadType, "caseLoadId", caseLoadId,"userId",userId),
					mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<MMTeamMembers>
	 */
	public List<TeamMembers> rgTeamAgyLocsRecordGroup(String user,String caseloadId) {
		final String sql = getQuery("OCMSVACP_FIND_RGTEAMAGYLOCS");
		final RowMapper<TeamMembers> mMTeamMembersRowMapper = Row2BeanRowMapper.makeMapping(sql, TeamMembers.class,
				mmteamMembersMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("createUserId",user,"caseloadId",caseloadId), mMTeamMembersRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<MMTeamMembersM>
	 */
	public List<TeamMembers> rgCorpLocsRecordGroup() {
		final String sql = getQuery("OCMSVACP_FIND_RGCORPLOCS");
		final RowMapper<TeamMembers> mMTeamMembersMRowMapper = Row2BeanRowMapper.makeMapping(sql, TeamMembers.class,
				mmteamMembersMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mMTeamMembersMRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<AgencyLocations> rgAgyLocsRecordGroup(String caseLoadId) {
		final String sql = getQuery("OCMSVACP_FIND_RGAGYLOCS");
		final RowMapper<AgencyLocations> mRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				mMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("caseLoadId", caseLoadId), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ProgramServices> rgAccProgramRecordGroup() {
		final String sql = getQuery("OCMSVACP_FIND_RGACCPROGRAM");
		final RowMapper<ProgramServices> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ProgramServices.class,
				mMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<MMInternalLocationUsages>
	 */
	public List<StaffMembers> rgIntLocationRecordGroup(String providerPartyCode) {
		final String sql = getQuery("OCMSVACP_FIND_RGINTLOCATION");
		final RowMapper<StaffMembers> mMInternalLocationUsagesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				StaffMembers.class, mminternalLocationUsagesMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("providerpartycode", providerPartyCode),
					mMInternalLocationUsagesRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<MMInternalLocationUsagesM>
	 */
	public List<VAddresses> rgAddressRecordGroup(Long providerPartyId) {
		final String sql = getQuery("OCMSVACP_FIND_RGADDRESS");
		final RowMapper<VAddresses> mMInternalLocationUsagesMRowMapper = Row2BeanRowMapper.makeMapping(sql,
				VAddresses.class, mminternalLocationUsagesmMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("providerPartyId", providerPartyId),
					mMInternalLocationUsagesMRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<MMInternalLocationUsagesMM>
	 */
	public List<VAddresses> rgAgyAddressRecordGroup(String providerPartyCode) {
		final String sql = getQuery("OCMSVACP_FIND_RGAGYADDRESS");
		final RowMapper<VAddresses> mMInternalLocationUsagesMMRowMapper = Row2BeanRowMapper.makeMapping(sql,
				VAddresses.class, mminternalLocationUsagesmmMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("providerPartyCode", providerPartyCode),
					mMInternalLocationUsagesMMRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<MMInternalLocationUsagesMMM>
	 */
	public List<VAddresses> rgAllAgyAddressRecordGroup(Long providerPartyId) {
		final String sql = getQuery("OCMSVACP_FIND_RGALLAGYADDRESS");
		final RowMapper<VAddresses> mMInternalLocationUsagesMMMRowMapper = Row2BeanRowMapper.makeMapping(sql,
				VAddresses.class, mminternalLocationUsagesmmmMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("providerPartyId", providerPartyId),
					mMInternalLocationUsagesMMMRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}
	
	
	public Long getCrsActyId() {
		final String sql = getQuery("SELECT_CRS_ACTY_ID_NEXTVAL");

		Long returnData = null;
		try {
			returnData = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
					Long.class);
		} catch (Exception e) {
			return returnData;
		}
		return returnData;
	}
	
	public String getDescription(final CourseActivities paramBean) {
		final String sql = getQuery("SELECT_DESCRIPTION_FROM_AGENCY_INTERNAL_LOCATIONS");

		String returnData = null;
		try {
			returnData = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("internalLocationId", paramBean.getInternalLocationId()), String.class);
		} catch (Exception e) {
			return returnData;
		}
		return returnData;
	}
	
	public VAddresses getPlacementDetails(final VCoursePhaseOfferings objSearchDao) {
		final String sql = getQuery("OCMSVACP_V_ADDRESS_DETAILS");
		final RowMapper<VAddresses> mMInternalLocationUsagesMMRowMapper = Row2BeanRowMapper.makeMapping(sql,
				VAddresses.class, mMapping);
		VAddresses returnData = new VAddresses();
		try {
			returnData = namedParameterJdbcTemplate.queryForObject(sql, createParams("P_ADDRESS_ID", objSearchDao.getCpServicesAddressId()),
					mMInternalLocationUsagesMMRowMapper);
		} catch (Exception e) {
			return returnData;
		}
		return returnData;
	}

	public Boolean isCoursePhaseExists(CourseActivities bean) {
		final String sql = getQuery("ON_DELETE_VALIDATION");
		Boolean returnVal = false;
		try {
			returnVal = namedParameterJdbcTemplate.queryForObject(sql, createParams("crsActyId",bean.getCrsActyId()), Boolean.class);
		}
		catch(Exception e) {
			logger.error("isCoursePhaseExists", e);
		}
		return returnVal;
		
	}

	@Override
	public void deleteCourseActivityAreas(Long crsActyId) {
		
	}

	@Override
	public void deleteCourseActivityParties(Long crsActyId) {
		
	}

	@Override
	public void deleteCourseActivityProf(Long crsActyId) {
		
	}

	@Override
	public List<VAddresses> rgAllAddressDeatails(String caseLoadId) {
		final String sql = getQuery("OCMSVACP_FIND_RG_ALL_ADDRESS_DEATAILS");
		final RowMapper<VAddresses> mMInternalLocationUsagesMMMRowMapper = Row2BeanRowMapper.makeMapping(sql,
				VAddresses.class, mminternalLocationUsagesmmmMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("caseLoadId", caseLoadId),
					mMInternalLocationUsagesMMMRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	
}
