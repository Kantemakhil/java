package net.syscon.s4.triggers;

public interface OffendersT1Repository {

	Integer gettingAddressCount(Long offenderId);

	Integer gettingPhoneCount(Long offenderId);

	 Integer gettingInternetAddressCount(Long offenderId);

}
