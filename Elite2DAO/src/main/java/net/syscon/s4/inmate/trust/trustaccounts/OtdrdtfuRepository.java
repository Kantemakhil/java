package net.syscon.s4.inmate.trust.trustaccounts;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.syscon.s4.common.InsertGlTransNew;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.OtrdreceReportBean;
import net.syscon.s4.common.beans.OtrreceiReportBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.TransactionTypes;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.im.beans.ChkFreezeDisbursements;
import net.syscon.s4.im.beans.Corporates;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.im.beans.SysDual;
import net.syscon.s4.im.beans.TransactionOperation;
import net.syscon.s4.inmate.beans.OffenderDeductions;
import net.syscon.s4.inst.booking.beans.Persons;

/**
 * Interface OtdrdtfuRepository
 */
public interface OtdrdtfuRepository {
	TransactionTypes cgfkchkOffTxnOffTxnTxn(TransactionTypes paramBean);

	List<Corporates> cgfkOffTxnPayeeCorporateIRecordGroup();

	OffenderTransactions mainProcess(OffenderTransactions paramBean);

	TransactionTypes mainProcess(TransactionTypes paramBean);

	List<Persons> cgfkOffTxnPayeePersonIdRecordGroup();

	Persons cgfkchkOffTxnOffTxnPer(Persons paramBean);

	List<Object> cgwhenNewFormInstance(SysDual paramBean);

	OffenderBookings offBkgPostQuery(OffenderBookings object);

	List<SystemProfiles> sysPflExecuteQuery(SystemProfiles objSystemProfiles);

	Persons mainProcess(Persons paramBean);

	SystemProfiles runReport(SystemProfiles paramBean);

	Corporates mainProcess(Corporates paramBean);

	Corporates cgfkchkOffTxnOffTxnCorp(Corporates paramBean);

	List<TransactionTypes> cgfkOffTxnTxnTypeRecordGroup(String caseloadId, String userName) ;

	TransactionOperation txnTypeValidation(String txnType, String caseloadId, String userName);

	Integer checkCaseloadValidation(String caseloadId, String agyLocId);
	
	SystemProfiles otdrdtfuModlibValidationHWhenValidateItemSystemProfileC();
	
	List<OffenderDeductions> otdrdtfuModlibValidationHWhenValidateItemGetTxnFeeType(Long offenderId, String caseloadId, String transType);
	
	String otdrdtfuModlibValidationHWhenValidateTrustGetLowHighFlag();
	
	String otdrdtfuModlibValidationHWhenValidateTrustGetTransactionFee(Long offenderId, String caseloadId, Long offenderDeductionId, String transType, Double txnamount, String lowHighFlag);

	String otdrdtfuModlibValidationHWhenValidateOffCreditLimit(Long OffenderId, String caseloadId, String offenderBookId, String transType, Double creditamt, String txnusg, String orcreflg);
	
	ReferenceCodes otdrdtfuOffCreditLimitTransactionTypesC(String txnType);
	
	String otdrdtfuOffCreditLimitAmountWrittenOffC(String caseloadId, Long offenderId, String deductionType, Date fromDate, Date toDate);
	
	Map<String, Object> otdrdtfuOffCreditLimitTrustGetOffenderSubBalance(String pCsldId, Long pOffId, String pSubActType, Double pAmount, Double pMinbal, Long pInddays, Date pInddate, Long pTrstcode, String pLockFlag, String txntype, String modName, String pSetupCsldId);
	
	Long otdrdtfuGetDebitActCode(String txnType, String csldId);
	
	String otdrdtfuOffCreditLimitFetchCrob(String obligationType);
	
	String otdrdtfuOffCreditLimitFetchIndigentflag(String caseloadId, String obligationType);
	
	String otdrdtfuOffCreditLimitFetchCtrWashSpecific();
	
	ReferenceCodes otdrdtfuOffCreditLimitFetchMaxLimitPeriodtype(String caseloadId, Long offenderId, String ObligationType);
	
	ReferenceCodes otdrdtfuOffCreditLimitFetchMaxLimitPeriodtypeWithoutOffenderId(String caseloadId, String ObligationType);
	
	String otdrdtfuOffCreditLimitFetchWeekday();
	
	Date otdrdtfuOffCreditLimitFetchFromdate(String weekDay);
	
	Date otdrdtfuOffCreditLimitFetchTodate(String weekDay);
	
	Date otdrdtfuOffCreditLimitFetchFromdateOnMonth();
	
	Date otdrdtfuOffCreditLimitFetchTodateOnMonth();
	
	Double getPreviousLoanAmountTotalTakenC(Long offenderId, String deductionType, String caseloadId, Date fromDate, Date toDate);
	
	Double getPreviousLoanAmountTotalReversedC(Long offenderId, String deductionType, String caseloadId, Date fromDate, Date toDate);

	ChkFreezeDisbursements chkDisbursementFreeze(ChkFreezeDisbursements chkFreezeDisbursements);

