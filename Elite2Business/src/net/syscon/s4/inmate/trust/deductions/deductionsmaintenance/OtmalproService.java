package net.syscon.s4.inmate.trust.deductions.deductionsmaintenance;
import java.util.Date;
import java.util.List;

import net.syscon.s4.common.beans.TransactionTypes;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.im.beans.CaseloadDeductionDetails;
import net.syscon.s4.im.beans.CaseloadDeductionDetailsCommitBean;
import net.syscon.s4.inmate.beans.CaseloadDeductionProfiles;
import net.syscon.s4.inmate.beans.CaseloadDeductionProfilesCommitBean;
import net.syscon.s4.inmate.beans.DeductionTypes;
/**
 * Interface OtmalproService 
 */
public interface OtmalproService  {
	List<DeductionTypes> cgfkCsldDpDeductionTypeRecordGroup(String caseloadType) ;

	String csldDpCommit(CaseloadDeductionProfilesCommitBean commitBean) ;

	List<CaseloadDeductionDetails> csldDdExecuteQuery(CaseloadDeductionDetails objCaseloadDeductionDetails) ;

	List<TransactionTypes> cgfkCsldDdReceiptTxnTypeRecordGroup(String caseloadType) ;

	Integer csldDdCommit(CaseloadDeductionDetailsCommitBean CommitBean) ;

	List<CaseloadDeductionProfiles> csldDpExecuteQuery(CaseloadDeductionProfiles objCaseloadDeductionProfiles) ;

	List<AccountCodes> cgfkCsldDpAccountCodeRecordGroup(String caseloadType) ;

	String getfromBalDesc(String deductionType);

	Integer compareEffectiveDatec(String effectiveDate);

	List<CaseloadDeductionProfiles> checkExists(String caseloadId, String deductionType);
	
	String getfromBalTypes(String deductionType);
	
	String allocTypeValidation(String allocType,String caseloadId);

}

