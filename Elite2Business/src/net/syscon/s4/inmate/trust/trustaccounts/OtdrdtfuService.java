package net.syscon.s4.inmate.trust.trustaccounts;

import java.util.Date;
import java.util.List;
import java.util.Map;

import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.OtrdreceReportBean;
import net.syscon.s4.common.beans.OtrreceiReportBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.ReportBean;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.SystemProfilesCommitBean;
import net.syscon.s4.common.beans.TransactionTypes;
import net.syscon.s4.im.beans.ChkFreezeDisbursements;
import net.syscon.s4.im.beans.Corporates;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.im.beans.TransactionOperation;
import net.syscon.s4.inmate.beans.OffenderDeductions;
import net.syscon.s4.inst.booking.beans.Persons;

/**
 * Interface OtdrdtfuService
 */
public interface OtdrdtfuService {
	List<Corporates> cgfkOffTxnPayeeCorporateIRecordGroup();

	Corporates cgfkchkOffTxnOffTxnCorp(Corporates paramBean);

	List<Object> cgwhenNewFormInstance();

	SystemProfiles runReport(SystemProfiles paramBean);

	TransactionTypes cgfkchkOffTxnOffTxnTxn(TransactionTypes paramBean);

	OffenderBookings offBkgPostQuery(OffenderBookings paramBean);

	List<SystemProfiles> sysPflExecuteQuery(SystemProfiles objSystemProfiles);

	Integer sysPflCommit(SystemProfilesCommitBean commitBean);

	List<OffenderTransactions> mainProcess(OffenderTransactions paramBean);

	List<TransactionTypes> cgfkOffTxnTxnTypeRecordGroup(String caseloadId, String userName);

	List<Persons> cgfkOffTxnPayeePersonIdRecordGroup();

	TransactionOperation txnTypeValidation(String txnType, String caseloadId, String userName);

	Integer checkCaseloadValidation(String caseloadId, String agyLocId);
	
	SystemProfiles otdrdtfuModlibValidationHWhenValidateItemSystemProfileC();
	
	List<OffenderDeductions> otdrdtfuModlibValidationHWhenValidateItemGetTxnFeeType(Long offenderId, String caseloadId,
			String transType);
	
	String otdrdtfuModlibValidationHWhenValidateTrustGetLowHighFlag();
	
	String otdrdtfuModlibValidationHWhenValidateTrustGetTransactionFee(Long offenderId, String caseloadId, Long offenderDeductionId, String transType, Double txnamount, String lowHighFlag);

	Map<String, Object> otdrdtfuModlibValidationHWhenValidateOffCreditLimit(Long OffenderId, String caseloadId, String offenderBookId, String transType, Double creditamt, String txnusg, String orcreflg, Double txnEntryAmount,String userName);
	
	ReferenceCodes otdrdtfuOffCreditLimitTransactionTypesC(String txnType);
	
	String otdrdtfuOffCreditLimitAmountWrittenOffC(String caseloadId, Long offenderId, String deductionType, Date fromDAte, Date toDate);
	
	Map<String, Object> otdrdtfuOffCreditLimitTrustGetOffenderSubBalance(String pCsldId, Long pOffId, String pSubActType, Double pAmount, Double pMinbal, Long pInddays, Date pInddate, Long pTrstcode, String pLockFlag, String txntype, String modName, String pSetupCsldId,String userName);
	
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

	String onAmountBlurValidation(OffenderTransactions paramBean);

	ChkFreezeDisbursements chkDisbursementFreeze(ChkFreezeDisbursements chkFreezeDisbursements);

	String chkAccountStatus(String caseloadId, Long offenderId);

	Integer reopenOffenerTrustAccount(String caseloadId, Long offenderId, String userName);

	ReportBean otdrdtfugenerateotrdrecereport(OtrdreceReportBean paramBean);
	
	ReportBean otdrdtfugenerateOtrreceireport(OtrreceiReportBean paramBean);

	String mainProcessAutoSubmitting();

	String deductionsChkOffenderDeductions(String caseloasdId, Long offenderId, String txnType, Integer shadowId);

}
