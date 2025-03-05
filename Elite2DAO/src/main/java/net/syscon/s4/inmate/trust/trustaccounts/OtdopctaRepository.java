package net.syscon.s4.inmate.trust.trustaccounts;

import java.util.List;

import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.im.beans.OffenderTrustAccounts;
import net.syscon.s4.im.beans.SysDual;
import net.syscon.s4.inmate.beans.OffenderSubAccounts;

/**
 * Interface OtdopctaRepository
 */
public interface OtdopctaRepository {
	List<OffenderSubAccounts> cgrichkOffenderTrustAccoun(OffenderSubAccounts paramBean);

	Integer offTaUpdateOffenderTrustAccounts(List<OffenderTrustAccounts> lstOffTrust);

	List<SysDual> cgwhenNewFormInstance(SysDual paramBean);

	List<SystemProfiles> sysPflExecuteQuery(SystemProfiles objSystemProfiles);

	List<OffenderTrustAccounts> offTaExecuteQuery(OffenderTrustAccounts objOffTrust);

	Integer offTaDeleteOffenderTrustAccounts(List<OffenderTrustAccounts> lstOffTrust);

	Integer insertIntoOffenderTransaction(OffenderTransactions offTrans);

	Integer processGlTransNew(OffenderTransactions offTrans);

	Integer updateOffenderSubAccounts(OffenderTransactions offTrans);

	String getAcAndSetIndDate(OffenderTransactions offTrans);

	AccountCodes getSubAccountType(OffenderTransactions objSearchDao);

	String checkAccountSatus(OffenderTransactions offTrans);

	Integer preInsert();

	String getTransactionType(String caseloadId);

}
