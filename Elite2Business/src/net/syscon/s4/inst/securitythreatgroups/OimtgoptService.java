package net.syscon.s4.inst.securitythreatgroups;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.common.beans.SecurityThreatGroups;
import net.syscon.s4.common.beans.SecurityThreatGroupsHty;
import net.syscon.s4.common.beans.SecurityThreatGroupsHtyCommitBean;

/**
 * Interface OimtgoptService
 */
public interface OimtgoptService {

	List<SecurityThreatGroupsHty> stgHty1ExecuteQuery(SecurityThreatGroupsHty objSecurityThreatGroupsHty);

	Integer stgHtyCommit(SecurityThreatGroupsHtyCommitBean commitBean);

	List<SecurityThreatGroupsHty> stgHtyExecuteQuery(SecurityThreatGroupsHty objSecurityThreatGroupsHty);

	BigDecimal cgtewhenRadioChanged(BigDecimal param);

	List<SecurityThreatGroups> lNationRecordGroup(Integer parentStgId);

	String stgHty1PostQuery(BigDecimal param);

	List<SecurityThreatGroups> lGangRecordGroup(Integer parentStgId);

}
