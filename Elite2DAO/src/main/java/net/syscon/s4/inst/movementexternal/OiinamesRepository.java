package net.syscon.s4.inst.movementexternal;

import java.util.List;

import net.syscon.s4.common.beans.Caseloads;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.common.beans.VNameSearch;
import net.syscon.s4.im.beans.Dual;
import net.syscon.s4.im.beans.SysDual;

/**
 * Interface OiinamesRepository
 * 
 */
public interface OiinamesRepository {
	List<Dual> cgwhenNewFormInstance(SysDual paramBean);

	List<VNameSearch> nameSrchExecuteQuery(VNameSearch objVNameSearch);

	List<SystemProfiles> sysPflExecuteQuery(SystemProfiles objSystemProfiles);

	Integer nameSrchInsertVNameSearch(List<VNameSearch> lstVNameSearch);

	List<SysDual> cgfdgetNameSrchDrvActive(SysDual paramBean);

	List<String> findAgyLocIdList(String userName);

	List<String> findLivingUnitsList(String userName);

	List<VHeaderBlock> offbkgGlobalQuery(Integer offenderBookId, String userId);

	List<String> findActiveFlagList();

	List<Caseloads> findAgyLocIdListLov();

}
