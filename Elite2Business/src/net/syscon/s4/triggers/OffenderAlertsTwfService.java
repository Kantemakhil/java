package net.syscon.s4.triggers;

import net.syscon.s4.im.beans.OffenderAlerts;

public interface OffenderAlertsTwfService {
	Integer createOffenderAlertMsg(OffenderAlerts OffenderAlerts, String trgType);
	Integer offenderAlertsTwfTrigger(OffenderAlerts OffenderAlerts, String sqlOperation);
}
