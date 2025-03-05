package net.syscon.s4.inmate.trust.trustaccounts;
import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.im.beans.SysDual;
/**
 * Interface OtdholdtRepository
 */
public interface OtdholdtRepository {
	Integer offTxnInsertOffenderTransactions(List<OffenderTransactions> lstOffTrans);

	List<SysDual> cgwhenNewFormInstance(SysDual paramBean);

	List<ReferenceCodes> cgfkOffTxnSubAccountTypeRecordGroup(String caseLoadId);

	BigDecimal offTxnExecuteQuery(Offenders objOffTrans);

	List<SystemProfiles> sysPflExecuteQuery(SystemProfiles objSystemProfiles);

	ReferenceCodes cgfkchkOffTxnOffTxnRef(ReferenceCodes paramBean);

	List<OffenderTransactions> offBkgOnCheckDeleteMaster(OffenderTransactions paramBean);

	 BigDecimal getExistingHoldAmount(Offenders paramBean);
	 
	 BigDecimal getSubAccountBalance(Offenders paramBean);
	 
	 Integer preInsert();
	 
	 Integer preInsertHoldNumber();
	 
	 Integer getMaxOffenderBookId(Long offenderId);
	 
	 Integer updateOffenderBalance(OffenderTransactions offTrans);
	 
	 Integer processGlTransNew(OffenderTransactions offTrans);
	 
	 Integer updateOffTrustAccounts(OffenderTransactions offTrans);

	BigDecimal otdholdGetSubacHoldbalance(OffenderTransactions objTrans);

	Integer otdholdUpdateSubacHoldbalance(BigDecimal holdBal, String caseloadId, Long offenderId,
			String subAccountType,String modifyUserId);

	void getAcAndSetIndDate(Long offenderId, String caseloadId);

	
}
