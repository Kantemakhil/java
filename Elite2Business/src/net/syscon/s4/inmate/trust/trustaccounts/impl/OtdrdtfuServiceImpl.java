package net.syscon.s4.inmate.trust.trustaccounts.impl;

import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
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

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.syscon.s4.common.InsertGlTransNew;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.OtddisbuProcessTransactionsBean;
import net.syscon.s4.common.beans.OtrdreceReportBean;
import net.syscon.s4.common.beans.OtrreceiReportBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.ReportBean;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.SystemProfilesCommitBean;
import net.syscon.s4.common.beans.TransactionTypes;
import net.syscon.s4.core.EliteDateService;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.im.beans.BankChequeData;
import net.syscon.s4.im.beans.ChkFreezeDisbursements;
import net.syscon.s4.im.beans.Corporates;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.im.beans.TransactionOperation;
import net.syscon.s4.inmate.beans.OffenderDeductions;
import net.syscon.s4.inmate.trust.trustaccounts.OtdrdtfuRepository;
import net.syscon.s4.inmate.trust.trustaccounts.OtdrdtfuService;
import net.syscon.s4.inst.booking.beans.Persons;
import net.syscon.s4.pkgs.common.CommonService;
import net.syscon.s4.pkgs.deductions.DeductionsService;
import net.syscon.s4.pkgs.financial.FinancialService;
import net.syscon.s4.pkgs.trust.TrustService;

/**
 * Class OtdrdtfuServiceImpl
 */
@Service
public class OtdrdtfuServiceImpl extends BaseBusiness implements OtdrdtfuService {

	@Autowired
	private OtdrdtfuRepository otdrdtfuRepository;
	
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
	private static Logger logger = LogManager.getLogger(OtdrdtfuServiceImpl.class.getName());

