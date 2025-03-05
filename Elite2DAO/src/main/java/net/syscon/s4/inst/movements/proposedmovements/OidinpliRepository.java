package net.syscon.s4.inst.movements.proposedmovements;

import java.util.List;

import net.syscon.s4.im.beans.LivingUnits;
import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.inst.casemanagement.beans.InternalScheduleReasons;
import net.syscon.s4.inst.movements.proposedmovements.beans.OffenderProposedIntlocs;
import net.syscon.s4.inst.movements.proposedmovements.beans.VHousingMoves;
import net.syscon.s4.inst.transportation.maintenance.OffenderLocChngDtls;

/**
 * Interface OidinpliRepository
 */
public interface OidinpliRepository {
	Integer statDetInsertOffenderProposedIntlocs(List<OffenderProposedIntlocs> lstOffenderProposedIntlocs);

	List<InternalScheduleReasons> rgMoveReasonRecordGroup(String movementType);

	List<LivingUnits> rgLocRecordGroup(String caseLoadId);

	Integer inmaDetUpdateOffenderProposedIntlocs(VHousingMoves commitBean);

	List<VHousingMoves> extrMoveExecuteQuery(VHousingMoves objVHousingMoves);

	List<InternalScheduleReasons> rgMoveTypeRecordGroup();

	Long capacityCur(Long livingUnitId);

	Long occupiedCur(Long livingUnitId);

	Long getMaxBedAssignSeqCur(Long offenderBookId);

	Long nextBedSeqCur(Long offenderBookId);

	Integer offBookUpdate(VHousingMoves updateBean);

	Integer bedAssignmentHistoriesUpdt(VHousingMoves updateBean);

	Integer bedAssignmentHistoriesInsert(VHousingMoves commitBean);

	List<ReferenceCodes> rgLocFromRecordGroup(String agyLocId);

	List<ReferenceCodes> rgLocToRecordGroup(String agyLocId);
	
	Integer poteSchCur(Integer offenderBookId);

	Integer stgNonAssoCur(Integer offenderBookId, Integer rootOffenderId);

}
