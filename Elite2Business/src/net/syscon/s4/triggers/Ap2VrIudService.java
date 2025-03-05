package net.syscon.s4.triggers;

import net.syscon.s4.inst.casemanagement.beans.OffApV2;

public interface Ap2VrIudService {

	Integer ap2VrIudTrigger(OffApV2 newOffApV2,String action);

}
