package net.syscon.s4.triggers;

import net.syscon.s4.cf.deductions.beans.OffenderMonDeductions;

public interface OffenderMonBeneficiariesTjnRepository {

	Integer insertOffenderMonBeneficiariesTjn(final OffenderMonDeductions obj);

	Integer updateOffenderMonBeneficiariesTjn(final OffenderMonDeductions obj);

	Integer deleteOffenderMonBeneficiariesTjn(final OffenderMonDeductions obj);

}
