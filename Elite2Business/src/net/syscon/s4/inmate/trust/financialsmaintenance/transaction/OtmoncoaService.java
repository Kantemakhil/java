package net.syscon.s4.inmate.trust.financialsmaintenance.transaction;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.common.beans.CaseloadAccountPeriods;
import net.syscon.s4.common.beans.CaseloadAccountPeriodsCommitBean;
import net.syscon.s4.common.beans.Caseloads;

/**
 * Interface OtmoncoaService
 */
public interface OtmoncoaService {
	List<CaseloadAccountPeriods> csldApExecuteQuery(CaseloadAccountPeriods objCaseloadAccountPeriods);

	List<Caseloads> cgfkCsldApCaseloadIdRecordGroup(String caseloadType);

	List<Caseloads> CgfkchkCsldApCsldApCsld(Caseloads paramBean);

	List<Object> CgwhenNewFormInstance();

	String csldApCommit(CaseloadAccountPeriodsCommitBean commitBean);

	BigDecimal getTotalCount(String caseloadId);

}
