package net.syscon.s4.inmate.trust.financialsmaintenance;

import java.util.List;

import net.syscon.s4.common.beans.Caseloads;
import net.syscon.s4.im.beans.CaseloadLimits;
import net.syscon.s4.im.beans.ReferenceCodes;

/**
 * Interface OtmcslimRepository
 */

public interface OtmcslimRepository {
	List<ReferenceCodes> cgfkchkCsldLimCsldLimPer(ReferenceCodes paramBean);

	List<ReferenceCodes> cgfkCsldLimPeriodTypeRecordGroup();

	Integer csldLimInsertCaseloadLimits(List<CaseloadLimits> lstCaseloadLimits);

	List<Caseloads> cgfkchkCsldLimCsldLimCsl(Caseloads paramBean);

	List<Caseloads> cgfkCsldLimCaseloadIdRecordGroup();

	List<ReferenceCodes> cgfkCsldLimLimitTypeRecordGroup();

	List<CaseloadLimits> csldLimExecuteQuery(CaseloadLimits objCaseloadLimits);

	Integer csldLimDeleteCaseloadLimits(List<CaseloadLimits> lstCaseloadLimits);

	List<ReferenceCodes> cgfkchkCsldLimCsldLimTyp(ReferenceCodes paramBean);

	Integer csldLimUpdateCaseloadLimits(List<CaseloadLimits> lstCaseloadLimits);

	String getCaseloadIdDesc(String caseloadId);

}
