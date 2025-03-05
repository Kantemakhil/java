package net.syscon.s4.triggers;

import net.syscon.s4.inst.automatedcounts.beans.TempOidcount;

public interface AgencyLocationCountsT1Repository {

	Integer update(TempOidcount tempOidcoun);
	
	Integer getRecountTotal(TempOidcount tempOidcoun);

}
