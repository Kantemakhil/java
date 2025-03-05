package net.syscon.s4.pkgs.tag_programmes;

import java.math.BigDecimal;
import java.util.Date;

import com.sun.org.apache.xpath.internal.operations.Bool;

import net.syscon.s4.cm.programsservices.VAcpSchedules;
import net.syscon.s4.cm.programsservices.VOffenderPrgObligations;
import net.syscon.s4.common.beans.OffenderCourseAttendance;
import net.syscon.s4.im.beans.OffenderPrgObligationHty;
import net.syscon.s4.im.beans.OffenderProgramProfiles;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.CourseSchedules;

public interface TagProgrammesService {

	void cancelAttendForSchedule(final Long crsSchId, final String userName);

	void updateCourseAttendanceDates(final VAcpSchedules vacp, final String userName);

	Integer updateStatus(final OffenderPrgObligationHty searchBean);

	void insertOffenderCourseAttendances(final OffenderCourseAttendance bean);

	void createOffWRReturnSchedule(final Integer eventId);

	Integer lockOffPrgObligationId(final Long pOffenderPrgObligationId, final Long pCheckSum);

	void allocateCourseToOffender(final VOffenderPrgObligations vOff, final Integer serTabCount, final String userName);

	Long createAllocation(final Long pCrsActyId, final Long pOffenderBookId, final Long pOffenderPrgObligationId,
			final Long pParentOffPrgrefId, final Long pSessionNo, final Date pScheduleDate, final String userName);

	Long getPrgProfile(final Long pOffenderPrgObligationId, final Long lvProgramId);

	void updateStatus(final Long pOffenderPrgObligationId, final String userName);

	void createOppAttendances(final Long lvOffPrgrefId, final String userName);

	void adjustAllocationDates(final Long pCrsActyId, final String pCourseClass, final Date pScheduleStartDate,
			final Date pScheduleEndDate, final Long pListSeq, final Long pParentCrsActyId, final String userName);

	Date getAllocationEndDate(final Long pOffPrgrefId);

	OffenderCourseAttendance getProgramInfo(final Long offPrgrefId);

	BigDecimal insCourseSchedulesCatchup(final OffenderCourseAttendance courseSchedules);

	Date getOffenderDates(final Long offenderBookId);

	CourseSchedules getCourseScheduleRec(final Long pCrsSchId);

	Long getOffCourseAttendChecksum(final Long eventId);

	Integer updateObligationWr(Long offenderBookId,String user);

	Integer insOffCrsAttCatchup(OffenderCourseAttendance courseSchedules);

	OffenderProgramProfiles getListSeqRange(OffenderProgramProfiles offPrg);

	void createProgramProfile(Long programId, Long offenderPrgObligationId, Long offenderBookId,String userName);
	
	Boolean checkAllocationExists(OffenderProgramProfiles offPrg);
	
	Boolean checkAttendanceOutcomes(Long offPrgrefId);
	
	Boolean checkAttendanceTaken(Long offPrgrefId, Date endDate);
	
	Integer cancelModuleAllocations(OffenderProgramProfiles offPrg);
	
	Integer endModuleAllocations(OffenderProgramProfiles offPrg);
	
	OffenderProgramProfiles getAllocationListSeqRange(OffenderProgramProfiles offPrg);
	
	Boolean checkSessionAllocated(OffenderProgramProfiles offPrg);
	
	Boolean checkModuleAllocated(OffenderProgramProfiles offPrg);
	
	Boolean validAllocationEndDate(OffenderProgramProfiles offPrg);
	
	Integer deleteOppAttendances(OffenderProgramProfiles offPrg);
	
	Integer updateModuleAllocations(OffenderProgramProfiles offPrg);
}