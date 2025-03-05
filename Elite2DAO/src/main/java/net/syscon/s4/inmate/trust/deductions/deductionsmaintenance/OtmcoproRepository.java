package net.syscon.s4.inmate.trust.deductions.deductionsmaintenance;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import net.syscon.s4.common.beans.TransactionTypes;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.im.beans.CaseloadDeductionDetails;
import net.syscon.s4.im.beans.Corporates;
import net.syscon.s4.inmate.beans.CaseloadDeductionProfiles;
import net.syscon.s4.inmate.beans.DeductionTypes;
import net.syscon.s4.inst.booking.beans.Persons;

/**
 * Interface OtmcoproRepository
 */
public interface OtmcoproRepository {

	List<DeductionTypes> cgfkCsldDpDeductionTypeRecordGroup(String caseloadType);

	List<TransactionTypes> cgfkchkCsldDdCsldDdTxnty(TransactionTypes paramBean);

	Integer csldDpInsertCaseloadDeductionProfiles(List<CaseloadDeductionProfiles> lstCaseloadDeductionProfiles);

	List<Corporates> cgfkchkCsldDpDedprofCorp(Corporates paramBean);

	List<CaseloadDeductionProfiles> csldDpExecuteQuery(CaseloadDeductionProfiles objCaseloadDeductionProfiles);

	Integer csldDdInsertCaseloadDeductionDetails(List<CaseloadDeductionDetails> lstCaseloadDeductionDetails);

	List<AccountCodes> cgfkCsldDpAccountCodeRecordGroup(String caseloadType);

	Integer csldDpDeleteCaseloadDeductionProfiles(List<CaseloadDeductionProfiles> lstCaseloadDeductionProfiles);

	List<CaseloadDeductionDetails> csldDdExecuteQuery(CaseloadDeductionDetails objCaseloadDeductionDetails);

	List<Persons> cgfkchkCsldDpDedprofPer(Persons paramBean);

	List<TransactionTypes> cgfkCsldDdReceiptTxnTypeRecordGroup(String caseloadType);

	Integer csldDdDeleteCaseloadDeductionDetails(List<CaseloadDeductionDetails> lstCaseloadDeductionDetails);

	Integer csldDpUpdateCaseloadDeductionProfiles(List<CaseloadDeductionProfiles> lstCaseloadDeductionProfiles);

	Integer csldDdUpdateCaseloadDeductionDetails(List<CaseloadDeductionDetails> lstCaseloadDeductionDetails);

	List<DeductionTypes> cgfkchkCsldDpDedprofDedty(DeductionTypes paramBean);

	List<Persons> cgfkCsldDpPayeePersonIdRecordGroup();

	List<Corporates> cgfkCsldDpPayeeCorporateIRecordGroup();

	List<AccountCodes> cgfkchkCsldDpDedprofAcCo(AccountCodes paramBean);

	Map<String, Object> getPercentAndEnternalPriority(String caseloadId, String deductionType);

	BigDecimal getMaxExternalPriorityNo(String caseloadId);

	BigDecimal getMaxInternalPriorityNo(String caseloadId, BigDecimal externalPriorityNo);

	BigDecimal getReceiptPercentage(String deductionType, String caseloadId);

	Integer updateCaseloadDeductionProfilesPercentage(BigDecimal percentage, String caseloadId, String deductionType,String userId);

	String getCalculateOnVal(String deductionType);

	String chkDuplicate(String caseloadId, String deductionType);

	CaseloadDeductionProfiles getLimitAndPeriodType(String caseloadId, String deductionType);

	Corporates cgfkchkCsldDbenCsldDben(BigDecimal corporateId);

}
