package net.syscon.s4.triggers;

import net.syscon.s4.inst.casemanagement.beans.OffApV1;

public interface ApVrIudService {
	Integer apVrIudTrigger(OffApV1 newOffApV1, String operation);

}
