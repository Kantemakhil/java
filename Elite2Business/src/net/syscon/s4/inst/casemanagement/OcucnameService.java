package net.syscon.s4.inst.casemanagement;

import java.util.List;

import net.syscon.s4.im.beans.OffenderCaseNotes;
import net.syscon.s4.im.beans.OffenderCaseNotesCommitBean;

/**
 * Interface OcucnameService
 */
public interface OcucnameService {
	List<OffenderCaseNotes> offCaseNoteExecuteQuery(OffenderCaseNotes object);

	List<OffenderCaseNotes> ocucnameWhenNewFormInstance(OffenderCaseNotes paramBean);

	Integer offCaseNoteCommit(OffenderCaseNotesCommitBean commitBean);

}
