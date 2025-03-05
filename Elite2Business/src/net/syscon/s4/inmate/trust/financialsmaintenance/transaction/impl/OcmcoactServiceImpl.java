package net.syscon.s4.inmate.trust.financialsmaintenance.transaction.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionOperations;

import net.syscon.s4.common.beans.Caseloads;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.inmate.beans.AccountCodesCommitBean;
import net.syscon.s4.inmate.trust.financialsmaintenance.transaction.OcmcoactRepository;
import net.syscon.s4.inmate.trust.financialsmaintenance.transaction.OcmcoactService;
import net.syscon.s4.pkgs.no_pkg_procudres.OtmoncoaGenAccountCodesService;

/**
 * Class OcmcoactServiceImpl
 */
@Service
public class OcmcoactServiceImpl extends BaseBusiness implements OcmcoactService {

	@Autowired
	private OcmcoactRepository ocmcoactRepository;
	
	@Autowired
	private OtmoncoaGenAccountCodesService otmoncoaGenAccountCodesService;

	/**
	 * Creates new OcmcoactServiceImpl class Object
	 */
	public OcmcoactServiceImpl() {
		// OcmcoactServiceImpl
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 * @return List<AccountCodes>
	 * 
	 */
	public List<AccountCodes> cgfkchkAcCodeAcCodeAcCo(AccountCodes paramBean) {
		return ocmcoactRepository.cgfkchkAcCodeAcCodeAcCo(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 * @return List<ReferenceCodes>
	 * 
	 */
	public List<ReferenceCodes> cgfkchkAcCodeAcSubAcct(ReferenceCodes paramBean) {
		return ocmcoactRepository.cgfkchkAcCodeAcSubAcct(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 * @return List<ReferenceCodes>
	 * 
	 */
	public List<ReferenceCodes> cgfkchkAcCodeAcPostType(ReferenceCodes paramBean) {
		return ocmcoactRepository.cgfkchkAcCodeAcPostType(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 * @return List<ReferenceCodes>
	 * 
	 */
	public List<ReferenceCodes> cgfkchkAcCodeAcAcctType(ReferenceCodes paramBean) {
		return ocmcoactRepository.cgfkchkAcCodeAcAcctType(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 * @return List<Caseloads>
	 * 
	 */
	public List<Caseloads> cgfkchkCsldAcdCsldAcdCsl(Caseloads paramBean) {
		return ocmcoactRepository.cgfkchkCsldAcdCsldAcdCsl(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 * @return List<TransactionOperations>
	 */
	public List<TransactionOperations> cgriChkAccountCodes(TransactionOperations paramBean) {
		return ocmcoactRepository.cgriChkAccountCodes(paramBean);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 * @return List<AccountCodes>
	 */
	public List<AccountCodes> acCodeExecuteQuery(AccountCodes searchRecord) {
		return ocmcoactRepository.acCodeExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param AccountCodesCommitBean
	 *            commitBean
	 * @return Integer
	 * 
	 */
	@Transactional
	public String acCodeCommit(final AccountCodesCommitBean commitBean) {
		Integer liReturn = 0;
		String returnList = null;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (AccountCodes obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
			}
			liReturn = acCodeInsertAccountCodes(commitBean.getInsertList());
			if (liReturn != 0) {
				returnList = liReturn.toString();
			} else {
				returnList = null;
			}
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (AccountCodes obj : commitBean.getUpdateList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
			}
			liReturn = ocmcoactRepository.acCodeUpdateAccountCodes(commitBean.getUpdateList());
			if (liReturn != 0) {
				returnList = liReturn.toString();
			} else {
				returnList = null;
			}
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			for (AccountCodes obj : commitBean.getDeleteList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = acCodeDeleteAccountCodes(commitBean.getDeleteList());
			if (liReturn != 0) {
				returnList = liReturn.toString();
			} else {
				returnList = null;
			//	returnList = "ORA-02292";
			}
		}
		return returnList;
	}
	
	public Integer acCodeInsertAccountCodes(final List<AccountCodes> lstAccountCodes) {
		Integer returnData = ocmcoactRepository.acCodeInsertAccountCodes(lstAccountCodes);
		for (final AccountCodes accountCodes : lstAccountCodes) {
			//Integer caseloadAccountCodes = ocmcoactRepository.caseloadAccountCodesInsert(accountCodes);
			//Procedure call
			Integer caseloadAccountCodes = otmoncoaGenAccountCodesService.otmoncoaGenAccountCodes(accountCodes, accountCodes.getCreateUserId());
			if (caseloadAccountCodes != 2) {
				returnData = null;
			}
		}
		return returnData;
	}

	public Integer acCodeDeleteAccountCodes(final List<AccountCodes> lstAccountCodes) {
		
		for (final AccountCodes accountCodes : lstAccountCodes) {
			Integer caseloadAcntSum = ocmcoactRepository.caseloadAccountSummaries(accountCodes);
			if (caseloadAcntSum != 0) {
			//	return 0;
			}
			Integer csldCurrentAcntsTxns = ocmcoactRepository.caseloadCurrentAccountsTxns(accountCodes);
			if (csldCurrentAcntsTxns != 0) {
			//	return 0;
			}
			Integer csldCurrentAcntsBase = ocmcoactRepository.caseloadCurrentAccountsBase(accountCodes);
			if (csldCurrentAcntsBase != 0) {
			//	return 0;
			}
		}
		return ocmcoactRepository.acCodeDeleteAccountCodes(lstAccountCodes);
	}

	/**
	 * This method is used to execute a record group
	 * 
	 * @return List<AccountCodes>
	 */
	public List<AccountCodes> cgfkAcCodeRecAccountCodeRecordGroup() {
		List<AccountCodes> returnList = ocmcoactRepository.cgfkAcCodeRecAccountCodeRecordGroup();
		for (final AccountCodes accountCodes : returnList) {
			accountCodes.setCode(accountCodes.getRecAccountCode().toString());
		}
		return returnList;
	}

	/**
	 * This method is used to execute a record group
	 * 
	 * @return List<AccountCodes>
	 */
	public List<AccountCodes> cgfkAcCodeParentCodeRecordGroup() {
		List<AccountCodes> returnList = ocmcoactRepository.cgfkAcCodeParentCodeRecordGroup();
		for (final AccountCodes accountCodes : returnList) {
			accountCodes.setCode(accountCodes.getParentAccountCode().toString());
		}
		return returnList;
	}

	/**
	 * This method is used to execute a record group
	 * 
	 * @return List<Caseloads>
	 */
	public List<Caseloads> cgfkCsldAcdCaseloadIdRecordGroup() {
		return ocmcoactRepository.cgfkCsldAcdCaseloadIdRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> cgfkAcCodeAccountTypeRecordGroup() {
		return ocmcoactRepository.cgfkAcCodeAccountTypeRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> cgfkAcCodeTxnPostingTypeRecordGroup() {
		return ocmcoactRepository.cgfkAcCodeTxnPostingTypeRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> cgfkAcCodeSubAccountTypeRecordGroup() {
		return ocmcoactRepository.cgfkAcCodeSubAccountTypeRecordGroup();

	}

	@Override
	public List<AccountCodes> caselaodAccountCodes(final Integer accountCode, final String caseloadId) {
		return ocmcoactRepository.caselaodAccountCodes(accountCode, caseloadId);
	}

	@Override
	public String chkSubAcTypeTxnCur(final Integer accountCode, final String caseloadId) {
		return ocmcoactRepository.chkSubAcTypeTxnCur(accountCode, caseloadId);
	}

	@Override
	public Integer chkDupSubAcTypeCur(final String caseloadId, final String subAcType) {
		return ocmcoactRepository.chkDupSubAcTypeCur(caseloadId, subAcType);
	}

	public String accountCodeValidation(final Integer accountCode) {
		return ocmcoactRepository.accountCodeValidation(accountCode);
	}

	public String caseloadAccountCodeValidation(final Integer accountCode) {
		return ocmcoactRepository.caseloadAccountCodeValidation(accountCode);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param searchRecord
	 * @return TransactionPayees
	 */
	public Integer txnTypeOnCheckDeleteMaster(final Integer accountCode) {
		Integer returnList = 0;
		Integer lTxnOpExistsCurLimits = ocmcoactRepository.lTxnOpExistsCur(accountCode);
		if (lTxnOpExistsCurLimits > 0) {
			returnList = 13;
			return returnList;
		}
		Integer lGlExistsLimits = ocmcoactRepository.lGlExists(accountCode);
		if (lGlExistsLimits > 0) {
			returnList = 14;
			return returnList;
		}
		boolean pCheckBoth = false;
		if (pCheckBoth) {
			Integer txnOperations = ocmcoactRepository.transactionOperationsTxnOpeDr(accountCode);
			if (txnOperations > 0) {
				returnList = 1;
				return returnList;
			}
			Integer offenderPostDatedTxns = ocmcoactRepository.transactionOperationsTxnOpeCr(accountCode);
			if (offenderPostDatedTxns > 0) {
				returnList = 2;
				return returnList;
			}
			Integer interestTxnsTypes = ocmcoactRepository.transactionOperationsTxnOpeBankDr(accountCode);
			if (interestTxnsTypes > 0) {
				returnList = 3;
				return returnList;
			}
			Integer glTransactions = ocmcoactRepository.transactionOperationsTxnOpeBankCr(accountCode);
			if (glTransactions > 0) {
				returnList = 4;
				return returnList;
			}
			Integer offenderInterests = ocmcoactRepository.transactionOperationsTxnOpeTxnNv(accountCode);
			if (offenderInterests > 0) {
				returnList = 5;
				return returnList;
			}
			Integer subAccountViewsLimits = ocmcoactRepository.subAccountViewsAv(accountCode);
			if (subAccountViewsLimits > 0) {
				returnList = 6;
				return returnList;
			}
			Integer payeeAccountBalancesLimits = ocmcoactRepository.payeeAccountBalancesValid(accountCode);
			if (payeeAccountBalancesLimits > 0) {
				returnList = 7;
				return returnList;
			}
			Integer interestTransactionTypesLimits = ocmcoactRepository.interestTransactionTypesValid(accountCode);
			if (interestTransactionTypesLimits > 0) {
				returnList = 8;
				return returnList;
			}
			Integer glTransactionsLimits = ocmcoactRepository.glTransactionsValid(accountCode);
			if (glTransactionsLimits > 0) {
				returnList = 9;
				return returnList;
			}
		}
		Integer offenderSubAccountsLimits = ocmcoactRepository.offenderSubAccountsValid(accountCode);
		if (offenderSubAccountsLimits > 0) {
			returnList = 10;
			return returnList;
		}
		Integer caseloadDeductionProfilesLimits = ocmcoactRepository.caseloadDeductionProfilesValid(accountCode);
		if (caseloadDeductionProfilesLimits > 0) {
			returnList = 11;
			return returnList;
		}
		Integer accountCodesLimits = ocmcoactRepository.accountCodesAcCodeValid(accountCode);
		if (accountCodesLimits > 0) {
			returnList = 12;
			return returnList;
		}
		return returnList;
	}

}