package net.syscon.s4.inmate.trust.checks.impl;

import java.math.BigDecimal;
import java.util.Arrays;
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

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.core.EliteDateService;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.BankChequeData;
import net.syscon.s4.im.beans.CaseloadCurrentAccounts;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.inmate.beans.BankChequeRegisters;
import net.syscon.s4.inmate.beans.BankChequeRegistersCommitBean;
import net.syscon.s4.inmate.beans.BeneficiaryTransactions;
import net.syscon.s4.inmate.beans.GlTransactions;
import net.syscon.s4.inmate.trust.checks.OtdcrvoiRepository;
import net.syscon.s4.inmate.trust.checks.OtdcrvoiService;
import net.syscon.s4.pkgs.deductions.DeductionsService;
import net.syscon.s4.pkgs.trust.TrustService;

/**
 * Class OtdcrvoiServiceImpl
 */
@Service
public class OtdcrvoiServiceImpl extends BaseBusiness implements OtdcrvoiService {

	private static Logger logger = LogManager.getLogger(OtdcrvoiServiceImpl.class.getName());

	@Autowired
	private OtdcrvoiRepository otdcrvoiRepository;

	@Autowired
	private EliteDateService eliteDateService;
	
	@Autowired
	private TrustService trustService;
	
	@Autowired
	private DeductionsService deductionsService;

