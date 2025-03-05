package net.syscon.s4.cm.programsservices;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.common.beans.OffenderCourseAttendance;
import net.syscon.s4.common.beans.ReferenceCodes;

/**
 * Interface OcupatofRepository
 */
public interface OcupatofRepository {
	Integer offCrsAttUpdateOffenderCourseAttendances(final List<OffenderCourseAttendance> lstOffenderCourseAttendances);

	List<ReferenceCodes> rgUnderstandingRecordGroup();

	List<ReferenceCodes> rgAttendancyViewRecordGroup();

	List<ReferenceCodes> rgAttendancesRecordGroup();

	List<OffenderCourseAttendance> offCrsAttExecuteQuery(final OffenderCourseAttendance offCourseAtten);

	List<ReferenceCodes> rgEngagementRecordGroup();

	int updateOffCrseAttend(final OffenderCourseAttendance searchBean);

	OffenderCourseAttendance getOffenderCourseDetails(final OffenderCourseAttendance searchBean);
	 
	String getModule(final OffenderCourseAttendance searchBean);
	
	String getModuleDescription(BigDecimal crsActyId);
	
	String getCatchupFlag(BigDecimal crsSchId);
	
	Integer updateVOffCrseEvents(final OffenderCourseAttendance searchBean);
}
