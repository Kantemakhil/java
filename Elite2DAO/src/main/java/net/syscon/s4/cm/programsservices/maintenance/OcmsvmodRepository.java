package net.syscon.s4.cm.programsservices.maintenance;

import java.util.List;

/**
 * Interface OcmsvmodRepository
 */
public interface OcmsvmodRepository {
	VProgramModules vPrgMdlsUpdateVProgramModules(List<VProgramModules> lstVProgramModules);

	List<VProgramModules> vPrgMdlsExecuteQuery(VProgramModules objVProgramModules);

	VProgramModules vPrgMdlsDeleteVProgramModules(List<VProgramModules> lstVProgramModules);

	VProgramModules vPrgMdlsInsertVProgramModules(List<VProgramModules> lstVProgramModules);

	Integer checkUniqueListSeq(VProgramModules objSearchDao);

	Integer getProgramIdSeq();

	Integer getSessionCount(VProgramModules objSearchDao);

	int doUpdateOnPhase(int programPhaseId, int sessionCount);

	Integer getListSeqMaxCount(Integer programPhaseId);
}
