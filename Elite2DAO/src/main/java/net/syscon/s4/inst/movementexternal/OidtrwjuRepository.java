package net.syscon.s4.inst.movementexternal;

import java.util.List;

import net.syscon.s4.common.beans.OffenderExternalMovements;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.Dual;
import net.syscon.s4.im.intake.beans.MovementReasons;

public interface OidtrwjuRepository {
	
	List<MovementReasons> cgfkOffEmMovementReasonCoRecordGroup();

    List<AgencyLocations> cgfkOffEmToAgyLocIdRecordGroup(String agyLocId);

	List<Object> cgfkchkoffemoffemmoversc(MovementReasons paramBean);

	List<Object> cgfkchkoffemoffemmovetoc(MovementReasons paramBean);

	List<Object> cgfkchkoffemoffemagylocc(AgencyLocations paramBean);

	List<Dual> cgwhennewforminstancec(Dual paramBean);

	OffenderExternalMovements offempreinsertc(OffenderExternalMovements paramBean);

	List<Object> cgfkchkoffemoffemrefmovc(ReferenceCodes paramBean);

	List<OffenderExternalMovements> offEmSearchOffenderExternalMovements(
			OffenderExternalMovements objOffenderExternalMovements);

	Integer offEmInsertOffenderExternalMovements(List<OffenderExternalMovements> lstOffenderExternalMovements);

	List<SystemProfiles> sysPflSearchSystemProfiles(SystemProfiles objSystemProfiles);

	Integer offExternalMovmentssgetMaxBookIdMovmentSeq(Long offenderBookId);

	Integer offEmUpdateOffenderExternalMovements(List<OffenderExternalMovements> updateList);

	Integer checkWaitListAndLocation(OffenderExternalMovements bean);	

	Integer suspendAllocations(OffenderExternalMovements bean);

	Integer endWaitlistAndAllocations(OffenderExternalMovements bean);
	
	OffenderExternalMovements getOffenderExternalMovements(Long offenderBookId,Long movementSeq);
}
