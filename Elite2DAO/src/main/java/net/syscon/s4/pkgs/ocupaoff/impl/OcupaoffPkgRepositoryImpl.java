package net.syscon.s4.pkgs.ocupaoff.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.beans.OffenderCourseAttendance;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.pkgs.ocupaoff.OcupaoffPkgRepository;

@Repository
public class OcupaoffPkgRepositoryImpl extends RepositoryBase implements OcupaoffPkgRepository {

	private static Logger logger = LogManager.getLogger(OcupaoffPkgRepositoryImpl.class.getName());

	@Override
	public Integer updateVOffCourseEvents(final OffenderCourseAttendance bean) {
		final String sql = getQuery("UPDATE_V_OFF_CRSE_EVENTS_UPDATE_V_OFF_CRS_EVENTS");
		Integer returnValue = null;
		try {
			namedParameterJdbcTemplate.update(sql,
					createParams("P_EVENT_OUTCOME", bean.getEventOutcome(), "P_COMMENT_TEXT", bean.getCommentText(),
							"P_OFF_PRGREF_ID", bean.getProgramId(), "P_CRS_ACTY_ID", bean.getCrsActyId(),
							"P_CRS_SCH_ID", bean.getCrsSchId()));
			returnValue = 1;
		} catch (Exception e) {
			logger.error("updateVOffCourseEvents :", e);
			returnValue = 0;
		}
		return returnValue;
	}

	@Override
	public Integer updateCourseAttendance(final OffenderCourseAttendance bean) {
		final String sql = getQuery("UPDATE_OFFNEDR_COURSE_ATTENDANCE");
		Integer count = null;
		try {
			count = namedParameterJdbcTemplate.update(sql,
					createParams("p_event_outcome", bean.getEventOutcome(), "p_in_time", bean.getInTime(), "p_out_time",
							bean.getOutTime(), "p_engagement_code", bean.getEngagementCode(), "p_understanding_code",
							bean.getUnderstandingCode(), "p_comment_text", bean.getCommentText(), "p_event_id",
							bean.getEventId(), "modifyUserId", bean.getModifyUserId()));
		} catch (DataAccessException e) {
			logger.error("updateCourseAttendance :" + e);
			return 0;
		}
		return count;
	}
}
