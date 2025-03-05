package net.syscon.s4.pkgs.financial.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.cf.deductions.beans.OffenderMonDeductions;
import net.syscon.s4.cf.deductions.maintenance.beans.TieredTransactionFeeAmounts;
import net.syscon.s4.cf.offendertransactions.beans.OffenderCreditPriorPayments;
import net.syscon.s4.cf.offendertransactions.beans.PaymentPlanTransaction;
import net.syscon.s4.common.Utilities;
import net.syscon.s4.common.beans.OffenderPaymentSchedules;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.genericservices.CustomException;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.im.beans.TransactionOperation;
import net.syscon.s4.inmate.beans.BeneficiaryTransactions;
import net.syscon.s4.inmate.beans.GlTransactions;
import net.syscon.s4.inmate.beans.OffenderBeneficiaries;
import net.syscon.s4.inmate.beans.OffenderDeductions;
import net.syscon.s4.inmate.beans.OffenderSubAccounts;
import net.syscon.s4.inmate.trust.trustaccounts.impl.OcuovrobRepositoryImpl;
import net.syscon.s4.pkgs.financial.FinancialRepository;
import net.syscon.s4.pkgs.financial.FinancialService;
import net.syscon.s4.pkgs.payment_schedules.PaymentSchedulesService;
import net.syscon.s4.pkgs.trust.TrustService;
import net.syscon.s4.triggers.OffenderBeneficiariesT1Service;
import net.syscon.s4.triggers.OffenderBeneficiariesT2Service;

@Service
public class FinancialServiceImpl implements FinancialService {

	@Autowired
	private FinancialRepository financialRepository;

	@Autowired
	private PaymentSchedulesService paymentSchedulesService;

	@Autowired
	private TrustService trustService;
	
	@Autowired
	private OffenderBeneficiariesT1Service offenderBeneficiariesT1Service;
	
	@Autowired
	private OffenderBeneficiariesT2Service offenderBeneficiariesT2Service;
	

	private static Logger logger = LogManager.getLogger(OcuovrobRepositoryImpl.class.getName());

	@Override
	public String updateOffenderBeneficiaries(final BigDecimal deductionId, final BigDecimal payeePersonId,
			final BigDecimal payeeCorporateId, final BigDecimal txnEntryAmount, final String userName) {

		OffenderBeneficiaries newRec = new OffenderBeneficiaries();
		newRec.setOffenderDeductionId(deductionId.longValue());
		newRec.setPersonId(payeePersonId);
		newRec.setCorporateId(payeeCorporateId);
		OffenderBeneficiaries oldRec = financialRepository.getOffenderBeneficiaries(deductionId, payeePersonId,
				payeeCorporateId);
		// Trigger OFFENDER_BENEFICIARIES_T1
		offenderBeneficiariesT1Service.offenderBeneficiariesT1Trigger(newRec, oldRec);
		// Trigger OFFENDER_BENEFICIARIES_T2
		offenderBeneficiariesT2Service.offenderBeneficiariesT2Trigger(newRec, "UPDATE");
		return financialRepository.updateOffenderBeneficiaries(deductionId, payeePersonId, payeeCorporateId,
				txnEntryAmount, userName);
	}

	@Override
	public Integer updatePaymentPlanSchedules(final PaymentPlanTransaction pl, Long pRecursiveAmt,
			final String userName) {

		Long cPaymentPlanId = 0L;
		Long cPaymentPlanSeq = 0L;
		Date cPaymentDate;
		Long cPaymentAmount = 0L;
		Long cPaidAmount = 0L;
		Long vAmountToPlan = pl.getTransactionAmount().longValue();
		Long vPaymentAmount;
		Long vAmountOwing;
		Integer vStillOweAmt;
		try {
			List<OffenderPaymentSchedules> paymentPeriod = new ArrayList<OffenderPaymentSchedules>();
			paymentPeriod = financialRepository.paymentPeriod(pl.getInformationNumber(), pl.getGroupId(),
					pl.getOffenderId().longValue());
			for (OffenderPaymentSchedules payPeriod : paymentPeriod) {
				cPaymentPlanId = payPeriod.getPaymentPlanId();
				cPaymentPlanSeq = payPeriod.getPaymentPlanSeq();
				cPaymentDate = payPeriod.getPaymentDate();
				cPaymentAmount = payPeriod.getPaymentAmount().longValue();
				cPaidAmount = payPeriod.getPaidAmount().longValue();

				if (!cPaidAmount.equals(cPaymentAmount) && vAmountToPlan > 0) {
					vAmountOwing = cPaymentAmount - cPaidAmount;
					if (vAmountToPlan > vAmountOwing) {
						vPaymentAmount = vAmountOwing;
					} else {
						vPaymentAmount = vAmountToPlan;
					}
					vAmountToPlan = vAmountToPlan - vPaymentAmount;
					pl.setModifyUserId(userName);
					pl.setPaymentPlanId(cPaymentPlanId);
					pl.setPaymentPlanSeq(cPaymentPlanSeq);
					pl.setPaymentDate(cPaymentDate);
					pl.setTransactionAmount(BigDecimal.valueOf(vPaymentAmount));
					final Integer count = financialRepository.offenderPaymentSchedulesUpdate(pl, pRecursiveAmt);
					if (count > 0) {
						Date currentDate = new Date();
						pl.setTransactionDate(currentDate);
						recordReceiptDisbursement(pl, userName);
					}
					vStillOweAmt = financialRepository.finnacialSelect(pl.getPaymentPlanId());
					if (vStillOweAmt == 0) {
						financialRepository.offenderPaymentPlansUpdate(pl.getPaymentPlanId(), userName);

						paymentSchedulesService.updateOwingAmount(pl.getOffenderId(), pl.getInformationNumber(),
								pl.getGroupId(), userName);
					}
				}
			}
		} catch (Exception e) {
			logger.error("updatePaymentPlanSchedules :" + e);
			return 0;
		}
		return 1;
	}

	@Override
	public void recordReceiptDisbursement(final PaymentPlanTransaction plan, final String userName) {
		Long nTxnseq=financialRepository.seqCur(plan);
		plan.setCreateUserId(userName);
		plan.setTransactionSeq(nTxnseq);
		financialRepository.paymentPlanTransactionsInsert(plan);

	}

	@Override
	public String reverseBeneficiaryTrans(final long txnId, final long txnEntrySeq, final long glEntrySeq,
			final Long offTxnId, final long glSeq, final String txnType, final String txnEntryDesc) {
		Integer vBenSeq = null;
		try {
			final List<Object[]> list = financialRepository.cBenTxn(offTxnId, txnEntrySeq, glEntrySeq);
			for (final Object[] obj : list) {
				vBenSeq = vBenSeq + 1;
				this.insertIntoBenTransactions((int) txnId, (int) txnEntrySeq, (long) obj[1], (BigDecimal) obj[3],
						(BigDecimal) obj[4], (BigDecimal) obj[5], (String) obj[2], txnEntryDesc, (String) obj[7],
						(Long) obj[8]);
			}
		} catch (Exception e) {
			logger.error("reverseBeneficiaryTrans :" + e);
		}
		return "GETINDDATE";
	}

	@Override
	public Integer insertIntoBenTransactions(final Integer lvTxnId, final Integer lvTxnSeq, final Long lvDeductionId,
			final BigDecimal personId, final BigDecimal corporateId, final BigDecimal unknownBenId,
			final String lvCrAccCode, final String lvTxnDesc, final String caseloadId, final Long beneficiaryId) {
		final String flag = financialRepository.insertIntoBenTransactionsSelect((long) lvTxnId);
		if (flag != null) {
			return 1;
		}
		return 0;
	}

