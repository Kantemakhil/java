package net.syscon.s4.triggers;

import java.util.List;

import net.syscon.s4.inst.movements.beans.OffenderInterMvmtLocations;

public interface OffenderInterMvmtLocT1Repository {
	Integer update(List<OffenderInterMvmtLocations> offenderInterMvmtLocationsList);
}
