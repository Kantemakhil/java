package net.syscon.s4.inmate.trust.financialsmaintenance.transaction.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.Caseloads;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.TransactionTypes;
import net.syscon.s4.common.beans.TransactionTypesCommitBean;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.Corporates;
import net.syscon.s4.im.beans.TransactionPayees;
import net.syscon.s4.im.beans.TransactionPayeesCommitBean;
import net.syscon.s4.inmate.beans.CaseloadTransactionTypes;
import net.syscon.s4.inmate.beans.DeductionTypes;
import net.syscon.s4.inmate.trust.financialsmaintenance.transaction.OcmtransRepository;
import net.syscon.s4.inmate.trust.financialsmaintenance.transaction.OcmtransService;
import net.syscon.s4.inst.booking.beans.Persons;
import net.syscon.s4.triggers.TransactionPayeesTjnService;
import net.syscon.s4.triggers.TransactionTypesTjnService;

/**
 * Class OcmtransServiceImpl
 */
@Service
public class OcmtransServiceImpl extends BaseBusiness implements OcmtransService {

	@Autowired
	private OcmtransRepository ocmtransRepository;
	@Autowired
	private TransactionTypesTjnService transactionTypesTjnService;
	@Autowired
	private TransactionPayeesTjnService transactionPayeesTjnService;