	@Override
	public Map<String, Object> doDeductionsFinancial(String pCsldId, Long pOffId, Long pOffBookId, String pTransType,
			Long pTransNumber, Date pTransDate, String pSubActType, String pDedFlag, BigDecimal pReceiptAmount,
			Long pShadowId, Long pInfoNumber, Integer txnSeq, String userId) {
		Map<String, Object> map = null;
		try {
			map = doDeductionsFinancial(pCsldId, pOffId, pOffBookId, pTransType, pTransNumber, pTransDate, pSubActType,
					pDedFlag, pReceiptAmount, pShadowId, pInfoNumber, "OTDRDTFU", txnSeq, userId);
		} catch (CustomException e) {
			e.printStackTrace();
		}
		return map;
	}

	private Map<String, Object> doDeductionsFinancial(String pCsldId, Long pOffId, Long pOffBookId, String pTransType,
			Long pTransNumber, Date pTransDate, String pSubActType, String pDedFlag, BigDecimal pReceiptAmount,
			Long pShadowId, Long pInfoNumber, String pModuleName, Integer txnSequence, String userId)
			throws CustomException {
		BigDecimal belowByAmt = BigDecimal.ZERO;
		BigDecimal amtAboveIndigent;
		String offIndFlag = null;
		String lTbdFlag = null;
		String lvValidDed = "N";
		String lvDoResidual = "Y";
		Integer lvLastExtPri = 0;
		Integer lvLastIntPri = 0;
		BigDecimal amntResidual = null;
		BigDecimal pDedAmount = BigDecimal.ZERO;
		String lvOdpFlag = null;
		String lvDedProcessFlag = null;
		String recOverpay = null;
		String cfExistFlag = null;
		BigDecimal amntOwing = null;
		BigDecimal subBal = null;
		TieredTransactionFeeAmounts feeAmounts = new TieredTransactionFeeAmounts();
		BigDecimal amntToDeduct = null;
		String cfppFalg = null;
		BigDecimal cfDedAmount = BigDecimal.ZERO;
		BigDecimal newAmount = BigDecimal.ZERO;
		String newTxnEntryDesc = null;
		Integer glSeq;
		String agyLoc = null;
		String csldType = trustService.getCaseloadType(pCsldId);
		Date effectDate;
		Date lRecursiveEndDate;
		String infoNumber = null;
		Long amtTlMax = 0L;
		Long amntMonthlyMax = 0L;
		Date lastMonthDedDate;
		Long amtMonthlyDeduct = 0L;
		Long recursiveAmt = 0L;
		String unlimDed = null;

		if (pReceiptAmount.compareTo(BigDecimal.ZERO) == -1) {
			throw new CustomException("Error: Receipt amount must be greater then zero.");
		}

		TransactionOperation transactionOperation = financialRepository.getTransactionOperationData(csldType, pCsldId);
		if (transactionOperation == null) {
			throw new CustomException("Error: Cannot find the transaction type for deduction for module ALLFRM1.");
		}
		String typeTrans = transactionOperation.getTxnType();
		String descTrans = transactionOperation.getDescription();
		BigDecimal paidToOtherCsldCode = transactionOperation.getBankDrAccountCode();
		String paidToOtherCsldPstngType = transactionOperation.getTxnPostingType();

		AccountCodes accountCodes = financialRepository.getAccountCodesData(pSubActType, csldType);
		if (accountCodes == null) {
			throw new CustomException("Error: Cannot find the G/L account code during deductions.");
		}
		Integer ofndrSubAcntCode = accountCodes.getAccountCode();

		lockOffenderDed(BigDecimal.valueOf(pOffId), pCsldId);
		String cfppFlag = getCfppFlag(BigDecimal.valueOf(pTransNumber));

		String indLogicFlag = financialRepository.getProfileValue();

		if (pOffBookId == null || csldType.equals("COMM")) {
			indLogicFlag = "N";
		}

		String overrideFlag = financialRepository.getOverrideFlag(pOffId);

		if (indLogicFlag.equals("Y") && overrideFlag.equals("N")) {
			try {
				agyLoc = financialRepository.getAgyLoc(pOffBookId);
				belowByAmt = subbalMinusMinbal(BigDecimal.valueOf(pOffId), pCsldId, agyLoc, pModuleName, pTransType);
			} catch (Exception e) {
				throw new CustomException(
						"Error: Cannot Retreive AGY_LOC_ID from Offender Bookings for: " + pOffBookId);
			}
		} else {
			belowByAmt = BigDecimal.ONE;
		}

		if (belowByAmt.intValue() <= 0) {
			offIndFlag = "Y";
		} else {
			offIndFlag = "N";
		}
		recOverpay = getRecOverpayFlag();

		// To do
		// GET_ACTIVE_COLLECTION_FEE(P_CSLD_ID,P_OFF_ID,CF_OFFENDER_DEDUCTION_ID,CF_DEDUCTION_TYPE,CF_EFFECTIVE_DATE);
		OffenderDeductions data = getActiveCollectionFee(pCsldId, BigDecimal.valueOf(pOffId), null, null, null);
		String deductionType = data.getDeductionType();
		List<String> lvIntPriArray = new ArrayList<>();
		List<String> lvExtPriArray = new ArrayList<>();
		for (int i = 0; i < 99; i++) {
			lvExtPriArray.add("N");
		}
		List<OffenderDeductions> doDeductionCList = financialRepository.doDeductionC(pCsldId, pOffId, pTransType,
				pInfoNumber, csldType, offIndFlag, pShadowId);
		if (txnSequence == null) {
			txnSequence = 0;
		}
		if (doDeductionCList != null && !doDeductionCList.isEmpty()) {
			for (OffenderDeductions doDeductionC : doDeductionCList) {
				String lvOblgType = null;
				amtTlMax = doDeductionC.getMaxRecursiveAmount() != null ? doDeductionC.getMaxRecursiveAmount().longValue():null;
				infoNumber = doDeductionC.getInformationNumber();
				amntMonthlyMax = doDeductionC.getMaxMonthlyAmount()!= null ? doDeductionC.getMaxMonthlyAmount().longValue(): null;
				lastMonthDedDate = doDeductionC.getMonthlyDeductionDate();
				amtMonthlyDeduct = doDeductionC.getDeductionAmount()!= null ? doDeductionC.getDeductionAmount().longValue():null;
				recursiveAmt = doDeductionC.getMaxRecursiveAmount()!=null? doDeductionC.getMaxRecursiveAmount().longValue(): null;
				unlimDed = doDeductionC.getUnlimitedDeduction();
				effectDate = doDeductionC.getEffectiveDate();
				OffenderDeductions deductionPirorityCur = financialRepository.deductionPirorityCur(pOffId,
						doDeductionC.getExternalPriorityNo(), pCsldId);

				amtAboveIndigent = pReceiptAmount;
				String lowHighFlag = trustService.getLowHighFlag();
				String methodFlag = financialRepository.getMethodFlag();

				if (!doDeductionC.getDeductionCategory().equals("ALCN") && csldType.equals("COMM")) {
					Integer lTbdCount = financialRepository.lTbdCountCur(doDeductionC.getOffenderDeductionId());
					if (lTbdCount > 0) {
						lTbdFlag = "Y";
					}
				}

				if (doDeductionC.getDeductionCategory().equals("CROB")
						|| doDeductionC.getDeductionCategory().equals("FXOB")) {
					try {
					 financialRepository.chkDedBenCur(doDeductionC.getOffenderDeductionId());
					} catch (Exception e) {
						throw new CustomException("Beneficiary for deduction " + doDeductionC.getOffenderDeductionId()
								+ " is not setup properly for offender " + pOffId);
					}
				}

				if (deductionPirorityCur.getExternalPriorityNo() != lvLastExtPri) {
					amntResidual = BigDecimal.ZERO;
					pDedAmount = BigDecimal.ZERO;
					lvOdpFlag = "N";
					lvDedProcessFlag = "D";
					lvDoResidual = "N";
				} else if (deductionPirorityCur.getInternalPriorityNo() != lvLastIntPri) {
					lvOdpFlag = "N";
					lvDedProcessFlag = "R";
					for (int i = 0; i < 100; i++) {
						lvIntPriArray.add("N");
					}
				} else if (deductionPirorityCur.getInternalPriorityNo() == lvLastIntPri) {
					lvOdpFlag = "Y";
				}

				if (!(lvExtPriArray.get(doDeductionC.getExternalPriorityNo()).equals("Y"))
						|| (lvExtPriArray.get(doDeductionC.getExternalPriorityNo()).equals("Y")
								&& amntResidual.compareTo(BigDecimal.ZERO) > 0 && lvOdpFlag.equals("N")
								&& doDeductionC.getFifoFlag().equals("Y"))
						|| (doDeductionC.getFifoFlag().equals("Y") && lvOdpFlag.equals("Y")
								&& amntResidual.compareTo(BigDecimal.ZERO) > 0)) {
					lvOblgType = getDedPeriod(doDeductionC.getMaxTotalAmount(), doDeductionC.getMaxMonthlyAmount(),
							doDeductionC.getMaxRecursiveAmount(), doDeductionC.getUnlimitedDeduction());
					if (doDeductionC.getDeductionCategory().equals("FXOB")) {
						if (lvOblgType.equals("TOTAL")
								&& doDeductionC.getDeductionAmount().compareTo(doDeductionC.getMaxTotalAmount()) < 0) {
							lvValidDed = "Y";
						} else if (lvOblgType.equals("MONTHLY") && doDeductionC.getMonthlyDeductionAmount()
								.compareTo(doDeductionC.getMaxMonthlyAmount()) < 0) {
							lvValidDed = "Y";
						} else if (lvOblgType.equals("RECURSIVE") && (doDeductionC.getMonthlyDeductionAmount()
								.compareTo(doDeductionC.getMaxRecursiveAmount()) < 0 || recOverpay.equals("Y"))) {
							lvValidDed = "Y";
						} else if (lvOblgType.equals("UNLIMITED")) {
							lvValidDed = "Y";
						}
					} else if (doDeductionC.getDeductionCategory().equals("DTF")) {
						if (lvOblgType.equals("UNLIMITED")) {
							lvValidDed = "Y";
						}
					} else if (doDeductionC.getDeductionCategory().equals("ALCN")
							&& doDeductionC.getCaseloadId().equals(pCsldId)) {
						if (lvOblgType.equals("TOTAL")) {
							BigDecimal lvOffBalance = null;
							lvOffBalance = trustService.getOffenderSubBal(doDeductionC.getCaseloadId(), csldType,
									pOffId, doDeductionC.getAccountCode());
							if (doDeductionC.getMaxTotalAmount().compareTo(lvOffBalance) > 0) {
								lvValidDed = "Y";
							}
						} else if (lvOblgType.equals("MONTHLY") && doDeductionC.getMonthlyDeductionAmount()
								.compareTo(doDeductionC.getMaxMonthlyAmount()) < 0) {
							lvValidDed = "Y";
						} else if (lvOblgType.equals("RECURSIVE") && (doDeductionC.getMonthlyDeductionAmount()
								.compareTo(doDeductionC.getMaxRecursiveAmount()) < 0 || recOverpay.equals("Y"))) {
							lvValidDed = "Y";
						} else if (lvOblgType.equals("UNLIMITED")) {
							lvValidDed = "Y";
						}
					} else if (doDeductionC.getDeductionCategory().equals("CROB")
							&& (doDeductionC.getDeductionAmount().compareTo(doDeductionC.getMaxTotalAmount()) < 0
									|| lvOblgType.equals("UNLIMITED"))) {
						lvValidDed = "Y";
					}
				}
				if (lvValidDed.equals("Y")) {
					lvExtPriArray.add("Y");
					lvLastExtPri = deductionPirorityCur.getExternalPriorityNo();
					lvIntPriArray.add("Y");
					lvLastIntPri = deductionPirorityCur.getInternalPriorityNo();
				}
				if (lvValidDed.equals("Y") || overrideFlag.equals("Y")) {
					if (indLogicFlag.equals("Y")
							&& (doDeductionC.getIndigentFlag() == null || doDeductionC.getIndigentFlag().equals("N"))
							&& overrideFlag.equals("N")) {
						// To do BELOW_BY_AMT :=
						// SUBBAL_MINUS_MINBAL(P_OFF_ID,P_CSLD_ID,AGY_LOC,P_MODULE_NAME, P_TRANS_TYPE);
						belowByAmt = subbalMinusMinbal(BigDecimal.valueOf(pOffId), pCsldId, agyLoc, pModuleName,
								pTransType);
						if (!(belowByAmt.add(pReceiptAmount).compareTo(BigDecimal.ZERO) <= 0)) {
							amtAboveIndigent = belowByAmt.add(pReceiptAmount);
						}
					}
					cfExistFlag = financialRepository.checkCollectionFee(doDeductionC.getOffenderDeductionId(),
							doDeductionC.getDeductionType());
					if (doDeductionC.getDeductionCategory().equals("DTF")) {
						feeAmounts = financialRepository.getTieredFeeAmounts(pCsldId, doDeductionC.getDeductionType(),
								pTransType, pReceiptAmount);
					}
					if (doDeductionC.getUnlimitedDeduction().equals("N")) {
						if (overrideFlag.equals("Y")) {
							amntOwing = financialRepository.getOverrideAmount(doDeductionC.getOffenderDeductionId(),
									doDeductionC.getDeductionType());
							if (!doDeductionC.getMaxRecursiveAmount().equals(BigDecimal.ZERO)) {
								doDeductionC.setMaxMonthlyAmount(amntOwing);
							}
						} else {
							if (doDeductionC.getMaxTotalAmount().equals(BigDecimal.ZERO)) {
								if (!doDeductionC.getMaxRecursiveAmount().equals(BigDecimal.ZERO)
										&& doDeductionC.getMaxMonthlyAmount().equals(BigDecimal.ZERO)) {
									// To do L_RECURSIVE_END_DATE := GET_RECURSIVE_END_DATE (P_OFF_ID, DED_ID);
									lRecursiveEndDate = getRecursiveEndDate(BigDecimal.valueOf(pOffId),
											doDeductionC.getOffenderDeductionId(),userId);
									SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
									Date cSydate = new Date();
									try {
										cSydate = formatter.parse(cSydate + "");
									} catch (Exception e) {
										logger.error(e);
									}
									// To do AMNT_OWING := GET_RECURSIVE_AMOUNT_OWING
									amntOwing = getRecursiveAmountOwing(effectDate, lRecursiveEndDate, cSydate,
											doDeductionC.getOffenderDeductionId(), null, null,
											doDeductionC.getMaxRecursiveAmount().longValue(), null, recOverpay);
								} else if (doDeductionC.getMaxRecursiveAmount().equals(BigDecimal.ZERO)) {
									amntOwing = Utilities.abs(doDeductionC.getMaxMonthlyAmount()
											.subtract(doDeductionC.getMonthlyDeductionAmount()));
								} else {
									subBal = BigDecimal.ZERO;
									if (doDeductionC.getDeductionCategory().equals("CROB")
											|| doDeductionC.getDeductionCategory().equals("FXOB")) {
										if (!lvDoResidual.equals("Y")) {
											BigDecimal tempValue = Utilities.abs(doDeductionC.getMaxTotalAmount()
													.subtract(doDeductionC.getDeductionAmount()));
											amntOwing = tempValue.compareTo(amntResidual) < 0 ? tempValue
													: amntResidual;
										}
									} else if (doDeductionC.getDeductionCategory().equals("ALCN")) {
										// To do TRUST.GET_SUB_BAL (P_CSLD_ID,P_OFF_ID,DED_TYPE,SUB_BAL);
										subBal = trustService.getSubBal(pCsldId, pOffId,
												doDeductionC.getDeductionType());
										amntOwing = Utilities.abs(doDeductionC.getMaxTotalAmount().subtract(subBal));
									}
								}
							}
						}
					}
					if(doDeductionC.getDeductionTxnEntry() != null) {
					BigDecimal tempVal = BigDecimal
							.valueOf(Math.ceil((doDeductionC.getDeductionTxnEntry().divide(pReceiptAmount))
									.multiply(BigDecimal.valueOf(100)).doubleValue()));
					if (!tempVal.equals(feeAmounts.getPercentage())) {
						doDeductionC.setDeductionTxnEntry(null);
					}
					}
				} else {
					if (overrideFlag.equals("Y")) {
						amntOwing = financialRepository.getOverrideAmount(doDeductionC.getOffenderDeductionId(),
								doDeductionC.getDeductionType());
					}
				}

				if (overrideFlag.equals("Y")) {
					amntToDeduct = amntOwing;
				} else {
					if( feeAmounts != null ) {

					if (doDeductionC.getFromBalanceType().equals("DB")) {
						amntToDeduct = doDeductionC.getDeductionTxnEntry() != null ? doDeductionC.getDeductionTxnEntry()
								: (pReceiptAmount.subtract(pDedAmount))
										.multiply((feeAmounts.getPercentage().divide(BigDecimal.valueOf(100))));
					} else {
						if(feeAmounts.getPercentage() != null) {
							
							amntToDeduct = doDeductionC.getDeductionTxnEntry() != null ? doDeductionC.getDeductionTxnEntry()
									: (pReceiptAmount.multiply(feeAmounts.getPercentage().divide(BigDecimal.valueOf(100))));
						}else {
							amntToDeduct = doDeductionC.getDeductionTxnEntry() != null ? doDeductionC.getDeductionTxnEntry()
									: (pReceiptAmount.multiply(doDeductionC.getNmbrPrcnt().divide(BigDecimal.valueOf(100))));
						}
					}
					}
				}

				if (feeAmounts.getPercentage() == null && feeAmounts.getFeeAmount() != null) {
					amntToDeduct = feeAmounts.getFeeAmount();
				} else if (feeAmounts.getFeeAmount() != null && feeAmounts.getPercentage() != null) {
					if (lowHighFlag.equals("L")) {
						if (feeAmounts.getFeeAmount().compareTo(amntToDeduct) < 0) {
							amntToDeduct = feeAmounts.getFeeAmount();
						}
					} else if (lowHighFlag.equals("H") && feeAmounts.getFeeAmount().compareTo(amntToDeduct) > 0) {
						amntToDeduct = feeAmounts.getFeeAmount();
					}
				}
				if (overrideFlag.equals("N") && indLogicFlag.equals("Y")
						&& (doDeductionC.getIndigentFlag() == null || doDeductionC.getIndigentFlag().equals("N"))) {
					amntToDeduct = amntToDeduct.compareTo(amtAboveIndigent) < 0 ? amntToDeduct : amtAboveIndigent;
				}
				if (doDeductionC.getUnlimitedDeduction().equals("N") && amntOwing!= null ) {
					if (amntToDeduct.compareTo(amntOwing) > 0) {
						if (amntResidual.compareTo(BigDecimal.ZERO) > 0) {
							if (amntOwing.compareTo(amntResidual) > 0) {
								amntToDeduct = amntResidual;
							} else {
								amntToDeduct = amntOwing;
							}
						} else {
							amntResidual = amntToDeduct.subtract(amntOwing);
							amntToDeduct = amntOwing;
						}
					} else {
						amntResidual = BigDecimal.ZERO;
					}
				}

				if (cfppFlag.equals("N") && overrideFlag.equals("N")) {
					BigDecimal lSubBal = null;
					// To do TRUST.CHK_MINIMUM_TRUST_BALANCE
					// (P_CSLD_ID,P_OFF_ID,P_TRANS_TYPE,DED_TYPE,AMNT_TO_DEDUCT,MIN_BAL_PASSED_FLAG);
					String minBalPassedFlag = null;
					minBalPassedFlag = trustService.chkMinimumTrustBalance(pCsldId, pOffId, pTransType,
							doDeductionC.getDeductionType(), amntToDeduct);
					if (minBalPassedFlag.equals("N")) {
						// To do TRUST.GET_SUB_BAL (P_CSLD_ID, P_OFF_ID, 'REG', L_SUB_BAL);
						lSubBal = trustService.getSubBal(pCsldId, pOffId, "REG");
						BigDecimal lMinTrustBalance = financialRepository.getMinTrustBalance(pCsldId,
								doDeductionC.getDeductionType(), pTransType);
						if (!methodFlag.equals("M1")) {
							amntToDeduct = lSubBal.subtract(lMinTrustBalance);
							if (amntToDeduct.compareTo(BigDecimal.ZERO) <= 0) {
								amntToDeduct = BigDecimal.ZERO;
							}
						}
					}
				}
				if (doDeductionC.getConfirmFlag().equals("Y") && amntToDeduct!= null && amntToDeduct.compareTo(BigDecimal.ZERO) > 0) {
					if (methodFlag.equals("M1")) {
						if (pDedAmount.add(amntToDeduct).compareTo(pReceiptAmount) > 0) {
							break;
						}
					}else if (methodFlag.equals("M2")) {
							if (pDedAmount.add(amntToDeduct).compareTo(pReceiptAmount) > 0) {
								amntToDeduct = pReceiptAmount.subtract(pDedAmount);
								if (doDeductionC.getUnlimitedDeduction().equals("N")) {
									if (amntToDeduct.compareTo(amntOwing) > 0) {
										if (amntResidual.compareTo(BigDecimal.ZERO) > 0) {
											if (amntOwing.compareTo(amntResidual) > 0) {
												amntToDeduct = amntResidual;
											} else {
												amntToDeduct = amntOwing;
											}
										} else {
											amntResidual = amntToDeduct.subtract(amntOwing);
											amntToDeduct = amntOwing;
										}
									}
								} else {
									amntResidual = BigDecimal.ZERO;
								}
							}
						}
						if (amntResidual.compareTo(BigDecimal.ZERO) > 0 && lvDoResidual.equals("Y")) {
							amntResidual = amntResidual.subtract(amntToDeduct);
						}
						pDedAmount = pDedAmount.add(amntToDeduct);
						if ("Y".equals(cfExistFlag)) {
							cfDedAmount = cfDedAmount.add(amntToDeduct);
						}
						if (pReceiptAmount.subtract(pDedAmount).compareTo(amntResidual) < 0) {
							amntResidual = pReceiptAmount.subtract(pDedAmount);
						}
						txnSequence = txnSequence + 1;
						if ("Y".equals(cfppFalg)) {
							newTxnEntryDesc = getCfppTxnDesc(pTransNumber, amntOwing);
							newAmount = BigDecimal.ZERO;
						} else {
							if (lvDoResidual.equals("Y")) {
								newTxnEntryDesc = descTrans.substring(0, 44) + "-" + doDeductionC.getDeductionType()
										+ "-" + doDeductionC.getInformationNumber() + " " + lvDedProcessFlag + "R";
							} else {
								newTxnEntryDesc = descTrans.substring(0, descTrans.length()-1) + "-" + doDeductionC.getDeductionType()
										+ "-" + doDeductionC.getInformationNumber() + " " + lvDedProcessFlag + "D";
							}
							newAmount = amntToDeduct;
						}
				}

				lTbdFlag = "N";
				if (!doDeductionC.getDeductionCategory().equals("ALCN") && csldType.equals("COMM")) {
					BigDecimal lTbdMaxDeductAmount = BigDecimal.ZERO;
					List<OffenderBeneficiaries> lTbdCheckCur = financialRepository
							.getLTbdCheckCur(doDeductionC.getOffenderDeductionId(), overrideFlag);
					for (OffenderBeneficiaries lRec : lTbdCheckCur) {
						lTbdMaxDeductAmount = lTbdMaxDeductAmount.add(lRec.getTotalOwing() != null ? new BigDecimal(lRec.getTotalOwing()) : BigDecimal.ZERO);
						if (lRec.getTbdFlag().equals("Y")) {
							lTbdFlag = "Y";
						}
					}
					newAmount = lTbdMaxDeductAmount.compareTo(newAmount) > 0 ? newAmount : lTbdMaxDeductAmount;
				}

				// To do TRUST.INSERT_INTO_OFFENDER_TRANS
				// (P_TRANS_NUMBER,TXN_SEQUENCE,P_CSLD_ID,P_OFF_ID,P_OFF_BOOK_ID,'DR',TYPE_TRANS,NEW_TXN_ENTRY_DESC,NEW_AMOUNT,P_TRANS_DATE,P_SUB_ACT_TYPE,'Y',P_RECEIPT_AMOUNT,DED_TYPE,ID_PAYEE_CORP,ID_PAYEE_PERSON,INFO_NUMBER,'Y','Y');
				txnSequence = txnSequence + 1;
				OffenderTransactions obj = new OffenderTransactions();
				obj.setTxnId(pTransNumber.intValue());
				obj.setTxnEntrySeq(txnSequence);
				obj.setCaseloadId(pCsldId);
				obj.setOffenderId(pOffId);
				obj.setOffenderBookId(pOffBookId);
				obj.setTxnPostingType("DR");
				obj.setTxnType(pTransType);
				obj.setTxnEntryDesc(newTxnEntryDesc);
				obj.setTxnEntryAmount(newAmount.doubleValue());
				obj.setTxnEntryDate(pTransDate);
				obj.setSubAccountType(pSubActType);
				obj.setSlipPrintedFlag("Y");
				obj.setPreWithholdAmount(pReceiptAmount.doubleValue());
				obj.setDeductionFlag("Y");
				obj.setPayeeCorporateId(doDeductionC.getPayeeCorporateId()!= null? doDeductionC.getPayeeCorporateId().intValue():null);
				obj.setPayeePersonId(doDeductionC.getPayeePersonId()!= null ?doDeductionC.getPayeePersonId().intValue():null);
				obj.setDeductionType(doDeductionC.getDeductionType());
				obj.setInfoNumber(doDeductionC.getInformationNumber());
				obj.setCreateUserId(userId);
				trustService.insertIntoOffenderTrans(obj);
				if (doDeductionC.getDeductionCategory().equals("ALCN")) {
					try {
					 financialRepository.getSubActType(doDeductionC.getAccountCode(), csldType);
					} catch (Exception e) {
						throw new CustomException("Allocation Sub Account Type Not defined.");
					}
					txnSequence = txnSequence + 1;

					obj.setTxnPostingType("CR");
					//Added by Srikanth for S4-24473
					obj.setTxnEntrySeq(txnSequence);
					trustService.insertIntoOffenderTrans(obj);
				}
				glSeq = 1;

				GlTransactions obj1 = new GlTransactions();
				obj1.setTxnId(pTransNumber.longValue());
				obj1.setTxnEntrySeq(txnSequence.longValue());
				obj1.setGlEntrySeq(glSeq.longValue());
				obj1.setAccountCode(BigDecimal.valueOf(ofndrSubAcntCode));
				obj1.setTxnEntryDate(pTransDate);
				obj1.setTxnType(pTransType);
				obj1.setTxnPostUsage("DR");
				obj1.setCaseloadId(pCsldId);
				obj1.setOffenderId(BigDecimal.valueOf(pOffId));
				obj1.setOffenderBookId(BigDecimal.valueOf(pOffBookId));
				obj1.setTxnEntryAmount(newAmount);
				obj1.setTxnEntryDesc(newTxnEntryDesc);
				obj1.setTxnReferenceNumber(null);
				obj1.setReconClearFlag("N");
				obj1.setTxnReversedFlag("N");
				obj1.setCreateUserId(userId);
				obj1.setPayeePersonId(doDeductionC.getPayeePersonId());
				obj1.setCreateDate(pTransDate);
				obj1.setPayeeCorporateId(doDeductionC.getPayeeCorporateId());
				obj1.setPayeeNameText(null);
				obj1.setReversedTxnId(null);
				obj1.setReversedTxnEntrySeq(null);
				obj1.setReversedGlEntrySeq(null);
				obj1.setDeductionId(BigDecimal.valueOf(doDeductionC.getOffenderDeductionId()));
				obj1.setTxnEntryTime(pTransDate);
				obj1.setInfoNumber(doDeductionC.getInformationNumber());
				trustService.insertGlTransNew(obj1);
				if (doDeductionC.getDeductionCategory().equals("CROB") && pCsldId != doDeductionC.getCaseloadId()) {
					doDeductionC.setAccountCode(paidToOtherCsldCode.intValue());
					doDeductionC.setTxnPostingType(paidToOtherCsldPstngType);
				}
				glSeq = 2;
				// To do TRUST.INSERT_GL_TRANS_NEW
				// ('CR',CODE_ACNT,TYPE_PSTNG,P_CSLD_ID,TYPE_TRANS,NEW_AMOUNT,P_TRANS_NUMBER,P_TRANS_DATE,NEW_TXN_ENTRY_DESC,TXN_SEQUENCE,GL_SEQ,TMP_OFF_ID,TMP_OFF_BOOK_ID,INFO_NUMBER,ID_PAYEE_PERSON,ID_PAYEE_CORP,'','','','','',DED_ID);

				obj1.setTxnPostUsage("CR");
				obj1.setGlEntrySeq(glSeq.longValue());
				trustService.insertGlTransNew(obj1);

				if (!doDeductionC.getDeductionCategory().equals("ALCN")) {
					String lvOrigCsldFlag;
					if (doDeductionC.getDeductionCategory().equals("CROB")
							&& pCsldId.equals(doDeductionC.getCaseloadId())) {
						lvOrigCsldFlag = "Y";
					} else {
						lvOrigCsldFlag = "N";
					}

					/*
					 * Current date is sent in place of Effect_date, MaxTotalAmount is sent in place
					 * of Amt_TL_MAX , doDeductionC.getMaxRecursiveAmount().longValue() is used for
					 * RECURSIVE_AMOUNT
					 */
					doBeneficiaryTransactions(pCsldId, pOffId, doDeductionC.getOffenderDeductionId(),
							newAmount.longValue(), pTransNumber, txnSequence.longValue(), glSeq.longValue(),
							doDeductionC.getAccountCode(), "CR", typeTrans, newTxnEntryDesc, overrideFlag,
							doDeductionC.getMaxTotalAmount().longValue(), effectDate,
							doDeductionC.getMaxRecursiveAmount().longValue(), lvOrigCsldFlag, pTransType,userId);
				}
				updateOffenderDeductionsFin(pOffId, deductionType, infoNumber, amtTlMax, amntToDeduct!= null ? amntToDeduct.longValue():0,
						doDeductionC.getOffenderDeductionId(), effectDate, amntMonthlyMax, lastMonthDedDate,
						amtMonthlyDeduct, recursiveAmt, unlimDed, userId);
				if (csldType.equals("COMM")) {
					BigDecimal pGrpId = null;
					String pInfoNum = null;
					OffenderDeductions offenderDeductions = financialRepository
							.getOffenderDeductions(doDeductionC.getOffenderDeductionId());
					pGrpId = offenderDeductions.getGroupId();
					pInfoNum = offenderDeductions.getInformationNumber();
					if (pGrpId != null) {
						// TO do FINANCIAL.UPDATE_PAYMENT_PLAN_SCHEDULES
						// (P_OFF_ID,AMNT_TO_DEDUCT,AMNT_MNTHLY_MAX,P_INFO_NUM,P_GRP_ID);
						PaymentPlanTransaction payPlanTxnObj = new PaymentPlanTransaction();
						payPlanTxnObj.setOffenderId(BigDecimal.valueOf(pOffId));
						payPlanTxnObj.setTransactionAmount(amntToDeduct);
						payPlanTxnObj.setInformationNumber(pInfoNum);
						payPlanTxnObj.setGroupId(pGrpId);
						updatePaymentPlanSchedules(payPlanTxnObj, amntMonthlyMax, userId);
					}
				}
				OffenderBeneficiaries oldRec = financialRepository.getOffenderBeneficiariesById(recursiveAmt);
				OffenderBeneficiaries newRec = new OffenderBeneficiaries();
				newRec.setOffenderDeductionId(doDeductionC.getOffenderDeductionId());
				newRec.setSealFlag(doDeductionC.getSealFlag());
				//Trigger call from offender_beneficiaries table
				offenderBeneficiariesT1Service.offenderBeneficiariesT1Trigger(newRec, oldRec);
				OffenderBeneficiaries offobj = new OffenderBeneficiaries();
				offenderBeneficiariesT2Service.offenderBeneficiariesT2Trigger(offobj, "INSERT");
				financialRepository.updateOverrideAmount(doDeductionC.getOffenderDeductionId(),userId);
				
				offobj.setBeneficiaryId(recursiveAmt);
				offobj.setCreateUserId(userId);				
			
				if (lTbdFlag.equals("Y")) {
					break;
				}
			}
		}
		
		return null;
	}

