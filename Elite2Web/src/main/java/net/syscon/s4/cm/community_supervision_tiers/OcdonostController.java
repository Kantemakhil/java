package net.syscon.s4.cm.community_supervision_tiers;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.server.ResponseStatusException;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.community_supervision_tiers.WlOfficerNonOffSpecificTasks;
import net.syscon.s4.community_supervision_tiers.WlOfficerNonOffSpecificTasksCommitBean;
import net.syscon.s4.pkgs.common.CommonService;

@EliteController
public class OcdonostController {

	private static Logger logger = LogManager.getLogger(OcdonostController.class.getName());

	@Autowired
	private OcdonostService ocdonostService;
	@Autowired
	private CommonService commonService;
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocdonost/getStaffName", method = RequestMethod.POST)
	public WlOfficerNonOffSpecificTasks getStaffName(@RequestBody WlOfficerNonOffSpecificTasks obj) {
		WlOfficerNonOffSpecificTasks retVal = new WlOfficerNonOffSpecificTasks();
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		obj.setModifyUserId(userName);
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			retVal = ocdonostService.getStaffName(obj);
		} catch (Exception e) {
			logger.error("In getStaffName method : ", e);
		}
		return retVal;
	}

	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocdonost/getNonOffenderSpecificTasks", method = RequestMethod.POST)
	public List<WlOfficerNonOffSpecificTasks> getNonOffenderSpecificTasks(
			@RequestBody WlOfficerNonOffSpecificTasks obj) {
		List<WlOfficerNonOffSpecificTasks> retVal = new ArrayList<WlOfficerNonOffSpecificTasks>();
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			retVal = ocdonostService.getNonOffenderSpecificTasks(obj);
		} catch (Exception e) {
			logger.error("In getNonOffenderSpecificTasks method : ", e);
		}
		return retVal;
	}

	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocdonost/offNonOffSpeTaskCommit", method = RequestMethod.POST)
	public Integer offNonOffSpeTaskCommit(@RequestBody WlOfficerNonOffSpecificTasksCommitBean commitBean) {
		Integer searchResult = 0;
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			searchResult = ocdonostService.offNonOffSpeTaskCommit(commitBean);
		} catch (Exception e) {
			logger.error("In ocdonostService method : ", e);
		}
		return searchResult;
	}
	private Boolean checkCallFormAccess(String role) {
		String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		return commonService.checkCallFormAccess(userId, role,"OCDONOST");
	}
}
