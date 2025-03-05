package net.syscon.s4.inmate.trust.financialsmaintenance.transaction;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.common.beans.CaseloadAccountPeriods;
import net.syscon.s4.common.beans.Caseloads;
import net.syscon.s4.im.beans.SysDual;

/**
 * Interface OtmoncoaRepository
 */

public interface OtmoncoaRepository {
	List<Object> cgwhenNewFormInstance(SysDual paramBean);

	List<CaseloadAccountPeriods> csldApExecuteQuery(CaseloadAccountPeriods objCaseloadAccountPeriods);

	Integer csldApInsertCaseloadAccountPeriods(List<CaseloadAccountPeriods> lstCaseloadAccountPeriods);

	List<Caseloads> cgfkCsldApCaseloadIdRecordGroup(String caseloadType);

	List<Caseloads> cgfkchkCsldApCsldApCsld(Caseloads paramBean);

	Integer csldApDeleteCaseloadAccountPeriods(List<CaseloadAccountPeriods> lstCaseloadAccountPeriods);

	BigDecimal getTotalCount(String caseloadId);

	void otmoncoaGenAccountCodes(String caseloadId);

}
