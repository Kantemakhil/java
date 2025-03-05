package net.syscon.s4.iwp;

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
import net.syscon.s4.inst.casemanagement.beans.CaseReviewPeriods;
import net.syscon.s4.inst.casemanagement.beans.CaseReviewPeriodsCommitBean;

/**
 * OcmcprevController
 */
@EliteController
public class OcmcprevController {
	@Autowired
	private OcmcprevService ocmcprevService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcmcprevController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmcprev/caseReviewPeriodsExecuteQuery", method = RequestMethod.POST)
	public List<CaseReviewPeriods> caseReviewPeriodsExecuteQuery(@RequestBody final CaseReviewPeriods searchBean) {
		List<CaseReviewPeriods> searchResult = new ArrayList<>();
		try {
			searchResult = ocmcprevService.caseReviewPeriodsExecuteQuery(searchBean);
		} catch (final Exception e) {
			final CaseReviewPeriods bean = new CaseReviewPeriods();
			logger.error("Exception :", e);
			// bean.setErrorMessage(e.getMessage());
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
	@RequestMapping(value = "/ocmcprev/caseReviewPeriodsCommit", method = RequestMethod.POST)
	public @ResponseBody List<CaseReviewPeriods> caseReviewPeriodsCommit(
			@RequestBody final CaseReviewPeriodsCommitBean commitBean) {
		List<CaseReviewPeriods> liReturn = new ArrayList<>();
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = ocmcprevService.caseReviewPeriodsCommit(commitBean);
		} catch (final Exception e) {
			final CaseReviewPeriods error = new CaseReviewPeriods();
			final String errorMsg = "Error : " + e.getMessage();
			error.setErrorMessage(errorMsg);
			liReturn.add(error);
		}
		return liReturn;
	}

	/**
	 * getting rgSupLevel LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmcprev/rgSupLevelRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgSupLevelRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocmcprevService.rgSupLevelRecordGroup();
		} catch (final Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception :", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

}