package net.syscon.s4.workspace;

import java.util.List;

import net.syscon.s4.common.beans.OffendersIntakeSummary;

public interface OwintakeService {

	List<OffendersIntakeSummary> getOffendersSummary(String caseLoadId);
}
