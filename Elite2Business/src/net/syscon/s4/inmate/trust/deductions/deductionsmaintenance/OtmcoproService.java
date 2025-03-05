package net.syscon.s4.inmate.trust.deductions.deductionsmaintenance;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import net.syscon.s4.common.beans.TransactionTypes;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.im.beans.CaseloadDeductionDetails;
import net.syscon.s4.im.beans.CaseloadDeductionDetailsCommitBean;
import net.syscon.s4.im.beans.Corporates;
import net.syscon.s4.inmate.beans.CaseloadDeductionProfiles;
import net.syscon.s4.inmate.beans.CaseloadDeductionProfilesCommitBean;
import net.syscon.s4.inmate.beans.DeductionTypes;
import net.syscon.s4.inst.booking.beans.Persons;

/**
 * Interface OtmcoproService
 */
public interface OtmcoproService {
	List<DeductionTypes> cgfkCsldDpDeductionTypeRecordGroup(String caseloadType);

	String csldDdCommit(CaseloadDeductionDetailsCommitBean commitBean);

	List<CaseloadDeductionProfiles> csldDpExecuteQuery(CaseloadDeductionProfiles objCaseloadDeductionProfiles);

	List<AccountCodes> cgfkCsldDpAccountCodeRecordGroup(String caseloadType);

	String csldDpCommit(CaseloadDeductionProfilesCommitBean commitBean);

	List<CaseloadDeductionDetails> csldDdExecuteQuery(CaseloadDeductionDetails objCaseloadDeductionDetails);

	List<TransactionTypes> cgfkCsldDdReceiptTxnTypeRecordGroup(String caseloadType);

	List<Persons> cgfkCsldDpPayeePersonIdRecordGroup();

	List<Corporates> cgfkCsldDpPayeeCorporateIRecordGroup();

	Map<String, Object> preCommit(String caseloadId, String deductionType);

	String getCalculateOnVal(String deductionType);

	String chkDuplicate(String caseloadId, String deductionType);

	Corporates cgfkchkCsldDbenCsldDben(BigDecimal corporateId);

}
