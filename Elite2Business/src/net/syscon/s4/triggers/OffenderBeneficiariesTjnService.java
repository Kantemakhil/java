package net.syscon.s4.triggers;

import net.syscon.s4.inmate.beans.OffenderBeneficiaries;

public interface OffenderBeneficiariesTjnService {

	Integer offenderBeneficiariesTjn(final OffenderBeneficiaries newObj, final OffenderBeneficiaries oldObj, final String operation);

}
