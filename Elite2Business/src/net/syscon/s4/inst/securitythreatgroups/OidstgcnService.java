package net.syscon.s4.inst.securitythreatgroups;

import java.util.List;

import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.im.beans.StgCaseNotes;
import net.syscon.s4.im.beans.StgCaseNotesCommitBean;

/**
 * Interface OidstgcnService
 */
public interface OidstgcnService {

	List<ReferenceCodes> rgNoteReasonRecordGroup();

	Integer stgCaseNotesCommit(StgCaseNotesCommitBean commitBean);

	List<ReferenceCodes> rgNoteTypeRecordGroup();

	List<StgCaseNotes> stgCaseNotesExecuteQuery(StgCaseNotes objStgCaseNotes);

	List<String> getParentCodes();

}
