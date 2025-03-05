package net.syscon.s4.cm.community_supervision_tiers;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.community_supervision_tiers.MaintainTierLevels;

public interface OcmtirlvRepository {

	List<MaintainTierLevels> tierLevelExecuteQuery(final MaintainTierLevels bean);
	
	Integer insertMaintainCommunityTierLevels(List<MaintainTierLevels> maintainTierLevels);

	Integer updateMaintainCommunityTierLevels(List<MaintainTierLevels> maintainTierLevels);
	
	Integer deleteMaintainCommunityTierLevels(List<MaintainTierLevels> maintainTierLevels);
	
	List<ReferenceCodes> rgIntakeTierRecordGroup();
}