	/**
	 * Creates new OcmtransServiceImpl class Object
	 */
	public OcmtransServiceImpl() {
		// OcmtransServiceImpl
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param searchRecord
	 * @return TransactionPayees
	 */
	public TransactionPayees txnTypeOnCheckDeleteMaster(final TransactionPayees paramBean) {
		return ocmtransRepository.txnTypeOnCheckDeleteMaster(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param searchRecord
	 * @return TransactionPayees
	 */
	public Integer txnTypeOnCheckDeleteMaster(final String txnType) {
		Integer returnList = 0;
		Integer txnOperations = ocmtransRepository.cgrichkTransactionOperations(txnType);
		if (txnOperations > 0) {
			returnList = 1;
			return returnList;
		}
		Integer offenderPostDatedTxns = ocmtransRepository.cgrichkOffenderPostDatedTxns(txnType);
		if (offenderPostDatedTxns > 0) {
			returnList = 2;
			return returnList;
		}
		Integer interestTxnsTypes = ocmtransRepository.cgrichkInterestTransactionTypes(txnType);
		if (interestTxnsTypes > 0) {
			returnList = 3;
			return returnList;
		}
		Integer glTransactions = ocmtransRepository.cgrichkGlTransactions(txnType);
		if (glTransactions > 0) {
			returnList = 4;
			return returnList;
		}
		Integer offenderInterests = ocmtransRepository.cgrichkOffenderInterests(txnType);
		if (offenderInterests > 0) {
			returnList = 5;
			return returnList;
		}
		Integer deductionLimits = ocmtransRepository.cgrichkDeductionLimits(txnType);
		if (deductionLimits > 0) {
			returnList = 6;
			return returnList;
		}
		return returnList;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param searchRecord
	 * @return ReferenceCodes
	 */
	public ReferenceCodes cgfkchkTxnTypeTxnTypeRef(final ReferenceCodes paramBean) {
		return ocmtransRepository.cgfkchkTxnTypeTxnTypeRef(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param searchRecord
	 * @return Caseloads
	 */
	public Caseloads cgfkchkCsldTtCsldTtCsld(final Caseloads paramBean) {
		return ocmtransRepository.cgfkchkCsldTtCsldTtCsld(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param searchRecord
	 * @return Corporates
	 */
	public String cgfkchkTxnPayeeTxnPayee(final BigDecimal corporateId) {
		return ocmtransRepository.cgfkchkTxnPayeeTxnPayee(corporateId);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param searchRecord
	 * @return Persons
	 */
	public String cgfkchkTxnPayeeTxnPayee(final Long personId) {
		return ocmtransRepository.cgfkchkTxnPayeeTxnPayee(personId);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 * @return List<TransactionTypes>
	 */
	public List<TransactionTypes> txnTypeExecuteQuery(final TransactionTypes searchRecord) {
		List<TransactionTypes> returnList = new ArrayList<TransactionTypes>();
		DeductionTypes deductionTypes = new DeductionTypes();
		ReferenceCodes referenceCodes = new ReferenceCodes();
		returnList = ocmtransRepository.txnTypeExecuteQuery(searchRecord);
		for (final TransactionTypes transactionTypes : returnList) {
			if (transactionTypes.getCreditObligationType() != null) {
				deductionTypes.setDeductionType(transactionTypes.getCreditObligationType());
				deductionTypes = ocmtransRepository.cgfkchkTxnTypeTxnTypeDed(deductionTypes);
				if (deductionTypes != null && deductionTypes.getDeductionDesc() != null
						&& deductionTypes.getListSeq() != null) {
				}
			}
			if (transactionTypes.getCreditObligationType() != null) {
				referenceCodes.setDescription(transactionTypes.getTxnUsage());
				referenceCodes = ocmtransRepository.cgfkchkTxnTypeTxnTypeRef(referenceCodes);
				if (referenceCodes != null && referenceCodes.getDescription() != null) {
				}
			}
		}
		return returnList;
	}

	/**
	 * Insert the records from database table
	 *
	 * @Param commitBean
	 * @return Integer
	 */
	@Transactional
	public Integer txnTypeCommit(final TransactionTypesCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (TransactionTypes obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
				obj.setModifyUserId(commitBean.getCreateUserId());
				obj.setJnOracleUser(commitBean.getCreateUserId());
				obj.setJnOperation("INS");
			}
			liReturn = txnTypeInsertTransactionTypes(commitBean.getInsertList());
			
			//transactionTypesTjnService.transactiontypesTJNTrigger(commitBean.getInsertList());
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (TransactionTypes obj : commitBean.getUpdateList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
				obj.setModifyUserId(commitBean.getCreateUserId());
				obj.setJnOracleUser(commitBean.getCreateUserId());
				obj.setJnOperation("INS");
			}
			liReturn = txnTypeUpdateTransactionTypes(commitBean.getUpdateList());
			//transactionTypesTjnService.transactiontypesTJNTrigger(commitBean.getUpdateList());
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			for (TransactionTypes obj : commitBean.getDeleteList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = txnTypeDeleteTransactionTypes(commitBean.getDeleteList());
		}
		return liReturn;
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOffenderAlerts
	 */
	@Transactional
	public Integer txnTypeInsertTransactionTypes(final List<TransactionTypes> lstOffenderAlerts) {
		for (TransactionTypes obj : lstOffenderAlerts) {
			if (obj.getActiveFlag() != null && obj.getActiveFlag().equals("true")) {
				obj.setActiveFlag("Y");
			} else {
				obj.setActiveFlag("N");
			}
			if (obj.getAllCaseloadFlag() != null && obj.getAllCaseloadFlag().equals("true")) {
				obj.setAllCaseloadFlag("Y");
			} else {
				obj.setAllCaseloadFlag("N");
			}
			if (obj.getUpdateAllowedFlag() != null && obj.getUpdateAllowedFlag().equals("true")) {
				obj.setUpdateAllowedFlag("Y");
			} else {
				obj.setUpdateAllowedFlag("N");
			}
			obj.setManualInvoiceFlag("Y");
		}
		return ocmtransRepository.txnTypeInsertTransactionTypes(lstOffenderAlerts);
	}

	/**
	 * Update the records from database table
	 *
	 * @param lstOffenderAlerts
	 */
	@Transactional
	public Integer txnTypeUpdateTransactionTypes(final List<TransactionTypes> lstOffenderAlerts) {
		for (TransactionTypes obj : lstOffenderAlerts) {
			if (obj.getActiveFlag() != null && obj.getActiveFlag().equals("true")) {
				obj.setActiveFlag("Y");
			} else {
				obj.setActiveFlag("N");
			}
			if (obj.getAllCaseloadFlag() != null && obj.getAllCaseloadFlag().equals("true")) {
				obj.setAllCaseloadFlag("Y");
			} else {
				obj.setAllCaseloadFlag("N");
			}
			if (obj.getUpdateAllowedFlag() != null && obj.getUpdateAllowedFlag().equals("true")) {
				obj.setUpdateAllowedFlag("Y");
			} else {
				obj.setUpdateAllowedFlag("N");
			}
		}
		return ocmtransRepository.txnTypeUpdateTransactionTypes(lstOffenderAlerts);
	}

	/**
	 * Delete the records from database table
	 *
	 * @param lstOffenderAlerts
	 */
	@Transactional
	public Integer txnTypeDeleteTransactionTypes(final List<TransactionTypes> lstOffenderAlerts) {
		for (TransactionTypes obj : lstOffenderAlerts) {
			if (obj.getActiveFlag() != null && obj.getActiveFlag().equals("true")) {
				obj.setActiveFlag("Y");
			} else {
				obj.setActiveFlag("N");
			}
			if (obj.getAllCaseloadFlag() != null && obj.getAllCaseloadFlag().equals("true")) {
				obj.setAllCaseloadFlag("Y");
			} else {
				obj.setAllCaseloadFlag("N");
			}
			if (obj.getUpdateAllowedFlag() != null && obj.getUpdateAllowedFlag().equals("true")) {
				obj.setUpdateAllowedFlag("Y");
			} else {
				obj.setUpdateAllowedFlag("N");
			}
		}
		return ocmtransRepository.txnTypeDeleteTransactionTypes(lstOffenderAlerts);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 * @return List<CaseloadTransactionTypes>
	 */
	public List<CaseloadTransactionTypes> csldTtExecuteQuery(final CaseloadTransactionTypes searchRecord) {
		return ocmtransRepository.csldTtExecuteQuery(searchRecord);

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 * @return List<TransactionPayees>
	 */
	public List<TransactionPayees> txnPayeeExecuteQuery(final TransactionPayees searchRecord) {
		List<TransactionPayees> returnList = ocmtransRepository.txnPayeeExecuteQuery(searchRecord);
		returnList.forEach(element -> {
			if (element.getPayeeCorporateId() != null) {
				final String corporates = ocmtransRepository.cgfkchkTxnPayeeTxnPayee(element.getPayeeCorporateId());
				if (corporates != null) {
					element.setCorporateName(corporates);
				} else {
					element.setCorporateName("CorporateValidate");
				}
			}
			if (element.getPayeePersonId() != null) {
				final String persons = ocmtransRepository.cgfkchkTxnPayeeTxnPayee(element.getPayeePersonId());
				if (persons != null) {
					element.setLastName(persons);
				} else {
					element.setLastName("PersonValidate");
				}
			}
		});
		return returnList;
	}

	/**
	 * Insert the records from database table
	 *
	 * @Param commitBean
	 * @return Integer
	 */
	@Transactional
	public Integer txnPayeeCommit(final TransactionPayeesCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (TransactionPayees obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
				obj.setModifyUserId(commitBean.getCreateUserId());
				obj.setJnOracleUser(commitBean.getCreateUserId());
				obj.setJnOperation("INS");
			}
			liReturn = txnPayeeInsertTransactionPayees(commitBean.getInsertList());
			//transactionPayeesTjnService.transactionPayessTJNTrigger(commitBean.getInsertList());
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (TransactionPayees obj : commitBean.getUpdateList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
				obj.setModifyUserId(commitBean.getCreateUserId());
				obj.setJnOracleUser(commitBean.getCreateUserId());
				obj.setJnOperation("UPD");
			}
			liReturn = txnPayeeUpdateTransactionPayees(commitBean.getUpdateList());
			//transactionPayeesTjnService.transactionPayessTJNTrigger(commitBean.getUpdateList());
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			for (TransactionPayees obj : commitBean.getDeleteList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = txnPayeeDeleteTransactionPayees(commitBean.getDeleteList());
		}
		return liReturn;
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOffenderAlerts
	 */
	@Transactional
	public Integer txnPayeeInsertTransactionPayees(final List<TransactionPayees> lstOffenderAlerts) {
		Long payeeSeqTemp = ocmtransRepository.payeeSeqInsert(lstOffenderAlerts.get(0).getTxnType());
		for (TransactionPayees obj : lstOffenderAlerts) {
			if (obj.getTxnType() != null) {
				obj.setPayeeSeq(payeeSeqTemp);
			}
			if (obj.getDefaultPayeeFlag() != null && obj.getDefaultPayeeFlag().equals("true")) {
				obj.setDefaultPayeeFlag("Y");
			} else {
				obj.setDefaultPayeeFlag("N");
			}
			payeeSeqTemp++;
		}
		return ocmtransRepository.txnPayeeInsertTransactionPayees(lstOffenderAlerts);
	}

	/**
	 * Update the records from database table
	 *
	 * @param lstOffenderAlerts
	 */
	@Transactional
	public Integer txnPayeeUpdateTransactionPayees(final List<TransactionPayees> lstOffenderAlerts) {
		for (TransactionPayees obj : lstOffenderAlerts) {
			if (obj.getDefaultPayeeFlag() != null && obj.getDefaultPayeeFlag().equals("true")) {
				obj.setDefaultPayeeFlag("Y");
			} else {
				obj.setDefaultPayeeFlag("N");
			}
		}
		return ocmtransRepository.txnPayeeUpdateTransactionPayees(lstOffenderAlerts);
	}

	/**
	 * Delete the records from database table
	 *
	 * @param lstOffenderAlerts
	 */
	@Transactional
	public Integer txnPayeeDeleteTransactionPayees(final List<TransactionPayees> lstOffenderAlerts) {
		for (TransactionPayees obj : lstOffenderAlerts) {
			if (obj.getDefaultPayeeFlag() != null && obj.getDefaultPayeeFlag().equals("true")) {
				obj.setDefaultPayeeFlag("Y");
			} else {
				obj.setDefaultPayeeFlag("N");
			}
		}
		return ocmtransRepository.txnPayeeDeleteTransactionPayees(lstOffenderAlerts);
	}

	/**
	 * This method is used to execute a record group
	 */
	public List<DeductionTypes> cgfkTxnTypeCreditObligatioRecordGroup() {
		List<DeductionTypes> resultList = ocmtransRepository.cgfkTxnTypeCreditObligatioRecordGroup();
		resultList.forEach(data -> {
			data.setDeductionCategory(data.getDescription());
			data.setDescription(data.getCode());
		});
		return resultList;

	}

	/**
	 * This method is used to execute a record group
	 */
	public List<Persons> cgfkTxnPayeePayeePersonIdRecordGroup() {
		List<Persons> resultList = ocmtransRepository.cgfkTxnPayeePayeePersonIdRecordGroup();
		resultList.forEach(data -> {
			if (data.getPersonId() != null) {
				data.setCode(data.getPersonId().toString());
				data.setDescription(data.getCode());
			}
		});
		return resultList;

	}

	/**
	 * This method is used to execute a record group
	 */
	public List<Corporates> cgfkTxnPayeePayeeCorporateRecordGroup() {
		List<Corporates> resultList = ocmtransRepository.cgfkTxnPayeePayeeCorporateRecordGroup();
		resultList.forEach(data -> {
			if (data.getCorporateId() != null) {
				data.setCode(data.getCorporateId().toString());
				data.setDescription(data.getCode());
			}
		});
		return resultList;

	}

	/**
	 * This method is used to execute a record group
	 */
	public List<Caseloads> cgfkCsldTtCaseloadIdRecordGroup() {
		return ocmtransRepository.cgfkCsldTtCaseloadIdRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 */
	public List<ReferenceCodes> cgfkTxnTypeTxnUsageRecordGroup() {
		List<ReferenceCodes> resultList = ocmtransRepository.cgfkTxnTypeTxnUsageRecordGroup();
		resultList.forEach(data -> {
			data.setDomain(data.getDescription());
			data.setDescription(data.getCode());
		});
		return resultList;
	}
	
	public String txnTypeValidation(final String txnType,String userName) {
		return ocmtransRepository.txnTypeValidation(txnType,userName);
	}

}