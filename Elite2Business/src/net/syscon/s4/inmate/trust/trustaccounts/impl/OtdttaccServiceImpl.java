package net.syscon.s4.inmate.trust.trustaccounts.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.Caseloads;
import net.syscon.s4.common.beans.OffenderTransactionsCommitBean;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.SystemProfilesCommitBean;
import net.syscon.s4.common.beans.TransactionTypes;
import net.syscon.s4.core.EliteDateService;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.BankChequeData;
import net.syscon.s4.im.beans.Corporates;
import net.syscon.s4.im.beans.OffenderSubAcShadows;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.im.beans.VTrustHeader;
import net.syscon.s4.inmate.beans.AccountPeriods;
import net.syscon.s4.inmate.beans.AccountPeriodsCommitBean;
import net.syscon.s4.inmate.beans.CaseloadTransactionTypes;
import net.syscon.s4.inmate.beans.CaseloadTransactionTypesCommitBean;
import net.syscon.s4.inmate.beans.OffenderSubAccounts;
import net.syscon.s4.inmate.trust.trustaccounts.OtdttaccRepository;
import net.syscon.s4.inmate.trust.trustaccounts.OtdttaccService;
import net.syscon.s4.pkgs.deductions.DeductionsService;
import net.syscon.s4.pkgs.trust.TrustService;

/**
 * Class OtdttaccServiceImpl
 */
@Service
public class OtdttaccServiceImpl extends BaseBusiness implements OtdttaccService {

	@Autowired
	private OtdttaccRepository otdttaccRepository;
	
	@Autowired
	 private EliteDateService eliteDateService;
	
	@Autowired
	TrustService trustService;
	
