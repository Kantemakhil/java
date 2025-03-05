package net.syscon.s4.inmate.trust.trustaccounts;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.SystemProfilesCommitBean;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.inmate.beans.GlTransactions;
import net.syscon.s4.inmate.beans.GlTransactionsCommitBean;

/**
 * Interface OtdaaccoService
 */
public interface OtdaaccoService {

	List<ReferenceCodes> cgfkGlTxnAccountCodeRecordGroup(String txnType, String caseLoadId, String caseLoadType);

	List<ReferenceCodes> cgfkGlTxn1AccountCodeRecordGroup();

	AccountCodes cgfkchkGlTxnGlTxnAcCode(AccountCodes paramBean);

	Integer systemProfilesCommit(SystemProfilesCommitBean commitBean);

	List<ReferenceCodes> cgfkGlTxn1TxnTypeRecordGroup(String caseLoadId, String caseLoadType);

	AccountCodes cgfkchkGlTxn1GlTxnAcCod(AccountCodes paramBean);

	GlTransactions glTxn1Commit(GlTransactionsCommitBean commitBean);

	List<GlTransactions> cgrichkGlTransactions(GlTransactions paramBean);

	List<SystemProfiles> systemProfilesExecuteQuery(SystemProfiles objSystemProfiles);

	List<GlTransactions> glTxn1ExecuteQuery(GlTransactions objGlTransactions);

	String checkNavigation(BigDecimal accountCode);

	String chkOffAcnt(BigDecimal accountCode);

	Integer whenValidateItem(GlTransactions bean);

	Integer chkInvalidAccounts(BigDecimal accountCodeOne, BigDecimal accountCodeTwo, String caseloadId);

	String whenCheckBoxChecked(String caseloadId, BigDecimal offfenderId, String txnType);

}