	@Override
	public String getDedPeriod(BigDecimal pTotalMax, BigDecimal pMonthlyMax, BigDecimal pRecursiveAmt,
			String pUnlimDedFlag) {
		String lvOblgType = null;
		if (pTotalMax.compareTo(BigDecimal.ZERO) > 0) {
			lvOblgType = "TOTAL";
		} else if (pMonthlyMax.compareTo(BigDecimal.ZERO) > 0) {
			lvOblgType = "MONTHLY";
		} else if (pRecursiveAmt.compareTo(BigDecimal.ZERO) > 0) {
			lvOblgType = "RECRUSIVE";
		} else if (pUnlimDedFlag.equals("Y")) {
			lvOblgType = "UNLIMITED";
		}
		return lvOblgType;
	}

	@Override
	public void lockOffenderDed(BigDecimal pOffId, String pCsldId) {
		financialRepository.lvDedLock(pOffId, pCsldId);
		financialRepository.lvBenLock(pOffId);
	}

	@Override
	public String getCfppFlag(BigDecimal pTransNumber) {
		String lvCfppFlag = null;
		lvCfppFlag = financialRepository.getCfppFlag(pTransNumber);
		return lvCfppFlag;
	}

	@Override
	public BigDecimal subbalMinusMinbal(BigDecimal pOffId, String pCsldId, String pAgyLocId, String modName,
			String txnType) {
		BigDecimal trustAcCode;
		BigDecimal pAmount = null;
		BigDecimal pMinBal = null;
		String lvIndigencyLevel;
		String lvCsldIdExists;

		trustAcCode = financialRepository.getTrustAccCode(pCsldId, modName, txnType);
		OffenderSubAccounts offSub = financialRepository.getAmountPMinbal(pCsldId, pOffId, trustAcCode);
		if (offSub != null) {
			pAmount =BigDecimal.valueOf( offSub.getBalance());
			pMinBal = offSub.getMinimumBalance();
			if (pMinBal == null) {
				lvCsldIdExists = financialRepository.chkCaseloadC(pCsldId);
				if (lvCsldIdExists != null) {
					pMinBal = financialRepository.getInstMiniBal(pCsldId, pAgyLocId, trustAcCode);
				} else {
					SystemProfiles sysPro = financialRepository.systemProfileC();
					lvIndigencyLevel = sysPro.getProfileValue();
					pMinBal = new BigDecimal(lvIndigencyLevel);
				}
			}
		}
		BigDecimal SubBal =BigDecimal.ZERO;
		if(pAmount!=null && pMinBal!=null) {
			 SubBal =pAmount.compareTo(pMinBal)==1 ? pAmount.subtract(pMinBal): pMinBal.subtract(pAmount) ;
		}
		return SubBal;
		
		
	}

