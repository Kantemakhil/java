package net.syscon.s4.inst.institutionalactivities;

import java.util.List;

import org.json.JSONObject;

import net.syscon.s4.common.beans.OffenderCourseAttendance;
import net.syscon.s4.im.beans.VOffenderCourseAttendances;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.OffAllowPayDetailsCommitBean;

public interface OcdcgpayService {

	List<VOffenderCourseAttendances> unpaidAttendanceExecuteQuery(VOffenderCourseAttendances searchBean);

	JSONObject generatePay(List<VOffenderCourseAttendances> updateList);

	List<OffenderCourseAttendance> getFromToDates();

	Integer saveOffAllowPayDetValues(OffAllowPayDetailsCommitBean commitBean);
}
