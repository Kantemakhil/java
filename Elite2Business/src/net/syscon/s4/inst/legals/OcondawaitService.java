package net.syscon.s4.inst.legals;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.OffenderSentConditions;
import net.syscon.s4.im.beans.Teams;
import net.syscon.s4.inst.legals.beans.OffenderAllocationsSentences;
import net.syscon.s4.inst.legals.beans.OffenderCondTransfer;
import net.syscon.s4.inst.legals.beans.OffenderCondTransferCommitBean;

public interface OcondawaitService {

	List<ReferenceCodes> rgLocationRecGroup(String caseLoadId);

	List<Teams> rgTeamRecGroup(String caseLoadId, String userName);

	List<OffenderAllocationsSentences> getSentenceData(OffenderAllocationsSentences searchBean);

	List<OffenderSentConditions> getAwaitingConditions(OffenderAllocationsSentences searchBean);

	List<OffenderCondTransfer> offenderCondTransferCommit(OffenderCondTransferCommitBean commitBean);

	List<ReferenceCodes> getStaffDetails(String caseLoadId);

	List<ReferenceCodes> getTeamMemberDetails(Integer teamId);

	List<OffenderCondTransfer> getAssignedConditions(OffenderAllocationsSentences searchBean);

	List<OffenderAllocationsSentences> getAssignedCondOffenders(OffenderAllocationsSentences searchBean);

	List<OffenderAllocationsSentences> getTransferredCondOffenders(OffenderAllocationsSentences searchBean);

	List<OffenderAllocationsSentences> getTransferredConditons(OffenderAllocationsSentences searchBean);
	
	List<Teams> rgTransferTeamRecGroup(String caseLoadId);

}
