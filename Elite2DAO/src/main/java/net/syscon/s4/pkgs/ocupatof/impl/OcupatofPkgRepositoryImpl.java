package net.syscon.s4.pkgs.ocupatof.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.beans.OffenderCourseAttendance;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.pkgs.ocupatof.OcupatofPkgRepository;

@Repository
public class OcupatofPkgRepositoryImpl extends RepositoryBase implements OcupatofPkgRepository {

	private static Logger logger = LogManager.getLogger(OcupatofPkgRepositoryImpl.class.getName());

	public Integer updateOffCrseAttend(final OffenderCourseAttendance searchBean) {
		final String sqlQuery = getQuery("UPDATE_OFF_CRSE_ATTEND");
		Map<String, Object> inParams = new HashMap<String, Object>();
		Integer retVal = 0;
		try {
			inParams.put("p_event_id", searchBean.getEventId());
			inParams.put("p_event_outcome", searchBean.getEventOutcome());
			inParams.put("p_in_time", searchBean.getInTime());
			inParams.put("p_out_time", searchBean.getOutTime());
			inParams.put("p_engagement_code", searchBean.getEngagementCode());
			inParams.put("p_understanding_code", searchBean.getUnderstandingCode());
			inParams.put("p_comment_text", searchBean.getCommentText());
			inParams.put("modifyUserId", searchBean.getModifyUserId());
			retVal = namedParameterJdbcTemplate.update(sqlQuery, inParams);
		} catch (Exception e) {
			retVal = 0;
			logger.error("updateOffCrseAttend", e);
		}
		return retVal;
	}

}