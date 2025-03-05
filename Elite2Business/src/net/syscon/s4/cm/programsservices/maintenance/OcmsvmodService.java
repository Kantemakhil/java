package net.syscon.s4.cm.programsservices.maintenance;

import java.util.List;

/**
 * Interface OcmsvmodService
 */
public interface OcmsvmodService {
	List<VProgramModules> vPrgMdlsExecuteQuery(VProgramModules objVProgramModules);

	VProgramModules vPrgMdlsCommit(VProgramModulesCommitBean commitBean);

	Integer getListSeqMaxCount(Integer programPhaseId);

}
