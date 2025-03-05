package net.syscon.s4.inmate.trust.deductions;

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
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.inmate.beans.BeneficiaryTransactions;
import net.syscon.s4.inmate.beans.VClearAccountPayables;
import net.syscon.s4.inmate.beans.VClearAccountPayablesCommitBean;

/**
 * Class OcdcbeneController
 */
@EliteController
public class OcdcbeneController {
	@Autowired
	private OcdcbeneService ocdcbeneService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcdcbeneController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdcbene/payeeAbExecuteQuery", method = RequestMethod.POST)
	public List<VClearAccountPayables> payeeAbExecuteQuery(@RequestBody final VClearAccountPayables searchBean) {
		List<VClearAccountPayables> searchResult = new ArrayList<>();
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		searchBean.setCreateUserId(userName);
		try {
			searchResult = ocdcbeneService.payeeAbExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"Error in method payeeAbExecuteQuery:", e);
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
	@RequestMapping(value = "/ocdcbene/payeeAbCommit", method = RequestMethod.POST)
	public @ResponseBody Integer payeeAbCommit(@RequestBody VClearAccountPayablesCommitBean commitBean) {
		Integer liReturn = null;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = ocdcbeneService.payeeAbCommit(commitBean);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"Error in method payeeAbCommit", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdcbene/benTxnExecuteQuery", method = RequestMethod.POST)
	public List<BeneficiaryTransactions> benTxnExecuteQuery(@RequestBody final BeneficiaryTransactions searchBean) {
		List<BeneficiaryTransactions> searchResult = new ArrayList<>();
		try {
			searchResult = ocdcbeneService.benTxnExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"Error in method benTxnExecuteQuery:", e);
		}
		return searchResult;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdcbene/sysPflExecuteQuery", method = RequestMethod.POST)
	public List<SystemProfiles> sysPflExecuteQuery(@RequestBody final SystemProfiles searchBean) {
		List<SystemProfiles> searchResult = new ArrayList<>();
		try {
			searchResult = ocdcbeneService.sysPflExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"Error in method sysPflExecuteQuery:", e);
		}
		return searchResult;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdcbene/checkLock", method = RequestMethod.GET)
	public String checkLock(@RequestParam(value = "caseLoadId") final String caseLoadId) {
		String returnVal = null;
		try {
			returnVal = ocdcbeneService.checkLock(caseLoadId);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"Error in method checkLock:", e);
		}
		return returnVal;
	}

}