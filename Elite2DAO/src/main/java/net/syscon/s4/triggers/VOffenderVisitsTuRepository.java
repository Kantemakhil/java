package net.syscon.s4.triggers;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.inst.visitsmanagement.beans.VOffenderVisits;

public interface VOffenderVisitsTuRepository {
	List<VOffenderVisits> getVOffenderVisits();

	Integer lCount(BigDecimal offenderVisitId);

	Integer insertOffenderVisits(VOffenderVisits vOffenderVisits);

	Integer insertOffenderVisitVisitors(VOffenderVisits vOffenderVisits);

	Integer updateOffenderVisits(VOffenderVisits vOffenderVisits);

	Integer updateOffenderVisitVisitors(VOffenderVisits vOffenderVisits);
}
