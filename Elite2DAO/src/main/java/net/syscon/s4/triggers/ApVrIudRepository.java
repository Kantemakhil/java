package net.syscon.s4.triggers;

import java.math.BigDecimal;

import net.syscon.s4.inst.casemanagement.beans.OffApV1;

public interface ApVrIudRepository {
	OffApV1 getApVrIud(BigDecimal offActionPlanId);

}
