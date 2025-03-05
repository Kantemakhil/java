package net.syscon.s4.inst.movementexternal;

import java.util.List;

import net.syscon.s4.common.beans.OffenderExternalMovements;
import net.syscon.s4.common.beans.OffenderExternalMovementsCommitBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfilesCommitBean;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.SystemProfiles;

/**
 * Interface OidunctaService
 */
public interface OidunctaService {
	List<ReferenceCodes> cgfkOffEmMovementReasonCoRecordGroup(String movementType);

	OffenderExternalMovements offEmExecuteQuery(OffenderExternalMovements objOffenderExternalMovements);

	Integer offEmCommit(OffenderExternalMovementsCommitBean commitBean);

	List<ReferenceCodes> cgfkOffEmToCityRecordGroup();

	List<ReferenceCodes> cgfkOffEmMovementTypeRecordGroup();

	List<SystemProfiles> sysPflExecuteQuery(SystemProfiles objSystemProfiles);

	Integer sysPflCommit(SystemProfilesCommitBean commitBean);

	List<AgencyLocations> cgfkOffEmToAgyLocIdRecordGroup(String directionCode, String fromAgyLocId);

	List<AgencyLocations> cgfkchkOffEmOffEmAgyLoc();

	List<AgencyLocations> cgfkOffEmFromAgyLocIdRecordGroup(String toagyLocId);

}
