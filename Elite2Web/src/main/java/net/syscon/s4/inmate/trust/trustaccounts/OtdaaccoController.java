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
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.SystemProfilesCommitBean;
import net.syscon.s4.inmate.beans.GlTransactions;
import net.syscon.s4.inmate.beans.GlTransactionsCommitBean;

/**
 * Class OtdaaccoController
 */
@EliteController
public class OtdaaccoController {
	@Autowired
	private OtdaaccoService otdaaccoService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OtdaaccoController.class.getName());

	/**
	 * getting cgfkGlTxn1TxnType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdaacco/cgfkGlTxn1TxnTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> cgfkGlTxn1TxnTypeRecordGroup(
			@RequestParam(value = "caseLoadId") final String caseLoadId,
			@RequestParam(value = "caseLoadType") final String caseLoadType) {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = otdaaccoService.cgfkGlTxn1TxnTypeRecordGroup(caseLoadId, caseLoadType);
		} catch (Exception e) {
			logger.error("cgfkGlTxn1TxnTypeRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * getting cgfkGlTxnAccountCode LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdaacco/cgfkGlTxnAccountCodeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> cgfkGlTxnAccountCodeRecordGroup(@RequestParam(value = "txnType") final String txnType,
			@RequestParam(value = "caseLoadId") final String caseLoadId,
			@RequestParam(value = "caseLoadType") final String caseLoadType) {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = otdaaccoService.cgfkGlTxnAccountCodeRecordGroup(txnType, caseLoadId, caseLoadType);
		} catch (Exception e) {
			logger.error("cgfkGlTxnAccountCodeRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * getting cgfkGlTxn1AccountCode LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdaacco/cgfkGlTxn1AccountCodeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> cgfkGlTxn1AccountCodeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = otdaaccoService.cgfkGlTxn1AccountCodeRecordGroup();
		} catch (Exception e) {
			logger.error("cgfkGlTxn1AccountCodeRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdaacco/glTxn1ExecuteQuery", method = RequestMethod.POST)
	public List<GlTransactions> glTxn1ExecuteQuery(@RequestBody final GlTransactions searchBean) {
		List<GlTransactions> searchResult = new ArrayList<>();
		try {
			searchResult = otdaaccoService.glTxn1ExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("glTxn1ExecuteQuery", e);
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
	@RequestMapping(value = "/otdaacco/glTxn1Commit", method = RequestMethod.POST)
	public @ResponseBody GlTransactions glTxn1Commit(@RequestBody final GlTransactionsCommitBean commitBean) {
		GlTransactions liReturn = null;
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = new GlTransactions();
			liReturn = otdaaccoService.glTxn1Commit(commitBean);
		} catch (Exception e) {
			logger.error("glTxn1Commit", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdaacco/systemProfilesExecuteQuery", method = RequestMethod.POST)
	public List<SystemProfiles> systemProfilesExecuteQuery(@RequestBody final SystemProfiles searchBean) {
		List<SystemProfiles> searchResult = new ArrayList<>();
		try {
			searchResult = otdaaccoService.systemProfilesExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("systemProfilesExecuteQuery", e);
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
	@RequestMapping(value = "/otdaacco/systemProfilesCommit", method = RequestMethod.POST)
	public @ResponseBody Integer systemProfilesCommit(@RequestBody final SystemProfilesCommitBean commitBean) {
		int liReturn = 0;
		try {
			liReturn = otdaaccoService.systemProfilesCommit(commitBean);
		} catch (Exception e) {
			logger.error("systemProfilesCommit", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdaacco/checkNavigation", method = RequestMethod.GET)
	public @ResponseBody String checkNavigation(@RequestParam(value = "accountCode") final BigDecimal accountCode) {
		String liReturn = null;
		try {
			liReturn = otdaaccoService.checkNavigation(accountCode);
		} catch (Exception e) {
			logger.error("checkNavigation", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdaacco/chkOffAcnt", method = RequestMethod.GET)
	public @ResponseBody String chkOffAcnt(@RequestParam(value = "accountCode") final BigDecimal accountCode) {
		String liReturn = null;
		try {
			liReturn = otdaaccoService.chkOffAcnt(accountCode);
		} catch (Exception e) {
			logger.error("chkOffAcnt", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdaacco/whenValidateItem", method = RequestMethod.POST)
	public @ResponseBody Integer whenValidateItem(@RequestBody final GlTransactions bean) {
		Integer liReturn = 0;
		try {
			liReturn = otdaaccoService.whenValidateItem(bean);
		} catch (Exception e) {
			logger.error("whenValidateItem", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdaacco/chkInvalidAccounts", method = RequestMethod.GET)
	public @ResponseBody Integer chkInvalidAccounts(
			@RequestParam(value = "accountCodeOne") final BigDecimal accountCodeOne,
			@RequestParam(value = "accountCodeTwo") final BigDecimal accountCodeTwo,
			@RequestParam(value = "caseloadId") final String caseloadId) {
		Integer liReturn = null;
		try {
			liReturn = otdaaccoService.chkInvalidAccounts(accountCodeOne, accountCodeTwo, caseloadId);
		} catch (Exception e) {
			logger.error("chkInvalidAccounts", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdaacco/whenCheckBoxChecked", method = RequestMethod.GET)
	public @ResponseBody String whenCheckBoxChecked(@RequestParam(value = "caseloadId") final String caseloadId,
			@RequestParam(value = "offfenderId") final BigDecimal offfenderId,
			@RequestParam(value = "txnType") final String txnType) {
		String liReturn = null;
		try {
			liReturn = otdaaccoService.whenCheckBoxChecked(caseloadId, offfenderId, txnType);
		} catch (Exception e) {
			logger.error("chkInvalidAccounts", e);
		}
		return liReturn;
	}

}