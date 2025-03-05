package net.syscon.s4.triggers.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.ProgramServicesProfiles;
import net.syscon.s4.triggers.CourseActivitiesT3Repository;

@Repository
public class CourseActivitiesT3RepositoryImpl extends RepositoryBase implements CourseActivitiesT3Repository {

	private static Logger logger = LogManager.getLogger(CourseActivitiesT3RepositoryImpl.class);

	@Override
	public ProgramServicesProfiles getProgServProf(final Long lvProgramId) {
		final String sql = getQuery("GET_PROG_SERV_PROF");
		ProgramServicesProfiles obj = new ProgramServicesProfiles();
		try {
			obj = namedParameterJdbcTemplate.queryForObject(sql, createParams("LV_PROGRAM_ID", lvProgramId),
					ProgramServicesProfiles.class);
		} catch (Exception e) {
			logger.error("getProgServProf: " + e);
		}
		return obj;
	}

	@Override
	public Integer insertCourseActivityProfiles(final Long programId, final Long crsActyId,String username) {
		final String sql = getQuery("INSERT_INTO_COURSE_ACTIVITY_PROFILES");
		Integer retVal = 0;
		try {
			retVal = namedParameterJdbcTemplate.update(sql,
					createParams("CRS_ACTY_ID", crsActyId, "PROGRAM_ID", programId,"createUserId",username));
		} catch (DataAccessException e) {
			logger.error("insertCourseActivityProfiles :" + e);
		}
		return retVal;
	}

}
