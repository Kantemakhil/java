package net.syscon.s4.triggers;

import net.syscon.s4.genericservices.CustomException;

public interface PhonesT1Service {
	Integer phonesT1Trigger(String ownerClass, Long ownerId, Long ownerSeq, String string) throws CustomException;
}
