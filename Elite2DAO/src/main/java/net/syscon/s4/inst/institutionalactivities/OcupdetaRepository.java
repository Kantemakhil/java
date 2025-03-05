package net.syscon.s4.inst.institutionalactivities;

import java.util.List;

import net.syscon.s4.im.beans.VOffenderCourseAttendances;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.OffAllowPayDetails;

public interface OcupdetaRepository {

	Integer generatePay(List<VOffenderCourseAttendances> updateList);

	List<VOffenderCourseAttendances> unpaidAttendanceExecuteQuery(VOffenderCourseAttendances obj);

	List<OffAllowPayDetails> getUnpaidAllowanceDetailsChildQry(VOffenderCourseAttendances obj);
	
	Integer generatePayAllow(List<VOffenderCourseAttendances> updateList);
	
	List<OffAllowPayDetails> getUnpaidGenerateDetailsPayData(VOffenderCourseAttendances searchBean);

}
