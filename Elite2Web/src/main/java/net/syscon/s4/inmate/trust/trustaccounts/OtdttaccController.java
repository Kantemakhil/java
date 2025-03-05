package net.syscon.s4.inmate.trust.trustaccounts;

import java.util.ArrayList;
import java.util.Date;
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
import net.syscon.s4.common.beans.Caseloads;
import net.syscon.s4.common.beans.OffenderTransactionsCommitBean;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.TransactionTypes;
import net.syscon.s4.im.beans.Corporates;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.inmate.beans.AccountPeriods;
import net.syscon.s4.inmate.beans.AccountPeriodsCommitBean;
import net.syscon.s4.inmate.beans.CaseloadTransactionTypes;
import net.syscon.s4.inmate.beans.CaseloadTransactionTypesCommitBean;
import net.syscon.s4.inmate.beans.OffenderSubAccounts;

/**
 * Class OtdttaccController
 */
@EliteController
public class OtdttaccController {
	@Autowired
	private OtdttaccService otdttaccService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OtdttaccController.class.getName());

	/**
	 * getting cgfkCsldTtTxnType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdttacc/cgfkCsldTtTxnTypeRecordGroup", method = RequestMethod.GET)
	public List<TransactionTypes> cgfkCsldTtTxnTypeRecordGroup(
			@RequestParam(value = "caseLoadId") final String caseLoadId) {
		List<TransactionTypes> recordList = new ArrayList<TransactionTypes>();
		try {
			recordList = otdttaccService.cgfkCsldTtTxnTypeRecordGroup(caseLoadId);
		} catch (Exception e) {
			final TransactionTypes obj = new TransactionTypes();
			logger.error(e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting cgfkCsldTtCaseloadId LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdttacc/cgfkCsldTtCaseloadIdRecordGroup", method = RequestMethod.GET)
	public List<Caseloads> cgfkCsldTtCaseloadIdRecordGroup(
			@RequestParam(value = "caseLoadId") final String caseLoadId) {
		List<Caseloads> recordList = new ArrayList<Caseloads>();
		try {
			recordList = otdttaccService.cgfkCsldTtCaseloadIdRecordGroup(caseLoadId);
		} catch (Exception e) {
			final Caseloads obj = new Caseloads();
			logger.error(e);
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
	@RequestMapping(value = "/otdttacc/csldTtExecuteQuery", method = RequestMethod.POST)
	public List<CaseloadTransactionTypes> csldTtExecuteQuery(@RequestBody final CaseloadTransactionTypes searchBean) {
		List<CaseloadTransactionTypes> searchResult = new ArrayList<>();
		try {
			searchResult = otdttaccService.csldTtExecuteQuery(searchBean);
		} catch (Exception e) {
			final CaseloadTransactionTypes bean = new CaseloadTransactionTypes();
			logger.error(e);
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 * Performing basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdttacc/csldTtCommit", method = RequestMethod.POST)
	public @ResponseBody Integer csldTtCommit(@RequestBody final CaseloadTransactionTypesCommitBean commitBean) {
		int liReturn = 0;
		try {
			liReturn = otdttaccService.csldTtCommit(commitBean);
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
	@RequestMapping(value = "/otdttacc/acPrdExecuteQuery", method = RequestMethod.POST)
	public List<AccountPeriods> acPrdExecuteQuery(@RequestBody final AccountPeriods searchBean) {
		List<AccountPeriods> searchResult = new ArrayList<>();
		try {
			searchResult = otdttaccService.acPrdExecuteQuery(searchBean);
		} catch (Exception e) {
			final AccountPeriods bean = new AccountPeriods();
			logger.error(e);
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 * Performing basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdttacc/acPrdCommit", method = RequestMethod.POST)
	public @ResponseBody Integer acPrdCommit(@RequestBody final AccountPeriodsCommitBean commitBean) {
		int liReturn = 0;
		try {
			liReturn = otdttaccService.acPrdCommit(commitBean);
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
	@RequestMapping(value = "/otdttacc/offTxnExecuteQuery", method = RequestMethod.POST)
	public List<OffenderTransactions> offTxnExecuteQuery(@RequestBody final OffenderTransactions searchBean) {
		List<OffenderTransactions> searchResult = new ArrayList<>();
		try {
			searchResult = otdttaccService.offTxnExecuteQuery(searchBean);
		} catch (Exception e) {
			final OffenderTransactions bean = new OffenderTransactions();
			logger.error(e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 * Performing basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdttacc/offTxnCommit", method = RequestMethod.POST)
	public  List<OffenderTransactions> offTxnCommit(@RequestBody final OffenderTransactionsCommitBean commitBean) {
		List<OffenderTransactions> liReturn =new ArrayList<OffenderTransactions>();
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = otdttaccService.offTxnCommit(commitBean);
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
	@RequestMapping(value = "/otdttacc/sysPflExecuteQuery", method = RequestMethod.POST)
	public List<SystemProfiles> sysPflExecuteQuery(@RequestBody final SystemProfiles searchBean) {
		List<SystemProfiles> searchResult = new ArrayList<>();
		try {
			searchResult = otdttaccService.sysPflExecuteQuery(searchBean);
		} catch (Exception e) {
			final SystemProfiles bean = new SystemProfiles();
			logger.error(e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdttacc/getHoldClearFlag", method = RequestMethod.GET)
	public String getHoldClearFlag(@RequestParam(value = "caseloadId") final String caseloadId,
			@RequestParam(value = "offenderId") final Long  offenderId,
			@RequestParam(value = "casaeloadType") final String casaeloadType) {
		String flag = null;
		try {
			flag = otdttaccService.getHoldClearFlag(caseloadId, offenderId, casaeloadType);
		} catch (Exception e) {
			logger.error(e);
		}
		return flag;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdttacc/getHoldBal", method = RequestMethod.GET)
	public List<OffenderSubAccounts> getHoldBal(@RequestParam(value = "caseloadId") final String caseloadId,
			@RequestParam(value = "offenderId") final Long offenderId,
			@RequestParam(value = "txnType") final String txnType) {
		List<OffenderSubAccounts> returnList = new ArrayList<OffenderSubAccounts>();
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		try {
			returnList = otdttaccService.getHoldBal(caseloadId, offenderId, txnType, userName);
		} catch (Exception e) {
			logger.error(e);
		}
		return returnList;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdttacc/getDuplicateOffenders", method = RequestMethod.GET)
	public String getDuplicateOffenders(@RequestParam(value = "offenderId") final Long offenderId) {
		String dFlag = null;
		try {
			dFlag = otdttaccService.getDuplicateOffenders(offenderId);
		} catch (Exception e) {
			logger.error(e);
		}
		return dFlag;
	}
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdttacc/getCorporateidNames", method = RequestMethod.GET)
	public List<Corporates> getCorporateidNames(@RequestParam(value = "toCaseload") final String toCaseload
			) {
		List<Corporates> returnList = new ArrayList<Corporates>();
		try {

			returnList = otdttaccService.getCorporateidNames(toCaseload);
		} catch (Exception e) {
			logger.error(e);
		}
		return returnList;
	}
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdttacc/checkTxnType", method = RequestMethod.GET)
	public Object checkTxnType(@RequestParam(value = "toCaseload") final String toCaseload,
			@RequestParam(value = "txnType") final String txnType,
			@RequestParam(value = "caseloadId") final String caseloadId) {
		Object returnList = new Object();
		try {
			returnList = otdttaccService.checkTxnType(toCaseload,txnType,caseloadId);
		} catch (Exception e) {
			logger.error(e);
		}
		return returnList;
	}
	

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdttacc/getRootOffenderId", method = RequestMethod.GET)
	public OffenderTransactions getRootOffenderId(@RequestParam(value = "casaeloadType") final String casaeloadType,
			@RequestParam(value = "offenderIdDisplay") final String offenderIdDisplay) {
		OffenderTransactions returnList = new OffenderTransactions();
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		try {
			returnList = otdttaccService.getRootOffenderId(casaeloadType,offenderIdDisplay, userName);
		} catch (Exception e) {
			logger.error(e);
		}
		return returnList;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdttacc/whenNewBlockInstanceRetrive", method = RequestMethod.GET)
	public List<OffenderTransactions> whenNewBlockInstanceRetrive(
			@RequestParam(value = "startDate") final Long startDate,
			@RequestParam(value = "endDate") final Long endDate,
			@RequestParam(value = "currentCaseload") final String currentCaseload,
			@RequestParam(value = "toCaseload") final String toCaseload,
			@RequestParam(value = "txnType") final String txnType,
			@RequestParam(value = "caseloadType") final String caseloadType) {
		List<OffenderTransactions> returnList = new ArrayList<OffenderTransactions>();
		try {
			returnList = otdttaccService.whenNewBlockInstanceRetrive(startDate,endDate,currentCaseload,toCaseload,txnType,caseloadType);
		} catch (Exception e) {
			logger.error(e);
		}
		return returnList;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdttacc/deleteOffacShads", method = RequestMethod.GET)
	public Integer deleteOffacShads(@RequestParam(value = "caseloadId") final String caseloadId,
			@RequestParam(value = "offenderId") final Long offenderId) {
		Integer returnList =0;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		try {
			returnList = otdttaccService.deleteOffacShads(caseloadId,offenderId,userName);
		} catch (Exception e) {
			logger.error(e);
		}
		return returnList;
	}
}