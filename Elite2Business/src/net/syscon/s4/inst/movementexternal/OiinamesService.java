package net.syscon.s4.inst.movementexternal;

import java.util.List;

import net.syscon.s4.common.beans.Caseloads;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.SystemProfilesCommitBean;
import net.syscon.s4.common.beans.VNameSearch;
import net.syscon.s4.common.beans.VNameSearchCommitBean;
import net.syscon.s4.im.beans.Dual;
import net.syscon.s4.im.beans.SysDual;

/**
 * Interface OiinamesService
 * 
 *
 */
public interface OiinamesService {
	List<Object> CgwhenNewFormInstance();

	Integer nameSrchCommit(VNameSearchCommitBean CommitBean);

	List<VNameSearch> nameSrchExecuteQuery(VNameSearch objVNameSearch);

	List<SystemProfiles> sysPflExecuteQuery(SystemProfiles objSystemProfiles);

	Integer sysPflCommit(SystemProfilesCommitBean commitBean);

	List<Object> CgfdgetNameSrchDrvActive();

	List<String> findAgyLocIdList(String userName);

	List<String> findLivingUnitsList(String userName);

	List<String> findActiveFlagList();

	List<Dual> cgwhenNewFormInstance(SysDual sysDual);

	List<Caseloads> findAgyLocIdListLov();

}
