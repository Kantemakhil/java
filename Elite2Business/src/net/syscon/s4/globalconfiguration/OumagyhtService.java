package net.syscon.s4.globalconfiguration;

import java.util.List;

import net.syscon.s4.im.beans.AgencyLocationAmendments;
import net.syscon.s4.im.beans.AgencyLocations;

/**
 * Interface OumagyhtService
 */
public interface OumagyhtService {
	List<AgencyLocations> agyLocExecuteQuery();

	List<AgencyLocationAmendments> agyLocAmExecuteQuery(AgencyLocationAmendments objAgencyLocationAmendments);

}
