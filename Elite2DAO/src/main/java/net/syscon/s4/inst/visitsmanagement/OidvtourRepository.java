package net.syscon.s4.inst.visitsmanagement;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.inst.visitsmanagement.beans.VisitingGroups;
import net.syscon.s4.inst.visitsmanagement.beans.VisitingMembers;

/**
 * Interface OidvtourRepository
 */
public interface OidvtourRepository {
	Integer visitingMembersInsertVisitingMembers(List<VisitingMembers> lstVisitingMembers);

	// Object visitingGroupsPreInsert(SysDual paramBean) ;

	List<ReferenceCodes> rgGroupPurposRecordGroup();

	List<ReferenceCodes> rgIdTypeRecordGroup();

	List<AgencyLocations> rgAgencyLocationsRecordGroup(String caseloadId, String caseloadType);

	List<StaffMembers> rgStaffMembersRecordGroup();

    Integer visitingMembersDeleteVisitingMembers(List<VisitingMembers> lstVisitingMembers) ;

	// VisitingMembers visitingGroupsOnCheckDeleteMaster(VisitingMembers
	// paramBean) ;

	 Integer visitingMembersUpdateVisitingMembers(List<VisitingMembers> lstVisitingMembers) ;

	// VisitingMembers visitingMembersPreInsert(VisitingMembers paramBean) ;

	List<VisitingGroups> visitingGroupsExecuteQuery(VisitingGroups objVisitingGroups);

	List<VisitingMembers> visitingMembersExecuteQuery(VisitingMembers objVisitingMembers);

	// List<Object> cgwhenNewFormInstance(SysDual paramBean) ;

    Integer visitingGroupsInsertVisitingGroups(List<VisitingGroups> lstVisitingGroups) ;

	// OmsModules createFormGlobals(OmsModules paramBean) ;

	 Integer visitingGroupsUpdateVisitingGroups(List<VisitingGroups> lstVisitingGroups) ;

	// List<Object> defaultAgyLocId(CaseloadAgencyLocations paramBean) ;

	Integer visitingGroupsDeleteVisitingGroups(List<VisitingGroups> lstVisitingGroups) ;

	StaffMembers getStaffnameQuery(Integer staffId);
	
	Integer visitingGroupsPreInsert();

	Integer getMemberSeq(Integer groupId);

}
