package net.syscon.s4.triggers;

import net.syscon.s4.im.beans.OffenderAlerts;

public interface OffenderAlertsTwfRepository {
	OffenderAlerts getOffenderAlerts(Long offenderBookId, Long alertSeq);
}
