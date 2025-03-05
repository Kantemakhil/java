package net.syscon.s4.inmate.trust.financialsmaintenance;
import java.util.List;

import net.syscon.s4.common.beans.TransactionTypes;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.inmate.beans.FreezeDisbursements;
/**
 * Interface OtmfreezRepository
 * @author Arkin Software Technologies 
 * @version 1.0 
 */
public interface OtmfreezRepository {
	List<AccountCodes> cgfkFreDisAccountCodeRecordGroup(String caseloadType) ;

	Integer freDisDeleteFreezeDisbursements(List<FreezeDisbursements> lstFreezeDisbursements) ;


	List<TransactionTypes> cgfkFreDisTxnTypeRecordGroup(String caseloadType) ;

	Integer freDisInsertFreezeDisbursements(List<FreezeDisbursements> lstFreezeDisbursements) ;

	Integer freDisUpdateFreezeDisbursements(List<FreezeDisbursements> lstFreezeDisbursements) ;

	List<FreezeDisbursements> freDisExecuteQuery(FreezeDisbursements objFreezeDisbursements) ;

	List<TransactionTypes> cgfkchkFreDisFreDisTxn(TransactionTypes paramBean) ;

	List<AccountCodes> cgfkchkFreDisFreDisAct(AccountCodes paramBean) ;
	
	List<FreezeDisbursements> getRegDescription(Long accountCode, String caseloadType);

	String getTxnDescription(String txnType, String caseloadType);

	String getTxnUsage(String caseloadType, String txnType);

	String getTxnUage(String txnType,String caseloadType);

	String getCode(String txnType,String caseloadType);

}
