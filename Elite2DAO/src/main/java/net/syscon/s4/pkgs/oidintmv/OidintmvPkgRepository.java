package net.syscon.s4.pkgs.oidintmv;

import java.util.List;

import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules;

public interface OidintmvPkgRepository {

	List<VOffenderAllSchedules> getSchedules(final VOffenderAllSchedules objSearchDao);

	List<VOffenderAllSchedules> getUnschedulesCur(final VOffenderAllSchedules objSearchDao);

}
