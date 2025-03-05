package net.syscon.s4.inmate.trust.generalledger.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
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
import org.springframework.transaction.support.TransactionOperations;

import net.syscon.s4.cf.deductions.beans.OffFeeBillTransactions;
import net.syscon.s4.cf.deductions.beans.OffFeeBills;
import net.syscon.s4.cf.offendertransactions.OcdbreciRepository;
import net.syscon.s4.cf.offendertransactions.OcdreverService;
import net.syscon.s4.common.beans.OtddisbuProcessTransactionsBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.SystemProfilesCommitBean;
import net.syscon.s4.core.EliteDateService;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.OffenderCreditPriorPayments;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.inmate.beans.GlTransactions;
import net.syscon.s4.inmate.beans.GlTransactionsCommitBean;
import net.syscon.s4.inmate.beans.OffenderBeneficiaries;
import net.syscon.s4.inmate.beans.OffenderDeductions;
import net.syscon.s4.inmate.beans.OffenderSubAccounts;
import net.syscon.s4.inmate.trust.generalledger.OtdglirtRepository;
import net.syscon.s4.inmate.trust.generalledger.OtdglirtService;
import net.syscon.s4.pkgs.deductions.DeductionsService;
import net.syscon.s4.pkgs.financial.FinancialService;
import net.syscon.s4.pkgs.trust.TrustService;
import net.syscon.s4.triggers.GlTransactionsT1Service;
import net.syscon.s4.triggers.GlTransactionsT2Service;
import net.syscon.s4.triggers.GlTransactionsTjnService;
import net.syscon.s4.triggers.OffFeeBillTransactionsT1Service;
import net.syscon.s4.triggers.OffFeeBillTransactionsT2Service;
import net.syscon.s4.triggers.OffenderBeneficiariesT1Service;
import net.syscon.s4.triggers.OffenderBeneficiariesT2Service;
import net.syscon.s4.triggers.OffenderDeductionsHty;
import net.syscon.s4.triggers.OffenderDeductionsT2Service;
import net.syscon.s4.triggers.OffenderDeductionsThtyService;
import net.syscon.s4.triggers.OffenderDeductionsTjnService;
import net.syscon.s4.triggers.TrustAudits;

/**
 * Class OtdglirtServiceImpl
 */
