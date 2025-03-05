package net.syscon.s4.inst.securitythreatgroupsmaintenance;

import java.util.List;

import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.LivingUnits;
import net.syscon.s4.im.beans.OffenderStgAffiliations;
import net.syscon.s4.im.beans.VStgLocationMembers;

/**
 * 
 * Interface OiistgmbService
 */

public interface OiistgmbService {

	List<VStgLocationMembers> vStgLocationMembersExecuteQuery(VStgLocationMembers objMember);

	List<OffenderStgAffiliations> getNumberOfMembers(OffenderStgAffiliations paramBean);

	List<LivingUnits> livingUnitsExecuteQuery(LivingUnits objLivingUnits);

	List<AgencyLocations> getLocationDescription(AgencyLocations paramBean);

}
