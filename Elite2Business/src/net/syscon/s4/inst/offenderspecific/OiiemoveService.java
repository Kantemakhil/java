package net.syscon.s4.inst.offenderspecific;
import java.util.List;

import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.OffenderExternalMovements;
import net.syscon.s4.common.beans.OffenderExternalMovementsCommitBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.im.beans.AgencyLocations;
/**
 * Interface OiiemoveService 
 */
public interface OiiemoveService  {
	List<OffenderExternalMovements> offEmExecuteQuery(OffenderExternalMovements objOffExtMov);
	
	List<OffenderExternalMovements> offEm1ExecuteQuery(OffenderExternalMovements objOffExtMov);

	List<ReferenceCodes> rgOffEm1DirectionCodeRecordGroup();

	ReferenceCodes cgfkchkOffEmOffEmDirecti(ReferenceCodes paramBean);

	Integer offEmCommit(OffenderExternalMovementsCommitBean commitBean);

	List<ReferenceCodes> rgOffEmMovementReasonCoRecordGroup();

	ReferenceCodes cgfkchkOffEmOffEmMoveRe(ReferenceCodes paramBean);

	List<ReferenceCodes> rgOffEmDirectionCodeRecordGroup();

	SystemProfiles getProfileValue(SystemProfiles paramBean);

	ReferenceCodes cgfkchkOffEm1OffEmDirect(ReferenceCodes paramBean);

	ReferenceCodes cgfkchkOffEm1OffEmMov2(ReferenceCodes paramBean);

	List<ReferenceCodes> rgOffEm1MovementTypeRecordGroup();

	Integer offEm1Commit(OffenderExternalMovementsCommitBean commitBean);

	ReferenceCodes cgfkchkOffEm1OffEmRefMo(ReferenceCodes paramBean);

	OffenderBookings offEm1PostQuery(OffenderBookings paramBean);

	List<ReferenceCodes> rgOffEmMovementTypeRecordGroup();

	List<ReferenceCodes> rgOffEm1MovementReasonCRecordGroup();

	OffenderExternalMovements offBkgOnCheckDeleteMaster(OffenderExternalMovements paramBean);
	
	List<AgencyLocations> alAgyLocIdRgRecordGroup();

}
