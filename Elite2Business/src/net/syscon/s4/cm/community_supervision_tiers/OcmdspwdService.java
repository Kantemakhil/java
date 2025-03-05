package net.syscon.s4.cm.community_supervision_tiers;

import java.util.List;

import net.syscon.s4.community_supervision_tiers.WlDefaultStaffUnits;
import net.syscon.s4.community_supervision_tiers.WlNonOffSpecificTasks;
import net.syscon.s4.community_supervision_tiers.WlNonOffSpecificTasksCommitBean;
import net.syscon.s4.im.beans.AgencyLocations;

public interface OcmdspwdService {

	List<AgencyLocations> getAgyLocRecordGroup(String caseloadId);

	Integer maintainDefStaffPosWL(WlNonOffSpecificTasksCommitBean commitBean);

	List<WlDefaultStaffUnits> assStartingDefWLUnitsExecuteQuery();

	List<WlNonOffSpecificTasks> nonOffSpecTaskPosExecuteQuery(WlNonOffSpecificTasks obj);

}
