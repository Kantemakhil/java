package net.syscon.s4.inmate.trust.financialsmaintenance;

import java.util.List;

import net.syscon.s4.common.beans.TransactionTypes;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.inmate.beans.FreezeDisbursements;
import net.syscon.s4.inmate.beans.FreezeDisbursementsCommitBean;

/**
 * Interface OtmfreezService
 * 
 * @author Arkin Software Technologies
 * @version 1.0
 */
public interface OtmfreezService {


	List<AccountCodes> cgfkFreDisAccountCodeRecordGroup(String caseloadType) ;

	List<TransactionTypes> cgfkFreDisTxnTypeRecordGroup(String caseloadType) ;


	List<FreezeDisbursements> freDisExecuteQuery(FreezeDisbursements objFreezeDisbursements) ;

	Integer freDisCommit(FreezeDisbursementsCommitBean commitBean) ;

	String getTxnUage(String txnType,String caseloadType);

}
