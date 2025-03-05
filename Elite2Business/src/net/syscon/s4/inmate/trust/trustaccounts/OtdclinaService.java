package net.syscon.s4.inmate.trust.trustaccounts;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.transaction.support.TransactionOperations;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.SystemProfilesCommitBean;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.inmate.beans.OffenderSubAccounts;
import net.syscon.s4.inmate.beans.OffenderTrustAccountsTemp;
import net.syscon.s4.inmate.beans.OffenderTrustAccountsTempCommitBean;
import net.syscon.s4.inmate.beans.OffenderWorks;
import net.syscon.s4.inst.automatedcounts.beans.LockedModules;

/**
 * Interface OtdclinaService
 */
public interface OtdclinaService {
	TransactionOperations CheckSetupBeforeTransfer(TransactionOperations paramBean);

	List<Offenders> ClearZeroBalanceAccounts(Offenders paramBean);

	List<OffenderSubAccounts> CheckBalanceTally(OffenderSubAccounts paramBean);

	Integer offTracCommit(OffenderTrustAccountsTempCommitBean commitBean);

	List<LockedModules> checkLock();

	Integer closeAccount(Integer txnIdNextVal, BigDecimal pTxnEntrySeq, String caseloadId, Long offenderId,
			BigDecimal rootOffenderId, String userId);

	OffenderTrustAccountsTemp offTracKeyEntqry(OffenderTrustAccountsTemp paramBean);

	Integer ZeroAmount(Integer PTxnNum, BigDecimal pTxnEntrySeq, String caseloadId, BigDecimal lvRootOffenderId,
			String pSubAcType, String userId);

	OffenderWorks CgrichkOffenderTrustAccoun(OffenderWorks paramBean);

	List<Offenders> TransferFundsToProPer(Offenders paramBean);

	OffenderTrustAccountsTemp offTracKeyExeqry(OffenderTrustAccountsTemp paramBean);

	List<OffenderTrustAccountsTemp> offTracExecuteQuery(OffenderTrustAccountsTemp objOffenderTrustAccountsTemp);

	List<SystemProfiles> sysPflExecuteQuery(SystemProfiles objSystemProfiles);

	Integer sysPflCommit(SystemProfilesCommitBean commitBean);

	List<ReferenceCodes> selectMethodRgRecordGroup();

	Integer whenButtonPressed(OffenderTrustAccountsTempCommitBean commitBean);

}
