package net.syscon.s4.inmate.trust.deductions.deductionsmaintenance;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import net.syscon.s4.common.beans.CaseloadDedBeneficiaries;
import net.syscon.s4.common.beans.OffenderSentObligations;
import net.syscon.s4.common.beans.TransactionTypes;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.im.beans.CaseloadDeductionDetails;
import net.syscon.s4.im.beans.Corporates;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.im.beans.SysDual;
import net.syscon.s4.inmate.beans.CaseloadDeductionProfiles;
import net.syscon.s4.inmate.beans.DeductionTypes;
import net.syscon.s4.inst.booking.beans.Persons;
import net.syscon.s4.inst.casemanagement.beans.CommunityConditions;

/**
 * Interface OtmfoproRepository
 */
public interface OtmfoproRepository {
	Persons cgfkchkCsldDbenCsldDben(Long personId);

	List<CaseloadDeductionProfiles> csldDpExecuteQuery(CaseloadDeductionProfiles objCaseloadDeductionProfiles);

	Integer csldDdInsertCaseloadDeductionDetails(List<CaseloadDeductionDetails> lstCaseloadDeductionDetails);

	Integer csldDpDeleteCaseloadDeductionProfiles(List<CaseloadDeductionProfiles> lstCaseloadDeductionProfiles);

	List<Object> cgwhenNewFormInstance(SysDual paramBean);

	List<CommunityConditions> rgConditionRecordGroup();

	Object cgrichkCaseloadDeductionPr(CaseloadDeductionDetails paramBean);

	Integer csldDpUpdateCaseloadDeductionProfiles(List<CaseloadDeductionProfiles> lstCaseloadDeductionProfiles);

	Integer csldDdUpdateCaseloadDeductionDetails(List<CaseloadDeductionDetails> lstCaseloadDeductionDetails);

	List<DeductionTypes> cgfkchkCsldDpDedprofDedty(DeductionTypes paramBean);

	List<Corporates> rgCorpRecordGroup();

	List<CommunityConditions> csldDpPostQuery(CommunityConditions paramBean);

	List<OffenderSentObligations> cgrichkCaseloadDeductionDe(OffenderSentObligations paramBean);

	List<DeductionTypes> cgfkCsldDpDeductionTypeRecordGroup(String caseloadType);

	List<TransactionTypes> cgfkchkCsldDdCsldDdTxnty(TransactionTypes paramBean);

	Integer csldDpInsertCaseloadDeductionProfiles(List<CaseloadDeductionProfiles> lstCaseloadDeductionProfiles);

	List<CaseloadDedBeneficiaries> csldDbenExecuteQuery(CaseloadDedBeneficiaries objCaseloadDedBeneficiaries);

	List<AccountCodes> cgfkCsldDpAccountCodeRecordGroup(String caseloadType);

	Object csldDbenPreInsert(SysDual paramBean);

	Integer csldDbenDeleteCaseloadDedBeneficiaries(List<CaseloadDedBeneficiaries> lstCaseloadDedBeneficiaries);

	Integer csldDbenUpdateCaseloadDedBeneficiaries(List<CaseloadDedBeneficiaries> lstCaseloadDedBeneficiaries);

	List<CaseloadDeductionDetails> csldDdExecuteQuery(CaseloadDeductionDetails objCaseloadDeductionDetails);

	Corporates cgfkchkCsldDbenCsldDben(BigDecimal corporateId);

	List<TransactionTypes> cgfkCsldDdReceiptTxnTypeRecordGroup(String caseloadType);

	Integer csldDdDeleteCaseloadDeductionDetails(List<CaseloadDeductionDetails> lstCaseloadDeductionDetails);

	Integer csldDbenInsertCaseloadDedBeneficiaries(List<CaseloadDedBeneficiaries> lstCaseloadDedBeneficiaries);

	List<OmsModules> createFormGlobalsCreateFormGlobals(OmsModules paramBean);

	List<AccountCodes> cgfkchkCsldDpDedprofAcCo(AccountCodes paramBean);

	BigDecimal getPriorityAmount(String caseloadId, String deductionType, BigDecimal priority);

	String calculateOn(String deductionType);

	Map<String, Object> calcBenTotal(String caseloadId, String deductionType);

	List<CaseloadDedBeneficiaries> dedPriorities(CaseloadDedBeneficiaries objSearchDao);

	Integer updateCaseloadDedBeneficiariesAmount(CaseloadDedBeneficiaries bean);

	Integer updateCaseloadDedBeneficiariesPercentage(BigDecimal percentage, BigDecimal caseloadDedBeneficiaryId,String modifyUserId);

	BigDecimal countMinBalLogic(String caseloadId, String deductionType);

	BigDecimal getMaxExternalPriorityNo(String caseloadId);

	BigDecimal getMaxInternalPriorityNo(String caseloadId, BigDecimal externalPriorityNo);

	BigDecimal vParentExists(String deductionType);

	BigDecimal vBenCount(String deductionType, String caseloadId);

	BigDecimal getReceiptPercentage(String deductionType, String caseloadId);

	Integer updateCaseloadDeductionProfilesPercentage(BigDecimal percentage, String caseloadId, String deductionType,String modifyUserId);

	Map<String, Object> getPercentAndEnternalPriority(String caseloadId, String deductionType);

}
