package net.syscon.s4.pkgs.oidshlog;

import net.syscon.s4.inst.shiftlogs.bean.AgencyShiftLogs;

public interface OidshlogPkgService {

	Integer saveObservationText(final AgencyShiftLogs agyShtLog, final String userName);

	String getObservationText(final AgencyShiftLogs agyShtLog);
}
