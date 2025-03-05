package net.syscon.s4.inst.schedules;

import java.util.Date;
import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.inst.schedules.bean.OffenderIndSchedules;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedulesCommitBean;

/**
 * Interface OidstojuService
 */
public interface OidstojuService {
	VOffenderAllSchedules offSchCommit(VOffenderAllSchedulesCommitBean commitBean);

	List<ReferenceCodes> rgEscortRecordGroup();

	List<VOffenderAllSchedules> offSchExecuteQuery(VOffenderAllSchedules objVOffAllSch);

	List<ReferenceCodes> rgLocationRecordGroup();

	List<OffenderIndSchedules> offBkgOnCheckDeleteMaster(OffenderIndSchedules paramBean);
	
	Date getCurrentDate();
	
	VOffenderAllSchedules offSchCheckScheduleConflict(VOffenderAllSchedulesCommitBean obj);
	
	List<ReferenceCodes> rgEventTypeSubTypeGroup();
	
	Integer offSchCheckScheduleConflictBeforeSave(VOffenderAllSchedules vOffAllSch);

}
