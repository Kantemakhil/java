package net.syscon.s4.inst.casemanagement;

import java.util.List;

import net.syscon.s4.common.beans.CaseNotePermissions;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.im.beans.OffenderCaseNotes;

/**
 * Interface OidcnoteRepository
 */
public interface OidcnoteRepository {
	List<OffenderCaseNotes> offBkgOnCheckDeleteMaster(OffenderCaseNotes paramBean);

	List<ReferenceCodes> rgnoteSourceRecordGroup();

	List<OffenderCaseNotes> offNotesExecuteQuery(OffenderCaseNotes objOffenderCaseNotes);

	List<ReferenceCodes> rgCasenoteTypeRecordGroup(String caseloadType, String userName);

	List<ReferenceCodes> rgCasenoteSubtypeRecordGroup(String caseNoteType, String userName,String caseloadType);

	Integer offNotesInsertOffenderCaseNotes(List<OffenderCaseNotes> lstOffenderCaseNotes);

	Integer offNotesUpdateOffenderCaseNotes(List<OffenderCaseNotes> lstOffenderCaseNotes);

	List<StaffMembers> rgStaffnameRecordGroup(String userName);

	Integer offNotesDeleteOffenderCaseNotes(List<OffenderCaseNotes> lstOffenderCaseNotes);

	StaffMembers getStaffnameQuery(String userName);

	Integer getcaseNoteId();

	String getmoduleName(String caseNoteType, String caseNoteSubType);
	
	String checkCasenoteSubType(String caseNoteType, String caseNoteCode, String userName);

	List<CaseNotePermissions> getCaseNoteLovs(String user);

}
