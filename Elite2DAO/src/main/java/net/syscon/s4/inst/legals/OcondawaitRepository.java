
package net.syscon.s4.inst.legals;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.OffenderSentConditions;
import net.syscon.s4.im.beans.Teams;
import net.syscon.s4.inst.legals.beans.OffenderAllocationsSentences;
import net.syscon.s4.inst.legals.beans.OffenderCondTransfer;

public interface OcondawaitRepository {

	List<ReferenceCodes> rgLocationRecGroup(String caseLoadId);

	List<Teams> rgTeamRecGroup(String caseLoadId, String userName);

	List<OffenderCondTransfer> getSentenceData(OffenderAllocationsSentences searchBean);

	List<OffenderCondTransfer> getAwaitingConditions(OffenderAllocationsSentences searchBean);

	Integer offenderCondTransferInsert(List<OffenderCondTransfer> insertList);

	List<ReferenceCodes> getStaffDetails(String caseLoadId);

	List<ReferenceCodes> getTeamMemberDetails(Integer teamId);

	List<OffenderCondTransfer> getAssignedConditions(OffenderAllocationsSentences searchBean);

	List<OffenderAllocationsSentences> getAssignedCondOffenders(OffenderAllocationsSentences searchBean);

	List<OffenderAllocationsSentences> getTransferredCondOffenders(OffenderAllocationsSentences searchBean);

	List<OffenderAllocationsSentences> getTransferredConditons(OffenderAllocationsSentences searchBean);

	List<OffenderSentConditions> checkCondExitsIntkAgyLocId(OffenderAllocationsSentences searchBean);
	
	Integer offenderCondTransferInsertCasePlan(List<OffenderCondTransfer> insertList);
	
	List<Teams> rgTransferTeamRecGroup(String caseLoadId);
	
	List<OffenderCondTransfer> getInstConditionsForOffenders();
	
	Integer updateInstConditonsToCOMM(List<OffenderCondTransfer> updateList);

}
