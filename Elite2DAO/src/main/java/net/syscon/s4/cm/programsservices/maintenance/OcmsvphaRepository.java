package net.syscon.s4.cm.programsservices.maintenance;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.VProgramPhases;

/**
 * Interface OcmsvphaRepository
 */
public interface OcmsvphaRepository {
	List<ReferenceCodes> rgPsModTypeRecordGroup();

	List<ReferenceCodes> rgPsPhaseRecordGroup();

	VProgramPhases vPrgPhssUpdateVProgramPhases(List<VProgramPhases> lstVProgramPhases);

	VProgramPhases vPrgPhssDeleteVProgramPhases(List<VProgramPhases> lstVProgramPhases);

	List<VProgramPhases> vPrgPhssExecuteQuery(VProgramPhases objVProgramPhases);

	VProgramPhases vPrgPhssInsertVProgramPhases(List<VProgramPhases> lstVProgramPhases);

	String getDescription(String description);

	Integer getListSeqMaxCount(BigDecimal programId);

	Integer getCourceActivityCount(BigDecimal programId);

	BigDecimal getProgramIdSeq();

	String getDescriptionOne(String description);

	String getModuleTypeDescription(final String code);

	String errorNameValidation(String sealFlag);

}
