package net.syscon.s4.inst.securitythreatgroups;

import java.util.List;

import net.syscon.s4.common.beans.SecurityThreatGroups;
import net.syscon.s4.im.beans.SecurityThreatGroupsCommitBean;

/**
 * Interface OimtgngsService
 */
public interface OimtgngsService {
	List<SecurityThreatGroups> secGrpExecuteQuery(SecurityThreatGroups object);

	Integer secGrpCommit(SecurityThreatGroupsCommitBean commitBean);

	List<SecurityThreatGroups> secGrp1ExecuteQuery(SecurityThreatGroups object);

	Integer secGrp2Commit(SecurityThreatGroupsCommitBean commitBean);

	Integer secGrp1Commit(SecurityThreatGroupsCommitBean commitBean);

	List<SecurityThreatGroups> secGrp2ExecuteQuery(SecurityThreatGroups searchBean);

	Integer getDuplicateStgCode(String stgCode);

	Integer getDuplicateGangsStgCode(String stgCode);

	Integer getDuplicateSetsStgCode(String stgCode);

	Integer offStgCur(Integer stgId);

	Integer offStgCurSecGrp(Integer stgId);
}
