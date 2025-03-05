package net.syscon.s4.triggers;

import net.syscon.s4.common.beans.OffenderPaymentSchedules;

public interface OffenderPaymentSchedulesTjnRepository {
	
	Integer insertOffenderPaymentSchedulesTjn(final OffenderPaymentSchedules obj);

	Integer updateOffenderPaymentSchedulesTjn(final OffenderPaymentSchedules obj);

	Integer deleteOffenderPaymentSchedulesTjn(final OffenderPaymentSchedules obj);


}
