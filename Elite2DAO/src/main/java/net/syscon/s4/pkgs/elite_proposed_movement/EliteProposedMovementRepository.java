package net.syscon.s4.pkgs.elite_proposed_movement;

import java.util.List;

import net.syscon.s4.inst.transportation.maintenance.OffenderLocChngDtls;
import net.syscon.s4.inst.transportation.maintenance.beans.OffenderMovementDetails;

public interface EliteProposedMovementRepository {

	String ifRoleAssigned(String userId, String roleNm);

	Integer getSeqCurInst(Integer offenderBookId);

	Integer getSeqCur(Integer pOffBkg, Integer pLocSeq);

	Integer insertLocChngDtls(OffenderLocChngDtls insertBean);

	List<OffenderLocChngDtls> statusInmCurInstLoc(String pChoice, Integer pOffBkg, Integer pMoveSeq);

	List<OffenderLocChngDtls> maxStatusInmCurInstLoc(String pChoice, Integer pOffBkg, Integer pMoveSeq);

	String ifIntrNonAssoExists(Integer offenderBookId, String currAgyId, Integer toLivingUnitId);

	String impStatusCur(Integer offenderBookId);

	Integer sanctionCur(Integer offenderBookId);

	Integer stgAffiCur(Integer offenderBookId);

	List<OffenderMovementDetails> statusInmCur(String pChoice, Integer pOffBkg, Integer pMoveSeq);

	List<OffenderMovementDetails> maxStatusInmCur(String pChoice, Integer pOffBkg, Integer pMoveSeq);

	OffenderMovementDetails statusNonInmCur();

	OffenderMovementDetails maxStatusNonInmCur();

	Integer insertExtMvmtDtls(OffenderMovementDetails insertBean);

	Long getSeqCur(Long pOffBkg, Long pMoveSeq);

}
