package net.syscon.s4.inst.visitsmanagement;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.AgencyInternalLocations;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.AgencyVisitSlots;
import net.syscon.s4.inst.visitsmanagement.beans.VOffenderAllVisitors;
import net.syscon.s4.inst.visitsmanagement.beans.VOffenderVisitDetails;
import net.syscon.s4.inst.visitsmanagement.beans.VOffenderVisits;
import net.syscon.s4.inst.visitsmanagement.beans.VOffenderVisitsCommitBean;
import net.syscon.s4.sa.recordmaintenance.ProsmainService;

/**
 * Class OiivisitController
 */
@EliteController
public class OiivisitController {
	@Autowired
	private OiivisitService oiivisitService;
	
	@Autowired
	private ProsmainService prosmainService;
	
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OiivisitController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiivisit/offVisExecuteQuery", method = RequestMethod.POST)
	public List<VOffenderVisitDetails> offVisExecuteQuery(@RequestBody final VOffenderVisitDetails searchBean) {
		List<VOffenderVisitDetails> searchResult = new ArrayList<>();
		try {
			searchResult = oiivisitService.offVisExecuteQuery(searchBean);
		} catch (Exception e) {
			final VOffenderVisitDetails bean = new VOffenderVisitDetails();
			logger.error(e);
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiivisit/offImpExecuteQuery", method = RequestMethod.POST)
	public List<VOffenderAllVisitors> offImpExecuteQuery(@RequestBody final VOffenderVisitDetails searchBean) {
		List<VOffenderAllVisitors> searchResult = new ArrayList<>();
		try {
			searchResult = oiivisitService.offImpExecuteQuery(searchBean);
		} catch (Exception e) {
			final VOffenderAllVisitors bean = new VOffenderAllVisitors();
			logger.error(e);
			searchResult.add(bean);
		}
		return searchResult;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiivisit/rgAgencyLocationsRecordGroup", method = RequestMethod.GET)
	public List<AgencyLocations> rgAgencyLocationsRecordGroup(
			@RequestParam(value = "caseloadId") final String caseloadId,
			@RequestParam(value = "caseloadType") final String caseloadType) {
		List<AgencyLocations> recordList = new ArrayList<AgencyLocations>();
		try {
			recordList = oiivisitService.rgAgencyLocationsRecordGroup(caseloadId, caseloadType);
		} catch (Exception e) {
			logger.error(e);
		}
		return recordList;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiivisit/rgAgyVisitDayOfWeekRecordGroup", method = RequestMethod.GET)
	public List<AgencyInternalLocations> rgAgyVisitDayOfWeekRecordGroup(@RequestParam("agyLocId") final String agyLocId) {
		List<AgencyInternalLocations> recordList = new ArrayList<>();
		try {
			recordList = oiivisitService.rgAgyVisitDayOfWeekRecordGroup(agyLocId);
		} catch (Exception e) {
			final AgencyInternalLocations obj = new AgencyInternalLocations();
			logger.error("Exception : Oimvdtsl:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiivisit/rgAgyVisitTimesRecRecordGroup", method = RequestMethod.GET)
	public List<VOffenderVisits> rgAgyVisitTimesRecRecordGroup(@RequestParam("agyLocId") final String agyLocId,@RequestParam("weekDay") final String weekDay) {
		List<VOffenderVisits> recordList = new ArrayList<>();
		try {
			recordList = oiivisitService.rgAgyVisitTimeSlotRecRecordGroup(agyLocId, weekDay);
		} catch (Exception e) {
			final VOffenderVisits obj = new VOffenderVisits();
			logger.error("Exception : Oimvdtsl:", e);
			recordList.add(obj);
		}
		return recordList;
	}
	
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiivisit/rgAgyVisitSlotsRecRecordGroup", method = RequestMethod.GET)
	public List<AgencyVisitSlots> rgAgyVisitSlotsRecRecordGroup(@RequestParam("agyLocId") final String agyLocId,@RequestParam("weekDay") final String weekDay,@RequestParam("timeSlotSeq") final String timeSlotSeq) {
		List<AgencyVisitSlots> recordList = new ArrayList<>();
		try {
			recordList = oiivisitService.rgAgyVisitSlotsRecRecordGroup(agyLocId, weekDay,timeSlotSeq);
		} catch (Exception e) {
			final AgencyVisitSlots obj = new AgencyVisitSlots();
			logger.error("Exception : Oimvdtsl:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiivisit/offenderVisitsSaveForm", method = RequestMethod.POST)
	public @ResponseBody Integer offenderVisitsSaveForm(@RequestBody final VOffenderVisitsCommitBean commitBean, @RequestHeader HttpHeaders headers) {
		Integer liReturn = 0;
		List<String> authorizationList = headers.get("Authorization");
		String authorization = authorizationList.get(0).split(",")[0];
		try {
			if (Optional.ofNullable(commitBean).isPresent())
				commitBean.setCreateUserId(
						SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			liReturn = oiivisitService.offenderVisitsSaveForm(commitBean);
			
			if(1==liReturn) { prosmainService.enableTriggers(commitBean, authorization,
			 "23"); 
			}
			 
		} catch (Exception e) {

			logger.error(this.getClass().getName() + "error in offVstCommit  : ", e);
		}
		return liReturn;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiivisit/rgVisitLocationWithoutDay", method = RequestMethod.GET)
	public List<AgencyVisitSlots> rgVisitLocationWithoutDay(@RequestParam("agyLocId") final String agyLocId) {
		List<AgencyVisitSlots> recordList = new ArrayList<AgencyVisitSlots>();
		try {
			recordList = oiivisitService.rgVisitLocationWithoutDay(agyLocId);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return recordList;
	}

	
}