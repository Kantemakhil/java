package net.syscon.s4.sa.admin;

import java.util.List;

import net.syscon.s4.inst.automatedcounts.beans.LockedModules;
import net.syscon.s4.sa.admin.beans.LockedModulesCommitBean;

/**
 * Interface OtmlockrService
 */
public interface OtmlockrService {

	List<LockedModules> lockModExecuteQuery(LockedModules objLockedModules);

	Integer lockModCommit(LockedModulesCommitBean commitBean);

}
