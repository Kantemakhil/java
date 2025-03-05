package net.syscon.s4.inst.movementexternal;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.OffenderExternalMovements;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.im.beans.BedAssignmentHistories;
import net.syscon.s4.im.beans.Dual;
import net.syscon.s4.im.beans.MovementReasons;
import net.syscon.s4.inst.movementexternal.beans.OffenderEscapes;
import net.syscon.s4.inst.schedules.bean.OffenderReleaseDetails;

/**
 * Interface OidreleaRepository
 */
public interface OidreleaRepository {
	List<MovementReasons> cgfkOffEmMovementReasonCoRecordGroup();

	List<ReferenceCodes> rgMovementReasonCodeRecordGroup();

	List<Object> cgfkchkOffEmOffEmRefMovc(ReferenceCodes paramBean);

	List<Dual> cgwhenNewFormInstancec(Dual paramBean);

	String offEmPreInsertc(OffenderExternalMovements paramBean);

	List<Object> cgfkchkOffEmOffEmMoveRsc(MovementReasons paramBean);

	List<OffenderExternalMovements> offEmSearchOffenderExternalMovements(
			OffenderExternalMovements objOffenderExternalMovements);

	List<MovementReasons> cgfkchkOffEmOffEmMovec(MovementReasons paramBean);

	Integer offEmInsertOffenderExternalMovements(List<OffenderExternalMovements> lstOffenderExternalMovements);

	List<SystemProfiles> sysPflSearchSystemProfiles(SystemProfiles objSystemProfiles);

	List<SystemProfiles> callToShowFingerprint(SystemProfiles paramBean);

	Integer offEmUpdateOffenderExternalMovements(OffenderExternalMovements list);

	Integer offBookingUpdateOffenderExternalMovements(VHeaderBlock lstOffenderExternalMovements);

	Integer omsMovementsCheckActiveSentence(OffenderExternalMovements ofExMovBean);

	OffenderExternalMovements offExtMvntsReleaseDateCheck(OffenderExternalMovements searchRecord);

	String omsMovementsCheckActiveCases(OffenderExternalMovements searchRecord);

	Integer offBkgUpdateOffenderExternalMovements(VHeaderBlock vblock);

	Integer postInsertEscape(List<OffenderEscapes> offEscList);

	String escapeCursor(String movementReasonCode);

	List<OffenderExternalMovements> movementDateComparison(Long offenderBookId);

	String offEmPostInsertc(OffenderExternalMovements obj);

	Integer postInsert(OffenderExternalMovements obj);

	String gettingProfileValue();

	String getClosedFlag(String movementCode);

	OffenderBookings gettingOldData(BigDecimal offenderBookId);
	
	BedAssignmentHistories getBedAh(Long offenderBookId);
	
	Integer updateBedAh(final BedAssignmentHistories object);
	MovementReasons gettingOldDataOffenderExternal(Long offenderBookId, Long movementSeq);
	
	String getCustodyStatus();
	
	List<OffenderReleaseDetails> getOffenderreleaseSchedule(Long offenderBookId);
	
	String getOffenderCommentText(Integer offenderBookId);
}
