package net.syscon.s4.inmate.trust.trustaccounts.impl;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.GetOffenderSubBalanceBean;
import net.syscon.s4.common.beans.OffenderTransactionsCommitBean;
import net.syscon.s4.common.beans.OtddisbuProcessTransactionsBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.SystemProfilesCommitBean;
import net.syscon.s4.common.beans.TransactionTypes;
import net.syscon.s4.core.EliteDateService;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.ChkFreezeDisbursements;
import net.syscon.s4.im.beans.Corporates;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.inmate.beans.OffenderDeductions;
import net.syscon.s4.inmate.trust.trustaccounts.OtddisbuRepository;
import net.syscon.s4.inmate.trust.trustaccounts.OtddisbuService;
import net.syscon.s4.pkgs.otddisbu.OtddisbuPkgService;
import net.syscon.s4.pkgs.trust.TrustService;
/**
 * Class OtddisbuServiceImpl
 */
@Service
public class OtddisbuServiceImpl extends BaseBusiness implements OtddisbuService{
	

@Autowired
private OtddisbuRepository otddisbuRepository;
@Autowired
private EliteDateService eliteDateService;

@Autowired
private TrustService trustService;

@Autowired
private OtddisbuPkgService otddisbuPkgService;




/**Fetch the records from database table
 *
 * @param searchRecord
 *
 * @throws SQLException
 */
public List<OffenderTransactions> offTxn1ExecuteQuery(final OffenderTransactions searchRecord) {
		return otddisbuRepository.offTxn1ExecuteQuery(searchRecord);

}

/**Insert the records from database table
 *
 * @param lstOFF_TXN1
 *
 * @throws SQLException
 */
@Transactional(rollbackFor=Exception.class)
public List<OffenderTransactions> offTxn1Commit(final OffenderTransactionsCommitBean commitBean) {
		List<OffenderTransactions> resultBean = new ArrayList<>();
			//insertRecords
			if (commitBean.getInsertList() != null	&& commitBean.getInsertList().size() > 0) {
				commitBean.getInsertList().forEach(obj -> {
					obj.setCreateUserId(commitBean.getCreateUserId());
				});
				resultBean = keyCommit(commitBean.getInsertList());
			}
			return resultBean;
}

private List<OffenderTransactions> keyCommit(List<OffenderTransactions> insertList) {
	String lChequeFlag = "";
	String lReceiptFlag = "";
	String lTxnUsage = "";
	String lReceiptNumber = "";
	Integer lTxnEntrySeq = 0;
	Integer txnId = otddisbuRepository.genTrustTrans("TXN_ID");
	OtddisbuProcessTransactionsBean otddissbutProcTran = new OtddisbuProcessTransactionsBean();
	if (insertList != null && !insertList.isEmpty()) {
		Map<String, Object> lModuleFlagsCr = 
				otddisbuRepository.lModuleFlagsCur(insertList.get(0).getCaseloadId(), insertList.get(0).getTxnType());
		if (lModuleFlagsCr != null) {
			if (lModuleFlagsCr.get("CHEQUE_PRODUCTION_FLAG") != null) {
				lChequeFlag = lModuleFlagsCr.get("CHEQUE_PRODUCTION_FLAG").toString();
			}
		if (lModuleFlagsCr.get("RECEIPT_PRODUCTION_FLAG") != null) {
			lReceiptFlag = lModuleFlagsCr.get("RECEIPT_PRODUCTION_FLAG").toString();			
			}
		if (lModuleFlagsCr.get("TXN_USAGE") != null) {
			lTxnUsage = lModuleFlagsCr.get("TXN_USAGE").toString();
		}
		}
		for (OffenderTransactions bean: insertList) {
			bean.setTxnId(txnId);
			lTxnEntrySeq++;
			if ("Y".equals(lReceiptFlag)) {
				Integer recpNo = otddisbuRepository.genTrustTrans("SEQUENCE_" + bean.getCaseloadId() + "_D");
				lReceiptNumber = bean.getCaseloadId() + recpNo;
				while (lReceiptNumber.length() < 10) {
					lReceiptNumber = "0" + lReceiptFlag;
				}
			}
				otddissbutProcTran.setpCaseloadId(bean.getCaseloadId());
				otddissbutProcTran.setpTxnType(bean.getTxnType());
				otddissbutProcTran.setpTxnDesc(bean.getTxnEntryDesc());
				otddissbutProcTran.setpTxnUsage(lTxnUsage);
				otddissbutProcTran.setpTxnId(txnId);
				otddissbutProcTran.setpOffenderId(bean.getOffenderId());
				otddissbutProcTran.setpTxnEntryAmount(doubleToBigDecimal(bean.getTxnEntryAmount()));
				otddissbutProcTran.setpPayeePersonId(bean.getPayeePersonId());
				otddissbutProcTran.setpPayeeCorpId(bean.getPayeeCorporateId());
				otddissbutProcTran.setpPayeeNameText(bean.getPayeeNameText());
				otddissbutProcTran.setpReceiptFlag(lReceiptFlag);
				otddissbutProcTran.setpTxnRefNumber(bean.getTxnReferenceNumber());
				otddissbutProcTran.setpChequeProdFlag(lChequeFlag);
				otddissbutProcTran.setpTxnEntrySeq(lTxnEntrySeq);
				otddissbutProcTran.setpTxnFee(doubleToBigDecimal(bean.getTotTxnFee()));
				otddissbutProcTran.setpReceiptNum(lReceiptNumber);
				otddissbutProcTran.setCreateUserId(bean.getCreateUserId());
				bean.setTxnId(txnId);
				bean.setReceiptNumber(lReceiptNumber);	
				try {
				//Map<String, Object> commitResult = otddisbuRepository.otddisbuTranactionProcess(otddissbutProcTran);
					//Procedure Call
				Map<String, Object> commitResult = otddisbuPkgService.processTransactions(otddissbutProcTran, bean.getCreateUserId());
				if (commitResult != null) {
					if (commitResult.get("P_ERROR_MESSAGE") != null && !commitResult.get("P_ERROR_MESSAGE").toString().isEmpty()) {
						throw new RuntimeException(commitResult.get("P_ERROR_MESSAGE").toString());
					}
				}
				} catch (Exception e) {
					throw new RuntimeException(); 
				}
				
				
			
			}
		
	}
	return insertList;
}

/**Fetch the records from database table
 *
 * @param searchRecord
 *
 * @throws SQLException
 */
public List<OffenderTransactions> offTxnExecuteQuery(final OffenderTransactions searchRecord) {
		return otddisbuRepository.offTxnExecuteQuery(searchRecord);

}

/**Insert the records from database table
 *
 * @param lstOFF_TXN
 *
 * @throws SQLException
 */
@Transactional
public Integer offTxnCommit1(final OffenderTransactionsCommitBean commitBean) {
		int liReturn = 0;
			//insertRecords
			if (commitBean.getInsertList() != null	&& commitBean.getInsertList().size() > 0) {
			}
			return liReturn;
}

/**Fetch the records from database table
 *
 * @param searchRecord
 *
 * @throws SQLException
 */
public List<SystemProfiles> sysPflExecuteQuery(final SystemProfiles searchRecord) {
		return otddisbuRepository.sysPflExecuteQuery(searchRecord);

}

/**
 * This method is used to execute a record group
 *
 *@throws SQLException
*/
public List<ReferenceCodes> cgfkOffTxn1PayeePersonIdRecordGroup() {
	List<ReferenceCodes> resultBean = otddisbuRepository.cgfkOffTxn1PayeePersonIdRecordGroup();
	if (resultBean != null) {
		resultBean.forEach(data -> {
			data.setDomain(data.getDescription());
			data.setDescription(data.getCode());
		});
	}
		return resultBean;

}
/**
 * This method is used to execute a record group
 *
 *@throws SQLException
*/
public List<TransactionTypes> cgfkOffTxn1TxnTypeRecordGroup(final String caseloadId,final String caseloadType ) {
	List<TransactionTypes> list=new ArrayList<TransactionTypes>();
	list= otddisbuRepository.cgfkOffTxn1TxnTypeRecordGroup(caseloadId,caseloadType);
	list.forEach(result->{
		result.setCode(result.getTxnType());
		result.setDescription(result.getDescription());
	});
	return list;

}


public String checkproductionFlag(final String caseloadId, final String txCode) {
	return otddisbuRepository.checkproductionFlag(caseloadId, txCode);
}
/**
 * This method is used to execute a record group
 *
 *@throws SQLException
*/
public List<ReferenceCodes> cgfkOffTxn1PayeeCorporateRecordGroup() {
		return otddisbuRepository.cgfkOffTxn1PayeeCorporateRecordGroup();

}

@Override
public List<Object> CgwhenNewFormInstance() {
	return null;
}


@Override
public Integer offTxnCommit(OffenderTransactionsCommitBean CommitBean) {
	return null;
}

@Override
public TransactionTypes CgfkchkOffTxn1OffTxnTxn(TransactionTypes paramBean) {
	return null;
}

@Override
public List<Offenders> CgfkchkOffTxnOffTxnOff(Offenders paramBean) {
	return null;
}

@Override
public Corporates CgfkchkOffTxn1OffTxnCorp(Corporates paramBean) {
	return null;
}

@Override
public Integer sysPflCommit(SystemProfilesCommitBean commitBean) {
	return null;
}

@Override
public Integer getDebitActCode(final String txnType, final String caseloadId) {
	return otddisbuRepository.getDebitActCode(txnType, caseloadId);
}

@Override
public GetOffenderSubBalanceBean getOffenderSubBalance(GetOffenderSubBalanceBean param) {
	GetOffenderSubBalanceBean result = new GetOffenderSubBalanceBean();
	if ("Y".equals(param.getpLockFlag())) {
	result = otddisbuRepository.getOffenderSubBalanceBeanFetBalOne(param.getpCsldId(), param.getpOffId(), param.getpTrstcode());
	} else {
	result = otddisbuRepository.getOffenderSubBalanceBeanFetBalTwo(param.getpCsldId(), param.getpOffId(), param.getpTrstcode());
	}
	if (result.getpInddate() == null && result.getpMinbal() == null) {
		Integer inDays = otddisbuRepository.getInDays(param.getpCsldId(), param.getpOffId(), param.getpTrstcode(), param.getCreateUserId());
		result.setpInddays(inDays);
		if (result.getpInddate() == null) {
			inDays = otddisbuRepository.getInDaysTwo(param.getpCsldId(), param.getpTrstcode());
			result.setpInddays(inDays);
		}
		Integer minBal = otddisbuRepository.getMinBal(param.getpCsldId(), param.getpOffId(), param.getpTrstcode(),param.getCreateUserId());
		result.setpMinbal(new BigDecimal(minBal));
	}
	
	return result;
}

public BigDecimal getOffenderSependableBalance(final Long offenderId, final String caseloadId, final String txnType,String UserId) {
	GetOffenderSubBalanceBean result = new GetOffenderSubBalanceBean();
	result.setpOffId(offenderId);
	result.setpCsldId(caseloadId);
	Integer trustCode = getDebitActCode(txnType,caseloadId);
	result.setpTrstcode(trustCode);
	result.setpLockFlag("Y");
	result.setCreateUserId(UserId);
	result = getOffenderSubBalance(result);
	return result.getpAmount() == null ? BigDecimal.ZERO : result.getpAmount();
}

public Map<String, Object> getCreditEligibility(OffenderTransactions param) {
	Map<String, Object> resultMap = new HashMap<String, Object>();
	Long offenderBookId = otddisbuRepository.getOffenderBookId(param.getOffenderId(),param.getCreateUserId());
	if (offenderBookId == null) {
		offenderBookId = param.getOffenderBookId();
	}
	String txnUsage = otddisbuRepository.getTxnUsage(param.getTxnType(), param.getCaseloadId(), param.getCreateUserId());
	String postingType = "";
	String subActType = "";
	Double totTxnFee = 0.0;	
	Double txnFeeAmount = 0.0;
	BigDecimal creditamt = BigDecimal.ZERO;
	String orcreflg = "";
	Map<String, Object> getSubActType =  getSubActType("OTDDISBU", param.getTxnType(), param.getCaseloadId(),param.getCreateUserId());
	if (getSubActType != null) {
			if (getSubActType.get("DR") != null) {
				postingType = getSubActType.get("DR").toString();
			}
			if (getSubActType.get("SUB_ACCOUNT_TYPE") != null) {
				subActType = getSubActType.get("SUB_ACCOUNT_TYPE").toString();
			}
			
	}
	String lowHighFlag = getLowHighFlag();
	List<OffenderDeductions> dftType =  getTxnFeeType(param.getOffenderId(), param.getCaseloadId(), param.getTxnType());
	for (OffenderDeductions data : dftType) {
		Map<String, Object> getTransactionFee =  trustGetTransactionFee(param.getOffenderId(), param.getCaseloadId(), data.getOffenderDeductionId(),
				param.getTxnType(), param.getTxnEntryAmount(), lowHighFlag );
		if (getTransactionFee != null) {
			if (getTransactionFee.get("TXN_FEE_AMT") != null ) {
				txnFeeAmount = (double) getTransactionFee.get("TXN_FEE_AMT");
			}
		}
		totTxnFee += txnFeeAmount;
		
	}
	Map<String, Object> offCreditLimit = offCreditLimit(param.getOffenderId(), param.getCaseloadId(), offenderBookId,
			param.getTxnType(), txnUsage, new BigDecimal(param.getTxnEntryAmount()),param.getCreateUserId());
	if (offCreditLimit != null) {
		if (offCreditLimit.get("creditAmt") != null) {
			creditamt = (BigDecimal) offCreditLimit.get("creditAmt");
		}
		if (offCreditLimit.get("balcreflg") != null) {
			orcreflg = offCreditLimit.get("balcreflg").toString();
		}
		
	}
	if (totTxnFee == null) {
		totTxnFee = 0.0;
	}
	resultMap.put("nbtPreWithholdAmount2", totTxnFee);
	if (creditamt.compareTo(new BigDecimal(param.getTxnEntryAmount() + totTxnFee).setScale(2, BigDecimal.ROUND_HALF_UP)) >= 0) {
		if ("Y".equals(orcreflg)) {
			resultMap.put("nbtSlipPrintedFlag", "Y");
		} else {
			resultMap.put("nbtSlipPrintedFlag", "N");
		}
	} else {
		resultMap.put("nbtSlipPrintedFlag", "N");
		resultMap.put("error", "Offender has insufficient funds to complete this transactions");
	}
	return resultMap;
}

private Map<String, Object> offCreditLimit(Long offenderId, String caseloadId, Long offenderBookId, String txnType,
		String txnUsage, BigDecimal txnEntryAmount,String userId) {
	Integer trstCode = getDebitActCode(txnType, caseloadId);
	GetOffenderSubBalanceBean getOffSubBal = new GetOffenderSubBalanceBean();
	String txnusage = null;
	String obligationtype = null;
	String indigentflag = null;
	BigDecimal subBalance = new BigDecimal(0.0);
	BigDecimal subBalanceOne = new BigDecimal(0.0);
	Date inDate = null;
	Integer inDays = null;
	String indflag = null;
	String balcreflg = "";
	String periodtype = "";
	Date fromdate = null;
	Date todate = null;
	Integer weekDay = null;
	BigDecimal overdrawn = BigDecimal.ZERO;
	BigDecimal loanTaken = BigDecimal.ZERO;
	BigDecimal amountWrittenOff = BigDecimal.ZERO; 
	BigDecimal loanAvailable = BigDecimal.ZERO;
	getOffSubBal.setpCsldId(caseloadId);
	getOffSubBal.setpOffId(offenderId);
	getOffSubBal.setpTrstcode(trstCode);
	getOffSubBal.setpLockFlag("N");
	getOffSubBal.setCreateUserId(userId);
	getOffSubBal = getOffenderSubBalance(getOffSubBal);
	
	BigDecimal creditAmt = BigDecimal.ZERO;
	if (getOffSubBal != null) {
		subBalance = getOffSubBal.getpAmount();
		inDate = getOffSubBal.getpInddate();
		inDays = getOffSubBal.getpInddays();
	}
	Map<String, Object> creditObligation = otddisbuRepository.getTxnCreditObligation(txnType);
	if (creditObligation != null) {
		if (creditObligation.get("TXN_USAGE") != null) {
			txnusage = creditObligation.get("TXN_USAGE").toString();
		}
		if (creditObligation.get("CREDIT_OBLIGATION_TYPE") != null) {
			obligationtype = creditObligation.get("CREDIT_OBLIGATION_TYPE").toString();
		}
	}
	if (obligationtype != null) {
		String crob = null;
		crob = otddisbuRepository.getCrob(obligationtype);
		if (crob == null) {
			obligationtype = null;
		}
	}
	if (obligationtype != null) {
		indigentflag = otddisbuRepository.getIndegentFlag(caseloadId,obligationtype);
	}
	subBalanceOne = subBalance;
	if (inDays == null) {
		inDays = 0;
	}
	if ("Y".equals(indigentflag)) {
		Long remaingDays = calculateDay(trunc(eliteDateService.getDBTime()), inDate);
		if (remaingDays == null) {
			remaingDays = 0l;
		}
		remaingDays = Math.abs(remaingDays);
		if (remaingDays >= inDays) {
			indflag = "Y";
			subBalance = BigDecimal.ZERO;
		} else {
			Integer ctrWashSpecific =  otddisbuRepository.ctrWashSpecific();
			if (ctrWashSpecific != null && ctrWashSpecific > 0) {
				indflag = "Y";
				
			} else { 
				
				if (subBalance.subtract(txnEntryAmount.setScale(2, BigDecimal.ROUND_HALF_UP)).compareTo(BigDecimal.ZERO) == -1 ) {
					indflag = "N";
			}
		}
			}
	} else {
		indflag = "Y";
	}
	overdrawn = subBalance.subtract(txnEntryAmount).setScale(2, BigDecimal.ROUND_HALF_UP);
	
	if (overdrawn.compareTo(BigDecimal.ZERO) >= 0) {
		creditAmt = subBalanceOne;
		balcreflg = "N";
		
	} else {
		if (obligationtype != null && "Y".equals(indflag)) {
			BigDecimal maxLimit = BigDecimal.ZERO;
			BigDecimal amountSpent = BigDecimal.ZERO;
			BigDecimal limitAvail = BigDecimal.ZERO;
			Map<String, Object> getLimitAmountAndPeriodType =
					otddisbuRepository.getLimitAmountAndPeriodType(offenderId, caseloadId, obligationtype);
			if (getLimitAmountAndPeriodType == null) {
				getLimitAmountAndPeriodType =
						otddisbuRepository.getLimitAmountAndPeriodTypeOne(caseloadId, obligationtype);
				if (getLimitAmountAndPeriodType == null) {
					maxLimit = new BigDecimal(999999.00);
				} else {
					if (getLimitAmountAndPeriodType.get("LIMIT_AMOUNT") != null) {
						maxLimit =  new BigDecimal(getLimitAmountAndPeriodType.get("LIMIT_AMOUNT").toString());
					}
					if (getLimitAmountAndPeriodType.get("PERIOD_TYPE") != null) {
						periodtype = getLimitAmountAndPeriodType.get("PERIOD_TYPE").toString();
					}
				}
			} else {
				if (getLimitAmountAndPeriodType.get("LIMIT_AMOUNT") != null) {
					maxLimit =  new BigDecimal(getLimitAmountAndPeriodType.get("LIMIT_AMOUNT").toString());
				}
				if (getLimitAmountAndPeriodType.get("PERIOD_TYPE") != null) {
					periodtype = getLimitAmountAndPeriodType.get("PERIOD_TYPE").toString();
				}
			}
			if ("DAY".equals(periodtype)) {
				fromdate = trunc(eliteDateService.getDBTime());
				todate = trunc(eliteDateService.getDBTime());
			} else if ("WEEK".equals(periodtype)) {
				weekDay =  otddisbuRepository.getWeekDay();
				fromdate = otddisbuRepository.getfromDate(weekDay);
				todate = otddisbuRepository.getToDate(weekDay);
			} else if ("MONTH".equals(periodtype)) {
				fromdate = otddisbuRepository.getfromDateOne();
				todate = otddisbuRepository.getToDateOne();
			}
			loanTaken  = getPreviousLoanAmount(fromdate,todate,obligationtype, offenderId, caseloadId);
			if (loanTaken == null) {
				loanTaken = BigDecimal.ZERO;
			}
			loanAvailable = maxLimit.subtract(loanTaken);
			amountWrittenOff = otddisbuRepository.getAmountWrittenOff(caseloadId, offenderId, fromdate, todate, obligationtype);
			loanAvailable = loanAvailable.add(amountWrittenOff);
			creditAmt = loanAvailable.add(subBalance);
			if (creditAmt.compareTo(txnEntryAmount) >= 0) {
				balcreflg = "Y";
			} else {
				balcreflg = "N";
			}
		} else {
			if ("V".equals(txnusage)) {
				creditAmt = BigDecimal.ZERO;
				balcreflg = "N";
			}
		}
	}
	Map<String, Object> returnObj = new HashMap<>();
	returnObj.put("creditAmt", creditAmt);
	returnObj.put("balcreflg", balcreflg);
	
	return returnObj;
}

private BigDecimal getPreviousLoanAmount(Date fromdate, Date todate, String obligationtype, Long offenderId, String caseloadId) {
	BigDecimal totalTaken  = BigDecimal.ZERO;
	BigDecimal totalReversed  = BigDecimal.ZERO;
	totalTaken = otddisbuRepository.getTotalTaken(offenderId, obligationtype, caseloadId, fromdate, todate);
	totalReversed = otddisbuRepository.getTotalReversed(offenderId, obligationtype, caseloadId, fromdate, todate);
	if (totalTaken == null) {
		totalTaken = BigDecimal.ZERO;
	}
	if (totalReversed == null) {
		totalReversed = BigDecimal.ZERO;
	}
	return totalTaken.subtract(totalReversed);
}

private Map<String, Object> trustGetTransactionFee(Long offenderId, String caseloadId, Long offenderDeductionId,
		String txnType, Double txnEntryAmount, String lowHighFlag) {
//		return otddisbuRepository.trustGetTransactionFee(offenderId,caseloadId,offenderDeductionId,txnType, txnEntryAmount,
//				lowHighFlag);
	// Produre Call
	return trustService.getTransactionFee(offenderId, caseloadId, offenderDeductionId, txnType, txnEntryAmount, lowHighFlag, null);
}

private List<OffenderDeductions> getTxnFeeType(Long offenderId, String caseloadId, String txnType) {
		return otddisbuRepository.getTxnFeeType(offenderId, caseloadId, txnType);
}

private String getLowHighFlag() {
//	return otddisbuRepository.getLowHighFlag();
	//Procedure Call
	return trustService.getLowHighFlag();
}

private Map<String, Object> getSubActType(String moduleName, String txnType, String caseloadId,String userId) {
	return otddisbuRepository.getSubActType(moduleName, txnType, caseloadId,userId);
}

public Long calculateDay(final Date startDate, final Date endDate) {
	if (startDate == null || endDate == null) {
		return 0l;
	} else {
		final LocalDate localStartDate = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		final LocalDate localEndDate = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		return  ChronoUnit.DAYS.between(localStartDate, localEndDate);
	}
	
}
public Date trunc(final Date date) {
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

	BigDecimal doubleToBigDecimal(final Double data) {
		if (data != null) {
			return new BigDecimal(data).setScale(2, BigDecimal.ROUND_HALF_UP); 
		} else {
			return null;
		}
	}

	@Override
	public String getVProValue() {
		return otddisbuRepository.getVProValue();
	}

	@Override
	public ChkFreezeDisbursements chkDisbursementFreeze(final ChkFreezeDisbursements chkFreezeDisbursements) {
		//return otddisbuRepository.chkDisbursementFreeze(chkFreezeDisbursements);
		// Procedure call
		ChkFreezeDisbursements returnObj = chkFreezeDisbursements;
		String FrzFlag = trustService.chkFreezeDisbursements(chkFreezeDisbursements);
		if (FrzFlag != null) {
			returnObj.setFrzFlag(FrzFlag);
		}
		return returnObj;
	}


}