	@Override
	public String getRecOverpayFlag() {
		return financialRepository.getRecOverpayFlag();
	}

	@Override
	public OffenderDeductions getActiveCollectionFee(String pCsldId, BigDecimal pOffId, BigDecimal pOffDedId,
			String dedType, Date effectiveDate) {
		return financialRepository.getActiveCollectionFee(pOffId, pCsldId);
	}

	@Override
	public Date getRecursiveEndDate(BigDecimal pOffId, Long pOffDedId,String userId) {
		Date cSysDate = new Date();
		Date lEndDate = null;
		Long lOffBookId = null;
		lEndDate = financialRepository.lPaymentPlanEnddateCur(pOffDedId);
		if (lEndDate == null) {
			BigDecimal lOffenderBookId = financialRepository.lOffenderBookIdCur(pOffId,userId);
			if (lOffenderBookId != null) {
				lOffBookId = lOffenderBookId.longValue();
			}
			lEndDate = financialRepository.lSentenceEnddateCur(cSysDate, lOffBookId);
			if (lEndDate == null) {
				lEndDate = financialRepository.addMonths(cSysDate, 24);
			}
		}
		return lEndDate;
	}

	@Override
	public BigDecimal getRecursiveAmountOwing(Date pDedEffDate, Date pDedEndDate, Date pSysDate, Long offDedId,
			Long pPriority, Long pBeneficiaryId, Long pMaxRecursiveAmt, Long pMonBeneficiaryAmt, String pOverpayFlag) {
		Long lTotalPaid = null;

		lTotalPaid = financialRepository.lBeneficiaryPaidCur(offDedId, pBeneficiaryId, pPriority);
		if (lTotalPaid == null) {
			lTotalPaid = 0L;
		}
		return null;
	}

