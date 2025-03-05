package net.syscon.s4.inst.institutionalactivities.maintenance;

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
import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.im.beans.CourseActivitiesCommitBean;
import net.syscon.s4.inst.casemanagement.beans.ProgramServices;
import net.syscon.s4.inst.schedules.bean.IntLocUsageLocations;

/**
 * Class OcmspracController
 */
@EliteController
public class OcmspracController {
	@Autowired
	private OcmspracService ocmspracService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcmspracController.class.getName());

	/**
	 * getting rgAgyLoc LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmsprac/rgAgyLocRecordGroup", method = RequestMethod.GET)
	public List<AgencyLocations> rgAgyLocRecordGroup(@RequestParam(value = "caseloadId") final String caseloadId) {
		List<AgencyLocations> recordList = new ArrayList<>();
		try {
			recordList = ocmspracService.rgAgyLocRecordGroup(caseloadId);
		} catch (Exception e) {
			AgencyLocations obj = new AgencyLocations();
			logger.error("Exception : Ocmsprac:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgPrisonActivity LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmsprac/rgPrisonActivityRecordGroup", method = RequestMethod.GET)
	public List<ProgramServices> rgPrisonActivityRecordGroup() {
		List<ProgramServices> recordList = new ArrayList<>();
		try {
			recordList = ocmspracService.rgPrisonActivityRecordGroup();
		} catch (Exception e) {
			ProgramServices obj = new ProgramServices();
			logger.error("Exception : Ocmsprac:", e);
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgInternalLocation LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmsprac/rgInternalLocationRecordGroup", method = RequestMethod.GET)
	public List<IntLocUsageLocations> rgInternalLocationRecordGroup(
			@RequestParam(value = "agyLocId") final String agyLocId) {
		List<IntLocUsageLocations> recordList = new ArrayList<>();
		try {
			recordList = ocmspracService.rgInternalLocationRecordGroup(agyLocId);
		} catch (Exception e) {
			IntLocUsageLocations obj = new IntLocUsageLocations();
			logger.error("Exception : Ocmsprac:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgIepLevel LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmsprac/rgIepLevelRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgIepLevelRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<>();
		try {
			recordList = ocmspracService.rgIepLevelRecordGroup();
		} catch (Exception e) {
			ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception : Ocmsprac:", e);
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
	@RequestMapping(value = "/ocmsprac/courseActivitiesExecuteQuery", method = RequestMethod.POST)
	public List<CourseActivities> courseActivitiesExecuteQuery(@RequestBody CourseActivities searchBean) {
		List<CourseActivities> searchResult = new ArrayList<>();
		try {
			searchResult = ocmspracService.courseActivitiesExecuteQuery(searchBean);
		} catch (Exception e) {
			CourseActivities bean = new CourseActivities();
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
	@RequestMapping(value = "/ocmsprac/courseActivitiesCommit", method = RequestMethod.POST)
	public @ResponseBody CourseActivities courseActivitiesCommit(@RequestBody CourseActivitiesCommitBean commitBean) {
		CourseActivities liReturn = new CourseActivities();
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = ocmspracService.courseActivitiesCommit(commitBean);
		} catch (Exception e) {

			logger.error("Exception : Ocmsprac", e);
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
	@RequestMapping(value = "/ocmsprac/chkActyEndDate", method = RequestMethod.POST)
	public @ResponseBody String chkActyEndDate(@RequestBody CourseActivities bean) {
		String liReturn = null;
		try {
			liReturn = ocmspracService.chkActyEndDate(bean);
		} catch (Exception e) {
			logger.error("Exception : Ocmsprac", e);
		}
		return liReturn;
	}

}