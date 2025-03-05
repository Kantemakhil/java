package net.syscon.s4.inst.securitythreatgroups;

import java.util.List;

import net.syscon.s4.common.beans.SecurityThreatGroups;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.VStgMembershipInquiry;
import net.syscon.s4.im.beans.OmsModules;

/**
 * Interface OiistgmiRepository
 */
public interface OiistgmiRepository {
	List<OmsModules> createFormGlobals(OmsModules paramBean);

	String getStgGroupDescription(SecurityThreatGroups paramBean);

	List<SystemProfiles> oiistgmiWhenNewFormInstance(SystemProfiles paramBean);

	List<VStgMembershipInquiry> vStgMembershipInquiryExecuteQuery(VStgMembershipInquiry object);

	List<VStgMembershipInquiry> vStgMembershipInqExecuteQuery(VStgMembershipInquiry searchRecord);

}