	/**
	 * Creates new OtdcrvoiServiceImpl class Object
	 */
	public OtdcrvoiServiceImpl() {
		// OtdcrvoiServiceImpl
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 */
	public BankChequeData bankCrOnCheckDeleteMaster(final BankChequeData paramBean) {
		return otdcrvoiRepository.bankCrOnCheckDeleteMaster(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 * @return
	 *
	 */
	public void cgfkchkBankCrBankCrGlAc(final CaseloadCurrentAccounts paramBean) {
		return;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<BankChequeRegisters> bankCrExecuteQuery(final BankChequeRegisters searchRecord) {
		return otdcrvoiRepository.bankCrExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstBANK_CR
	 *
	 */
	@Transactional(rollbackFor = Exception.class)
	public String bankCrCommit(final BankChequeRegistersCommitBean commitBean) {
		String liReturn = "0";
		// updateRecords
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (final BankChequeRegisters data : commitBean.getUpdateList()) {
				data.setCreateUserId(commitBean.getCreateUserId());
				liReturn = reverseChequeVoid(data);
				otdcrvoiRepository.bankCrUpdateBankChequeRegisters(commitBean.getUpdateList());
			}
		}
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<BankChequeData> bnkCdExecuteQuery(final BankChequeData searchRecord) {
		final List<BankChequeData> resultList = otdcrvoiRepository.bnkCdExecuteQuery(searchRecord);
		if (resultList != null) {
			resultList.forEach(data -> {
				if (data.getTxnId() != null) {
					final BankChequeData offenderId = otdcrvoiRepository.getOffenderId(data.getTxnId());
					if (offenderId != null && offenderId.getOffenderId() != null) {
						data.setOffenderId(offenderId.getOffenderId());
					}
				}
				if (null != data.getOffenderId()) {
					//final String closedFlag = otdcrvoiRepository.chkAccountStatus(data.getCaseloadId(),data.getOffenderId());
					//Procedure call
					final Map<String,Object> closedFlag1 = trustService.chkAccountStatus(data.getCaseloadId(), data.getOffenderId());
					final String closedFlag = String.valueOf(closedFlag1.get("P_OPEN_AN_ACCOUNT"));
					data.setClosedFlag(closedFlag);
				}
			});
		}
		return resultList;

	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<ReferenceCodes> cgfkBankCrChequeStatusRecordGroup() {
		return otdcrvoiRepository.cgfkBankCrChequeStatusRecordGroup();

	}

	public List<ReferenceCodes> cgfkBankCrAccountCodeRecordGroup(final String caseloadId,String userName) {
		final List<ReferenceCodes> resultList = otdcrvoiRepository.cgfkBankCrAccountCodeRecordGroup(caseloadId,userName);
		if (resultList != null) {
			resultList.forEach(data -> {
				data.setDomain(data.getDescription());
				data.setDescription(data.getCode());
			});
		}
		return resultList;

	}

	@Override
	public Map<String, Object> whenValidatingItem(final BigDecimal txnId) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		BigDecimal tempTxnId = txnId;
		String tempType = null;
		String clearFlag = null;
		try {
			tempType = otdcrvoiRepository.whenValidateGetCheckTxnType(tempTxnId);
			String[] tempTyps = { "TOR", "TOS", "TOB", "TIR", "TIS" };
			if (Arrays.asList(tempTyps).contains(tempType)) {
				resultMap.put("errorMessage", "otdcrvoi.cntvoidarcvedtrnschk");
				resultMap.put("errorCode", -1);
			} else {
				try {
					clearFlag = otdcrvoiRepository.whenValidateGetCheckClearTxn(tempTxnId);
					if ("Y".equals(clearFlag)) {
						resultMap.put("errorMessage", "otdcrvoi.cntvidclrchk");
						resultMap.put("errorCode", -2);
					} else {
						resultMap.put("errorCode", null);
					}
				} catch (Exception e) {
					logger.error("Error in When_Validate_Items, checking Cleared Cheque.", e);
					resultMap.put("errorMessage", "otdcrvoi.errinvaliitem");
					resultMap.put("errorCode", -3);
				}
			}
		} catch (Exception e) {
			logger.error("Error in When_Validate_Items, checking Received Transferred Cheque.", e);
			resultMap.put("errorMessage", "otdcrvoi.errchkngrcdtrnsfund");
			resultMap.put("errorCode", -4);
		}
		return resultMap;

	}

	@Override
	public List<String> verifyTxnTypeCount(final BigDecimal txnId) {
		return otdcrvoiRepository.verifyTxnTypeCount(txnId);
	}

	private String reverseChequeVoid(BankChequeRegisters param) {
		Integer nmbrSqnc = null;
		Long glSqnc = null;
		String inst = null;
		Integer acnt = null;
		Double amnt = null;
		String typeTrans = null;
		String transPosting = null;
		String accPosting = null;
		Integer recordNumber = 0;
		String newTransPstng = null;
		BigDecimal newTrans = null;
		Date newDate = trunc(eliteDateService.getDBTime());
		Long offender = null;
		Long ofndrBk = null;
		BigDecimal temp1 = param.getTxnId();
		String temp2 = param.getChequeFlag();
		String temp3 = param.getPayeeNameText();
		Long temp4 = param.getChequeNumber();
		String checkStatus2 = null;
		String vTxnReversedFlag = "Y";
		try {
			List<OffenderTransactions> cOnes = otdcrvoiRepository.getCOneOffenderDetail(temp1, temp2, temp3);
			if (cOnes != null) {
				for (OffenderTransactions cOne : cOnes) {
					nmbrSqnc = cOne.getTxnEntrySeq();
					glSqnc = cOne.getGlEntrySeq();
					inst = cOne.getCaseloadId();
					acnt = cOne.getAccountCode().intValue();
					amnt = cOne.getTxnEntryAmount();
					typeTrans = cOne.getTxnType();
					transPosting = cOne.getTxnPostUsage();
					accPosting = cOne.getTxnPostingType();
					offender = cOne.getOffenderId();
					ofndrBk = cOne.getOffenderBookId();

					if (offender != null) {
						//checkStatus2 = otdcrvoiRepository.chkAccountStatus(inst, BigDecimal.valueOf(offender));
						//Procedure call
						final Map<String, Object> checkStatus = trustService.chkAccountStatus(inst,BigDecimal.valueOf(offender));
						checkStatus2 = String.valueOf(checkStatus.get("P_OPEN_AN_ACCOUNT"));
						if ("Y".equals(checkStatus2)) {
							// RE-OPEN CLOSED TRUST ACCOUNT
							otdcrvoiRepository.updateOffenderTrustAccountsReOpen(offender, inst);
							BigDecimal rTxnNum = otdcrvoiRepository.genTrustTrans("TXN_ID");
							Integer rTxnEntrySeq = 1;
							String rCsldId = inst;
							Long rOffId = offender;
							Long rOffBookId = ofndrBk;
							String rTxnPtgType = "CR";
							String rTxnType = "OP";
							String rTxnDesc = "Re-Open Closed Account";
							Double rTxnAmount = 0.0;
							Date rTxnDate = eliteDateService.getDBTime();
							String rSubAcType = "REG";
							String rSlipPrFlag = "N";
							String rModlName = "OTDOPCTA";
							Integer rGlSqnc = null;
							try {
								/*
								 * otdcrvoiRepository.trustInsertIntoOffenderTrans(rTxnNum, rTxnEntrySeq,
								 * rCsldId, rOffId, rOffBookId, rTxnPtgType, rTxnType, rTxnDesc, rTxnAmount,
								 * rTxnDate, rSubAcType, "", "", "", "", "", "", rSlipPrFlag, "");
								 */
								OffenderTransactions obj = new OffenderTransactions();
								obj.setTxnId(rTxnNum.intValue());
								obj.setTxnEntrySeq(rTxnEntrySeq);
								obj.setCaseloadId(rCsldId);
								obj.setOffenderId(rOffId);
								obj.setOffenderBookId(rOffBookId);
								obj.setTxnPostingType(rTxnPtgType);
								obj.setTxnType(rTxnType);
								obj.setTxnDescription(rTxnDesc);
								obj.setTxnEntryAmount(rTxnAmount);
								obj.setTxnEntryDate(rTxnDate);
								obj.setSubAccountType(rSubAcType);
								obj.setDeductionFlag("");
								obj.setPreWithholdAmount(null);
								obj.setDeductionType("");
								obj.setPayeeCorporateId(null);
								obj.setPayeePersonId(null);
								obj.setInfoNumber("");
								obj.setSlipPrintedFlag(rSlipPrFlag);	
								obj.setCreateUserId(param.getCreateUserId());
								//Procedure call
								trustService.insertIntoOffenderTrans(obj);
								rGlSqnc = 0;
								/*
								 * otdcrvoiRepository.processGlTransNew(rCsldId, rTxnType, null, rTxnAmount,
								 * rTxnNum, rTxnDate, rTxnDesc, rTxnEntrySeq, rModlName, rOffId, rOffBookId,
								 * null, rSubAcType, "", "", "", rGlSqnc, null);
								 */
								//Procedure call
								trustService.processGlTransNew(rCsldId, rTxnType, null, rTxnAmount, rTxnNum.intValue(), rTxnDate,
										rTxnDesc, rTxnEntrySeq, rModlName, rOffId.intValue(), rOffBookId, null, rSubAcType, "", "",
										"", rGlSqnc, null, param.getCreateUserId());
							} catch (Exception e) {
								logger.error("REVERSE_CHEQUE_VOID. When reopening trust account.", e);
								throw new RuntimeException("otdcrvoi.revsdchdwhnopntrstac");
							}

						}
					}
				}

			}
			try {
				BigDecimal drSum = BigDecimal.ZERO;
				BigDecimal crSum = BigDecimal.ZERO;
				Integer lNewTxnEntrySeq = 0;
				Integer lNewGlEntrySeq = 0;
				Integer lCurrTxnEntrySeq = 0;
				Integer lOffTxnEntrySeq = 0;

				newTrans = otdcrvoiRepository.genTrustTrans("TXN_ID");

				List<OffenderTransactions> cOness = otdcrvoiRepository.getCOneOffenderDetail(temp1, temp2, temp3);
				if (cOness != null) {
					for (final OffenderTransactions cOne : cOness) {
						nmbrSqnc = cOne.getTxnEntrySeq();
						glSqnc = cOne.getGlEntrySeq();
						inst = cOne.getCaseloadId();
						acnt = cOne.getAccountCode().intValue();
						amnt = cOne.getTxnEntryAmount();
						typeTrans = cOne.getTxnType();
						transPosting = cOne.getTxnPostUsage();
						accPosting = cOne.getTxnPostingType();
						offender = cOne.getOffenderId();
						ofndrBk = cOne.getOffenderBookId();
						if (lCurrTxnEntrySeq != nmbrSqnc) {
							if ("OT".equals(typeTrans)) {
								lNewTxnEntrySeq = lNewTxnEntrySeq + 2;
							} else {
								lNewTxnEntrySeq = lNewTxnEntrySeq + 1;
							}
							lNewGlEntrySeq = 1;
						} else {
							lNewGlEntrySeq = lNewGlEntrySeq + 1;
						}
						lCurrTxnEntrySeq = nmbrSqnc;
						recordNumber = recordNumber + 1;
						if ("CR".equals(transPosting)) {
							crSum = crSum.add(BigDecimal.valueOf(amnt));
							newTransPstng = "DR";
						} else {
							drSum = drSum.add(BigDecimal.valueOf(amnt));
							newTransPstng = "CR";
						}
						/*
						 * Update the Existing G/L Transaction as been Reversed
						 */
						try {
							otdcrvoiRepository.updateGlTransactionReversedFlag(temp1, nmbrSqnc, glSqnc);
						} catch (Exception e) {
							logger.error("Other error in reversiong GL transactions", e);
							throw new RuntimeException("otdcrvoi.otrnregltrns");
						}

						// ADD A GL RECORD FOR THE REV OF GL
						if ("VOID".equals(param.getChequeStatus())) {
							try {
								/*
								 * otdcrvoiRepository.insertGlTransNew(newTransPstng, acnt, accPosting, inst,
								 * typeTrans, amnt, newTrans, newDate, "VOID CHECK#" + temp4, lNewTxnEntrySeq,
								 * lNewGlEntrySeq, offender, ofndrBk, "", "", "", "", "", "", "", "", "");
								 */
								GlTransactions obj = new GlTransactions();
								obj.setDspTxnPostingType(newTransPstng);
								obj.setAccountCode(BigDecimal.valueOf(acnt));
								obj.setAcntPosting(accPosting);
								obj.setCaseloadId(inst);
								obj.setTxnType(typeTrans);
								obj.setTxnEntryAmount(BigDecimal.valueOf(amnt));
								obj.setTxnId(newTrans.longValue());
								obj.setTxnEntryDate(newDate);
								obj.setTxnEntryDesc("VOID CHECK#" + temp4);
								obj.setTxnEntrySeq(lNewTxnEntrySeq.longValue());
								obj.setGlEntrySeq(lNewGlEntrySeq.longValue());
								obj.setOffenderId(BigDecimal.valueOf(offender));
								obj.setOffenderBookId(BigDecimal.valueOf(ofndrBk));
								obj.setInfoNumber("");
								obj.setPayeePersonId(null);
								obj.setPayeeCorporateId(null);
								obj.setPayeeNameText("");
								obj.setReversedTxnId(null);
								obj.setReversedTxnEntrySeq(null);
								obj.setReversedGlEntrySeq(null);
								obj.setTxnReferenceNumber("");
								obj.setOffDeductionId(null);
								//Procedure call							
								trustService.insertGlTransNew(obj);
							} catch (Exception e) {
								logger.error("Other Error in TRUST.INSERT_GL_TRANS_NEW", e);
								throw new RuntimeException("otdcrvoi.otrintrstinsrgl");
							}
							// SET THE REVERSED FLAG OF THE NEW REVERSAL RECORD
							// TO Y
							otdcrvoiRepository.updateGlTransactionReversedFlagAndValues(vTxnReversedFlag, temp1,
									nmbrSqnc, glSqnc, newTrans, lNewTxnEntrySeq, lNewGlEntrySeq);
						} else {
							try {
								/*
								 * otdcrvoiRepository.insertGlTransNew(newTransPstng, acnt, accPosting, inst,
								 * typeTrans, amnt, newTrans, newDate, "VOID CHECK#" + temp4, lNewTxnEntrySeq,
								 * lNewGlEntrySeq, offender, ofndrBk, "", "", "", "", "", "", "", "", "");
								 */
								GlTransactions obj = new GlTransactions();
								obj.setDspTxnPostingType(newTransPstng);
								obj.setAccountCode(BigDecimal.valueOf(acnt));
								obj.setAcntPosting(accPosting);
								obj.setCaseloadId(inst);
								obj.setTxnType(typeTrans);
								obj.setTxnEntryAmount(BigDecimal.valueOf(amnt));
								obj.setTxnId(newTrans.longValue());
								obj.setTxnEntryDate(newDate);
								obj.setTxnEntryDesc("VOID CHECK#" + temp4);
								obj.setTxnEntrySeq(lNewTxnEntrySeq.longValue());
								obj.setGlEntrySeq(lNewGlEntrySeq.longValue());
								obj.setOffenderId(BigDecimal.valueOf(offender));
								obj.setOffenderBookId(BigDecimal.valueOf(ofndrBk));
								obj.setInfoNumber("");
								obj.setPayeePersonId(null);
								obj.setPayeeCorporateId(null);
								obj.setPayeeNameText("");
								obj.setReversedTxnId(null);
								obj.setReversedTxnEntrySeq(null);
								obj.setReversedGlEntrySeq(null);
								obj.setTxnReferenceNumber("");
								obj.setOffDeductionId(null); 
								//Procedure call							
								trustService.insertGlTransNew(obj);
							} catch (Exception e) {
								logger.error("Other Error in TRUST.INSERT_GL_TRANS_NEW", e);
								throw new RuntimeException("otdcrvoi.otrintrstinsrgl");
							}
						}

					}
				}
				/*
				 * Reversing Offender_Transactions records with it's own loop
				 * due to the structure of reversing OT (sub account transfer)
				 * transactions can't be incorporated within the GL Transactions
				 * loop
				 */
				try {
					List<OffenderTransactions> cOffTxns = otdcrvoiRepository.cOffTxnOffenderDetail(temp1, temp2, temp3);
					if (cOffTxns != null) {
						for (final OffenderTransactions offTxn : cOffTxns) {
							if ("CR".equals(offTxn.getTxnPostingType())) {
								newTransPstng = "DR";
							} else {
								newTransPstng = "CR";
							}
							otdcrvoiRepository.insertIntoOffenderTransactionsViaQuery(newTrans, lOffTxnEntrySeq,
									newTransPstng, temp1, offTxn.getTxnEntrySeq());
							try {
								otdcrvoiRepository.otdcrvoiUpdateOffenderTransactionTxnAdjecenmentFlag(temp1,
										offTxn.getTxnEntrySeq());
								/*
								 * otdcrvoiRepository.updateOffenderBalance(offTxn.getCaseloadId(),
								 * offTxn.getOffenderId(), newTransPstng, newDate, newTrans,
								 * offTxn.getTxnType(), offTxn.getTxnEntryAmount(), offTxn.getSubAccountType(),
								 * "N");
								 */
								OffenderTransactions obj = new OffenderTransactions();
								obj.setCaseloadId(offTxn.getCaseloadId());
								obj.setOffenderId(offTxn.getOffenderId());
								obj.setTxnPostingType(newTransPstng);
								obj.setTxnEntryDate(newDate);
								obj.setTxnId(newTrans.intValue());
								obj.setTxnType(offTxn.getTxnType());
								obj.setTxnEntryAmount(offTxn.getTxnEntryAmount());
								if("CR".equals(newTransPstng)) {
									obj.setToSubAccountType(offTxn.getSubAccountType());
								} else {
									obj.setSubAccountType(offTxn.getSubAccountType());
								}
								//Procedure call
								trustService.updateOffenderBalance(obj, param.getCreateUserId());
							} catch (Exception e) {
								logger.error("Error: When calling TRUST.UPDATE_OFFENDER_BALANCE.", e);
								throw new RuntimeException("otdcrvoi.errwhnclngupdoffbal");
							}
							try {
								/*
								 * otdcrvoiRepository.deductionGetAcAndSetIndDate(offTxn.getOffenderId(),
								 * offTxn.getCaseloadId());
								 */
								//Procedure call
								deductionsService.getAcAndSetIndDate(offTxn.getOffenderId(),offTxn.getCaseloadId(), param.getCreateUserId());
							} catch (Exception e) {
								logger.error("Error in reverse_cheque_void.Deductions.get_ac_and_set_ind_date", e);
								throw new RuntimeException("otdcrvoi.revecheerrorvoid");
							}
						}
					}
				} catch (Exception e) {
					logger.error("Error: when reversing into Offender Transactions.", e);
					throw new RuntimeException("otdcrvoi.errwhnreverinofftrns");
				}

				// VALIDATE CR TOTAL MATCHES DR TOTAL
				if (!crSum.equals(drSum)) {
					throw new RuntimeException("otdcrvoi.revcrtotdsntmtdrtot");
				}
				try {
					reverseBeneficiaries(temp1, param.getCaseLoadId());
				} catch (Exception e) {
					logger.error("Error: when calling REVERSE_BENEFICIARIES.", e);
					throw new RuntimeException("otdcrvoi.errwnclrevebebenefe");
				}
				// VALIDATE AT LEAST 2 GL TXN IS REV (1 CR, 1 DR)
				if (recordNumber >= 2) {
					return newTrans.toString();
				} else {
					if (recordNumber < 2) {
						throw new RuntimeException("otdcrvoi.reventscplcntsprt");
					}
				}

				if (crSum.equals(BigDecimal.ZERO) || drSum.equals(BigDecimal.ZERO)) {
					throw new RuntimeException("Trying to reverse $0.00 record");
				}

			} catch (Exception e) {

				throw new RuntimeException(e.getMessage());
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}

		return null;
	}

	private void reverseBeneficiaries(BigDecimal pChequeTxnId, String caseloadId) {
		Long vTxnId = null;
		BigDecimal vPerId = null;
		BigDecimal vCorpId = null;
		BigDecimal vDedId = null;
		try {
			List<BeneficiaryTransactions> cChequeBeneficiaries = otdcrvoiRepository.cChequeBeneficiaries(pChequeTxnId);
			if (cChequeBeneficiaries != null) {
				for (final BeneficiaryTransactions cChequeBeneficiarie : cChequeBeneficiaries) {
					vTxnId = cChequeBeneficiarie.getTxnId();
					vPerId = cChequeBeneficiarie.getPersonId();
					vCorpId = cChequeBeneficiarie.getCorporateId();
					vDedId = cChequeBeneficiarie.getOffenderDeductionId();
					otdcrvoiRepository.updateBeneficiaryTransactions(vTxnId, vPerId, vCorpId, vDedId);

					try {
						otdcrvoiRepository.cGetAccountCode(vTxnId, vPerId, vCorpId, vDedId);
					} catch (Exception e) {
						logger.error("Error: REVERSE_BENEFICIARIES.", e);
						throw new RuntimeException("otdcrvoi.errreverbene");
					}

				}
			}
		} catch (Exception e) {
			logger.error("Error: REVERSE_BENEFICIARIES (outter).", e);
			throw new RuntimeException("otdcrvoi.errrevebeneotr");
		}

	}

	private Date trunc(final Date date) {
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

}
