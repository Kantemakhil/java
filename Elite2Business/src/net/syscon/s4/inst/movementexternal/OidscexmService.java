package net.syscon.s4.inst.movementexternal;

import java.util.List;

import net.syscon.s4.common.beans.OffenderExternalMovements;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.LivingUnits;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.inst.movementexternal.beans.OffenderPendNotifications;
import net.syscon.s4.inst.movementexternal.beans.VOiusstri;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedulesCommitBean;

/**
 * Interface OidscexmService
 */
public interface OidscexmService {
	List<LivingUnits> rgBuildingRecordGroup();

	Integer offSchCommit(VOffenderAllSchedulesCommitBean commitBean);

	OffenderPendNotifications ChkNotification(OffenderPendNotifications paramBean);

	List<LivingUnits> rgTierRecordGroup();

	List<VOffenderAllSchedules> offSchExecuteQuery(VOffenderAllSchedules objVOffenderAllSchedules);

	List<ReferenceCodes> rgMoveTypeRecordGroup();

	List<AgencyLocations> rgAgyIdRecordGroup();

	OmsModules CreateFormGlobals(OmsModules paramBean);

	List<VOiusstri> schTripsExecuteQuery(VOiusstri objVOiusstri);

	OffenderExternalMovements getLastMovementDateTime(VOffenderAllSchedules paramBean);

	VOffenderAllSchedules processExternalMovement(VOffenderAllSchedulesCommitBean commitBean);

	OffenderExternalMovements suspendAllocations(VOffenderAllSchedules vOffenderAllSchedules);

}
