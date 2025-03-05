package net.syscon.s4.triggers;

import net.syscon.s4.common.beans.Orders;

public interface OrdersTjnService {

	void ordersTjn(final Orders newRecord, final Orders oldRecord, String operation);
}
