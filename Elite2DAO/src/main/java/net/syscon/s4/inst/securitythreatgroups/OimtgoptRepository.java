package net.syscon.s4.inst.securitythreatgroups;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.common.beans.SecurityThreatGroups;
import net.syscon.s4.common.beans.SecurityThreatGroupsHty;
import net.syscon.s4.im.beans.OmsModules;

/**
 * Interface OimtgoptRepository
 */
public interface OimtgoptRepository {
	BigDecimal cgtewhenRadioChanged(BigDecimal param);

	List<OmsModules> createFormGlobals(OmsModules paramBean);

	List<SecurityThreatGroupsHty> stgHty1ExecuteQuery(SecurityThreatGroupsHty objSecurityThreatGroupsHty);

	Integer stgHtyInsertSecurityThreatGroupsHty(List<SecurityThreatGroupsHty> lstSecurityThreatGroupsHty);

	List<SecurityThreatGroupsHty> stgHtyExecuteQuery(SecurityThreatGroupsHty objSecurityThreatGroupsHty);

	String stgHty1PostQuery(BigDecimal param);

	List<SecurityThreatGroups> lGangRecordGroup(Integer parentStgId);

	List<SecurityThreatGroups> lNationRecordGroup(Integer parentStgId);

	BigDecimal preInsert(BigDecimal stgId);

	SecurityThreatGroups preInsertSecurityThreatGroups(BigDecimal stgId);

	Integer updateSecurityThreatGroups(SecurityThreatGroupsHty action);

	Integer updateSecurityThreatGroupsOne(SecurityThreatGroupsHty action);

	SecurityThreatGroups toParentStgIdData(SecurityThreatGroupsHty action);

	Integer updateSecurityThreatGroupsTwo(SecurityThreatGroupsHty action);

}
