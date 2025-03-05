package net.syscon.s4.inmate.trust.trustaccounts;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

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
import net.syscon.s4.common.beans.TransactionTypes;
import net.syscon.s4.im.beans.ChkFreezeDisbursements;
import net.syscon.s4.im.beans.OffenderTransactions;

/**
 * Class OtddisbuController
 */
@EliteController
public class OtddisbuController {
	@Autowired
	private OtddisbuService otddisbuService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OtddisbuController.class.getName());

	/**
	 * getting cgfkOffTxn1PayeePersonId LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otddisbu/cgfkOffTxn1PayeePersonIdRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> cgfkOffTxn1PayeePersonIdRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = otddisbuService.cgfkOffTxn1PayeePersonIdRecordGroup();
		} catch (Exception e) {
			logger.error(e);
		}
		return recordList;
	}

	/**
	 * getting cgfkOffTxn1TxnType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otddisbu/cgfkOffTxn1TxnTypeRecordGroup", method = RequestMethod.GET)
	public List<TransactionTypes> cgfkOffTxn1TxnTypeRecordGroup(
			@RequestParam(value = "caseloadId") final String caseloadId,
			@RequestParam(value = "caseloadType") final String caseloadType) {
		List<TransactionTypes> recordList = new ArrayList<TransactionTypes>();
		try {
			recordList = otddisbuService.cgfkOffTxn1TxnTypeRecordGroup(caseloadId, caseloadType);
		} catch (Exception e) {
			logger.error(e);
		}
		return recordList;
	}

	/**
	 * getting cgfkOffTxn1TxnType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otddisbu/checkproductionFlag", method = RequestMethod.GET)
	public String checkproductionFlag(@RequestParam(value = "caseloadId") final String caseloadId,
			@RequestParam(value = "txCode") final String txCode) {
		String recordList = "";
		try {
			recordList = otddisbuService.checkproductionFlag(caseloadId, txCode);
		} catch (Exception e) {
			logger.error(e);
		}
		return recordList;
	}

	// /**
	// *getting cgfkOffTxn1PayeeCorporate LOV values
	// */
	// @PreAuthorize("hasEliteRole('read')")
	// @RequestMapping(value="/otddisbu/cgfkOffTxn1PayeeCorporateRecordGroup",method=RequestMethod.GET)
	// public List<> cgfkOffTxn1PayeeCorporateRecordGroup() {
	// List<> recordList =new ArrayList<>();
	// try {
	// recordList = otddisbuService.cgfkOffTxn1PayeeCorporateRecordGroup();
	// } catch(Exception e){
	// logger.error(e);
	// }
	// return recordList;
	// }

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otddisbu/offTxn1ExecuteQuery", method = RequestMethod.POST)
	public List<OffenderTransactions> offTxn1ExecuteQuery(@RequestBody final OffenderTransactions searchBean) {
		List<OffenderTransactions> searchResult = new ArrayList<>();
		try {
			searchResult = otddisbuService.offTxn1ExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error(e);
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
	@RequestMapping(value = "/otddisbu/offTxn1Commit", method = RequestMethod.POST)
	public @ResponseBody List<OffenderTransactions> offTxn1Commit(@RequestBody final OffenderTransactionsCommitBean commitBean) {
		List<OffenderTransactions> liReturn = null;
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = otddisbuService.offTxn1Commit(commitBean);
		} catch (Exception e) {
			liReturn = Collections.emptyList();
			logger.error("Otddisbu Unable to Call offTxn1Commit Serveice", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otddisbu/offTxnExecuteQuery", method = RequestMethod.POST)
	public List<OffenderTransactions> offTxnExecuteQuery(@RequestBody final OffenderTransactions searchBean) {
		List<OffenderTransactions> searchResult = new ArrayList<>();
		try {
			searchResult = otddisbuService.offTxnExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error(e);
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
	@RequestMapping(value = "/otddisbu/offTxnCommit", method = RequestMethod.POST)
	public @ResponseBody Integer offTxnCommit(@RequestBody final OffenderTransactionsCommitBean commitBean) {
		int liReturn = 0;
		try {
			liReturn = otddisbuService.offTxnCommit(commitBean);
		} catch (Exception e) {

			logger.error(e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otddisbu/sysPflExecuteQuery", method = RequestMethod.POST)
	public List<SystemProfiles> sysPflExecuteQuery(@RequestBody final SystemProfiles searchBean) {
		List<SystemProfiles> searchResult = new ArrayList<>();
		try {
			searchResult = otddisbuService.sysPflExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error(e);
		}
		return searchResult;
	}
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otddisbu/getOffenderSependableBalance", method = RequestMethod.GET)
	public BigDecimal getOffenderSependableBalance(@RequestParam(value="offenderId")final Long offenderId, @RequestParam(value="caseloadId")final String caseloadId,
													@RequestParam(value="txnType")final String txnType) {
		try {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			return otddisbuService.getOffenderSependableBalance(offenderId, caseloadId, txnType,userName);
		}catch (Exception e) {
			logger.error("getOffenderSependableBalance", e);
		}
		return null;
		
	}
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otddisbu/getCreditEligibility", method = RequestMethod.POST)
	public Map<String, Object> getCreditEligibility(@RequestBody OffenderTransactions param) {
		try {
			if (param != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				param.setCreateUserId(userName);
			}
			return otddisbuService.getCreditEligibility(param);
		}catch (Exception e) {
			logger.error("getCreditEligibility", e);
		}
		return null;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otddisbu/getVProValue", method = RequestMethod.GET)
	public String getVProValue() {
		try {
			return otddisbuService.getVProValue();
		}catch (Exception e) {
			logger.error("OtddisbuController getVProValue in calling Business Service : ", e);
		}
		return null;
	}
	
//	chk_disbursement_freeze
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otddisbu/chkDisbursementFreeze", method = RequestMethod.POST)
	public ChkFreezeDisbursements chkDisbursementFreeze(@RequestBody final ChkFreezeDisbursements chkFreezeDisbursements) {
		ChkFreezeDisbursements retrunObj = new ChkFreezeDisbursements();
		try{	
			retrunObj = otddisbuService.chkDisbursementFreeze(chkFreezeDisbursements);
		} catch(Exception e) {
			logger.error("Error in chkDisbursementFreeze", e);
			retrunObj.setErrorMessage(e.getMessage());
		}
		return retrunObj;
		}

}