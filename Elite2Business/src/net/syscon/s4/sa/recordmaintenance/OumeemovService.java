package net.syscon.s4.sa.recordmaintenance;

import java.util.List;

import net.syscon.s4.common.beans.OffenderExternalMovements;
import net.syscon.s4.common.beans.OffenderExternalMovementsCommitBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.MovementReasons;

/**
 * Interface OumeemovService
 */
public interface OumeemovService {

	List<AgencyLocations> cgfkOffEmFromAgyLocIdRecordGroup();

	List<ReferenceCodes> cgfkOffEmFromCityRecordGroup();

	Integer offEmCommit(OffenderExternalMovementsCommitBean commitBean);

	List<MovementReasons> cgfkOffEmMovementReasonCoRecordGroup(String movementType);

	List<ReferenceCodes> cgfkOffEmMovementTypeRecordGroup();

	String ValidateSeqNum(OffenderExternalMovements paramBean);

	List<ReferenceCodes> cgfkOffEmDirectionCodeRecordGroup();

	List<OffenderExternalMovements> offEmExecuteQuery(OffenderExternalMovements objOffenderExternalMovements);

	List<AgencyLocations> cgfkOffEmToAgyLocIdRecordGroup();

	List<ReferenceCodes> cgfkOffEmToCityRecordGroup();

}
