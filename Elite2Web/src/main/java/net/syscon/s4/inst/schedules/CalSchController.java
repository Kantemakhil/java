package net.syscon.s4.inst.schedules;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import net.syscon.s4.cm.casemanagement.maintenance.beans.EventMeasures;
import net.syscon.s4.cm.community_supervision_tiers.OcmtidetService;
import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.ScheduleSeries;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.community_supervision_tiers.MaintainTierDefaultEvents;
import net.syscon.s4.community_supervision_tiers.MaintainTierLevelsCommitBean;
import net.syscon.s4.globaloffenderrecords.OcunawrnService;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.inst.casemanagement.beans.InternalScheduleReasons;
import net.syscon.s4.inst.casemanagement.beans.StaffMembersV2;
import net.syscon.s4.inst.institutionalactivities.maintenance.OcmschrcController;
import net.syscon.s4.inst.legals.OcdcCasesService;
import net.syscon.s4.inst.legals.OidcrtevService;
import net.syscon.s4.inst.legals.beans.Court;
import net.syscon.s4.inst.schedules.bean.CalScheduleBean;
import net.syscon.s4.inst.schedules.bean.CourtEvents;
import net.syscon.s4.inst.schedules.bean.IntLocUsageLocations;
import net.syscon.s4.inst.schedules.bean.OffenderIndSchedules;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedulesCommitBean;
import net.syscon.s4.iwp.OcdclogsService;
import net.syscon.s4.iwp.OcuaoffiService;

@EliteController
public class CalSchController {
	@Autowired
	private CalSchService calSchService;
	@Autowired
	private RecurringScheduleService recurrSchService;
	
	@Autowired
	private OcdclogsService ocdclogsService;
	
	@Autowired
	private OcuaoffiService ocuaoffiService;
	
	@Autowired
	private OcunawrnService ocunawrnService;
	
	@Autowired
	private OcdcCasesService ocdcCasesService;
	
	@Autowired
	private OidcrtevService oidcrtevService;
	
	@Autowired 
	private OcmtidetService ocmtidetService;
	
