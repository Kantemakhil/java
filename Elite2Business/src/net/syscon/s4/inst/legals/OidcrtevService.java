package net.syscon.s4.inst.legals;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.inst.legals.beans.Court;
import net.syscon.s4.inst.schedules.bean.CourtEvents;
import net.syscon.s4.inst.schedules.bean.CourtEventsCommitBean;

public interface OidcrtevService {

	List<CourtEvents> crtEveExecuteQuery(final CourtEvents objCourtEvents);

	List<Court> populateCourtData();

	Integer crtEveCommit(final CourtEventsCommitBean commitBean);

	List<AgencyLocations> hearingreasonRecordGroup();

	List<AgencyLocations> apperancelocationRecordGroup(String agyLocId);

	String checkNonAssociations(CourtEventsCommitBean commitBean);
	 
	String checkNonAssociationsDataForINP(List<CourtEvents> courtEventsList, String userId);
	
	String getDefaultCancellationReason();
	
	Integer courtEventsSave(CourtEvents courtEvents);
	
	CourtEvents phoneNumberAndEmailCheck(BigDecimal offenderBookId);
}
