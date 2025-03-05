package net.syscon.s4.inmate.trust.financialreports;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import net.syscon.s4.cf.deductions.beans.FeeAccountBalanceBean;
import net.syscon.s4.cf.deductions.beans.offBillingStatements;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.im.beans.CasePlans;
import net.syscon.s4.im.beans.Dual;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.im.beans.OmsModuleParameters;
import net.syscon.s4.im.beans.OtrbnrcnBean;
import net.syscon.s4.im.beans.VAgencyAddresses;
import net.syscon.s4.inst.schedules.bean.VAddressUsages;

/**
 * Interface OsureporRepository
 */
public interface OsureporRepository {
	List<Dual> rg1cRecordGroup();

	List<Dual> rg3cRecordGroup();

	List<Dual> rg5cRecordGroup();

	List<Dual> rg5c1RecordGroup();

	List<Dual> rg2cRecordGroup();

	List<Dual> rg4cRecordGroup();

	List<OmsModuleParameters> populateRecords(OmsModuleParameters paramBean);

	String profileValueData();

	String profileValueDataOne();

	String profileValueDataTwo();

	List<ReferenceCodes> getDynamicLov(String qry, String caseload, BigDecimal accCode);

	List<AccountCodes> matserdata(String caseloadId);

	List<AccountCodes> childData(String caseloadId, Long parentAccountCode);

	List<AccountCodes> subChildData(String caseloadId, Integer accountCode);

	List<AccountCodes> getReport(String caseloadId, String flag);

	BigDecimal getTempCloseBalance(String caseloadId, Integer accountCode);

	BigDecimal gettempTxnAmount(String caseloadId, Long accountPeriod, Integer accountCode);

	BigDecimal gettempTxnMonAmount(String caseloadId, Long accountPeriod, Integer accountCode, Date d);

	BigDecimal getOpenBalance(Long accountPeriod, String caseloadId, Integer accountCode);

	BigDecimal getctempTxnAmount(Long accountPeriod, String caseloadId, Integer accountCode, Date d);

	Long getAccountPeriod(Date date);

	List<OtrbnrcnBean> getBankReconciliationReport(Long accountCode, String userDate, String caseloadId);

	String reportApplnCode(String moduleName);

	List<OtrbnrcnBean> getQuery3List(Long accountCode, String userDate, String caseloadId);

	BigDecimal getQuery4List(Long crTxn, Long accountCode);

	List<OtrbnrcnBean> getQuery5List(Long accountCode, String userDate, String caseloadId);

	BigDecimal getTotalCrAmnt(Long accountCode, String userDate, String caseloadId);

	BigDecimal getTotalDrAmnt(Long accountCode, String userDate, String caseloadId);

	List<OtrbnrcnBean> getQuery4List(Long accountCode, String userDate, String caseloadId);

	List<OtrbnrcnBean> getQueryFourMainList(Long accountCode, String userDate, String caseloadId);

	BigDecimal queryFourSubtotalAmnt(Long accountCode, String userDate);

	BigDecimal getFsysBal(String caseloadId, Long accountCode, String userDate);

	BigDecimal getFsysBalN(String caseloadId, Long accountCode);

	String getProfileVal();

	BigDecimal queryFourAdjustmentAmount(Long accountCode, String userDate);

	String getUserName();

	String getfcaseloadDesc(String caseloadId);

	List<VAgencyAddresses> getAddreesDetails(VAgencyAddresses paramBean);

	String getClientName(Long offenderBookId);

	Date getLongestSupervisionExpireDate(Long offenderBookId);

	List<CasePlans> getCasePlanDetailsToGetPoName(Long offenderBookId);

	List<offBillingStatements> gettingAccountBalanceOffBillingStatements(OffenderTransactions returnReport);

	List<FeeAccountBalanceBean> gettingPaymentsAndCreditsForAccounts(OffenderTransactions returnReport,Integer StatementId);

	BigDecimal gettingPaymentSumAmount(OffenderTransactions returnReport,Integer statementId);

	List<FeeAccountBalanceBean> gettingBilingForAccountSection(OffenderTransactions returnReport,Integer statementId);

	List<offBillingStatements> gettingSummaryOfFeeAccount(OffenderTransactions returnReport);

	Date getBillingCycleEndDate(Long rootOffenderId);

	Date getFBookingDate(Long rootOffenderId);

	String getMsgValue(String moduleName);

	List<ReferenceCodes> getFromBillingCycleDate();

	List<ReferenceCodes> getToBillingCycleDate();

	String getCountySpecificMsg(String string);

	VAddressUsages getBadAddress(BigDecimal ownerId);

	List<CasePlans> getPoName(Long offenderBookId);

	List<ReferenceCodes> getPoCasloadNames(String caseloadId);

	Date getForPeriodEndingdate(String endDate);

}
