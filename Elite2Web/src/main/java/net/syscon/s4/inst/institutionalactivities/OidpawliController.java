package net.syscon.s4.inst.institutionalactivities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.im.beans.OffenderProgramProfiles;
import net.syscon.s4.im.beans.OffenderProgramProfilesCommitBean;
import net.syscon.s4.inst.institutionalactivities.beans.VPrisonActivities;

@EliteController
public class OidpawliController {
	@Autowired
	private OidpawliService oidpawliService;
	@Autowired
	private OidpactiService oidpactiService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidpawliController.class.getName());

	/**
	 * getting rgEstablishment LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidpawli/rgEstablishmentRecordGroup", method = RequestMethod.GET)
	public List<AgencyLocations> rgEstablishmentRecordGroup(@RequestParam(value = "ageLocId") final String agyLocId) {
		List<AgencyLocations> recordList = new ArrayList<AgencyLocations>();
		try {
			recordList = oidpawliService.rgEstablishmentRecordGroup(agyLocId);
		} catch (Exception e) {
			final AgencyLocations obj = new AgencyLocations();
			logger.error(this.getClass().getName() + " In method rgEstablishmentRecordGroup :", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgServices LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidpawli/rgServicesRecordGroup", method = RequestMethod.GET)
	public List<VPrisonActivities> rgServicesRecordGroup(@RequestParam(value = "ageLocId") final String ageLocId) {
		List<VPrisonActivities> recordList = new ArrayList<VPrisonActivities>();
		try {
			recordList = oidpawliService.rgServicesRecordGroup(ageLocId);
		} catch (Exception e) {
			final VPrisonActivities obj = new VPrisonActivities();
			logger.error(this.getClass().getName() + "In method rgServicesRecordGroup :", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgPriority LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidpawli/rgPriorityRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgPriorityRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oidpawliService.rgPriorityRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error(this.getClass().getName() + "In method rgPriorityRecordGroup", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgDecision LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidpawli/rgDecisionRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgDecisionRecordGroup(@RequestParam(value = "systemMode") final String systemMode) {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oidpawliService.rgDecisionRecordGroup(systemMode);
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error(this.getClass().getName() + "In method rgDecisionRecordGroup", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgReason LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidpawli/rgReasonRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgReasonRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oidpawliService.rgReasonRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error(this.getClass().getName() + "In method rgReasonRecordGroup", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgActDesc LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidpawli/rgActDescRecordGroup", method = RequestMethod.GET)
	public List<CourseActivities> rgActDescRecordGroup(@RequestParam(value = "input") final String input) {
		List<CourseActivities> recordList = new ArrayList<CourseActivities>();
		try {
			recordList = oidpawliService.rgActDescRecordGroup(input);
		} catch (Exception e) {
			final CourseActivities obj = new CourseActivities();
			logger.error(this.getClass().getName() + "In method rgActDescRecordGroup", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidpawli/waitlistExecuteQuery", method = RequestMethod.POST)
	public List<OffenderProgramProfiles> waitlistExecuteQuery(@RequestBody final OffenderProgramProfiles searchBean) {
		List<OffenderProgramProfiles> searchResult = new ArrayList<>();
		try {
			searchResult = oidpawliService.waitlistExecuteQuery(searchBean);
		} catch (Exception e) {
			final OffenderProgramProfiles bean = new OffenderProgramProfiles();
			logger.error(this.getClass().getName() + "In method waitlistExecuteQuery", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidpawli/futureDays", method = RequestMethod.POST)
	public Long futureDays(@RequestBody final OffenderProgramProfiles searchBean) {
		Long days = null;
		try {
			days = oidpawliService.futureDays(searchBean);
		} catch (Exception e) {
			final OffenderProgramProfiles bean = new OffenderProgramProfiles();
			logger.error(this.getClass().getName() + "In method futureDays", e);
			bean.setErrorMessage(e.getMessage());
		}
		return days;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oidpawli/checkAssignConflict", method = RequestMethod.POST)
	public List<OffenderProgramProfiles> checkAssignConflict(
			@RequestBody final List<OffenderProgramProfiles> searchBean) {
		List<OffenderProgramProfiles> searchResult = new ArrayList<>();
		try {
			if (searchBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				searchBean.get(0).setCreateUserId(userName);
			}
			searchResult = oidpawliService.checkAssignConflict(searchBean);
		} catch (Exception e) {
			final OffenderProgramProfiles bean = new OffenderProgramProfiles();
			logger.error(this.getClass().getName() + "In method checkAssignConflict", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 * getting booking date
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidpawli/getBookingDate", method = RequestMethod.GET)
	public Date getBookingDate(@RequestParam(value = "bookingId") final Integer bookingId) {
		Date bookingDate = null;
		try {
			bookingDate = oidpawliService.getBookingDate(bookingId);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + "In method getBookingDate", e);
		}
		return bookingDate;
	}

	/**
	 * getting booking date
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidpawli/ocdxprogOffPrgrefId", method = RequestMethod.GET)
	public Long ocdxprogOffPrgrefId() {
		Long prgRefId = null;
		try {
			prgRefId = oidpawliService.ocdxprogOffPrgrefId();
		} catch (Exception e) {
			logger.error(this.getClass().getName() + "In method ocdxprogOffPrgrefId", e);
		}
		return prgRefId;
	}

	/**
	 * checking wait list
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidpawli/checkWaitList2", method = RequestMethod.POST)
	public Map<String, Object> checkWaitList2(@RequestBody final OffenderProgramProfiles searchRecord) {
		Map<String, Object> waitList = null;
		try {
			waitList = oidpawliService.checkWaitList2(searchRecord);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + "In method checkWaitList2", e);
		}
		return waitList;
	}

	/**
	 * checking wait list
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidpawli/chkAllocated", method = RequestMethod.POST)
	public Map<String, Object> chkAllocated(@RequestBody final List<OffenderProgramProfiles> searchRecord) {
		Map<String, Object> waitList = null;
		try {
			waitList = oidpawliService.chkAllocated(searchRecord);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + "In method chkAllocated", e);
		}
		return waitList;
	}

	/**
	 * checking wait list
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidpawli/backdateAttendances", method = RequestMethod.POST)
	public Map<String, Object> backdateAttendances(@RequestBody final OffenderProgramProfiles searchRecord) {
		Map<String, Object> waitList = null;
		try {
			waitList = oidpawliService.backdateAttendances(searchRecord);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + "In method backdateAttendances", e);
		}
		return waitList;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oidpawli/waitlistCommits", method = RequestMethod.POST)
	public @ResponseBody List<OffenderProgramProfiles> waitlistCommit(
			@RequestBody final OffenderProgramProfilesCommitBean commitBean) {
		List<OffenderProgramProfiles> liReturn = new ArrayList<>();
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = oidpawliService.waitlistCommit(commitBean);
		} catch (Exception e) {
			final OffenderProgramProfiles error = new OffenderProgramProfiles();
			final String errorMsg = "Error : " + e.getMessage();
			error.setErrorMessage(errorMsg);
			liReturn.add(error);
			logger.error(this.getClass().getName() + "In method waitlistCommits", e);
		}
		return liReturn;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oidpawli/getCourseActivity", method = RequestMethod.POST)
	public @ResponseBody OffenderProgramProfiles getCourseActivity(
			@RequestBody final OffenderProgramProfiles offProfiles) {
		OffenderProgramProfiles liReturn = new OffenderProgramProfiles();
		try {
			liReturn = oidpawliService.getCourseActivity(offProfiles);
		} catch (Exception e) {
			final OffenderProgramProfiles error = new OffenderProgramProfiles();
			final String errorMsg = "Error : " + e.getMessage();
			error.setErrorMessage(errorMsg);
			logger.error(this.getClass().getName() + "In method getCourseActivity", e);
		}
		return liReturn;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidpawli/chkNonAssociation", method = RequestMethod.POST)
	public List<OffenderProgramProfiles> checkNonAssociations(
			@RequestBody final List<OffenderProgramProfiles> searchRecord) {
		List<OffenderProgramProfiles> resultString = new ArrayList<OffenderProgramProfiles>();
		try {

			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();

			resultString = oidpawliService.checkNonAssociations(searchRecord, userName);
		} catch (final Exception e) {
			logger.error(this.getClass().getName() + "Error occured in checkNonAssociations :", e);
		}
		return resultString;
	}
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidpawli/rgEstablishmentRecordGroupOidpacti", method = RequestMethod.GET)
	public List<AgencyLocations> rgEstablishmentRecordGroupOidpacti(
			@RequestParam(value = "caseloadId") final String caseloadId) {
		List<AgencyLocations> recordList = new ArrayList<>();
		try {
			recordList = oidpactiService.rgEstablishmentRecordGroup(caseloadId);
		} catch (Exception e) {
			AgencyLocations obj = new AgencyLocations();
			logger.error("Exception in rgEstablishmentRecordGroupOidpacti:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}
	
	/**
	 * getting rgServices LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidpawli/getServices", method = RequestMethod.GET)
	public List<VPrisonActivities> getServices() {
		List<VPrisonActivities> recordList = new ArrayList<>();
		try {
			recordList = oidpactiService.getServices();
		} catch (Exception e) {
			logger.error("Exception in getServices: Oidpacti:", e);
		}
		return recordList;
	}

}