package net.syscon.s4.inmate.trust.generalledger.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.SystemProfilesCommitBean;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.inmate.beans.BankChequeRegisters;
import net.syscon.s4.inmate.beans.BankClearReconcilesTmp;
import net.syscon.s4.inmate.beans.BankClearReconcilesTmpCommitBean;
import net.syscon.s4.inmate.beans.BankReconAudits;
import net.syscon.s4.inmate.beans.BankReconAuditsCommitBean;
import net.syscon.s4.inmate.beans.GlTransactions;
import net.syscon.s4.inmate.beans.GlTransactionsCommitBean;
import net.syscon.s4.inmate.trust.generalledger.OtdbacreRepository;
import net.syscon.s4.inmate.trust.generalledger.OtdbacreService;
import net.syscon.s4.inmate.trust.trustaccounts.impl.OtdaaccoServiceImpl;
import net.syscon.s4.pkgs.otdbacre.OtdbacrePkgService;

/**
 * Class OtdbacreServiceImpl
 */
@Service
public class OtdbacreServiceImpl extends BaseBusiness implements OtdbacreService {
	
	
	private static Logger logger = LogManager.getLogger(OtdaaccoServiceImpl.class.getName());

	@Autowired
	private OtdbacreRepository otdbacreRepository;

	@Autowired
	private OtdbacrePkgService otdbacrepkgservice;
	/**
	 * Creates new OtdbacreServiceImpl class Object
	 */
	public OtdbacreServiceImpl() {
		// OtdbacreServiceImpl
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public OmsModules createFormGlobals(final OmsModules paramBean) {
		final OmsModules omsModules = otdbacreRepository.createFormGlobals(paramBean);
		return omsModules;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public AccountCodes cgfkchkGlTxnGlTxnAcCode(final AccountCodes paramBean) {
		final AccountCodes accountCodes = otdbacreRepository.cgfkchkGlTxnGlTxnAcCode(paramBean);
		return accountCodes;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<Object> cgwhenNewFormInstance() {
		return null;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @
	 */
	public List<GlTransactions> glTxnExecuteQuery(final GlTransactions searchRecord) {
		return otdbacreRepository.glTxnExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstGL_TXN
	 *
	 * @
	 */
	@Transactional
	public Integer glTxnCommit(final GlTransactionsCommitBean commitBean) {
		int liReturn = 0;
		// insertRecords
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			liReturn = otdbacreRepository.glTxnInsertGlTransactions(commitBean.getInsertList());
		}
		// updateRecords
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			liReturn = otdbacreRepository.glTxnUpdateGlTransactions(commitBean.getUpdateList());
		}
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @
	 */
	public List<BankReconAudits> bankRcExecuteQuery(final BankReconAudits searchRecord) {
		return otdbacreRepository.bankRcExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstBANK_RC
	 *
	 * @
	 */
	@Transactional
	public Integer bankRcCommit(final BankReconAuditsCommitBean CommitBean) {
		final int liReturn = 0;
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @
	 */
	public List<SystemProfiles> sysPflExecuteQuery(final SystemProfiles searchRecord) {
		return otdbacreRepository.sysPflExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstSYS_PFL
	 *
	 * @
	 */
	@Transactional
	public Integer sysPflCommit(final SystemProfilesCommitBean commitBean) {
		int liReturn = 0;
		// insertRecords
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			liReturn = otdbacreRepository.sysPflInsertSystemProfiles(commitBean.getInsertList());
		}
		// deleteRecords
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().forEach(bo -> {
				bo.setModifyUserId(commitBean.getCreateUserId());
			});
			liReturn = otdbacreRepository.sysPflDeleteSystemProfiles(commitBean.getDeleteList());
		}
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @
	 */
	public List<BankClearReconcilesTmp> bcrTmpExecuteQuery(final BankClearReconcilesTmp searchRecord) {
		BigDecimal temp = null;
//		Integer fetchTmp = otdbacreRepository.fetchTemp(searchRecord);
		//Procedure call
		GlTransactions objGlTransactions=new GlTransactions();
		objGlTransactions.setCaseloadId(searchRecord.getCaseloadId());
		objGlTransactions.setAccountCode(BigDecimal.valueOf(searchRecord.getAccountCode()));
		objGlTransactions.setSelectMode(searchRecord.getCgNbtAccountCode());
		objGlTransactions.setBankStatementDate(searchRecord.getCgnbtBankStatementDate());
		objGlTransactions.setLastReconDate(searchRecord.getLastReconciledDate());
		otdbacrepkgservice.fetchBankClearReconcile(objGlTransactions, searchRecord.getCreateUserId());
		
		BigDecimal currentBal = otdbacreRepository.getCurrentBalfromCaseloadCurrentTxns(searchRecord.getAccountCode(),
				searchRecord.getCaseloadId());
	
		if (searchRecord.getPlusTxnEntryAmount() == null) {
			searchRecord.setPlusTxnEntryAmount(BigDecimal.ZERO);
		}
		if (searchRecord.getMinusTxnEntryAmount() == null) {
			searchRecord.setMinusTxnEntryAmount(BigDecimal.ZERO);
		}
		if (searchRecord.getTxnEntryAmount() == null) {
			searchRecord.setTxnEntryAmount(BigDecimal.ZERO);
		}
		if (searchRecord.getAccountCode() != null && searchRecord.getCaseloadId() != null) {
			List<GlTransactions> crdrList = otdbacreRepository.getBalancecrdrList(searchRecord.getAccountCode(),
					searchRecord.getCaseloadId());
			for (GlTransactions glObj : crdrList) {
				if (glObj.getCgnbtPayeeNameTextOne() == null) {
					glObj.setCgnbtPayeeNameTextOne(BigDecimal.ZERO);
				}
				if (glObj.getCgnbtPayeeNameTextTwo() == null) {
					glObj.setCgnbtPayeeNameTextTwo(BigDecimal.ZERO);
				}
				BigDecimal lessOutDebits = glObj.getCgnbtPayeeNameTextOne();
				BigDecimal plussOutDebits = glObj.getCgnbtPayeeNameTextTwo();
				temp = searchRecord.getTxnEntryAmount().add(searchRecord.getPlusTxnEntryAmount());
				temp = temp.subtract(searchRecord.getMinusTxnEntryAmount());
				temp = temp.add(plussOutDebits.subtract(lessOutDebits));
			}
		}
		List<BankClearReconcilesTmp> returnList = otdbacreRepository.bcrTmpExecuteQuery(searchRecord);
		for (BankClearReconcilesTmp obj : returnList) {
			List<BankChequeRegisters> chkRefgList = otdbacreRepository.postQuery(obj.getTxnId(), obj.getTxnEntrySeq(),
					obj.getGlEntrySeq(), searchRecord.getCaseloadId());
			for (BankChequeRegisters bankObj : chkRefgList) {
				obj.setCgNbtDescription(bankObj.getReconClearFlag());
				obj.setCheckFlag(bankObj.getReconClearFlag());
				obj.setNbtBankStatementDate(bankObj.getBankStatementDate());
				obj.setTxnEntryDate(bankObj.getTransactionDate());

			}
			if (searchRecord.getAccountCode() != null && searchRecord.getCaseloadId() != null) {
				obj.setReconciledstmntbalance(temp);
				obj.setTrustbalance(currentBal);
				obj.setDifferenceBal(temp.subtract(currentBal));
			}

		}
		return returnList;

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstBCR_TMP
	 *
	 * @
	 */
	@Transactional
	public Integer bcrTmpCommit(final BankClearReconcilesTmpCommitBean commitBean) {
		int liReturn = 0;
		// updateRecords
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			liReturn = otdbacreRepository.bcrTmpUpdateBankClearReconcilesTmp(commitBean.getUpdateList());
		}
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @
	 */
	public List<GlTransactions> glTxn1ExecuteQuery(final GlTransactions searchRecord) {
		return otdbacreRepository.glTxnExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstGL_TXN1
	 *
	 * @
	 */
	@Transactional
	public Integer glTxn1Commit(final GlTransactionsCommitBean CommitBean) {
		final int liReturn = 0;
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @
	 */
	public List<GlTransactions> glTxn2ExecuteQuery(final GlTransactions searchRecord) {
		return otdbacreRepository.glTxnExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstGL_TXN2
	 *
	 * @
	 */
	@Transactional
	public Integer glTxn2Commit(final GlTransactionsCommitBean CommitBean) {
		final int liReturn = 0;
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @
	 */
	public List<GlTransactions> glTxn3ExecuteQuery(final GlTransactions searchRecord) {
		return otdbacreRepository.glTxnExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstGL_TXN3
	 *
	 * @
	 */
	@Transactional
	public Integer glTxn3Commit(final GlTransactionsCommitBean commitBean) {
		final int liReturn = 0;
		return liReturn;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<AccountCodes> cgfkGlTxnAccountCodeRecordGroup(final String caseloadId, final String caseloadType,final String userName) {
		return otdbacreRepository.cgfkGlTxnAccountCodeRecordGroup(caseloadId, caseloadType, userName);

	}

	/**
	 * This method is used to get Last Reconciled date
	 *
	 * @
	 */
	public BankReconAudits getPmaxDate(final String caseloadId, final Integer accCode) {
		return otdbacreRepository.getPmaxDate(caseloadId, accCode);
	}

	public Integer compareEffectiveDatec(String effectiveDate, String maxDate) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		SimpleDateFormat result = new SimpleDateFormat("dd/MM/yyyy");
		Integer compareEffectiveDatec=null;
		try {
			compareEffectiveDatec= otdbacreRepository.compareEffectiveDatec(result.format(dateFormat.parse(effectiveDate)),maxDate.equals("") ? "" :result.format(dateFormat.parse(maxDate)));
		}catch (Exception e) {
			logger.error("Exception in method compareEffectiveDatec "+e);
		}
		return compareEffectiveDatec;
	}

	public String getchcqueFlag(Long txnId, String txnEnterySeq, String glEntrySeq, Long cgnbtBankStatementDate) {
		Date date = new Date(cgnbtBankStatementDate);

		String val = otdbacreRepository.getchcqueFlag(txnId, txnEnterySeq, glEntrySeq, date);
		// Integer updateyCount =
		// otdbacreRepository.updateGlTransactions(date,txnId);
		// if ("N".equals(val) && updateyCount ==2 ) {
		// val= "X";

		// }

		return val;
	}

	public Integer updateGlTransactionswithN(Long txnId) {
		return otdbacreRepository.updateGlTransactionswithN(txnId);
	}

	public Integer updateBankReconAudits(final BankReconAudits searchBean) {
//		Date date = new Date(bankStatemntDate);
		return otdbacreRepository.updateBankReconAudits(searchBean);
	}

	public Integer insertBankReconAudits(final BankReconAudits searchBean) {
		Integer insertBankReconAudits=null;
		try {
			insertBankReconAudits=otdbacreRepository.insertBankReconAudits(searchBean);
		}catch (Exception e) {
			logger.error("Exception in method insertBankReconAudits "+e);
		}
		return insertBankReconAudits;
	}

	public BigDecimal getTrustBal(final Long accountCode, final String caseloadId) {
		return otdbacreRepository.getTrustBal(accountCode, caseloadId);
	}
}