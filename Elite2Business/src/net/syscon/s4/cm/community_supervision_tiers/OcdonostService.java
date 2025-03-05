package net.syscon.s4.cm.community_supervision_tiers;

import java.util.List;

import net.syscon.s4.community_supervision_tiers.WlOfficerNonOffSpecificTasks;
import net.syscon.s4.community_supervision_tiers.WlOfficerNonOffSpecificTasksCommitBean;

public interface OcdonostService {

	WlOfficerNonOffSpecificTasks getStaffName(WlOfficerNonOffSpecificTasks obj);

	List<WlOfficerNonOffSpecificTasks> getNonOffenderSpecificTasks(WlOfficerNonOffSpecificTasks obj);

	Integer offNonOffSpeTaskCommit(WlOfficerNonOffSpecificTasksCommitBean commitBean);

}
