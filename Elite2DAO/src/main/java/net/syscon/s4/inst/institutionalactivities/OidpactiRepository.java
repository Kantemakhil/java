package net.syscon.s4.inst.institutionalactivities;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.im.beans.OffenderProgramProfiles;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.im.beans.ProgramsNonAssocTmp;
import net.syscon.s4.inst.institutionalactivities.beans.VPrisonActivities;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.VOffenderCourseEvents;

/**
 * Interface OidpactiRepository
 */
public interface OidpactiRepository {
	List<OffenderProgramProfiles> offProgProfWhenValidateRecord(OffenderProgramProfiles paramBean);

	String offProgProfInsertOffenderProgramProfiles(List<OffenderProgramProfiles> object);

	List<VOffenderCourseEvents> vOffCourseEvntsExecuteQuery(VOffenderCourseEvents object);

	List<OffenderProgramProfiles> offProgProfExecuteQuery(OffenderProgramProfiles object);

	List<VPrisonActivities> rgServicesRecordGroup(String agyLocId);

	List<OffenderProgramProfiles> offBkgOnCheckDeleteMaster(OffenderProgramProfiles paramBean);

	String offProgProfDeleteOffenderProgramProfiles(List<OffenderProgramProfiles> object);

	List<OffenderProgramProfiles> checkAssignConflictWait(OffenderProgramProfiles paramBean);

	List<ReferenceCodes> rgPerformanceRecordGroup();

	Integer checkDup(OffenderProgramProfiles paramBean);

	List<Object> offProgProfWhenCreateRecord(CourseActivities paramBean);

	List<ReferenceCodes> rgEndReasonRecordGroup();

	List<ReferenceCodes> rgAttendenceRecordGroup(String pshowoutcome);

	List<AgencyLocations> rgEstablishmentRecordGroup(String caseloadId);

	List<VPrisonActivities> lovServices2RecordGroup();

	List<OffenderProgramProfiles> chkActiveIaAllocation(OffenderProgramProfiles paramBean);

	Integer vOffCourseEvntsDeleteVOffenderCourseEvents(List<VOffenderCourseEvents> object);

	List<ReferenceCodes> oidpactiWhenNewFormInstance(ReferenceCodes paramBean);

	String offProgProfUpdateOffenderProgramProfiles(List<OffenderProgramProfiles> object);

	Integer vOffCourseEvntsUpdateVOffenderCourseEvents(List<VOffenderCourseEvents> object);

	List<ReferenceCodes> rgDecisionRecordGroup(String systemMode);

	List<ReferenceCodes> rgPriorityRecordGroup();

	List<ReferenceCodes> pgPsRejRsnRecordGroup();

	List<VPrisonActivities> getServices();

	ProgramsNonAssocTmp checkConflict(OffenderProgramProfiles object);

	List<OffenderProgramProfiles> offenderProgramProfileswaitExecuteQuery(OffenderProgramProfiles object);

	Long getPrgRefId();

	Integer cntAsnCur(OffenderProgramProfiles object);

	Integer deleteVoffenderCourseEvents(OffenderProgramProfiles bean);

	Integer deleteOffenderCourseApptGrps(OffenderProgramProfiles bean);

	Integer deleteOffenderCourseAttendances(OffenderProgramProfiles bean);

	String getProfileValue();

	List<Offenders> getNaPrgSrv(OffenderProgramProfiles bean);

	List<Offenders> getStgNaPrgSrv(OffenderProgramProfiles bean);

	List<Offenders> getOffDetails(OffenderProgramProfiles obj);

	VOffenderCourseEvents getOldValues(final VOffenderCourseEvents obj);

	String insertOffenderProgramProfiles(List<OffenderProgramProfiles> objPrograms);

	Integer gettingOldValueDeleteVoffenderCrseEvents(final OffenderProgramProfiles paramBean);

}
