package net.syscon.s4.triggers.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.inst.institutionalactivities.maintenance.beans.CourseSchedules;
import net.syscon.s4.triggers.CourseSchedulesT1Repository;
import net.syscon.s4.triggers.CourseSchedulesT1Service;

@Service
public class CourseSchedulesT1ServiceImpl implements CourseSchedulesT1Service {
	private static Logger logger = LogManager.getLogger(CourseSchedulesT1ServiceImpl.class);
	@Autowired
	CourseSchedulesT1Repository courseSchedulesT1Repository;

	@Override
	public Long crsSchId(final CourseSchedules courseSchedules) {
		Long result = null;
		try {
			if (courseSchedules != null && courseSchedules.getCrsSchId() == null) {
				result = courseSchedulesT1Repository.lCrsSchIdNextval();
			}
		} catch (final Exception e) {
			logger.error("crsSchId", e);
			return result;
		}
		return result;
	}

}
