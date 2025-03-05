package net.syscon.s4.inmate.trust.statements;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.im.beans.SubAccountBalancesBean;
import net.syscon.s4.im.beans.SysDual;
import net.syscon.s4.im.beans.TransStatementBean;
import net.syscon.s4.inmate.beans.Printers;

/**
 * Interface OtstastaRepository
 */
public interface OtstastaRepository {
	List<ReferenceCodes> cgfkAcCodeSubAccountTypeRecordGroup();

	List<Printers> cgfkOmsReqPrinterIdRecordGroup();

	List<AccountCodes> acCodeExecuteQuery(AccountCodes objAccountCodes);

	ReferenceCodes cgfkchkAcCodeAcSubAcct(ReferenceCodes paramBean);

	List<OffenderBookings> offBkg1ExecuteQuery(OffenderBookings objOffenderBookings);

	Integer offBkg1UpdateOffenderBookings(List<OffenderBookings> lstOffenderBookings);

	Integer offBkg1InsertOffenderBookings(List<OffenderBookings> lstOffenderBookings);

	Object omsReqPreInsert(SysDual paramBean);

	OffenderBookings otstastaKeyCommit(OffenderBookings paramBean);

	List<SystemProfiles> sysPflExecuteQuery(SystemProfiles objSystemProfiles);

	SystemProfiles runReport(SystemProfiles paramBean);

	Integer acCodeInsertAccountCodes(List<AccountCodes> lstAccountCodes);

	Printers cgfkchkOmsReqOmsReqPrint(Printers paramBean);

	List<SubAccountBalancesBean> subAccountBalanceData(TransStatementBean paramBean);

	String headerBlockDataQuery(String caseloadType);

	String caseloadTypeData(String caseloadId);

	String caceloadNameOneQuery(String caseloadId);

	String fFacilityData();

	String fDobData();

	List<SubAccountBalancesBean> debsObligationsQueryDataBase(TransStatementBean paramBean);

	String profileValueId();

	List<SubAccountBalancesBean> subAccountData(TransStatementBean paramBean);

	List<TransStatementBean> mainHeaderQuery(TransStatementBean paramBean);

	List<SubAccountBalancesBean> subAccountTransactionQueryDataBase(TransStatementBean transStatementBean);

	String bookLabelData();

	String bookingNumberData(Long offenderBookId);

	String dollarSymbolQuery();

	String accountCodesRegSavData(String caseloadType, String getfAccountName);

	BigDecimal gettingBalance(BigDecimal openingBalnce, BigDecimal txnEntryAmount);

	List<SubAccountBalancesBean> subAccountTransactionQueryDataBaseWithoutSubAccType(
			TransStatementBean transStatementBean);

}
