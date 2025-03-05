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
import org.springframework.web.bind.annotation.ResponseBody;

import net.syscon.s4.cm.community_supervision_tiers.OcdotrlvService;
import net.syscon.s4.common.EliteController;
import net.syscon.s4.community_supervision_tiers.OffenderTierLevel;
import net.syscon.s4.community_supervision_tiers.OffenderTierLevelCommitBean;
import net.syscon.s4.inst.demographicsbiometrics.OcuverifRepository;

@EliteController
public class OcdotrlvController {
	private static Logger logger = LogManager.getLogger(OcdotrlvController.class.getName());
	
	@Autowired
	private OcdotrlvService ocdotrlvService;
	
	
	
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocdotrlv/offendertierLevelExecuteQuery", method = RequestMethod.POST)
	public List<OffenderTierLevel> offendertierLevelExecuteQuery(@RequestBody final OffenderTierLevel searchBean) {
		List<OffenderTierLevel> searchResult = new ArrayList<>();
		try {
			if (searchBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				searchBean.setCreateUserId(userName);
			}
			searchResult = ocdotrlvService.offendertierLevelExecuteQuery(searchBean);
		} catch (Exception e) {
			final OffenderTierLevel bean = new OffenderTierLevel();
			logger.error("In method offAssExecuteQuery", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}
	
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocdotrlv/offendertierLevelCommit", method = RequestMethod.POST)
	public @ResponseBody Integer offendertierLevelCommit(@RequestBody final OffenderTierLevelCommitBean commitBean) {
		int liReturn = 0;
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = ocdotrlvService.offendertierLevelCommit(commitBean);
		} catch (Exception e) {

			logger.error("offendertierLevelCommit", e);
		}
		return liReturn;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdotrlv/offenderTierLevesRecordGroup", method = RequestMethod.GET)
	public List<OffenderTierLevel> offenderTierLevesRecordGroup(@RequestParam(value = "caseLoadId") String caseLoadId) {
		List<OffenderTierLevel> searchResult = new ArrayList<>();
		try {
			searchResult = ocdotrlvService.offenderTierLevesRecordGroup(caseLoadId);
		} catch (Exception e) {
			final OffenderTierLevel bean = new OffenderTierLevel();
			logger.error("In method offenderTierLevesRecordGroup", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdotrlv/offTierLevesUserIdRecordGroup", method = RequestMethod.GET)
	public String offTierLevesUserIdRecordGroup(@RequestParam(value = "userName") String userName) {
		String userId =null;
		try {
			userId = ocdotrlvService.offTierLevesUserIdRecordGroup(userName);
		} catch (Exception e) {
			logger.error("In method offTierLevesUserIdRecordGroup", e);
		}
		return userId;
	}

	
	

}
