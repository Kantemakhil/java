package net.syscon.s4.inmate.trust.trustaccounts;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.im.beans.Corporates;
import net.syscon.s4.inmate.beans.BeneficiaryTransactions;
import net.syscon.s4.inmate.beans.OffenderBeneficiaries;
import net.syscon.s4.inmate.beans.OffenderDeductions;
import net.syscon.s4.inst.booking.beans.Persons;

/**
 * Interface OcuovrobRepository
 */
public interface OcuovrobRepository {
	Integer sysPflInsertSystemProfiles(List<SystemProfiles> lstSystemProfiles);

	Integer offBncUpdateOffenderBeneficiaries(List<OffenderBeneficiaries> lstOffenderBeneficiaries);

	Corporates cgfkchkOffBncOffBncCorp(Corporates paramBean);

	Persons cgfkchkOffBncOffBncPer(OffenderBeneficiaries paramBean);

	List<OffenderBeneficiaries> offBncExecuteQuery(OffenderBeneficiaries objOffenderBeneficiaries,String wherecond);

	OffenderDeductions cgfkchkOffBncOffBncOff(OffenderDeductions paramBean);

	List<SystemProfiles> sysPflExecuteQuery(SystemProfiles objSystemProfiles);

	OffenderDeductions cgfdgetOffBncDrvAmount(OffenderDeductions paramBean);

	Integer sysPflDeleteSystemProfiles(List<SystemProfiles> lstSystemProfiles);

	String getCaseLoadType(String userId);

	BigDecimal getDrvAmount(OffenderBeneficiaries returnBean);

	String getCorporateNameFromId(OffenderBeneficiaries returnBean);

	String checkCfppsetUp(Long offenderDeductionId);

	String getCaseNumber(Long offenderDeductionId);

	String getCrAccCodeCur(String caseloadId, String deductionType);

	String getTransDetailCur(String caseloadId);

	OffenderBookings getOffenderBookIdCur(BigDecimal offenderId);

	OffenderDeductions getDeductionInfoCur(Long lvDeductionId);

	String getPostingTypeCur(String lvCrAccCode);

	Integer trustInsertGltransNew(String TxnCode, Integer glSeq, String lvCrAccCode, String lvTxnPostUsage,
			String caseloadId, String lvTxnDesc, Integer lvTxnSeq, BigDecimal offenderId, Long offenderBookId,
			String lvCaseNumber, BigDecimal personId, BigDecimal corporateId, Long lvDeductionId, Integer txnId,
			Date sysdate);

	String checkPaymentPlan();

	Integer updatePaymentPlanSchedules(BigDecimal offenderId, BigDecimal overrideAmount, int amt,
			String informationNumber, BigDecimal groupId);

	Integer getTxnId();

	String getSubAccountTypeCur(String lvTransOprRec);

	Integer insertIntoOffenderTrans(Integer lvTxnId, Integer lvTxnSeq, String caseloadId, BigDecimal offenderId,
			Long offenderBookId, String txnPostingType, String txnType, String lvTxnDesc, int transAmt, Date sysdate,
			String lvSubAccountType, String dedFlag, BigDecimal overrideAmount, String deductionType,
			BigDecimal corporateId, BigDecimal personId, String informationNumber);

	Integer insOffCrPriorPayments(OffenderBeneficiaries bean);

	Integer updtOffenderDeductions(OffenderBeneficiaries bean);

	Integer insertIntoBenTransactions(Integer lvTxnId, Integer lvTxnSeq, Long lvDeductionId, BigDecimal personId,
			BigDecimal corporateId, BigDecimal unknownBenId, String lvCrAccCode, String lvTxnDesc, String caseloadId,
			Long beneficiaryId, String userName);

	BigDecimal getReceivedAmount(OffenderBeneficiaries bean);

	List<OffenderBeneficiaries> getOffenderBenOldRec(Long beneficiaryId);

	OffenderDeductions getoffenderDedOldRec(Long offenderDeductionId);
}
