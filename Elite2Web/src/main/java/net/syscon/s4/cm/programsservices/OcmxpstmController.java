package net.syscon.s4.cm.programsservices;

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
import net.syscon.s4.globalconfiguration.impl.OumrcodeServiceImpl;

@EliteController
public class OcmxpstmController {
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcmxpstmController.class.getName());

	@Autowired
	private OcmxpstmService ocmxpstmService;

	@Autowired
	private OumrcodeServiceImpl oumrcodeservice;

	@RequestMapping(value = "/ocmxpstm/refDmnExecuteQuery", method = RequestMethod.POST)
	public List<ReferenceDomains> refDmnExecuteQuery(@RequestBody ReferenceDomains searchBean) {
		List<ReferenceDomains> searchResult = new ArrayList<>();
		try {
			searchResult = oumrcodeservice.refDmnExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " refDmnExecuteQuery", e);
		}
		return searchResult;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmxpstm/refCodeExecuteQuery", method = RequestMethod.POST)
	public List<ReferenceCodes> refCodeExecuteQuery(@RequestBody ReferenceCodes searchBean) {
		List<ReferenceCodes> searchResult = new ArrayList<>();
		try {
			searchResult = ocmxpstmService.refCodeExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " refCodeExecuteQuery", e);
		}
		return searchResult;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmxpstm/refCodeCondExecuteQuery", method = RequestMethod.GET)
	public ReferenceCodes refCodeCondExecuteQuery(@RequestParam("code") String code) {
		ReferenceCodes obj = new ReferenceCodes();
		try {
			obj = ocmxpstmService.refCodeCondExecuteQuery(code);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " refCodeCondExecuteQuery", e);
		}
		return obj;
	}

	@RequestMapping(value = "/ocmxpstm/refCodeCondCommit", method = RequestMethod.POST)
	public @ResponseBody Integer refCodeCondCommit(@RequestBody ReferenceCodesCommitBean commitBean) {
		int liReturn = 0;
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = ocmxpstmService.refCodeCondCommit(commitBean);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " refCodeCommit", e);
		}
		return liReturn;
	}

}
