package net.syscon.s4.portalapp;

import java.util.List;

import net.syscon.s4.common.beans.OffenderIdentifier;
import net.syscon.s4.common.beans.VHeaderBlock2;
import net.syscon.s4.inst.legals.beans.KeyDates;
import net.syscon.s4.portalapp.beans.CaseOffense;
import net.syscon.s4.portalapp.beans.CaseOffenseResponse;
import net.syscon.s4.portalapp.beans.CaseScheduleInfo;
import net.syscon.s4.portalapp.beans.CaseSentence;
import net.syscon.s4.portalapp.beans.CourtCaseInfo;
import net.syscon.s4.portalapp.beans.CourtCaseResponse;
import net.syscon.s4.portalapp.beans.CourtScheduleInfo;
import net.syscon.s4.portalapp.beans.CourtScheduleResponse;
import net.syscon.s4.portalapp.beans.OffenderInfo;
import net.syscon.s4.portalapp.beans.RejectOffenderInfo;

public interface PortalAppService {
	
	List<OffenderInfo> getAllNewBookings(String actionCode); 
	List<VHeaderBlock2> getOffendersInfo(List<String> offenderIds, String userName);
	Integer rejectPersonToAdmit(RejectOffenderInfo rejectOffenderInfo);
	Integer acceptPersonToAdmit(OffenderInfo offenderInfo);
	List<VHeaderBlock2> searchMatchedOffedner(OffenderInfo offenderInfo);
	List<OffenderInfo> getAllNonPendingBookings();
	List<CourtScheduleInfo> getAllNewSchedules(String actionCode);
	List<CourtScheduleResponse> createCourtSchedules(List<CourtScheduleInfo> courtScheduleInfo);
	CourtCaseResponse createCourtCase(CourtCaseInfo courtCaseInfo);
	CourtCaseResponse createCourtCaseSequence(CaseScheduleInfo courtCaseInfo);
	CaseOffenseResponse createCourtCaseOffense(CaseOffense caseOffense);
	CourtScheduleResponse insertUpdateSchedules(CourtScheduleInfo courtScheduleInfo);
	List<CourtCaseInfo> getAllPendingLegals();
	List<KeyDates> populateKeyDates(List<String> personIds);
	CourtCaseResponse createCaseSentence(CaseSentence caseSentence) throws Exception;
	OffenderIdentifier createOffenderIdentifier(OffenderIdentifier offenderIdentifier);
}
