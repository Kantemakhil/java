package net.syscon.s4.inst.movementexternal;

import java.util.List;

import net.syscon.s4.common.beans.OffenderExternalMovements;
import net.syscon.s4.common.beans.OffenderExternalMovementsCommitBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.im.beans.Dual;
//import net.syscon.s4.im.intake.beans.MovementReasons;
import net.syscon.s4.im.beans.MovementReasons;
import net.syscon.s4.inst.schedules.bean.OffenderReleaseDetails;


/**
 * Interface OidreleaService
 */
public interface OidreleaService {
	List<ReferenceCodes> cgfkOffEmMovementReasonCoRgroup();

	List<ReferenceCodes> rgMovementReasonCodeRgroup();

	List<Object> cgfkchkOffEmOffEmRefMovc(ReferenceCodes paramBean);

	List<Dual> cgwhenNewFormInstancec(Dual paramBean);

	String offEmPreInsertc(OffenderExternalMovements paramBean);

	List<MovementReasons> cgfkchkOffEmOffEmMovec(MovementReasons paramBean);

	List<SystemProfiles> callToShowFingerprint(SystemProfiles paramBean);

	List<Object> cgfkchkOffEmOffEmMoveRsc(MovementReasons paramBean);

	List<OffenderExternalMovements> offEmSearchOffenderExternalMovements(
			OffenderExternalMovements objOffenderExternalMovements);

	Integer offEmInsertOffenderExternalMovements(OffenderExternalMovementsCommitBean commitBean);

	List<SystemProfiles> sysPflSearchSystemProfiles(SystemProfiles objSystemProfiles);

	List<MovementReasons> cgfkOffEmMovementReasonCoRecordGroup();

	Integer offBookingUpdateOffenderExternalMovements(VHeaderBlock commitBean);

	Integer omsMovementsCheckActiveSentence(OffenderExternalMovements offExmBean);

	OffenderExternalMovements offExtMvntsReleaseDateCheck(OffenderExternalMovements paramBean);

	String omsMovementsCheckActiveCases(OffenderExternalMovements paramBean);

	Integer offBkgUpdateOffenderExternalMovements(VHeaderBlock commitBean);

	List<OffenderExternalMovements> movementDateComparison(Long offenderBookId);

	String gettingProfileValue();

	String getClosedFlag(String movementCode);
	
	Integer updateCustodyStatus(Long offenderBookId, String userName);
	
	List<OffenderReleaseDetails> getOffenderreleaseSchedule(Long offenderBoodId);
	
	String getOffenderCommentText(Integer offenderBookId);
}
