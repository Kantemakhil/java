package net.syscon.s4.inmate.trust.checks.checksmainataince;

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
import net.syscon.s4.im.beans.BankChequeBooks;
import net.syscon.s4.im.beans.BankChequeBooksCommitBean;
import net.syscon.s4.im.beans.BankChequeRegistersCommitBean;
import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.inmate.beans.BankChequeRegisters;

/**
 * class OtmcprinController
 * 
 */
@EliteController
public class OtmcprinController {
	@Autowired
	private OtmcprinService otmcprinService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OtmcprinController.class.getName());

	/**
	 * getting cgfkBankCrChequeStatus LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otmcprin/cgfkBankCrChequeStatusRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> cgfkBankCrChequeStatusRecordGroup(@RequestParam (value= "chequeStatus") final String chequeStatus) {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = otmcprinService.cgfkBankCrChequeStatusRecordGroup(chequeStatus);
		} catch (Exception e) {
			ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception :", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting cgfkBankCrCheqStatusVoid LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otmcprin/cgfkBankCrCheqStatusVoidRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> cgfkBankCrCheqStatusVoidRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = otmcprinService.cgfkBankCrCheqStatusVoidRecordGroup();
		} catch (Exception e) {
			ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception :", e);
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
	@RequestMapping(value = "/otmcprin/bankCbExecuteQuery", method = RequestMethod.POST)
	public List<BankChequeBooks> bankCbExecuteQuery(@RequestBody BankChequeBooks searchBean) {
		List<BankChequeBooks> searchResult = new ArrayList<>();
		try {
			searchResult = otmcprinService.bankCbExecuteQuery(searchBean);
		} catch (Exception e) {
			BankChequeBooks bean = new BankChequeBooks();
			logger.error("Exception :", e);
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
	@RequestMapping(value = "/otmcprin/bankCbCommit", method = RequestMethod.POST)
	public @ResponseBody Integer bankCbCommit(@RequestBody BankChequeBooksCommitBean commitBean) {
		int liReturn = 0;
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = otmcprinService.bankCbCommit(commitBean);
		} catch (Exception e) {

			logger.error("Exception :", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otmcprin/bankCrExecuteQuery", method = RequestMethod.POST)
	public List<BankChequeRegisters> bankCrExecuteQuery(@RequestBody BankChequeRegisters searchBean) {
		List<BankChequeRegisters> searchResult = new ArrayList<>();
		try {
			searchResult = otmcprinService.bankCrExecuteQuery(searchBean);
		} catch (Exception e) {
			BankChequeRegisters bean = new BankChequeRegisters();
			logger.error("Exception :", e);
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
	@RequestMapping(value = "/otmcprin/bankCrCommit", method = RequestMethod.POST)
	public @ResponseBody Integer bankCrCommit(@RequestBody BankChequeRegistersCommitBean commitBean) {
		int liReturn = 0;
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = otmcprinService.bankCrCommit(commitBean);
		} catch (Exception e) {

			logger.error("Exception :", e);
		}
		return liReturn;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otmcprin/checkIfNewSeries", method = RequestMethod.POST)
	public List<String> checkIfNewSeries(@RequestBody BankChequeBooks searchBean) {
		List<String> searchResult = new ArrayList<String>();
		try {
			searchResult = otmcprinService.checkIfNewSeries(searchBean);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return searchResult;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otmcprin/bcRowMaxChecqueNumber", method = RequestMethod.GET)
	public Long bcRowMaxChecqueNumber(@RequestParam(value = "caseloadId") final String caseloadId,
			@RequestParam(value = "accountCode") final Long accountCode,
			@RequestParam(value = "firstCheckNumber") final String firstCheckNumber,
			@RequestParam(value = "lastCheckNumber") final String lastCheckNumber) {
		Long maxChequeNum = null;
		try {
			maxChequeNum = otmcprinService.bcRowMaxChecqueNumber(caseloadId, accountCode, firstCheckNumber,
					lastCheckNumber);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return maxChequeNum;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otmcprin/isTransactionReversed", method = RequestMethod.POST)
	public String  isTransactionReversed(@RequestParam(value = "txnId") final Long txnId,
			@RequestParam(value = "accountCode") final Long accountCode) {
		String  lvTxnReversedFlg = null;
		try {
			lvTxnReversedFlg = otmcprinService.isTransactionReversed(txnId,accountCode);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return lvTxnReversedFlg;
	}
}