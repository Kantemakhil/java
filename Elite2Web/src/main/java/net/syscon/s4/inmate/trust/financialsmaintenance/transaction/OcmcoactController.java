package net.syscon.s4.inmate.trust.financialsmaintenance.transaction;

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
import net.syscon.s4.common.beans.Caseloads;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.inmate.beans.AccountCodesCommitBean;

@EliteController
public class OcmcoactController {
	@Autowired
	private OcmcoactService ocmcoactcontroller;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcmcoactController.class.getName());

	/**
	 * getting cgfkAcCodeRecAccountCode LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmcoact/cgfkAcCodeRecAccountCodeRecordGroup", method = RequestMethod.GET)
	public List<AccountCodes> cgfkAcCodeRecAccountCodeRecordGroup() {
		List<AccountCodes> recordList = new ArrayList<AccountCodes>();
		try {
			recordList = ocmcoactcontroller.cgfkAcCodeRecAccountCodeRecordGroup();
		} catch (Exception e) {
			logger.error("cgfkAcCodeRecAccountCodeRecordGroup :", e);
		}
		return recordList;
	}

	/**
	 * getting cgfkAcCodeParentCode LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmcoact/cgfkAcCodeParentCodeRecordGroup", method = RequestMethod.GET)
	public List<AccountCodes> cgfkAcCodeParentCodeRecordGroup() {
		List<AccountCodes> recordList = new ArrayList<AccountCodes>();
		try {
			recordList = ocmcoactcontroller.cgfkAcCodeParentCodeRecordGroup();
		} catch (Exception e) {
			logger.error("cgfkAcCodeParentCodeRecordGroup :", e);
		}
		return recordList;
	}

	/**
	 * getting cgfkCsldAcdCaseloadId LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmcoact/cgfkCsldAcdCaseloadIdRecordGroup", method = RequestMethod.GET)
	public List<Caseloads> cgfkCsldAcdCaseloadIdRecordGroup() {
		List<Caseloads> recordList = new ArrayList<Caseloads>();
		try {
			recordList = ocmcoactcontroller.cgfkCsldAcdCaseloadIdRecordGroup();
		} catch (Exception e) {
			logger.error("cgfkCsldAcdCaseloadIdRecordGroup :", e);
		}
		return recordList;
	}

	/**
	 * getting cgfkAcCodeAccountType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmcoact/cgfkAcCodeAccountTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> cgfkAcCodeAccountTypeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocmcoactcontroller.cgfkAcCodeAccountTypeRecordGroup();
		} catch (Exception e) {
			logger.error("ExcecgfkAcCodeAccountTypeRecordGroupption :", e);
		}
		return recordList;
	}

	/**
	 * getting cgfkAcCodeTxnPostingType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmcoact/cgfkAcCodeTxnPostingTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> cgfkAcCodeTxnPostingTypeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocmcoactcontroller.cgfkAcCodeTxnPostingTypeRecordGroup();
		} catch (Exception e) {
			logger.error("cgfkAcCodeTxnPostingTypeRecordGroup :", e);
		}
		return recordList;
	}

	/**
	 * getting cgfkAcCodeSubAccountType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmcoact/cgfkAcCodeSubAccountTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> cgfkAcCodeSubAccountTypeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocmcoactcontroller.cgfkAcCodeSubAccountTypeRecordGroup();
		} catch (Exception e) {
			logger.error("cgfkAcCodeSubAccountTypeRecordGroup :", e);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmcoact/acCodeExecuteQuery", method = RequestMethod.POST)
	public List<AccountCodes> acCodeExecuteQuery(@RequestBody AccountCodes searchBean) {
		List<AccountCodes> searchResult = new ArrayList<>();
		try {
			searchResult = ocmcoactcontroller.acCodeExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("acCodeExecuteQuery :", e);
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
	@RequestMapping(value = "/ocmcoact/acCodeCommit", method = RequestMethod.POST)
	public @ResponseBody String acCodeCommit(@RequestBody AccountCodesCommitBean commitBean) {
		String liReturn = null;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = ocmcoactcontroller.acCodeCommit(commitBean);
		} catch (Exception e) {
			liReturn = e.getMessage().toUpperCase();
			logger.error("acCodeCommit :", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmcoact/caselaodAccountCodes", method = RequestMethod.GET)
	public List<AccountCodes> caselaodAccountCodes(@RequestParam("accountCode") final Integer accountCode,
			@RequestParam("caseloadId") final String caseloadId) {
		List<AccountCodes> searchResult = new ArrayList<>();
		try {
			searchResult = ocmcoactcontroller.caselaodAccountCodes(accountCode, caseloadId);
		} catch (Exception e) {
			logger.error("acCodeExecuteQuery :", e);
		}
		return searchResult;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param Integer accountCode, String caseloadId
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmcoact/chkSubAcTypeTxnCur", method = RequestMethod.GET)
	public String chkSubAcTypeTxnCur(@RequestParam("accountCode") final Integer accountCode,
			@RequestParam("caseloadId") final String caseloadId) {
		String searchResult = null;
		try {
			searchResult = ocmcoactcontroller.chkSubAcTypeTxnCur(accountCode, caseloadId);
		} catch (Exception e) {
			logger.error("acCodeExecuteQuery :", e);
		}
		return searchResult;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param Integer accountCode, String caseloadId
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmcoact/chkDupSubAcTypeCur", method = RequestMethod.GET)
	public Integer chkDupSubAcTypeCur(@RequestParam("caseloadId") final String caseloadId,
			@RequestParam("subAcType") final String subAcType) {
		Integer searchResult = null;
		try {
			searchResult = ocmcoactcontroller.chkDupSubAcTypeCur(caseloadId, subAcType);
		} catch (Exception e) {
			logger.error("acCodeExecuteQuery :", e);
		}
		return searchResult;
	}

	/**
	 * getting txnTypeValidation delete validations
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmcoact/accountCodeValidation", method = RequestMethod.GET)
	public String accountCodeValidation(@RequestParam("accountCode") final Integer accountCode) {
		String recordList = null;
		try {
			recordList = ocmcoactcontroller.accountCodeValidation(accountCode);
		} catch (Exception e) {
			logger.error("txnTypeValidation", e);
		}
		return recordList;
	}

	/**
	 * getting txnTypeValidation delete validations
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmcoact/caseloadAccountCodeValidation", method = RequestMethod.GET)
	public String caseloadAccountCodeValidation(@RequestParam("accountCode") final Integer accountCode) {
		String recordList = null;
		try {
			recordList = ocmcoactcontroller.caseloadAccountCodeValidation(accountCode);
		} catch (Exception e) {
			logger.error("txnTypeValidation", e);
		}
		return recordList;
	}

	/**
	 * getting txnTypeOnCheckDeleteMaster delete validations
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmcoact/txnTypeOnCheckDeleteMaster", method = RequestMethod.GET)
	public Integer txnTypeOnCheckDeleteMaster(@RequestParam("accountCode") final Integer accountCode) {
		Integer recordList = 0;
		try {
			recordList = ocmcoactcontroller.txnTypeOnCheckDeleteMaster(accountCode);
		} catch (Exception e) {
			logger.error("oidadmisCgfkOffEmDspDescriptionRecordGroup", e);
		}
		return recordList;
	}
}