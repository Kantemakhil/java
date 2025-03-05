package net.syscon.s4.inst.casemanagement;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.MovementReasons;
import net.syscon.s4.inst.casemanagement.beans.InternalScheduleReasons;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedulesCommitBean;

/**
 * Interface OiioscedService
 */
public interface OiioscedService {
	List<VOffenderAllSchedules> offBkgOnCheckDeleteMaster(VOffenderAllSchedules paramBean);

	List<ReferenceCodes> rgSchTypeRecordGroup(String domain);

	List<MovementReasons> rgSchReaExtRecordGroup(String schTypeCode);

	List<VOffenderAllSchedules> vOffenderAllSchedulesExecuteQuery(VOffenderAllSchedules objVOffenderAllSchedules);

	Integer vOffenderAllSchedulesCommit(VOffenderAllSchedulesCommitBean CommitBean);

	List<InternalScheduleReasons> rgSchReaIntRecordGroup(String schTypeCode);
	
	List<ReferenceCodes> rgSchFilterRecordGroup();

}
