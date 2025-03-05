package net.syscon.s4.inmate.trust.financialsmaintenance.transaction.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.im.beans.TransactionOperation;
import net.syscon.s4.im.beans.TxnOpsInvalidAccounts;
import net.syscon.s4.im.beans.TxnOpsInvalidAccountsCommitBean;
import net.syscon.s4.inmate.trust.financialsmaintenance.transaction.OtuinvacRepository;
import net.syscon.s4.inmate.trust.financialsmaintenance.transaction.OtuinvacService;

/**
 * Class OcmtropsServiceImpl
 */
@Service
public class OtuinvacServiceImpl extends BaseBusiness implements OtuinvacService {

	@Autowired
	private OtuinvacRepository otuinvacRepository;

	/**
	 * Creates new OtuinvacServiceImpl class Object
	 */
	public OtuinvacServiceImpl() {
		// OtuinvacServiceImpl
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 * @return List<TxnOpsInvalidAccounts>
	 */
	public List<TxnOpsInvalidAccounts> txnInvExecuteQuery(TxnOpsInvalidAccounts searchBean) {
		return otuinvacRepository.txnInvExecuteQuery(searchBean);
	}

	/**
	 * getting cgfkTxnInvInvalidAccountCRecordGroup LOV values
	 */
	public List<AccountCodes> cgfkTxnInvInvalidAccountCRecordGroup() {
		List<AccountCodes> resultList = otuinvacRepository.cgfkTxnInvInvalidAccountCRecordGroup();
		resultList.forEach(data -> {
			if (data.getDescription() != null) {
				data.setTxnPostingType(data.getDescription());
			}
			if (data.getAccountCode() != null) {
				data.setCode(data.getAccountCode().toString());
			} else {
				data.setCode("");
			}
			data.setDescription(data.getCode());
		});
		return resultList;
	}

	/**
	 * Insert the records from database table
	 *
	 * @param commitBean
	 * @return Integer
	 */
	@Transactional
	public Integer txnInvCommit(final TxnOpsInvalidAccountsCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (TxnOpsInvalidAccounts obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
			}
			liReturn = txnInvInsertTxnOpsInvalidAccounts(commitBean.getInsertList());
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (TxnOpsInvalidAccounts obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = txnInvUpdateTxnOpsInvalidAccounts(commitBean.getUpdateList());
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			for (TxnOpsInvalidAccounts obj : commitBean.getDeleteList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = txnInvDeleteTxnOpsInvalidAccounts(commitBean.getDeleteList());
		}
		return liReturn;
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOffenderAlerts
	 */
	@Transactional
	public Integer txnInvInsertTxnOpsInvalidAccounts(final List<TxnOpsInvalidAccounts> lstOffenderAlerts) {
		return otuinvacRepository.txnInvInsertTxnOpsInvalidAccounts(lstOffenderAlerts);
	}

	/**
	 * Update the records from database table
	 *
	 * @param lstOffenderAlerts
	 */
	@Transactional
	public Integer txnInvUpdateTxnOpsInvalidAccounts(final List<TxnOpsInvalidAccounts> lstOffenderAlerts) {
		return otuinvacRepository.txnInvUpdateTxnOpsInvalidAccounts(lstOffenderAlerts);
	}

	/**
	 * Delete the records from database table
	 *
	 * @param lstOffenderAlerts
	 */
	@Transactional
	public Integer txnInvDeleteTxnOpsInvalidAccounts(final List<TxnOpsInvalidAccounts> lstOffenderAlerts) {
		return otuinvacRepository.txnInvDeleteTxnOpsInvalidAccounts(lstOffenderAlerts);
	}

}