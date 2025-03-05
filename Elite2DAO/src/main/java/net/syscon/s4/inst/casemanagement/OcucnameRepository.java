package net.syscon.s4.inst.casemanagement;

import java.util.List;

import net.syscon.s4.im.beans.OffenderCaseNotes;
import net.syscon.s4.im.beans.OmsModules;

/**
 * Interface OcucnameRepository
 */
public interface OcucnameRepository {
	OmsModules createFormGlobals(OmsModules paramBean);

	OffenderCaseNotes ocucnameWhenNewFormInstancewhenNewFormInstance(OffenderCaseNotes paramBean);

	List<OffenderCaseNotes> offCaseNoteExecuteQuery(OffenderCaseNotes object);

	Integer offNotesUpdateOffenderCaseNotes(List<OffenderCaseNotes> updateList);

}
