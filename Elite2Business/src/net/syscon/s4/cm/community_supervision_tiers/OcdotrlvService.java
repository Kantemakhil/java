package net.syscon.s4.cm.community_supervision_tiers;

import java.util.List;

import net.syscon.s4.community_supervision_tiers.OffenderTierLevel;
import net.syscon.s4.community_supervision_tiers.OffenderTierLevelCommitBean;

public interface OcdotrlvService {
	
	List<OffenderTierLevel> offendertierLevelExecuteQuery(final OffenderTierLevel bean);
	
	Integer offendertierLevelCommit(OffenderTierLevelCommitBean commitBean);
	
	List<OffenderTierLevel> offenderTierLevesRecordGroup( String caseLoadId);

	String offTierLevesUserIdRecordGroup(String userName);
	
	Integer defaultTierLevelAfterCustodyIntake(Long offenderBookId,String UserId);


}
