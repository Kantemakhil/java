package net.syscon.s4.inst.schedules;
import java.util.List;

import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.inst.casemanagement.beans.InternalScheduleReasons;
import net.syscon.s4.inst.schedules.bean.IntLocUsageLocations;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedulesCommitBean;
/**
 * Interface OidsiappService 
 */
public interface OidsiappService  {
	
	List<IntLocUsageLocations> rgInternalMoveLocationsRecordGroup(String agyLocId);

	Integer offSchCommit(VOffenderAllSchedulesCommitBean commitBean);

	List<VOffenderAllSchedules> offSchExecuteQuery(VOffenderAllSchedules objVOffAllSch);

	List<InternalScheduleReasons> rgSchInternalScheduleRecordGroup();

	List<VOffenderAllSchedules> offBkgOnCheckDeleteMaster(VOffenderAllSchedules paramBean);
	
	List<VHeaderBlock> checkNa(VHeaderBlock paramBean);
	
	Integer checkScheduleConflict(VOffenderAllSchedules vOffAllSch);
	
	String checkNonAssociations(VOffenderAllSchedulesCommitBean commitBean);

}
