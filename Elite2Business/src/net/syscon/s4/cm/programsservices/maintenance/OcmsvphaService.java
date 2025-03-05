package net.syscon.s4.cm.programsservices.maintenance;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.VProgramPhases;

/**
 * Interface OcmsvphaService
 */
public interface OcmsvphaService {
	List<ReferenceCodes> rgPsModTypeRecordGroup();

	VProgramPhases vPrgPhssCommit(VProgramPhasesCommitBean commitBean);

	List<ReferenceCodes> rgPsPhaseRecordGroup();

	List<VProgramPhases> vPrgPhssExecuteQuery(VProgramPhases objVProgramPhases);

	Integer getListSeqMaxCount(BigDecimal bigDecimal);

	Integer getCourceActivityCount(BigDecimal bigDecimal);

	String errorNameValidation(String sealFlag);

}
