package net.syscon.s4.cm.intakeclosure;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.OffenderBookingEvent;
import net.syscon.s4.common.beans.OffenderBookingEventCommitBean;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.OffenderResidence;
import net.syscon.s4.common.beans.OffenderResidenceCommitBean;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.common.beans.VHeaderBlock2;
import net.syscon.s4.globaloffenderrecords.OsiosearService;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.im.beans.SystemProfiles;
import net.syscon.s4.sa.recordmaintenance.ProsmainService;

/**
 * OcdintakController
 */
@EliteController
public class OcdintakController {
	@Autowired
	private OcdintakService ocdintakService;
	
	@Autowired
	private ProsmainService prosmainService;
	
	@Autowired
	private OsiosearService osiosearService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcdintakController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdintak/offBkgExecuteQuery", method = RequestMethod.POST)
	public List<VHeaderBlock2> offBkgExecuteQuery(@RequestBody final VHeaderBlock2 searchBean) {
		List<VHeaderBlock2> searchResult = new ArrayList<VHeaderBlock2>();
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			searchBean.setCreateUserId(userName);
			searchResult = ocdintakService.offBkgExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return searchResult;
	}

	/**
	 * getting rgToAgyLoc LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdintak/rgToAgyLocRecordGroup", method = RequestMethod.GET)
	public List<AgencyLocations> rgToAgyLocRecordGroup(@RequestParam(value = "caseloadId") final String caseloadId,
			@RequestParam(value = "offenderId") final Long offenderId) {
		List<AgencyLocations> recordList = new ArrayList<AgencyLocations>();
		try {
			recordList = ocdintakService.rgToAgyLocRecordGroup(caseloadId, offenderId);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return recordList;
	}

	/**
	 * getting rgIntakeType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdintak/rgIntakeTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgIntakeTypeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocdintakService.rgIntakeTypeRecordGroup();
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return recordList;
	}

	/**
	 * getting rgIntakeRsn LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdintak/rgIntakeRsnRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgIntakeRsnRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocdintakService.rgIntakeRsnRecordGroup();
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return recordList;
	}

	/**
	 * getting rgFromAgyLoc LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdintak/rgFromAgyLocRecordGroup", method = RequestMethod.GET)
	public List<AgencyLocations> rgFromAgyLocRecordGroup() {
		List<AgencyLocations> recordList = new ArrayList<AgencyLocations>();
		try {
			recordList = ocdintakService.rgFromAgyLocRecordGroup();
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdintak/offBkgsExecuteQuery", method = RequestMethod.POST)
	public List<OffenderBookings> offBkgsExecuteQuery(@RequestBody final OffenderBookings searchBean) {
		List<OffenderBookings> searchResult = new ArrayList<OffenderBookings>();
		try {
			searchResult = ocdintakService.offBkgsExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return searchResult;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdintak/offBkgeExecuteQuery", method = RequestMethod.POST)
	public List<OffenderBookingEvent> offBkgeExecuteQuery(@RequestBody final OffenderBookingEvent searchBean) {
		List<OffenderBookingEvent> searchResult = new ArrayList<OffenderBookingEvent>();
		try {
			searchResult = ocdintakService.offBkgeExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return searchResult;
	}

	/**
	 * method: offBkgeCommit
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocdintak/offBkgeCommit", method = RequestMethod.POST)
	public Integer offBkgeCommit(@RequestBody final OffenderBookingEventCommitBean commitBean, @RequestHeader HttpHeaders headers) {
		int liReturn = 0;
        		List<String> authorizationList = headers.get("Authorization");
		String authorization = authorizationList.get(0).split(",")[0];
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = ocdintakService.offBkgeCommit(commitBean);
			if(1==liReturn) {
				commitBean.getInsertList().get(0).setModuleName("OCDINTAK");
				commitBean.getInsertList().get(0).setIntCorrelationId(osiosearService.getCorrelationId());
				prosmainService.enableTriggers(commitBean, authorization, "6");
			}
		} catch (Exception e) {

			logger.error("Exception :", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdintak/reportInExecuteQuery", method = RequestMethod.POST)
	public List<OffenderResidence> reportInExecuteQuery(@RequestBody final OffenderResidence searchBean) {
		List<OffenderResidence> searchResult = new ArrayList<OffenderResidence>();
		try {
			searchResult = ocdintakService.reportInExecuteQuery(searchBean);
		} catch (Exception e) {
			OffenderResidence bean = new OffenderResidence();
			logger.error("Exception :", e);
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 * method:reportInCommit
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocdintak/reportInCommit", method = RequestMethod.POST)
	public Integer reportInCommit(@RequestBody final OffenderResidenceCommitBean commitBean) {
		int liReturn = 0;
		try {
			liReturn = ocdintakService.reportInCommit(commitBean);
		} catch (Exception e) {

			logger.error("Exception :", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdintak/sysPflExecuteQuery", method = RequestMethod.POST)
	public List<SystemProfiles> sysPflExecuteQuery(@RequestBody final SystemProfiles searchBean) {
		List<SystemProfiles> searchResult = new ArrayList<SystemProfiles>();
		try {
			searchResult = ocdintakService.sysPflExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return searchResult;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdintak/checkPrevBooking", method = RequestMethod.POST)
	public Date checkPrevBooking(@RequestBody final OffenderBookings searchBean) {
		Date date = null;
		try {
			date = ocdintakService.checkPrevBooking(searchBean);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return date;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdintak/wNewBlockInstanceintakeCase", method = RequestMethod.GET)
	public String wNewBlockInstanceintakeCase() {
		String string = null;
		try {
			string = ocdintakService.wNewBlockInstanceintakeCase();
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return string;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdintak/checkForActiveBooking", method = RequestMethod.POST)
	public Integer checkForActiveBooking(@RequestBody final VHeaderBlock obj) {
		Integer returnVal = 0;
		try {
			returnVal = ocdintakService.checkForActiveBooking(obj);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return returnVal;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdintak/setNewcontactFlag", method = RequestMethod.POST)
	public OffenderBookingEvent setNewcontactFlag(@RequestBody final OffenderBookings searchBean) {
		OffenderBookingEvent searchResult = new OffenderBookingEvent();
		try {
			searchResult = ocdintakService.setNewContactFlag(searchBean);
		} catch (Exception e) {
			searchResult = new OffenderBookingEvent();
			logger.error("Exception :", e);
		}
		return searchResult;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdintak/getDefaultIntakeValues", method = RequestMethod.GET)
	public OffenderBookingEvent getDefaultIntakeValues(@RequestParam(value = "intakeType") final String intakeType,
			@RequestParam(value = "intakeReason") final String intakeReason) {
		OffenderBookingEvent searchResult = new OffenderBookingEvent();
		try {
			searchResult = ocdintakService.getDefaultIntakeValues(intakeType, intakeReason);
		} catch (Exception e) {
			searchResult = new OffenderBookingEvent();
			logger.error("Exception :", e);
		}
		return searchResult;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdintak/getTrustValues", method = RequestMethod.GET)
	public String getTrustValues(@RequestParam(value = "client") final String client,
			@RequestParam(value = "intakeTrust") final String intakeTrust) {
		String returnObj = null;
		try {
			returnObj = ocdintakService.getTrustValues(client, intakeTrust);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return returnObj;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdintak/intakeCaseMultiple", method = RequestMethod.POST)
	public Integer intakeCaseMultiple(@RequestBody final VHeaderBlock searchBean) {
		Integer returnVal = 0;
		try {
			returnVal = ocdintakService.intakeCaseMultiple(searchBean);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return returnVal;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdintak/getDspDescription", method = RequestMethod.GET)
	public String getDspDescription() {
		String returnVal = null;
		try {
			returnVal = ocdintakService.getDspDescription();
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return returnVal;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdintak/intakeCaseactBkgExistFlag", method = RequestMethod.POST)
	public String intakeCaseactBkgExistFlag(@RequestBody final VHeaderBlock searchBean) {
		String returnVal = null;
		try {
			returnVal = ocdintakService.intakeCaseactBkgExistFlag(searchBean);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return returnVal;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdintak/toAgyLoc", method = RequestMethod.POST)
	public String toAgyLoc(@RequestBody final OffenderBookingEvent searchBean) {
		String returnVal = null;
		try {
			returnVal = ocdintakService.toAgyLoc(searchBean);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return returnVal;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdintak/getProfileValueDisabled", method = RequestMethod.GET)
	public String getProfileValueDisabled() {
		String string = null;
		try {
			string = ocdintakService.getProfileValueDisabled();
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return string;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdintak/getProfileTrustValueDisabled", method = RequestMethod.GET)
	public String getProfileTrustValueDisabled() {
		String string = null;
		try {
			string = ocdintakService.getProfileTrustValueDisabled();
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return string;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdintak/getBackdatedAdmissionDate", method = RequestMethod.GET)
	public Date getBackdatedAdmissionDate(){
		Date returnVal = null;
		try {
			returnVal = ocdintakService.getBackdatedAdmissionDate();
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return returnVal;
	}

}