package net.syscon.s4.inmate.trust.deductions.deductionsmaintenance;

import java.util.List;

import net.syscon.s4.inmate.beans.CaseloadDeductionProfiles;
import net.syscon.s4.inmate.beans.CaseloadDeductionProfilesCommitBean;

/**
 * Interface OtmdprioService
 */
public interface OtmdprioService {

	Integer csldDpCommit(CaseloadDeductionProfilesCommitBean commitBean);

	List<CaseloadDeductionProfiles> csldDpExecuteQuery(CaseloadDeductionProfiles objCaseloadDeductionProfiles);

}
