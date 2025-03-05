package net.syscon.s4.inst.movements.housingchanges;

import java.util.List;

import net.syscon.s4.common.beans.BedAssignmentHistoriesCommitBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.LivingUnits;
import net.syscon.s4.inst.movements.housingchanges.beans.CourtMovementTmp;

public interface OidcholoService {
	List<VHeaderBlock> CgfklkpBedAhBedAhVhbF1(VHeaderBlock paramBean);

	List<ReferenceCodes> cgfkCrtMvTmpAgyLocIdRecordGroup(String caseload);

	List<ReferenceCodes> cgfkBedAhDspDescriptionRecordGroup();

	List<VHeaderBlock> CgfkchkBedAhBedAhVhbF1(VHeaderBlock paramBean);

	List<ReferenceCodes> cgfkBedAhDspOffenderIdDiRecordGroup(String userName);

	AgencyLocations agyCgfkchkCrtMvTmpCrtMv(AgencyLocations paramBean);

	List<LivingUnits> CgfklkpCrtMvTmpCrtMv(LivingUnits paramBean);

	List<LivingUnits> cgfkCrtMvTmpDspLiving4RecordGroup(String agyLocId);

	List<VHeaderBlock> crtMvTmpExecuteQuery(CourtMovementTmp objCourtMovementTmp);

	ReferenceCodes CgfklkpBedAhBedAhRefCod(ReferenceCodes paramBean);

	List<ReferenceCodes> CgfkchkCrtMvTmpCrtMvTmp(ReferenceCodes paramBean);

	List<ReferenceCodes> CgfkchkBedAhBedAhRefCod(ReferenceCodes paramBean);

	List<LivingUnits> untCgfkchkCrtMvTmpCrtMv(LivingUnits paramBean);

	List<LivingUnits> cgfkCrtMvTmpDspLiving3RecordGroup(String agyLocId, String livingUnitId);

	Integer crtMvTmpCommit(BedAssignmentHistoriesCommitBean commitBean);

	List<ReferenceCodes> cgfkCrtMvTmpMovementReasoRecordGroup();

	List<ReferenceCodes> cgfkCrtMvTmpDspLiving2RecordGroup();

	List<ReferenceCodes> cgfkCrtMvTmpDspLivingUniRecordGroup();

	List<LivingUnits> CgfklkpCrtMvTmpCrtMvTmp(LivingUnits paramBean);

	List<VHeaderBlock> oidcholoCgfklkpBedAhBedDatetimeProc(Integer livingUnitId, Integer offenderBookId);

}
