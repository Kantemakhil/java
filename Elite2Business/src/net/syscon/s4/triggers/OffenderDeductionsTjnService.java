package net.syscon.s4.triggers;

import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.inmate.beans.OffenderDeductions;

public interface OffenderDeductionsTjnService {
	
	void offenderDeductionsTjn(OffenderDeductions newRec,OffenderDeductions oldRec, String operationType);

}
