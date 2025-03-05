package net.syscon.s4.pkgs.tag_programmes;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import net.syscon.s4.cm.programsservices.VAcpSchedules;
import net.syscon.s4.common.beans.OffenderCourseAttendance;
import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.im.beans.OffenderPrgObligationHty;
import net.syscon.s4.im.beans.OffenderPrgObligations;
import net.syscon.s4.im.beans.OffenderProgramProfiles;
import net.syscon.s4.inst.casemanagement.beans.ProgramServices;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.CourseSchedules;
import net.syscon.s4.triggers.OffenderCourseAttendances;

public interface TagProgrammesRepository {

	List<OffenderCourseAttendance> selectCrsSchId(final Long crsSchId);

	Integer updateOffCourseAttendance(final Long crsSchId, final String userName);

	List<OffenderCourseAttendance> ocaCur(final Long pCrsSchId);

	Integer updateOffenderCourseAttendance(final BigDecimal pCrsSchId, final VAcpSchedules vacp, final String userName);

	String getXFromOffPrgObligations(final Long pOffenderPrgObligationId);

	Integer updateStatusUpdateOffPrgObli(final String pStatus, final String pReason, final Date pDate,
			final Long pOffenderPrgObligationId, final String userName);
	
	List<OffenderPrgObligationHty> getOldRecordForUpdateStatus(final Integer offenderPrgObligationId);

	
	public Integer insertOffenderCourseAttendance(final OffenderCourseAttendance bean);

	public OffenderCourseAttendance selectOffCourseAtt(final OffenderCourseAttendance bean);

	void createOffWRReturnSchedule(final Integer eventId);

	List<ProgramServices> getPgmCur(final Long offPrgrefId);

	List<ProgramServices> getPhaseCur(final Long parentOffPrgrefId);

	public List<OffenderPrgObligations> getOffPrgOblDetails(final Long pOffenderPrgObligationId);

	Long getPrgProfile(final Long pOffenderPrgObligationId, final Long lvProgramId);

	String updateStatusSelect(final Long pOffenderPrgObligationId);

	void updateStatusUpOperation(final Long pOffenderPrgObligationId, final String userName);

	Long getRetOffPrgRefId();

	void insertOffProgramProfiles(final Long retOffPrgRefId, final Long pOffenderBookId, final Long lvProgramId,
			final Long pCrsActyId, final Long pOffenderPrgObligationId, final Long pParentOffPrgrefId,
			final Long lvProgramOffPrgrefId, final Date lvScheduleStartDate, final Long lvStartSessionNo,
			final Date lvScheduleEndDate, final String userName);

	void insertCreateOppAttendances(final Long lvOffPrgrefId, final String userName);

	List<CourseActivities> modCur(final Long pPhaseInstanceId);

	List<OffenderProgramProfiles> allocCur(final Long crsActyId);

	Date endCur(final Long pOffPrgrefId, final Date lvBigDate);

	Integer updateOffenderProgramProfiles(final Long pOffPrgrefId, final Date lvOffenderStartDate,
			final Date lvOffenderEndDate, final String userName);

	BigDecimal getCrsSchId();

	void insertCourseSchedules(final OffenderCourseAttendance courseSchedules);

	Date getOffenderDates(final Long offenderBooId);

	CourseSchedules getCourseScheduleRec(final Long pCrsSchId);

	Long getOffCourseAttendChecksum(final Long eventId);

	List<OffenderPrgObligations> offPrgProfCur(Long offenderBookId);

	Integer updateOffenderProgPros(Long offenderBookId, Long offenPrgObligaId, OffenderPrgObligations refObject);

	Integer updateOffenderPrgOblig(Long offenderBookId);

	Integer insertOffenderCourseAttendancesForCatchup(OffenderCourseAttendance offCouAttds);

	OffenderProgramProfiles getListSeqRange(OffenderProgramProfiles offPrg);

	List<ProgramServices> pgmCurList(Long programId);

	void insertOffenderProgramProfiles(OffenderProgramProfiles offPrg);
	
	Integer checkAllocationExists(OffenderProgramProfiles offPrg);

	Integer checkAttendanceOutcomes(Long offPrgrefId);

	Integer checkAttendanceTaken(Long offPrgrefId, Date endDate);

	void updateCancelModuleAllocations(OffenderProgramProfiles offPrg);

	void updateEndModuleAllocations(OffenderProgramProfiles offPrg);

	OffenderProgramProfiles getAllocationListSeqRange(OffenderProgramProfiles offPrg);

	Integer checkSessionAllocated(OffenderProgramProfiles offPrg);

	Integer checkModuleAllocated(OffenderProgramProfiles offPrg);

	Integer validAllocationEndDate(OffenderProgramProfiles offPrg);

	List<OffenderCourseAttendances> attCur(OffenderProgramProfiles offPrg);

	void deleteOffenderCourseAttendances(OffenderCourseAttendances offCou);

	List<CourseActivities> crsCur(Long crsActyId);

	void offenderCourseAttendancesDelete(Long offenderPrgObligationId, Long crsActyId, Long parentOffPrgrefId,String modifyUserId);

	void offenderProgramProfilesDelete(Long offenderPrgObligationId, Long crsActyId, Long parentOffPrgrefId,String modifyUserId);
	
	List<OffenderPrgObligations> getOldRecOffenderPrgObligations(final BigDecimal offenderPrgObligationId);
}
