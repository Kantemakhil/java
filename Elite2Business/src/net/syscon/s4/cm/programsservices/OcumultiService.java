package net.syscon.s4.cm.programsservices;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedulesCommitBean;

/**
 * Interface OcumultiService
 */
public interface OcumultiService {
	List<VOffenderAllSchedules> offBlockExecuteQuery(VOffenderAllSchedules objVOffenderAllSchedules);

	Integer offBlockCommit(VOffenderAllSchedulesCommitBean commitBean);

	List<ReferenceCodes> rgYnFlagRecordGroup();

}
