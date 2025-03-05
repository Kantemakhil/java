package net.syscon.s4.inst.programswithoutschedules.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.im.beans.VAddresses;
import net.syscon.s4.inst.accreditedprograms.beans.VProgramProviders;
import net.syscon.s4.inst.casemanagement.beans.ProgramServices;
import net.syscon.s4.inst.programswithoutschedules.OcmxprogRepository;
import net.syscon.s4.inst.schedules.bean.IntLocUsageLocations;

/**
 * Class OcmxprogRepositoryImpl
 */
@Repository
public class OcmxprogRepositoryImpl extends RepositoryBase implements OcmxprogRepository {
	/**
	 * Logger object used to print the log in the file
	 */
	final private Logger log = LogManager.getLogger(OcmxprogRepositoryImpl.class);
	
	private final Map<String, FieldMapper> vAddressesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("NBT_PROVIDER_CODE", new FieldMapper("nbtProviderCode"))
			.put("NBT_PROVIDER_DESC", new FieldMapper("nbtProviderDesc"))
			.put("NBT_PROVIDER_ID", new FieldMapper("nbtProviderId")).put("ADDRESS_ID", new FieldMapper(" addressId "))
			.build();
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DECODE(PARTY_CLASS", new FieldMapper("        decode(partyClass "))
			.put("'INTINST'", new FieldMapper(" 'intinst' ")).put("DESCRIPTION", new FieldMapper("description"))
			.put("PARTY_CODE", new FieldMapper(" partyCode")).put("'TEAM'", new FieldMapper(" 'team' "))
			.put("NULL", new FieldMapper(" null")).put("PARTY_NAME", new FieldMapper("partyName"))
			.put("NBT_TYPE", new FieldMapper("nbtType")).put("PROGRAM_CODE", new FieldMapper("programCode"))
			.put("PARTY_ID", new FieldMapper("partyId")).put("CODE", new FieldMapper("code"))
			.put("ACTIVE_FLAG", new FieldMapper("activeFlag"))
			.put("LIST_SEQ", new FieldMapper("listSeq")).build();
	private final Map<String, FieldMapper> mIntMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("INTERNAL_LOCATION_CODE", new FieldMapper("internalLocationCode")).put("CODE", new FieldMapper("code"))
			.build();
	private final Map<String, FieldMapper> crsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description")).put("TEAM_CODE", new FieldMapper("teamCode"))
			.put("TEAM_ID", new FieldMapper("teamId")).put("CRS_ACTY_ID", new FieldMapper("crsActyId"))
			.put("CASELOAD_ID", new FieldMapper("caseloadId")).put("ACTIVE_FLAG", new FieldMapper("activeFlag"))
			.put("EXPIRY_DATE", new FieldMapper("expiryDate")).put("CAPACITY", new FieldMapper("capacity"))
			.put("SCHEDULE_START_DATE", new FieldMapper("scheduleStartDate"))
			.put("SCHEDULE_END_DATE", new FieldMapper("scheduleEndDate"))
			.put("CASELOAD_TYPE", new FieldMapper("caseloadType")).put("PROGRAM_ID", new FieldMapper("programId"))
			.put("PROVIDER_PARTY_ID", new FieldMapper("providerPartyId"))
			.put("PROVIDER_PARTY_CODE", new FieldMapper("providerPartyCode"))
			.put("COMMENT_TEXT", new FieldMapper("commentText")).put("CODE", new FieldMapper("code"))
			.put("HOLIDAY_FLAG", new FieldMapper("holidayFlag")).put("COURSE_CLASS", new FieldMapper("courseClass"))
			.put("COURSE_ACTIVITY_TYPE", new FieldMapper("courseActivityType"))
			.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId")).put("AGY_LOC_ID", new FieldMapper("agyLocId"))
			.put("SERVICES_ADDRESS_ID", new FieldMapper("servicesAddressId"))
			.put("INTERNAL_LOCATION_ID", new FieldMapper("internalLocationId"))
			.put("LIST_SEQ", new FieldMapper("listSeq"))
			.put("AGENCY_LOCATION_TYPE", new FieldMapper("agencyLocationType"))
			.put("PROVIDER_TYPE", new FieldMapper("providerType"))
			.put("NO_OF_SESSIONS", new FieldMapper("noOfSessions"))
			.put("SESSION_LENGTH", new FieldMapper("sessionLength"))
			.put("MULTI_PHASE_SCHEDULING_FLAG", new FieldMapper("multiPhaseSchedulingFlag"))
			.put("SCHEDULE_NOTES", new FieldMapper("scheduleNotes")).put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.put("ALLOW_DOUBLE_BOOK_FLAG", new FieldMapper("allowDoubleBookFlag"))
			.put("PROVIDER_PARTY_CLASS", new FieldMapper("providerPartyClass"))
			.put("PROGRAM_ID_VAL", new FieldMapper("programIdVal"))
			.put("INTERNAL_LOCATION_ID_VAL", new FieldMapper("internalLocationIdVal"))
			.put("COMMENT_TEMP", new FieldMapper("commentTemp"))
			.put("INTERNAL_LOCATION_ID_VAL_TEMP", new FieldMapper("internalLocationIdValTemp"))
			.build();
	/**
	 * Creates new OcmxprogRepositoryImpl class Object
	 */
	public OcmxprogRepositoryImpl() {
		//constructor
	}

	

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            CourseActivities
	 *
	 * @return List<CourseActivities>
	 *
	 * @throws SQLException
	 */
	public List<CourseActivities> crsActExecuteQuery(final CourseActivities objSearchDao) {
		final String sql = getQuery("OCMXPROG_CRSACT_FIND_COURSE_ACTIVITIES");
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		List<CourseActivities> returnList;
		final MapSqlParameterSource params = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" WHERE");
			if (objSearchDao.getCourseActivityType() != null
					&& !objSearchDao.getCourseActivityType().trim().equals("")) {
				sqlQuery.append(" COURSE_ACTIVITY_TYPE = :courseActivityType AND");
				params.addValue("courseActivityType", objSearchDao.getCourseActivityType());

			}
			if (objSearchDao.getProviderPartyId() != null) {
				sqlQuery.append(" PROVIDER_PARTY_CLASS in ('CORP','TEAM') AND");
				sqlQuery.append(" PROVIDER_PARTY_ID = :providerPartyId AND");
				params.addValue("providerPartyId", objSearchDao.getProviderPartyId());
			}

			if (objSearchDao.getProviderPartyCode() != null && !objSearchDao.getProviderPartyCode().trim().equals("")) {
				sqlQuery.append(" PROVIDER_PARTY_CLASS in ('AGY') AND");
				sqlQuery.append(" PROVIDER_PARTY_CODE = :providerPartyCode AND");
				params.addValue("providerPartyCode", objSearchDao.getProviderPartyCode());
				sqlQuery.append(" PROVIDER_PARTY_ID is null AND");
			}
			if (objSearchDao.getProgramId() != null) {
				sqlQuery.append(" PROGRAM_ID = :programId AND");
				params.addValue("programId", objSearchDao.getProgramId());
			}
			if (objSearchDao.getCode() != null && !objSearchDao.getCode().isEmpty()
					&& !objSearchDao.getCode().trim().equals("")) {
				sqlQuery.append(" CODE LIKE :code AND ");
				params.addValue("code", objSearchDao.getCode().trim());
			}
			if (objSearchDao.getDescription() != null && !objSearchDao.getDescription().isEmpty()
					&& !objSearchDao.getDescription().trim().equals("")) {
				sqlQuery.append(" DESCRIPTION LIKE :description AND ");
				params.addValue("description", objSearchDao.getDescription().trim());
			}
			if (objSearchDao.getExpiryDate() != null) {
//				sqlQuery.append("(TRUNC(EXPIRY_DATE)  LIKE to_date('"
//						+ new java.sql.Date(objSearchDao.getExpiryDate().getTime()) + "','yyyy/MM/dd'))" + " AND ");
				sqlQuery.append(" date_trunc('D',EXPIRY_DATE)::text  LIKE '"
						+ new java.sql.Date(objSearchDao.getExpiryDate().getTime()) + "%'" + " AND ");
			}
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("WHERE")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("AND")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		//preparedSql = preparedSql.concat(" Order by DECODE(active_flag,'Y','A','N','B', active_flag), code desc");
		preparedSql = preparedSql.concat("order by case when active_flag = 'Y' then 'A' when active_flag = 'N' then 'B' else active_flag end, code desc");
		final RowMapper<CourseActivities> CourseActivitiesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				CourseActivities.class, crsMapping);
		returnList = (ArrayList<CourseActivities>) namedParameterJdbcTemplate.query(preparedSql, params,
				CourseActivitiesRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstCourseActivities
	 *            List<CourseActivities>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public Integer crsActInsertCourseActivities(final List<CourseActivities> list) {
		final String sql = getQuery("OCMXPROG_CRSACT_INSERT_COURSE_ACTIVITIES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final CourseActivities courseActivities : list) {
			parameters.add(new BeanPropertySqlParameterSource(courseActivities));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));

		if (list.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstCourseActivities
	 *            List<CourseActivities>
	 *
	 * @throws SQLException
	 */
	public Integer crsActUpdateCourseActivities(final List<CourseActivities> list) {
		final String sql = getQuery("OCMXPROG_CRSACT_UPDATE_COURSE_ACTIVITIES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final CourseActivities courseActivities : list) {
			parameters.add(new BeanPropertySqlParameterSource(courseActivities));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (list.size() == returnArray.length) {
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
	public List<ReferenceCodes> rgPsProvTypeRecordGroup() {
		final String sql = getQuery("OCMXPROG_FIND_RGPSPROVTYPE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			log.error("rgPsProvTypeRecordGroup :ocmxprog"+e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<VProgramProviders> rgProviderRecordGroup(final String caseloadId,final String caseloadType,final String providerType,String user) {
		final String sql = getQuery("OCMXPROG_FIND_RGPROVIDER_FN");
		try {
			return namedParameterJdbcTemplate.query(sql, 
					createParams("caseLoadId",caseloadId ),
					new BeanPropertyRowMapper<VProgramProviders>(VProgramProviders.class));
		} catch (EmptyResultDataAccessException e) {
			log.error("rgProviderRecordGroup :ocmxprog"+e);
			return Collections.emptyList();
		}
	}

	public List<VProgramProviders> rgProviderRecordGroupTeam(final String caseloadId,final String caseloadType,
			final String providerType,String user) {
		String sql = null;
		if(providerType!=null && providerType.equals("INT")) {
			sql = getQuery("OCMXPROG_FIND_RGPROVIDER_TEAM");
		} else if(providerType!=null && providerType.equals("EXT")) {
			sql = getQuery("OCMXPROG_FIND_RGPROVIDER_TEAM_EXTERNAL");
		}
		final RowMapper<VProgramProviders> mRowMapper = Row2BeanRowMapper.makeMapping(sql, VProgramProviders.class,
				mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql,
					createParams("caseloadid", caseloadId, "caseloadtype", caseloadType, "providertype", providerType,"createUserId",user),
					mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			log.error("rgProviderRecordGroupTeam :ocmxprog"+e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ProgramServices> rgProgramTypeRecordGroup() {
		final String sql = getQuery("OCMXPROG_FIND_RGPROGRAMTYPE");
		final RowMapper<ProgramServices> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ProgramServices.class,
				mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			log.error("rgProgramTypeRecordGroup :ocmxprog"+e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<MMInternalLocationUsages>
	 */
	public List<IntLocUsageLocations> rgIntLocRecordGroup(final String agyLocId) {
		final String sql = getQuery("OCMXPROG_FIND_RGINTLOC");
		final RowMapper<IntLocUsageLocations> rowMaper = Row2BeanRowMapper.makeMapping(sql,
				IntLocUsageLocations.class, mIntMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams("agyLocID", agyLocId),
					rowMaper);
		} catch (EmptyResultDataAccessException e) {
			log.error("rgIntLocRecordGroup :ocmxprog"+e);
			return Collections.emptyList();
		}
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * createFormGlobals
	 *
	 * @param params
	 *
	 */
	public List<OmsModules> createFormGlobals(final OmsModules paramBean) {
		final String sql = getQuery("OCMXPROG_CREATE_FORM_GLOBALS");
		final RowMapper<OmsModules> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OmsModules.class, mMapping);
		return namedParameterJdbcTemplate.query(sql,createParams(), columnRowMapper);
		
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * getAddressId
	 *
	 * @param params
	 *
	 */
	public List<VAddresses> getAddressId(final VAddresses paramBean) {
		final String sql = getQuery("OCMXPROG_GET_ADDRESS_ID");
		final RowMapper<VAddresses> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, VAddresses.class,
				vAddressesMapping);
		return namedParameterJdbcTemplate.query(sql,createParams(), columnRowMapper);
		
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * getAddressId
	 *
	 * @param params
	 *
	 */
	public Long getCrsActyId() {
		Long count =null;
		final String sql = getQuery("OCMXPROG_GETCRS_ACTYID");
		try {
			count =  namedParameterJdbcTemplate.queryForObject(sql, createParams(), Long.class);
		} catch (Exception e) {
			log.error("getCrsActyId :ocmxprog"+e);
		}
		return count;
	}

	public Integer checkCodeExistOcmsvacp(final CourseActivities courseActivity) {
		Integer count = null;
		try {
			if (courseActivity.getProviderPartyId() != null) {
				final String sql = getQuery("OCMSVACP_CHECK_CODE_EXISTS");
				count =  namedParameterJdbcTemplate.queryForObject(sql, createParams("providerPartyId",
						courseActivity.getProviderPartyId(), "code", courseActivity.getCode()), Integer.class);
			} else {
				final String sql = getQuery("OCMSVACP_CHECK_CODE_EXISTS_PROVIDERCODE");
				count=  namedParameterJdbcTemplate.queryForObject(sql, createParams("providerPartyCode",
						courseActivity.getProviderPartyCode(), "code", courseActivity.getCode()), Integer.class);
			}
		} catch (Exception e) {
			log.error("checkCodeExistOcmsvacp :ocmxprog"+e);
		}
		return count;
	}

	public Long getAgyAddressID(final String providerPartyCode) {
		Long serviceAddressId = null;
		final String sql = getQuery("OCMXPROG_GET_ADDRESS_ID_AGY");
		try {
			serviceAddressId = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("providerPartyCode", providerPartyCode), Long.class);
		} catch (Exception e) {
			log.error("getAgyAddressID :ocmxprog"+e);
			serviceAddressId = null;
			return serviceAddressId;
		}
		return serviceAddressId;
	}

	public Long getTeamAddressId(final Long providerId,String user) {
		Long serviceAddressId = null;
		final String sql = getQuery("OCMXPROG_GET_ADDRESS_ID_TEAM");
		try {
			serviceAddressId = namedParameterJdbcTemplate.queryForObject(sql, createParams("providerId", providerId,"createUserId",user),
					Long.class);
		} catch (Exception e) {
			log.error("getTeamAddressId :ocmxprog"+e);
			serviceAddressId = null;
			return serviceAddressId;
		}
		return serviceAddressId;

	}

	public Long getCorpAddressId(final String providerPartyCode) {
		Long serviceAddressId = null;
		final String sql = getQuery("OCMXPROG_GET_ADDRESS_ID_CORP");
		try {
			serviceAddressId = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("providerPartyCode", providerPartyCode), Long.class);
		} catch (Exception e) {
			log.error("getCorpAddressId :ocmxprog"+e);
			serviceAddressId = null;
			return serviceAddressId;
		}
		return serviceAddressId;

	}



}
