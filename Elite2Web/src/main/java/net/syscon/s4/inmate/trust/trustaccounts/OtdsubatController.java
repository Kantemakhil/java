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
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.im.beans.TransactionOperation;

/**
 * Class OtdsubatController
 */
@EliteController
public class OtdsubatController {
	@Autowired
	private OtdsubatService otdsubatService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OtdsubatController.class.getName());

	/**
	 * getting cgfkOffTxn2SubAccountType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdsubat/cgfkOffTxn2SubAccountTypeRecordGroup", method = RequestMethod.GET)
	public List<TransactionOperation> cgfkOffTxn2SubAccountTypeRecordGroup(@RequestParam(value="caseLoadId") final String caseLoadId) {
		List<TransactionOperation> recordList = new ArrayList<TransactionOperation>();
		try {
			recordList = otdsubatService.cgfkOffTxn2SubAccountTypeRecordGroup(caseLoadId);
		} catch (Exception e) {
			logger.error("The Exception in cgfkOffTxn2SubAccountTypeRecordGroup:",e);
		}
		return recordList;
	}

	/**
	 * getting cgfkOffTxnSubAccountType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdsubat/cgfkOffTxnSubAccountTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> cgfkOffTxnSubAccountTypeRecordGroup(@RequestParam(value="fromSubAccount") final String fromSubAccount,
			@RequestParam(value="caseLoadId") final String caseLoadId) {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = otdsubatService.cgfkOffTxnSubAccountTypeRecordGroup(fromSubAccount,caseLoadId);
		} catch (Exception e) {
			logger.error("The Exception in cgfkOffTxnSubAccountTypeRecordGroup:", e);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdsubat/offTxn2ExecuteQuery", method = RequestMethod.POST)
	public List<OffenderTransactions> offTxn2ExecuteQuery(@RequestBody final OffenderTransactions searchBean) {
		List<OffenderTransactions> searchResult = new ArrayList<>();
		try {
			searchResult = otdsubatService.offTxn2ExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("The Exception in offTxn2ExecuteQuery:", e);
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
	@RequestMapping(value = "/otdsubat/offTxn2Commit", method = RequestMethod.POST)
	public @ResponseBody List<OffenderTransactions> offTxn2Commit(@RequestBody final OffenderTransactionsCommitBean commitBean) {
		List<OffenderTransactions> liReturn = new ArrayList<OffenderTransactions>();
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			liReturn = otdsubatService.offTxn2Commit(commitBean,userName);
		} catch (Exception e) {
			logger.error("The Exception in offTxn2Commit:", e);
		}
		return liReturn;
	}
//
//	/**
//	 * Fetching the record from database table
//	 * 
//	 * @Param searchRecord
//	 */
//	@PreAuthorize("hasEliteRole('read')")
//	@RequestMapping(value = "/otdsubat/offTxnExecuteQuery", method = RequestMethod.POST)
//	public List<OffenderTransactions> offTxnExecuteQuery(@RequestBody OffenderTransactions searchBean) {
//		List<OffenderTransactions> searchResult = new ArrayList<>();
//		try {
//			searchResult = otdsubatService.offTxnExecuteQuery(searchBean);
//		} catch (Exception e) {
//			logger.error(e);
//		}
//		return searchResult;
//	}

//	/**
//	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
//	 * database table
//	 * 
//	 * @Param commitBean
//	 */
//	@PreAuthorize("hasEliteRole('full')")
//	@RequestMapping(value = "/otdsubat/offTxnCommit", method = RequestMethod.POST)
//	public @ResponseBody Integer offTxnCommit(@RequestBody OffenderTransactionsCommitBean commitBean) {
//		int liReturn = 0;
//		try {
//			liReturn = otdsubatService.offTxnCommit(commitBean);
//		} catch (Exception e) {
//			logger.error(e);
//		}
//		return liReturn;
//	}
	@RequestMapping(value = "/otdsubat/getDescription", method = RequestMethod.GET)
	public String getDescription(@RequestParam(value="caseloadType") final String caseloadType,
			@RequestParam(value="txnType") final String txnType) {
		String recordList = "";
		try {
			recordList = otdsubatService.getDescription(caseloadType,txnType);
		} catch (Exception e) {
			logger.error("The Exception in cgfkOffTxnSubAccountTypeRecordGroup:", e);
		}
		return recordList;
	}
//	otdsubat/getacCode
	@RequestMapping(value = "/otdsubat/getacCode", method = RequestMethod.GET)
	public String getacCode(@RequestParam(value="code") final String code,
			@RequestParam(value="caseloadType") final String caseloadType)
			 {
		String recordList = "";
		try {
			recordList = otdsubatService.getacCode(code,caseloadType);
		} catch (Exception e) {
			logger.error("The Exception in cgfkOffTxnSubAccountTypeRecordGroup:", e);
		}
		return recordList;
	}
	@RequestMapping(value = "/otdsubat/getBal", method = RequestMethod.GET)
	public String getBal(@RequestParam(value="offenderId") final String offenderId,
			@RequestParam(value="caseloadId") final String caseloadId,
			@RequestParam(value="acCode") final String acCode)
			 {
		String recordList = "";
		try {
			recordList = otdsubatService.getBal(offenderId,caseloadId,acCode);
		} catch (Exception e) {
			logger.error("The Exception in cgfkOffTxnSubAccountTypeRecordGroup:", e);
		}
		return recordList;
	}
	@RequestMapping(value = "/otdsubat/checkAcntClose", method = RequestMethod.GET)
	public String getAcClosedFlg(@RequestParam(value="offenderId") final Long offenderId,
			@RequestParam(value="caseloadId") final String caseloadId)
			 {
		String recordList = "";
		try {
			recordList = otdsubatService.getAcClosedFlg(offenderId,caseloadId);
		} catch (Exception e) {
			logger.error("The Exception in cgfkOffTxnSubAccountTypeRecordGroup:", e);
		}
		return recordList;
	}
}