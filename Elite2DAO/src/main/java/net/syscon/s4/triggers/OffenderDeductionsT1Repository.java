package net.syscon.s4.triggers;

import java.math.BigDecimal;

import net.syscon.s4.inmate.beans.OffenderDeductions;

public interface OffenderDeductionsT1Repository {

	BigDecimal getNextSeq(Long offenderDeductionId);

	Integer insOffOblHty(OffenderDeductions bean);

	Integer updOffOblHty(OffenderDeductions bean);

	OffenderDeductions getMaxTotalAmt(OffenderDeductions bean);

}
