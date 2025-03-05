package net.syscon.s4.iwp;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.inst.casemanagement.beans.CaseReviewPeriods;
import net.syscon.s4.inst.casemanagement.beans.CaseReviewPeriodsCommitBean;

/**
 * Interface OcmcprevService
 */
public interface OcmcprevService {
	List<ReferenceCodes> rgSupLevelRecordGroup();

	List<CaseReviewPeriods> caseReviewPeriodsCommit(CaseReviewPeriodsCommitBean CommitBean);

	List<ReferenceCodes> caseReviewPeriodsPostQuery(ReferenceCodes paramBean);

	List<CaseReviewPeriods> caseReviewPeriodsExecuteQuery(CaseReviewPeriods objCaseReviewPeriods);

}
