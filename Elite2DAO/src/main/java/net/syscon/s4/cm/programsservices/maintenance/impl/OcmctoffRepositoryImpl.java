package net.syscon.s4.cm.programsservices.maintenance.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.cm.programsservices.maintenance.CourseActivityProfiles;
import net.syscon.s4.cm.programsservices.maintenance.OcmctoffRepository;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;

/**
 * Class OcmctoffRepositoryImpl
 */
@Repository
public class OcmctoffRepositoryImpl extends RepositoryBase implements OcmctoffRepository {

	private static Logger logger = LogManager.getLogger(OcmctoffRepositoryImpl.class.getName());
	/**
	 * Creates new OcmctoffRepositoryImpl class Object
	 */
	public OcmctoffRepositoryImpl() {
	}

	/**
	 * Logger object used to print the log in the file
	 */

	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description")).build();

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao CourseActivityProfiles
	 *
	 * @return List<CourseActivityProfiles>
	 *
	 * 
	 */
	public List<CourseActivityProfiles> crPrfGdExecuteQuery(final CourseActivityProfiles objSearchDao) {
		final String sql = getQuery("OCMCTOFF_CRPRFGD_FIND_COURSE_ACTIVITY_PROFILES");
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		sqlQuery.append(sql);
		if (objSearchDao.getSealFlag().equals("PS_SEX")) {
			sqlQuery.append(
					" WHERE CRS_ACTY_ID = :crsActyId and program_profile_type = 'PS_SEX' order by tag_service_sort_reference_code(PROGRAM_PROFILE_CODE,'PS_SEX')");
		} else if (objSearchDao.getSealFlag().equals("PS_ETHNICITY")) {
			sqlQuery.append(
					" WHERE CRS_ACTY_ID = :crsActyId and program_profile_type = 'PS_ETHNICITY' order by tag_service_sort_reference_code(PROGRAM_PROFILE_CODE,'ETHNICITY')");

		} else if (objSearchDao.getSealFlag().equals("PS_AGE_RANGE")) {
			sqlQuery.append(
					" WHERE CRS_ACTY_ID = :crsActyId and program_profile_type = 'PS_AGE_RANGE' order by tag_service_sort_reference_code(PROGRAM_PROFILE_CODE,'PS_AGE_RANGE')");
		} else if (objSearchDao.getSealFlag().equals("PS_FACILITY")) {
			sqlQuery.append(
					" WHERE CRS_ACTY_ID = :crsActyId and program_profile_type = 'PS_FACILITY' order by tag_service_sort_reference_code(PROGRAM_PROFILE_CODE,'PS_NEEDS')");
		} else if (objSearchDao.getSealFlag().equals("includeGrp")) {
			sqlQuery.append(
					" WHERE CRS_ACTY_ID = :crsActyId and program_profile_type = 'PS_INC_GRP' order by tag_service_sort_reference_code(PROGRAM_PROFILE_CODE,'PS_OFF_GRPS')");
		} else if (objSearchDao.getSealFlag().equals("excludeGrp")) {
			sqlQuery.append(
					" WHERE CRS_ACTY_ID = :crsActyId and program_profile_type = 'PS_EXC_GRP' order by tag_service_sort_reference_code(PROGRAM_PROFILE_CODE,'PS_OFF_GRPS')");
		}
		preparedSql = sqlQuery.toString().trim();
		final RowMapper<CourseActivityProfiles> courseActivityProfilesRowMapper = Row2BeanRowMapper
				.makeMapping(preparedSql, CourseActivityProfiles.class, mMapping);
		return namedParameterJdbcTemplate.query(preparedSql, createParams("crsActyId", objSearchDao.getCrsActyId()),
				courseActivityProfilesRowMapper);
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstCourseActivityProfiles List<CourseActivityProfiles>
	 *
	 * @return List<Integer>
	 *
	 */
	public Integer crPrfGdInsertCourseActivityProfiles(final List<CourseActivityProfiles> lstCourseActivityProfiles) {
		final String sql = getQuery("OCMCTOFF_CRPRFGD_INSERT_COURSE_ACTIVITY_PROFILES");
		int[] returnArray;
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final CourseActivityProfiles courseActivityProfiles : lstCourseActivityProfiles) {
			parameters.add(new BeanPropertySqlParameterSource(courseActivityProfiles));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstCourseActivityProfiles.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstCourseActivityProfiles List<CourseActivityProfiles>
	 *
	 * 
	 */
	public Integer crPrfGdDeleteCourseActivityProfiles(final List<CourseActivityProfiles> lstCourseActivityProfiles) {
		final String sql = getQuery("OCMCTOFF_CRPRFGD_DELETE_COURSE_ACTIVITY_PROFILES");
		int[] returnArray;
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final CourseActivityProfiles courseActivityProfiles : lstCourseActivityProfiles) {
			parameters.add(new BeanPropertySqlParameterSource(courseActivityProfiles));
		}
		try {
			String tableName = "COURSE_ACTIVITY_PROFILES";
			String whereClause = "CRS_ACTY_ID = :crsActyId AND PROGRAM_PROFILE_TYPE = :programProfileType AND PROGRAM_PROFILE_CODE = :programProfileCode";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method crPrfGdDeleteCourseActivityProfiles", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstCourseActivityProfiles.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

}