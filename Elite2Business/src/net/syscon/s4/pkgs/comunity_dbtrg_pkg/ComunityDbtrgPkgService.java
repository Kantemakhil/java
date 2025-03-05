package net.syscon.s4.pkgs.comunity_dbtrg_pkg;

import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.triggers.CaseNotes;

public interface ComunityDbtrgPkgService {

	String getEmailadres(Long pOffenderBookId);

	Boolean getActiveFlag(String casNotType, String reason, String triggerEvent);

	Long getNoteSeq(Long offenderBookId);

	Integer getStaffId(String userId);

	Integer insertCaseNNotes(CaseNotes caseNotes);

	VHeaderBlock commEmailParams(Long offenderBookId,String userId);

	String getEmailPboard();

	Boolean getActiveFlag(String casNotType, String reason);

	String getStaffInfo(Integer staffId);

	String getMiscValues(Integer type, String pAgyLocId, String pEventType, String pEventSubType);
	
}
