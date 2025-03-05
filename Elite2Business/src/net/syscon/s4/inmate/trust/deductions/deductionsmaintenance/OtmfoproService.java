package net.syscon.s4.inmate.trust.deductions.deductionsmaintenance;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import net.syscon.s4.common.beans.CaseloadDedBeneficiaries;
import net.syscon.s4.common.beans.CaseloadDedBeneficiariesCommitBean;
import net.syscon.s4.common.beans.OffenderSentObligations;
import net.syscon.s4.common.beans.TransactionTypes;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.im.beans.CaseloadDeductionDetails;
import net.syscon.s4.im.beans.CaseloadDeductionDetailsCommitBean;
import net.syscon.s4.im.beans.Corporates;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.inmate.beans.CaseloadDeductionProfiles;
import net.syscon.s4.inmate.beans.CaseloadDeductionProfilesCommitBean;
import net.syscon.s4.inmate.beans.DeductionTypes;
import net.syscon.s4.inst.booking.beans.Persons;
import net.syscon.s4.inst.casemanagement.beans.CommunityConditions;

/**
 * Interface OtmfoproService
 * 
 */
public interface OtmfoproService {
	List<DeductionTypes> cgfkCsldDpDeductionTypeRecordGroup(String caseloadType);

	List<DeductionTypes> CgfkchkCsldDpDedprofDedty(DeductionTypes paramBean);

	Integer csldDbenCommit(CaseloadDedBeneficiariesCommitBean CommitBean);

	void csldDpPreDelete();

	Corporates CgfkchkCsldDbenCsldDben(BigDecimal corporateId);

	List<Object> CgwhenNewFormInstance();

	String csldDdCommit(CaseloadDeductionDetailsCommitBean CommitBean);

	List<CommunityConditions> csldDpPostQuery(CommunityConditions paramBean);

	List<CaseloadDedBeneficiaries> csldDbenExecuteQuery(CaseloadDedBeneficiaries objCaseloadDedBeneficiaries);

	List<OffenderSentObligations> CgrichkCaseloadDeductionDe(OffenderSentObligations paramBean);

	List<CaseloadDeductionProfiles> csldDpExecuteQuery(CaseloadDeductionProfiles objCaseloadDeductionProfiles);

	List<AccountCodes> cgfkCsldDpAccountCodeRecordGroup(String caseloadType);

	Object csldDbenPreInsert();

	List<TransactionTypes> CgfkchkCsldDdCsldDdTxnty(TransactionTypes paramBean);

	List<AccountCodes> CgfkchkCsldDpDedprofAcCo(AccountCodes paramBean);

	List<CommunityConditions> rgConditionRecordGroup();

	Integer csldDpCommit(CaseloadDeductionProfilesCommitBean commitBean);

	List<CaseloadDeductionDetails> csldDdExecuteQuery(CaseloadDeductionDetails objCaseloadDeductionDetails);

	List<TransactionTypes> cgfkCsldDdReceiptTxnTypeRecordGroup(String caseloadType);

	Persons CgfkchkCsldDbenCsldDben(Long personId);

	Object CgrichkCaseloadDeductionPr(CaseloadDeductionDetails paramBean);

	List<OmsModules> CreateFormGlobals(OmsModules paramBean);

	List<Corporates> rgCorpRecordGroup();

	BigDecimal getPriorityAmount(String caseloadId, String deductionType, BigDecimal priority, BigDecimal amount);

	String calculateOn(String deductionType);

	Map<String, Object> calcBenTotal(String caseloadId, String deductionType);

	Boolean checkMaxAmount(CaseloadDeductionProfiles bean);

	Boolean percentage(CaseloadDeductionProfiles bean);

	BigDecimal countMinBalLogic(String caseloadId, String deductionType);

	String singleCommit(CaseloadDeductionProfilesCommitBean commitBean);

	Map<String, Object> preCommit(String caseloadId, String deductionType);

}
