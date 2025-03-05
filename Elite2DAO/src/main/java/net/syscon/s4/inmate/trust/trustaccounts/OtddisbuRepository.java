package net.syscon.s4.inmate.trust.trustaccounts;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.syscon.s4.common.beans.GetOffenderSubBalanceBean;
import net.syscon.s4.common.beans.OtddisbuProcessTransactionsBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.TransactionTypes;
import net.syscon.s4.im.beans.ChkFreezeDisbursements;
import net.syscon.s4.im.beans.Corporates;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.inmate.beans.OffenderDeductions;
import net.syscon.s4.inst.booking.beans.Persons;

/**
 * Interface OtddisbuRepository
 */
public interface OtddisbuRepository {
	Corporates cgfkchkOffTxn1OffTxnCorp(Corporates paramBean);

	TransactionTypes cgfkchkOffTxn1OffTxnTxn(TransactionTypes paramBean);

	Persons cgfkchkOffTxn1OffTxnPer(Persons paramBean);

	Offenders cgfkchkOffTxnOffTxnOff(Offenders paramBean);

	List<OffenderTransactions> offTxn1ExecuteQuery(OffenderTransactions objOffenderTransactions);

	// List<Object> cgwhenNewFormInstance(SysDual paramBean);

	List<SystemProfiles> sysPflExecuteQuery(SystemProfiles objSystemProfiles);

	// List<MMCrp.corporateName> cgfkOffTxn1PayeeCorporateRecordGroup();

	List<ReferenceCodes> cgfkOffTxn1PayeePersonIdRecordGroup();

	Integer offTxn1InsertOffenderTransactions(List<OffenderTransactions> lstOffenderTransactions);

	List<TransactionTypes> cgfkOffTxn1TxnTypeRecordGroup(String caseloadId, String caseloadType);

	List<OffenderTransactions> offTxnExecuteQuery(OffenderTransactions searchRecord);

	String checkproductionFlag(String caseloadId, String txCode);
	
	Integer getDebitActCode(String txnType, String caseloadId);

	GetOffenderSubBalanceBean getOffenderSubBalanceBeanFetBalOne(String getpCsldId, Long getpOffId,
			Integer getpTrstcode);
	
	GetOffenderSubBalanceBean getOffenderSubBalanceBeanFetBalTwo(String getpCsldId, Long getpOffId,
			Integer getpTrstcode);
	
	Integer getInDays(String getpCsldId, Long getpOffId,Integer getpTrstcode,String userId);
	
	Integer getInDaysTwo(String getpCsldId, Integer getpTrstcode);
	
	Integer getMinBal(String getpCsldId, Long getpOffId,Integer getpTrstcode,String userId);

	Long getOffenderBookId(Long offenderId, String userId);

	String getTxnUsage(String txnType, String caseloadId,String userId);

	Map<String, Object> getSubActType(String moduleName, String txnType, String caseloadId,String userId);

	String getLowHighFlag();

	List<OffenderDeductions> getTxnFeeType(Long offenderId, String caseloadId, String txnType);

	Map<String, Object> trustGetTransactionFee(Long offenderId, String caseloadId, Long offenderDeductionId,
			String txnType, Double txnEntryAmount, String lowHighFlag);

	Map<String, Object> getTxnCreditObligation(String txnType);

	String getCrob(String obligationtype);

	String getIndegentFlag(String caseloadId, String obligationtype);

	Integer ctrWashSpecific();

	Map<String, Object> getLimitAmountAndPeriodType(Long offenderId, String caseloadId, String obligationtype);

	Map<String, Object> getLimitAmountAndPeriodTypeOne(String caseloadId, String obligationtype);

	Integer getWeekDay();

	Date getfromDate(Integer weekDay);

	Date getToDate(Integer weekDay);

	Date getfromDateOne();

	Date getToDateOne();

	BigDecimal getAmountWrittenOff(String caseloadId, Long offenderId, Date fromdate, Date todate,
			String obligationtype);

	BigDecimal getTotalTaken(Long offenderId, String obligationtype, String caseloadId, Date fromdate, Date todate);

	BigDecimal getTotalReversed(Long offenderId, String obligationtype, String caseloadId, Date fromdate, Date todate);

	Integer genTrustTrans(String seqId);

	Map<String, Object> lModuleFlagsCur(String caseloadId, String txnType);

	Map<String, Object> otddisbuTranactionProcess(OtddisbuProcessTransactionsBean otddissbutProcTran);

	List<ReferenceCodes> cgfkOffTxn1PayeeCorporateRecordGroup();
	
	String getVProValue();

	ChkFreezeDisbursements chkDisbursementFreeze(ChkFreezeDisbursements chkFreezeDisbursements);
		

}
