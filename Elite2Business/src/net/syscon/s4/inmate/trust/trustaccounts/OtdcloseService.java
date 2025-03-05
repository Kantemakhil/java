package net.syscon.s4.inmate.trust.trustaccounts;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.common.beans.OffenderTransactionsCommitBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.SystemProfilesCommitBean;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.inmate.beans.OffenderSubAccounts;
import net.syscon.s4.inmate.beans.OffenderSubAccountsCommitBean;

/**
 * Interface OtdcloseService
 */
public interface OtdcloseService {
	List<ReferenceCodes> cgfkchkOffTxnOffTxnRef(ReferenceCodes paramBean);

	ReferenceCodes offTxnWhenNewBlockInstance(ReferenceCodes paramBean);

	List<OffenderTransactions> offTxnExecuteQuery(OffenderTransactions object);

	List<SystemProfiles> sysPflExecuteQuery(SystemProfiles objSystemProfiles);

	Integer sysPflCommit(SystemProfilesCommitBean commitBean);

	List<OffenderSubAccounts> offSubaExecuteQuery(OffenderSubAccounts object);

	Integer offSubaCommit(OffenderSubAccountsCommitBean commitBean);

	List<ReferenceCodes> cgfkOffTxnPayeeCodeRecordGroup();

	OffenderTransactions offTxnCommit(OffenderTransactionsCommitBean commitBean);

	BigDecimal getRegBal(Long rootOffenderId, String caseloadId);

	List<AccountCodes> accountNameForValidation();

	String accountClosedFlagValidation(Long offenderId, String caseloadId);

	BigDecimal chkAccountClosedFlag(Long offenderId, String caseloadId,String userName);

	String freezDisbursement(String caseloadId, Long offenderId, String pTxnType, String caseloadType);

	BigDecimal chkSubAccountFlag(Long offenderId, String caseloadId);


}
