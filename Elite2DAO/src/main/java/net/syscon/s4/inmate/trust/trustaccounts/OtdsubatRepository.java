package net.syscon.s4.inmate.trust.trustaccounts;

import java.util.List;

import net.syscon.s4.cf.deductions.beans.FeeAccountProfiles;
import net.syscon.s4.cf.maintenance.beans.FeeAccounts;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.im.beans.SysDual;
import net.syscon.s4.im.beans.TransactionOperation;

/**
 * Interface OtdsubatRepository
 */
public interface OtdsubatRepository {
	List<SysDual> cgwhenNewFormInstance(SysDual paramBean);

	List<ReferenceCodes> cgfkOffTxnSubAccountTypeRecordGroup(String fromSubAccount, String caseLoadId);

	Integer offtxn2InsertOffenderTransactions(List<OffenderTransactions> list);

	List<ReferenceCodes> cgfkchkOffTxn2OffTxnRef(ReferenceCodes paramBean);

	Integer offTxn2UpdateOffenderTransactions(List<OffenderTransactions> list);

	List<OffenderTransactions> offTxn2ExecuteQuery(OffenderTransactions list);

	List<ReferenceCodes> cgfkchkOffTxnOffTxnRef(ReferenceCodes paramBean);

	List<TransactionOperation> cgfkOffTxn2SubAccountTypeRecordGroup(String caseLoadId);

	String getDescription(String caseloadType, String txnType);

	Integer getAccountCode(String caseloadType, String fmSubAccountType);

	List<TransactionOperation> getCheckProductionFlag(String moduleName, String txnType, Integer crAccountCode,
			Integer drAcCode, String caseloadId);

	List<OffenderTransactions> getCorporateIdName(String moduleName, String txnType, Integer crAccountCode,
			Integer drAcCode, String caseloadId,String userId);

	Integer getTxnId();

	String getacCode(String code, String caseloadType);

	String getBal(String offenderId, String caseloadId, String acCode);

	String getAcClosedFlg(Long offenderId, String caseloadId);

	Long getRootOffenderId(String offenderIdDisplay, String caseloadId ,String bookingNo);

	Integer updateoffFeeAccountProfileHty(OffenderTransactions bean);

	Long getOffenderBookId(Long offenderId, String caseLoadId);

	Long getOffenderFeeId(Long offnderBookId, String feeCode);

	String getFeeAccountProfileData(Long offenderFeeId);

	Integer updateFeeAccountProfiles(FeeAccountProfiles bean);

	List<FeeAccounts> prepaidFeeCodes();
	

}
