package net.syscon.s4.inst.demographicsbiometrics;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.im.beans.OffenderAlerts;
import net.syscon.s4.inst.casemanagement.beans.WorkFlowLogs;
import net.syscon.s4.inst.casemanagement.beans.WorkFlowLogsCommitBean;
import net.syscon.s4.pkgs.common.CommonService;
import net.syscon.s4.sa.recordmaintenance.ProsmainService;

/**
 * class OcuverifController 
 */
@EliteController
public class OcuverifController {

	@Autowired
	private OcuverifService ocuverifService;	
	@Autowired
	private ProsmainService prosmainService;
	@Autowired
	private CommonService commonService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcuverifController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocuverif/workFlExecuteQuery", method = RequestMethod.POST)
	public List<WorkFlowLogs> workFlExecuteQuery(@RequestBody final OffenderAlerts searchBean) {
		List<WorkFlowLogs> searchResult = new ArrayList<>();
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			searchResult = ocuverifService.workFlExecuteQuery(searchBean);
		} catch (Exception e) {
			final WorkFlowLogs bean = new WorkFlowLogs();
			logger.error("workFlExecuteQuery: ", e);
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
	@RequestMapping(value = "/ocuverif/workFlCommit", method = RequestMethod.POST)
	public @ResponseBody Integer workFlCommit(@RequestBody final WorkFlowLogsCommitBean commitBean) {
		int liReturn = 0;
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			commitBean.setCreateUserId(userName);
			liReturn = ocuverifService.workFlCommit(commitBean);
		} catch (Exception e) {
			logger.error("In method workFlCommit", e);
		}
		return liReturn;
	}

	
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocuverif/workFlVerifyCommit", method = RequestMethod.POST)
	public @ResponseBody Integer workFlVerificationCommit(@RequestBody final WorkFlowLogsCommitBean commitBean, @RequestHeader HttpHeaders headers) {
		List<String> authorizationList = headers.get("Authorization");
		String authorization = authorizationList.get(0).split(",")[0];
		int liReturn = 0;
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			commitBean.setCreateUserId(userName);
			liReturn = ocuverifService.workFlVerificationCommit(commitBean);
			if(liReturn == 1) {
				prosmainService.enableTriggers(commitBean, authorization, "116");
			}
		} catch (Exception e) {
			logger.error("In method workFlCommit", e);
		}
		return liReturn;
	}
	
	private Boolean checkCallFormAccess(String role) {
		String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		return commonService.checkCallFormAccess(userId, role,"OCUVERIF");
	}
}