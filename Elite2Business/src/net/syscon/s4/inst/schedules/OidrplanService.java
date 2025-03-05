package net.syscon.s4.inst.schedules;

import java.util.List;

import net.syscon.s4.common.beans.OffenderEmployments;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.im.beans.OffenderChecklistDetails;
import net.syscon.s4.im.beans.OffenderChecklistDetailsCommitBean;
import net.syscon.s4.im.beans.ProfileCodes;
import net.syscon.s4.im.beans.ProfileTypes;
import net.syscon.s4.im.beans.ReleasePlans;
import net.syscon.s4.im.beans.ReleasePlansCommitBean;
import net.syscon.s4.im.beans.VAddresses;

/**
 * Interface OidrplanService
 */
public interface OidrplanService {

	Object releasePlansWhenNewRecordInstance(StaffMembers paramBean);

	List<ReleasePlans> releasePlansExecuteQuery(ReleasePlans objReleasePlans);

	List<StaffMembers> rgParoleAgentsRecordGroup(String caseLoadId);

	List<ReferenceCodes> rgEmploymentStatusRecordGroup();

	Integer offChecklistDetCommit(OffenderChecklistDetailsCommitBean CommitBean);

	List<ProfileCodes> rgChecklistAnsRecordGroup(String profileType);

	ProfileTypes offChecklistDetPostQuery(ProfileTypes paramBean);

	List<StaffMembers> rgCaseManagersRecordGroup(String caseLoadId);

	List<OffenderChecklistDetails> releasePlansKeyDelrec(OffenderChecklistDetails paramBean);

	List<ReferenceCodes> rgPlanStatusRecordGroup(final String userName);

	List<OffenderEmployments> rgProposedEmploymentRecordGroup(Long offenderBookId);

	List<ReleasePlans> offBkgOnCheckDeleteMaster(ReleasePlans paramBean);

	List<OffenderChecklistDetails> offChecklistDetExecuteQuery(OffenderChecklistDetails objOffenderChecklistDetails);

	VAddresses releasePlansPreInsert(VAddresses paramBean);

	ProfileTypes offChecklistDetWhenNewRecordInstance(ProfileTypes paramBean);

	Object releasePlansPreRecord(SystemProfiles paramBean);

	List<VAddresses> rgProposedHousingRecordGroup(String rootOffenderId);

	Integer releasePlansCommit(ReleasePlansCommitBean commitBean);

	ReferenceCodes rpReadyForApproval(ReleasePlans releasePlan);

}
