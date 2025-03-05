package net.syscon.s4.inst.movements.proposedmovements;

import java.util.List;
import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.inst.movements.proposedmovements.beans.OffenderProposedIntlocs;
import net.syscon.s4.inst.movements.proposedmovements.beans.VHousingMoves;
import net.syscon.s4.inst.transportation.maintenance.OffenderLocChngDtls;


/**
 * Interface OidhoustService
 */
public interface OidhoustService {

	List<ReferenceCodes> rgAgyIdRecordGroup(String caseloadId);

	List<ReferenceCodes> rgLocFromRecordGroup(String agyLocId);

	List<ReferenceCodes> rgLocToRecordGroup(String agyLocId);

	List<ReferenceCodes> rgMoveReasonRecordGroup(String movementType);

	List<ReferenceCodes> rgMoveTypeRecordGroup();

	List<ReferenceCodes> rgTxnStatusRecordGroup();

	List<ReferenceCodes> rgAppStatusRecordGroup();

	List<VHousingMoves> housMoveExecuteQuery(VHousingMoves searchBean);

	List<OffenderProposedIntlocs> populateInmaDetails(VHousingMoves searchBean);

	Integer inmateCommit(VHousingMoves updateBean);

	Integer statDetCommit(OffenderLocChngDtls commiBean);

	List<OffenderLocChngDtls> populatestatDetDetails(VHousingMoves searchBean);

	String getCurInmAppStatus(VHousingMoves searchBean);

}
