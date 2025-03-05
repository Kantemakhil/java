package net.syscon.s4.inst.programswithoutschedules;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.OffenderCaseNotes;
import net.syscon.s4.im.beans.OffenderCaseNotesCommitBean;

public interface OcdpnoteService {
	List<OffenderCaseNotes> offenderCaseNotesExecuteQuery(OffenderCaseNotes objOffCaseNotes);

	Integer offenderCaseNotesCommit(OffenderCaseNotesCommitBean commitBean);

	List<ReferenceCodes> rgSubTypeRecordGroup();

	List<ReferenceCodes> ocdpnoteGlobalUserAndCaseloadtype(String userName);

	List<ReferenceCodes> ocdpnoteStaffMemberName();
	
	OffenderCaseNotes getModuleName(OffenderCaseNotes objOffenderCaseNotes);

}
