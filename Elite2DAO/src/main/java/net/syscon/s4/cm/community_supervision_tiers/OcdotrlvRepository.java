package net.syscon.s4.cm.community_supervision_tiers;

import java.util.List;

import net.syscon.s4.community_supervision_tiers.MaintainTierDefaultEvents;
import net.syscon.s4.community_supervision_tiers.OffenderTierLevel;

public interface OcdotrlvRepository {
	
	List<OffenderTierLevel> offendertierLevelExecuteQuery(final OffenderTierLevel bean);
	
	Integer insertOffenderTierLevel(List<OffenderTierLevel> offenderTierLevel);

	Integer updateOffenderTierLevel(List<OffenderTierLevel> offenderTierLevel);
	
	Integer deleteOffenderTierLevel(List<OffenderTierLevel> offenderTierLevel);
	
	List<OffenderTierLevel> offenderTierLevesRecordGroup(String caseLoadId);
	
	OffenderTierLevel getDefaultIntakeTier();
	
	Long getStaffId();
	
	Long getReviewDays(String code);
	
	List<MaintainTierDefaultEvents> getMainTierDefaultEvents(String tierLevel);
	
	Long getMaxTierLevel(Long offenderBokId);

}
