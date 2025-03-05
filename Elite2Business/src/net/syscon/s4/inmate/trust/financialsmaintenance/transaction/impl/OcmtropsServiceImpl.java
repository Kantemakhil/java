package net.syscon.s4.inmate.trust.financialsmaintenance.transaction.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.Caseloads;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.TransactionTypes;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.im.beans.Statutes;
import net.syscon.s4.im.beans.TransactionOperation;
import net.syscon.s4.im.beans.TransactionOperationCommitBean;
import net.syscon.s4.inmate.trust.financialsmaintenance.transaction.OcmtropsRepository;
import net.syscon.s4.inmate.trust.financialsmaintenance.transaction.OcmtropsService;

/**
 * Class OcmtropsServiceImpl
 */
@Service
public class OcmtropsServiceImpl extends BaseBusiness implements OcmtropsService {

	@Autowired
	private OcmtropsRepository ocmtropsRepository;

	/**
	 * Creates new OcmtropsServiceImpl class Object
	 */
	public OcmtropsServiceImpl() {
		// OcmtropsServiceImpl
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param paramBean
	 * @return Caseloads
	 */
	public Caseloads cgfkchkTxnOperTxnOperCsl(final Caseloads paramBean) {
		return ocmtropsRepository.cgfkchkTxnOperTxnOperCsl(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param paramBean
	 * @return OmsModules
	 */
	public OmsModules cgfkchkTxnOperTxnOperOms(final OmsModules paramBean) {
		return ocmtropsRepository.cgfkchkTxnOperTxnOperOms(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param paramBean
	 * @return ReferenceCodes
	 */
	public ReferenceCodes cgfkchkTxnOperTxnOperRef(final ReferenceCodes paramBean) {
		return ocmtropsRepository.cgfkchkTxnOperTxnOperRef(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param paramBean
	 * @return AccountCodes
	 */
	public AccountCodes cgfkchkTxnOperTxnOperBnk(final AccountCodes paramBean) {
		return ocmtropsRepository.cgfkchkTxnOperTxnOperBnk(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param paramBean
	 * @return AccountCodes
	 */
	public AccountCodes cgfkchkTxnOperTxnOper(final AccountCodes paramBean) {
		return ocmtropsRepository.cgfkchkTxnOperTxnOper(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param paramBean
	 * @return AccountCodes
	 */
	public AccountCodes cgfkchkTxnOperTxnOperCr(final AccountCodes paramBean) {
		return ocmtropsRepository.cgfkchkTxnOperTxnOperCr(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param paramBean
	 * @return AccountCodes
	 */
	public AccountCodes cgfkchkTxnOperTxnOperDr(final AccountCodes paramBean) {
		return ocmtropsRepository.cgfkchkTxnOperTxnOperDr(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 */
	// cgfkchkTxnOperTxnOperTxn() {
	/// * CGFK$CHK_TXN_OPER_TXN_OPER_TXN */
	// if (c%!found ){
	// throw new Error('form_trigger_failure');
	// }
	// }

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @return List<Object>
	 */
	public List<Object> cgwhenNewFormInstance() {
		return null;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 * @return List<TransactionOperations>
	 */
	public List<TransactionOperation> txnOperExecuteQuery(final TransactionOperation searchRecord) {
		List<TransactionOperation> returnList = ocmtropsRepository.txnOperExecuteQuery(searchRecord);
		for (final TransactionOperation obj : returnList) {
			if (obj.getDrAccountCode() != null) {
				obj.setDrAccountCodeTemp((obj.getDrAccountCode().toString()));
			}
			if (obj.getCrAccountCode() != null) {
				obj.setCrAccountCodeTemp(obj.getCrAccountCode().toString());
			}
			if (obj.getBankDrAccountCode() != null) {
				obj.setBankDrAccountCodeTemp(obj.getBankDrAccountCode().toString());
			}
			if (obj.getBankCrAccountCode() != null) {
				obj.setBankCrAccountCodeTemp(obj.getBankCrAccountCode().toString());
			}
		}
		return returnList;

	}

	/**
	 * Insert the records from database table
	 *
	 * @param commitBean
	 * @return Integer
	 */
	@Transactional
	public Integer txnOperCommit(final TransactionOperationCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (TransactionOperation obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
			}
			liReturn = txnOperInsertTransactionOperations(commitBean.getInsertList());
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (TransactionOperation obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = txnOperUpdateTransactionOperations(commitBean.getUpdateList());
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			for (TransactionOperation obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = txnOperDeleteTransactionOperations(commitBean.getDeleteList());
		}
		return liReturn;
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOffenderAlerts
	 */
	@Transactional
	public Integer txnOperInsertTransactionOperations(final List<TransactionOperation> lstOffenderAlerts) {
		for (TransactionOperation obj : lstOffenderAlerts) {
			if (obj.getChequeProductionFlag() != null && obj.getChequeProductionFlag().equals("true")) {
				obj.setChequeProductionFlag("Y");
			} else {
				obj.setChequeProductionFlag("N");
			}
			if (obj.getReceiptProductionFlag() != null && obj.getReceiptProductionFlag().equals("true")) {
				obj.setReceiptProductionFlag("Y");
			} else {
				obj.setReceiptProductionFlag("N");
			}
			if (obj.getUpdateAllowedFlag() != null && obj.getUpdateAllowedFlag().equals("true")) {
				obj.setUpdateAllowedFlag("Y");
			} else {
				obj.setUpdateAllowedFlag("N");
			}
			if (obj.getInvalidAccountsFlag() != null && obj.getInvalidAccountsFlag().equals("true")) {
				obj.setInvalidAccountsFlag("Y");
			} else {
				obj.setInvalidAccountsFlag("N");
			}
		}
		return ocmtropsRepository.txnOperInsertTransactionOperations(lstOffenderAlerts);
	}

	/**
	 * Update the records from database table
	 *
	 * @param lstOffenderAlerts
	 */
	@Transactional
	public Integer txnOperUpdateTransactionOperations(final List<TransactionOperation> lstOffenderAlerts) {
		for (TransactionOperation obj : lstOffenderAlerts) {
			if (obj.getChequeProductionFlag() != null && obj.getChequeProductionFlag().equals("true")) {
				obj.setChequeProductionFlag("Y");
			} else {
				obj.setChequeProductionFlag("N");
			}
			if (obj.getReceiptProductionFlag() != null && obj.getReceiptProductionFlag().equals("true")) {
				obj.setReceiptProductionFlag("Y");
			} else {
				obj.setReceiptProductionFlag("N");
			}
			if (obj.getUpdateAllowedFlag() != null && obj.getUpdateAllowedFlag().equals("true")) {
				obj.setUpdateAllowedFlag("Y");
			} else {
				obj.setUpdateAllowedFlag("N");
			}
			if (obj.getInvalidAccountsFlag() != null && obj.getInvalidAccountsFlag().equals("true")) {
				obj.setInvalidAccountsFlag("Y");
			} else {
				obj.setInvalidAccountsFlag("N");
			}
		}
		return ocmtropsRepository.txnOperUpdateTransactionOperations(lstOffenderAlerts);
	}

	/**
	 * Delete the records from database table
	 *
	 * @param lstOffenderAlerts
	 */
	@Transactional
	public Integer txnOperDeleteTransactionOperations(final List<TransactionOperation> lstOffenderAlerts) {
		for (TransactionOperation obj : lstOffenderAlerts) {
			if (obj.getChequeProductionFlag() != null && obj.getChequeProductionFlag().equals("true")) {
				obj.setChequeProductionFlag("Y");
			} else {
				obj.setChequeProductionFlag("N");
			}
			if (obj.getReceiptProductionFlag() != null && obj.getReceiptProductionFlag().equals("true")) {
				obj.setReceiptProductionFlag("Y");
			} else {
				obj.setReceiptProductionFlag("N");
			}
			if (obj.getUpdateAllowedFlag() != null && obj.getUpdateAllowedFlag().equals("true")) {
				obj.setUpdateAllowedFlag("Y");
			} else {
				obj.setUpdateAllowedFlag("N");
			}
			if (obj.getInvalidAccountsFlag() != null && obj.getInvalidAccountsFlag().equals("true")) {
				obj.setInvalidAccountsFlag("Y");
			} else {
				obj.setInvalidAccountsFlag("N");
			}
		}
		return ocmtropsRepository.txnOperDeleteTransactionOperations(lstOffenderAlerts);
	}

	/**
	 * This method is used to execute a record group
	 */
	public List<Caseloads> cgfkTxnOperCaseloadIdRecordGroup(String userName) {
		List<Caseloads> resultList = ocmtropsRepository.cgfkTxnOperCaseloadIdRecordGroup(userName);
		resultList.forEach(data -> {
			data.setTrustCaseloadId(data.getDescription());
			data.setDescription(data.getCode());
		});
		return resultList;
	}

	/**
	 * This method is used to execute a record group
	 */
	public List<TransactionTypes> cgfkTxnOperTxnTypeRecordGroup(String userName) {
		List<TransactionTypes> resultList = ocmtropsRepository.cgfkTxnOperTxnTypeRecordGroup(userName);
		resultList.forEach(data -> {
			data.setTxnUsage(data.getDescription());
			data.setDescription(data.getCode());
			if (data.getSealFlag() != null &&data.getSealFlag().equals("Y")) {
				data.setCanDisplay(true);
			} else {
				data.setCanDisplay(false);
			}
		});
		return resultList;
	}

	/**
	 * This method is used to execute a record group
	 */
	public List<AccountCodes> cgfkTxnOperDrAccountCodeRecordGroup(String userName) {
		List<AccountCodes> resultList = ocmtropsRepository.cgfkTxnOperDrAccountCodeRecordGroup(userName);
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
	 * This method is used to execute a record group
	 */
	public List<AccountCodes> cgfkTxnOperCrAccountCodeRecordGroup(String userName) {
		List<AccountCodes> resultList = ocmtropsRepository.cgfkTxnOperCrAccountCodeRecordGroup(userName);
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
	 * This method is used to execute a record group
	 */
	public List<AccountCodes> cgfkTxnOperBankDrAccountRecordGroup(String userName) {
		List<AccountCodes> resultList = ocmtropsRepository.cgfkTxnOperBankDrAccountRecordGroup(userName);
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
	 * This method is used to execute a record group
	 */
	public List<AccountCodes> cgfkTxnOperBankCrAccountRecordGroup(String userName) {
		List<AccountCodes> resultList = ocmtropsRepository.cgfkTxnOperBankCrAccountRecordGroup(userName);
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
	 * This method is used to execute a record group
	 */
	public List<ReferenceCodes> cgfkTxnOperTxnOperationTyRecordGroup() {
		List<ReferenceCodes> resultList = ocmtropsRepository.cgfkTxnOperTxnOperationTyRecordGroup();
		resultList.forEach(data -> {
			data.setDomain(data.getDescription());
			data.setDescription(data.getCode());
		});
		return resultList;

	}

	/**
	 * This method is used to execute a record group
	 */
	public List<OmsModules> cgfkTxnOperModuleNameRecordGroup() {
		List<OmsModules> resultList = ocmtropsRepository.cgfkTxnOperModuleNameRecordGroup();
		resultList.forEach(data -> {
			data.setModuleType(data.getDescription());
			data.setDescription(data.getCode());
		});
		return resultList;
	}

	public String txnTypeValidation(final String txnType, final String moduleName, final String caseloadId,
			final Long txnOperationSeq) {
		return ocmtropsRepository.txnTypeValidation(txnType, moduleName, caseloadId, txnOperationSeq);
	}

}