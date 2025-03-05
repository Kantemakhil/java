package net.syscon.s4.inst.movements.proposedmovements;

import java.util.List;

import net.syscon.s4.inst.movements.proposedmovements.beans.VOffSchOverview;

public interface OiuschcoRepository {

	List<VOffSchOverview> vOffSchOverviewExecuteQuery(VOffSchOverview searchRecord);

}
