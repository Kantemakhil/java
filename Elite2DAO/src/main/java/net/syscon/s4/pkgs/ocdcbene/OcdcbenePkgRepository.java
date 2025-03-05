package net.syscon.s4.pkgs.ocdcbene;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.syscon.s4.inmate.beans.BeneficiaryTransactions;

public interface OcdcbenePkgRepository {

	Date getLockedDate(String PModuleName, String PCaseloadId);

	List<BeneficiaryTransactions> lBenPersonTxnCur(BigDecimal PPersonId, BigDecimal pAccountCode, String pCaseloadId,
			Date pLockedDate);

	List<BeneficiaryTransactions> lBenCorporateTxnCur(BigDecimal pCorporateId, BigDecimal pAccountCode,
			String pCaseloadId, Date pLockedDate);

	Map<String, Object> lChequeDetails(String pModuleName, String pCaseloadId, BigDecimal pAccountCode);

	String lValidatePerson(BigDecimal pPersonId, BigDecimal pOffenderDeductionId);

	String lValidateCorporate(BigDecimal pCorporateId, BigDecimal pOffenderDeductionId);

	Integer updateBeneficiaryTransactionsByPersonId(BigDecimal pPersonId,
			BeneficiaryTransactions beneficiaryTransactions, BigDecimal pAccountCode, String userName);

	Integer insertBankChequeBeneficiariesByPersonId(BigDecimal pNewTxnId, BigDecimal pTotalAmount,
			BigDecimal lAbsTxnEntryAmount, BeneficiaryTransactions beneficiaryTransactions, String userName);

	Integer updateBeneficiaryTransactionsByCorporateId(BigDecimal pCorporateId,
			BeneficiaryTransactions beneficiaryTransactions, BigDecimal pAccountCode, String userName);

	Integer insertBankChequeBeneficiariesByCorporateId(BigDecimal pNewTxnId, BigDecimal pTotalAmount,
			BigDecimal lAbsTxnEntryAmount, BeneficiaryTransactions beneficiaryTransactions, String userName);

}
