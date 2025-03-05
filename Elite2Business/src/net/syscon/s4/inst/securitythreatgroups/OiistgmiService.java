package net.syscon.s4.inst.securitythreatgroups;

import java.util.List;

import net.syscon.s4.common.beans.SecurityThreatGroups;
import net.syscon.s4.common.beans.VStgMembershipInquiry;
import net.syscon.s4.common.beans.VStgMembershipInquiryCommitBean;

/**
 * Interface OiistgmiService
 */
public interface OiistgmiService {
	Integer vStgMembershipInquiryCommit(VStgMembershipInquiryCommitBean commitBean);

	List<VStgMembershipInquiry> vStgMembershipInquiryExecuteQuery(VStgMembershipInquiry object);

	String getStgGroupDescription(SecurityThreatGroups searchBean);
}
