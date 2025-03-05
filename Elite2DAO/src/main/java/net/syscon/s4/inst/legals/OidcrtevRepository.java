package net.syscon.s4.inst.legals;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import net.syscon.s4.common.beans.AgyIntLocProfiles;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.OffenderNaDetails;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.inst.legals.beans.Court;
import net.syscon.s4.inst.schedules.bean.CourtEvents;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules;

public interface OidcrtevRepository {

	List<CourtEvents> crtEveExecuteQuery(CourtEvents objCourtEvents);

	List<Court> populateCourtData();

	Integer checkScheduleConflict(CourtEvents objCourts);

	Integer crtEveInsertCourtEvents(List<CourtEvents> lstCourtEvents);

	Integer crtEveUpdateCourtEvents(final List<CourtEvents> lstCourtEvents);

	List<AgencyLocations> hearingreasonRecordGroup();

	List<AgencyLocations> apperancelocationRecordGroup(String agyLocId);

	List<CourtEvents>  getOffenderCourtData(BigDecimal nsOffenderBookId, CourtEvents insert);

	List<CourtEvents> getOffenderCourtDataByOMEORVID(BigDecimal nsOffenderBookId, String appearanceLocation, Date eventDate);

	List<AgyIntLocProfiles> getNonAssociationType(String appearanceLocation);
	
	List<Offenders> getIndividualNonassocationDetailsByOMEORVID(CourtEvents courtevents);
	
	String getDefaultCancellationReason();
	
	Boolean checkScreenAccess(String userId);
	
	Object[] phoneNumberAndEmailCheck(BigDecimal offenderBookId);

	List<AgencyLocations> getApperancelocationRecordGroup(String agyLocId);
	
}
