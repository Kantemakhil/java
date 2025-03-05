package net.syscon.s4.iwp;

import java.util.List;

import net.syscon.s4.cm.primaryofficeragentassignment.beans.ExtOwnershipTransfer;
import net.syscon.s4.cm.primaryofficeragentassignment.beans.ExtOwnershipTransferCommitBean;
import net.syscon.s4.cm.primaryofficeragentassignment.beans.VOmTeamMembers;
import net.syscon.s4.cm.primaryofficeragentassignment.beans.VOmTeamMembersCommitBean;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.OffenderBookingsCommitBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.Teams;

/**
 * Interface OcdorassService
 */
public interface OcdorassService {
	List<AgencyLocations> rgAgyLocIdRecordGroup(final String caseLoadId);

	List<ReferenceCodes> rgPositionRecordGroup();

	List<ReferenceCodes> rgRoleRecordGroup();

	List<ReferenceCodes> rgSexCodeRecordGroup();

	List<OffenderBookings> offBkg1ExecuteQuery(OffenderBookings objOffenderBookings);

	Integer offBkg1Commit(OffenderBookingsCommitBean commitBean);

	Integer vOffDetCommit(VOmTeamMembersCommitBean commitBean) throws Exception;

	List<ExtOwnershipTransfer> extOtExecuteQuery(ExtOwnershipTransfer objExtOwnershipTransfer);

	List<Teams> rgTeamRecordGroup(String sealFlag);

	List<VOmTeamMembers> vOffDetExecuteQuery(VOmTeamMembers objVOmTeamMembers);

	List<ReferenceCodes> rgScheduleTypeRecordGroup();

	Integer extOtCommit(ExtOwnershipTransferCommitBean commitBean);
	
	Boolean ocdorassGetOmTeamMand();

}
