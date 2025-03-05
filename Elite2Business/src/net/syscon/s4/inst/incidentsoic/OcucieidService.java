package net.syscon.s4.inst.incidentsoic;

import java.util.List;

import net.syscon.s4.im.incidentsoic.beans.ExternalInvestigationOffenses;
import net.syscon.s4.im.incidentsoic.beans.ExternalInvestigationOffensesCommitBean;

public interface OcucieidService {

	ExternalInvestigationOffenses checkForInsertOrUpdateAndDeleteExternalInvst(final String userName);

	List<ExternalInvestigationOffenses> getAllExternalInvstDetails(
			final ExternalInvestigationOffenses extInvOffe);

	Integer insertOrUpdateOrDelete(ExternalInvestigationOffensesCommitBean commitBean);

}
