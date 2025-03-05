package net.syscon.s4.inst.programswithoutschedules;

import java.util.List;

import net.syscon.s4.cm.programsservices.OffenderCourseAttendancesCommitBean;
import net.syscon.s4.cm.programsservices.VOffenderSentCondActs;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.im.beans.Dual;
import net.syscon.s4.im.beans.OffenderPrgObligations;
import net.syscon.s4.im.beans.OffenderPrgObligationsCommitBean;
import net.syscon.s4.im.beans.OffenderProgramProfiles;
import net.syscon.s4.im.beans.OffenderProgramProfilesCommitBean;
import net.syscon.s4.im.beans.VCourseActivities;
import net.syscon.s4.inst.casemanagement.beans.ProgramServices;
import net.syscon.s4.inst.legals.beans.OffenderSentences;

/**
 * Interface OcdxprogService
 */
public interface OcdxprogService {
	List<OffenderProgramProfiles> offProgramProfilesWhenValidateRecord(OffenderProgramProfiles paramBean);

	List<Dual> offPrgObligationsWhenNewRecordInstance(Dual paramBean);

	List<OffenderPrgObligations> offBkgOnCheckDeleteMaster(OffenderPrgObligations paramBean);

	Integer offProgramProfilesCommit(OffenderProgramProfilesCommitBean commitBean);

	List<ProgramServices> rgProgramRecordGroup();

	List<ReferenceCodes> rgEndReasonRecordGroup();

	List<ReferenceCodes> rgAvailabilityCodeRecordGroup();

	String offProgramProfilesPostQuery(VCourseActivities paramBean);

	List<CourseActivities> callOciscata(CourseActivities paramBean);

	List<OffenderPrgObligations> offPrgObligationsExecuteQuery(OffenderPrgObligations objOffenderPrgObligations);

	List<OffenderProgramProfiles> offProgramProfilesExecuteQuery(OffenderProgramProfiles objOffenderProgramProfiles);

	Integer offPrgObligationsCommit(OffenderPrgObligationsCommitBean commitBean);

	List<OffenderProgramProfiles> offPrgObligationsOnCheckDeleteMaster(OffenderProgramProfiles paramBean);

	String currentCaseloadType(String caseloadId);

	Integer offProgramPrflesUpdatePrgStatus(Long offenderPrgObligationId, Long offenderBookId);

	int checkPrivilegeExists();
	
	List<OffenderSentences> rgOffenderSentencesRecordGroup(Integer offenderBookId);

	String checkNonAssociations(OffenderProgramProfilesCommitBean commitBean);
	
	List<OffenderSentences> rgOffenderSentencesRecordGroupBothCustAndNonCust(Integer offenderBookId);
}
