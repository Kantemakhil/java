package net.syscon.s4.inst.movementexternal;

import java.util.List;

import net.syscon.s4.common.beans.OffenderExternalMovements;
import net.syscon.s4.common.beans.OffenderExternalMovementsCommitBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.Dual;
import net.syscon.s4.im.intake.beans.MovementReasons;

public interface OidtrwjuService {

	List<MovementReasons> cgfkOffEmMovementReasonCoRecordGroup();

	List<AgencyLocations> cgfkOffEmToAgyLocIdRecordGroup(String agyLocId);

	OffenderExternalMovements offempreinsertc(OffenderExternalMovements paramBean);

	List<Object> cgfkchkoffemoffemagylocc(AgencyLocations paramBean);

	List<Dual> cgwhennewforminstancec(Dual paramBean);

	List<OffenderExternalMovements> offEmSearchOffenderExternalMovements(
			OffenderExternalMovements objOffenderExternalMovements);

	List<Object> cgfkchkoffemoffemrefmovc(ReferenceCodes paramBean);

	List<Object> cgfkchkoffemoffemmoversc(MovementReasons paramBean);

	List<Object> cgfkchkoffemoffemmovetoc(MovementReasons paramBean);

	Integer offEmInsertOffenderExternalMovements(List<OffenderExternalMovements> lstOffenderExternalMovements);

	List<SystemProfiles> sysPflSearchSystemProfiles(SystemProfiles objSystemProfiles);
	
	 Integer offEmCommit (OffenderExternalMovementsCommitBean commitBean);

	Integer checkWaitListAndLocation(OffenderExternalMovements bean);

	Integer suspendAllocations(OffenderExternalMovements bean);

	Integer endWaitlistAndAllocations(OffenderExternalMovements bean);

	
}
