package net.syscon.s4.inst.institutionalactivities.maintenance;

import java.util.List;

import net.syscon.s4.inst.casemanagement.beans.ProgramServices;

/**
 * Interface OcmphblkRepository
 */
public interface OcmphblkRepository {
	Integer prgSrvUpdateProgramServices(List<ProgramServices> lstProgramServices);

	List<ProgramServices> prgSrvExecuteQuery(ProgramServices objProgramServices);

	Integer prgSrvInsertProgramServices(List<ProgramServices> lstProgramServices);

	Integer prgSrvDeleteProgramServices(List<ProgramServices> lstProgramServices);

	Integer checkNextPrgSrvSeqUnique(ProgramServices object);

	Integer getNextPrgSrvListSeq(ProgramServices object);

	Integer getProgramIdSeq(ProgramServices object);

	Integer getCheckNextPrgSrvSeqUnique(ProgramServices object);

}
