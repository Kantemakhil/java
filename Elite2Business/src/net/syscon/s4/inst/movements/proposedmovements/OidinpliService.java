package net.syscon.s4.inst.movements.proposedmovements;

import java.util.List;

import net.syscon.s4.im.beans.LivingUnits;
import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.inst.casemanagement.beans.InternalScheduleReasons;
import net.syscon.s4.inst.movements.proposedmovements.beans.VHousingMoves;
import net.syscon.s4.inst.movements.proposedmovements.beans.VHousingMovesCommitBean;
import net.syscon.s4.inst.transportation.maintenance.OffenderLocChngDtls;

/**
 * Interface OidinpliService
 */
public interface OidinpliService {

	Integer inmaDetCommit(VHousingMoves commitBean);

	List<InternalScheduleReasons> rgMoveReasonRecordGroup(String movementType);

	List<LivingUnits> rgLocRecordGroup(String caseLoadId);

	List<VHousingMoves> extrMoveExecuteQuery(VHousingMoves objVHousingMoves);

	List<OffenderLocChngDtls> statDetExecuteQuery(OffenderLocChngDtls objOffenderProposedIntlocs);

	List<InternalScheduleReasons> rgMoveTypeRecordGroup();

	Integer saveStatDet(OffenderLocChngDtls bean);

	Integer transactCommitQuery(VHousingMovesCommitBean commitBean);

	List<ReferenceCodes> rgLocFromRecordGroup(String agyLocId);

	List<ReferenceCodes> rgLocToRecordGroup(String fromAgyLocId);

}
