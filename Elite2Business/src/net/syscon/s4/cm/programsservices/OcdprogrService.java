package net.syscon.s4.cm.programsservices;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import net.syscon.s4.cm.casemanagement.maintenance.beans.EventMeasures;
import net.syscon.s4.common.beans.OffenderCourseAttendance;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.InternalLocationUsages;
import net.syscon.s4.im.beans.OffenderProgramProfiles;
import net.syscon.s4.im.beans.OffenderProgramProfilesCommitBean;
import net.syscon.s4.inst.casemanagement.beans.ProgramServices;
import net.syscon.s4.inst.legals.beans.OffenderSentences;

/**
 * Interface OcdprogrService
 */
public interface OcdprogrService {
	List<AgencyLocations> rgAgyLocIdRecordGroup(String caseloadId);

	List<OffenderCourseAttendance> offCrsAppExecuteQuery(OffenderCourseAttendance object);

	List<ReferenceCodes> rgUnderstandingRecordGroup();

	Integer vAcpProgressCommit(VAcpProgressCommitBean commitBean);

	List<InternalLocationUsages> rgIntLocationRecordGroup(String agyLocId);

	OffenderProgramProfiles offPgmProfCommit(OffenderProgramProfilesCommitBean commitBean);

	Integer offCrsAppCommit(OffenderCourseAttendancesCommitBean commitBean);

	List<ProgramServices> rgProgramServicesRecordGroup();

	List<ReferenceCodes> rgFutureAttendanceRecordGroup();

	List<VAcpProgress> vAcpProgressExecuteQuery(VAcpProgress objVAcpProgress);

	List<OffenderSentences> rgOffenderSentencesRecordGroup(Integer offenderBookId);

	List<ReferenceCodes> rgEngagementRecordGroup();

	List<VOffenderPrgObligations> offBkgOnCheckDeleteMaster(VOffenderPrgObligations paramBean);

	void vOffPrgOblPreDelete();

	Integer vOffPrgOblCommit(VOffenderPrgObligationsCommitBean commitBean);

	List<ReferenceCodes> rgOutcomeReasonsRecordGroup(String eventOutcome);

	List<ReferenceCodes> rgPsPrgAvailRecordGroup();

	List<OffenderProgramProfiles> offPgmProfExecuteQuery(OffenderProgramProfiles object);

	List<ProgramServices> rgPhasesRecordGroup(Integer ProgramId);

	List<ReferenceCodes> rgOffPrgStsRecordGroup();

	List<EventMeasures> rgEventSubTypesRecordGroup();

	List<VOffenderPrgObligations> vOffPrgOblExecuteQuery(VOffenderPrgObligations object);

	List<ProgramServices> rgModulesRecordGroup(Integer phaseId);

	List<ReferenceCodes> rgPsEndAllocRecordGroup();

	Date getOffenderDates(BigDecimal offenderBookId);

	List<ReferenceCodes> rgObligationSource();

	Integer checkScheduleConflict(OffenderCourseAttendance bean);

	OffenderCourseAttendance checkUa(OffenderCourseAttendance bean);

	Boolean validAllocationEndDate(OffenderProgramProfiles bean);

	Boolean checkAttendanceOutcomes(OffenderProgramProfiles bean);

	OffenderProgramProfiles checkAllocationExists(OffenderProgramProfiles bean);
	
	String checkNonAssociations(final OffenderCourseAttendancesCommitBean commitBean);
	
	String checkInstNonAssociation(final OffenderProgramProfilesCommitBean commitBean);
	
	String checkNonAssociationsforInstWhileAssigning(OffenderProgramProfilesCommitBean commitBean);

	String checkNonAssociationsWhileScheduling(OffenderCourseAttendancesCommitBean commitBean);
	
	Integer updateoffPgmProfCommit(OffenderProgramProfilesCommitBean commitBean);

}
