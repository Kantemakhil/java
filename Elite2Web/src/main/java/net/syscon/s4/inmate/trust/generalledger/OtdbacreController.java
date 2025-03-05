package net.syscon.s4.inmate.trust.generalledger;

import java.math.BigDecimal;
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
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.SystemProfilesCommitBean;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.inmate.beans.BankClearReconcilesTmp;
import net.syscon.s4.inmate.beans.BankClearReconcilesTmpCommitBean;
import net.syscon.s4.inmate.beans.BankReconAudits;
import net.syscon.s4.inmate.beans.GlTransactions;
import net.syscon.s4.inmate.beans.GlTransactionsCommitBean;

/**
 * Class OtdbacreController
 */
@EliteController
public class OtdbacreController {
	@Autowired
	private OtdbacreService otdbacreService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OtdbacreController.class.getName());

	/**
	 * getting cgfkGlTxnAccountCode LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdbacre/cgfkGlTxnAccountCodeRecordGroup", method = RequestMethod.GET)
	public List<AccountCodes> cgfkGlTxnAccountCodeRecordGroup(
			@RequestParam(value = "caseloadId") final String caseloadId,
			@RequestParam(value = "caseloadType") final String caseloadType) {
		List<AccountCodes> recordList = new ArrayList<AccountCodes>();
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			recordList = otdbacreService.cgfkGlTxnAccountCodeRecordGroup(caseloadId, caseloadType,userName);
		} catch (Exception e) {
			final AccountCodes obj = new AccountCodes();
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
	@RequestMapping(value = "/otdbacre/glTxnExecuteQuery", method = RequestMethod.POST)
	public List<GlTransactions> glTxnExecuteQuery(@RequestBody final GlTransactions searchBean) {
		List<GlTransactions> searchResult = new ArrayList<>();
		try {
			searchResult = otdbacreService.glTxnExecuteQuery(searchBean);
		} catch (Exception e) {
			final GlTransactions bean = new GlTransactions();
			logger.error(e);
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
	@RequestMapping(value = "/otdbacre/glTxnCommit", method = RequestMethod.POST)
	public @ResponseBody Integer glTxnCommit(@RequestBody final GlTransactionsCommitBean commitBean) {
		int liReturn = 0;
		try {
			liReturn = otdbacreService.glTxnCommit(commitBean);
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
	@RequestMapping(value = "/otdbacre/bankRcExecuteQuery", method = RequestMethod.POST)
	public List<BankReconAudits> bankRcExecuteQuery(@RequestBody final BankReconAudits searchBean) {
		List<BankReconAudits> searchResult = new ArrayList<>();
		try {
			searchResult = otdbacreService.bankRcExecuteQuery(searchBean);
		} catch (Exception e) {
			final BankReconAudits bean = new BankReconAudits();
			logger.error(e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdbacre/sysPflExecuteQuery", method = RequestMethod.POST)
	public List<SystemProfiles> sysPflExecuteQuery(@RequestBody final SystemProfiles searchBean) {
		List<SystemProfiles> searchResult = new ArrayList<>();
		try {
			searchResult = otdbacreService.sysPflExecuteQuery(searchBean);
		} catch (Exception e) {
			final SystemProfiles bean = new SystemProfiles();
			logger.error(e);
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
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdbacre/sysPflCommit", method = RequestMethod.POST)
	public @ResponseBody Integer sysPflCommit(@RequestBody final SystemProfilesCommitBean commitBean) {
		int liReturn = 0;
		try {
			liReturn = otdbacreService.sysPflCommit(commitBean);
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
	@RequestMapping(value = "/otdbacre/bcrTmpExecuteQuery", method = RequestMethod.POST)
	public List<BankClearReconcilesTmp> bcrTmpExecuteQuery(@RequestBody final BankClearReconcilesTmp searchBean) {
		List<BankClearReconcilesTmp> searchResult = new ArrayList<>();
		try {
				if (searchBean != null) {
					String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
					searchBean.setCreateUserId(userName);
				}
			searchResult = otdbacreService.bcrTmpExecuteQuery(searchBean);
		} catch (Exception e) {
			final BankClearReconcilesTmp bean = new BankClearReconcilesTmp();
			logger.error(e);
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
	@RequestMapping(value = "/otdbacre/bcrTmpCommit", method = RequestMethod.POST)
	public @ResponseBody Integer bcrTmpCommit(@RequestBody final BankClearReconcilesTmpCommitBean commitBean) {
		int liReturn = 0;
		try {
			liReturn = otdbacreService.bcrTmpCommit(commitBean);
		} catch (Exception e) {

			logger.error(e);
		}
		return liReturn;
	}

	// /**
	// *Fetching the record from database table
	// *@Param searchRecord
	// */
	// @PreAuthorize("hasEliteRole('read')")
	// @RequestMapping(value="/otdbacre/glTxn1ExecuteQuery",
	// method=RequestMethod.POST)
	// public List<GlTransactions> glTxn1ExecuteQuery(@RequestBody
	// GlTransactions searchBean) {
	// List<GlTransactions> searchResult = new ArrayList<>();
	// try {
	// searchResult = otdbacreService.glTxn1ExecuteQuery(searchBean);
	// } catch (Exception e) {
	// GlTransactions bean = new GlTransactions();
	// logger.error(e);
	// searchResult.add(bean);
	// }
	// return searchResult;
	// }

	// /**
	// *Fetching the record from database table
	// *@Param searchRecord
	// */
	// @PreAuthorize("hasEliteRole('read')")
	// @RequestMapping(value="/otdbacre/glTxn2ExecuteQuery",
	// method=RequestMethod.POST)
	// public List<GlTransactions> glTxn2ExecuteQuery(@RequestBody
	// GlTransactions searchBean) {
	// List<GlTransactions> searchResult = new ArrayList<>();
	// try {
	// searchResult = otdbacreService.glTxn2ExecuteQuery(searchBean);
	// } catch (Exception e) {
	// GlTransactions bean = new GlTransactions();
	// logger.error(e);
	// searchResult.add(bean);
	// }
	// return searchResult;
	// }

	// /**
	// *Fetching the record from database table
	// *@Param searchRecord
	// */
	// @PreAuthorize("hasEliteRole('read')")
	// @RequestMapping(value="/otdbacre/glTxn3ExecuteQuery",
	// method=RequestMethod.POST)
	// public List<GlTransactions> glTxn3ExecuteQuery(@RequestBody
	// GlTransactions searchBean) {
	// List<GlTransactions> searchResult = new ArrayList<>();
	// try {
	// searchResult = otdbacreService.glTxn3ExecuteQuery(searchBean);
	// } catch (Exception e) {
	// GlTransactions bean = new GlTransactions();
	// logger.error(e);
	// searchResult.add(bean);
	// }
	// return searchResult;
	// }
	/**
	 * Fetching the record from database table
	 * 
	 * @Params caseloadId ,accCode
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdbacre/getPmaxDate", method = RequestMethod.GET)
	public BankReconAudits getPmaxDate(@RequestParam(value = "caseloadId") final String caseloadId,
			@RequestParam(value = "accCode") final Integer accCode) {
		BankReconAudits searchResult = new BankReconAudits();
		try {
			searchResult = otdbacreService.getPmaxDate(caseloadId, accCode);
		} catch (Exception e) {
			logger.error(e);
		}
		return searchResult;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdbacre/compareEffectiveDate", method = RequestMethod.GET)
	public Integer compareEffectiveDate(@RequestParam(value = "effDate") final String effDate,
			@RequestParam(value = "maxDate") final String maxDate) {
		Integer searchResult = null;
		try {
			searchResult = otdbacreService.compareEffectiveDatec(effDate, maxDate);
		} catch (Exception e) {
		}
		return searchResult;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdbacre/getchcqueFlag", method = RequestMethod.GET)
	public String getchcqueFlag(@RequestParam(value = "txnId") final Long txnId,
			@RequestParam(value = "txnEnterySeq") final String txnEnterySeq,
			@RequestParam(value = "glEntrySeq") final String glEntrySeq,
			@RequestParam(value = "cgnbtBankStatementDate") final Long cgnbtBankStatementDate) {
		String searchResult = null;
		try {
			searchResult = otdbacreService.getchcqueFlag(txnId, txnEnterySeq, glEntrySeq, cgnbtBankStatementDate);
		} catch (Exception e) {
		}
		return searchResult;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdbacre/updateGlTransactionswithN", method = RequestMethod.GET)
	public Integer updateGlTransactionswithN(@RequestParam(value = "txnId") final Long txnId) {
		Integer searchResult = null;
		try {
			searchResult = otdbacreService.updateGlTransactionswithN(txnId);
		} catch (Exception e) {
		}
		return searchResult;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdbacre/updateBankReconAudits", method = RequestMethod.POST)
	public Integer updateBankReconAudits(@RequestBody final BankReconAudits searchBean) {
		Integer searchResult = null;
		try {
			searchResult = otdbacreService.updateBankReconAudits(searchBean);
		} catch (Exception e) {
		}
		return searchResult;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdbacre/insertBankReconAudits", method = RequestMethod.POST)
	public Integer insertBankReconAudits(@RequestBody final BankReconAudits searchBean) {
		Integer searchResult = null;
		if (searchBean != null) {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			searchBean.setCreateUserId(userName);
		}
		try {
			searchResult = otdbacreService.insertBankReconAudits(searchBean);
		} catch (Exception e) {
		}
		return searchResult;
	}
	
	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdbacre/getTrustBal", method = RequestMethod.GET)
	public BigDecimal getTrustBal(@RequestParam(value = "accountCode") final Long accountCode,
			@RequestParam(value = "caseloadId") final String caseloadId) {
		BigDecimal searchResult = null;
		try {
			searchResult = otdbacreService.getTrustBal(accountCode,caseloadId);
		} catch (Exception e) {
		}
		return searchResult;
	}
}