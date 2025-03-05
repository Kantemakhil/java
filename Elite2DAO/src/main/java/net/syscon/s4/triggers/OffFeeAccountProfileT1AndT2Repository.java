package net.syscon.s4.triggers;

import net.syscon.s4.cf.deductions.beans.FeeAccountProfiles;

public interface OffFeeAccountProfileT1AndT2Repository {
	Integer insertAccountProfiles(FeeAccountProfiles profiles);
}
