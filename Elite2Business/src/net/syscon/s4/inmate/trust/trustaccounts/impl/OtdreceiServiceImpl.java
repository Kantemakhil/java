package net.syscon.s4.inmate.trust.trustaccounts.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.OffenderTransactionsCommitBean;
import net.syscon.s4.common.beans.OtddisbuProcessTransactionsBean;
import net.syscon.s4.common.beans.ProcessGlTransNewBean;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.SystemProfilesCommitBean;
import net.syscon.s4.common.beans.TransactionTypes;
import net.syscon.s4.core.EliteDateService;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.inmate.trust.trustaccounts.OtdreceiRepository;
import net.syscon.s4.inmate.trust.trustaccounts.OtdreceiService;
import net.syscon.s4.pkgs.common.CommonService;
import net.syscon.s4.pkgs.deductions.DeductionsService;
import net.syscon.s4.pkgs.financial.FinancialService;
import net.syscon.s4.pkgs.trust.TrustService;

@Service
public class OtdreceiServiceImpl extends BaseBusiness implements OtdreceiService {

	@Autowired
	private OtdreceiRepository otdreceiRepository;

	@Autowired
	private EliteDateService eliteDateService;
	
	@Autowired
	private TrustService trustService;
	
	@Autowired
	private CommonService commonService;
	
	@Autowired
	private FinancialService financialService;
	
