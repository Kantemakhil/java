package net.syscon.s4.triggers;

import net.syscon.s4.of.payroll.maintenance.SystemEvents;

public interface SystemEventsTjnRepository {
	SystemEvents getSystemEvents(SystemEvents systemEvents);

	Integer insert(SystemEvents systemEvents);

	Integer update(SystemEvents systemEvents);

	Integer delete(SystemEvents ystemEvents);
}
