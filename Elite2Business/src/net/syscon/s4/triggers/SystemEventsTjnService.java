package net.syscon.s4.triggers;

import java.util.List;

import net.syscon.s4.of.payroll.maintenance.SystemEvents;

public interface SystemEventsTjnService {
	Integer systemEventsTjnTgr(List<SystemEvents> systemEventsList, String sqlOperation);
}