	String chkAccountStatus(String caseloadId, Long offenderId);

	Integer reopenOffenerTrustAccount(String caseloadId, Long offenderId, String userName);
	
	List<OtrdreceReportBean> otdrdtfugenerateotrdrecereport(OtrdreceReportBean paramBean);
	
	List<OtrreceiReportBean> otdrdtfugenerateOtrreceireport(OtrreceiReportBean paramBean);
	
	Corporates getCorp(Long cropId);

	Persons getPerson(Integer payeePersonId);

	String txnIdCur(Integer txnId);

	Integer genTrustTrans(String seqId);

	Map<String, Object> getSubActType(String string, String txnType, String txnUsage, String caseloadId);

	String creditObligExistsC(String txnType, String caseloadId);

	Map<String, Object> trustChkOverdrawn(String caseloadId, Long offenderId, String subAccountType, String txnType,
			Double txnEntryAmount, String modelName, Integer txnId, Long offenderBookId, Double totTxnFee, Integer txnEntrySeq);

	Integer txnSeqCur(Integer txnId, Integer txnEntrySeq);

	Integer offenderTransactionsCommit(List<OffenderTransactions> offenderTransactionsList);

	Map<String, Object> processGlTransNew(String caseloadId, String txnType, Object object, Double txnEntryAmount,
			Integer txnId, Date txnEntryDate, String txnEntryDesc, Integer txnEntrySeq, String string, Long offenderId,
			Long offenderBookId, Object object2, String subAccountType, Integer payeePersonId, Integer payeeCorporateId,
			String payeeNameText, String string2);

	Map<String, Object> processTransactionFee(String string, String caseloadId, Long offenderId, Long offenderBookId,
			Integer txnId, Integer txnEntrySeq, String txnType, Double txnEntryAmount, Date txnEntryDate,
			String txnUsage);

	String updateOffenderBalance(String caseloadId, Long offenderId, String txnPostingType, Date txnEntryDate,
			Integer txnId, String txnType, Double txnEntryAmount, String subAccountType, String string);

	Integer curDays(String txnType);

	void processHold(Integer txnId, String caseloadId, Long offenderId, String txnType, Integer holdDays,
			String subAccountType, Double txnEntryAmount, String txnEntryDesc, String txnReferenceNumber, Integer txnNum, 
			Integer holdNumbers);

	Map<String, Object> financialDoDuctionsFinancial(String caseloadId, Long offenderId, Long offenderBookId,
			String txnType, Integer txnId, Date txnEntryDate, String subAccountType, String string,
			Double txnEntryAmount, int i, Double txnEntryAmount2, Integer txnEntrySeq, String string2);

	void deductionGetAcAndSetIndDate(Long offenderId, String caseloadId);

	void insetIntoChequeData(String caseloadId, Integer txnId, Double txnEntryAmount, String string,
			Double txnEntryAmount2, Double txnEntryAmount3, Integer payeeId, Integer payeeCorporateId, String payeeName,
			String string2, String string3, String string4, String string5, String txnType);

	List<OtrdreceReportBean> otdrdtfugenerateOtrdrecereport(OtrdreceReportBean paramBean);

	String getTxnType(String moduleName, String caseloadId);

	void trustInsertIntoOffenderTrans(Integer txnNum, Integer txnEntrySeq, String caseloadId, Long offenderId,
			Long offenderBookId, String txnPostingType, String txnType, String txnDesc, Double txnAmount, Date txnData,
			String subAccountType, String pDeductionFlag, Double pPreDedAmount, String pDeductionType,
			Integer pPayeeCorpId, Integer pPayeePersonId, String pInfoNumber, String rSlipFlag, String pAllowOverdrawn);
	
	List<AccountCodes> processGlTransNewPostingC(String txnType, String modelName, String txnOperationType, String subAccountType);

	void trustInsertGltransNew(InsertGlTransNew param);

	List<String> getAcAndSetIndDateChkCaseloadC(String caseloadId);

	List<Integer> getAcAndSetIndDateChkIndAcC(Long offenderId);

	void updateIndigentDate(Integer indAc, Long offenderId, String caseloadId);

	SystemProfiles getAcAndSetIndDateSystemProfileC();

	Integer getTrustAccountCodeC();
	
	Double sumOffSubActBalC(Long offenderId, Integer lvTrustAccountCode);

	Date getAcAndSetIndDateMaxIndDateC(Long offenderId, Integer lvTrustAccountCode);

	Integer getAcAndSetIndUpdateOffenderSubAccounts(Date lvIndigentDate, Integer lvIndigentDaysLimit, Long offenderId, Integer lvTrustAccountCode);

	String mainProcessAutoSubmitting();
	
	Integer reportRecievedUpdate(Integer txnId, String receiptNumber);
	
	String numberToWord(BigDecimal amount);

	String getCurrencySymbol();

	String deductionsChkOffenderDeductions(String caseloasdId, Long offenderId, String txnType, Integer shadowId);
	
}
