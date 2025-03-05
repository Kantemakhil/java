package net.syscon.s4.inst.movementexternal;

import java.util.Date;
import java.util.List;

import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.OffenderExternalMovements;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.im.beans.BedAssignmentHistories;
import net.syscon.s4.im.beans.MovementReasons;
import net.syscon.s4.im.beans.SysDual;

public interface OidtrojuRepository {
	Integer offEmPreInsert(OffenderExternalMovements paramBean);

	List<OffenderExternalMovements> offEmExecuteQuery(OffenderExternalMovements object);

	List<Object> cgwhenNewFormInstance(SysDual paramBean);

	List<ReferenceCodes> cgfkOffEmToProvStatCodeRecordGroup();

	List<SystemProfiles> sysPflExecuteQuery(SystemProfiles objSystemProfiles);

	ReferenceCodes cgfkchkOffEmOffEmRefTo(ReferenceCodes paramBean);

	Integer offEmInsertoffEm(List<OffenderExternalMovements> insertList);

	Date offExmGetMaxMovDate(OffenderExternalMovements object);

	MovementReasons clostContactFlag();

	OffenderBookings commFlagCur(Integer offbkgId);

	Integer offbkgUpdateQueryForN(OffenderBookings updateList);

	Integer offbkgUpdateQueryForY(OffenderBookings updateList);

	Integer offbkgUpdateQuery(OffenderBookings updateList);

	Integer payrollUpdateWorkAsgnStatuses(Integer parseInt, String caseloadId, Date dbTime);

	Integer getMaxBedAssignSeqCur(Integer offbkgId);

	Integer updateBedAssignmentHistories(BedAssignmentHistories updateList);

	VHeaderBlock getVsHeadcurOffId(String offIddisplay,String userId);

	Integer insNotification(OffenderExternalMovements beanObj);

	OffenderBookings getOldOffenderBookingsRecords(OffenderBookings paramBean);

}
