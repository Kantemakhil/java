package net.syscon.s4.triggers;

import java.util.List;

import net.syscon.s4.common.beans.Addresses;

public interface AddresesTJNRepository {

	Integer addressTJNTrigger(List<Addresses> lstAddresses);

}