	@Override
	public String getCfppTxnDesc(Long pTransNumber, BigDecimal amntOwing) {
		String lvCppLocation = null;
		String lvCppCommentText = null;
		OffenderCreditPriorPayments offCPP = financialRepository.getCfppLocation(pTransNumber);
		if (offCPP != null) {
			lvCppLocation = offCPP.getLocation();
			lvCppCommentText = offCPP.getCommentText();
		}
		 financialRepository.lvTxnEntryDesc(amntOwing, lvCppLocation, lvCppCommentText);

		return null;
	}

	@Override
	public void doBeneficiaryTransactions(String pCaseloadId, Long pOffId, Long pOffDedId, Long pAmntToDeduct,
			Long pTxnId, Long pTxnEntrySeq, Long pGlEntrySeq, Integer pAccountCode, String pTxnPostType,
			String pTxnType, String pTxnEntryDesc, String pOverrideFlag, Long pMaxTotalAmt, Date pEffectiveDate,
			Long pMaxReccursiveAmt, String pOrigCsldCrobFlag, String pReceiptTxnType,String userId) {

		Long lAmntToDeduct;
		Long lBenEntrySeq = 0L;
		BigDecimal lRecursivePriorityOwing = BigDecimal.ZERO;
		Date lRecursiveEndDate = null;
		Long lBenePercent;
		Long lBeneOwing = 0L;
		Long lRestPriorotyOwing = 0L;
		Long lDedAmountTemp = 0L;
		Long lAvailAmount;
		Long lResidueAmount;
		Long lAmountToBene;
		String lRecursiveFlag;
		String lMonthlyFlag;
		String lRecoveryOvepayFlag;
		Long lMaxTotalAmount = 0L;
		Long lMaxMonthlyAmount = 0L;
		Long lMaxRecursiveAmount = 0L;
		Long lPriorityRunTotal;
		Date cSysDate = new Date();

		OffenderDeductions offDed = financialRepository.lDeductionCur(pOffDedId);
		if (offDed != null) {
			lMaxTotalAmount = offDed.getMaxTotalAmount().longValue();
			lMaxMonthlyAmount = offDed.getMaxMonthlyAmount().longValue();
			lMaxRecursiveAmount = offDed.getMaxRecursiveAmount().longValue();
		}
		lRecoveryOvepayFlag = financialRepository.lRecursiveOverpayCur();
		if (lRecoveryOvepayFlag == null) {
			lRecoveryOvepayFlag = "N";
		}
		 financialRepository.lOffenderBookIdCurOne(pCaseloadId, BigDecimal.valueOf(pOffId));
		if (("Y").equalsIgnoreCase(pOverrideFlag)) {
			List<OffenderBeneficiaries> offB = new ArrayList<OffenderBeneficiaries>();
			offB = financialRepository.lOverrideCure(pOffDedId);
			for (OffenderBeneficiaries obj : offB) {
				lBenEntrySeq = lBenEntrySeq + 1;
				insertIntoBenTransactionsOne(pTxnId, pTxnEntrySeq, pGlEntrySeq, lBenEntrySeq, pOffDedId,
						obj.getPersonId(), obj.getCorporateId(), obj.getUnknownBenId(), pAccountCode.longValue(),
						pTxnPostType, pTxnType, pTxnEntryDesc, obj.getOverrideAmount().longValue(), pCaseloadId, null,
						null, null, null, "", pOrigCsldCrobFlag, pReceiptTxnType, obj.getBeneficiaryId() , userId);
			}
		} else {
			lAmntToDeduct = pAmntToDeduct;
			if (lMaxRecursiveAmount > 0) {
				lRecursiveFlag = "Y";
			} else {
				lRecursiveFlag = "N";
			}
			if (lMaxMonthlyAmount > 0) {
				lMonthlyFlag = "Y";
			} else {
				lMonthlyFlag = "N";
			}
			List<OffenderBeneficiaries> lPriorityCurList = new ArrayList<OffenderBeneficiaries>();
			lPriorityCurList = financialRepository.lPriorityCur(pOffDedId);
			for (OffenderBeneficiaries lPriorityRec : lPriorityCurList) {
				if (lRecursiveFlag.equalsIgnoreCase("Y")) {
					lRecursiveEndDate = getRecursiveEndDate(BigDecimal.valueOf(pOffId), pOffDedId,userId);
					lRecursivePriorityOwing = getRecursiveAmountOwing(pEffectiveDate, lRecursiveEndDate, cSysDate,
							pOffDedId, lPriorityRec.getPriority().longValue(), null, lMaxRecursiveAmount, null,
							lRecoveryOvepayFlag);
				}
				lPriorityRunTotal = 0L;
				List<OffenderBeneficiaries> lOffBencurList = new ArrayList<OffenderBeneficiaries>();
				lOffBencurList = financialRepository.lBeneficiaryCur(pOffDedId, lPriorityRec.getPriority().longValue(),
						lRecursiveFlag, lMonthlyFlag);
				for (OffenderBeneficiaries lBenRec : lOffBencurList) {
					lBenePercent = lBenRec.getAmount().longValue() / lPriorityRec.getAmount().longValue();
					if (lMaxTotalAmount > 0) {
						lBeneOwing = lBenRec.getAmount().longValue() - lBenRec.getReceivedAmount().longValue();
						lRestPriorotyOwing = lPriorityRec.getReceivedAmount().longValue() - lBeneOwing;
					} else if (lMaxMonthlyAmount > 0) {
						lBeneOwing = 0L;
						lBeneOwing = financialRepository.lBeneficiaryMonOwingCur(pOffDedId,lBenRec.getBeneficiaryId());
						lBeneOwing = Math.min(lBenRec.getMonthlyAmount()!=null ? lBenRec.getMonthlyAmount().intValue():0, lMaxMonthlyAmount) -  lBeneOwing;

					} else if (lMaxRecursiveAmount > 0) {
						getRecursiveAmountOwing(pEffectiveDate, lRecursiveEndDate, cSysDate, pOffDedId, null,
								lBenRec.getBeneficiaryId(), null, lBenRec.getAmount().longValue(), lRecoveryOvepayFlag);
						lRestPriorotyOwing = lRecursivePriorityOwing.longValue() - lBeneOwing;
					} else {
						lBeneOwing = lBenRec.getAmount().longValue();
						lRestPriorotyOwing = lBeneOwing;
					}
					lDedAmountTemp = lAmntToDeduct;
					lAvailAmount = Math.round(lDedAmountTemp.doubleValue() * lBenePercent.doubleValue());
					lDedAmountTemp = lDedAmountTemp - lAvailAmount;
					lResidueAmount = lDedAmountTemp - lRestPriorotyOwing;
					if (lResidueAmount > 0) {
						lAvailAmount = lAvailAmount + lResidueAmount;
					}
					lAmountToBene = Math.min(lAvailAmount, lBeneOwing);
					lPriorityRunTotal = lPriorityRunTotal + lAmountToBene;
					lBenEntrySeq = lBenEntrySeq + 1;
					insertIntoBenTransactionsOne(pTxnId, pTxnEntrySeq, pGlEntrySeq, lBenEntrySeq, pOffDedId,
							lBenRec.getPersonId(), lBenRec.getCorporateId(), lBenRec.getUnknownBenId(),
							pAccountCode.longValue(), pTxnPostType, pTxnType, pTxnEntryDesc, lAmountToBene, pCaseloadId,
							null, null, null, null, "", pOrigCsldCrobFlag, pReceiptTxnType, lBenRec.getBeneficiaryId() , userId);
				}
				lAmntToDeduct = lAmntToDeduct - lPriorityRunTotal;
			}
		}
	}

