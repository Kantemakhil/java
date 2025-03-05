package net.syscon.s4.iwp;

import java.util.Date;
import java.util.List;

import net.syscon.s4.cf.deductions.beans.FeeAccountProfiles;
import net.syscon.s4.cm.primaryofficeragentassignment.beans.ExtOwnershipTransfer;
import net.syscon.s4.cm.primaryofficeragentassignment.beans.VOffenderAssigned;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.inmate.beans.CaseloadDeductionProfiles;

/**
 * Interface OcdexpowRepository
 */
public interface OcdexpowRepository {
	List<AgencyLocations> cgfkExtOtAgyLocIdFromRecordGroup(final String caseloadid);

	List<ExtOwnershipTransfer> extOtExecuteQuery(ExtOwnershipTransfer objExtOwnershipTransfer);

	Integer vOffenderAssignedUpdateVOffenderAssigned(List<VOffenderAssigned> lstVOffenderAssigned);

	List<VOffenderAssigned> vOffenderAssignedExecuteQuery(VOffenderAssigned objVOffenderAssigned);

	List<AgencyLocations> cgfkExtOtAgyLocIdToRecordGroup(final String agylocidfrom);

	List<SystemProfiles> getProfileValue(SystemProfiles paramBean);

	List<StaffMembers> rgStaffMembersRecordGroup(final String agylocidfrom);

	Integer extOtInsertextOt(final List<ExtOwnershipTransfer> lstExtOwnershipTransfer);

	List<AgencyLocations> cgfkchkExtOtExtOtAgyLoc(String agylocidfrom);

	Long getExtTransferId(Long extTrnferid);

	List<FeeAccountProfiles> getFeeActsDet(VOffenderAssigned obj);

	Date getSupervisionStartdate(ExtOwnershipTransfer bean);

	Integer updateFeeAccounts(List<FeeAccountProfiles> feeUpdatelist);
	
	Integer updateFeeAcntCaseLoad(List<FeeAccountProfiles> feeUpdatelist);
	
	List<String> getCaseLoadType(VOffenderAssigned vOffAssignObj);

	List<FeeAccountProfiles> getSupData(ExtOwnershipTransfer beanObject);
}
