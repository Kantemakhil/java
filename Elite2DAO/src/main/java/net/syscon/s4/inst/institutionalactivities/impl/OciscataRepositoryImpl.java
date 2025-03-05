package net.syscon.s4.inst.institutionalactivities.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.jdbc.support.oracle.SqlReturnStruct;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.Areas;
import net.syscon.s4.im.beans.Corporates;
import net.syscon.s4.im.beans.OffenderNonAssociations;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.im.beans.ProgramsNonAssocTmp;
import net.syscon.s4.im.beans.Teams;
import net.syscon.s4.im.beans.VCourseActivities;
import net.syscon.s4.inst.casemanagement.beans.ProgramServices;
import net.syscon.s4.inst.institutionalactivities.OciscataRepository;
import net.syscon.s4.inst.movements.beans.OffRec;
import oracle.jdbc.OracleTypes;

/**
 * Class OciscataRepositoryImpl
 */
@Repository
public class OciscataRepositoryImpl extends RepositoryBase implements OciscataRepository {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OciscataRepositoryImpl.class.getName());

	@Autowired
	private DataSource ds;
	private final Map<String, FieldMapper> mmMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("TEAM_ID", new FieldMapper("teamId"))
			.put("CODE", new FieldMapper("code"))
			.build();

	private final Map<String, FieldMapper> programServicesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper(" description"))
			.put("P_PROGRAM_ID", new FieldMapper("pProgramId"))
			.put("DISTINC", new FieldMapper("distinc"))
			.put("OFFENDER_ID", new FieldMapper("offenderId"))
			.put("PROGRAM_ID", new FieldMapper("programId"))
			.build();

	private final Map<String, FieldMapper> referenceCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CODE", new FieldMapper("code"))
			.build();
	private final Map<String, FieldMapper> courseActivityPartiesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CRS_ACTY_ID", new FieldMapper("crsActyId"))
			.build();

	private final Map<String, FieldMapper> areasMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("NULL", new FieldMapper("null "))
			.put("AREA_COD", new FieldMapper("areaCod"))
			.build();

	private final Map<String, FieldMapper> mmmMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("TEAM_ID", new FieldMapper("teamId")
					).build();

	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CORPORATE_NAME", new FieldMapper("corporateName"))
			.put("AGY_LOC_ID", new FieldMapper("agyLocId"))
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("PROGRAM_ID", new FieldMapper("seqValue"))
			.put("TEAM_ID", new FieldMapper("teamId"))
			.put("CODE", new FieldMapper("code"))
			.put("TEAM_CODE", new FieldMapper("teamCode"))
			.put("PARENT_AREA_CODE", new FieldMapper("parentAreaCode"))
			.put("CORPORATE_ID", new FieldMapper("corporateId"))
			.build();

	private final Map<String, FieldMapper> AreaCodeMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("AREA_CODE", new FieldMapper("areaCode"))
			.put("DESCRIPTION", new FieldMapper("description "))
			.build();

	private final Map<String, FieldMapper> offenderBookingsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("P_PROGRAM_ID", new FieldMapper("pProgramId"))
			.put("DISTINC", new FieldMapper("distinc"))
			.put("OFFENDER_ID", new FieldMapper("offenderId"))
			.put("PROGRAM_ID", new FieldMapper("programId "))
			.build();

	private final Map<String, FieldMapper> vCourseActivitiesMap = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("P_PROGRAM_ID", new FieldMapper("pProgramId"))
			.put("DISTINC", new FieldMapper("distinc"))
			.put("OFFENDER_ID", new FieldMapper("offenderId"))
			.put("PROGRAM_ID", new FieldMapper("programId"))
			.build();
	
	private final Map<String, FieldMapper> progNonAssMap = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_BOOK_ID", new FieldMapper("description"))
			.put("LINE", new FieldMapper("pProgramId"))
			.put("OFFENDER_ID", new FieldMapper("offenderId"))
			.put("PROGRAM_ID", new FieldMapper("programId"))
			.put("CRS_ACTY_ID", new FieldMapper("crsActyId"))
			.build();
	

	/**
	 * Creates new OciscataRepositoryImpl class Object
	 */
	public OciscataRepositoryImpl() {
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            VCourseActivities
	 *
	 * @return List<VCourseActivities>
	 *
	 */
	public List<VCourseActivities> vCrsActExecuteQuery(final VCourseActivities objSearchDao) {
		final String sql = getQuery("OCISCATA_VCRSACT_FIND_V_COURSE_ACTIVITIES");
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		String agyLocIdParam = null;
		final MapSqlParameterSource params = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			if (objSearchDao.getProgramCategory() != null) {
				if ("ACP".equals(objSearchDao.getProgramCategory())){
					agyLocIdParam = "Y";
				} else {
					agyLocIdParam = "N";
				}
				}
			if (objSearchDao.getProgramCategory() != null 
					&& (objSearchDao.getNbtAgyLocId() == null && "ACP".equals(objSearchDao.getProgramCategory())
					  && "Y".equals(agyLocIdParam) )) {
				objSearchDao.setNbtAgyLocId("Y");
			}
			sqlQuery.append(" where ");
			if (objSearchDao.getEnvironment() != null) {
				sqlQuery.append("ENVIRONMENT = :ENVIRONMENT" + " and ");
				params.addValue("ENVIRONMENT", objSearchDao.getEnvironment());
			}
			if (objSearchDao.getProgramCategory() != null) {
				sqlQuery.append("PROGRAM_CATEGORY = :PROGRAM_CATEGORY" + " and ");
				params.addValue("PROGRAM_CATEGORY", objSearchDao.getProgramCategory());
			}
			if (objSearchDao.getProgramId() != null) {
				sqlQuery.append("PROGRAM_ID = :PROGRAM_ID" + " and ");
				params.addValue("PROGRAM_ID", objSearchDao.getProgramId());
			}
			if (objSearchDao.getProgramCategoryDesc() != null) {

				if (objSearchDao.getProviderId() != null || objSearchDao.getProviderName() != null) {
					if ("EXT".equals(objSearchDao.getProgramCategoryDesc())
							|| ( "COMM".equals(objSearchDao.getEnvironment())
							&& "INT".equals(objSearchDao.getProgramCategoryDesc())) && objSearchDao.getProviderId() != null) {

						sqlQuery.append("PROVIDER_ID = :PROVIDER_ID" + " and ");
						params.addValue("PROVIDER_ID", objSearchDao.getProviderId());
					} else {
						sqlQuery.append("PROVIDER_CODE = :PROVIDER_CODE" + " and ");
						params.addValue("PROVIDER_CODE", objSearchDao.getProviderName());
					}

				}
			}
			if (objSearchDao.getNbtAdvSearch() != null && "Y".equals(objSearchDao.getNbtAdvSearch())) {
				if (objSearchDao.getNbtGender() != null) {
					sqlQuery.append(
							"CRS_ACTY_ID   IN (SELECT  CRS_ACTY_ID FROM COURSE_ACTIVITY_PROFILES WHERE PROGRAM_PROFILE_TYPE = 'PS_SEX' AND PROGRAM_PROFILE_CODE = :NBT_SEX_CODE  )  and ");
					params.addValue("NBT_SEX_CODE", objSearchDao.getNbtGender());
				}

				if (objSearchDao.getNbtRace() != null) {
					sqlQuery.append(
							"CRS_ACTY_ID   IN (SELECT  CRS_ACTY_ID FROM COURSE_ACTIVITY_PROFILES WHERE PROGRAM_PROFILE_TYPE = 'PS_ETHNICITY' AND PROGRAM_PROFILE_CODE = :NBT_RACE  )   and ");
					params.addValue("NBT_RACE", objSearchDao.getNbtRace());
				}
				if (objSearchDao.getNbtAge() != null) {
					sqlQuery.append(
							"CRS_ACTY_ID   IN (SELECT  CRS_ACTY_ID FROM COURSE_ACTIVITY_PROFILES WHERE PROGRAM_PROFILE_TYPE = 'PS_AGE_RANGE' AND PROGRAM_PROFILE_CODE = :NBT_AGE  )   and ");
					params.addValue("NBT_AGE", objSearchDao.getNbtAge());
				}
				if (objSearchDao.getNbtFacility() != null) {
					sqlQuery.append(
							"CRS_ACTY_ID   IN (SELECT  CRS_ACTY_ID FROM COURSE_ACTIVITY_PROFILES WHERE PROGRAM_PROFILE_TYPE = 'PS_FACILITY' AND PROGRAM_PROFILE_CODE = :NBT_FACILITY  )  and ");
					params.addValue("NBT_FACILITY", objSearchDao.getNbtFacility());
				}
				if (objSearchDao.getNbtInclude() != null) {
					sqlQuery.append(
							"CRS_ACTY_ID   IN (SELECT  CRS_ACTY_ID FROM COURSE_ACTIVITY_PROFILES WHERE PROGRAM_PROFILE_TYPE = 'PS_INC_GRP' AND PROGRAM_PROFILE_CODE = :NBT_INCLUDE  )  and ");
					params.addValue("NBT_INCLUDE", objSearchDao.getNbtInclude());
				}
				if (objSearchDao.getNbtExclude() != null) {
					sqlQuery.append(
							"CRS_ACTY_ID   IN (SELECT  CRS_ACTY_ID FROM COURSE_ACTIVITY_PROFILES WHERE PROGRAM_PROFILE_TYPE = 'PS_EXC_GRP' AND PROGRAM_PROFILE_CODE = :NBT_EXCLUDE  )   and ");
					params.addValue("NBT_EXCLUDE", objSearchDao.getNbtExclude());
				}

			}

			if (objSearchDao.getNbtStatus() != null && !"ALL".equals(objSearchDao.getNbtStatus())) {
				if ("AVAIL".equals(objSearchDao.getNbtStatus())) {
					sqlQuery.append(
							"  ( coalesce(SCHEDULE_END_DATE,'31-DEC-2382') >= current_date AND ACTIVE_FLAG = 'Y' )  and ");
				} else if ("UNAVAIL".equals(objSearchDao.getNbtStatus())) {
					sqlQuery.append(
							" ( coalesce(SCHEDULE_END_DATE,'31-DEC-2382') <  current_date OR ACTIVE_FLAG = 'N' )  and ");
				}
			}
			
			if (!"WR".equals(objSearchDao.getProgramCategory())) {
				if (objSearchDao.getAreaCode() != null) {
					sqlQuery.append(
							"CRS_ACTY_ID   IN (SELECT  CRS_ACTY_ID FROM COURSE_ACTIVITY_AREAS WHERE ((AREA_CODE = :AREA_CODE)  OR (AREA_CODE = :PARENT_AREA_CODE)))"
									+ " and ");
					params.addValue("AREA_CODE", objSearchDao.getAreaCode());
					params.addValue("PARENT_AREA_CODE", objSearchDao.getParentAreaCode());
				} else {
					if (objSearchDao.getNbtRegion() != null) {
						sqlQuery.append(
								"CRS_ACTY_ID   IN (SELECT  CRS_ACTY_ID FROM COURSE_ACTIVITY_AREAS WHERE ((AREA_CODE = :NBT_REGION)  OR (AREA_CODE IN  (SELECT AREA_CODE "
										+ "FROM (select c.area_code, p.parent_area_code    from ( select area_code,parent_area_code  from areas  where parent_area_code is not null) c , "
										+ "  ( select area_code parent_area_code  from areas where parent_area_code is null and area_class = 'REGION') p  where p.parent_area_code = c.parent_area_code(+)"
										+ " union all   select c.area_code,NULL from areas c where area_class = 'REGION' and parent_area_code is null  )  WHERE PARENT_AREA_CODE =:NBT_REGION   ))))"
										+ " and ");
						params.addValue("NBT_REGION", objSearchDao.getNbtRegion());
					}
				}

			} else {

				if (objSearchDao.getNbtRegion() != null) {
					sqlQuery.append(
							"EXISTS (SELECT  1 FROM AGENCY_LOCATIONS AL WHERE  AL.AGY_LOC_ID = V_COURSE_ACTIVITIES.AGY_LOC_ID  AND AL.NOMS_REGION_CODE =:NBT_REGION ) and ");
					params.addValue("NBT_REGION", objSearchDao.getNbtRegion());

				}

				if (objSearchDao.getAreaCode() != null) {
					sqlQuery.append(
							" EXISTS (SELECT  1 FROM AGENCY_LOCATIONS AL WHERE AL.AGY_LOC_ID = V_COURSE_ACTIVITIES.AGY_LOC_ID  AND AL.AREA_CODE =:AREA_CODE )   and ");
					params.addValue("AREA_CODE", objSearchDao.getAreaCode());

				}

			}
			

			if ("ACP".equalsIgnoreCase(objSearchDao.getProgramCategory())) {
				sqlQuery.append("COURSE_CLASS = 'CRS_PH'" + " and ");
				if ("EXT".equalsIgnoreCase(objSearchDao.getProgramCategoryDesc())) {
					sqlQuery.append("PROVIDER_CLASS = 'CORP'" + " and ");
				} else {
					
					sqlQuery.append(" PROVIDER_CLASS IN ('TEAM','AGY')" + " and ");
					
				}
			}
			if ("PWS".equalsIgnoreCase(objSearchDao.getProgramCategory())) {
				sqlQuery.append("COURSE_CLASS = 'COURSE'" + " and ");
				if ("EXT".equalsIgnoreCase(objSearchDao.getProgramCategoryDesc())) {
					sqlQuery.append("PROVIDER_CLASS = 'CORP'" + " and ");
				} else {
					
					sqlQuery.append(" PROVIDER_CLASS IN ('TEAM','AGY')" + " and ");
					
					
					
				}
			}
			
			if ( agyLocIdParam != null && objSearchDao.getProgramCategory()!= null &&
					"WR".equals(objSearchDao.getProgramCategory())  ) {
				sqlQuery.append(" AGY_LOC_ID = :AGY_LOC_ID_PARAM " + " and ");
				params.addValue("AGY_LOC_ID_PARAM",objSearchDao.getAgyLocId());
			}

			if (objSearchDao.getNbtStatus() == null) {
				sqlQuery.append(" ACTIVE_FLAG='Y'  and coalesce(SCHEDULE_END_DATE,'31-DEC-2382') >= current_date and ");
			}
			if("EXT".equalsIgnoreCase(objSearchDao.getProgramCategoryDesc())) {
				sqlQuery.append("((provider_id IS NULL  ) OR( provider_id IS not null AND provider_id in(select c.corporate_id from corporates c))) and");
			}else if("INT".equalsIgnoreCase(objSearchDao.getProgramCategoryDesc())) {
				sqlQuery.append("((provider_id IS NULL  ) OR( provider_id IS not null AND provider_id in(select team_id from automation_teams))) and");
			}
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("where")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("and")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}

		if (objSearchDao != null && objSearchDao.getNbtCallingForm() != null
				&& "OCDUPROJ".equals(objSearchDao.getNbtCallingForm())) {
			preparedSql = preparedSql.concat(
					" order by  active_flag desc, tag_unpaid_work_get_pqs_value(offender_book_id,CRS_ACTY_ID) DESC ,"
							+ " schedule_start_date desc,course_activity_desc ");
		} else {
			preparedSql = preparedSql
					.concat(" order by  active_flag desc,schedule_start_date desc,course_activity_desc  ");
		}
		final RowMapper<VCourseActivities> vCourseActRM = Row2BeanRowMapper.makeMapping(sql,
				VCourseActivities.class, vCourseActivitiesMap);
		final ArrayList<VCourseActivities> returnList = (ArrayList<VCourseActivities>) namedParameterJdbcTemplate
				.query(preparedSql, params, vCourseActRM);
		return returnList;
	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstVCourseActivities
	 *            List<VCourseActivities>
	 *
	 * return Integer
	 */
	public Integer vCrsActUpdateVCourseActivities(final List<VCourseActivities> list) {
		final String sql = getQuery("OCISCATA_VCRSACT_UPDATE_V_COURSE_ACTIVITIES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final VCourseActivities vCourseActivities : list) {
			parameters.add(new BeanPropertySqlParameterSource(vCourseActivities));
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
	 * @param environment
	 * @param region
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgAreasRecordGroup(final String environment, final String region) {
		final String sql = getQuery("OCISCATA_FIND_RGAREAS");
		final RowMapper<ReferenceCodes> mapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		String strEnvironment = null;
		String strRegion = null;
		try {
			
			if (environment != null &&  !"undefined".equals(environment)  && !"NONE".equals(environment)) {
				strEnvironment = environment;
			 }
			if (region != null &&  !"undefined".equals(region) &&  !"NONE".equals(region)) {
				strRegion = region;
			 }
			return namedParameterJdbcTemplate.query(sql,
					createParams("NBTENVIRONMENTCODE", strEnvironment, "NBTREGIONCODE", strRegion), mapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("In rgAreasRecordGroup method : ", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgPsAgeRangeRecordGroup() {
		final String sql = getQuery("OCISCATA_FIND_RGPSAGERANGE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgPsAvailRecordGroup() {
		final String sql = getQuery("OCISCATA_FIND_RGPSAVAIL");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("In rgPsAvailRecordGroup method : ", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgPsCategoryRecordGroup() {
		final String sql = getQuery("OCISCATA_FIND_RGPSCATEGORY");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("In rgPsCategoryRecordGroup method : ", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgPsNeedsRecordGroup() {
		final String sql = getQuery("OCISCATA_FIND_RGPSNEEDS");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("In rgPsNeedsRecordGroup method : ", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgPsOffGrpsRecordGroup() {
		final String sql = getQuery("OCISCATA_FIND_RGPSOFFGRPS");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("In rgPsOffGrpsRecordGroup method : ", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgPsProvTypeRecordGroup() {
		final String sql = getQuery("OCISCATA_FIND_RGPSPROVTYPE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("In rgPsProvTypeRecordGroup method : ", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgPsSexRecordGroup() {
		final String sql = getQuery("OCISCATA_FIND_RGPSSEX");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("In rgPsSexRecordGroup method : ", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgEthnicityRecordGroup() {
		final String sql = getQuery("OCISCATA_FIND_RGETHNICITY");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("In rgEthnicityRecordGroup method : ", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgRegionRecordGroup() {
		final String sql = getQuery("OCISCATA_FIND_RGREGION");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("In rgRegionRecordGroup method : ", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgServicesRecordGroup(final String category) {
		final String sql = getQuery("OCISCATA_FIND_RGSERVICES");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams("PROGRAMCATEGORY", category), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("In rgServicesRecordGroup method : ", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgCsldCodeRecordGroup() {
		final String sql = getQuery("OCISCATA_FIND_RGCSLDCODE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("In rgCsldCodeRecordGroup method : ", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<Teams> rgTeamAgyLocsRecordGroup() {
		final String sql = getQuery("OCISCATA_FIND_RGTEAMAGYLOCS");
		final RowMapper<Teams> mRowMapper = Row2BeanRowMapper.makeMapping(sql, Teams.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("In rgTeamAgyLocsRecordGroup method : ", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @param category
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgCorpLocsRecordGroup(final String category) {
		final String sql = getQuery("OCISCATA_FIND_RGCORPLOCS");
		final RowMapper<Corporates> mMRowMapper = Row2BeanRowMapper.makeMapping(sql, Corporates.class,
				mMapping);
		final List<ReferenceCodes> lstRef = new ArrayList<ReferenceCodes>();
		ReferenceCodes objRef = null;
		try {
			final List<Corporates> lstCorporates = (List<Corporates>) namedParameterJdbcTemplate
					.query(sql, createParams("NBTPROGRAMCATEGORY", category), mMRowMapper);
			
			if (lstCorporates != null && lstCorporates.size() > 0) {
				for (final Corporates objCorportate : lstCorporates) {
				 objRef = new ReferenceCodes();
				 objRef.setCode(String.valueOf(objCorportate.getCorporateId()));
				 objRef.setDescription(objCorportate.getCorporateName());
				 lstRef.add(objRef);
				}
			}
		} catch (EmptyResultDataAccessException e) {
			logger.error("In rgCorpLocsRecordGroup method : ", e);
			return Collections.emptyList();
		}
		return lstRef;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgAgyLocsRecordGroup() {
		final String sql = getQuery("OCISCATA_FIND_RGAGYLOCS");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("In rgAgyLocsRecordGroup method : ", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgAgyLocClRecordGroup() {
		final String sql = getQuery("OCISCATA_FIND_RGAGYLOCCL");
		final RowMapper<ReferenceCodes> mMRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				mMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mMRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("In rgAgyLocClRecordGroup method : ", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgTeamUnpaidWkRecordGroup(final String userName) {
		final String sql = getQuery("OCISCATA_FIND_RGTEAMUNPAIDWK");
		final RowMapper<ReferenceCodes> mMMRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				mMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("userName", userName), mMMRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("In rgTeamUnpaidWkRecordGroup method : ", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgProviderDttoRecordGroup() {
		final String sql = getQuery("OCISCATA_FIND_RGPROVIDERDTTO");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("In rgProviderDttoRecordGroup method : ", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgTeamAcpRecordGroup(final String userName) {
		final String sql = getQuery("OCISCATA_FIND_RGTEAMACP");
		final RowMapper<ReferenceCodes> mMRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				mMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("userName",userName), mMRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("In rgTeamAcpRecordGroup method : ", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @param caseloadId
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgAcpProviderInstRecordGroup(final String caseloadId) {
		final String sql = getQuery("OCISCATA_FIND_RGACPPROVIDERINST");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("CASELOADID",caseloadId,"MODE","ENTER-QUERY"), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("In rgAcpProviderInstRecordGroup method : ", e);
			return Collections.emptyList();
		}
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @param paramBean
	 * @return List<Areas>
	 */
	public List<Areas> vCrsActPreQuery(final Areas paramBean) {
		final String sql = getQuery("OCISCATA_V_CRS_ACT_PREQUERY");
		final RowMapper<Areas> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Areas.class, areasMapping);
		final ArrayList<Areas> returnList = (ArrayList<Areas>) namedParameterJdbcTemplate.query(sql,
				createParams(paramBean), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @param paramBean
	 * @return List<CourseActivityParties>
	 */
	/**
	 * Used to capture results from select query
	 * 
	 * @return Integer
	 */
	public Integer vCrsActWhenNewRecordInstance(final Long crystalId) {
		final String sql = getQuery("OCISCATA_V_CRS_ACT_WHENNEWRECORDINSTANCE");
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("CRSACTYID", crystalId), Integer.class);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @param paramBean
	 * @return List<OffenderBookings>
	 */
	public List<OffenderBookings> ociscataWhenNewFormInstance(final OffenderBookings paramBean) {
		final String sql = getQuery("OCISCATA_OCISCATA_WHENNEWFORMINSTANCE");
		final RowMapper<OffenderBookings> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderBookings.class,
				offenderBookingsMapping);
		final ArrayList<OffenderBookings> returnList = (ArrayList<OffenderBookings>) namedParameterJdbcTemplate
				.query(sql, createParams(paramBean), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @param paramBean
	 * @return List<ProgramServices>
	 */
	public List<ProgramServices> ociscataWhenNewFormInstance(final ProgramServices paramBean) {
		final String sql = getQuery("OCISCATA_OCISCATA_WHENNEWFORMINSTANCE");
		final RowMapper<ProgramServices> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ProgramServices.class,
				programServicesMapping);
		final ArrayList<ProgramServices> returnList = (ArrayList<ProgramServices>) namedParameterJdbcTemplate.query(sql,
				createParams(paramBean), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * setupDefaults
	 *
	 * @param params
	 *
	 */
	public String setupDefaults(final BigDecimal listSeq) {
		final String sql = getQuery("OCISCATA_SETUP_DEFAULTS");
		final String returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("LIST_SEQ",listSeq), String.class);
		return returnList;
	}

	/**
	 * This method will get the default status code
	 * 
	 * @return String
	 */
	public String getDefaultDomain() {
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[2];
		String value = null;
		sqlParameters = new SqlParameter[] { new SqlParameter("P_DOMAIN", OracleTypes.VARCHAR),
				new SqlOutParameter("CODE", OracleTypes.VARCHAR) };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_REFERENCE_CODES").withFunctionName("DEFAULT_DOMAIN")
				.declareParameters(sqlParameters);
		inParamMap.put("P_DOMAIN", "PS_AVAIL");
		inParamMap.put("CODE", OracleTypes.VARCHAR);
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			value = simpleJDBCCall.executeFunction(String.class, inParameter);

		} catch (Exception e) {
			logger.error("In getDefaultDomain method : ", e);
		}
		return value;
	}
	
	/**
	 * This method will get the default agency code
	 * 
	 * @param caseloadId
	 * @return String
	 */
	public String getDefaultAgency(final String caseloadId) {
		Map<String, Object> returnObject = null;
		String strAgyLocId = "NONE";
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[3];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_GLOBAL_CASELOAD_ID", OracleTypes.VARCHAR),
				new SqlOutParameter("P_AGY_LOC_ID", OracleTypes.VARCHAR),
				new SqlOutParameter("P_DESCRIPTION", OracleTypes.VARCHAR), };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_ESTABLISHMENT").withProcedureName("DEFAULT_AGENCY")
				.declareParameters(sqlParameters);
		inParamMap.put("P_GLOBAL_CASELOAD_ID", caseloadId);
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			
			if (returnObject != null && returnObject.get("P_AGY_LOC_ID") != null) {
				strAgyLocId =  String.valueOf(returnObject.get("P_AGY_LOC_ID")) ;
			}
		} catch (Exception e) {
			logger.error("In getDefaultAgency method : ", e);
		}
		return strAgyLocId;
	}
	
	
	/**
	 * 
	 * This method will get the default status code
	 *@param strCode
	 *@param strDesc
	 *@return String
	 */
	public String  getDescCode(final String strCode, final String strDesc) {
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[2];
		String value = null;
		sqlParameters = new SqlParameter[] { new SqlParameter("P_DOMAIN", OracleTypes.VARCHAR),
				 new SqlParameter("P_REFCODE", OracleTypes.VARCHAR),
				new SqlOutParameter("P_RESULT", OracleTypes.VARCHAR) };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("OMS_MISCELLANEOUS").withFunctionName("GETDESCCODE")
				.declareParameters(sqlParameters).withoutProcedureColumnMetaDataAccess();
		inParamMap.put("P_DOMAIN", strCode);
		inParamMap.put("P_REFCODE", strDesc);
		inParamMap.put("P_RESULT", OracleTypes.VARCHAR);
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			value = simpleJDBCCall.executeFunction(String.class, inParameter);
		} catch (Exception e) {
			logger.error("In getDescCode method : ", e);
		}
		return value;
	}
	
	
	/**
	 *@param programId
	 *@return String
	 */
	public String getAccProgram(final BigDecimal programId) {
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[2];
		String value = null;
		sqlParameters = new SqlParameter[] { new SqlParameter("P_PROGRAM_ID", OracleTypes.NUMBER),
				new SqlOutParameter("DESCRIPTION", OracleTypes.VARCHAR) };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("OCMSVACP").withFunctionName("GET_ACC_PROGRAM")
				.declareParameters(sqlParameters);
		inParamMap.put("P_PROGRAM_ID", programId);
		inParamMap.put("DESCRIPTION", OracleTypes.VARCHAR);
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			value = simpleJDBCCall.executeFunction(String.class, inParameter);
		} catch (Exception e) {
			logger.error("In getAccProgram method : ", e);
		}
		return value;
	}

	public int createTable() {
		String createTableSQL = "CREATE GLOBAL TEMPORARY TABLE PROGRAMS_NON_ASSOC_TMP ( LINE INT not null, OFFENDER_ID BIGINT not null, OFFENDER_BOOK_ID BIGINT not null, PROGRAM_ID BIGINT, CRS_ACTY_ID BIGINT ) ON COMMIT DELETE ROWS";
		return namedParameterJdbcTemplate.update(createTableSQL, createParams());
	}

	public String getTableName() {
		String table = null;
		try(Connection connection = ds.getConnection()) {
			DatabaseMetaData meta = connection.getMetaData();
			ResultSet res = meta.getTables(null, null, "PROGRAMS_NON_ASSOC_TMP", new String[] { "TABLE" });
			while (res.next()) {
				table = res.getString("TABLE_NAME");
			}
		} catch (Exception e) {
			logger.error("getTableName", e);
		}
		return table;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ProgramsNonAssocTmp>
	 */
	public List<ProgramsNonAssocTmp> getProgramsNonAssTmp() {
		String table = getTableName();
		if (table == null)
			this.createTable();
		final String sql = getQuery("OCISCATA_PROGRAMS_NON_ASSOC_TMP");
		final RowMapper<ProgramsNonAssocTmp> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ProgramsNonAssocTmp.class, progNonAssMap);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("In getProgramsNonAssTmp method : ", e);
			return Collections.emptyList();
			
		}
	}
	
	/**
	 * Used to capture results from select query
	 * 
	 * @return Integer
	 */
	public Integer getProgramsNonAssTmpCount() {
		String table = getTableName();
		if (table == null)
			this.createTable();
		final String sql = getQuery("OCISCATA_PROGRAMS_NON_ASSOC_TMP_COUNT");
			return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Integer.class);
	}
	
	/**
	 * This method will check if there are any non association conflicts
	 * 
	 * @return ProgramsNonAssocTmp
	 */
	public ProgramsNonAssocTmp chkNaPrgSrvConflictRt(final ProgramsNonAssocTmp progNonAssTemp,final String coursePhase) {
		String conflict = null;
		Map<String, Object> returnObject = null;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[20];
		final ProgramsNonAssocTmp returnProgObj = new ProgramsNonAssocTmp();
		sqlParameters = new SqlParameter[] { new SqlParameter("P_OFFENDER_ID", OracleTypes.NUMBER),
				new SqlParameter("P_OFFENDER_BOOK_ID", OracleTypes.NUMBER),
				new SqlParameter("P_CRS_ACTY_ID", OracleTypes.NUMBER),
				new SqlOutParameter("P_OFF_INFO", OracleTypes.STRUCT, "TAG_CORE.OFF_INFO_TYPE",
						new SqlReturnStruct(OffRec.class)),
				new SqlOutParameter("P_CONFLICT_FLAG", OracleTypes.VARCHAR),
				new SqlParameter("P_OFFENDER_START_DATE", OracleTypes.DATE),
				new SqlParameter("P_OFFENDER_END_DATE", OracleTypes.DATE)};
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("NON_ASSOCIATION").withProcedureName("CHK_NA_PRG_SRV_CONFLICT_RT")
				.declareParameters(sqlParameters);
		inParamMap.put("P_OFFENDER_ID", progNonAssTemp.getOffenderId());
		inParamMap.put("P_OFFENDER_BOOK_ID", progNonAssTemp.getOffenderBookId());
		inParamMap.put("P_CRS_ACTY_ID", new BigDecimal(coursePhase));
		inParamMap.put("P_OFFENDER_START_DATE", null);
		inParamMap.put("P_OFFENDER_END_DATE", null);
		MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			conflict = returnObject.get("P_CONFLICT_FLAG").toString();
			if(conflict != null && "Y".equals(conflict)) {
		    final OffRec st = (OffRec) returnObject.get("P_OFF_INFO");
		 sqlParameters = new SqlParameter[20];
			sqlParameters = new SqlParameter[] { 
					new SqlParameter("P_OFFENDER_ID", OracleTypes.NUMBER),
					new SqlParameter("P_OFF_INFO", OracleTypes.STRUCT, "TAG_CORE.OFF_INFO_TYPE"),
					new SqlOutParameter("LV_WARNING_MESSAGE", OracleTypes.VARCHAR),
					new SqlOutParameter("LV_WARNING_PROMPT", OracleTypes.VARCHAR)};
			
			 simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
					.withCatalogName("NON_ASSOCIATION").withProcedureName("GET_CONFLICT_WARNING")
					.declareParameters(sqlParameters);
			inParamMap.put("P_OFFENDER_ID", progNonAssTemp.getOffenderId());
			inParamMap.put("P_OFF_INFO", st);
			 inParameter = new MapSqlParameterSource(inParamMap);
				returnObject = simpleJDBCCall.execute(inParameter);
				returnProgObj.setWarningMsg(String.valueOf(returnObject.get("LV_WARNING_MESSAGE")));
				returnProgObj.setWarningPrompt(String.valueOf(returnObject.get("LV_WARNING_PROMPT")));
			}
		} catch (Exception e) {
			logger.error("chkNaPrgSrvConflictRt: ", e);
		}
		return returnProgObj;
	}
	
	/**
	 * This method will get the default agency code
	 * 
	 * @param caseloadId
	 * @return ReferenceCodes
	 */
	public ReferenceCodes getCommDefaults(final String caseloadId) {
		Map<String, Object> returnObject = null;
		ReferenceCodes objRef = null;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[3];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_USER_ID", OracleTypes.VARCHAR),
				new SqlOutParameter("P_AGY_LOC_ID", OracleTypes.VARCHAR),
				new SqlOutParameter("P_AGY_LOC_DESC", OracleTypes.VARCHAR), };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_SERVICE").withProcedureName("GET_COMM_DEFAULT")
				.declareParameters(sqlParameters);
		inParamMap.put("P_USER_ID", caseloadId);
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			
			if (returnObject != null && returnObject.get("P_AGY_LOC_ID") != null) {
				objRef  = new ReferenceCodes();
				objRef.setCode(String.valueOf(returnObject.get("P_AGY_LOC_ID"))) ;
				objRef.setDescription(String.valueOf(returnObject.get("P_AGY_LOC_DESC"))) ;
			}
		} catch (Exception e) {
			logger.error("In getCommDefaults method : ", e);
		}
		return objRef;
	}
	
	@Override
	public Integer checkNonAssociationConflict(BigDecimal OffenderBookIdOne,BigDecimal OffenderBookIdTwo) {
		final String sql = getQuery("OCISCATA_CHK_NON_ASS_CONFLICT");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("P_OFFENDER_BOOK_ID",OffenderBookIdOne,"P_VIS_OFFENDER_BOOK_ID",OffenderBookIdTwo), Integer.class);
		}catch (Exception e) {
			logger.error("In checkNonAssociationConflict method : ", e);
			return 0;
		}
	}
	
	
	@Override
	public List<OffenderNonAssociations> checkNonAssociations(final BigDecimal offenderBookId,String userId) {
		final String sql = getQuery("OCISCATA_CHK_NONASSOCIATIONS");
		List<OffenderNonAssociations> returnList = new ArrayList<OffenderNonAssociations>();
		final RowMapper<OffenderNonAssociations> VAcpProgressRowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderNonAssociations.class,
				AreaCodeMapping);
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("offenderBookId", offenderBookId,"USERID",userId), VAcpProgressRowMapper);
		} catch (Exception e) {
			return returnList;
		}
		return returnList;
	}

	@Override
	public List<Offenders> getOffenderDetails(BigDecimal offenderBookId) {
		List<Offenders> off=new ArrayList<Offenders>();
		try {
			final String sql = getQuery("OCISCATA_OFFENDER_DETAILS");
			final RowMapper<Offenders> mRowMapper = Row2BeanRowMapper.makeMapping(sql, Offenders.class, progNonAssMap);
			off = namedParameterJdbcTemplate.query(sql, createParams("offenderBookId", offenderBookId), mRowMapper);
		}catch (Exception e) {
			off=null;
			logger.error(this.getClass().getName()+" error in getOffenderDetails "+e);
		}
		
		return off;
	}

	@Override
	public List<Offenders> getOffenderDetailsByInd(BigDecimal offenderBookId, List<Integer> listOfProgramIds) {
		
		List<Offenders> offenderDetailsList =new ArrayList<Offenders> ();
		try {
			final String sql = getQuery("OCISCATA_OFFENDER_DETAILS_EXTERNAL_INDIVIDUAL");
			final RowMapper<Offenders> mRowMapper = Row2BeanRowMapper.makeMapping(sql, Offenders.class, progNonAssMap);
			offenderDetailsList = namedParameterJdbcTemplate.query(sql, createParams("offenderBookId", offenderBookId,"crsActyId",listOfProgramIds.get(0)), mRowMapper);
		}catch (Exception e) {
			logger.error(this.getClass().getName()+" error in getOffenderDetailsByInd "+e);
		}
		
		
		return offenderDetailsList;
	}

	@Override
	public List<Offenders> getOffenderDetailsByGang(BigDecimal offenderBookId, List<Integer> listOfProgramIds) {

		List<Offenders> offenderDetailsList =new ArrayList<Offenders> ();
		try {
			final String sql = getQuery("OCISCATA_OFFENDER_DETAILS_EXTERNAL_GANG");
			final RowMapper<Offenders> mRowMapper = Row2BeanRowMapper.makeMapping(sql, Offenders.class, progNonAssMap);
			offenderDetailsList = namedParameterJdbcTemplate.query(sql, createParams("offenderBookId", offenderBookId,"crsActyId",listOfProgramIds.get(0)), mRowMapper);
		}catch (Exception e) {
			logger.error(this.getClass().getName()+" error in getOffenderDetailsByInd "+e);
		}
		
		
		return offenderDetailsList;
	}

	@Override
	public String offenderName(Long offenderId) {
		final String sql = getQuery("OCISCATA_OFFENDER_NAME_BY_ID");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderId", offenderId), String.class);
		} catch (Exception e) {
			return null;
		}
	}
	

}
