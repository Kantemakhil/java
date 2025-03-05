package net.syscon.s4.inst.schedules;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.common.beans.OffenderEmployments;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.im.beans.AddressUsages;
import net.syscon.s4.im.beans.OffenderChecklistDetails;
import net.syscon.s4.im.beans.ProfileCodes;
import net.syscon.s4.im.beans.ProfileTypes;
import net.syscon.s4.im.beans.ReleasePlans;
import net.syscon.s4.im.beans.VAddresses;
import net.syscon.s4.inst.property.bean.Persons;

/**
 * Interface OidrplanRepository
 */
public interface OidrplanRepository {
	ProfileTypes offChecklistDetPostQuery(ProfileTypes paramBean);

	Object releasePlansWhenNewRecordInstance(StaffMembers paramBean);

	ProfileTypes offChecklistDetWhenNewRecordInstance(ProfileTypes paramBean);

	List<ReleasePlans> releasePlansExecuteQuery(ReleasePlans objReleasePlans);

	List<StaffMembers> rgParoleAgentsRecordGroup(String caseLoadId);

	List<ReleasePlans> offBkgOnCheckDeleteMaster(ReleasePlans paramBean);

	Integer offChecklistDetUpdateOffenderChecklistDetails(List<OffenderChecklistDetails> lstOffenderChecklistDetails);

	OffenderEmployments releasePlansOffenderEmploymentsPostQuery(OffenderEmployments paramBean);

	List<StaffMembers> rgCaseManagersRecordGroup(String caseLoadId);

	String releasePlansPreInsert(BigDecimal paramBean);

	List<OffenderEmployments> rgProposedEmploymentRecordGroup(Long offenderBookId);

	Object releasePlansAddressUsagesPostQuery(AddressUsages paramBean);

	Integer releasePlansUpdateReleasePlans(List<ReleasePlans> lstReleasePlans);

	Integer releasePlansDeleteReleasePlans(List<ReleasePlans> lstReleasePlans);

	Object releasePlansPreRecord(SystemProfiles paramBean);

	List<VAddresses> releasePlansPostQuery(VAddresses paramBean);

	List<ReferenceCodes> rgEmploymentStatusRecordGroup();

	Integer releasePlansInsertReleasePlans(ReleasePlans data);

	List<ProfileCodes> rgChecklistAnsRecordGroup(String profileType);

	List<ReleasePlans> releasePlansOffenderassesmentsPostQuery(Long offenderBookId);

	ProfileCodes offChecklistDetPostQuery(ProfileCodes paramBean);

	List<ReferenceCodes> rgPlanStatusRecordGroup(final String userName);

	List<ReleasePlans> releasePlansAssesmentsPostQuery(Long offenderBookId, BigDecimal assessmentSeq);

	VAddresses releasePlansPreInsert(VAddresses paramBean);

	List<OffenderChecklistDetails> offChecklistDetExecuteQuery(OffenderChecklistDetails objOffenderChecklistDetails);

	List<OffenderChecklistDetails> releasePlansKeyDelrec(OffenderChecklistDetails paramBean);

	StaffMembers getDescriptionOfStaffId(Integer code);

	List<VAddresses> rgProposedHousingRecordGroup(String rootOffenderId);

	Integer releasePlanPreInsertc();

	Integer updateReleasePlans(List<ReleasePlans> lstReleasePlans);

	Integer existsReleasePlansInprgress(ReleasePlans paramBean);

	Integer offenderCheckListInsertOffenderCheckList(ReleasePlans lstReleasePlans);

	Integer releasePlansPreDelete(List<ReleasePlans> paramBean);

	String rpReadyChkConditionExistsCur(ReleasePlans paramBean);

	List<ProfileTypes> rpReadyChkAllMandQuesCur(ReleasePlans paramBean);

	String rpReadyChkPrimOccContactedCur(ReleasePlans paramBean);

	String rpReadyChkOtherOccExistsCur(ReleasePlans paramBean);

	List<Persons> rpReadyChkPrimaryOccExistsCur(ReleasePlans paramBean);

	ReleasePlans releasePlansPostQueryHousing(BigDecimal rootOffenderId);

	String releasePlansPostQueryAddress(BigDecimal rootOffenderId);

	String releasePlansPostQueryProposedEmployments(Long offenderBookId, BigDecimal employSeq);

	String releasePlansPostQueryCondition(ReleasePlans paramBean);
}
