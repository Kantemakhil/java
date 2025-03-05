package net.syscon.s4.pkgs.common;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import net.syscon.s4.common.beans.Caseloads;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.im.beans.BedAssignmentHistories;
import net.syscon.s4.inst.classification.beans.OiiclassClassInquiry;

public interface CommonRepository {

	String getImageColumnCur();

	String getResultImageMarkExist(final BigDecimal pOffenderBookId, final String pImageObjectType,
			final String pMarkType, final String pBodyPart, final BigDecimal pObjectSeq, String lvColumnName);

	Integer getCountForCreateTabOne();

	void createAgencyTab(final String query);

	Integer getCountForCreateTabTwo();


	Integer unlockFormModule(final String caseloadId, final String moduleName, final String user);

	Long getMaxOffBookId(final Long offenderId);

	Long getMaxTxnEntrySeq(final Integer txnId);

	Date getHoldUntilDate(final Integer holdDays);

	Integer insertOffenderTransactions(final Integer txnId, final Long pTxnEntrySeq, final String caseloadId,
			final Long offenderId, final Long pOffBookId, final String pTxnPostingType, final String pTxnType,
			final String txnEntryDesc, final Double txnEntryAmount, final String subAccountType,
			final String pTxnAdjustedFlag, final String pHoldClearFlag, final Integer holdNumbers,
			final Date pHoldUntilDate, final String txnReferenceNumber, final String txnType, final String userName);

	Integer updateOffenderTrustAccounts(final Double txnEntryAmount, final Long offenderId, final String caseloadId,
			final String userName);

	Integer updateOffenderSubAccounts(final Double txnEntryAmount, final Long offenderId, final String caseloadId,
			final String subAccountType, final String userName);

	BigDecimal getScheduleDaysCur();

	List<OiiclassClassInquiry> oiiclassClassInquiryFirstSelect(final OiiclassClassInquiry objSearchDao);

	List<OiiclassClassInquiry> oiiclassClassInquirySecondSelect(final OiiclassClassInquiry objSearchDao);

	List<OiiclassClassInquiry> oiiclassClassInquiryThirdSelect(final OiiclassClassInquiry objSearchDao);

	String getCurrentCur(final String pCaseLoadId);

	Integer updateStaffMembers(final String pCaseLoadId, String userName);
	
	
	
	List<Caseloads> getLCaseloadsCur(final String caseloadId);

	Long getLCurrentPeriodCur(final Date pDate);

	Long getLLastClosedCur(final String caseloadId);

	List<AccountCodes> getLCsldCurrAcctBaseCur(final String pCaseloadId, final Integer pAccountCode,
			final Long pAccountPeriodId);

	List<AccountCodes> getLCsldCurrAcctTxnsCur(final String pCaseloadId, final Integer pAccountCode,
			final Long pLastPeriodId, final Long pCurrentPeriodId, final String userName);

	List<AccountCodes> getLCsldAcctPeriodsCur(final String pCaseloadId, final Long pAccountPeriodId, final String userName);

	List<AccountCodes> getLCsldAcctSummCur(final String pCaseloadId, final Integer pAccountCode,
			final Long pAccountPeriodId, final String userName);

	Integer insertCaseloadCurrentAccountsBase(final String caseloadId, final Integer accountCode,
			final Long lCurrentPeriodId, final Date cSysdate, final String createUserId);
	
	Integer insertCaseloadCurrentAccountsTxns(final AccountCodes txns);
	
	Integer insertCaseloadAccountPeriods(final AccountCodes periods);
	
	Integer insertCaseloadAccountSummaries(final AccountCodes periods);

	String getbillStatementExists(String billId);
	
	Integer getBedAssSeqCur(Integer offenderBookId);
	
	Integer bedAhInsertBedAssignmentHistories(final List<BedAssignmentHistories> lstBedAssignmentHistories);
	
	long agencyLocationsCount();
	
	long saveAgencylocations();
	
	Boolean checkCallFormAccess(String userId, List<String> accessPrivilegeList,String callForm);

	Boolean checkOffenderSpecificScreenAccess(String userId, List<String> accessPrivilegeList);
}
