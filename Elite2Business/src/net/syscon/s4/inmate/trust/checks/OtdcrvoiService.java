package net.syscon.s4.inmate.trust.checks;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.BankChequeData;
import net.syscon.s4.im.beans.CaseloadCurrentAccounts;
import net.syscon.s4.inmate.beans.BankChequeRegisters;
import net.syscon.s4.inmate.beans.BankChequeRegistersCommitBean;

/**
 * Interface OtdcrvoiService
 */
public interface OtdcrvoiService {

	List<ReferenceCodes> cgfkBankCrAccountCodeRecordGroup(String caseloadId,String userName);

	BankChequeData bankCrOnCheckDeleteMaster(BankChequeData paramBean);

	List<BankChequeRegisters> bankCrExecuteQuery(BankChequeRegisters obj);

	String bankCrCommit(BankChequeRegistersCommitBean commitBean);

	void cgfkchkBankCrBankCrGlAc(CaseloadCurrentAccounts paramBean);

	List<ReferenceCodes> cgfkBankCrChequeStatusRecordGroup();

	List<BankChequeData> bnkCdExecuteQuery(BankChequeData objBankChequeData);

	Map<String, Object> whenValidatingItem(BigDecimal txnId);

	List<String> verifyTxnTypeCount(BigDecimal txnId);

}
