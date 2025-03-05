package net.syscon.s4.inmate.trust.trustaccounts;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.im.beans.OffenderTrustAccounts;
import net.syscon.s4.im.beans.SysDual;
import net.syscon.s4.inmate.beans.DeductionTypes;
import net.syscon.s4.inmate.beans.OffenderDeductions;
import net.syscon.s4.inmate.beans.OffenderSubAccounts;
import net.syscon.s4.inmate.beans.OffenderWorks;
import net.syscon.s4.inmate.beans.Remitters;

/**
 * Interface OtidtaccRepository
 */
public interface OtidtaccRepository {

	List<OffenderWorks> offTxnWhenNewRecordInstance(OffenderWorks paramBean);

	List<OffenderSubAccounts> offSubaExecuteQuery(OffenderSubAccounts obj);

	Integer sysPflDeleteSystemProfiles(List<SystemProfiles> lstSystemProfiles);

	OffenderDeductions populateCreditObligation(Long rootOffenderId);

	List<OffenderDeductions> offDedExecuteQuery(OffenderDeductions obj);

	OffenderTransactions populatePayeeRemitterName(OffenderTransactions paramBean);

	List<OffenderTransactions> calcAccountBalancesRecordGroup();

	DeductionTypes getTheDescriptionOfDeductionType(DeductionTypes paramBean);

	List<OffenderTransactions> offTxnExecuteQuery(OffenderTransactions obj);

	List<SystemProfiles> sysPflExecuteQuery(SystemProfiles objSystemProfiles);

	List<OffenderTrustAccounts> offTaExecuteQuery(OffenderTrustAccounts obj);

	OffenderDeductions populateFixedObligation(Long rootOffenderId);

	OffenderDeductions checkFixMthAct(OffenderDeductions paramBean);

	ReferenceCodes cgfkchkOffSubaOffSubaAc(Long accountCode);

	String getAgyLocId(OffenderSubAccounts searchRecord);

	Integer getTheDaysRemaining(OffenderSubAccounts obj);

	String indigentFlagFormula(OffenderSubAccounts searchBean);

	String getNbtAccountClosedFlag(OffenderSubAccounts searchBean);

	Integer calculateTheRemainDays(OffenderSubAccounts obj);

	OffenderTransactions getChequeAndClearData(Integer txnId);

	Integer getThePayRollId(OffenderTransactions obj);

	String getTheBookingNoOfOffenderBookId(Long offenderBookId);

	String getTheRemitterName(Integer remitterId);

	String getPersonCursor(Integer payeePersonId);

	String getCorporateCursor(Integer payeeCorporateId);

	BigDecimal getTheCurrentBalance(Long offenderId);

	String getThedeductionCategory(String deductionType);

	BigDecimal getTheDeductionAmount(Long offDeductionId);

	String getaccountClosedFlag(OffenderSubAccounts searchBean);

	Double getHoldBalance(String offenderId, String caseloadId);

	Double getTheCurrentBalanceWithCaseLoad(Long offenderId, String currentCaseLoad);

}
