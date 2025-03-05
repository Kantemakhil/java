package net.syscon.s4.inmate.trust.checks.checksmainataince;

import java.util.List;

import net.syscon.s4.im.beans.BankChequeBooks;
import net.syscon.s4.im.beans.BankChequeBooksCommitBean;
import net.syscon.s4.im.beans.BankChequeRegistersCommitBean;
import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.inmate.beans.BankChequeRegisters;

/**
 * Interface OtmcprinService
 */
public interface OtmcprinService {

	List<BankChequeRegisters> bankCrExecuteQuery(BankChequeRegisters objBankChequeRegisters);

	List<ReferenceCodes> cgfkBankCrCheqStatusVoidRecordGroup();

	List<ReferenceCodes> cgfkBankCrChequeStatusRecordGroup(String chequeStatus);

	List<BankChequeBooks> bankCbExecuteQuery(BankChequeBooks objBankChequeBooks);

	Integer bankCbCommit(BankChequeBooksCommitBean commitBean);

	Integer bankCrCommit(BankChequeRegistersCommitBean commitBean);

	List<String> checkIfNewSeries(BankChequeBooks searchBean);

	Long bcRowMaxChecqueNumber(String caseloadId, Long accountCode, String firstCheckNumber, String lastCheckNumber);

	String  isTransactionReversed(Long txnId, Long accountCode);

}
