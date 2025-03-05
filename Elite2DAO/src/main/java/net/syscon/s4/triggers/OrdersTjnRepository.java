package net.syscon.s4.triggers;

import net.syscon.s4.common.beans.Orders;

public interface OrdersTjnRepository {
	Integer insertOrdersTjn(final Orders obj);

	Integer updateOrdersTjn(final Orders obj);

	Integer deleteOrdersTjn(final Orders obj);
}
