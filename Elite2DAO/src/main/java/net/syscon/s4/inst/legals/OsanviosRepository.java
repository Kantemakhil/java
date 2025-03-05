package net.syscon.s4.inst.legals;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.inst.legals.beans.Court;
import net.syscon.s4.inst.legals.beans.CourtEvnetAppointmentOutcome;
import net.syscon.s4.inst.legals.beans.OffenderCommunitySentense;
import net.syscon.s4.inst.schedules.bean.CourtEvents;

public interface OsanviosRepository {
	List<AgencyLocations> hearingreasonRecordGroup();

	List<Court> populateCourtData();
	
	List<CourtEvents> crtEveExecuteQuery(CourtEvents objCourtEvents);
	
	Integer crtEveInsertCourtEvents(List<CourtEvents> lstCourtEvents);

	Integer crtEveUpdateCourtEvents(final List<CourtEvents> lstCourtEvents);
	
	List<OffenderCommunitySentense> retriveSentenceData(OffenderCommunitySentense searchBean);

	List<CourtEvnetAppointmentOutcome> appointMentsData(CourtEvnetAppointmentOutcome searchBean);

	Integer crtEventAppointmentInsertCourtEvents(List<CourtEvnetAppointmentOutcome> insertList);

	Integer crtEventAppointmentUpdateCourtEvents(List<CourtEvnetAppointmentOutcome> updateList);

	CourtEvnetAppointmentOutcome getDatabaseCourtEventAppointmentsData(CourtEvnetAppointmentOutcome inputParamBean);
	
	String getDefaultCancellationReason();
	
	Integer countOutcomereasonCodes(Date eventDate,Integer offenderBookId);

	Integer getLinkedCourtEventData(BigDecimal eventId);
	
	List<CourtEvnetAppointmentOutcome> progOutComeEecuteQuery(CourtEvnetAppointmentOutcome obj);

	List<CourtEvnetAppointmentOutcome> progAppointmentEecuteQuery(CourtEvnetAppointmentOutcome searchBean);

	List<CourtEvnetAppointmentOutcome> comServiceEecuteQuery(CourtEvnetAppointmentOutcome searchBean);

	CourtEvnetAppointmentOutcome getCrtEvntSancDetailsData(CourtEvnetAppointmentOutcome obj);
}
