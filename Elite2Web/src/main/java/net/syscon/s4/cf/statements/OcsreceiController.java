package net.syscon.s4.cf.statements;

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
import net.syscon.s4.common.beans.OffenderTransactionsCommitBean;
import net.syscon.s4.common.beans.OmsRequests;
import net.syscon.s4.common.beans.OmsRequestsCommitBean;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.inmate.beans.Printers;

/**
 * Class OcsreceiController
 */
@EliteController
public class OcsreceiController {
	@Autowired
	private OcsreceiService ocsreceiService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcsreceiController.class.getName());

	/**
	 * getting cgfkOmsReqPrinterId LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocsrecei/cgfkOmsReqPrinterIdRecordGroup", method = RequestMethod.GET)
	public List<Printers> cgfkOmsReqPrinterIdRecordGroup() {
		List<Printers> recordList = new ArrayList<>();
		try {
			recordList = ocsreceiService.cgfkOmsReqPrinterIdRecordGroup();
		} catch (Exception e) {
			logger.error("Exception in  Ocsrecei cgfkOmsReqPrinterIdRecordGroup:", e);
		}
		return recordList;
	}

	/**
	 * getting cgfkOmsReqModuleName LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocsrecei/cgfkOmsReqModuleNameRecordGroup", method = RequestMethod.GET)
	public List<OmsModules> cgfkOmsReqModuleNameRecordGroup() {
		List<OmsModules> recordList = new ArrayList<>();
		try {
			recordList = ocsreceiService.cgfkOmsReqModuleNameRecordGroup();
		} catch (Exception e) {
			logger.error("Exception in Ocsrecei cgfkOmsReqModuleNameRecordGroup:", e);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocsrecei/omsReqExecuteQuery", method = RequestMethod.POST)
	public List<OmsRequests> oms_reqExecuteQuery(@RequestBody OmsRequests searchBean) {
		List<OmsRequests> searchResult = new ArrayList<>();
		try {
			searchResult = ocsreceiService.omsReqExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("Exception in ocsrecei omsReqExecuteQuery:", e);
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
	@RequestMapping(value = "/ocsrecei/omsReqCommit", method = RequestMethod.POST)
	public @ResponseBody Integer omsReqCommit(@RequestBody OmsRequestsCommitBean commitBean) {
		int liReturn = 0;
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			commitBean.setCreateUserId(userName);
			liReturn = ocsreceiService.omsReqCommit(commitBean);
		} catch (Exception e) {

			logger.error("Exception : Ocsrecei", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocsrecei/offTxnExecuteQuery", method = RequestMethod.POST)
	public List<OffenderTransactions> ofoffTxnExecuteQueryf_txnExecuteQuery(@RequestBody OmsRequests searchBean) {
		List<OffenderTransactions> searchResult = new ArrayList<>();
		try {
			searchResult = ocsreceiService.offTxnExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("Exception :", e);
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
	@RequestMapping(value = "/ocsrecei/offTxnCommit", method = RequestMethod.POST)
	public @ResponseBody List<OffenderTransactions> offTxnCommit(
			@RequestBody OffenderTransactionsCommitBean commitBean, @RequestHeader HttpHeaders headers) {
		List<OffenderTransactions> liReturn = new ArrayList<>();
		List<String> authorizationList = headers.get("Authorization");
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			commitBean.setCreateUserId(userName);
			liReturn = ocsreceiService.offTxnCommit(commitBean);
		} catch (Exception e) {

			logger.error("Exception : Ocsrecei", e);
		}
		return liReturn;
	}

}