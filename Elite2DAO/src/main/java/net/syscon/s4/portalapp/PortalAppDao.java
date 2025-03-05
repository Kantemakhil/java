package net.syscon.s4.portalapp;

import java.util.List;

import net.syscon.s4.common.beans.VHeaderBlock2;
import net.syscon.s4.portalapp.beans.CaseOffenseResponse;
import net.syscon.s4.portalapp.beans.CaseSentenceResponse;
import net.syscon.s4.portalapp.beans.CourtScheduleResponse;
import net.syscon.s4.portalapp.beans.OffenderInfo;
import net.syscon.s4.portalapp.beans.RejectOffenderInfo;

public interface PortalAppDao {
	List<OffenderInfo> getAllNewBookings(String actionCode);
	
	List<OffenderInfo> getAllNonPendingBookings();
	
	Long getNumberOfExactMatches(OffenderInfo offenderInfo);
	
	Long getNumberOfMaybeMatches(OffenderInfo offenderInfo);
	
	List<VHeaderBlock2> getNumberOfExactMatchesOffeneder(OffenderInfo offenderInfo);
	
	List<VHeaderBlock2> getNumberOfMaybeMatchesOffeneder(OffenderInfo offenderInfo);
	
	List<VHeaderBlock2> offenderBookingAvailable(List<String> offenderDisplayIds);
	
	Integer rejectPersonToAdmit(RejectOffenderInfo rejectOffenderInfo);
	
	Integer acceptPersonToAdmit(OffenderInfo offenderInfo);
	
	Integer updateScheduleOutputPayload(CourtScheduleResponse courtScheduleResponse);
	
	Integer updateOffenseOutputPayload(CaseOffenseResponse courtScheduleResponse);
	
	List<OffenderInfo> getFailedLegals();
	
	Integer updateSentenceOutputPayload(CaseSentenceResponse courtScheduleResponse);
	
	Long getRootOfenderId(Long offenderId);
}
