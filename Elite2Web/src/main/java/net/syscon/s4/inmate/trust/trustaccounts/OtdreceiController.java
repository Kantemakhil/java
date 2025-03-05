package net.syscon.s4.inmate.trust.trustaccounts;

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
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.TransactionTypes;
import net.syscon.s4.im.beans.OffenderTransactions;

@EliteController
public class OtdreceiController {
	@Autowired
	private OtdreceiService otdreceiService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OtdreceiController.class.getName());

	/**
	 * getting cgfkOffTxn1TxnType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdrecei/cgfkOffTxn1TxnTypeRecordGroup", method = RequestMethod.GET)
	public List<TransactionTypes> cgfkOffTxn1TxnTypeRecordGroup(
			@RequestParam(value = "caseloadId") final String caseloadId) {
		List<TransactionTypes> recordList = new ArrayList<TransactionTypes>();
		try {
			recordList = otdreceiService.cgfkOffTxn1TxnTypeRecordGroup(caseloadId);
		} catch (Exception e) {
			TransactionTypes obj = new TransactionTypes();

			logger.error(this.getClass().getName()+"OtdreceiController  cgfkOffTxn1TxnTypeRecordGroup issue in service : ", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdrecei/offTxn1ExecuteQuery", method = RequestMethod.POST)
	public List<OffenderTransactions> offTxn1ExecuteQuery(@RequestBody OffenderTransactions searchBean) {
		List<OffenderTransactions> searchResult = new ArrayList<>();
		try {
			searchResult = otdreceiService.offTxn1ExecuteQuery(searchBean);
		} catch (Exception e) {
			OffenderTransactions bean = new OffenderTransactions();
			logger.error(this.getClass().getName()+"OtdreceiController offTxn1ExecuteQuery  issue in service : ", e);
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
	@RequestMapping(value = "/otdrecei/offTxn1Commit", method = RequestMethod.POST)
	public @ResponseBody List<OffenderTransactions> offTxn1Commit(
			@RequestBody OffenderTransactionsCommitBean commitBean) {
		List<OffenderTransactions> liReturn = new ArrayList<OffenderTransactions>();
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = otdreceiService.offTxn1Commit(commitBean);
		} catch (Exception e) {
			OffenderTransactions errorBean = new OffenderTransactions();
			errorBean.setErrorMessage(e.getMessage());
			liReturn.add(errorBean);
			logger.error(this.getClass().getName()+"Exception occured in offTxn1Commit ", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdrecei/offTxnExecuteQuery", method = RequestMethod.POST)
	public List<OffenderTransactions> offTxnExecuteQuery(@RequestBody OffenderTransactions searchBean) {
		List<OffenderTransactions> searchResult = new ArrayList<>();
		try {
			searchResult = otdreceiService.offTxn1ExecuteQuery(searchBean);
		} catch (Exception e) {
			OffenderTransactions bean = new OffenderTransactions();
			logger.error(this.getClass().getName()+"OtdreceiController offTxnExecuteQuery  issue in service : ", e);
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
	@RequestMapping(value = "/otdrecei/offTxnCommit", method = RequestMethod.POST)
	public @ResponseBody Integer offTxnCommit(@RequestBody OffenderTransactionsCommitBean commitBean) {
		int liReturn = 0;
		try {
			liReturn = otdreceiService.offTxnCommit(commitBean);
		} catch (Exception e) {

			logger.error(this.getClass().getName()+"OtdreceiController  issue in service : ", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdrecei/sysPflExecuteQuery", method = RequestMethod.POST)
	public List<SystemProfiles> sysPflExecuteQuery(@RequestBody SystemProfiles searchBean) {
		List<SystemProfiles> searchResult = new ArrayList<>();
		try {
			searchResult = otdreceiService.sysPflExecuteQuery(searchBean);
		} catch (Exception e) {
			SystemProfiles bean = new SystemProfiles();
			logger.error(this.getClass().getName()+"OtdreceiController sysPflExecuteQuery issue in service : ", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdrecei/otdreceiChkReceiptFlag", method = RequestMethod.GET)
	public String otdreceiChkReceiptFlag(@RequestParam(value = "txnType") final String txnType,
			@RequestParam(value = "caseloadId") final String caseloadId) {
		try {
			return otdreceiService.otdreceiChkReceiptFlag(txnType, caseloadId);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"Error in otdreceiChkReceiptFlag : ", e);
		}
		return "N";
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdrecei/deductionsChkOffenderDeductions", method = RequestMethod.GET)
	public String deductionsChkOffenderDeductions(@RequestParam(value = "caseloadId") final String caseloadId,
			@RequestParam(value = "offenderId") final Long offenderId,
			@RequestParam(value = "txnType") final String txnType,
			@RequestParam(value = "shadowId") final Integer shadowId) {
		try {
			return otdreceiService.deductionsChkOffenderDeductions(caseloadId, offenderId, txnType, shadowId);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"Error in deductionsChkOffenderDeductions : ", e);
		}
		return "N";
	}

}