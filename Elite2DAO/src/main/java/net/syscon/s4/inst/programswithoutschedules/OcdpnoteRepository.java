package net.syscon.s4.inst.programswithoutschedules;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.Dual;
import net.syscon.s4.im.beans.OffenderCaseNotes;
import net.syscon.s4.im.beans.OmsModules;

public interface OcdpnoteRepository {
	Integer offenderCaseNotesInsertOffenderCaseNotes(List<OffenderCaseNotes> lstOffenderCaseNotes);

	Integer offenderCaseNotesUpdateOffenderCaseNotes(List<OffenderCaseNotes> lstOffenderCaseNotes);

	List<OffenderCaseNotes> offenderCaseNotesExecuteQuery(OffenderCaseNotes objOffenderCaseNotes);

	List<OmsModules> createFormGlobals(OmsModules paramBean);

	List<Object> offenderCaseNotesWhenNewRecordInstance(Dual paramBean);

	List<ReferenceCodes> rgSubTypeRecordGroup();

	List<ReferenceCodes> ocdpnoteGlobalUserAndCaseloadtype(String userName);

	List<ReferenceCodes> ocdpnoteStaffMemberName();
	
	String getModuleName(OffenderCaseNotes objOffenderCaseNotes);

}
