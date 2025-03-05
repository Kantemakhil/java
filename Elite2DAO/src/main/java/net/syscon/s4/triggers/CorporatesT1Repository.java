package net.syscon.s4.triggers;

import java.math.BigDecimal;

public interface CorporatesT1Repository {
	
	Integer gettingAddressCount(BigDecimal corporateId);

	Integer gettingPhoneCount(BigDecimal corporateId);

	Integer gettingInternetAddressCount(BigDecimal corporateId);

}
