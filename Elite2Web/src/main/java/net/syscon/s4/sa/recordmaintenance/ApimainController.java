package net.syscon.s4.sa.recordmaintenance;

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

@EliteController
public class ApimainController {
	@Autowired
	private ApimainService apimainService;
	
	private static Logger logger = LogManager.getLogger(OumbadmiController.class.getName());
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/apimain/apimainExecuteQuery", method = RequestMethod.GET)  
	public List<ActionApi> apimainExecuteQuery() {
		List<ActionApi> searchResult = new ArrayList<>();
		try {
			searchResult = apimainService.apimainExecuteQuery();
		} catch (Exception e) {
			ActionApi bean = new ActionApi();
			logger.error("Exception :", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}
	
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/apimain/apimainCommit")
	public @ResponseBody Integer apimainCommit(@RequestBody ActionApiCommitBean commitBean) {
		int liReturn = 0;
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			commitBean.setCreateUserId(userName);
			liReturn = apimainService.apimainCommit(commitBean);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return liReturn;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/apimain/rgQueryKeyRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgQueryKeyRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = apimainService.rgQueryKeyRecordGroup();
		} catch (Exception e) {
			ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception :", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/apimain/getQuickActions", method = RequestMethod.GET)  
	public List<ActionApi> getQuickActions() {
		List<ActionApi> searchResult = new ArrayList<>();
		try {
			searchResult = apimainService.getQuickActions();
		} catch (Exception e) {
			ActionApi bean = new ActionApi();
			logger.error("Exception :", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/apimain/rgUrlKeyRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgUrlKeyRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = apimainService.rgUrlKeyRecordGroup();
		} catch (Exception e) {
			ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception :", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}
}
