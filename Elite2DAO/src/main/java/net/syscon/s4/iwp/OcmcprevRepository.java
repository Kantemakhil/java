package net.syscon.s4.iwp;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.inst.casemanagement.beans.CaseReviewPeriods;

import java.util.List;

/**
 * Interface OcmcprevRepository
 */
public interface OcmcprevRepository {
	List<OmsModules> createFormGlobals(OmsModules paramBean);

	Integer caseReviewPeriodsInsertCaseReviewPeriods(List<CaseReviewPeriods> lstCaseReviewPeriods);

	Integer caseReviewPeriodsDeleteCaseReviewPeriods(List<CaseReviewPeriods> lstCaseReviewPeriods);

	List<ReferenceCodes> rgSupLevelRecordGroup();

	Integer caseReviewPeriodsUpdateCaseReviewPeriods(List<CaseReviewPeriods> lstCaseReviewPeriods);

	List<CaseReviewPeriods> caseReviewPeriodsExecuteQuery(CaseReviewPeriods objCaseReviewPeriods);

	List<ReferenceCodes> caseReviewPeriodsPostQuery(ReferenceCodes paramBean);

}
