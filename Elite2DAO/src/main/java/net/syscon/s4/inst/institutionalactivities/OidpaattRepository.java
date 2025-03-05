package net.syscon.s4.inst.institutionalactivities;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.VOffenderCourseAttendances;
import net.syscon.s4.im.beans.VPrisonActivities;

/**
 * Interface OidpaattRepository
 */
public interface OidpaattRepository {
	List<ReferenceCodes> rgOutcomesRecordGroup();

	List<ReferenceCodes> rgPsActPerfRecordGroup();

	List<AgencyLocations> rgAgyLocRecordGroup(final String caseloadId);

	List<AgencyLocations> defaultAgency(AgencyLocations paramBean);

	List<VPrisonActivities> rgServicesRecordGroup(final String agyLocId);

	Integer vActAttUpdateVOffenderCourseAttendances(List<VOffenderCourseAttendances> lstVOffenderCourseAttendances);

	List<VOffenderCourseAttendances> vActAttExecuteQuery(VOffenderCourseAttendances objVOffenderCourseAttendances);

	String getDefAttOutcome();

	String getDefAttPerformance();

	Integer insertCrsAttendance(List<VOffenderCourseAttendances> updateList);

}
