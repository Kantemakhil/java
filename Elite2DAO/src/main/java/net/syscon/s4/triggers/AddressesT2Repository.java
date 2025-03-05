package net.syscon.s4.triggers;

public interface AddressesT2Repository {
	Integer phoneRecordsExists(Long addressId);

	Integer internetAddrRecordsExists(Long addressId);

}
