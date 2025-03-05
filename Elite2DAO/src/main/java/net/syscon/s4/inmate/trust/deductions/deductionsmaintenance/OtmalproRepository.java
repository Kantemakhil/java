package net.syscon.s4.inmate.trust.deductions.deductionsmaintenance;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import net.syscon.s4.common.beans.TransactionTypes;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.im.beans.CaseloadDeductionDetails;
import net.syscon.s4.im.beans.SysDual;
import net.syscon.s4.inmate.beans.CaseloadDeductionProfiles;
import net.syscon.s4.inmate.beans.DeductionTypes;

/**
 * Interface OtmalproRepository
 */
public interface OtmalproRepository {
	List<DeductionTypes> cgfkCsldDpDeductionTypeRecordGroup(String caseloadType);

	List<TransactionTypes> cgfkchkCsldDdCsldDdTxnty(TransactionTypes paramBean);

	Integer csldDpInsertCaseloadDeductionProfiles(List<CaseloadDeductionProfiles> lstCaseloadDeductionProfiles);

	List<CaseloadDeductionProfiles> csldDpExecuteQuery(CaseloadDeductionProfiles objCaseloadDeductionProfiles);

	Integer csldDdInsertCaseloadDeductionDetails(List<CaseloadDeductionDetails> lstCaseloadDeductionDetails);

	List<AccountCodes> cgfkCsldDpAccountCodeRecordGroup(String caseloadType);

	Integer csldDpDeleteCaseloadDeductionProfiles(List<CaseloadDeductionProfiles> lstCaseloadDeductionProfiles);

	List<Object> cgwhenNewFormInstance(SysDual paramBean);

	List<CaseloadDeductionDetails> csldDdExecuteQuery(CaseloadDeductionDetails objCaseloadDeductionDetails);

	List<TransactionTypes> cgfkCsldDdReceiptTxnTypeRecordGroup(String caseloadType);

	Integer csldDdDeleteCaseloadDeductionDetails(List<CaseloadDeductionDetails> lstCaseloadDeductionDetails);

	Integer csldDpUpdateCaseloadDeductionProfiles(List<CaseloadDeductionProfiles> lstCaseloadDeductionProfiles);

	Integer csldDdUpdateCaseloadDeductionDetails(List<CaseloadDeductionDetails> lstCaseloadDeductionDetails);

	List<DeductionTypes> cgfkchkCsldDpDedprofDedty(DeductionTypes paramBean);

	List<AccountCodes> cgfkchkCsldDpDedprofAcCo(AccountCodes paramBean);

	List<CaseloadDeductionDetails> csldDdExecuteQuery(String caseloadId, String deductionType);

	String getDesc(String caseloadType, String receiptTxnType);

	String getfromBalType(String deductionType);

	String getFormBalDsc(String fromBalType);

	BigDecimal getExternalPriority(String caseloadId);

	List<CaseloadDeductionProfiles> gtePercentageandExternalPriority(String caseloadId, String deductionType);

	String getfromBalDesc(String deductionType);

	Integer compareEffectiveDatec(String effectiveDate);

	List<CaseloadDeductionProfiles> checkExists(String caseloadId, String deductionType);

	BigDecimal getMaxPercenatge(String caseloadId, String deductionType);

	void updateCaseloadDeductionProfiles(String caseloadId, BigDecimal percentage, String deductionType, String modifyUserId);

	void updateCaseloadDeductionProfilesWithouttxnType(String caseloadId, String deductionType, String modifyUserId);
	
	String getfromBalTypes(String deductionType);
	
	String allocTypeValidation(String allocType, String caseloadId);
}
