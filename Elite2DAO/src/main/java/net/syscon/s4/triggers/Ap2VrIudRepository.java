package net.syscon.s4.triggers;

import java.math.BigDecimal;

import net.syscon.s4.inst.casemanagement.beans.OffApV2;

public interface Ap2VrIudRepository {

	OffApV2 getAp2VrIud(BigDecimal offActionPlanId);

}
