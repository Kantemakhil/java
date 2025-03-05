package net.syscon.s4.inmate.trust.trustaccounts.impl;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionOperations;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.SystemProfilesCommitBean;
import net.syscon.s4.core.EliteDateService;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.inmate.beans.OffenderSubAccounts;
import net.syscon.s4.inmate.beans.OffenderTrustAccountsTemp;
import net.syscon.s4.inmate.beans.OffenderTrustAccountsTempCommitBean;
import net.syscon.s4.inmate.beans.OffenderWorks;
import net.syscon.s4.inmate.trust.trustaccounts.OtdclinaRepository;
import net.syscon.s4.inmate.trust.trustaccounts.OtdclinaService;
import net.syscon.s4.inst.automatedcounts.beans.LockedModules;
import net.syscon.s4.pkgs.otdclina.OtdclinaPkgService;
import net.syscon.s4.pkgs.trust.TrustService;

/**
 * Class OtdclinaServiceImpl
 */
@Service
public class OtdclinaServiceImpl extends BaseBusiness implements OtdclinaService {

	@Autowired
	private OtdclinaRepository otdclinaRepository;

	@Autowired
	private EliteDateService eliteDateService;
	@Autowired
	private OtdclinaPkgService otdclinaPkgService;
	@Autowired
	private TrustService trustService;

