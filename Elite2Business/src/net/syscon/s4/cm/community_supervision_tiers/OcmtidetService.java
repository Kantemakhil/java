package net.syscon.s4.cm.community_supervision_tiers;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.community_supervision_tiers.MaintainTierDefaultEvents;
import net.syscon.s4.community_supervision_tiers.MaintainTierLevelsCommitBean;

public interface OcmtidetService {

	List<MaintainTierDefaultEvents> tierdefaultEventsExecuteQuery(MaintainTierDefaultEvents searchBean);

	Integer tierdefaultEventsCommit(MaintainTierLevelsCommitBean commitBean);
	
	ReferenceCodes getActiveTierEvent(Long OffnderBooId);
	
	List<ReferenceCodes> rgScheduleTypeRecordGroup(String scheduleType);


}
