package net.syscon.s4.triggers;

import java.math.BigDecimal;

import net.syscon.s4.cf.deductions.beans.OffenderMonDeductions;
import net.syscon.s4.inmate.beans.OffenderBeneficiaries;
import net.syscon.s4.inmate.beans.OffenderDeductions;

public interface OffenderBeneficiariesT1Repository {
	
	OffenderDeductions dedAmountType(Long offenderDeductionId);
	
	String thisMonthBeneficiary(Long beneficiaryId);
	
	Integer updateOffenderMonBeneficiaries(Long beneficiaryId,BigDecimal receivedAmount );
	
	Integer insertOffenderMonBeneficiaries(OffenderBeneficiaries obj);
	
	OffenderMonDeductions getOffenderMonBeneficiaries(Long beneficiaryId);
}
