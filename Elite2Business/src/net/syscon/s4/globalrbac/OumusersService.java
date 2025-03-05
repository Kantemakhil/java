package net.syscon.s4.globalrbac;
import java.util.List;

import net.syscon.s4.common.beans.CaseloadAgencyLocations;
import net.syscon.s4.common.beans.Caseloads;
import net.syscon.s4.common.beans.Images;
import net.syscon.s4.common.beans.StaffMemberRoles;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.beans.UserCreation;
import net.syscon.s4.common.beans.dao.RecordGroup;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.CaseloadAgencyLocationsCommitBean;
import net.syscon.s4.im.beans.OmsRoles;
import net.syscon.s4.im.beans.PersonnelIdentifications;
import net.syscon.s4.im.beans.StaffAccessibleCaseloads;
import net.syscon.s4.im.beans.StaffAccessibleCaseloadsCommitBean;
import net.syscon.s4.im.beans.StaffMemberRolesCommitBean;
import net.syscon.s4.im.beans.StaffMembersCommitBean;
/**
 * Interface OumusersService 
 */
public interface OumusersService  {

	Integer staffMemberRolesCommit(StaffMemberRolesCommitBean commitBean);

	List<OmsRoles>  cgfkchkStaffMemberRolesSt(OmsRoles paramBean);

	List<StaffMemberRoles> staffMemberRolesExecuteQuery(StaffMemberRoles objStaffMemRoles);

	Integer staffAcCommit(StaffAccessibleCaseloadsCommitBean commitBean);

	Caseloads cgfkchkStaffAcStaffAcCsl(Caseloads paramBean);

	List<StaffAccessibleCaseloads> staffAcExecuteQuery(StaffAccessibleCaseloads objStfAccClds);

	String staffCommit(StaffMembersCommitBean commitBean);

	List<Caseloads> rgStaffAcCaseloadIdRecordGroup();

	List<OmsRoles> rgStaffMemberRolesRoleRecordGroup();

	List<RecordGroup> rgStaffAssignedCaseloadRecordGroup();

	List<AgencyLocations> cgfkchkCalCsldAlAgyLoc(AgencyLocations paramBean);

	List<CaseloadAgencyLocations> calExecuteQuery(CaseloadAgencyLocations objClAgyLocs);

	List<PersonnelIdentifications> cgrichkStaffMembers(PersonnelIdentifications paramBean);

	Integer calCommit(CaseloadAgencyLocationsCommitBean commitBean);

	List<StaffMembers> staffExecuteQuery(StaffMembers objStaffMembers);
	
    List<Images> imageExecuteQuery(final StaffMembers bean) ;
    
    List<UserCreation> insightsExecuteQuery(final StaffMembers searchBean);
    
    StaffMembers resetPassword(StaffMembers objStaffMembers);

    List<String> getGroupIdList(String userId);
    
    Integer deleteUserFromGroups(List<String> groupIdList, int user);
    
    Integer removeInsightUser(UserCreation userDeatils);

}