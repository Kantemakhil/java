package net.syscon.s4.pkgs.trust.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.InstitutionMiniBalances;
import net.syscon.s4.common.beans.OtddisbuProcessTransactionsBean;
import net.syscon.s4.common.beans.TransactionTypes;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.im.beans.BankChequeData;
import net.syscon.s4.im.beans.ChkFreezeDisbursements;
import net.syscon.s4.im.beans.OffenderDeductionReceipts;
import net.syscon.s4.im.beans.OffenderLimits;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.inmate.beans.CaseloadDeductionProfiles;
import net.syscon.s4.inmate.beans.GlTransactions;
import net.syscon.s4.inmate.beans.OffenderBeneficiaries;
import net.syscon.s4.inmate.beans.OffenderDeductions;
import net.syscon.s4.inmate.beans.OffenderSubAccounts;
import net.syscon.s4.inmate.trust.trustaccounts.OcuovrobRepository;
import net.syscon.s4.pkgs.trust.TrustRepository;
import net.syscon.s4.pkgs.trust.TrustService;
import net.syscon.s4.triggers.OffenderDeductionsHty;
import net.syscon.s4.triggers.OffenderDeductionsT2Service;
import net.syscon.s4.triggers.OffenderDeductionsThtyService;
import net.syscon.s4.triggers.OffenderDeductionsTjnService;
import net.syscon.s4.triggers.OffenderTransactionsTjnService;

@Service
public class TrustServiceImpl implements TrustService {

	@Autowired
	private TrustRepository trustRepository;
	
	@Autowired
	private OffenderTransactionsTjnService offenderTransactionsTjnService;
	
	@Autowired
	private OffenderDeductionsTjnService offenderDeductionsTjnService;
	@Autowired
	private OffenderDeductionsThtyService offenderDeductionsThtyService;
	@Autowired
	private OffenderDeductionsT2Service offenderDeductionsT2Service;
	@Autowired
	private OcuovrobRepository ocuovrobRepository;

	private static final String X = "X";
	private static final String Y = "Y";
	private static final String N = "N";
	private static final String D = "D";
	private static final String CR = "CR";
	private static final String V = "V";
	private static final String CLIENT = "CLIENT";
	private static final String IND_CON = "IND_CON";
	private static final String OODOSALE = "OODOSALE";
	private static final String C = "C";
	private static final String DAY = "DAY";
	private static final String WEEK = "WEEK";
	private static final String MONTH = "MONTH";
	private static final String A = "A";
	private static final String L = "L";
	private static final String H = "H";

	private static Logger logger = LogManager.getLogger(TrustServiceImpl.class.getName());

