package net.syscon.s4.pkgs.elite_proposed_movement;

import java.util.List;

import net.syscon.s4.inst.transportation.maintenance.OffenderLocChngDtls;
import net.syscon.s4.inst.transportation.maintenance.beans.OffenderMovementDetails;

public interface EliteProposedMovementService {

	String ifRoleAssigned(String userId, String roleNm);

	Integer getLocationSeq(Integer offenderBookId);

	Integer insertLocChngDtls(OffenderLocChngDtls insertBean);

	List<OffenderLocChngDtls> latestStatusesIntlocs(String pChoice, Integer pOffBkg, Integer pLocSeq);

	List<OffenderMovementDetails> latestStatusesExtlocs(String pChoice, Integer pOffBkg, Integer pMoveSeq);

	String ifIntrNonAssoExists(Integer offenderBookId, String currAgyId, Integer toLivingUnitId);

	String getImprisonmentStatus(Integer offenderBookId);

	Integer ifSanctionExists(Integer offenderBookId);

	Integer getStgAffiliation(Integer offenderBookId);

	Integer insertExtMvmtDtls(OffenderMovementDetails insertBean);

}
