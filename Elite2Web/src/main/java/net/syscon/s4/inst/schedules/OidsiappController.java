package net.syscon.s4.inst.schedules;

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
import net.syscon.s4.inst.casemanagement.beans.InternalScheduleReasons;
import net.syscon.s4.inst.schedules.bean.IntLocUsageLocations;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedulesCommitBean;

/**
 * class OidsiappController
 */
@EliteController
public class OidsiappController {
	@Autowired
	private OidsiappService oidsiappService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidsiappController.class.getName());

	/**
	 * getting rgInternalMoveLocations LOV values
	 * 
	 * @param agyLocId {@link String}
	 * @return a list of the IntLocUsageLocations {@link IntLocUsageLocations} for
	 *         the matched agency location Id
	 *
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidsiapp/rgInternalMoveLocationsRecordGroup", method = RequestMethod.GET)
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

	/**
	 * getting rgSchInternalSchedule LOV values
	 * 
	 * @return a list of the InternalScheduleReasons {@link InternalScheduleReasons}
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidsiapp/rgSchInternalScheduleRecordGroup", method = RequestMethod.GET)
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

	/**
	 * Fetching the record from database table
	 * 
	 * @param searchBean {@link VOffenderAllSchedules}
	 * @return a list of the VOffenderAllSchedules {@link VOffenderAllSchedules} for
	 *         the matched VOffenderAllSchedules
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidsiapp/offSchExecuteQuery", method = RequestMethod.POST)
	public List<VOffenderAllSchedules> offSchExecuteQuery(@RequestBody final VOffenderAllSchedules searchBean) {
		List<VOffenderAllSchedules> searchResult = new ArrayList<>();
		try {
			searchResult = oidsiappService.offSchExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("Error in Class " + "In offSchExecuteQuery method : ", e);
		}
		return searchResult;
	}

	/**
	 * Performing basic Oracle form functions i.e. insert,delete, update into the
	 * database table
	 * 
	 * @param commitBean {@link VOffenderAllSchedulesCommitBean}
	 * @return success/failure of the insert/update {@link Integer} for the matching
	 *         passed data
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oidsiapp/offSchCommit", method = RequestMethod.POST)
	public @ResponseBody Integer offSchCommit(@RequestBody final VOffenderAllSchedulesCommitBean commitBean) {
		int liReturn = 0;
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = oidsiappService.offSchCommit(commitBean);
		} catch (Exception e) {
			logger.error("Error in Class " + "In offSchCommit method : ", e);
		}
		return liReturn;
	}

	/**
	 * Before inserting the record it verifying whether any other schedules are
	 * assigned to the offender
	 *
	 * @param vOffAllSch {@link VOffenderAllSchedules}
	 * @return success/failure of the insert/update {@link Integer} for the matching
	 *         passed data
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidsiapp/checkScheduleConflict", method = RequestMethod.POST)
	public @ResponseBody Integer checkScheduleConflict(@RequestBody final VOffenderAllSchedules vOffAllSch) {
		int liReturn = 0;
		try {
			liReturn = oidsiappService.checkScheduleConflict(vOffAllSch);
		} catch (Exception e) {
			logger.error("Error in Class " + "In checkScheduleConflict method : ", e);
		}
		return liReturn;
	}

	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oidsiapp/checkNonAssociations", method = RequestMethod.POST)
	public String checkNonAssociations(@RequestBody final VOffenderAllSchedulesCommitBean commitBean) {
		String liReturn = "";
		try {
			liReturn = oidsiappService.checkNonAssociations(commitBean);
		} catch (Exception e) {
			logger.error("Error in Class " + "In checkNonAssociations method : ", e);
		}
		return liReturn;
	}
	
	

}