	/**
	 * Creates new OtdclinaServiceImpl class Object
	 */
	public OtdclinaServiceImpl() {
		// OtdclinaServiceImpl
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * 
	 */
	public OffenderTrustAccountsTemp offTracKeyExeqry(final OffenderTrustAccountsTemp paramBean) {
		OffenderTrustAccountsTemp offenderTrustAccountsTemp = otdclinaRepository.offTracKeyExeqry(paramBean);
		return offenderTrustAccountsTemp;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * 
	 */
	public OffenderTrustAccountsTemp offTracKeyEntqry(final OffenderTrustAccountsTemp paramBean) {
		OffenderTrustAccountsTemp offenderTrustAccountsTemp = otdclinaRepository.offTracKeyEntqry(paramBean);
		return offenderTrustAccountsTemp;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * 
	 */
	public OffenderWorks CgrichkOffenderTrustAccoun(final OffenderWorks paramBean) {
		final OffenderWorks offenderWorks = (OffenderWorks) otdclinaRepository.cgrichkOffenderTrustAccoun(paramBean);
		return offenderWorks;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * 
	 */
	public List<Offenders> ClearZeroBalanceAccounts(final Offenders paramBean) {
		final List<Offenders> offendersList = null;
		return offendersList;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * 
	 */
	public List<OffenderSubAccounts> CheckBalanceTally(final OffenderSubAccounts paramBean) {
		final List<OffenderSubAccounts> offenderSubAccountsList = null;
		return offenderSubAccountsList;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * 
	 */
	public TransactionOperations CheckSetupBeforeTransfer(final TransactionOperations paramBean) {
		final TransactionOperations transactionOperations = null;
		return transactionOperations;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * 
	 */
	public List<LockedModules> checkLock() {
		return otdclinaRepository.checkLock();
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * 
	 */
	public List<Offenders> TransferFundsToProPer(final Offenders paramBean) {
		List<Offenders> offendersList = null;
		return offendersList;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * 
	 */
	public List<SystemProfiles> sysPflExecuteQuery(final SystemProfiles searchRecord) {
		return otdclinaRepository.sysPflExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstSYS_PFL
	 *
	 * 
	 */
	@Transactional
	public Integer sysPflCommit(final SystemProfilesCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			liReturn = otdclinaRepository.sysPflInsertSystemProfiles(commitBean.getInsertList());
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			liReturn = otdclinaRepository.sysPflUpdateSystemProfiles(commitBean.getUpdateList());
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().forEach(bo -> {bo.setModifyUserId(commitBean.getCreateUserId());});
			liReturn = otdclinaRepository.sysPflDeleteSystemProfiles(commitBean.getDeleteList());
		}
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * 
	 */
	public List<OffenderTrustAccountsTemp> offTracExecuteQuery(final OffenderTrustAccountsTemp searchRecord) {
//		BigDecimal totalBal = otdclinaRepository.offTracPopulateTempTable(searchRecord);
		//Procedure Call 
		searchRecord.setSelectionMethod(searchRecord.getAgyLocId());
		BigDecimal totalBal = otdclinaPkgService.populateTable(searchRecord, searchRecord.getCreateUserId());
		List<OffenderTrustAccountsTemp> retrunList = otdclinaRepository.offTracExecuteQuery(searchRecord);
		for (final OffenderTrustAccountsTemp offenderTrustAccountsTemp : retrunList) {
			if ("Y".equals(searchRecord.getSealFlag())) {
				offenderTrustAccountsTemp.setTotalAmount(totalBal);
			} else {
				offenderTrustAccountsTemp.setTotalAmount(new BigDecimal(0));
			}
			BigDecimal SubAcctBal = sumBalanceOffenderSubAccounts(offenderTrustAccountsTemp.getOffenderId(),offenderTrustAccountsTemp.getCreateUserId());
			Double valCurrentBal = null;
			Double SubAcctBalTemp = null;
			
			if (offenderTrustAccountsTemp.getCurrentBalance() != null){
				valCurrentBal = Double.valueOf(offenderTrustAccountsTemp.getCurrentBalance().toString());
			}
			if (SubAcctBal != null) {
				SubAcctBalTemp = Double.valueOf(SubAcctBal.toString());
			}
			if (valCurrentBal != null && SubAcctBalTemp != null && valCurrentBal.compareTo(SubAcctBalTemp) != 0) {
				offenderTrustAccountsTemp.setIsValid(1);
			} else {
				offenderTrustAccountsTemp.setIsValid(0);
			}
		}
		
		return retrunList;
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFF_TRAC
	 *
	 * 
	 */
	@Transactional
	public Integer offTracCommit(final OffenderTrustAccountsTempCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			liReturn = otdclinaRepository.offTracInsertOffenderTrustAccountsTemp(commitBean.getInsertList());
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			liReturn = otdclinaRepository.offTracUpdateOffenderTrustAccountsTemp(commitBean.getUpdateList());
			for (final OffenderTrustAccountsTemp objGlTransactions : commitBean.getUpdateList()) {
				liReturn = otdclinaRepository.updateOffenderTrustAccounts(objGlTransactions.getOffenderId(),
						objGlTransactions.getNotifyDate(), objGlTransactions.getCaseloadId());
			}
			liReturn = otdclinaRepository.offTracUpdateOffenderTrustAccountsTemp(commitBean.getUpdateList());
		}
		return liReturn;
	}

	/**
	 * This method is used to execute a record group
	 *
	 *
	 */
	public List<ReferenceCodes> selectMethodRgRecordGroup() {
		return otdclinaRepository.selectMethodRgRecordGroup();

	}

	@Transactional(rollbackFor = Exception.class)
	public Integer whenButtonPressed(final OffenderTrustAccountsTempCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			Double valCurrentBal = null;
			Double SubAcctBalTemp = null;
			String lvRecordCleared = "N";
			Integer lvTransactionMonitor = 0;
			Integer cgnbtTotalCount = 0;
			Double cgnbtTotalCountTemp = null;
			Double pTotalBalanceTemp = null;
			Integer lvNoErrors = 0;
			String lvConfirmAll = "N";
			BigDecimal pTotalBalance = null;
			String payeeCode = "IND";
			BigDecimal pBalance = new BigDecimal(0);
			Date transDate = trunc(eliteDateService.getDBTime());
			BigDecimal pTxnEntrySeq = BigDecimal.ZERO;
			BigDecimal pGlTxnEntrySeq = BigDecimal.ZERO;
			String lvTxnEntryDesc = null;
			String vTxnType = null;
			Long pAccountCode = null;
			String PSuAaccountType = null;
			BigDecimal pGlSeq = BigDecimal.ZERO;
			String lvTxnType = null;
			for (final OffenderTrustAccountsTemp objGlTransactions : commitBean.getUpdateList()) {
				pTxnEntrySeq = BigDecimal.ZERO;
				pGlTxnEntrySeq = BigDecimal.ZERO;
				if ("true".equals(objGlTransactions.getCbntAccountClosedFlag())) {
					objGlTransactions.setCbntAccountClosedFlag("Y");
				} else {
					objGlTransactions.setCbntAccountClosedFlag("N");
				}
				if ("Y".equals(objGlTransactions.getSealFlagTemp())) {
					lvConfirmAll = "Y";
				}
				Integer txnTypeCount = otdclinaRepository.txnTypeCountData(commitBean.getCreateUserId());
				if (txnTypeCount < 1) {
					liReturn = 23;
					return liReturn;
				}
				OffenderTrustAccountsTemp offTrstAcntType = new OffenderTrustAccountsTemp();
				String maxXData = otdclinaRepository.offenderSubAccountsMax(objGlTransactions.getOffenderId(), commitBean.getCreateUserId());
				if (("ZERO".equals(objGlTransactions.getSealFlag()))
						|| ("NOTI".equals(objGlTransactions.getSealFlag()))) {
					if ("Y".equals(objGlTransactions.getCbntAccountClosedFlag())) {
						if (objGlTransactions.getCurrentBalance().compareTo(BigDecimal.ZERO) == 0) {
							if (objGlTransactions.getCgnbtModifyUserId() != null) {
								lvTxnEntryDesc = objGlTransactions.getCgnbtModifyUserId();
							} else {
								lvTxnEntryDesc = "Transfer to Welfare Fund";
							}
							BigDecimal SubAcctBal = sumBalanceOffenderSubAccounts(objGlTransactions.getOffenderId(),objGlTransactions.getCreateUserId());
							valCurrentBal = Double.valueOf(objGlTransactions.getCurrentBalance().toString());
//							SubAcctBalTemp = Double.valueOf(SubAcctBal.toString());
							if(SubAcctBal!=null) {
								String value=SubAcctBal.toString();
								if(value!=null && !value.equals("null") && !value.equals("")) {
									SubAcctBalTemp =Double.parseDouble(value);
								}
							}
							if (SubAcctBalTemp!=null && valCurrentBal.compareTo(SubAcctBalTemp) != 0) {
								liReturn = 21;
								return liReturn;
							}
							if (maxXData == null) {
								pTxnEntrySeq = BigDecimal.ZERO;
								pTxnEntrySeq = BigDecimal.ONE;
								pGlSeq = BigDecimal.ZERO;
								Integer txnIdNextVal = otdclinaRepository.txnIdNextValData();
								if ("INST".equals(objGlTransactions.getCaseloadType())) {
									vTxnType = "CL";
								} else {
									vTxnType = "CLC";
								}
								BigDecimal rootOffenderId = otdclinaRepository
										.findRootOffenderId(objGlTransactions.getOffenderId());
								String lastNameFirstName = otdclinaRepository.lastNameFirstName(rootOffenderId, commitBean.getCreateUserId());
								String txnTypeDescription = otdclinaRepository.txnIdDescription(vTxnType);
								if (txnTypeDescription != null) {
									lvTxnEntryDesc = txnTypeDescription;
								}
								//Procedure Call
//								liReturn = otdclinaRepository.insertIntoOffenderTransaction(txnIdNextVal, pTxnEntrySeq,
//										objGlTransactions.getCaseloadId(), rootOffenderId,
//										objGlTransactions.getOffenderBookId(), vTxnType, lvTxnEntryDesc, pBalance,
//										transDate, "REG", "DR");
								OffenderTransactions obj = new OffenderTransactions();
								obj.setTxnId(txnIdNextVal);
								obj.setTxnEntrySeq(pTxnEntrySeq!= null ? pTxnEntrySeq.intValue() : null);
								obj.setCaseloadId(objGlTransactions.getCaseloadId());
								obj.setOffenderId(rootOffenderId != null ? rootOffenderId.longValue() : null);
								obj.setOffenderBookId(objGlTransactions.getOffenderBookId()!=null ? objGlTransactions.getOffenderBookId().longValue(): null);
								obj.setTxnPostingType("DR");
								obj.setTxnType(vTxnType);
								obj.setTxnEntryDesc(lvTxnEntryDesc);
								obj.setTxnEntryAmount(pBalance!=null ? pBalance.doubleValue() : null);
								obj.setTxnEntryDate(transDate);
								obj.setSubAccountType("REG");
								obj.setDeductionFlag(null);
								obj.setDeductionType(null);
								obj.setPayeeCorporateId(null);
								obj.setPayeePersonId(null);
								obj.setInfoNumber(null);
								obj.setSlipPrintedFlag("N");
								obj.setCreateUserId(commitBean.getCreateUserId());
								liReturn = trustService.insertIntoOffenderTrans(obj);
								if (liReturn == 0) {
									liReturn = 2;
									return liReturn;
								}
								liReturn = otdclinaRepository.updateOffenderTransactions(payeeCode, lastNameFirstName,
										txnIdNextVal, pTxnEntrySeq, commitBean.getCreateUserId());
								if (liReturn == 0) {
									liReturn = 2;
									return liReturn;
								}
								//Procedure call
//								liReturn = otdclinaRepository.processGlTransNew(objGlTransactions.getCaseloadId(),
//										vTxnType, pBalance, txnIdNextVal, transDate, lvTxnEntryDesc, pTxnEntrySeq,
//										rootOffenderId, objGlTransactions.getOffenderBookId(), pGlSeq, "CASH", "REG",
//										null);
								liReturn = trustService.processGlTransNew(objGlTransactions.getCaseloadId(),
										vTxnType, "CASH", pBalance != null ? pBalance.doubleValue() : null,
										txnIdNextVal, transDate, lvTxnEntryDesc,
										pTxnEntrySeq != null ? pTxnEntrySeq.intValue() : null, "OTDCLINA",
										rootOffenderId != null ? rootOffenderId.intValue() : null,
										objGlTransactions.getOffenderBookId(), "REG", null, null,
										null, null, 0, null, commitBean.getCreateUserId());
								if (liReturn == 0) {
									liReturn = 3;
									return liReturn;
								}
								Integer closeAccountData = closeAccount(txnIdNextVal, pTxnEntrySeq,
										objGlTransactions.getCaseloadId(), objGlTransactions.getOffenderId(),
										rootOffenderId, commitBean.getCreateUserId());
								if (closeAccountData == 1) {
									liReturn = 4;
									return liReturn;
								}
								Integer offenderTrstAcnt = otdclinaRepository
										.updateOffenderTrustAccountsY(rootOffenderId, commitBean.getCreateUserId());
								if (offenderTrstAcnt == 0) {
									liReturn = 5;
									return liReturn;
								}
								lvRecordCleared = "Y";
								lvTransactionMonitor = lvTransactionMonitor + 1;
								Integer countSessionId = otdclinaRepository.countSessionId();
								if (countSessionId == 0 || countSessionId > 0) {
									cgnbtTotalCount = countSessionId - 1;
								}
							} else {
								liReturn = 6;
								lvNoErrors = lvNoErrors + 1;
								return liReturn;
							}
						}
					}
					if (lvNoErrors >= 1) {
						liReturn = 7;
						return liReturn;
					}
					if ("Y".equals(lvRecordCleared)) {
						offTrstAcntType.setAgyLocId(objGlTransactions.getSealFlag());
						offTrstAcntType.setCaseloadId(objGlTransactions.getCaseloadId());
						offTrstAcntType.setSessionId(objGlTransactions.getSessionIdTemp());
						offTrstAcntType.setCreateDateTime(objGlTransactions.getCreateDateTimeTemp());
						offTrstAcntType.setModifyDateTime(objGlTransactions.getModifyDateTimeTemp());
						offTrstAcntType.setLastName(objGlTransactions.getLastNameTemp());
						offTrstAcntType.setSealFlag(lvConfirmAll);
						//Procedure call
						//pTotalBalance = otdclinaRepository.offTracPopulateTempTable(offTrstAcntType);
						pTotalBalance = otdclinaPkgService.populateTable(offTrstAcntType, commitBean.getCreateUserId());
						if (pTotalBalance != null) {
							liReturn = 8;
						}
					}
				}
				if (("ALL".equals(objGlTransactions.getSealFlag()))
						|| ("NOTI".equals(objGlTransactions.getSealFlag()))) {
					if ("Y".equals(objGlTransactions.getCbntAccountClosedFlag())) {
						if (objGlTransactions.getCurrentBalance().compareTo(BigDecimal.ZERO) > 0) {
							if (objGlTransactions.getCgnbtModifyUserId() != null) {
								lvTxnEntryDesc = objGlTransactions.getCgnbtModifyUserId();
							} else {
								lvTxnEntryDesc = "Sub-Account Transfer";
							}
							BigDecimal SubAcctBal = sumBalanceOffenderSubAccounts(objGlTransactions.getOffenderId(),objGlTransactions.getCreateUserId());
							if (SubAcctBal == null) {
								SubAcctBal = BigDecimal.ZERO;
							}
							valCurrentBal = Double.valueOf(objGlTransactions.getCurrentBalance().toString());
							SubAcctBalTemp = Double.valueOf(SubAcctBal.toString());
							if (valCurrentBal.compareTo(SubAcctBalTemp) != 0) {
								liReturn = 21;
								return liReturn;
							}
							if (maxXData == null) {
								BigDecimal rootOffenderId = otdclinaRepository
										.findRootOffenderId(objGlTransactions.getOffenderId());
								String lastNameFirstName = otdclinaRepository.lastNameFirstName(rootOffenderId, commitBean.getCreateUserId());
								List<OffenderSubAccounts> offSubAcnts = otdclinaRepository
										.balanceSubAccountCode(rootOffenderId, commitBean.getCreateUserId());
								Integer txnIdNextVal = otdclinaRepository.txnIdNextValData();
								for (OffenderSubAccounts offenderSubAccounts : offSubAcnts) {
									pAccountCode = offenderSubAccounts.getTrustAccountCode();
									pBalance = BigDecimal.valueOf(offenderSubAccounts.getBalance());
									PSuAaccountType = offenderSubAccounts.getModifyUserId();
									if (offenderSubAccounts.getBalance() > 0) {
										pTxnEntrySeq = pTxnEntrySeq.add(BigDecimal.ONE);
										pGlTxnEntrySeq = pTxnEntrySeq;
										//Procedure Call
//										liReturn = otdclinaRepository.insertIntoOffenderTransaction(txnIdNextVal,
//												pTxnEntrySeq, objGlTransactions.getCaseloadId(), rootOffenderId,
//												objGlTransactions.getOffenderBookId(), "OT", lvTxnEntryDesc, pBalance,
//												transDate, PSuAaccountType, "DR");
										OffenderTransactions obj = new OffenderTransactions();
										obj.setTxnId(txnIdNextVal);
										obj.setTxnEntrySeq(pTxnEntrySeq!= null ? pTxnEntrySeq.intValue() : null);
										obj.setCaseloadId(objGlTransactions.getCaseloadId());
										obj.setOffenderId(rootOffenderId != null ? rootOffenderId.longValue() : null);
										obj.setOffenderBookId(objGlTransactions.getOffenderBookId()!=null ? objGlTransactions.getOffenderBookId().longValue(): null);
										obj.setTxnPostingType("DR");
										obj.setTxnType("OT");
										obj.setTxnEntryDesc(lvTxnEntryDesc);
										obj.setTxnEntryAmount(pBalance!=null ? pBalance.doubleValue() : null);
										obj.setTxnEntryDate(transDate);
										obj.setSubAccountType(PSuAaccountType);
										obj.setDeductionFlag(null);
										obj.setDeductionType(null);
										obj.setPayeeCorporateId(null);
										obj.setPayeePersonId(null);
										obj.setInfoNumber(null);
										obj.setSlipPrintedFlag("N");
										obj.setCreateUserId(commitBean.getCreateUserId());
										liReturn = trustService.insertIntoOffenderTrans(obj);
										liReturn = otdclinaRepository.updateOffenderTransactionsTransfer(
												lastNameFirstName, txnIdNextVal, pTxnEntrySeq, commitBean.getCreateUserId());
										if (liReturn == 0) {
											liReturn = 9;
											return liReturn;
										}
										pTxnEntrySeq = pTxnEntrySeq.add(BigDecimal.ONE);
										//Procedure Call 
//										liReturn = otdclinaRepository.insertIntoOffenderTransaction(txnIdNextVal,
//												pTxnEntrySeq, objGlTransactions.getCaseloadId(), rootOffenderId,
//												objGlTransactions.getOffenderBookId(), "OT", lvTxnEntryDesc, pBalance,
//												transDate, "REG", "CR");
										OffenderTransactions transObj = new OffenderTransactions();
										transObj.setTxnId(txnIdNextVal);
										transObj.setTxnEntrySeq(pTxnEntrySeq!= null ? pTxnEntrySeq.intValue() : null);
										transObj.setCaseloadId(objGlTransactions.getCaseloadId());
										transObj.setOffenderId(rootOffenderId != null ? rootOffenderId.longValue() : null);
										transObj.setOffenderBookId(objGlTransactions.getOffenderBookId()!=null ? objGlTransactions.getOffenderBookId().longValue(): null);
										transObj.setTxnPostingType("CR");
										transObj.setTxnType("OT");
										transObj.setTxnEntryDesc(lvTxnEntryDesc);
										transObj.setTxnEntryAmount(pBalance!=null ? pBalance.doubleValue() : null);
										transObj.setTxnEntryDate(transDate);
										transObj.setSubAccountType("REG");
										transObj.setDeductionFlag(null);
										transObj.setDeductionType(null);
										transObj.setPayeeCorporateId(null);
										transObj.setPayeePersonId(null);
										transObj.setInfoNumber(null);
										transObj.setSlipPrintedFlag("N");
										transObj.setCreateUserId(commitBean.getCreateUserId());
										liReturn = trustService.insertIntoOffenderTrans(transObj);
										liReturn = otdclinaRepository.updateOffenderTransactionsTransfer(
												lastNameFirstName, txnIdNextVal, pTxnEntrySeq, commitBean.getCreateUserId());
										if (liReturn == 0) {
											liReturn = 10;
											return liReturn;
										}
										//Procedure Call
//										liReturn = otdclinaRepository.processGlTransNew(
//												objGlTransactions.getCaseloadId(), "OT", pBalance, txnIdNextVal,
//												transDate, lvTxnEntryDesc, pGlTxnEntrySeq, rootOffenderId,
//												objGlTransactions.getOffenderBookId(), pGlSeq, null, PSuAaccountType,
//												"REG");
										liReturn = trustService.processGlTransNew(objGlTransactions.getCaseloadId(),
												"OT", null, pBalance != null ? pBalance.doubleValue() : null,
												txnIdNextVal, transDate, lvTxnEntryDesc,
												pGlTxnEntrySeq != null ? pGlTxnEntrySeq.intValue() : null, "OTDCLINA",
												rootOffenderId != null ? rootOffenderId.intValue() : null,
												objGlTransactions.getOffenderBookId(), PSuAaccountType, "REG", null,
												null, null, 0, null, commitBean.getCreateUserId());
										if (liReturn == 0) {
											liReturn = 11;
											return liReturn;
										}
										Integer returnZeroAmount = ZeroAmount(txnIdNextVal, pTxnEntrySeq,
												objGlTransactions.getCaseloadId(), rootOffenderId, PSuAaccountType, commitBean.getCreateUserId());
										if (returnZeroAmount == 1) {
											liReturn = 12;
											return liReturn;
										}
									}
								}
								if (objGlTransactions.getCgnbtModifyUserId() != null) {
									lvTxnEntryDesc = objGlTransactions.getCgnbtModifyUserId();
								} else {
									lvTxnEntryDesc = "Transfer to Pro Per Payable";
								}
								List<OffenderSubAccounts> offSubAcntsDataTmp = otdclinaRepository
										.balanceSubAccountCodeProp(rootOffenderId, commitBean.getCreateUserId());
								for (OffenderSubAccounts offenderSubAccountsData : offSubAcntsDataTmp) {
									pAccountCode = offenderSubAccountsData.getTrustAccountCode();
									pBalance = BigDecimal.valueOf(offenderSubAccountsData.getBalance());
									PSuAaccountType = offenderSubAccountsData.getModifyUserId();
									pGlSeq = BigDecimal.ZERO;
									if (offenderSubAccountsData.getBalance() > 0) {
										lvTxnType = "PROP";
										pGlSeq = BigDecimal.ZERO;
										pTxnEntrySeq = pTxnEntrySeq.add(BigDecimal.ONE);
										//Procedure Call
//										liReturn = otdclinaRepository.insertIntoOffenderTransaction(txnIdNextVal,
//												pTxnEntrySeq, objGlTransactions.getCaseloadId(), rootOffenderId,
//												objGlTransactions.getOffenderBookId(), lvTxnType, lvTxnEntryDesc,
//												pBalance, transDate, PSuAaccountType, "DR");
										OffenderTransactions transObj = new OffenderTransactions();
										transObj.setTxnId(txnIdNextVal);
										transObj.setTxnEntrySeq(pTxnEntrySeq!= null ? pTxnEntrySeq.intValue() : null);
										transObj.setCaseloadId(objGlTransactions.getCaseloadId());
										transObj.setOffenderId(rootOffenderId != null ? rootOffenderId.longValue() : null);
										transObj.setOffenderBookId(objGlTransactions.getOffenderBookId()!=null ? objGlTransactions.getOffenderBookId().longValue(): null);
										transObj.setTxnPostingType("DR");
										transObj.setTxnType(lvTxnType);
										transObj.setTxnEntryDesc(lvTxnEntryDesc);
										transObj.setTxnEntryAmount(pBalance!=null ? pBalance.doubleValue() : null);
										transObj.setTxnEntryDate(transDate);
										transObj.setSubAccountType(PSuAaccountType);
										transObj.setDeductionFlag(null);
										transObj.setDeductionType(null);
										transObj.setPayeeCorporateId(null);
										transObj.setPayeePersonId(null);
										transObj.setInfoNumber(null);
										transObj.setSlipPrintedFlag("N");
										transObj.setCreateUserId(commitBean.getCreateUserId());
										liReturn = trustService.insertIntoOffenderTrans(transObj);
										liReturn = otdclinaRepository.updateOffenderTransactionsTransfer(
												lastNameFirstName, txnIdNextVal, pTxnEntrySeq, commitBean.getCreateUserId());
										if (liReturn == 0) {
											liReturn = 13;
											return liReturn;
										}
										//Procedure Call
//										liReturn = otdclinaRepository.processGlTransNew(
//												objGlTransactions.getCaseloadId(), lvTxnType, pBalance, txnIdNextVal,
//												transDate, lvTxnEntryDesc, pTxnEntrySeq, rootOffenderId,
//												objGlTransactions.getOffenderBookId(), pGlSeq, null, PSuAaccountType,
//												null);
										liReturn = trustService.processGlTransNew(objGlTransactions.getCaseloadId(),
												lvTxnType, null, pBalance != null ? pBalance.doubleValue() : null,
												txnIdNextVal, transDate, lvTxnEntryDesc,
												pTxnEntrySeq != null ? pTxnEntrySeq.intValue() : null, "OTDCLINA",
												rootOffenderId != null ? rootOffenderId.intValue() : null,
												objGlTransactions.getOffenderBookId(), PSuAaccountType, null, null,
												null, null, 0, null, commitBean.getCreateUserId());
										if (liReturn == 0) {
											liReturn = 14;
											return liReturn;
										}
										Integer returnZeroAmount = ZeroAmount(txnIdNextVal, pTxnEntrySeq,
												objGlTransactions.getCaseloadId(), rootOffenderId, PSuAaccountType, commitBean.getCreateUserId());
										if (returnZeroAmount == 1) {
											liReturn = 15;
											return liReturn;
										}
									}
								}
								Integer offenderTrstAcnt = otdclinaRepository
										.updateOffenderTrustAccountsY(rootOffenderId, commitBean.getCreateUserId());
								if (offenderTrstAcnt == 0) {
								}
								if (objGlTransactions.getCgnbtModifyUserId() != null) {
									lvTxnEntryDesc = objGlTransactions.getCgnbtModifyUserId();
								} else {
									lvTxnEntryDesc = "Transfer to Welfare Fund";
								}
								String txnTypeDescription = otdclinaRepository.txnIdDescription(vTxnType);
								if (txnTypeDescription != null) {
									lvTxnEntryDesc = txnTypeDescription;
								}
								List<OffenderSubAccounts> offSubAcntsDataTmpReg = otdclinaRepository
										.balanceSubAccountCodeReg(rootOffenderId, commitBean.getCreateUserId());
								for (OffenderSubAccounts offenderSubAccountsDataTemp : offSubAcntsDataTmpReg) {
									pAccountCode = offenderSubAccountsDataTemp.getTrustAccountCode();
									pBalance = BigDecimal.valueOf(offenderSubAccountsDataTemp.getBalance());
									PSuAaccountType = offenderSubAccountsDataTemp.getModifyUserId();
									if (offenderSubAccountsDataTemp.getBalance() > 0) {
										lvTxnType = "WFT";
										pTxnEntrySeq = pTxnEntrySeq.add(BigDecimal.ONE);
										
										//Procedure call
//										liReturn = otdclinaRepository.insertIntoOffenderTransaction(txnIdNextVal,
//												pTxnEntrySeq, objGlTransactions.getCaseloadId(), rootOffenderId,
//												objGlTransactions.getOffenderBookId(), lvTxnType, lvTxnEntryDesc,
//												pBalance, transDate, "REG", "DR");
										OffenderTransactions transObj = new OffenderTransactions();
										transObj.setTxnId(txnIdNextVal);
										transObj.setTxnEntrySeq(pTxnEntrySeq!= null ? pTxnEntrySeq.intValue() : null);
										transObj.setCaseloadId(objGlTransactions.getCaseloadId());
										transObj.setOffenderId(rootOffenderId != null ? rootOffenderId.longValue() : null);
										transObj.setOffenderBookId(objGlTransactions.getOffenderBookId()!=null ? objGlTransactions.getOffenderBookId().longValue(): null);
										transObj.setTxnPostingType("DR");
										transObj.setTxnType(lvTxnType);
										transObj.setTxnEntryDesc(lvTxnEntryDesc);
										transObj.setTxnEntryAmount(pBalance!=null ? pBalance.doubleValue() : null);
										transObj.setTxnEntryDate(transDate);
										transObj.setSubAccountType("REG");
										transObj.setDeductionFlag(null);
										transObj.setDeductionType(null);
										transObj.setPayeeCorporateId(null);
										transObj.setPayeePersonId(null);
										transObj.setInfoNumber(null);
										transObj.setSlipPrintedFlag("N");
										transObj.setCreateUserId(commitBean.getCreateUserId());
										liReturn = trustService.insertIntoOffenderTrans(transObj);
										liReturn = otdclinaRepository.updateOffenderTransactionsTransfer(
												lastNameFirstName, txnIdNextVal, pTxnEntrySeq, commitBean.getCreateUserId());
										if (liReturn == 0) {
											liReturn = 16;
											return liReturn;
										}
										//Procedure Call
//										liReturn = otdclinaRepository.processGlTransNew(
//												objGlTransactions.getCaseloadId(), lvTxnType, pBalance, txnIdNextVal,
//												transDate, lvTxnEntryDesc, pTxnEntrySeq, rootOffenderId,
//												objGlTransactions.getOffenderBookId(), pGlSeq, null, "REG", null);
										
										liReturn = trustService.processGlTransNew(objGlTransactions.getCaseloadId(),
												lvTxnType, null, pBalance != null ? pBalance.doubleValue() : null,
												txnIdNextVal, transDate, lvTxnEntryDesc,
												pTxnEntrySeq != null ? pTxnEntrySeq.intValue() : null, "OTDCLINA",
												rootOffenderId != null ? rootOffenderId.intValue() : null,
												objGlTransactions.getOffenderBookId(), "REG", null, null,
												null, null, 0, null, commitBean.getCreateUserId());
										if (liReturn == 0) {
											liReturn = 17;
											return liReturn;
										}
										Integer returnZeroAmount = ZeroAmount(txnIdNextVal, pTxnEntrySeq,
												objGlTransactions.getCaseloadId(), rootOffenderId, "REG", commitBean.getCreateUserId());
										if (returnZeroAmount == 1) {
											liReturn = 18;
											return liReturn;
										}
										BigDecimal sumBalance = otdclinaRepository.sumBalanceTemp(rootOffenderId, commitBean.getCreateUserId());
										Integer lvBalances = otdclinaRepository
												.updateOffenderTrustAccountsBalances(sumBalance, rootOffenderId, commitBean.getCreateUserId());
										Double gTotalBalance = Double
												.valueOf(objGlTransactions.getTotalAmount().toString());
										Double pBalanceTemp = Double.valueOf(pBalance.toString());
										pTotalBalanceTemp = gTotalBalance - pBalanceTemp;
										cgnbtTotalCountTemp = pTotalBalanceTemp;
										Integer closeAccountData = closeAccount(txnIdNextVal, pTxnEntrySeq,
												objGlTransactions.getCaseloadId(), objGlTransactions.getOffenderId(),
												rootOffenderId, commitBean.getCreateUserId());
										if (closeAccountData == 1) {
											liReturn = 4;
											return liReturn;
										}
										lvRecordCleared = "Y";
										lvTransactionMonitor = lvTransactionMonitor + 1;
										cgnbtTotalCountTemp = Double.valueOf(lvTransactionMonitor.toString());
									}
								}
							} else {
								liReturn = 19;
								lvNoErrors = lvNoErrors + 1;
								return liReturn;
							}
						}
						if (lvNoErrors >= 1) {
							liReturn = 7;
							return liReturn;
						}
					}
					if ("Y".equals(lvRecordCleared)) {
						offTrstAcntType.setAgyLocId(objGlTransactions.getSealFlag());
						offTrstAcntType.setCaseloadId(objGlTransactions.getCaseloadId());
						offTrstAcntType.setSessionId(objGlTransactions.getSessionIdTemp());
						offTrstAcntType.setCreateDateTime(objGlTransactions.getCreateDateTimeTemp());
						offTrstAcntType.setModifyDateTime(objGlTransactions.getModifyDateTimeTemp());
						offTrstAcntType.setLastName(objGlTransactions.getLastNameTemp());
						offTrstAcntType.setSealFlag(lvConfirmAll);
						//Procedure call
						//pTotalBalance = otdclinaRepository.offTracPopulateTempTable(offTrstAcntType);
						pTotalBalance = otdclinaPkgService.populateTable(offTrstAcntType, commitBean.getCreateUserId());
						if (pTotalBalance != null) {
							liReturn = 8;
						}
					}
				}
				if ("Y".equals(lvConfirmAll)) {
//					cgnbtTotalCount = Integer.valueOf(String.valueOf(pTotalBalance));
				} else {
					cgnbtTotalCount = 0;
				}
			}
		}
		return liReturn;
	}

	public BigDecimal sumBalanceOffenderSubAccounts(final Long offenderId, String userId) {
		return otdclinaRepository.sumBalanceOffenderSubAccounts(offenderId, userId);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * 
	 */
	public Integer closeAccount(final Integer txnIdNextVal, final BigDecimal pTxnEntrySeq, final String caseloadId,
			final Long offenderId, final BigDecimal rootOffenderId,String userId) {
		Integer returnData = null;
		BigDecimal lvRootOffenderId = otdclinaRepository.closeAccount(offenderId);
		Integer PTxnNum = otdclinaRepository.closeAccountRootOffIdCur();
		Integer returnZeroAmount = ZeroAmount(PTxnNum, pTxnEntrySeq, caseloadId, lvRootOffenderId, "REG", userId);
		if (returnZeroAmount == 1) {
			returnZeroAmount = 1;
			return returnData;
		}
		returnZeroAmount = 2;
		return returnZeroAmount;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * 
	 */
	public Integer ZeroAmount(final Integer PTxnNum, final BigDecimal pTxnEntrySeq, final String caseloadId,
			final BigDecimal lvRootOffenderId, final String pSubAcType, String userId) {
		Integer accountCodesList = null;
		Integer PTrustAccountCode = otdclinaRepository.accountCodeData(pSubAcType, userId);
		accountCodesList = otdclinaRepository.updateOffenderSubAccounts(PTxnNum, pTxnEntrySeq, caseloadId,
				lvRootOffenderId, PTrustAccountCode, userId);
		if (accountCodesList == 0) {
			accountCodesList = 1;
			return accountCodesList;
		}
		accountCodesList = 2;
		return accountCodesList;
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

}