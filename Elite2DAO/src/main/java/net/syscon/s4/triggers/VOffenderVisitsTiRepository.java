package net.syscon.s4.triggers;

import java.math.BigDecimal;

import net.syscon.s4.inst.visitsmanagement.beans.VOffenderVisits;

public interface VOffenderVisitsTiRepository {
	Integer lCount(BigDecimal offenderVisitId);

	Integer insertOffenderVisits(VOffenderVisits vOffenderVisits);

	Integer insertOffenderVisitVisitors(VOffenderVisits vOffenderVisits);
}
