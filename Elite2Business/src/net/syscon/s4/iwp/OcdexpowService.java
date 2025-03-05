package net.syscon.s4.iwp;

import java.util.List;

import net.syscon.s4.cm.primaryofficeragentassignment.beans.ExtOwnershipTransfer;
import net.syscon.s4.cm.primaryofficeragentassignment.beans.ExtOwnershipTransferCommitBean;
import net.syscon.s4.cm.primaryofficeragentassignment.beans.VOffenderAssigned;
import net.syscon.s4.cm.primaryofficeragentassignment.beans.VOffenderAssignedCommitBean;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.im.beans.AgencyLocations;

/**
 * Interface OcdexpowService
 */
public interface OcdexpowService {

	List<SystemProfiles> GetProfileValue(SystemProfiles paramBean);

	Integer vOffenderAssignedCommit(VOffenderAssignedCommitBean CommitBean) throws Exception;

	List<AgencyLocations> cgfkExtOtAgyLocIdFromRecordGroup(final String caseloadid);

	List<ExtOwnershipTransfer> extOtExecuteQuery(ExtOwnershipTransfer objExtOwnershipTransfer);

	List<VOffenderAssigned> vOffenderAssignedExecuteQuery(VOffenderAssigned objVOffenderAssigned);

	Integer extOtCommit(ExtOwnershipTransferCommitBean commitBean);

	List<AgencyLocations> cgfkExtOtAgyLocIdToRecordGroup(final String agylocidfrom);

	List<AgencyLocations> cgfkchkExtOtExtOtAgyLoc(String paramBean);

	List<StaffMembers> rgStaffMembersRecordGroup(final String agylocidfrom);

}
