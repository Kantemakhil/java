package net.syscon.s4.triggers;

import net.syscon.s4.common.beans.OffenderPaymentSchedules;

public interface OffenderPaymentSchedulesTjnService {

	Integer OffenderPaymentSchedulesTjn(final OffenderPaymentSchedules newObj, final OffenderPaymentSchedules oldObj, final String operation);

}