	@Override
	public BigDecimal calculateBeneficiariesTotal(final BigDecimal corporateId) {

		List<OffenderBeneficiaries> rpBuildList = new ArrayList<OffenderBeneficiaries>();
		BigDecimal pTotlOwing = null;
		try {
			rpBuildList = trustRepository.getCorpBeneficiariesRecord(corporateId);

			Integer tmpTotAmt = 0;
			Integer tmpTotPaid = 0;
			for (final OffenderBeneficiaries offBen : rpBuildList) {

				if (offBen.getMaxTotalAmount() != null) {
					if (offBen.getAmount() != null) {
						tmpTotAmt = tmpTotAmt + (offBen.getAmount()).intValue();
					} else {
						tmpTotAmt = tmpTotAmt + 0;
					}

					if (offBen.getReceivedAmount() != null) {
						tmpTotPaid = tmpTotPaid + (offBen.getReceivedAmount()).intValue();
					} else {
						tmpTotPaid = tmpTotPaid + 0;
					}

				} else if (offBen.getMaxMontlyAmount() != null) {
					Integer monAmt = 0;
					monAmt = trustRepository.getMonAmt(offBen.getOffenderDeductionId(), corporateId);
					if (offBen.getAmount() != null) {
						tmpTotAmt = tmpTotAmt + (offBen.getAmount()).intValue();
					} else {
						tmpTotAmt = tmpTotAmt + 0;
					}

					tmpTotPaid = tmpTotPaid + monAmt;

				} else if (offBen.getMaxRecursiveAmount() != null) {
					Integer recMonth = 0;
					recMonth = trustRepository.getRecMonth(offBen.getOffenderDeductionId());

					if (offBen.getAmount() != null) {
						tmpTotAmt = tmpTotAmt + ((offBen.getAmount()).intValue() * recMonth);
					} else {
						tmpTotAmt = tmpTotAmt + 0 * recMonth;
					}

					if (offBen.getReceivedAmount() != null) {
						tmpTotPaid = tmpTotPaid + (offBen.getReceivedAmount()).intValue();
					} else {
						tmpTotPaid = tmpTotPaid + 0;
					}

				} // else if end
				else {
					if (offBen.getReceivedAmount() != null) {
						tmpTotPaid = tmpTotPaid + (offBen.getReceivedAmount().intValue());
					} else {
						tmpTotPaid = tmpTotPaid + 0;
					}
				} // else end
			} // for loop end

			pTotlOwing = BigDecimal.valueOf(tmpTotAmt - tmpTotPaid);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return pTotlOwing;
	}

	@Override
	public Map<String, Object> chkAccountStatus(final String caseLoadId, final BigDecimal offenderId) {
		final Map<String, Object> returnMap = new HashMap<String, Object>();
		String openAnAccount = null;
		openAnAccount = trustRepository.chkAccountStatusSelect(caseLoadId, offenderId);

		if (openAnAccount == null) {
			openAnAccount = X;
		}
		returnMap.put("P_OPEN_AN_ACCOUNT", openAnAccount);
		return returnMap;
	}

	@Override
	public String chkFreezeDisbursements(final ChkFreezeDisbursements chkFreeze) {
		Integer tempCount = 0;
		Integer frzcount = 0;
		String returnFrzFlag = N;

		tempCount = trustRepository.tempFlagSelectOperation(chkFreeze);

		if (tempCount > 0) {
			frzcount = trustRepository.frzFlagSelectOperation(chkFreeze.getpCsldId(), chkFreeze.getpOffId(),
					chkFreeze.getpDate());
		}
		if (frzcount > 0) {
			returnFrzFlag = Y;
		}
		return returnFrzFlag;
	}

	@Override
	public String getCaseloadType(final String caseLoadId) {
		return trustRepository.csldTypeC(caseLoadId);
	}

	@Override
	public String getLowHighFlag() {
		return trustRepository.getLowHighFlag();
	}

	@Override
	public Map<String, Object> getTransactionFee(final Long offId, final String csldId, final Long dedId,
			final String disbuType, final Double txnAmount, final String lowHighFlag, Integer txnFeeAmt) {
		final Map<String, Object> outParam = new HashMap<String, Object>();
		List<OffenderDeductionReceipts> offDedList = null;
		Integer rPercentage = null;
		Integer flatFee = null;

		offDedList = trustRepository.getRateCur(disbuType, dedId);
		for (final OffenderDeductionReceipts offDedRec : offDedList) {
			rPercentage = offDedRec.getReceiptPercentage() != null ? offDedRec.getReceiptPercentage().intValue() : null;
			flatFee = offDedRec.getFlatRate() != null ? offDedRec.getFlatRate().intValue() : null;
		}
		if (flatFee == null && rPercentage != null) {
			txnFeeAmt = (int) ((txnAmount * rPercentage) / 100);
		} else if (flatFee != null && rPercentage == null) {
			txnFeeAmt = flatFee;
		} else if (flatFee != null && rPercentage != null) {
			if (L.equals(lowHighFlag)) {
				if (flatFee < ((int) ((txnAmount * rPercentage) / 100))) {
					txnFeeAmt = flatFee;
				} else {
					txnFeeAmt = (int) ((txnAmount * rPercentage) / 100);
				}
			} else if (H.equals(lowHighFlag)) {
				if (flatFee > ((int) ((txnAmount * rPercentage) / 100))) {
					txnFeeAmt = flatFee;
				} else {
					txnFeeAmt = (int) ((txnAmount * rPercentage) / 100);
				}
			}
		} else {
			txnFeeAmt = 0;
		}
		outParam.put("TXN_FEE_AMT", txnFeeAmt);
		return outParam;
	}

	@Override
	public void insertGlTransNew(final GlTransactions glTrans) {
		final GlTransactions glTra = new GlTransactions();
		String vTxnRefNum = null;
		if (glTrans.getTxnReferenceNumber() != null && !glTrans.getTxnReferenceNumber().isEmpty()) {
			vTxnRefNum = glTrans.getTxnReferenceNumber();
		} else {
			vTxnRefNum = trustRepository.getVTxnRefNum(glTrans.getTxnId(), glTrans.getTxnEntrySeq());
		}

		glTrans.setTxnReferenceNumber(vTxnRefNum);
		trustRepository.glTransInsert(glTrans);

		if (glTrans.getAcntPosting() == glTrans.getTxnPostUsage()) {
			glTrans.getTxnEntryAmount().doubleValue();
		} else {
//			pstngAmount=-1.0 * (glTrans.getTxnEntryAmount().doubleValue());
		}
	}

	@Override
	public void insertIntoChequeData(final BankChequeData bankCheqData, String userName) {
		Long bankCode;
		if (bankCheqData.getBankAccountCode() == null) {
			bankCode = trustRepository.insertIntoChequeDataSelectOperation(bankCheqData.getCaseloadId(),
					bankCheqData.getModuleName(), bankCheqData.getTxnType(),userName);
		} else {
			bankCode = bankCheqData.getBankAccountCode();
		}
		bankCheqData.setBankAccountCode(bankCode);
		bankCheqData.setCreateUserId(userName);
		trustRepository.insertIntoChequeDataInsertOperation(bankCheqData);
	}

	@Override
	public Integer updateOffenderBalance(final OffenderTransactions offTrans, final String userName) {
		Double postingAmount = 0.0;
		final String pAllowOveriden = N;
		if (CR.equals(offTrans.getTxnPostingType())) {
			postingAmount = offTrans.getTxnEntryAmount();
		} else {
			postingAmount = (-1.0 * offTrans.getTxnEntryAmount());
		}
		final String caseLoadType = getCaseloadType(offTrans.getCaseloadId());
		if (CR.equals(offTrans.getTxnPostingType()) || Y.equals(pAllowOveriden)) {
			try {
			trustRepository.updateOffenderSubAccounts(offTrans.getTxnEntryDate(), offTrans.getTxnId(), postingAmount,
					offTrans.getCaseloadId(), offTrans.getOffenderId(), offTrans.getToSubAccountType(), caseLoadType,
					userName);
			}catch (Exception e) {
				logger.error("updateOffenderSubAccounts"+e);
			}
			try{
			trustRepository.updateOffenderTrustAccount(offTrans.getTxnEntryDate(), postingAmount,
					offTrans.getCaseloadId(), offTrans.getOffenderId(), userName);
			}catch (Exception e) {
				logger.error("updateOffenderTrustAccount"+e);
			}
		} else {
			try {
			trustRepository.getBalanceFromOffenderAccounts(offTrans.getCaseloadId(), offTrans.getOffenderId(),
					offTrans.getSubAccountType(), caseLoadType);
			}catch (Exception e) {
				logger.error("getBalanceFromOffenderAccounts"+e);
			}
			try {
			trustRepository.updateOffenderSubAccountsWhenOverridenFlagN(offTrans.getTxnEntryDate(), offTrans.getTxnId(),
					postingAmount, offTrans.getCaseloadId(), offTrans.getOffenderId(), offTrans.getSubAccountType(),
					caseLoadType, userName);
			}catch (Exception e) {
				logger.error("updateOffenderSubAccountsWhenOverridenFlagN"+e);
			}
			try {
			trustRepository.updateOffenderTrustAccount(offTrans.getTxnEntryDate(), postingAmount,
					offTrans.getCaseloadId(), offTrans.getOffenderId(), userName);
			}catch (Exception e) {
				logger.error("updateOffenderTrustAccount"+e);
			}
			}
		return 1;
	}

	@Override
	public Map<String, Object> getSubActType(final OtddisbuProcessTransactionsBean proTxn1) {
		final Map<String, Object> retMap = new HashMap<String, Object>();
		String csldType = null;
		String pSubActType = null;
		String pTxnPostType = null;
		List<AccountCodes> subActNdTxnPost = null;

		// 3260 get_caseload_type
		csldType = getCaseloadType(proTxn1.getpCaseloadId());
		proTxn1.setCaseLoadType(csldType);

		if (D.equals(proTxn1.getpTxnUsage())) {
			subActNdTxnPost = trustRepository.getSubActNdTxnPostTypesDr(proTxn1);
		} else {
			subActNdTxnPost = trustRepository.getSubActNdTxnPostTypesCr(proTxn1);
		}
		for (final AccountCodes accCode : subActNdTxnPost) {
			pSubActType = accCode.getSubAccountType();
			pTxnPostType = accCode.getCode();
		}
		if (subActNdTxnPost != null && !subActNdTxnPost.isEmpty()) {
			retMap.put("P_SUB_ACT_TYPE", pSubActType);
			retMap.put("P_TXN_POST_TYPE", pTxnPostType);
		}
		return retMap;
	}

	@Override
	public Integer processGlTransNew(final String pCsldId, final String pTransType, final Object pOperationType,
			final Double pTransAmount, final Integer pTransNumber, final Date pTransDate, final String pTransDesc,
			final Integer pTransSeq, final String pModuleName, final Integer pOffId, final Object pOffBookId,
			final Object pSubActTypeDr, final String pSubActTypeCr, final Object pPayeePersId,
			final Object pPayeeCorpId, final Object pPayeeNameText, Integer pGlSqnc, final Object pOffDedId,String userId) {

		Integer codeAcntOthr1;
		Integer codeAcntOthr2;
		Double pstngAmount;
		Double runTotal = 0.0;
		String indPstngStsDr = null;
		String indPstngStsCr = null;
		String indPstngStsO1;
		String indPstngStsO2;
		Integer sqncNumber;
		Integer recordNumber = 0;
		String firstTime = Y;
		String csldType;
		List<AccountCodes> accCodesList = new ArrayList<AccountCodes>();

		pGlSqnc = pGlSqnc != null ? pGlSqnc : 0;

		try {
			// get_caseload_type(p_csld_id, csld_type); 4262
			csldType = getCaseloadType(pCsldId);
			// posting_c 4264
		accCodesList = trustRepository.postingCCursor(pSubActTypeDr, pSubActTypeCr, csldType, pTransType,
					pModuleName, pOperationType, pCsldId);
			if(accCodesList!=null && accCodesList.size()>0) {
			for (final AccountCodes accountCodes : accCodesList) {
				Integer drAccountCode = accountCodes.getDrAccountCode();
				String txnPostingTypeDr = accountCodes.getbTxnPostingType();
				indPstngStsDr = accountCodes.getbPostingStatusFlag();
				Integer crAccountCode = accountCodes.getCrAccountCode();
				String getcTxnPostingTypeCr = accountCodes.getcTxnPostingType();
				indPstngStsCr = accountCodes.getcPostingStatusFlag();
				codeAcntOthr1 = accountCodes.getBankDrAccountCode();
				String getdTxnPostingType = accountCodes.getdTxnPostingType();
				indPstngStsO1 = accountCodes.getdPostingStatusFlag();
				codeAcntOthr2 = accountCodes.getBankCrAccountCode();
				accountCodes.getTxnPostingType();
				indPstngStsO2 = accountCodes.getePostingStatusFlag();
				sqncNumber = accountCodes.getTxnOperationSeq();
				recordNumber = recordNumber + 1;
				if (N.equals(indPstngStsDr) || N.equals(indPstngStsCr)) {
					break;
				}
				if (Y.equals(firstTime)) {
					pstngAmount = pTransAmount;
					pGlSqnc = pGlSqnc + 1;
					GlTransactions obj=new GlTransactions();
					obj.setTxnId(pTransNumber.longValue());
					obj.setTxnEntrySeq(pTransSeq.longValue());
				//	obj.setAccountPeriodId(accountPeriodId);
					obj.setGlEntrySeq(pGlSqnc.longValue());
					obj.setAccountCode(BigDecimal.valueOf(drAccountCode));
					obj.setTxnEntryDate(pTransDate);
					obj.setTxnType(pTransType);
					obj.setTxnPostUsage("DR");
					obj.setCaseloadId(pCsldId);
					obj.setOffenderId(BigDecimal.valueOf(pOffId));
					obj.setOffenderBookId(pOffBookId!=null?new BigDecimal(pOffBookId+""):null);
					obj.setTxnEntryAmount(BigDecimal.valueOf(pTransAmount));
					obj.setTxnEntryDesc(pTransDesc);
					obj.setTxnReferenceNumber(null);
					obj.setReconClearFlag("N");
					obj.setTxnReversedFlag("N");
					obj.setCreateUserId(userId);
					obj.setPayeePersonId(pPayeePersId!=null?new BigDecimal(pPayeePersId+""):null);
					obj.setCreateDate(pTransDate);
					obj.setPayeeCorporateId(null);
					obj.setPayeeNameText(null);
					obj.setReversedTxnId(null);
					obj.setReversedTxnEntrySeq(null);
					obj.setReversedGlEntrySeq(null);
					obj.setDeductionId(pOffDedId!=null?new BigDecimal(pOffDedId+""):null);
					obj.setTxnEntryTime(pTransDate);
					obj.setInfoNumber(null);
				/* Insert a DR posting in GL_TRANSACTIONS table */
					insertGlTransNew(obj);
				}	
					
					// TRUST.insert_gl_trans_new 4331
//					Integer count = insertGlTransNew("DR", codeAcntToDr, typePstngToDr, pCsldId, pTransType,
//							pstngAmount, pTransNumber, pTransDate, pTransDesc, pTransSeq, pGlSqnc, idOfndr, ofndrBk, "",
//							pPayeePersId, pPayeeCorpId, pPayeeNameText, "", "", "", "", pOffDedId);
//					if (count == 0) {
//						throw new Exception("Error: Cannot insert record into GL_Transactions for account:"
//								.concat(codeAcntToDr.toString()).concat("."));
//					}
				
				if (sqncNumber == 99) {
					pstngAmount = pTransAmount - runTotal;
				} else {
					pstngAmount = pTransAmount;
					runTotal = runTotal + pstngAmount;
				}
				pGlSqnc = pGlSqnc + 1;
				GlTransactions obj=new GlTransactions();
				obj.setTxnId(pTransNumber.longValue());
				obj.setTxnEntrySeq(pTransSeq.longValue());
			//	obj.setAccountPeriodId(accountPeriodId);
				obj.setGlEntrySeq(pGlSqnc.longValue());
				obj.setAccountCode(BigDecimal.valueOf(crAccountCode));
				obj.setTxnEntryDate(pTransDate);
				obj.setTxnType(pTransType);
				obj.setTxnPostUsage("CR");
				obj.setCaseloadId(pCsldId);
				obj.setOffenderId(BigDecimal.valueOf(pOffId));
				obj.setOffenderBookId(pOffBookId!=null?new BigDecimal(pOffBookId+""):null);
				obj.setTxnEntryAmount(BigDecimal.valueOf(pTransAmount));
				obj.setTxnEntryDesc(pTransDesc);
				obj.setTxnReferenceNumber(null);
				obj.setReconClearFlag("N");
				obj.setTxnReversedFlag("N");
				obj.setCreateUserId(userId);
				obj.setPayeePersonId(pPayeePersId!=null?new BigDecimal(pPayeePersId+""):null);
				obj.setCreateDate(pTransDate);
				obj.setPayeeCorporateId(null);
				obj.setPayeeNameText(null);
				obj.setReversedTxnId(null);
				obj.setReversedTxnEntrySeq(null);
				obj.setReversedGlEntrySeq(null);
				obj.setDeductionId(pOffDedId!=null?new BigDecimal(pOffDedId+""):null);
				obj.setTxnEntryTime(pTransDate);
				obj.setInfoNumber(null);
				/* Insert a CR posting in GL_TRANSACTIONS table */
				insertGlTransNew(obj);
				// TRUST.insert_gl_trans_new 4387,only one i/p changed
//				Integer count1 = insertGlTransNew("CR", codeAcntToDr, typePstngToDr, pCsldId, pTransType, pstngAmount,
//						pTransNumber, pTransDate, pTransDesc, pTransSeq, pGlSqnc, idOfndr, ofndrBk, "", pPayeePersId,
//						pPayeeCorpId, pPayeeNameText, "", "", "", "", pOffDedId);
//				if (count1 == 0) {
//					throw new Exception("Error: Cannot insert record into GL_Transactions for account:"
//							.concat(codeAcntToCr.toString()).concat("."));
//				}
				if (codeAcntOthr1 != null && Y.equals(indPstngStsO1) && codeAcntOthr2 != null
						&& Y.equals(indPstngStsO2)) {
					pGlSqnc = pGlSqnc + 1;
					GlTransactions obj1=new GlTransactions();
					obj1.setTxnId(pTransNumber.longValue());
					obj1.setTxnEntrySeq(pTransSeq.longValue());
			//		obj1.setAccountPeriodId(accountPeriodId);
					obj1.setGlEntrySeq(pGlSqnc.longValue());
					obj1.setAccountCode(BigDecimal.valueOf(codeAcntOthr1));
					obj1.setTxnEntryDate(pTransDate);
					obj1.setTxnType(pTransType);
					obj1.setTxnPostUsage("DR");
					obj1.setCaseloadId(pCsldId);
					obj1.setOffenderId(BigDecimal.valueOf(pOffId));
					obj1.setOffenderBookId(pOffBookId!=null?new BigDecimal(pOffBookId+""):null);
					obj1.setTxnEntryAmount(BigDecimal.valueOf(pTransAmount));
					obj1.setTxnEntryDesc(pTransDesc);
					obj1.setTxnReferenceNumber(null);
					obj1.setReconClearFlag("N");
					obj1.setTxnReversedFlag("N");
					obj1.setCreateUserId(userId);
					obj1.setPayeePersonId(pPayeePersId!=null?new BigDecimal(pPayeePersId+""):null);
					obj1.setCreateDate(pTransDate);
					obj1.setPayeeCorporateId(null);
					obj1.setPayeeNameText(null);
					obj1.setReversedTxnId(null);
					obj1.setReversedTxnEntrySeq(null);
					obj1.setReversedGlEntrySeq(null);
					obj.setDeductionId(pOffDedId!=null?new BigDecimal(pOffDedId+""):null);
					obj1.setTxnEntryTime(pTransDate);
					obj1.setInfoNumber(null);
					insertGlTransNew(obj1);
				
					// TRUST.insert_gl_trans_new 4436
//					Integer count2 = insertGlTransNew("DR", codeAcntOthr1, typePstngToO1, pCsldId, pTransType,
//							pTransAmount, pTransNumber, pTransDate, pTransDesc, pTransSeq, pGlSqnc, idOfndr, ofndrBk,
//							"", pPayeePersId, pPayeeCorpId, pPayeeNameText, "", "", "", "", pOffDedId);
//
//					if (count2 == 0) {
//						throw new Exception("Error: Cannot insert record into GL_Transactions for account:"
//								.concat(codeAcntOthr1.toString()).concat("."));
//					}
					pGlSqnc = pGlSqnc + 1;
					GlTransactions obj2=new GlTransactions();
					obj2.setTxnId(pTransNumber.longValue());
					obj2.setTxnEntrySeq(pTransSeq.longValue());
			//		obj2.setAccountPeriodId(accountPeriodId);
					obj2.setGlEntrySeq(pGlSqnc.longValue());
					obj2.setAccountCode(BigDecimal.valueOf(codeAcntOthr2));
					obj2.setTxnEntryDate(pTransDate);
					obj2.setTxnType(pTransType);
					obj2.setTxnPostUsage("CR");
					obj2.setCaseloadId(pCsldId);
					obj2.setOffenderId(BigDecimal.valueOf(pOffId));
					obj2.setOffenderBookId(pOffBookId!=null?new BigDecimal(pOffBookId+""):null);
					obj2.setTxnEntryAmount(BigDecimal.valueOf(pTransAmount));
					obj2.setTxnEntryDesc(pTransDesc);
					obj2.setTxnReferenceNumber(null);
					obj2.setReconClearFlag("N");
					obj2.setTxnReversedFlag("N");
					obj2.setCreateUserId(userId);
					obj2.setPayeePersonId(pPayeePersId!=null?new BigDecimal(pPayeePersId+""):null);
					obj2.setCreateDate(pTransDate);
					obj2.setPayeeCorporateId(null);
					obj2.setPayeeNameText(null);
					obj2.setReversedTxnId(null);
					obj2.setReversedTxnEntrySeq(null);
					obj2.setReversedGlEntrySeq(null);
					obj.setDeductionId(pOffDedId!=null?new BigDecimal(pOffDedId+""):null);
					obj2.setTxnEntryTime(pTransDate);
					obj2.setInfoNumber(null);
					insertGlTransNew(obj2);
					
					// TRUST.insert_gl_trans_new 4480
//					Integer count3 = insertGlTransNew("CR", codeAcntOthr2, typePstngToO2, pCsldId, pTransType,
//							pTransAmount, pTransNumber, pTransDate, pTransDesc, pTransSeq, pGlSqnc, idOfndr, ofndrBk,
//							"", pPayeePersId, pPayeeCorpId, pPayeeNameText, "", "", "", "", pOffDedId);
//					if (count3 == 0) {
//						throw new Exception("Error: Cannot insert record into GL_Transactions for account:"
//								.concat(codeAcntOthr2.toString()).concat("."));
//					}
				}
				firstTime = N;
			}
			}
			if (recordNumber == 0) {
				throw new Exception(
						"@*@*@*@*@*@*Error: No record found in Transaction Operation table for transaction type \""
								.concat(pTransType).concat("\"."));
			} else if (N.equals(indPstngStsDr) || N.equals(indPstngStsCr)) {
				throw new Exception("Error: One account was found with posting status = \"N\" for transaction type \""
						.concat(pTransType).concat("\"."));
			}
				 }catch (Exception e) {
					 pGlSqnc = 0;
			logger.error("processGlTransNew", e);
		}
		pGlSqnc = 1;
		return pGlSqnc;
	}

	@Override
	public Map<String, Object> chkOverdrawn(final String pCsldId, final BigDecimal pOffId, final String pSubActType,
			final BigDecimal transAmount, final String txntype, Long seqNo, String checkInd, final String modName,
			final BigDecimal pTxnId, final BigDecimal pOffBid, final Integer pTxnFee, final String pSetupCaseload,
			final String userName) {
		final Map<String, Object> returnMap = new HashMap<String, Object>();
		Double subBalance = null;
		Double overdrawn = null;
		String txnusage = null;
		String obligationtype = null;
		String proflag = null;
		BigDecimal minbal = null;
		String indigentflag = null;
		String indflag = null;
		String indflagO = Y;
		BigDecimal inddays = null;
		Date inddate = null;
		BigDecimal trstcode = null;
		String csldType = null;
		String lvSetupCsldType = null;

		final Map<String, Object> result = getOffenderSubBalance(pCsldId, pOffId, pSubActType, minbal, trstcode, N,
				txntype, modName, pSetupCaseload, userName);
		if (result != null) {
			subBalance = (Double) result.get("P_AMOUNT");
			minbal = (BigDecimal) result.get("P_MINBAL");
			inddays = (BigDecimal) result.get("P_INDDAYS");
			inddate = (Date) result.get("P_INDDATE");
			trstcode = (BigDecimal) result.get("P_TRSTCODE");
		}

		if (inddate != null && (subBalance != null ? subBalance : 0) > (minbal != null ? minbal.doubleValue() : 0)) {
			throw new IllegalArgumentException("Account Code " + trstcode.toString()
					+ ": ERROR: Offender balance exceeds minimum balance. Indigent Date: "
					+ new SimpleDateFormat("dd-MMM-yyyy").format(inddate));
		}

		csldType = trustRepository.getCaseLoadType(pCsldId);

		if (pSetupCaseload != null) {
			lvSetupCsldType = trustRepository.getCaseLoadType(pSetupCaseload);
		}

		final TransactionTypes tt = trustRepository.transactionTypesC(csldType, lvSetupCsldType,txntype);
		txnusage = tt.getTxnUsage();
		obligationtype = tt.getCreditObligationType();
		if (obligationtype != null) {
			try {
				trustRepository.getCrob(obligationtype, lvSetupCsldType, lvSetupCsldType);
			} catch (EmptyResultDataAccessException e) {
				obligationtype = null;
			} catch (IncorrectResultSizeDataAccessException e) {
				obligationtype = null;
			}

		}

		if (obligationtype != null) {
			indigentflag = trustRepository.indigentflag(pCsldId, obligationtype).getCoCreditWhenIndigentFlag();

		}
		if(indigentflag !=null) {
		if (indigentflag.equals(Y)) {
			if (inddate == null) {
				if (trustRepository.getCount() > 0) {
					indflag = Y;
				} else {
					indflag = N;
				}

			} else {
				indflag = Y;
				final Long days = ((new Date().getTime() - inddate.getTime()) / (1000 * 60 * 60 * 24)) % 365;
				if (days >= inddays.longValue() || inddays.intValue() == 0) {
					subBalance = 0d;
				}
			}

			Double subBalanceO = null;
			BigDecimal minbalO = null;
			BigDecimal inddaysO = null;
			Date inddateO = null;

			final List<OffenderSubAccounts> list = trustRepository.chkIndAcOthersC(pOffId, pCsldId, trstcode);
			for (int i = 0; i < list.size(); i++) {
				final Map<String, Object> map = getOffenderSubBalance(pCsldId, pOffId, pSubActType, minbalO,
						BigDecimal.valueOf(list.get(i).getTrustAccountCode()), N, txntype, modName, pSetupCaseload, userName);
				if (map != null) {
					subBalanceO = (Double) map.get("p_amount");
					minbalO = (BigDecimal) map.get("p_minbal");
					inddaysO = (BigDecimal) map.get("p_inddays");
					inddateO = (Date) map.get("p_inddate");
					list.get(i).setTrustAccountCode(((BigDecimal) map.get("p_trstcode")).longValue());
				}

				if (inddateO != null
						&& (subBalanceO != null ? subBalanceO : 0) > (minbalO != null ? minbalO.doubleValue() : 0)) {
					throw new IllegalArgumentException("Account Code " + list.get(i).getTrustAccountCode().toString()
							+ ": ERROR: Offender balance exceeds minimum balance. Indigent Date: "
							+ new SimpleDateFormat("dd-MMM-yyyy").format(inddateO));
				}
				final Long daysDiff = ((new Date().getTime() - inddateO.getTime()) / (1000 * 60 * 60 * 24)) % 365;
				if (inddateO != null && (daysDiff != null ? daysDiff : 0) >= inddaysO.longValue()) {
					indflagO = Y;
				} else {
					indflagO = N;
				}

				if (indflagO.equals(N)) {
					break;
				}
			}
		} }else {
			indflag = Y;
			indflagO = Y;
		}

		overdrawn = (subBalance - transAmount.doubleValue()) + ((pTxnFee != null) ? pTxnFee : 0);
		if (overdrawn < 0.0d) {
			checkInd = N;
			if (obligationtype != null && indflag.equals(Y) && indflagO.equals(Y)) {
				final Map<String, Object> map = doLegalLoan(pCsldId, pOffId, pOffBid, pTxnId, new Date(),
						overdrawn * -1.0, txntype, obligationtype, modName, subBalance, seqNo, pSetupCaseload,
						userName);
				if (map != null) {
					seqNo = (Long) map.get("SEQ_NO");
					proflag = (String) map.get("FLAG");
				}

				if (proflag.equals(N)) {
					throw new IllegalArgumentException("Overdrawn transaction for Offender " + pOffId + ".");
				} else {
					checkInd = Y;
				}
			} else if (obligationtype != null && indflag.equals("N") && indflagO.equals("N")) {
				throw new IllegalArgumentException("Insufficient balance for non-indigent Offender " + pOffId + ".");
			} else if (obligationtype != null && indflag.equals("N") && indflagO.equals("Y")) {
				throw new IllegalArgumentException("Insufficient balance for Offender  " + pOffId + ".");
			} else if (obligationtype != null && indflag.equals("Y") && indflagO.equals("N")) {
				throw new IllegalArgumentException(
						"Non-indigent Offender " + pOffId + " due to other non-indigent sub account(s).");
			} else {
				if (!txnusage.equals(V)) {
					throw new IllegalArgumentException("Overdrawn transaction for Offender " + pOffId + ".");
				}
			}

		} else {
			checkInd = Y;
		}

		returnMap.put("SEQ_NO", seqNo);
		returnMap.put("CHECK_IND", checkInd);
		return returnMap;
	}

//	private Date trunc(final Date date) {
//		if (date != null) {
//			final Calendar calender = Calendar.getInstance();
//			calender.setTime(date);
//			calender.set(Calendar.HOUR, 0);
//			calender.set(Calendar.MINUTE, 0);
//			calender.set(Calendar.SECOND, 0);
//			calender.set(Calendar.MILLISECOND, 0);
//			final Date returnDate = calender.getTime();
//			return returnDate;
//		}
//		return null;
//	}

	@Override
	public Map<String, Object> getOffenderSubBalance(final String pCsldId, final BigDecimal pOffId,
			final String pSubActType, final BigDecimal pMinbal, BigDecimal pTrstcode, final String pLockFlag,
			final String txntype, final String modName, final String pSetupCsldId, final String userId) {
		final Map<String, Object> returnMap = new HashMap<String, Object>();
		BigDecimal trustacccode = null;
		String lvCsldType = null;
		String lCaseloadExists = null;
		String lAgyLocId = null;
		BigDecimal lMinBalance = null;
		BigDecimal lIndDays = null;
		Date lIndDate = null;
		Double lBalance = null;

		lvCsldType = trustRepository.getCaseLoadType(pCsldId);

		if (pSetupCsldId != null) {
			trustRepository.getCaseLoadType(pSetupCsldId);
		}

		if (pTrstcode == null) {
			try {
				trustacccode = trustRepository.getDrAccountCode((pSetupCsldId != null) ? pSetupCsldId : pCsldId,
						modName, txntype);
				pTrstcode = trustacccode;
			} catch (EmptyResultDataAccessException e) {
				throw new IllegalArgumentException(
						"Can not find record in Transaction Operations for " + txntype + " Type.");
			} catch (IncorrectResultSizeDataAccessException e) {
				throw new IllegalArgumentException(
						"More than one ROW found in Transaction Operations for " + txntype + " Type.");
			}
		} else {
			trustacccode = pTrstcode;
		}

		if (pLockFlag == null || pLockFlag.equals(N)) {
			final OffenderSubAccounts acts = trustRepository.getOffSubAccounts(pCsldId, pOffId, trustacccode);
			if (acts != null) {
				lBalance = acts.getBalance();
				lMinBalance = acts.getMinimumBalance();
				lIndDate = acts.getIndDate();
				lIndDays = acts.getIndDays();
			}

		} else {
			final OffenderSubAccounts acts = trustRepository.getOffSubAccountstwo(pCsldId, pOffId, trustacccode);
			if (acts != null) {
				lBalance = acts.getBalance();
				lMinBalance = acts.getMinimumBalance();
				lIndDate = acts.getIndDate();
				lIndDays = acts.getIndDays();
			}

		}

		if (lMinBalance == null) {
			lCaseloadExists = null;
			lCaseloadExists = trustRepository.lCheckCaseloadCur(pCsldId);

			if (lCaseloadExists.equals(Y)) {
				lAgyLocId = trustRepository.lAgyLocIdCur(pOffId, lvCsldType,userId);

				final InstitutionMiniBalances imb = trustRepository.lMinBalanceCur(pSetupCsldId, lAgyLocId,
						trustacccode);
				if (imb != null) {
					lMinBalance = imb.getMinimumAccountBalance();
					lIndDays = imb.getIndDays();
				}

			} else {
//				SystemProfiles sp = trustRepository.lSystemProfilesCur(CLIENT, IND_CON);
				trustRepository.lSystemProfilesCur(CLIENT, IND_CON);
				// lMinBalance=sp.getProfileValue();
				// lIndDays=sp.getProfileValue2();
			}

		}

		lMinBalance = (lMinBalance != null) ? lMinBalance : BigDecimal.valueOf(0l);
		if (lBalance <= lMinBalance.doubleValue()) {
			if (lIndDate == null) {
				lIndDate = new Date();
			} else {
				lIndDays = trustRepository.lIndDays(pSetupCsldId, pOffId, trustacccode);
			}
		} else {
			lIndDate = null;
		}

		returnMap.put("P_AMOUNT", lBalance);
		returnMap.put("P_MINBAL", lMinBalance);
		returnMap.put("P_INDDAYS", lIndDays);
		returnMap.put("P_INDDATE", lIndDate);
		returnMap.put("P_TRSTCODE", pTrstcode);
		return returnMap;
	}

	@Override
	public Map<String, Object> doLegalLoan(final String caseload, final BigDecimal offenderid,
			final BigDecimal offbookid, final BigDecimal nmbrTrnsctn, final Date todayDate, final Double overdrawn,
			final String txntype, final String obligationtype, final String formName, final Double subBalance,
			final Long seqNo, final String pSetupCaseload, final String userName) {

		final Map<String, Object> returnMap = new HashMap<String, Object>();
		String procflag = N;
		String drsubacctype;
		String crsubacctype;
		Long offdeductionid = null;
		BigDecimal payeeperson = null;
		BigDecimal payeecorp = null;
		String infrmtn = null;
		String txndesc;
		Integer glSqnc = null;
		String payeename = null;
		BigDecimal maxlimit = null;
		String csldType;
		String lvSetupcsldType = null;

		final Map<String, Object> map = chkOffenderLimit(caseload, offenderid, obligationtype, overdrawn, maxlimit,
				subBalance);
		if (map != null) {
			maxlimit = (BigDecimal) map.get("MAX_LIMIT");
			procflag = (String) map.get("FLAG");
		}

		if (procflag.equals(N)) {
			returnMap.put("SEQ_NO", seqNo);
			returnMap.put("FLAG", procflag);
			return returnMap;
		}

		csldType = trustRepository.getCaseLoadType(caseload);

		if (pSetupCaseload != null) {
			lvSetupcsldType = csldType = trustRepository.getCaseLoadType(pSetupCaseload);
		}

		final AccountCodes acclist = trustRepository.getAccountCodes(formName, obligationtype, lvSetupcsldType,
				csldType, pSetupCaseload, pSetupCaseload);
		if (acclist == null) {
			returnMap.put("SEQ_NO", seqNo);
			returnMap.put("FLAG", N);
			return returnMap;
		} else {
			acclist.getDrAccountCode();
			acclist.getaTxnPostingType();
			drsubacctype = acclist.getSubAccountType();
			acclist.getCrAccountCode();
			acclist.getbTxnPostingType();
			crsubacctype = acclist.getCaseloadType();
			txndesc = acclist.getDescription();

		}

		String adjreasontype = null;
		final OffenderDeductions offDed = trustRepository.getOffenderDeductions(pSetupCaseload, offenderid,
				obligationtype);
		if (offDed != null) {
			offdeductionid = offDed.getOffenderDeductionId();
			payeeperson = offDed.getPayeePersonId();
			payeecorp = offDed.getPayeeCorporateId();
			adjreasontype = offDed.getAdjustmentReasonCode();
			infrmtn = offDed.getInformationNumber();
		}

		if (adjreasontype != null || (payeeperson == null && payeecorp == null)) {
			Map<String, Object> offDedMap = createOffenderDeductions(pSetupCaseload, offenderid, obligationtype,
					payeeperson, payeecorp, payeename, infrmtn, offdeductionid, userName);
			if (offDedMap != null) {
				payeeperson = (BigDecimal) offDedMap.get("PAYEEPERSON");
				payeecorp = (BigDecimal) offDedMap.get("PAYEECORP");
				payeename = (String) offDedMap.get("PAYEENAME");
				infrmtn = (String) offDedMap.get("INFONUM");
				offdeductionid = (Long) offDedMap.get("OFFDEDID");
				procflag = (String) offDedMap.get("FLAG");
			}

		}

		if (procflag.equals(N)) {
			returnMap.put("SEQ_NO", seqNo);
			returnMap.put("FLAG", N);
			return returnMap;
		}

		insrtIntoOffenderTrans(nmbrTrnsctn, seqNo, caseload, offenderid, offbookid, CR, obligationtype, txndesc,
				overdrawn, todayDate, crsubacctype, N, overdrawn, obligationtype, C, payeecorp, payeeperson, payeename,
				infrmtn, N, N, userName);

		if (formName.equals(OODOSALE)) {
			glSqnc = processGlTransNew(pSetupCaseload, caseload, obligationtype, null, overdrawn, nmbrTrnsctn,
					todayDate, txndesc, seqNo, formName, offenderid, offbookid, drsubacctype, crsubacctype, payeeperson,
					payeecorp, payeename, glSqnc, offdeductionid);
		} else {
			glSqnc = processGlTransNew(caseload, obligationtype, null, overdrawn, nmbrTrnsctn.intValue(), todayDate,
					txndesc, seqNo.intValue(), formName, offenderid.intValue(), offbookid, drsubacctype, crsubacctype,
					payeeperson, payeecorp, payeename, glSqnc, offdeductionid, userName);
		}

		Integer serchList = trustRepository.updateOffenderDeductions(maxlimit, overdrawn, todayDate, offdeductionid,
				userName);
		//OFFENDER_DEDUCTIONS_TJN 
		OffenderDeductions newRec = new OffenderDeductions();
		OffenderDeductions oldRec = new OffenderDeductions();
		oldRec = ocuovrobRepository.getoffenderDedOldRec(offdeductionid);
		//offenderDeductionsTjnService.offenderDeductionsTjn(newRec, oldRec, "UPDATE");
		//OFFENDER_DEDUCTIONS_THTY
		OffenderDeductionsHty offenderDeductionsThty = new OffenderDeductionsHty();
		offenderDeductionsThty.setCreateUserId(userName);
		offenderDeductionsThty.setOffenderDeductionId(offdeductionid);
		offenderDeductionsThty.setCaseloadId(pSetupCaseload);
		offenderDeductionsThtyService.OffenderDeductionsThtyTrigger(offenderDeductionsThty, "UPDATE");
		//OFFENDER_DEDUCTIONS_T2
		newRec.setOffenderDeductionId(offdeductionid);
		newRec.setMaxTotalAmount(new BigDecimal(overdrawn));
		offenderDeductionsT2Service.offenderDeductionsT2Trigger(newRec, oldRec, "UPDATE");
		
		if (serchList == 0) {
			throw new IllegalArgumentException("Error Occured in Updating OFFENDER_DEDUCTIONS.");
		}
		serchList = trustRepository.updateOffenderBeneficiaries(overdrawn, todayDate, offdeductionid, userName);
		if (serchList == 0) {
			throw new IllegalArgumentException("Error Occured while Updating OFFENDER_BENEFICIARIES.");
		}
		returnMap.put("SEQ_NO", seqNo);
		returnMap.put("FLAG", Y);
		return returnMap;

	}

	@Override
	public Map<String, Object> chkOffenderLimit(final String caseloadid, final BigDecimal offenderid,
			final String limittype, final Double amntTrans, BigDecimal maxLimit, final Double subBalance) {
		final Map<String, Object> returnMap = new HashMap<String, Object>();
		String maxflag = N;
		String periodtype;
		BigDecimal loanTaken;
		Date fromdate = null;
		Date todate = null;
		Integer weekday;
		BigDecimal loanAvailable;
		BigDecimal amountWrittenOff;

		final OffenderLimits searchList = trustRepository.getOffenderLimits(caseloadid, offenderid, limittype);
		if (searchList != null) {
			maxLimit = searchList.getLimitAmount();
			periodtype = searchList.getPeriodType();
		} else {
			final OffenderLimits ol1 = trustRepository.getOffenderLimitsTwo(caseloadid, limittype);
			if (ol1 == null) {
				maxLimit = BigDecimal.valueOf(999999.00);
				maxflag = Y;
				returnMap.put("MAX_LIMIT", maxLimit);
				returnMap.put("FLAG", N);
				return returnMap;
			}
			maxLimit = ol1.getLimitAmount();
			periodtype = ol1.getPeriodType();
		}

		if (periodtype.equals(DAY)) {
			fromdate = new Date();
			todate = new Date();
		} else if (periodtype.equals(WEEK)) {
			weekday = trustRepository.getWeekDay();
			fromdate = trustRepository.getFromDate(weekday);
			todate = trustRepository.getToDate(weekday);
		} else if (periodtype.equals(MONTH)) {
			fromdate = trustRepository.getFromDateTwo();
			todate = trustRepository.getToDateTwo();
		}

		loanTaken = getPreviousLoanAmount(fromdate, todate, limittype, offenderid, caseloadid);
		loanAvailable = maxLimit.subtract((loanTaken != null) ? loanTaken : BigDecimal.ZERO);
		amountWrittenOff = trustRepository.getAmountWrittenOff(offenderid, limittype, caseloadid, fromdate, todate);
		loanAvailable = loanAvailable.add(amountWrittenOff);
		if (amntTrans > (loanAvailable.doubleValue() + subBalance)) {
			returnMap.put("MAX_LIMIT", maxLimit);
			returnMap.put("FLAG", N);
			return returnMap;
		} else {
			if (maxflag.equals(Y)) {
				maxLimit = null;
			}
			returnMap.put("MAX_LIMIT", maxLimit);
			returnMap.put("FLAG", Y);
			return returnMap;
		}

	}

	@Override
	public BigDecimal getPreviousLoanAmount(final Date fromDate, final Date toDate, final String dedType,
			final BigDecimal pOffenderId, final String caseloadId) {
		BigDecimal totalTaken = BigDecimal.ZERO;
		BigDecimal totalReversed = BigDecimal.ZERO;
		totalTaken = trustRepository.getTotalTaken(pOffenderId, dedType, caseloadId, fromDate, toDate);
		totalReversed = trustRepository.getTotalReversed(pOffenderId, dedType, caseloadId, fromDate, toDate);
		if (totalTaken == null) {
			totalTaken = BigDecimal.ZERO;
		}
		if (totalReversed == null) {
			totalReversed = BigDecimal.ZERO;
		}
		return totalTaken.subtract(totalReversed);

	}

	@Override
	public Map<String, Object> createOffenderDeductions(final String caseload, final BigDecimal offenderid,
			final String obligationtype, final BigDecimal payeeperson, BigDecimal payeecorp, String payeename,
			String infonum, Long offdedid, final String userName) {
		String fifoflag = null;
		BigDecimal prtyNmbr = null;
		Map<String, Object> returnMap = new HashMap<String, Object>();

		try {
			final CaseloadDeductionProfiles cdp = trustRepository.getCaseloadDeductionProfiles(caseload,
					obligationtype);
			if (cdp != null) {
				fifoflag = cdp.getFifoFlag();
				infonum = cdp.getFrequency();
				payeecorp = cdp.getPayeeCorporateId();
				payeename = cdp.getCorporateName();
			}
		} catch (EmptyResultDataAccessException e) {
			throw new IllegalArgumentException(
					"Please setup a Corporate Id in Caseload Deduction Profiles for Caseload " + caseload + ".");

		} catch (IncorrectResultSizeDataAccessException e) {
			throw new IllegalArgumentException("Too many rows found for Caseload " + caseload + " in Corporate Table.");
		}

		final Integer llCnt = trustRepository.getLlCnt(offenderid, obligationtype, infonum);
		if (llCnt > 0) {
			infonum = infonum.concat("-").concat(llCnt.toString());
		}

		prtyNmbr = trustRepository.getPrtyNmbr(offenderid, obligationtype);

		offdedid = trustRepository.getOffdedid();

		final OffenderDeductions offndDed = new OffenderDeductions();
		offndDed.setOffenderDeductionId(offdedid);
		offndDed.setCaseloadId(caseload);
		offndDed.setOffenderId(offenderid.longValue());
		offndDed.setDeductionType(obligationtype);
		offndDed.setDeductionPriority(prtyNmbr);
		offndDed.setInformationNumber(infonum);
		offndDed.setFifoFlag(fifoflag);
		offndDed.setPayeeCorporateId(payeecorp);
		offndDed.setDeductionAmount(BigDecimal.valueOf(0.0d));
		offndDed.setDeductionStatus(A);
		trustRepository.insertOffenderDeductions(offndDed);
		trustRepository.insertOffenderDeductionReceipts(caseload, offenderid, offdedid);
		trustRepository.insertOffenderBeneficiaries(offenderid, offdedid, payeecorp, userName);

		returnMap.put("PAYEEPERSON", payeeperson);
		returnMap.put("PAYEECORP", payeecorp);
		returnMap.put("PAYEENAME", payeename);
		returnMap.put("INFONUM", infonum);
		returnMap.put("OFFDEDID", offdedid);
		returnMap.put("FLAG", Y);
		return returnMap;
	}

	@Override
	public void insrtIntoOffenderTrans(final BigDecimal pTransNumber, final Long pTransSeq, final String pCsldId,
			final BigDecimal pOffId, final BigDecimal pOffBookId, final String pTransPostType, final String pTransType,
			final String pTransDesc, final Double pTransAmount, final Date pTransDate, final String pSubActType,
			final String pDeductionFlag, final Double pPreDedAmount, final String pDeductionType,
			final String pPayeeCode, final BigDecimal pPayeeCorpId, final BigDecimal pPayeePersonId,
			final String pPayeeNameText, final String pInfoNumber, final String pSlipPrintFlag,
			final String pAllowOverdrawn, final String userName) {

		BigDecimal offBookId = null;

		if (pOffBookId == null) {
			offBookId = trustRepository.getOffBookId(pOffId);
		} else {
			offBookId = pOffBookId;
		}

		trustRepository.insertOffenderTransactions(pTransNumber, pTransSeq, pCsldId, pOffId, offBookId, pTransPostType,
				pTransType, pTransDesc, pTransAmount, pTransDate, pSubActType, pSlipPrintFlag, pPreDedAmount,
				pDeductionFlag, pPayeeCode, pPayeeCorpId, pPayeePersonId, pPayeeNameText, pDeductionType, pInfoNumber,
				userName);

		final OffenderTransactions offTrans = new OffenderTransactions();
		offTrans.setCaseloadId(pCsldId);
		offTrans.setOffenderId(pOffId.longValue());
		offTrans.setTxnPostingType(pTransPostType);
		offTrans.setTxnEntryDate(pTransDate);
		offTrans.setTxnId(pTransNumber.intValue());
		offTrans.setTxnType(pTransType);
		offTrans.setTxnEntryAmount(pTransAmount);
		offTrans.setSubAccountType(pSubActType);
		updateOffenderBalance(offTrans, userName);

	}

	@Override
	public Integer processGlTransNew(final String pSetupCsldId, final String pPostingCsldId, final String pTransType,
			final String pOperationType, final Double pTransAmount, final BigDecimal pTransNumber,
			final Date pTransDate, final String pTransDesc, final Long pTransSeq, final String pModuleName,
			final BigDecimal pOffId, final BigDecimal pOffBookId, final String pSubActTypeDr,
			final String pSubActTypeCr, final BigDecimal pPayeePersId, final BigDecimal pPayeeCorpId,
			final String pPayeeNameText, Integer pGlSqnc, final Long pOffDedId) {

		Integer codeAcntOthr1;
		Integer codeAcntOthr2;
		Double pstngAmount;
		Double runTotal;
		String indPstngStsDr = null;
		String indPstngStsCr = null;
		String indPstngStsO1;
		String indPstngStsO2;
		Integer sqncNumber;
		Integer recordNumber;
		String firstTime;
		String setupCsldType;
		setupCsldType = trustRepository.getCaseLoadType(pSetupCsldId);
		trustRepository.getCaseLoadType(pPostingCsldId);
		recordNumber = 0;
		runTotal = 0d;
		pGlSqnc = pGlSqnc != null ? pGlSqnc : 0;
		firstTime = Y;

		for (int i = 0; i < i + 1; i++) {
			AccountCodes acCodes = trustRepository.postingC(pSubActTypeDr, pSubActTypeCr, setupCsldType, pTransType,
					pModuleName, pOperationType, pSetupCsldId);
			if (acCodes == null) {
				break;
			}
			acCodes.getDrAccountCode();
			acCodes.getbTxnPostingType();
			indPstngStsDr = acCodes.getbPostingStatusFlag();
			acCodes.getCrAccountCode();
			acCodes.getcTxnPostingType();
			indPstngStsCr = acCodes.getcPostingStatusFlag();
			codeAcntOthr1 = acCodes.getBankDrAccountCode();
			acCodes.getdTxnPostingType();
			indPstngStsO1 = acCodes.getdPostingStatusFlag();
			codeAcntOthr2 = acCodes.getBankCrAccountCode();
			acCodes.getTxnPostingType();
			indPstngStsO2 = acCodes.getePostingStatusFlag();
			sqncNumber = acCodes.getTxnOperationSeq();
			recordNumber = recordNumber + 1;
			if (indPstngStsDr.equals(N) || indPstngStsCr.equals(N)) {
				break;
			}

			if (firstTime.equals(Y)) {
				pstngAmount = pTransAmount;
				pGlSqnc = pGlSqnc + 1;
				pOffId.longValue();
				pOffBookId.longValue();

			}

			if (sqncNumber == 99) {
				pstngAmount = pTransAmount - runTotal;
			} else {
				pstngAmount = pTransAmount;
				runTotal = runTotal + pstngAmount;
			}

			pGlSqnc = pGlSqnc + 1;
			pOffId.longValue();
			pOffBookId.longValue();

			if (codeAcntOthr1 != null && indPstngStsO1.equals(Y) && codeAcntOthr2 != null && indPstngStsO2.equals(Y)) {
				pGlSqnc = pGlSqnc + 1;
				/*
				 * processOcdintakTrustService.insertGlTransNew("DR", codeAcntOthr1,
				 * typePstngToO1, pPostingCsldId, pTransType, pTransAmount,
				 * pTransNumber.intValue(), pTransDate, pTransDesc, pTransSeq.intValue(),
				 * pGlSqnc.intValue(), idOfndr.intValue(), ofndrBk.intValue(), "", pPayeePersId,
				 * pPayeeCorpId, pPayeeNameText, "", "", "", "", pOffDedId);
				 */

				pGlSqnc = pGlSqnc + 1;
				/*
				 * processOcdintakTrustService.insertGlTransNew("CR", codeAcntOthr2,
				 * typePstngToO2, pPostingCsldId, pTransType, pTransAmount,
				 * pTransNumber.intValue(), pTransDate, pTransDesc, pTransSeq.intValue(),
				 * pGlSqnc.intValue(), idOfndr.intValue(), ofndrBk.intValue(), "", pPayeePersId,
				 * pPayeeCorpId, pPayeeNameText, "", "", "", "", pOffDedId);
				 */

			}

			firstTime = N;
		}

		if (recordNumber == 0) {
			throw new IllegalArgumentException(
					"@*@*@*@*@*@*Error: No record found in Transaction Operation table for transaction type "
							+ pTransType + ".");
		} else if (indPstngStsDr.equals("N") || indPstngStsCr.equals("N")) {
			throw new IllegalArgumentException(
					"Error: One account was found with posting status = 'N' for transaction type " + pTransType + ".");
		}
		return 1;
	}

	@Override
	public String trustCommunityCsld(final String caseloadId) {
		return trustRepository.trustCommunityCsld(caseloadId);
	}

	@Override
	public Integer insertIntoOffenderTrans(final OffenderTransactions obj) {
		Long offBookId;
		Integer genSeq = 0;
		try {
			if (obj.getOffenderBookId() == null) {
				offBookId = trustRepository.getOffenderBookIdCurForInsert(BigDecimal.valueOf(obj.getOffenderId()),
						obj.getCaseloadId());
			} else {
				offBookId = obj.getOffenderBookId();
			}
			final OffenderTransactions offenTrans = new OffenderTransactions();
			offenTrans.setTxnId(obj.getTxnId());
			offenTrans.setTxnEntrySeq(obj.getTxnEntrySeq());
			offenTrans.setCaseloadId(obj.getCaseloadId());
			offenTrans.setOffenderId(obj.getOffenderId());
			offenTrans.setOffenderBookId(offBookId);
			offenTrans.setTxnPostingType(obj.getTxnPostingType());
			offenTrans.setTxnType(obj.getTxnType());
			offenTrans.setTxnEntryDesc(obj.getTxnEntryDesc());
			offenTrans.setTxnEntryAmount(obj.getTxnEntryAmount());
			offenTrans.setTxnEntryDate(obj.getTxnEntryDate());
			offenTrans.setSubAccountType(obj.getSubAccountType());
			offenTrans.setSlipPrintedFlag(obj.getSlipPrintedFlag());
			offenTrans.setPreWithholdAmount(obj.getPreWithholdAmount());
			offenTrans.setDeductionFlag(obj.getDeductionFlag());
			offenTrans.setPayeeCorporateId(obj.getPayeeCorporateId());
			offenTrans.setPayeePersonId(obj.getPayeePersonId());
			offenTrans.setDeductionType(obj.getDeductionType());
			offenTrans.setInfoNumber(obj.getInfoNumber());
			offenTrans.setCreateUserId(obj.getCreateUserId());					
			trustRepository.insertOffenderTransactions(offenTrans);
			logger.info("insertOffenderTransactions response");
			
			//OffenderTransactions newRecord = new OffenderTransactions();
			//BeanUtils.copyProperties(obj, newRecord);
			//added trigger call from OffenderTransactionsTjn
			//offenderTransactionsTjnService.OffenderTransactionsTjn(newRecord, new OffenderTransactions(), "INSERT");
			updateOffenderBalance(obj, obj.getCreateUserId());
			genSeq = 1;
		} catch (Exception e) {
			logger.error("insertIntoOffenderTrans :" + e);
			return genSeq;
		}
		return genSeq;
	}
	 /***********************************************************************************************
	   ||
	   || Procedure PROCESS_TRANSACTION_FEE
	   ||    This procedure is for posting Offender Transaction and GL Transaction records
	   ||    if transaction fee is being collected
	   ||
	   ***********************************************************************************************/
	@Override
	public Long processTransactionFee(OffenderTransactions obj,String moduleName) {
		
		Integer txnFeeAmt=null;
		Double glSeq=null;
		String subActType=null,postingType=null,lowHighFlag=null,drPostingType=null,drPostingStatus=null,caseloadtype=null;
		BigDecimal drActCode=null;
	//	getLowHighFlag();
	//	getCaseloadType(obj.getCaseloadId());
		 lowHighFlag = trustRepository.getLowHighFlag();
	     caseloadtype = trustRepository.getCaseLoadType(obj.getCaseloadId());
		AccountCodes codes= trustRepository.getDrTxnOpr(obj.getTxnType(), moduleName, obj.getCaseloadId(), caseloadtype);
		drActCode=codes.getDrAccountCode()!=null?new BigDecimal(codes.getDrAccountCode()):null;
		drPostingType=codes.getTxnPostingType();
		drPostingStatus=codes.getPostingStatusFlag();
		subActType=codes.getSubAccountType();
		for(OffenderDeductions bean:trustRepository.getTxnFeeType(obj.getOffenderId(), obj.getCaseloadId(), caseloadtype)){
			Map<String, Object> transactionFeeAmount=getTransactionFee(obj.getOffenderId(), obj.getCaseloadId(), bean.getOffenderDeductionId(), obj.getTxnType(), obj.getTxnEntryAmount(), lowHighFlag, txnFeeAmt!=null?txnFeeAmt.intValue():null);
			txnFeeAmt=  (Integer) transactionFeeAmount.get("TXN_FEE_AMT");
			obj.setTxnEntrySeq(obj.getTxnEntrySeq()+1);
			OffenderTransactions tran=new OffenderTransactions();
			BeanUtils.copyProperties(obj, tran);
			tran.setTxnPostingType("DR");
			tran.setTxnType("TRNFEE");
			tran.setTxnEntryDesc("Transaction fee for"+bean.getDeductionType()+"-"+obj.getTxnType());
			tran.setSlipPrintedFlag("N");
			tran.setDeductionFlag("");
			tran.setPreWithholdAmount(null);
			tran.setDeductionType("");
			tran.setPayeeCorporateId(null);
			tran.setPayeePersonId(null);
			tran.setInfoNumber("");
			insertIntoOffenderTrans(tran);
			glSeq=Double.valueOf(1);
			GlTransactions git=new GlTransactions();
			git.setDspTxnPostingType("DR");
			git.setAccountCode(drActCode);
			git.setAcntPosting(drPostingStatus);
			git.setCaseloadId(obj.getCaseloadId());
			git.setTxnType("TRNFEE");
			git.setTxnEntryAmount(txnFeeAmt!=null?new BigDecimal(txnFeeAmt):null);
			git.setTxnReferenceNumber(obj.getTxnId()+"");
			git.setTxnEntryDate(obj.getTxnEntryDate());
			git.setTxnEntryDesc("Transaction fee for"+bean.getDeductionType()+"-"+obj.getTxnType());
			git.setTxnEntrySeq(obj.getTxnEntrySeq()!=null?obj.getTxnEntrySeq().longValue():null);
			git.setGlEntrySeq(glSeq!=null?glSeq.longValue():null);
			git.setOffenderId(obj.getOffenderId()!=null?new BigDecimal(obj.getOffenderId()):null);
			git.setOffenderBookId(obj.getOffenderBookId()!=null?new BigDecimal(obj.getOffenderBookId()):null);
			git.setInfoNumber(bean.getInformationNumber());
			git.setPayeeCorporateId(bean.getPayeeCorporateId());
			git.setTxnReferenceNumber(bean.getOffenderDeductionId()+"");
			git.setOffDeductionId(bean.getOffenderDeductionId()!=null?new BigDecimal(bean.getOffenderDeductionId()):null);
			git.setTxnId(obj.getTxnId().longValue());
			git.setCreateUserId(obj.getCreateUserId());
			git.setTxnPostUsage("DR");
			git.setCreateDate(obj.getTxnEntryDate());
			insertGlTransNew(git);
			glSeq=glSeq+1;
			git.setDspTxnPostingType("CR");
			git.setAccountCode(bean.getAccountCode()!=null?new BigDecimal(bean.getAccountCode()):null);
			git.setAcntPosting(bean.getPostingStatus());
			git.setTxnPostUsage("CR");
			git.setCreateDate(obj.getTxnEntryDate());
			git.setGlEntrySeq(glSeq!=null?glSeq.longValue():null);
			insertGlTransNew(git);
		}
		return obj.getTxnEntrySeq()!=null?obj.getTxnEntrySeq().longValue():null;
	}

	@Override
	public BigDecimal getOffenderSubBal(String caseloadId, String csldType, Long pOffId, Integer accountCode) {
		
		return trustRepository.gettingLvOffBalance(caseloadId,csldType,pOffId,accountCode);
	}

	@Override
	public BigDecimal getSubBal(String pCsldId, Long pOffId, String deductionType) {
		BigDecimal subBalance;
	   String caseloadType=trustRepository.getCaseLoadType(pCsldId);
	   subBalance= trustRepository.getSubBal(pCsldId,pOffId,deductionType,caseloadType);
		if (subBalance == null) {
			subBalance=trustRepository.gettingSubBal(deductionType,pOffId,pCsldId,caseloadType);
		}
		return subBalance;
	}

	@Override
	public String chkMinimumTrustBalance(String pCsldId, Long pOffId, String pTransType, String deductionType,
			BigDecimal amntToDeduct) {
		String pProcessFlag=null;
		BigDecimal subBal = getSubBal(pCsldId, pOffId, "REG");
		BigDecimal vMinBal=trustRepository.gettingVMinBal(pCsldId,deductionType,pTransType);
		BigDecimal val = subBal.subtract(amntToDeduct!= null?amntToDeduct:new BigDecimal(0));
		Double data = val.doubleValue();
		Double dataOne = vMinBal!=null?vMinBal.doubleValue():0;
		if (data  >= dataOne) {
			pProcessFlag="Y";
		}else {
			pProcessFlag="N";
		}
		return pProcessFlag;
	}

}