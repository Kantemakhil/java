package net.syscon.s4.triggers;

import net.syscon.s4.cf.deductions.beans.OffenderMonDeductions;

public interface OffenderMonBeneficiariesTjnService {
	
	Integer offenderMonBeneficiariesTjn(final OffenderMonDeductions newObj, final OffenderMonDeductions oldObj, final String operation);

}
