package net.syscon.s4.triggers;

import net.syscon.s4.inmate.beans.OffenderDeductions;

public interface OffenderBeneficiariesT2Repository {
	
	OffenderDeductions dedAmountType(Long offenderDeductionId);
	
}
