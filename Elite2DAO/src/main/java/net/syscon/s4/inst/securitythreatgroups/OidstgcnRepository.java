package net.syscon.s4.inst.securitythreatgroups;

import java.util.List;

import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.im.beans.StgCaseNotes;

/**
 * Interface OidstgcnRepository
 */
public interface OidstgcnRepository {

	List<OmsModules> createFormGlobals(OmsModules paramBean);

	Integer stgCaseNotesUpdateStgCaseNotes(List<StgCaseNotes> lstStgCaseNotes);

	List<ReferenceCodes> rgNoteReasonRecordGroup();

	Integer stgCaseNotesInsertStgCaseNotes(List<StgCaseNotes> lstStgCaseNotes);

	List<ReferenceCodes> stgCaseNotesPostQuery(ReferenceCodes paramBean);

	List<ReferenceCodes> rgNoteTypeRecordGroup();

	Integer stgCaseNotesDeleteStgCaseNotes(List<StgCaseNotes> lstStgCaseNotes);

	List<StgCaseNotes> stgCaseNotesExecuteQuery(StgCaseNotes objStgCaseNotes);

	Long identifierSeqData(Long stgId);

	List<String> getParentCodes();

}
