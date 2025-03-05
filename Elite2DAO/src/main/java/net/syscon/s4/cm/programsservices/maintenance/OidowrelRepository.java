package net.syscon.s4.cm.programsservices.maintenance;

import java.util.List;

import net.syscon.s4.common.beans.OffenderExternalMovements;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.Corporates;
import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.im.beans.OffenderCaseNotes;
import net.syscon.s4.im.beans.OffenderPrgObligations;
import net.syscon.s4.im.beans.OffenderProgramProfiles;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.im.beans.VCourseActivities;
import net.syscon.s4.inst.casemanagement.beans.ProgramAssessments;
import net.syscon.s4.inst.casemanagement.beans.ProgramServices;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.VOffenderCourseEvents;
import net.syscon.s4.triggers.OffenderCourseAttendances;

public interface OidowrelRepository {
	
	OmsModules createFormGlobals(OmsModules paramBean);

	List<OffenderPrgObligations> offPrgObligationsExecuteQuery(OffenderPrgObligations objOffenderPrgObligations);
	
	Integer insertOffenderPrgObligations(List<OffenderPrgObligations> lstOffenderPrgObligations);

	Integer updateOffenderPrgObligations(List<OffenderPrgObligations> lstOffenderPrgObligations);

	Integer deleteOffenderPrgObligations(List<OffenderPrgObligations> lstOffenderPrgObligations);

	Integer insertOffenderProgramProfiles(List<OffenderProgramProfiles> lstOffenderProgramProfiles);

	Integer updateOffenderProgramProfiles(List<OffenderProgramProfiles> lstOffenderProgramProfiles);

	List<ReferenceCodes> rgPriorityRecordGroup();
	
	List<ProgramServices> rgProgramRecordGroup();
	
	String getOldStatus(Long offenderPrgObligationId);
	
	Long getseqPrgRefIdSeq();

	Corporates vOffenderCourseEventsPostQuery(Corporates paramBean);

	List<OffenderCourseAttendances> chkDeleteRule(OffenderCourseAttendances paramBean);

	ProgramAssessments displayAssessmentButton(ProgramAssessments paramBean);

	List<ReferenceCodes> rgEndReasonRecordGroup();

	CourseActivities createAllocation(CourseActivities paramBean);

	ProgramServices callOcuoscpv(ProgramServices paramBean);

	Integer insertOffenderCaseNotes(List<OffenderCaseNotes> lstOffenderCaseNotes);

	List<VOffenderCourseEvents> vOffenderCourseEventsExecuteQuery(VOffenderCourseEvents objVOffenderCourseEvents);

	Integer offenderCaseNotesUpdateOffenderCaseNotes(List<OffenderCaseNotes> lstOffenderCaseNotes);
	
	Integer offenderCaseNotesDeleteOffenderCaseNotes(final List<OffenderCaseNotes> lstOffenderCaseNotes);

	Corporates offProgramProfilesPostQuery(Corporates paramBean);

	ProgramServices callOcuupsta(ProgramServices paramBean);

	Integer vOffenderCourseEventsUpdateVOffenderCourseEvents(List<VOffenderCourseEvents> list);

	List<ReferenceCodes> rgCancelReasonRecordGroup();

	List<OffenderPrgObligations> offBkgOnCheckDeleteMaster(OffenderPrgObligations paramBean);

	List<VOffenderCourseEvents> offProgramProfilesOnCheckDeleteMaster(VOffenderCourseEvents paramBean);

	ReferenceCodes offenderCaseNotesPreInsert(ReferenceCodes paramBean);

	List<OffenderCaseNotes> offBkgOnCheckDeleteMaster(OffenderCaseNotes paramBean);

	List<OffenderProgramProfiles> offPrgObligationsOnCheckDeleteMaster(OffenderProgramProfiles paramBean);

	List<OffenderCaseNotes> offenderCaseNotesExecuteQuery(OffenderCaseNotes objOffenderCaseNotes);

	List<VOffenderCourseEvents> vOffenderCourseEventsWhenNewRecordInstance(VOffenderCourseEvents paramBean);

	Object vOffenderCourseEventsWhenNewRecordInstance(OffenderExternalMovements paramBean);

	List<OffenderProgramProfiles> offProgramProfilesExecuteQuery(OffenderProgramProfiles objOffenderProgramProfiles);
		
	VCourseActivities getCourseActivityData(Long crsActId);
	
	ReferenceCodes getReferenceCode(String caseLoadType);
	
	String getNbtProviderName(Long crsActId);


}
