package net.syscon.s4.inmate.trust.trustaccounts;

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
import net.syscon.s4.common.beans.OffenderTransactionsCommitBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.inmate.beans.OffenderSubAccounts;
import net.syscon.s4.inmate.beans.OffenderSubAccountsCommitBean;

/**
 * Class OtdcloseController
 */
@EliteController
public class OtdcloseController {
	@Autowired
	private OtdcloseService otdcloseService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OtdcloseController.class.getName());

	/**
	 * getting cgfkOffTxnPayeeCode LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdclose/cgfkOffTxnPayeeCodeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> cgfkOffTxnPayeeCodeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = otdcloseService.cgfkOffTxnPayeeCodeRecordGroup();
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"error in method cgfkOffTxnPayeeCodeRecordGroup : ", e);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdclose/offSubaExecuteQuery", method = RequestMethod.POST)
	public List<OffenderSubAccounts> offSubaExecuteQuery(@RequestBody final OffenderSubAccounts searchBean) {
		List<OffenderSubAccounts> searchResult = new ArrayList<>();
		try {
			searchResult = otdcloseService.offSubaExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"error in method offSubaExecuteQuery : ", e);
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
	@RequestMapping(value = "/otdclose/offSubaCommit", method = RequestMethod.POST)
	public @ResponseBody Integer offSubaCommit(@RequestBody final OffenderSubAccountsCommitBean commitBean) {
		int liReturn = 0;
		try {
			liReturn = otdcloseService.offSubaCommit(commitBean);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"error in method offSubaCommit : ", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdclose/offTxnExecuteQuery", method = RequestMethod.POST)
	public List<OffenderTransactions> offTxnExecuteQuery(@RequestBody final OffenderTransactions searchBean) {
		List<OffenderTransactions> searchResult = new ArrayList<>();
		try {
			searchResult = otdcloseService.offTxnExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"error in method offTxnExecuteQuery : ", e);
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
	@RequestMapping(value = "/otdclose/offTxnCommit", method = RequestMethod.POST)
	public @ResponseBody OffenderTransactions offTxnCommit(
			@RequestBody final OffenderTransactionsCommitBean commitBean) {
		OffenderTransactions liReturn = null;
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			commitBean.setCreateUserId(userName);
			liReturn = new OffenderTransactions();
			liReturn = otdcloseService.offTxnCommit(commitBean);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"error in method offTxnCommit : ", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdclose/sysPflExecuteQuery", method = RequestMethod.POST)
	public List<SystemProfiles> sysPflExecuteQuery(@RequestBody final SystemProfiles searchBean) {
		List<SystemProfiles> searchResult = new ArrayList<>();
		try {
			searchResult = otdcloseService.sysPflExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"error in method sysPflExecuteQuery : ", e);
		}
		return searchResult;
	}

	/**
	 * getting cgfkOffTxnPayeeCode LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdclose/getRegBal", method = RequestMethod.GET)
	public BigDecimal getRegBal(@RequestParam(value = "offenderId") final Long offenderId,
			@RequestParam(value = "caseloadId") final String caseloadId) {
		BigDecimal returnObj = new BigDecimal(0);
		try {
			returnObj = otdcloseService.getRegBal(offenderId, caseloadId);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"error in method getRegBal : ", e);
		}
		return returnObj;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdclose/accountNameForValidation", method = RequestMethod.GET)
	public List<AccountCodes> accountNameForValidation() {
		List<AccountCodes> searchResult = new ArrayList<>();
		try {
			searchResult = otdcloseService.accountNameForValidation();
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"error in method accountNameForValidation : ", e);
		}
		return searchResult;
	}

	/**
	 * getting cgfkOffTxnPayeeCode LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdclose/accountClosedFlagValidation", method = RequestMethod.GET)
	public String accountClosedFlagValidation(@RequestParam(value = "offenderId") final Long offenderId,
			@RequestParam(value = "caseloadId") final String caseloadId) {
		String returnObj = null;
		try {
			returnObj = otdcloseService.accountClosedFlagValidation(offenderId, caseloadId);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"error in method accountClosedFlagValidation : ", e);
		}
		return returnObj;
	}

	/**
	 * getting cgfkOffTxnPayeeCode LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdclose/chkAccountClosedFlag", method = RequestMethod.GET)
	public BigDecimal chkAccountClosedFlag(@RequestParam(value = "offenderId") final Long offenderId,
			@RequestParam(value = "caseloadId") final String caseloadId) {
		BigDecimal returnObj = null;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();

		try {
			returnObj = otdcloseService.chkAccountClosedFlag(offenderId, caseloadId,userName);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"error in method chkAccountClosedFlag : ", e);
		}
		return returnObj;
	}

	/**
	 * getting cgfkOffTxnPayeeCode LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdclose/chkSubAccountFlag", method = RequestMethod.GET)
	public BigDecimal chkSubAccountFlag(@RequestParam(value = "offenderId") final Long offenderId,
			@RequestParam(value = "caseloadId") final String caseloadId) {
		BigDecimal returnObj = null;
		try {
			returnObj = otdcloseService.chkSubAccountFlag(offenderId, caseloadId);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"error in method chkSubAccountFlag : ", e);
		}
		return returnObj;
	}

	/**
	 * getting cgfkOffTxnPayeeCode LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdclose/freezDisbursement", method = RequestMethod.GET)
	public String freezDisbursement(@RequestParam(value = "caseloadId") final String caseloadId,
			@RequestParam(value = "offenderId") final Long offenderId,
			@RequestParam(value = "pTxnType") final String pTxnType,
			@RequestParam(value = "caseloadType") final String caseloadType) {
		String returnObj = null;
		try {
			returnObj = otdcloseService.freezDisbursement(caseloadId, offenderId, pTxnType, caseloadType);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"error in method freezDisbursement : ", e);
		}
		return returnObj;
	}

}