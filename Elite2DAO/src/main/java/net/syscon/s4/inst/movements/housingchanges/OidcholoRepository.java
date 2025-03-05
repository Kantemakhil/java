package net.syscon.s4.inst.movements.housingchanges;

import java.util.List;

import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.BedAssignmentHistories;
import net.syscon.s4.im.beans.LivingUnits;
import net.syscon.s4.inst.movements.housingchanges.beans.CourtMovementTmp;

public interface OidcholoRepository {
	List<ReferenceCodes> cgfkCrtMvTmpAgyLocIdRecordGroup(String caseload);

	List<ReferenceCodes> cgfkBedAhDspDescriptionRecordGroup();

	List<VHeaderBlock> cgfklkpBedAhBedAhVhbF1(VHeaderBlock paramBean);

	List<ReferenceCodes> cgfkchkCrtMvTmpCrtMvTmp(ReferenceCodes paramBean);

	List<VHeaderBlock> cgfkchkBedAhBedAhVhbF1(VHeaderBlock paramBean);

	List<ReferenceCodes> cgfkBedAhDspOffenderIdDiRecordGroup(String userName);

	AgencyLocations cgfkchkCrtMvTmpCrtMv(AgencyLocations paramBean);

	List<ReferenceCodes> cgfkchkBedAhBedAhRefCod(ReferenceCodes paramBean);

	ReferenceCodes cgfklkpBedAhBedAhRefCod(ReferenceCodes paramBean);

	List<LivingUnits> cgfkCrtMvTmpDspLiving4RecordGroup(String agyLocId);

	List<VHeaderBlock> crtMvTmpExecuteQuery(CourtMovementTmp objCourtMovementTmp);

	Integer crtMvTmpDeleteCourtMovementTmp(List<CourtMovementTmp> lstCourtMovementTmp);

	List<LivingUnits> cgfkCrtMvTmpDspLiving3RecordGroup(String agyLocId, String livingUnitId);

	Integer crtMvTmpUpdateCourtMovementTmp(List<BedAssignmentHistories> lstCourtMovementTmp);

	Integer crtMvTmpInsertCourtMovementTmp(List<BedAssignmentHistories> lstCourtMovementTmp);

	Integer oidcholoCrtmvtmpUpdateOffbkgs(List<OffenderBookings> offBkgsList);

	List<LivingUnits> cgfklkpCrtMvTmpCrtMvTmp(LivingUnits paramBean);

	List<ReferenceCodes> cgfkCrtMvTmpMovementReasoRecordGroup();

	List<ReferenceCodes> cgfkCrtMvTmpDspLiving2RecordGroup();

	List<LivingUnits> cgfkchkCrtMvTmpCrtMv(LivingUnits paramBean);

	List<LivingUnits> cgfklkpCrtMvTmpCrtMv(LivingUnits paramBean);

	List<ReferenceCodes> cgfkCrtMvTmpDspLivingUniRecordGroup();

	List<VHeaderBlock> oidcholoCgfklkpBedAhBedDatetimeProc(Integer livingUnitId, Integer offenderBookId);
	
	List<OffenderBookings> getOldRecOffBooking(Integer offenderBookId);

}
