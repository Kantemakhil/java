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
import net.syscon.s4.common.beans.TransactionTypes;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.im.beans.TransactionOperation;
import net.syscon.s4.im.beans.TransactionOperationCommitBean;

/**
 * @author Arkin Software Technologies
 * @version 1.0
 */
@EliteController
public class OcmtropsController {
	@Autowired
	private OcmtropsService ocmtropsService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcmtropsController.class.getName());

	/**
	 * getting cgfkTxnOperCaseloadId LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmtrops/cgfkTxnOperCaseloadIdRecordGroup", method = RequestMethod.GET)
	public List<Caseloads> cgfkTxnOperCaseloadIdRecordGroup() {
		List<Caseloads> recordList = new ArrayList<Caseloads>();
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		try {
			recordList = ocmtropsService.cgfkTxnOperCaseloadIdRecordGroup(userName);
		} catch (Exception e) {
			logger.error("cgfkTxnOperCaseloadIdRecordGroup: ", e);
		}
		return recordList;
	}

	/**
	 * getting cgfkTxnOperTxnType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmtrops/cgfkTxnOperTxnTypeRecordGroup", method = RequestMethod.GET)
	public List<TransactionTypes> cgfkTxnOperTxnTypeRecordGroup() {
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		List<TransactionTypes> recordList = new ArrayList<TransactionTypes>();
		try {
			recordList = ocmtropsService.cgfkTxnOperTxnTypeRecordGroup(userName);
		} catch (Exception e) {
			logger.error("cgfkTxnOperTxnTypeRecordGroup: ", e);
		}
		return recordList;
	}

	/**
	 * getting cgfkTxnOperDrAccountCode LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmtrops/cgfkTxnOperDrAccountCodeRecordGroup", method = RequestMethod.GET)
	public List<AccountCodes> cgfkTxnOperDrAccountCodeRecordGroup() {
		List<AccountCodes> recordList = new ArrayList<AccountCodes>();
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		try {
			recordList = ocmtropsService.cgfkTxnOperDrAccountCodeRecordGroup(userName);
		} catch (Exception e) {
			logger.error("cgfkTxnOperDrAccountCodeRecordGroup: ", e);
		}
		return recordList;
	}

	/**
	 * getting cgfkTxnOperCrAccountCode LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmtrops/cgfkTxnOperCrAccountCodeRecordGroup", method = RequestMethod.GET)
	public List<AccountCodes> cgfkTxnOperCrAccountCodeRecordGroup() {
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		List<AccountCodes> recordList = new ArrayList<AccountCodes>();
		try {
			recordList = ocmtropsService.cgfkTxnOperCrAccountCodeRecordGroup(userName);
		} catch (Exception e) {
			logger.error("cgfkTxnOperCrAccountCodeRecordGroup: ", e);
		}
		return recordList;
	}

	/**
	 * getting cgfkTxnOperBankDrAccount LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmtrops/cgfkTxnOperBankDrAccountRecordGroup", method = RequestMethod.GET)
	public List<AccountCodes> cgfkTxnOperBankDrAccountRecordGroup() {
		List<AccountCodes> recordList = new ArrayList<AccountCodes>();
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		try {
			recordList = ocmtropsService.cgfkTxnOperBankDrAccountRecordGroup(userName);
		} catch (Exception e) {
			logger.error("cgfkTxnOperBankDrAccountRecordGroup: ", e);
		}
		return recordList;
	}

	/**
	 * getting cgfkTxnOperBankCrAccount LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmtrops/cgfkTxnOperBankCrAccountRecordGroup", method = RequestMethod.GET)
	public List<AccountCodes> cgfkTxnOperBankCrAccountRecordGroup() {
		List<AccountCodes> recordList = new ArrayList<AccountCodes>();
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		try {
			recordList = ocmtropsService.cgfkTxnOperBankCrAccountRecordGroup(userName);
		} catch (Exception e) {
			logger.error("cgfkTxnOperBankCrAccountRecordGroup: ", e);
		}
		return recordList;
	}

	/**
	 * getting cgfkTxnOperTxnOperationTy LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmtrops/cgfkTxnOperTxnOperationTyRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> cgfkTxnOperTxnOperationTyRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocmtropsService.cgfkTxnOperTxnOperationTyRecordGroup();
		} catch (Exception e) {
			logger.error("cgfkTxnOperTxnOperationTyRecordGroup: ", e);
		}
		return recordList;
	}

	/**
	 * getting cgfkTxnOperModuleName LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmtrops/cgfkTxnOperModuleNameRecordGroup", method = RequestMethod.GET)
	public List<OmsModules> cgfkTxnOperModuleNameRecordGroup() {
		List<OmsModules> recordList = new ArrayList<OmsModules>();
		try {
			recordList = ocmtropsService.cgfkTxnOperModuleNameRecordGroup();
		} catch (Exception e) {
			logger.error("cgfkTxnOperModuleNameRecordGroup: ", e);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @return List<TransactionOperation>
	 * @Param searchBean
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmtrops/txnOperExecuteQuery", method = RequestMethod.POST)
	public List<TransactionOperation> txnOperExecuteQuery(@RequestBody final TransactionOperation searchBean) {
		List<TransactionOperation> searchResult = new ArrayList<>();
		try {
			searchResult = ocmtropsService.txnOperExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("txnOperExecuteQuery: ", e);
		}
		return searchResult;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @Param commitBean
	 * @return Integer
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocmtrops/txnOperCommit", method = RequestMethod.POST)
	public @ResponseBody Integer txnOperCommit(@RequestBody final TransactionOperationCommitBean commitBean) {
		int liReturn = 0;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = ocmtropsService.txnOperCommit(commitBean);
		} catch (Exception e) {

			logger.error("txnOperCommit: ", e);
		}
		return liReturn;
	}

	/**
	 * getting txnTypeValidation delete validations
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmtrops/txnTypeValidation", method = RequestMethod.GET)
	public String txnTypeValidation(@RequestParam("txnType") final String txnType,
			@RequestParam("moduleName") final String moduleName, @RequestParam("caseloadId") final String caseloadId,
			@RequestParam("txnOperationSeq") final Long txnOperationSeq) {
		String recordList = null;
		try {
			recordList = ocmtropsService.txnTypeValidation(txnType, moduleName, caseloadId, txnOperationSeq);
		} catch (Exception e) {
			logger.error("txnTypeValidation", e);
		}
		return recordList;
	}

}