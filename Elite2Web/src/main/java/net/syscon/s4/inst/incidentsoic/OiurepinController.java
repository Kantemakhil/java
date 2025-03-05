package net.syscon.s4.inst.incidentsoic;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.im.incidentsoic.beans.ReportableIncedentDetails;
import net.syscon.s4.im.incidentsoic.beans.ReportableIncedentDetailsCommitBean;
import net.syscon.s4.sa.recordmaintenance.ProsmainService;

@EliteController
public class OiurepinController {
	
	@Autowired
	private OiurepinService oiurepinsevice;
	@Autowired
	private ProsmainService prosmainService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OiurepinController.class.getName());

	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiurepin/getReportDetailsExecuteQuery", method = RequestMethod.POST)
	public List<ReportableIncedentDetails> getReportDetailsExecuteQuery(@RequestBody final ReportableIncedentDetails searchBean) {
		List<ReportableIncedentDetails> searchResult = new ArrayList<ReportableIncedentDetails>();
		final ReportableIncedentDetails bean = new ReportableIncedentDetails();
		try {
			searchResult = oiurepinsevice.getReportDetailsExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("getReportDetailsExecuteQuery",e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}
	
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oiurepin/reportableIncedentDetailsCommit", method = RequestMethod.POST)
	public @ResponseBody List<ReportableIncedentDetails> reportableIncedentDetailsCommit(
			@RequestBody final ReportableIncedentDetailsCommitBean commitBean,@RequestHeader HttpHeaders headers) {
		List<String> authorizationList = headers.get("Authorization");
		String authorization = authorizationList.get(0).split(",")[0];
		List<ReportableIncedentDetails> liReturn = new ArrayList<>();
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = oiurepinsevice.reportableIncedentDetailsCommit(commitBean);
			if(liReturn.size()>0) {
				prosmainService.enableTriggers(commitBean, authorization, "121");
			}
		} catch (Exception e) {
			final ReportableIncedentDetails error = new ReportableIncedentDetails();
			final String errorMsg = "Error : " + e.getMessage();
			error.setErrorMessage(errorMsg.toUpperCase());
			liReturn.add(error);
			logger.error("Exception :", e);

		}
		return liReturn;
	}
	
	
	@RequestMapping(value = "/oiurepin/getUserNameLog", method = RequestMethod.GET)
	public String getUserNameLog() {
		String userName=null;
		try {
			String userNameLog = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			userName = oiurepinsevice.getUserNameLog(userNameLog);
		} catch (Exception e) {
			logger.error("getReportDetailsExecuteQuery",e);
		}
		return userName;
	}
	
}