	@Override
	public void insertIntoBenTransactionsOne(Long pTxnId, Long pTxnEntrySeq, Long pGlSeq, Long pBenSeq,
			Long pDeductionId, BigDecimal pPersonId, BigDecimal pCorporateId, BigDecimal pUnknownBenId,
			Long pAccountCode, String pTxnPostType, String pTxnType, String pTxnEntryDesc, Long pAmountToBen,
			String pCsldId, Long pRevTxnId, Long pRevEntrySeq, Long pRevGlSeq, Long pRevBenSeq, String pRevTxnFlag,
			String pOrigCsldCrobFlag, String pReceiptTxnType, Long pBeneficiaryId , String userId) {

		Long vAmountToBen = pAmountToBen;
		String cfppFlag;
		Long newAmount;
		BigDecimal cfppPayment = BigDecimal.ZERO;

		cfppFlag = financialRepository.insertBenTxnsgetCfppFlag(pTxnId);
		if (cfppFlag.equalsIgnoreCase("Y")) {
			newAmount = 0L;
		} else {
			newAmount = pAmountToBen;
		}
		if (cfppFlag.equalsIgnoreCase("Y")) {
			if (pPersonId != null) {
				cfppPayment = financialRepository.cfppPaymentPerCur(pDeductionId, pPersonId);
			} else if (pCorporateId != null) {
				cfppPayment = financialRepository.cfppPaymentCorpCur(pDeductionId, pCorporateId);
			}
		 financialRepository.vTxnEntryDesc(cfppPayment, pTxnEntryDesc);
		}
		if (pOrigCsldCrobFlag.equalsIgnoreCase("N")) {
			BeneficiaryTransactions insertObj = new BeneficiaryTransactions();
			insertObj.setTxnId(pTxnId);
			insertObj.setTxnEntrySeq(pTxnEntrySeq);
			insertObj.setGlEntrySeq(pGlSeq);
			insertObj.setBenEntrySeq(pBenSeq);
			insertObj.setOffenderDeductionId(BigDecimal.valueOf(pDeductionId));
			insertObj.setAccountCode(BigDecimal.valueOf(pAccountCode));
			insertObj.setPersonId(pPersonId);
			insertObj.setCorporateId(pCorporateId);
			insertObj.setUnknownBenId(pUnknownBenId);
			insertObj.setTxnPostUsage(pTxnPostType);
			insertObj.setTxnEntryDate(null);
			insertObj.setTxnEntryTime(null);
			insertObj.setTxnEntryAmount(BigDecimal.valueOf(newAmount));
			insertObj.setCaseloadId(pCsldId);
			insertObj.setTxnType(pTxnType);
			insertObj.setTxnEntryDesc(pTxnEntryDesc);
			insertObj.setModifyUserId(null);
			insertObj.setModifyDate(null);
			insertObj.setRevTxnId(pRevTxnId != null ? BigDecimal.valueOf(pRevTxnId) : null);
			insertObj.setRevTxnEntrySeq(pRevEntrySeq != null ? BigDecimal.valueOf(pRevEntrySeq) : null);
			insertObj.setRevGlEntrySeq(pRevGlSeq != null ? BigDecimal.valueOf(pRevGlSeq) : null);
			insertObj.setRevBenEntrySeq(pRevGlSeq != null ? BigDecimal.valueOf(pRevBenSeq) : null);
			insertObj.setRevTxnFlag("N");
			insertObj.setReceiptTxnType(pReceiptTxnType);
			insertObj.setBeneficiaryId(BigDecimal.valueOf(pBeneficiaryId));
			insertObj.setCreateDateTime(null);
			insertObj.setCreateUserId(userId);
			

			financialRepository.insertIntoBenficiaryTransactions(insertObj);
			

			if (pRevTxnFlag != null && pRevTxnFlag.equalsIgnoreCase("Y")) {
				financialRepository.updateIntoBeneficiaryTransactions(pRevTxnId, pRevEntrySeq, pRevGlSeq, pRevBenSeq);
			}
		}
		if (pTxnPostType != null && pTxnPostType.equalsIgnoreCase("DR")) {
			vAmountToBen = -1 * vAmountToBen;
		}
		if (cfppFlag != null && cfppFlag.equalsIgnoreCase("N")) {
			updateOffenderBeneficiaries(BigDecimal.valueOf(pDeductionId), pPersonId, pCorporateId,
					BigDecimal.valueOf(vAmountToBen), "user");
			if ((pPersonId != null || pCorporateId != null) && pOrigCsldCrobFlag != null
					&& pOrigCsldCrobFlag.equalsIgnoreCase("N")) {
			}
		} else {
			updateOffenderBeneficiaries(BigDecimal.valueOf(pDeductionId), pPersonId, pCorporateId, cfppPayment, "USer");
		}
	}

