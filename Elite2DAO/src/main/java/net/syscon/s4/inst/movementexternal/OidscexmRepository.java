package net.syscon.s4.inst.movementexternal;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.OffenderExternalMovements;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.BedAssignmentHistories;
import net.syscon.s4.im.beans.LivingUnits;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.inst.movementexternal.beans.OffenderNotCompletions;
import net.syscon.s4.inst.movementexternal.beans.OffenderPendNotifications;
import net.syscon.s4.inst.movementexternal.beans.VOiusstri;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules;

/**
 * Interface OidscexmRepository
 */
public interface OidscexmRepository {
	BedAssignmentHistories processExternalMovement(BedAssignmentHistories paramBean);

	List<LivingUnits> rgBuildingRecordGroup();

	List<LivingUnits> rgTierRecordGroup();

	List<VOffenderAllSchedules> offSchExecuteQuery(VOffenderAllSchedules objVOffenderAllSchedules);
	
	List<VOffenderAllSchedules> getoffenderExtMovements(VOffenderAllSchedules objVOffenderAllSchedules);


	OffenderPendNotifications chkNotification(OffenderPendNotifications paramBean);

	OffenderPendNotifications chkNotificationgetpendnotiinfocur(OffenderPendNotifications paramBean);

	List<ReferenceCodes> rgMoveTypeRecordGroup();

	List<AgencyLocations> rgAgyIdRecordGroup();

	OffenderBookings processExternalMovement(OffenderBookings paramBean);

	Integer offSchUpdateVOffenderAllSchedules(List<VOffenderAllSchedules> lstVOffenderAllSchedules);

	OffenderNotCompletions chkNotification(OffenderNotCompletions paramBean);

	OmsModules createFormGlobals(OmsModules paramBean);

	List<VOiusstri> schTripsExecuteQuery(VOiusstri objVOiusstri);

	OffenderExternalMovements getLastMovementDateTime(String offenderBookId);

	Integer prisonActivities(VOffenderAllSchedules paramBean);

	VOffenderAllSchedules deactivateOffender(VOffenderAllSchedules paramBean);

	VOffenderAllSchedules updateOffenderInOutStatus(VOffenderAllSchedules paramBean);

	VOffenderAllSchedules insertReturnSchedule(VOffenderAllSchedules paramBean);

	String getLastToAgyLocId(Integer offBookId);

	String getLastToCity(Integer offBookId);

	String getToAddress(Integer offBookId);

	VOffenderAllSchedules updateOffExtMvnts(VOffenderAllSchedules paramBean);

	VOffenderAllSchedules insertExternalMovement(Integer offenderBookId, Integer eventId, String fromAgyLocId,
			String lvFromCity, Integer fromAddress, String toAgyLocId, Integer lvToAddressId, String toCityCode,
			String eventType, String eventSubType, String escortCode, String directionCode, String commentText,
			Date fMtime);

	VOffenderAllSchedules updateCrtEventStatus(Integer eventId);

	VOffenderAllSchedules updateOffSchStatus(Integer eventId);

	VOffenderAllSchedules updateWorkAsgnStatuses(Integer offId, String caseLoadId, Date movementDate);

	Integer statusRecords(VOffenderAllSchedules paramBean);

	Integer chkNotificationgetcountcur(OffenderPendNotifications paramBean);

	String getToAgyLocId(String toAgyLocDesc);

	OffenderExternalMovements suspendAllocations(VOffenderAllSchedules paramBean);

	OffenderExternalMovements endWaitingListAllocations(VOffenderAllSchedules paramBean);
	
	List<VOffenderAllSchedules> getOldRecOffenderIndSchedules(final BigDecimal getEventId);

	String getExternalAddress(BigDecimal toAddressId);

}