	@Autowired
	DeductionsService deductionsService;
	
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OtdttaccServiceImpl.class.getName());

	/**
	 * Creates new OtdttaccServiceImpl class Object
	 */
	public OtdttaccServiceImpl() {
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * 
	 */
	public TransactionTypes CgfkchkCsldTtCsldTyTxn(final TransactionTypes paramBean) {
		final TransactionTypes transactionTypes = otdttaccRepository.cgfkchkCsldTtCsldTyTxn(paramBean);
		return transactionTypes;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * 
	 */
	public Caseloads CgfkchkCsldTtCsldTtCsld(final Caseloads paramBean) {
		final Caseloads caseloads = otdttaccRepository.cgfkchkCsldTtCsldTtCsld(paramBean);
		return caseloads;
	}


	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * 
	 */
	public List<CaseloadTransactionTypes> csldTtExecuteQuery(final CaseloadTransactionTypes searchRecord) {
		return otdttaccRepository.csldTtExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstCSLD_TT
	 *
	 * 
	 */
	@Transactional
	public Integer csldTtCommit(final CaseloadTransactionTypesCommitBean commitBean) {
		int liReturn = 0;
		// insertRecords
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			liReturn = otdttaccRepository.csldTtInsertCaseloadTransactionTypes(commitBean.getInsertList());
		}
		// deleteRecords
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().forEach(bo -> {bo.setModifyUserId(commitBean.getCreateUserId());});
			liReturn = otdttaccRepository.csldTtDeleteCaseloadTransactionTypes(commitBean.getDeleteList());
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
	public List<AccountPeriods> acPrdExecuteQuery(final AccountPeriods searchRecord) {
		return otdttaccRepository.acPrdExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstAC_PRD
	 *
	 * 
	 */
	@Transactional
	public Integer acPrdCommit(final AccountPeriodsCommitBean commitBean) {
		int liReturn = 0;
		// insertRecords
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			liReturn = otdttaccRepository.acPrdInsertAccountPeriods(commitBean.getInsertList());
		}
		// updateRecords
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			liReturn = otdttaccRepository.acPrdUpdateAccountPeriods(commitBean.getUpdateList());
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
	public List<OffenderTransactions> offTxnExecuteQuery(final OffenderTransactions searchRecord) {
		return otdttaccRepository.offTxnExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFF_TXN
	 *
	 * 
	 */
	@Transactional
	public List<OffenderTransactions> offTxnCommit(final OffenderTransactionsCommitBean commitBean) {
		List<OffenderTransactions> liReturn = new ArrayList<OffenderTransactions>();
		// insertRecords
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for(OffenderTransactions obj: commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
			}
			liReturn = offTxnInsertOffenderTransactions(commitBean.getInsertList());
		}
		return liReturn;
	}

	private List<OffenderTransactions> offTxnInsertOffenderTransactions(List<OffenderTransactions> insertList) {
		Integer txnId = otdttaccRepository.getNextVal();
		List<OffenderTransactions> requestData = new ArrayList<>();
		List<OffenderTransactions> requestObj = new ArrayList<>();
		Double txnAmount = 0.0;
		Double checkAmnt = 0.0;
		Integer seq = 0;
		String desc = " TO CASELOAD: ";
		String txnDesc = null;
		final OffenderTransactions tempBean = new OffenderTransactions();
		try {
			for (final OffenderTransactions obj : insertList) {
				txnAmount = obj.getTxnEntryAmount();
				requestData.clear();
				requestObj.clear();
				seq++;
				obj.setTxnId(txnId);
				obj.setModifyUserId(obj.getCreateUserId());
				List<VTrustHeader> rootOffenders = otdttaccRepository.getRootOffenderIds(obj.getCaseloadId(),
						obj.getOffenderIdDisplay());
				if (rootOffenders != null) {
					obj.setOffenderId(Long.valueOf(rootOffenders.get(0).getRootOffenderId().toString()));
				}
				if (obj.getTxnEntrySeq() == null) {
					obj.setTxnEntrySeq(0);
				}
				obj.setTxnEntrySeq(seq);
				final List<OffenderSubAcShadows> txnAmtList = otdttaccRepository
						.gettxnAmtandTxnsubAc(obj.getOffenderId(), obj.getCaseloadId());
				if (txnAmtList.size() > 0) {
					for (final OffenderSubAcShadows shadow : txnAmtList) {
						obj.setTxnEntryAmount(Double.valueOf(shadow.getTransferedAmount().toString()));
						obj.setPreWithholdAmount(Double.valueOf(shadow.getTransferedAmount().toString()));
						obj.setSubAccountType(shadow.getTrustAccountCode());

					}
				} else {
					obj.setTxnEntryAmount(new Double(0));
					obj.setSubAccountType("REG");
					obj.setTxnEntryDate(trunc(eliteDateService.getDBTime()));

				}
				requestObj.add(obj);
				try {
					final Integer insretResult = otdttaccRepository.offTxnInsertOffenderTransactions(requestObj);

					if (insretResult != null) {
						//otdttaccRepository.updateOffenderBalance(requestObj);
						//Procedure call
						trustService.updateOffenderBalance(obj, obj.getCreateUserId());
												
						//Integer glNew = otdttaccRepository.processGlTransNew(requestObj);
						Integer glNew = trustService.processGlTransNew(obj.getCaseloadId(),obj.getTxnType(), null, obj.getTxnEntryAmount(), 
								obj.getTxnId(), obj.getTxnEntryDate(), obj.getTxnDescription(), obj.getTxnEntrySeq(), "OTDTTACC", obj.getOffenderId().intValue(), 
								obj.getOffenderBookId(), obj.getSubAccountType(), null, null, obj.getPayeeCorporateId(), obj.getPayeeNameText(), 0, null, obj.getCreateUserId());
						if (glNew != null) {

							for (OffenderTransactions off : requestObj) {
								seq++;
								if ("TOS".equals(off.getTxnType())) {
									final String txnEntryDesc = off.getTxnEntryDesc() + desc
											+ off.getTransferCaseloadId();
									off.setTxnEntryDesc(txnEntryDesc);
									off.setTxnEntrySeq(seq);
									off.setTxnType("CR");
									requestData.clear();
									requestData.add(off);
									final Integer tosResult = otdttaccRepository
											.offTxnTorInsertOffenderTransactions(requestData);
									//final Integer glNewTor = otdttaccRepository.processGlTransNew(requestData);
									//Procedure call
									Integer glNewTor = trustService.processGlTransNew(obj.getCaseloadId(),obj.getTxnType(), null, obj.getTxnEntryAmount(), 
											obj.getTxnId(), obj.getTxnEntryDate(), obj.getTxnDescription(), obj.getTxnEntrySeq(), "OTDTTACC", obj.getOffenderId().intValue(), 
											obj.getOffenderBookId(), obj.getSubAccountType(), null, null, obj.getPayeeCorporateId(), obj.getPayeeNameText(), 0, null, obj.getCreateUserId());
									
									if (glNewTor != 1 || tosResult != 1) {
										logger.error("Offender Transactions and GL Transactions insert fail : ");
										throw new RuntimeException(
												"Offender Transactions and GL Transactions insert fail : ");
									}
								}
								// checkAmnt = checkAmnt + off.getTxnEntryAmount();
								checkAmnt = checkAmnt + txnAmount;
								off.setCheckAmnt(checkAmnt);
								List<OffenderSubAcShadows> subac = otdttaccRepository.getTrustacTransAmt(
										off.getOffenderId(), off.getCaseloadId(), off.getSubAccountType());
								if (subac.size() <= 0) {
									off.setTxnEntryAmount(0.0);
									off.setTxnPostingType(null);

								} else {
									for (final OffenderSubAcShadows ofshadow : subac) {
										final String txnEntryDesc = off.getTxnEntryDesc() + desc
												+ off.getTransferCaseloadId();
										off.setTxnEntryDesc(txnEntryDesc);
										off.setTxnEntrySeq(++seq);
										off.setTxnEntryAmount(
												Double.valueOf(ofshadow.getTransferedAmount().toString()));
										off.setSubAccountType(ofshadow.getTrustAccountCode());
										off.setPreWithholdAmount(
												Double.valueOf(ofshadow.getTransferedAmount().toString()));

										requestData.clear();
										requestData.add(off);
										try {
											Integer checkamtrslt = otdttaccRepository
													.offTxnCheckAmtInsertOffenderTransactions(requestData);
											//otdttaccRepository.updateOffenderBalance(requestData);
											//Procedure call
											trustService.updateOffenderBalance(obj, obj.getCreateUserId());
											if (checkamtrslt != null) {
												//Integer glNewCheckamt = otdttaccRepository.processGlTransNewforcheckAmt(requestData);
												//Procedure call
												Integer glNewCheckamt = trustService.processGlTransNew(obj.getCaseloadId(),obj.getTxnType(), null, obj.getTxnEntryAmount(), 
														obj.getTxnId(), obj.getTxnEntryDate(), obj.getTxnEntryDesc(), obj.getTxnEntrySeq(), "OTDTTACC", obj.getOffenderId().intValue(), 
														obj.getOffenderBookId(), obj.getSubAccountType(), null, 0, obj.getPayeeCorporateId(), obj.getPayeeNameText(), 0, 0, obj.getCreateUserId());
											
											}
										} catch (Exception e) {
											logger.error(
													"Offender Transaction Check Amount and GL Balances update fail :");
											throw new RuntimeException(
													"Offender Transaction Check Amount and GL Balances update fail : ");
										}
									}
									if ("TOS".equals(off.getTxnType())) {
										try {
											Integer checkAmtTos = otdttaccRepository
													.offTxnTorInsertOffenderTransactions(requestData);
											//Integer glNewTor = otdttaccRepository.processGlTransNew(requestData);
											//Procedure call
											Integer glNewTor = trustService.processGlTransNew(obj.getCaseloadId(),obj.getTxnType(), null, obj.getTxnEntryAmount(), 
													obj.getTxnId(), obj.getTxnEntryDate(), obj.getTxnDescription(), obj.getTxnEntrySeq(), "OTDTTACC", obj.getOffenderId().intValue(), 
													obj.getOffenderBookId(), obj.getSubAccountType(), null, null, obj.getPayeeCorporateId(), obj.getPayeeNameText(), 0, null, obj.getCreateUserId());
											
											
										} catch (Exception e) {
											logger.error("Offender Transaction TOR and Process GL Trans insert fail :");
											throw new RuntimeException(
													"Offender Transaction TOR and Process GL Trans insert fail :");
										}
									}

								}
								requestData.clear();
								requestData.add(off);
								final Integer deleteOffsubAcshadows = otdttaccRepository
										.deleteoffendersubAcShadows(requestData);
								//otdttaccRepository.deductionGetAcAndSetIndDate(off.getOffenderId(),off.getCaseloadId());
								//Procedure call
								deductionsService.getAcAndSetIndDate(off.getOffenderId(),off.getCaseloadId(), obj.getCreateUserId());
								off = onCommitOfftxn(off);
							}

						}

					}

					if (insretResult != 1) {
						final OffenderTransactions offTransError = new OffenderTransactions();
						offTransError.setErrorMessage("Error");
						List<OffenderTransactions> offTransErrorList = new ArrayList<>();
						offTransErrorList.add(offTransError);
						return offTransErrorList;
					}
				} catch (Exception e) {
					OffenderTransactions offData = insertList.get(0);
					logger.error("Offender Transactions insert fail : ");
					throw new RuntimeException("Main method : ");
				}
			}
		} catch (Exception e) {
			logger.error("Main method : ", e);
			throw new RuntimeException("Main method : ");
		}

		return insertList;
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
	
	private OffenderTransactions onCommitOfftxn(final OffenderTransactions tempBean) {
		 String chqExistFlag =null;
		final String checkPro =otdttaccRepository.getcheckProductionflag(tempBean);
		if(checkPro != null && "TOR".equals(tempBean.getTxnType() )){
			if(tempBean.getCheckAmnt() > 0 && "Y".equals(checkPro)){
				final String getOne= otdttaccRepository.checkChkRrecCur(tempBean);
				if(getOne !=null){
					chqExistFlag ="Y";
					otdttaccRepository.updatecheckAmt(tempBean);
					
				} else {
					chqExistFlag ="N";
					//otdttaccRepository.insertIntoChequeData(tempBean);
					//Procedure call
					BankChequeData obj = new BankChequeData();
					obj.setCaseloadId(tempBean.getCaseloadId());
					obj.setTxnId(tempBean.getTxnId().longValue());
					obj.setTxnEntryAmount(BigDecimal.valueOf(tempBean.getTxnEntryAmount()));
					obj.setChequeFlag("O");
					obj.setStartTxnId(BigDecimal.valueOf(tempBean.getTxnId()));
					obj.setEndTxnId(BigDecimal.valueOf(tempBean.getTxnId()));
					obj.setPayeePersonId(null);
					obj.setPayeeCorporateId(BigDecimal.valueOf(tempBean.getPayeeCorporateId()));
					obj.setCorporateName(tempBean.getCorporateName());
					obj.setOffenderPayee(null);
					obj.setSingleEntryFlag("Y");
					obj.setBankAccountCode(null);
					obj.setModuleName("OTDTTACC");
					obj.setTxnType(tempBean.getTxnType());
					trustService.insertIntoChequeData(obj, tempBean.getCreateUserId());
				}
			}
			final String trusrtTrans=otdttaccRepository.trustTranExistCur(tempBean);
			if(trusrtTrans !=null){		
				otdttaccRepository.trustTranupdateOffendertrustTrans(tempBean);
				
			} else {
				otdttaccRepository.trustTranInsertOffendertrustTrans(tempBean);
			}
		}
		return tempBean;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * 
	 */
	public List<SystemProfiles> sysPflExecuteQuery(final SystemProfiles searchRecord) {
		return otdttaccRepository.sysPflExecuteQuery(searchRecord);

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
		return liReturn;
	}

	/**
	 * This method is used to execute a record group
	 *
	 *
	 */
	public List<TransactionTypes> cgfkCsldTtTxnTypeRecordGroup(final String caseLoadId) {
		return otdttaccRepository.cgfkCsldTtTxnTypeRecordGroup(caseLoadId);

	}


	/**
	 * This method is used to execute a record group
	 *
	 *
	 */
	public List<Caseloads> cgfkCsldTtCaseloadIdRecordGroup(final String caseLoadId) {
		return otdttaccRepository.cgfkCsldTtCaseloadIdRecordGroup(caseLoadId);

	}

	
	public String getHoldClearFlag(final String caseloadId, final Long offenderId, final String casaeloadType) {
		return otdttaccRepository.getHoldClearFlag(caseloadId, offenderId, casaeloadType);
	}

	public List<OffenderSubAccounts> getHoldBal(final String caseloadId, final Long offenderId,
			final String txnType, final String userName) {
		String accept = null;
		final List<OffenderSubAccounts>  returnList= otdttaccRepository.getHoldBal(caseloadId, offenderId, txnType);
		final List<OffenderSubAcShadows> selectList = otdttaccRepository.selectOffenderAcntShadows(caseloadId,offenderId);
		if(selectList.size() <= 0){
			accept = "Y";			
		}
		for(final OffenderSubAccounts obj:returnList){
			obj.setOffenderId(offenderId);
			obj.setCaseloadId(caseloadId);	
			obj.setCreateUserId(userName);
			if("Y".equals(accept)) {			 
				final Integer val = otdttaccRepository.inserOffenderAcntShadows(obj);
			}			
		}
		return returnList;
	}

	
	public String getDuplicateOffenders(final Long offenderId) {
		return otdttaccRepository.getDuplicateOffenders(offenderId);
	}

	
	public List<Corporates> getCorporateidNames(final String toCaseload) {
		return otdttaccRepository.getCorporateidNames(toCaseload);
	}

	
	public Object checkTxnType(final String toCaseload, final String txnType, final String caseloadId) {
		return otdttaccRepository.checkTxnType(toCaseload, txnType, caseloadId);
	}

	
	public OffenderTransactions getRootOffenderId(final String casaeloadType, final String offenderIdDisplay, final String userName) {
		final OffenderTransactions returnObject = otdttaccRepository.getRootOffenderId(casaeloadType, offenderIdDisplay);
		if (returnObject.getRootOffenderId()!= null) {
			final OffenderTransactions returnObjbookId= otdttaccRepository.getBookIdandStatusDisplay(
					returnObject.getRootOffenderId(),casaeloadType, userName);
			if (returnObjbookId.getOffenderBookId()!=null) {
				returnObject.setOffenderBookId(returnObjbookId.getOffenderBookId());
				returnObject.setStatusDisplay(returnObjbookId.getStatusDisplay());
				
			}
			
		}
		return returnObject;
	}

	
	public List<OffenderTransactions> whenNewBlockInstanceRetrive(final Long  pstartDate,final  Long  pendDate, final String currentCaseload,
			final String toCaseload,final String txnType, final String caseloadType) {
		Date startDate = null;
		if (pstartDate != null) {
			startDate = new Date(pstartDate);
		}
		Date endDate = null;
		if (pendDate != null) {
			endDate = new Date(pendDate);
		}
		 List<OffenderTransactions> returnList=null;
		 List<OffenderSubAccounts> balances=null;
		 Double txnAmt=0.0;
		returnList= otdttaccRepository.whenNewBlockInstanceRetrive(startDate,endDate,currentCaseload,toCaseload,txnType);
		for(final OffenderTransactions obj:returnList){
			balances = otdttaccRepository.getHoldBal(currentCaseload, obj.getOffenderId(),txnType);
			final String clearFlag = otdttaccRepository.getHoldClearFlag(currentCaseload, obj.getOffenderId(), caseloadType);
			if ("A".equals(clearFlag)) {
				obj.setHoldClearFlag("Y");
				obj.setCaseloadId(currentCaseload);
				} else {
					obj.setHoldClearFlag("N");
					obj.setCaseloadId(currentCaseload);
				}
			for (final OffenderSubAccounts objSub: balances){
				if("REG".equals(objSub.getSubAccountType())){
				 txnAmt = objSub.getBalance();
				 obj.setTxnHoldEntryAmount(Double.valueOf(objSub.getHoldBalance().toString()));

				}
				if("SAV".equals(objSub.getSubAccountType())){
				Double totAmt= txnAmt+ objSub.getBalance();
					
				obj.setTxnEntryAmount(totAmt);
				}
			}
			
		}
		return returnList;
	}

	
	public Integer deleteOffacShads(final String caseloadId, final Long offenderId, String modifyUserId) {
		return otdttaccRepository.deleteOffacShads(caseloadId, offenderId, modifyUserId);
	}


}