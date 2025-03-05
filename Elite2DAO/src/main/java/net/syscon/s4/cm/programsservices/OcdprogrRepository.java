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
import net.syscon.s4.im.beans.OffenderNonAssociations;
import net.syscon.s4.im.beans.OffenderPrgObligations;
import net.syscon.s4.im.beans.OffenderProgramProfiles;
import net.syscon.s4.im.beans.VOffenderProgramProfiles;
import net.syscon.s4.inst.casemanagement.beans.ProgramServices;
import net.syscon.s4.inst.legals.beans.OffenderSentences;
import net.syscon.s4.inst.legals.beans.VOffenderSentenceEvents;

/**
 * Interface OcdprogrRepository
 */
public interface OcdprogrRepository {
	List<ProgramServices> rgProgramServicesRecordGroup();

	List<VAcpProgress> vAcpProgressExecuteQuery(VAcpProgress objVAcpProgress);

	List<OffenderSentences> rgOffenderSentencesRecordGroup(Integer offenderBookId);

	List<ReferenceCodes> rgEngagementRecordGroup();

	Integer vOffPrgOblDeleteVOffenderPrgObligations(List<VOffenderPrgObligations> object);

	List<OffenderProgramProfiles> offPgmProfExecuteQuery(OffenderProgramProfiles object);

	List<ProgramServices> rgPhasesRecordGroup(Integer programId);

	List<VOffenderPrgObligations> offBkgOnCheckDeleteMaster(VOffenderPrgObligations paramBean);

	List<ReferenceCodes> rgOffPrgStsRecordGroup();

	List<ReferenceCodes> rgPsEndAllocRecordGroup();

	List<AgencyLocations> rgAgyLocIdRecordGroup(String caseloadId);

	List<OffenderCourseAttendance> offCrsAppExecuteQuery(OffenderCourseAttendance object);

	List<ReferenceCodes> rgUnderstandingRecordGroup();

	List<InternalLocationUsages> rgIntLocationRecordGroup(String agyLocId);

	Integer vOffPrgOblUpdateVOffenderPrgObligations(List<VOffenderPrgObligations> object);

	Integer offCrsAppInsertOffenderCourseAttendances(List<OffenderCourseAttendance> object);

	List<ReferenceCodes> rgFutureAttendanceRecordGroup();

	List<ReferenceCodes> rgOutcomeReasonsRecordGroup(String eventType, String eventSubType);

	List<ReferenceCodes> rgPsPrgAvailRecordGroup();

	Integer offCrsAppUpdateOffenderCourseAttendances(List<OffenderCourseAttendance> object);

	Integer offPgmProfUpdateOffenderProgramProfiles(List<OffenderProgramProfiles> object);

	List<EventMeasures> rgEventSubTypesRecordGroup();

	Integer vAcpProgressUpdateVAcpProgress(List<VAcpProgress> lstVAcpProgress);

	Integer offPgmProfInsertOffenderProgramProfiles(List<OffenderProgramProfiles> object);

	List<VOffenderPrgObligations> vOffPrgOblExecuteQuery(VOffenderPrgObligations object);

	Integer vOffPrgOblInsertVOffenderPrgObligations(List<VOffenderPrgObligations> object);

	List<ProgramServices> rgModulesRecordGroup(Integer phaseId);

	ProgramServices getPrgSrvDetails(OffenderProgramProfiles bean);

	OffenderProgramProfiles getAllocationInfo(OffenderProgramProfiles bean);

	Date getOffenderDates(VHeaderBlock bean);

	List<ReferenceCodes> rgObligationSource();

	Integer checkActiveObligation(List<BigDecimal> offBkgId, List<BigDecimal> programid);

	Integer vAcpProgressPreDelete(List<VOffenderPrgObligations> deleteList);

	OffenderCourseAttendance getprogInfo(OffenderCourseAttendance bean);

	Integer checkScheduleConflict(OffenderCourseAttendance bean);

	Boolean checkUaEventOutcome(OffenderCourseAttendance bean);

	List<VOffenderSentenceEvents> failureCur(OffenderCourseAttendance bean);

	Integer countSentenceUa(VOffenderSentenceEvents beanObj);

	String prgApptEventClass(OffenderCourseAttendance bean);

	Integer adjustUa(OffenderCourseAttendance bean);

	Boolean validAllocationEndDate(OffenderProgramProfiles bean);

	Boolean checkModuleAllocated(OffenderProgramProfiles bean);

	Boolean checkSessionAllocated(OffenderProgramProfiles bean);

	Integer updateModuleAllocations(OffenderProgramProfiles bean);

	Integer createOppAttendances(OffenderProgramProfiles bean);

	Integer updateStatus(OffenderProgramProfiles bean);

	Integer endModuleAllocations(OffenderProgramProfiles bean);

	Integer cancelModuleAllocations(OffenderProgramProfiles bean);

	Integer deleteOppAttendances(OffenderProgramProfiles bean);

	OffenderProgramProfiles getAllocationListSeqRange(OffenderProgramProfiles bean);

	Boolean checkAttendanceTaken(OffenderProgramProfiles bean);

	Boolean checkAttendanceOutcomes(OffenderProgramProfiles bean);

	Boolean checkAllocationExists(OffenderProgramProfiles bean);

	OffenderProgramProfiles getCrsDetails(OffenderProgramProfiles object);

	OffenderProgramProfiles getListSeqRange(OffenderProgramProfiles bean);

	Long getOffPrgrefId();

	Integer getOffPrgrefIdOne(Long offenderPrgObligationId, Long programId);

	List<OffenderNonAssociations> checkNonAssociations(OffenderCourseAttendance bean);

	List<OffenderCourseAttendance> checkNonAssociationAppointments(OffenderCourseAttendance search,
			OffenderNonAssociations offNonAss);

	VHeaderBlock ocdprogrGetOffenderNames(final BigDecimal offBookId,String userName);

	OffenderPrgObligations gettingOldData(BigDecimal offenderPrgObligationId);

	BigDecimal gettingOffenderPrgObligationId();

	OffenderProgramProfiles gettingOffenderProgramStatus(BigDecimal offPrgrefId);

	Long gettingEventId();
	
	Long gettingOffprgrefId();

	List<VOffenderProgramProfiles> vOffPrgProfilesExecuteQuery(VOffenderProgramProfiles objSearchDao);

	List<OffenderNonAssociations> getNonAssociationforInst(BigDecimal offenderBookId);

	List<OffenderCourseAttendance> getNonAssociationsforAppointments(OffenderCourseAttendance attendence);

	String getProgramDescription(BigDecimal offPrgrefId);
	
	Integer updateCourseActivitiesEmail(OffenderProgramProfiles offenderProgramProfiles);

	Integer updateCourseActivitiesSms(OffenderProgramProfiles offenderProgramProfiles);

	Integer updateCourseActivitiesEmailAndSms(OffenderProgramProfiles offenderProgramProfiles);
	
	Date getProgramLastEventDateTime(Long offPrgrefId);

}
