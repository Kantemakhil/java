package net.syscon.s4.inst.institutionalactivities;

import java.util.List;

import net.syscon.s4.im.beans.VOffenderCourseAttendances;
import net.syscon.s4.inst.institutionalactivities.beans.prgPayBatchesBean;

public interface OciphistService {

	List<prgPayBatchesBean> prgPayBatchesExecuteQuery(prgPayBatchesBean searchBean);

	List<VOffenderCourseAttendances> payDetailsExecuteQuery(prgPayBatchesBean searchBean);

}
