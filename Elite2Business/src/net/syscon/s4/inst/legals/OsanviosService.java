package net.syscon.s4.inst.legals;

import java.util.List;

import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.inst.legals.beans.Court;
import net.syscon.s4.inst.legals.beans.CourtEvnetAppointmentOutcome;
import net.syscon.s4.inst.legals.beans.CourtEvnetAppointmentOutcomeCommitBean;
import net.syscon.s4.inst.legals.beans.OffenderCommunitySentense;
import net.syscon.s4.inst.schedules.bean.CourtEvents;
import net.syscon.s4.inst.schedules.bean.CourtEventsCommitBean;

public interface OsanviosService {
	List<AgencyLocations> hearingreasonRecordGroup();

	List<Court> populateCourtData();

	List<CourtEvents> crtEveExecuteQuery(final CourtEvents objCourtEvents);

	Integer crtEveCommit(final CourtEventsCommitBean commitBean);

	List<OffenderCommunitySentense> retriveSentenceData(OffenderCommunitySentense searchBean);

	List<CourtEvnetAppointmentOutcome> appointMentsData(CourtEvnetAppointmentOutcome searchBean);

	Integer crtEventAppointmentCommit(CourtEvnetAppointmentOutcomeCommitBean commitBean);

	String getDefaultCancellationReason();

	List<CourtEvnetAppointmentOutcome> progOutComeEecuteQuery(CourtEvnetAppointmentOutcome object);

	List<CourtEvnetAppointmentOutcome> progAppointmentEecuteQuery(CourtEvnetAppointmentOutcome searchBean);

	List<CourtEvnetAppointmentOutcome> comServiceEecuteQuery(CourtEvnetAppointmentOutcome searchBean);

	String populateLoggedStaffName(String userName);
}
