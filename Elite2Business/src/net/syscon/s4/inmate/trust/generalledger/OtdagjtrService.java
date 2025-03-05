package net.syscon.s4.inmate.trust.generalledger;

import java.util.Date;
import java.util.List;
import java.util.Map;

import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.SystemProfilesCommitBean;
import net.syscon.s4.common.beans.TransactionTypes;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.im.beans.Corporates;
import net.syscon.s4.inmate.beans.GlTransactions;
import net.syscon.s4.inmate.beans.GlTransactionsCommitBean;
import net.syscon.s4.inst.booking.beans.Persons;

public interface OtdagjtrService {
	Corporates CgfkchkGlTxnGlTxnCorpF8(Corporates paramBean);

	List<Object> CgwhenNewFormInstance();

	AccountCodes CgfkchkGlTxn1GlTxnAcCod(AccountCodes paramBean);

	List<GlTransactions> glTxnExecuteQuery(GlTransactions objGlTransactions);

	List<Corporates> cgfkGlTxnPayeeCorporateIdRecordGroup(Integer corporateId);

	TransactionTypes CgfkchkGlTxnGlTxnTxnTyp(TransactionTypes paramBean);

	List<GlTransactions> CgrichkGlTransactions(GlTransactions paramBean);

	AccountCodes CgfkchkGlTxnGlTxnAcCode(AccountCodes paramBean);

	Integer glTxnCommit(GlTransactionsCommitBean commitBean);

	Integer glTxn1Commit(GlTransactionsCommitBean CommitBean);

	List<TransactionTypes> cgfkGlTxnTxnTypeRecordGroup(String caseloadId, String caseloadType);

	List<SystemProfiles> sysPflExecuteQuery(SystemProfiles objSystemProfiles);

	Integer sysPflCommit(SystemProfilesCommitBean commitBean);

	Persons CgfkchkGlTxnGlTxnPerF7(Persons paramBean);

	List<Persons> cgfkGlTxnPayeePersonIdRecordGroup(Integer personId);

	Date trustGLReopenClosedPeriod(String caseloadId);

	Map<String, Object> onTxnEntryDateBlur(String caseloadId, Long txnDate);

	Map<String, Object> onTxnTypeValueChange(String caseloadId, String caseloadType, String txnType, String userName);

	List<GlTransactions> prGetOffsetAccounts(GlTransactions paramBean);

}
