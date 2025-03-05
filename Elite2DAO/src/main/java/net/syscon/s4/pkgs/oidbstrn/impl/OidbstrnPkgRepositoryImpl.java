package net.syscon.s4.pkgs.oidbstrn.impl;

import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules;
import net.syscon.s4.pkgs.oidbstrn.OidbstrnPkgRepository;

@Repository
public class OidbstrnPkgRepositoryImpl extends RepositoryBase implements OidbstrnPkgRepository {

	public Integer duplicateExists(final VOffenderAllSchedules schedules) {
		final String sql = getQuery("GET_V_OFFENDER_ALL_SCHEDULES");
		Integer retObj = null;
		try {
			retObj = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("offenderIdDisplay", schedules.getOffenderIdDisplay(), "eventStatus",
							schedules.getEventStatus(), "eventClass", schedules.getEventClass(), "eventType",
							schedules.getEventType(), "eventDate", schedules.getEventDate(), "startTime",
							schedules.getStartTime(), "eventId", schedules.getEventId()),
					Integer.class);
		} catch (Exception e) {
			return 0;
		}
		return retObj;
	}
}
