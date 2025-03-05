package net.syscon.s4.triggers;

import java.math.BigDecimal;

import net.syscon.s4.genericservices.CustomException;

public interface AddressesT1Service {

	Integer addresesT1Trigger(String ownerClass, Long ownerId, BigDecimal ownerSeq, String string) throws CustomException;

}
