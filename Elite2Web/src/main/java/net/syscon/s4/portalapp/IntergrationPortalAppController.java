package net.syscon.s4.portalapp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import net.syscon.s4.common.EliteController;
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

@EliteController
public class IntergrationPortalAppController {
	
	private static Logger logger = LogManager.getLogger(IntergrationPortalAppController.class.getName());
	
	@Autowired
	private PortalAppService portalAppService;
	
	@RequestMapping(value = "/portalapp/allbookings", method = RequestMethod.GET)
	public List<OffenderInfo> getAllNewBookings() {
		return portalAppService.getAllNewBookings("BOOKING");
	}
	
	@RequestMapping(value = "/portalapp/allSchedulebookings", method = RequestMethod.GET)
	public List<CourtScheduleInfo> getAllNewScheduleBookings() {
		return portalAppService.getAllNewSchedules("SCHEDULE");
	}
	
	@RequestMapping(value = "/portalapp/nonPendingBookings", method = RequestMethod.GET)
	public List<OffenderInfo> getAllNonPendingBookings() {
		return portalAppService.getAllNonPendingBookings();
	}
	
	@RequestMapping(value = "/portalapp/failedLegals", method = RequestMethod.GET)
	public List<OffenderInfo> getAllFailedLegals() {
		return portalAppService.getAllNonPendingBookings();
	}
	
	
	@RequestMapping(value = "/portalapp/offenderInfo", method = RequestMethod.GET)
	public List<VHeaderBlock2> getOffendersInfo(@RequestParam(value = "offenderId") final String offenderIds) {
		String[] personIdArray = {offenderIds};
		if(offenderIds.contains(",")){
			personIdArray = offenderIds.split(",");
		} 
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		return portalAppService.getOffendersInfo(Arrays.asList(personIdArray),userName);
	}
	
	@RequestMapping(value = "/portalapp/rejectPerson", method = RequestMethod.POST)
	public Integer rejectPerosnToAdmit(@RequestBody RejectOffenderInfo rejectOffenderInfo) {
		return portalAppService.rejectPersonToAdmit(rejectOffenderInfo);
	}
	
	@RequestMapping(value = "/portalapp/updatePersonStatus", method = RequestMethod.POST)
	public Integer bookPerson(@RequestBody OffenderInfo offenderInfo) {
		return portalAppService.acceptPersonToAdmit(offenderInfo);
	}
	
	@RequestMapping(value = "/portalapp/searchMatches", method = RequestMethod.POST)
	public List<VHeaderBlock2> searchMatches(@RequestBody OffenderInfo offenderInfo) {
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		offenderInfo.setCreateUserId(userName);
		return portalAppService.searchMatchedOffedner(offenderInfo);
	}
	
	@RequestMapping(value = "/portalapp/scheduleOffender", method = RequestMethod.POST)
	public List<CourtScheduleResponse> bookPerson(@RequestBody List<CourtScheduleInfo> courtSchedule) {
		return portalAppService.createCourtSchedules(courtSchedule);
	}
	
	@RequestMapping(value = "/portalapp/schedulePerson", method = RequestMethod.POST)
	public CourtScheduleResponse bookPerson(@RequestBody CourtScheduleInfo courtSchedule) {
		List<CourtScheduleInfo> courtSchedules = new ArrayList<CourtScheduleInfo>();
		courtSchedules.add(courtSchedule);
		return portalAppService.createCourtSchedules(courtSchedules).get(0);
	}
	
	@RequestMapping(value = "/portalapp/autoSchedule", method = RequestMethod.POST)
	public CourtScheduleResponse autoSchedulePerson(@RequestBody CourtScheduleInfo courtSchedule) {
		
		if(courtSchedule.getCaseId()==null) {
			List<CourtScheduleInfo> courtSchedules = new ArrayList<CourtScheduleInfo>();
			courtSchedules.add(courtSchedule);
			return portalAppService.createCourtSchedules(courtSchedules).get(0);
		} else {
			return portalAppService.insertUpdateSchedules(courtSchedule);
		}
		
	}
	
	@RequestMapping(value = "/portalapp/updateCourtCases", method = RequestMethod.POST)
	public CourtCaseResponse updateCourtCase(@RequestBody CourtCaseInfo courtCaseInfo) {
		return portalAppService.createCourtCase(courtCaseInfo);
	}
	
	@RequestMapping(value = "/portalapp/courtCases", method = RequestMethod.POST)
	public CourtCaseResponse createCourtCase(@RequestBody CourtCaseInfo courtCaseInfo) {
		return portalAppService.createCourtCase(courtCaseInfo);
	}
	
	@RequestMapping(value = "/portalapp/courtCasesSchedule", method = RequestMethod.POST)
	public CourtCaseResponse createCourtCaseSchedule(@RequestBody CaseScheduleInfo courtCaseInfo) {
		return portalAppService.createCourtCaseSequence(courtCaseInfo);
	}
	
	@RequestMapping(value = "/portalapp/courtCasesOffense", method = RequestMethod.POST)
	public CaseOffenseResponse createCourtCaseOffense(@RequestBody CaseOffense courtCaseInfo) {
		return portalAppService.createCourtCaseOffense(courtCaseInfo);
	}
	
	@RequestMapping(value = "/portalapp/keydates", method = RequestMethod.GET)
	public List<KeyDates> createCourtCaseOffense(@RequestParam String personId) {
		return portalAppService.populateKeyDates(Arrays.asList(personId));
	}
	
	@RequestMapping(value = "/portalapp/caseSentence", method = RequestMethod.POST)
	public CourtCaseResponse createCaseSentence(@RequestBody CaseSentence caseSentence) throws Exception {
		return portalAppService.createCaseSentence(caseSentence);
	}
	
	@RequestMapping(value = "/portalapp/createIdentifiers", method = RequestMethod.POST)
	public OffenderIdentifier createCaseSentence(@RequestBody OffenderIdentifier offenderIdentifier) throws Exception {
		return portalAppService.createOffenderIdentifier(offenderIdentifier);
	}
	
}
