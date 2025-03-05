package net.syscon.s4.inst.institutionalactivities;

import java.util.List;

import net.syscon.s4.im.beans.VOffenderCourseAttendances;

public interface OcupdetaService {

	Integer generatePay(List<VOffenderCourseAttendances> updateList);

	List<VOffenderCourseAttendances> unpaidAttendanceExecuteQuery(VOffenderCourseAttendances searchBean);
	
	List<VOffenderCourseAttendances> postUpdateExecuteQuery(VOffenderCourseAttendances obj);

}
