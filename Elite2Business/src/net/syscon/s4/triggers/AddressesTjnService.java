/**
 * 
 */
package net.syscon.s4.triggers;

import net.syscon.s4.common.beans.Addresses;

public interface AddressesTjnService {

	void addressesTjn(Addresses newBean, Addresses oldBean, String operation);
}
