package net.syscon.s4.inmate.trust.trustaccounts;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import net.syscon.s4.common.beans.Caseloads;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.im.beans.SysDual;
import net.syscon.s4.inmate.beans.OffenderTrustTransfers;
/**
 * Interface OtdrttfuRepository
 */
public interface OtdrttfuRepository {
	
	Caseloads cgfkchkOffTtOffTtCsld(Caseloads paramBean);

	Integer offTxnInsertOffenderTransactions(List<OffenderTransactions> lstOffTrans);

	Integer offTtUpdateOffenderTrustTransfers(List<OffenderTrustTransfers> lstOffTrans);

	List<SysDual> cgwhenNewFormInstance(SysDual paramBean);

	Integer offTxnDeleteOffenderTransactions(List<OffenderTransactions> lstOffTrans);

	List<OffenderTransactions> offTxnExecuteQuery(OffenderTransactions objOffTrans);

	List<SystemProfiles> sysPflExecuteQuery(SystemProfiles objSysProf);

	Integer offTtInsertOffenderTrustTransfers(List<OffenderTrustTransfers> lstOffTrans);

	List<OffenderTrustTransfers> offTtExecuteQuery(OffenderTrustTransfers objOffTrans);

	String getfromCaseloadDesc(String fromCaseload);

	Integer getTheCheckNo(String fromCaseload, BigDecimal txnId);

	Long getBookId(Long offenderId);

	String getActiveFlg(Long obkgId);

	String getAcStatus(String toCaseloadId, Long offenderId);

	OffenderTransactions getLastNameFirstNames( Long offenderId,String caseLoadType);

	Integer updateOffenderTrustTransfers(OffenderTrustTransfers obj);

	List<OffenderTransactions> getSubAcntOffIdtxnAmtbkgId(OffenderTrustTransfers obj);

	OffenderTransactions getTxnTypeDescTxnUsageRecProdFlag(OffenderTrustTransfers obj);

	void updateOffenderTrustTransfersWithClosedFlagN(OffenderTrustTransfers obj);

	 Integer insertOffenderTrustAcounts(OffenderTrustTransfers obj);

	Integer insertOffenderSubAccounts(OffenderTrustTransfers list);

	BigDecimal getNextVal();

	Date createDefaultDeductions(OffenderTrustTransfers obj);
	
	 Integer genTrustRcptNmbr(String seqId) ;

	 Integer insertintoOffenderTrans(OffenderTrustTransfers obj);

	void updateOffenderBalance(OffenderTrustTransfers obj);

	Integer processGlTransNew(OffenderTrustTransfers obj);

	String getProfileVal();

	Integer doDeductionsFinancial(OffenderTrustTransfers obj);

	void deductionGetAcAndSetIndDate(Long offenderId, String caseloadId);

}
