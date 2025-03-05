package net.syscon.s4.inst.securitythreatgroups;

import java.util.List;

import net.syscon.s4.common.beans.SecurityThreatGroups;

/**
 * Interface OimtgngsRepository
 */
public interface OimtgngsRepository {

	Integer secGrp1UpdateSecurityThreatGroups(List<SecurityThreatGroups> object);

	Integer secGrpInsertSecurityThreatGroups(List<SecurityThreatGroups> object);

	Integer secGrp1InsertSecurityThreatGroups(List<SecurityThreatGroups> object);

	List<SecurityThreatGroups> secGrp1WhenNewRecordInstance(SecurityThreatGroups paramBean);

	List<SecurityThreatGroups> secGrpExecuteQuery(SecurityThreatGroups object);

	List<SecurityThreatGroups> secGrp1ExecuteQuery(SecurityThreatGroups object);

	Integer secGrpUpdateSecurityThreatGroups(List<SecurityThreatGroups> object);

	List<SecurityThreatGroups> secGrp1OnCheckDeleteMaster(SecurityThreatGroups paramBean);

	List<SecurityThreatGroups> secGrp2ExecuteQuery(SecurityThreatGroups searchBean);

	Integer getNextValue();

	Integer getSetNextVal();

	Integer getGangNextVal();

	Integer secGrp2InsertSecurityThreatGroups(List<SecurityThreatGroups> insertList);

	Integer secGrp2UpdateSecurityThreatGroups(List<SecurityThreatGroups> updateList);

	Integer getDuplicateStgCode(String stgCode);

	Integer getLpValue();

	Integer getLpGang(Integer stgId);

	Integer getLpSet(Integer parentStgId);

	Integer getDuplicateGangsStgCode(String stgCode);

	Integer getDuplicateSetsStgCode(String stgCode);

	Integer offStgCur(Integer stgId);

	Integer offStgCurSecGrp(Integer stgId);
}