	@Autowired
	private OidsiappService oidsiappService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcmschrcController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/calsch/getEventData", method = RequestMethod.POST)
	public List<CalScheduleBean> getEventData(@RequestBody CalScheduleBean obj) {
		List<CalScheduleBean> searchResult = new ArrayList<>();
		try {
			searchResult = calSchService.getEventData(obj);
		} catch (Exception e) {
			logger.error("In getEventData method : ", e);

		}
		return searchResult;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/calsch/calculateSchedules", method = RequestMethod.POST)
	public List<Date> calculateSchedules(@RequestBody ScheduleSeries obj) {
		List<Date> searchResult = new ArrayList<>();
		try {
			searchResult = recurrSchService.calculateSchedules(obj);
		} catch (Exception e) {
			logger.error("In calculateSchedules method : ", e);

		}
		return searchResult;
	}
	
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/calsch/getScheduleConflicts", method = RequestMethod.POST)
	public List<VOffenderAllSchedules> getScheduleConflicts(@RequestBody List<OffenderIndSchedules> obj) {
		List<VOffenderAllSchedules> searchResult = new ArrayList<>();
		try {
			searchResult = recurrSchService.getScheduleConflicts(obj);
		} catch (Exception e) {
			logger.error("In getScheduleConflicts method : ", e);

		}
		return searchResult;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/calsch/saveRecurrSchedule", method = RequestMethod.POST)
	public Integer recurrRuleCommit(@RequestBody VOffenderAllSchedulesCommitBean commitBean) {
		Integer returnVal = null;
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			commitBean.setCreateUserId(userName);
			returnVal = recurrSchService.recurrRuleCommit(commitBean);
		} catch (Exception e) {
			logger.error("In recurrRuleCommit method : ", e);

		}
		return returnVal;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/calsch/getScheduleSeries", method = RequestMethod.POST)
	public ScheduleSeries getScheduleSeries(@RequestBody ScheduleSeries searchBean) {
		ScheduleSeries searchResult = new ScheduleSeries();
		try {
			searchResult = recurrSchService.getScheduleSeries(searchBean);
		} catch (Exception e) {
			logger.error("In getScheduleSeries method : ", e);

		}
		return searchResult;
	}
	
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/calsch/commitScheduledEventDetails", method = RequestMethod.POST)
	public Integer commitScheduledEventDetails(@RequestBody MaintainTierLevelsCommitBean commitBean) {
		Integer count = 0;
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			commitBean.setCreateUserId(userName);
			count = recurrSchService.commitScheduledEventDetails(commitBean);
		} catch (Exception e) {
			logger.error(this.getClass() + "In commitScheduledEventDetails method : ", e);
		}
		return count;
	}
	
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/calsch/courtEventsSave", method = RequestMethod.POST)
	public Integer courtEventsSave(@RequestBody CourtEvents courtEvents) {
		Integer count = 0;
		try {
			courtEvents.setCreateUserId(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			count = calSchService.courtEventsSave(courtEvents);
		} catch (Exception e) {
			logger.error(this.getClass() + "In courtEventsSave method : ", e);
		}
		return count;
	}
	
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/calsch/checkScreenAccess", method = RequestMethod.GET)
	public Boolean checkScreenAccess(@RequestParam("userId") String userId) {
		Boolean result = null;
		try {

			result = calSchService.checkScreenAccess(userId);
		} catch (Exception e) {
			logger.error(this.getClass() + "In courtEventsSave method : ", e);
		}
		return result;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/calsch/updateViewClickData", method = RequestMethod.POST)
	public List<CalScheduleBean> updateViewClickData(@RequestBody CalScheduleBean obj) {
		List<CalScheduleBean> searchResult = new ArrayList<>();
		try {
			searchResult = calSchService.updateViewClickData(obj);
		} catch (Exception e) {
			logger.error("In getEventData method : ", e);

		}
		return searchResult;
	}
	
	@RequestMapping(value = "/calsch/rgScheduleTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgScheduleTypeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocdclogsService.rgScheduleTypeRecordGroup();
		} catch (Exception e) {
			logger.error("rgScheduleTypeRecordGroup :", e);
		}
		return recordList;
	}
	
	@RequestMapping(value = "/calsch/rgScheduleSubTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgScheduleSubTypeRecordGroup(
			@RequestParam(value = "eventType") final String eventType) {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocdclogsService.rgScheduleSubTypeRecordGroup(eventType);
		} catch (Exception e) {
			logger.error("rgScheduleSubTypeRecordGroup :", e);
		}
		return recordList;
	}
	
	@RequestMapping(value = "/calsch/rgLocationRecordGroup", method = RequestMethod.GET)
	public List<AgencyLocations> rgLocationRecordGroup() {
		List<AgencyLocations> recordList = new ArrayList<AgencyLocations>();
		try {
			recordList = ocdclogsService.rgLocationRecordGroup();
		} catch (Exception e) {
			logger.error("rgLocationRecordGroup :", e);
		}
		return recordList;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/calsch/getEmailSmsFlag", method = RequestMethod.POST)
	public List<EventMeasures> getEmailSmsFlag(@RequestBody final VOffenderAllSchedules beanOne) {
		List<EventMeasures> returnList=new ArrayList<>();
		try {
			returnList = ocdclogsService.getEmailSmsFlag(beanOne);
		} catch (Exception e) {
			logger.error("getEmailSmsFlag :", e);
			return returnList;
		}
		return returnList;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/calsch/addStaffExecuteQuery", method = RequestMethod.POST) //din't find
	public List<StaffMembersV2> addStaffExecuteQuery(@RequestBody final StaffMembersV2 searchBean) {
		List<StaffMembersV2> searchResult = new ArrayList<>();
		try {
			searchResult = ocuaoffiService.addStaffExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("Exception in addStaffExecuteQuery Ocuaoffi:", e);

		}
		return searchResult;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/calsch/checkNonAssociationConflicts", method = RequestMethod.POST)
	public VOffenderAllSchedulesCommitBean cgfkOffNaDspOffenderIdDiRecordGroup(@RequestBody VOffenderAllSchedulesCommitBean commitBean) {		
		try {
			commitBean = ocunawrnService.checkNonAssociationConflicts(commitBean);
		} catch (final Exception e) {
			logger.error("Exception in " + this.getClass().getName() + " checkNonAssociationConflicts", e);
		}
		return commitBean;
	}
	
	@RequestMapping(value = "/calsch/populateCourtData", method = RequestMethod.GET)
	public List<Court> populateCourtData() {
		List<Court> courtList = new ArrayList<Court>();
		try {			
			courtList = ocdcCasesService.populateCourtData();
		} catch (Exception e) {
			logger.error("populateCourtData", e);
		}
		return courtList;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/calsch/hearingreasonRecordGroup", method = RequestMethod.GET)
	public List<AgencyLocations> hearingreasonRecordGroup() {
		logger.info(this.getClass().getName() + " hearingreasonRecordGroup start");
		List<AgencyLocations> recordList = new ArrayList<>();
		try {
			recordList = oidcrtevService.hearingreasonRecordGroup();
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in hearingreason", e);
		}
		return recordList;
	}
	
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/calsch/apperancelocationRecordGroup", method = RequestMethod.GET)
	public List<AgencyLocations> apperancelocationRecordGroup(@RequestParam("caseLoadId") String agyLocId) {
		logger.info(this.getClass().getName() + " apperancelocationRecordGroup start " + agyLocId != null ? agyLocId
				: null);
		List<AgencyLocations> recordList = new ArrayList<>();
		try {
			recordList = oidcrtevService.apperancelocationRecordGroup(agyLocId);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in apperancelocationRecordGroup", e);
		}
		return recordList;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/calsch/getActiveTierEvent", method = RequestMethod.GET)
	public ReferenceCodes getActiveTierEvent(@RequestParam("offenederBookId") Long offnderBookId) {
		ReferenceCodes bean = new ReferenceCodes();
		try {
			bean = ocmtidetService.getActiveTierEvent(offnderBookId);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + "getActiveTierEvent", e);
		}
		return bean;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/calsch/tierdefaultEventsExecuteQuery", method = RequestMethod.POST)
	public List<MaintainTierDefaultEvents> tierdefaultEventsExecuteQuery(
			@RequestBody final MaintainTierDefaultEvents searchBean) {
		List<MaintainTierDefaultEvents> searchResult = new ArrayList<>();
		try {
			searchResult = ocmtidetService.tierdefaultEventsExecuteQuery(searchBean);
		} catch (Exception e) {
			final MaintainTierDefaultEvents bean = new MaintainTierDefaultEvents();
			searchResult.add(bean);
			logger.error(this.getClass().getName() + " In method offAssExecuteQuery", e);
		}
		return searchResult;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/calsch/rgInternalMoveLocationsRecordGroup", method = RequestMethod.GET)
	public List<IntLocUsageLocations> rgInternalMoveLocationsRecordGroup(
			@RequestParam(value = "agyLocId") final String agyLocId) {
		List<IntLocUsageLocations> recordList = new ArrayList<IntLocUsageLocations>();
		try {
			recordList = oidsiappService.rgInternalMoveLocationsRecordGroup(agyLocId);
		} catch (Exception e) {
			final IntLocUsageLocations obj = new IntLocUsageLocations();
			logger.error("Error in Class " + "In rgInternalMoveLocationsRecordGroup method : ", e);
			obj.setErrorMessage(e.getMessage());
		}
		return recordList;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/calsch/rgSchInternalScheduleRecordGroup", method = RequestMethod.GET)
	public List<InternalScheduleReasons> rgSchInternalScheduleRecordGroup() {
		List<InternalScheduleReasons> recordList = new ArrayList<InternalScheduleReasons>();
		try {
			recordList = oidsiappService.rgSchInternalScheduleRecordGroup();
		} catch (Exception e) {
			final InternalScheduleReasons obj = new InternalScheduleReasons();
			logger.error("Error in Class " + "In rgSchInternalScheduleRecordGroup method : ", e);
			obj.setErrorMessage(e.getMessage());
		}
		return recordList;
	}
	
}
