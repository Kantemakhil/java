
package net.syscon.s4.inst.movements.proposedmovements;

import java.util.List;

import net.syscon.s4.common.beans.OffenderExternalMovements;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.inst.movements.proposedmovements.beans.OffenderProposedMvmnts;
import net.syscon.s4.inst.movements.proposedmovements.beans.OffenderProposedMvmntsCommitBean;
import net.syscon.s4.inst.transportation.maintenance.beans.OffenderMovementDetails;

/**
 * Interface OidpeximService
 */
public interface OidpeximService {

	List<ReferenceCodes> rgMoveReasonRecordGroup(final String movementType);
	
	Integer proposedMvmntsCommite(OffenderProposedMvmntsCommitBean insertList);
	
	List<ReferenceCodes> fromAgyRecordGroup();

	List<ReferenceCodes> rgMoveTypeRecordGroup();

	List<ReferenceCodes> rgAgyRecordGroup();
	
	List<OffenderProposedMvmnts> propMoveExecuteQuery(OffenderProposedMvmnts Offpropmvts) ;
	
	List<OffenderExternalMovements> offExtMovExecuteQuery(OffenderExternalMovements Offextmvts) ;

	OffenderMovementDetails getStatuses(OffenderProposedMvmnts searchBean);
	
	
	
	List<OffenderExternalMovements> getExtInCur();
	
}
