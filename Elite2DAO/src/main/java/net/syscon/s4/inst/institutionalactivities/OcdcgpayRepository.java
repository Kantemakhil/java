package net.syscon.s4.inst.institutionalactivities;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.common.beans.OffenderCourseAttendance;
import net.syscon.s4.im.beans.VOffenderCourseAttendances;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.OffAllowPayDetails;

public interface OcdcgpayRepository {

	List<VOffenderCourseAttendances> unpaidAttendanceExecuteQuery(VOffenderCourseAttendances searchBean);

	Integer generatePay(List<VOffenderCourseAttendances> updateList);

	Integer getBatchId();

	List<OffenderCourseAttendance> getFromToDates();

	Integer insertPrgPayBatches(VOffenderCourseAttendances object);

	BigDecimal getSystemPayRate(String eventType, long programId, long crsActyId, long offPrgRefId);

	Integer insertPrgPayBatchOffCrs(List<VOffenderCourseAttendances> updateList);

	List<OffAllowPayDetails> getUnpaidAllowanceDetailsQry(VOffenderCourseAttendances obj);

	Integer insertPayBatchOffAllow(List<VOffenderCourseAttendances> updateList);
	
	Integer generateAllowPay(List<VOffenderCourseAttendances> updateList);
	
}
