package net.syscon.s4.inmate;

import java.util.List;

import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.im.beans.Dual;
import net.syscon.s4.im.beans.OffenderTrustAccounts;
import net.syscon.s4.im.beans.SysDual;
import net.syscon.s4.im.beans.VTrustHeader;

/**
 * Interface OtinamesRepository
 */
public interface OtinamesRepository {
	List<VTrustHeader> vThaExecuteQuery(VTrustHeader objVTrustHeader, String defWhere);

	List<Dual> cgwhenNewFormInstance(SysDual paramBean);

	List<SystemProfiles> sysPflExecuteQuery(SystemProfiles objSystemProfiles);

	OffenderTrustAccounts cgfkchkVThaVThaOffTaF(OffenderTrustAccounts paramBean);

	OffenderTrustAccounts vThaPostQuery(VTrustHeader vSearchData);

	Integer vscMtCur(String caseloadId);

}
