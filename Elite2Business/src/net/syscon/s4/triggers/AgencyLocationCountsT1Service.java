package net.syscon.s4.triggers;

import net.syscon.s4.inst.automatedcounts.beans.TempOidcount;

public interface AgencyLocationCountsT1Service {

	Integer agencyLocationCountsT1Trigger(TempOidcount tempOidcoun, String rcntInProgressFlag);

}
