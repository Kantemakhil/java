package net.syscon.s4.triggers.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.beans.CourseActivityAreas;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.inst.accreditedprograms.beans.VCoursePhaseOfferings;
import net.syscon.s4.triggers.VCoursePhaseOfferingsTuRepository;

@Repository
public class VCoursePhaseOfferingsTuRepositoryImpl extends RepositoryBase implements VCoursePhaseOfferingsTuRepository {
	private final Logger logger = LogManager.getLogger(VCoursePhaseOfferingsTuRepositoryImpl.class);

	@Override
	public Long lCrsActyId() {
		final String sql = getQuery("V_COURSE_PHASE_OFFERINGS_TU_L_CRS_ACTY_ID");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Long.class);
		} catch (final Exception e) {
			logger.error("lCrsActyId", e);
			return null;
		}
	}

	@Override
	public Integer courseActivitiesInsert(final CourseActivities courseActivities) {
		final String sql = getQuery("V_COURSE_PHASE_OFFERINGS_TU_COURSE_ACTIVITIES_INSERT");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		parameters.add(new BeanPropertySqlParameterSource(courseActivities));
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (final Exception e) {
			logger.error("courseActivitiesInsert", e);
		}
		if (returnArray.length == 1) {
			return 1;
		} else {
			return 0;
		}

	}

	@Override
	public Integer courseActivitiesUpdate(final CourseActivities courseActivities) {
		final String sql = getQuery("V_COURSE_PHASE_OFFERINGS_TU_COURSE_ACTIVITIES_UPDATE");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		CourseActivities returnData = new CourseActivities();
		List<CourseActivities> reurnList = new ArrayList<>();
		reurnList.add(courseActivities);
		parameters.add(new BeanPropertySqlParameterSource(courseActivities));
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		}catch (Exception e){
			String error = "Error : " + e.getMessage();
			if (error.contains("OMS_OWNER")) {
				error = error.substring(error.indexOf("OMS_OWNER."), error.indexOf(")")).replaceFirst("OMS_OWNER.", "");
				final String tableName = errorNameValidation(error);
				returnData.setSealFlag(tableName);
				returnData.setListSeq(Long.valueOf(2292));
			}
		}
		if (reurnList.size() == returnArray.length) {
			returnData.setSealFlag("1");
			return Integer.parseInt(returnData.getSealFlag());
		} 
			returnData.setSealFlag("0");
			return Integer.parseInt(returnData.getSealFlag());

	}
	public String errorNameValidation(final String errorName) {
		final String sql = getQuery("OCMSVACP_CONSTRAINT_VALIDATIONS");
		String returnData = null;
		try {
			returnData = namedParameterJdbcTemplate.queryForObject(sql, createParams("CONSTRAINTNAME", errorName),
					String.class);
		} catch (Exception e) {
			return returnData;
		}
		return returnData;
	}
	@Override
	public Integer courseActivityAreasDelete(final CourseActivityAreas courseActivityAreas) {
		final String sql = getQuery("V_COURSE_PHASE_OFFERINGS_TU_COURSE_ACTIVITY_AREAS_DELETE");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		parameters.add(new BeanPropertySqlParameterSource(courseActivityAreas));
		try {
			String tableName = "COURSE_ACTIVITY_AREAS";
			String whereClause = "CRS_ACTY_ID = :crsActyId";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method courseActivityAreasDelete", e);
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (final Exception e) {
			logger.error("courseActivityAreasDelete", e);
		}
		if (returnArray.length == 1) {
			return 1;
		} else {
			return 0;
		}

	}

	@Override
	public CourseActivities courseActivitiesDelete(final CourseActivities courseActivities) {
		final String sql = getQuery("V_COURSE_PHASE_OFFERINGS_TU_COURSE_ACTIVITIES_DELETE");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		CourseActivities returnData = new CourseActivities();
		List<CourseActivities> reurnList = new ArrayList <>();
		reurnList.add(courseActivities);
		parameters.add(new BeanPropertySqlParameterSource(courseActivities));
		try {
			String tableName = "COURSE_ACTIVITIES";
			String whereClause = "CRS_ACTY_ID = :crsActyId";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method courseActivitiesDelete", e);
		}
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		return returnData;

	}

	@Override
	public Integer courseActivitiesUpdate2(final CourseActivities courseActivities) {
		final String sql = getQuery("V_COURSE_PHASE_OFFERINGS_TU_COURSE_ACTIVITIES_UPDATE2");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		parameters.add(new BeanPropertySqlParameterSource(courseActivities));
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (final Exception e) {
			logger.error("courseActivitiesDelete", e);
		}
		if (returnArray.length == 1) {
			return 1;
		} else {
			return 0;
		}

	}

	@Override
	public Integer deleteCoursePhaseModules(final VCoursePhaseOfferings newObj) {
		final String sql = getQuery("DELETE_COURSE_PHASE_MODULES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		parameters.add(new BeanPropertySqlParameterSource(newObj));
		try {
			String tableName = "v_course_modules";
			String whereClause = "COURSE_PHASE_ID = :coursePhaseId";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteCoursePhaseModules", e);
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (final Exception e) {
			logger.error("deleteCoursePhaseModules", e);
		}
		if (returnArray.length == 1) {
			return 1;
		} else {
			return 0;
		}

	}
	
	@Override
	public VCoursePhaseOfferings getOldValuesVCoursePhaseOfferings(final VCoursePhaseOfferings vCoursePhaseOfferings) {
		final String sql = getQuery("GET_OLD_VALUES_V_COURSE_PHASE_OFFERINGS");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("courseId",
				vCoursePhaseOfferings.getCourseId(), "programPhaseId", vCoursePhaseOfferings.getProgramPhaseId()),
				new BeanPropertyRowMapper<VCoursePhaseOfferings>(VCoursePhaseOfferings.class));

	}

}
