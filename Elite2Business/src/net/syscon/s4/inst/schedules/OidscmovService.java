package net.syscon.s4.inst.schedules;

import java.util.Date;
import java.util.List;

import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.im.beans.MovementReasons;
import net.syscon.s4.inst.schedules.bean.CourtEvents;
import net.syscon.s4.inst.schedules.bean.CourtEventsCommitBean;

/**
 * Interface OidscmovService
 */
public interface OidscmovService {
	List<AgencyLocations> rgCtrlInstRecordGroup(String caseloadId);

	List<CourtEvents> crtEveExecuteQuery(final CourtEvents objCourtEvents);

	List<MovementReasons> rgCourtReaRecordGroup();

	Object crtEvePreInsert();

	List<MovementReasons> rgCtrlReasonRecordGroup();

	Integer crtEveCommit(final CourtEventsCommitBean commitBean);

	List<AgencyLocations> rgCtrlCourtRecordGroup();

	Date getCurrentDate();

	Integer checkScheduleConflict(CourtEvents objCourts);

	List<CourtEvents> getOffenderDetails(String nbtOffDisplayId, String agyLocId, String caseloadId);

	Boolean getChkNaConflictFlag(Integer offenderBookId, String agyLocId, Date eventDate);

	List<CourseActivities> getNonAssociationWarnings(List<CourseActivities> courtEvents);
	
	CourtEventsCommitBean getNonAssociationWarningsForINPOrVIDOrOME(CourtEventsCommitBean commitBean);
	
}
