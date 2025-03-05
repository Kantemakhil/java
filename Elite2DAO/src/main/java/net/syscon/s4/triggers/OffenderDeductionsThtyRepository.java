package net.syscon.s4.triggers;

import java.util.List;

public interface OffenderDeductionsThtyRepository {
	Integer insertingUpdateDelete(List<OffenderDeductionsHty> offenderDeducHty);

	OffenderDeductionsHty getOffenderDeductionsHty(OffenderDeductionsHty offenderDeducThty);

}