@Service
public class OtdglirtServiceImpl extends BaseBusiness implements OtdglirtService {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OtdglirtServiceImpl.class.getName());

	@Autowired
	private OtdglirtRepository otdglirtRepository;

	@Autowired
	private EliteDateService eliteDateService;

	@Autowired
	private OcdbreciRepository ocdbreciRepository;
	
	@Autowired
	private OcdreverService ocdreverService;
	
	@Autowired 
	private TrustService trustService;
	
	@Autowired 
	private GlTransactionsT1Service gltransactionst1service;
	
	@Autowired
	private GlTransactionsT2Service gltransactionst2service;
	
	@Autowired
	private GlTransactionsTjnService gltransactionstjnservice;
	
	@Autowired
	private FinancialService financialservice;
	
	@Autowired
	private OffenderDeductionsTjnService offenderdeductionstjnservice;
	
	@Autowired
	private OffenderDeductionsThtyService offenderdeductionsthtyservice;
	
	@Autowired
	private OffenderDeductionsT2Service offenderDeductionsT2Service;
	
	@Autowired
	private OffenderBeneficiariesT1Service offenderbeneficiariest1service;
	
	@Autowired
	private OffenderBeneficiariesT2Service offenderbeneficiariest2service;
	
	@Autowired
	private DeductionsService deductionService;
	
	@Autowired
	private OffFeeBillTransactionsT1Service offfeebilltransactionst1service;
	
	@Autowired
	private OffFeeBillTransactionsT2Service offfeebilltransactionst2service;
	
	final private String AR ="AR";

	/**
	 * Creates new OtdglirtServiceImpl class Object
	 */
	public OtdglirtServiceImpl() {
		// OtdglirtServiceImpl
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<GlTransactions> glTxnExecuteQuery(final GlTransactions searchRecord) {
		try {
			if(searchRecord.getNbtOffenderIdDisplay()!=null){
				 searchRecord.setOffenderId(new BigDecimal(searchRecord.getNbtOffenderIdDisplay()));
				}
		} catch(NumberFormatException e){
			
			searchRecord.setOffenderId(BigDecimal.valueOf(0));
		}
		
		if (searchRecord != null && searchRecord.getReceiptNumber() != null) {
			final GlTransactions data = otdglirtRepository.receiptNumberQueryReturn(searchRecord);
			if (data != null && data.getTxnId() != null && data.getTxnEntrySeq() != null) {
				searchRecord.setTxnId(data.getTxnId());
				searchRecord.setTxnEntrySeq(data.getTxnEntrySeq());
				searchRecord.setReceiptNumber(null);
			}
		}
		final List<GlTransactions> returnList = otdglirtRepository.glTxnExecuteQuery(searchRecord);
		for (final GlTransactions glTransactions : returnList) {
			if (glTransactions.getTxnEntryDesc() != null) {
				glTransactions.setDescription(glTransactions.getTxnEntryDesc());
			}
			if (glTransactions.getTxnId() != null && glTransactions.getTxnEntrySeq() != null) {
				glTransactions.setTxnEntryDescOne(
						"REV GL#" + glTransactions.getTxnId() + " SEQ#" + glTransactions.getTxnEntrySeq());
			}
			if ("Y".equals(glTransactions.getTxnReversedFlag())) {
				glTransactions.setTxnReversedFlagOne("*");
			} else if ("P".equals(glTransactions.getTxnReversedFlag())) {
				glTransactions.setTxnReversedFlagOne("P");
			}
			if ("DR".equals(glTransactions.getTxnPostUsage())) {
				glTransactions.setAccountCodeTwo(glTransactions.getAccountCode());
			} else {
				glTransactions.setAccountCodeOne(glTransactions.getAccountCode());
			}
		}
		return returnList;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<GlTransactions> glTxnOneExecuteQuery(final GlTransactions searchRecord) {
		final List<GlTransactions> returnList = otdglirtRepository.glTxnOneExecuteQuery(searchRecord);
		for (final GlTransactions glTransactions : returnList) {
			if (glTransactions.getAccountCode() != null) {
				final String accountName = otdglirtRepository.cgfkchkGlTxnGlTxnAcCod(glTransactions.getAccountCode(),searchRecord.getCreateUserId());
				if (accountName != null) {
					glTransactions.setDescription(accountName);
				}
			}
		}
		return returnList;
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstGL_TXN
	 *
	 * @throws SQLException
	 */
	@Transactional(rollbackFor = Exception.class)
	public Integer glTxnCommit(final GlTransactionsCommitBean commitBean) {
		int liReturn = 0;
		GlTransactions objTrans = null;
		String createCaseload = null;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			objTrans = new GlTransactions();
			try {
				for (final GlTransactions objGlTransactions : commitBean.getInsertList()) {
					Integer adjustmentAmt = 0;
					Integer writeoffTxntype = 0;
					String txnFlag = null;
					String crobTrueFlag = null;
					BigDecimal glDeductionCur = null;
					Double revAmt = null;
					String tempInfoNumber = null;
					String dedType = null;
					Long tempOffId = null;
					String dedFlag = null;
					String dedCat = null;
					Long lDeductionId = null;
					Double paidAmt = null;
					Double crobAmt = null;
					Boolean pHoldTransFlag = null;
					String returnFlag = null;
					String proceedFlag = "Y";
					Long glSeq = null;
					objGlTransactions.setCreateUserId(commitBean.getCreateUserId());
					objGlTransactions.setModifyUserId(commitBean.getCreateUserId());
					objTrans.setCaseloadId(objGlTransactions.getCaseloadId());
					objTrans.setTxnType(objGlTransactions.getTxnType());
					objTrans.setTxnId(objGlTransactions.getTxnId());
					objTrans.setTxnEntrySeq(objGlTransactions.getTxnEntrySeq());
					objTrans.setTxnEntryDate(objGlTransactions.getTxnEntryDate());
					objTrans.setTxnEntryDesc(objGlTransactions.getTxnEntryDescOne());
					objTrans.setTxnEntryAmount(objGlTransactions.getTxnEntryAmount());
					objTrans.setDeductionId(objGlTransactions.getDeductionId());
					objTrans.setOffenderId(objGlTransactions.getOffenderId());
					objTrans.setCreateUserId(commitBean.getCreateUserId());
					objTrans.setModifyUserId(commitBean.getCreateUserId());
					if (objGlTransactions.getDeductionId() != null) {
						BigDecimal lvAdjustmentAmt = otdglirtRepository
								.lvAdjustmentAmtData(objGlTransactions.getDeductionId());
						if (lvAdjustmentAmt == null) {
							adjustmentAmt = 0;
						} else {
							adjustmentAmt = Integer.valueOf(lvAdjustmentAmt.toString());
						}
					}
					if (objGlTransactions.getTxnType() != null) {
						logger.info(this.getClass().getName()+" transactionOperationsModule method call");
						Integer lvWriteoffTxntype = otdglirtRepository
								.transactionOperationsModule(objGlTransactions.getTxnType(),objGlTransactions.getCreateUserId());
						if (lvWriteoffTxntype == 0) {
							writeoffTxntype = 0;
						} else {
							writeoffTxntype = lvWriteoffTxntype;
						}
					}
					final String txnTypeTemp = objGlTransactions.getTxnType();
					logger.info(this.getClass().getName()+" txnReversedFlagData method call");
					List<GlTransactions> glTransactionsFlag = otdglirtRepository
							.txnReversedFlagData(objGlTransactions.getTxnId(), objGlTransactions.getTxnEntrySeq());
					if (glTransactionsFlag.size() > 0) {
						for (final GlTransactions glTransactions : glTransactionsFlag) {
							txnFlag = glTransactions.getTxnReversedFlag();
						}
					} else if (glTransactionsFlag.size() <= 0) {
						txnFlag = "Y";
					} else {
						txnFlag = "L";
					}
					if ("Y".equals(txnFlag)) {
						liReturn = 21;
						return liReturn;
					} else if ("L".equals(txnFlag)) {
						liReturn = 2;
						return liReturn;
					} else if (adjustmentAmt > 0 && !("DED".equals(txnTypeTemp) || "DEDC".equals(txnTypeTemp))
							&& writeoffTxntype == 0) {
						liReturn = 3;
						return liReturn;
					}
					logger.info(this.getClass().getName()+" findingDeductionId method call");
					List<GlTransactions> glTransactionsFlagTemp = otdglirtRepository
							.findingDeductionId(objGlTransactions.getTxnId(), objGlTransactions.getTxnEntrySeq());
					if (glTransactionsFlagTemp.size() > 0) {
						for (final GlTransactions glTransactions : glTransactionsFlagTemp) {
							glDeductionCur = glTransactions.getDeductionId();
						}
					}
					logger.info(this.getClass().getName()+" findingDeductionType method call");
					OffenderTransactions offTxns = otdglirtRepository.findingDeductionType(objGlTransactions.getTxnId(),
							objGlTransactions.getTxnEntrySeq());
					if (offTxns != null && offTxns.getDeductionType() != null) {
						revAmt = offTxns.getTxnEntryAmount();
						tempInfoNumber = offTxns.getInfoNumber();
						dedType = offTxns.getDeductionType();
						tempOffId = offTxns.getOffenderId();
						dedFlag = offTxns.getDeductionFlag();
					}
					logger.info(this.getClass().getName()+" findingDeductionCategory method call");
					final String deductionCategory = otdglirtRepository.findingDeductionCategory(dedType);
					if (deductionCategory != null) {
						dedCat = deductionCategory;
					} else {
						dedCat = "@";
						crobTrueFlag = "N";
					}
					logger.info(this.getClass().getName()+" txnUsageData method call");
					final String txnUsageTemp = otdglirtRepository.txnUsageData(objGlTransactions.getTxnType());
					if ("CROB".equals(dedCat) && "R".equals(txnUsageTemp)) {
						String txnUsageTempOne = null;
						logger.info(this.getClass().getName()+" txnUsageData method call");
						OffenderTransactions txnDatatemp = otdglirtRepository
								.txnIdOffIdData(objGlTransactions.getTxnId(), objGlTransactions.getTxnEntrySeq());
						if (txnDatatemp == null) {
							returnFlag = "Y";
						} else {
							logger.info(this.getClass().getName()+" txnUsageData method call");
							txnUsageTempOne = otdglirtRepository.txnUsageData(txnDatatemp.getTxnType());
							if (txnUsageTempOne == null) {
								liReturn = 4;
								returnFlag = "N";
								return liReturn;
							}
						}
						if ("O".equals(txnUsageTempOne) || "D".equals(txnUsageTempOne) || "R".equals(txnUsageTempOne)) {
							logger.info(this.getClass().getName()+" balanceIndDateData method call");
							OffenderSubAccounts offSubAcnts = otdglirtRepository.balanceIndDateData(
									txnDatatemp.getOffenderId(), objGlTransactions.getTxnId(),
									objGlTransactions.getTxnEntrySeq(),objGlTransactions.getCreateUserId());
							if (offSubAcnts != null) {
								final Double val = Double.valueOf(objGlTransactions.getTxnEntryAmount().toString());
								if (offSubAcnts.getBalance() < val) {
									liReturn = 5;
									returnFlag = "N";
									return liReturn;
								} else {
									returnFlag = "Y";
								}
							} else {
								returnFlag = "N";
							}
						} else {
							returnFlag = "Y";
						}
						proceedFlag = returnFlag;
						if ("Y".equals(proceedFlag)) {
							objGlTransactions.setTxnReversedFlag("Y");
							objGlTransactions.setTxnReversedFlagOne("*");
						}
					}
					if (!"Y".equals(objGlTransactions.getTxnReversedFlag())) {
						liReturn = 0;
						return liReturn;
					}
					logger.info(this.getClass().getName()+" txnIdNextValData method call");
					final Integer txnIdNextVal = otdglirtRepository.txnIdNextValData();
					final GlTransactions glTransactions = new GlTransactions();
					final Long valId = Long.valueOf(txnIdNextVal.toString());
					glTransactions.setTxnId(valId);
					if ("Y".equals(proceedFlag)) {
						glSeq = 0L;
						final Date transDate = trunc(eliteDateService.getDBTime());
						final Date transTime = eliteDateService.getDBTime();
						String acntTypePstng = null;
						Double pStngAmount = null;
						Long ofndrSeq = 0L;
						String retFlag = null;
						Integer inForOffDeductions = 0;
						BigDecimal lvrevdedamount = null;
						String revTypeTrnsctnPstng = null;
						String dedCatgTemp = null;
						logger.info(this.getClass().getName()+" glTransactionsNewData method call");
						List<GlTransactions> newGlTransactions = otdglirtRepository.glTransactionsNewData(
								objGlTransactions.getTxnId(), objGlTransactions.getTxnEntrySeq(),
								objGlTransactions.getCaseloadId());
						if (newGlTransactions.size() > 0) {
							for (final GlTransactions glTransactionsNewData : newGlTransactions) {
								glTransactions.setAccountCode(glTransactionsNewData.getAccountCode());
								glTransactions.setOffenderId(glTransactionsNewData.getOffenderId());
								glTransactions.setTxnEntryAmount(glTransactionsNewData.getTxnEntryAmount());
								glTransactions.setTxnPostUsage(glTransactionsNewData.getTxnPostUsage());
								glTransactions.setTxnType(glTransactionsNewData.getTxnType());
								glTransactions.setGlEntrySeq(glTransactionsNewData.getGlEntrySeq());
								glTransactions.setPayeePersonId(glTransactionsNewData.getPayeePersonId());
								glTransactions.setPayeeCorporateId(glTransactionsNewData.getPayeeCorporateId());
								glTransactions.setInfoNumber(glTransactionsNewData.getInfoNumber());
								glTransactions.setDeductionId(glTransactionsNewData.getDeductionId());
								glTransactions.setDeductionType(glTransactionsNewData.getDeductionType());
								glTransactions.setOffTxnType(glTransactionsNewData.getOffTxnType());
								glTransactions.setOffDeductionId(glTransactionsNewData.getOffDeductionId());
								glTransactions.setCreateUserId(commitBean.getCreateUserId());
								glTransactions.setModifyUserId(commitBean.getCreateUserId());
								glTransactions.setCaseloadId(objGlTransactions.getCaseloadId());
								glTransactions.setTxnEntryDate(objGlTransactions.getTxnEntryDate());
								glTransactions.setTxnEntrySeq(objGlTransactions.getTxnEntrySeq());
								
								if (!"Y".equals(proceedFlag)) {
									liReturn = 6;
									return liReturn;
								}
								if ("CR".equals(glTransactions.getTxnPostUsage())) {
									revTypeTrnsctnPstng = "DR";
								} else {
									revTypeTrnsctnPstng = "CR";
								}
								glSeq = glSeq + 1;
								logger.info(this.getClass().getName()+" txnPostingTypeTemp method call");
								String tempTxnPostingType = otdglirtRepository
										.txnPostingTypeTemp(glTransactions.getAccountCode(),commitBean.getCreateUserId());
								if (tempTxnPostingType != null) {
									acntTypePstng = tempTxnPostingType;
								} else {
									liReturn = 7;
									return liReturn;
								}
								final String transDesc = objGlTransactions.getTxnEntryDesc();
								final String vReversalRsn = objGlTransactions.getReversalReasonCode();
								logger.info(this.getClass().getName()+" txnEntryDescTempData method call");
								final String tempDesc = otdglirtRepository.txnEntryDescTempData(transDesc, vReversalRsn);
								TrustAudits trustAudits = new TrustAudits();
								trustAudits.setCreateUserId(commitBean.getCreateUserId());
								trustAudits.setTxnId(objGlTransactions.getTxnId()!=null ?new BigDecimal(objGlTransactions.getTxnId()):null);
								//Trigger call GL_TRANSACTIONS_T1 
								gltransactionst1service.glTransactionsT1Trigger(trustAudits);
								logger.info(this.getClass().getName()+" offTxnInsertGlTransactions method call");
									liReturn = otdglirtRepository.offTxnInsertGlTransactions(glTransactions.getTxnId(), 1L,
										glTransactions.getAccountCode(), glSeq, transDate,
										objGlTransactions.getTxnType(), revTypeTrnsctnPstng,
										objGlTransactions.getCaseloadId(), glTransactions.getOffenderId(),
										objGlTransactions.getOffenderBookId(), glTransactions.getTxnEntryAmount(),
										tempDesc, objGlTransactions.getTxnId(), objGlTransactions.getTxnEntrySeq(),
										glTransactions.getGlEntrySeq(), objGlTransactions.getReversalReasonCode(),
										transTime, glTransactions.getDeductionId(),commitBean.getCreateUserId());
								//Trigger Call GL_TRANSACTIONS_T
								gltransactionst2service.glTransactionsT2Trigger(glTransactions);
							//	glTransactions.setTxnEntryAmount(glTransactionsNewData.getTxnEntryAmount());
								//TRigger Call GL_TRANSACTIONS_TJN
							//	gltransactionstjnservice.glTransactionsTjnTrigger(glTransactions,"INSERTING");
								if (liReturn == 0) {
									liReturn = 8;
									throw new RuntimeException("8");
								}
								if (acntTypePstng.equals(glTransactions.getTxnPostUsage())) {
									pStngAmount = Double.valueOf(glTransactions.getTxnEntryAmount().toString());
								} else {
									final Double valAMnt = Double.valueOf(glTransactions.getTxnEntryAmount().toString());
									pStngAmount = (-1.0) * valAMnt;
								}
								logger.info(this.getClass().getName()+" updateGlBalance method call");
	    						/*otdglirtRepository.updateGlBalance(objGlTransactions.getCaseloadId(),
										glTransactions.getAccountCode(), pStngAmount, transDate);*/
								//No need to migrate updateGlBal procedure because in side of the procedure code is in commented. 
								logger.info(this.getClass().getName()+" checkCaseloadType method call");
								/*
								 * String chkAcntSatus = otdglirtRepository
								 * .checkCaseloadType(objGlTransactions.getCaseloadId());
								 */
								//Procedure call
								String chkAcntSatus = trustService.getCaseloadType(objGlTransactions.getCaseloadId());
								if (glTransactions.getOffenderId() != null
										&& !"WA".equals(glTransactions.getTxnType())) {
									logger.info(this.getClass().getName()+" accountClosedFlagData method call");
									List<GlTransactions> glTransactionsFlagOne = otdglirtRepository
											.accountClosedFlagData(glTransactions.getOffenderId(),commitBean.getCreateUserId());
									if (glTransactionsFlagOne.size() > 0) {
										for (final GlTransactions glTxnsFlag : glTransactionsFlagOne) {
											if ("Y".equals(glTransactions.getTxnReversedFlag())) {
												liReturn = 9;
												throw new RuntimeException("9");
											}
										}
									} else if (glTransactionsFlagOne.size() > 1) {
										liReturn = 10;
										throw new RuntimeException("10");
									} else {
										liReturn = 11;
										throw new RuntimeException("11");
									}
									logger.info(this.getClass().getName()+" subAccountTypeOffTrans method call");
									String subAccountTypeFlag = otdglirtRepository
											.subAccountTypeOffTrans(glTransactions.getAccountCode(),commitBean.getCreateUserId());
									if (subAccountTypeFlag != null && !subAccountTypeFlag.isEmpty()) {
										logger.info(this.getClass().getName()+" updateOffenderTransactions method call");
										liReturn = otdglirtRepository.updateOffenderTransactions(
												objGlTransactions.getTxnId(), objGlTransactions.getTxnEntrySeq(),commitBean.getCreateUserId());
										if (liReturn == 0) {
											liReturn = 12;
											throw new RuntimeException("12");
										}
										ofndrSeq = ofndrSeq + 1;
										try {
										logger.info(this.getClass().getName()+" offTxnInsertOffenderTransactions method call");
										liReturn = otdglirtRepository.offTxnInsertOffenderTransactions(
												glTransactions.getTxnId(), ofndrSeq, glTransactions.getOffenderId(),
												objGlTransactions.getOffenderBookId(), transDate,
												objGlTransactions.getCaseloadId(), glTransactions.getTxnEntryAmount(),
												objGlTransactions.getTxnEntryDesc(), objGlTransactions.getTxnType(),
												acntTypePstng, subAccountTypeFlag, glTransactions.getInfoNumber(),
												objGlTransactions.getTxnId(), objGlTransactions.getTxnEntrySeq(),
												glTransactions.getDeductionType(),commitBean.getCreateUserId());
										logger.info(this.getClass().getName()+" updateOffenderBalance method call");
										/*
										 * otdglirtRepository.updateOffenderBalance(objGlTransactions.getCaseloadId(),
										 * objGlTransactions.getOffenderId(), acntTypePstng, transDate,
										 * glTransactions.getTxnId(), objGlTransactions.getTxnType(),
										 * glTransactions.getTxnEntryAmount(), subAccountTypeFlag);
										 */
										OffenderTransactions obj = new OffenderTransactions();
										 obj.setCaseloadId(objGlTransactions.getCaseloadId());
										 obj.setOffenderId(glTransactions.getOffenderId().longValue());
										 obj.setTxnPostingType(acntTypePstng);
										 obj.setTxnEntryDate(transDate);
										 obj.setTxnId(glTransactions.getTxnId().intValue());
										 obj.setTxnType(objGlTransactions.getTxnType());
										 obj.setTxnEntryAmount(glTransactions.getTxnEntryAmount().doubleValue());
										 if("CR".equals(acntTypePstng)) {
										 obj.setToSubAccountType(subAccountTypeFlag);
										 } else {
											 obj.setSubAccountType(subAccountTypeFlag);
										 }
										//Procedure call
			 							trustService.updateOffenderBalance(obj, commitBean.getCreateUserId());
										logger.info(this.getClass().getName()+" getAcAndSetIndDate method call");
										String getIndDate = deductionService.getAcAndSetIndDate(glTransactions.getOffenderId().longValue(), objGlTransactions.getCaseloadId(), 
												commitBean.getCreateUserId());
//										String getIndDate = otdglirtRepository.getAcAndSetIndDate(
//												objGlTransactions.getCaseloadId(), objGlTransactions.getOffenderId());
										} catch (Exception e) {
											liReturn = 13;
											throw new RuntimeException("13");
										}
										logger.info(this.getClass().getName()+" findCaseloadId method call");
										createCaseload = otdglirtRepository
												.findCaseloadId(glTransactions.getOffDeductionId());
										if (createCaseload != null) {
										} else {
											createCaseload = objGlTransactions.getCaseloadId();
										}
										logger.info(this.getClass().getName()+" subAccountTypeOffTrans method call");
										String subAccountTypeFlagTemp = otdglirtRepository
												.subAccountTypeOffTrans(glTransactions.getAccountCode(),commitBean.getCreateUserId());
										if (subAccountTypeFlagTemp != null) {
											retFlag = "Y";
										} else {
											retFlag = "N";
										}
										logger.info(this.getClass().getName()+" findingDeductionCategoryTemp method call");
										String dedCatg = otdglirtRepository
												.findingDeductionCategoryTemp(glTransactions.getDeductionType());
										if (dedCatg != null) {
											dedCatgTemp = dedCatg;
										}
										if (glTransactions.getDeductionType() != null) {
											if ("Y".equals(retFlag)) {
												inForOffDeductions = inForOffDeductions + 1;
											}
											logger.info(this.getClass().getName()+" paymentAmountData method call");
											OffenderCreditPriorPayments offTrns = otdglirtRepository
													.paymentAmountData(objGlTransactions.getTxnId());
											if (inForOffDeductions == 1) {
												if (offTrns != null && offTrns.getPaymentAmount() != null) {
													lvrevdedamount = new BigDecimal(0);
												} else {
													lvrevdedamount = glTransactions.getTxnEntryAmount();
												}
											}
											if (glTransactions.getOffenderId() != null
													&& glTransactions.getInfoNumber() != null) {
												final Double amntValue = Double.valueOf(lvrevdedamount.toString());
												final Double finalAmt = -1.0 * amntValue;
												if (glTransactions.getDeductionType()
														.equals(glTransactions.getOffTxnType())) {
													logger.info(this.getClass().getName()+" updateOffenderDeductions method call");
													liReturn = otdglirtRepository.updateOffenderDeductions(
															glTransactions.getDeductionType(),
															glTransactions.getOffenderId(), finalAmt,
															objGlTransactions.getOffDeductionId(), createCaseload,commitBean.getCreateUserId());
				
													OffenderDeductions newRec = new OffenderDeductions();
													newRec.setDeductionType(glTransactions.getDeductionType());
													newRec.setOffenderId(glTransactions.getOffenderId()!=null ? glTransactions.getOffenderId().longValue() : null);
													newRec.setOffenderDeductionId(objGlTransactions.getOffDeductionId()!=null ? objGlTransactions.getOffDeductionId().longValue():null);
													newRec.setCreateUserId(commitBean.getCreateUserId());
													newRec.setCaseloadId(createCaseload);
													
													logger.info(this.getClass().getName()+" getOffenderDeductionOldData method call");
													//Old data of OFFENDER_DEDUCTIONS table
													OffenderDeductions oldRec = otdglirtRepository.getOffenderDeductionOldData(objGlTransactions.getOffDeductionId()!=null ?objGlTransactions.getOffDeductionId().longValue():null );
													
													logger.info(this.getClass().getName()+" offenderDeductionsTjn method call");
													//Trigger call OFFENDER_DEDUCTIONS_TJN 
													//offenderdeductionstjnservice.offenderDeductionsTjn(newRec, oldRec,"UPDATE");
													oldRec.setCreateUserId(commitBean.getCreateUserId());
													
													logger.info(this.getClass().getName()+" OffenderDeductionsThtyTrigger method call");
													//Trigger call OFFENDER_DEDUCTIONS_THTY 
													offenderdeductionsthtyservice.OffenderDeductionsThtyTrigger(dataMappingOfOffenderDeductionsHty(oldRec),"UPDATE");
													//Trigger call OFFENDER_DEDUCTIONS_T2 
													logger.info(this.getClass().getName()+" offenderDeductionsT2Trigger method call");
													offenderDeductionsT2Service.offenderDeductionsT2Trigger(newRec, oldRec, "UPDATE");
													
													if (liReturn == 0) {
														liReturn = 14;
														throw new RuntimeException("14");
													}
													
													OffenderBeneficiaries newRecOffBen = new OffenderBeneficiaries();
													newRecOffBen.setOffenderDeductionId(objGlTransactions.getOffDeductionId()!=null ? objGlTransactions.getOffDeductionId().longValue():null);
													newRecOffBen.setTotalAmount(finalAmt!=null ? new BigDecimal(finalAmt):null);
													
													//old data of OFFENDER_BENEFICIARIES table
													logger.info(this.getClass().getName()+" getOldDataOfOffenderBeneficiaries method call");
													OffenderBeneficiaries oldRecOffBen = otdglirtRepository.getOldDataOfOffenderBeneficiaries(objGlTransactions.getOffDeductionId()!=null ? objGlTransactions.getOffDeductionId().longValue():null);
													
													//Trigger call OFFENDER_BENEFICIARIES_T1
													logger.info(this.getClass().getName()+" offenderBeneficiariesT1Trigger method call");
													offenderbeneficiariest1service.offenderBeneficiariesT1Trigger(newRecOffBen, oldRecOffBen);
													
													//Trigger call OFFENDER_BENEFICIARIES_T2
													logger.info(this.getClass().getName()+" offenderBeneficiariesT2Trigger method call");
													offenderbeneficiariest2service.offenderBeneficiariesT2Trigger(oldRecOffBen,"UPDATE");
													
													logger.info(this.getClass().getName()+" updateOffenderBeneficiaries method call");
													liReturn = otdglirtRepository.updateOffenderBeneficiaries(finalAmt,
															objGlTransactions.getOffDeductionId());
													if (liReturn == 0) {
														liReturn = 15;
														throw new RuntimeException("15");
													}
												} else {
													logger.info(this.getClass().getName()+" updateOffenderDeductionsTemp method call");
			  										liReturn = otdglirtRepository.updateOffenderDeductionsTemp(
															glTransactions.getDeductionType(),
															glTransactions.getOffenderId(), finalAmt,
															objGlTransactions.getDeductionId(), createCaseload,commitBean.getCreateUserId());
			  
													//Old data of OFFENDER_DEDUCTIONS table
													OffenderDeductions oldRec = otdglirtRepository.getOffenderDeductionOldData(objGlTransactions.getOffDeductionId()!=null ?objGlTransactions.getOffDeductionId().longValue():null );
				
													OffenderDeductions newRec = new OffenderDeductions();
													newRec.setDeductionType(glTransactions.getDeductionType());
													newRec.setOffenderId(glTransactions.getOffenderId()!=null ? glTransactions.getOffenderId().longValue() : null);
													newRec.setOffenderDeductionId(objGlTransactions.getDeductionId()!=null ? objGlTransactions.getDeductionId().longValue():null);
													newRec.setCreateUserId(commitBean.getCreateUserId());
													newRec.setCaseloadId(createCaseload);
													
			  										logger.info(this.getClass().getName()+" offenderDeductionsTjn method call");
			  										//Trigger call OFFENDER_DEDUCTIONS_TJN 
			  										//offenderdeductionstjnservice.offenderDeductionsTjn(newRec, oldRec,"UPDATE");
			  										
													logger.info(this.getClass().getName()+" OffenderDeductionsThtyTrigger method call");
													//Trigger call OFFENDER_DEDUCTIONS_THTY 
													offenderdeductionsthtyservice.OffenderDeductionsThtyTrigger(dataMappingOfOffenderDeductionsHty(oldRec),"UPDATE");
			  										
													//Trigger call OFFENDER_DEDUCTIONS_T2 
													logger.info(this.getClass().getName()+" offenderDeductionsT2Trigger method call");
													offenderDeductionsT2Service.offenderDeductionsT2Trigger(newRec, oldRec, "UPDATE");
													if (liReturn == 0) {
														liReturn = 16;
														throw new RuntimeException("16");
													}
												}
											}
											if (offTrns != null && offTrns.getPaymentAmount() != null) {
			  									logger.info(this.getClass().getName()+" insertOffenderCreditPriorPayments method call");
												liReturn = otdglirtRepository.insertOffenderCreditPriorPayments(
														glTransactions.getTxnId(), offTrns.getCaseloadId(),
														glTransactions.getOffenderId(), lvrevdedamount,
														offTrns.getLocation(), offTrns.getCommentText(),commitBean.getCreateUserId());
											}
										}
									}
								}
								objGlTransactions.setTxnPostUsage(glTransactions.getTxnPostUsage());
								if ("CROB".equals(dedCatgTemp) && glTransactions.getPayeePersonId() == null
										&& glTransactions.getPayeeCorporateId() == null) {
									logger.info(this.getClass().getName()+" corporateIdPersonId method call");
									List<GlTransactions> txnTempList = otdglirtRepository
											.corporateIdPersonId(glTransactions.getDeductionId());
									if (txnTempList.size() > 0) {
										for (final GlTransactions txnTempData : txnTempList) {
											if (txnTempData.getPayeeCorporateId() != null) {
												objGlTransactions
														.setPayeeCorporateId(txnTempData.getPayeeCorporateId());
											}
											if (txnTempData.getPayeePersonId() != null) {
												objGlTransactions.setPayeePersonId(txnTempData.getPayeePersonId());
											}
										}
									}
								}
								logger.info(this.getClass().getName()+" findCaseloadId method call");
								createCaseload = otdglirtRepository.findCaseloadId(glTransactions.getDeductionId());
								if (createCaseload != null) {
								} else {
									createCaseload = objGlTransactions.getCaseloadId();
									;
								}
								if (("DED".equals(glTransactions.getTxnType())
										|| "WA".equals(glTransactions.getTxnType())
										|| "DEDC".equals(glTransactions.getTxnType()))
										&& "CR".equals(glTransactions.getTxnPostUsage()) && "CROB".equals(dedCatgTemp)
										&& !(createCaseload.equals(glTransactions.getOffenderId().toString()))) {
									logger.info(this.getClass().getName()+" reverseBeneficiaryTrans method call");
								/*String reverseBeneficiary = otdglirtRepository.reverseBeneficiaryTrans(
											objGlTransactions.getTxnId(), objGlTransactions.getTxnEntrySeq(),
											glTransactions.getGlEntrySeq(), glTransactions.getTxnId(), glSeq,
											objGlTransactions.getTxnType(), tempDesc);*/
									
									//Procedure call FINANCIAL.REVERSE_BENEFICIARY_TRANS
									String reverseBeneficiary =financialservice.reverseBeneficiaryTrans(objGlTransactions.getTxnId(),  objGlTransactions.getTxnEntrySeq(), glTransactions.getGlEntrySeq(), glTransactions.getTxnId(), glSeq, objGlTransactions.getTxnType(), tempDesc);
									if (reverseBeneficiary != null) {
									} else {
										liReturn = 17;
										throw new RuntimeException("17");
									}
								} else if (("DED".equals(glTransactions.getTxnType())
										|| "WA".equals(glTransactions.getTxnType())
										|| "DEDC".equals(glTransactions.getTxnType()))
										&& "CR".equals(glTransactions.getTxnPostUsage()) && "CROB".equals(dedCatgTemp)
										&& createCaseload == glTransactions.getOffenderId().toString()) {
									logger.info(this.getClass().getName()+" updateOffenderBeneficiaries method call");
		   						/*String reverseBeneficiary = otdglirtRepository.updateOffenderBeneficiaries(
											glTransactions.getOffDeductionId(), objGlTransactions.getPayeePersonId(),
											objGlTransactions.getPayeeCorporateId(),
											glTransactions.getTxnEntryAmount());*/
									//Procedure Call FINANCIAL.UPDATE_OFFENDER_BENEFICIARIES
									String reverseBeneficiary = financialservice.updateOffenderBeneficiaries(glTransactions.getOffDeductionId(), objGlTransactions.getPayeePersonId(), objGlTransactions.getPayeeCorporateId(), glTransactions.getTxnEntryAmount(), objGlTransactions.getCreateUserId());
									if (reverseBeneficiary != null) {
									}
								}
								logger.info(this.getClass().getName()+" txnTypeOutputData method call");
								String txnTypeData = otdglirtRepository
										.txnTypeOutputData(objGlTransactions.getTxnType());
								if ("CR".equals(glTransactions.getTxnPostUsage())
										&& glTransactions.getOffenderId() != null
										&& glTransactions.getDeductionId() != null) {
									logger.info(this.getClass().getName()+" accountClosedFlagData method call");
									List<GlTransactions> glTransactionsFlagTwo = otdglirtRepository
											.accountClosedFlagData(objGlTransactions.getOffenderId(),objGlTransactions.getCreateUserId());
									if (glTransactionsFlagTwo.size() > 0) {
										for (final GlTransactions glTxnsFlagOne : glTransactionsFlagTwo) {
											if ("Y".equals(glTransactions.getTxnReversedFlag())) {
												liReturn = 9;
												throw new RuntimeException("9");
											}
										}
									} else if (glTransactionsFlagTwo.size() > 1) {
										liReturn = 10;
										throw new RuntimeException("10");
									} else {
										liReturn = 11;
										throw new RuntimeException("11");
									}
									Double lvMaxTotalAmount = null;
									Double lvDeductionAmount = null;
									Double lvAdjustmentAmount = 0.0;
									Double lvResidualAmount = null;
									String lvAdjustmentReason = null;
									logger.info(this.getClass().getName()+" maxTotalAmountDeductionAamount method call");
									OffenderDeductions offDed = otdglirtRepository
											.maxTotalAmountDeductionAamount(glTransactions.getDeductionId());
									if (offDed != null) {
										if (offDed.getMaxTotalAmount() != null) {
											lvMaxTotalAmount = Double.valueOf(offDed.getMaxTotalAmount().toString());
										} else {
											lvMaxTotalAmount = 0.0;
										}
										if (offDed.getDeductionAmount() != null) {
											lvDeductionAmount = Double.valueOf(offDed.getDeductionAmount().toString());
										} else {
											lvDeductionAmount = 0.0;
										}
										if (offDed.getAdjustmentAmount() != null) {
											lvAdjustmentAmount = Double
													.valueOf(offDed.getAdjustmentAmount().toString());
										} else {
											lvAdjustmentAmount = 0.0;
										}
										lvResidualAmount = lvMaxTotalAmount + lvDeductionAmount + lvAdjustmentAmount;
										if (lvResidualAmount <= 0) {
											lvResidualAmount = 0.0;
										}
									}
									logger.info(this.getClass().getName()+" insertOffenderAdjustmentTxns method call");
			     					liReturn = otdglirtRepository.insertOffenderAdjustmentTxns(
											glTransactions.getTxnId(), glTransactions.getOffenderId(),
											glTransactions.getDeductionId(), glTransactions.getTxnEntryAmount(),
											objGlTransactions.getTxnId(),commitBean.getCreateUserId());
									Double pTxnEntryAmount = Double
											.valueOf(glTransactions.getTxnEntryAmount().toString());
									final Double totalAmount = lvAdjustmentAmount - pTxnEntryAmount;
									if (totalAmount > 0) {
										lvAdjustmentReason = "P";
									} else {
										lvAdjustmentReason = null;
									}
									logger.info(this.getClass().getName()+" updateOffenderDeductionsAdjustmentAmount method call");
			     					liReturn = otdglirtRepository.updateOffenderDeductionsAdjustmentAmount(
											lvAdjustmentAmount, glTransactions.getTxnEntryAmount(), lvAdjustmentReason,
											glTransactions.getDeductionId(),commitBean.getCreateUserId());
			
									//Old data of OFFENDER_DEDUCTIONS table
									OffenderDeductions oldRec = otdglirtRepository.getOffenderDeductionOldData(objGlTransactions.getOffDeductionId()!=null ?objGlTransactions.getOffDeductionId().longValue():null );

									OffenderDeductions newRec = new OffenderDeductions();
									newRec.setDeductionType(glTransactions.getDeductionType());
									newRec.setOffenderId(glTransactions.getOffenderId()!=null ? glTransactions.getOffenderId().longValue() : null);
									newRec.setOffenderDeductionId(objGlTransactions.getDeductionId()!=null ? objGlTransactions.getDeductionId().longValue():null);
									newRec.setCreateUserId(commitBean.getCreateUserId());
									newRec.setCaseloadId(createCaseload);
			
									logger.info(this.getClass().getName()+" offenderDeductionsTjn method call");
									//Trigger call OFFENDER_DEDUCTIONS_TJN 
									//offenderdeductionstjnservice.offenderDeductionsTjn(newRec, oldRec,"UPDATE");
				
			    					logger.info(this.getClass().getName()+" OffenderDeductionsThtyTrigger method call");
			    						//Trigger call OFFENDER_DEDUCTIONS_THTY 
			    					offenderdeductionsthtyservice.OffenderDeductionsThtyTrigger(dataMappingOfOffenderDeductionsHty(oldRec),"UPDATE");
				
			    					//Trigger call OFFENDER_DEDUCTIONS_T2 
			    					logger.info(this.getClass().getName()+" offenderDeductionsT2Trigger method call");
			    					offenderDeductionsT2Service.offenderDeductionsT2Trigger(newRec, oldRec, "UPDATE");
									if (liReturn == 0) {
										liReturn = 18;
										throw new RuntimeException("18");
									}
								}
							}
						} else {
							liReturn = 19;
							throw new RuntimeException("19");
						}
					}
					if ("Y".equals(proceedFlag)) {
						//Trigger call GL_TRANSACTIONS_T1 
						TrustAudits trustAudits = new TrustAudits();
						trustAudits.setCreateUserId(commitBean.getCreateUserId());
						trustAudits.setTxnId(objGlTransactions.getTxnId()!=null ?new BigDecimal(objGlTransactions.getTxnId()):null);
						gltransactionst1service.glTransactionsT1Trigger(trustAudits);
						liReturn = otdglirtRepository.updateGlTransactionsTxnReversedFlag(
								objGlTransactions.getTxnReversedFlag(), objGlTransactions.getTxnId(),
								objGlTransactions.getTxnEntrySeq(), objGlTransactions.getCaseloadId(),commitBean.getCreateUserId());
						objGlTransactions.setCreateUserId(commitBean.getCreateUserId());
						objGlTransactions.setModifyUserId(commitBean.getCreateUserId());
						//Trigger call GL_TRANSACTIONS_TJN
						//gltransactionstjnservice.glTransactionsTjnTrigger(objGlTransactions,"UPDATING");
						if (liReturn == 0) {
							liReturn = 20;
							throw new RuntimeException("20");
						}
					}
					if ("Y".equals(proceedFlag)) {
						String txnTypeExistFlag = otdglirtRepository.txnTypeExist(objGlTransactions.getTxnType(),
								objGlTransactions.getCaseloadId());
						if ("Y".equals(txnTypeExistFlag)) {
							liReturn = otdglirtRepository.updateOffenderWorks(glTransactions.getOffenderId(),
									objGlTransactions.getTxnId(),commitBean.getCreateUserId());
						}
					}
					Long txnId = objGlTransactions.getTxnId();
					OffenderTransactions bean = new OffenderTransactions();
					bean.setTxnId(txnId.intValue());
					// Wrong fee account
					logger.info(this.getClass().getName()+" wrongFeeAccount method call");
					wrongFeeAccount(bean, glTransactions.getTxnId(),commitBean.getCreateUserId());
					 //Update Offender fee bills
					logger.info(this.getClass().getName()+" updatetOffFeeBills method call");
					updatetOffFeeBills(objGlTransactions.getTxnId(), objGlTransactions.getTxnEntrySeq(),txnIdNextVal.longValue(),commitBean.getCreateUserId());
				}
			} catch (Exception e) {
				logger.error(this.getClass().getName()+" glTxnCommit method call in error : ", e);
				throw new RuntimeException("20");
			}
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
	public List<SystemProfiles> sysPflExecuteQuery(final SystemProfiles searchRecord) {
		return otdglirtRepository.sysPflExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstSYS_PFL
	 *
	 * @throws SQLException
	 */
	@Transactional
	public Integer sysPflCommit(final SystemProfilesCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			liReturn = otdglirtRepository.sysPflInsertSystemProfiles(commitBean.getInsertList());
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().forEach(bo -> {
				bo.setModifyUserId(commitBean.getCreateUserId());
			});
			liReturn = otdglirtRepository.sysPflDeleteSystemProfiles(commitBean.getDeleteList());
		}
		return liReturn;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> cgfkGlTxnReversalReasonRecordGroup() {
		return otdglirtRepository.cgfkGlTxnReversalReasonRecordGroup();

	}

	public List<GlTransactions> txnReversedFlagData(final Long txnId, final Long txnEntrySeq) {
		return otdglirtRepository.txnReversedFlagData(txnId, txnEntrySeq);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	@Transactional(rollbackFor = Exception.class)
	public Integer whenNextbuttonClick(final GlTransactions searchRecord) {
		Integer returnList = 0;
		String accountCloseFalg = "N";
		String closedFlag = null;
		String postingType = null;
		String subActType = null;
		String crobTrueFlag = null;
		BigDecimal glDeductionCur = null;
		Double revAmt = null;
		String tempInfoNumber = null;
		String dedType = null;
		Long tempOffId = null;
		String dedFlag = null;
		String dedCat = null;
		Long lDeductionId = null;
		Double paidAmt = null;
		Double crobAmt = null;
		String txnFlag = null;
		Boolean pHoldTransFlag = null;
		if ("N".equals(searchRecord.getNextButton())) {
			if (searchRecord.getTxnType() != null) {
				Integer txnOprDataTemp = otdglirtRepository.transactionOperationsDataTest(searchRecord.getTxnType(),
						searchRecord.getCaseloadId());
				if (txnOprDataTemp > 0) {
					returnList = 1;
					return returnList;
				}
				Integer txnOprTempData = otdglirtRepository.transactionOperationsOtdshift(searchRecord.getTxnType(),
						searchRecord.getCaseloadId());
				if (txnOprTempData > 0) {
					returnList = 2;
					return returnList;
				}
				String txnTypeHor = otdglirtRepository.txnTypeHor(searchRecord.getTxnType(),
						searchRecord.getCaseloadId());
				if ("HOR".equals(txnTypeHor)) {
					returnList = 3;
					return returnList;
				}
			}
			if (searchRecord.getTxnId() != null && searchRecord.getTxnEntrySeq() != null
					&& searchRecord.getCaseloadId() != null) {
				final Long offId = otdglirtRepository.txnOffenderId(searchRecord.getTxnId(), searchRecord.getTxnEntrySeq());
				closedFlag = otdglirtRepository.accountClosedFlag(offId, searchRecord.getCaseloadId());
				if ("Y".equals(closedFlag)) {
					accountCloseFalg = "Y";
				} else {
					accountCloseFalg = "N";
				}
			}
			if ("Y".equals(accountCloseFalg)) {
				returnList = 4;
				return returnList;
			}
		} else {
			OffenderTransactions offenderTransactions = new OffenderTransactions();
			Map<String, Object> getSubActTypeMap = new HashMap<String, Object>();
			final int result = otdglirtRepository.whenNextbuttonUpdates(searchRecord);
			if (result != 0) {
				final BigDecimal maxOffenderBookId = otdglirtRepository.maxOffenderBookId(searchRecord.getOffenderId());
				final Integer txnIdNextVal = otdglirtRepository.txnIdNextValData();
				final String txnType = otdglirtRepository.txnType(searchRecord.getCreateUserId());
//				getSubActTypeMap = otdglirtRepository.getSubActType("OTDOPCTA", searchRecord.getTxnType(),
//						searchRecord.getCaseloadId());
			   OtddisbuProcessTransactionsBean data = new	OtddisbuProcessTransactionsBean();
			   data.setpModuleName("OTDOPCTA");
			   data.setpTxnType(txnType);
			   data.setpCaseloadId(searchRecord.getCaseloadId());
			   getSubActTypeMap=trustService.getSubActType(data);
				if (getSubActTypeMap != null) {
					if (getSubActTypeMap.get("P_TXN_POST_TYPE") != null) {
						postingType = getSubActTypeMap.get("P_TXN_POST_TYPE").toString();
					}
					if (getSubActTypeMap.get("P_SUB_ACT_TYPE") != null) {
						subActType = getSubActTypeMap.get("P_SUB_ACT_TYPE").toString();
					}
				}
				offenderTransactions.setTxnId(txnIdNextVal);
				offenderTransactions.setTxnEntrySeq(1);
				offenderTransactions.setCaseloadId(searchRecord.getCaseloadId());
				offenderTransactions.setOffenderId(Long.valueOf(searchRecord.getOffenderId().toString()));
				offenderTransactions.setOffenderBookId(Long.valueOf(maxOffenderBookId.toString()));
				offenderTransactions.setTxnPostingType(postingType);
				offenderTransactions.setTxnType(txnType);
				offenderTransactions.setTxnEntryDesc("Re-Open Closed Account");
				offenderTransactions.setTxnEntryAmount(0.0);
				offenderTransactions.setTxnEntryDate(trunc(eliteDateService.getDBTime()));
				offenderTransactions.setSubAccountType(subActType);
				offenderTransactions.setDeductionFlag(null);
				offenderTransactions.setPreWithholdAmount(null);
				offenderTransactions.setDeductionType(null);
				offenderTransactions.setPayeeCorporateId(null);
				offenderTransactions.setPayeePersonId(null);
				offenderTransactions.setInfoNumber(null);
				try {
			//	returnList = otdglirtRepository.insertIntoOffenderTransaction(offenderTransactions);
				offenderTransactions.setTxnEntryAmount(0.0);
				offenderTransactions.setSlipPrintedFlag("N");
				trustService.insertIntoOffenderTrans(offenderTransactions);
			//	returnList = otdglirtRepository.processGlTransNew(offenderTransactions);
				trustService.processGlTransNew(offenderTransactions.getCaseloadId(), offenderTransactions.getTxnType(), (Object)null,  0.0, offenderTransactions.getTxnId(), 
						offenderTransactions.getTxnEntryDate(), offenderTransactions.getTxnEntryDesc(),
						offenderTransactions.getTxnEntrySeq(), "OTDOPCTA", offenderTransactions.getOffenderId().intValue(),
						offenderTransactions.getOffenderBookId(), null, offenderTransactions.getSubAccountType(),
						null, null, null, 0, null, offenderTransactions.getCreateUserId());
				} catch (Exception e) {
					throw new RuntimeException("Error : ");
				}
			}
		}
		List<GlTransactions> glTransactionsFlag = otdglirtRepository.findingDeductionId(searchRecord.getTxnId(),
				searchRecord.getTxnEntrySeq());
		if (glTransactionsFlag.size() > 0) {
			for (final GlTransactions glTransactions : glTransactionsFlag) {
				glDeductionCur = glTransactions.getDeductionId();
			}
		}
		OffenderTransactions offTxns = otdglirtRepository.findingDeductionType(searchRecord.getTxnId(),
				searchRecord.getTxnEntrySeq());
		if (offTxns != null && offTxns.getDeductionType() != null) {
			revAmt = offTxns.getTxnEntryAmount();
			tempInfoNumber = offTxns.getInfoNumber();
			dedType = offTxns.getDeductionType();
			tempOffId = offTxns.getOffenderId();
			dedFlag = offTxns.getDeductionFlag();
		}
		String deductionCategory = otdglirtRepository.findingDeductionCategory(dedType);
		if (deductionCategory != null) {
			dedCat = deductionCategory;
		} else {
			dedCat = "@";
			crobTrueFlag = "N";
		}
		if ("CROB".equals(dedCat)) {
			lDeductionId = Long.valueOf(glDeductionCur.toString());
			OffenderDeductions offenderDeductions = otdglirtRepository.findingDeductionAmount(lDeductionId);
			if (offenderDeductions != null) {
				paidAmt = Double.valueOf(offenderDeductions.getDeductionAmount().toString());
				crobAmt = Double.valueOf(offenderDeductions.getMaxTotalAmount().toString());
			} else {
				returnList = 5;
				throw new RuntimeException("5");
			}
			Double val = crobAmt - paidAmt;
			if ((revAmt > val) && "N".equals(dedFlag)) {
				crobTrueFlag = "Y";
			} else {
				crobTrueFlag = "N";
			}
		}
		if ("Y".equals(crobTrueFlag)) {
			returnList = 6;
			throw new RuntimeException("6");
		}
		Long txnId = Long.valueOf(searchRecord.getTxnId());
		Long txnEntrySeq = Long.valueOf(searchRecord.getTxnEntrySeq());
		List<GlTransactions> glTransactionsFlagOne = otdglirtRepository.txnReversedFlagData(txnId, txnEntrySeq);
		if (glTransactionsFlagOne.size() > 0) {
			for (final GlTransactions glTransactions : glTransactionsFlagOne) {
				txnFlag = glTransactions.getTxnReversedFlag();
			}
		} else if (glTransactionsFlagOne.size() == 0) {
			txnFlag = "Y";
		} else {
			txnFlag = "L";
		}
		List<TransactionOperations> transactionOperations = otdglirtRepository.chkHoldTrans(searchRecord.getTxnType(),
				searchRecord.getCaseloadId());
		if (transactionOperations.size() > 0) {
			pHoldTransFlag = true;
		} else {
			pHoldTransFlag = false;
		}
		if (pHoldTransFlag == true) {
			returnList = 7;
			throw new RuntimeException("7");
		}
		if ("Y".equals(txnFlag)) {
			returnList = 8;
			throw new RuntimeException("8");
		} else if ("L".equals(txnFlag)) {
			returnList = 9;
			throw new RuntimeException("9");
		}
		String sysProfFlag = null;
		String benClearedFlag = null;
		String chequeFlag = null;
		String tempBankChequeData = otdglirtRepository.bankChequeData(txnId);
		if (tempBankChequeData != null) {
			chequeFlag = tempBankChequeData;
			if ("Y".equals(chequeFlag)) {
				returnList = 10;
				throw new RuntimeException("10");
			}
		}
		String tempProfileValue = otdglirtRepository.profileValue();
		if (tempProfileValue != null) {
			sysProfFlag = tempProfileValue;
		}
		String beneficiaryTransactions = otdglirtRepository.beneficiaryTransactions(txnId, txnEntrySeq);
		if (beneficiaryTransactions != null) {
			benClearedFlag = beneficiaryTransactions;
		}
		String bankChequeRegisters = otdglirtRepository.bankChequeRegisters(txnId);
		if (bankChequeRegisters != null) {
			chequeFlag = bankChequeRegisters;
			if ("Y".equals(chequeFlag)) {
				returnList = 11;
				throw new RuntimeException("11");
			}
		}
		if ("Y".equals(sysProfFlag)) {
			if ("Y".equals(benClearedFlag)) {
				returnList = 12;
				throw new RuntimeException("12");
			}
		} else if ("Y".equals(benClearedFlag)) {
			returnList = 13;
			throw new RuntimeException("13");
		}
		if (searchRecord.getOffenderId() != null) {
			String chkTrans = otdglirtRepository.accountFlagTrust(searchRecord.getOffenderId(),searchRecord.getCreateUserId());
			if ("Y".equals(chkTrans)) {
				returnList = 14;
				throw new RuntimeException("14");
			}
		}
		if ("REV".equals(searchRecord.getTxnType())) {
			OffenderTransactions offTxnsTemp = otdglirtRepository.infoNumberCreditObligationType(searchRecord);
			if (offTxnsTemp.getInfoNumber() != null && offTxnsTemp.getTxnType() != null) {
				returnList = 15;
				throw new RuntimeException("15");
			}
		}
		return returnList;
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

	private OffenderTransactions setTxnDetails(OffenderTransactions offTxn, String txnPostingType, String subAccntType)
			throws Exception {
		offTxn.setSubAccountType(subAccntType);
		offTxn.setTxnPostingType(txnPostingType);
		final Date transDate = trunc(eliteDateService.getDBTime());
		offTxn.setTxnEntryDate(transDate);
		offTxn.setModifyDate(transDate);
		offTxn.setPreWithholdAmount(offTxn.getTxnEntryAmount());
		offTxn.setSlipPrintedFlag("N");
		offTxn.setReceiptPendingPrintFlag("Y");
		return offTxn;
	}

	// Wrong fee Account
	public Integer wrongFeeAccount(OffenderTransactions bean, Long orgTxnId,String userId) throws Exception {
		Integer result = 0;
		logger.info(this.getClass().getName()+" txnIdBasedData method call");
		OffenderTransactions oldBean = otdglirtRepository.txnIdBasedData(bean.getTxnId().longValue());
		logger.info(this.getClass().getName()+" getCrAccountCode method call");
		Integer crAcctCode = otdglirtRepository.getCrAccountCode("DEDC", oldBean.getCaseloadId());
		logger.info(this.getClass().getName()+" getCrAccountCode method call");
		Integer drAcctCode = otdglirtRepository.getDrAccountCode("DEDC", oldBean.getCaseloadId());
		logger.info(this.getClass().getName()+" getTxnEntrySeq method call");
		Integer txnEntrySeq = ocdbreciRepository.getTxnEntrySeq(orgTxnId!=null?orgTxnId.intValue():null, oldBean.getOffenderId());
		oldBean.setTxnEntrySeq(txnEntrySeq);
		logger.info(this.getClass().getName()+" getSubAccountTypeDesc method call");
		oldBean.setTxnEntryDesc(ocdbreciRepository.getSubAccountTypeDesc("DEDC"));
		oldBean.setModuleName("OTDGLIRT");
		oldBean.setAccountCode(crAcctCode!=null?new BigDecimal(crAcctCode):null);
		oldBean.setTxnPostingType("CR");
		oldBean.setTxnEntryDate(trunc(eliteDateService.getDBTime()));
		logger.info(this.getClass().getName()+" getGlTxnEntrySeq method call");
		Integer entrySeq=otdglirtRepository.getGlTxnEntrySeq(orgTxnId!=null?orgTxnId.intValue():null, oldBean.getOffenderId());
		oldBean.setGlEntrySeq(entrySeq!=null?entrySeq.longValue():null);
		try {
		logger.info(this.getClass().getName()+" offTxnInsertGlTransactions method call");
		result = otdglirtRepository.offTxnInsertGlTransactions(orgTxnId, oldBean.getTxnEntrySeq().longValue(),
				crAcctCode!=null?new BigDecimal(crAcctCode):null, oldBean.getGlEntrySeq(), oldBean.getTxnEntryDate(), "DEDC",
				oldBean.getTxnPostingType(), oldBean.getCaseloadId(),oldBean.getOffenderId()!=null? new BigDecimal(oldBean.getOffenderId()):null,
						oldBean.getOffenderBookId()!=null?new BigDecimal(oldBean.getOffenderBookId()):null, oldBean.getTxnEntryAmount()!=null?new BigDecimal(oldBean.getTxnEntryAmount()):null,
				oldBean.getTxnEntryDesc(), bean.getTxnId()!=null?bean.getTxnId().longValue():null, null, 1l, null, null, null,userId);
		}catch (Exception e) {
			logger.error(this.getClass().getName()+" offTxnInsertGlTransactions method call in error ::"+e);
			throw new RuntimeException("20");
		}
		oldBean.setAccountCode(new BigDecimal(drAcctCode));
		oldBean.setTxnPostingType("DR");
		oldBean.setTxnType("DEDC");
		oldBean.setGlEntrySeq(oldBean.getGlEntrySeq()+ 1);
		try {
		logger.info(this.getClass().getName()+" offTxnInsertGlTransactions method call");
		result = otdglirtRepository.offTxnInsertGlTransactions(orgTxnId, oldBean.getTxnEntrySeq()!=null?oldBean.getTxnEntrySeq().longValue():null,
				drAcctCode!=null?new BigDecimal(drAcctCode):null, oldBean.getGlEntrySeq(), oldBean.getTxnEntryDate(), "DEDC",
				oldBean.getTxnPostingType(), oldBean.getCaseloadId(), oldBean.getOffenderId()!=null?new BigDecimal(oldBean.getOffenderId()):null,
						oldBean.getOffenderBookId()!=null?new BigDecimal(oldBean.getOffenderBookId()):null,oldBean.getTxnEntryAmount()!=null? new BigDecimal(oldBean.getTxnEntryAmount()):null,
				oldBean.getTxnEntryDesc(),bean.getTxnId()!=null? bean.getTxnId().longValue():null, null, 2l, null, null, null,userId);
	    }catch (Exception e) {
			logger.error(this.getClass().getName()+" offTxnInsertGlTransactions method call in error ::"+e);
		    throw new RuntimeException("20");
	    }
		oldBean.setSubAccountType("REG");
		oldBean.setTxnId(orgTxnId.intValue());
		oldBean.setCreateUserId(userId);
		
		try {
		logger.info(this.getClass().getName()+" insertIntoOffenderTransaction method call");
		result = ocdbreciRepository.insertIntoOffenderTransaction(oldBean);
		}catch (Exception e) {
			logger.error(this.getClass().getName()+" insertIntoOffenderTransaction method call in error ::"+e);
			throw new RuntimeException("12");
		}
		logger.info(this.getClass().getName()+" updateOffenderBalance method call");
		//ocdbreciRepository.updateOffenderBalance(oldBean);
		//Procedure call
		trustService.updateOffenderBalance(oldBean, bean.getCreateUserId());
		return result;
	}

	// update off FeeBills
	@Transactional
	public Integer updatetOffFeeBills(final Long oldTxnId, final Long txnEntrySeq, final Long newTxnId,String userId) throws Exception {
		int result = 0;
		logger.info(this.getClass().getName()+" offenderTxnData method call");
		OffenderTransactions offTxn = otdglirtRepository.offenderTxnData(oldTxnId, txnEntrySeq);
		logger.info(this.getClass().getName()+" offFeeExecuteQuery method call");
		OffFeeBillTransactions feeBills = otdglirtRepository.offFeeExecuteQuery(oldTxnId, txnEntrySeq);
		if (feeBills != null && feeBills.getBillTxnAmount() != null) {
			feeBills.setTrustTxnId(newTxnId.intValue());
			logger.info(this.getClass().getName()+" getBillTranId method call");
			feeBills.setBillTxnNo(ocdbreciRepository.getBillTranId(feeBills.getBillId()));
			feeBills.setTrustTxnEntrySeq(1);
			feeBills.setBillTxnAmount(new BigDecimal(offTxn.getTxnEntryAmount()));
			logger.info(this.getClass().getName()+" updateOffenderFees method call");
			feeBills.setOriginalBillId(feeBills.getBillId());
			feeBills.setOriginalBillTxnNo(txnEntrySeq.intValue());
			OffFeeBills bills=otdglirtRepository.getUdjustmentFeeBills(feeBills.getBillId());
			feeBills.setBillArDueDate(bills.getBillExpectedArDueDate());
			feeBills.setBillLdppStartDate(bills.getBillExpectedLdppStartDate());
			feeBills.setBillLdppEndDate(bills.getBillExpectedLdppEndDate());
			feeBills=revarsalPaymentBillAging(feeBills);
			logger.info(this.getClass().getName()+" revarsalPaymentBillAging method call");
			feeBills.setBillTxnDatetime(eliteDateService.getDBTime());
			feeBills.setCreateUserId(userId);
			
			result = otdglirtRepository.updateOffenderFees(feeBills);
			
			logger.info(this.getClass().getName()+" updateOffenderFees method call");
			//OFF_FEE_BILL_TRANSACTIONS_T1 Trigger call
			logger.info(this.getClass().getName()+" offFeeBillTransactionsT1 method call");
			offfeebilltransactionst1service.offFeeBillTransactionsT1(feeBills);
			//OFF_FEE_BILL_TRANSACTIONS_T2 Trigger call
			logger.info(this.getClass().getName()+" offFeeBillTransactionsT2 method call");
			offfeebilltransactionst2service.offFeeBillTransactionsT2(feeBills);
		}
		return result;
	}
	
	
	@Override
	@Transactional
	public OffFeeBillTransactions revarsalPaymentBillAging(OffFeeBillTransactions bean) throws Exception {
		List<OffFeeBillTransactions> list = otdglirtRepository.getUdjustmentFeeBills(bean);
		if (list != null && list.size()>0 && list.get(0).getOriginalBillTxnNo() == null) {
			bean = ocdreverService.unReversedPaymentExistsFlow(bean, list.get(0));
		} else {
			bean = ocdreverService.unReversedPaymentNotExistsFlow(bean);
		}
		return bean;
	}
	
	
	private OffenderDeductionsHty dataMappingOfOffenderDeductionsHty(OffenderDeductions oldRec) {
		OffenderDeductionsHty offDedHty = new OffenderDeductionsHty();
		offDedHty.setJnOperation("UPD");
		offDedHty.setJnOracleUser(oldRec.getModifyUserId());
		offDedHty.setOffenderDeductionId(oldRec.getOffenderDeductionId());
		offDedHty.setCaseloadId(oldRec.getCaseloadId());
		offDedHty.setCreditLimit(oldRec.getCreditLimit());
		offDedHty.setDeductionType(oldRec.getDeductionType());
		offDedHty.setDeductionStatus(oldRec.getDeductionStatus());
		offDedHty.setDeductionPriority(
				(oldRec.getDeductionPriority() != null ? oldRec.getDeductionPriority().doubleValue() : null));
		offDedHty.setInformationNumber(oldRec.getInformationNumber());
		offDedHty.setDeductionPercentage(
				(oldRec.getDeductionPercentage() != null ? oldRec.getDeductionPercentage().doubleValue()
						: null));
		offDedHty.setProcessPriorityNumber(
				(oldRec.getProcessPriorityNumb() != null ? oldRec.getProcessPriorityNumb().doubleValue()
						: null));
		offDedHty.setEffectiveDate(oldRec.getEffectiveDate());
		offDedHty.setCommentText(oldRec.getCommentText());
		offDedHty.setFifoFlag(oldRec.getFifoFlag());
		offDedHty.setPayeePersonId(
				(oldRec.getPayeePersonId() != null ? oldRec.getPayeePersonId().longValue() : null));
		offDedHty.setPayeeCorporateId(
				(oldRec.getPayeeCorporateId() != null ? oldRec.getPayeeCorporateId().longValue() : null));
		offDedHty.setMaxMonthlyAmount(oldRec.getMaxMonthlyAmount());
		offDedHty.setMaxTotalAmount(oldRec.getMaxTotalAmount());
		offDedHty.setDeductionAmount(oldRec.getDeductionAmount());
		offDedHty.setAdjustmentReasonCode(oldRec.getAdjustmentReasonCode());
		offDedHty.setAdjustmentAmount(oldRec.getAdjustmentAmount());
		offDedHty.setAdjustmentUserId(oldRec.getAdjustmentUserId());
		offDedHty.setAdjustmentTxnId(
				(oldRec.getAdjustmentTxnId() != null ? oldRec.getAdjustmentTxnId().longValue() : null));
		offDedHty.setAdjustmentText(oldRec.getAdjustmentText());
		offDedHty.setModifyDate(oldRec.getModifyDate());
		offDedHty.setPayDeductionFlag(oldRec.getPayDeductionFlag());
		offDedHty.setMaxRecursiveAmount(oldRec.getMaxRecursiveAmount());
		offDedHty.setGroupId((oldRec.getGroupId() != null ? oldRec.getGroupId().longValue() : null));
		offDedHty.setCaseId((oldRec.getCaseId() != null ? oldRec.getCaseId().longValue() : null));
		offDedHty.setParentDeductionId(
				(oldRec.getParentDeductionId() != null ? oldRec.getParentDeductionId().longValue() : null));
		offDedHty.setJsStatus(oldRec.getJsStatus());
		offDedHty.setCollectAgencyAmount(oldRec.getCollectAgencyAmount());
		offDedHty.setCollectAgencyFlag(oldRec.getCollectAgencyFlag());
		offDedHty.setCollectSentDate(oldRec.getCollectSentDate());
		offDedHty.setOffenderPaymentProfileId((oldRec.getOffenderPaymentProfileId() != null
				? oldRec.getOffenderPaymentProfileId().longValue()
				: null));
		offDedHty.setSealFlag(oldRec.getSealFlag());
		offDedHty.setCreateUserId(oldRec.getCreateUserId());
		offDedHty.setModifyUserId(oldRec.getModifyUserId());
		
		return offDedHty;
	}
	
}
