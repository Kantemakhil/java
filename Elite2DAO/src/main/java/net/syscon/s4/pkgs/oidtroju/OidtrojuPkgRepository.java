package net.syscon.s4.pkgs.oidtroju;

import java.util.List;

import net.syscon.s4.common.beans.MovementReason;
import net.syscon.s4.common.beans.OffenderExternalMovements;
import net.syscon.s4.inst.legalscreens.releasenotification.OffenderReleaseNotis;
import net.syscon.s4.inst.movementexternal.beans.OffenderNotCompletions;
import net.syscon.s4.inst.movementexternal.beans.OffenderPendNotifications;

public interface OidtrojuPkgRepository {

	MovementReason getNotifInfoCur(final OffenderExternalMovements offExtMov);

	Long getCntPendCur(final OffenderExternalMovements offExtMov);

	Long getCheckforNotifCur(final OffenderExternalMovements offExtMov);

	List<OffenderReleaseNotis> getAllNotifRecCur(final OffenderExternalMovements offExtMov);

	Long getNextNotiMovSeqCur(final Long cpOffBookId, final Long cpNotiSeq);

	Integer insertOffenderPendNotification(final OffenderPendNotifications bean);

	Integer insertOffenderNotCompletion(final OffenderNotCompletions offNtCpm);

}
