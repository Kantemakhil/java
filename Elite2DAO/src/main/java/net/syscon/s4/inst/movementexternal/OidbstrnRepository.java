package net.syscon.s4.inst.movementexternal;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.MovementReasons;
import net.syscon.s4.inst.schedules.bean.OffenderIndSchedules;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules;

public interface OidbstrnRepository {
	Integer offAllSchInsertVOffenderAllSchedules(List<VOffenderAllSchedules> lstVOffenderAllSchedules);

	List<AgencyLocations> rgAllAgyLocRecordGroup();

	List<ReferenceCodes> rgEscortRecordGroup();

	List<ReferenceCodes> rgCancelReasonRecordGroup();

	List<VOffenderAllSchedules> offAllSchExecuteQuery(String caseLoad, VOffenderAllSchedules objVOffenderAllSchedules);

	List<AgencyLocations> rgAgyLocRecordGroup(String caseLoadId);

	Integer offAllSchUpdateVOffenderAllSchedules(List<VOffenderAllSchedules> lstVOffenderAllSchedules);

	List<MovementReasons> rgReasonRecordGroup();

	Integer tagSchedulesCreateSchedules(List<OffenderIndSchedules> offederIndSch);

	Integer tagSchedulesUpdateSchedules(List<OffenderIndSchedules> offederIndSch);

	String tagScheduleLockEventWl(Long eventId, Long checkSum);

	Integer oidbstrnDuplicateExists(VOffenderAllSchedules schedules);

	Integer oidbstrnTagScheduleCheckScheduleConflict(VOffenderAllSchedules schedules);

}
