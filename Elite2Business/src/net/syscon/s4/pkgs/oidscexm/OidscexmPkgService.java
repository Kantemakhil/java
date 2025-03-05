package net.syscon.s4.pkgs.oidscexm;

import java.text.ParseException;

import net.syscon.s4.common.beans.OffenderExternalMovements;
import net.syscon.s4.inst.schedules.bean.OffenderIndSchedules;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules;

public interface OidscexmPkgService {

	VOffenderAllSchedules insertExternalMovement(final OffenderExternalMovements offExtmov, final String userName);

	VOffenderAllSchedules deactivateOffender(final VOffenderAllSchedules paramBean, final String userName);

	VOffenderAllSchedules insertReturnSchedule(final VOffenderAllSchedules paramBean, final String userName,OffenderIndSchedules oldObj) throws ParseException;

	VOffenderAllSchedules updateCrtEventStatus(final Integer eventId, final String userName);

	VOffenderAllSchedules updateOffExtmvnts(final VOffenderAllSchedules paramBean, final String userName);

	VOffenderAllSchedules updateOffSchStatus(final Integer eventId);

	VOffenderAllSchedules updateOffenderInOutStatus(final VOffenderAllSchedules paramBean, final String userName);
}
