package net.syscon.s4.inst.institutionalactivities;

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
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.VOffenderCourseAttendances;
import net.syscon.s4.im.beans.VOffenderCourseAttendancesCommitBean;
import net.syscon.s4.im.beans.VPrisonActivities;

/**
 * class OidpaattController
 * 
 */
@EliteController
public class OidpaattController {
	@Autowired
	private OidpaattService oidpaattService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidpaattController.class.getName());

	/**
	 * getting rgPsActPerf LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidpaatt/rgPsActPerfRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgPsActPerfRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oidpaattService.rgPsActPerfRecordGroup();
		} catch (Exception e) {
			ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception :", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgOutcomes LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidpaatt/rgOutcomesRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgOutcomesRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oidpaattService.rgOutcomesRecordGroup();
		} catch (Exception e) {
			ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception :", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgServices LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidpaatt/rgServicesRecordGroup", method = RequestMethod.GET)
	public List<VPrisonActivities> rgServicesRecordGroup(@RequestParam(value = "agyLocId") final String agyLocId) {
		List<VPrisonActivities> recordList = new ArrayList<VPrisonActivities>();
		try {
			recordList = oidpaattService.rgServicesRecordGroup(agyLocId);
		} catch (Exception e) {
			VPrisonActivities obj = new VPrisonActivities();
			logger.error("Exception :", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgAgyLoc LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidpaatt/rgAgyLocRecordGroup", method = RequestMethod.GET)
	public List<AgencyLocations> rgAgyLocRecordGroup(@RequestParam(value = "caseloadId") final String caseloadId) {
		List<AgencyLocations> recordList = new ArrayList<AgencyLocations>();
		try {
			recordList = oidpaattService.rgAgyLocRecordGroup(caseloadId);
		} catch (Exception e) {
			AgencyLocations obj = new AgencyLocations();
			logger.error("Exception :", e);
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
	@RequestMapping(value = "/oidpaatt/vActAttExecuteQuery", method = RequestMethod.POST)
	public List<VOffenderCourseAttendances> vActAttExecuteQuery(@RequestBody VOffenderCourseAttendances searchBean) {
		List<VOffenderCourseAttendances> searchResult = new ArrayList<>();
		try {
			searchResult = oidpaattService.vActAttExecuteQuery(searchBean);
		} catch (Exception e) {
			VOffenderCourseAttendances bean = new VOffenderCourseAttendances();
			logger.error("Exception :", e);
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
	@RequestMapping(value = "/oidpaatt/vActAttCommit", method = RequestMethod.POST)
	public @ResponseBody Integer vActAttCommit(@RequestBody VOffenderCourseAttendancesCommitBean commitBean) {
		int liReturn = 0;
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			commitBean.setCreateUserId(userName);
			liReturn = oidpaattService.vActAttCommit(commitBean);
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
	@RequestMapping(value = "/oidpaatt/defaultAttendanceData", method = RequestMethod.GET)
	public VOffenderCourseAttendances defaultAttendanceData() {
		VOffenderCourseAttendances searchResult = new VOffenderCourseAttendances();
		try {
			searchResult = oidpaattService.defaultAttendanceData();
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return searchResult;
	}

}