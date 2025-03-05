package net.syscon.s4.sa.admin;

import java.util.List;

import net.syscon.s4.im.beans.SysDual;
import net.syscon.s4.inst.automatedcounts.beans.LockedModules;

/**
 * Interface OtmlockrRepository
 */
public interface OtmlockrRepository {
	List<SysDual> cgwhenNewFormInstance(SysDual paramBean);

	Integer lockModDeleteLockedModules(List<LockedModules> lstLockedModules);

	Integer lockModInsertLockedModules(List<LockedModules> lstLockedModules);

	List<LockedModules> lockModExecuteQuery(LockedModules objLockedModules);

	Integer lockModUpdateLockedModules(List<LockedModules> lstLockedModules);
	
	Long getRepotingLocId(LockedModules lockedModules);
	
	Integer deleteInitiateRecords(Long repotingLocId);
	
	Integer deleteInitiateRecordsOfAgencyLocationCounts(Long repotingLocId);
	
}
