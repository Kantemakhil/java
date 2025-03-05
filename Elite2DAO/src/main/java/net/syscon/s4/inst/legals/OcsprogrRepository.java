package net.syscon.s4.inst.legals;

import java.util.List;

import net.syscon.s4.cm.programsservices.VOffenderSentCondActs;
import net.syscon.s4.cm.teamsworkflow.beans.TeamMembers;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.SentenceCalcTypes;
import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.inst.accreditedprograms.beans.VProgramProviders;
import net.syscon.s4.inst.casemanagement.beans.ProgramServices;

public interface OcsprogrRepository {

	List<ReferenceCodes> rgRefCodeRecordGroup();
	
	List<ProgramServices> rgAccProgramRecordGroup();
	
	List<OffenderBookings> offExecQuery(List<String> status,String programId,String intProviderPartyId,String extProviderPartyId,String currentCaseload);
	
	 List<TeamMembers> rgRefCodeProviderGroup(String userId);
	 
	 List<VProgramProviders> rgProviderRecordGroupTeam();

	List<OffenderBookings> getOffPrgData(OffenderBookings searchBean);

}
