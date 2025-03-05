package net.syscon.s4.iwp;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import net.syscon.s4.cf.deductions.beans.FeeAccountProfiles;
import net.syscon.s4.cf.deductions.maintenance.beans.FeeOverrideDetails;
import net.syscon.s4.cm.primaryofficeragentassignment.beans.ExtOwnershipTransfer;
import net.syscon.s4.cm.primaryofficeragentassignment.beans.VOmTeamMembers;
import net.syscon.s4.common.beans.OffenderBookingEvent;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.OffenderCommunityFile;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.CasePlans;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.im.beans.Teams;
import net.syscon.s4.inst.legals.beans.OffenderCondTransfer;
import net.syscon.s4.sa.usersystemsecurity.beans.AssignmentTransfers;

/**
 * Interface OcdorassRepository
 */
public interface OcdorassRepository {
	List<AgencyLocations> rgAgyLocIdRecordGroup(final String caseLoadId);

	List<ReferenceCodes> rgPositionRecordGroup();

	List<ReferenceCodes> rgRoleRecordGroup();

	List<ReferenceCodes> rgSexCodeRecordGroup();

	List<OffenderBookings> offBkg1ExecuteQuery(OffenderBookings objOffenderBookings);

	List<ExtOwnershipTransfer> extOtExecuteQuery(ExtOwnershipTransfer objExtOwnershipTransfer);

	List<Teams> rgTeamRecordGroup(String position, String role, Long staffid);

	Integer vOffDetUpdateVOmTeamMembers(List<VOmTeamMembers> lstVOmTeamMembers);

	Integer extOtUpdateExtOwnershipTransfer(List<ExtOwnershipTransfer> lstExtOwnershipTransfer);

	List<VOmTeamMembers> vOffDetExecuteQuery(VOmTeamMembers objVOmTeamMembers);

	Integer offBkg1UpdateOffenderBookings(List<OffenderBookings> lstOffenderBookings);

	List<ReferenceCodes> rgScheduleTypeRecordGroup();

	Offenders offenderBookingsPostQuery(BigDecimal rootOffenderId);

	Long ocdorassExtotGetCountCasePlans(final VOmTeamMembers paramBean);

	List<ExtOwnershipTransfer> ocdorassExtotGetLastNameFirstName(ExtOwnershipTransfer searchRecord);

	String ocdorassExtotGetAgyLocDesc(ExtOwnershipTransfer searchRecord);

	Offenders ocdorassExtotGetPostQuery(Long offenderBookId);

	Long ocdorassGetCountTeamMembers(VOmTeamMembers parambean);

	Long ocdorassGetCountTwoTeamMembers(VOmTeamMembers parambean);

	Long ocdorassGetTeamCodeDescription(VOmTeamMembers parambean);

	Integer defaultTeam(Long staffId);

	String getTeamDesc(Integer teamId);

	Boolean ocdorassGetOmTeamMand();

	String getSuperVersionLevel(Long offenderBookId);

	Integer getReviewPeriodCaseReviewPeriods(String vSuperVisionLevel);

	Long getMaxCasePlanId(Long offenderBookId);

	Date getFromDateStaffLocationRoles(VOmTeamMembers objectOne);

	Integer getUpdateCasePlans(VOmTeamMembers returnObj);

	String getCreationUserCasePlans(Long offenderBookId, long vId);

	List<CasePlans> getInstInfoCasePlans(CasePlans paramBean);

	Integer ocdorassGetCountCasePlans(Long offenderBookId);

	Integer ocdorassInsertCasePlans(VOmTeamMembers returnObj);

	Long getAsstraSeqNextval();

	Integer ocdorassInsertAssignmentTransfers(VOmTeamMembers obj);

	Integer ocdorassUpdateAssignmentTransfers(VOmTeamMembers obj);

	Integer updateExtOwnershipTransfer(ExtOwnershipTransfer parambean);

	Long getEventSeqOffenderBookingsEvents(Long offenderBookId);

	String getProfileValueSystemProfiles();

	Integer getUpdateOffenderCommunityFiles(VOmTeamMembers obj);

	List<OffenderCommunityFile> getDetailsOffenderCommunityFiles(ExtOwnershipTransfer object);

	Integer getUpdateOffenderCommunityFilesTwo(ExtOwnershipTransfer object);

	String transferFile(ExtOwnershipTransfer obj);

	Boolean getCgnbtSkillSubTypeVOmTeamMembers(Long vstaffId);

	VOmTeamMembers getPreviousOffenderWorkAssignments(long vBookId);

	Long countOffenderBookingAgyLocs(Long offenderBookId, String agyLocId);

	Integer insertIntoOffenderBookingEvents(ExtOwnershipTransfer obj);

	String assaignOffender(Long vOffBookId, Integer vTeamId);

	AssignmentTransfers getOffenderWorkAssignmentsOffBkgCommit(Long offenderBookId);

	Long getOffenderFileSeqOffenderCommunityFiles(Long offenderId);

	Integer getUpdateOffenderBookingAgyLocs(OffenderBookings object);

	Integer ocdorassGetCaseOfficerId(Long lvOffenderBookId);

	Integer insertOffenderBookingAgyLocs(OffenderBookings object);

	Integer updateOffenderBookingAgyLocs(OffenderBookings object);

	Integer ocdorassGetupdateOffenderBookingAgyLocs(OffenderBookings returnBean);

	Integer ocdorassGetinsertOffenderBookingAgyLocs(OffenderBookings returnBean);

	Long getOffenderIdOffenderBookings(Long vBookId);

	List<Teams> getCountOfTeamEnable();

	OffenderBookings getOffBkgsOldRec(OffenderBookings bean);

	void executingOmtofsbTrigger(String modifyUserId);

	List<FeeOverrideDetails> getFeeOverride(Long offenderBookId, String agyLocIdFrom);
	
	BigDecimal getOffenderId(Long offenderBookId);
	
	Integer checkFeeAccountStatus(Long offenderBookId, String agyLocIdTo);
	
	Integer checkSupvAccnt(Long offenderFeeId);
	
	List<FeeAccountProfiles> getActiveFeeOverridesAtReceiveCounty(Long offenderBookId, String agyLocIdTo);

	Integer getOffTierLevelWlUnits(Long offenderBookId);

	List<Integer> getWorkedStaffMembers(Long offenderBookId);

	List<OffenderCondTransfer> getOffenderCondition(Long vBookId);

	

}
