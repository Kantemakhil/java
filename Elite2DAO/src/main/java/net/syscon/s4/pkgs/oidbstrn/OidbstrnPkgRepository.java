package net.syscon.s4.pkgs.oidbstrn;


import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules;

public interface OidbstrnPkgRepository{    
	Integer duplicateExists(final VOffenderAllSchedules schedules);
}