package net.syscon.s4.cm.community_supervision_tiers;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.community_supervision_tiers.MaintainTierLevels;
import net.syscon.s4.community_supervision_tiers.MaintainTierLevelsCommitBean;

public interface OcmtirlvService {

	List<MaintainTierLevels> tierLevelExecuteQuery(final MaintainTierLevels bean);

	Integer tierLevelCommit(MaintainTierLevelsCommitBean commitBean);

	List<ReferenceCodes> rgIntakeTierRecordGroup(); 
	
	
}
