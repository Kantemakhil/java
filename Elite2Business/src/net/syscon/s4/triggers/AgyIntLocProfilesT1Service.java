package net.syscon.s4.triggers;

public interface AgyIntLocProfilesT1Service {
	Boolean isChanged(String pOldVal, String pNewVal);
	Integer agyIntLocProfilesT1Trigger(String operationType,AgyIntLocAmendments oldRef,AgyIntLocAmendments newRef);
	
}
