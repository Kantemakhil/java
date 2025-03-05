package net.syscon.s4.globalrbac;

import java.util.List;
import java.util.Map;

import net.syscon.s4.common.beans.CaseloadAgencyLocations;
import net.syscon.s4.common.beans.Caseloads;
import net.syscon.s4.common.beans.Images;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.StaffMemberRoles;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.beans.dao.RecordGroup;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.OffenderMedicalScreenings;
import net.syscon.s4.im.beans.OmsRoles;
import net.syscon.s4.im.beans.PersonnelIdentifications;
import net.syscon.s4.im.beans.PersonnelIssuedCards;
import net.syscon.s4.im.beans.StaffAccessibleCaseloads;

/**
 * Interface OumusersRepository
 */
public interface OumusersRepository {
	PersonnelIssuedCards cgrichkStaffMembers(PersonnelIssuedCards paramBean);

	Integer staffMemberRolesDeleteStaffMemberRoles(List<StaffMemberRoles> lstStaffMemRoles);

	List<PersonnelIdentifications> cgrichkStaffMembers(PersonnelIdentifications paramBean);

	String staffUpdateStaffMembers(List<StaffMembers> lstStaffMembers);

	String staffDeleteStaffMembers(List<StaffMembers> lstStaffMembers);

	List<StaffMemberRoles> staffMemberRolesExecuteQuery(StaffMemberRoles objStaffMemRoles);

	Integer staffMemberRolesInsertStaffMemberRoles(List<StaffMemberRoles> lstStaffMemRoles);

	Integer staffAcDeleteStaffAccessibleCaseloads(List<StaffAccessibleCaseloads> lstStaffAccCl);

	Caseloads cgfkchkStaffAcStaffAcCsl(Caseloads paramBean);

	List<OmsRoles> cgfkchkStaffMemberRolesSt(OmsRoles paramBean);

	List<StaffAccessibleCaseloads> staffAcExecuteQuery(StaffAccessibleCaseloads objStaffAccCl);

	Integer staffAcUpdateStaffAccessibleCaseloads(List<StaffAccessibleCaseloads> lstStaffAccCl);

	List<AgencyLocations> cgfkchkCalCsldAlAgyLoc(AgencyLocations paramBean);

	Integer staffAcInsertStaffAccessibleCaseloads(List<StaffAccessibleCaseloads> lstStaffAccCl);

	Caseloads cgfkchkStaffStaffCsldF1(Caseloads paramBean);

	List<Caseloads> rgStaffAcCaseloadIdRecordGroup();

	List<OmsRoles> rgStaffMemberRolesRoleRecordGroup();

	List<RecordGroup> rgStaffAssignedCaseloadRecordGroup();

	List<OffenderMedicalScreenings> cgrichkStaffMembers(OffenderMedicalScreenings paramBean);

	Integer calUpdateCaseloadAgencyLocations(List<CaseloadAgencyLocations> lstClAgencyLocs);

	List<CaseloadAgencyLocations> calExecuteQuery(CaseloadAgencyLocations objClAgencyLocs);

	List<OffenderBookings> cgrichkStaffMembers(OffenderBookings paramBean);

	List<StaffMembers> staffExecuteQuery(StaffMembers objStaffMembers);

	Map<String, Object> validateUserid(StaffMembers paramBean);

	String cSysUsers(StaffMembers bean);

	Integer userIdCheckCur(StaffMembers bean);

	Integer validateuseridChar(StaffMembers bean);

	List<StaffAccessibleCaseloads> staffCaseLoadData(String userId);

	List<Images> imageExecuteQuery(StaffMembers bean);

	StaffMembers triggerStaffExceQuery(final Integer staffId);

	Integer getValueAssignedCountForStaff(StaffAccessibleCaseloads object);

	Integer staffPasswordReset(StaffMembers objStaffMembers, String pass);

	String getDefaultUserName();

	Integer getValueWorkingCountForStaff(StaffAccessibleCaseloads object);
}
