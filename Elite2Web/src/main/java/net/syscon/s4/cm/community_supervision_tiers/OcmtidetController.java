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

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.community_supervision_tiers.MaintainTierDefaultEvents;
import net.syscon.s4.community_supervision_tiers.MaintainTierLevelsCommitBean;

@EliteController
public class OcmtidetController {

	private static Logger logger = LogManager.getLogger(OcmtidetController.class.getName());

	@Autowired
	private OcmtidetService ocmtidetservice;

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmtidet/tierdefaultEventsExecuteQuery", method = RequestMethod.POST)
	public List<MaintainTierDefaultEvents> tierdefaultEventsExecuteQuery(
			@RequestBody final MaintainTierDefaultEvents searchBean) {
		List<MaintainTierDefaultEvents> searchResult = new ArrayList<>();
		try {
			searchResult = ocmtidetservice.tierdefaultEventsExecuteQuery(searchBean);
		} catch (Exception e) {
			final MaintainTierDefaultEvents bean = new MaintainTierDefaultEvents();
			searchResult.add(bean);
			logger.error(this.getClass().getName() + " In method offAssExecuteQuery", e);
		}
		return searchResult;
	}

	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocmtidet/tierdefaultEventsCommit", method = RequestMethod.POST)
	public @ResponseBody Integer tierdefaultEventsCommit(@RequestBody final MaintainTierLevelsCommitBean commitBean) {
		int liReturn = 0;
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = ocmtidetservice.tierdefaultEventsCommit(commitBean);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + "tierLevelCommit", e);
		}
		return liReturn;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmtidet/getActiveTierEvent", method = RequestMethod.GET)
	public ReferenceCodes getActiveTierEvent(@RequestParam("offenederBookId") Long offnderBookId) {
		ReferenceCodes bean = new ReferenceCodes();
		try {
			bean = ocmtidetservice.getActiveTierEvent(offnderBookId);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + "getActiveTierEvent", e);
		}
		return bean;
	}
	
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmtidet/rgScheduleTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgScheduleTypeRecordGroup(@RequestParam("scheduleType") String scheduleType) {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocmtidetservice.rgScheduleTypeRecordGroup(scheduleType);
		} catch (Exception e) {
			logger.error("rgScheduleTypeRecordGroup :", e);
		}
		return recordList;
	}

}
