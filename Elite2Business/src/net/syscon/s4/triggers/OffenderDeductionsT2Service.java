package net.syscon.s4.triggers;

import net.syscon.s4.inmate.beans.OffenderDeductions;

public interface OffenderDeductionsT2Service {
	
	void offenderDeductionsT2Trigger(final OffenderDeductions newRec,final OffenderDeductions oldRec,final String operationType);
}
