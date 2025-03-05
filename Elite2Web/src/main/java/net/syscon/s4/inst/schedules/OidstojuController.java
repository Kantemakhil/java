package net.syscon.s4.inst.schedules;

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
import org.springframework.web.bind.annotation.ResponseBody;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedulesCommitBean;
import net.syscon.s4.sa.recordmaintenance.ProsmainService;

/**
 * class OidstojuController
 */
@EliteController
public class OidstojuController {
	@Autowired
	private OidstojuService oidstojuService;
	@Autowired
	private ProsmainService prosmainService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidstojuController.class.getName());

	/**
	 * getting rgLocation LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidstoju/rgLocationRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgLocationRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		final ReferenceCodes bean = new ReferenceCodes();
		try {
			recordList = oidstojuService.rgLocationRecordGroup();
		} catch (Exception e) {
			logger.error("In rgLocationRecordGroup method : ", e);
			bean.setErrorMessage(e.getMessage());
		}
		return recordList;
	}

	/**
	 * getting rgEscort LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidstoju/rgEscortRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgEscortRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		final ReferenceCodes bean = new ReferenceCodes();
		try {
			recordList = oidstojuService.rgEscortRecordGroup();
		} catch (Exception e) {
			logger.error("In rgEscortRecordGroup method : ", e);
			bean.setErrorMessage(e.getMessage());
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @param searchBean {@link VOffenderAllSchedules}
	 * @return a list of the VOffenderAllSchedules {@link VOffenderAllSchedules} for
	 *         the matched VOffenderAllSchedules
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidstoju/offSchExecuteQuery", method = RequestMethod.POST)
	public List<VOffenderAllSchedules> offSchExecuteQuery(@RequestBody final VOffenderAllSchedules searchBean) {
		List<VOffenderAllSchedules> searchResult = new ArrayList<>();
		try {
			searchResult = oidstojuService.offSchExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("In offSchExecuteQuery method : ", e);
		}
		return searchResult;
	}

	/**
	 * Performing basic Oracle form functions i.e. insert,delete, update into the
	 * database table
	 * 
	 * @param commitBean {@link VOffenderAllSchedulesCommitBean}
	 * @return the VOffenderAllSchedulesCommitBean
	 *         {@link VOffenderAllSchedulesCommitBean} for the matched
	 *         VOffenderAllSchedulesCommitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oidstoju/offSchCommit", method = RequestMethod.POST)
	public @ResponseBody VOffenderAllSchedules offSchCommit(
			@RequestBody final VOffenderAllSchedulesCommitBean commitBean, @RequestHeader HttpHeaders headers) {
		VOffenderAllSchedules liReturn = new VOffenderAllSchedules();
		List<String> authorizationList = headers.get("Authorization");
		String authorization = authorizationList.get(0).split(",")[0];
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = oidstojuService.offSchCommit(commitBean);
			if(liReturn != null) {
				prosmainService.enableTriggers(commitBean, authorization, "8");
			}
		} catch (Exception e) {
			logger.error("In offSchCommit method : ", e);
		}
		return liReturn;
	}

	/**
	 * getting getCurrentDate LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidstoju/getCurrentDate", method = RequestMethod.GET)
	public Date getCurrentDate() {
		Date value = null;
		try {
			value = oidstojuService.getCurrentDate();
		} catch (Exception e) {
			logger.error(" In getCurrentDate method : ", e);
		}
		return value;
	}

	/**
	 * Before inserting the record it verifying whether any other schedules are
	 * assigned to the offender
	 *
	 * @param offSch {@link VOffenderAllSchedules}
	 * @return the VOffenderAllSchedules {@link VOffenderAllSchedules} for the
	 *         matched VOffenderAllSchedules
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidstoju/offSchCheckScheduleConflict", method = RequestMethod.POST)
	public @ResponseBody VOffenderAllSchedules offSchCheckScheduleConflict(
			@RequestBody final VOffenderAllSchedulesCommitBean commitBean) {
		VOffenderAllSchedules vSchedules = new VOffenderAllSchedules();
		try {
			vSchedules = oidstojuService.offSchCheckScheduleConflict(commitBean);
		} catch (Exception e) {
			logger.error("In offSchCheckScheduleConflict method : ", e);
		}
		return vSchedules;
	}

	/**
	 * getting rgEscort LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidstoju/rgEventTypeSubTypeGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgEventTypeSubTypeGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		final ReferenceCodes bean = new ReferenceCodes();
		try {
			recordList = oidstojuService.rgEventTypeSubTypeGroup();
		} catch (Exception e) {
			logger.error("In rgEventTypeSubTypeGroup method : ", e);
			bean.setErrorMessage(e.getMessage());
		}
		return recordList;
	}

	/**
	 * Before inserting the record it verifying whether any other schedules are
	 * assigned to the offender
	 *
	 * @param offSch {@link VOffenderAllSchedules}
	 * @return success/failure of the schedule conflict before save as Integer
	 *         {@link Integer} for the matching passed data
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidstoju/offSchCheckScheduleConflictBeforeSave", method = RequestMethod.POST)
	public @ResponseBody Integer offSchCheckScheduleConflictBeforeSave(
			@RequestBody final VOffenderAllSchedules offSch) {
		Integer liReturn = 0;
		try {
			liReturn = oidstojuService.offSchCheckScheduleConflictBeforeSave(offSch);
		} catch (Exception e) {
			logger.error("In offSchCheckScheduleConflictBeforeSave method : ", e);
		}
		return liReturn;
	}
}