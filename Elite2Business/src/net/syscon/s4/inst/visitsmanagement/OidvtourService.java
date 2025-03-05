package net.syscon.s4.inst.visitsmanagement;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.inst.visitsmanagement.beans.VisitingGroups;
import net.syscon.s4.inst.visitsmanagement.beans.VisitingGroupsCommitBean;
import net.syscon.s4.inst.visitsmanagement.beans.VisitingMembers;
import net.syscon.s4.inst.visitsmanagement.beans.VisitingMembersCommitBean;

/**
 * Interface OidvtourService
 */
public interface OidvtourService {

	List<ReferenceCodes> rgGroupPurposRecordGroup();

	List<ReferenceCodes> rgIdTypeRecordGroup();

	List<StaffMembers> rgStaffMembersRecordGroup();

	List<AgencyLocations> rgAgencyLocationsRecordGroup(String caseloadId, String caseloadType);

	List<VisitingGroups> visitingGroupsExecuteQuery(VisitingGroups objVisitingGroups);

	Integer visitingMembersCommit(VisitingMembersCommitBean commitBean);

	Integer visitingGroupsCommit(VisitingGroupsCommitBean commitBean);

	List<VisitingMembers> visitingMembersExecuteQuery(VisitingMembers objVisitingMembers);

}
