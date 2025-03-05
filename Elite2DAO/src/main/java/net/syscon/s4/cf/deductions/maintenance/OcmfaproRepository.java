package net.syscon.s4.cf.deductions.maintenance;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import net.syscon.s4.cf.deductions.beans.FeeAccountProfiles;
import net.syscon.s4.common.beans.CaseloadDedBeneficiaries;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.im.beans.CaseloadDeductionDetails;
import net.syscon.s4.im.beans.Corporates;
import net.syscon.s4.inmate.beans.CaseloadDeductionProfiles;
import net.syscon.s4.inst.booking.beans.Persons;

public interface OcmfaproRepository {
	List<ReferenceCodes> feeActTypeRecordGroup();

	List<ReferenceCodes> locationRecordGroup(String agyLocType);

	List<ReferenceCodes> creditDedToRecordGroup(String caseloadType);

	List<ReferenceCodes> frequencyRecordGroup();

	List<ReferenceCodes> codeRecordGroup();

	List<ReferenceCodes> reciptTypeRecordGroup(String caseloadType);
	
	List<CaseloadDeductionProfiles> caseloadDedProfExecuteQuery(CaseloadDeductionProfiles objSearchDao);

	List<CaseloadDedBeneficiaries> caseloadDedBenficExecuteQuery(CaseloadDedBeneficiaries objSearchDao);

	BigDecimal getMaxInternalPriorityNo(String caseloadId, BigDecimal externalPriorityNo);

	BigDecimal getMaxExternalPriorityNo(String caseloadId);

    Integer caseloadDedProfInsert(List<CaseloadDeductionProfiles> listCaseloadDedProf);

	Integer caseloadDedBenficInsert(List<CaseloadDedBeneficiaries> listCaseloadDedBenfi);

	Integer caseloadDedProfUpdate(List<CaseloadDeductionProfiles> listCaseloadDedProf);

	Integer caseloadDedBenficiUpdate(List<CaseloadDedBeneficiaries> listCaseloadDedBenfi);

	Integer caseloadDedProfDelete(List<CaseloadDeductionProfiles> listCaseloadDedProf);

	Integer caseloadDedBenficDelete(List<CaseloadDedBeneficiaries> listCaseloadDedBenfi);

	List<CaseloadDeductionDetails> caseloadDedDetExecuteQuery(CaseloadDeductionDetails objSearchDao);

	Integer caseloadDedDetInsert(List<CaseloadDeductionDetails> lstCaseloadDeductionDetails);

	Integer caseloadDedDetUpdate(List<CaseloadDeductionDetails> lstCaseloadDeductionDetails);

	Integer caseloadDedDetDelete(List<CaseloadDeductionDetails> lstCaseloadDeductionDetails);

	Map<String, Object> getPercentAndEnternalPriority(String caseloadId, String deductionType);

	Long csldDbenPreInsert();

	Persons cgfkchkCsldDbenCsldDben(Long personId);

	Corporates cgfkchkCsldDbenCsldDben(BigDecimal corporateId);

	String calculateOn(String deductionType);

	Integer updateCaseloadDedBeneficiariesAmount(CaseloadDedBeneficiaries bean);

	Integer updateCaseloadDedBeneficiariesPercentage(BigDecimal percentage,
													 BigDecimal caseloadDedBeneficiaryId,String modifyUserId);

	List<CaseloadDedBeneficiaries> dedPriorities(CaseloadDedBeneficiaries objSearchDao);

	BigDecimal getPriorityAmount(String caseloadId, String deductionType,
								 BigDecimal priority);

    Map<String, Object> calcBenTotal(String caseloadId, String deductionType);

	BigDecimal getReceiptPercentage(String deductionType, String caseloadId);

	Integer updateCaseloadDeductionProfilesPercentage(BigDecimal percentage, String caseloadId,
													  String deductionType,String modifyUserId);

	List<FeeAccountProfiles> getExistingOffenders(CaseloadDeductionProfiles data);

	Integer updateExistingOff(List<FeeAccountProfiles> updateList);

	List<CaseloadDedBeneficiaries> getExistingBenData(CaseloadDedBeneficiaries data);

	Integer updateExistingBenData(List<CaseloadDedBeneficiaries> inputList);

	List<SystemProfiles> sysPflExecuteQuery();

	List<CaseloadDeductionDetails> getExistingDedData(FeeAccountProfiles obj);

	Integer updateExistingDetData(List<CaseloadDeductionDetails> updateList);

    Integer updateAmountOffenderFeeAct(FeeAccountProfiles obj);

	Integer deleteBenfData(FeeAccountProfiles obj);

	Integer insertCsldBenData(List<CaseloadDedBeneficiaries> benList);

	BigDecimal getAmount(String caseloadId, String deductionType);

	Integer deleteDedDetails(FeeAccountProfiles obj);

	Integer insertCsldDedDetails(List<CaseloadDeductionDetails> dedDetails);

    List<SystemProfiles> sysPfl2ExecuteQuery();

    BigDecimal getMaxAmount(String deductionType, String caseloadId);
    List<SystemProfiles> sysPflExecuteQueryUpdateFeeAccounts();
}
