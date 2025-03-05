package net.syscon.s4.inmate.trust.financialsmaintenance;

import java.util.List;

import net.syscon.s4.common.beans.Caseloads;
import net.syscon.s4.im.beans.CaseloadLimits;
import net.syscon.s4.im.beans.CaseloadLimitsCommitBean;
import net.syscon.s4.im.beans.ReferenceCodes;

/**
 * Interface OtmcslimService
 */

public interface OtmcslimService {
	List<ReferenceCodes> cgfkCsldLimPeriodTypeRecordGroup();

	List<Caseloads> cgfkCsldLimCaseloadIdRecordGroup();

	List<ReferenceCodes> cgfkCsldLimLimitTypeRecordGroup();

	List<CaseloadLimits> csldLimExecuteQuery(CaseloadLimits objCaseloadLimits);

	List<ReferenceCodes> cgfkchkCsldLimCsldLimTyp(ReferenceCodes paramBean);

	List<Caseloads> cgfkchkCsldLimCsldLimCsl(Caseloads paramBean);

	String csldLimCommit(CaseloadLimitsCommitBean CommitBean);

	List<ReferenceCodes> cgfkchkCsldLimCsldLimPer(ReferenceCodes paramBean);

}
