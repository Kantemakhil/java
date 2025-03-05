package net.syscon.s4.global;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.CaseloadAgencyLocations;
import net.syscon.s4.common.beans.Caseloads;
import net.syscon.s4.common.beans.EliteViewLog;
import net.syscon.s4.common.beans.HelpMedia;
import net.syscon.s4.common.beans.MenuSecurities;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.UserRoleInfo;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.common.beans.WorkFlowFolders;
import net.syscon.s4.common.beans.WorkflowScreens;
import net.syscon.s4.core.EliteDateService;
import net.syscon.s4.globalconfiguration.OumacaseService;

/**
 * Class Omss40Controller
 */
@EliteController
public class Omss40Controller {
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(Omss40Controller.class.getName());

	@Autowired
	private Omss40Service omss40Service;
	
	@Autowired
	private OumacaseService oumacaseService;

	@Autowired
	private EliteDateService eliteDateService;

	/**
	 * returning SystemProfiles table values
	 *
	 * @param paramBean {@link SystemProfiles}
	 * @return a list of the SystemProfiles {@link SystemProfiles} for the matched SystemProfiles
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/omss40/preFormPtypeClient", method = RequestMethod.POST)
	public @ResponseBody List<SystemProfiles> preFormPtypeClient(@RequestBody final SystemProfiles paramBean) {
		List<SystemProfiles> dataObj = new ArrayList<SystemProfiles>();
		try {
			dataObj = omss40Service.preFormPtypeClient(paramBean);
		} catch (Exception e) {
			logger.error("", e);
		}
		return dataObj;
	}

	/**
	 * returning SystemProfiles table values
	 *
	 * @param paramBean {@link SystemProfiles}
	 * @return a list of the SystemProfiles {@link SystemProfiles} for the matched SystemProfiles

	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/omss40/preFormPtypeSys", method = RequestMethod.POST)
	public @ResponseBody List<SystemProfiles> preFormPtypeSys(@RequestBody final SystemProfiles paramBean) {
		List<SystemProfiles> dataObj = new ArrayList<SystemProfiles>();
		try {
			dataObj = omss40Service.preFormPtypeSys(paramBean);
		} catch (Exception e) {
			logger.error("", e);
		}
		return dataObj;
	}

	/**
	 * returning MenuSecurities table values
	 *
	 * @param paramBean {@link MenuSecurities}
	 * @return a list of the MenuSecurities {@link MenuSecurities} for the matched MenuSecurities
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/omss40/whenNewFormInstanceMenuNamecur", method = RequestMethod.GET)
	public @ResponseBody List<MenuSecurities> whenNewFormInstanceMenuNamecur(
			@RequestBody final MenuSecurities paramBean) {
		List<MenuSecurities> dataObj = new ArrayList<MenuSecurities>();
		try {
			dataObj = omss40Service.whenNewFormInstanceMenuNameCur(paramBean);
		} catch (Exception e) {
			logger.error("", e);

		}
		return dataObj;
	}

	/**
	 * returning WorkFlowFolders table values
	 *
	 * @param paramBean {@link WorkflowScreens}
	 * @return a list of the WorkFlowFolders {@link WorkFlowFolders} for the matched WorkflowScreens
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/omss40/buildworkFlowmenurgmainWorkflowCur", method = RequestMethod.GET)
	public @ResponseBody List<WorkFlowFolders> buildworkFlowmenurgmainWorkflowCur(
			@RequestBody final WorkflowScreens paramBean) {
		List<WorkFlowFolders> listOfRecords = new ArrayList<WorkFlowFolders>();
		try {
			listOfRecords = omss40Service.buildworkFlowmenurgmainWorkflowCur(paramBean);
		} catch (Exception e) {
			logger.error("", e);
		}
		return listOfRecords;
	}

	/**
	 * returning WorkflowScreens table values
	 *
	 * @param paramBean
	 * @return WorkflowScreens
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/omss40/buildworkFlowmenurgsubWorkflowCur", method = RequestMethod.GET)
	public @ResponseBody WorkflowScreens buildworkFlowmenurgsubWorkflowCur(
			@RequestBody final WorkflowScreens paramBean) {
		WorkflowScreens listOfRecords = null;
		try {
			listOfRecords = omss40Service.buildworkFlowmenurgsubWorkflowCur(paramBean);
		} catch (Exception e) {
			logger.error("", e);

		}
		return listOfRecords;
	}

	/**
	 * returning CaseLoads table values
	 *
	 * @return a list of the CurrentCaseloads {@link String}
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/omss40/getCurrentCaseload", method = RequestMethod.GET)
	public @ResponseBody String getCurrentCaseload() {
		String dataObj = null;
		try {
			final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			dataObj = omss40Service.getCurrentCaseload(authentication.getName());
		} catch (Exception e) {
			logger.error("", e);
		}
		return dataObj;
	}

	/**
	 * returning CaseLoads table values
	 *
	 * @return a list of the StaffMembers {@link StaffMembers}
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/omss40/getCurrentStaffDetail", method = RequestMethod.GET)
	public @ResponseBody StaffMembers getCurrentStaffDetail() {
		StaffMembers dataObj = null;
		try {
			final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			dataObj = omss40Service.getCurrentStaffDetail(authentication.getName());
		} catch (Exception e) {
			logger.error("", e);
		}
		return dataObj;
	}

	/**
	 * returning CaseLoads table values
	 *
	 * @return the current case load type
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/omss40/getCurrentCaseloadType", method = RequestMethod.GET)
	public @ResponseBody String getCurrentCaseloadType() {
		String dataObj = null;
		try {
			String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			dataObj = omss40Service.getCurrentCaseloadType(userId);
		} catch (Exception e) {
			logger.error("", e);
		}
		return dataObj;
	}

	/**
	 * returning MenuSecurities table values
	 *
	 * @param paramBean {@link MenuSecurities}
	 * @return a list of the matching MenuSecurities {@link MenuSecurities}
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/omss40/mainpopList", method = RequestMethod.POST)
	public @ResponseBody List<MenuSecurities> mainpopList(@RequestBody final MenuSecurities paramBean) {
		List<MenuSecurities> dataObj = new ArrayList<MenuSecurities>();
		try {
			dataObj = omss40Service.mainpopList(paramBean);
		} catch (Exception e) {
			logger.error("", e);
		}
		return dataObj;
	}

	/**
	 * returning Caseloads table values
	 *
	 * @param searchBean {@link StaffMembers}
	 * @return a list of the StaffMembers {@link StaffMembers} for the matched StaffMembers
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/omss40/getCaseLoads", method = RequestMethod.GET)
	public @ResponseBody List<Caseloads> getCaseLoads(final StaffMembers searchBean) {
		List<Caseloads> dataObj = new ArrayList<Caseloads>();
		String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		try {
			searchBean.setCreateUserId(userId);
			dataObj = omss40Service.getCaseloads(searchBean);
		} catch (Exception e) {
			logger.error("", e);
		}
		return dataObj;
	}

	/**
	 * returning Caseloads table values
	 *
	 * @return a list of mapped value of CaseLoadAgencies {@link Map}
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/omss40/getCaseLoadAgencies", method = RequestMethod.GET)
	public @ResponseBody Map<String, List<String>> getCaseLoadAgencies() {
		Map<String, List<String>> dataObj = new HashMap<>();
		try {
			final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			dataObj = omss40Service.getCaseLoadAgencies(authentication.getName());
		} catch (Exception e) {
			logger.error("", e);
		}
		return dataObj;
	}

	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/omss40/changeWorkingCaseLoad", method = RequestMethod.POST)
	public @ResponseBody  String changeWorkingCaseLoad(@RequestBody final Caseloads caseLoadsBean) {
		String dataObj = "";
		try {
			if (caseLoadsBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				caseLoadsBean.setCreateUserId(userName);
			}
			dataObj = omss40Service.changeWorkingCaseLoad(caseLoadsBean.getCaseloadId(),caseLoadsBean.getCreateUserId());
		} catch (Exception e) {
			logger.error(e);
		}
		return dataObj;
	}

	/**
	 * returning UserRole table values
	 *
	 * @return the User and their role info {@link UserRoleInfo}
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/omss40/UserRoleInfo", method = RequestMethod.GET)
	public @ResponseBody UserRoleInfo getUserRoleInfo() {
		UserRoleInfo roles = null;
		try {
			final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			roles = omss40Service.getUserRoleInfo(authentication.getName());
		} catch (Exception e) {
			logger.error(e);
		}
		return roles;
	}

	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/omss40/getAssignedOffenderList", method = RequestMethod.GET)
	public @ResponseBody List<VHeaderBlock> getAssignedOffenderList(@RequestParam(value = "currentCaseLoadType") final String currentCaseLoadType) {
		List<VHeaderBlock> assignedOffenders = new ArrayList<VHeaderBlock>();
		try {
			final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			assignedOffenders = omss40Service.getAssignedOffenderList(authentication.getName(),currentCaseLoadType);
		} catch (Exception e) {
			logger.error(e);
		}
		return assignedOffenders;
	}

	/**
	 * updating RecentOffender info values
	 *
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/omss40/updateRecentOffenderList", method = RequestMethod.POST)
	public @ResponseBody List<VHeaderBlock> updateRecentOffenderList(@RequestBody final VHeaderBlock paramBean) {
		List<VHeaderBlock> receOffenderList = new ArrayList<VHeaderBlock>();
		try {
			final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			paramBean.setUserId(authentication.getName());
			receOffenderList = omss40Service.updateRecentOffenderList(paramBean);
		} catch (Exception e) {
			logger.error("", e);
		}
		return receOffenderList;
	}
	/**
	 * getting RecentOffender List
	 *
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/omss40/getRecentOffenderList", method = RequestMethod.GET)
	public @ResponseBody List<VHeaderBlock> getRecentOffenderList(@RequestParam(value = "caseLoadId") final String caseLoadId) {
		List<VHeaderBlock> recentOffenderList = new ArrayList<VHeaderBlock>();
		/**
		 *  count variable to show no. of recent offender
		 **/
		int count = 10;
		try {
			final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			recentOffenderList = omss40Service.getRecentOffenderList(authentication.getName(),caseLoadId,count);
		} catch (Exception e) {
			logger.error("", e);
		}
		return recentOffenderList;
	}

	/**
	 * getting RecentOffender List
	 *
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/omss40/getServeTime", method = RequestMethod.GET)
	public @ResponseBody String getServeTime() {
		String time = null;
		try {
			time = eliteDateService.getFormateDBTime();
		} catch (Exception e) {
			logger.error("", e);
		}
		return time;
	}


	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/omss40/getHelpMedia", method = RequestMethod.GET)
	public List<HelpMedia> getHelpMedia(@RequestParam(value = "moduleName") final String moduleName) {
		List<HelpMedia> helpMediaList = new ArrayList<>();
		try {
			helpMediaList = omss40Service.getHelpMedia(moduleName);
		} catch (Exception e) {
			logger.error("", e);
		}
		return helpMediaList;
			}


	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/omss40/iwpSupported", method = RequestMethod.GET)
	public int iwpSupported(@RequestParam(value = "moduleName") final String moduleName) {
		int count = 0;
		try {
			count = omss40Service.iwpOnScreen(moduleName);
		} catch (Exception e) {
			logger.error("", e);
		}
		return count;
	}
	
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/omss40/getUserName", method = RequestMethod.GET)
	public @ResponseBody String getUserName() {
		String userName = "";
		try {
			final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			userName = authentication.getName();
		} catch (Exception e) {
			logger.error(e);
		}
		return userName;
	}
	
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/omss40/getApplicationStatus", method = RequestMethod.GET)
	public Map<String,Object> getApplicationStatus() {
		Map<String,Object> statusMap = null;
		try {
			statusMap=omss40Service.getApplicationStatus();
		} catch (final Exception e) {
			logger.error("Exception in getApplicationStatus:", e.getMessage());
		}
		return statusMap;
	}
	
	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchBean CaseloadAgencyLocations
	 * @return List<CaseloadAgencyLocations>
	 */
	@RequestMapping(value = "/omss40/alExecuteQuery", method = RequestMethod.POST)
	public List<CaseloadAgencyLocations> alExecuteQuery(@RequestBody final CaseloadAgencyLocations searchBean) {
		List<CaseloadAgencyLocations> searchResult = new ArrayList<>();
		final CaseloadAgencyLocations bean = new CaseloadAgencyLocations();
		try {
			searchResult = oumacaseService.alExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("In alExecuteQuery method : ", e);
			bean.setErrorMessage(e.getMessage());
		}
		return searchResult;
	}
	
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/omss40/auditPage", method = RequestMethod.POST)
	public int auditPage(@RequestBody EliteViewLog auditLog) {
		int updated = 0;
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			auditLog.setUserId(userName);
			omss40Service.auditPage(auditLog);
		} catch (Exception e) {
			logger.error("In Omss40Controller auditPage :  {}", e.getMessage());
		}
		return updated;
	}

}