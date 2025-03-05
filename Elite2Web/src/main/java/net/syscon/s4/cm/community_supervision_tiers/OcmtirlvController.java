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
import org.springframework.web.bind.annotation.ResponseBody;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.community_supervision_tiers.MaintainTierLevels;
import net.syscon.s4.community_supervision_tiers.MaintainTierLevelsCommitBean;

@EliteController
public class OcmtirlvController {

	private static Logger logger = LogManager.getLogger(OcmtirlvController.class.getName());

	@Autowired
	private OcmtirlvService ocmtirlvservice;

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmtirlv/tierLevelExecuteQuery", method = RequestMethod.POST)
	public List<MaintainTierLevels> tierLevelExecuteQuery(@RequestBody final MaintainTierLevels searchBean) {
		List<MaintainTierLevels> searchResult = new ArrayList<>();
		try {
			searchResult = ocmtirlvservice.tierLevelExecuteQuery(searchBean);
		} catch (Exception e) {
			final MaintainTierLevels bean = new MaintainTierLevels();
			searchResult.add(bean);
			logger.error(this.getClass().getName()+" In method offAssExecuteQuery", e);
		}
		return searchResult;
	}

	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocmtirlv/tierLevelCommit", method = RequestMethod.POST)
	public @ResponseBody Integer offNotesCommit(@RequestBody final MaintainTierLevelsCommitBean commitBean) {
		int liReturn = 0;
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = ocmtirlvservice.tierLevelCommit(commitBean);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"tierLevelCommit", e);
		}
		return liReturn;
	}

	
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmtirlv/rgIntakeTierRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgIntakeTierRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocmtirlvservice.rgIntakeTierRecordGroup();
		} catch (Exception e) {
			logger.error(this.getClass().getName()+" rgIntakeTierRecordGroup in error", e);
		}
		return recordList;
	}
}