	@Autowired
	private DeductionsService deductionsService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OtdreceiServiceImpl.class.getName());

	/**
	 * Creates new OtdreceiServiceImpl class Object
	 */
	public OtdreceiServiceImpl() {
		// OtdreceiServiceImpl
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<Object> offTxn1WhenValidateRecord(AccountCodes paramBean) throws SQLException {
		return null;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public TransactionTypes CgfkchkOffTxn1OffTxnTxn(TransactionTypes paramBean) throws SQLException {
		TransactionTypes transactionTypesList = otdreceiRepository.cgfkchkOffTxn1OffTxnTxn(paramBean);
		return transactionTypesList;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<Object> CgwhenNewFormInstance() throws SQLException {
		return null;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<OffenderTransactions> offTxn1ExecuteQuery(OffenderTransactions searchRecord) throws SQLException {
		return otdreceiRepository.offTxn1ExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFF_TXN1
	 *
	 * @throws SQLException
	 */
	@Transactional(rollbackFor= Exception.class)
	public List<OffenderTransactions> offTxn1Commit(OffenderTransactionsCommitBean commitBean) throws SQLException {
		List<OffenderTransactions> liReturn = new ArrayList<>();
		// insertRecords
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			commitBean.getInsertList() .forEach(data->{
				data.setCreateUserId(commitBean.getCreateUserId());
				data.setModifyUserId(commitBean.getCreateUserId());
			});
			
			liReturn = keyCommit(commitBean.getInsertList());
		}
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<OffenderTransactions> offTxnExecuteQuery(OffenderTransactions searchRecord) throws SQLException {
		return otdreceiRepository.offTxn1ExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFF_TXN
	 *
	 * @throws SQLException
	 */
	@Transactional
	public Integer offTxnCommit(OffenderTransactionsCommitBean commitBean) throws SQLException {
		int liReturn = 0;
		// insertRecords
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			liReturn = otdreceiRepository.offTxn1InsertOffenderTransactions(commitBean.getInsertList());
		}
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<SystemProfiles> sysPflExecuteQuery(SystemProfiles searchRecord) throws SQLException {
		return otdreceiRepository.sysPflExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstSYS_PFL
	 *
	 * @throws SQLException
	 */
	@Transactional
	public Integer sysPflCommit(SystemProfiles commitBean) throws SQLException {
		return null;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<TransactionTypes> cgfkOffTxn1TxnTypeRecordGroup(final String caseloadId) throws SQLException {
		return otdreceiRepository.cgfkOffTxn1TxnTypeRecordGroup(caseloadId);

	}

	@Override
	public Integer sysPflCommit(SystemProfilesCommitBean commitBean) throws SQLException {
		return null;
	}

	@Override
	public String otdreceiChkReceiptFlag(String txnType, String caseloadId) {
		return otdreceiRepository.OtdreceiChkReceiptFlag(txnType, caseloadId);
	}

	public List<OffenderTransactions> keyCommit(List<OffenderTransactions> paramBean) {
		OffenderTransactions offenderTransactions = new OffenderTransactions();
		offenderTransactions.setTxnEntrySeq(0);
		String errorMessage = null;
		Integer txnId = 0;
		try {
			txnId = otdreceiRepository.genTrustTrans("TXN_ID");
		} catch (Exception e) {
			offenderTransactions.setErrorMessage("Error(off_txn_pre_commit.gen_trust_trans)");
			logger.error("Error(off_txn_pre_commit.gen_trust_trans)", e);
			throw new RuntimeException(offenderTransactions.getErrorMessage());
		}
		int seq = 0;
		for (OffenderTransactions bean : paramBean) {
			bean.setTxnId(txnId);
			bean.setTxnEntrySeq(seq);
			bean.setTxnEntryDate(trunc(eliteDateService.getDBTime()));
			//String accountStatus = otdreceiRepository.chkAccountStatus(bean.getCaseloadId(), bean.getOffenderId());
			//Procedure call
			Map<String, Object> accountStatus1 = trustService.chkAccountStatus(bean.getCaseloadId(), new BigDecimal(bean.getOffenderId()));
			String accountStatus = (String) accountStatus1.get("P_OPEN_AN_ACCOUNT");
			if ("Y".equals(accountStatus)) {
				try {
					bean = reOpenTrustAccount(bean);
				} catch (Exception e) {
					errorMessage = "Other Error in REOPEN_TRUST_ACCOUNT";
					bean.setErrorMessage(errorMessage);
					throw new RuntimeException(errorMessage);
				}
			}
			if (bean.getErrorMessage() != null) {
				errorMessage = bean.getErrorMessage();
				throw new RuntimeException(errorMessage);
			}
			bean.setTxnEntrySeq(seq);
			bean = offTxnPreInsert(bean);
			if (bean.getErrorMessage() != null) {
				errorMessage = bean.getErrorMessage();
				throw new RuntimeException(errorMessage);
			}
			bean = offTxnPostInsert(bean);
			if (bean.getErrorMessage() != null) {
				errorMessage = bean.getErrorMessage();
				throw new RuntimeException(errorMessage);
			}
			seq = bean.getTxnEntrySeq();

		}
		
			return paramBean;
		
	}

	private OffenderTransactions offTxnPostInsert(OffenderTransactions bean) {
		try {
			
			ProcessGlTransNewBean glBean = new ProcessGlTransNewBean();
			glBean.setpTransSeq(Long.valueOf(bean.getTxnEntrySeq()));
			if ("Y".equals(bean.getAccountClosedFlag())) {
				bean.setTxnEntrySeq(bean.getTxnEntrySeq()+1);
			} 
			glBean.setpCsldId(bean.getCaseloadId());
			glBean.setpTransType(bean.getTxnType());
			glBean.setpOperationType(null);
			glBean.setpTransAmount(bean.getTxnEntryAmount());
			glBean.setpTransNumber(Long.valueOf(bean.getTxnId()));
			glBean.setpTransDate(trunc(eliteDateService.getDBTime()));
			glBean.setpTransDesc(bean.getTxnEntryDesc());
			glBean.setpModuleName("OTDRECEI");
			glBean.setpOffId(bean.getOffenderId());
			glBean.setpOffBookId(bean.getOffenderBookId());
			glBean.setpSubActTypeDr(null);
			glBean.setpSubActTypeCr(bean.getSubAccountType());
			glBean.setpPayeePersId(null);
			glBean.setpPayeeCorpId(null);
			glBean.setpPayeeNameText(null);
			glBean.setpGlSqnc(0l);
			glBean.setpOffDedId(null);
			//otdreceiRepository.processGlTransNew(glBean);
			//Procedure call
			trustService.processGlTransNew(bean.getCaseloadId(), bean.getTxnType(), null, bean.getTxnEntryAmount(), bean.getTxnId(),
					trunc(eliteDateService.getDBTime()), bean.getTxnEntryDesc(), glBean.getpTransSeq().intValue(), "OTDRECEI", bean.getOffenderId().intValue(),
					bean.getOffenderBookId(), null, bean.getSubAccountType(), null, null, null, 0, null, bean.getCreateUserId());
		} catch (Exception e) {
			logger.error("Error(off_txn_post_insert.Trust.process_gl_trans_new):", e);
			bean.setErrorMessage("Error(off_txn_post_insert.Trust.process_gl_trans_new):");
			throw new RuntimeException("Error(off_txn_post_insert.Trust.process_gl_trans_new):");
		}

		try {
			//otdreceiRepository.updateOffenderBalance(bean);
			//Procedure call
			bean.setToSubAccountType(bean.getSubAccountType());
			trustService.updateOffenderBalance(bean, bean.getCreateUserId());
		} catch (Exception e) {
			logger.error("Error(off_txn_post_insert.Trust.update_offender_balance):");
			bean.setErrorMessage("Error(off_txn_post_insert.Trust.update_offender_balance):");
			throw new RuntimeException("Error(off_txn_post_insert.Trust.update_offender_balance):");
		}
		TransactionTypes daysCur = otdreceiRepository.daysCur(bean.getTxnType());
		if ("R".equals(daysCur.getTxnUsage()) && daysCur.getDays() > 0) {
			Integer holdNumber = otdreceiRepository.genTrustTrans("HOLD_NUMBER");
			try {
//				otdreceiRepository.processHold(bean.getTxnId(), bean.getCaseloadId(), bean.getOffenderId(),
//						bean.getTxnType(), daysCur.getDays(), bean.getSubAccountType(), bean.getTxnEntryAmount(),
//						bean.getTxnEntryDesc(), bean.getTxnReferenceNumber(), bean.getTxnId(), holdNumber);
				//Procedure call
				commonService.processHold(bean.getTxnId(), bean.getCaseloadId(), bean.getOffenderId(),
						bean.getTxnType(), daysCur.getDays(), bean.getSubAccountType(), bean.getTxnEntryAmount(),
						bean.getTxnEntryDesc(), bean.getTxnReferenceNumber(), bean.getTxnId(), holdNumber, bean.getCreateUserId());
				
				Integer maxTxnEntrySeq = otdreceiRepository.getMaxTxnEntrySeq(bean.getTxnId());
				bean.setTxnEntrySeq(maxTxnEntrySeq);
			} catch (Exception e) {
				logger.error("Error(off_txn_post_insert.process hold)");
				throw new RuntimeException("Error(off_txn_post_insert.process hold)");
				
			}
		} else {
			try {
//				otdreceiRepository.financialDoDuctionsFinancial(bean.getCaseloadId(), bean.getOffenderId(),
//						bean.getOffenderBookId(), bean.getTxnType(), bean.getTxnId(),
//						trunc(eliteDateService.getDBTime()), bean.getSubAccountType(), "Y", bean.getTxnEntryAmount(),
//						null, bean.getTxnEntryAmount(), bean.getTxnEntrySeq(), "");
				//Procedure call
				financialService.doDeductionsFinancial(bean.getCaseloadId(), bean.getOffenderId(),
						bean.getOffenderBookId(), bean.getTxnType(), Long.valueOf(bean.getTxnId()),
						trunc(eliteDateService.getDBTime()), bean.getSubAccountType(), "Y", new BigDecimal(bean.getTxnEntryAmount()),
						null, bean.getTxnEntryAmount().longValue(), bean.getTxnEntrySeq(), "");
				
						Integer maxTxnEntrySeq = otdreceiRepository.getMaxTxnEntrySeq(bean.getTxnId());
						bean.setTxnEntrySeq(maxTxnEntrySeq);
			} catch (Exception e) {
				logger.error("Other error in Financial.do_deductions_financial", e);
				bean.setErrorMessage("Other error in Financial.do_deductions_financial");
				throw new RuntimeException("Other error in Financial.do_deductions_financial");
			}
		}
		try {
			//otdreceiRepository.deductionsGetAcAndSetIndDate(bean.getOffenderId(), bean.getCaseloadId());
			//Procedure call
			deductionsService.getAcAndSetIndDate(bean.getOffenderId(), bean.getCaseloadId(), bean.getCreateUserId());
		} catch (Exception e) {
			logger.error("Error(off_txn_post_insert.Trust.get_ac_and_set_ind_date)", e);
			bean.setErrorMessage("Error(off_txn_post_insert.Trust.get_ac_and_set_ind_date)");
			throw new RuntimeException(bean.getErrorMessage());
		}
		return bean;
	}

	private OffenderTransactions offTxnPreInsert(OffenderTransactions bean) {
		OffenderTransactions offenderTransactions = new OffenderTransactions();
		String receiptFlag = null;
		String recieptNumber = null;
		String postingType = null;
		String subActType = null;
		Map<String, Object> getSubActTypeMap = new HashMap<String, Object>();
		try {
			//receiptFlag = otdreceiRepository.chkAccountStatus(bean.getCaseloadId(), bean.getOffenderId());
			//Procedure call
			Map<String, Object> accountStatus1 = trustService.chkAccountStatus(bean.getCaseloadId(), new BigDecimal(bean.getOffenderId()));
			receiptFlag = (String) accountStatus1.get("P_OPEN_AN_ACCOUNT");			
		} catch (Exception e) {
			offenderTransactions.setErrorMessage("Error(off_txn_pre_insert.Trust.check_account_status)");
			throw new RuntimeException(offenderTransactions.getErrorMessage());
		}
		if (bean.getTxnEntrySeq() == 0) {
			bean.setTxnEntrySeq(0);
		}
		bean.setTxnEntrySeq(bean.getTxnEntrySeq() + 1);
		receiptFlag = otdreceiChkReceiptFlag(bean.getTxnType(), bean.getCaseloadId());
		if ("Y".equals(receiptFlag)) {
			Integer reciptNum = otdreceiRepository.genTrustTrans("SEQUENCE_" + bean.getCaseloadId());
			recieptNumber = bean.getCaseloadId() + reciptNum;
			while (recieptNumber.length() < 10) {
				recieptNumber = "0" + recieptNumber;
			}
			bean.setReceiptNumber(recieptNumber);
		}
		try {
			//getSubActTypeMap = otdreceiRepository.getSubActType("OTDRECEI", bean.getTxnType(), bean.getCaseloadId());
			OtddisbuProcessTransactionsBean bean1 = new OtddisbuProcessTransactionsBean();
			bean1.setpModuleName("OTDRECEI");
			bean1.setpTxnType(bean.getTxnType());
			bean1.setpCaseloadId(bean.getCaseloadId());
			//Procedure call
			getSubActTypeMap = trustService.getSubActType(bean1);
			if (getSubActTypeMap != null) {
				if (getSubActTypeMap.get("P_TXN_POST_TYPE") != null) {
					postingType = getSubActTypeMap.get("P_TXN_POST_TYPE").toString();
				}
				if (getSubActTypeMap.get("P_SUB_ACT_TYPE") != null) {
					subActType = getSubActTypeMap.get("P_SUB_ACT_TYPE").toString();
				}
			}

		} catch (Exception e) {
			offenderTransactions.setErrorMessage("Error(off_txn_pre_insert.Trust.get_sub_act_type)");
			throw new RuntimeException(offenderTransactions.getErrorMessage());
		}

		bean.setTxnPostingType(postingType);
		bean.setSubAccountType(subActType);
		bean.setPreWithholdAmount(bean.getTxnEntryAmount());
		bean.setSlipPrintedFlag("N");

		Integer insertResult = otdreceiRepository.insrtIntoOffenderTransForm(bean);
		if (insertResult == 1) {
			return bean;
		}

		return null;
	}

	private OffenderTransactions reOpenTrustAccount(OffenderTransactions bean) {
		String pSubActtype = "";
		String pTxnPostingType = "";
		int result = otdreceiRepository.reopenClosedTrustAccount(bean.getOffenderId(), bean.getCaseloadId(),bean.getModifyUserId());
		if (result != 0) {
			bean.setTxnEntrySeq(bean.getTxnEntrySeq() + 1);
			String rTxnType = otdreceiRepository.getRTxnType("OTDOPCTA", bean.getCaseloadId());
			AccountCodes accountCode = otdreceiRepository.getSubAccType("OTDOPCTA", rTxnType, bean.getCaseloadId());
			if (accountCode != null) {
				pSubActtype = accountCode.getSubAccountType();
				pTxnPostingType = accountCode.getTxnPostingType();
			}
			try {
				bean.setTxnEntrySeq(bean.getTxnEntrySeq() + 1);
				OffenderTransactions procedureBean = new OffenderTransactions();
				procedureBean.setTxnId(bean.getTxnId());
				procedureBean.setTxnEntrySeq(bean.getTxnEntrySeq());
				procedureBean.setOffenderId(bean.getOffenderId());
				procedureBean.setCaseloadId(bean.getCaseloadId());
				procedureBean.setOffenderBookId(bean.getOffenderBookId());
				procedureBean.setTxnPostingType(pTxnPostingType);
				procedureBean.setTxnType(rTxnType);
				procedureBean.setTxnEntryDesc("Re-Open Closed Account");
				procedureBean.setTxnEntryDate(eliteDateService.getDBTime());
				procedureBean.setSubAccountType(pSubActtype);
				procedureBean.setToSubAccountType(pSubActtype);
				procedureBean.setSlipPrintedFlag("N");
				procedureBean.setCreateUserId(bean.getCreateUserId());
				procedureBean.setTxnEntryAmount(new Double(0.0));
				//otdreceiRepository.insertIntoOffenderTransaction(procedureBean);
				//Procedure call
				trustService.insertIntoOffenderTrans(procedureBean);
			} catch (Exception e) {
				logger.error("Error(reopen_trust_account.Trust.insert_into_offender_trans):", e);
				bean.setErrorMessage("Error(reopen_trust_account.Trust.insert_into_offender_trans)");
				return bean;
			}
			try {

				ProcessGlTransNewBean glBean = new ProcessGlTransNewBean();
				glBean.setpCsldId(bean.getCaseloadId());
				glBean.setpTransType(rTxnType);
				glBean.setpOperationType(null);
				glBean.setpTransAmount(0.0);
				glBean.setpTransNumber(Long.valueOf(bean.getTxnId()));
				glBean.setpTransDate(eliteDateService.getDBTime());
				glBean.setpTransDesc("Re-Open Closed Account");
				glBean.setpTransSeq(Long.valueOf(bean.getTxnEntrySeq()));
				glBean.setpModuleName("OTDOPCTA");
				glBean.setpOffId(bean.getOffenderId());
				glBean.setpOffBookId(bean.getOffenderBookId());
				glBean.setpSubActTypeDr(null);
				glBean.setpSubActTypeCr(bean.getSubAccountType());
				glBean.setpPayeePersId(null);
				glBean.setpPayeeCorpId(null);
				glBean.setpPayeeNameText(null);
				glBean.setpGlSqnc(0l);
				glBean.setpOffDedId(null);
			//	otdreceiRepository.processGlTransNew(glBean);
				//Procedure call
				trustService.processGlTransNew(bean.getCaseloadId(), glBean.getpTransType(), null, glBean.getpTransAmount(), bean.getTxnId(),
						trunc(eliteDateService.getDBTime()), glBean.getpTransDesc(), bean.getTxnEntrySeq(), "OTDOPCTA", bean.getOffenderId().intValue(),
						bean.getOffenderBookId(), null, bean.getSubAccountType(), null, null, null, 0, null, bean.getCreateUserId());
				bean.setTxnEntrySeq(bean.getTxnEntrySeq() + 1);
			} catch (Exception e) {
				logger.error("Error(reopen_trust_account.Trust.process_gl_trans_new)", e);
				bean.setErrorMessage("Error(reopen_trust_account.Trust.process_gl_trans_new)");
				return bean;
			}
		}

		return bean;
	}

	public Date trunc(final Date date) {
		if (date != null) {
			final Calendar calender = Calendar.getInstance();
			calender.setTime(date);
			calender.set(Calendar.HOUR, 0);
			calender.set(Calendar.MINUTE, 0);
			calender.set(Calendar.SECOND, 0);
			calender.set(Calendar.MILLISECOND, 0);
			final Date returnDate = calender.getTime();
			return returnDate;
		}
		return null;
	}

	@Override
	public String deductionsChkOffenderDeductions(String caseloasdId, Long offenderId, String txnType,
			Integer shadowId) {
		//return otdreceiRepository.deductionsChkOffenderDeductions(caseloasdId, offenderId, txnType, shadowId);
		//Procedure call
		return deductionsService.chkOffenderDeductions(caseloasdId, offenderId, txnType, shadowId.longValue());
	}
}