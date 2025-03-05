package net.syscon.s4.triggers;

import java.math.BigDecimal;

import net.syscon.s4.cf.deductions.beans.FeeAccountProfiles;

public interface OffFeeAccountProfileT2Repository {
	
	Integer OffFeeAccProfileT2InsertA(FeeAccountProfiles feeAccountProfiles);
	
	Integer OffFeeAccProfileT2InsertP(FeeAccountProfiles feeAccountProfiles);
	
	String OldOffFeeActStatus(BigDecimal offenderFeeId);

}
