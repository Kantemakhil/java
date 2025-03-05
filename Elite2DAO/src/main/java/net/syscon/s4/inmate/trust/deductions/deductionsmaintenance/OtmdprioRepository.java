package net.syscon.s4.inmate.trust.deductions.deductionsmaintenance;

import java.util.List;

import net.syscon.s4.im.beans.SysDual;
import net.syscon.s4.inmate.beans.CaseloadDeductionProfiles;

/**
 * Interface OtmdprioRepository
 */
public interface OtmdprioRepository {
	List<Object> cgwhenNewFormInstance(SysDual paramBean);

	Integer csldDpUpdateCaseloadDeductionProfiles(List<CaseloadDeductionProfiles> lstCaseloadDeductionProfiles);

	List<CaseloadDeductionProfiles> csldDpExecuteQuery(CaseloadDeductionProfiles objCaseloadDeductionProfiles);

	List<CaseloadDeductionProfiles> getBalTypeDesc(String deductionType);

}
