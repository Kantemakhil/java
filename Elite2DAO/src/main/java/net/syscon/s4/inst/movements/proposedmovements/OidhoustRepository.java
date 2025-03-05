package net.syscon.s4.inst.movements.proposedmovements;

import java.util.List;
import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.inst.movements.proposedmovements.beans.OffenderProposedIntlocs;
import net.syscon.s4.inst.movements.proposedmovements.beans.VHousingMoves;
import net.syscon.s4.inst.transportation.maintenance.OffenderLocChngDtls;

/**
 * Interface OidhoustRepository
 */
public interface OidhoustRepository {

	List<ReferenceCodes> rgAgyIdRecordGroup(String caseloadId);

	List<ReferenceCodes> rgLocFromRecordGroup(String agyLocId);

	List<ReferenceCodes> rgLocToRecordGroup(String agyLocId);

	List<ReferenceCodes> rgMoveTypeRecordGroup();

	List<ReferenceCodes> rgMoveReasonRecordGroup(String movementType);

	List<ReferenceCodes> rgAppStatusRecordGroup();

	List<ReferenceCodes> rgTxnStatusRecordGroup();

	List<VHousingMoves> housMoveExecuteQuery(VHousingMoves searchBean);

	List<OffenderProposedIntlocs> populateInmaDetails(VHousingMoves searchBean);

	Integer inmateCommit(VHousingMoves updateBean);

	List<OffenderLocChngDtls> populateStatDetDetails(VHousingMoves searchBean);

	String getCurInmAppStatus(VHousingMoves searchBean);

}
