
package net.syscon.s4.inst.movements.proposedmovements;

import java.util.List;
import net.syscon.s4.common.beans.OffenderExternalMovements;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.inst.movements.proposedmovements.beans.OffenderProposedMvmnts;


/**
 * Interface OidpeximRepository
 */
public interface OidpeximRepository {

	List<ReferenceCodes> rgMoveReasonRecordGroup(final String movementType);
	
	Integer proposedMvmntsInsert(List<OffenderProposedMvmnts> insertList);
	
	Integer proposedMvmntsUpdate(List<OffenderProposedMvmnts> updateList);
	
	List<ReferenceCodes> fromAgyRecordGroup();

	List<ReferenceCodes> rgMoveTypeRecordGroup();

	List<ReferenceCodes> rgAgyRecordGroup();
	
	List<OffenderProposedMvmnts> propMoveExecuteQuery(OffenderProposedMvmnts Offpropmvts) ;
	
	List<OffenderExternalMovements> offExtMovExecuteQuery(OffenderExternalMovements Offextmvts) ;

	List<OffenderExternalMovements> getExtInCur();
	

	
}
