package net.syscon.s4.inmate.trust.financialsmaintenance.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.OffenderTransactionsCommitBean;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.SystemProfilesCommitBean;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.inmate.beans.OffenderDeductions;
import net.syscon.s4.inmate.beans.OffenderDeductionsCommitBean;
import net.syscon.s4.inmate.beans.OffenderSubAccounts;
import net.syscon.s4.inmate.trust.financialsmaintenance.OtdcntacRepository;
import net.syscon.s4.inmate.trust.financialsmaintenance.OtdcntacService;
import net.syscon.s4.pkgs.deductions.DeductionsService;
import net.syscon.s4.pkgs.tag_security.TagSecurityService;
import net.syscon.s4.pkgs.trust.TrustService;
import net.syscon.s4.triggers.OffenderDeductionsHty;
import net.syscon.s4.triggers.OffenderDeductionsT2Service;
import net.syscon.s4.triggers.OffenderDeductionsThtyService;
import net.syscon.s4.triggers.OffenderDeductionsTjnService;

/**
 * Class OtdcntacServiceImpl
 * 
 */
@Service
public class OtdcntacServiceImpl extends BaseBusiness implements OtdcntacService {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OtdcntacServiceImpl.class.getName());

	@Autowired
	private OtdcntacRepository otdcntacRepository;
	@Autowired
	private TrustService trustservice;
	@Autowired
	private DeductionsService deductionsservice;
	@Autowired
	private TagSecurityService TagSecurityService;
	@Autowired
	private OffenderDeductionsT2Service offenderDeductionsT2Service; 
	@Autowired
	private OffenderDeductionsThtyService offenderDeductionsThtyService;
	@Autowired
	private OffenderDeductionsTjnService offenderDeductionsTjnService;
	/**
	 * Creates new OtdcntacServiceImpl class Object
	 */
	public OtdcntacServiceImpl() {
		// OtdcntacServiceImpl
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @
	 */
	public List<OffenderTransactions> offTxnExecuteQuery(final OffenderTransactions searchRecord) {
		return otdcntacRepository.offTxnExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param commitBean
	 *
	 */
	@Transactional
	public OffenderTransactions offTxnCommit(final OffenderTransactionsCommitBean commitBean) {
		OffenderTransactions liReturn = null;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			/*
			 * for(OffenderTransactions bean : commitBean.getInsertList()) {
			 * //bean.setCreateUserId(commitBean.getCreateUserId()); //
			 * bean.setModifyUserId(commitBean.getCreateUserId()); offTxnCommit.add(bean); }
			 */
			liReturn = offTxnInsertOffenderTransactions(commitBean.getInsertList());
		}
		return liReturn;
	}

	/**
	 * Insert the records from database table
	 *
	 * @param commitBean
	 *
	 */
	@Transactional(rollbackFor = Exception.class)
	public OffenderTransactions offTxnInsertOffenderTransactions(final List<OffenderTransactions> commitBean) {
		Integer returnData = 0;
		OffenderTransactions offTxnsPostQuery = new OffenderTransactions();
		String receiptNumber = ""; //  Modified By Yaseen to Fetch Receipt Number in HTML
		logger.info("Inside offTxnInsertOffenderTransactions to Create Trust Account with Inputs " + commitBean);
		try {
			for (final OffenderTransactions offenderTransactions : commitBean) {
				logger.info("Calling otdcntacRepository.checkAccountSatus to Check account status with Inputs " + offenderTransactions);
				Map<String, Object> chkAcntSatus = trustservice.chkAccountStatus(offenderTransactions.getCaseloadId(), new BigDecimal(offenderTransactions.getOffenderId()));
//				String chkAcntSatus = otdcntacRepository.checkAccountSatus(offenderTransactions);
				logger.info("Response from otdcntacRepository.checkAccountSatus - Check account status response " + chkAcntSatus);
				if (offenderTransactions.getTxnEntryAmount() != null) {
					logger.info("Calling otdcntacRepository.drAccountCodeCrAccountCode() to get debit and credit accounts of the caseload with current user");
					OffenderTransactions checkingDrCrAccount = otdcntacRepository.drAccountCodeCrAccountCode(offenderTransactions.getCreateUserId());
					logger.info("Response from otdcntacRepository.drAccountCodeCrAccountCode() to get debit and credit accounts of the caseload with current user " + checkingDrCrAccount);
					if (checkingDrCrAccount == null) {
						logger.info("checkingDrCrAccount response is null hence returning seal flag 7 : " + checkingDrCrAccount);
						offTxnsPostQuery.setSealFlag("7");
						return offTxnsPostQuery;
					}
					OffenderTransactions txnTypeDesc = otdcntacRepository.initPostTranTypesTxnType(offenderTransactions.getCreateUserId());
					logger.info("initPostTranTypesTxnType response " + txnTypeDesc);
					if (txnTypeDesc != null && txnTypeDesc.getTxnType() != null
							&& txnTypeDesc.getTxnEntryDesc() != null) {
						offenderTransactions.setTxnType(txnTypeDesc.getTxnType());
						offenderTransactions.setTxnEntryDesc(txnTypeDesc.getTxnEntryDesc());
					} else {
						offTxnsPostQuery.setSealFlag("3");
						return offTxnsPostQuery;
					}
				}
				if (offenderTransactions.getTxnType() != null) {
					OffenderTransactions subAccountType = otdcntacRepository
							.initPostTranTypesAccountType(offenderTransactions.getTxnType(),offenderTransactions.getCreateUserId());
					logger.info("initPostTranTypesAccountType response " + subAccountType);
					if (subAccountType != null && subAccountType.getSubAccountType() != null) {
						offenderTransactions.setSubAccountType(subAccountType.getSubAccountType());
					} else {
						offTxnsPostQuery.setSealFlag("4");
						return offTxnsPostQuery;
					}
				}
				Integer txnIdNextVal = otdcntacRepository.txnIdNextValData();
				logger.info("txnIdNextValData response " + txnIdNextVal);
				offenderTransactions.setTxnId(txnIdNextVal);
				if (offenderTransactions.getTxnId() != null) {
					Integer txnEntrySeqNextVal = otdcntacRepository
							.txnEntrySeqNextValData(offenderTransactions.getTxnId());
					logger.info("txnEntrySeqNextValData response " + txnEntrySeqNextVal);
					if (txnEntrySeqNextVal != null) {
						offenderTransactions.setTxnEntrySeq(txnEntrySeqNextVal);
					}
				}
				if (offenderTransactions.getTxnType() != null && offenderTransactions.getSubAccountType() != null) {
					String txnOprFlag = otdcntacRepository.transactionOperationsFlag(offenderTransactions);
					logger.info("transactionOperationsFlag response " + txnOprFlag);
					if ("Y".equals(txnOprFlag) && offenderTransactions.getTxnEntryAmount() > 0.0d) {
						Integer receiptNum = otdcntacRepository
								.genTrustRcptNmbr("SEQUENCE_" + offenderTransactions.getCaseloadId());
						logger.info("genTrustRcptNmbr response " + receiptNum);
						if (receiptNum != null) {
							String num = receiptNum.toString();
							while (num.length() < 6) {
								num = "0" + num;
							}
							offenderTransactions.setReceiptNumber(offenderTransactions.getCaseloadId() + num);
							receiptNumber = offenderTransactions.getReceiptNumber(); //  Modified By Yaseen to Fetch Receipt Number in HTML
						}
					} else {
						offenderTransactions.setReceiptNumber(null);
					}
				}
				if ("X".equals(chkAcntSatus.get("P_OPEN_AN_ACCOUNT"))) {
					OffenderTransactions trustAccounts = new OffenderTransactions();
					trustAccounts.setOffenderId(offenderTransactions.getOffenderId());
					trustAccounts.setCaseloadId(offenderTransactions.getCaseloadId());
					trustAccounts.setCurrentBalance(offenderTransactions.getTxnEntryAmount());
					trustAccounts.setCreateUserId(offenderTransactions.getCreateUserId());
					trustAccounts.setModifyUserId(offenderTransactions.getModifyUserId());
					Integer offenderTrustAccounts = otdcntacRepository.offTxnInsertOffenderTrustAccounts(trustAccounts);
					logger.info("offTxnInsertOffenderTrustAccounts response " + offenderTrustAccounts);
					if (offenderTrustAccounts < 0) {
						offTxnsPostQuery.setSealFlag("5");
						return offTxnsPostQuery;
					}
					BigDecimal accountCode = otdcntacRepository
							.accountCodeTemp(offenderTransactions.getSubAccountType(), offenderTransactions.getCreateUserId());
					logger.info("accountCodeTemp response " + accountCode);
					OffenderSubAccounts offTxns = new OffenderSubAccounts();
					if (accountCode != null) {
						offTxns.setCaseloadId(offenderTransactions.getCaseloadId());
						offTxns.setOffenderId(offenderTransactions.getOffenderId());
						offTxns.setLastTxnId(
								BigDecimal.valueOf(Long.valueOf(offenderTransactions.getTxnId().toString())));
						offTxns.setTrustAccountCode(Long.valueOf(accountCode.toString()));
						offTxns.setModifyDate(offenderTransactions.getModifyDate());
						offTxns.setCreateDatetime(offenderTransactions.getCreateDateTime());
						offTxns.setCreateUserId(offenderTransactions.getCreateUserId());
						offTxns.setModifyUserId(offenderTransactions.getCreateUserId());
						offTxns.setBalance(offenderTransactions.getTxnEntryAmount());
					}
					Integer offenderSubAccounts = otdcntacRepository.offTxnInsertOffenderSubAccounts(offTxns);
					logger.info("offTxnInsertOffenderSubAccounts response " + offenderSubAccounts);
					if (offenderSubAccounts < 0) {
					}
					offTxnsPostQuery.setCaseloadId(offenderTransactions.getCaseloadId());
					offTxnsPostQuery.setTxnType(offenderTransactions.getTxnType());
					offTxnsPostQuery.setTxnEntryAmount(offenderTransactions.getTxnEntryAmount());
					offTxnsPostQuery.setTxnId(offenderTransactions.getTxnId());
					offTxnsPostQuery.setTxnEntryDate(offenderTransactions.getTxnEntryDate());
					offTxnsPostQuery.setTxnEntryDesc(offenderTransactions.getTxnEntryDesc());
					offTxnsPostQuery.setTxnEntrySeq(offenderTransactions.getTxnEntrySeq());
					offTxnsPostQuery.setOffenderId(offenderTransactions.getOffenderId());
					offTxnsPostQuery.setOffenderBookId(offenderTransactions.getOffenderBookId());
					offTxnsPostQuery.setToSubAccountType(offenderTransactions.getSubAccountType());
					offTxnsPostQuery.setTxnPostingType(offenderTransactions.getTxnPostingType());
					offTxnsPostQuery.setCreateUserId(offenderTransactions.getCreateUserId());
//					otdcntacRepository.updateOffenderBalance(offTxnsPostQuery);
					logger.info("updateOffenderBalance call completed ");
					trustservice.updateOffenderBalance(offTxnsPostQuery, offenderTransactions.getCreateUserId());
				} else {
					offTxnsPostQuery.setSealFlag("2");
					return offTxnsPostQuery;
				}
			}
			returnData = otdcntacRepository.offTxnInsertOffenderTransactions(commitBean);
//			Integer glTransNew = otdcntacRepository.processGlTransNew(offTxnsPostQuery);
			Integer glTransNew =trustservice.processGlTransNew(offTxnsPostQuery.getCaseloadId(), offTxnsPostQuery.getTxnType(), null, offTxnsPostQuery.getTxnEntryAmount(), offTxnsPostQuery.getTxnId()
					, offTxnsPostQuery.getTxnEntryDate(), offTxnsPostQuery.getTxnEntryDesc(), offTxnsPostQuery.getTxnEntrySeq(), "OTDCNTAC", Integer.valueOf(offTxnsPostQuery.getOffenderId().toString()), offTxnsPostQuery.getOffenderBookId()
					, null, offTxnsPostQuery.getSubAccountType(), null, null, "", 0, null,offTxnsPostQuery.getCreateUserId());
			if (glTransNew == 0) {
			}
//			String deductionProc = otdcntacRepository.deductionTypesProcedure(offTxnsPostQuery);
			String deductionProc = null;
			try {
			deductionsservice.createDefaultDeductions(offTxnsPostQuery.getCaseloadId(), Integer.valueOf(offTxnsPostQuery.getOffenderId().toString()), offTxnsPostQuery.getCreateUserId());
			deductionProc = "TEST";
			} catch(Exception e) {
				deductionProc = null;
			}
			if (deductionProc != null && "TEST".equals(deductionProc)) {
				List<OffenderDeductions> deductionType = otdcntacRepository
						.deductionTypeTemp(offTxnsPostQuery.getOffenderId());
				logger.info("deductionTypeTemp response " + deductionType);
				String obligationGroups = otdcntacRepository.obligationGroups();
				logger.info("obligationGroups response " + deductionType);
				if (obligationGroups == null) {
					offTxnsPostQuery.setSealFlag("8");
					return offTxnsPostQuery;
				}
				if (deductionType != null && deductionType.size()>0 ) {
					for (int i = 0; i < deductionType.size(); i++) {
						String caseloadCode = otdcntacRepository.caseloadCodeTemp(offTxnsPostQuery.getDeductionType());
						logger.info("caseloadCodeTemp response " + caseloadCode);
						Integer groupId = otdcntacRepository.groupIdTemp(offTxnsPostQuery.getDeductionType());
						logger.info("groupIdTemp response " + groupId);
						if (groupId < 0) {
						}
						if (caseloadCode != null && ("BOTH".equals(caseloadCode) || "COMM".equals(caseloadCode))) {
							Integer returnValue = otdcntacRepository.offDedInsertOffenderDeductions(offTxnsPostQuery);
							logger.info("offDedInsertOffenderDeductions response " + returnValue);
							OffenderDeductions oldRec=	otdcntacRepository.gettingOldRecordOffendderDeduction(offTxnsPostQuery.getOffenderId(),offTxnsPostQuery.getDeductionType());
							OffenderDeductions newRec = new OffenderDeductions();
							newRec.setMaxTotalAmount(BigDecimal.valueOf(offTxnsPostQuery.getTxnEntryAmount()));
							newRec.setOffenderDeductionId(oldRec.getOffenderDeductionId());
							newRec.setInformationNumber(offTxnsPostQuery.getInfoNumber());
							newRec.setDeductionType(offTxnsPostQuery.getDeductionType());
							newRec.setCreateUserId(offTxnsPostQuery.getCreateUserId());
							newRec.setModifyUserId(offTxnsPostQuery.getCreateUserId());
							// calling trigger OFFENDER_DEDUCTIONS_T2
							offenderDeductionsT2Service.offenderDeductionsT2Trigger(newRec, oldRec, "UPDATE");
					    	OffenderDeductionsHty offenderDeductionhtty=new OffenderDeductionsHty();
					    	offenderDeductionhtty.setCreateUserId(newRec.getCreateUserId());
					    	// calling trigger OFFENDER_DEDUCTIONS_THTY
							offenderDeductionsThtyService.OffenderDeductionsThtyTrigger(offenderDeductionhtty, "UPDATE");
							// calling trigger OFFENDER_DEDUCTIONS_TJN
							//offenderDeductionsTjnService.offenderDeductionsTjn(newRec, oldRec, "UPDATE");
							OffenderDeductionsCommitBean commitbean = new OffenderDeductionsCommitBean();
							if (returnValue == 0) {
								offTxnsPostQuery.setSealFlag("8");
								return offTxnsPostQuery;
							}
						}
					}
				}
			}
//			String getIndDate = otdcntacRepository.getAcAndSetIndDate(offTxnsPostQuery);
//			logger.info("getAcAndSetIndDate response " + getIndDate);
			String getIndDate = null;
			try {
				deductionsservice.getAcAndSetIndDate(offTxnsPostQuery.getOffenderId(), offTxnsPostQuery.getCaseloadId(),
						offTxnsPostQuery.getCreateUserId());
				getIndDate = "GETINDDATE";
			} catch (Exception e) {
				getIndDate = null;
			}
			if (getIndDate != null && "GETINDDATE".equals(getIndDate)) {
			}
			if (returnData == 1) {
				offTxnsPostQuery.setSealFlag("1");
				offTxnsPostQuery.setReceiptNumber(receiptNumber); //  Modified By Yaseen to Fetch Receipt Number in HTML  
				return offTxnsPostQuery;
			}
		} catch (Exception e) {
			logger.error("offTxnCommit : ", e);
		}
		return offTxnsPostQuery;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @
	 */
	public List<SystemProfiles> sysPflExecuteQuery(final SystemProfiles searchRecord) {
		return otdcntacRepository.sysPflExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param commitBean
	 *
	 */
	@Transactional
	public Integer sysPflCommit(final SystemProfilesCommitBean commitBean) {
		final int liReturn = 0;
		return liReturn;
	}

	/**
	 * This method will update the transaction in the table
	 * 
	 * @param offTrans
	 * @return String
	 */
	public String checkAccountSatus(final OffenderTransactions offenderTransactions) {
		 	Map<String, Object> chkAcntSatus = trustservice.chkAccountStatus(offenderTransactions.getCaseloadId(), new BigDecimal(offenderTransactions.getOffenderId()));;
		 	return (String) chkAcntSatus.get("P_OPEN_AN_ACCOUNT");
	}

	/**
	 * This method will update the transaction in the table
	 * 
	 * @return String
	 */
	public String getGroupPrivilege(final String username) {
		return	TagSecurityService.getGroupPrivilege("OTDCNTAC",username);
//		return otdcntacRepository.getGroupPrivilege();
	}

}
