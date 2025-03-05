package net.syscon.s4.triggers;

import net.syscon.s4.genericservices.CustomException;

public interface InternetAddressesT1Service {
	Integer internetAddressesT1Trigger(String ownerClass, Long ownerId, Long ownerSeq, String ownerCode) throws CustomException;
}
