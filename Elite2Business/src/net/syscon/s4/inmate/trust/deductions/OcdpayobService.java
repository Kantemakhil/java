package net.syscon.s4.inmate.trust.deductions;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.cf.deductions.beans.OffFeeBillTransactions;
import net.syscon.s4.cf.deductions.beans.OffFeeBillTransactionsCommitBean;
import net.syscon.s4.common.beans.OffenderTransactionsCommitBean;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.SystemProfilesCommitBean;
import net.syscon.s4.im.beans.Corporates;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.im.beans.TransactionOperation;
import net.syscon.s4.inmate.beans.OffenderBeneficiaries;
import net.syscon.s4.inmate.beans.OffenderBeneficiariesCommitBean;
import net.syscon.s4.inst.booking.beans.Persons;

/**
 * Interface OcdpayobService
 */
public interface OcdpayobService {
	OffenderBeneficiaries offBncCommit(OffenderBeneficiariesCommitBean commitBean) throws Exception;

	List<OffenderBeneficiaries> offBncExecuteQuery(OffenderBeneficiaries object);

	List<TransactionOperation> cgfkOffTxnSubAccountTypeRecordGroup(String caseloadId);

	List<OffenderTransactions> offTxnExecuteQuery(OffenderTransactions object);

	List<SystemProfiles> sysPflExecuteQuery(SystemProfiles objSystemProfiles);

	Integer sysPflCommit(SystemProfilesCommitBean commitBean);

	Persons cgfkchkOffBncOffBncPer(OffenderBeneficiaries object);

	Integer offTxnCommit(OffenderTransactionsCommitBean commitBean);

	Corporates cgfkchkOffBncOffBncCorp(OffenderBeneficiaries object);

	BigDecimal getCurrentBalance(OffenderTransactions searchBean);

	String txnTyepOffTxns(String subAccountType, String caseloadType, String caseloadId);

	String getOffenderFeesEnableBtn();

	List<OffFeeBillTransactions> offFeeExecuteQuery(OffFeeBillTransactions serachBean);

	Integer offFeesCommit(OffFeeBillTransactionsCommitBean commitBean);

	OffenderBeneficiaries updateOffenderFees(Integer trustTxnId,Integer txnEntrySeq,Integer glSeq,String caseLoad,
			String FeeeCode,Double amount,Long offenderBookId,Long offenderId, String userName) throws Exception;

}
