package net.syscon.s4.pkgs.comunity_dbtrg_pkg;

import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.triggers.CaseNotes;

public interface ComunityDbtrgPkgRepository {
	StaffMembers vsEmailcur(Long pOffenderBookId);

	String vsCaseNoteCur(String casNotType, String reason, String triggerEvent);

	Long vsNseqCur(Long offenderBookId);

	Integer insertCaseNotes(CaseNotes caseNotes);

	StaffMembers vsStaffCur(String cUser);

	VHeaderBlock vsOffInfoCur(Long offenderBookId,String userId);

	String curEmailPboard();

	String vsCaseNotesCur(String casNotType, String reason);

	String staffCur(Integer staffId);

	String agyLocCur(String pAgyLocId);

	String eventDspCur(String pEventType);

	String evtSubDspCur(String pEventSubType, String pEventType);
}
