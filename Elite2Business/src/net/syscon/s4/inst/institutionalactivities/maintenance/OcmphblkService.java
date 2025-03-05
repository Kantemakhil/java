package net.syscon.s4.inst.institutionalactivities.maintenance;

import java.util.List;

import net.syscon.s4.inst.casemanagement.beans.ProgramServices;
import net.syscon.s4.inst.casemanagement.beans.ProgramServicesCommitBean;

/**
 * Interface OcmphblkService
 */
public interface OcmphblkService {
	Integer prgSrvCommit(ProgramServicesCommitBean commitBean);

	List<ProgramServices> prgSrvExecuteQuery(ProgramServices objProgramServices);

	Integer getNextPrgSrvListSeq(ProgramServices programServices);

}