	/**
	 * Creates new OtdrdtfuServiceImpl class Object
	 */
	public OtdrdtfuServiceImpl() {
		// OtdrdtfuServiceImpl
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public OffenderBookings offBkgPostQuery(final OffenderBookings paramBean) {
		final OffenderBookings offenderBookings = otdrdtfuRepository.offBkgPostQuery(paramBean);
		return offenderBookings;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public Corporates cgfkchkOffTxnOffTxnCorp(final Corporates paramBean) {
		final Corporates corporates = otdrdtfuRepository.cgfkchkOffTxnOffTxnCorp(paramBean);
		return corporates;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public TransactionTypes cgfkchkOffTxnOffTxnTxn(final TransactionTypes paramBean) {
		final TransactionTypes transactionTypes = otdrdtfuRepository.cgfkchkOffTxnOffTxnTxn(paramBean);
		return transactionTypes;
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
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	@Transactional(rollbackFor=Exception.class)
	public List<OffenderTransactions> mainProcess(final OffenderTransactions paramBean) {
		OffenderTransactions returnObj = new OffenderTransactions();
		final List<OffenderTransactions> offenderTransactionsList = new ArrayList<>();
		Integer txnEntrySeq = null;
		if(paramBean != null) {
			if (paramBean.getPayeeName() != null && !paramBean.getPayeeName().isEmpty()) {
				if (paramBean.getPayeeCorporateId() != null) {
					try {
						final Corporates isValidCorporete =  otdrdtfuRepository.getCorp(Long.valueOf(paramBean.getPayeeCorporateId().toString()));
						if (isValidCorporete != null) {
							if (isValidCorporete.getActiveFlag() != null && "N".equals(isValidCorporete.getActiveFlag())) {
								returnObj.setErrorMessage("#payeecorporateisinactive");
								offenderTransactionsList.clear();
								offenderTransactionsList.add(returnObj);
								throw new RuntimeException("#payeecorporateisinactive");
							}
							paramBean.setPayeeName(isValidCorporete.getCorporateName());
						} else {
							returnObj.setErrorMessage("#payeeenteredcannotbefound");
							offenderTransactionsList.clear();
							offenderTransactionsList.add(returnObj);
							throw new RuntimeException("#payeeenteredcannotbefound");
						}
						
					}catch (Exception e) {
						logger.error("Error in get_Corp : ", e);
						returnObj.setErrorMessage(e.getMessage());
						offenderTransactionsList.clear();
						offenderTransactionsList.add(returnObj);
						throw new RuntimeException("Error in get_Corp : ");
					}
				} else if (paramBean.getPayeePersonId() != null) {
					final Persons person = otdrdtfuRepository.getPerson(paramBean.getPayeePersonId());
					try {
					if (person != null) {
						paramBean.setPayeeName(person.getLastName());
					} else {
						returnObj.setErrorMessage("#payeeperonenteredcannotbefound");
						offenderTransactionsList.clear();
						offenderTransactionsList.add(returnObj);
						throw new RuntimeException("#payeeperonenteredcannotbefound");
					}
					}catch (Exception e) {
						logger.error("Error in get_Person : ", e);
						returnObj.setErrorMessage(e.getMessage());
						offenderTransactionsList.clear();
						offenderTransactionsList.add(returnObj);
						throw new RuntimeException("Error in get_Person : ");
					}
				}
			}
			paramBean.setReceiptPendingPrintFlag(paramBean.getReceiptPrintedFlag());
			if ("R".equals(paramBean.getTxnUsage()) && "Y".equals(paramBean.getReceiptPendingPrintFlag())) {
				if (paramBean.getRemitterId() == null && (paramBean.getRemitterName() == null)) {
					returnObj.setErrorMessage("#remittermustbeentered");
					offenderTransactionsList.clear();
					offenderTransactionsList.add(returnObj);
					throw new RuntimeException("#remittermustbeentered");
				}
			}
			if ("D".equals(paramBean.getTxnUsage()) && "Y".equals(paramBean.getChequeProductionFlag())) {
				if (paramBean.getPayeeCorporateId() == null && paramBean.getPayeePersonId() == null
						&& paramBean.getPayeeName() == null) {
					returnObj.setErrorMessage("#payeemustbeentered");
					offenderTransactionsList.clear();
					offenderTransactionsList.add(returnObj);
					throw new RuntimeException("#payeemustbeentered");
				}
			}
			 String existFlag = "N"; 
			 existFlag = otdrdtfuRepository.txnIdCur(paramBean.getTxnId());
			if ("Y".equals(existFlag)) {
				return null;
			}
			final String chkResult = chkAccountStatus(paramBean);
			if (!"Y".equals(chkResult)) {
				returnObj.setErrorMessage("#chkResult");
				offenderTransactionsList.clear();
				offenderTransactionsList.add(returnObj);
				throw new RuntimeException("#chkResult");
			}
				final Integer txnId = otdrdtfuRepository.genTrustTrans("TXN_ID");
				
				paramBean.setTxnId(txnId);
				paramBean.setTxnEntrySeq(1);
				try {
				//Map<String, Object> subAccoutType = otdrdtfuRepository.getSubActType("OTDRDTFU", paramBean.getTxnType(), paramBean.getTxnUsage(), paramBean.getCaseloadId());
					//Procedure Call
					OtddisbuProcessTransactionsBean proTxn1 = new OtddisbuProcessTransactionsBean();
					proTxn1.setpModuleName("OTDRDTFU");
					proTxn1.setpTxnType(paramBean.getTxnType());
					proTxn1.setpTxnUsage(paramBean.getTxnUsage());
					proTxn1.setpCaseloadId(paramBean.getCaseloadId());
					Map<String, Object> subAccoutType = trustService.getSubActType(proTxn1);
					String pTxnPostType=null;
					String pSubActType=null;
				if (subAccoutType != null) {
					if (subAccoutType.get("P_TXN_POST_TYPE") != null) {
						pTxnPostType= subAccoutType.get("P_TXN_POST_TYPE").toString();
			    	paramBean.setTxnPostingType(pTxnPostType);
					}
					if (subAccoutType.get("P_SUB_ACT_TYPE") != null) {
						pSubActType= subAccoutType.get("P_SUB_ACT_TYPE").toString();
				paramBean.setSubAccountType(pSubActType);
					}
				}
				} catch (Exception e) {
					logger.error("get_Sub_Act_Type", e);
				}
				paramBean.setTxnEntryDate(trunc(eliteDateService.getDBTime()));
				paramBean.setPreWithholdAmount(paramBean.getTxnEntryAmount());
				paramBean.setModifyDate(trunc(eliteDateService.getDBTime()));
				paramBean.setSlipPrintedFlag("N");
										
				if ("D".equals(paramBean.getTxnUsage())) {
					String creditObligType = otdrdtfuRepository.creditObligExistsC(paramBean.getTxnType(), paramBean.getCaseloadType());
					if (creditObligType != null) {
						//Procedure call
						Map<String, Object> chkOverdrawn = trustService.chkOverdrawn(paramBean.getCaseloadId(), paramBean.getOffenderId()!=null?new BigDecimal(paramBean.getOffenderId()):null, paramBean.getSubAccountType(), paramBean.getTxnEntryAmount()!=null?new BigDecimal(paramBean.getTxnEntryAmount()):null, paramBean.getTxnType(),
								paramBean.getTxnEntrySeq()!=null?paramBean.getTxnEntrySeq().longValue():null, "Y", "OTDRDTFU", paramBean.getTxnId()!=null?new BigDecimal(paramBean.getTxnId()):null, paramBean.getOffenderBookId()!=null?new BigDecimal(paramBean.getOffenderBookId()):null, paramBean.getTotTxnFee()!=null? paramBean.getTotTxnFee().intValue():null, paramBean.getCaseloadType(), paramBean.getCreateUserId());
						 
						try {
							if (chkOverdrawn != null) {
								if (chkOverdrawn.get("SEQ_NO") != null) {
									paramBean.setTxnEntrySeq(Integer.valueOf(chkOverdrawn.get("SEQ_NO").toString()));
								}
								if (chkOverdrawn.get("CHECK_IND") != null) {
									paramBean.setCheckInd(chkOverdrawn.get("CHECK_IND").toString());
								}
							} else {
								returnObj.setErrorMessage("#errorintrushchkoverdrawn");
								offenderTransactionsList.clear();
								offenderTransactionsList.add(returnObj);
								throw new RuntimeException("#errorintrushchkoverdrawn");
							}
						} catch (Exception e) {
							returnObj.setErrorMessage("#errorintrushchkoverdrawn");
							offenderTransactionsList.clear();
							offenderTransactionsList.add(returnObj);
							throw new RuntimeException("#errorintrushchkoverdrawn");
						}
						
					} else {
						paramBean.setCheckInd("Y");
					}
					if ("Y".equals(paramBean.getReceiptPrintedFlag())) {
						Integer reciept = otdrdtfuRepository.genTrustTrans("SEQUENCE_" + paramBean.getCaseloadId()+"_D" );
						if (reciept != null) {
						String recieptNum = reciept.toString();
						while (recieptNum.length() < 6) {
							recieptNum = "0" + recieptNum;
						}
						paramBean.setReceiptNumber(paramBean.getCaseloadId() + recieptNum);
						}
					}
				} else {
					if ("Y".equals(paramBean.getReceiptPrintedFlag())) {
						Integer reciept = otdrdtfuRepository.genTrustTrans("SEQUENCE_" + paramBean.getCaseloadId());
						if (reciept != null) {
						String recieptNum = reciept.toString();
						while (recieptNum.length() < 6) {
							recieptNum = "0" + recieptNum;
						}
						paramBean.setReceiptNumber(paramBean.getCaseloadId() + recieptNum);
						}
					}
				}
				final Integer count = otdrdtfuRepository.txnSeqCur(paramBean.getTxnId(), paramBean.getTxnEntrySeq());
				if (count > 0) {
					paramBean.setTxnEntrySeq(paramBean.getTxnEntrySeq() + 1);
				}
				
				try {
					offenderTransactionsList.clear();
					offenderTransactionsList.add(paramBean);
					Integer isInserted = otdrdtfuRepository.offenderTransactionsCommit(offenderTransactionsList);
					if (isInserted != 1) {
						returnObj.setErrorMessage("#errorwhileinsertingrecordinoffendertransaction");
						offenderTransactionsList.clear();
						offenderTransactionsList.add(returnObj);
						throw new RuntimeException("#errorwhileinsertingrecordinoffendertransaction");
					}
				} catch (Exception e) {
					logger.error("Insert Query Error", e);
					returnObj.setErrorMessage("#errorwhileinsertingrecordinoffendertransaction");
					offenderTransactionsList.clear();
					offenderTransactionsList.add(returnObj);
					throw new RuntimeException("#errorwhileinsertingrecordinoffendertransaction");
				}
				
				Integer glEntrySequence = 0;
				 if ("D".equals(paramBean.getTxnUsage())) {
				
				 try {
					 //Procedure call
					 final Integer processGlTransNew = trustService.processGlTransNew(paramBean.getCaseloadId(), paramBean.getTxnType(), null, paramBean.getTxnEntryAmount(), paramBean.getTxnId(),
							 paramBean.getTxnEntryDate(), paramBean.getTxnEntryDesc(), paramBean.getTxnEntrySeq(), "OTDRDTFU", paramBean.getOffenderId().intValue(), paramBean.getOffenderBookId(),
							 paramBean.getSubAccountType(), null, paramBean.getPayeePersonId(), paramBean.getPayeeCorporateId(), paramBean.getPayeeNameText(), 0, null, paramBean.getCreateUserId());
					 
				 	if (processGlTransNew != null) {
				 		glEntrySequence = processGlTransNew;
								 	}
				 } catch (Exception e) {
					 returnObj.setErrorMessage("#errorinprocessgltransnew");
						offenderTransactionsList.clear();
						offenderTransactionsList.add(returnObj);
						throw new RuntimeException("#errorinprocessgltransnew");
				}
				
				 //Procedure call
				 OffenderTransactions obj = new OffenderTransactions();
				 obj.setCaseloadId(paramBean.getCaseloadId());
				 obj.setOffenderId(paramBean.getOffenderId());
				 obj.setOffenderBookId(paramBean.getOffenderBookId());
				 obj.setTxnId(paramBean.getTxnId());
				 obj.setTxnEntrySeq(paramBean.getTxnEntrySeq());
				 obj.setTxnType(paramBean.getTxnType());
				 obj.setTxnEntryAmount(paramBean.getTxnEntryAmount());
				 obj.setTxnEntryDate(paramBean.getTxnEntryDate());
				 obj.setTxnUsage(paramBean.getTxnUsage());
				 obj.setSubAccountType(paramBean.getSubAccountType());
				 obj.setCreateUserId(paramBean.getCreateUserId());
				 
				 final Long processTransactionFee = trustService.processTransactionFee(obj, "OTDRDTFU");
				 		if (processTransactionFee != null) {
				 			txnEntrySeq = processTransactionFee.intValue();
						
				 		}
				 } else {
					 try {
						 //Procedure call
						 final Integer processGlTransNew = trustService.processGlTransNew(paramBean.getCaseloadId(), paramBean.getTxnType(), null, paramBean.getTxnEntryAmount(), paramBean.getTxnId(),
								 paramBean.getTxnEntryDate(), paramBean.getTxnEntryDesc(), paramBean.getTxnEntrySeq(), "OTDRDTFU", paramBean.getOffenderId().intValue(), paramBean.getOffenderBookId(),
								 null, paramBean.getSubAccountType(), paramBean.getPayeePersonId(), paramBean.getPayeeCorporateId(), paramBean.getPayeeNameText(), 0, null, paramBean.getCreateUserId());
							
						 if (processGlTransNew != null) {
							 glEntrySequence = processGlTransNew;
							
						 	}
						 } catch (Exception e) {
							 logger.error("error in process_gl_trans_new", e);
							 returnObj.setErrorMessage("#errorinprocessgltransnew");
								offenderTransactionsList.clear();
								offenderTransactionsList.add(returnObj);
								throw new RuntimeException("#errorinprocessgltransnew");
						}
					 
				 }
				 try {
					 OffenderTransactions obj = new OffenderTransactions();
					 obj.setCaseloadId(paramBean.getCaseloadId());
					 obj.setOffenderId(paramBean.getOffenderId());
					 obj.setTxnPostingType(paramBean.getTxnPostingType());
					 obj.setTxnEntryDate(paramBean.getTxnEntryDate());
					 obj.setTxnId(paramBean.getTxnId());
					 obj.setTxnType(paramBean.getTxnType());
					 obj.setTxnEntryAmount(paramBean.getTxnEntryAmount());
					 if("CR".equals(paramBean.getTxnPostingType())) {
					 obj.setToSubAccountType(paramBean.getSubAccountType());
					 } else {
						 obj.setSubAccountType(paramBean.getSubAccountType());
					 }
					 //Procedure call
					 
					 Integer updateOffenderBalance = trustService.updateOffenderBalance(obj, paramBean.getCreateUserId());
					 
				 } catch (Exception e) {
						logger.error(this.getClass().getName()+"Error In mainProcess", e);
				}
				Integer holdDays = otdrdtfuRepository.curDays(paramBean.getTxnType());
				if ("R".equals(paramBean.getTxnUsage()) && holdDays > 0) {
					final Integer holdNumbers = otdrdtfuRepository.genTrustTrans("HOLD_NUMBER");
					try {
					
						//Procedure call
						commonService.processHold(paramBean.getTxnId(), paramBean.getCaseloadId(), paramBean.getOffenderId(),
								paramBean.getTxnType(), holdDays, paramBean.getSubAccountType(), paramBean.getTxnEntryAmount(),
								paramBean.getTxnEntryDesc(), paramBean.getTxnReferenceNumber(), paramBean.getTxnId(), holdNumbers, paramBean.getCreateUserId());
					} catch (Exception e) {
						return offenderTransactionsList;
					}
				} else {
					
					try {
					
						//Procedure call
					 financialService.doDeductionsFinancial(
								paramBean.getCaseloadId(), paramBean.getOffenderId(), paramBean.getOffenderBookId(),
								paramBean.getTxnType(), paramBean.getTxnId().longValue(), paramBean.getTxnEntryDate(),
								paramBean.getSubAccountType(), "Y", new BigDecimal(paramBean.getTxnEntryAmount()),
								0L, null, paramBean.getTxnEntrySeq(), paramBean.getCreateUserId());
					} catch (Exception e) {
						logger.error("financial.Do_Ductions_Financial", e);
					}
				}
				if ("R".equals(paramBean.getTxnUsage())) {
					try {
						//Procedure call
						deductionsService.getAcAndSetIndDate(paramBean.getOffenderId(), paramBean.getCaseloadId(), paramBean.getCreateUserId());
					} catch (Exception e) {
						 returnObj.setErrorMessage("#errorinsetacandsetdate");
							offenderTransactionsList.clear();
							offenderTransactionsList.add(returnObj);
							throw new RuntimeException("#errorinsetacandsetdate");
					}
				} else if ("D".equals(paramBean.getTxnUsage()) && "Y".equals(paramBean.getCheckInd())){
					try {
						//Procedure call
						deductionsService.getAcAndSetIndDate(paramBean.getOffenderId(), paramBean.getCaseloadId(), paramBean.getCreateUserId());;
					} catch (Exception e) {
						 returnObj.setErrorMessage("#errorinsetacandsetdate");
							offenderTransactionsList.clear();
							offenderTransactionsList.add(returnObj);
							throw new RuntimeException("#errorinsetacandsetdate");
					}
				} else if ("O".equals(paramBean.getTxnUsage())) {
					try {
						//Procedure call
						deductionsService.getAcAndSetIndDate(paramBean.getOffenderId(), paramBean.getCaseloadId(), paramBean.getCreateUserId());
					} catch (Exception e) {
						 returnObj.setErrorMessage("#errorinsetacandsetdate");
							offenderTransactionsList.clear();
							offenderTransactionsList.add(returnObj);
							throw new RuntimeException("#errorinsetacandsetdate");
					}
				}
				if ("Y".equals(paramBean.getChequeProductionFlag())) {
					try {
						BankChequeData obj = new BankChequeData();
						obj.setCaseloadId(paramBean.getCaseloadId());
						obj.setTxnId(paramBean.getTxnId().longValue());
						obj.setTxnEntryAmount(paramBean.getTxnEntryAmount()!=null?new BigDecimal(paramBean.getTxnEntryAmount()):null);
						obj.setChequeFlag("O");			
						obj.setStartTxnId(paramBean.getTxnEntryAmount()!=null?new BigDecimal(paramBean.getTxnEntryAmount()):null);
						obj.setEndTxnId(paramBean.getTxnEntryAmount()!=null?new BigDecimal(paramBean.getTxnEntryAmount()):null);
						obj.setPayeePersonId(paramBean.getPayeePersonId()!=null?new BigDecimal(paramBean.getPayeePersonId()):null);
						obj.setPayeeCorporateId(paramBean.getPayeeCorporateId()!=null?new BigDecimal(paramBean.getPayeeCorporateId()):null);
						obj.setPayeeNameText(paramBean.getPayeeNameText());
						obj.setSingleEntryFlag("1");
						obj.setBankAccountCode(null);
						obj.setModuleName("OTDRDTFU");
						obj.setTxnType(paramBean.getTxnType());
						//Procedure call
						trustService.insertIntoChequeData(obj, paramBean.getCreateUserId());
					} catch (Exception e) {
						returnObj.setErrorMessage("#othererrorininsertintochequedata");
						offenderTransactionsList.clear();
						offenderTransactionsList.add(returnObj);
						throw new RuntimeException("#othererrorininsertintochequedata");
					}
				}
				
			
		}
		
		offenderTransactionsList.clear();
		offenderTransactionsList.add(paramBean);
		return offenderTransactionsList;
	}

	private String chkAccountStatus(OffenderTransactions paramBean) {
		Integer txnNum = otdrdtfuRepository.genTrustTrans("TXN_ID");
		try {
			//Procedure call
			trustService.chkAccountStatus(paramBean.getCaseloadId(), new BigDecimal(paramBean.getOffenderId()));
		} catch (Exception e) {
			return "#othererrorinchktrustaccount";
		}
		if ("Y".equals(paramBean.getAccountClosedFlag())) {
			final String moduleName = "OTDOPCTA";
			final String txnDesc = "Re-Open Closed Account"; 
			final Double txnAmount = 0.0;
			final Date txnData = eliteDateService.getDBTime();
			final String rSlipFlag = "N";
			final String pAllowOverdrawn = "";
			final String pInfoNumber = "";
			final String pDeductionFlag = "";  
			final Double pPreDedAmount = 0.0;  
			final String pDeductionType = "";  
			final Integer pPayeeCorpId = null;   
			final Integer pPayeePersonId = null; 
			final Integer rTxnEntrySeq = 1;  

			String txnType = otdrdtfuRepository.getTxnType(moduleName, paramBean.getCaseloadId());
			try {
				//Procedure call
		//<String, Object> getSubActType = otdrdtfuRepository.getSubActType(moduleName, txnType, paramBean.getTxnUsage(), paramBean.getCaseloadId());
			OtddisbuProcessTransactionsBean subActType=new OtddisbuProcessTransactionsBean();
			subActType.setpModuleName(moduleName);
			subActType.setpTxnType(txnType);
			subActType.setpTxnUsage(paramBean.getTxnUsage());
			subActType.setTxnPostType(null);
			subActType.setSubActType(null);
			subActType.setpCaseloadId(paramBean.getCaseloadId());
			
			Map<String, Object> getSubActType =trustService.getSubActType(subActType);	
			if (getSubActType != null) {
				if (getSubActType.get("P_TXN_POST_TYPE") != null) {
					String pTxnPostType = getSubActType.get("P_TXN_POST_TYPE").toString();
					paramBean.setTxnPostingType(pTxnPostType);
						}
						if (getSubActType.get("P_SUB_ACT_TYPE") != null) {
					String pSubActType = getSubActType.get("P_SUB_ACT_TYPE").toString();
					paramBean.setSubAccountType(pSubActType);
						}
			} else {
				return "#othererroringetsubacttype";
			}
			} catch (Exception e) {
				logger.error("other error in get_sub_act_type", e);
				return "#othererroringetsubacttype";
			}
			try {
				/*otdrdtfuRepository.trustInsertIntoOffenderTrans(txnNum, 1, paramBean.getCaseloadId(), paramBean.getOffenderId(),
						paramBean.getOffenderBookId(), paramBean.getTxnPostingType(), txnType, txnDesc, txnAmount, trunc(txnData), paramBean.getSubAccountType(),
						pDeductionFlag, pPreDedAmount, pDeductionType, pPayeeCorpId, pPayeePersonId, pInfoNumber, rSlipFlag, pAllowOverdrawn
						);*/
				OffenderTransactions offTrans=new OffenderTransactions();
				
				offTrans.setTxnId(txnNum);
				offTrans.setTxnEntrySeq(1);
				offTrans.setCaseloadId(paramBean.getCaseloadId());
				offTrans.setOffenderId(paramBean.getOffenderId());
				offTrans.setOffenderBookId(paramBean.getOffenderBookId());
				offTrans.setTxnPostingType(paramBean.getTxnPostingType());
				offTrans.setTxnType(txnType);
				offTrans.setTxnEntryDesc(txnDesc);
				offTrans.setTxnEntryAmount(txnAmount);
				offTrans.setTxnEntryDate(trunc(txnData));
				offTrans.setSubAccountType( paramBean.getSubAccountType());
				offTrans.setDeductionFlag(pDeductionFlag);
				offTrans.setPreWithholdAmount(pPreDedAmount);
				offTrans.setDeductionType(pDeductionType);
				offTrans.setPayeeCorporateId(pPayeeCorpId);
				offTrans.setPayeePersonId(pPayeePersonId);
				offTrans.setInfoNumber(pInfoNumber);
				offTrans.setSlipPrintedFlag(rSlipFlag);
				offTrans.setCreateUserId(paramBean.getCreateUserId());
				trustService.insertIntoOffenderTrans(offTrans);
				
			} catch (Exception e) {
				logger.error("other error in insert into offender_trans", e);
				return "#othererrorininsertintooffendertrans";
			}
			
			try {
				trustService.processGlTransNew(paramBean.getCaseloadId(), txnType, null, txnAmount, txnNum, txnData, txnDesc, rTxnEntrySeq, moduleName, paramBean.getOffenderId()!=null?paramBean.getOffenderId().intValue():null, paramBean.getOffenderBookId()!=null?paramBean.getOffenderBookId().intValue():null, null, paramBean.getSubAccountType(), null, null, null, null,0l,paramBean.getCreateUserId());
			} catch (Exception e) {
				logger.error("other error in insert_into_offendertrans", e);
				return "#othererroringlnewtrans";
				
			}
			
		}
		return "Y";
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public SystemProfiles runReport(final SystemProfiles paramBean) {
		final SystemProfiles systemProfiles = otdrdtfuRepository.runReport(paramBean);
		return systemProfiles;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @
	 */
	public List<SystemProfiles> sysPflExecuteQuery(final SystemProfiles searchRecord) {
		return otdrdtfuRepository.sysPflExecuteQuery(searchRecord);

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
		final int liReturn = 0;
		return liReturn;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<Persons> cgfkOffTxnPayeePersonIdRecordGroup() {
		return otdrdtfuRepository.cgfkOffTxnPayeePersonIdRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<TransactionTypes> cgfkOffTxnTxnTypeRecordGroup(final String caseloadId, final String userName) {
		return otdrdtfuRepository.cgfkOffTxnTxnTypeRecordGroup(caseloadId, userName);

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<Corporates> cgfkOffTxnPayeeCorporateIRecordGroup() {
		return otdrdtfuRepository.cgfkOffTxnPayeeCorporateIRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public TransactionOperation txnTypeValidation(final String txnType, final String caseloadId, final String userName) {
		return otdrdtfuRepository.txnTypeValidation(txnType, caseloadId, userName);

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public Integer checkCaseloadValidation(final String caseloadId, final String agyLocId) {
		return otdrdtfuRepository.checkCaseloadValidation(caseloadId, agyLocId);

	}

	@Override
	public SystemProfiles otdrdtfuModlibValidationHWhenValidateItemSystemProfileC() {
		return otdrdtfuRepository.otdrdtfuModlibValidationHWhenValidateItemSystemProfileC();
	}

	@Override
	public List<OffenderDeductions> otdrdtfuModlibValidationHWhenValidateItemGetTxnFeeType(Long offenderId,
			String caseloadId, String transType) {
		return otdrdtfuRepository.otdrdtfuModlibValidationHWhenValidateItemGetTxnFeeType(offenderId, caseloadId,
				transType);
	}

	@Override
	public String otdrdtfuModlibValidationHWhenValidateTrustGetLowHighFlag() {
		return trustService.getLowHighFlag();
	}

	@Override
	public String otdrdtfuModlibValidationHWhenValidateTrustGetTransactionFee(Long offenderId, String caseloadId,
			Long offenderDeductionId, String transType, Double txnamount, String lowHighFlag) {
		return otdrdtfuRepository.otdrdtfuModlibValidationHWhenValidateTrustGetTransactionFee(offenderId, caseloadId,
				offenderDeductionId, transType, txnamount, lowHighFlag);
	}

	@Override
	public Map<String, Object> otdrdtfuModlibValidationHWhenValidateOffCreditLimit(Long OffenderId, String caseloadId,
			String offenderBookId, String transType, Double creditamt, String txnusg, String orcreflg, Double txnEntryAmount,String userName) {
		String balcreflg = "";
		Double overdrawn = 0.0;
		Double subBalance = 0.0;
		Double subBalanceOne = 0.0;
		String indigentflag = "";
		Double txnamount = txnEntryAmount;
		String indflag = "";
		Double indays = 0.0;
		Integer ctrWashSpecific = 0;
		Long offenderId = OffenderId;
		Date sysdate = eliteDateService.getDBTime();
		Date indate = null;
		String  periodtype = "";
		Date fromDate = null;
		Date toDate = null;
		Double loanTaken = 0.0;
		Double loanAvailable = 0.0;
		final Long trstcode = otdrdtfuGetDebitActCode(transType, caseloadId);
		final Map<String, Object> offenderSubBalance = otdrdtfuOffCreditLimitTrustGetOffenderSubBalance(caseloadId, OffenderId, null,
				creditamt, null, 0l, null, trstcode, "N", transType, "OTDRDTFU", null,userName);
		
	
		
		if (offenderSubBalance != null) {
			if (offenderSubBalance.get("P_AMOUNT") != null) {
				subBalance = Double.parseDouble(offenderSubBalance.get("P_AMOUNT").toString());
			} else {
				subBalance = 0.0;
			}
			
			if (offenderSubBalance.get("P_INDDAYS") != null) {
				indays = Double.parseDouble(offenderSubBalance.get("P_INDDAYS").toString()); 
			} else {
				indays = 0.0;
			}
			
			if (offenderSubBalance.get("P_INDDATE") != null) {
				indate = (Date) offenderSubBalance.get("P_INDDATE");
			} else {
				indate = null;
			}
							
		}
		
		
		final ReferenceCodes transactionTypesC = otdrdtfuOffCreditLimitTransactionTypesC(transType);
		String txnusage = transactionTypesC.getCode();
		String obligationtype = transactionTypesC.getDescription();
		if (obligationtype != null) {
			String crob = "N";
			try {
				crob = otdrdtfuOffCreditLimitFetchCrob(obligationtype);
				if (crob == null) {
					obligationtype = null;
				}
			} catch (Exception e) {
				obligationtype = null;
			}
		}
			if (obligationtype != null) {
				indigentflag =  otdrdtfuOffCreditLimitFetchIndigentflag(caseloadId, obligationtype);
			}
			subBalanceOne = subBalance;
			if ("Y".equals(indigentflag)) {
				if (indate == null) {
					if (subBalance - txnamount < 0) {
						indflag = "N";
					} 
				}else if (calculateDay(indate, trunc(sysdate)) < indays) {
						String ctrWashSpecificVal = otdrdtfuOffCreditLimitFetchCtrWashSpecific();
						if (ctrWashSpecificVal != null) {
							ctrWashSpecific = Integer.parseInt(ctrWashSpecificVal);
						} else {
							ctrWashSpecific = 0;
						}
						
						if (ctrWashSpecific > 0) {
							indflag = "Y";
						} else {
							indflag = "N";
						}
					}  else {
						indflag = "Y";
						subBalance = 0.0;
					}
					
			}	else {
						indflag = "Y";
					}
				
			
					overdrawn = subBalance - txnamount;
					if (overdrawn >= 0) {
						creditamt = subBalanceOne;
						balcreflg = "N";
					} else {
						if (obligationtype != null && "Y".equals(indflag)) {
							Double maxLimit = 0.0;
							ReferenceCodes maxLimitPeriodtype = null;
							try {
								
							
							maxLimitPeriodtype = otdrdtfuOffCreditLimitFetchMaxLimitPeriodtype(caseloadId, offenderId,
									 obligationtype);
							if (maxLimitPeriodtype != null) {
								maxLimit = Double.parseDouble(maxLimitPeriodtype.getCode());
								periodtype = maxLimitPeriodtype.getDescription();
							}
							} catch (Exception e) {
								logger.error("otdrdtfuOffCreditLimitFetchMaxLimitPeriodtype", e);
								maxLimitPeriodtype = null;
							}
							if (maxLimitPeriodtype == null) {
								try {
								maxLimitPeriodtype = otdrdtfuOffCreditLimitFetchMaxLimitPeriodtypeWithoutOffenderId(caseloadId, obligationtype);
								maxLimit = Double.parseDouble(maxLimitPeriodtype.getCode());
								periodtype = maxLimitPeriodtype.getDescription();
								} catch (Exception e) {
									logger.error("otdrdtfuOffCreditLimitFetchMaxLimitPeriodtype", e);
									maxLimit = 999999.00;
								}
							}
							if ("DAY".equals(periodtype)) {
								fromDate = trunc(sysdate);
								toDate = trunc(sysdate);
							} else if ("WEEK".equals(periodtype)) {
							String weekDayStr =	otdrdtfuOffCreditLimitFetchWeekday();
							fromDate = otdrdtfuOffCreditLimitFetchFromdate(weekDayStr);
							toDate = otdrdtfuOffCreditLimitFetchTodate(weekDayStr);
								
							} else if ("MONTH".equals(periodtype)) {
								fromDate = otdrdtfuOffCreditLimitFetchFromdateOnMonth();
								toDate = otdrdtfuOffCreditLimitFetchTodateOnMonth();
							}
							loanTaken =  getPreviousLoanAmountTotalTakenC(offenderId, obligationtype, caseloadId, fromDate,
									toDate);
							loanAvailable =  (maxLimit - loanTaken);
							String amountWrittenOffC =  otdrdtfuOffCreditLimitAmountWrittenOffC(caseloadId, offenderId, obligationtype, fromDate, toDate);
							loanAvailable += Double.parseDouble(amountWrittenOffC);
							creditamt = Double.parseDouble(new DecimalFormat("#.##").format(loanAvailable + subBalance));
				            if(creditamt >= txnamount) {
				            	balcreflg = "Y";
				            } else {
				            	balcreflg = "N";
				            }
						} else if (obligationtype != null && "N".equals(indflag)) {
							creditamt = subBalance;
							balcreflg = "N";
						}
						else {
							// Do not allow overdraft for transaction type != 'V'
							if ("V".equals(txnusage)) {
								creditamt = 0.0;
								balcreflg = "N";
								}
						}
					}
				
			
			
			Map<String, Object> returnObj = new HashMap<String, Object>();
			returnObj.put("orcreflg", balcreflg);
			returnObj.put("creditamt", creditamt);
		return returnObj;
	}

	@Override
	public ReferenceCodes otdrdtfuOffCreditLimitTransactionTypesC(String txnType) {
		return otdrdtfuRepository.otdrdtfuOffCreditLimitTransactionTypesC(txnType);
	}

	@Override
	public String otdrdtfuOffCreditLimitAmountWrittenOffC(String caseloadId, Long offenderId, String deductionType, Date fromDate, Date toDate) {
		return otdrdtfuRepository.otdrdtfuOffCreditLimitAmountWrittenOffC(caseloadId, offenderId, deductionType, fromDate, toDate);
	}

	@Override
	public Map<String, Object> otdrdtfuOffCreditLimitTrustGetOffenderSubBalance(String pCsldId, Long pOffId, String pSubActType,
			Double pAmount, Double pMinbal, Long pInddays, Date pInddate, Long pTrstcode, String pLockFlag,
			String txntype, String modName, String pSetupCsldId,String userId) {
		 Map<String, Object> obj=new HashMap<String, Object>();
		
		 try {
			//Procedure call
			 obj= trustService.getOffenderSubBalance(pCsldId, pOffId!=null?BigDecimal.valueOf(pOffId):null, pSubActType, pMinbal!=null?BigDecimal.valueOf(pMinbal):null,
					 pTrstcode!=null? BigDecimal.valueOf(pTrstcode):null, pLockFlag, txntype, modName, pSetupCsldId, userId);
		 }catch (Exception e) {
			 logger.error("Error at otdrdtfuOffCreditLimitTrustGetOffenderSubBalance ", e);
		}
		 return obj;
	}

	@Override
	public Long otdrdtfuGetDebitActCode(String txnType, String csldId) {
		return otdrdtfuRepository.otdrdtfuGetDebitActCode(txnType, csldId);
	}

	@Override
	public String otdrdtfuOffCreditLimitFetchCrob(String obligationType) {
		return otdrdtfuRepository.otdrdtfuOffCreditLimitFetchCrob(obligationType);
	}

	@Override
	public String otdrdtfuOffCreditLimitFetchIndigentflag(String caseloadId, String obligationType) {
		return otdrdtfuRepository.otdrdtfuOffCreditLimitFetchIndigentflag(caseloadId, obligationType);
	}
	
	@Override
	public String otdrdtfuOffCreditLimitFetchCtrWashSpecific() {
		return otdrdtfuRepository.otdrdtfuOffCreditLimitFetchCtrWashSpecific();
	}

	@Override
	public ReferenceCodes otdrdtfuOffCreditLimitFetchMaxLimitPeriodtype(String caseloadId, Long offenderId,
			String ObligationType) {
		return otdrdtfuRepository.otdrdtfuOffCreditLimitFetchMaxLimitPeriodtype(caseloadId, offenderId, ObligationType);
	}

	@Override
	public ReferenceCodes otdrdtfuOffCreditLimitFetchMaxLimitPeriodtypeWithoutOffenderId(String caseloadId,
			String ObligationType) {
		return otdrdtfuRepository.otdrdtfuOffCreditLimitFetchMaxLimitPeriodtypeWithoutOffenderId(caseloadId, ObligationType);
	}

	@Override
	public String otdrdtfuOffCreditLimitFetchWeekday() {
		return otdrdtfuRepository.otdrdtfuOffCreditLimitFetchWeekday();
	}

	@Override
	public Date otdrdtfuOffCreditLimitFetchFromdate(String weekDay) {
		return otdrdtfuRepository.otdrdtfuOffCreditLimitFetchFromdate(weekDay);
	}

	@Override
	public Date otdrdtfuOffCreditLimitFetchTodate(String weekDay) {
		return otdrdtfuRepository.otdrdtfuOffCreditLimitFetchTodate(weekDay);
	}

	@Override
	public Date otdrdtfuOffCreditLimitFetchFromdateOnMonth() {
		return otdrdtfuRepository.otdrdtfuOffCreditLimitFetchFromdateOnMonth();
	}

	@Override
	public Date otdrdtfuOffCreditLimitFetchTodateOnMonth() {
		return otdrdtfuRepository.otdrdtfuOffCreditLimitFetchTodateOnMonth();
	}

	@Override
	public Double getPreviousLoanAmountTotalTakenC(Long offenderId, String deductionType, String caseloadId,
			Date fromDate, Date toDate) {
		final Double totalTaken = otdrdtfuRepository.getPreviousLoanAmountTotalTakenC(offenderId, deductionType, caseloadId, fromDate,
				toDate);
		final Double totalReversed = getPreviousLoanAmountTotalReversedC(offenderId, deductionType, caseloadId, fromDate,
				toDate);
		final Double pAmount = totalTaken - totalReversed;
		return pAmount;
	}

	@Override
	public Double getPreviousLoanAmountTotalReversedC(Long offenderId, String deductionType, String caseloadId,
			Date fromDate, Date toDate) {
				return otdrdtfuRepository.getPreviousLoanAmountTotalReversedC(offenderId, deductionType, caseloadId, fromDate,
				toDate);
	}

	@Override
	public String onAmountBlurValidation(final OffenderTransactions paramBean) {
		final SystemProfiles systemProfileC = otdrdtfuModlibValidationHWhenValidateItemSystemProfileC();
		String returnObj = "";
		String lowHighFlag = "";
		Double txnFeeAmt = 0.0;
		Double totTxnFee = 0.0;
		Double creditamt = 0.0;
		String orcreflg = "";
		if (systemProfileC != null) {
			final String lvZeroTxn = systemProfileC.getProfileValue();

			if ("R".equals(paramBean.getTxnUsage()) && "Y".equals(lvZeroTxn)) {
				if (paramBean.getTxnEntryAmount() != null || paramBean.getTxnEntryAmount() < 0) {
					returnObj += "\n errorMessage : #nagativeTransactionAmount";
					return returnObj.trim();
				} 
				} else if (paramBean.getTxnEntryAmount() <= 0) {
					returnObj += "\n errorMessage : #zeroTransactionAmount";
					return returnObj.trim();
				}
			
			if ("D".equals(paramBean.getTxnUsage())) {
				try {
					
					//Procedure call
					lowHighFlag=trustService.getLowHighFlag();
				} catch (Exception e) {
					logger.error("Error at otdrdtfuModlibValidationHWhenValidateTrustGetLowHighFlag : ", e);
					returnObj += "\n errorMessage : #lowHeighError " + e.getMessage();
					return returnObj.trim();
				}
				totTxnFee = 0.0;
				final List<OffenderDeductions> getTxnFeeType = otdrdtfuModlibValidationHWhenValidateItemGetTxnFeeType(
						paramBean.getOffenderId(), paramBean.getCaseloadId(), paramBean.getTxnType());
				try {
					for (OffenderDeductions bean : getTxnFeeType) {

						//Procedure call
						Map<String, Object>	trustGetTransaction=	trustService.getTransactionFee(paramBean.getOffenderId(), paramBean.getCaseloadId(), bean.getOffenderDeductionId(), 
								paramBean.getTxnType(), paramBean.getTxnEntryAmount(), lowHighFlag, 0);
						String trustGetTransactionFee=(String) trustGetTransaction.get("TXN_FEE_AMT");
						
						if (trustGetTransactionFee != null && trustGetTransactionFee.split("\n").length > 1) {
							for (String trustGetTransactionFeeValue : trustGetTransactionFee.split("\n")) {
								if (trustGetTransactionFeeValue != null
										&& trustGetTransactionFeeValue.trim().startsWith("txnFeeAmt")) {
									if (trustGetTransactionFeeValue.split(":").length > 1
											&& trustGetTransactionFeeValue.split(":")[1] != null) {
										txnFeeAmt = Double
												.parseDouble(trustGetTransactionFeeValue.split(":")[1].trim());
										totTxnFee += txnFeeAmt;
									}
								}
							}
						}
					}
				} catch (Exception e) {
					returnObj += "\n errorMessage : #gettransactionfee " + e.getMessage();
					logger.error("Error at otdrdtfuModlibValidationHWhenValidateTrustGetTransactionFee : ", e);
					return returnObj.trim();
				}
				try {
					returnObj += "\n totTxnFee ["+totTxnFee+"]";
					final Map<String, Object> offCreditLimit = otdrdtfuModlibValidationHWhenValidateOffCreditLimit(
							paramBean.getOffenderId(), paramBean.getCaseloadId(),paramBean.getOffenderBookId()!=null ?paramBean.getOffenderBookId().toString():null,
							paramBean.getTxnType(), creditamt, paramBean.getTxnUsage(), orcreflg!=null?orcreflg.toString():null, paramBean.getTxnEntryAmount(),paramBean.getCreateUserId());
					if (offCreditLimit.get("orcreflg") != null) {
						orcreflg = (String) offCreditLimit.get("orcreflg");
					} else {
						orcreflg = "";
					}
					
					if (offCreditLimit.get("creditamt") != null) {
						creditamt = (Double) offCreditLimit.get("creditamt");
					} else {
						creditamt = 0.0;
					}
					
					if (creditamt >= paramBean.getTxnEntryAmount() + txnFeeAmt) {
						if ("N".equals(orcreflg)) {
							returnObj += "\n nbtTxnType : N";
						} else {
							returnObj += "\n nbtTxnType : Y";
						}

					} else {
						returnObj += "\n errorMessage : #insufficient";
						return returnObj.trim();
					}

				} catch (Exception e) {
					returnObj += "\n Exception : " + e.getMessage();
					logger.error("Error at otdrdtfuModlibValidationHWhenValidateOffCreditLimit ", e);
					return returnObj;
				}

			}

		}
		if (returnObj == null || returnObj.isEmpty()) {
			returnObj += "\n nbtTxnType : N";
		}
		return returnObj;
	}
	
	public static Date trunc(final Date date) {
		if (date != null) {
			final Calendar calender =  Calendar.getInstance();  
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
	public ChkFreezeDisbursements chkDisbursementFreeze(final ChkFreezeDisbursements chkFreezeDisbursements) {
		//Procedure call
		 String retval = trustService.chkFreezeDisbursements(chkFreezeDisbursements);
		 ChkFreezeDisbursements data = new ChkFreezeDisbursements();
		 data.setFrzFlag(retval);
		 return data;
	}
	
	public Long calculateDay(final Date startDate, final Date endDate) {
		if (startDate == null || endDate == null) {
			return 0l;
		} else {
			Date sDate = new Date(startDate.getTime());
			Date eDate = new Date(endDate.getTime());
			final LocalDate localStartDate = sDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			final LocalDate localEndDate = eDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			return  ChronoUnit.DAYS.between(localStartDate, localEndDate);
		}
		
	}

	@Override
	public String chkAccountStatus(final String caseloadId, final Long offenderId) {
		//Procedure call
		Map<String, Object> param = trustService.chkAccountStatus(caseloadId, new BigDecimal(offenderId));
		String var = (String) param.get("P_OPEN_AN_ACCOUNT");
		return var;
	}

	@Override
	public Integer reopenOffenerTrustAccount(final String caseloadId, final Long offenderId, final String userName) {
		return otdrdtfuRepository.reopenOffenerTrustAccount(caseloadId,offenderId, userName);
	}

	@Override
	public ReportBean otdrdtfugenerateotrdrecereport(final OtrdreceReportBean paramBean) {
		List<OtrdreceReportBean> result = new ArrayList<OtrdreceReportBean>();
		byte[] returnReport = null;
		result = otdrdtfuRepository.otdrdtfugenerateOtrdrecereport(paramBean);
		result.forEach(data -> {
			if (data.getAmt() != null) {
			final String txnAmount = otdrdtfuRepository.numberToWord(data.getAmt());
			data.setAmtText(txnAmount);
			}
		});
		String currSymbol = otdrdtfuRepository.getCurrencySymbol();
		Map<String, Object> param = new HashMap<>();
		param.put("cfCurr", currSymbol);
		returnReport = generateReport("OTRDRECE", param,result); 
		final ReportBean reportBean = new ReportBean();
		reportBean.setReport(returnReport);
		otdrdtfuRepository.reportRecievedUpdate(paramBean.getTxnId(), paramBean.getRecNum());
		return reportBean;
	}
	
	public ReportBean otdrdtfugenerateOtrreceireport(final OtrreceiReportBean paramBean) {
		List<OtrreceiReportBean> result = new ArrayList<OtrreceiReportBean>();
		byte[] returnReport = null;
		result = otdrdtfuRepository.otdrdtfugenerateOtrreceireport(paramBean);
		result.forEach(data -> {
			if (data.getAmt() != null) {
			final String txnAmount = otdrdtfuRepository.numberToWord(data.getAmt());
			data.setAmtText(txnAmount);
			}
		});
		String currSymbol = otdrdtfuRepository.getCurrencySymbol();
		Map<String, Object> param = new HashMap<>();
		param.put("cfCurr", currSymbol);
		returnReport = generateReport("OTRRECEI", param,result);
		final ReportBean reportBean = new ReportBean();
		reportBean.setReport(returnReport);
		otdrdtfuRepository.reportRecievedUpdate(paramBean.getTxnId(), paramBean.getRecNum());
		return reportBean;
	}
	
	public byte[] generateReport(final String reportName, final Map<String, Object> parameteres, List<?> fields) {
		byte[] returnReport = null;
		JasperPrint jasperPrint = null;
		try {
		final InputStream reportInStream = this.getClass().getClassLoader().getResourceAsStream("resource/jasperreports/" + reportName + ".jrxml");
		final JasperReport jasperReport = JasperCompileManager.compileReport(reportInStream);
		if ((fields != null && !fields.isEmpty())) {
			jasperPrint = JasperFillManager.fillReport(jasperReport, parameteres, new JRBeanCollectionDataSource(fields));
		} else {
			jasperPrint = JasperFillManager.fillReport(jasperReport, parameteres, new JREmptyDataSource());
		}
		returnReport = JasperExportManager.exportReportToPdf(jasperPrint);
		} catch (Exception e) {
			logger.error("Exception in generateReport : ", e);
			return null;
		}
		return returnReport;
	}
	
	Map<String, Object> processGlTransNew(String caseloadId, String txnType, Object object, Double txnEntryAmount,
			Integer txnId, Date txnEntryDate, String txnEntryDesc, Integer txnEntrySeq, String string, Long offenderId,
			Long offenderBookId, Object object2, String subAccountType, Integer payeePersonId, Integer payeeCorporateId,
			String payeeNameText, String string2) {
		final List<AccountCodes> postingC = otdrdtfuRepository.processGlTransNewPostingC(txnType, "OTDRDTFU", null, subAccountType);
		final Map<String, Object> returnMap = new HashMap<>();
		
		returnMap.put("P_GL_SQNC", 0);
		if (postingC != null) {
			final InsertGlTransNew param = new InsertGlTransNew();
			String firstTime = "Y";
			Double runTotal = 0.0;
			for ( AccountCodes data: postingC) {
				Double pstngAmount = txnEntryAmount;
				Integer pGlSeq = 1;
				returnMap.put("P_GL_SQNC", pGlSeq);
				if ("N".equals(data.getbPostingStatusFlag()) || "N".equals(data.getcPostingStatusFlag())) {
					break;
				}
				if ("Y".equals(firstTime)) {
					param.setpPostType("DR");
					if (data.getDrAccountCode() != null) {
					param.setpAccountCode(Long.valueOf(data.getDrAccountCode()));
					}
					param.setpAcntPosting(data.getaTxnPostingType());
					param.setpCsldId(caseloadId);
					param.setpTransType(txnType);
					if (txnId != null) {
					param.setpTransNumber(Long.valueOf(txnId));
					}
					param.setpTransDate(txnEntryDate);
					param.setpTransDesc(txnEntryDesc);
					param.setpTransSeq(txnEntrySeq);
					param.setpGlSqnc(pGlSeq);
					param.setpOffId(offenderId);
					param.setpOffBookId(offenderBookId);
					param.setpInfoNumber("");
					param.setpPayeePersonId(0l);
					param.setpPayeeCorporateId(0l);
					param.setpPayeeNameText("");
					param.setpRevrTxnId(0);
					param.setpRevrGlEntrySeq(0);
					param.setpRevrTxnEntrySeq(0);
					param.setpTxnRefNumber("");
					param.setpOffDedId(0l);
					param.setpTransAmount(pstngAmount);
					try {
					otdrdtfuRepository.trustInsertGltransNew(param);
					} catch (Exception e) {
						
					}
					 if (data.getTxnOperationSeq() == 99) {
						 pstngAmount = pstngAmount - runTotal;
					 } else {
						 pstngAmount = txnEntryAmount;
						 runTotal += pstngAmount;
					 }
					 pGlSeq++;
					 returnMap.put("P_GL_SQNC", pGlSeq);
					 param.setpPostType("CR");
						if (data.getDrAccountCode() != null) {
						param.setpAccountCode(Long.valueOf(data.getCrAccountCode()));
						}
						param.setpAcntPosting(data.getbTxnPostingType());
						param.setpCsldId(caseloadId);
						param.setpTransType(txnType);
						if (txnId != null) {
						param.setpTransNumber(Long.valueOf(txnId));
						}
						param.setpTransDate(txnEntryDate);
						param.setpTransDesc(txnEntryDesc);
						param.setpTransSeq(txnEntrySeq);
						param.setpGlSqnc(pGlSeq);
						param.setpOffId(offenderId);
						param.setpOffBookId(offenderBookId);
						param.setpInfoNumber("");
						param.setpPayeePersonId(0l);
						param.setpPayeeCorporateId(0l);
						param.setpPayeeNameText("");
						param.setpRevrTxnId(0);
						param.setpRevrGlEntrySeq(0);
						param.setpRevrTxnEntrySeq(0);
						param.setpTxnRefNumber("");
						param.setpOffDedId(0l);
						param.setpTransAmount(pstngAmount);
						try {
						otdrdtfuRepository.trustInsertGltransNew(param);
						} catch (Exception e) {
							
						}
						if (data.getcPostingStatusFlag() != null && "Y".equals(data.getcTxnPostingType())
							&& data.getdPostingStatusFlag() != null && "Y".equals(data.getdTxnPostingType())) {
							param.setpPostType("DR");
							if (data.getDrAccountCode() != null) {
							param.setpAccountCode(Long.valueOf(data.getDrAccountCode()));
							}
							param.setpAcntPosting(data.getcTxnPostingType());
							param.setpCsldId(caseloadId);
							param.setpTransType(txnType);
							if (txnId != null) {
							param.setpTransNumber(Long.valueOf(txnId));
							}
							param.setpTransDate(txnEntryDate);
							param.setpTransDesc(txnEntryDesc);
							param.setpTransSeq(txnEntrySeq);
							param.setpGlSqnc(pGlSeq);
							param.setpOffId(offenderId);
							param.setpOffBookId(offenderBookId);
							param.setpInfoNumber("");
							param.setpPayeePersonId(0l);
							param.setpPayeeCorporateId(0l);
							param.setpPayeeNameText("");
							param.setpRevrTxnId(0);
							param.setpRevrGlEntrySeq(0);
							param.setpRevrTxnEntrySeq(0);
							param.setpTxnRefNumber("");
							param.setpOffDedId(0l);
							param.setpTransAmount(txnEntryAmount);
							try {
							otdrdtfuRepository.trustInsertGltransNew(param);
							} catch (Exception e) {
								
							}
							
									pGlSeq++;
									returnMap.put("P_GL_SQNC", pGlSeq);
									param.setpPostType("CR");
									if (data.getDrAccountCode() != null) {
									param.setpAccountCode(Long.valueOf(data.getDrAccountCode()));
									}
									param.setpAcntPosting(data.getTxnPostingType());
									param.setpCsldId(caseloadId);
									param.setpTransType(txnType);
									if (txnId != null) {
									param.setpTransNumber(Long.valueOf(txnId));
									}
									param.setpTransDate(txnEntryDate);
									param.setpTransDesc(txnEntryDesc);
									param.setpTransSeq(txnEntrySeq);
									param.setpGlSqnc(pGlSeq);
									param.setpOffId(offenderId);
									param.setpOffBookId(offenderBookId);
									param.setpInfoNumber("");
									param.setpPayeePersonId(0l);
									param.setpPayeeCorporateId(0l);
									param.setpPayeeNameText("");
									param.setpRevrTxnId(0);
									param.setpRevrGlEntrySeq(0);
									param.setpRevrTxnEntrySeq(0);
									param.setpTxnRefNumber("");
									param.setpOffDedId(0l);
									param.setpTransAmount(txnEntryAmount);
									try {
									otdrdtfuRepository.trustInsertGltransNew(param);
									} catch (Exception e) {
										
									}
						}
					
					
				}
				firstTime = "N";
			}
		}
		return returnMap;
	}
	
	public void deductionGetAcAndSetIndDate(Long offenderId, String caseloadId, Date admissionDate) {
		
		List<String> lvCsldIdExists =  otdrdtfuRepository.getAcAndSetIndDateChkCaseloadC(caseloadId);
		Date lvIndigentDate = null; 
		Integer lvIndigentDaysLimit = 0;
		Double lvIndigencyLevel = 0.0;

		if (lvCsldIdExists != null) {
			List<Integer> chkIndAcC = otdrdtfuRepository.getAcAndSetIndDateChkIndAcC(offenderId);
			if(chkIndAcC != null) {
				for (Integer indAc: chkIndAcC) {
					//Procedure call
					deductionsService.updateIndigentDate(indAc, offenderId, caseloadId, caseloadId);
					
				}
			} else {
					SystemProfiles systemProfiles = otdrdtfuRepository.getAcAndSetIndDateSystemProfileC();
					if (systemProfiles != null) {
						lvIndigencyLevel = Double.valueOf(systemProfiles.getProfileValue());
						lvIndigentDaysLimit = Integer.valueOf(systemProfiles.getProfileValue2());
					}
					Integer lvTrustAccountCode = otdrdtfuRepository.getTrustAccountCodeC();
					Double lvSubAcctBalance = otdrdtfuRepository.sumOffSubActBalC(offenderId, lvTrustAccountCode);
					if (lvSubAcctBalance == null) {
						lvSubAcctBalance = 0.0;
					}
						if (lvSubAcctBalance <= lvIndigencyLevel) {
						
							if (admissionDate != null) {
								lvIndigentDate = admissionDate;
								lvIndigentDaysLimit = 0;
							} else {
								lvIndigentDate = otdrdtfuRepository.getAcAndSetIndDateMaxIndDateC(offenderId, lvTrustAccountCode);
								if (lvIndigentDate == null) {
									lvIndigentDate = trunc(eliteDateService.getDBTime());
								}
							otdrdtfuRepository.getAcAndSetIndUpdateOffenderSubAccounts(lvIndigentDate, lvIndigentDaysLimit, offenderId, lvTrustAccountCode);
							}
						} else {
							otdrdtfuRepository.getAcAndSetIndUpdateOffenderSubAccounts(null, null, offenderId, lvTrustAccountCode);
						}
					
			}
		}
	}

	@Override
	public String mainProcessAutoSubmitting() {
		return otdrdtfuRepository.mainProcessAutoSubmitting();
	}
	@Override
	public String deductionsChkOffenderDeductions(final String caseloasdId, final Long offenderId, final String txnType, final Integer shadowId) {
//		return otdrdtfuRepository.deductionsChkOffenderDeductions(caseloasdId, offenderId, txnType, shadowId);
		String str=null;
		try {
			str=deductionsService.chkOffenderDeductions(caseloasdId, offenderId, txnType, shadowId!=null?shadowId.longValue():null);
		}catch (Exception e) {
			logger.error("Error at deductionsChkOffenderDeductions ", e);
		}
		return str;
	}
	

}