	@Override
	public void updateOffenderDeductionsFin(Long pOffId, String pDedType, String pInfoNumber, Long pMaxTotalAmount,
			Long pAmtToDeduct, Long pOffDedId, Date pEffectDate, Long pMaxMonthlyAmt, Date pLastMonthDeductDate,
			Long pLastMonthDeductAmount, Long pMaxRecursiveAmt, String pUnlimitedFlag, String userName) {

		Long vAmountResidual;
		Date lLastRecursivePaidDate = null;
		Long lLastRecursiveAmount = 0L;
		Date lRecursiveStartDate;
		Date lRecursiveEndDate;
		Long lAmountToApply = 0L;
		Date lCurrentMonth;

		financialRepository.updateOffenderDeductionsFinOffBeneficiaries(pOffDedId,userName);
		financialRepository.updateOffenderDeductionsFinOffDed(pOffDedId, pAmtToDeduct, userName);
		if (pUnlimitedFlag != null && !pUnlimitedFlag.equalsIgnoreCase("Y") && pMaxTotalAmount == 0
				&& pMaxRecursiveAmt > 0) {
			OffenderMonDeductions lLastRecursivePaidCur = new OffenderMonDeductions();
			lLastRecursivePaidCur = financialRepository.lLastRecursivePaidCur(pOffDedId);
			lLastRecursivePaidDate = lLastRecursivePaidCur.getMonthlyDeductionDate();
			lLastRecursiveAmount = lLastRecursivePaidCur.getDeductionAmount();
			if (lLastRecursivePaidDate == null) {
				lRecursiveStartDate = pEffectDate;
			} else {
				lRecursiveStartDate = lLastRecursivePaidDate;
			}
			lRecursiveEndDate = getRecursiveEndDate(BigDecimal.valueOf(pOffId), pOffDedId,userName);
			vAmountResidual = pAmtToDeduct;
			if (lLastRecursiveAmount != null && lLastRecursiveAmount > 0) {
				lAmountToApply = Math.min(vAmountResidual, (pMaxRecursiveAmt - lLastRecursiveAmount));

			 financialRepository.updateOffMonDeductions(lAmountToApply, lAmountToApply,
						pOffDedId, userName);
				vAmountResidual = vAmountResidual - lAmountToApply;
				lRecursiveStartDate = financialRepository.addMonths(lRecursiveStartDate, 1);
			}
			if (vAmountResidual > 0) {
				lRecursiveStartDate = financialRepository.addMonths(lRecursiveStartDate, -1);
				Integer lDateIdx = financialRepository.monthsBetweenUpdateFin(lRecursiveStartDate, lRecursiveEndDate);
				for (int i = 1; i < lDateIdx; i++) {
					lAmountToApply = Math.min(vAmountResidual, pMaxRecursiveAmt);
					lCurrentMonth = financialRepository.addMonths(lRecursiveStartDate, lDateIdx);
					OffenderMonDeductions insertObj = new OffenderMonDeductions();
					insertObj.setOffenderDeducttionId(pOffDedId);
					insertObj.setDeductionAmount(lAmountToApply);
					insertObj.setMonthlyDeductionDate(lCurrentMonth);
					insertObj.setCreateUserId(userName);
					 financialRepository.insertOffMonDeductions(insertObj);
					vAmountResidual = vAmountResidual - lAmountToApply;
					if (vAmountResidual <= 0) {
					}
				}
			} else if (pMaxMonthlyAmt > 0) {
				Integer updOffMonDed = 0;
				updOffMonDed = financialRepository.updateOffMonDeductionsOne(pAmtToDeduct, pOffDedId, userName);
				if (updOffMonDed == 0) {
					OffenderMonDeductions insertObj = new OffenderMonDeductions();
					Date sysDate = new Date();
					insertObj.setOffenderDeducttionId(pOffDedId);
					insertObj.setDeductionAmount(pAmtToDeduct);
					insertObj.setMonthlyDeductionDate(sysDate);
					insertObj.setCreateUserId(userName);
					 financialRepository.insertOffMonDeductions(insertObj);
				}
			}
		}
	}

	@Override
	public void processCollectionFees(OffenderTransactions offTxn, Long cOffDedId, BigDecimal cfDedAmt) {
	 financialRepository.chkDedBenCurOne(cOffDedId);
	}

}
