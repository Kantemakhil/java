package net.syscon.s4.pkgs.oidstoju;

import net.syscon.s4.inst.movementexternal.beans.OffenderPendNotifications;
import net.syscon.s4.inst.schedules.bean.OffenderIndSchedules;

public interface OidstojuPkgRepository {

	String recUpdCur(final OffenderIndSchedules offIndSch);

	OffenderPendNotifications notiExisCur(final Integer eventId);

	void deleteOffenderNotCompletions(final Long notiSeq, final Long notiMoveSeq, final Integer vOffBookId);

	Integer chNotCur(final Long notiSeq, final Long notiMoveSeq, Integer vOffBookId);

	void updateOffenderNotCompletions(final Long notiSeq, final Long notiMoveSeq, final String userName);

}
