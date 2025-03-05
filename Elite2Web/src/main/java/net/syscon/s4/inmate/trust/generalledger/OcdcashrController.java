package net.syscon.s4.inmate.trust.generalledger;

import java.math.BigDecimal;
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
import net.syscon.s4.common.beans.SystemProfilesCommitBean;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.inmate.beans.GlTransactions;
import net.syscon.s4.inmate.beans.GlTransactionsCommitBean;

/**
 * Class OcdcashrController
 */
@EliteController
public class OcdcashrController {
	@Autowired
	private OcdcashrService ocdcashrService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcdcashrController.class.getName());

	/**
	 * getting cgfkGlTxnAccountCode LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdcashr/cgfkGlTxnAccountCodeRecordGroup", method = RequestMethod.GET)
	public List<AccountCodes> cgfkGlTxnAccountCodeRecordGroup(@RequestParam("caseloadId") final String caseloadId,
			@RequestParam("caseloadType") final String caseloadType) {
		List<AccountCodes> recordList = new ArrayList<AccountCodes>();
		try {
			recordList = ocdcashrService.cgfkGlTxnAccountCodeRecordGroup(caseloadId, caseloadType);
		} catch (Exception e) {
			logger.error("cgfkGlTxnAccountCodeRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * getting cgfkGlTxnAccountCode LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdcashr/accountCodeChangeEvent", method = RequestMethod.GET)
	public String accountCodeChangeEvent(@RequestParam("caseloadId") final String caseloadId,
			@RequestParam("caseloadType") final String caseloadType,
			@RequestParam("accountCode") final BigDecimal accountCode) {
		String recordList = null;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		try {
			recordList = ocdcashrService.accountCodeChangeEvent(caseloadId, caseloadType, accountCode, userName);
		} catch (Exception e) {
			logger.error("accountCodeChangeEvent", e);
		}
		return recordList;
	}
	
	/**
	 * getting cgfkGlTxnAccountCode LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdcashr/txnAmountDataSlashes", method = RequestMethod.GET)
	public String txnAmountDataSlashes(@RequestParam("txnEntryAmount") final String txnEntryAmount) {
		String recordList = null;
		try {
			recordList = ocdcashrService.txnAmountDataSlashes(txnEntryAmount);
		} catch (Exception e) {
			logger.error("txnAmountDataSlashes", e);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdcashr/glTxnExecuteQuery", method = RequestMethod.POST)
	public List<GlTransactions> glTxnExecuteQuery(@RequestBody final GlTransactions searchBean) {
		List<GlTransactions> searchResult = new ArrayList<>();
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		searchBean.setCreateUserId(userName);
		try {
			searchResult = ocdcashrService.glTxnExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("glTxnExecuteQuery", e);
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
	@RequestMapping(value = "/ocdcashr/glTxnCommit", method = RequestMethod.POST)
	public @ResponseBody GlTransactions glTxnCommit(@RequestBody final GlTransactionsCommitBean commitBean) {
		GlTransactions liReturn = new GlTransactions();
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = ocdcashrService.glTxnCommit(commitBean);
		} catch (Exception e) {
			liReturn.setSealFlag(e.getMessage());
			logger.error("glTxnCommit", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdcashr/sysPflExecuteQuery", method = RequestMethod.POST)
	public List<SystemProfiles> sysPflExecuteQuery(@RequestBody final SystemProfiles searchBean) {
		List<SystemProfiles> searchResult = new ArrayList<>();
		try {
			searchResult = ocdcashrService.sysPflExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("sysPflExecuteQuery", e);
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
	@RequestMapping(value = "/ocdcashr/sysPflCommit", method = RequestMethod.POST)
	public @ResponseBody Integer sysPflCommit(@RequestBody final SystemProfilesCommitBean commitBean) {
		int liReturn = 0;
		try {
			liReturn = ocdcashrService.sysPflCommit(commitBean);
		} catch (Exception e) {
			logger.error("sysPflCommit", e);
		}
		return liReturn;
	}

}