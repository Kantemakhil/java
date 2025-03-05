package net.syscon.s4.inmate.trust.deductions;

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

import net.syscon.s4.cf.deductions.beans.OffFeeBillTransactions;
import net.syscon.s4.cf.deductions.beans.OffFeeBillTransactionsCommitBean;
import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.OffenderTransactionsCommitBean;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.im.beans.TransactionOperation;
import net.syscon.s4.inmate.beans.OffenderBeneficiaries;
import net.syscon.s4.inmate.beans.OffenderBeneficiariesCommitBean;

/**
 * Class OcdpayobController
 */
@EliteController
public class OcdpayobController {
	@Autowired
	private OcdpayobService ocdpayobService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcdpayobController.class.getName());

	/**
	 * getting cgfkOffTxnSubAccountType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdpayob/cgfkOffTxnSubAccountTypeRecordGroup", method = RequestMethod.GET)
	public List<TransactionOperation> cgfkOffTxnSubAccountTypeRecordGroup(
			@RequestParam(value = "caseloadId") final String caseloadId) {
		List<TransactionOperation> recordList = new ArrayList<TransactionOperation>();
		try {
			recordList = ocdpayobService.cgfkOffTxnSubAccountTypeRecordGroup(caseloadId);
		} catch (Exception e) {
			logger.error("cgfkOffTxnSubAccountTypeRecordGroup : ", e);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdpayob/offTxnExecuteQuery", method = RequestMethod.POST)
	public List<OffenderTransactions> offTxnExecuteQuery(@RequestBody final OffenderTransactions searchBean) {
		List<OffenderTransactions> searchResult = new ArrayList<>();
		try {
			searchResult = ocdpayobService.offTxnExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("offTxnExecuteQuery : ", e);
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
	@RequestMapping(value = "/ocdpayob/offTxnCommit", method = RequestMethod.POST)
	public @ResponseBody Integer offTxnCommit(@RequestBody final OffenderTransactionsCommitBean commitBean) {
		int liReturn = 0;
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = ocdpayobService.offTxnCommit(commitBean);
		} catch (Exception e) {
			logger.error("offTxnCommit : ", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdpayob/offBncExecuteQuery", method = RequestMethod.POST)
	public List<OffenderBeneficiaries> offBncExecuteQuery(@RequestBody final OffenderBeneficiaries searchBean) {
		List<OffenderBeneficiaries> searchResult = new ArrayList<>();
		try {
			searchResult = ocdpayobService.offBncExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("offBncExecuteQuery : ", e);
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
	@RequestMapping(value = "/ocdpayob/offBncCommit", method = RequestMethod.POST)
	public @ResponseBody OffenderBeneficiaries offBncCommit(
			@RequestBody final OffenderBeneficiariesCommitBean commitBean) {
		OffenderBeneficiaries liReturn = null;
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = new OffenderBeneficiaries();
			liReturn = ocdpayobService.offBncCommit(commitBean);
		} catch (Exception e) {
			logger.error("offBncCommit : ", e);
			if(e.getMessage().equals("AMT_CANNOT_GREATER_THAN_BALANCE_OWING")) {
				liReturn.setSealFlag("I");	
			}
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdpayob/sysPflExecuteQuery", method = RequestMethod.POST)
	public List<SystemProfiles> sysPflExecuteQuery(@RequestBody final SystemProfiles searchBean) {
		List<SystemProfiles> searchResult = new ArrayList<>();
		try {
			searchResult = ocdpayobService.sysPflExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("sysPflExecuteQuery : ", e);
		}
		return searchResult;
	}

	/**
	 * getting getCurrentBalance value
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdpayob/getCurrentBalance", method = RequestMethod.POST)
	public BigDecimal getCurrentBalance(@RequestBody final OffenderTransactions searchBean) {
		BigDecimal returnValue = BigDecimal.ZERO;
		try {
			returnValue = ocdpayobService.getCurrentBalance(searchBean);
		} catch (Exception e) {
			logger.error("getCurrentBalance : ", e);
		}
		return returnValue;
	}

	/**
	 * getting txnTyepOffTxns value
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdpayob/txnTyepOffTxns", method = RequestMethod.GET)
	public String txnTyepOffTxns(@RequestParam(value = "subAccountType") final String subAccountType,
			@RequestParam(value = "caseloadType") final String caseloadType,
			@RequestParam(value = "caseloadId") final String caseloadId) {
		String returnValue = null;
		try {
			returnValue = ocdpayobService.txnTyepOffTxns(subAccountType, caseloadType, caseloadId);
		} catch (Exception e) {
			logger.error("txnTyepOffTxns : ", e);
		}
		return returnValue;
	}
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdpayob/getOffenderFeesEnableBtn", method = RequestMethod.GET)
	public String getOffenderFeesEnableBtn() {
		String profileValue = null;
		try {
			profileValue = ocdpayobService.getOffenderFeesEnableBtn();
		} catch (final Exception e) {
			logger.error("getProfileValueDisableBtn :", e);
		}
		return profileValue;
	}
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdpayob/offFeeExecuteQuery", method = RequestMethod.POST)
	public List<OffFeeBillTransactions> offFeeExecuteQuery(@RequestBody final OffFeeBillTransactions serachBean) {
		List<OffFeeBillTransactions> returnList = new ArrayList<>();
		try {
			returnList = ocdpayobService.offFeeExecuteQuery(serachBean);
		} catch (Exception e) {
			logger.error("Exception in offFeeExecuteQuery :", e);
		}
		return returnList;
	}
	@RequestMapping(value = "/ocdpayob/offFeesCommit" , method =RequestMethod.POST)
	public Integer offFeesCommit(@RequestBody OffFeeBillTransactionsCommitBean commitBean){
		Integer retVal = 0;
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			retVal = ocdpayobService.offFeesCommit(commitBean);
		} catch (Exception e) {
			logger.error("offFeesCommit :", e);
		}
		return retVal;
	}

}