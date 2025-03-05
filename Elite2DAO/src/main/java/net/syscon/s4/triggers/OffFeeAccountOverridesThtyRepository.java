package net.syscon.s4.triggers;

import net.syscon.s4.cf.deductions.maintenance.beans.FeeOverrideDetails;

public interface OffFeeAccountOverridesThtyRepository {
	Integer OffFeeAccountOvverRideHistory(FeeOverrideDetails feeOverrideDetails);
}
