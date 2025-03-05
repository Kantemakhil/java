package net.syscon.s4.triggers;

import net.syscon.s4.inmate.beans.OffenderObligationHty;

public interface OffenderDeductionsT2Repository {
	
	Integer insertOffenderObligationHty(OffenderObligationHty obj);
	
	Long nextDedSeqC(Long offenderDeductionId);
}
