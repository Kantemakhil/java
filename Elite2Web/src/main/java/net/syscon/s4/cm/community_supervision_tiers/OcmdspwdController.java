package net.syscon.s4.cm.community_supervision_tiers;

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

import net.syscon.s4.common.EliteController;
import net.syscon.s4.community_supervision_tiers.WlDefaultStaffUnits;
import net.syscon.s4.community_supervision_tiers.WlNonOffSpecificTasks;
import net.syscon.s4.community_supervision_tiers.WlNonOffSpecificTasksCommitBean;
import net.syscon.s4.im.beans.AgencyLocations;

@EliteController
public class OcmdspwdController {

	private static Logger logger = LogManager.getLogger(OcmdspwdController.class.getName());

	@Autowired
	private OcmdspwdService ocmdspwdService;

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmdspwd/getAgyLocRecordGroup", method = RequestMethod.GET)
	public List<AgencyLocations> getAgyLocRecordGroup(@RequestParam String caseloadId) {
		List<AgencyLocations> searchResult = new ArrayList<>();
		final AgencyLocations bean = new AgencyLocations();
		try {
			searchResult = ocmdspwdService.getAgyLocRecordGroup(caseloadId);
		} catch (Exception e) {
			logger.error("In getAgyLocRecordGroup method : ", e);
			bean.setErrorMessage(e.getMessage());
		}
		return searchResult;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmdspwd/maintainDefStaffPosWL", method = RequestMethod.POST)
	public Integer maintainDefStaffPosWL(@RequestBody WlNonOffSpecificTasksCommitBean commitBean) {
		Integer searchResult = 0;
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			searchResult = ocmdspwdService.maintainDefStaffPosWL(commitBean);
		} catch (Exception e) {
			logger.error("In maintainDefStaffPosWL method : ", e);
		}
		return searchResult;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmdspwd/assStartingDefWLUnitsExecuteQuery", method = RequestMethod.GET)
	public List<WlDefaultStaffUnits> assStartingDefWLUnitsExecuteQuery() {
		List<WlDefaultStaffUnits> searchResult = new ArrayList<WlDefaultStaffUnits>();
		try {
			searchResult = ocmdspwdService.assStartingDefWLUnitsExecuteQuery();
		} catch (Exception e) {
			logger.error("In assStartingDefWLUnitsExecuteQuery method : ", e);
		}
		return searchResult;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmdspwd/nonOffSpecTaskPosExecuteQuery", method = RequestMethod.POST)
	public List<WlNonOffSpecificTasks> nonOffSpecTaskPosExecuteQuery(@RequestBody WlNonOffSpecificTasks obj) {
		List<WlNonOffSpecificTasks> searchResult = new ArrayList<WlNonOffSpecificTasks>();
		try {
			searchResult = ocmdspwdService.nonOffSpecTaskPosExecuteQuery(obj);
		} catch (Exception e) {
			logger.error("In nonOffSpecTaskPosExecuteQuery method : ", e);
		}
		return searchResult;
	}
	
}
