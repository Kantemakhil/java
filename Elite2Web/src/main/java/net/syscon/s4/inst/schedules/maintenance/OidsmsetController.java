package net.syscon.s4.inst.schedules.maintenance;

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
import net.syscon.s4.inst.schedules.bean.SchMovSettingCommitBean;
import net.syscon.s4.inst.schedules.bean.ScheduleMovementSetting;

@EliteController
public class OidsmsetController {

	private static Logger logger = LogManager.getLogger(OimrelscController.class.getName());
	@Autowired
	private OidsmsetService oidsmsetService;

	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oidsmset/tapScheduleSettingCommit", method = RequestMethod.POST)
	public @ResponseBody Integer tapScheduleSettingCommit(@RequestBody final SchMovSettingCommitBean commitBean) {
		Integer liReturn = null;
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = oidsmsetService.tapScheduleSettingCommit(commitBean);
		} catch (Exception e) {
			logger.error("Exception in tapScheduleSettingCommit :", e);
		}
		return liReturn;
	}

	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oidsmset/tapScheduleSettingExecuteQuery", method = RequestMethod.GET)
	public List<ScheduleMovementSetting> tapScheduleSettingExecuteQuery() {
		List<ScheduleMovementSetting> searchResult = new ArrayList<>();
		try {
			searchResult = oidsmsetService.tapScheduleSettingExecuteQuery();
		} catch (final Exception e) {
			logger.error("Exception in tapScheduleSettingExecuteQuery :", e);
		}
		return searchResult;
	}

}
