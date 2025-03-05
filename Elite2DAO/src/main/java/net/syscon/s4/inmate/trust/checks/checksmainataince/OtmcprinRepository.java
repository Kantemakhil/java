package net.syscon.s4.inmate.trust.checks.checksmainataince;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.im.beans.BankChequeBooks;
import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.inmate.beans.BankChequeRegisters;

/**
 * Interface OtmcprinRepository
 */

public interface OtmcprinRepository {

	List<BankChequeRegisters> bankCrExecuteQuery(BankChequeRegisters objBankChequeRegisters);

	List<BankChequeRegisters> bankCrPostDelete(BankChequeRegisters paramBean);

	List<ReferenceCodes> cgfkBankCrCheqStatusVoidRecordGroup();

	List<ReferenceCodes> cgfkBankCrChequeStatusRecordGroup(String bankChequeStatus);

	Integer bankCbUpdateBankChequeBooks(List<BankChequeBooks> lstBankChequeBooks);

	Integer bankCrUpdateBankChequeRegisters(List<BankChequeRegisters> lstBankChequeRegisters);

	List<ReferenceCodes> cgfkchkBankCrBankCrRef(ReferenceCodes paramBean);

	List<BankChequeBooks> bankCbExecuteQuery(BankChequeBooks objBankChequeBooks);

	Integer getMaxCheckNum(String caseLoadId, Long accountCode, Integer firstCheckNumber, Integer lastCheckNumber);

	Integer insertIntoRegister(BankChequeRegisters obj);

	Integer insertIntoRegister(BankChequeBooks obj);

	Integer getMaxCheckNum(String caseloadId, Long accountCode, BigDecimal firstCheckNumber,
			BigDecimal lastCheckNumber);

	List<String> checkIfNewSeries(BankChequeBooks searchBean);

	List<String> checkIfOverOthSeries(BankChequeBooks searchBean);

	Long bcRowMaxChecqueNumber(String caseloadId, Long accountCode, String firstCheckNumber, String lastCheckNumber);

	String isTransactionReversed(Long txnId,Long accountCode);

	String getDesc(String chequeStatus);

	

}
