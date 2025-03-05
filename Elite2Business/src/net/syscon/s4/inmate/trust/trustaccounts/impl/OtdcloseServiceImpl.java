package net.syscon.s4.inmate.trust.trustaccounts.impl;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.OffenderTransactionsCommitBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.SystemProfilesCommitBean;
import net.syscon.s4.core.EliteDateService;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.im.beans.BankChequeData;
import net.syscon.s4.im.beans.ChkFreezeDisbursements;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.inmate.beans.OffenderSubAccounts;
import net.syscon.s4.inmate.beans.OffenderSubAccountsCommitBean;
import net.syscon.s4.inmate.trust.trustaccounts.OtdcloseRepository;
import net.syscon.s4.inmate.trust.trustaccounts.OtdcloseService;
import net.syscon.s4.pkgs.trust.TrustService;

/**
 * Class OtdcloseServiceImpl
 */
@Service
public class OtdcloseServiceImpl extends BaseBusiness implements OtdcloseService {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OtdcloseServiceImpl.class.getName());

	@Autowired
	private OtdcloseRepository otdcloseRepository;

	@Autowired
	private EliteDateService eliteDateService;
	
	@Autowired
	private TrustService trustService;

	/**
	 * Creates new OtdcloseServiceImpl class Object
	 */
	public OtdcloseServiceImpl() {
		// OtdcloseServiceImpl
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public ReferenceCodes offTxnWhenNewBlockInstance(final ReferenceCodes paramBean) {
		final ReferenceCodes returnObj = otdcloseRepository.offTxnWhenNewBlockInstance(paramBean);
		return returnObj;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<ReferenceCodes> cgfkchkOffTxnOffTxnRef(final ReferenceCodes paramBean) {
		return otdcloseRepository.cgfkchkOffTxnOffTxnRef(paramBean);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @
	 */
	public List<OffenderSubAccounts> offSubaExecuteQuery(final OffenderSubAccounts searchRecord) {
		return otdcloseRepository.offSubaExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFF_SUBA
	 *
	 * @
	 */
	@Transactional
	public Integer offSubaCommit(final OffenderSubAccountsCommitBean commitBean) {
		logger.info(logger.getName().getClass()+" offSubaCommit method start");
		int liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			logger.info("Calling otdcloseRepository.offSubaInsertOffenderSubAccounts ");
			liReturn = otdcloseRepository.offSubaInsertOffenderSubAccounts(commitBean.getInsertList());
			logger.info("offSubaInsertOffenderSubAccounts response " + liReturn);
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			logger.info("Calling otdcloseRepository.offSubaUpdateOffenderSubAccounts ");
			liReturn = otdcloseRepository.offSubaUpdateOffenderSubAccounts(commitBean.getUpdateList());
			logger.info("offSubaUpdateOffenderSubAccounts response " + liReturn);
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			logger.info("Calling otdcloseRepository.offSubaDeleteOffenderSubAccounts ");
			commitBean.getDeleteList().forEach(bo -> {bo.setModifyUserId(commitBean.getCreateUserId());});
			liReturn = otdcloseRepository.offSubaDeleteOffenderSubAccounts(commitBean.getDeleteList());
			logger.info("offSubaDeleteOffenderSubAccounts response " + liReturn);
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
	public List<OffenderTransactions> offTxnExecuteQuery(final OffenderTransactions searchRecord) {
		return otdcloseRepository.offTxnExecuteQuery(searchRecord);

	}

	public List<AccountCodes> accountNameForValidation() {
		return otdcloseRepository.accountNameForValidation();
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFF_TXN
	 *
	 * @
	 */
	@Transactional
	public OffenderTransactions offTxnCommit(final OffenderTransactionsCommitBean commitBean) {
		logger.info(logger.getName().getClass()+" offTxnCommit method start");
		int liReturn = 0;
		OffenderTransactions offTxnsRetunData = new OffenderTransactions();
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			Long pAccountCode = null;
			Double pBalance = 0.0;
			String PSuAaccountType = null;
			BigDecimal pTxnEntrySeq = new BigDecimal(0);
			BigDecimal pGlTxnEntrySeq = new BigDecimal(0);
			BigDecimal pGlSeq = new BigDecimal(0);
			Date transDate = trunc(eliteDateService.getDBTime());
			String lvTxnEntryDesc = null;
			String lvTxnType = null;
			Integer pTxnNumber = null;
			BigDecimal pTxnEntrySequence = new BigDecimal(0);
			String vTxnType = null;
			Integer txnIdOne = null;
			Integer txnIdTwo = null;
			String payeeNameText = null;
			String personsC = null;
			String corporateC = null;
			Double pGovt = null;
			Double txnEntryAmount = 0.0;
			Double nbtTxnEntryAmount2 = 0.0;
			try {
				for (final OffenderTransactions objOffTransactions : commitBean.getInsertList()) {
					objOffTransactions.setCreateUserId(commitBean.getCreateUserId());
					logger.info("Calling otdcloseRepository.getRegBalOne ");
					BigDecimal regValReturn = getRegBalOne(objOffTransactions.getOffenderId(),
							objOffTransactions.getCaseloadId());
					logger.info("getRegBalOne response " + regValReturn);
					if (regValReturn.compareTo(BigDecimal.ONE) == 1) {
						offTxnsRetunData.setOffenderBookId(2L);
						return offTxnsRetunData;
					}
					if ("Y".equals(objOffTransactions.getNbtModifyUserId())) {
						if ("IND".equals(objOffTransactions.getPayeeCode())
								|| "COR".equals(objOffTransactions.getPayeeCode())
								|| "OTH".equals(objOffTransactions.getPayeeCode())
								|| "PER".equals(objOffTransactions.getPayeeCode())) {
							if (objOffTransactions.getTxnEntryAmount() == null
									&& objOffTransactions.getNbtTxnEntryAmount2() == null) {
								offTxnsRetunData.setOffenderBookId(29L);
								return offTxnsRetunData;
							}
							if (objOffTransactions.getTxnEntryAmount() == null) {
								txnEntryAmount = 0.0;
							} else {
								txnEntryAmount = objOffTransactions.getTxnEntryAmount();
							}
							if (objOffTransactions.getNbtTxnEntryAmount2() == null) {
								nbtTxnEntryAmount2 = 0.0;
							} else {
								nbtTxnEntryAmount2 = objOffTransactions.getNbtTxnEntryAmount2();
							}
							Double val;
							if ((txnEntryAmount + nbtTxnEntryAmount2) != (val = objOffTransactions.getCurrentBalance() == null ? 0 : objOffTransactions.getCurrentBalance())) {
								offTxnsRetunData.setOffenderBookId(30L);
								return offTxnsRetunData;
							} else {
								if (objOffTransactions.getTxnEntryAmount() != null) {
									logger.info("Calling otdcloseRepository.pCashtxnOperation ");
									String pCach = otdcloseRepository.pCashtxnOperation(commitBean.getCreateUserId());
									logger.info("get pCashtxnOperation response " + pCach);
									if (pCach == null) {
										offTxnsRetunData.setOffenderBookId(3L);
										return offTxnsRetunData;
									}
								}
								if (objOffTransactions.getNbtTxnEntryAmount2() != null) {
									logger.info("Calling otdcloseRepository.pChecktxnOperation ");
									String pCheck = otdcloseRepository.pChecktxnOperation(commitBean.getCreateUserId());
									logger.info("get pChecktxnOperation response " + pCheck);
									if (pCheck == null) {
										offTxnsRetunData.setOffenderBookId(4L);
										return offTxnsRetunData;
									}
								}
							}
						}
					}
					logger.info("Calling otdcloseRepository.accountClosedFlagValidation for account closed verification ");
					String accountClosedValidate = otdcloseRepository.accountClosedFlagValidation(
							objOffTransactions.getOffenderId(), objOffTransactions.getCaseloadId());
					logger.info("get accountClosedFlagValidation response " + accountClosedValidate);
					if ("Y".equals(accountClosedValidate)) {
						offTxnsRetunData.setOffenderBookId(5L);
						return offTxnsRetunData;
					}
					if ("Y".equals(objOffTransactions.getNbtModifyUserId())) {
						if ("IND".equals(objOffTransactions.getPayeeCode())
								|| "COR".equals(objOffTransactions.getPayeeCode())
								|| "OTH".equals(objOffTransactions.getPayeeCode())
								|| "PER".equals(objOffTransactions.getPayeeCode())) {
							if (objOffTransactions.getTxnEntryAmount() == null
									|| objOffTransactions.getTxnEntryAmount() == 0) {
								txnEntryAmount = 0.0;
							} else {
								txnEntryAmount = objOffTransactions.getTxnEntryAmount();
							}
							if (objOffTransactions.getNbtTxnEntryAmount2() == null
									|| objOffTransactions.getNbtTxnEntryAmount2() == 0) {
								nbtTxnEntryAmount2 = 0.0;
							} else {
								nbtTxnEntryAmount2 = objOffTransactions.getNbtTxnEntryAmount2();
							}
							if ((txnEntryAmount + nbtTxnEntryAmount2) != objOffTransactions.getCurrentBalance()) {
								offTxnsRetunData.setOffenderBookId(6L);
								return offTxnsRetunData;
							}
						}
					} else {
						offTxnsRetunData.setOffenderBookId(7L);
						return offTxnsRetunData;
					}
					logger.info("Calling otdcloseRepository.offenderForeignCurrenciesCount  ");
					Integer foreignCurrenciesCount = otdcloseRepository.offenderForeignCurrenciesCount(
							objOffTransactions.getOffenderId(), objOffTransactions.getCaseloadId());
					logger.info("get offenderForeignCurrenciesCount response " + foreignCurrenciesCount);
					
					if (foreignCurrenciesCount == 0 || foreignCurrenciesCount == 1 || foreignCurrenciesCount == null) {
						foreignCurrenciesCount = null;
					} else {
						offTxnsRetunData.setOffenderBookId(8L);
						return offTxnsRetunData;
					}
					logger.info("Calling otdcloseRepository.holdDataCount  ");
					Double holdData = otdcloseRepository.holdDataCount(objOffTransactions.getOffenderId(),
							objOffTransactions.getCaseloadId());
					logger.info("get holdDataCount response " + holdData);
					if (holdData > 0) {
						offTxnsRetunData.setOffenderBookId(9L);
						return offTxnsRetunData;
					}
					logger.info("Calling otdcloseRepository.balanceSubAccountCode  ");
					List<OffenderSubAccounts> offSubAcnts = otdcloseRepository.balanceSubAccountCode(
							objOffTransactions.getOffenderId(), objOffTransactions.getCaseloadId());
					logger.info("get balanceSubAccountCode response " );
					Integer txnIdNextVal = otdcloseRepository.txnIdNextValData();
					if (offSubAcnts.size() > 0) {
						for (OffenderSubAccounts offenderSubAccounts : offSubAcnts) {
							pAccountCode = offenderSubAccounts.getTrustAccountCode();
							pBalance = offenderSubAccounts.getBalance();
							PSuAaccountType = offenderSubAccounts.getSubAccountType();
							if (pBalance > 0) {
								pTxnEntrySeq = pTxnEntrySeq.add(BigDecimal.ONE);
								pGlTxnEntrySeq = pTxnEntrySeq;
								try {
									logger.info("Calling otdcloseRepository.offTxnInsertOffenderTransactions  ");
									liReturn = otdcloseRepository.offTxnInsertOffenderTransactions(txnIdNextVal,
											pTxnEntrySeq, objOffTransactions.getCaseloadId(),
											objOffTransactions.getOffenderId(), objOffTransactions.getOffenderBookId(),
											"DR", "OT", "SUB ACCOUNT TRANSFER", pBalance, PSuAaccountType, objOffTransactions.getCreateUserId(), "N",
											"N", "N", objOffTransactions.getPayeeCode(),
											objOffTransactions.getPayeeCorporateId(),
											objOffTransactions.getPayeePersonId(), objOffTransactions.getCreateUserId());
									logger.info("get offTxnInsertOffenderTransactions response " +liReturn);
									if (liReturn == 0) {
										offTxnsRetunData.setOffenderBookId(10L);
										return offTxnsRetunData;
									}
									/*
									 * otdcloseRepository.updateOffenderBalance(objOffTransactions.getCaseloadId(),
									 * objOffTransactions.getOffenderId(), "DR", transDate, txnIdNextVal, "OT",
									 * pBalance, PSuAaccountType);
									 */
									OffenderTransactions obj =  new OffenderTransactions();
									obj.setCaseloadId(objOffTransactions.getCaseloadId());
									obj.setOffenderId(objOffTransactions.getOffenderId());
									obj.setTxnPostingType("DR");
									obj.setTxnEntryDate(transDate);
									obj.setTxnId(txnIdNextVal);
									obj.setTxnType("OT");
									obj.setTxnEntryAmount(pBalance);
									obj.setSubAccountType(PSuAaccountType);
									//Procedure call
									logger.info("Calling trustService.updateOffenderBalance ");
									trustService.updateOffenderBalance(obj, commitBean.getCreateUserId());
								} catch (Exception e) {
									offTxnsRetunData.setOffenderBookId(10L);
									return offTxnsRetunData;
								}
								pTxnEntrySeq = pTxnEntrySeq.add(BigDecimal.ONE);
								try {
									logger.info("Calling otdcloseRepository.offTxnInsertOffenderTransactions  ");
									liReturn = otdcloseRepository.offTxnInsertOffenderTransactions(txnIdNextVal,
											pTxnEntrySeq, objOffTransactions.getCaseloadId(),
											objOffTransactions.getOffenderId(), objOffTransactions.getOffenderBookId(),
											"CR", "OT", "SUB ACCOUNT TRANSFER", pBalance, "REG", objOffTransactions.getCreateUserId(), "N", "N", "N",
											objOffTransactions.getPayeeCode(), objOffTransactions.getPayeeCorporateId(),
											objOffTransactions.getPayeePersonId(), objOffTransactions.getCreateUserId());
									logger.info("get offTxnInsertOffenderTransactions response " +liReturn);
									if (liReturn == 0) {
										offTxnsRetunData.setOffenderBookId(31L);
										return offTxnsRetunData;
									}
									/*
									 * otdcloseRepository.updateOffenderBalance(objOffTransactions.getCaseloadId(),
									 * objOffTransactions.getOffenderId(), "CR", transDate, txnIdNextVal, "OT",
									 * pBalance, "REG");
									 */
									OffenderTransactions obj =  new OffenderTransactions();
									obj.setCaseloadId(objOffTransactions.getCaseloadId());
									obj.setOffenderId(objOffTransactions.getOffenderId());
									obj.setTxnPostingType("CR");
									obj.setTxnEntryDate(transDate);
									obj.setTxnId(txnIdNextVal);
									obj.setTxnType("OT");
									obj.setTxnEntryAmount(pBalance);
									obj.setToSubAccountType("REG");
									//Procedure call
									logger.info("Calling trustService.updateOffenderBalance ");
									trustService.updateOffenderBalance(obj, commitBean.getCreateUserId());
								} catch (Exception e) {
									offTxnsRetunData.setOffenderBookId(11L);
									return offTxnsRetunData;
								}
								/*
								 * liReturn =
								 * otdcloseRepository.processGlTransNew(objOffTransactions.getCaseloadId(),
								 * "OT", null, pBalance, txnIdNextVal, transDate, "SUB ACCOUNT TRANSFER",
								 * pGlTxnEntrySeq, objOffTransactions.getOffenderId(),
								 * objOffTransactions.getOffenderBookId(), PSuAaccountType, "REG", null, null,
								 * null, pGlSeq);
								 */
								//Procedure call
								logger.info("Calling trustService.processGlTransNew  ");
								liReturn = trustService.processGlTransNew(objOffTransactions.getCaseloadId(), "OT", 
										null, pBalance, txnIdNextVal, transDate, "SUB ACCOUNT TRANSFER", pGlTxnEntrySeq.intValue(), 
										"OTDCLOSE", objOffTransactions.getOffenderId().intValue(), 
										 objOffTransactions.getOffenderBookId(), PSuAaccountType, "REG", null, 
										null, null, pGlSeq.intValue(), null, objOffTransactions.getCreateUserId());
								logger.info("get trustService.processGlTransNew response " +liReturn);
								if (liReturn == 0) {
									offTxnsRetunData.setOffenderBookId(12L);
									return offTxnsRetunData;
								} 
								logger.info("Calling ZeroAmount  ");			
								Integer returnZeroAmount = ZeroAmount(txnIdNextVal, pTxnEntrySeq,
										objOffTransactions.getCaseloadId(), objOffTransactions.getOffenderId(),
										PSuAaccountType, objOffTransactions.getCreateUserId());
								logger.info("get Calling ZeroAmount response " +returnZeroAmount);
								if (returnZeroAmount == 2) {
									offTxnsRetunData.setOffenderBookId(13L);
									return offTxnsRetunData;
								}
							}
						}
					}
					logger.info("Calling otdcloseRepository.lastNameFirstName  ");	
					String lastNameFirstName = otdcloseRepository.lastNameFirstName(objOffTransactions.getOffenderId(), objOffTransactions.getCreateUserId());
					logger.info("get otdcloseRepository.lastNameFirstNam response " +lastNameFirstName);
					lvTxnEntryDesc = "Transfer to Pro Per Payable";
					List<OffenderSubAccounts> offSubAcntsProp = otdcloseRepository.balanceSubAccountCodeProp(
							objOffTransactions.getOffenderId(), objOffTransactions.getCaseloadId(), objOffTransactions.getCreateUserId());
					if (offSubAcntsProp.size() > 0) {
						for (OffenderSubAccounts offenderSubAccounts : offSubAcnts) {
							pAccountCode = offenderSubAccounts.getTrustAccountCode();
							pBalance = offenderSubAccounts.getBalance();
							PSuAaccountType = offenderSubAccounts.getModifyUserId();
							if (pBalance > 0) {
								lvTxnType = "PROP";
								pTxnEntrySeq = pTxnEntrySeq.add(BigDecimal.ONE);
//								liReturn = otdcloseRepository.insertIntoOffenderTransaction(txnIdNextVal, pTxnEntrySeq,
//										objOffTransactions.getCaseloadId(), objOffTransactions.getOffenderId(),
//										objOffTransactions.getOffenderBookId(), lvTxnType, lvTxnEntryDesc, pBalance,
//										transDate, PSuAaccountType);
								logger.info("Calling trustService.updateOffenderBalance ");
								trustService.insrtIntoOffenderTrans(BigDecimal.valueOf(txnIdNextVal), pTxnEntrySeq.longValue(), objOffTransactions.getCaseloadId(), 
										BigDecimal.valueOf(objOffTransactions.getOffenderId()),BigDecimal.valueOf(objOffTransactions.getOffenderBookId()), "DR", lvTxnType,
										lvTxnEntryDesc, pBalance,transDate ,PSuAaccountType ,null ,null ,null ,null 
										,null ,null ,null , null,"N" , "N", objOffTransactions.getCreateUserId());
								logger.info("Calling otdcloseRepository.updateOffenderTransactionsTransfer ");
								liReturn = otdcloseRepository.updateOffenderTransactionsTransfer(lastNameFirstName,
										txnIdNextVal, pTxnEntrySeq);
								logger.info("get otdcloseRepository.updateOffenderTransactionsTransfer response " +liReturn);
								
								if (liReturn == 0) {
									offTxnsRetunData.setOffenderBookId(14L);
									return offTxnsRetunData;
								}
								/*
								 * liReturn =
								 * otdcloseRepository.processGlTransNew(objOffTransactions.getCaseloadId(),
								 * lvTxnType, null, pBalance, txnIdNextVal, transDate, lvTxnEntryDesc,
								 * pTxnEntrySeq, objOffTransactions.getOffenderId(),
								 * objOffTransactions.getOffenderBookId(), PSuAaccountType, null, null, null,
								 * null, pGlSeq);
								 */
								//Procedure call
								logger.info("Calling trustService.processGlTransNew  ");	
								liReturn = trustService.processGlTransNew(objOffTransactions.getCaseloadId(), vTxnType, 
										null, pBalance, txnIdNextVal, transDate, 
										lvTxnEntryDesc, pTxnEntrySeq.intValue(), "OTDCLOSE", 
										objOffTransactions.getOffenderId().intValue(), objOffTransactions.getOffenderBookId().intValue(), 
										PSuAaccountType, null, null, null, null, pGlSeq.intValue(), null, objOffTransactions.getCreateUserId());
								logger.info("get trustService.processGlTransNew response " +liReturn);
								if (liReturn == 0) {
									offTxnsRetunData.setOffenderBookId(15L);
									return offTxnsRetunData;
								}
								logger.info("Calling ZeroAmount  ");	
								Integer returnZeroAmountData = ZeroAmount(txnIdNextVal, pTxnEntrySeq,
										objOffTransactions.getCaseloadId(), objOffTransactions.getOffenderId(),
										PSuAaccountType, objOffTransactions.getCreateUserId());
								logger.info("get ZeroAmount response  " +returnZeroAmountData);
								if (returnZeroAmountData == 2) {
									offTxnsRetunData.setOffenderBookId(16L);
									return offTxnsRetunData;
								}
							}
						}
					}
					pTxnNumber = txnIdNextVal;
					pTxnEntrySequence = pTxnEntrySeq.add(BigDecimal.ONE);
					if (objOffTransactions.getCurrentBalance() >= 0) {
						if (objOffTransactions.getPayeeCode() == null) {
							offTxnsRetunData.setOffenderBookId(17L);
							return offTxnsRetunData;
						} else {
							if ("IND".equals(objOffTransactions.getPayeeCode())
									|| "OTH".equals(objOffTransactions.getPayeeCode())
									|| "COR".equals(objOffTransactions.getPayeeCode())
									|| "PER".equals(objOffTransactions.getPayeeCode())) {
								if (objOffTransactions.getTxnEntryAmount() != null) {
									offTxnsRetunData.setTxnId(pTxnNumber);
									if ("INST".equals(objOffTransactions.getCaseloadType())) {
										vTxnType = "CL";
									} else {
										vTxnType = "CLC";
									}
									try {
										logger.info("Calling otdcloseRepository.offTxnInsertOffenderTransactions for CLOSE ACCOUNT - CASH  ");	
										liReturn = otdcloseRepository.offTxnInsertOffenderTransactions(pTxnNumber,
												pTxnEntrySequence, objOffTransactions.getCaseloadId(),
												objOffTransactions.getOffenderId(),
												objOffTransactions.getOffenderBookId(), "DR", vTxnType,
												"CLOSE ACCOUNT - CASH", objOffTransactions.getTxnEntryAmount(), "REG",
												objOffTransactions.getCreateUserId(), "N", "N", "N", objOffTransactions.getPayeeCode(),
												objOffTransactions.getPayeeCorporateId(),
												objOffTransactions.getPayeePersonId(), objOffTransactions.getCreateUserId());
										logger.info("Response from otdcloseRepository.offTxnInsertOffenderTransactions response for CLOSE ACCOUNT - CASH  "+liReturn);	
										if (liReturn == 0) {
											offTxnsRetunData.setOffenderBookId(18L);
											return offTxnsRetunData;
										}
										/*
										 * otdcloseRepository.updateOffenderBalance(objOffTransactions.getCaseloadId(),
										 * objOffTransactions.getOffenderId(), "DR", transDate, pTxnNumber, vTxnType,
										 * objOffTransactions.getTxnEntryAmount(), "REG");
										 */
										OffenderTransactions obj =  new OffenderTransactions();
										obj.setCaseloadId(objOffTransactions.getCaseloadId());
										obj.setOffenderId(objOffTransactions.getOffenderId());
										obj.setTxnPostingType("DR");
										obj.setTxnEntryDate(transDate);
										obj.setTxnId(pTxnNumber);
										obj.setTxnType(vTxnType);
										obj.setTxnEntryAmount(objOffTransactions.getTxnEntryAmount());
										obj.setSubAccountType("REG");
										//Procedure call
										logger.info("Calling trustService.updateOffenderBalance ");
										trustService.updateOffenderBalance(obj, commitBean.getCreateUserId());
									} catch (Exception e) {
										offTxnsRetunData.setOffenderBookId(18L);
										return offTxnsRetunData;
									}
									/*liReturn = otdcloseRepository.processGlTransNew(objOffTransactions.getCaseloadId(),
											vTxnType, "CASH", objOffTransactions.getTxnEntryAmount(), pTxnNumber,
											transDate, "CLOSE ACCOUNT - CASH", pTxnEntrySequence,
											objOffTransactions.getOffenderId(), objOffTransactions.getOffenderBookId(),
											"REG", null, null, null, null, pGlSeq);*/
									//Procedure call
									logger.info("Calling trustService.processGlTransNew for CLOSE ACCOUNT - CASH  ");	
									liReturn = trustService.processGlTransNew(objOffTransactions.getCaseloadId(), vTxnType, 
											"CASH", objOffTransactions.getTxnEntryAmount(), pTxnNumber, transDate, 
											"CLOSE ACCOUNT - CASH", pTxnEntrySequence.intValue(), "OTDCLOSE", 
											objOffTransactions.getOffenderId().intValue(), objOffTransactions.getOffenderBookId().intValue(), 
											"REG", null, null, null, null, pGlSeq.intValue(), null, objOffTransactions.getCreateUserId());
									logger.info("Response from trustService.processGlTransNew response for CLOSE ACCOUNT - CASH  "+liReturn);	

									if (liReturn == 0) {
										offTxnsRetunData.setOffenderBookId(19L);
										return offTxnsRetunData;
									}
									pTxnEntrySeq = pTxnEntrySeq.add(BigDecimal.ONE);
									txnIdOne = txnIdNextVal;
								}
								if (objOffTransactions.getNbtTxnEntryAmount2() != null) {
									txnIdTwo = otdcloseRepository.txnIdNextValData();
									offTxnsRetunData.setAdjustTxnId(txnIdTwo);
									pTxnEntrySeq = new BigDecimal(1);
									if (objOffTransactions.getPayeePersonId() != null) {
										personsC = otdcloseRepository
												.personIdLastfirstName(objOffTransactions.getPayeePersonId());
									}
									if (objOffTransactions.getPayeeCorporateId() != null) {
										corporateC = otdcloseRepository
												.corporateIdLastfirstName(objOffTransactions.getPayeeCorporateId());
									}
									if ("".equals(objOffTransactions.getPayeeNameText())
											|| objOffTransactions.getPayeeNameText() == null) {
										payeeNameText = null;
									} else {
										payeeNameText = objOffTransactions.getPayeeNameText();
									}
									if (payeeNameText == null) {
										if (objOffTransactions.getPayeePersonId() != null) {
											payeeNameText = personsC;
										} else if (objOffTransactions.getPayeeCorporateId() != null) {
											payeeNameText = corporateC;
										}
									}
									if ("INST".equals(objOffTransactions.getCaseloadType())) {
										vTxnType = "CL";
									} else {
										vTxnType = "CLC";
									}
									try {
										logger.info("Calling otdcloseRepository.offTxnInsertOffenderTransactions for CLOSE ACCOUNT - CHECK  ");	
										liReturn = otdcloseRepository.offTxnInsertOffenderTransactions(txnIdTwo,
												pTxnEntrySeq, objOffTransactions.getCaseloadId(),
												objOffTransactions.getOffenderId(),
												objOffTransactions.getOffenderBookId(), "DR", vTxnType,
												"CLOSE ACCOUNT - CHECK", objOffTransactions.getNbtTxnEntryAmount2(),
												"REG", objOffTransactions.getCreateUserId(), "N", "N", "N", objOffTransactions.getPayeeCode(),
												objOffTransactions.getPayeeCorporateId(),
												objOffTransactions.getPayeePersonId(), objOffTransactions.getCreateUserId());
										logger.info("Response from otdcloseRepository.offTxnInsertOffenderTransactions response for CLOSE ACCOUNT - CHECK  "+liReturn);	
										if (liReturn == 0) {
											offTxnsRetunData.setOffenderBookId(20L);
											return offTxnsRetunData;
										}
										/*
										 * otdcloseRepository.updateOffenderBalance(objOffTransactions.getCaseloadId(),
										 * objOffTransactions.getOffenderId(), "DR", transDate, txnIdTwo, vTxnType,
										 * objOffTransactions.getNbtTxnEntryAmount2(), "REG");
										 */
										OffenderTransactions obj =  new OffenderTransactions();
										obj.setCaseloadId(objOffTransactions.getCaseloadId());
										obj.setOffenderId(objOffTransactions.getOffenderId());
										obj.setTxnPostingType("DR");
										obj.setTxnEntryDate(transDate);
										obj.setTxnId(txnIdTwo);
										obj.setTxnType(vTxnType);
										obj.setTxnEntryAmount(nbtTxnEntryAmount2);
										obj.setSubAccountType("REG");
										//Procedure call
										logger.info("Calling trustService.updateOffenderBalance ");
										trustService.updateOffenderBalance(obj, commitBean.getCreateUserId());
									} catch (Exception e) {
										offTxnsRetunData.setOffenderBookId(20L);
										return offTxnsRetunData;
									}
									/*
									 * liReturn =
									 * otdcloseRepository.processGlTransNew(objOffTransactions.getCaseloadId(),
									 * vTxnType, "CHECK", objOffTransactions.getNbtTxnEntryAmount2(), txnIdTwo,
									 * transDate, "CLOSE ACCOUNT - CHECK", pTxnEntrySeq,
									 * objOffTransactions.getOffenderId(), objOffTransactions.getOffenderBookId(),
									 * "REG", null, objOffTransactions.getPayeePersonId(),
									 * objOffTransactions.getPayeeCorporateId(), payeeNameText, pGlSeq);
									 */
									//Procedure call
									logger.info("Calling trustService.processGlTransNew for CLOSE ACCOUNT - CHECK  ");	
									liReturn = trustService.processGlTransNew(objOffTransactions.getCaseloadId(), vTxnType, 
											"CHECK", objOffTransactions.getNbtTxnEntryAmount2(), txnIdTwo, transDate, 
											"CLOSE ACCOUNT - CHECK", pTxnEntrySeq.intValue(), "OTDCLOSE", objOffTransactions.getOffenderId().intValue(), 
											 objOffTransactions.getOffenderBookId(), "REG", null, objOffTransactions.getPayeePersonId(), 
											 objOffTransactions.getPayeeCorporateId(), null, pGlSeq.intValue(), null, objOffTransactions.getCreateUserId());
									logger.info("Response from trustService.processGlTransNew  for CLOSE ACCOUNT - CHECK  "+liReturn);	
									if (liReturn == 0) {
										offTxnsRetunData.setOffenderBookId(21L);
										return offTxnsRetunData;
									}
									if (objOffTransactions.getNbtTxnEntryAmount2() > 0) {
										/*
										 * liReturn = otdcloseRepository.insertIntoChequeData(
										 * objOffTransactions.getCaseloadId(), txnIdTwo,
										 * objOffTransactions.getNbtTxnEntryAmount2(), pTxnNumber,
										 * objOffTransactions.getPayeePersonId(),
										 * objOffTransactions.getPayeeCorporateId(), payeeNameText,
										 * objOffTransactions.getOffenderId(), vTxnType);
										 */
										BankChequeData obj = new BankChequeData();
										obj.setCaseloadId(objOffTransactions.getCaseloadId());
										obj.setTxnId(txnIdTwo.longValue());
										obj.setTxnEntryAmount(new BigDecimal(objOffTransactions.getNbtTxnEntryAmount2()));
										obj.setChequeFlag("O");
										obj.setStartTxnId(new BigDecimal(pTxnNumber));
										obj.setEndTxnId(new BigDecimal(txnIdTwo));
										obj.setPayeePersonId( objOffTransactions.getPayeePersonId() !=null?new BigDecimal(objOffTransactions.getPayeePersonId()):null);
										obj.setPayeeCorporateId(objOffTransactions.getPayeeCorporateId() !=null? new BigDecimal(objOffTransactions.getPayeeCorporateId()):null);
										obj.setPayeeNameText(payeeNameText);
										obj.setOffenderId(new BigDecimal(objOffTransactions.getOffenderId()));
										obj.setSingleEntryFlag("N");
										obj.setBankAccountCode(null);
										obj.setModuleName("OTDCLOSE");
										obj.setTxnType(vTxnType);
										obj.setCreateUserId(objOffTransactions.getCreateUserId());
										//Procedure call
										logger.info("Calling trustService.insertIntoChequeData ");
										trustService.insertIntoChequeData(obj, obj.getCreateUserId());
										if (liReturn == 0) {
											offTxnsRetunData.setOffenderBookId(22L);
											return offTxnsRetunData;
										}
									}
									pTxnEntrySeq = pTxnEntrySeq.add(BigDecimal.ONE);
								}

							} else {
								if (pGovt >= 0) {
									if ("INST".equals(objOffTransactions.getCaseloadType())) {
										vTxnType = "CL";
									} else {
										vTxnType = "CLC";
									}
									try {
										logger.info("Calling otdcloseRepository.offTxnInsertOffenderTransactions  ");	
										liReturn = otdcloseRepository.offTxnInsertOffenderTransactions(pTxnNumber,
												pTxnEntrySequence, objOffTransactions.getCaseloadId(),
												objOffTransactions.getOffenderId(),
												objOffTransactions.getOffenderBookId(), "DR", vTxnType, "CLOSE ACCOUNT",
												pGovt, "REG", objOffTransactions.getCreateUserId(), "N", "N", "N", objOffTransactions.getPayeeCode(),
												objOffTransactions.getPayeeCorporateId(),
												objOffTransactions.getPayeePersonId(), objOffTransactions.getCreateUserId());
										logger.info("Response from otdcloseRepository.offTxnInsertOffenderTransactions  for CLOSE ACCOUNT - CHECK  "+liReturn);		
										if (liReturn == 0) {
											offTxnsRetunData.setOffenderBookId(23L);
											return offTxnsRetunData;
										}
										/*
										 * otdcloseRepository.updateOffenderBalance(objOffTransactions.getCaseloadId(),
										 * objOffTransactions.getOffenderId(), "DR", transDate, pTxnNumber, vTxnType,
										 * objOffTransactions.getNbtTxnEntryAmount2(), "REG");
										 */
										OffenderTransactions obj =  new OffenderTransactions();
										obj.setCaseloadId(objOffTransactions.getCaseloadId());
										obj.setOffenderId(objOffTransactions.getOffenderId());
										obj.setTxnPostingType("DR");
										obj.setTxnEntryDate(transDate);
										obj.setTxnId(pTxnNumber);
										obj.setTxnType(vTxnType);
										obj.setTxnEntryAmount(objOffTransactions.getNbtTxnEntryAmount2());
										obj.setSubAccountType("REG");
										//Procedure call
										logger.info("Calling trustService.updateOffenderBalance ");
										trustService.updateOffenderBalance(obj, commitBean.getCreateUserId());
									} catch (Exception e) {
										offTxnsRetunData.setOffenderBookId(23L);
										return offTxnsRetunData;
									}
									/*
									 * liReturn =
									 * otdcloseRepository.processGlTransNew(objOffTransactions.getCaseloadId(),
									 * vTxnType, "CHECK", pGovt, pTxnNumber, transDate, "CLOSE ACCOUNT",
									 * pTxnEntrySequence, objOffTransactions.getOffenderId(),
									 * objOffTransactions.getOffenderBookId(), "REG", null, null, null, null,
									 * pGlSeq);
									 */
									//Procedure call
									logger.info("Calling trustService.processGlTransNew for CLOSE ACCOUNT - CHECK  ");	
									liReturn = trustService.processGlTransNew(objOffTransactions.getCaseloadId(), vTxnType, 
											"CHECK", pGovt, pTxnNumber, transDate, "CLOSE ACCOUNT", pTxnEntrySequence.intValue(), 
											"OTDCLOSE", objOffTransactions.getOffenderId().intValue(), 
											 objOffTransactions.getOffenderBookId(), "REG", null, null, 
											null, null, pGlSeq.intValue(), null, objOffTransactions.getCreateUserId());
									logger.info("Response from trustService.processGlTransNew  for CLOSE ACCOUNT - CHECK  "+liReturn);		
									if (liReturn == 0) {
										offTxnsRetunData.setOffenderBookId(24L);
										return offTxnsRetunData;
									}
									pTxnEntrySeq = pTxnEntrySeq.add(BigDecimal.ONE);
								}
							}
						}
					} else {
						if ("INST".equals(objOffTransactions.getCaseloadType())) {
							vTxnType = "CL";
						} else {
							vTxnType = "CLC";
						}
						try {
							logger.info("Calling otdcloseRepository.offTxnInsertOffenderTransactions ");	
							liReturn = otdcloseRepository.offTxnInsertOffenderTransactions(pTxnNumber,
									pTxnEntrySequence, objOffTransactions.getCaseloadId(),
									objOffTransactions.getOffenderId(), objOffTransactions.getOffenderBookId(), "CR",
									vTxnType, "CLOSE ACCOUNT", objOffTransactions.getCurrentBalance(), "REG", objOffTransactions.getCreateUserId(),
									"N", "N", "N", objOffTransactions.getPayeeCode(),
									objOffTransactions.getPayeeCorporateId(), objOffTransactions.getPayeePersonId(), objOffTransactions.getCreateUserId());
							logger.info("Response from otdcloseRepository.offTxnInsertOffenderTransactions    "+liReturn);		
							if (liReturn == 0) {
								offTxnsRetunData.setOffenderBookId(25L);
								return offTxnsRetunData;
							}
							/*
							 * otdcloseRepository.updateOffenderBalance(objOffTransactions.getCaseloadId(),
							 * objOffTransactions.getOffenderId(), "CR", transDate, pTxnNumber, vTxnType,
							 * objOffTransactions.getCurrentBalance(), "REG");
							 */
							OffenderTransactions obj =  new OffenderTransactions();
							obj.setCaseloadId(objOffTransactions.getCaseloadId());
							obj.setOffenderId(objOffTransactions.getOffenderId());
							obj.setTxnPostingType("CR");
							obj.setTxnEntryDate(transDate);
							obj.setTxnId(pTxnNumber);
							obj.setTxnType(vTxnType);
							obj.setTxnEntryAmount(objOffTransactions.getCurrentBalance());
							obj.setToSubAccountType("REG");
							//Procedure call
							logger.info("Calling trustService.updateOffenderBalance ");
							trustService.updateOffenderBalance(obj, commitBean.getCreateUserId());
						} catch (Exception e) {
							offTxnsRetunData.setOffenderBookId(25L);
							return offTxnsRetunData;
						}
						/*
						 * liReturn =
						 * otdcloseRepository.processGlTransNew(objOffTransactions.getCaseloadId(),
						 * vTxnType, "O/D", objOffTransactions.getCurrentBalance(), pTxnNumber,
						 * transDate, "CLOSE ACCOUNT", pTxnEntrySequence,
						 * objOffTransactions.getOffenderId(), objOffTransactions.getOffenderBookId(),
						 * null, "REG", null, null, null, pGlSeq);
						 */
						//Procedure call
						logger.info("Calling trustService.processGlTransNew for CLOSE ACCOUNT - O/D  ");	
						liReturn = trustService.processGlTransNew(objOffTransactions.getCaseloadId(), vTxnType, 
								"O/D", objOffTransactions.getCurrentBalance(), pTxnNumber, transDate, "CLOSE ACCOUNT", pTxnEntrySequence.intValue(), 
								"OTDCLOSE", objOffTransactions.getOffenderId().intValue(), 
								 objOffTransactions.getOffenderBookId(), null, "REG", null, 
								null, null, pGlSeq.intValue(), null, objOffTransactions.getCreateUserId());
						logger.info("Response from trustService.processGlTransNew  for CLOSE ACCOUNT - O/D  "+liReturn);	
						if (liReturn == 0) {
							offTxnsRetunData.setOffenderBookId(26L);
							return offTxnsRetunData;
						}
						pTxnEntrySeq = pTxnEntrySeq.add(BigDecimal.ONE);
					}
					logger.info("Calling returnZeroAmountFinal  ");	
					Integer returnZeroAmountFinal = ZeroAmount(pTxnNumber, pTxnEntrySeq,
							objOffTransactions.getCaseloadId(), objOffTransactions.getOffenderId(), "REG",objOffTransactions.getCreateUserId());
					logger.info("Response from returnZeroAmountFinal   "+liReturn);	
					if (returnZeroAmountFinal == 2) {
						offTxnsRetunData.setOffenderBookId(27L);
						return offTxnsRetunData;
					}
					logger.info("Calling otdcloseRepository.updateOffenderTrustAccountsY  ");	
					Integer offenderTrstAcnt = otdcloseRepository.updateOffenderTrustAccountsY(
							objOffTransactions.getOffenderId(), objOffTransactions.getCaseloadId());
					logger.info("Response from otdcloseRepository.updateOffenderTrustAccountsY  "+offenderTrstAcnt);	
					if (offenderTrstAcnt == 0) {
						offTxnsRetunData.setOffenderBookId(28L);
						return offTxnsRetunData;
					}
					offTxnsRetunData.setOffenderBookId(1L);
					return offTxnsRetunData;
				}
			} catch (Exception e) {
				logger.error(this.getClass().getName()+" error in glTxnCommit : ", e);
			}
		}
		return offTxnsRetunData;
	}

	private Integer ZeroAmount(final Integer txnIdNextVal, final BigDecimal pTxnEntrySeq, final String caseloadId,
			final Long offenderId, final String pSuAaccountType, final String userName) {
		Integer accountCodesList = null;
		Integer PTrustAccountCode = otdcloseRepository.accountCodeNumber(pSuAaccountType, userName);
		if (PTrustAccountCode != null) {
			accountCodesList = otdcloseRepository.updateOffenderSubAccounts(txnIdNextVal, pTxnEntrySeq, caseloadId,
					offenderId, PTrustAccountCode, userName);
			if (accountCodesList == 0) {
				accountCodesList = 2;
				return accountCodesList;
			}
		}
		return accountCodesList;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @
	 */
	public List<SystemProfiles> sysPflExecuteQuery(final SystemProfiles searchRecord) {
		return otdcloseRepository.sysPflExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstSYS_PFL
	 *
	 * @
	 */
	@Transactional
	public Integer sysPflCommit(final SystemProfilesCommitBean CommitBean) {
		final int liReturn = 0;
		return liReturn;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<ReferenceCodes> cgfkOffTxnPayeeCodeRecordGroup() {
		return otdcloseRepository.cgfkOffTxnPayeeCodeRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public BigDecimal getRegBalOne(final Long offenderId, final String caseloadId) {
		BigDecimal lvAcName = null;
		lvAcName = otdcloseRepository.getRegBal(offenderId, caseloadId);
		lvAcName = lvAcName == null ? BigDecimal.ZERO : lvAcName; 
		if (lvAcName.compareTo(BigDecimal.ZERO) > 0) {
			lvAcName = BigDecimal.ONE;
			return lvAcName;
		}
		return lvAcName;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public BigDecimal getRegBal(final Long offenderId, final String caseloadId) {
		return otdcloseRepository.getRegBal(offenderId, caseloadId);
	}

	public String accountClosedFlagValidation(final Long offenderId, final String caseloadId) {
		return otdcloseRepository.accountClosedFlagValidation(offenderId, caseloadId);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOffTxnOffTxnRef
	 *
	 * @param params
	 *
	 */
	public BigDecimal chkAccountClosedFlag(final Long offenderId, final String caseloadId,String userName) {
		BigDecimal lvAcName = null;
		lvAcName = otdcloseRepository.chkAccountClosedFlag(offenderId, caseloadId,userName);
		if (lvAcName != null) {
			if (lvAcName.compareTo(BigDecimal.ZERO) > 0) {
				lvAcName = BigDecimal.ONE;
				return lvAcName;
			}
		}
		return lvAcName;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOffTxnOffTxnRef
	 *
	 * @param params
	 *
	 */
	public BigDecimal chkSubAccountFlag(final Long offenderId, final String caseloadId) {
		BigDecimal lvAcName = null;
		lvAcName = otdcloseRepository.chkSubAccountFlag(offenderId, caseloadId);
		if (lvAcName != null) {
			if (lvAcName.compareTo(BigDecimal.ZERO) > 0) {
				lvAcName = BigDecimal.ONE;
				return lvAcName;
			}
		}
		return lvAcName;
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
	public String freezDisbursement(final String caseloadId, final Long offenderId, final String pTxnType,
			final String caseloadType) {
	//	return otdcloseRepository.freezDisbursement(caseloadId, offenderId, pTxnType, caseloadType);
       ChkFreezeDisbursements object = new ChkFreezeDisbursements();
       object.setpCsldId(caseloadId);
       object.setpCsldType(caseloadType);
       object.setpOffId(offenderId);
       object.setpTxnType(pTxnType);
       object.setpModuleName("OTDCLOSE");
       object.setpAcntCode(null);
       object.setpDate(null);
	return	trustService.chkFreezeDisbursements(object);
	}

}