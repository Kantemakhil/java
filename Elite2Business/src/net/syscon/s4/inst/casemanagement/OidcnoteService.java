package net.syscon.s4.inst.casemanagement;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.im.beans.OffenderCaseNotes;
import net.syscon.s4.im.beans.OffenderCaseNotesCommitBean;

/**
 * Interface OidcnoteService
 */
public interface OidcnoteService {
	List<ReferenceCodes> rgnoteSourceRecordGroup();

	List<OffenderCaseNotes> offNotesExecuteQuery(OffenderCaseNotes searchRecord);

	List<ReferenceCodes> rgCasenoteTypeRecordGroup(String caseloadType, String userName);

	List<ReferenceCodes> rgCasenoteSubtypeRecordGroup(String caseNoteType, String userName,String caseloadType);

	List<StaffMembers> rgStaffnameRecordGroup(String userName );

	Integer offNotesCommit(OffenderCaseNotesCommitBean commitBean);
	
	String checkCasenoteSubType(String caseNoteType, String caseNoteCode, String userName);
}
