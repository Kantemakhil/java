package net.syscon.s4.inst.visitsmanagement.maintenance;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.ResponseBody;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.AgencyInternalLocations;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.AgencyVisitDays;
import net.syscon.s4.im.beans.AgencyVisitDaysCommitBean;
import net.syscon.s4.im.beans.AgencyVisitSlots;
import net.syscon.s4.im.beans.AgencyVisitSlotsCommitBean;
import net.syscon.s4.im.beans.AgencyVisitTimes;
import net.syscon.s4.im.beans.AgencyVisitTimesCommitBean;

/**
 * OimvdtslController
 */
@EliteController
public class OimvdtslController {
	@Autowired
	private OimvdtslService oimvdtslService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OimvdtslController.class.getName());

	/**
	 * getting rgAgyVisitDays LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimvdtsl/rgAgyVisitDaysRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgAgyVisitDaysRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<>();
		try {
			recordList = oimvdtslService.rgAgyVisitDaysRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception : Oimvdtsl:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgAgyIntLoc LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimvdtsl/rgAgyIntLocRecordGroup", method = RequestMethod.GET)
	public List<AgencyLocations> rgAgyIntLocRecordGroup() {
		List<AgencyLocations> recordList = new ArrayList<>();
		try {
			String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			recordList = oimvdtslService.rgAgyIntLocRecordGroup(userId);
		} catch (Exception e) {
			final AgencyLocations obj = new AgencyLocations();
			logger.error("Exception : Oimvdtsl:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgAgyVisitSlots LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimvdtsl/rgAgyVisitSlotsRecordGroup", method = RequestMethod.GET)
	public List<AgencyInternalLocations> rgAgyVisitSlotsRecordGroup(@RequestParam("agyLocId") final String agyLocId,@RequestParam String caseLoadId) {
		List<AgencyInternalLocations> recordList = new ArrayList<>();
		try {
			recordList = oimvdtslService.rgAgyVisitSlotsRecordGroup(agyLocId, caseLoadId);
		} catch (Exception e) {
			final AgencyInternalLocations obj = new AgencyInternalLocations();
			logger.error("Exception : Oimvdtsl:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgWeekday LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimvdtsl/rgWeekdayRecordGroup", method = RequestMethod.GET)
	public List<AgencyVisitDays> rgWeekdayRecordGroup() {
		List<AgencyVisitDays> recordList = new ArrayList<>();
		try {
			recordList = oimvdtslService.rgWeekdayRecordGroup();
		} catch (Exception e) {
			final AgencyVisitDays obj = new AgencyVisitDays();
			logger.error("Exception : Oimvdtsl:", e);
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
	@RequestMapping(value = "/oimvdtsl/agyVisitDaysExecuteQuery", method = RequestMethod.POST)
	public List<AgencyVisitDays> agyVisitDaysExecuteQuery(@RequestBody final AgencyVisitDays searchBean) {
		List<AgencyVisitDays> searchResult = new ArrayList<>();
		try {
			searchResult = oimvdtslService.agyVisitDaysExecuteQuery(searchBean);
		} catch (Exception e) {
			final AgencyVisitDays bean = new AgencyVisitDays();
			logger.error("Exception in agyVisitDaysExecuteQuery:oimvdtsl", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oimvdtsl/agyVisitDaysCommit", method = RequestMethod.POST)
	public @ResponseBody List<AgencyVisitDays> agyVisitDaysCommit(
			@RequestBody final AgencyVisitDaysCommitBean commitBean) {
		List<AgencyVisitDays> liReturn = new ArrayList<>();
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = oimvdtslService.agyVisitDaysCommit(commitBean);
		} catch (Exception e) {
			final AgencyVisitDays error = new AgencyVisitDays();
			final String errorMsg = "Error : " + e.getMessage();
			error.setErrorMessage(errorMsg.toUpperCase());
			liReturn.add(error);
			logger.error("Exception  : Oimvdtsl", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimvdtsl/agyVisitTimesExecuteQuery", method = RequestMethod.POST)
	public List<AgencyVisitTimes> agyVisitTimesExecuteQuery(@RequestBody final AgencyVisitTimes searchBean) {
		List<AgencyVisitTimes> searchResult = new ArrayList<>();
		try {
			searchResult = oimvdtslService.agyVisitTimesExecuteQuery(searchBean);
		} catch (Exception e) {
			final AgencyVisitTimes bean = new AgencyVisitTimes();
			logger.error("Exception :oimvdtsl", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oimvdtsl/agyVisitTimesCommit", method = RequestMethod.POST)
	public @ResponseBody List<AgencyVisitTimes> agyVisitTimesCommit(
			@RequestBody final AgencyVisitTimesCommitBean commitBean) {
		List<AgencyVisitTimes> liReturn = new ArrayList<>();
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = oimvdtslService.agyVisitTimesCommit(commitBean);
		} catch (Exception e) {
			final AgencyVisitTimes error = new AgencyVisitTimes();
			final String errorMsg = "Error : " + e.getMessage();
			error.setErrorMessage(errorMsg.toUpperCase());
			liReturn.add(error);
			logger.error("Exception  : Oimvdtsl", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimvdtsl/agyVisitSlotsExecuteQuery", method = RequestMethod.POST)
	public List<AgencyVisitSlots> agyVisitSlotsExecuteQuery(@RequestBody final AgencyVisitSlots searchBean) {
		List<AgencyVisitSlots> searchResult = new ArrayList<>();
		try {
			searchResult = oimvdtslService.agyVisitSlotsExecuteQuery(searchBean);
		} catch (Exception e) {
			final AgencyVisitSlots bean = new AgencyVisitSlots();
			logger.error("Exception :oimvdtsl", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 * Performing basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oimvdtsl/agyVisitSlotsCommit", method = RequestMethod.POST)
	public @ResponseBody List<AgencyVisitSlots> agyVisitSlotsCommit(
			@RequestBody final AgencyVisitSlotsCommitBean commitBean) {
		List<AgencyVisitSlots> liReturn = new ArrayList<>();
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = oimvdtslService.agyVisitSlotsCommit(commitBean);
		} catch (Exception e) {
			final AgencyVisitSlots error = new AgencyVisitSlots();
			final String errorMsg = "Error : " + e.getMessage();
			error.setErrorMessage(errorMsg.toUpperCase());
			liReturn.add(error);
			logger.error("Exception  : Oimvdtsl", e);
		}
		return liReturn;
	}

	/**
	 * getting capacity value from this method
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimvdtsl/agyGetCapaityFrom", method = RequestMethod.POST)
	public AgencyVisitSlots agyGetCapaityFrom(@RequestBody final AgencyVisitSlots searchBean) {
		AgencyVisitSlots searchResult = new AgencyVisitSlots();
		try {
			searchResult = oimvdtslService.agyGetCapaityFrom(searchBean);
		} catch (Exception e) {
			final AgencyVisitSlots bean = new AgencyVisitSlots();
			logger.error("Exception :oimvdtsl", e);
			bean.setErrorMessage(e.getMessage());
		}
		return searchResult;
	}

	/**
	 * to check child records exist or not from this method
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimvdtsl/agyVisitDaysOnCheckDeleteMaster", method = RequestMethod.POST)
	public Integer agyVisitDaysOnCheckDeleteMaster(@RequestBody final AgencyVisitTimes searchBean) {
		Integer searchResult = 0;
		try {
			searchResult = oimvdtslService.agyVisitDaysOnCheckDeleteMaster(searchBean);
		} catch (Exception e) {
			final AgencyVisitSlots bean = new AgencyVisitSlots();
			logger.error("Exception :oimvdtsl", e);
			bean.setErrorMessage(e.getMessage());
		}
		return searchResult;
	}

	/**
	 * to check child records exist or not from this method
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimvdtsl/agyVisitTimesOnCheckDeleteMaster", method = RequestMethod.POST)
	public Integer agyVisitTimesOnCheckDeleteMaster(@RequestBody final AgencyVisitSlots searchBean) {
		Integer searchResult = 0;
		try {
			searchResult = oimvdtslService.agyVisitTimesOnCheckDeleteMaster(searchBean);
		} catch (Exception e) {
			final AgencyVisitSlots bean = new AgencyVisitSlots();
			logger.error("Exception :oimvdtsl", e);
			bean.setErrorMessage(e.getMessage());
		}
		return searchResult;
	}

	/**
	 * to get the location from this method
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimvdtsl/getLocationDescription", method = RequestMethod.POST)
	public AgencyVisitSlots getLocationDescription(@RequestBody final AgencyVisitSlots objSearchDao) {
		AgencyVisitSlots searchResult = new AgencyVisitSlots();
		try {
			searchResult = oimvdtslService.getLocationDescription(objSearchDao);
		} catch (Exception e) {
			final AgencyVisitSlots bean = new AgencyVisitSlots();
			logger.error("Exception :oimvdtsl", e);
			bean.setErrorMessage(e.getMessage());
		}
		return searchResult;
	}

	/**
	 * to validate the check box in times grid
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimvdtsl/agyVisitTimescheckboxChange", method = RequestMethod.POST)
	public String agyVisitTimescheckboxChange(@RequestBody final AgencyVisitTimes searchBean) {
		String searchResult = null;
		try {
			searchResult = oimvdtslService.agyVisitTimescheckboxChange(searchBean);
		} catch (Exception e) {
			final AgencyVisitSlots bean = new AgencyVisitSlots();
			logger.error("Exception :oimvdtsl ", e);
			bean.setErrorMessage(e.getMessage());
		}
		return searchResult;
	}

}