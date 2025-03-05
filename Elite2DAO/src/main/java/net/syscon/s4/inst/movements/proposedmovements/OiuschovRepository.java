package net.syscon.s4.inst.movements.proposedmovements;

import java.util.List;

import net.syscon.s4.inst.movements.proposedmovements.beans.VOffSchOverview;
/**
 * interface OiuschovRepository
 */
public interface OiuschovRepository {
	
	List<VOffSchOverview> vOffSchOverviewExecQuery(VOffSchOverview objvOffSchOverview) ;
	
	String moveTypeCur(String movementReason);
	
	String moveReasonCur(String movementReason);
	
	String moveReasonIntCur(String movementReason);

}
