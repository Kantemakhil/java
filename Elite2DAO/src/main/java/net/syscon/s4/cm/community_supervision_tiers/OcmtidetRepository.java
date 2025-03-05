package net.syscon.s4.cm.community_supervision_tiers;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.community_supervision_tiers.MaintainTierDefaultEvents;

public interface OcmtidetRepository {
	
	List<MaintainTierDefaultEvents> tierdefaultEventsExecuteQuery(MaintainTierDefaultEvents bean);
	
	Integer insertTierdefaultEvents(List<MaintainTierDefaultEvents> maintainTierLevels);

	Integer updateTierdefaultEvents(List<MaintainTierDefaultEvents> maintainTierLevels);
	
	Integer deleteTierdefaultEvents(List<MaintainTierDefaultEvents> maintainTierLevels);
	
	ReferenceCodes getActiveTierEvent(Long offenderBookId);
	
	List<ReferenceCodes> rgScheduleTypeRecordGroup(String scheduleType);
	
	String getTierLevelCode(BigDecimal eventId);
	
	List<MaintainTierDefaultEvents> getActiveTierLevelData(Long offenderBookId,BigDecimal offenderTierId ,String eventType,String eventSubType,Long TierEventSchVersionId);
	
	MaintainTierDefaultEvents getDeafultTierEventData(String eventType,String eventSubType,BigDecimal offenderTierLevelId,Long offenderBookId,Long version);
	
	MaintainTierDefaultEvents getActiveTierLevelRecord(Long offenderBookId);
	
	BigDecimal getOffenderTierLevelId(Long eventId);

	Long getOffenderTierLevelVersionNo(Long eventId);
	
	MaintainTierDefaultEvents getOffenderTierlevelVersionNoBasedDetails(String eventType,String eventSubType,String tierLevelCode,Long version);
}
