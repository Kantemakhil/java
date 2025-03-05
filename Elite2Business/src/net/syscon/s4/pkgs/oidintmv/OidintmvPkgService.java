package net.syscon.s4.pkgs.oidintmv;

import java.util.List;

import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules;

public interface OidintmvPkgService {
	List<VOffenderAllSchedules> getSchedules(final VOffenderAllSchedules objSearchDao, String pMoveType);
}
