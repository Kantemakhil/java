package net.syscon.s4.cm.programsservices.maintenance;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.OffenderCaseNotes;
import net.syscon.s4.im.beans.OffenderCaseNotesCommitBean;
import net.syscon.s4.im.beans.OffenderPrgObligations;
import net.syscon.s4.im.beans.OffenderPrgObligationsCommitBean;
import net.syscon.s4.im.beans.OffenderProgramProfiles;
import net.syscon.s4.im.beans.OffenderProgramProfilesCommitBean;
import net.syscon.s4.inst.casemanagement.beans.ProgramServices;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.VOffenderCourseEvents;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.VOffenderCourseEventsCommitBean;

public interface OidowrelService {

	List<OffenderPrgObligations> offPrgObligationsExecuteQuery(OffenderPrgObligations objOffenderPrgObligations);

	String getStaffName(String userName);

	List<ProgramServices> rgProgramRecordGroup();

	List<ReferenceCodes> rgEndReasonRecordGroup();

	Integer vOffenderCourseEventsCommit(VOffenderCourseEventsCommitBean CommitBean);

	Integer offProgramProfilesCommit(OffenderProgramProfilesCommitBean commitBean);

	Integer offenderCaseNotesCommit(OffenderCaseNotesCommitBean CommitBean);

	List<VOffenderCourseEvents> vOffenderCourseEventsExecuteQuery(VOffenderCourseEvents objVOffenderCourseEvents);

	List<ReferenceCodes> rgCancelReasonRecordGroup();

	Integer offPrgObligationsCommit(OffenderPrgObligationsCommitBean CommitBean);

	List<OffenderCaseNotes> offenderCaseNotesExecuteQuery(OffenderCaseNotes objOffenderCaseNotes);

	List<ReferenceCodes> rgPriorityRecordGroup();

	List<OffenderProgramProfiles> offProgramProfilesExecuteQuery(OffenderProgramProfiles objOffenderProgramProfiles);

	OffenderCaseNotes getModuleName(OffenderCaseNotes objOffenderCaseNotes);
	
	String checkNonAssociations(OffenderProgramProfilesCommitBean commitBean);
}
