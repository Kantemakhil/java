package net.syscon.s4.pkgs.ocdcbene.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.im.beans.BankChequeData;
import net.syscon.s4.inmate.beans.BeneficiaryTransactions;
import net.syscon.s4.inmate.beans.GlTransactions;
import net.syscon.s4.pkgs.ocdcbene.OcdcbenePkgRepository;
import net.syscon.s4.pkgs.ocdcbene.OcdcbenePkgService;
import net.syscon.s4.pkgs.trust.impl.TrustServiceImpl;

@Service
public class OcdcbenePkgServiceImpl implements OcdcbenePkgService {

	@Autowired
	private OcdcbenePkgRepository ocdcbeneRepository;

	@Autowired
	TrustServiceImpl trustService;

	private static Logger logger = LogManager.getLogger(OcdcbenePkgServiceImpl.class.getName());

	@Override
	public Integer processBankCheque(final BigDecimal pPersonId, final BigDecimal pCorporateId,
			final BigDecimal pAccountCode, final String pPayeeName, final String pCaseLoadId, final String pModuleName,
			final BigDecimal pTotalAmount, final BigDecimal pNewTxnId, final String userName) {

		BigDecimal lCreditAccount = null;
		String lTxnType = null;
		String lChqProdFlag = null;
		String lTxnDesc = null;
		String lDrPostingType = null;
		String lCrPostingType = null;
		long lFirstTxnId = 0;
		long lLastTxnId = 0;
		String lDummy;
		String lErrorMessage;
		// lUserDefinedError;
		Date lLockedDate;
		BigDecimal lAbsTxnEntryAmount;

		try {
			lLockedDate = ocdcbeneRepository.getLockedDate(pModuleName, pCaseLoadId);
			Map<String, Object> lChequeDetailsMap = ocdcbeneRepository.lChequeDetails(pModuleName, pCaseLoadId,
					pAccountCode);
			if (Objects.nonNull(lChequeDetailsMap)) {
				lCreditAccount =BigDecimal.valueOf((Integer) lChequeDetailsMap.get("cr_account_code"));
				lTxnType = (String) lChequeDetailsMap.get("txn_type");
				lChqProdFlag = (String) lChequeDetailsMap.get("cheque_production_flag");
				lTxnDesc = (String) lChequeDetailsMap.get("description");
				lDrPostingType = (String) lChequeDetailsMap.get("txn_posting_type");
				lCrPostingType = (String) lChequeDetailsMap.get("txn_posting_type");
			}
			if (StringUtils.isBlank(lTxnType)) {
				lErrorMessage = "Transaction Operation not set up for cheque info: " + "Account_code=" + pAccountCode
						+ " Caseload_id=" + pCaseLoadId;
				throw new RuntimeException(lErrorMessage);
			}
			if (Objects.nonNull(pPersonId)) {
				List<BeneficiaryTransactions> beneficiaryTransactionsByPersonId = ocdcbeneRepository
						.lBenPersonTxnCur(pPersonId, pAccountCode, pCaseLoadId, lLockedDate);
			for (BeneficiaryTransactions beneficiaryTransactions : beneficiaryTransactionsByPersonId) {
				
				lDummy = ocdcbeneRepository.lValidatePerson(pPersonId,
						beneficiaryTransactions.getOffenderDeductionId());
				if (!StringUtils.equalsIgnoreCase(lDummy, "1")) {
					lErrorMessage = "Person ID=" + pPersonId + " Offender_deduction_id="
							+ beneficiaryTransactions.getOffenderDeductionId()
							+ " not found in Offender_Beneficiaries table ";
					throw new RuntimeException(lErrorMessage);
				}
				ocdcbeneRepository.updateBeneficiaryTransactionsByPersonId(pPersonId, beneficiaryTransactions,
						pAccountCode, userName);
				if (StringUtils.equals(beneficiaryTransactions.getTxnPostUsage(), "DR")) {
					BigDecimal minusOne = new BigDecimal(-1);
					lAbsTxnEntryAmount = minusOne.multiply(beneficiaryTransactions.getTxnEntryAmount());
				} else {
					lAbsTxnEntryAmount = beneficiaryTransactions.getTxnEntryAmount();
				}
				ocdcbeneRepository.insertBankChequeBeneficiariesByPersonId(pNewTxnId, pTotalAmount, lAbsTxnEntryAmount,
						beneficiaryTransactions,userName);
				lFirstTxnId = beneficiaryTransactions.getTxnId();
				lLastTxnId = beneficiaryTransactions.getTxnId();
			}
			} else if (Objects.nonNull(pCorporateId)) {
				List<BeneficiaryTransactions> beneficiaryTransactionsByCorporateId = ocdcbeneRepository
						.lBenCorporateTxnCur(pCorporateId, pAccountCode, pCaseLoadId, lLockedDate);
				for (BeneficiaryTransactions beneficiaryTransactions : beneficiaryTransactionsByCorporateId) {
					
				lDummy = ocdcbeneRepository.lValidateCorporate(pCorporateId,
						beneficiaryTransactions.getOffenderDeductionId());
				if (!StringUtils.equalsIgnoreCase(lDummy, "1")) {
					lErrorMessage = "Corporate ID=" + pCorporateId + " Offender_deduction_id="
							+ beneficiaryTransactions.getOffenderDeductionId()
							+ " not found in Offender_Beneficiaries table ";
					throw new RuntimeException(lErrorMessage);
				}
			     ocdcbeneRepository.updateBeneficiaryTransactionsByCorporateId(pCorporateId,beneficiaryTransactions,pAccountCode,userName);
				if (StringUtils.equals(beneficiaryTransactions.getTxnPostUsage(), "DR")) {
					BigDecimal minusOne = new BigDecimal(-1);
					lAbsTxnEntryAmount = minusOne.multiply(beneficiaryTransactions.getTxnEntryAmount());
				} else {
					lAbsTxnEntryAmount = beneficiaryTransactions.getTxnEntryAmount();
				}
				ocdcbeneRepository.insertBankChequeBeneficiariesByCorporateId(pNewTxnId, pTotalAmount,
						lAbsTxnEntryAmount, beneficiaryTransactions, userName);
				lFirstTxnId = beneficiaryTransactions.getTxnId();
				lLastTxnId = beneficiaryTransactions.getTxnId();
				}
			}
			if (lFirstTxnId == 0) {
				lErrorMessage = "No Offender Beneficiary record found for Person ID=" + pPersonId + " Corporate ID="
						+ pCorporateId + " Caseload_id=" + pCaseLoadId + " Account_code=" + pAccountCode;
				throw new RuntimeException(lErrorMessage);

			}
			GlTransactions glTransactions = new GlTransactions();
			glTransactions.setTxnPostUsage("DR");
			glTransactions.setAccountCode(pAccountCode);
			glTransactions.setAcntPosting(lDrPostingType);
			glTransactions.setCaseloadId(pCaseLoadId);
			glTransactions.setTxnType(lTxnType);
			glTransactions.setTxnEntryAmount(pTotalAmount);
			glTransactions.setTxnId(pNewTxnId.longValue());
			glTransactions.setTxnEntryDate(new Date());
			glTransactions.setTxnEntryDesc(lTxnDesc);
			glTransactions.setTxnEntrySeq(1L);
			glTransactions.setGlEntrySeq(1L);
			glTransactions.setPayeePersonId(pPersonId);
			glTransactions.setPayeeCorporateId(pCorporateId);
			glTransactions.setPayeeNameText(pPayeeName);
			glTransactions.setCreateUserId(userName);
			trustService.insertGlTransNew(glTransactions);

			GlTransactions glTransactions1 = new GlTransactions();
			glTransactions1.setTxnPostUsage("CR");
			glTransactions1.setAccountCode(lCreditAccount);
			glTransactions.setAcntPosting(lCrPostingType);
			glTransactions1.setCaseloadId(pCaseLoadId);
			glTransactions1.setTxnType(lTxnType);
			glTransactions1.setTxnEntryAmount(pTotalAmount);
			glTransactions1.setTxnId(pNewTxnId.longValue());
			glTransactions1.setTxnEntryDate(new Date());
			glTransactions1.setTxnEntryDesc(lTxnDesc);
			glTransactions1.setTxnEntrySeq(1L);
			glTransactions1.setGlEntrySeq(2L);
			glTransactions1.setPayeePersonId(pPersonId);
			glTransactions1.setPayeeCorporateId(pCorporateId);
			glTransactions1.setPayeeNameText(pPayeeName);
			glTransactions1.setCreateUserId(userName);
			trustService.insertGlTransNew(glTransactions1);

			if ("Y".equals(lChqProdFlag)) {
				BankChequeData bankCheqData = new BankChequeData();
				bankCheqData.setCaseloadId(pCaseLoadId);
				bankCheqData.setTxnId(pNewTxnId.longValue());
				bankCheqData.setTxnEntryAmount(pTotalAmount);
				bankCheqData.setChequeFlag("P");
				bankCheqData.setStartTxnId(new BigDecimal(lFirstTxnId));
				bankCheqData.setEndTxnId(new BigDecimal(lLastTxnId));
				bankCheqData.setPayeePersonId(pPersonId);
				bankCheqData.setPayeeCorporateId(pCorporateId);
				bankCheqData.setPayeeNameText(pPayeeName);
				bankCheqData.setBankAccountCode(lCreditAccount.longValue());
				bankCheqData.setModuleName(pModuleName);
				bankCheqData.setTxnType(lTxnType);
				trustService.insertIntoChequeData(bankCheqData,userName);
			}
		} catch (Exception e) {
			logger.error("processBankCheque :", e);
			return 0;
		}
		return 1;
	}
}
