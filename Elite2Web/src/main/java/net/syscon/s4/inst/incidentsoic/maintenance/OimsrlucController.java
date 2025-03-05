package net.syscon.s4.inst.incidentsoic.maintenance;

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
import net.syscon.s4.common.beans.ReferenceCodesCommitBean;
import net.syscon.s4.common.beans.ReferenceDomains;

@EliteController
public class OimsrlucController {
		/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OimsrlucController.class.getName());

	@Autowired
	private OimsrlucService oimsrlucService;

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimsrluc/refDmnExcuteQuery", method = RequestMethod.GET)
	public List<ReferenceDomains> refDmnExcuteQuery() {
		List<ReferenceDomains> recordList = new ArrayList<ReferenceDomains>();
		try {
			recordList = oimsrlucService.refDmnExcuteQuery();
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return recordList;
	}
	
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimsrluc/refCodeExecuteQuery", method = RequestMethod.POST)
	public List<ReferenceCodes> refCodeExecuteQuery(@RequestBody ReferenceCodes searchBean) {
		List<ReferenceCodes> searchResult = new ArrayList<>();
		try {
			searchResult = oimsrlucService.refCodeExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " refCodeExecuteQuery", e);
		}
		return searchResult;
	}

	@RequestMapping(value = "/oimsrluc/refCodeCondCommit", method = RequestMethod.POST)
	public @ResponseBody Integer refCodeCondCommit(@RequestBody ReferenceCodesCommitBean commitBean) {
		int liReturn = 0;
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = oimsrlucService.refCodeCondCommit(commitBean);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " refCodeCommit", e);
		}
		return liReturn;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimsrluc/refCodeCondExecuteQuery", method = RequestMethod.GET)
	public ReferenceCodes refCodeCondExecuteQuery(@RequestParam("code") String code) {
		ReferenceCodes obj = new ReferenceCodes();
		try {
			obj = oimsrlucService.refCodeCondExecuteQuery(code);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " refCodeCondExecuteQuery", e);
		}
		return obj;
	}
	
	
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimsrluc/unitLovExecuteQuery", method = RequestMethod.GET)
	public List<ReferenceCodes> unitLovExecuteQuery() {
		List<ReferenceCodes> searchResult = new ArrayList<>();
		try {
			searchResult = oimsrlucService.unitLovExecuteQuery();
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " refCodeExecuteQuery", e);
		}
		return searchResult;
	}
}
