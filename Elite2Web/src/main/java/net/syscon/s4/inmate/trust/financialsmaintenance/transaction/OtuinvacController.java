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
import org.springframework.web.bind.annotation.ResponseBody;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.im.beans.TxnOpsInvalidAccounts;
import net.syscon.s4.im.beans.TxnOpsInvalidAccountsCommitBean;

@EliteController
public class OtuinvacController {
	@Autowired
	private OtuinvacService otuinvacservice;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OtuinvacController.class.getName());

	/**
	 * getting cgfkTxnInvInvalidAccountCRecordGroup LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otuinvac/cgfkTxnInvInvalidAccountCRecordGroup", method = RequestMethod.GET)
	public List<AccountCodes> cgfkTxnInvInvalidAccountCRecordGroup() {
		List<AccountCodes> recordList = new ArrayList<AccountCodes>();
		try {
			recordList = otuinvacservice.cgfkTxnInvInvalidAccountCRecordGroup();
		} catch (Exception e) {
			logger.error("cgfkTxnInvInvalidAccountCRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchBean {@link TxnOpsInvalidAccounts}
	 * @return a list of the TxnOpsInvalidAccounts {@link TxnOpsInvalidAccounts} for the passed TxnOpsInvalidAccounts
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otuinvac/txnInvExecuteQuery", method = RequestMethod.POST)
	public List<TxnOpsInvalidAccounts> txnInvExecuteQuery(@RequestBody TxnOpsInvalidAccounts searchBean) {
		List<TxnOpsInvalidAccounts> searchResult = new ArrayList<>();
		try {
			searchResult = otuinvacservice.txnInvExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("txnInvExecuteQuery", e);
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
	@RequestMapping(value = "/otuinvac/txnInvCommit", method = RequestMethod.POST)
	public @ResponseBody Integer txnInvCommit(@RequestBody TxnOpsInvalidAccountsCommitBean commitBean) {
		int liReturn = 0;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = otuinvacservice.txnInvCommit(commitBean);
		} catch (Exception e) {
			logger.error("txnInvCommit", e);
		}
		return liReturn;
	}

}