package net.syscon.s4.inst.incidentsoic;

import java.util.List;

import net.syscon.s4.im.incidentsoic.beans.ExternalInvestigationOffenses;

public interface OcucieidRepository {

	Integer checkForInsertOrUpdateExternalInvst(final String userName);

	Integer checkForDeleteExternalInvst(final String userName);

	List<ExternalInvestigationOffenses> getAllExternalInvstDetails( final ExternalInvestigationOffenses extInvOffe);

	Integer insertExternalInvstDetails(List<ExternalInvestigationOffenses> insertList);

	Integer updateExternalInvstDetails(List<ExternalInvestigationOffenses> updateList);

	Integer deteleExternalInvstDetails(List<ExternalInvestigationOffenses> deleteList);

}
